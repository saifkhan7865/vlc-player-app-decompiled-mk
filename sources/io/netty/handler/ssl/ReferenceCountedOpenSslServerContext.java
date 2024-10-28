package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.netty.internal.tcnative.CertificateCallback;
import io.netty.internal.tcnative.SSLContext;
import io.netty.internal.tcnative.SniHostNameMatcher;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

public final class ReferenceCountedOpenSslServerContext extends ReferenceCountedOpenSslContext {
    private static final byte[] ID = {110, 101, 116, 116, 121};
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslServerContext.class);
    private final OpenSslServerSessionContext sessionContext;

    ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2, Map.Entry<SslContextOption<?>, Object>... entryArr) throws SSLException {
        this(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2, clientAuth, strArr, z, z2, str2, entryArr);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2, Map.Entry<SslContextOption<?>, Object>... entryArr) throws SSLException {
        super(iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, 1, x509CertificateArr2, clientAuth, strArr, z, z2, true, entryArr);
        OpenSslServerSessionContext newSessionContext;
        try {
            try {
                newSessionContext = newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, str2, j, j2);
            } catch (Throwable th) {
                th = th;
                release();
                throw th;
            }
            try {
                this.sessionContext = newSessionContext;
                if (SERVER_ENABLE_SESSION_TICKET) {
                    newSessionContext.setTicketKeys(new OpenSslSessionTicketKey[0]);
                }
            } catch (Throwable th2) {
                th = th2;
                release();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            release();
            throw th;
        }
    }

    public OpenSslServerSessionContext sessionContext() {
        return this.sessionContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static io.netty.handler.ssl.OpenSslServerSessionContext newSessionContext(io.netty.handler.ssl.ReferenceCountedOpenSslContext r16, long r17, io.netty.handler.ssl.OpenSslEngineMap r19, java.security.cert.X509Certificate[] r20, javax.net.ssl.TrustManagerFactory r21, java.security.cert.X509Certificate[] r22, java.security.PrivateKey r23, java.lang.String r24, javax.net.ssl.KeyManagerFactory r25, java.lang.String r26, long r27, long r29) throws javax.net.ssl.SSLException {
        /*
            r0 = r17
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r26
            r9 = r27
            r11 = r29
            java.lang.String r13 = "unable to setup accepted issuers for trustmanager "
            r14 = 0
            r15 = 10
            r11 = 0
            io.netty.internal.tcnative.SSLContext.setVerify(r0, r14, r15)     // Catch:{ Exception -> 0x0124 }
            boolean r12 = io.netty.handler.ssl.OpenSsl.useKeyManagerFactory()     // Catch:{ Exception -> 0x0124 }
            if (r12 != 0) goto L_0x0037
            if (r25 != 0) goto L_0x002f
            java.lang.String r12 = "keyCertChain"
            io.netty.util.internal.ObjectUtil.checkNotNull(r5, r12)     // Catch:{ Exception -> 0x0124 }
            setKeyMaterial(r0, r5, r6, r7)     // Catch:{ Exception -> 0x0124 }
            r5 = r11
            goto L_0x0075
        L_0x002f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0124 }
            java.lang.String r1 = "KeyManagerFactory not supported"
            r0.<init>(r1)     // Catch:{ Exception -> 0x0124 }
            throw r0     // Catch:{ Exception -> 0x0124 }
        L_0x0037:
            if (r25 != 0) goto L_0x0062
            char[] r12 = keyStorePassword(r24)     // Catch:{ Exception -> 0x0124 }
            java.security.KeyStore r5 = buildKeyStore(r5, r6, r12, r8)     // Catch:{ Exception -> 0x0124 }
            java.util.Enumeration r6 = r5.aliases()     // Catch:{ Exception -> 0x0124 }
            boolean r6 = r6.hasMoreElements()     // Catch:{ Exception -> 0x0124 }
            if (r6 == 0) goto L_0x0051
            io.netty.handler.ssl.OpenSslX509KeyManagerFactory r6 = new io.netty.handler.ssl.OpenSslX509KeyManagerFactory     // Catch:{ Exception -> 0x0124 }
            r6.<init>()     // Catch:{ Exception -> 0x0124 }
            goto L_0x005e
        L_0x0051:
            io.netty.handler.ssl.OpenSslCachingX509KeyManagerFactory r6 = new io.netty.handler.ssl.OpenSslCachingX509KeyManagerFactory     // Catch:{ Exception -> 0x0124 }
            java.lang.String r14 = javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm()     // Catch:{ Exception -> 0x0124 }
            javax.net.ssl.KeyManagerFactory r14 = javax.net.ssl.KeyManagerFactory.getInstance(r14)     // Catch:{ Exception -> 0x0124 }
            r6.<init>(r14)     // Catch:{ Exception -> 0x0124 }
        L_0x005e:
            r6.init(r5, r12)     // Catch:{ Exception -> 0x0124 }
            goto L_0x0064
        L_0x0062:
            r6 = r25
        L_0x0064:
            io.netty.handler.ssl.OpenSslKeyMaterialProvider r5 = providerFor(r6, r7)     // Catch:{ Exception -> 0x0124 }
            io.netty.handler.ssl.ReferenceCountedOpenSslServerContext$OpenSslServerCertificateCallback r6 = new io.netty.handler.ssl.ReferenceCountedOpenSslServerContext$OpenSslServerCertificateCallback     // Catch:{ Exception -> 0x011f }
            io.netty.handler.ssl.OpenSslKeyMaterialManager r7 = new io.netty.handler.ssl.OpenSslKeyMaterialManager     // Catch:{ Exception -> 0x011f }
            r7.<init>(r5)     // Catch:{ Exception -> 0x011f }
            r6.<init>(r2, r7)     // Catch:{ Exception -> 0x011f }
            io.netty.internal.tcnative.SSLContext.setCertificateCallback(r0, r6)     // Catch:{ Exception -> 0x011f }
        L_0x0075:
            if (r3 == 0) goto L_0x0082
            javax.net.ssl.TrustManagerFactory r3 = buildTrustManagerFactory((java.security.cert.X509Certificate[]) r3, (javax.net.ssl.TrustManagerFactory) r4, (java.lang.String) r8)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            goto L_0x0094
        L_0x007c:
            r0 = move-exception
            goto L_0x0113
        L_0x007f:
            r0 = move-exception
            goto L_0x011b
        L_0x0082:
            if (r4 != 0) goto L_0x0093
            java.lang.String r3 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            javax.net.ssl.TrustManagerFactory r3 = javax.net.ssl.TrustManagerFactory.getInstance(r3)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            r4 = r11
            java.security.KeyStore r4 = (java.security.KeyStore) r4     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            r3.init(r11)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            goto L_0x0094
        L_0x0093:
            r3 = r4
        L_0x0094:
            javax.net.ssl.TrustManager[] r3 = r3.getTrustManagers()     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            javax.net.ssl.X509TrustManager r3 = chooseTrustManager(r3)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            setVerifyCallback(r0, r2, r3)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            java.security.cert.X509Certificate[] r4 = r3.getAcceptedIssuers()     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            r6 = 0
            if (r4 == 0) goto L_0x00d4
            int r8 = r4.length     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            if (r8 <= 0) goto L_0x00d4
            io.netty.buffer.ByteBufAllocator r8 = io.netty.buffer.ByteBufAllocator.DEFAULT     // Catch:{ all -> 0x00cf }
            long r11 = toBIO((io.netty.buffer.ByteBufAllocator) r8, (java.security.cert.X509Certificate[]) r4)     // Catch:{ all -> 0x00cf }
            boolean r4 = io.netty.internal.tcnative.SSLContext.setCACertificateBio(r0, r11)     // Catch:{ all -> 0x00cc }
            if (r4 == 0) goto L_0x00ba
            freeBio(r11)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            goto L_0x00d4
        L_0x00ba:
            javax.net.ssl.SSLException r0 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x00cc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cc }
            r1.<init>(r13)     // Catch:{ all -> 0x00cc }
            r1.append(r3)     // Catch:{ all -> 0x00cc }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00cc }
            r0.<init>(r1)     // Catch:{ all -> 0x00cc }
            throw r0     // Catch:{ all -> 0x00cc }
        L_0x00cc:
            r0 = move-exception
            r6 = r11
            goto L_0x00d0
        L_0x00cf:
            r0 = move-exception
        L_0x00d0:
            freeBio(r6)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            throw r0     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
        L_0x00d4:
            int r3 = io.netty.util.internal.PlatformDependent.javaVersion()     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            r4 = 8
            if (r3 < r4) goto L_0x00e4
            io.netty.handler.ssl.ReferenceCountedOpenSslServerContext$OpenSslSniHostnameMatcher r3 = new io.netty.handler.ssl.ReferenceCountedOpenSslServerContext$OpenSslSniHostnameMatcher     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            r3.<init>(r2)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
            io.netty.internal.tcnative.SSLContext.setSniHostnameMatcher(r0, r3)     // Catch:{ SSLException -> 0x007f, Exception -> 0x007c }
        L_0x00e4:
            io.netty.handler.ssl.OpenSslServerSessionContext r0 = new io.netty.handler.ssl.OpenSslServerSessionContext     // Catch:{ all -> 0x011c }
            r1 = r16
            r0.<init>(r1, r5)     // Catch:{ all -> 0x011c }
            byte[] r1 = ID     // Catch:{ all -> 0x011c }
            r0.setSessionIdContext(r1)     // Catch:{ all -> 0x011c }
            boolean r1 = SERVER_ENABLE_SESSION_CACHE     // Catch:{ all -> 0x011c }
            r0.setSessionCacheEnabled(r1)     // Catch:{ all -> 0x011c }
            r1 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x0104
            long r3 = java.lang.Math.min(r9, r1)     // Catch:{ all -> 0x011c }
            int r4 = (int) r3     // Catch:{ all -> 0x011c }
            r0.setSessionCacheSize(r4)     // Catch:{ all -> 0x011c }
        L_0x0104:
            r3 = r29
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0112
            long r1 = java.lang.Math.min(r3, r1)     // Catch:{ all -> 0x011c }
            int r2 = (int) r1     // Catch:{ all -> 0x011c }
            r0.setSessionTimeout(r2)     // Catch:{ all -> 0x011c }
        L_0x0112:
            return r0
        L_0x0113:
            javax.net.ssl.SSLException r1 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x011c }
            java.lang.String r2 = "unable to setup trustmanager"
            r1.<init>(r2, r0)     // Catch:{ all -> 0x011c }
            throw r1     // Catch:{ all -> 0x011c }
        L_0x011b:
            throw r0     // Catch:{ all -> 0x011c }
        L_0x011c:
            r0 = move-exception
            r11 = r5
            goto L_0x012d
        L_0x011f:
            r0 = move-exception
            r11 = r5
            goto L_0x0125
        L_0x0122:
            r0 = move-exception
            goto L_0x012d
        L_0x0124:
            r0 = move-exception
        L_0x0125:
            javax.net.ssl.SSLException r1 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x0122 }
            java.lang.String r2 = "failed to set certificate and key"
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0122 }
            throw r1     // Catch:{ all -> 0x0122 }
        L_0x012d:
            if (r11 == 0) goto L_0x0132
            r11.destroy()
        L_0x0132:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslServerContext.newSessionContext(io.netty.handler.ssl.ReferenceCountedOpenSslContext, long, io.netty.handler.ssl.OpenSslEngineMap, java.security.cert.X509Certificate[], javax.net.ssl.TrustManagerFactory, java.security.cert.X509Certificate[], java.security.PrivateKey, java.lang.String, javax.net.ssl.KeyManagerFactory, java.lang.String, long, long):io.netty.handler.ssl.OpenSslServerSessionContext");
    }

    private static void setVerifyCallback(long j, OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
        if (useExtendedTrustManager(x509TrustManager)) {
            SSLContext.setCertVerifyCallback(j, new ExtendedTrustManagerVerifyCallback(openSslEngineMap, NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) x509TrustManager)));
        } else {
            SSLContext.setCertVerifyCallback(j, new TrustManagerVerifyCallback(openSslEngineMap, x509TrustManager));
        }
    }

    private static final class OpenSslServerCertificateCallback implements CertificateCallback {
        private final OpenSslEngineMap engineMap;
        private final OpenSslKeyMaterialManager keyManagerHolder;

        OpenSslServerCertificateCallback(OpenSslEngineMap openSslEngineMap, OpenSslKeyMaterialManager openSslKeyMaterialManager) {
            this.engineMap = openSslEngineMap;
            this.keyManagerHolder = openSslKeyMaterialManager;
        }

        public void handle(long j, byte[] bArr, byte[][] bArr2) throws Exception {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine != null) {
                try {
                    this.keyManagerHolder.setKeyMaterialServerSide(referenceCountedOpenSslEngine);
                } catch (Throwable th) {
                    referenceCountedOpenSslEngine.initHandshakeException(th);
                    if (th instanceof Exception) {
                        throw th;
                    }
                    throw new SSLException(th);
                }
            }
        }
    }

    private static final class TrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509TrustManager manager;

        TrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
            super(openSslEngineMap);
            this.manager = x509TrustManager;
        }

        /* access modifiers changed from: package-private */
        public void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str);
        }
    }

    private static final class ExtendedTrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509ExtendedTrustManager manager;

        ExtendedTrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509ExtendedTrustManager x509ExtendedTrustManager) {
            super(openSslEngineMap);
            this.manager = x509ExtendedTrustManager;
        }

        /* access modifiers changed from: package-private */
        public void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str, referenceCountedOpenSslEngine);
        }
    }

    private static final class OpenSslSniHostnameMatcher implements SniHostNameMatcher {
        private final OpenSslEngineMap engineMap;

        OpenSslSniHostnameMatcher(OpenSslEngineMap openSslEngineMap) {
            this.engineMap = openSslEngineMap;
        }

        public boolean match(long j, String str) {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine != null) {
                return referenceCountedOpenSslEngine.checkSniHostnameMatch(str.getBytes(CharsetUtil.UTF_8));
            }
            ReferenceCountedOpenSslServerContext.logger.warn("No ReferenceCountedOpenSslEngine found for SSL pointer: {}", (Object) Long.valueOf(j));
            return false;
        }
    }
}
