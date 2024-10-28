package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.netty.internal.tcnative.CertificateCallback;
import io.netty.internal.tcnative.SSL;
import io.netty.internal.tcnative.SSLContext;
import io.netty.util.internal.EmptyArrays;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public final class ReferenceCountedOpenSslClientContext extends ReferenceCountedOpenSslContext {
    /* access modifiers changed from: private */
    public static final Set<String> SUPPORTED_KEY_TYPES = Collections.unmodifiableSet(new LinkedHashSet(Arrays.asList(new String[]{"RSA", "DH_RSA", "EC", "EC_RSA", "EC_EC"})));
    private final OpenSslSessionContext sessionContext;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReferenceCountedOpenSslClientContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, String[] strArr, long j, long j2, boolean z, String str2, Map.Entry<SslContextOption<?>, Object>... entryArr) throws SSLException {
        super(iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), 0, x509CertificateArr2, ClientAuth.NONE, strArr, false, z, true, entryArr);
        try {
            try {
            } catch (Throwable th) {
                th = th;
                release();
                throw th;
            }
            try {
                this.sessionContext = newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, str2, j, j2);
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

    public OpenSslSessionContext sessionContext() {
        return this.sessionContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static io.netty.handler.ssl.OpenSslSessionContext newSessionContext(io.netty.handler.ssl.ReferenceCountedOpenSslContext r16, long r17, io.netty.handler.ssl.OpenSslEngineMap r19, java.security.cert.X509Certificate[] r20, javax.net.ssl.TrustManagerFactory r21, java.security.cert.X509Certificate[] r22, java.security.PrivateKey r23, java.lang.String r24, javax.net.ssl.KeyManagerFactory r25, java.lang.String r26, long r27, long r29) throws javax.net.ssl.SSLException {
        /*
            r0 = r17
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r25
            r9 = r26
            r10 = r27
            r12 = r29
            if (r6 != 0) goto L_0x001a
            if (r5 != 0) goto L_0x001f
        L_0x001a:
            if (r6 == 0) goto L_0x0027
            if (r5 == 0) goto L_0x001f
            goto L_0x0027
        L_0x001f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Either both keyCertChain and key needs to be null or none of them"
            r0.<init>(r1)
            throw r0
        L_0x0027:
            r14 = 0
            boolean r15 = io.netty.handler.ssl.OpenSsl.useKeyManagerFactory()     // Catch:{ Exception -> 0x00ff }
            if (r15 != 0) goto L_0x003f
            if (r8 != 0) goto L_0x0037
            if (r5 == 0) goto L_0x0035
            setKeyMaterial(r0, r5, r6, r7)     // Catch:{ Exception -> 0x00ff }
        L_0x0035:
            r5 = r14
            goto L_0x0090
        L_0x0037:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x00ff }
            java.lang.String r1 = "KeyManagerFactory not supported"
            r0.<init>(r1)     // Catch:{ Exception -> 0x00ff }
            throw r0     // Catch:{ Exception -> 0x00ff }
        L_0x003f:
            if (r8 != 0) goto L_0x0070
            if (r5 == 0) goto L_0x0070
            char[] r8 = keyStorePassword(r24)     // Catch:{ Exception -> 0x00ff }
            java.security.KeyStore r5 = buildKeyStore(r5, r6, r8, r9)     // Catch:{ Exception -> 0x00ff }
            java.util.Enumeration r6 = r5.aliases()     // Catch:{ Exception -> 0x00ff }
            boolean r6 = r6.hasMoreElements()     // Catch:{ Exception -> 0x00ff }
            if (r6 == 0) goto L_0x005b
            io.netty.handler.ssl.OpenSslX509KeyManagerFactory r6 = new io.netty.handler.ssl.OpenSslX509KeyManagerFactory     // Catch:{ Exception -> 0x00ff }
            r6.<init>()     // Catch:{ Exception -> 0x00ff }
            goto L_0x0068
        L_0x005b:
            io.netty.handler.ssl.OpenSslCachingX509KeyManagerFactory r6 = new io.netty.handler.ssl.OpenSslCachingX509KeyManagerFactory     // Catch:{ Exception -> 0x00ff }
            java.lang.String r15 = javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm()     // Catch:{ Exception -> 0x00ff }
            javax.net.ssl.KeyManagerFactory r15 = javax.net.ssl.KeyManagerFactory.getInstance(r15)     // Catch:{ Exception -> 0x00ff }
            r6.<init>(r15)     // Catch:{ Exception -> 0x00ff }
        L_0x0068:
            r6.init(r5, r8)     // Catch:{ Exception -> 0x00ff }
            io.netty.handler.ssl.OpenSslKeyMaterialProvider r5 = providerFor(r6, r7)     // Catch:{ Exception -> 0x00ff }
            goto L_0x0078
        L_0x0070:
            if (r8 == 0) goto L_0x0077
            io.netty.handler.ssl.OpenSslKeyMaterialProvider r5 = providerFor(r8, r7)     // Catch:{ Exception -> 0x00ff }
            goto L_0x0078
        L_0x0077:
            r5 = r14
        L_0x0078:
            if (r5 == 0) goto L_0x0090
            io.netty.handler.ssl.OpenSslKeyMaterialManager r6 = new io.netty.handler.ssl.OpenSslKeyMaterialManager     // Catch:{ Exception -> 0x008c }
            r6.<init>(r5)     // Catch:{ Exception -> 0x008c }
            io.netty.handler.ssl.ReferenceCountedOpenSslClientContext$OpenSslClientCertificateCallback r7 = new io.netty.handler.ssl.ReferenceCountedOpenSslClientContext$OpenSslClientCertificateCallback     // Catch:{ Exception -> 0x008c }
            r7.<init>(r2, r6)     // Catch:{ Exception -> 0x008c }
            io.netty.internal.tcnative.SSLContext.setCertificateCallback(r0, r7)     // Catch:{ Exception -> 0x008c }
            goto L_0x0090
        L_0x0088:
            r0 = move-exception
            r14 = r5
            goto L_0x0108
        L_0x008c:
            r0 = move-exception
            r14 = r5
            goto L_0x0100
        L_0x0090:
            r6 = 1
            r7 = 10
            io.netty.internal.tcnative.SSLContext.setVerify(r0, r6, r7)     // Catch:{ all -> 0x0088 }
            if (r3 == 0) goto L_0x009f
            javax.net.ssl.TrustManagerFactory r3 = buildTrustManagerFactory((java.security.cert.X509Certificate[]) r3, (javax.net.ssl.TrustManagerFactory) r4, (java.lang.String) r9)     // Catch:{ Exception -> 0x009d }
            goto L_0x00b1
        L_0x009d:
            r0 = move-exception
            goto L_0x00f0
        L_0x009f:
            if (r4 != 0) goto L_0x00b0
            java.lang.String r3 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()     // Catch:{ Exception -> 0x009d }
            javax.net.ssl.TrustManagerFactory r3 = javax.net.ssl.TrustManagerFactory.getInstance(r3)     // Catch:{ Exception -> 0x009d }
            r4 = r14
            java.security.KeyStore r4 = (java.security.KeyStore) r4     // Catch:{ Exception -> 0x009d }
            r3.init(r14)     // Catch:{ Exception -> 0x009d }
            goto L_0x00b1
        L_0x00b0:
            r3 = r4
        L_0x00b1:
            javax.net.ssl.TrustManager[] r3 = r3.getTrustManagers()     // Catch:{ Exception -> 0x009d }
            javax.net.ssl.X509TrustManager r3 = chooseTrustManager(r3)     // Catch:{ Exception -> 0x009d }
            setVerifyCallback(r0, r2, r3)     // Catch:{ Exception -> 0x009d }
            io.netty.handler.ssl.ReferenceCountedOpenSslClientContext$OpenSslClientSessionContext r0 = new io.netty.handler.ssl.ReferenceCountedOpenSslClientContext$OpenSslClientSessionContext     // Catch:{ all -> 0x0088 }
            r1 = r16
            r0.<init>(r1, r5)     // Catch:{ all -> 0x0088 }
            boolean r1 = CLIENT_ENABLE_SESSION_CACHE     // Catch:{ all -> 0x0088 }
            r0.setSessionCacheEnabled(r1)     // Catch:{ all -> 0x0088 }
            r1 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r3 = 0
            int r6 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x00d9
            long r6 = java.lang.Math.min(r10, r1)     // Catch:{ all -> 0x0088 }
            int r7 = (int) r6     // Catch:{ all -> 0x0088 }
            r0.setSessionCacheSize(r7)     // Catch:{ all -> 0x0088 }
        L_0x00d9:
            int r6 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x00e5
            long r1 = java.lang.Math.min(r12, r1)     // Catch:{ all -> 0x0088 }
            int r2 = (int) r1     // Catch:{ all -> 0x0088 }
            r0.setSessionTimeout(r2)     // Catch:{ all -> 0x0088 }
        L_0x00e5:
            boolean r1 = CLIENT_ENABLE_SESSION_TICKET     // Catch:{ all -> 0x0088 }
            if (r1 == 0) goto L_0x00ef
            r1 = 0
            io.netty.handler.ssl.OpenSslSessionTicketKey[] r1 = new io.netty.handler.ssl.OpenSslSessionTicketKey[r1]     // Catch:{ all -> 0x0088 }
            r0.setTicketKeys((io.netty.handler.ssl.OpenSslSessionTicketKey[]) r1)     // Catch:{ all -> 0x0088 }
        L_0x00ef:
            return r0
        L_0x00f0:
            if (r5 == 0) goto L_0x00f5
            r5.destroy()     // Catch:{ all -> 0x0088 }
        L_0x00f5:
            javax.net.ssl.SSLException r1 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x0088 }
            java.lang.String r2 = "unable to setup trustmanager"
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0088 }
            throw r1     // Catch:{ all -> 0x0088 }
        L_0x00fd:
            r0 = move-exception
            goto L_0x0108
        L_0x00ff:
            r0 = move-exception
        L_0x0100:
            javax.net.ssl.SSLException r1 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x00fd }
            java.lang.String r2 = "failed to set certificate and key"
            r1.<init>(r2, r0)     // Catch:{ all -> 0x00fd }
            throw r1     // Catch:{ all -> 0x00fd }
        L_0x0108:
            if (r14 == 0) goto L_0x010d
            r14.destroy()
        L_0x010d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslClientContext.newSessionContext(io.netty.handler.ssl.ReferenceCountedOpenSslContext, long, io.netty.handler.ssl.OpenSslEngineMap, java.security.cert.X509Certificate[], javax.net.ssl.TrustManagerFactory, java.security.cert.X509Certificate[], java.security.PrivateKey, java.lang.String, javax.net.ssl.KeyManagerFactory, java.lang.String, long, long):io.netty.handler.ssl.OpenSslSessionContext");
    }

    private static void setVerifyCallback(long j, OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
        if (useExtendedTrustManager(x509TrustManager)) {
            SSLContext.setCertVerifyCallback(j, new ExtendedTrustManagerVerifyCallback(openSslEngineMap, NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) x509TrustManager)));
        } else {
            SSLContext.setCertVerifyCallback(j, new TrustManagerVerifyCallback(openSslEngineMap, x509TrustManager));
        }
    }

    static final class OpenSslClientSessionContext extends OpenSslSessionContext {
        OpenSslClientSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, OpenSslKeyMaterialProvider openSslKeyMaterialProvider) {
            super(referenceCountedOpenSslContext, openSslKeyMaterialProvider, SSL.SSL_SESS_CACHE_CLIENT, new OpenSslClientSessionCache(referenceCountedOpenSslContext.engineMap));
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
            this.manager.checkServerTrusted(x509CertificateArr, str);
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
            this.manager.checkServerTrusted(x509CertificateArr, str, referenceCountedOpenSslEngine);
        }
    }

    private static final class OpenSslClientCertificateCallback implements CertificateCallback {
        private final OpenSslEngineMap engineMap;
        private final OpenSslKeyMaterialManager keyManagerHolder;

        OpenSslClientCertificateCallback(OpenSslEngineMap openSslEngineMap, OpenSslKeyMaterialManager openSslKeyMaterialManager) {
            this.engineMap = openSslEngineMap;
            this.keyManagerHolder = openSslKeyMaterialManager;
        }

        public void handle(long j, byte[] bArr, byte[][] bArr2) throws Exception {
            X500Principal[] x500PrincipalArr;
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine != null) {
                try {
                    String[] strArr = (String[]) supportedClientKeyTypes(bArr).toArray(EmptyArrays.EMPTY_STRINGS);
                    if (bArr2 == null) {
                        x500PrincipalArr = null;
                    } else {
                        x500PrincipalArr = new X500Principal[bArr2.length];
                        for (int i = 0; i < bArr2.length; i++) {
                            x500PrincipalArr[i] = new X500Principal(bArr2[i]);
                        }
                    }
                    this.keyManagerHolder.setKeyMaterialClientSide(referenceCountedOpenSslEngine, strArr, x500PrincipalArr);
                } catch (Throwable th) {
                    referenceCountedOpenSslEngine.initHandshakeException(th);
                    if (th instanceof Exception) {
                        throw th;
                    }
                    throw new SSLException(th);
                }
            }
        }

        private static Set<String> supportedClientKeyTypes(byte[] bArr) {
            if (bArr == null) {
                return ReferenceCountedOpenSslClientContext.SUPPORTED_KEY_TYPES;
            }
            HashSet hashSet = new HashSet(bArr.length);
            for (byte clientKeyType : bArr) {
                String clientKeyType2 = clientKeyType(clientKeyType);
                if (clientKeyType2 != null) {
                    hashSet.add(clientKeyType2);
                }
            }
            return hashSet;
        }

        private static String clientKeyType(byte b) {
            if (b == 1) {
                return "RSA";
            }
            if (b == 3) {
                return "DH_RSA";
            }
            switch (b) {
                case 64:
                    return "EC";
                case 65:
                    return "EC_RSA";
                case 66:
                    return "EC_EC";
                default:
                    return null;
            }
        }
    }
}
