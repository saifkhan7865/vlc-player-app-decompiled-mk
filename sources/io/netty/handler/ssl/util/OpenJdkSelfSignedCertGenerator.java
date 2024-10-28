package io.netty.handler.ssl.util;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Date;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

final class OpenJdkSelfSignedCertGenerator {
    private static final Constructor<X509CertImpl> CERT_IMPL_CONSTRUCTOR;
    private static final Method CERT_IMPL_GET_METHOD;
    private static final Method CERT_IMPL_SIGN_METHOD;
    private static final Method CERT_INFO_SET_METHOD;
    private static final Constructor<?> ISSUER_NAME_CONSTRUCTOR;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OpenJdkSelfSignedCertGenerator.class);

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.reflect.GenericDeclaration} */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.reflect.Constructor<sun.security.x509.X509CertImpl>] */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.reflect.Constructor<?>] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v10, types: [java.lang.reflect.Constructor] */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    static {
        /*
            java.lang.Class<io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator> r0 = io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator.class
            io.netty.util.internal.logging.InternalLogger r0 = io.netty.util.internal.logging.InternalLoggerFactory.getInstance((java.lang.Class<?>) r0)
            logger = r0
            r0 = 0
            io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$1 r1 = new io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$1     // Catch:{ all -> 0x0070 }
            r1.<init>()     // Catch:{ all -> 0x0070 }
            java.lang.Object r1 = java.security.AccessController.doPrivileged(r1)     // Catch:{ all -> 0x0070 }
            boolean r2 = r1 instanceof java.lang.reflect.Method     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x006d
            java.lang.reflect.Method r1 = (java.lang.reflect.Method) r1     // Catch:{ all -> 0x0070 }
            io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$2 r2 = new io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$2     // Catch:{ all -> 0x006a }
            r2.<init>()     // Catch:{ all -> 0x006a }
            java.lang.Object r2 = java.security.AccessController.doPrivileged(r2)     // Catch:{ all -> 0x006a }
            boolean r3 = r2 instanceof java.lang.reflect.Constructor     // Catch:{ all -> 0x006a }
            if (r3 == 0) goto L_0x0067
            java.lang.reflect.Constructor r2 = (java.lang.reflect.Constructor) r2     // Catch:{ all -> 0x006a }
            io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$3 r3 = new io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$3     // Catch:{ all -> 0x0064 }
            r3.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.Object r3 = java.security.AccessController.doPrivileged(r3)     // Catch:{ all -> 0x0064 }
            boolean r4 = r3 instanceof java.lang.reflect.Constructor     // Catch:{ all -> 0x0064 }
            if (r4 == 0) goto L_0x0061
            java.lang.reflect.Constructor r3 = (java.lang.reflect.Constructor) r3     // Catch:{ all -> 0x0064 }
            io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$4 r4 = new io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$4     // Catch:{ all -> 0x005e }
            r4.<init>()     // Catch:{ all -> 0x005e }
            java.lang.Object r4 = java.security.AccessController.doPrivileged(r4)     // Catch:{ all -> 0x005e }
            boolean r5 = r4 instanceof java.lang.reflect.Method     // Catch:{ all -> 0x005e }
            if (r5 == 0) goto L_0x005b
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4     // Catch:{ all -> 0x005e }
            io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$5 r5 = new io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator$5     // Catch:{ all -> 0x0059 }
            r5.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.Object r5 = java.security.AccessController.doPrivileged(r5)     // Catch:{ all -> 0x0059 }
            boolean r6 = r5 instanceof java.lang.reflect.Method     // Catch:{ all -> 0x0059 }
            if (r6 == 0) goto L_0x0056
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5     // Catch:{ all -> 0x0059 }
            r0 = r5
            goto L_0x007c
        L_0x0056:
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ all -> 0x0059 }
            throw r5     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r5 = move-exception
            goto L_0x0075
        L_0x005b:
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ all -> 0x005e }
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005e:
            r5 = move-exception
            r4 = r0
            goto L_0x0075
        L_0x0061:
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ all -> 0x0064 }
            throw r3     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r5 = move-exception
            r3 = r0
            goto L_0x0074
        L_0x0067:
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x006a }
            throw r2     // Catch:{ all -> 0x006a }
        L_0x006a:
            r5 = move-exception
            r2 = r0
            goto L_0x0073
        L_0x006d:
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x0070 }
            throw r1     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r5 = move-exception
            r1 = r0
            r2 = r1
        L_0x0073:
            r3 = r2
        L_0x0074:
            r4 = r3
        L_0x0075:
            io.netty.util.internal.logging.InternalLogger r6 = logger
            java.lang.String r7 = "OpenJdkSelfSignedCertGenerator not supported"
            r6.debug((java.lang.String) r7, (java.lang.Throwable) r5)
        L_0x007c:
            CERT_INFO_SET_METHOD = r1
            ISSUER_NAME_CONSTRUCTOR = r2
            CERT_IMPL_CONSTRUCTOR = r3
            CERT_IMPL_GET_METHOD = r4
            CERT_IMPL_SIGN_METHOD = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.util.OpenJdkSelfSignedCertGenerator.<clinit>():void");
    }

    static String[] generate(String str, KeyPair keyPair, SecureRandom secureRandom, Date date, Date date2, String str2) throws Exception {
        String str3 = str;
        String str4 = str2;
        Method method = CERT_INFO_SET_METHOD;
        if (method == null || ISSUER_NAME_CONSTRUCTOR == null || CERT_IMPL_CONSTRUCTOR == null || CERT_IMPL_GET_METHOD == null || CERT_IMPL_SIGN_METHOD == null) {
            throw new UnsupportedOperationException("OpenJdkSelfSignedCertGenerator not supported on the used JDK version");
        }
        PrivateKey privateKey = keyPair.getPrivate();
        X509CertInfo x509CertInfo = new X509CertInfo();
        X500Name x500Name = new X500Name("CN=" + str3);
        method.invoke(x509CertInfo, new Object[]{"version", new CertificateVersion(2)});
        method.invoke(x509CertInfo, new Object[]{"serialNumber", new CertificateSerialNumber(new BigInteger(64, secureRandom))});
        try {
            method.invoke(x509CertInfo, new Object[]{"subject", new CertificateSubjectName(x500Name)});
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof CertificateException) {
                CERT_INFO_SET_METHOD.invoke(x509CertInfo, new Object[]{"subject", x500Name});
            } else {
                throw e;
            }
        }
        try {
            CERT_INFO_SET_METHOD.invoke(x509CertInfo, new Object[]{"issuer", ISSUER_NAME_CONSTRUCTOR.newInstance(new Object[]{x500Name})});
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof CertificateException) {
                CERT_INFO_SET_METHOD.invoke(x509CertInfo, new Object[]{"issuer", x500Name});
            } else {
                throw e2;
            }
        }
        Method method2 = CERT_INFO_SET_METHOD;
        method2.invoke(x509CertInfo, new Object[]{"validity", new CertificateValidity(date, date2)});
        method2.invoke(x509CertInfo, new Object[]{LeanbackPreferenceDialogFragment.ARG_KEY, new CertificateX509Key(keyPair.getPublic())});
        method2.invoke(x509CertInfo, new Object[]{"algorithmID", new CertificateAlgorithmId(AlgorithmId.get("1.2.840.113549.1.1.11"))});
        Constructor<X509CertImpl> constructor = CERT_IMPL_CONSTRUCTOR;
        X509CertImpl newInstance = constructor.newInstance(new Object[]{x509CertInfo});
        Method method3 = CERT_IMPL_SIGN_METHOD;
        String str5 = "SHA256withECDSA";
        method3.invoke(newInstance, new Object[]{privateKey, str4.equalsIgnoreCase("EC") ? str5 : "SHA256withRSA"});
        method2.invoke(x509CertInfo, new Object[]{"algorithmID.algorithm", CERT_IMPL_GET_METHOD.invoke(newInstance, new Object[]{"x509.algorithm"})});
        X509CertImpl newInstance2 = constructor.newInstance(new Object[]{x509CertInfo});
        if (!str4.equalsIgnoreCase("EC")) {
            str5 = "SHA256withRSA";
        }
        method3.invoke(newInstance2, new Object[]{privateKey, str5});
        newInstance2.verify(keyPair.getPublic());
        return SelfSignedCertificate.newSelfSignedCertificate(str3, privateKey, newInstance2);
    }

    private OpenJdkSelfSignedCertGenerator() {
    }
}
