package io.netty.handler.ssl;

import io.ktor.http.ContentDisposition;
import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.util.LazyJavaxX509Certificate;
import io.netty.handler.ssl.util.LazyX509Certificate;
import io.netty.internal.tcnative.AsyncTask;
import io.netty.internal.tcnative.Buffer;
import io.netty.internal.tcnative.SSL;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.security.cert.X509Certificate;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class ReferenceCountedOpenSslEngine extends SSLEngine implements ReferenceCounted, ApplicationProtocolAccessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final SSLEngineResult CLOSED_NOT_HANDSHAKING = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);
    /* access modifiers changed from: private */
    public static final X509Certificate[] JAVAX_CERTS_NOT_SUPPORTED = new X509Certificate[0];
    static final int MAX_PLAINTEXT_LENGTH = SSL.SSL_MAX_PLAINTEXT_LENGTH;
    static final int MAX_RECORD_SIZE = SSL.SSL_MAX_RECORD_LENGTH;
    private static final SSLEngineResult NEED_UNWRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_UNWRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final int[] OPENSSL_OP_NO_PROTOCOLS = {SSL.SSL_OP_NO_SSLv2, SSL.SSL_OP_NO_SSLv3, SSL.SSL_OP_NO_TLSv1, SSL.SSL_OP_NO_TLSv1_1, SSL.SSL_OP_NO_TLSv1_2, SSL.SSL_OP_NO_TLSv1_3};
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV2 = 0;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV3 = 1;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1 = 2;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_1 = 3;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_2 = 4;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_3 = 5;
    private static final ResourceLeakDetector<ReferenceCountedOpenSslEngine> leakDetector;
    private static final InternalLogger logger;
    private Object algorithmConstraints;
    final ByteBufAllocator alloc;
    private final OpenSslApplicationProtocolNegotiator apn;
    private volatile String applicationProtocol;
    private volatile ClientAuth clientAuth = ClientAuth.NONE;
    /* access modifiers changed from: private */
    public final boolean clientMode;
    private volatile boolean destroyed;
    /* access modifiers changed from: private */
    public final boolean enableOcsp;
    private String endPointIdentificationAlgorithm;
    private final OpenSslEngineMap engineMap;
    private String[] explicitlyEnabledProtocols;
    /* access modifiers changed from: private */
    public HandshakeState handshakeState = HandshakeState.NOT_STARTED;
    private boolean isInboundDone;
    final boolean jdkCompatibilityMode;
    /* access modifiers changed from: private */
    public volatile long lastAccessed = -1;
    /* access modifiers changed from: private */
    public final ResourceLeakTracker<ReferenceCountedOpenSslEngine> leak;
    private volatile Collection<?> matchers;
    private int maxWrapBufferSize;
    private int maxWrapOverhead;
    private volatile boolean needTask;
    private long networkBIO;
    private boolean outboundClosed;
    /* access modifiers changed from: private */
    public final ReferenceCountedOpenSslContext parentContext;
    private Throwable pendingException;
    private boolean receivedShutdown;
    private final AbstractReferenceCounted refCnt = new AbstractReferenceCounted() {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<ReferenceCountedOpenSslEngine> cls = ReferenceCountedOpenSslEngine.class;
        }

        public ReferenceCounted touch(Object obj) {
            if (ReferenceCountedOpenSslEngine.this.leak != null) {
                ReferenceCountedOpenSslEngine.this.leak.record(obj);
            }
            return ReferenceCountedOpenSslEngine.this;
        }

        /* access modifiers changed from: protected */
        public void deallocate() {
            ReferenceCountedOpenSslEngine.this.shutdown();
            if (ReferenceCountedOpenSslEngine.this.leak != null) {
                ReferenceCountedOpenSslEngine.this.leak.close(ReferenceCountedOpenSslEngine.this);
            }
            ReferenceCountedOpenSslEngine.this.parentContext.release();
        }
    };
    /* access modifiers changed from: private */
    public final OpenSslSession session;
    private boolean sessionSet;
    private final ByteBuffer[] singleDstBuffer = new ByteBuffer[1];
    private final ByteBuffer[] singleSrcBuffer = new ByteBuffer[1];
    /* access modifiers changed from: private */
    public List<String> sniHostNames;
    /* access modifiers changed from: private */
    public long ssl;

    private enum HandshakeState {
        NOT_STARTED,
        STARTED_IMPLICITLY,
        STARTED_EXPLICITLY,
        FINISHED
    }

    private interface NativeSslException {
        int errorCode();
    }

    public final boolean getEnableSessionCreation() {
        return false;
    }

    static {
        Class<ReferenceCountedOpenSslEngine> cls = ReferenceCountedOpenSslEngine.class;
        logger = InternalLoggerFactory.getInstance((Class<?>) cls);
        leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(cls);
    }

    ReferenceCountedOpenSslEngine(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, ByteBufAllocator byteBufAllocator, String str, int i, boolean z, boolean z2) {
        super(str, i);
        boolean z3;
        OpenSsl.ensureAvailability();
        this.engineMap = referenceCountedOpenSslContext.engineMap;
        boolean z4 = referenceCountedOpenSslContext.enableOcsp;
        this.enableOcsp = z4;
        this.jdkCompatibilityMode = z;
        this.alloc = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "alloc");
        this.apn = (OpenSslApplicationProtocolNegotiator) referenceCountedOpenSslContext.applicationProtocolNegotiator();
        boolean isClient = referenceCountedOpenSslContext.isClient();
        this.clientMode = isClient;
        if (PlatformDependent.javaVersion() >= 7) {
            this.session = new ExtendedOpenSslSession(new DefaultOpenSslSession(referenceCountedOpenSslContext.sessionContext())) {
                private String[] peerSupportedSignatureAlgorithms;
                private List requestedServerNames;

                public List getRequestedServerNames() {
                    List list;
                    if (ReferenceCountedOpenSslEngine.this.clientMode) {
                        return Java8SslUtils.getSniHostNames((List<String>) ReferenceCountedOpenSslEngine.this.sniHostNames);
                    }
                    synchronized (ReferenceCountedOpenSslEngine.this) {
                        if (this.requestedServerNames == null) {
                            if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                                this.requestedServerNames = Collections.emptyList();
                            } else if (SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl) == null) {
                                this.requestedServerNames = Collections.emptyList();
                            } else {
                                this.requestedServerNames = Java8SslUtils.getSniHostName(SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl).getBytes(CharsetUtil.UTF_8));
                            }
                        }
                        list = this.requestedServerNames;
                    }
                    return list;
                }

                public String[] getPeerSupportedSignatureAlgorithms() {
                    String[] strArr;
                    synchronized (ReferenceCountedOpenSslEngine.this) {
                        if (this.peerSupportedSignatureAlgorithms == null) {
                            if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                                this.peerSupportedSignatureAlgorithms = EmptyArrays.EMPTY_STRINGS;
                            } else {
                                String[] sigAlgs = SSL.getSigAlgs(ReferenceCountedOpenSslEngine.this.ssl);
                                if (sigAlgs == null) {
                                    this.peerSupportedSignatureAlgorithms = EmptyArrays.EMPTY_STRINGS;
                                } else {
                                    LinkedHashSet linkedHashSet = new LinkedHashSet(sigAlgs.length);
                                    for (String javaName : sigAlgs) {
                                        String javaName2 = SignatureAlgorithmConverter.toJavaName(javaName);
                                        if (javaName2 != null) {
                                            linkedHashSet.add(javaName2);
                                        }
                                    }
                                    this.peerSupportedSignatureAlgorithms = (String[]) linkedHashSet.toArray(EmptyArrays.EMPTY_STRINGS);
                                }
                            }
                        }
                        strArr = (String[]) this.peerSupportedSignatureAlgorithms.clone();
                    }
                    return strArr;
                }

                public List<byte[]> getStatusResponses() {
                    byte[] bArr = null;
                    if (ReferenceCountedOpenSslEngine.this.enableOcsp && ReferenceCountedOpenSslEngine.this.clientMode) {
                        synchronized (ReferenceCountedOpenSslEngine.this) {
                            if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                                bArr = SSL.getOcspResponse(ReferenceCountedOpenSslEngine.this.ssl);
                            }
                        }
                    }
                    return bArr == null ? Collections.emptyList() : Collections.singletonList(bArr);
                }
            };
        } else {
            this.session = new DefaultOpenSslSession(referenceCountedOpenSslContext.sessionContext());
        }
        if (!referenceCountedOpenSslContext.sessionContext().useKeyManager()) {
            this.session.setLocalCertificate(referenceCountedOpenSslContext.keyCertChain);
        }
        Lock readLock = referenceCountedOpenSslContext.ctxLock.readLock();
        readLock.lock();
        try {
            long newSSL = SSL.newSSL(referenceCountedOpenSslContext.ctx, !referenceCountedOpenSslContext.isClient());
            synchronized (this) {
                this.ssl = newSSL;
                try {
                    this.networkBIO = SSL.bioNewByteBuffer(newSSL, referenceCountedOpenSslContext.getBioNonApplicationBufferSize());
                    setClientAuth(isClient ? ClientAuth.NONE : referenceCountedOpenSslContext.clientAuth);
                    if (referenceCountedOpenSslContext.protocols != null) {
                        setEnabledProtocols0(referenceCountedOpenSslContext.protocols, true);
                    } else {
                        this.explicitlyEnabledProtocols = getEnabledProtocols();
                    }
                    if (isClient && SslUtils.isValidHostNameForSNI(str)) {
                        if (PlatformDependent.javaVersion() < 8) {
                            SSL.setTlsExtHostName(this.ssl, str);
                            this.sniHostNames = Collections.singletonList(str);
                        } else if (Java8SslUtils.isValidHostNameForSNI(str)) {
                            SSL.setTlsExtHostName(this.ssl, str);
                            this.sniHostNames = Collections.singletonList(str);
                        }
                    }
                    if (z4) {
                        SSL.enableOcsp(this.ssl);
                    }
                    if (!z) {
                        long j = this.ssl;
                        SSL.setMode(j, SSL.getMode(j) | SSL.SSL_MODE_ENABLE_PARTIAL_WRITE);
                    }
                    if (isProtocolEnabled(SSL.getOptions(this.ssl), SSL.SSL_OP_NO_TLSv1_3, SslProtocols.TLS_v1_3)) {
                        if (isClient) {
                            z3 = ReferenceCountedOpenSslContext.CLIENT_ENABLE_SESSION_TICKET_TLSV13;
                        } else {
                            z3 = ReferenceCountedOpenSslContext.SERVER_ENABLE_SESSION_TICKET_TLSV13;
                        }
                        if (z3) {
                            SSL.clearOptions(this.ssl, SSL.SSL_OP_NO_TICKET);
                        }
                    }
                    if (OpenSsl.isBoringSSL() && isClient) {
                        SSL.setRenegotiateMode(this.ssl, SSL.SSL_RENEGOTIATE_ONCE);
                    }
                    calculateMaxWrapOverhead();
                } catch (Throwable th) {
                    shutdown();
                    PlatformDependent.throwException(th);
                }
            }
            this.parentContext = referenceCountedOpenSslContext;
            referenceCountedOpenSslContext.retain();
            this.leak = z2 ? leakDetector.track(this) : null;
        } finally {
            readLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized String[] authMethods() {
        if (isDestroyed()) {
            return EmptyArrays.EMPTY_STRINGS;
        }
        return SSL.authenticationMethods(this.ssl);
    }

    /* access modifiers changed from: package-private */
    public final boolean setKeyMaterial(OpenSslKeyMaterial openSslKeyMaterial) throws Exception {
        synchronized (this) {
            if (isDestroyed()) {
                return false;
            }
            SSL.setKeyMaterial(this.ssl, openSslKeyMaterial.certificateChainAddress(), openSslKeyMaterial.privateKeyAddress());
            this.session.setLocalCertificate(openSslKeyMaterial.certificateChain());
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized SecretKeySpec masterKey() {
        if (isDestroyed()) {
            return null;
        }
        return new SecretKeySpec(SSL.getMasterKey(this.ssl), "AES");
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isSessionReused() {
        if (isDestroyed()) {
            return false;
        }
        return SSL.isSessionReused(this.ssl);
    }

    public void setOcspResponse(byte[] bArr) {
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        } else if (!this.clientMode) {
            synchronized (this) {
                if (!isDestroyed()) {
                    SSL.setOcspResponse(this.ssl, bArr);
                }
            }
        } else {
            throw new IllegalStateException("Not a server SSLEngine");
        }
    }

    public byte[] getOcspResponse() {
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        } else if (this.clientMode) {
            synchronized (this) {
                if (isDestroyed()) {
                    byte[] bArr = EmptyArrays.EMPTY_BYTES;
                    return bArr;
                }
                byte[] ocspResponse = SSL.getOcspResponse(this.ssl);
                return ocspResponse;
            }
        } else {
            throw new IllegalStateException("Not a client SSLEngine");
        }
    }

    public final int refCnt() {
        return this.refCnt.refCnt();
    }

    public final ReferenceCounted retain() {
        this.refCnt.retain();
        return this;
    }

    public final ReferenceCounted retain(int i) {
        this.refCnt.retain(i);
        return this;
    }

    public final ReferenceCounted touch() {
        this.refCnt.touch();
        return this;
    }

    public final ReferenceCounted touch(Object obj) {
        this.refCnt.touch(obj);
        return this;
    }

    public final boolean release() {
        return this.refCnt.release();
    }

    public final boolean release(int i) {
        return this.refCnt.release(i);
    }

    public String getApplicationProtocol() {
        return this.applicationProtocol;
    }

    public String getHandshakeApplicationProtocol() {
        return this.applicationProtocol;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized javax.net.ssl.SSLSession getHandshakeSession() {
        /*
            r2 = this;
            monitor-enter(r2)
            int[] r0 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState     // Catch:{ all -> 0x0018 }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r1 = r2.handshakeState     // Catch:{ all -> 0x0018 }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x0018 }
            r0 = r0[r1]     // Catch:{ all -> 0x0018 }
            r1 = 1
            if (r0 == r1) goto L_0x0015
            r1 = 2
            if (r0 == r1) goto L_0x0015
            io.netty.handler.ssl.OpenSslSession r0 = r2.session     // Catch:{ all -> 0x0018 }
            monitor-exit(r2)
            return r0
        L_0x0015:
            monitor-exit(r2)
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.getHandshakeSession():javax.net.ssl.SSLSession");
    }

    public final synchronized long sslPointer() {
        return this.ssl;
    }

    public final synchronized void shutdown() {
        if (!this.destroyed) {
            this.destroyed = true;
            OpenSslEngineMap openSslEngineMap = this.engineMap;
            if (openSslEngineMap != null) {
                openSslEngineMap.remove(this.ssl);
            }
            SSL.freeSSL(this.ssl);
            this.networkBIO = 0;
            this.ssl = 0;
            this.outboundClosed = true;
            this.isInboundDone = true;
        }
        SSL.clearError();
    }

    private int writePlaintextData(ByteBuffer byteBuffer, int i) {
        int i2;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        if (byteBuffer.isDirect()) {
            i2 = SSL.writeToSSL(this.ssl, bufferAddress(byteBuffer) + ((long) position), i);
            if (i2 > 0) {
                byteBuffer.position(position + i2);
            }
        } else {
            ByteBuf directBuffer = this.alloc.directBuffer(i);
            try {
                byteBuffer.limit(position + i);
                directBuffer.setBytes(0, byteBuffer);
                byteBuffer.limit(limit);
                i2 = SSL.writeToSSL(this.ssl, OpenSsl.memoryAddress(directBuffer), i);
                if (i2 > 0) {
                    byteBuffer.position(position + i2);
                } else {
                    byteBuffer.position(position);
                }
            } finally {
                directBuffer.release();
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void bioSetFd(int i) {
        if (!isDestroyed()) {
            SSL.bioSetFd(this.ssl, i);
        }
    }

    private ByteBuf writeEncryptedData(ByteBuffer byteBuffer, int i) throws SSLException {
        int position = byteBuffer.position();
        if (byteBuffer.isDirect()) {
            SSL.bioSetByteBuffer(this.networkBIO, bufferAddress(byteBuffer) + ((long) position), i, false);
            return null;
        }
        ByteBuf directBuffer = this.alloc.directBuffer(i);
        try {
            int limit = byteBuffer.limit();
            byteBuffer.limit(position + i);
            directBuffer.writeBytes(byteBuffer);
            byteBuffer.position(position);
            byteBuffer.limit(limit);
            SSL.bioSetByteBuffer(this.networkBIO, OpenSsl.memoryAddress(directBuffer), i, false);
            return directBuffer;
        } catch (Throwable th) {
            directBuffer.release();
            PlatformDependent.throwException(th);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    private int readPlaintextData(ByteBuffer byteBuffer) throws SSLException {
        int position = byteBuffer.position();
        if (byteBuffer.isDirect()) {
            int readFromSSL = SSL.readFromSSL(this.ssl, bufferAddress(byteBuffer) + ((long) position), byteBuffer.limit() - position);
            if (readFromSSL <= 0) {
                return readFromSSL;
            }
            byteBuffer.position(position + readFromSSL);
            return readFromSSL;
        }
        int limit = byteBuffer.limit();
        int min = Math.min(maxEncryptedPacketLength0(), limit - position);
        ByteBuf directBuffer = this.alloc.directBuffer(min);
        try {
            int readFromSSL2 = SSL.readFromSSL(this.ssl, OpenSsl.memoryAddress(directBuffer), min);
            if (readFromSSL2 > 0) {
                byteBuffer.limit(position + readFromSSL2);
                directBuffer.getBytes(directBuffer.readerIndex(), byteBuffer);
                byteBuffer.limit(limit);
            }
            directBuffer.release();
            return readFromSSL2;
        } catch (Throwable th) {
            directBuffer.release();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized int maxWrapOverhead() {
        return this.maxWrapOverhead;
    }

    /* access modifiers changed from: package-private */
    public final synchronized int maxEncryptedPacketLength() {
        return maxEncryptedPacketLength0();
    }

    /* access modifiers changed from: package-private */
    public final int maxEncryptedPacketLength0() {
        return this.maxWrapOverhead + MAX_PLAINTEXT_LENGTH;
    }

    /* access modifiers changed from: package-private */
    public final int calculateMaxLengthForWrap(int i, int i2) {
        return (int) Math.min((long) this.maxWrapBufferSize, ((long) i) + (((long) this.maxWrapOverhead) * ((long) i2)));
    }

    /* access modifiers changed from: package-private */
    public final int calculateOutNetBufSize(int i, int i2) {
        return (int) Math.min(2147483647L, ((long) i) + (((long) this.maxWrapOverhead) * ((long) i2)));
    }

    /* access modifiers changed from: package-private */
    public final synchronized int sslPending() {
        return sslPending0();
    }

    /* access modifiers changed from: private */
    public void calculateMaxWrapOverhead() {
        this.maxWrapOverhead = SSL.getMaxWrapOverhead(this.ssl);
        this.maxWrapBufferSize = this.jdkCompatibilityMode ? maxEncryptedPacketLength0() : maxEncryptedPacketLength0() << 4;
    }

    private int sslPending0() {
        if (this.handshakeState != HandshakeState.FINISHED) {
            return 0;
        }
        return SSL.sslPending(this.ssl);
    }

    private boolean isBytesAvailableEnoughForWrap(int i, int i2, int i3) {
        return ((long) i) - (((long) this.maxWrapOverhead) * ((long) i3)) >= ((long) i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01a5, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01d9, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0219, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0248, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0276, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x02eb, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0317, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x036b, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x03b2, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x040e, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x0434, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x045e, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x048a, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x04b0, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x04ea, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x050e, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a8, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d6, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0102, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x012f, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x017d, code lost:
        return r12;
     */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x0529  */
    /* JADX WARNING: Removed duplicated region for block: B:346:0x0538  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:342:0x0522=Splitter:B:342:0x0522, B:140:0x0229=Splitter:B:140:0x0229} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final javax.net.ssl.SSLEngineResult wrap(java.nio.ByteBuffer[] r12, int r13, int r14, java.nio.ByteBuffer r15) throws javax.net.ssl.SSLException {
        /*
            r11 = this;
            java.lang.String r0 = "srcs"
            io.netty.util.internal.ObjectUtil.checkNotNullWithIAE(r12, r0)
            java.lang.String r0 = "dst"
            io.netty.util.internal.ObjectUtil.checkNotNullWithIAE(r15, r0)
            int r0 = r12.length
            if (r13 >= r0) goto L_0x054a
            int r0 = r13 + r14
            int r1 = r12.length
            if (r0 > r1) goto L_0x054a
            boolean r14 = r15.isReadOnly()
            if (r14 != 0) goto L_0x0544
            monitor-enter(r11)
            boolean r14 = r11.isOutboundDone()     // Catch:{ all -> 0x0541 }
            if (r14 == 0) goto L_0x0033
            boolean r12 = r11.isInboundDone()     // Catch:{ all -> 0x0541 }
            if (r12 != 0) goto L_0x002f
            boolean r12 = r11.isDestroyed()     // Catch:{ all -> 0x0541 }
            if (r12 == 0) goto L_0x002c
            goto L_0x002f
        L_0x002c:
            javax.net.ssl.SSLEngineResult r12 = NEED_UNWRAP_CLOSED     // Catch:{ all -> 0x0541 }
            goto L_0x0031
        L_0x002f:
            javax.net.ssl.SSLEngineResult r12 = CLOSED_NOT_HANDSHAKING     // Catch:{ all -> 0x0541 }
        L_0x0031:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x0033:
            r14 = 0
            r1 = 0
            boolean r2 = r15.isDirect()     // Catch:{ all -> 0x0521 }
            if (r2 == 0) goto L_0x0051
            long r3 = r11.networkBIO     // Catch:{ all -> 0x0521 }
            long r5 = bufferAddress(r15)     // Catch:{ all -> 0x0521 }
            int r2 = r15.position()     // Catch:{ all -> 0x0521 }
            long r7 = (long) r2     // Catch:{ all -> 0x0521 }
            long r5 = r5 + r7
            int r7 = r15.remaining()     // Catch:{ all -> 0x0521 }
            r8 = 1
            io.netty.internal.tcnative.SSL.bioSetByteBuffer(r3, r5, r7, r8)     // Catch:{ all -> 0x0521 }
            r2 = r14
            goto L_0x0069
        L_0x0051:
            io.netty.buffer.ByteBufAllocator r2 = r11.alloc     // Catch:{ all -> 0x0521 }
            int r3 = r15.remaining()     // Catch:{ all -> 0x0521 }
            io.netty.buffer.ByteBuf r2 = r2.directBuffer(r3)     // Catch:{ all -> 0x0521 }
            long r3 = r11.networkBIO     // Catch:{ all -> 0x051e }
            long r5 = io.netty.handler.ssl.OpenSsl.memoryAddress(r2)     // Catch:{ all -> 0x051e }
            int r7 = r2.writableBytes()     // Catch:{ all -> 0x051e }
            r8 = 1
            io.netty.internal.tcnative.SSL.bioSetByteBuffer(r3, r5, r7, r8)     // Catch:{ all -> 0x051e }
        L_0x0069:
            long r3 = r11.networkBIO     // Catch:{ all -> 0x051e }
            int r3 = io.netty.internal.tcnative.SSL.bioLengthByteBuffer(r3)     // Catch:{ all -> 0x051e }
            boolean r4 = r11.outboundClosed     // Catch:{ all -> 0x051e }
            r5 = 1
            if (r4 == 0) goto L_0x013a
            int r12 = r15.remaining()     // Catch:{ all -> 0x051e }
            r13 = 2
            boolean r12 = r11.isBytesAvailableEnoughForWrap(r12, r13, r5)     // Catch:{ all -> 0x051e }
            if (r12 != 0) goto L_0x00a9
            javax.net.ssl.SSLEngineResult r12 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x051e }
            javax.net.ssl.SSLEngineResult$Status r13 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x051e }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r14 = r11.getHandshakeStatus()     // Catch:{ all -> 0x051e }
            r12.<init>(r13, r14, r1, r1)     // Catch:{ all -> 0x051e }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x0099
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x00a7
        L_0x0099:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r1)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x00a7:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x00a9:
            long r12 = r11.networkBIO     // Catch:{ all -> 0x051e }
            int r12 = io.netty.internal.tcnative.SSL.bioFlushByteBuffer(r12)     // Catch:{ all -> 0x051e }
            if (r12 > 0) goto L_0x00d7
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x0135 }
            javax.net.ssl.SSLEngineResult r13 = r11.newResultMayFinishHandshake(r13, r1, r1)     // Catch:{ all -> 0x0135 }
            long r0 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r0)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x00c7
            int r14 = r15.position()     // Catch:{ all -> 0x0541 }
            int r14 = r14 + r12
            r15.position(r14)     // Catch:{ all -> 0x0541 }
            goto L_0x00d5
        L_0x00c7:
            int r14 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r12 = r2.internalNioBuffer(r14, r12)     // Catch:{ all -> 0x0541 }
            r15.put(r12)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x00d5:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r13
        L_0x00d7:
            boolean r13 = r11.doSSLShutdown()     // Catch:{ all -> 0x0135 }
            if (r13 != 0) goto L_0x0103
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x0135 }
            javax.net.ssl.SSLEngineResult r13 = r11.newResultMayFinishHandshake(r13, r1, r12)     // Catch:{ all -> 0x0135 }
            long r0 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r0)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x00f3
            int r14 = r15.position()     // Catch:{ all -> 0x0541 }
            int r14 = r14 + r12
            r15.position(r14)     // Catch:{ all -> 0x0541 }
            goto L_0x0101
        L_0x00f3:
            int r14 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r12 = r2.internalNioBuffer(r14, r12)     // Catch:{ all -> 0x0541 }
            r15.put(r12)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x0101:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r13
        L_0x0103:
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0135 }
            int r12 = io.netty.internal.tcnative.SSL.bioLengthByteBuffer(r13)     // Catch:{ all -> 0x0135 }
            int r3 = r3 - r12
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x0130 }
            javax.net.ssl.SSLEngineResult r12 = r11.newResultMayFinishHandshake(r12, r1, r3)     // Catch:{ all -> 0x0130 }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x0120
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r3
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x012e
        L_0x0120:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r3)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x012e:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x0130:
            r12 = move-exception
            r14 = r2
            r1 = r3
            goto L_0x0522
        L_0x0135:
            r13 = move-exception
            r1 = r12
            r12 = r13
            goto L_0x051f
        L_0x013a:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r4 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x051e }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r6 = r11.handshakeState     // Catch:{ all -> 0x051e }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r7 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.FINISHED     // Catch:{ all -> 0x051e }
            if (r6 == r7) goto L_0x0277
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r4 = r11.handshakeState     // Catch:{ all -> 0x051e }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r7 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.STARTED_EXPLICITLY     // Catch:{ all -> 0x051e }
            if (r4 == r7) goto L_0x014c
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r4 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.STARTED_IMPLICITLY     // Catch:{ all -> 0x051e }
            r11.handshakeState = r4     // Catch:{ all -> 0x051e }
        L_0x014c:
            long r7 = r11.networkBIO     // Catch:{ all -> 0x051e }
            int r4 = io.netty.internal.tcnative.SSL.bioFlushByteBuffer(r7)     // Catch:{ all -> 0x051e }
            java.lang.Throwable r7 = r11.pendingException     // Catch:{ all -> 0x051a }
            if (r7 == 0) goto L_0x01a6
            if (r4 <= 0) goto L_0x017e
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r1, r4)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x016e
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x017c
        L_0x016e:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x017c:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x017e:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = r11.handshakeException()     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r1, r1)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x0196
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x01a4
        L_0x0196:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x01a4:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x01a6:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r7 = r11.handshake()     // Catch:{ all -> 0x051a }
            long r8 = r11.networkBIO     // Catch:{ all -> 0x051a }
            int r4 = io.netty.internal.tcnative.SSL.bioLengthByteBuffer(r8)     // Catch:{ all -> 0x051a }
            int r4 = r3 - r4
            javax.net.ssl.SSLEngineResult$HandshakeStatus r8 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ all -> 0x051a }
            if (r7 != r8) goto L_0x01da
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r7, r1, r4)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x01ca
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x01d8
        L_0x01ca:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x01d8:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x01da:
            if (r4 <= 0) goto L_0x021a
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x051a }
            if (r7 == r12) goto L_0x01f0
            if (r4 != r3) goto L_0x01e5
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x051a }
            goto L_0x01f2
        L_0x01e5:
            long r12 = r11.networkBIO     // Catch:{ all -> 0x051a }
            int r12 = io.netty.internal.tcnative.SSL.bioLengthNonApplication(r12)     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = r11.getHandshakeStatus(r12)     // Catch:{ all -> 0x051a }
            goto L_0x01f2
        L_0x01f0:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x051a }
        L_0x01f2:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = r11.mayFinishHandshake(r12)     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r1, r4)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x020a
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x0218
        L_0x020a:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x0218:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x021a:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r8 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ all -> 0x051a }
            if (r7 != r8) goto L_0x0249
            boolean r12 = r11.isOutboundDone()     // Catch:{ all -> 0x051a }
            if (r12 == 0) goto L_0x0227
            javax.net.ssl.SSLEngineResult r12 = NEED_UNWRAP_CLOSED     // Catch:{ all -> 0x051a }
            goto L_0x0229
        L_0x0227:
            javax.net.ssl.SSLEngineResult r12 = NEED_UNWRAP_OK     // Catch:{ all -> 0x051a }
        L_0x0229:
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x0239
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x0247
        L_0x0239:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x0247:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x0249:
            boolean r8 = r11.outboundClosed     // Catch:{ all -> 0x051a }
            if (r8 == 0) goto L_0x0279
            long r12 = r11.networkBIO     // Catch:{ all -> 0x051a }
            int r12 = io.netty.internal.tcnative.SSL.bioFlushByteBuffer(r12)     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult r13 = r11.newResultMayFinishHandshake(r7, r1, r12)     // Catch:{ all -> 0x0135 }
            long r0 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r0)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x0267
            int r14 = r15.position()     // Catch:{ all -> 0x0541 }
            int r14 = r14 + r12
            r15.position(r14)     // Catch:{ all -> 0x0541 }
            goto L_0x0275
        L_0x0267:
            int r14 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r12 = r2.internalNioBuffer(r14, r12)     // Catch:{ all -> 0x0541 }
            r15.put(r12)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x0275:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r13
        L_0x0277:
            r7 = r4
            r4 = 0
        L_0x0279:
            boolean r8 = r11.jdkCompatibilityMode     // Catch:{ all -> 0x051a }
            if (r8 != 0) goto L_0x0281
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r8 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.FINISHED     // Catch:{ all -> 0x051a }
            if (r6 == r8) goto L_0x02ec
        L_0x0281:
            r6 = r13
            r8 = 0
        L_0x0283:
            if (r6 >= r0) goto L_0x02b7
            r9 = r12[r6]     // Catch:{ all -> 0x051a }
            if (r9 == 0) goto L_0x029b
            int r10 = MAX_PLAINTEXT_LENGTH     // Catch:{ all -> 0x051a }
            if (r8 != r10) goto L_0x028e
            goto L_0x0298
        L_0x028e:
            int r9 = r9.remaining()     // Catch:{ all -> 0x051a }
            int r8 = r8 + r9
            if (r8 > r10) goto L_0x0297
            if (r8 >= 0) goto L_0x0298
        L_0x0297:
            r8 = r10
        L_0x0298:
            int r6 = r6 + 1
            goto L_0x0283
        L_0x029b:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x051a }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x051a }
            r13.<init>()     // Catch:{ all -> 0x051a }
            java.lang.String r14 = "srcs["
            r13.append(r14)     // Catch:{ all -> 0x051a }
            r13.append(r6)     // Catch:{ all -> 0x051a }
            java.lang.String r14 = "] is null"
            r13.append(r14)     // Catch:{ all -> 0x051a }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x051a }
            r12.<init>(r13)     // Catch:{ all -> 0x051a }
            throw r12     // Catch:{ all -> 0x051a }
        L_0x02b7:
            int r6 = r15.remaining()     // Catch:{ all -> 0x051a }
            boolean r5 = r11.isBytesAvailableEnoughForWrap(r6, r8, r5)     // Catch:{ all -> 0x051a }
            if (r5 != 0) goto L_0x02ec
            javax.net.ssl.SSLEngineResult r12 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult$Status r13 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r14 = r11.getHandshakeStatus()     // Catch:{ all -> 0x051a }
            r12.<init>(r13, r14, r1, r1)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x02dc
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x02ea
        L_0x02dc:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x02ea:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x02ec:
            long r5 = r11.networkBIO     // Catch:{ all -> 0x051a }
            int r4 = io.netty.internal.tcnative.SSL.bioFlushByteBuffer(r5)     // Catch:{ all -> 0x051a }
            if (r4 <= 0) goto L_0x0318
            javax.net.ssl.SSLEngineResult r12 = r11.newResultMayFinishHandshake(r7, r1, r4)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x0308
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x0316
        L_0x0308:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x0316:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x0318:
            java.lang.Throwable r5 = r11.pendingException     // Catch:{ all -> 0x051a }
            if (r5 != 0) goto L_0x050f
        L_0x031c:
            if (r13 >= r0) goto L_0x04eb
            r14 = r12[r13]     // Catch:{ all -> 0x051a }
            int r5 = r14.remaining()     // Catch:{ all -> 0x051a }
            if (r5 != 0) goto L_0x0327
            goto L_0x038c
        L_0x0327:
            boolean r6 = r11.jdkCompatibilityMode     // Catch:{ all -> 0x051a }
            if (r6 == 0) goto L_0x0337
            int r6 = MAX_PLAINTEXT_LENGTH     // Catch:{ all -> 0x051a }
            int r6 = r6 - r1
            int r5 = java.lang.Math.min(r5, r6)     // Catch:{ all -> 0x051a }
            int r14 = r11.writePlaintextData(r14, r5)     // Catch:{ all -> 0x051a }
            goto L_0x0374
        L_0x0337:
            int r6 = r15.remaining()     // Catch:{ all -> 0x051a }
            int r6 = r6 - r4
            int r8 = r11.maxWrapOverhead     // Catch:{ all -> 0x051a }
            int r6 = r6 - r8
            if (r6 > 0) goto L_0x036c
            javax.net.ssl.SSLEngineResult r12 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult$Status r13 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r14 = r11.getHandshakeStatus()     // Catch:{ all -> 0x051a }
            r12.<init>(r13, r14, r1, r4)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x035c
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x036a
        L_0x035c:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x036a:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x036c:
            int r5 = java.lang.Math.min(r5, r6)     // Catch:{ all -> 0x051a }
            int r14 = r11.writePlaintextData(r14, r5)     // Catch:{ all -> 0x051a }
        L_0x0374:
            long r5 = r11.networkBIO     // Catch:{ all -> 0x051a }
            int r5 = io.netty.internal.tcnative.SSL.bioLengthByteBuffer(r5)     // Catch:{ all -> 0x051a }
            int r3 = r3 - r5
            int r3 = r3 + r4
            if (r14 <= 0) goto L_0x03b3
            int r1 = r1 + r14
            boolean r14 = r11.jdkCompatibilityMode     // Catch:{ all -> 0x0130 }
            if (r14 != 0) goto L_0x038f
            int r14 = r15.remaining()     // Catch:{ all -> 0x0130 }
            if (r3 != r14) goto L_0x038a
            goto L_0x038f
        L_0x038a:
            r4 = r3
            r3 = r5
        L_0x038c:
            int r13 = r13 + 1
            goto L_0x031c
        L_0x038f:
            javax.net.ssl.SSLEngineResult r12 = r11.newResultMayFinishHandshake(r7, r1, r3)     // Catch:{ all -> 0x0130 }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x03a3
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r3
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x03b1
        L_0x03a3:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r3)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x03b1:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x03b3:
            long r12 = r11.ssl     // Catch:{ all -> 0x0130 }
            int r12 = io.netty.internal.tcnative.SSL.getError(r12, r14)     // Catch:{ all -> 0x0130 }
            int r13 = io.netty.internal.tcnative.SSL.SSL_ERROR_ZERO_RETURN     // Catch:{ all -> 0x0130 }
            if (r12 != r13) goto L_0x0435
            boolean r12 = r11.receivedShutdown     // Catch:{ all -> 0x0130 }
            if (r12 != 0) goto L_0x040f
            r11.closeAll()     // Catch:{ all -> 0x0130 }
            long r12 = r11.networkBIO     // Catch:{ all -> 0x0130 }
            int r12 = io.netty.internal.tcnative.SSL.bioLengthByteBuffer(r12)     // Catch:{ all -> 0x0130 }
            int r5 = r5 - r12
            int r12 = r3 + r5
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x0135 }
            if (r7 == r13) goto L_0x03e5
            int r13 = r15.remaining()     // Catch:{ all -> 0x0135 }
            if (r12 != r13) goto L_0x03da
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x0135 }
            goto L_0x03e7
        L_0x03da:
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0135 }
            int r13 = io.netty.internal.tcnative.SSL.bioLengthNonApplication(r13)     // Catch:{ all -> 0x0135 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = r11.getHandshakeStatus(r13)     // Catch:{ all -> 0x0135 }
            goto L_0x03e7
        L_0x03e5:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x0135 }
        L_0x03e7:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = r11.mayFinishHandshake(r13)     // Catch:{ all -> 0x0135 }
            javax.net.ssl.SSLEngineResult r13 = r11.newResult(r13, r1, r12)     // Catch:{ all -> 0x0135 }
            long r0 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r0)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x03ff
            int r14 = r15.position()     // Catch:{ all -> 0x0541 }
            int r14 = r14 + r12
            r15.position(r14)     // Catch:{ all -> 0x0541 }
            goto L_0x040d
        L_0x03ff:
            int r14 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r12 = r2.internalNioBuffer(r14, r12)     // Catch:{ all -> 0x0541 }
            r15.put(r12)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x040d:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r13
        L_0x040f:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x0130 }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r1, r3)     // Catch:{ all -> 0x0130 }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x0425
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r3
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x0433
        L_0x0425:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r3)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x0433:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x0435:
            int r13 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_READ     // Catch:{ all -> 0x0130 }
            if (r12 != r13) goto L_0x045f
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ all -> 0x0130 }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r1, r3)     // Catch:{ all -> 0x0130 }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x044f
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r3
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x045d
        L_0x044f:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r3)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x045d:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x045f:
            int r13 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_WRITE     // Catch:{ all -> 0x0130 }
            if (r12 != r13) goto L_0x04b1
            if (r3 <= 0) goto L_0x048b
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x0130 }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r1, r3)     // Catch:{ all -> 0x0130 }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x047b
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r3
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x0489
        L_0x047b:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r3)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x0489:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x048b:
            javax.net.ssl.SSLEngineResult$Status r12 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x0130 }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r7, r1, r3)     // Catch:{ all -> 0x0130 }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x04a1
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r3
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x04af
        L_0x04a1:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r3)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x04af:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x04b1:
            int r13 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_X509_LOOKUP     // Catch:{ all -> 0x0130 }
            if (r12 == r13) goto L_0x04c5
            int r13 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY     // Catch:{ all -> 0x0130 }
            if (r12 == r13) goto L_0x04c5
            int r13 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION     // Catch:{ all -> 0x0130 }
            if (r12 != r13) goto L_0x04be
            goto L_0x04c5
        L_0x04be:
            java.lang.String r13 = "SSL_write"
            javax.net.ssl.SSLException r12 = r11.shutdownWithError(r13, r12)     // Catch:{ all -> 0x0130 }
            throw r12     // Catch:{ all -> 0x0130 }
        L_0x04c5:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ all -> 0x0130 }
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r12, r1, r3)     // Catch:{ all -> 0x0130 }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x04db
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r3
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x04e9
        L_0x04db:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r3)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x04e9:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x04eb:
            javax.net.ssl.SSLEngineResult r12 = r11.newResultMayFinishHandshake(r7, r1, r4)     // Catch:{ all -> 0x051a }
            long r13 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r13)     // Catch:{ all -> 0x0541 }
            if (r2 != 0) goto L_0x04ff
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r4
            r15.position(r13)     // Catch:{ all -> 0x0541 }
            goto L_0x050d
        L_0x04ff:
            int r13 = r2.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r2.internalNioBuffer(r13, r4)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r2.release()     // Catch:{ all -> 0x0541 }
        L_0x050d:
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            return r12
        L_0x050f:
            r11.pendingException = r14     // Catch:{ all -> 0x051a }
            r11.shutdown()     // Catch:{ all -> 0x051a }
            javax.net.ssl.SSLException r12 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x051a }
            r12.<init>(r5)     // Catch:{ all -> 0x051a }
            throw r12     // Catch:{ all -> 0x051a }
        L_0x051a:
            r12 = move-exception
            r14 = r2
            r1 = r4
            goto L_0x0522
        L_0x051e:
            r12 = move-exception
        L_0x051f:
            r14 = r2
            goto L_0x0522
        L_0x0521:
            r12 = move-exception
        L_0x0522:
            long r2 = r11.networkBIO     // Catch:{ all -> 0x0541 }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x0541 }
            if (r14 == 0) goto L_0x0538
            int r13 = r14.readerIndex()     // Catch:{ all -> 0x0541 }
            java.nio.ByteBuffer r13 = r14.internalNioBuffer(r13, r1)     // Catch:{ all -> 0x0541 }
            r15.put(r13)     // Catch:{ all -> 0x0541 }
            r14.release()     // Catch:{ all -> 0x0541 }
            goto L_0x0540
        L_0x0538:
            int r13 = r15.position()     // Catch:{ all -> 0x0541 }
            int r13 = r13 + r1
            r15.position(r13)     // Catch:{ all -> 0x0541 }
        L_0x0540:
            throw r12     // Catch:{ all -> 0x0541 }
        L_0x0541:
            r12 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0541 }
            throw r12
        L_0x0544:
            java.nio.ReadOnlyBufferException r12 = new java.nio.ReadOnlyBufferException
            r12.<init>()
            throw r12
        L_0x054a:
            java.lang.IndexOutOfBoundsException r15 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "offset: "
            r0.<init>(r1)
            r0.append(r13)
            java.lang.String r13 = ", length: "
            r0.append(r13)
            r0.append(r14)
            java.lang.String r13 = " (expected: offset <= offset + length <= srcs.length ("
            r0.append(r13)
            int r12 = r12.length
            r0.append(r12)
            java.lang.String r12 = "))"
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r15.<init>(r12)
            goto L_0x0575
        L_0x0574:
            throw r15
        L_0x0575:
            goto L_0x0574
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.wrap(java.nio.ByteBuffer[], int, int, java.nio.ByteBuffer):javax.net.ssl.SSLEngineResult");
    }

    private SSLEngineResult newResult(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) {
        return newResult(SSLEngineResult.Status.OK, handshakeStatus, i, i2);
    }

    private SSLEngineResult newResult(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) {
        if (isOutboundDone()) {
            if (isInboundDone()) {
                handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                shutdown();
            }
            return new SSLEngineResult(SSLEngineResult.Status.CLOSED, handshakeStatus, i, i2);
        }
        if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_TASK) {
            this.needTask = true;
        }
        return new SSLEngineResult(status, handshakeStatus, i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(mayFinishHandshake(handshakeStatus, i, i2), i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(status, mayFinishHandshake(handshakeStatus, i, i2), i, i2);
    }

    private SSLException shutdownWithError(String str, int i) {
        return shutdownWithError(str, i, SSL.getLastErrorNumber());
    }

    private SSLException shutdownWithError(String str, int i, int i2) {
        InternalLogger internalLogger = logger;
        if (internalLogger.isDebugEnabled()) {
            String errorString = SSL.getErrorString((long) i2);
            internalLogger.debug("{} failed with {}: OpenSSL error: {} {}", str, Integer.valueOf(i), Integer.valueOf(i2), errorString);
        }
        shutdown();
        SSLException newSSLExceptionForError = newSSLExceptionForError(i2);
        Throwable th = this.pendingException;
        if (th != null) {
            newSSLExceptionForError.initCause(th);
            this.pendingException = null;
        }
        return newSSLExceptionForError;
    }

    private SSLEngineResult handleUnwrapException(int i, int i2, SSLException sSLException) throws SSLException {
        int lastErrorNumber = SSL.getLastErrorNumber();
        if (lastErrorNumber != 0) {
            return sslReadErrorResult(SSL.SSL_ERROR_SSL, lastErrorNumber, i, i2);
        }
        throw sSLException;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x017a, code lost:
        if (r14 != null) goto L_0x017c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r14.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01af, code lost:
        if (r10 <= 0) goto L_0x01ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01b1, code lost:
        r0 = newResult(javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW, r17, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01ba, code lost:
        r6 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01c0, code lost:
        if (isInboundDone() == false) goto L_0x01c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01c2, code lost:
        r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01c5, code lost:
        r0 = javax.net.ssl.SSLEngineResult.Status.OK;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01c7, code lost:
        r0 = newResultMayFinishHandshake(r0, r6, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01cb, code lost:
        if (r14 == null) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:?, code lost:
        r14.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:?, code lost:
        io.netty.internal.tcnative.SSL.bioClearByteBuffer(r1.networkBIO);
        rejectRemoteInitiatedRenegotiation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x01d9, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x01eb, code lost:
        if (r14 == null) goto L_0x0277;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x01ee, code lost:
        r6 = r17;
        r7 = io.netty.internal.tcnative.SSL.getError(r1.ssl, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x01f8, code lost:
        if (r7 == io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_READ) goto L_0x026d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x01fc, code lost:
        if (r7 != io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_WRITE) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0202, code lost:
        if (r7 != io.netty.internal.tcnative.SSL.SSL_ERROR_ZERO_RETURN) goto L_0x0229;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0206, code lost:
        if (r1.receivedShutdown != false) goto L_0x020b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0208, code lost:
        closeAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x020f, code lost:
        if (isInboundDone() == false) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0211, code lost:
        r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0214, code lost:
        r0 = javax.net.ssl.SSLEngineResult.Status.OK;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0216, code lost:
        r0 = newResultMayFinishHandshake(r0, r6, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x021a, code lost:
        if (r14 == null) goto L_0x021f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:?, code lost:
        r14.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:?, code lost:
        io.netty.internal.tcnative.SSL.bioClearByteBuffer(r1.networkBIO);
        rejectRemoteInitiatedRenegotiation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0228, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x022b, code lost:
        if (r7 == io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_X509_LOOKUP) goto L_0x024d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x022f, code lost:
        if (r7 == io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY) goto L_0x024d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0233, code lost:
        if (r7 != io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) goto L_0x0236;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0236, code lost:
        r0 = sslReadErrorResult(r7, io.netty.internal.tcnative.SSL.getLastErrorNumber(), r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x023e, code lost:
        if (r14 == null) goto L_0x0243;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:?, code lost:
        r14.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:?, code lost:
        io.netty.internal.tcnative.SSL.bioClearByteBuffer(r1.networkBIO);
        rejectRemoteInitiatedRenegotiation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x024c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0251, code lost:
        if (isInboundDone() == false) goto L_0x0256;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0253, code lost:
        r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0256, code lost:
        r0 = javax.net.ssl.SSLEngineResult.Status.OK;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0258, code lost:
        r0 = newResult(r0, javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x025e, code lost:
        if (r14 == null) goto L_0x0263;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:?, code lost:
        r14.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:?, code lost:
        io.netty.internal.tcnative.SSL.bioClearByteBuffer(r1.networkBIO);
        rejectRemoteInitiatedRenegotiation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x026c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x026d, code lost:
        r2 = r2 + 1;
        r7 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0271, code lost:
        if (r2 < r7) goto L_0x02a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0273, code lost:
        if (r14 == null) goto L_0x0277;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x02a4, code lost:
        if (r14 == null) goto L_0x02a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:?, code lost:
        r14.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x02a9, code lost:
        r10 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x0299 A[Catch:{ SSLException -> 0x02ad, all -> 0x02c2, all -> 0x02d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x029c A[Catch:{ SSLException -> 0x02ad, all -> 0x02c2, all -> 0x02d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0156 A[Catch:{ SSLException -> 0x02ad, all -> 0x02c2, all -> 0x02d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0166 A[Catch:{ SSLException -> 0x02ad, all -> 0x02c2, all -> 0x02d9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final javax.net.ssl.SSLEngineResult unwrap(java.nio.ByteBuffer[] r20, int r21, int r22, java.nio.ByteBuffer[] r23, int r24, int r25) throws javax.net.ssl.SSLException {
        /*
            r19 = this;
            r1 = r19
            r0 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            r5 = r24
            r6 = r25
            java.lang.String r7 = "srcs"
            io.netty.util.internal.ObjectUtil.checkNotNullWithIAE(r0, r7)
            int r7 = r0.length
            if (r2 >= r7) goto L_0x0318
            int r7 = r2 + r3
            int r8 = r0.length
            if (r7 > r8) goto L_0x0318
            java.lang.String r3 = "dsts"
            io.netty.util.internal.ObjectUtil.checkNotNullWithIAE(r4, r3)
            int r3 = r4.length
            if (r5 >= r3) goto L_0x02ee
            int r3 = r5 + r6
            int r8 = r4.length
            if (r3 > r8) goto L_0x02ee
            r6 = r5
            r10 = 0
        L_0x002b:
            if (r6 >= r3) goto L_0x004c
            r12 = r4[r6]
            java.lang.String r13 = "dsts"
            java.lang.Object r12 = io.netty.util.internal.ObjectUtil.checkNotNullArrayParam(r12, r6, r13)
            java.nio.ByteBuffer r12 = (java.nio.ByteBuffer) r12
            boolean r13 = r12.isReadOnly()
            if (r13 != 0) goto L_0x0046
            int r12 = r12.remaining()
            long r12 = (long) r12
            long r10 = r10 + r12
            int r6 = r6 + 1
            goto L_0x002b
        L_0x0046:
            java.nio.ReadOnlyBufferException r0 = new java.nio.ReadOnlyBufferException
            r0.<init>()
            throw r0
        L_0x004c:
            r6 = r2
            r12 = 0
        L_0x004f:
            if (r6 >= r7) goto L_0x0064
            r14 = r0[r6]
            java.lang.String r15 = "srcs"
            java.lang.Object r14 = io.netty.util.internal.ObjectUtil.checkNotNullArrayParam(r14, r6, r15)
            java.nio.ByteBuffer r14 = (java.nio.ByteBuffer) r14
            int r14 = r14.remaining()
            long r14 = (long) r14
            long r12 = r12 + r14
            int r6 = r6 + 1
            goto L_0x004f
        L_0x0064:
            monitor-enter(r19)
            boolean r6 = r19.isInboundDone()     // Catch:{ all -> 0x02eb }
            if (r6 == 0) goto L_0x007f
            boolean r0 = r19.isOutboundDone()     // Catch:{ all -> 0x02eb }
            if (r0 != 0) goto L_0x007b
            boolean r0 = r19.isDestroyed()     // Catch:{ all -> 0x02eb }
            if (r0 == 0) goto L_0x0078
            goto L_0x007b
        L_0x0078:
            javax.net.ssl.SSLEngineResult r0 = NEED_WRAP_CLOSED     // Catch:{ all -> 0x02eb }
            goto L_0x007d
        L_0x007b:
            javax.net.ssl.SSLEngineResult r0 = CLOSED_NOT_HANDSHAKING     // Catch:{ all -> 0x02eb }
        L_0x007d:
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x007f:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r6 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x02eb }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r14 = r1.handshakeState     // Catch:{ all -> 0x02eb }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r15 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.FINISHED     // Catch:{ all -> 0x02eb }
            r8 = 0
            if (r14 == r15) goto L_0x00b0
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r6 = r1.handshakeState     // Catch:{ all -> 0x02eb }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r9 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.STARTED_EXPLICITLY     // Catch:{ all -> 0x02eb }
            if (r6 == r9) goto L_0x0092
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r6 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.STARTED_IMPLICITLY     // Catch:{ all -> 0x02eb }
            r1.handshakeState = r6     // Catch:{ all -> 0x02eb }
        L_0x0092:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r6 = r19.handshake()     // Catch:{ all -> 0x02eb }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r9 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ all -> 0x02eb }
            if (r6 != r9) goto L_0x00a0
            javax.net.ssl.SSLEngineResult r0 = r1.newResult(r6, r8, r8)     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x00a0:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r9 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x02eb }
            if (r6 != r9) goto L_0x00a8
            javax.net.ssl.SSLEngineResult r0 = NEED_WRAP_OK     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x00a8:
            boolean r9 = r1.isInboundDone     // Catch:{ all -> 0x02eb }
            if (r9 == 0) goto L_0x00b0
            javax.net.ssl.SSLEngineResult r0 = NEED_WRAP_CLOSED     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x00b0:
            int r9 = r19.sslPending0()     // Catch:{ all -> 0x02eb }
            boolean r15 = r1.jdkCompatibilityMode     // Catch:{ all -> 0x02eb }
            if (r15 != 0) goto L_0x00e6
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r15 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.FINISHED     // Catch:{ all -> 0x02eb }
            if (r14 == r15) goto L_0x00bd
            goto L_0x00e6
        L_0x00bd:
            r14 = 0
            int r18 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r18 != 0) goto L_0x00cd
            if (r9 > 0) goto L_0x00cd
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x02eb }
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r8, r8)     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x00cd:
            r14 = 0
            int r16 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r16 != 0) goto L_0x00db
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x02eb }
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r8, r8)     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x00db:
            r10 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r10 = java.lang.Math.min(r10, r12)     // Catch:{ all -> 0x02eb }
            int r11 = (int) r10     // Catch:{ all -> 0x02eb }
            r22 = r9
            goto L_0x014b
        L_0x00e6:
            r14 = 5
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x00f4
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x02eb }
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r8, r8)     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x00f4:
            int r14 = io.netty.handler.ssl.SslUtils.getEncryptedPacketLength((java.nio.ByteBuffer[]) r20, (int) r21)     // Catch:{ all -> 0x02eb }
            r15 = -2
            if (r14 == r15) goto L_0x02e3
            int r15 = r14 + -5
            r22 = r9
            long r8 = (long) r15     // Catch:{ all -> 0x02eb }
            int r16 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r16 <= 0) goto L_0x013b
            int r0 = MAX_RECORD_SIZE     // Catch:{ all -> 0x02eb }
            if (r15 > r0) goto L_0x0116
            io.netty.handler.ssl.OpenSslSession r0 = r1.session     // Catch:{ all -> 0x02eb }
            r0.tryExpandApplicationBufferSize(r15)     // Catch:{ all -> 0x02eb }
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x02eb }
            r2 = 0
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r2, r2)     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x0116:
            javax.net.ssl.SSLException r0 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x02eb }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x02eb }
            r2.<init>()     // Catch:{ all -> 0x02eb }
            java.lang.String r3 = "Illegal packet length: "
            r2.append(r3)     // Catch:{ all -> 0x02eb }
            r2.append(r15)     // Catch:{ all -> 0x02eb }
            java.lang.String r3 = " > "
            r2.append(r3)     // Catch:{ all -> 0x02eb }
            io.netty.handler.ssl.OpenSslSession r3 = r1.session     // Catch:{ all -> 0x02eb }
            int r3 = r3.getApplicationBufferSize()     // Catch:{ all -> 0x02eb }
            r2.append(r3)     // Catch:{ all -> 0x02eb }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x02eb }
            r0.<init>(r2)     // Catch:{ all -> 0x02eb }
            throw r0     // Catch:{ all -> 0x02eb }
        L_0x013b:
            long r8 = (long) r14     // Catch:{ all -> 0x02eb }
            int r10 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x0149
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x02eb }
            r8 = 0
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r8, r8)     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x0149:
            r8 = 0
            r11 = r14
        L_0x014b:
            r10 = r22
            r9 = 0
        L_0x014e:
            r12 = r0[r2]     // Catch:{ all -> 0x02d9 }
            int r13 = r12.remaining()     // Catch:{ all -> 0x02d9 }
            if (r13 != 0) goto L_0x0166
            if (r10 > 0) goto L_0x015e
            int r2 = r2 + 1
            if (r2 < r7) goto L_0x014e
            goto L_0x0277
        L_0x015e:
            long r13 = r1.networkBIO     // Catch:{ all -> 0x02d9 }
            int r13 = io.netty.internal.tcnative.SSL.bioLengthByteBuffer(r13)     // Catch:{ all -> 0x02d9 }
            r14 = 0
            goto L_0x016e
        L_0x0166:
            int r13 = java.lang.Math.min(r11, r13)     // Catch:{ all -> 0x02d9 }
            io.netty.buffer.ByteBuf r14 = r1.writeEncryptedData(r12, r13)     // Catch:{ SSLException -> 0x02c9 }
        L_0x016e:
            r15 = r4[r5]     // Catch:{ all -> 0x02c2 }
            boolean r16 = r15.hasRemaining()     // Catch:{ all -> 0x02c2 }
            if (r16 != 0) goto L_0x0181
            int r5 = r5 + 1
            if (r5 < r3) goto L_0x016e
            if (r14 == 0) goto L_0x0277
        L_0x017c:
            r14.release()     // Catch:{ all -> 0x02d9 }
            goto L_0x0277
        L_0x0181:
            r21 = r10
            int r10 = r1.readPlaintextData(r15)     // Catch:{ SSLException -> 0x02ad }
            r17 = r6
            r16 = r7
            long r6 = r1.networkBIO     // Catch:{ all -> 0x02c2 }
            int r6 = io.netty.internal.tcnative.SSL.bioLengthByteBuffer(r6)     // Catch:{ all -> 0x02c2 }
            int r6 = r13 - r6
            int r8 = r8 + r6
            int r11 = r11 - r6
            int r13 = r13 - r6
            int r7 = r12.position()     // Catch:{ all -> 0x02c2 }
            int r7 = r7 + r6
            r12.position(r7)     // Catch:{ all -> 0x02c2 }
            if (r10 <= 0) goto L_0x01ee
            int r9 = r9 + r10
            boolean r6 = r15.hasRemaining()     // Catch:{ all -> 0x02c2 }
            if (r6 != 0) goto L_0x01dd
            int r10 = r19.sslPending0()     // Catch:{ all -> 0x02c2 }
            int r5 = r5 + 1
            if (r5 < r3) goto L_0x01da
            if (r10 <= 0) goto L_0x01ba
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x02c2 }
            r6 = r17
            javax.net.ssl.SSLEngineResult r0 = r1.newResult(r0, r6, r8, r9)     // Catch:{ all -> 0x02c2 }
            goto L_0x01cb
        L_0x01ba:
            r6 = r17
            boolean r0 = r19.isInboundDone()     // Catch:{ all -> 0x02c2 }
            if (r0 == 0) goto L_0x01c5
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x02c2 }
            goto L_0x01c7
        L_0x01c5:
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch:{ all -> 0x02c2 }
        L_0x01c7:
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r8, r9)     // Catch:{ all -> 0x02c2 }
        L_0x01cb:
            if (r14 == 0) goto L_0x01d0
            r14.release()     // Catch:{ all -> 0x02d9 }
        L_0x01d0:
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x01da:
            r6 = r17
            goto L_0x01e8
        L_0x01dd:
            r6 = r17
            if (r11 == 0) goto L_0x01eb
            boolean r7 = r1.jdkCompatibilityMode     // Catch:{ all -> 0x02c2 }
            if (r7 == 0) goto L_0x01e6
            goto L_0x01eb
        L_0x01e6:
            r10 = r21
        L_0x01e8:
            r7 = r16
            goto L_0x016e
        L_0x01eb:
            if (r14 == 0) goto L_0x0277
            goto L_0x017c
        L_0x01ee:
            r6 = r17
            long r12 = r1.ssl     // Catch:{ all -> 0x02c2 }
            int r7 = io.netty.internal.tcnative.SSL.getError(r12, r10)     // Catch:{ all -> 0x02c2 }
            int r10 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_READ     // Catch:{ all -> 0x02c2 }
            if (r7 == r10) goto L_0x026d
            int r10 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_WRITE     // Catch:{ all -> 0x02c2 }
            if (r7 != r10) goto L_0x0200
            goto L_0x026d
        L_0x0200:
            int r0 = io.netty.internal.tcnative.SSL.SSL_ERROR_ZERO_RETURN     // Catch:{ all -> 0x02c2 }
            if (r7 != r0) goto L_0x0229
            boolean r0 = r1.receivedShutdown     // Catch:{ all -> 0x02c2 }
            if (r0 != 0) goto L_0x020b
            r19.closeAll()     // Catch:{ all -> 0x02c2 }
        L_0x020b:
            boolean r0 = r19.isInboundDone()     // Catch:{ all -> 0x02c2 }
            if (r0 == 0) goto L_0x0214
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x02c2 }
            goto L_0x0216
        L_0x0214:
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch:{ all -> 0x02c2 }
        L_0x0216:
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r8, r9)     // Catch:{ all -> 0x02c2 }
            if (r14 == 0) goto L_0x021f
            r14.release()     // Catch:{ all -> 0x02d9 }
        L_0x021f:
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x0229:
            int r0 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_X509_LOOKUP     // Catch:{ all -> 0x02c2 }
            if (r7 == r0) goto L_0x024d
            int r0 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY     // Catch:{ all -> 0x02c2 }
            if (r7 == r0) goto L_0x024d
            int r0 = io.netty.internal.tcnative.SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION     // Catch:{ all -> 0x02c2 }
            if (r7 != r0) goto L_0x0236
            goto L_0x024d
        L_0x0236:
            int r0 = io.netty.internal.tcnative.SSL.getLastErrorNumber()     // Catch:{ all -> 0x02c2 }
            javax.net.ssl.SSLEngineResult r0 = r1.sslReadErrorResult(r7, r0, r8, r9)     // Catch:{ all -> 0x02c2 }
            if (r14 == 0) goto L_0x0243
            r14.release()     // Catch:{ all -> 0x02d9 }
        L_0x0243:
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x024d:
            boolean r0 = r19.isInboundDone()     // Catch:{ all -> 0x02c2 }
            if (r0 == 0) goto L_0x0256
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x02c2 }
            goto L_0x0258
        L_0x0256:
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch:{ all -> 0x02c2 }
        L_0x0258:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r2 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ all -> 0x02c2 }
            javax.net.ssl.SSLEngineResult r0 = r1.newResult(r0, r2, r8, r9)     // Catch:{ all -> 0x02c2 }
            if (r14 == 0) goto L_0x0263
            r14.release()     // Catch:{ all -> 0x02d9 }
        L_0x0263:
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x026d:
            int r2 = r2 + 1
            r7 = r16
            if (r2 < r7) goto L_0x02a4
            if (r14 == 0) goto L_0x0277
            goto L_0x017c
        L_0x0277:
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            boolean r0 = r1.receivedShutdown     // Catch:{ all -> 0x02eb }
            if (r0 != 0) goto L_0x0293
            long r2 = r1.ssl     // Catch:{ all -> 0x02eb }
            int r0 = io.netty.internal.tcnative.SSL.getShutdown(r2)     // Catch:{ all -> 0x02eb }
            int r2 = io.netty.internal.tcnative.SSL.SSL_RECEIVED_SHUTDOWN     // Catch:{ all -> 0x02eb }
            r0 = r0 & r2
            int r2 = io.netty.internal.tcnative.SSL.SSL_RECEIVED_SHUTDOWN     // Catch:{ all -> 0x02eb }
            if (r0 != r2) goto L_0x0293
            r19.closeAll()     // Catch:{ all -> 0x02eb }
        L_0x0293:
            boolean r0 = r19.isInboundDone()     // Catch:{ all -> 0x02eb }
            if (r0 == 0) goto L_0x029c
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x02eb }
            goto L_0x029e
        L_0x029c:
            javax.net.ssl.SSLEngineResult$Status r0 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch:{ all -> 0x02eb }
        L_0x029e:
            javax.net.ssl.SSLEngineResult r0 = r1.newResultMayFinishHandshake(r0, r6, r8, r9)     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x02a4:
            if (r14 == 0) goto L_0x02a9
            r14.release()     // Catch:{ all -> 0x02d9 }
        L_0x02a9:
            r10 = r21
            goto L_0x014e
        L_0x02ad:
            r0 = move-exception
            r2 = r0
            javax.net.ssl.SSLEngineResult r0 = r1.handleUnwrapException(r8, r9, r2)     // Catch:{ all -> 0x02c2 }
            if (r14 == 0) goto L_0x02b8
            r14.release()     // Catch:{ all -> 0x02d9 }
        L_0x02b8:
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x02c2:
            r0 = move-exception
            if (r14 == 0) goto L_0x02c8
            r14.release()     // Catch:{ all -> 0x02d9 }
        L_0x02c8:
            throw r0     // Catch:{ all -> 0x02d9 }
        L_0x02c9:
            r0 = move-exception
            r2 = r0
            javax.net.ssl.SSLEngineResult r0 = r1.handleUnwrapException(r8, r9, r2)     // Catch:{ all -> 0x02d9 }
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            return r0
        L_0x02d9:
            r0 = move-exception
            long r2 = r1.networkBIO     // Catch:{ all -> 0x02eb }
            io.netty.internal.tcnative.SSL.bioClearByteBuffer(r2)     // Catch:{ all -> 0x02eb }
            r19.rejectRemoteInitiatedRenegotiation()     // Catch:{ all -> 0x02eb }
            throw r0     // Catch:{ all -> 0x02eb }
        L_0x02e3:
            io.netty.handler.ssl.NotSslRecordException r0 = new io.netty.handler.ssl.NotSslRecordException     // Catch:{ all -> 0x02eb }
            java.lang.String r2 = "not an SSL/TLS record"
            r0.<init>((java.lang.String) r2)     // Catch:{ all -> 0x02eb }
            throw r0     // Catch:{ all -> 0x02eb }
        L_0x02eb:
            r0 = move-exception
            monitor-exit(r19)     // Catch:{ all -> 0x02eb }
            throw r0
        L_0x02ee:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "offset: "
            r2.<init>(r3)
            r2.append(r5)
            java.lang.String r3 = ", length: "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r3 = " (expected: offset <= offset + length <= dsts.length ("
            r2.append(r3)
            int r3 = r4.length
            r2.append(r3)
            java.lang.String r3 = "))"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0318:
            java.lang.IndexOutOfBoundsException r4 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "offset: "
            r5.<init>(r6)
            r5.append(r2)
            java.lang.String r2 = ", length: "
            r5.append(r2)
            r5.append(r3)
            java.lang.String r2 = " (expected: offset <= offset + length <= srcs.length ("
            r5.append(r2)
            int r0 = r0.length
            r5.append(r0)
            java.lang.String r0 = "))"
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r4.<init>(r0)
            goto L_0x0343
        L_0x0342:
            throw r4
        L_0x0343:
            goto L_0x0342
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.unwrap(java.nio.ByteBuffer[], int, int, java.nio.ByteBuffer[], int, int):javax.net.ssl.SSLEngineResult");
    }

    private boolean needWrapAgain(int i) {
        if (SSL.bioLengthNonApplication(this.networkBIO) <= 0) {
            return false;
        }
        Throwable th = this.pendingException;
        if (th == null) {
            this.pendingException = newSSLExceptionForError(i);
        } else if (shouldAddSuppressed(th, i)) {
            ThrowableUtil.addSuppressed(this.pendingException, (Throwable) newSSLExceptionForError(i));
        }
        SSL.clearError();
        return true;
    }

    private SSLException newSSLExceptionForError(int i) {
        String errorString = SSL.getErrorString((long) i);
        return this.handshakeState == HandshakeState.FINISHED ? new OpenSslException(errorString, i) : new OpenSslHandshakeException(errorString, i);
    }

    private static boolean shouldAddSuppressed(Throwable th, int i) {
        for (Throwable th2 : ThrowableUtil.getSuppressed(th)) {
            if ((th2 instanceof NativeSslException) && ((NativeSslException) th2).errorCode() == i) {
                return false;
            }
        }
        return true;
    }

    private SSLEngineResult sslReadErrorResult(int i, int i2, int i3, int i4) throws SSLException {
        if (needWrapAgain(i2)) {
            return new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, i3, i4);
        }
        throw shutdownWithError("SSL_read", i, i2);
    }

    private void closeAll() throws SSLException {
        this.receivedShutdown = true;
        closeOutbound();
        closeInbound();
    }

    private void rejectRemoteInitiatedRenegotiation() throws SSLHandshakeException {
        if (isDestroyed()) {
            return;
        }
        if (((!this.clientMode && SSL.getHandshakeCount(this.ssl) > 1) || (this.clientMode && SSL.getHandshakeCount(this.ssl) > 2)) && !SslProtocols.TLS_v1_3.equals(this.session.getProtocol()) && this.handshakeState == HandshakeState.FINISHED) {
            shutdown();
            throw new SSLHandshakeException("remote-initiated renegotiation not allowed");
        }
    }

    public final SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, ByteBuffer[] byteBufferArr2) throws SSLException {
        return unwrap(byteBufferArr, 0, byteBufferArr.length, byteBufferArr2, 0, byteBufferArr2.length);
    }

    private ByteBuffer[] singleSrcBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleSrcBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleSrcBuffer() {
        this.singleSrcBuffer[0] = null;
    }

    private ByteBuffer[] singleDstBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleDstBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleDstBuffer() {
        this.singleDstBuffer[0] = null;
    }

    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i, int i2) throws SSLException {
        SSLEngineResult unwrap;
        try {
            unwrap = unwrap(singleSrcBuffer(byteBuffer), 0, 1, byteBufferArr, i, i2);
            resetSingleSrcBuffer();
        } catch (Throwable th) {
            resetSingleSrcBuffer();
            throw th;
        }
        return unwrap;
    }

    public final synchronized SSLEngineResult wrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        SSLEngineResult wrap;
        try {
            wrap = wrap(singleSrcBuffer(byteBuffer), byteBuffer2);
            resetSingleSrcBuffer();
        } catch (Throwable th) {
            resetSingleSrcBuffer();
            throw th;
        }
        return wrap;
    }

    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        SSLEngineResult unwrap;
        try {
            unwrap = unwrap(singleSrcBuffer(byteBuffer), singleDstBuffer(byteBuffer2));
            resetSingleSrcBuffer();
            resetSingleDstBuffer();
        } catch (Throwable th) {
            resetSingleSrcBuffer();
            resetSingleDstBuffer();
            throw th;
        }
        return unwrap;
    }

    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) throws SSLException {
        SSLEngineResult unwrap;
        try {
            unwrap = unwrap(singleSrcBuffer(byteBuffer), byteBufferArr);
            resetSingleSrcBuffer();
        } catch (Throwable th) {
            resetSingleSrcBuffer();
            throw th;
        }
        return unwrap;
    }

    private class TaskDecorator<R extends Runnable> implements Runnable {
        protected final R task;

        TaskDecorator(R r) {
            this.task = r;
        }

        public void run() {
            ReferenceCountedOpenSslEngine.this.runAndResetNeedTask(this.task);
        }
    }

    private final class AsyncTaskDecorator extends TaskDecorator<AsyncTask> implements AsyncRunnable {
        AsyncTaskDecorator(AsyncTask asyncTask) {
            super(asyncTask);
        }

        public void run(Runnable runnable) {
            if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                this.task.runAsync(new TaskDecorator(runnable));
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void runAndResetNeedTask(Runnable runnable) {
        try {
            if (isDestroyed()) {
                this.needTask = false;
                return;
            }
            runnable.run();
            this.needTask = false;
        } catch (Throwable th) {
            this.needTask = false;
            throw th;
        }
    }

    public final synchronized Runnable getDelegatedTask() {
        if (isDestroyed()) {
            return null;
        }
        AsyncTask task = SSL.getTask(this.ssl);
        if (task == null) {
            return null;
        }
        if (task instanceof AsyncTask) {
            return new AsyncTaskDecorator(task);
        }
        return new TaskDecorator(task);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0027, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void closeInbound() throws javax.net.ssl.SSLException {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isInboundDone     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 1
            r2.isInboundDone = r0     // Catch:{ all -> 0x0028 }
            boolean r0 = r2.isOutboundDone()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0013
            r2.shutdown()     // Catch:{ all -> 0x0028 }
        L_0x0013:
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r0 = r2.handshakeState     // Catch:{ all -> 0x0028 }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r1 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.NOT_STARTED     // Catch:{ all -> 0x0028 }
            if (r0 == r1) goto L_0x0026
            boolean r0 = r2.receivedShutdown     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x001e
            goto L_0x0026
        L_0x001e:
            javax.net.ssl.SSLException r0 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = "Inbound closed before receiving peer's close_notify: possible truncation attack?"
            r0.<init>(r1)     // Catch:{ all -> 0x0028 }
            throw r0     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r2)
            return
        L_0x0028:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.closeInbound():void");
    }

    public final synchronized boolean isInboundDone() {
        return this.isInboundDone;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void closeOutbound() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.outboundClosed     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 1
            r2.outboundClosed = r0     // Catch:{ all -> 0x002c }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r0 = r2.handshakeState     // Catch:{ all -> 0x002c }
            io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r1 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.NOT_STARTED     // Catch:{ all -> 0x002c }
            if (r0 == r1) goto L_0x0027
            boolean r0 = r2.isDestroyed()     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x0027
            long r0 = r2.ssl     // Catch:{ all -> 0x002c }
            int r0 = io.netty.internal.tcnative.SSL.getShutdown(r0)     // Catch:{ all -> 0x002c }
            int r1 = io.netty.internal.tcnative.SSL.SSL_SENT_SHUTDOWN     // Catch:{ all -> 0x002c }
            r0 = r0 & r1
            int r1 = io.netty.internal.tcnative.SSL.SSL_SENT_SHUTDOWN     // Catch:{ all -> 0x002c }
            if (r0 == r1) goto L_0x002a
            r2.doSSLShutdown()     // Catch:{ all -> 0x002c }
            goto L_0x002a
        L_0x0027:
            r2.shutdown()     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r2)
            return
        L_0x002c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.closeOutbound():void");
    }

    private boolean doSSLShutdown() {
        if (SSL.isInInit(this.ssl) != 0) {
            return false;
        }
        int shutdownSSL = SSL.shutdownSSL(this.ssl);
        if (shutdownSSL >= 0) {
            return true;
        }
        int error = SSL.getError(this.ssl, shutdownSSL);
        if (error == SSL.SSL_ERROR_SYSCALL || error == SSL.SSL_ERROR_SSL) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isDebugEnabled()) {
                int lastErrorNumber = SSL.getLastErrorNumber();
                internalLogger.debug("SSL_shutdown failed: OpenSSL error: {} {}", Integer.valueOf(lastErrorNumber), SSL.getErrorString((long) lastErrorNumber));
            }
            shutdown();
            return false;
        }
        SSL.clearError();
        return true;
    }

    public final synchronized boolean isOutboundDone() {
        boolean z;
        if (this.outboundClosed) {
            long j = this.networkBIO;
            if (j == 0 || SSL.bioLengthNonApplication(j) == 0) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public final String[] getSupportedCipherSuites() {
        return (String[]) OpenSsl.AVAILABLE_CIPHER_SUITES.toArray(EmptyArrays.EMPTY_STRINGS);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        return io.netty.util.internal.EmptyArrays.EMPTY_STRINGS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r4 = new java.util.LinkedHashSet(r0.length + r1.length);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r2 >= r0.length) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        r5 = toJavaCipherSuite(r0[r2]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r5 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        r5 = r0[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        if (r3 == false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (io.netty.handler.ssl.OpenSsl.isTlsv13Supported() != false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        if (io.netty.handler.ssl.SslUtils.isTLSv13Cipher(r5) == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        r4.add(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0053, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0056, code lost:
        java.util.Collections.addAll(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0059, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
        return (java.lang.String[]) r4.toArray(io.netty.util.internal.EmptyArrays.EMPTY_STRINGS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r0 != null) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String[] getEnabledCipherSuites() {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x0066
            long r0 = r7.ssl     // Catch:{ all -> 0x006a }
            java.lang.String[] r0 = io.netty.internal.tcnative.SSL.getCiphers(r0)     // Catch:{ all -> 0x006a }
            long r1 = r7.ssl     // Catch:{ all -> 0x006a }
            int r1 = io.netty.internal.tcnative.SSL.getOptions(r1)     // Catch:{ all -> 0x006a }
            int r2 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_3     // Catch:{ all -> 0x006a }
            java.lang.String r3 = "TLSv1.3"
            boolean r1 = isProtocolEnabled(r1, r2, r3)     // Catch:{ all -> 0x006a }
            r2 = 0
            if (r1 == 0) goto L_0x0022
            java.lang.String[] r1 = io.netty.handler.ssl.OpenSsl.EXTRA_SUPPORTED_TLS_1_3_CIPHERS     // Catch:{ all -> 0x006a }
            r3 = 1
            goto L_0x0025
        L_0x0022:
            java.lang.String[] r1 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x006a }
            r3 = 0
        L_0x0025:
            monitor-exit(r7)     // Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x002b
            java.lang.String[] r0 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS
            return r0
        L_0x002b:
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            int r5 = r0.length
            int r6 = r1.length
            int r5 = r5 + r6
            r4.<init>(r5)
            monitor-enter(r7)
        L_0x0034:
            int r5 = r0.length     // Catch:{ all -> 0x0063 }
            if (r2 >= r5) goto L_0x0056
            r5 = r0[r2]     // Catch:{ all -> 0x0063 }
            java.lang.String r5 = r7.toJavaCipherSuite(r5)     // Catch:{ all -> 0x0063 }
            if (r5 != 0) goto L_0x0041
            r5 = r0[r2]     // Catch:{ all -> 0x0063 }
        L_0x0041:
            if (r3 == 0) goto L_0x0049
            boolean r6 = io.netty.handler.ssl.OpenSsl.isTlsv13Supported()     // Catch:{ all -> 0x0063 }
            if (r6 != 0) goto L_0x0050
        L_0x0049:
            boolean r6 = io.netty.handler.ssl.SslUtils.isTLSv13Cipher(r5)     // Catch:{ all -> 0x0063 }
            if (r6 == 0) goto L_0x0050
            goto L_0x0053
        L_0x0050:
            r4.add(r5)     // Catch:{ all -> 0x0063 }
        L_0x0053:
            int r2 = r2 + 1
            goto L_0x0034
        L_0x0056:
            java.util.Collections.addAll(r4, r1)     // Catch:{ all -> 0x0063 }
            monitor-exit(r7)     // Catch:{ all -> 0x0063 }
            java.lang.String[] r0 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS
            java.lang.Object[] r0 = r4.toArray(r0)
            java.lang.String[] r0 = (java.lang.String[]) r0
            return r0
        L_0x0063:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0063 }
            throw r0
        L_0x0066:
            java.lang.String[] r0 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x006a }
            monitor-exit(r7)     // Catch:{ all -> 0x006a }
            return r0
        L_0x006a:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x006a }
            goto L_0x006e
        L_0x006d:
            throw r0
        L_0x006e:
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.getEnabledCipherSuites():java.lang.String[]");
    }

    public final void setEnabledCipherSuites(String[] strArr) {
        ObjectUtil.checkNotNull(strArr, "cipherSuites");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        CipherSuiteConverter.convertToCipherStrings(Arrays.asList(strArr), sb, sb2, OpenSsl.isBoringSSL());
        String sb3 = sb.toString();
        String sb4 = sb2.toString();
        if (OpenSsl.isTlsv13Supported() || sb4.isEmpty()) {
            synchronized (this) {
                if (!isDestroyed()) {
                    try {
                        SSL.setCipherSuites(this.ssl, sb3, false);
                        if (OpenSsl.isTlsv13Supported()) {
                            SSL.setCipherSuites(this.ssl, OpenSsl.checkTls13Ciphers(logger, sb4), true);
                        }
                        HashSet hashSet = new HashSet(this.explicitlyEnabledProtocols.length);
                        Collections.addAll(hashSet, this.explicitlyEnabledProtocols);
                        if (sb3.isEmpty()) {
                            hashSet.remove(SslProtocols.TLS_v1);
                            hashSet.remove(SslProtocols.TLS_v1_1);
                            hashSet.remove(SslProtocols.TLS_v1_2);
                            hashSet.remove(SslProtocols.SSL_v3);
                            hashSet.remove(SslProtocols.SSL_v2);
                            hashSet.remove(SslProtocols.SSL_v2_HELLO);
                        }
                        if (sb4.isEmpty()) {
                            hashSet.remove(SslProtocols.TLS_v1_3);
                        }
                        setEnabledProtocols0((String[]) hashSet.toArray(EmptyArrays.EMPTY_STRINGS), false);
                    } catch (Exception e) {
                        throw new IllegalStateException("failed to enable cipher suites: " + sb3, e);
                    }
                } else {
                    throw new IllegalStateException("failed to enable cipher suites: " + sb3);
                }
            }
            return;
        }
        throw new IllegalArgumentException("TLSv1.3 is not supported by this java version.");
    }

    public final String[] getSupportedProtocols() {
        return (String[]) OpenSsl.SUPPORTED_PROTOCOLS_SET.toArray(EmptyArrays.EMPTY_STRINGS);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (isProtocolEnabled(r1, io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_1, io.netty.handler.ssl.SslProtocols.TLS_v1_1) == false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        r0.add(io.netty.handler.ssl.SslProtocols.TLS_v1_1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        if (isProtocolEnabled(r1, io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_2, io.netty.handler.ssl.SslProtocols.TLS_v1_2) == false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        r0.add(io.netty.handler.ssl.SslProtocols.TLS_v1_2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        if (isProtocolEnabled(r1, io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_3, io.netty.handler.ssl.SslProtocols.TLS_v1_3) == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        r0.add(io.netty.handler.ssl.SslProtocols.TLS_v1_3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005d, code lost:
        if (isProtocolEnabled(r1, io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv2, io.netty.handler.ssl.SslProtocols.SSL_v2) == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        r0.add(io.netty.handler.ssl.SslProtocols.SSL_v2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        if (isProtocolEnabled(r1, io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv3, io.netty.handler.ssl.SslProtocols.SSL_v3) == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        r0.add(io.netty.handler.ssl.SslProtocols.SSL_v3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007b, code lost:
        return (java.lang.String[]) r0.toArray(io.netty.util.internal.EmptyArrays.EMPTY_STRINGS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (isProtocolEnabled(r1, io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1, io.netty.handler.ssl.SslProtocols.TLS_v1) == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        r0.add(io.netty.handler.ssl.SslProtocols.TLS_v1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String[] getEnabledProtocols() {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 6
            r0.<init>(r1)
            java.lang.String r1 = "SSLv2Hello"
            r0.add(r1)
            monitor-enter(r4)
            boolean r1 = r4.isDestroyed()     // Catch:{ all -> 0x0086 }
            if (r1 != 0) goto L_0x007c
            long r1 = r4.ssl     // Catch:{ all -> 0x0086 }
            int r1 = io.netty.internal.tcnative.SSL.getOptions(r1)     // Catch:{ all -> 0x0086 }
            monitor-exit(r4)     // Catch:{ all -> 0x0086 }
            int r2 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1
            java.lang.String r3 = "TLSv1"
            boolean r2 = isProtocolEnabled(r1, r2, r3)
            if (r2 == 0) goto L_0x0028
            java.lang.String r2 = "TLSv1"
            r0.add(r2)
        L_0x0028:
            int r2 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_1
            java.lang.String r3 = "TLSv1.1"
            boolean r2 = isProtocolEnabled(r1, r2, r3)
            if (r2 == 0) goto L_0x0037
            java.lang.String r2 = "TLSv1.1"
            r0.add(r2)
        L_0x0037:
            int r2 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_2
            java.lang.String r3 = "TLSv1.2"
            boolean r2 = isProtocolEnabled(r1, r2, r3)
            if (r2 == 0) goto L_0x0046
            java.lang.String r2 = "TLSv1.2"
            r0.add(r2)
        L_0x0046:
            int r2 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_3
            java.lang.String r3 = "TLSv1.3"
            boolean r2 = isProtocolEnabled(r1, r2, r3)
            if (r2 == 0) goto L_0x0055
            java.lang.String r2 = "TLSv1.3"
            r0.add(r2)
        L_0x0055:
            int r2 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv2
            java.lang.String r3 = "SSLv2"
            boolean r2 = isProtocolEnabled(r1, r2, r3)
            if (r2 == 0) goto L_0x0064
            java.lang.String r2 = "SSLv2"
            r0.add(r2)
        L_0x0064:
            int r2 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv3
            java.lang.String r3 = "SSLv3"
            boolean r1 = isProtocolEnabled(r1, r2, r3)
            if (r1 == 0) goto L_0x0073
            java.lang.String r1 = "SSLv3"
            r0.add(r1)
        L_0x0073:
            java.lang.String[] r1 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS
            java.lang.Object[] r0 = r0.toArray(r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
            return r0
        L_0x007c:
            java.lang.String[] r1 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x0086 }
            java.lang.Object[] r0 = r0.toArray(r1)     // Catch:{ all -> 0x0086 }
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch:{ all -> 0x0086 }
            monitor-exit(r4)     // Catch:{ all -> 0x0086 }
            return r0
        L_0x0086:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0086 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.getEnabledProtocols():java.lang.String[]");
    }

    private static boolean isProtocolEnabled(int i, int i2, String str) {
        return (i & i2) == 0 && OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(str);
    }

    public final void setEnabledProtocols(String[] strArr) {
        setEnabledProtocols0(strArr, true);
    }

    private void setEnabledProtocols0(String[] strArr, boolean z) {
        ObjectUtil.checkNotNullWithIAE(strArr, "protocols");
        int length = OPENSSL_OP_NO_PROTOCOLS.length;
        int length2 = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            String str = strArr[i];
            if (OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(str)) {
                if (str.equals(SslProtocols.SSL_v2)) {
                    if (length > 0) {
                        length = 0;
                    }
                    if (i2 < 0) {
                        i2 = 0;
                    }
                } else if (str.equals(SslProtocols.SSL_v3)) {
                    if (length > 1) {
                        length = 1;
                    }
                    if (i2 < 1) {
                        i2 = 1;
                    }
                } else if (str.equals(SslProtocols.TLS_v1)) {
                    if (length > 2) {
                        length = 2;
                    }
                    if (i2 < 2) {
                        i2 = 2;
                    }
                } else if (str.equals(SslProtocols.TLS_v1_1)) {
                    if (length > 3) {
                        length = 3;
                    }
                    if (i2 < 3) {
                        i2 = 3;
                    }
                } else if (str.equals(SslProtocols.TLS_v1_2)) {
                    if (length > 4) {
                        length = 4;
                    }
                    if (i2 < 4) {
                        i2 = 4;
                    }
                } else if (str.equals(SslProtocols.TLS_v1_3)) {
                    if (length > 5) {
                        length = 5;
                    }
                    if (i2 < 5) {
                        i2 = 5;
                    }
                }
                i++;
            } else {
                throw new IllegalArgumentException("Protocol " + str + " is not supported.");
            }
        }
        synchronized (this) {
            if (z) {
                this.explicitlyEnabledProtocols = strArr;
            }
            if (!isDestroyed()) {
                SSL.clearOptions(this.ssl, SSL.SSL_OP_NO_SSLv2 | SSL.SSL_OP_NO_SSLv3 | SSL.SSL_OP_NO_TLSv1 | SSL.SSL_OP_NO_TLSv1_1 | SSL.SSL_OP_NO_TLSv1_2 | SSL.SSL_OP_NO_TLSv1_3);
                int i3 = 0;
                for (int i4 = 0; i4 < length; i4++) {
                    i3 |= OPENSSL_OP_NO_PROTOCOLS[i4];
                }
                int i5 = i2 + 1;
                while (true) {
                    int[] iArr = OPENSSL_OP_NO_PROTOCOLS;
                    if (i5 < iArr.length) {
                        i3 |= iArr[i5];
                        i5++;
                    } else {
                        SSL.setOptions(this.ssl, i3);
                    }
                }
            } else {
                throw new IllegalStateException("failed to enable protocols: " + Arrays.asList(strArr));
            }
        }
    }

    public final SSLSession getSession() {
        return this.session;
    }

    public final synchronized void beginHandshake() throws SSLException {
        int i = AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState[this.handshakeState.ordinal()];
        if (i == 1) {
            this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
            if (handshake() == SSLEngineResult.HandshakeStatus.NEED_TASK) {
                this.needTask = true;
            }
            calculateMaxWrapOverhead();
        } else if (i == 2) {
            throw new SSLException("renegotiation unsupported");
        } else if (i == 3) {
            checkEngineClosed();
            this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
            calculateMaxWrapOverhead();
        } else if (i != 4) {
            throw new Error();
        }
    }

    private void checkEngineClosed() throws SSLException {
        if (isDestroyed()) {
            throw new SSLException("engine closed");
        }
    }

    private static SSLEngineResult.HandshakeStatus pendingStatus(int i) {
        return i > 0 ? SSLEngineResult.HandshakeStatus.NEED_WRAP : SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
    }

    /* access modifiers changed from: private */
    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    /* access modifiers changed from: private */
    public static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    private SSLEngineResult.HandshakeStatus handshakeException() throws SSLException {
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        }
        Throwable th = this.pendingException;
        this.pendingException = null;
        shutdown();
        if (th instanceof SSLHandshakeException) {
            throw ((SSLHandshakeException) th);
        }
        SSLHandshakeException sSLHandshakeException = new SSLHandshakeException("General OpenSslEngine problem");
        sSLHandshakeException.initCause(th);
        throw sSLHandshakeException;
    }

    /* access modifiers changed from: package-private */
    public final void initHandshakeException(Throwable th) {
        Throwable th2 = this.pendingException;
        if (th2 == null) {
            this.pendingException = th;
        } else {
            ThrowableUtil.addSuppressed(th2, th);
        }
    }

    private SSLEngineResult.HandshakeStatus handshake() throws SSLException {
        if (this.needTask) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        }
        if (this.handshakeState == HandshakeState.FINISHED) {
            return SSLEngineResult.HandshakeStatus.FINISHED;
        }
        checkEngineClosed();
        if (this.pendingException != null) {
            if (SSL.doHandshake(this.ssl) <= 0) {
                SSL.clearError();
            }
            return handshakeException();
        }
        this.engineMap.add(this);
        if (!this.sessionSet) {
            this.parentContext.sessionContext().setSessionFromCache(getPeerHost(), getPeerPort(), this.ssl);
            this.sessionSet = true;
        }
        if (this.lastAccessed == -1) {
            this.lastAccessed = System.currentTimeMillis();
        }
        int doHandshake = SSL.doHandshake(this.ssl);
        if (doHandshake <= 0) {
            int error = SSL.getError(this.ssl, doHandshake);
            if (error == SSL.SSL_ERROR_WANT_READ || error == SSL.SSL_ERROR_WANT_WRITE) {
                return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
            }
            if (error == SSL.SSL_ERROR_WANT_X509_LOOKUP || error == SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY || error == SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                return SSLEngineResult.HandshakeStatus.NEED_TASK;
            }
            if (needWrapAgain(SSL.getLastErrorNumber())) {
                return SSLEngineResult.HandshakeStatus.NEED_WRAP;
            }
            if (this.pendingException != null) {
                return handshakeException();
            }
            throw shutdownWithError("SSL_do_handshake", error);
        } else if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        } else {
            this.session.handshakeFinished(SSL.getSessionId(this.ssl), SSL.getCipherForSSL(this.ssl), SSL.getVersion(this.ssl), SSL.getPeerCertificate(this.ssl), SSL.getPeerCertChain(this.ssl), SSL.getTime(this.ssl) * 1000, 1000 * this.parentContext.sessionTimeout());
            selectApplicationProtocol();
            return SSLEngineResult.HandshakeStatus.FINISHED;
        }
    }

    private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        if ((handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_UNWRAP && i2 > 0) || (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_WRAP && i > 0)) {
            return handshake();
        }
        return mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? getHandshakeStatus() : SSLEngineResult.HandshakeStatus.FINISHED);
    }

    private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        if (handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            return handshakeStatus;
        }
        if (this.handshakeState != HandshakeState.FINISHED) {
            return handshake();
        }
        return (isDestroyed() || SSL.bioLengthNonApplication(this.networkBIO) <= 0) ? handshakeStatus : SSLEngineResult.HandshakeStatus.NEED_WRAP;
    }

    public final synchronized SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        if (!needPendingStatus()) {
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        } else if (this.needTask) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        } else {
            return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
        }
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatus(int i) {
        if (!needPendingStatus()) {
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        }
        if (this.needTask) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        }
        return pendingStatus(i);
    }

    private boolean needPendingStatus() {
        return this.handshakeState != HandshakeState.NOT_STARTED && !isDestroyed() && (this.handshakeState != HandshakeState.FINISHED || isInboundDone() || isOutboundDone());
    }

    /* access modifiers changed from: private */
    public String toJavaCipherSuite(String str) {
        if (str == null) {
            return null;
        }
        return CipherSuiteConverter.toJava(str, toJavaCipherSuitePrefix(SSL.getVersion(this.ssl)));
    }

    private static String toJavaCipherSuitePrefix(String str) {
        char c = 0;
        if (str != null && !str.isEmpty()) {
            c = str.charAt(0);
        }
        if (c == 'S') {
            return "SSL";
        }
        if (c != 'T') {
            return "UNKNOWN";
        }
        return "TLS";
    }

    public final void setUseClientMode(boolean z) {
        if (z != this.clientMode) {
            throw new UnsupportedOperationException();
        }
    }

    public final boolean getUseClientMode() {
        return this.clientMode;
    }

    public final void setNeedClientAuth(boolean z) {
        setClientAuth(z ? ClientAuth.REQUIRE : ClientAuth.NONE);
    }

    public final boolean getNeedClientAuth() {
        return this.clientAuth == ClientAuth.REQUIRE;
    }

    public final void setWantClientAuth(boolean z) {
        setClientAuth(z ? ClientAuth.OPTIONAL : ClientAuth.NONE);
    }

    public final boolean getWantClientAuth() {
        return this.clientAuth == ClientAuth.OPTIONAL;
    }

    public final synchronized void setVerify(int i, int i2) {
        if (!isDestroyed()) {
            SSL.setVerify(this.ssl, i, i2);
        }
    }

    private void setClientAuth(ClientAuth clientAuth2) {
        if (!this.clientMode) {
            synchronized (this) {
                if (this.clientAuth != clientAuth2) {
                    if (!isDestroyed()) {
                        int i = AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ClientAuth[clientAuth2.ordinal()];
                        if (i == 1) {
                            SSL.setVerify(this.ssl, 0, 10);
                        } else if (i == 2) {
                            SSL.setVerify(this.ssl, 2, 10);
                        } else if (i == 3) {
                            SSL.setVerify(this.ssl, 1, 10);
                        } else {
                            throw new Error(clientAuth2.toString());
                        }
                    }
                    this.clientAuth = clientAuth2;
                }
            }
        }
    }

    public final void setEnableSessionCreation(boolean z) {
        if (z) {
            throw new UnsupportedOperationException();
        }
    }

    public final synchronized SSLParameters getSSLParameters() {
        SSLParameters sSLParameters;
        sSLParameters = super.getSSLParameters();
        int javaVersion = PlatformDependent.javaVersion();
        if (javaVersion >= 7) {
            sSLParameters.setEndpointIdentificationAlgorithm(this.endPointIdentificationAlgorithm);
            Java7SslParametersUtils.setAlgorithmConstraints(sSLParameters, this.algorithmConstraints);
            if (javaVersion >= 8) {
                List<String> list = this.sniHostNames;
                if (list != null) {
                    Java8SslUtils.setSniHostNames(sSLParameters, list);
                }
                if (!isDestroyed()) {
                    Java8SslUtils.setUseCipherSuitesOrder(sSLParameters, (SSL.getOptions(this.ssl) & SSL.SSL_OP_CIPHER_SERVER_PREFERENCE) != 0);
                }
                Java8SslUtils.setSNIMatchers(sSLParameters, this.matchers);
            }
        }
        return sSLParameters;
    }

    public final synchronized void setSSLParameters(SSLParameters sSLParameters) {
        int javaVersion = PlatformDependent.javaVersion();
        if (javaVersion >= 7) {
            if (NioPathKt$$ExternalSyntheticApiModelOutline0.m(sSLParameters) == null) {
                boolean isDestroyed = isDestroyed();
                if (javaVersion >= 8) {
                    if (!isDestroyed) {
                        if (this.clientMode) {
                            List<String> sniHostNames2 = Java8SslUtils.getSniHostNames(sSLParameters);
                            for (String tlsExtHostName : sniHostNames2) {
                                SSL.setTlsExtHostName(this.ssl, tlsExtHostName);
                            }
                            this.sniHostNames = sniHostNames2;
                        }
                        if (Java8SslUtils.getUseCipherSuitesOrder(sSLParameters)) {
                            SSL.setOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
                        } else {
                            SSL.clearOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
                        }
                    }
                    this.matchers = NioPathKt$$ExternalSyntheticApiModelOutline0.m(sSLParameters);
                }
                String m = NioPathKt$$ExternalSyntheticApiModelOutline0.m(sSLParameters);
                if (!isDestroyed && this.clientMode && isEndPointVerificationEnabled(m)) {
                    SSL.setVerify(this.ssl, 2, -1);
                }
                this.endPointIdentificationAlgorithm = m;
                this.algorithmConstraints = NioPathKt$$ExternalSyntheticApiModelOutline0.m(sSLParameters);
            } else {
                throw new IllegalArgumentException("AlgorithmConstraints are not supported.");
            }
        }
        super.setSSLParameters(sSLParameters);
    }

    private static boolean isEndPointVerificationEnabled(String str) {
        return str != null && !str.isEmpty();
    }

    /* access modifiers changed from: private */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /* access modifiers changed from: package-private */
    public final boolean checkSniHostnameMatch(byte[] bArr) {
        return Java8SslUtils.checkSniHostnameMatch(this.matchers, bArr);
    }

    public String getNegotiatedApplicationProtocol() {
        return this.applicationProtocol;
    }

    private static long bufferAddress(ByteBuffer byteBuffer) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.directBufferAddress(byteBuffer);
        }
        return Buffer.address(byteBuffer);
    }

    private void selectApplicationProtocol() throws SSLException {
        ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior = this.apn.selectedListenerFailureBehavior();
        List<String> protocols = this.apn.protocols();
        int i = AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol[this.apn.protocol().ordinal()];
        if (i == 1) {
            return;
        }
        if (i == 2) {
            String alpnSelected = SSL.getAlpnSelected(this.ssl);
            if (alpnSelected != null) {
                this.applicationProtocol = selectApplicationProtocol(protocols, selectedListenerFailureBehavior, alpnSelected);
            }
        } else if (i == 3) {
            String nextProtoNegotiated = SSL.getNextProtoNegotiated(this.ssl);
            if (nextProtoNegotiated != null) {
                this.applicationProtocol = selectApplicationProtocol(protocols, selectedListenerFailureBehavior, nextProtoNegotiated);
            }
        } else if (i == 4) {
            String alpnSelected2 = SSL.getAlpnSelected(this.ssl);
            if (alpnSelected2 == null) {
                alpnSelected2 = SSL.getNextProtoNegotiated(this.ssl);
            }
            if (alpnSelected2 != null) {
                this.applicationProtocol = selectApplicationProtocol(protocols, selectedListenerFailureBehavior, alpnSelected2);
            }
        } else {
            throw new Error();
        }
    }

    /* renamed from: io.netty.handler.ssl.ReferenceCountedOpenSslEngine$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ClientAuth;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0069 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0073 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007d */
        static {
            /*
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol[] r0 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol = r0
                r1 = 1
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r2 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r3 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.ALPN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r4 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.NPN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r5 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.NPN_AND_ALPN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                io.netty.handler.ssl.ClientAuth[] r4 = io.netty.handler.ssl.ClientAuth.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$io$netty$handler$ssl$ClientAuth = r4
                io.netty.handler.ssl.ClientAuth r5 = io.netty.handler.ssl.ClientAuth.NONE     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r4 = $SwitchMap$io$netty$handler$ssl$ClientAuth     // Catch:{ NoSuchFieldError -> 0x004e }
                io.netty.handler.ssl.ClientAuth r5 = io.netty.handler.ssl.ClientAuth.REQUIRE     // Catch:{ NoSuchFieldError -> 0x004e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r4 = $SwitchMap$io$netty$handler$ssl$ClientAuth     // Catch:{ NoSuchFieldError -> 0x0058 }
                io.netty.handler.ssl.ClientAuth r5 = io.netty.handler.ssl.ClientAuth.OPTIONAL     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState[] r4 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState = r4
                io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r5 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.NOT_STARTED     // Catch:{ NoSuchFieldError -> 0x0069 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0069 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0069 }
            L_0x0069:
                int[] r1 = $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState     // Catch:{ NoSuchFieldError -> 0x0073 }
                io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r4 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.FINISHED     // Catch:{ NoSuchFieldError -> 0x0073 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0073 }
            L_0x0073:
                int[] r0 = $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState     // Catch:{ NoSuchFieldError -> 0x007d }
                io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r1 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.STARTED_IMPLICITLY     // Catch:{ NoSuchFieldError -> 0x007d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r0 = $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState     // Catch:{ NoSuchFieldError -> 0x0087 }
                io.netty.handler.ssl.ReferenceCountedOpenSslEngine$HandshakeState r1 = io.netty.handler.ssl.ReferenceCountedOpenSslEngine.HandshakeState.STARTED_EXPLICITLY     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.AnonymousClass3.<clinit>():void");
        }
    }

    private String selectApplicationProtocol(List<String> list, ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior, String str) throws SSLException {
        if (selectedListenerFailureBehavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT) {
            return str;
        }
        int size = list.size();
        if (list.contains(str)) {
            return str;
        }
        if (selectedListenerFailureBehavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL) {
            return list.get(size - 1);
        }
        throw new SSLException("unknown protocol " + str);
    }

    /* access modifiers changed from: package-private */
    public final void setSessionId(OpenSslSessionId openSslSessionId) {
        this.session.setSessionId(openSslSessionId);
    }

    private final class DefaultOpenSslSession implements OpenSslSession {
        private volatile int applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH;
        private String cipher;
        private volatile long creationTime;
        private OpenSslSessionId id = OpenSslSessionId.NULL_ID;
        private volatile Certificate[] localCertificateChain;
        private Certificate[] peerCerts;
        private String protocol;
        private final OpenSslSessionContext sessionContext;
        private boolean valid = true;
        private Map<String, Object> values;
        private X509Certificate[] x509PeerCerts;

        DefaultOpenSslSession(OpenSslSessionContext openSslSessionContext) {
            this.sessionContext = openSslSessionContext;
        }

        private SSLSessionBindingEvent newSSLSessionBindingEvent(String str) {
            return new SSLSessionBindingEvent(ReferenceCountedOpenSslEngine.this.session, str);
        }

        public void setSessionId(OpenSslSessionId openSslSessionId) {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.id == OpenSslSessionId.NULL_ID) {
                    this.id = openSslSessionId;
                    this.creationTime = System.currentTimeMillis();
                }
            }
        }

        public OpenSslSessionId sessionId() {
            OpenSslSessionId openSslSessionId;
            byte[] sessionId;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.id == OpenSslSessionId.NULL_ID && !ReferenceCountedOpenSslEngine.this.isDestroyed() && (sessionId = SSL.getSessionId(ReferenceCountedOpenSslEngine.this.ssl)) != null) {
                    this.id = new OpenSslSessionId(sessionId);
                }
                openSslSessionId = this.id;
            }
            return openSslSessionId;
        }

        public void setLocalCertificate(Certificate[] certificateArr) {
            this.localCertificateChain = certificateArr;
        }

        public byte[] getId() {
            return sessionId().cloneBytes();
        }

        public OpenSslSessionContext getSessionContext() {
            return this.sessionContext;
        }

        public long getCreationTime() {
            long j;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                j = this.creationTime;
            }
            return j;
        }

        public long getLastAccessedTime() {
            long access$900 = ReferenceCountedOpenSslEngine.this.lastAccessed;
            return access$900 == -1 ? getCreationTime() : access$900;
        }

        public void invalidate() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                this.valid = false;
                this.sessionContext.removeFromCache(this.id);
            }
        }

        public boolean isValid() {
            boolean z;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!this.valid) {
                    if (!this.sessionContext.isInCache(this.id)) {
                        z = false;
                    }
                }
                z = true;
            }
            return z;
        }

        public void putValue(String str, Object obj) {
            Object put;
            ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
            ObjectUtil.checkNotNull(obj, "value");
            synchronized (this) {
                Map map = this.values;
                if (map == null) {
                    map = new HashMap(2);
                    this.values = map;
                }
                put = map.put(str, obj);
            }
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueBound(newSSLSessionBindingEvent(str));
            }
            notifyUnbound(put, str);
        }

        public Object getValue(String str) {
            ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map == null) {
                    return null;
                }
                Object obj = map.get(str);
                return obj;
            }
        }

        public void removeValue(String str) {
            ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map != null) {
                    Object remove = map.remove(str);
                    notifyUnbound(remove, str);
                }
            }
        }

        public String[] getValueNames() {
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map != null) {
                    if (!map.isEmpty()) {
                        String[] strArr = (String[]) map.keySet().toArray(EmptyArrays.EMPTY_STRINGS);
                        return strArr;
                    }
                }
                String[] strArr2 = EmptyArrays.EMPTY_STRINGS;
                return strArr2;
            }
        }

        private void notifyUnbound(Object obj, String str) {
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueUnbound(newSSLSessionBindingEvent(str));
            }
        }

        public void handshakeFinished(byte[] bArr, String str, String str2, byte[] bArr2, byte[][] bArr3, long j, long j2) throws SSLException {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    this.creationTime = j;
                    if (this.id == OpenSslSessionId.NULL_ID) {
                        this.id = bArr == null ? OpenSslSessionId.NULL_ID : new OpenSslSessionId(bArr);
                    }
                    this.cipher = ReferenceCountedOpenSslEngine.this.toJavaCipherSuite(str);
                    this.protocol = str2;
                    if (ReferenceCountedOpenSslEngine.this.clientMode) {
                        if (ReferenceCountedOpenSslEngine.isEmpty((Object[]) bArr3)) {
                            this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
                            if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                                this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
                            } else {
                                this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                            }
                        } else {
                            this.peerCerts = new Certificate[bArr3.length];
                            if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                                this.x509PeerCerts = new X509Certificate[bArr3.length];
                            } else {
                                this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                            }
                            initCerts(bArr3, 0);
                        }
                    } else if (ReferenceCountedOpenSslEngine.isEmpty(bArr2)) {
                        this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
                        this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
                    } else if (ReferenceCountedOpenSslEngine.isEmpty((Object[]) bArr3)) {
                        this.peerCerts = new Certificate[]{new LazyX509Certificate(bArr2)};
                        if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                            this.x509PeerCerts = new X509Certificate[]{new LazyJavaxX509Certificate(bArr2)};
                        } else {
                            this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                        }
                    } else {
                        Certificate[] certificateArr = new Certificate[(bArr3.length + 1)];
                        this.peerCerts = certificateArr;
                        certificateArr[0] = new LazyX509Certificate(bArr2);
                        if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                            X509Certificate[] x509CertificateArr = new X509Certificate[(bArr3.length + 1)];
                            this.x509PeerCerts = x509CertificateArr;
                            x509CertificateArr[0] = new LazyJavaxX509Certificate(bArr2);
                        } else {
                            this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                        }
                        initCerts(bArr3, 1);
                    }
                    ReferenceCountedOpenSslEngine.this.calculateMaxWrapOverhead();
                    HandshakeState unused = ReferenceCountedOpenSslEngine.this.handshakeState = HandshakeState.FINISHED;
                } else {
                    throw new SSLException("Already closed");
                }
            }
        }

        private void initCerts(byte[][] bArr, int i) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                int i3 = i + i2;
                this.peerCerts[i3] = new LazyX509Certificate(bArr[i2]);
                if (this.x509PeerCerts != ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED) {
                    this.x509PeerCerts[i3] = new LazyJavaxX509Certificate(bArr[i2]);
                }
            }
        }

        public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
            Certificate[] certificateArr;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.isEmpty((Object[]) this.peerCerts)) {
                    certificateArr = (Certificate[]) this.peerCerts.clone();
                } else {
                    throw new SSLPeerUnverifiedException("peer not verified");
                }
            }
            return certificateArr;
        }

        public Certificate[] getLocalCertificates() {
            Certificate[] certificateArr = this.localCertificateChain;
            if (certificateArr == null) {
                return null;
            }
            return (Certificate[]) certificateArr.clone();
        }

        public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
            X509Certificate[] x509CertificateArr;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.x509PeerCerts == ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED) {
                    throw new UnsupportedOperationException();
                } else if (!ReferenceCountedOpenSslEngine.isEmpty((Object[]) this.x509PeerCerts)) {
                    x509CertificateArr = (X509Certificate[]) this.x509PeerCerts.clone();
                } else {
                    throw new SSLPeerUnverifiedException("peer not verified");
                }
            }
            return x509CertificateArr;
        }

        public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
            return ((java.security.cert.X509Certificate) getPeerCertificates()[0]).getSubjectX500Principal();
        }

        public Principal getLocalPrincipal() {
            Certificate[] certificateArr = this.localCertificateChain;
            if (certificateArr == null || certificateArr.length == 0) {
                return null;
            }
            return ((java.security.cert.X509Certificate) certificateArr[0]).getSubjectX500Principal();
        }

        public String getCipherSuite() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                String str = this.cipher;
                if (str == null) {
                    return "SSL_NULL_WITH_NULL_NULL";
                }
                return str;
            }
        }

        public String getProtocol() {
            String str = this.protocol;
            if (str == null) {
                synchronized (ReferenceCountedOpenSslEngine.this) {
                    if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                        str = SSL.getVersion(ReferenceCountedOpenSslEngine.this.ssl);
                    } else {
                        str = "";
                    }
                }
            }
            return str;
        }

        public String getPeerHost() {
            return ReferenceCountedOpenSslEngine.this.getPeerHost();
        }

        public int getPeerPort() {
            return ReferenceCountedOpenSslEngine.this.getPeerPort();
        }

        public int getPacketBufferSize() {
            return SSL.SSL_MAX_ENCRYPTED_LENGTH;
        }

        public int getApplicationBufferSize() {
            return this.applicationBufferSize;
        }

        public void tryExpandApplicationBufferSize(int i) {
            if (i > ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH && this.applicationBufferSize != ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE) {
                this.applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE;
            }
        }

        public String toString() {
            return "DefaultOpenSslSession{sessionContext=" + this.sessionContext + ", id=" + this.id + AbstractJsonLexerKt.END_OBJ;
        }
    }

    private static final class OpenSslException extends SSLException implements NativeSslException {
        private final int errorCode;

        OpenSslException(String str, int i) {
            super(str);
            this.errorCode = i;
        }

        public int errorCode() {
            return this.errorCode;
        }
    }

    private static final class OpenSslHandshakeException extends SSLHandshakeException implements NativeSslException {
        private final int errorCode;

        OpenSslHandshakeException(String str, int i) {
            super(str);
            this.errorCode = i;
        }

        public int errorCode() {
            return this.errorCode;
        }
    }
}
