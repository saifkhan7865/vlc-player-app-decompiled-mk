package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.AccessController;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

final class OpenSslX509TrustManagerWrapper {
    private static final InternalLogger LOGGER;
    private static final TrustManagerWrapper WRAPPER;

    private interface TrustManagerWrapper {
        X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager);
    }

    static {
        final SSLContext sSLContext;
        InternalLogger instance = InternalLoggerFactory.getInstance((Class<?>) OpenSslX509TrustManagerWrapper.class);
        LOGGER = instance;
        TrustManagerWrapper r1 = new TrustManagerWrapper() {
            public X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager) {
                return x509TrustManager;
            }
        };
        Throwable th = null;
        if (PlatformDependent.getUnsafeUnavailabilityCause() == null) {
            try {
                sSLContext = newSSLContext();
                sSLContext.init((KeyManager[]) null, new TrustManager[]{new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        throw new CertificateException();
                    }

                    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        throw new CertificateException();
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return EmptyArrays.EMPTY_X509_CERTIFICATES;
                    }
                }}, (SecureRandom) null);
            } catch (Throwable th2) {
                th = th2;
                sSLContext = null;
            }
            if (th != null) {
                LOGGER.debug("Unable to access wrapped TrustManager", th);
            } else {
                Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
                        r0 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
                        return r0;
                     */
                    /* JADX WARNING: Failed to process nested try/catch */
                    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
                    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038 A[Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }, LOOP:0: B:5:0x0018->B:14:0x0038, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f A[ExcHandler: SecurityException (r0v2 'e' java.lang.SecurityException A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
                    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039 A[EDGE_INSN: B:21:0x0039->B:15:0x0039 ?: BREAK  , SYNTHETIC] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public java.lang.Object run() {
                        /*
                            r7 = this;
                            java.lang.Class<javax.net.ssl.SSLContext> r0 = javax.net.ssl.SSLContext.class
                            java.lang.String r1 = "contextSpi"
                            java.lang.reflect.Field r0 = r0.getDeclaredField(r1)     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                            long r0 = io.netty.util.internal.PlatformDependent.objectFieldOffset(r0)     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                            javax.net.ssl.SSLContext r2 = r0     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                            java.lang.Object r2 = io.netty.util.internal.PlatformDependent.getObject(r2, r0)     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                            if (r2 == 0) goto L_0x0039
                            java.lang.Class r3 = r2.getClass()     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                        L_0x0018:
                            java.lang.String r4 = "trustManager"
                            java.lang.reflect.Field r4 = r3.getDeclaredField(r4)     // Catch:{ NoSuchFieldException -> 0x0032, SecurityException -> 0x003f }
                            long r4 = io.netty.util.internal.PlatformDependent.objectFieldOffset(r4)     // Catch:{ NoSuchFieldException -> 0x0032, SecurityException -> 0x003f }
                            java.lang.Object r6 = io.netty.util.internal.PlatformDependent.getObject(r2, r4)     // Catch:{ NoSuchFieldException -> 0x0032, SecurityException -> 0x003f }
                            boolean r6 = io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0.m$2((java.lang.Object) r6)     // Catch:{ NoSuchFieldException -> 0x0032, SecurityException -> 0x003f }
                            if (r6 == 0) goto L_0x0032
                            io.netty.handler.ssl.OpenSslX509TrustManagerWrapper$UnsafeTrustManagerWrapper r6 = new io.netty.handler.ssl.OpenSslX509TrustManagerWrapper$UnsafeTrustManagerWrapper     // Catch:{ NoSuchFieldException -> 0x0032, SecurityException -> 0x003f }
                            r6.<init>(r0, r4)     // Catch:{ NoSuchFieldException -> 0x0032, SecurityException -> 0x003f }
                            return r6
                        L_0x0032:
                            java.lang.Class r3 = r3.getSuperclass()     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                            if (r3 == 0) goto L_0x0039
                            goto L_0x0018
                        L_0x0039:
                            java.lang.NoSuchFieldException r0 = new java.lang.NoSuchFieldException     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                            r0.<init>()     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                            throw r0     // Catch:{ NoSuchFieldException -> 0x0041, SecurityException -> 0x003f }
                        L_0x003f:
                            r0 = move-exception
                            return r0
                        L_0x0041:
                            r0 = move-exception
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSslX509TrustManagerWrapper.AnonymousClass3.run():java.lang.Object");
                    }
                });
                if (doPrivileged instanceof Throwable) {
                    LOGGER.debug("Unable to access wrapped TrustManager", (Throwable) doPrivileged);
                } else {
                    r1 = (TrustManagerWrapper) doPrivileged;
                }
            }
        } else {
            instance.debug("Unable to access wrapped TrustManager", (Throwable) null);
        }
        WRAPPER = r1;
    }

    private OpenSslX509TrustManagerWrapper() {
    }

    static X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager) {
        return WRAPPER.wrapIfNeeded(x509TrustManager);
    }

    /* access modifiers changed from: private */
    public static SSLContext newSSLContext() throws NoSuchAlgorithmException, NoSuchProviderException {
        return SSLContext.getInstance("TLS", "SunJSSE");
    }

    private static final class UnsafeTrustManagerWrapper implements TrustManagerWrapper {
        private final long spiOffset;
        private final long tmOffset;

        UnsafeTrustManagerWrapper(long j, long j2) {
            this.spiOffset = j;
            this.tmOffset = j2;
        }

        public X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager) {
            if (!NioPathKt$$ExternalSyntheticApiModelOutline0.m$2((Object) x509TrustManager)) {
                try {
                    SSLContext access$000 = OpenSslX509TrustManagerWrapper.newSSLContext();
                    access$000.init((KeyManager[]) null, new TrustManager[]{x509TrustManager}, (SecureRandom) null);
                    Object object = PlatformDependent.getObject(access$000, this.spiOffset);
                    if (object != null) {
                        Object object2 = PlatformDependent.getObject(object, this.tmOffset);
                        if (NioPathKt$$ExternalSyntheticApiModelOutline0.m$2(object2)) {
                            return (X509TrustManager) object2;
                        }
                    }
                } catch (NoSuchAlgorithmException e) {
                    PlatformDependent.throwException(e);
                } catch (KeyManagementException e2) {
                    PlatformDependent.throwException(e2);
                } catch (NoSuchProviderException e3) {
                    PlatformDependent.throwException(e3);
                }
            }
            return x509TrustManager;
        }
    }
}
