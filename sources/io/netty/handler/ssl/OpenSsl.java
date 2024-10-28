package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.internal.tcnative.Buffer;
import io.netty.internal.tcnative.Library;
import io.netty.internal.tcnative.SSL;
import io.netty.internal.tcnative.SSLContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.NativeLibraryLoader;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.fusesource.jansi.AnsiRenderer;

public final class OpenSsl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Set<String> AVAILABLE_CIPHER_SUITES;
    private static final Set<String> AVAILABLE_JAVA_CIPHER_SUITES;
    private static final Set<String> AVAILABLE_OPENSSL_CIPHER_SUITES;
    private static final String CERT = "-----BEGIN CERTIFICATE-----\nMIICrjCCAZagAwIBAgIIdSvQPv1QAZQwDQYJKoZIhvcNAQELBQAwFjEUMBIGA1UEAxMLZXhhbXBs\nZS5jb20wIBcNMTgwNDA2MjIwNjU5WhgPOTk5OTEyMzEyMzU5NTlaMBYxFDASBgNVBAMTC2V4YW1w\nbGUuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAggbWsmDQ6zNzRZ5AW8E3eoGl\nqWvOBDb5Fs1oBRrVQHuYmVAoaqwDzXYJ0LOwa293AgWEQ1jpcbZ2hpoYQzqEZBTLnFhMrhRFlH6K\nbJND8Y33kZ/iSVBBDuGbdSbJShlM+4WwQ9IAso4MZ4vW3S1iv5fGGpLgbtXRmBf/RU8omN0Gijlv\nWlLWHWijLN8xQtySFuBQ7ssW8RcKAary3pUm6UUQB+Co6lnfti0Tzag8PgjhAJq2Z3wbsGRnP2YS\nvYoaK6qzmHXRYlp/PxrjBAZAmkLJs4YTm/XFF+fkeYx4i9zqHbyone5yerRibsHaXZWLnUL+rFoe\nMdKvr0VS3sGmhQIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQADQi441pKmXf9FvUV5EHU4v8nJT9Iq\nyqwsKwXnr7AsUlDGHBD7jGrjAXnG5rGxuNKBQ35wRxJATKrUtyaquFUL6H8O6aGQehiFTk6zmPbe\n12Gu44vqqTgIUxnv3JQJiox8S2hMxsSddpeCmSdvmalvD6WG4NthH6B9ZaBEiep1+0s0RUaBYn73\nI7CCUaAtbjfR6pcJjrFk5ei7uwdQZFSJtkP2z8r7zfeANJddAKFlkaMWn7u+OIVuB4XPooWicObk\nNAHFtP65bocUYnDpTVdiyvn8DdqyZ/EO8n1bBKBzuSLplk2msW4pdgaFgY7Vw/0wzcFXfUXmL1uy\nG8sQD/wx\n-----END CERTIFICATE-----";
    private static final Set<String> CLIENT_DEFAULT_PROTOCOLS = protocols("jdk.tls.client.protocols");
    static final List<String> DEFAULT_CIPHERS;
    private static final String[] DEFAULT_NAMED_GROUPS = {"x25519", "secp256r1", "secp384r1", "secp521r1"};
    static final String[] EXTRA_SUPPORTED_TLS_1_3_CIPHERS;
    static final String EXTRA_SUPPORTED_TLS_1_3_CIPHERS_STRING;
    private static final boolean IS_BORINGSSL;
    static final boolean JAVAX_CERTIFICATE_CREATION_SUPPORTED;
    private static final String KEY = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCBtayYNDrM3NFnkBbwTd6gaWp\na84ENvkWzWgFGtVAe5iZUChqrAPNdgnQs7Brb3cCBYRDWOlxtnaGmhhDOoRkFMucWEyuFEWUfops\nk0PxjfeRn+JJUEEO4Zt1JslKGUz7hbBD0gCyjgxni9bdLWK/l8YakuBu1dGYF/9FTyiY3QaKOW9a\nUtYdaKMs3zFC3JIW4FDuyxbxFwoBqvLelSbpRRAH4KjqWd+2LRPNqDw+COEAmrZnfBuwZGc/ZhK9\nihorqrOYddFiWn8/GuMEBkCaQsmzhhOb9cUX5+R5jHiL3OodvKid7nJ6tGJuwdpdlYudQv6sWh4x\n0q+vRVLewaaFAgMBAAECggEAP8tPJvFtTxhNJAkCloHz0D0vpDHqQBMgntlkgayqmBqLwhyb18pR\ni0qwgh7HHc7wWqOOQuSqlEnrWRrdcI6TSe8R/sErzfTQNoznKWIPYcI/hskk4sdnQ//Yn9/Jvnsv\nU/BBjOTJxtD+sQbhAl80JcA3R+5sArURQkfzzHOL/YMqzAsn5hTzp7HZCxUqBk3KaHRxV7NefeOE\nxlZuWSmxYWfbFIs4kx19/1t7h8CHQWezw+G60G2VBtSBBxDnhBWvqG6R/wpzJ3nEhPLLY9T+XIHe\nipzdMOOOUZorfIg7M+pyYPji+ZIZxIpY5OjrOzXHciAjRtr5Y7l99K1CG1LguQKBgQDrQfIMxxtZ\nvxU/1cRmUV9l7pt5bjV5R6byXq178LxPKVYNjdZ840Q0/OpZEVqaT1xKVi35ohP1QfNjxPLlHD+K\niDAR9z6zkwjIrbwPCnb5kuXy4lpwPcmmmkva25fI7qlpHtbcuQdoBdCfr/KkKaUCMPyY89LCXgEw\n5KTDj64UywKBgQCNfbO+eZLGzhiHhtNJurresCsIGWlInv322gL8CSfBMYl6eNfUTZvUDdFhPISL\nUljKWzXDrjw0ujFSPR0XhUGtiq89H+HUTuPPYv25gVXO+HTgBFZEPl4PpA+BUsSVZy0NddneyqLk\n42Wey9omY9Q8WsdNQS5cbUvy0uG6WFoX7wKBgQDZ1jpW8pa0x2bZsQsm4vo+3G5CRnZlUp+XlWt2\ndDcp5dC0xD1zbs1dc0NcLeGDOTDv9FSl7hok42iHXXq8AygjEm/QcuwwQ1nC2HxmQP5holAiUs4D\nWHM8PWs3wFYPzE459EBoKTxeaeP/uWAn+he8q7d5uWvSZlEcANs/6e77eQKBgD21Ar0hfFfj7mK8\n9E0FeRZBsqK3omkfnhcYgZC11Xa2SgT1yvs2Va2n0RcdM5kncr3eBZav2GYOhhAdwyBM55XuE/sO\neokDVutNeuZ6d5fqV96TRaRBpvgfTvvRwxZ9hvKF4Vz+9wfn/JvCwANaKmegF6ejs7pvmF3whq2k\ndrZVAoGAX5YxQ5XMTD0QbMAl7/6qp6S58xNoVdfCkmkj1ZLKaHKIjS/benkKGlySVQVPexPfnkZx\np/Vv9yyphBoudiTBS9Uog66ueLYZqpgxlM/6OhYg86Gm3U2ycvMxYjBM1NFiyze21AqAhI+HX+Ot\nmraV2/guSgDgZAhukRZzeQ2RucI=\n-----END PRIVATE KEY-----";
    static final String[] NAMED_GROUPS;
    private static final Set<String> SERVER_DEFAULT_PROTOCOLS = protocols("jdk.tls.server.protocols");
    static final Set<String> SUPPORTED_PROTOCOLS_SET;
    private static final boolean SUPPORTS_KEYMANAGER_FACTORY;
    private static final boolean SUPPORTS_OCSP;
    private static final boolean TLSV13_SUPPORTED;
    private static final Throwable UNAVAILABILITY_CAUSE;
    private static final boolean USE_KEYMANAGER_FACTORY;
    private static final InternalLogger logger;

    /* JADX WARNING: type inference failed for: r0v53, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01f5 A[Catch:{ all -> 0x0214 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0281 A[SYNTHETIC, Splitter:B:158:0x0281] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x028c A[Catch:{ all -> 0x0285 }] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0293 A[Catch:{ all -> 0x0285 }] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x029a A[Catch:{ all -> 0x0285 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x02a6 A[Catch:{ all -> 0x0362 }] */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x034e  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x0389 A[Catch:{ all -> 0x03a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0390 A[Catch:{ all -> 0x03a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x0397 A[Catch:{ all -> 0x03a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x039e A[Catch:{ all -> 0x03a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x03de  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x044f  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x045d  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x046b  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x047a  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0489  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x04a3  */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x04bb  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x017b A[Catch:{ all -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x019f A[Catch:{ all -> 0x0375 }] */
    static {
        /*
            java.lang.String r1 = "io.netty.handler.ssl.openssl.useKeyManagerFactory"
            java.lang.Class<io.netty.handler.ssl.OpenSsl> r0 = io.netty.handler.ssl.OpenSsl.class
            io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.internal.logging.InternalLoggerFactory.getInstance((java.lang.Class<?>) r0)
            logger = r2
            r3 = 4
            java.lang.String[] r4 = new java.lang.String[r3]
            java.lang.String r5 = "x25519"
            r6 = 0
            r4[r6] = r5
            java.lang.String r5 = "secp256r1"
            r7 = 1
            r4[r7] = r5
            java.lang.String r5 = "secp384r1"
            r8 = 2
            r4[r8] = r5
            java.lang.String r5 = "secp521r1"
            r9 = 3
            r4[r9] = r5
            DEFAULT_NAMED_GROUPS = r4
            java.lang.String r4 = "io.netty.handler.ssl.noOpenSsl"
            boolean r4 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r4, r6)
            r5 = 0
            if (r4 == 0) goto L_0x003b
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r4 = "OpenSSL was explicit disabled with -Dio.netty.handler.ssl.noOpenSsl=true"
            r0.<init>(r4)
            java.lang.Class<io.netty.handler.ssl.OpenSslEngine> r4 = io.netty.handler.ssl.OpenSslEngine.class
            java.lang.String r4 = "netty-tcnative explicit disabled; OpenSslEngine will be unavailable."
            r2.debug((java.lang.String) r4, (java.lang.Throwable) r0)
            goto L_0x008c
        L_0x003b:
            java.lang.String r2 = "io.netty.internal.tcnative.SSLContext"
            java.lang.ClassLoader r0 = io.netty.util.internal.PlatformDependent.getClassLoader(r0)     // Catch:{ ClassNotFoundException -> 0x0046 }
            java.lang.Class.forName(r2, r6, r0)     // Catch:{ ClassNotFoundException -> 0x0046 }
            r0 = r5
            goto L_0x0050
        L_0x0046:
            r0 = move-exception
            io.netty.util.internal.logging.InternalLogger r2 = logger
            java.lang.Class<io.netty.handler.ssl.OpenSslEngine> r4 = io.netty.handler.ssl.OpenSslEngine.class
            java.lang.String r4 = "netty-tcnative not in the classpath; OpenSslEngine will be unavailable."
            r2.debug((java.lang.String) r4)
        L_0x0050:
            if (r0 != 0) goto L_0x008c
            loadTcNative()     // Catch:{ all -> 0x0057 }
            r2 = r0
            goto L_0x0062
        L_0x0057:
            r0 = move-exception
            r2 = r0
            io.netty.util.internal.logging.InternalLogger r0 = logger
            java.lang.Class<io.netty.handler.ssl.OpenSslEngine> r4 = io.netty.handler.ssl.OpenSslEngine.class
            java.lang.String r4 = "Failed to load netty-tcnative; OpenSslEngine will be unavailable, unless the application has already loaded the symbols by some other means. See https://netty.io/wiki/forked-tomcat-native.html for more information."
            r0.debug((java.lang.String) r4, (java.lang.Throwable) r2)
        L_0x0062:
            java.lang.String r0 = "io.netty.handler.ssl.openssl.engine"
            java.lang.String r0 = io.netty.util.internal.SystemPropertyUtil.get(r0, r5)     // Catch:{ all -> 0x007e }
            if (r0 != 0) goto L_0x0072
            io.netty.util.internal.logging.InternalLogger r4 = logger     // Catch:{ all -> 0x007e }
            java.lang.String r10 = "Initialize netty-tcnative using engine: 'default'"
            r4.debug((java.lang.String) r10)     // Catch:{ all -> 0x007e }
            goto L_0x0079
        L_0x0072:
            io.netty.util.internal.logging.InternalLogger r4 = logger     // Catch:{ all -> 0x007e }
            java.lang.String r10 = "Initialize netty-tcnative using engine: '{}'"
            r4.debug((java.lang.String) r10, (java.lang.Object) r0)     // Catch:{ all -> 0x007e }
        L_0x0079:
            initializeTcNative(r0)     // Catch:{ all -> 0x007e }
            r0 = r5
            goto L_0x008c
        L_0x007e:
            r0 = move-exception
            if (r2 != 0) goto L_0x0082
            r2 = r0
        L_0x0082:
            io.netty.util.internal.logging.InternalLogger r4 = logger
            java.lang.Class<io.netty.handler.ssl.OpenSslEngine> r10 = io.netty.handler.ssl.OpenSslEngine.class
            java.lang.String r10 = "Failed to initialize netty-tcnative; OpenSslEngine will be unavailable. See https://netty.io/wiki/forked-tomcat-native.html for more information."
            r4.debug((java.lang.String) r10, (java.lang.Throwable) r0)
            r0 = r2
        L_0x008c:
            UNAVAILABILITY_CAUSE = r0
            java.lang.String r2 = "jdk.tls.client.protocols"
            java.util.Set r2 = protocols(r2)
            CLIENT_DEFAULT_PROTOCOLS = r2
            java.lang.String r2 = "jdk.tls.server.protocols"
            java.util.Set r2 = protocols(r2)
            SERVER_DEFAULT_PROTOCOLS = r2
            java.lang.String r2 = ""
            if (r0 != 0) goto L_0x04d6
            io.netty.util.internal.logging.InternalLogger r0 = logger
            java.lang.String r4 = "netty-tcnative using native library: {}"
            java.lang.String r10 = io.netty.internal.tcnative.SSL.versionString()
            r0.debug((java.lang.String) r4, (java.lang.Object) r10)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.LinkedHashSet r10 = new java.util.LinkedHashSet
            r0 = 128(0x80, float:1.794E-43)
            r10.<init>(r0)
            java.lang.String[] r11 = DEFAULT_NAMED_GROUPS
            int r12 = r11.length
            java.lang.String[] r12 = new java.lang.String[r12]
            r13 = 0
        L_0x00bf:
            int r14 = r11.length
            if (r13 >= r14) goto L_0x00cd
            r14 = r11[r13]
            java.lang.String r14 = io.netty.handler.ssl.GroupsConverter.toOpenSsl(r14)
            r12[r13] = r14
            int r13 = r13 + 1
            goto L_0x00bf
        L_0x00cd:
            java.lang.String r13 = "BoringSSL"
            java.lang.String r14 = versionString()
            boolean r13 = r13.equals(r14)
            IS_BORINGSSL = r13
            if (r13 == 0) goto L_0x0110
            java.lang.String[] r2 = new java.lang.String[r9]
            java.lang.String r13 = "TLS_AES_128_GCM_SHA256"
            r2[r6] = r13
            java.lang.String r13 = "TLS_AES_256_GCM_SHA384"
            r2[r7] = r13
            java.lang.String r13 = "TLS_CHACHA20_POLY1305_SHA256"
            r2[r8] = r13
            EXTRA_SUPPORTED_TLS_1_3_CIPHERS = r2
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r0)
            int r0 = r2.length
            r14 = 0
        L_0x00f2:
            if (r14 >= r0) goto L_0x0101
            r15 = r2[r14]
            r13.append(r15)
            java.lang.String r15 = ":"
            r13.append(r15)
            int r14 = r14 + 1
            goto L_0x00f2
        L_0x0101:
            int r0 = r13.length()
            int r0 = r0 - r7
            r13.setLength(r0)
            java.lang.String r0 = r13.toString()
            EXTRA_SUPPORTED_TLS_1_3_CIPHERS_STRING = r0
            goto L_0x0116
        L_0x0110:
            java.lang.String[] r0 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS
            EXTRA_SUPPORTED_TLS_1_3_CIPHERS = r0
            EXTRA_SUPPORTED_TLS_1_3_CIPHERS_STRING = r2
        L_0x0116:
            r0 = 63
            long r13 = io.netty.internal.tcnative.SSLContext.make(r0, r7)     // Catch:{ Exception -> 0x03b5 }
            io.netty.handler.ssl.SslProvider r0 = io.netty.handler.ssl.SslProvider.JDK     // Catch:{ all -> 0x03ab }
            boolean r0 = io.netty.handler.ssl.SslProvider.isTlsv13Supported(r0)     // Catch:{ all -> 0x03ab }
            if (r0 == 0) goto L_0x0167
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0167 }
            r0.<init>()     // Catch:{ Exception -> 0x0167 }
            java.util.Set<java.lang.String> r2 = io.netty.handler.ssl.SslUtils.TLSV13_CIPHERS     // Catch:{ Exception -> 0x0167 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x0167 }
        L_0x012f:
            boolean r15 = r2.hasNext()     // Catch:{ Exception -> 0x0167 }
            if (r15 == 0) goto L_0x014d
            java.lang.Object r15 = r2.next()     // Catch:{ Exception -> 0x0167 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Exception -> 0x0167 }
            boolean r3 = IS_BORINGSSL     // Catch:{ Exception -> 0x0167 }
            java.lang.String r3 = io.netty.handler.ssl.CipherSuiteConverter.toOpenSsl(r15, r3)     // Catch:{ Exception -> 0x0167 }
            if (r3 == 0) goto L_0x014b
            r0.append(r3)     // Catch:{ Exception -> 0x0167 }
            r3 = 58
            r0.append(r3)     // Catch:{ Exception -> 0x0167 }
        L_0x014b:
            r3 = 4
            goto L_0x012f
        L_0x014d:
            int r2 = r0.length()     // Catch:{ Exception -> 0x0167 }
            if (r2 != 0) goto L_0x0155
            r0 = 0
            goto L_0x0165
        L_0x0155:
            int r2 = r0.length()     // Catch:{ Exception -> 0x0167 }
            int r2 = r2 - r7
            r0.setLength(r2)     // Catch:{ Exception -> 0x0167 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0167 }
            io.netty.internal.tcnative.SSLContext.setCipherSuite(r13, r0, r7)     // Catch:{ Exception -> 0x0167 }
            r0 = 1
        L_0x0165:
            r2 = r0
            goto L_0x0168
        L_0x0167:
            r2 = 0
        L_0x0168:
            java.lang.String r0 = "ALL"
            io.netty.internal.tcnative.SSLContext.setCipherSuite(r13, r0, r6)     // Catch:{ all -> 0x03a6 }
            long r22 = io.netty.internal.tcnative.SSL.newSSL(r13, r7)     // Catch:{ all -> 0x03a6 }
            r24 = 0
            java.lang.String[] r0 = io.netty.internal.tcnative.SSL.getCiphers(r22)     // Catch:{ all -> 0x0375 }
            int r3 = r0.length     // Catch:{ all -> 0x0375 }
            r15 = 0
        L_0x0179:
            if (r15 >= r3) goto L_0x019b
            r5 = r0[r15]     // Catch:{ all -> 0x0375 }
            if (r5 == 0) goto L_0x0197
            boolean r16 = r5.isEmpty()     // Catch:{ all -> 0x0375 }
            if (r16 != 0) goto L_0x0197
            boolean r16 = r10.contains(r5)     // Catch:{ all -> 0x0375 }
            if (r16 != 0) goto L_0x0197
            if (r2 != 0) goto L_0x0194
            boolean r16 = io.netty.handler.ssl.SslUtils.isTLSv13Cipher(r5)     // Catch:{ all -> 0x0375 }
            if (r16 == 0) goto L_0x0194
            goto L_0x0197
        L_0x0194:
            r10.add(r5)     // Catch:{ all -> 0x0375 }
        L_0x0197:
            int r15 = r15 + 1
            r5 = 0
            goto L_0x0179
        L_0x019b:
            boolean r0 = IS_BORINGSSL     // Catch:{ all -> 0x0375 }
            if (r0 == 0) goto L_0x01b5
            java.lang.String[] r3 = EXTRA_SUPPORTED_TLS_1_3_CIPHERS     // Catch:{ all -> 0x0375 }
            java.util.Collections.addAll(r10, r3)     // Catch:{ all -> 0x0375 }
            java.lang.String[] r3 = new java.lang.String[r9]     // Catch:{ all -> 0x0375 }
            java.lang.String r5 = "AEAD-AES128-GCM-SHA256"
            r3[r6] = r5     // Catch:{ all -> 0x0375 }
            java.lang.String r5 = "AEAD-AES256-GCM-SHA384"
            r3[r7] = r5     // Catch:{ all -> 0x0375 }
            java.lang.String r5 = "AEAD-CHACHA20-POLY1305-SHA256"
            r3[r8] = r5     // Catch:{ all -> 0x0375 }
            java.util.Collections.addAll(r10, r3)     // Catch:{ all -> 0x0375 }
        L_0x01b5:
            java.lang.String r3 = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCBtayYNDrM3NFnkBbwTd6gaWp\na84ENvkWzWgFGtVAe5iZUChqrAPNdgnQs7Brb3cCBYRDWOlxtnaGmhhDOoRkFMucWEyuFEWUfops\nk0PxjfeRn+JJUEEO4Zt1JslKGUz7hbBD0gCyjgxni9bdLWK/l8YakuBu1dGYF/9FTyiY3QaKOW9a\nUtYdaKMs3zFC3JIW4FDuyxbxFwoBqvLelSbpRRAH4KjqWd+2LRPNqDw+COEAmrZnfBuwZGc/ZhK9\nihorqrOYddFiWn8/GuMEBkCaQsmzhhOb9cUX5+R5jHiL3OodvKid7nJ6tGJuwdpdlYudQv6sWh4x\n0q+vRVLewaaFAgMBAAECggEAP8tPJvFtTxhNJAkCloHz0D0vpDHqQBMgntlkgayqmBqLwhyb18pR\ni0qwgh7HHc7wWqOOQuSqlEnrWRrdcI6TSe8R/sErzfTQNoznKWIPYcI/hskk4sdnQ//Yn9/Jvnsv\nU/BBjOTJxtD+sQbhAl80JcA3R+5sArURQkfzzHOL/YMqzAsn5hTzp7HZCxUqBk3KaHRxV7NefeOE\nxlZuWSmxYWfbFIs4kx19/1t7h8CHQWezw+G60G2VBtSBBxDnhBWvqG6R/wpzJ3nEhPLLY9T+XIHe\nipzdMOOOUZorfIg7M+pyYPji+ZIZxIpY5OjrOzXHciAjRtr5Y7l99K1CG1LguQKBgQDrQfIMxxtZ\nvxU/1cRmUV9l7pt5bjV5R6byXq178LxPKVYNjdZ840Q0/OpZEVqaT1xKVi35ohP1QfNjxPLlHD+K\niDAR9z6zkwjIrbwPCnb5kuXy4lpwPcmmmkva25fI7qlpHtbcuQdoBdCfr/KkKaUCMPyY89LCXgEw\n5KTDj64UywKBgQCNfbO+eZLGzhiHhtNJurresCsIGWlInv322gL8CSfBMYl6eNfUTZvUDdFhPISL\nUljKWzXDrjw0ujFSPR0XhUGtiq89H+HUTuPPYv25gVXO+HTgBFZEPl4PpA+BUsSVZy0NddneyqLk\n42Wey9omY9Q8WsdNQS5cbUvy0uG6WFoX7wKBgQDZ1jpW8pa0x2bZsQsm4vo+3G5CRnZlUp+XlWt2\ndDcp5dC0xD1zbs1dc0NcLeGDOTDv9FSl7hok42iHXXq8AygjEm/QcuwwQ1nC2HxmQP5holAiUs4D\nWHM8PWs3wFYPzE459EBoKTxeaeP/uWAn+he8q7d5uWvSZlEcANs/6e77eQKBgD21Ar0hfFfj7mK8\n9E0FeRZBsqK3omkfnhcYgZC11Xa2SgT1yvs2Va2n0RcdM5kncr3eBZav2GYOhhAdwyBM55XuE/sO\neokDVutNeuZ6d5fqV96TRaRBpvgfTvvRwxZ9hvKF4Vz+9wfn/JvCwANaKmegF6ejs7pvmF3whq2k\ndrZVAoGAX5YxQ5XMTD0QbMAl7/6qp6S58xNoVdfCkmkj1ZLKaHKIjS/benkKGlySVQVPexPfnkZx\np/Vv9yyphBoudiTBS9Uog66ueLYZqpgxlM/6OhYg86Gm3U2ycvMxYjBM1NFiyze21AqAhI+HX+Ot\nmraV2/guSgDgZAhukRZzeQ2RucI=\n-----END PRIVATE KEY-----"
            java.nio.charset.Charset r5 = io.netty.util.CharsetUtil.US_ASCII     // Catch:{ all -> 0x0375 }
            byte[] r3 = r3.getBytes(r5)     // Catch:{ all -> 0x0375 }
            io.netty.handler.ssl.PemPrivateKey r3 = io.netty.handler.ssl.PemPrivateKey.valueOf((byte[]) r3)     // Catch:{ all -> 0x0375 }
            r5 = 0
            io.netty.internal.tcnative.SSLContext.setCertificateCallback(r13, r5)     // Catch:{ Error -> 0x0266, all -> 0x0257 }
            java.security.cert.X509Certificate r5 = selfSignedCertificate()     // Catch:{ Error -> 0x0266, all -> 0x0257 }
            io.netty.buffer.ByteBufAllocator r9 = io.netty.buffer.ByteBufAllocator.DEFAULT     // Catch:{ Error -> 0x0266, all -> 0x0257 }
            java.security.cert.X509Certificate[] r15 = new java.security.cert.X509Certificate[r7]     // Catch:{ Error -> 0x0266, all -> 0x0257 }
            r15[r6] = r5     // Catch:{ Error -> 0x0266, all -> 0x0257 }
            long r26 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.toBIO((io.netty.buffer.ByteBufAllocator) r9, (java.security.cert.X509Certificate[]) r15)     // Catch:{ Error -> 0x0266, all -> 0x0257 }
            long r28 = io.netty.internal.tcnative.SSL.parseX509Chain(r26)     // Catch:{ Error -> 0x0252, all -> 0x024a }
            io.netty.buffer.UnpooledByteBufAllocator r5 = io.netty.buffer.UnpooledByteBufAllocator.DEFAULT     // Catch:{ Error -> 0x0245, all -> 0x023d }
            io.netty.handler.ssl.PemEncoded r9 = r3.retain()     // Catch:{ Error -> 0x0245, all -> 0x023d }
            long r8 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.toBIO((io.netty.buffer.ByteBufAllocator) r5, (io.netty.handler.ssl.PemEncoded) r9)     // Catch:{ Error -> 0x0245, all -> 0x023d }
            r5 = 0
            long r30 = io.netty.internal.tcnative.SSL.parsePrivateKey(r8, r5)     // Catch:{ Error -> 0x023a, all -> 0x0234 }
            r16 = r22
            r18 = r28
            r20 = r30
            io.netty.internal.tcnative.SSL.setKeyMaterial(r16, r18, r20)     // Catch:{ Error -> 0x026e, all -> 0x0230 }
            boolean r5 = io.netty.util.internal.SystemPropertyUtil.contains(r1)     // Catch:{ all -> 0x0214 }
            if (r0 != 0) goto L_0x0205
            boolean r0 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r1, r7)     // Catch:{ all -> 0x0214 }
            if (r5 == 0) goto L_0x0212
            io.netty.util.internal.logging.InternalLogger r1 = logger     // Catch:{ all -> 0x0203 }
            java.lang.String r5 = "System property 'io.netty.handler.ssl.openssl.useKeyManagerFactory' is deprecated and so will be ignored in the future"
            r1.info((java.lang.String) r5)     // Catch:{ all -> 0x0203 }
            goto L_0x0212
        L_0x0203:
            r1 = r0
            goto L_0x0215
        L_0x0205:
            if (r5 == 0) goto L_0x0211
            io.netty.util.internal.logging.InternalLogger r0 = logger     // Catch:{ all -> 0x020f }
            java.lang.String r1 = "System property 'io.netty.handler.ssl.openssl.useKeyManagerFactory' is deprecated and will be ignored when using BoringSSL"
            r0.info((java.lang.String) r1)     // Catch:{ all -> 0x020f }
            goto L_0x0211
        L_0x020f:
            r1 = 1
            goto L_0x0215
        L_0x0211:
            r0 = 1
        L_0x0212:
            r1 = r0
            goto L_0x021c
        L_0x0214:
            r1 = 0
        L_0x0215:
            io.netty.util.internal.logging.InternalLogger r0 = logger     // Catch:{ Error -> 0x022e, all -> 0x0228 }
            java.lang.String r5 = "Failed to get useKeyManagerFactory system property."
            r0.debug((java.lang.String) r5)     // Catch:{ Error -> 0x022e, all -> 0x0228 }
        L_0x021c:
            r3.release()     // Catch:{ all -> 0x0222 }
            r5 = 1
            goto L_0x027a
        L_0x0222:
            r0 = move-exception
            r21 = r2
            r5 = 1
            goto L_0x0382
        L_0x0228:
            r0 = move-exception
            r21 = r2
            r5 = 1
            goto L_0x036f
        L_0x022e:
            r5 = 1
            goto L_0x0270
        L_0x0230:
            r0 = move-exception
            r21 = r2
            goto L_0x0262
        L_0x0234:
            r0 = move-exception
            r21 = r2
            r30 = r24
            goto L_0x0262
        L_0x023a:
            r30 = r24
            goto L_0x026e
        L_0x023d:
            r0 = move-exception
            r21 = r2
            r8 = r24
            r30 = r8
            goto L_0x0262
        L_0x0245:
            r8 = r24
            r30 = r8
            goto L_0x026e
        L_0x024a:
            r0 = move-exception
            r21 = r2
            r8 = r24
            r28 = r8
            goto L_0x0260
        L_0x0252:
            r8 = r24
            r28 = r8
            goto L_0x026c
        L_0x0257:
            r0 = move-exception
            r21 = r2
            r8 = r24
            r26 = r8
            r28 = r26
        L_0x0260:
            r30 = r28
        L_0x0262:
            r1 = 0
            r5 = 0
            goto L_0x036f
        L_0x0266:
            r8 = r24
            r26 = r8
            r28 = r26
        L_0x026c:
            r30 = r28
        L_0x026e:
            r1 = 0
            r5 = 0
        L_0x0270:
            io.netty.util.internal.logging.InternalLogger r0 = logger     // Catch:{ all -> 0x036c }
            java.lang.String r15 = "KeyManagerFactory not supported."
            r0.debug((java.lang.String) r15)     // Catch:{ all -> 0x036c }
            r3.release()     // Catch:{ all -> 0x0368 }
        L_0x027a:
            io.netty.internal.tcnative.SSL.freeSSL(r22)     // Catch:{ all -> 0x0362 }
            int r0 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x0288
            io.netty.internal.tcnative.SSL.freeBIO(r26)     // Catch:{ all -> 0x0285 }
            goto L_0x0288
        L_0x0285:
            r0 = move-exception
            goto L_0x03af
        L_0x0288:
            int r0 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x028f
            io.netty.internal.tcnative.SSL.freeBIO(r8)     // Catch:{ all -> 0x0285 }
        L_0x028f:
            int r0 = (r28 > r24 ? 1 : (r28 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x0296
            io.netty.internal.tcnative.SSL.freeX509Chain(r28)     // Catch:{ all -> 0x0285 }
        L_0x0296:
            int r0 = (r30 > r24 ? 1 : (r30 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x029d
            io.netty.internal.tcnative.SSL.freePrivateKey(r30)     // Catch:{ all -> 0x0285 }
        L_0x029d:
            java.lang.String r0 = "jdk.tls.namedGroups"
            r3 = 0
            java.lang.String r0 = io.netty.util.internal.SystemPropertyUtil.get(r0, r3)     // Catch:{ all -> 0x0362 }
            if (r0 == 0) goto L_0x034e
            java.lang.String r3 = ","
            java.lang.String[] r0 = r0.split(r3)     // Catch:{ all -> 0x0362 }
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet     // Catch:{ all -> 0x0362 }
            int r8 = r0.length     // Catch:{ all -> 0x0362 }
            r3.<init>(r8)     // Catch:{ all -> 0x0362 }
            java.util.LinkedHashSet r8 = new java.util.LinkedHashSet     // Catch:{ all -> 0x0362 }
            int r9 = r0.length     // Catch:{ all -> 0x0362 }
            r8.<init>(r9)     // Catch:{ all -> 0x0362 }
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet     // Catch:{ all -> 0x0362 }
            r9.<init>()     // Catch:{ all -> 0x0362 }
            int r15 = r0.length     // Catch:{ all -> 0x0362 }
        L_0x02be:
            if (r6 >= r15) goto L_0x02ec
            r7 = r0[r6]     // Catch:{ all -> 0x0362 }
            r19 = r0
            java.lang.String r0 = io.netty.handler.ssl.GroupsConverter.toOpenSsl(r7)     // Catch:{ all -> 0x0362 }
            r20 = r1
            r21 = r2
            r1 = 1
            java.lang.String[] r2 = new java.lang.String[r1]     // Catch:{ all -> 0x034a }
            r1 = 0
            r2[r1] = r0     // Catch:{ all -> 0x034a }
            boolean r1 = io.netty.internal.tcnative.SSLContext.setCurvesList(r13, r2)     // Catch:{ all -> 0x034a }
            if (r1 == 0) goto L_0x02df
            r8.add(r0)     // Catch:{ all -> 0x034a }
            r3.add(r7)     // Catch:{ all -> 0x034a }
            goto L_0x02e2
        L_0x02df:
            r9.add(r7)     // Catch:{ all -> 0x034a }
        L_0x02e2:
            int r6 = r6 + 1
            r0 = r19
            r1 = r20
            r2 = r21
            r7 = 1
            goto L_0x02be
        L_0x02ec:
            r20 = r1
            r21 = r2
            boolean r0 = r3.isEmpty()     // Catch:{ all -> 0x034a }
            if (r0 == 0) goto L_0x0311
            io.netty.util.internal.logging.InternalLogger r0 = logger     // Catch:{ all -> 0x030e }
            java.lang.String r1 = "All configured namedGroups are not supported: {}. Use default: {}."
            java.lang.String[] r2 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x030e }
            java.lang.Object[] r2 = r9.toArray(r2)     // Catch:{ all -> 0x030e }
            java.lang.String r2 = java.util.Arrays.toString(r2)     // Catch:{ all -> 0x030e }
            java.lang.String[] r3 = DEFAULT_NAMED_GROUPS     // Catch:{ all -> 0x030e }
            java.lang.String r3 = java.util.Arrays.toString(r3)     // Catch:{ all -> 0x030e }
            r0.info(r1, r2, r3)     // Catch:{ all -> 0x030e }
            goto L_0x0352
        L_0x030e:
            r0 = move-exception
            r11 = r12
            goto L_0x034b
        L_0x0311:
            java.lang.String[] r0 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x034a }
            java.lang.Object[] r0 = r3.toArray(r0)     // Catch:{ all -> 0x034a }
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch:{ all -> 0x034a }
            boolean r1 = r9.isEmpty()     // Catch:{ all -> 0x034a }
            if (r1 == 0) goto L_0x032b
            io.netty.util.internal.logging.InternalLogger r1 = logger     // Catch:{ all -> 0x034a }
            java.lang.String r2 = "Using configured namedGroups -D 'jdk.tls.namedGroup': {} "
            java.lang.String r0 = java.util.Arrays.toString(r0)     // Catch:{ all -> 0x034a }
            r1.info((java.lang.String) r2, (java.lang.Object) r0)     // Catch:{ all -> 0x034a }
            goto L_0x0340
        L_0x032b:
            io.netty.util.internal.logging.InternalLogger r1 = logger     // Catch:{ all -> 0x034a }
            java.lang.String r2 = "Using supported configured namedGroups: {}. Unsupported namedGroups: {}. "
            java.lang.String r0 = java.util.Arrays.toString(r0)     // Catch:{ all -> 0x034a }
            java.lang.String[] r3 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x034a }
            java.lang.Object[] r3 = r9.toArray(r3)     // Catch:{ all -> 0x034a }
            java.lang.String r3 = java.util.Arrays.toString(r3)     // Catch:{ all -> 0x034a }
            r1.info(r2, r0, r3)     // Catch:{ all -> 0x034a }
        L_0x0340:
            java.lang.String[] r0 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x034a }
            java.lang.Object[] r0 = r8.toArray(r0)     // Catch:{ all -> 0x034a }
            r12 = r0
            java.lang.String[] r12 = (java.lang.String[]) r12     // Catch:{ all -> 0x034a }
            goto L_0x0352
        L_0x034a:
            r0 = move-exception
        L_0x034b:
            r1 = r20
            goto L_0x03a3
        L_0x034e:
            r20 = r1
            r21 = r2
        L_0x0352:
            r11 = r12
            io.netty.internal.tcnative.SSLContext.free(r13)     // Catch:{ Exception -> 0x035c }
            r1 = r20
            r2 = r21
            goto L_0x03c0
        L_0x035c:
            r0 = move-exception
            r1 = r20
            r2 = r21
            goto L_0x03b9
        L_0x0362:
            r0 = move-exception
            r20 = r1
            r21 = r2
            goto L_0x03af
        L_0x0368:
            r0 = move-exception
            r21 = r2
            goto L_0x0382
        L_0x036c:
            r0 = move-exception
            r21 = r2
        L_0x036f:
            r3.release()     // Catch:{ all -> 0x0373 }
            throw r0     // Catch:{ all -> 0x0373 }
        L_0x0373:
            r0 = move-exception
            goto L_0x0382
        L_0x0375:
            r0 = move-exception
            r21 = r2
            r8 = r24
            r26 = r8
            r28 = r26
            r30 = r28
            r1 = 0
            r5 = 0
        L_0x0382:
            io.netty.internal.tcnative.SSL.freeSSL(r22)     // Catch:{ all -> 0x03a2 }
            int r2 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1))
            if (r2 == 0) goto L_0x038c
            io.netty.internal.tcnative.SSL.freeBIO(r26)     // Catch:{ all -> 0x03a2 }
        L_0x038c:
            int r2 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1))
            if (r2 == 0) goto L_0x0393
            io.netty.internal.tcnative.SSL.freeBIO(r8)     // Catch:{ all -> 0x03a2 }
        L_0x0393:
            int r2 = (r28 > r24 ? 1 : (r28 == r24 ? 0 : -1))
            if (r2 == 0) goto L_0x039a
            io.netty.internal.tcnative.SSL.freeX509Chain(r28)     // Catch:{ all -> 0x03a2 }
        L_0x039a:
            int r2 = (r30 > r24 ? 1 : (r30 == r24 ? 0 : -1))
            if (r2 == 0) goto L_0x03a1
            io.netty.internal.tcnative.SSL.freePrivateKey(r30)     // Catch:{ all -> 0x03a2 }
        L_0x03a1:
            throw r0     // Catch:{ all -> 0x03a2 }
        L_0x03a2:
            r0 = move-exception
        L_0x03a3:
            r2 = r21
            goto L_0x03af
        L_0x03a6:
            r0 = move-exception
            r21 = r2
            r1 = 0
            goto L_0x03ae
        L_0x03ab:
            r0 = move-exception
            r1 = 0
            r2 = 0
        L_0x03ae:
            r5 = 0
        L_0x03af:
            io.netty.internal.tcnative.SSLContext.free(r13)     // Catch:{ Exception -> 0x03b3 }
            throw r0     // Catch:{ Exception -> 0x03b3 }
        L_0x03b3:
            r0 = move-exception
            goto L_0x03b9
        L_0x03b5:
            r0 = move-exception
            r1 = 0
            r2 = 0
            r5 = 0
        L_0x03b9:
            io.netty.util.internal.logging.InternalLogger r3 = logger
            java.lang.String r6 = "Failed to get the list of available OpenSSL cipher suites."
            r3.warn((java.lang.String) r6, (java.lang.Throwable) r0)
        L_0x03c0:
            NAMED_GROUPS = r11
            java.util.Set r0 = java.util.Collections.unmodifiableSet(r10)
            AVAILABLE_OPENSSL_CIPHER_SUITES = r0
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet
            int r6 = r0.size()
            r7 = 2
            int r6 = r6 * 2
            r3.<init>(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x03d8:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x0401
            java.lang.Object r6 = r0.next()
            java.lang.String r6 = (java.lang.String) r6
            boolean r7 = io.netty.handler.ssl.SslUtils.isTLSv13Cipher(r6)
            if (r7 != 0) goto L_0x03fd
            java.lang.String r7 = "TLS"
            java.lang.String r7 = io.netty.handler.ssl.CipherSuiteConverter.toJava(r6, r7)
            r3.add(r7)
            java.lang.String r7 = "SSL"
            java.lang.String r6 = io.netty.handler.ssl.CipherSuiteConverter.toJava(r6, r7)
            r3.add(r6)
            goto L_0x03d8
        L_0x03fd:
            r3.add(r6)
            goto L_0x03d8
        L_0x0401:
            java.lang.String[] r0 = io.netty.handler.ssl.SslUtils.DEFAULT_CIPHER_SUITES
            io.netty.handler.ssl.SslUtils.addIfSupported(r3, r4, r0)
            java.lang.String[] r0 = io.netty.handler.ssl.SslUtils.TLSV13_CIPHER_SUITES
            io.netty.handler.ssl.SslUtils.addIfSupported(r3, r4, r0)
            java.lang.String[] r0 = EXTRA_SUPPORTED_TLS_1_3_CIPHERS
            io.netty.handler.ssl.SslUtils.addIfSupported(r3, r4, r0)
            io.netty.handler.ssl.SslUtils.useFallbackCiphersIfDefaultIsEmpty((java.util.List<java.lang.String>) r4, (java.lang.Iterable<java.lang.String>) r3)
            java.util.List r0 = java.util.Collections.unmodifiableList(r4)
            DEFAULT_CIPHERS = r0
            java.util.Set r3 = java.util.Collections.unmodifiableSet(r3)
            AVAILABLE_JAVA_CIPHER_SUITES = r3
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            java.util.Set<java.lang.String> r6 = AVAILABLE_OPENSSL_CIPHER_SUITES
            int r7 = r6.size()
            int r8 = r3.size()
            int r7 = r7 + r8
            r4.<init>(r7)
            r4.addAll(r6)
            r4.addAll(r3)
            AVAILABLE_CIPHER_SUITES = r4
            SUPPORTS_KEYMANAGER_FACTORY = r5
            USE_KEYMANAGER_FACTORY = r1
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r3 = 6
            r1.<init>(r3)
            java.lang.String r3 = "SSLv2Hello"
            r1.add(r3)
            int r3 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv2
            r4 = 1
            boolean r3 = doesSupportProtocol(r4, r3)
            if (r3 == 0) goto L_0x0454
            java.lang.String r3 = "SSLv2"
            r1.add(r3)
        L_0x0454:
            int r3 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv3
            r4 = 2
            boolean r3 = doesSupportProtocol(r4, r3)
            if (r3 == 0) goto L_0x0462
            java.lang.String r3 = "SSLv3"
            r1.add(r3)
        L_0x0462:
            int r3 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1
            r4 = 4
            boolean r3 = doesSupportProtocol(r4, r3)
            if (r3 == 0) goto L_0x0470
            java.lang.String r3 = "TLSv1"
            r1.add(r3)
        L_0x0470:
            r3 = 8
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_1
            boolean r3 = doesSupportProtocol(r3, r4)
            if (r3 == 0) goto L_0x047f
            java.lang.String r3 = "TLSv1.1"
            r1.add(r3)
        L_0x047f:
            r3 = 16
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_2
            boolean r3 = doesSupportProtocol(r3, r4)
            if (r3 == 0) goto L_0x048e
            java.lang.String r3 = "TLSv1.2"
            r1.add(r3)
        L_0x048e:
            if (r2 == 0) goto L_0x04a3
            r2 = 32
            int r3 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_3
            boolean r2 = doesSupportProtocol(r2, r3)
            if (r2 == 0) goto L_0x04a3
            java.lang.String r2 = "TLSv1.3"
            r1.add(r2)
            r2 = 1
            TLSV13_SUPPORTED = r2
            goto L_0x04a7
        L_0x04a3:
            r2 = 1
            r3 = 0
            TLSV13_SUPPORTED = r3
        L_0x04a7:
            java.util.Set r1 = java.util.Collections.unmodifiableSet(r1)
            SUPPORTED_PROTOCOLS_SET = r1
            boolean r3 = doesSupportOcsp()
            SUPPORTS_OCSP = r3
            io.netty.util.internal.logging.InternalLogger r3 = logger
            boolean r4 = r3.isDebugEnabled()
            if (r4 == 0) goto L_0x04c5
            java.lang.String r4 = "Supported protocols (OpenSSL): {} "
            r3.debug((java.lang.String) r4, (java.lang.Object) r1)
            java.lang.String r1 = "Default cipher suites (OpenSSL): {}"
            r3.debug((java.lang.String) r1, (java.lang.Object) r0)
        L_0x04c5:
            java.lang.String r0 = "-----BEGIN CERTIFICATE-----\nMIICrjCCAZagAwIBAgIIdSvQPv1QAZQwDQYJKoZIhvcNAQELBQAwFjEUMBIGA1UEAxMLZXhhbXBs\nZS5jb20wIBcNMTgwNDA2MjIwNjU5WhgPOTk5OTEyMzEyMzU5NTlaMBYxFDASBgNVBAMTC2V4YW1w\nbGUuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAggbWsmDQ6zNzRZ5AW8E3eoGl\nqWvOBDb5Fs1oBRrVQHuYmVAoaqwDzXYJ0LOwa293AgWEQ1jpcbZ2hpoYQzqEZBTLnFhMrhRFlH6K\nbJND8Y33kZ/iSVBBDuGbdSbJShlM+4WwQ9IAso4MZ4vW3S1iv5fGGpLgbtXRmBf/RU8omN0Gijlv\nWlLWHWijLN8xQtySFuBQ7ssW8RcKAary3pUm6UUQB+Co6lnfti0Tzag8PgjhAJq2Z3wbsGRnP2YS\nvYoaK6qzmHXRYlp/PxrjBAZAmkLJs4YTm/XFF+fkeYx4i9zqHbyone5yerRibsHaXZWLnUL+rFoe\nMdKvr0VS3sGmhQIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQADQi441pKmXf9FvUV5EHU4v8nJT9Iq\nyqwsKwXnr7AsUlDGHBD7jGrjAXnG5rGxuNKBQ35wRxJATKrUtyaquFUL6H8O6aGQehiFTk6zmPbe\n12Gu44vqqTgIUxnv3JQJiox8S2hMxsSddpeCmSdvmalvD6WG4NthH6B9ZaBEiep1+0s0RUaBYn73\nI7CCUaAtbjfR6pcJjrFk5ei7uwdQZFSJtkP2z8r7zfeANJddAKFlkaMWn7u+OIVuB4XPooWicObk\nNAHFtP65bocUYnDpTVdiyvn8DdqyZ/EO8n1bBKBzuSLplk2msW4pdgaFgY7Vw/0wzcFXfUXmL1uy\nG8sQD/wx\n-----END CERTIFICATE-----"
            java.nio.charset.Charset r1 = io.netty.util.CharsetUtil.US_ASCII     // Catch:{ CertificateException -> 0x04d2 }
            byte[] r0 = r0.getBytes(r1)     // Catch:{ CertificateException -> 0x04d2 }
            javax.security.cert.X509Certificate.getInstance(r0)     // Catch:{ CertificateException -> 0x04d2 }
            r6 = 1
            goto L_0x04d3
        L_0x04d2:
            r6 = 0
        L_0x04d3:
            JAVAX_CERTIFICATE_CREATION_SUPPORTED = r6
            goto L_0x050b
        L_0x04d6:
            java.util.List r0 = java.util.Collections.emptyList()
            DEFAULT_CIPHERS = r0
            java.util.Set r0 = java.util.Collections.emptySet()
            AVAILABLE_OPENSSL_CIPHER_SUITES = r0
            java.util.Set r0 = java.util.Collections.emptySet()
            AVAILABLE_JAVA_CIPHER_SUITES = r0
            java.util.Set r0 = java.util.Collections.emptySet()
            AVAILABLE_CIPHER_SUITES = r0
            r1 = 0
            SUPPORTS_KEYMANAGER_FACTORY = r1
            USE_KEYMANAGER_FACTORY = r1
            java.util.Set r0 = java.util.Collections.emptySet()
            SUPPORTED_PROTOCOLS_SET = r0
            SUPPORTS_OCSP = r1
            TLSV13_SUPPORTED = r1
            IS_BORINGSSL = r1
            java.lang.String[] r0 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS
            EXTRA_SUPPORTED_TLS_1_3_CIPHERS = r0
            EXTRA_SUPPORTED_TLS_1_3_CIPHERS_STRING = r2
            java.lang.String[] r0 = DEFAULT_NAMED_GROUPS
            NAMED_GROUPS = r0
            JAVAX_CERTIFICATE_CREATION_SUPPORTED = r1
        L_0x050b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSsl.<clinit>():void");
    }

    static String checkTls13Ciphers(InternalLogger internalLogger, String str) {
        boolean z;
        if (IS_BORINGSSL && !str.isEmpty()) {
            String[] strArr = EXTRA_SUPPORTED_TLS_1_3_CIPHERS;
            HashSet hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
            String[] split = str.split(":");
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                String str2 = split[i];
                if (!hashSet.isEmpty() && (hashSet.remove(str2) || hashSet.remove(CipherSuiteConverter.toJava(str2, "TLS")))) {
                    i++;
                }
            }
            z = true;
            if ((!hashSet.isEmpty()) || z) {
                if (internalLogger.isInfoEnabled()) {
                    StringBuilder sb = new StringBuilder(128);
                    for (String java2 : str.split(":")) {
                        sb.append(CipherSuiteConverter.toJava(java2, "TLS"));
                        sb.append(":");
                    }
                    sb.setLength(sb.length() - 1);
                    internalLogger.info("BoringSSL doesn't allow to enable or disable TLSv1.3 ciphers explicitly. Provided TLSv1.3 ciphers: '{}', default TLSv1.3 ciphers that will be used: '{}'.", sb, EXTRA_SUPPORTED_TLS_1_3_CIPHERS_STRING);
                }
                return EXTRA_SUPPORTED_TLS_1_3_CIPHERS_STRING;
            }
        }
        return str;
    }

    static boolean isSessionCacheSupported() {
        return ((long) version()) >= 269484032;
    }

    static X509Certificate selfSignedCertificate() throws CertificateException {
        return (X509Certificate) SslContext.X509_CERT_FACTORY.generateCertificate(new ByteArrayInputStream(CERT.getBytes(CharsetUtil.US_ASCII)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean doesSupportOcsp() {
        /*
            int r0 = version()
            long r0 = (long) r0
            r2 = 268443648(0x10002000, double:1.326287843E-315)
            r4 = 0
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 < 0) goto L_0x0039
            r0 = 16
            r1 = 1
            r2 = -1
            long r5 = io.netty.internal.tcnative.SSLContext.make(r0, r1)     // Catch:{ Exception -> 0x0030, all -> 0x0026 }
            io.netty.internal.tcnative.SSLContext.enableOcsp(r5, r4)     // Catch:{ Exception -> 0x0024, all -> 0x0022 }
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x0020
            io.netty.internal.tcnative.SSLContext.free(r5)
        L_0x0020:
            r4 = 1
            goto L_0x0039
        L_0x0022:
            r0 = move-exception
            goto L_0x0028
        L_0x0024:
            goto L_0x0032
        L_0x0026:
            r0 = move-exception
            r5 = r2
        L_0x0028:
            int r1 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x002f
            io.netty.internal.tcnative.SSLContext.free(r5)
        L_0x002f:
            throw r0
        L_0x0030:
            r5 = r2
        L_0x0032:
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x0039
            io.netty.internal.tcnative.SSLContext.free(r5)
        L_0x0039:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSsl.doesSupportOcsp():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean doesSupportProtocol(int r3, int r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r4 = 2
            long r3 = io.netty.internal.tcnative.SSLContext.make(r3, r4)     // Catch:{ Exception -> 0x0016, all -> 0x0014 }
            r0 = -1
            int r2 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r2 == 0) goto L_0x0012
            io.netty.internal.tcnative.SSLContext.free(r3)
        L_0x0012:
            r3 = 1
            return r3
        L_0x0014:
            r3 = move-exception
            throw r3
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.OpenSsl.doesSupportProtocol(int, int):boolean");
    }

    public static boolean isAvailable() {
        return UNAVAILABILITY_CAUSE == null;
    }

    @Deprecated
    public static boolean isAlpnSupported() {
        return ((long) version()) >= 268443648;
    }

    public static boolean isOcspSupported() {
        return SUPPORTS_OCSP;
    }

    public static int version() {
        if (isAvailable()) {
            return SSL.version();
        }
        return -1;
    }

    public static String versionString() {
        if (isAvailable()) {
            return SSL.versionString();
        }
        return null;
    }

    public static void ensureAvailability() {
        Throwable th = UNAVAILABILITY_CAUSE;
        if (th != null) {
            throw ((Error) new UnsatisfiedLinkError("failed to load the required native library").initCause(th));
        }
    }

    public static Throwable unavailabilityCause() {
        return UNAVAILABILITY_CAUSE;
    }

    @Deprecated
    public static Set<String> availableCipherSuites() {
        return availableOpenSslCipherSuites();
    }

    public static Set<String> availableOpenSslCipherSuites() {
        return AVAILABLE_OPENSSL_CIPHER_SUITES;
    }

    public static Set<String> availableJavaCipherSuites() {
        return AVAILABLE_JAVA_CIPHER_SUITES;
    }

    public static boolean isCipherSuiteAvailable(String str) {
        String openSsl = CipherSuiteConverter.toOpenSsl(str, IS_BORINGSSL);
        if (openSsl != null) {
            str = openSsl;
        }
        return AVAILABLE_OPENSSL_CIPHER_SUITES.contains(str);
    }

    public static boolean supportsKeyManagerFactory() {
        return SUPPORTS_KEYMANAGER_FACTORY;
    }

    @Deprecated
    public static boolean supportsHostnameValidation() {
        return isAvailable();
    }

    static boolean useKeyManagerFactory() {
        return USE_KEYMANAGER_FACTORY;
    }

    static long memoryAddress(ByteBuf byteBuf) {
        if (byteBuf.hasMemoryAddress()) {
            return byteBuf.memoryAddress();
        }
        return Buffer.address(byteBuf.internalNioBuffer(0, byteBuf.readableBytes()));
    }

    private OpenSsl() {
    }

    private static void loadTcNative() throws Exception {
        String normalizedOs = PlatformDependent.normalizedOs();
        String normalizedArch = PlatformDependent.normalizedArch();
        LinkedHashSet linkedHashSet = new LinkedHashSet(5);
        if ("linux".equals(normalizedOs)) {
            for (String str : PlatformDependent.normalizedLinuxClassifiers()) {
                linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch + "_" + str);
            }
            linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch);
            linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch + "_fedora");
        } else {
            linkedHashSet.add("netty_tcnative_" + normalizedOs + '_' + normalizedArch);
        }
        linkedHashSet.add("netty_tcnative_" + normalizedArch);
        linkedHashSet.add("netty_tcnative");
        NativeLibraryLoader.loadFirstAvailable(PlatformDependent.getClassLoader(SSLContext.class), (String[]) linkedHashSet.toArray(EmptyArrays.EMPTY_STRINGS));
    }

    private static boolean initializeTcNative(String str) throws Exception {
        return Library.initialize("provided", str);
    }

    static void releaseIfNeeded(ReferenceCounted referenceCounted) {
        if (referenceCounted.refCnt() > 0) {
            ReferenceCountUtil.safeRelease(referenceCounted);
        }
    }

    static boolean isTlsv13Supported() {
        return TLSV13_SUPPORTED;
    }

    static boolean isOptionSupported(SslContextOption<?> sslContextOption) {
        if (!isAvailable()) {
            return false;
        }
        if (sslContextOption == OpenSslContextOption.USE_TASKS) {
            return true;
        }
        if (!isBoringSSL()) {
            return false;
        }
        if (sslContextOption == OpenSslContextOption.ASYNC_PRIVATE_KEY_METHOD || sslContextOption == OpenSslContextOption.PRIVATE_KEY_METHOD || sslContextOption == OpenSslContextOption.CERTIFICATE_COMPRESSION_ALGORITHMS || sslContextOption == OpenSslContextOption.TLS_FALSE_START || sslContextOption == OpenSslContextOption.MAX_CERTIFICATE_LIST_BYTES) {
            return true;
        }
        return false;
    }

    private static Set<String> protocols(String str) {
        HashSet hashSet = null;
        String str2 = SystemPropertyUtil.get(str, (String) null);
        if (str2 != null) {
            hashSet = new HashSet();
            for (String trim : str2.split(AnsiRenderer.CODE_LIST_SEPARATOR)) {
                hashSet.add(trim.trim());
            }
        }
        return hashSet;
    }

    static String[] defaultProtocols(boolean z) {
        Set<String> set = z ? CLIENT_DEFAULT_PROTOCOLS : SERVER_DEFAULT_PROTOCOLS;
        if (set == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(set.size());
        for (String next : set) {
            if (SUPPORTED_PROTOCOLS_SET.contains(next)) {
                arrayList.add(next);
            }
        }
        return (String[]) arrayList.toArray(EmptyArrays.EMPTY_STRINGS);
    }

    static boolean isBoringSSL() {
        return IS_BORINGSSL;
    }
}
