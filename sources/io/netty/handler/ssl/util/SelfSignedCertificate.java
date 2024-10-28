package io.netty.handler.ssl.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

public final class SelfSignedCertificate {
    private static final int DEFAULT_KEY_LENGTH_BITS = SystemPropertyUtil.getInt("io.netty.handler.ssl.util.selfSignedKeyStrength", 2048);
    private static final Date DEFAULT_NOT_AFTER = new Date(SystemPropertyUtil.getLong("io.netty.selfSignedCertificate.defaultNotAfter", 253402300799000L));
    private static final Date DEFAULT_NOT_BEFORE = new Date(SystemPropertyUtil.getLong("io.netty.selfSignedCertificate.defaultNotBefore", System.currentTimeMillis() - 31536000000L));
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SelfSignedCertificate.class);
    private final X509Certificate cert;
    private final File certificate;
    private final PrivateKey key;
    private final File privateKey;

    public SelfSignedCertificate() throws CertificateException {
        this(DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(Date date, Date date2) throws CertificateException {
        this("localhost", date, date2, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(Date date, Date date2, String str, int i) throws CertificateException {
        this("localhost", date, date2, str, i);
    }

    public SelfSignedCertificate(String str) throws CertificateException {
        this(str, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(String str, String str2, int i) throws CertificateException {
        this(str, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, str2, i);
    }

    public SelfSignedCertificate(String str, Date date, Date date2) throws CertificateException {
        this(str, ThreadLocalInsecureRandom.current(), DEFAULT_KEY_LENGTH_BITS, date, date2, "RSA");
    }

    public SelfSignedCertificate(String str, Date date, Date date2, String str2, int i) throws CertificateException {
        this(str, ThreadLocalInsecureRandom.current(), i, date, date2, str2);
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i) throws CertificateException {
        this(str, secureRandom, i, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA");
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, String str2, int i) throws CertificateException {
        this(str, secureRandom, i, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, str2);
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i, Date date, Date date2) throws CertificateException {
        this(str, secureRandom, i, date, date2, "RSA");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c1 A[SYNTHETIC, Splitter:B:41:0x00c1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SelfSignedCertificate(java.lang.String r9, java.security.SecureRandom r10, int r11, java.util.Date r12, java.util.Date r13, java.lang.String r14) throws java.security.cert.CertificateException {
        /*
            r8 = this;
            java.lang.String r0 = "Failed to close a file: "
            r8.<init>()
            java.lang.String r1 = "EC"
            boolean r1 = r1.equalsIgnoreCase(r14)
            if (r1 != 0) goto L_0x002a
            java.lang.String r1 = "RSA"
            boolean r1 = r1.equalsIgnoreCase(r14)
            if (r1 == 0) goto L_0x0016
            goto L_0x002a
        L_0x0016:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Algorithm not valid: "
            r10.<init>(r11)
            r10.append(r14)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x002a:
            java.security.KeyPairGenerator r1 = java.security.KeyPairGenerator.getInstance(r14)     // Catch:{ NoSuchAlgorithmException -> 0x00f5 }
            r1.initialize(r11, r10)     // Catch:{ NoSuchAlgorithmException -> 0x00f5 }
            java.security.KeyPair r11 = r1.generateKeyPair()     // Catch:{ NoSuchAlgorithmException -> 0x00f5 }
            r2 = r9
            r3 = r11
            r4 = r10
            r5 = r12
            r6 = r13
            r7 = r14
            java.lang.String[] r9 = io.netty.handler.ssl.util.BouncyCastleSelfSignedCertGenerator.generate(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0040 }
            goto L_0x0060
        L_0x0040:
            r1 = move-exception
            boolean r2 = isBouncyCastleAvailable()
            if (r2 != 0) goto L_0x004f
            io.netty.util.internal.logging.InternalLogger r2 = logger
            java.lang.String r3 = "Failed to generate a self-signed X.509 certificate because BouncyCastle PKIX is not available in classpath"
            r2.debug((java.lang.String) r3)
            goto L_0x0056
        L_0x004f:
            io.netty.util.internal.logging.InternalLogger r2 = logger
            java.lang.String r3 = "Failed to generate a self-signed X.509 certificate using Bouncy Castle:"
            r2.debug((java.lang.String) r3, (java.lang.Throwable) r1)
        L_0x0056:
            r2 = r9
            r3 = r11
            r4 = r10
            r5 = r12
            r6 = r13
            r7 = r14
            java.lang.String[] r9 = io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator.generate(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00e2 }
        L_0x0060:
            java.io.File r10 = new java.io.File
            r12 = 0
            r12 = r9[r12]
            r10.<init>(r12)
            r8.certificate = r10
            java.io.File r12 = new java.io.File
            r13 = 1
            r9 = r9[r13]
            r12.<init>(r9)
            r8.privateKey = r12
            java.security.PrivateKey r9 = r11.getPrivate()
            r8.key = r9
            r9 = 0
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r11.<init>(r10)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            java.lang.String r9 = "X509"
            java.security.cert.CertificateFactory r9 = java.security.cert.CertificateFactory.getInstance(r9)     // Catch:{ Exception -> 0x00af }
            java.security.cert.Certificate r9 = r9.generateCertificate(r11)     // Catch:{ Exception -> 0x00af }
            java.security.cert.X509Certificate r9 = (java.security.cert.X509Certificate) r9     // Catch:{ Exception -> 0x00af }
            r8.cert = r9     // Catch:{ Exception -> 0x00af }
            r11.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x00ae
        L_0x0092:
            r9 = move-exception
            io.netty.util.internal.logging.InternalLogger r10 = logger
            boolean r10 = r10.isWarnEnabled()
            if (r10 == 0) goto L_0x00ae
            io.netty.util.internal.logging.InternalLogger r10 = logger
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r0)
            java.io.File r12 = r8.certificate
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.warn((java.lang.String) r11, (java.lang.Throwable) r9)
        L_0x00ae:
            return
        L_0x00af:
            r9 = move-exception
            goto L_0x00b8
        L_0x00b1:
            r10 = move-exception
            r11 = r9
            r9 = r10
            goto L_0x00bf
        L_0x00b5:
            r10 = move-exception
            r11 = r9
            r9 = r10
        L_0x00b8:
            java.security.cert.CertificateEncodingException r10 = new java.security.cert.CertificateEncodingException     // Catch:{ all -> 0x00be }
            r10.<init>(r9)     // Catch:{ all -> 0x00be }
            throw r10     // Catch:{ all -> 0x00be }
        L_0x00be:
            r9 = move-exception
        L_0x00bf:
            if (r11 == 0) goto L_0x00e1
            r11.close()     // Catch:{ IOException -> 0x00c5 }
            goto L_0x00e1
        L_0x00c5:
            r10 = move-exception
            io.netty.util.internal.logging.InternalLogger r11 = logger
            boolean r11 = r11.isWarnEnabled()
            if (r11 == 0) goto L_0x00e1
            io.netty.util.internal.logging.InternalLogger r11 = logger
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r0)
            java.io.File r13 = r8.certificate
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.warn((java.lang.String) r12, (java.lang.Throwable) r10)
        L_0x00e1:
            throw r9
        L_0x00e2:
            r9 = move-exception
            io.netty.util.internal.logging.InternalLogger r10 = logger
            java.lang.String r11 = "Failed to generate a self-signed X.509 certificate using sun.security.x509:"
            r10.debug((java.lang.String) r11, (java.lang.Throwable) r9)
            java.security.cert.CertificateException r10 = new java.security.cert.CertificateException
            java.lang.String r11 = "No provider succeeded to generate a self-signed certificate. See debug log for the root cause."
            r10.<init>(r11, r9)
            io.netty.util.internal.ThrowableUtil.addSuppressed((java.lang.Throwable) r10, (java.lang.Throwable) r1)
            throw r10
        L_0x00f5:
            r9 = move-exception
            java.lang.Error r10 = new java.lang.Error
            r10.<init>(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.util.SelfSignedCertificate.<init>(java.lang.String, java.security.SecureRandom, int, java.util.Date, java.util.Date, java.lang.String):void");
    }

    public File certificate() {
        return this.certificate;
    }

    public File privateKey() {
        return this.privateKey;
    }

    public X509Certificate cert() {
        return this.cert;
    }

    public PrivateKey key() {
        return this.key;
    }

    public void delete() {
        safeDelete(this.certificate);
        safeDelete(this.privateKey);
    }

    /* JADX INFO: finally extract failed */
    static String[] newSelfSignedCertificate(String str, PrivateKey privateKey2, X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(privateKey2.getEncoded());
        try {
            wrappedBuffer = Base64.encode(wrappedBuffer, true);
            String str2 = "-----BEGIN PRIVATE KEY-----\n" + wrappedBuffer.toString(CharsetUtil.US_ASCII) + "\n-----END PRIVATE KEY-----\n";
            wrappedBuffer.release();
            String replaceAll = str.replaceAll("[^\\w.-]", "x");
            File createTempFile = PlatformDependent.createTempFile("keyutil_" + replaceAll + '_', ".key", (File) null);
            createTempFile.deleteOnExit();
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            try {
                fileOutputStream.write(str2.getBytes(CharsetUtil.US_ASCII));
                fileOutputStream.close();
                ByteBuf wrappedBuffer2 = Unpooled.wrappedBuffer(x509Certificate.getEncoded());
                try {
                    wrappedBuffer2 = Base64.encode(wrappedBuffer2, true);
                    String str3 = "-----BEGIN CERTIFICATE-----\n" + wrappedBuffer2.toString(CharsetUtil.US_ASCII) + "\n-----END CERTIFICATE-----\n";
                    wrappedBuffer2.release();
                    File createTempFile2 = PlatformDependent.createTempFile("keyutil_" + replaceAll + '_', ".crt", (File) null);
                    createTempFile2.deleteOnExit();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile2);
                    try {
                        fileOutputStream2.write(str3.getBytes(CharsetUtil.US_ASCII));
                        fileOutputStream2.close();
                        return new String[]{createTempFile2.getPath(), createTempFile.getPath()};
                    } catch (Throwable th) {
                        safeClose(createTempFile2, fileOutputStream2);
                        safeDelete(createTempFile2);
                        safeDelete(createTempFile);
                        throw th;
                    }
                } catch (Throwable th2) {
                    throw th2;
                } finally {
                    wrappedBuffer2.release();
                }
            } catch (Throwable th3) {
                safeClose(createTempFile, fileOutputStream);
                safeDelete(createTempFile);
                throw th3;
            }
        } catch (Throwable th4) {
            throw th4;
        } finally {
            wrappedBuffer.release();
        }
    }

    private static void safeDelete(File file) {
        if (!file.delete()) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isWarnEnabled()) {
                internalLogger.warn("Failed to delete a file: " + file);
            }
        }
    }

    private static void safeClose(File file, OutputStream outputStream) {
        try {
            outputStream.close();
        } catch (IOException e) {
            if (logger.isWarnEnabled()) {
                InternalLogger internalLogger = logger;
                internalLogger.warn("Failed to close a file: " + file, (Throwable) e);
            }
        }
    }

    private static boolean isBouncyCastleAvailable() {
        try {
            Class.forName("org.bouncycastle.cert.X509v3CertificateBuilder");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
