package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.util.internal.PlatformDependent;
import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

@Deprecated
public final class JdkSslServerContext extends JdkSslContext {
    @Deprecated
    public JdkSslServerContext(File file, File file2) throws SSLException {
        this((Provider) null, file, file2, (String) null, (Iterable<String>) null, IdentityCipherSuiteFilter.INSTANCE, JdkDefaultApplicationProtocolNegotiator.INSTANCE, 0, 0, (String) null);
    }

    @Deprecated
    public JdkSslServerContext(File file, File file2, String str) throws SSLException {
        this(file, file2, str, (Iterable<String>) null, (CipherSuiteFilter) IdentityCipherSuiteFilter.INSTANCE, (JdkApplicationProtocolNegotiator) JdkDefaultApplicationProtocolNegotiator.INSTANCE, 0, 0);
    }

    @Deprecated
    public JdkSslServerContext(File file, File file2, String str, Iterable<String> iterable, Iterable<String> iterable2, long j, long j2) throws SSLException {
        this((Provider) null, file, file2, str, iterable, IdentityCipherSuiteFilter.INSTANCE, toNegotiator(toApplicationProtocolConfig(iterable2), true), j, j2, KeyStore.getDefaultType());
    }

    @Deprecated
    public JdkSslServerContext(File file, File file2, String str, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        this((Provider) null, file, file2, str, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig, true), j, j2, KeyStore.getDefaultType());
    }

    @Deprecated
    public JdkSslServerContext(File file, File file2, String str, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, long j, long j2) throws SSLException {
        this((Provider) null, file, file2, str, iterable, cipherSuiteFilter, jdkApplicationProtocolNegotiator, j, j2, KeyStore.getDefaultType());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    JdkSslServerContext(java.security.Provider r17, java.io.File r18, java.io.File r19, java.lang.String r20, java.lang.Iterable<java.lang.String> r21, io.netty.handler.ssl.CipherSuiteFilter r22, io.netty.handler.ssl.JdkApplicationProtocolNegotiator r23, long r24, long r26, java.lang.String r28) throws javax.net.ssl.SSLException {
        /*
            r16 = this;
            java.security.cert.X509Certificate[] r3 = toX509CertificatesInternal(r18)
            java.security.PrivateKey r4 = toPrivateKeyInternal(r19, r20)
            r6 = 0
            r1 = 0
            r2 = 0
            r0 = r17
            r5 = r20
            r7 = r24
            r9 = r26
            r11 = r28
            javax.net.ssl.SSLContext r8 = newSSLContext(r0, r1, r2, r3, r4, r5, r6, r7, r9, r11)
            io.netty.handler.ssl.ClientAuth r13 = io.netty.handler.ssl.ClientAuth.NONE
            r14 = 0
            r15 = 0
            r9 = 0
            r7 = r16
            r10 = r21
            r11 = r22
            r12 = r23
            r7.<init>((javax.net.ssl.SSLContext) r8, (boolean) r9, (java.lang.Iterable<java.lang.String>) r10, (io.netty.handler.ssl.CipherSuiteFilter) r11, (io.netty.handler.ssl.JdkApplicationProtocolNegotiator) r12, (io.netty.handler.ssl.ClientAuth) r13, (java.lang.String[]) r14, (boolean) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.JdkSslServerContext.<init>(java.security.Provider, java.io.File, java.io.File, java.lang.String, java.lang.Iterable, io.netty.handler.ssl.CipherSuiteFilter, io.netty.handler.ssl.JdkApplicationProtocolNegotiator, long, long, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JdkSslServerContext(java.io.File r22, javax.net.ssl.TrustManagerFactory r23, java.io.File r24, java.io.File r25, java.lang.String r26, javax.net.ssl.KeyManagerFactory r27, java.lang.Iterable<java.lang.String> r28, io.netty.handler.ssl.CipherSuiteFilter r29, io.netty.handler.ssl.ApplicationProtocolConfig r30, long r31, long r33) throws javax.net.ssl.SSLException {
        /*
            r21 = this;
            java.security.cert.X509Certificate[] r1 = toX509CertificatesInternal(r22)
            java.security.cert.X509Certificate[] r3 = toX509CertificatesInternal(r24)
            java.security.PrivateKey r4 = toPrivateKeyInternal(r25, r26)
            r11 = 0
            r0 = 0
            r2 = r23
            r5 = r26
            r6 = r27
            r7 = r31
            r9 = r33
            javax.net.ssl.SSLContext r13 = newSSLContext(r0, r1, r2, r3, r4, r5, r6, r7, r9, r11)
            io.netty.handler.ssl.ClientAuth r18 = io.netty.handler.ssl.ClientAuth.NONE
            r19 = 0
            r20 = 0
            r14 = 0
            r12 = r21
            r15 = r28
            r16 = r29
            r17 = r30
            r12.<init>((javax.net.ssl.SSLContext) r13, (boolean) r14, (java.lang.Iterable<java.lang.String>) r15, (io.netty.handler.ssl.CipherSuiteFilter) r16, (io.netty.handler.ssl.ApplicationProtocolConfig) r17, (io.netty.handler.ssl.ClientAuth) r18, (java.lang.String[]) r19, (boolean) r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.JdkSslServerContext.<init>(java.io.File, javax.net.ssl.TrustManagerFactory, java.io.File, java.io.File, java.lang.String, javax.net.ssl.KeyManagerFactory, java.lang.Iterable, io.netty.handler.ssl.CipherSuiteFilter, io.netty.handler.ssl.ApplicationProtocolConfig, long, long):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JdkSslServerContext(java.io.File r22, javax.net.ssl.TrustManagerFactory r23, java.io.File r24, java.io.File r25, java.lang.String r26, javax.net.ssl.KeyManagerFactory r27, java.lang.Iterable<java.lang.String> r28, io.netty.handler.ssl.CipherSuiteFilter r29, io.netty.handler.ssl.JdkApplicationProtocolNegotiator r30, long r31, long r33) throws javax.net.ssl.SSLException {
        /*
            r21 = this;
            java.security.cert.X509Certificate[] r1 = toX509CertificatesInternal(r22)
            java.security.cert.X509Certificate[] r3 = toX509CertificatesInternal(r24)
            java.security.PrivateKey r4 = toPrivateKeyInternal(r25, r26)
            java.lang.String r11 = java.security.KeyStore.getDefaultType()
            r0 = 0
            r2 = r23
            r5 = r26
            r6 = r27
            r7 = r31
            r9 = r33
            javax.net.ssl.SSLContext r13 = newSSLContext(r0, r1, r2, r3, r4, r5, r6, r7, r9, r11)
            io.netty.handler.ssl.ClientAuth r18 = io.netty.handler.ssl.ClientAuth.NONE
            r19 = 0
            r20 = 0
            r14 = 0
            r12 = r21
            r15 = r28
            r16 = r29
            r17 = r30
            r12.<init>((javax.net.ssl.SSLContext) r13, (boolean) r14, (java.lang.Iterable<java.lang.String>) r15, (io.netty.handler.ssl.CipherSuiteFilter) r16, (io.netty.handler.ssl.JdkApplicationProtocolNegotiator) r17, (io.netty.handler.ssl.ClientAuth) r18, (java.lang.String[]) r19, (boolean) r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.JdkSslServerContext.<init>(java.io.File, javax.net.ssl.TrustManagerFactory, java.io.File, java.io.File, java.lang.String, javax.net.ssl.KeyManagerFactory, java.lang.Iterable, io.netty.handler.ssl.CipherSuiteFilter, io.netty.handler.ssl.JdkApplicationProtocolNegotiator, long, long):void");
    }

    JdkSslServerContext(Provider provider, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, String str2) throws SSLException {
        super(newSSLContext(provider, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, j, j2, str2), false, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig, true), clientAuth, strArr, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035 A[Catch:{ Exception -> 0x001f }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004a A[SYNTHETIC, Splitter:B:21:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f A[Catch:{ Exception -> 0x001f }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006f A[Catch:{ Exception -> 0x001f }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007b A[Catch:{ Exception -> 0x001f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static javax.net.ssl.SSLContext newSSLContext(java.security.Provider r13, java.security.cert.X509Certificate[] r14, javax.net.ssl.TrustManagerFactory r15, java.security.cert.X509Certificate[] r16, java.security.PrivateKey r17, java.lang.String r18, javax.net.ssl.KeyManagerFactory r19, long r20, long r22, java.lang.String r24) throws javax.net.ssl.SSLException {
        /*
            r0 = r13
            r1 = r14
            r2 = r15
            r7 = r20
            r9 = r22
            if (r17 != 0) goto L_0x0014
            if (r19 == 0) goto L_0x000c
            goto L_0x0014
        L_0x000c:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "key, keyManagerFactory"
            r0.<init>(r1)
            throw r0
        L_0x0014:
            r11 = 0
            if (r1 == 0) goto L_0x0021
            r3 = r24
            javax.net.ssl.TrustManagerFactory r1 = buildTrustManagerFactory((java.security.cert.X509Certificate[]) r14, (javax.net.ssl.TrustManagerFactory) r15, (java.lang.String) r3)     // Catch:{ Exception -> 0x001f }
        L_0x001d:
            r12 = r1
            goto L_0x0033
        L_0x001f:
            r0 = move-exception
            goto L_0x0084
        L_0x0021:
            if (r2 != 0) goto L_0x0032
            java.lang.String r1 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()     // Catch:{ Exception -> 0x001f }
            javax.net.ssl.TrustManagerFactory r1 = javax.net.ssl.TrustManagerFactory.getInstance(r1)     // Catch:{ Exception -> 0x001f }
            r2 = r11
            java.security.KeyStore r2 = (java.security.KeyStore) r2     // Catch:{ Exception -> 0x001f }
            r1.init(r11)     // Catch:{ Exception -> 0x001f }
            goto L_0x001d
        L_0x0032:
            r12 = r2
        L_0x0033:
            if (r17 == 0) goto L_0x0044
            r2 = 0
            r6 = 0
            r1 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            javax.net.ssl.KeyManagerFactory r1 = buildKeyManagerFactory(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x001f }
            goto L_0x0046
        L_0x0044:
            r1 = r19
        L_0x0046:
            java.lang.String r2 = "TLS"
            if (r0 != 0) goto L_0x004f
            javax.net.ssl.SSLContext r0 = javax.net.ssl.SSLContext.getInstance(r2)     // Catch:{ Exception -> 0x001f }
            goto L_0x0053
        L_0x004f:
            javax.net.ssl.SSLContext r0 = javax.net.ssl.SSLContext.getInstance(r2, r13)     // Catch:{ Exception -> 0x001f }
        L_0x0053:
            javax.net.ssl.KeyManager[] r1 = r1.getKeyManagers()     // Catch:{ Exception -> 0x001f }
            javax.net.ssl.TrustManager[] r2 = r12.getTrustManagers()     // Catch:{ Exception -> 0x001f }
            javax.net.ssl.TrustManager[] r2 = wrapTrustManagerIfNeeded(r2)     // Catch:{ Exception -> 0x001f }
            r0.init(r1, r2, r11)     // Catch:{ Exception -> 0x001f }
            javax.net.ssl.SSLSessionContext r1 = r0.getServerSessionContext()     // Catch:{ Exception -> 0x001f }
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r4 = 0
            int r6 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0077
            long r6 = java.lang.Math.min(r7, r2)     // Catch:{ Exception -> 0x001f }
            int r7 = (int) r6     // Catch:{ Exception -> 0x001f }
            r1.setSessionCacheSize(r7)     // Catch:{ Exception -> 0x001f }
        L_0x0077:
            int r6 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0083
            long r2 = java.lang.Math.min(r9, r2)     // Catch:{ Exception -> 0x001f }
            int r3 = (int) r2     // Catch:{ Exception -> 0x001f }
            r1.setSessionTimeout(r3)     // Catch:{ Exception -> 0x001f }
        L_0x0083:
            return r0
        L_0x0084:
            boolean r1 = r0 instanceof javax.net.ssl.SSLException
            if (r1 == 0) goto L_0x008b
            javax.net.ssl.SSLException r0 = (javax.net.ssl.SSLException) r0
            throw r0
        L_0x008b:
            javax.net.ssl.SSLException r1 = new javax.net.ssl.SSLException
            java.lang.String r2 = "failed to initialize the server-side SSL context"
            r1.<init>(r2, r0)
            goto L_0x0094
        L_0x0093:
            throw r1
        L_0x0094:
            goto L_0x0093
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.JdkSslServerContext.newSSLContext(java.security.Provider, java.security.cert.X509Certificate[], javax.net.ssl.TrustManagerFactory, java.security.cert.X509Certificate[], java.security.PrivateKey, java.lang.String, javax.net.ssl.KeyManagerFactory, long, long, java.lang.String):javax.net.ssl.SSLContext");
    }

    private static TrustManager[] wrapTrustManagerIfNeeded(TrustManager[] trustManagerArr) {
        if (PlatformDependent.javaVersion() >= 7) {
            for (int i = 0; i < trustManagerArr.length; i++) {
                TrustManager trustManager = trustManagerArr[i];
                if (NioPathKt$$ExternalSyntheticApiModelOutline0.m$2((Object) trustManager)) {
                    trustManagerArr[i] = new EnhancingX509ExtendedTrustManager(NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) trustManager));
                }
            }
        }
        return trustManagerArr;
    }
}
