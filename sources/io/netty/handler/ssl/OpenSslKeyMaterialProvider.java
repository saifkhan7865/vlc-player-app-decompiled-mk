package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.internal.tcnative.SSL;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509KeyManager;

class OpenSslKeyMaterialProvider {
    private final X509KeyManager keyManager;
    private final String password;

    /* access modifiers changed from: package-private */
    public void destroy() {
    }

    OpenSslKeyMaterialProvider(X509KeyManager x509KeyManager, String str) {
        this.keyManager = x509KeyManager;
        this.password = str;
    }

    static void validateKeyMaterialSupported(X509Certificate[] x509CertificateArr, PrivateKey privateKey, String str) throws SSLException {
        validateSupported(x509CertificateArr);
        validateSupported(privateKey, str);
    }

    private static void validateSupported(PrivateKey privateKey, String str) throws SSLException {
        if (privateKey != null) {
            long j = 0;
            try {
                long bio = ReferenceCountedOpenSslContext.toBIO((ByteBufAllocator) UnpooledByteBufAllocator.DEFAULT, privateKey);
                try {
                    long parsePrivateKey = SSL.parsePrivateKey(bio, str);
                    SSL.freeBIO(bio);
                    if (parsePrivateKey != 0) {
                        SSL.freePrivateKey(parsePrivateKey);
                    }
                } catch (Exception e) {
                    e = e;
                    j = bio;
                    try {
                        throw new SSLException("PrivateKey type not supported " + privateKey.getFormat(), e);
                    } catch (Throwable th) {
                        th = th;
                        SSL.freeBIO(j);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    j = bio;
                    SSL.freeBIO(j);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                throw new SSLException("PrivateKey type not supported " + privateKey.getFormat(), e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void validateSupported(java.security.cert.X509Certificate[] r7) throws javax.net.ssl.SSLException {
        /*
            if (r7 == 0) goto L_0x0048
            int r0 = r7.length
            if (r0 != 0) goto L_0x0006
            goto L_0x0048
        L_0x0006:
            r0 = 0
            r2 = 0
            io.netty.buffer.UnpooledByteBufAllocator r3 = io.netty.buffer.UnpooledByteBufAllocator.DEFAULT     // Catch:{ Exception -> 0x0036 }
            r4 = 1
            io.netty.handler.ssl.PemEncoded r2 = io.netty.handler.ssl.PemX509Certificate.toPEM(r3, r4, r7)     // Catch:{ Exception -> 0x0036 }
            io.netty.buffer.UnpooledByteBufAllocator r7 = io.netty.buffer.UnpooledByteBufAllocator.DEFAULT     // Catch:{ Exception -> 0x0036 }
            io.netty.handler.ssl.PemEncoded r3 = r2.retain()     // Catch:{ Exception -> 0x0036 }
            long r3 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.toBIO((io.netty.buffer.ByteBufAllocator) r7, (io.netty.handler.ssl.PemEncoded) r3)     // Catch:{ Exception -> 0x0036 }
            long r5 = io.netty.internal.tcnative.SSL.parseX509Chain(r3)     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            io.netty.internal.tcnative.SSL.freeBIO(r3)
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 == 0) goto L_0x0028
            io.netty.internal.tcnative.SSL.freeX509Chain(r5)
        L_0x0028:
            if (r2 == 0) goto L_0x002d
            r2.release()
        L_0x002d:
            return
        L_0x002e:
            r7 = move-exception
            r0 = r3
            goto L_0x003f
        L_0x0031:
            r7 = move-exception
            r0 = r3
            goto L_0x0037
        L_0x0034:
            r7 = move-exception
            goto L_0x003f
        L_0x0036:
            r7 = move-exception
        L_0x0037:
            javax.net.ssl.SSLException r3 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x0034 }
            java.lang.String r4 = "Certificate type not supported"
            r3.<init>(r4, r7)     // Catch:{ all -> 0x0034 }
            throw r3     // Catch:{ all -> 0x0034 }
        L_0x003f:
            io.netty.internal.tcnative.SSL.freeBIO(r0)
            if (r2 == 0) goto L_0x0047
            r2.release()
        L_0x0047:
            throw r7
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSslKeyMaterialProvider.validateSupported(java.security.cert.X509Certificate[]):void");
    }

    /* access modifiers changed from: package-private */
    public X509KeyManager keyManager() {
        return this.keyManager;
    }

    /* JADX WARNING: type inference failed for: r0v14, types: [io.netty.handler.ssl.OpenSslKeyMaterial] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.netty.handler.ssl.OpenSslKeyMaterial chooseKeyMaterial(io.netty.buffer.ByteBufAllocator r21, java.lang.String r22) throws java.lang.Exception {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            r2 = r22
            javax.net.ssl.X509KeyManager r3 = r1.keyManager
            java.security.cert.X509Certificate[] r9 = r3.getCertificateChain(r2)
            if (r9 == 0) goto L_0x0094
            int r3 = r9.length
            if (r3 != 0) goto L_0x0013
            goto L_0x0094
        L_0x0013:
            javax.net.ssl.X509KeyManager r3 = r1.keyManager
            java.security.PrivateKey r2 = r3.getPrivateKey(r2)
            r3 = 1
            io.netty.handler.ssl.PemEncoded r3 = io.netty.handler.ssl.PemX509Certificate.toPEM(r0, r3, r9)
            r10 = 0
            io.netty.handler.ssl.PemEncoded r4 = r3.retain()     // Catch:{ all -> 0x0076 }
            long r12 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.toBIO((io.netty.buffer.ByteBufAllocator) r0, (io.netty.handler.ssl.PemEncoded) r4)     // Catch:{ all -> 0x0076 }
            long r14 = io.netty.internal.tcnative.SSL.parseX509Chain(r12)     // Catch:{ all -> 0x0072 }
            boolean r4 = r2 instanceof io.netty.handler.ssl.OpenSslPrivateKey     // Catch:{ all -> 0x006d }
            if (r4 == 0) goto L_0x0037
            io.netty.handler.ssl.OpenSslPrivateKey r2 = (io.netty.handler.ssl.OpenSslPrivateKey) r2     // Catch:{ all -> 0x006d }
            io.netty.handler.ssl.OpenSslKeyMaterial r0 = r2.newKeyMaterial(r14, r9)     // Catch:{ all -> 0x006d }
            goto L_0x0055
        L_0x0037:
            long r7 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.toBIO((io.netty.buffer.ByteBufAllocator) r0, (java.security.PrivateKey) r2)     // Catch:{ all -> 0x006d }
            if (r2 != 0) goto L_0x0040
            r16 = r10
            goto L_0x0048
        L_0x0040:
            java.lang.String r0 = r1.password     // Catch:{ all -> 0x0067 }
            long r4 = io.netty.internal.tcnative.SSL.parsePrivateKey(r7, r0)     // Catch:{ all -> 0x0067 }
            r16 = r4
        L_0x0048:
            io.netty.handler.ssl.DefaultOpenSslKeyMaterial r0 = new io.netty.handler.ssl.DefaultOpenSslKeyMaterial     // Catch:{ all -> 0x0063 }
            r4 = r0
            r5 = r14
            r18 = r7
            r7 = r16
            r4.<init>(r5, r7, r9)     // Catch:{ all -> 0x005f }
            r10 = r18
        L_0x0055:
            io.netty.internal.tcnative.SSL.freeBIO(r12)
            io.netty.internal.tcnative.SSL.freeBIO(r10)
            r3.release()
            return r0
        L_0x005f:
            r0 = move-exception
            r7 = r18
            goto L_0x007c
        L_0x0063:
            r0 = move-exception
            r18 = r7
            goto L_0x007c
        L_0x0067:
            r0 = move-exception
            r18 = r7
            r16 = r10
            goto L_0x007c
        L_0x006d:
            r0 = move-exception
            r7 = r10
            r16 = r7
            goto L_0x007c
        L_0x0072:
            r0 = move-exception
            r7 = r10
            r14 = r7
            goto L_0x007a
        L_0x0076:
            r0 = move-exception
            r7 = r10
            r12 = r7
            r14 = r12
        L_0x007a:
            r16 = r14
        L_0x007c:
            io.netty.internal.tcnative.SSL.freeBIO(r12)
            io.netty.internal.tcnative.SSL.freeBIO(r7)
            int r2 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0089
            io.netty.internal.tcnative.SSL.freeX509Chain(r14)
        L_0x0089:
            int r2 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0090
            io.netty.internal.tcnative.SSL.freePrivateKey(r16)
        L_0x0090:
            r3.release()
            throw r0
        L_0x0094:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSslKeyMaterialProvider.chooseKeyMaterial(io.netty.buffer.ByteBufAllocator, java.lang.String):io.netty.handler.ssl.OpenSslKeyMaterial");
    }
}
