package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.util.LazyX509Certificate;
import io.netty.internal.tcnative.AsyncSSLPrivateKeyMethod;
import io.netty.internal.tcnative.CertificateCompressionAlgo;
import io.netty.internal.tcnative.CertificateVerifier;
import io.netty.internal.tcnative.ResultCallback;
import io.netty.internal.tcnative.SSL;
import io.netty.internal.tcnative.SSLContext;
import io.netty.internal.tcnative.SSLPrivateKeyMethod;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

public abstract class ReferenceCountedOpenSslContext extends SslContext implements ReferenceCounted {
    static final boolean CLIENT_ENABLE_SESSION_CACHE = SystemPropertyUtil.getBoolean("io.netty.handler.ssl.openssl.sessionCacheClient", true);
    static final boolean CLIENT_ENABLE_SESSION_TICKET = SystemPropertyUtil.getBoolean("jdk.tls.client.enableSessionTicketExtension", false);
    static final boolean CLIENT_ENABLE_SESSION_TICKET_TLSV13 = SystemPropertyUtil.getBoolean("jdk.tls.client.enableSessionTicketExtension", true);
    private static final int DEFAULT_BIO_NON_APPLICATION_BUFFER_SIZE = Math.max(1, SystemPropertyUtil.getInt("io.netty.handler.ssl.openssl.bioNonApplicationBufferSize", 2048));
    private static final Integer DH_KEY_LENGTH;
    static final OpenSslApplicationProtocolNegotiator NONE_PROTOCOL_NEGOTIATOR = new OpenSslApplicationProtocolNegotiator() {
        public ApplicationProtocolConfig.Protocol protocol() {
            return ApplicationProtocolConfig.Protocol.NONE;
        }

        public List<String> protocols() {
            return Collections.emptyList();
        }

        public ApplicationProtocolConfig.SelectorFailureBehavior selectorFailureBehavior() {
            return ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL;
        }

        public ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior() {
            return ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT;
        }
    };
    static final boolean SERVER_ENABLE_SESSION_CACHE = SystemPropertyUtil.getBoolean("io.netty.handler.ssl.openssl.sessionCacheServer", true);
    static final boolean SERVER_ENABLE_SESSION_TICKET = SystemPropertyUtil.getBoolean("jdk.tls.server.enableSessionTicketExtension", false);
    static final boolean SERVER_ENABLE_SESSION_TICKET_TLSV13 = SystemPropertyUtil.getBoolean("jdk.tls.server.enableSessionTicketExtension", true);
    static final boolean USE_TASKS = SystemPropertyUtil.getBoolean("io.netty.handler.ssl.openssl.useTasks", true);
    protected static final int VERIFY_DEPTH = 10;
    private static final ResourceLeakDetector<ReferenceCountedOpenSslContext> leakDetector;
    /* access modifiers changed from: private */
    public static final InternalLogger logger;
    private final OpenSslApplicationProtocolNegotiator apn;
    private volatile int bioNonApplicationBufferSize = DEFAULT_BIO_NON_APPLICATION_BUFFER_SIZE;
    final ClientAuth clientAuth;
    protected long ctx;
    final ReadWriteLock ctxLock = new ReentrantReadWriteLock();
    final boolean enableOcsp;
    final OpenSslEngineMap engineMap = new DefaultOpenSslEngineMap();
    final Certificate[] keyCertChain;
    /* access modifiers changed from: private */
    public final ResourceLeakTracker<ReferenceCountedOpenSslContext> leak;
    private final int mode;
    final String[] protocols;
    private final AbstractReferenceCounted refCnt = new AbstractReferenceCounted() {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<ReferenceCountedOpenSslContext> cls = ReferenceCountedOpenSslContext.class;
        }

        public ReferenceCounted touch(Object obj) {
            if (ReferenceCountedOpenSslContext.this.leak != null) {
                ReferenceCountedOpenSslContext.this.leak.record(obj);
            }
            return ReferenceCountedOpenSslContext.this;
        }

        /* access modifiers changed from: protected */
        public void deallocate() {
            ReferenceCountedOpenSslContext.this.destroy();
            if (ReferenceCountedOpenSslContext.this.leak != null) {
                ReferenceCountedOpenSslContext.this.leak.close(ReferenceCountedOpenSslContext.this);
            }
        }
    };
    final boolean tlsFalseStart;
    private final List<String> unmodifiableCiphers;

    @Deprecated
    public boolean getRejectRemoteInitiatedRenegotiation() {
        return true;
    }

    public abstract OpenSslSessionContext sessionContext();

    /* JADX WARNING: Can't wrap try/catch for region: R(2:7|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r3 = logger;
        r3.debug("ReferenceCountedOpenSslContext supports -Djdk.tls.ephemeralDHKeySize={int}, but got: " + r2);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x006e */
    static {
        /*
            java.lang.String r0 = "ReferenceCountedOpenSslContext supports -Djdk.tls.ephemeralDHKeySize={int}, but got: "
            java.lang.Class<io.netty.handler.ssl.ReferenceCountedOpenSslContext> r1 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.class
            io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.internal.logging.InternalLoggerFactory.getInstance((java.lang.Class<?>) r1)
            logger = r2
            java.lang.String r2 = "io.netty.handler.ssl.openssl.bioNonApplicationBufferSize"
            r3 = 2048(0x800, float:2.87E-42)
            int r2 = io.netty.util.internal.SystemPropertyUtil.getInt(r2, r3)
            r3 = 1
            int r2 = java.lang.Math.max(r3, r2)
            DEFAULT_BIO_NON_APPLICATION_BUFFER_SIZE = r2
            java.lang.String r2 = "io.netty.handler.ssl.openssl.useTasks"
            boolean r2 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r2, r3)
            USE_TASKS = r2
            io.netty.util.ResourceLeakDetectorFactory r2 = io.netty.util.ResourceLeakDetectorFactory.instance()
            io.netty.util.ResourceLeakDetector r1 = r2.newResourceLeakDetector(r1)
            leakDetector = r1
            java.lang.String r1 = "jdk.tls.client.enableSessionTicketExtension"
            r2 = 0
            boolean r4 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r1, r2)
            CLIENT_ENABLE_SESSION_TICKET = r4
            boolean r1 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r1, r3)
            CLIENT_ENABLE_SESSION_TICKET_TLSV13 = r1
            java.lang.String r1 = "jdk.tls.server.enableSessionTicketExtension"
            boolean r2 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r1, r2)
            SERVER_ENABLE_SESSION_TICKET = r2
            boolean r1 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r1, r3)
            SERVER_ENABLE_SESSION_TICKET_TLSV13 = r1
            java.lang.String r1 = "io.netty.handler.ssl.openssl.sessionCacheServer"
            boolean r1 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r1, r3)
            SERVER_ENABLE_SESSION_CACHE = r1
            java.lang.String r1 = "io.netty.handler.ssl.openssl.sessionCacheClient"
            boolean r1 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r1, r3)
            CLIENT_ENABLE_SESSION_CACHE = r1
            io.netty.handler.ssl.ReferenceCountedOpenSslContext$2 r1 = new io.netty.handler.ssl.ReferenceCountedOpenSslContext$2
            r1.<init>()
            NONE_PROTOCOL_NEGOTIATOR = r1
            r1 = 0
            java.lang.String r2 = "jdk.tls.ephemeralDHKeySize"
            java.lang.String r2 = io.netty.util.internal.SystemPropertyUtil.get(r2)     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x007f
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch:{ NumberFormatException -> 0x006e }
            r1 = r0
            goto L_0x007f
        L_0x006e:
            io.netty.util.internal.logging.InternalLogger r3 = logger     // Catch:{ all -> 0x007f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x007f }
            r4.<init>(r0)     // Catch:{ all -> 0x007f }
            r4.append(r2)     // Catch:{ all -> 0x007f }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x007f }
            r3.debug((java.lang.String) r0)     // Catch:{ all -> 0x007f }
        L_0x007f:
            DH_KEY_LENGTH = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslContext.<clinit>():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: java.security.cert.Certificate[]} */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ReferenceCountedOpenSslContext(java.lang.Iterable<java.lang.String> r19, io.netty.handler.ssl.CipherSuiteFilter r20, io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator r21, int r22, java.security.cert.Certificate[] r23, io.netty.handler.ssl.ClientAuth r24, java.lang.String[] r25, boolean r26, boolean r27, boolean r28, java.util.Map.Entry<io.netty.handler.ssl.SslContextOption<?>, java.lang.Object>... r29) throws javax.net.ssl.SSLException {
        /*
            r18 = this;
            r1 = r18
            r0 = r22
            r2 = r27
            r3 = r29
            java.lang.String r4 = ""
            r5 = r26
            r1.<init>(r5)
            io.netty.handler.ssl.ReferenceCountedOpenSslContext$1 r5 = new io.netty.handler.ssl.ReferenceCountedOpenSslContext$1
            r5.<init>()
            r1.refCnt = r5
            io.netty.handler.ssl.ReferenceCountedOpenSslContext$DefaultOpenSslEngineMap r5 = new io.netty.handler.ssl.ReferenceCountedOpenSslContext$DefaultOpenSslEngineMap
            r6 = 0
            r5.<init>()
            r1.engineMap = r5
            java.util.concurrent.locks.ReentrantReadWriteLock r5 = new java.util.concurrent.locks.ReentrantReadWriteLock
            r5.<init>()
            r1.ctxLock = r5
            int r5 = DEFAULT_BIO_NON_APPLICATION_BUFFER_SIZE
            r1.bioNonApplicationBufferSize = r5
            io.netty.handler.ssl.OpenSsl.ensureAvailability()
            if (r2 == 0) goto L_0x003d
            boolean r5 = io.netty.handler.ssl.OpenSsl.isOcspSupported()
            if (r5 == 0) goto L_0x0035
            goto L_0x003d
        L_0x0035:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "OCSP is not supported."
            r0.<init>(r2)
            throw r0
        L_0x003d:
            r5 = 1
            if (r0 == r5) goto L_0x004b
            if (r0 != 0) goto L_0x0043
            goto L_0x004b
        L_0x0043:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "mode most be either SSL.SSL_MODE_SERVER or SSL.SSL_MODE_CLIENT"
            r0.<init>(r2)
            throw r0
        L_0x004b:
            boolean r7 = USE_TASKS
            if (r3 == 0) goto L_0x00cf
            int r9 = r3.length
            r12 = r6
            r13 = r12
            r14 = r13
            r15 = r14
            r10 = 0
            r11 = 0
        L_0x0056:
            if (r10 >= r9) goto L_0x00d4
            r16 = r3[r10]
            java.lang.Object r17 = r16.getKey()
            r6 = r17
            io.netty.handler.ssl.SslContextOption r6 = (io.netty.handler.ssl.SslContextOption) r6
            io.netty.handler.ssl.OpenSslContextOption<java.lang.Boolean> r5 = io.netty.handler.ssl.OpenSslContextOption.TLS_FALSE_START
            if (r6 != r5) goto L_0x0071
            java.lang.Object r5 = r16.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r11 = r5.booleanValue()
            goto L_0x00ca
        L_0x0071:
            io.netty.handler.ssl.OpenSslContextOption<java.lang.Boolean> r5 = io.netty.handler.ssl.OpenSslContextOption.USE_TASKS
            if (r6 != r5) goto L_0x0081
            java.lang.Object r5 = r16.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r7 = r5
            goto L_0x00ca
        L_0x0081:
            io.netty.handler.ssl.OpenSslContextOption<io.netty.handler.ssl.OpenSslPrivateKeyMethod> r5 = io.netty.handler.ssl.OpenSslContextOption.PRIVATE_KEY_METHOD
            if (r6 != r5) goto L_0x008d
            java.lang.Object r5 = r16.getValue()
            r12 = r5
            io.netty.handler.ssl.OpenSslPrivateKeyMethod r12 = (io.netty.handler.ssl.OpenSslPrivateKeyMethod) r12
            goto L_0x00ca
        L_0x008d:
            io.netty.handler.ssl.OpenSslContextOption<io.netty.handler.ssl.OpenSslAsyncPrivateKeyMethod> r5 = io.netty.handler.ssl.OpenSslContextOption.ASYNC_PRIVATE_KEY_METHOD
            if (r6 != r5) goto L_0x0099
            java.lang.Object r5 = r16.getValue()
            r13 = r5
            io.netty.handler.ssl.OpenSslAsyncPrivateKeyMethod r13 = (io.netty.handler.ssl.OpenSslAsyncPrivateKeyMethod) r13
            goto L_0x00ca
        L_0x0099:
            io.netty.handler.ssl.OpenSslContextOption<io.netty.handler.ssl.OpenSslCertificateCompressionConfig> r5 = io.netty.handler.ssl.OpenSslContextOption.CERTIFICATE_COMPRESSION_ALGORITHMS
            if (r6 != r5) goto L_0x00a5
            java.lang.Object r5 = r16.getValue()
            r14 = r5
            io.netty.handler.ssl.OpenSslCertificateCompressionConfig r14 = (io.netty.handler.ssl.OpenSslCertificateCompressionConfig) r14
            goto L_0x00ca
        L_0x00a5:
            io.netty.handler.ssl.OpenSslContextOption<java.lang.Integer> r5 = io.netty.handler.ssl.OpenSslContextOption.MAX_CERTIFICATE_LIST_BYTES
            if (r6 != r5) goto L_0x00b1
            java.lang.Object r5 = r16.getValue()
            r15 = r5
            java.lang.Integer r15 = (java.lang.Integer) r15
            goto L_0x00ca
        L_0x00b1:
            io.netty.util.internal.logging.InternalLogger r5 = logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "Skipping unsupported SslContextOption: "
            r6.<init>(r8)
            java.lang.Class<io.netty.handler.ssl.SslContextOption> r8 = io.netty.handler.ssl.SslContextOption.class
            java.lang.Object r8 = r16.getKey()
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r5.debug((java.lang.String) r6)
        L_0x00ca:
            int r10 = r10 + 1
            r5 = 1
            r6 = 0
            goto L_0x0056
        L_0x00cf:
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x00d4:
            if (r12 == 0) goto L_0x00e5
            if (r13 != 0) goto L_0x00d9
            goto L_0x00e5
        L_0x00d9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.Class<io.netty.handler.ssl.OpenSslAsyncPrivateKeyMethod> r2 = io.netty.handler.ssl.OpenSslAsyncPrivateKeyMethod.class
            java.lang.Class<io.netty.handler.ssl.OpenSslPrivateKeyMethod> r2 = io.netty.handler.ssl.OpenSslPrivateKeyMethod.class
            java.lang.String r2 = "You can either only use OpenSslAsyncPrivateKeyMethod or OpenSslPrivateKeyMethod"
            r0.<init>(r2)
            throw r0
        L_0x00e5:
            r1.tlsFalseStart = r11
            if (r28 == 0) goto L_0x00f0
            io.netty.util.ResourceLeakDetector<io.netty.handler.ssl.ReferenceCountedOpenSslContext> r3 = leakDetector
            io.netty.util.ResourceLeakTracker r3 = r3.track(r1)
            goto L_0x00f1
        L_0x00f0:
            r3 = 0
        L_0x00f1:
            r1.leak = r3
            r1.mode = r0
            boolean r3 = r18.isServer()
            if (r3 == 0) goto L_0x0106
            java.lang.String r3 = "clientAuth"
            r5 = r24
            java.lang.Object r3 = io.netty.util.internal.ObjectUtil.checkNotNull(r5, r3)
            io.netty.handler.ssl.ClientAuth r3 = (io.netty.handler.ssl.ClientAuth) r3
            goto L_0x0108
        L_0x0106:
            io.netty.handler.ssl.ClientAuth r3 = io.netty.handler.ssl.ClientAuth.NONE
        L_0x0108:
            r1.clientAuth = r3
            if (r25 != 0) goto L_0x0116
            if (r0 != 0) goto L_0x0110
            r3 = 1
            goto L_0x0111
        L_0x0110:
            r3 = 0
        L_0x0111:
            java.lang.String[] r3 = io.netty.handler.ssl.OpenSsl.defaultProtocols(r3)
            goto L_0x0118
        L_0x0116:
            r3 = r25
        L_0x0118:
            r1.protocols = r3
            r1.enableOcsp = r2
            if (r23 != 0) goto L_0x0120
            r6 = 0
            goto L_0x0127
        L_0x0120:
            java.lang.Object r3 = r23.clone()
            r6 = r3
            java.security.cert.Certificate[] r6 = (java.security.cert.Certificate[]) r6
        L_0x0127:
            r1.keyCertChain = r6
            java.lang.String r3 = "cipherFilter"
            r5 = r20
            java.lang.Object r3 = io.netty.util.internal.ObjectUtil.checkNotNull(r5, r3)
            io.netty.handler.ssl.CipherSuiteFilter r3 = (io.netty.handler.ssl.CipherSuiteFilter) r3
            java.util.List<java.lang.String> r5 = io.netty.handler.ssl.OpenSsl.DEFAULT_CIPHERS
            java.util.Set r6 = io.netty.handler.ssl.OpenSsl.availableJavaCipherSuites()
            r8 = r19
            java.lang.String[] r3 = r3.filterCipherSuites(r8, r5, r6)
            java.util.LinkedHashSet r5 = new java.util.LinkedHashSet
            int r6 = r3.length
            r5.<init>(r6)
            java.util.Collections.addAll(r5, r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r5)
            r1.unmodifiableCiphers = r3
            java.lang.String r5 = "apn"
            r6 = r21
            java.lang.Object r5 = io.netty.util.internal.ObjectUtil.checkNotNull(r6, r5)
            io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator r5 = (io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator) r5
            r1.apn = r5
            boolean r5 = io.netty.handler.ssl.OpenSsl.isTlsv13Supported()     // Catch:{ all -> 0x02fe }
            if (r5 == 0) goto L_0x0164
            r8 = 62
            goto L_0x0166
        L_0x0164:
            r8 = 30
        L_0x0166:
            long r8 = io.netty.internal.tcnative.SSLContext.make(r8, r0)     // Catch:{ Exception -> 0x02f5 }
            r1.ctx = r8     // Catch:{ Exception -> 0x02f5 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x02fe }
            r0.<init>()     // Catch:{ all -> 0x02fe }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x02fe }
            r8.<init>()     // Catch:{ all -> 0x02fe }
            boolean r9 = r3.isEmpty()     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            if (r9 == 0) goto L_0x018b
            long r8 = r1.ctx     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            r3 = 0
            io.netty.internal.tcnative.SSLContext.setCipherSuite(r8, r4, r3)     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            if (r5 == 0) goto L_0x01ae
            long r8 = r1.ctx     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            r3 = 1
            io.netty.internal.tcnative.SSLContext.setCipherSuite(r8, r4, r3)     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            goto L_0x01ae
        L_0x018b:
            boolean r4 = io.netty.handler.ssl.OpenSsl.isBoringSSL()     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            io.netty.handler.ssl.CipherSuiteConverter.convertToCipherStrings(r3, r0, r8, r4)     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            long r3 = r1.ctx     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            java.lang.String r9 = r0.toString()     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            r10 = 0
            io.netty.internal.tcnative.SSLContext.setCipherSuite(r3, r9, r10)     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            if (r5 == 0) goto L_0x01ae
            long r3 = r1.ctx     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            io.netty.util.internal.logging.InternalLogger r9 = logger     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            java.lang.String r8 = r8.toString()     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            java.lang.String r8 = io.netty.handler.ssl.OpenSsl.checkTls13Ciphers(r9, r8)     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
            r9 = 1
            io.netty.internal.tcnative.SSLContext.setCipherSuite(r3, r8, r9)     // Catch:{ SSLException -> 0x02f3, Exception -> 0x02d9 }
        L_0x01ae:
            long r3 = r1.ctx     // Catch:{ all -> 0x02fe }
            int r3 = io.netty.internal.tcnative.SSLContext.getOptions(r3)     // Catch:{ all -> 0x02fe }
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv2     // Catch:{ all -> 0x02fe }
            r3 = r3 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv3     // Catch:{ all -> 0x02fe }
            r3 = r3 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1     // Catch:{ all -> 0x02fe }
            r3 = r3 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_1     // Catch:{ all -> 0x02fe }
            r3 = r3 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_CIPHER_SERVER_PREFERENCE     // Catch:{ all -> 0x02fe }
            r3 = r3 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_COMPRESSION     // Catch:{ all -> 0x02fe }
            r3 = r3 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TICKET     // Catch:{ all -> 0x02fe }
            r3 = r3 | r4
            int r0 = r0.length()     // Catch:{ all -> 0x02fe }
            if (r0 != 0) goto L_0x01de
            int r0 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv2     // Catch:{ all -> 0x02fe }
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_SSLv3     // Catch:{ all -> 0x02fe }
            r0 = r0 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1     // Catch:{ all -> 0x02fe }
            r0 = r0 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_1     // Catch:{ all -> 0x02fe }
            r0 = r0 | r4
            int r4 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_2     // Catch:{ all -> 0x02fe }
            r0 = r0 | r4
            r3 = r3 | r0
        L_0x01de:
            if (r5 != 0) goto L_0x01e3
            int r0 = io.netty.internal.tcnative.SSL.SSL_OP_NO_TLSv1_3     // Catch:{ all -> 0x02fe }
            r3 = r3 | r0
        L_0x01e3:
            long r4 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setOptions(r4, r3)     // Catch:{ all -> 0x02fe }
            long r3 = r1.ctx     // Catch:{ all -> 0x02fe }
            int r0 = io.netty.internal.tcnative.SSLContext.getMode(r3)     // Catch:{ all -> 0x02fe }
            int r5 = io.netty.internal.tcnative.SSL.SSL_MODE_ACCEPT_MOVING_WRITE_BUFFER     // Catch:{ all -> 0x02fe }
            r0 = r0 | r5
            io.netty.internal.tcnative.SSLContext.setMode(r3, r0)     // Catch:{ all -> 0x02fe }
            java.lang.Integer r0 = DH_KEY_LENGTH     // Catch:{ all -> 0x02fe }
            if (r0 == 0) goto L_0x0201
            long r3 = r1.ctx     // Catch:{ all -> 0x02fe }
            int r0 = r0.intValue()     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setTmpDHLength(r3, r0)     // Catch:{ all -> 0x02fe }
        L_0x0201:
            java.util.List r0 = r21.protocols()     // Catch:{ all -> 0x02fe }
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x02fe }
            r4 = 3
            r5 = 2
            if (r3 != 0) goto L_0x024c
            java.lang.String[] r3 = io.netty.util.internal.EmptyArrays.EMPTY_STRINGS     // Catch:{ all -> 0x02fe }
            java.lang.Object[] r0 = r0.toArray(r3)     // Catch:{ all -> 0x02fe }
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.ApplicationProtocolConfig$SelectorFailureBehavior r3 = r21.selectorFailureBehavior()     // Catch:{ all -> 0x02fe }
            int r3 = opensslSelectorFailureBehavior(r3)     // Catch:{ all -> 0x02fe }
            int[] r8 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r6 = r21.protocol()     // Catch:{ all -> 0x02fe }
            int r6 = r6.ordinal()     // Catch:{ all -> 0x02fe }
            r6 = r8[r6]     // Catch:{ all -> 0x02fe }
            r8 = 1
            if (r6 == r8) goto L_0x0247
            if (r6 == r5) goto L_0x0241
            if (r6 != r4) goto L_0x023b
            long r8 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setNpnProtos(r8, r0, r3)     // Catch:{ all -> 0x02fe }
            long r8 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setAlpnProtos(r8, r0, r3)     // Catch:{ all -> 0x02fe }
            goto L_0x024c
        L_0x023b:
            java.lang.Error r0 = new java.lang.Error     // Catch:{ all -> 0x02fe }
            r0.<init>()     // Catch:{ all -> 0x02fe }
            throw r0     // Catch:{ all -> 0x02fe }
        L_0x0241:
            long r8 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setAlpnProtos(r8, r0, r3)     // Catch:{ all -> 0x02fe }
            goto L_0x024c
        L_0x0247:
            long r8 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setNpnProtos(r8, r0, r3)     // Catch:{ all -> 0x02fe }
        L_0x024c:
            if (r2 == 0) goto L_0x0257
            long r2 = r1.ctx     // Catch:{ all -> 0x02fe }
            boolean r0 = r18.isClient()     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.enableOcsp(r2, r0)     // Catch:{ all -> 0x02fe }
        L_0x0257:
            long r2 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setUseTasks(r2, r7)     // Catch:{ all -> 0x02fe }
            if (r12 == 0) goto L_0x026a
            long r2 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.ReferenceCountedOpenSslContext$PrivateKeyMethod r0 = new io.netty.handler.ssl.ReferenceCountedOpenSslContext$PrivateKeyMethod     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.OpenSslEngineMap r6 = r1.engineMap     // Catch:{ all -> 0x02fe }
            r0.<init>(r6, r12)     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setPrivateKeyMethod(r2, r0)     // Catch:{ all -> 0x02fe }
        L_0x026a:
            if (r13 == 0) goto L_0x0278
            long r2 = r1.ctx     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.ReferenceCountedOpenSslContext$AsyncPrivateKeyMethod r0 = new io.netty.handler.ssl.ReferenceCountedOpenSslContext$AsyncPrivateKeyMethod     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.OpenSslEngineMap r6 = r1.engineMap     // Catch:{ all -> 0x02fe }
            r0.<init>(r6, r13)     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setPrivateKeyMethod(r2, r0)     // Catch:{ all -> 0x02fe }
        L_0x0278:
            if (r14 == 0) goto L_0x02c6
            java.util.Iterator r0 = r14.iterator()     // Catch:{ all -> 0x02fe }
        L_0x027e:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x02fe }
            if (r2 == 0) goto L_0x02c6
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.OpenSslCertificateCompressionConfig$AlgorithmConfig r2 = (io.netty.handler.ssl.OpenSslCertificateCompressionConfig.AlgorithmConfig) r2     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.ReferenceCountedOpenSslContext$CompressionAlgorithm r3 = new io.netty.handler.ssl.ReferenceCountedOpenSslContext$CompressionAlgorithm     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.OpenSslEngineMap r6 = r1.engineMap     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.OpenSslCertificateCompressionAlgorithm r7 = r2.algorithm()     // Catch:{ all -> 0x02fe }
            r3.<init>(r6, r7)     // Catch:{ all -> 0x02fe }
            int[] r6 = io.netty.handler.ssl.ReferenceCountedOpenSslContext.AnonymousClass3.$SwitchMap$io$netty$handler$ssl$OpenSslCertificateCompressionConfig$AlgorithmMode     // Catch:{ all -> 0x02fe }
            io.netty.handler.ssl.OpenSslCertificateCompressionConfig$AlgorithmMode r2 = r2.mode()     // Catch:{ all -> 0x02fe }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x02fe }
            r2 = r6[r2]     // Catch:{ all -> 0x02fe }
            r6 = 1
            if (r2 == r6) goto L_0x02be
            if (r2 == r5) goto L_0x02b6
            if (r2 != r4) goto L_0x02b0
            long r7 = r1.ctx     // Catch:{ all -> 0x02fe }
            int r2 = io.netty.internal.tcnative.SSL.SSL_CERT_COMPRESSION_DIRECTION_BOTH     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.addCertificateCompressionAlgorithm(r7, r2, r3)     // Catch:{ all -> 0x02fe }
            goto L_0x027e
        L_0x02b0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x02fe }
            r0.<init>()     // Catch:{ all -> 0x02fe }
            throw r0     // Catch:{ all -> 0x02fe }
        L_0x02b6:
            long r7 = r1.ctx     // Catch:{ all -> 0x02fe }
            int r2 = io.netty.internal.tcnative.SSL.SSL_CERT_COMPRESSION_DIRECTION_COMPRESS     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.addCertificateCompressionAlgorithm(r7, r2, r3)     // Catch:{ all -> 0x02fe }
            goto L_0x027e
        L_0x02be:
            long r7 = r1.ctx     // Catch:{ all -> 0x02fe }
            int r2 = io.netty.internal.tcnative.SSL.SSL_CERT_COMPRESSION_DIRECTION_DECOMPRESS     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.addCertificateCompressionAlgorithm(r7, r2, r3)     // Catch:{ all -> 0x02fe }
            goto L_0x027e
        L_0x02c6:
            if (r15 == 0) goto L_0x02d1
            long r2 = r1.ctx     // Catch:{ all -> 0x02fe }
            int r0 = r15.intValue()     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setMaxCertList(r2, r0)     // Catch:{ all -> 0x02fe }
        L_0x02d1:
            long r2 = r1.ctx     // Catch:{ all -> 0x02fe }
            java.lang.String[] r0 = io.netty.handler.ssl.OpenSsl.NAMED_GROUPS     // Catch:{ all -> 0x02fe }
            io.netty.internal.tcnative.SSLContext.setCurvesList(r2, r0)     // Catch:{ all -> 0x02fe }
            return
        L_0x02d9:
            r0 = move-exception
            javax.net.ssl.SSLException r2 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x02fe }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02fe }
            r3.<init>()     // Catch:{ all -> 0x02fe }
            java.lang.String r4 = "failed to set cipher suite: "
            r3.append(r4)     // Catch:{ all -> 0x02fe }
            java.util.List<java.lang.String> r4 = r1.unmodifiableCiphers     // Catch:{ all -> 0x02fe }
            r3.append(r4)     // Catch:{ all -> 0x02fe }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02fe }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x02fe }
            throw r2     // Catch:{ all -> 0x02fe }
        L_0x02f3:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x02fe }
        L_0x02f5:
            r0 = move-exception
            javax.net.ssl.SSLException r2 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x02fe }
            java.lang.String r3 = "failed to create an SSL_CTX"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x02fe }
            throw r2     // Catch:{ all -> 0x02fe }
        L_0x02fe:
            r0 = move-exception
            r18.release()
            goto L_0x0304
        L_0x0303:
            throw r0
        L_0x0304:
            goto L_0x0303
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslContext.<init>(java.lang.Iterable, io.netty.handler.ssl.CipherSuiteFilter, io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator, int, java.security.cert.Certificate[], io.netty.handler.ssl.ClientAuth, java.lang.String[], boolean, boolean, boolean, java.util.Map$Entry[]):void");
    }

    private static int opensslSelectorFailureBehavior(ApplicationProtocolConfig.SelectorFailureBehavior selectorFailureBehavior) {
        int i = AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectorFailureBehavior[selectorFailureBehavior.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new Error();
    }

    public final List<String> cipherSuites() {
        return this.unmodifiableCiphers;
    }

    public ApplicationProtocolNegotiator applicationProtocolNegotiator() {
        return this.apn;
    }

    public final boolean isClient() {
        return this.mode == 0;
    }

    public final SSLEngine newEngine(ByteBufAllocator byteBufAllocator, String str, int i) {
        return newEngine0(byteBufAllocator, str, i, true);
    }

    /* access modifiers changed from: protected */
    public final SslHandler newHandler(ByteBufAllocator byteBufAllocator, boolean z) {
        return new SslHandler(newEngine0(byteBufAllocator, (String) null, -1, false), z);
    }

    /* access modifiers changed from: protected */
    public final SslHandler newHandler(ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        return new SslHandler(newEngine0(byteBufAllocator, str, i, false), z);
    }

    /* access modifiers changed from: protected */
    public SslHandler newHandler(ByteBufAllocator byteBufAllocator, boolean z, Executor executor) {
        return new SslHandler(newEngine0(byteBufAllocator, (String) null, -1, false), z, executor);
    }

    /* access modifiers changed from: protected */
    public SslHandler newHandler(ByteBufAllocator byteBufAllocator, String str, int i, boolean z, Executor executor) {
        return new SslHandler(newEngine0(byteBufAllocator, str, i, false), executor);
    }

    /* access modifiers changed from: package-private */
    public SSLEngine newEngine0(ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        return new ReferenceCountedOpenSslEngine(this, byteBufAllocator, str, i, z, true);
    }

    public final SSLEngine newEngine(ByteBufAllocator byteBufAllocator) {
        return newEngine(byteBufAllocator, (String) null, -1);
    }

    @Deprecated
    public final long context() {
        return sslCtxPointer();
    }

    @Deprecated
    public final OpenSslSessionStats stats() {
        return sessionContext().stats();
    }

    @Deprecated
    public void setRejectRemoteInitiatedRenegotiation(boolean z) {
        if (!z) {
            throw new UnsupportedOperationException("Renegotiation is not supported");
        }
    }

    public void setBioNonApplicationBufferSize(int i) {
        this.bioNonApplicationBufferSize = ObjectUtil.checkPositiveOrZero(i, "bioNonApplicationBufferSize");
    }

    public int getBioNonApplicationBufferSize() {
        return this.bioNonApplicationBufferSize;
    }

    @Deprecated
    public final void setTicketKeys(byte[] bArr) {
        sessionContext().setTicketKeys(bArr);
    }

    @Deprecated
    public final long sslCtxPointer() {
        Lock readLock = this.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.getSslCtx(this.ctx);
        } finally {
            readLock.unlock();
        }
    }

    @Deprecated
    public final void setPrivateKeyMethod(OpenSslPrivateKeyMethod openSslPrivateKeyMethod) {
        ObjectUtil.checkNotNull(openSslPrivateKeyMethod, "method");
        Lock writeLock = this.ctxLock.writeLock();
        writeLock.lock();
        try {
            SSLContext.setPrivateKeyMethod(this.ctx, new PrivateKeyMethod(this.engineMap, openSslPrivateKeyMethod));
        } finally {
            writeLock.unlock();
        }
    }

    @Deprecated
    public final void setUseTasks(boolean z) {
        Lock writeLock = this.ctxLock.writeLock();
        writeLock.lock();
        try {
            SSLContext.setUseTasks(this.ctx, z);
        } finally {
            writeLock.unlock();
        }
    }

    /* access modifiers changed from: private */
    public void destroy() {
        Lock writeLock = this.ctxLock.writeLock();
        writeLock.lock();
        try {
            long j = this.ctx;
            if (j != 0) {
                if (this.enableOcsp) {
                    SSLContext.disableOcsp(j);
                }
                SSLContext.free(this.ctx);
                this.ctx = 0;
                OpenSslSessionContext sessionContext = sessionContext();
                if (sessionContext != null) {
                    sessionContext.destroy();
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    protected static X509Certificate[] certificates(byte[][] bArr) {
        int length = bArr.length;
        X509Certificate[] x509CertificateArr = new X509Certificate[length];
        for (int i = 0; i < length; i++) {
            x509CertificateArr[i] = new LazyX509Certificate(bArr[i]);
        }
        return x509CertificateArr;
    }

    protected static X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
        for (X509TrustManager x509TrustManager : trustManagerArr) {
            if (x509TrustManager instanceof X509TrustManager) {
                X509TrustManager x509TrustManager2 = x509TrustManager;
                if (PlatformDependent.javaVersion() < 7) {
                    return x509TrustManager2;
                }
                X509TrustManager wrapIfNeeded = OpenSslX509TrustManagerWrapper.wrapIfNeeded(x509TrustManager2);
                return useExtendedTrustManager(wrapIfNeeded) ? new EnhancingX509ExtendedTrustManager(wrapIfNeeded) : wrapIfNeeded;
            }
        }
        throw new IllegalStateException("no X509TrustManager found");
    }

    protected static X509KeyManager chooseX509KeyManager(KeyManager[] keyManagerArr) {
        for (X509KeyManager x509KeyManager : keyManagerArr) {
            if (x509KeyManager instanceof X509KeyManager) {
                return x509KeyManager;
            }
        }
        throw new IllegalStateException("no X509KeyManager found");
    }

    static OpenSslApplicationProtocolNegotiator toNegotiator(ApplicationProtocolConfig applicationProtocolConfig) {
        if (applicationProtocolConfig == null) {
            return NONE_PROTOCOL_NEGOTIATOR;
        }
        int i = AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol[applicationProtocolConfig.protocol().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            int i2 = AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectedListenerFailureBehavior[applicationProtocolConfig.selectedListenerFailureBehavior().ordinal()];
            if (i2 == 1 || i2 == 2) {
                int i3 = AnonymousClass3.$SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectorFailureBehavior[applicationProtocolConfig.selectorFailureBehavior().ordinal()];
                if (i3 == 1 || i3 == 2) {
                    return new OpenSslDefaultApplicationProtocolNegotiator(applicationProtocolConfig);
                }
                throw new UnsupportedOperationException("OpenSSL provider does not support " + applicationProtocolConfig.selectorFailureBehavior() + " behavior");
            }
            throw new UnsupportedOperationException("OpenSSL provider does not support " + applicationProtocolConfig.selectedListenerFailureBehavior() + " behavior");
        } else if (i == 4) {
            return NONE_PROTOCOL_NEGOTIATOR;
        } else {
            throw new Error();
        }
    }

    /* renamed from: io.netty.handler.ssl.ReferenceCountedOpenSslContext$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectedListenerFailureBehavior;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectorFailureBehavior;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$OpenSslCertificateCompressionConfig$AlgorithmMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|15|16|(2:17|18)|19|(2:21|22)|23|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|12|13|15|16|(2:17|18)|19|(2:21|22)|23|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|18|19|21|22|23|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        static {
            /*
                io.netty.handler.ssl.ApplicationProtocolConfig$SelectedListenerFailureBehavior[] r0 = io.netty.handler.ssl.ApplicationProtocolConfig.SelectedListenerFailureBehavior.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectedListenerFailureBehavior = r0
                r1 = 1
                io.netty.handler.ssl.ApplicationProtocolConfig$SelectedListenerFailureBehavior r2 = io.netty.handler.ssl.ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectedListenerFailureBehavior     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.ssl.ApplicationProtocolConfig$SelectedListenerFailureBehavior r3 = io.netty.handler.ssl.ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                io.netty.handler.ssl.ApplicationProtocolConfig$SelectorFailureBehavior[] r2 = io.netty.handler.ssl.ApplicationProtocolConfig.SelectorFailureBehavior.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectorFailureBehavior = r2
                io.netty.handler.ssl.ApplicationProtocolConfig$SelectorFailureBehavior r3 = io.netty.handler.ssl.ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r2 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectorFailureBehavior     // Catch:{ NoSuchFieldError -> 0x0038 }
                io.netty.handler.ssl.ApplicationProtocolConfig$SelectorFailureBehavior r3 = io.netty.handler.ssl.ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                io.netty.handler.ssl.OpenSslCertificateCompressionConfig$AlgorithmMode[] r2 = io.netty.handler.ssl.OpenSslCertificateCompressionConfig.AlgorithmMode.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$io$netty$handler$ssl$OpenSslCertificateCompressionConfig$AlgorithmMode = r2
                io.netty.handler.ssl.OpenSslCertificateCompressionConfig$AlgorithmMode r3 = io.netty.handler.ssl.OpenSslCertificateCompressionConfig.AlgorithmMode.Decompress     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r2 = $SwitchMap$io$netty$handler$ssl$OpenSslCertificateCompressionConfig$AlgorithmMode     // Catch:{ NoSuchFieldError -> 0x0053 }
                io.netty.handler.ssl.OpenSslCertificateCompressionConfig$AlgorithmMode r3 = io.netty.handler.ssl.OpenSslCertificateCompressionConfig.AlgorithmMode.Compress     // Catch:{ NoSuchFieldError -> 0x0053 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0053 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0053 }
            L_0x0053:
                r2 = 3
                int[] r3 = $SwitchMap$io$netty$handler$ssl$OpenSslCertificateCompressionConfig$AlgorithmMode     // Catch:{ NoSuchFieldError -> 0x005e }
                io.netty.handler.ssl.OpenSslCertificateCompressionConfig$AlgorithmMode r4 = io.netty.handler.ssl.OpenSslCertificateCompressionConfig.AlgorithmMode.Both     // Catch:{ NoSuchFieldError -> 0x005e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol[] r3 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol = r3
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r4 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.NPN     // Catch:{ NoSuchFieldError -> 0x006f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                int[] r1 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol     // Catch:{ NoSuchFieldError -> 0x0079 }
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r3 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.ALPN     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r0 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol     // Catch:{ NoSuchFieldError -> 0x0083 }
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r1 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.NPN_AND_ALPN     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol     // Catch:{ NoSuchFieldError -> 0x008e }
                io.netty.handler.ssl.ApplicationProtocolConfig$Protocol r1 = io.netty.handler.ssl.ApplicationProtocolConfig.Protocol.NONE     // Catch:{ NoSuchFieldError -> 0x008e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008e }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008e }
            L_0x008e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslContext.AnonymousClass3.<clinit>():void");
        }
    }

    static boolean useExtendedTrustManager(X509TrustManager x509TrustManager) {
        return PlatformDependent.javaVersion() >= 7 && NioPathKt$$ExternalSyntheticApiModelOutline0.m$2((Object) x509TrustManager);
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

    static abstract class AbstractCertificateVerifier extends CertificateVerifier {
        private final OpenSslEngineMap engineMap;

        /* access modifiers changed from: package-private */
        public abstract void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception;

        AbstractCertificateVerifier(OpenSslEngineMap openSslEngineMap) {
            this.engineMap = openSslEngineMap;
        }

        public final int verify(long j, byte[][] bArr, String str) {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine == null) {
                return CertificateVerifier.X509_V_ERR_UNSPECIFIED;
            }
            try {
                verify(referenceCountedOpenSslEngine, ReferenceCountedOpenSslContext.certificates(bArr), str);
                return CertificateVerifier.X509_V_OK;
            } catch (Throwable th) {
                ReferenceCountedOpenSslContext.logger.debug("verification of certificate failed", (Throwable) th);
                referenceCountedOpenSslEngine.initHandshakeException(th);
                if (th instanceof OpenSslCertificateException) {
                    return th.errorCode();
                }
                if (th instanceof CertificateExpiredException) {
                    return CertificateVerifier.X509_V_ERR_CERT_HAS_EXPIRED;
                }
                if (th instanceof CertificateNotYetValidException) {
                    return CertificateVerifier.X509_V_ERR_CERT_NOT_YET_VALID;
                }
                if (PlatformDependent.javaVersion() >= 7) {
                    return translateToError(th);
                }
                return CertificateVerifier.X509_V_ERR_UNSPECIFIED;
            }
        }

        private static int translateToError(Throwable th) {
            if (NioPathKt$$ExternalSyntheticApiModelOutline0.m$3((Object) th)) {
                return CertificateVerifier.X509_V_ERR_CERT_REVOKED;
            }
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                if (cause instanceof CertPathValidatorException) {
                    CertPathValidatorException.Reason m = ((CertPathValidatorException) cause).getReason();
                    if (m == NioPathKt$$ExternalSyntheticApiModelOutline0.m()) {
                        return CertificateVerifier.X509_V_ERR_CERT_HAS_EXPIRED;
                    }
                    if (m == NioPathKt$$ExternalSyntheticApiModelOutline0.m$1()) {
                        return CertificateVerifier.X509_V_ERR_CERT_NOT_YET_VALID;
                    }
                    if (m == NioPathKt$$ExternalSyntheticApiModelOutline0.m$2()) {
                        return CertificateVerifier.X509_V_ERR_CERT_REVOKED;
                    }
                }
            }
            return CertificateVerifier.X509_V_ERR_UNSPECIFIED;
        }
    }

    private static final class DefaultOpenSslEngineMap implements OpenSslEngineMap {
        private final Map<Long, ReferenceCountedOpenSslEngine> engines;

        private DefaultOpenSslEngineMap() {
            this.engines = PlatformDependent.newConcurrentHashMap();
        }

        public ReferenceCountedOpenSslEngine remove(long j) {
            return this.engines.remove(Long.valueOf(j));
        }

        public void add(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine) {
            this.engines.put(Long.valueOf(referenceCountedOpenSslEngine.sslPointer()), referenceCountedOpenSslEngine);
        }

        public ReferenceCountedOpenSslEngine get(long j) {
            return this.engines.get(Long.valueOf(j));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void setKeyMaterial(long r16, java.security.cert.X509Certificate[] r18, java.security.PrivateKey r19, java.lang.String r20) throws javax.net.ssl.SSLException {
        /*
            r0 = r19
            r1 = 0
            r3 = 0
            io.netty.buffer.ByteBufAllocator r4 = io.netty.buffer.ByteBufAllocator.DEFAULT     // Catch:{ SSLException -> 0x0090, Exception -> 0x0085, all -> 0x0081 }
            r5 = 1
            r6 = r18
            io.netty.handler.ssl.PemEncoded r3 = io.netty.handler.ssl.PemX509Certificate.toPEM(r4, r5, r6)     // Catch:{ SSLException -> 0x0090, Exception -> 0x0085, all -> 0x0081 }
            io.netty.buffer.ByteBufAllocator r4 = io.netty.buffer.ByteBufAllocator.DEFAULT     // Catch:{ SSLException -> 0x007d, Exception -> 0x0079, all -> 0x0075 }
            io.netty.handler.ssl.PemEncoded r6 = r3.retain()     // Catch:{ SSLException -> 0x007d, Exception -> 0x0079, all -> 0x0075 }
            long r14 = toBIO((io.netty.buffer.ByteBufAllocator) r4, (io.netty.handler.ssl.PemEncoded) r6)     // Catch:{ SSLException -> 0x007d, Exception -> 0x0079, all -> 0x0075 }
            io.netty.buffer.ByteBufAllocator r4 = io.netty.buffer.ByteBufAllocator.DEFAULT     // Catch:{ SSLException -> 0x0070, Exception -> 0x006b, all -> 0x0066 }
            io.netty.handler.ssl.PemEncoded r6 = r3.retain()     // Catch:{ SSLException -> 0x0070, Exception -> 0x006b, all -> 0x0066 }
            long r11 = toBIO((io.netty.buffer.ByteBufAllocator) r4, (io.netty.handler.ssl.PemEncoded) r6)     // Catch:{ SSLException -> 0x0070, Exception -> 0x006b, all -> 0x0066 }
            if (r0 == 0) goto L_0x0031
            io.netty.buffer.ByteBufAllocator r4 = io.netty.buffer.ByteBufAllocator.DEFAULT     // Catch:{ SSLException -> 0x002e, Exception -> 0x002b }
            long r1 = toBIO((io.netty.buffer.ByteBufAllocator) r4, (java.security.PrivateKey) r0)     // Catch:{ SSLException -> 0x002e, Exception -> 0x002b }
            goto L_0x0031
        L_0x002b:
            r0 = move-exception
            goto L_0x0088
        L_0x002e:
            r0 = move-exception
            goto L_0x0093
        L_0x0031:
            if (r20 != 0) goto L_0x0037
            java.lang.String r0 = ""
            r13 = r0
            goto L_0x0039
        L_0x0037:
            r13 = r20
        L_0x0039:
            r7 = r16
            r9 = r14
            r18 = r3
            r3 = r11
            r11 = r1
            io.netty.internal.tcnative.SSLContext.setCertificateBio(r7, r9, r11, r13)     // Catch:{ SSLException -> 0x0061, Exception -> 0x005c, all -> 0x0057 }
            r6 = r16
            io.netty.internal.tcnative.SSLContext.setCertificateChainBio(r6, r3, r5)     // Catch:{ SSLException -> 0x0061, Exception -> 0x005c, all -> 0x0057 }
            freeBio(r1)
            freeBio(r14)
            freeBio(r3)
            if (r18 == 0) goto L_0x0056
            r18.release()
        L_0x0056:
            return
        L_0x0057:
            r0 = move-exception
            r11 = r3
            r3 = r18
            goto L_0x0095
        L_0x005c:
            r0 = move-exception
            r11 = r3
            r3 = r18
            goto L_0x0088
        L_0x0061:
            r0 = move-exception
            r11 = r3
            r3 = r18
            goto L_0x0093
        L_0x0066:
            r0 = move-exception
            r18 = r3
            r11 = r1
            goto L_0x0095
        L_0x006b:
            r0 = move-exception
            r18 = r3
            r11 = r1
            goto L_0x0088
        L_0x0070:
            r0 = move-exception
            r18 = r3
            r11 = r1
            goto L_0x0093
        L_0x0075:
            r0 = move-exception
            r18 = r3
            goto L_0x0082
        L_0x0079:
            r0 = move-exception
            r18 = r3
            goto L_0x0086
        L_0x007d:
            r0 = move-exception
            r18 = r3
            goto L_0x0091
        L_0x0081:
            r0 = move-exception
        L_0x0082:
            r11 = r1
            r14 = r11
            goto L_0x0095
        L_0x0085:
            r0 = move-exception
        L_0x0086:
            r11 = r1
            r14 = r11
        L_0x0088:
            javax.net.ssl.SSLException r4 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x0094 }
            java.lang.String r5 = "failed to set certificate and key"
            r4.<init>(r5, r0)     // Catch:{ all -> 0x0094 }
            throw r4     // Catch:{ all -> 0x0094 }
        L_0x0090:
            r0 = move-exception
        L_0x0091:
            r11 = r1
            r14 = r11
        L_0x0093:
            throw r0     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r0 = move-exception
        L_0x0095:
            freeBio(r1)
            freeBio(r14)
            freeBio(r11)
            if (r3 == 0) goto L_0x00a3
            r3.release()
        L_0x00a3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ReferenceCountedOpenSslContext.setKeyMaterial(long, java.security.cert.X509Certificate[], java.security.PrivateKey, java.lang.String):void");
    }

    static void freeBio(long j) {
        if (j != 0) {
            SSL.freeBIO(j);
        }
    }

    static long toBIO(ByteBufAllocator byteBufAllocator, PrivateKey privateKey) throws Exception {
        if (privateKey == null) {
            return 0;
        }
        PemEncoded pem = PemPrivateKey.toPEM(byteBufAllocator, true, privateKey);
        try {
            return toBIO(byteBufAllocator, pem.retain());
        } finally {
            pem.release();
        }
    }

    static long toBIO(ByteBufAllocator byteBufAllocator, X509Certificate... x509CertificateArr) throws Exception {
        if (x509CertificateArr == null) {
            return 0;
        }
        ObjectUtil.checkNonEmpty((T[]) x509CertificateArr, "certChain");
        PemEncoded pem = PemX509Certificate.toPEM(byteBufAllocator, true, x509CertificateArr);
        try {
            return toBIO(byteBufAllocator, pem.retain());
        } finally {
            pem.release();
        }
    }

    static long toBIO(ByteBufAllocator byteBufAllocator, PemEncoded pemEncoded) throws Exception {
        ByteBuf directBuffer;
        try {
            ByteBuf content = pemEncoded.content();
            if (content.isDirect()) {
                long newBIO = newBIO(content.retainedSlice());
                pemEncoded.release();
                return newBIO;
            }
            directBuffer = byteBufAllocator.directBuffer(content.readableBytes());
            directBuffer.writeBytes(content, content.readerIndex(), content.readableBytes());
            long newBIO2 = newBIO(directBuffer.retainedSlice());
            if (pemEncoded.isSensitive()) {
                SslUtils.zeroout(directBuffer);
            }
            directBuffer.release();
            pemEncoded.release();
            return newBIO2;
        } catch (Throwable th) {
            pemEncoded.release();
            throw th;
        }
    }

    private static long newBIO(ByteBuf byteBuf) throws Exception {
        try {
            long newMemBIO = SSL.newMemBIO();
            int readableBytes = byteBuf.readableBytes();
            if (SSL.bioWrite(newMemBIO, OpenSsl.memoryAddress(byteBuf) + ((long) byteBuf.readerIndex()), readableBytes) == readableBytes) {
                return newMemBIO;
            }
            SSL.freeBIO(newMemBIO);
            throw new IllegalStateException("Could not write data to memory BIO");
        } finally {
            byteBuf.release();
        }
    }

    static OpenSslKeyMaterialProvider providerFor(KeyManagerFactory keyManagerFactory, String str) {
        if (keyManagerFactory instanceof OpenSslX509KeyManagerFactory) {
            return ((OpenSslX509KeyManagerFactory) keyManagerFactory).newProvider();
        }
        if (keyManagerFactory instanceof OpenSslCachingX509KeyManagerFactory) {
            return ((OpenSslCachingX509KeyManagerFactory) keyManagerFactory).newProvider(str);
        }
        return new OpenSslKeyMaterialProvider(chooseX509KeyManager(keyManagerFactory.getKeyManagers()), str);
    }

    /* access modifiers changed from: private */
    public static ReferenceCountedOpenSslEngine retrieveEngine(OpenSslEngineMap openSslEngineMap, long j) throws SSLException {
        ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = openSslEngineMap.get(j);
        if (referenceCountedOpenSslEngine != null) {
            return referenceCountedOpenSslEngine;
        }
        throw new SSLException("Could not find a " + StringUtil.simpleClassName((Class<?>) ReferenceCountedOpenSslEngine.class) + " for sslPointer " + j);
    }

    private static final class PrivateKeyMethod implements SSLPrivateKeyMethod {
        private final OpenSslEngineMap engineMap;
        private final OpenSslPrivateKeyMethod keyMethod;

        PrivateKeyMethod(OpenSslEngineMap openSslEngineMap, OpenSslPrivateKeyMethod openSslPrivateKeyMethod) {
            this.engineMap = openSslEngineMap;
            this.keyMethod = openSslPrivateKeyMethod;
        }

        public byte[] sign(long j, int i, byte[] bArr) throws Exception {
            ReferenceCountedOpenSslEngine access$400 = ReferenceCountedOpenSslContext.retrieveEngine(this.engineMap, j);
            try {
                return ReferenceCountedOpenSslContext.verifyResult(this.keyMethod.sign(access$400, i, bArr));
            } catch (Exception e) {
                access$400.initHandshakeException(e);
                throw e;
            }
        }

        public byte[] decrypt(long j, byte[] bArr) throws Exception {
            ReferenceCountedOpenSslEngine access$400 = ReferenceCountedOpenSslContext.retrieveEngine(this.engineMap, j);
            try {
                return ReferenceCountedOpenSslContext.verifyResult(this.keyMethod.decrypt(access$400, bArr));
            } catch (Exception e) {
                access$400.initHandshakeException(e);
                throw e;
            }
        }
    }

    private static final class AsyncPrivateKeyMethod implements AsyncSSLPrivateKeyMethod {
        private final OpenSslEngineMap engineMap;
        private final OpenSslAsyncPrivateKeyMethod keyMethod;

        AsyncPrivateKeyMethod(OpenSslEngineMap openSslEngineMap, OpenSslAsyncPrivateKeyMethod openSslAsyncPrivateKeyMethod) {
            this.engineMap = openSslEngineMap;
            this.keyMethod = openSslAsyncPrivateKeyMethod;
        }

        public void sign(long j, int i, byte[] bArr, ResultCallback<byte[]> resultCallback) {
            try {
                ReferenceCountedOpenSslEngine access$400 = ReferenceCountedOpenSslContext.retrieveEngine(this.engineMap, j);
                this.keyMethod.sign(access$400, i, bArr).addListener(new ResultCallbackListener(access$400, j, resultCallback));
            } catch (SSLException e) {
                resultCallback.onError(j, e);
            }
        }

        public void decrypt(long j, byte[] bArr, ResultCallback<byte[]> resultCallback) {
            try {
                ReferenceCountedOpenSslEngine access$400 = ReferenceCountedOpenSslContext.retrieveEngine(this.engineMap, j);
                this.keyMethod.decrypt(access$400, bArr).addListener(new ResultCallbackListener(access$400, j, resultCallback));
            } catch (SSLException e) {
                resultCallback.onError(j, e);
            }
        }

        private static final class ResultCallbackListener implements FutureListener<byte[]> {
            private final ReferenceCountedOpenSslEngine engine;
            private final ResultCallback<byte[]> resultCallback;
            private final long ssl;

            ResultCallbackListener(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, long j, ResultCallback<byte[]> resultCallback2) {
                this.engine = referenceCountedOpenSslEngine;
                this.ssl = j;
                this.resultCallback = resultCallback2;
            }

            public void operationComplete(Future<byte[]> future) {
                Throwable cause = future.cause();
                if (cause == null) {
                    try {
                        this.resultCallback.onSuccess(this.ssl, ReferenceCountedOpenSslContext.verifyResult(future.getNow()));
                        return;
                    } catch (SignatureException e) {
                        cause = e;
                        this.engine.initHandshakeException(cause);
                    }
                }
                this.resultCallback.onError(this.ssl, cause);
            }
        }
    }

    /* access modifiers changed from: private */
    public static byte[] verifyResult(byte[] bArr) throws SignatureException {
        if (bArr != null) {
            return bArr;
        }
        throw new SignatureException();
    }

    private static final class CompressionAlgorithm implements CertificateCompressionAlgo {
        private final OpenSslCertificateCompressionAlgorithm compressionAlgorithm;
        private final OpenSslEngineMap engineMap;

        CompressionAlgorithm(OpenSslEngineMap openSslEngineMap, OpenSslCertificateCompressionAlgorithm openSslCertificateCompressionAlgorithm) {
            this.engineMap = openSslEngineMap;
            this.compressionAlgorithm = openSslCertificateCompressionAlgorithm;
        }

        public byte[] compress(long j, byte[] bArr) throws Exception {
            return this.compressionAlgorithm.compress(ReferenceCountedOpenSslContext.retrieveEngine(this.engineMap, j), bArr);
        }

        public byte[] decompress(long j, int i, byte[] bArr) throws Exception {
            return this.compressionAlgorithm.decompress(ReferenceCountedOpenSslContext.retrieveEngine(this.engineMap, j), i, bArr);
        }

        public int algorithmId() {
            return this.compressionAlgorithm.algorithmId();
        }
    }
}
