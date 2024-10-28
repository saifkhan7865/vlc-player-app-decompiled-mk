package io.netty.handler.ssl;

import io.netty.internal.tcnative.SSLSession;
import io.netty.internal.tcnative.SSLSessionCache;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.SystemPropertyUtil;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.security.cert.X509Certificate;

class OpenSslSessionCache implements SSLSessionCache {
    private static final int DEFAULT_CACHE_SIZE;
    private static final OpenSslSession[] EMPTY_SESSIONS = new OpenSslSession[0];
    private final OpenSslEngineMap engineMap;
    /* access modifiers changed from: private */
    public final AtomicInteger maximumCacheSize = new AtomicInteger(DEFAULT_CACHE_SIZE);
    private int sessionCounter;
    private final AtomicInteger sessionTimeout = new AtomicInteger(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION);
    private final Map<OpenSslSessionId, NativeSslSession> sessions = new LinkedHashMap<OpenSslSessionId, NativeSslSession>() {
        private static final long serialVersionUID = -7773696788135734448L;

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry<OpenSslSessionId, NativeSslSession> entry) {
            int i = OpenSslSessionCache.this.maximumCacheSize.get();
            if (i < 0 || size() <= i) {
                return false;
            }
            OpenSslSessionCache.this.removeSessionWithId(entry.getKey());
            return false;
        }
    };

    /* access modifiers changed from: protected */
    public boolean sessionCreated(NativeSslSession nativeSslSession) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void sessionRemoved(NativeSslSession nativeSslSession) {
    }

    /* access modifiers changed from: package-private */
    public void setSession(long j, String str, int i) {
    }

    static {
        int i = SystemPropertyUtil.getInt("javax.net.ssl.sessionCacheSize", 20480);
        if (i >= 0) {
            DEFAULT_CACHE_SIZE = i;
        } else {
            DEFAULT_CACHE_SIZE = 20480;
        }
    }

    OpenSslSessionCache(OpenSslEngineMap openSslEngineMap) {
        this.engineMap = openSslEngineMap;
    }

    /* access modifiers changed from: package-private */
    public final void setSessionTimeout(int i) {
        if (this.sessionTimeout.getAndSet(i) > i) {
            clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final int getSessionTimeout() {
        return this.sessionTimeout.get();
    }

    /* access modifiers changed from: package-private */
    public final void setSessionCacheSize(int i) {
        if (((long) this.maximumCacheSize.getAndSet(i)) > ((long) i) || i == 0) {
            clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final int getSessionCacheSize() {
        return this.maximumCacheSize.get();
    }

    private void expungeInvalidSessions() {
        if (!this.sessions.isEmpty()) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator<Map.Entry<OpenSslSessionId, NativeSslSession>> it = this.sessions.entrySet().iterator();
            while (it.hasNext()) {
                NativeSslSession nativeSslSession = (NativeSslSession) it.next().getValue();
                if (!nativeSslSession.isValid(currentTimeMillis)) {
                    it.remove();
                    notifyRemovalAndFree(nativeSslSession);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean sessionCreated(long r9, long r11) {
        /*
            r8 = this;
            io.netty.handler.ssl.OpenSslEngineMap r0 = r8.engineMap
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine r9 = r0.get(r9)
            r10 = 0
            if (r9 != 0) goto L_0x000a
            return r10
        L_0x000a:
            io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession r7 = new io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession
            java.lang.String r3 = r9.getPeerHost()
            int r4 = r9.getPeerPort()
            int r0 = r8.getSessionTimeout()
            long r0 = (long) r0
            r5 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 * r0
            r0 = r7
            r1 = r11
            r0.<init>(r1, r3, r4, r5)
            io.netty.handler.ssl.OpenSslSessionId r11 = r7.sessionId()
            r9.setSessionId(r11)
            monitor-enter(r8)
            int r9 = r8.sessionCounter     // Catch:{ all -> 0x0057 }
            r11 = 1
            int r9 = r9 + r11
            r8.sessionCounter = r9     // Catch:{ all -> 0x0057 }
            r12 = 255(0xff, float:3.57E-43)
            if (r9 != r12) goto L_0x0039
            r8.sessionCounter = r10     // Catch:{ all -> 0x0057 }
            r8.expungeInvalidSessions()     // Catch:{ all -> 0x0057 }
        L_0x0039:
            boolean r9 = r8.sessionCreated(r7)     // Catch:{ all -> 0x0057 }
            if (r9 != 0) goto L_0x0044
            r7.close()     // Catch:{ all -> 0x0057 }
            monitor-exit(r8)     // Catch:{ all -> 0x0057 }
            return r10
        L_0x0044:
            java.util.Map<io.netty.handler.ssl.OpenSslSessionId, io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession> r9 = r8.sessions     // Catch:{ all -> 0x0057 }
            io.netty.handler.ssl.OpenSslSessionId r10 = r7.sessionId()     // Catch:{ all -> 0x0057 }
            java.lang.Object r9 = r9.put(r10, r7)     // Catch:{ all -> 0x0057 }
            io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession r9 = (io.netty.handler.ssl.OpenSslSessionCache.NativeSslSession) r9     // Catch:{ all -> 0x0057 }
            if (r9 == 0) goto L_0x0055
            r8.notifyRemovalAndFree(r9)     // Catch:{ all -> 0x0057 }
        L_0x0055:
            monitor-exit(r8)     // Catch:{ all -> 0x0057 }
            return r11
        L_0x0057:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0057 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSslSessionCache.sessionCreated(long, long):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r2.updateLastAccessedTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        return r2.session();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long getSession(long r2, byte[] r4) {
        /*
            r1 = this;
            io.netty.handler.ssl.OpenSslSessionId r2 = new io.netty.handler.ssl.OpenSslSessionId
            r2.<init>(r4)
            monitor-enter(r1)
            java.util.Map<io.netty.handler.ssl.OpenSslSessionId, io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession> r3 = r1.sessions     // Catch:{ all -> 0x0040 }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ all -> 0x0040 }
            io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession r2 = (io.netty.handler.ssl.OpenSslSessionCache.NativeSslSession) r2     // Catch:{ all -> 0x0040 }
            r3 = -1
            if (r2 != 0) goto L_0x0014
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            return r3
        L_0x0014:
            boolean r0 = r2.isValid()     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0037
            boolean r0 = r2.upRef()     // Catch:{ all -> 0x0040 }
            if (r0 != 0) goto L_0x0021
            goto L_0x0037
        L_0x0021:
            boolean r3 = r2.shouldBeSingleUse()     // Catch:{ all -> 0x0040 }
            if (r3 == 0) goto L_0x002e
            io.netty.handler.ssl.OpenSslSessionId r3 = r2.sessionId()     // Catch:{ all -> 0x0040 }
            r1.removeSessionWithId(r3)     // Catch:{ all -> 0x0040 }
        L_0x002e:
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            r2.updateLastAccessedTime()
            long r2 = r2.session()
            return r2
        L_0x0037:
            io.netty.handler.ssl.OpenSslSessionId r2 = r2.sessionId()     // Catch:{ all -> 0x0040 }
            r1.removeSessionWithId(r2)     // Catch:{ all -> 0x0040 }
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            return r3
        L_0x0040:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSslSessionCache.getSession(long, byte[]):long");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void removeSessionWithId(OpenSslSessionId openSslSessionId) {
        NativeSslSession remove = this.sessions.remove(openSslSessionId);
        if (remove != null) {
            notifyRemovalAndFree(remove);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean containsSessionWithId(OpenSslSessionId openSslSessionId) {
        return this.sessions.containsKey(openSslSessionId);
    }

    private void notifyRemovalAndFree(NativeSslSession nativeSslSession) {
        sessionRemoved(nativeSslSession);
        nativeSslSession.free();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized io.netty.handler.ssl.OpenSslSession getSession(io.netty.handler.ssl.OpenSslSessionId r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.Map<io.netty.handler.ssl.OpenSslSessionId, io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession> r0 = r1.sessions     // Catch:{ all -> 0x001d }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x001d }
            io.netty.handler.ssl.OpenSslSessionCache$NativeSslSession r2 = (io.netty.handler.ssl.OpenSslSessionCache.NativeSslSession) r2     // Catch:{ all -> 0x001d }
            if (r2 == 0) goto L_0x001b
            boolean r0 = r2.isValid()     // Catch:{ all -> 0x001d }
            if (r0 != 0) goto L_0x001b
            io.netty.handler.ssl.OpenSslSessionId r2 = r2.sessionId()     // Catch:{ all -> 0x001d }
            r1.removeSessionWithId(r2)     // Catch:{ all -> 0x001d }
            monitor-exit(r1)
            r2 = 0
            return r2
        L_0x001b:
            monitor-exit(r1)
            return r2
        L_0x001d:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSslSessionCache.getSession(io.netty.handler.ssl.OpenSslSessionId):io.netty.handler.ssl.OpenSslSession");
    }

    /* access modifiers changed from: package-private */
    public final List<OpenSslSessionId> getIds() {
        OpenSslSession[] openSslSessionArr;
        synchronized (this) {
            openSslSessionArr = (OpenSslSession[]) this.sessions.values().toArray(EMPTY_SESSIONS);
        }
        ArrayList arrayList = new ArrayList(openSslSessionArr.length);
        for (OpenSslSession openSslSession : openSslSessionArr) {
            if (openSslSession.isValid()) {
                arrayList.add(openSslSession.sessionId());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public synchronized void clear() {
        Iterator<Map.Entry<OpenSslSessionId, NativeSslSession>> it = this.sessions.entrySet().iterator();
        while (it.hasNext()) {
            it.remove();
            notifyRemovalAndFree((NativeSslSession) it.next().getValue());
        }
    }

    static final class NativeSslSession implements OpenSslSession {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final ResourceLeakDetector<NativeSslSession> LEAK_DETECTOR = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(NativeSslSession.class);
        private final long creationTime;
        private boolean freed;
        private final OpenSslSessionId id;
        private volatile long lastAccessedTime;
        private final ResourceLeakTracker<NativeSslSession> leakTracker;
        private final String peerHost;
        private final int peerPort;
        private final long session;
        private final long timeout;
        private volatile boolean valid = true;

        public String getCipherSuite() {
            return null;
        }

        public String getProtocol() {
            return null;
        }

        public OpenSslSessionContext getSessionContext() {
            return null;
        }

        public Object getValue(String str) {
            return null;
        }

        public void removeValue(String str) {
        }

        static {
            Class<OpenSslSessionCache> cls = OpenSslSessionCache.class;
        }

        NativeSslSession(long j, String str, int i, long j2) {
            long currentTimeMillis = System.currentTimeMillis();
            this.creationTime = currentTimeMillis;
            this.lastAccessedTime = currentTimeMillis;
            this.session = j;
            this.peerHost = str;
            this.peerPort = i;
            this.timeout = j2;
            this.id = new OpenSslSessionId(SSLSession.getSessionId(j));
            this.leakTracker = LEAK_DETECTOR.track(this);
        }

        public void setSessionId(OpenSslSessionId openSslSessionId) {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public boolean shouldBeSingleUse() {
            return SSLSession.shouldBeSingleUse(this.session);
        }

        /* access modifiers changed from: package-private */
        public long session() {
            return this.session;
        }

        /* access modifiers changed from: package-private */
        public boolean upRef() {
            return SSLSession.upRef(this.session);
        }

        /* access modifiers changed from: package-private */
        public synchronized void free() {
            close();
            SSLSession.free(this.session);
        }

        /* access modifiers changed from: package-private */
        public void close() {
            this.freed = true;
            invalidate();
            ResourceLeakTracker<NativeSslSession> resourceLeakTracker = this.leakTracker;
            if (resourceLeakTracker != null) {
                resourceLeakTracker.close(this);
            }
        }

        public OpenSslSessionId sessionId() {
            return this.id;
        }

        /* access modifiers changed from: package-private */
        public boolean isValid(long j) {
            return this.creationTime + this.timeout >= j && this.valid;
        }

        public void setLocalCertificate(Certificate[] certificateArr) {
            throw new UnsupportedOperationException();
        }

        public void tryExpandApplicationBufferSize(int i) {
            throw new UnsupportedOperationException();
        }

        public void handshakeFinished(byte[] bArr, String str, String str2, byte[] bArr2, byte[][] bArr3, long j, long j2) {
            throw new UnsupportedOperationException();
        }

        public byte[] getId() {
            return this.id.cloneBytes();
        }

        public long getCreationTime() {
            return this.creationTime;
        }

        /* access modifiers changed from: package-private */
        public void updateLastAccessedTime() {
            this.lastAccessedTime = System.currentTimeMillis();
        }

        public long getLastAccessedTime() {
            return this.lastAccessedTime;
        }

        public void invalidate() {
            this.valid = false;
        }

        public boolean isValid() {
            return isValid(System.currentTimeMillis());
        }

        public void putValue(String str, Object obj) {
            throw new UnsupportedOperationException();
        }

        public String[] getValueNames() {
            return EmptyArrays.EMPTY_STRINGS;
        }

        public Certificate[] getPeerCertificates() {
            throw new UnsupportedOperationException();
        }

        public Certificate[] getLocalCertificates() {
            throw new UnsupportedOperationException();
        }

        public X509Certificate[] getPeerCertificateChain() {
            throw new UnsupportedOperationException();
        }

        public Principal getPeerPrincipal() {
            throw new UnsupportedOperationException();
        }

        public Principal getLocalPrincipal() {
            throw new UnsupportedOperationException();
        }

        public String getPeerHost() {
            return this.peerHost;
        }

        public int getPeerPort() {
            return this.peerPort;
        }

        public int getPacketBufferSize() {
            return ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE;
        }

        public int getApplicationBufferSize() {
            return ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH;
        }

        public int hashCode() {
            return this.id.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OpenSslSession)) {
                return false;
            }
            return this.id.equals(((OpenSslSession) obj).sessionId());
        }
    }
}
