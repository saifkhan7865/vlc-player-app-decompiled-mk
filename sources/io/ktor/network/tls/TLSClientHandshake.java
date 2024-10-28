package io.ktor.network.tls;

import io.ktor.network.tls.cipher.TLSCipher;
import io.ktor.network.tls.extensions.HashAndSign;
import io.ktor.network.tls.extensions.SignatureAlgorithm;
import io.ktor.network.tls.extensions.SignatureAlgorithmKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.pool.ObjectPool;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010/\u001a\u00020\u00122\b\u00100\u001a\u0004\u0018\u000101H\u0002J\u0011\u00102\u001a\u000203H@ø\u0001\u0000¢\u0006\u0002\u00104J5\u00105\u001a\u0002032\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u00100\u001a\u0004\u0018\u000101H@ø\u0001\u0000¢\u0006\u0002\u0010<J\u0011\u0010=\u001a\u000203H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u0010>\u001a\u000203H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u0010?\u001a\u00020.H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0011\u0010@\u001a\u000203H@ø\u0001\u0000¢\u0006\u0002\u00104J\u001b\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020;H@ø\u0001\u0000¢\u0006\u0002\u0010DJ!\u0010E\u001a\u0002032\u0006\u0010C\u001a\u00020;2\u0006\u0010F\u001a\u00020BH@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0019\u0010H\u001a\u0002032\u0006\u0010I\u001a\u00020'H@ø\u0001\u0000¢\u0006\u0002\u0010JJ\u0011\u0010K\u001a\u000203H@ø\u0001\u0000¢\u0006\u0002\u00104J3\u0010L\u001a\u0002032\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010M\u001a\u00020\u00122\b\u00100\u001a\u0004\u0018\u000101H@ø\u0001\u0000¢\u0006\u0002\u0010NJ2\u0010O\u001a\u0002032\u0006\u0010P\u001a\u00020Q2\u0017\u0010R\u001a\u0013\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u0002030S¢\u0006\u0002\bUH@ø\u0001\u0000¢\u0006\u0002\u0010VJ\u0010\u0010W\u001a\u0002032\u0006\u0010-\u001a\u00020.H\u0002R\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\u00020\u0016X\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0019¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b%\u0010\u0010\u001a\u0004\b#\u0010$R\u000e\u0010&\u001a\u00020'X.¢\u0006\u0002\n\u0000R\u001d\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001e0)¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u001c\u001a\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020.X.¢\u0006\u0002\n\u0000\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006X"}, d2 = {"Lio/ktor/network/tls/TLSClientHandshake;", "Lkotlinx/coroutines/CoroutineScope;", "rawInput", "Lio/ktor/utils/io/ByteReadChannel;", "rawOutput", "Lio/ktor/utils/io/ByteWriteChannel;", "config", "Lio/ktor/network/tls/TLSConfig;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/network/tls/TLSConfig;Lkotlin/coroutines/CoroutineContext;)V", "cipher", "Lio/ktor/network/tls/cipher/TLSCipher;", "getCipher", "()Lio/ktor/network/tls/cipher/TLSCipher;", "cipher$delegate", "Lkotlin/Lazy;", "clientSeed", "", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "digest", "Lio/ktor/network/tls/Digest;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "handshakes", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/network/tls/TLSHandshake;", "getHandshakes$annotations", "()V", "input", "Lio/ktor/network/tls/TLSRecord;", "getInput$annotations", "getInput", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "keyMaterial", "getKeyMaterial", "()[B", "keyMaterial$delegate", "masterSecret", "Ljavax/crypto/spec/SecretKeySpec;", "output", "Lkotlinx/coroutines/channels/SendChannel;", "getOutput$annotations", "getOutput", "()Lkotlinx/coroutines/channels/SendChannel;", "serverHello", "Lio/ktor/network/tls/TLSServerHello;", "generatePreSecret", "encryptionInfo", "Lio/ktor/network/tls/EncryptionInfo;", "handleCertificatesAndKeys", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleServerDone", "exchangeType", "Lio/ktor/network/tls/SecretExchangeType;", "serverCertificate", "Ljava/security/cert/Certificate;", "certificateInfo", "Lio/ktor/network/tls/CertificateInfo;", "(Lio/ktor/network/tls/SecretExchangeType;Ljava/security/cert/Certificate;Lio/ktor/network/tls/CertificateInfo;Lio/ktor/network/tls/EncryptionInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "negotiate", "receiveServerFinished", "receiveServerHello", "sendChangeCipherSpec", "sendClientCertificate", "Lio/ktor/network/tls/CertificateAndKey;", "info", "(Lio/ktor/network/tls/CertificateInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendClientCertificateVerify", "certificateAndKey", "(Lio/ktor/network/tls/CertificateInfo;Lio/ktor/network/tls/CertificateAndKey;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendClientFinished", "masterKey", "(Ljavax/crypto/spec/SecretKeySpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendClientHello", "sendClientKeyExchange", "preSecret", "(Lio/ktor/network/tls/SecretExchangeType;Ljava/security/cert/Certificate;[BLio/ktor/network/tls/EncryptionInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendHandshakeRecord", "handshakeType", "Lio/ktor/network/tls/TLSHandshakeType;", "block", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/network/tls/TLSHandshakeType;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyHello", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientHandshake.kt */
public final class TLSClientHandshake implements CoroutineScope {
    private final Lazy cipher$delegate;
    /* access modifiers changed from: private */
    public final byte[] clientSeed;
    /* access modifiers changed from: private */
    public final TLSConfig config;
    private final CoroutineContext coroutineContext;
    /* access modifiers changed from: private */
    public final BytePacketBuilder digest = UtilsKt.Digest();
    private final ReceiveChannel<TLSHandshake> handshakes;
    private final ReceiveChannel<TLSRecord> input;
    private final Lazy keyMaterial$delegate;
    /* access modifiers changed from: private */
    public volatile SecretKeySpec masterSecret;
    private final SendChannel<TLSRecord> output;
    /* access modifiers changed from: private */
    public volatile TLSServerHello serverHello;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSClientHandshake.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003b */
        static {
            /*
                io.ktor.network.tls.SecretExchangeType[] r0 = io.ktor.network.tls.SecretExchangeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                io.ktor.network.tls.SecretExchangeType r2 = io.ktor.network.tls.SecretExchangeType.ECDHE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                io.ktor.network.tls.SecretExchangeType r3 = io.ktor.network.tls.SecretExchangeType.RSA     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                io.ktor.network.tls.TLSHandshakeType[] r0 = io.ktor.network.tls.TLSHandshakeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.network.tls.TLSHandshakeType r3 = io.ktor.network.tls.TLSHandshakeType.Certificate     // Catch:{ NoSuchFieldError -> 0x002a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                io.ktor.network.tls.TLSHandshakeType r1 = io.ktor.network.tls.TLSHandshakeType.CertificateRequest     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                io.ktor.network.tls.TLSHandshakeType r1 = io.ktor.network.tls.TLSHandshakeType.ServerKeyExchange     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                io.ktor.network.tls.TLSHandshakeType r1 = io.ktor.network.tls.TLSHandshakeType.ServerDone     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.WhenMappings.<clinit>():void");
        }
    }

    private static /* synthetic */ void getHandshakes$annotations() {
    }

    public static /* synthetic */ void getInput$annotations() {
    }

    public static /* synthetic */ void getOutput$annotations() {
    }

    public TLSClientHandshake(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, TLSConfig tLSConfig, CoroutineContext coroutineContext2) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "rawInput");
        Intrinsics.checkNotNullParameter(byteWriteChannel, "rawOutput");
        Intrinsics.checkNotNullParameter(tLSConfig, "config");
        Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
        this.config = tLSConfig;
        this.coroutineContext = coroutineContext2;
        this.clientSeed = TLSClientHandshakeKt.generateClientSeed(tLSConfig.getRandom());
        this.keyMaterial$delegate = LazyKt.lazy(new TLSClientHandshake$keyMaterial$2(this));
        this.cipher$delegate = LazyKt.lazy(new TLSClientHandshake$cipher$2(this));
        CoroutineScope coroutineScope = this;
        this.input = ProduceKt.produce$default(coroutineScope, new CoroutineName("cio-tls-parser"), 0, new TLSClientHandshake$input$1(byteReadChannel, this, (Continuation<? super TLSClientHandshake$input$1>) null), 2, (Object) null);
        this.output = ActorKt.actor$default(coroutineScope, new CoroutineName("cio-tls-encoder"), 0, (CoroutineStart) null, (Function1) null, new TLSClientHandshake$output$1(this, byteWriteChannel, (Continuation<? super TLSClientHandshake$output$1>) null), 14, (Object) null);
        this.handshakes = ProduceKt.produce$default(coroutineScope, new CoroutineName("cio-tls-handshake"), 0, new TLSClientHandshake$handshakes$1(this, (Continuation<? super TLSClientHandshake$handshakes$1>) null), 2, (Object) null);
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* access modifiers changed from: private */
    public final byte[] getKeyMaterial() {
        return (byte[]) this.keyMaterial$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final TLSCipher getCipher() {
        return (TLSCipher) this.cipher$delegate.getValue();
    }

    public final ReceiveChannel<TLSRecord> getInput() {
        return this.input;
    }

    public final SendChannel<TLSRecord> getOutput() {
        return this.output;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b7 A[Catch:{ all -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object negotiate(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof io.ktor.network.tls.TLSClientHandshake$negotiate$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.network.tls.TLSClientHandshake$negotiate$1 r0 = (io.ktor.network.tls.TLSClientHandshake$negotiate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSClientHandshake$negotiate$1 r0 = new io.ktor.network.tls.TLSClientHandshake$negotiate$1
            r0.<init>(r10, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x007e
            if (r2 == r6) goto L_0x006b
            if (r2 == r5) goto L_0x0059
            if (r2 == r4) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            int r1 = r0.I$0
            java.lang.Object r0 = r0.L$0
            io.ktor.network.tls.Digest r0 = (io.ktor.network.tls.Digest) r0
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x003b }
            goto L_0x00e3
        L_0x003b:
            r11 = move-exception
            goto L_0x00ef
        L_0x003e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0046:
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$1
            io.ktor.network.tls.Digest r4 = (io.ktor.network.tls.Digest) r4
            java.lang.Object r5 = r0.L$0
            io.ktor.network.tls.TLSClientHandshake r5 = (io.ktor.network.tls.TLSClientHandshake) r5
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0055 }
            goto L_0x00d3
        L_0x0055:
            r11 = move-exception
            r0 = r4
            goto L_0x00ef
        L_0x0059:
            int r2 = r0.I$0
            java.lang.Object r5 = r0.L$2
            io.ktor.network.tls.TLSClientHandshake r5 = (io.ktor.network.tls.TLSClientHandshake) r5
            java.lang.Object r6 = r0.L$1
            io.ktor.network.tls.Digest r6 = (io.ktor.network.tls.Digest) r6
            java.lang.Object r8 = r0.L$0
            io.ktor.network.tls.TLSClientHandshake r8 = (io.ktor.network.tls.TLSClientHandshake) r8
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x007a }
            goto L_0x00af
        L_0x006b:
            int r2 = r0.I$0
            java.lang.Object r6 = r0.L$1
            io.ktor.network.tls.Digest r6 = (io.ktor.network.tls.Digest) r6
            java.lang.Object r8 = r0.L$0
            io.ktor.network.tls.TLSClientHandshake r8 = (io.ktor.network.tls.TLSClientHandshake) r8
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x007a }
            r11 = r6
            goto L_0x009b
        L_0x007a:
            r11 = move-exception
            r0 = r6
            goto L_0x00ef
        L_0x007e:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.utils.io.core.BytePacketBuilder r11 = r10.digest
            io.ktor.network.tls.Digest r11 = io.ktor.network.tls.Digest.m1462boximpl(r11)
            r11.m1471unboximpl()     // Catch:{ all -> 0x00eb }
            r0.L$0 = r10     // Catch:{ all -> 0x00eb }
            r0.L$1 = r11     // Catch:{ all -> 0x00eb }
            r2 = 0
            r0.I$0 = r2     // Catch:{ all -> 0x00eb }
            r0.label = r6     // Catch:{ all -> 0x00eb }
            java.lang.Object r6 = r10.sendClientHello(r0)     // Catch:{ all -> 0x00eb }
            if (r6 != r1) goto L_0x009a
            return r1
        L_0x009a:
            r8 = r10
        L_0x009b:
            r0.L$0 = r8     // Catch:{ all -> 0x00eb }
            r0.L$1 = r11     // Catch:{ all -> 0x00eb }
            r0.L$2 = r8     // Catch:{ all -> 0x00eb }
            r0.I$0 = r2     // Catch:{ all -> 0x00eb }
            r0.label = r5     // Catch:{ all -> 0x00eb }
            java.lang.Object r5 = r8.receiveServerHello(r0)     // Catch:{ all -> 0x00eb }
            if (r5 != r1) goto L_0x00ac
            return r1
        L_0x00ac:
            r6 = r11
            r11 = r5
            r5 = r8
        L_0x00af:
            io.ktor.network.tls.TLSServerHello r11 = (io.ktor.network.tls.TLSServerHello) r11     // Catch:{ all -> 0x007a }
            r5.serverHello = r11     // Catch:{ all -> 0x007a }
            io.ktor.network.tls.TLSServerHello r11 = r8.serverHello     // Catch:{ all -> 0x007a }
            if (r11 != 0) goto L_0x00bd
            java.lang.String r11 = "serverHello"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)     // Catch:{ all -> 0x007a }
            r11 = r7
        L_0x00bd:
            r8.verifyHello(r11)     // Catch:{ all -> 0x007a }
            r0.L$0 = r8     // Catch:{ all -> 0x007a }
            r0.L$1 = r6     // Catch:{ all -> 0x007a }
            r0.L$2 = r7     // Catch:{ all -> 0x007a }
            r0.I$0 = r2     // Catch:{ all -> 0x007a }
            r0.label = r4     // Catch:{ all -> 0x007a }
            java.lang.Object r11 = r8.handleCertificatesAndKeys(r0)     // Catch:{ all -> 0x007a }
            if (r11 != r1) goto L_0x00d1
            return r1
        L_0x00d1:
            r4 = r6
            r5 = r8
        L_0x00d3:
            r0.L$0 = r4     // Catch:{ all -> 0x0055 }
            r0.L$1 = r7     // Catch:{ all -> 0x0055 }
            r0.I$0 = r2     // Catch:{ all -> 0x0055 }
            r0.label = r3     // Catch:{ all -> 0x0055 }
            java.lang.Object r11 = r5.receiveServerFinished(r0)     // Catch:{ all -> 0x0055 }
            if (r11 != r1) goto L_0x00e2
            return r1
        L_0x00e2:
            r0 = r4
        L_0x00e3:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003b }
            r0.close()
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00eb:
            r0 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
        L_0x00ef:
            r0.close()     // Catch:{ all -> 0x00f3 }
            goto L_0x00f7
        L_0x00f3:
            r0 = move-exception
            io.ktor.utils.io.core.CloseableJVMKt.addSuppressedInternal(r11, r0)
        L_0x00f7:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.negotiate(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void verifyHello(TLSServerHello tLSServerHello) {
        CipherSuite cipherSuite = tLSServerHello.getCipherSuite();
        if (this.config.getCipherSuites().contains(cipherSuite)) {
            Collection arrayList = new ArrayList();
            for (Object next : SignatureAlgorithmKt.getSupportedSignatureAlgorithms()) {
                HashAndSign hashAndSign = (HashAndSign) next;
                if (hashAndSign.getHash() == cipherSuite.getHash() && hashAndSign.getSign() == cipherSuite.getSignatureAlgorithm()) {
                    arrayList.add(next);
                }
            }
            List list = (List) arrayList;
            if (!list.isEmpty()) {
                List<HashAndSign> hashAndSignAlgorithms = tLSServerHello.getHashAndSignAlgorithms();
                if (!hashAndSignAlgorithms.isEmpty()) {
                    Iterable<HashAndSign> iterable = list;
                    if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                        for (HashAndSign contains : iterable) {
                            if (hashAndSignAlgorithms.contains(contains)) {
                                return;
                            }
                        }
                    }
                    throw new TLSException("No sign algorithms in common. \nServer candidates: " + hashAndSignAlgorithms + " \nClient candidates: " + list, (Throwable) null, 2, (DefaultConstructorMarker) null);
                }
                return;
            }
            throw new TLSException("No appropriate hash algorithm for suite: " + cipherSuite, (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
        throw new IllegalStateException(("Unsupported cipher suite " + cipherSuite.getName() + " in SERVER_HELLO").toString());
    }

    /* access modifiers changed from: private */
    public final Object sendClientHello(Continuation<? super Unit> continuation) {
        Object sendHandshakeRecord = sendHandshakeRecord(TLSHandshakeType.ClientHello, new TLSClientHandshake$sendClientHello$2(this), continuation);
        return sendHandshakeRecord == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendHandshakeRecord : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object receiveServerHello(kotlin.coroutines.Continuation<? super io.ktor.network.tls.TLSServerHello> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.network.tls.TLSClientHandshake$receiveServerHello$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            io.ktor.network.tls.TLSClientHandshake$receiveServerHello$1 r0 = (io.ktor.network.tls.TLSClientHandshake$receiveServerHello$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSClientHandshake$receiveServerHello$1 r0 = new io.ktor.network.tls.TLSClientHandshake$receiveServerHello$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0040
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.ReceiveChannel<io.ktor.network.tls.TLSHandshake> r5 = r4.handshakes
            r0.label = r3
            java.lang.Object r5 = r5.receive(r0)
            if (r5 != r1) goto L_0x0040
            return r1
        L_0x0040:
            io.ktor.network.tls.TLSHandshake r5 = (io.ktor.network.tls.TLSHandshake) r5
            io.ktor.network.tls.TLSHandshakeType r0 = r5.getType()
            io.ktor.network.tls.TLSHandshakeType r1 = io.ktor.network.tls.TLSHandshakeType.ServerHello
            if (r0 != r1) goto L_0x0053
            io.ktor.utils.io.core.ByteReadPacket r5 = r5.getPacket()
            io.ktor.network.tls.TLSServerHello r5 = io.ktor.network.tls.ParserKt.readTLSServerHello(r5)
            return r5
        L_0x0053:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Expected TLS handshake ServerHello but got "
            r0.<init>(r1)
            io.ktor.network.tls.TLSHandshakeType r5 = r5.getType()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r5 = r5.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.receiveServerHello(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1 */
    /* JADX WARNING: type inference failed for: r8v2, types: [java.lang.Throwable, kotlin.jvm.internal.DefaultConstructorMarker, io.ktor.utils.io.pool.ObjectPool, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Throwable, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARNING: type inference failed for: r8v8 */
    /* JADX WARNING: type inference failed for: r8v9 */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01d7  */
    public final java.lang.Object handleCertificatesAndKeys(kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            r30 = this;
            r1 = r30
            r0 = r31
            boolean r2 = r0 instanceof io.ktor.network.tls.TLSClientHandshake$handleCertificatesAndKeys$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            io.ktor.network.tls.TLSClientHandshake$handleCertificatesAndKeys$1 r2 = (io.ktor.network.tls.TLSClientHandshake$handleCertificatesAndKeys$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            io.ktor.network.tls.TLSClientHandshake$handleCertificatesAndKeys$1 r2 = new io.ktor.network.tls.TLSClientHandshake$handleCertificatesAndKeys$1
            r2.<init>(r1, r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            java.lang.String r5 = "serverHello"
            r6 = 2
            r7 = 1
            r8 = 0
            if (r4 == 0) goto L_0x005e
            if (r4 == r7) goto L_0x003d
            if (r4 != r6) goto L_0x0035
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00c7
        L_0x0035:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x003d:
            java.lang.Object r4 = r2.L$4
            io.ktor.network.tls.EncryptionInfo r4 = (io.ktor.network.tls.EncryptionInfo) r4
            java.lang.Object r9 = r2.L$3
            io.ktor.network.tls.CertificateInfo r9 = (io.ktor.network.tls.CertificateInfo) r9
            java.lang.Object r10 = r2.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r2.L$1
            io.ktor.network.tls.SecretExchangeType r11 = (io.ktor.network.tls.SecretExchangeType) r11
            java.lang.Object r12 = r2.L$0
            io.ktor.network.tls.TLSClientHandshake r12 = (io.ktor.network.tls.TLSClientHandshake) r12
            kotlin.ResultKt.throwOnFailure(r0)
        L_0x0054:
            r14 = r2
            r13 = r4
            r2 = r10
            r10 = r11
            r29 = r12
            r12 = r9
            r9 = r29
            goto L_0x0090
        L_0x005e:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.network.tls.TLSServerHello r0 = r1.serverHello
            if (r0 != 0) goto L_0x0069
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r0 = r8
        L_0x0069:
            io.ktor.network.tls.CipherSuite r0 = r0.getCipherSuite()
            io.ktor.network.tls.SecretExchangeType r0 = r0.getExchangeType()
            kotlin.jvm.internal.Ref$ObjectRef r4 = new kotlin.jvm.internal.Ref$ObjectRef
            r4.<init>()
            r11 = r0
            r12 = r1
            r10 = r4
            r4 = r8
            r9 = r4
        L_0x007b:
            kotlinx.coroutines.channels.ReceiveChannel<io.ktor.network.tls.TLSHandshake> r0 = r12.handshakes
            r2.L$0 = r12
            r2.L$1 = r11
            r2.L$2 = r10
            r2.L$3 = r9
            r2.L$4 = r4
            r2.label = r7
            java.lang.Object r0 = r0.receive(r2)
            if (r0 != r3) goto L_0x0054
            return r3
        L_0x0090:
            io.ktor.network.tls.TLSHandshake r0 = (io.ktor.network.tls.TLSHandshake) r0
            io.ktor.utils.io.core.ByteReadPacket r4 = r0.getPacket()
            io.ktor.network.tls.TLSHandshakeType r11 = r0.getType()
            int[] r15 = io.ktor.network.tls.TLSClientHandshake.WhenMappings.$EnumSwitchMapping$1
            int r11 = r11.ordinal()
            r11 = r15[r11]
            if (r11 == r7) goto L_0x01d7
            if (r11 == r6) goto L_0x01c9
            r15 = 3
            if (r11 == r15) goto L_0x00e2
            r4 = 4
            if (r11 != r4) goto L_0x00ca
            T r0 = r2.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r11 = r0
            java.security.cert.Certificate r11 = (java.security.cert.Certificate) r11
            r14.L$0 = r8
            r14.L$1 = r8
            r14.L$2 = r8
            r14.L$3 = r8
            r14.L$4 = r8
            r14.label = r6
            java.lang.Object r0 = r9.handleServerDone(r10, r11, r12, r13, r14)
            if (r0 != r3) goto L_0x00c7
            return r3
        L_0x00c7:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00ca:
            io.ktor.network.tls.TLSException r2 = new io.ktor.network.tls.TLSException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Unsupported message type during handshake: "
            r3.<init>(r4)
            io.ktor.network.tls.TLSHandshakeType r0 = r0.getType()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0, r8, r6, r8)
            throw r2
        L_0x00e2:
            int[] r0 = io.ktor.network.tls.TLSClientHandshake.WhenMappings.$EnumSwitchMapping$0
            int r11 = r10.ordinal()
            r0 = r0[r11]
            if (r0 == r7) goto L_0x0100
            if (r0 == r6) goto L_0x00f1
            r1 = 1
            goto L_0x0293
        L_0x00f1:
            r4.release()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Server key exchange handshake doesn't expected in RCA exchange type"
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0100:
            io.ktor.network.tls.extensions.NamedCurve r0 = io.ktor.network.tls.ParserKt.readCurveParams(r4)
            int r11 = r0.getFieldSize()
            java.security.spec.ECPoint r11 = io.ktor.network.tls.ParserKt.readECPoint(r4, r11)
            io.ktor.network.tls.extensions.HashAndSign r13 = io.ktor.network.tls.extensions.SignatureAlgorithmKt.readHashAndSign(r4)
            if (r13 == 0) goto L_0x01bd
            io.ktor.utils.io.core.BytePacketBuilder r15 = new io.ktor.utils.io.core.BytePacketBuilder
            r15.<init>(r8, r7, r8)
            io.ktor.network.tls.ServerKeyExchangeType r16 = io.ktor.network.tls.ServerKeyExchangeType.NamedCurve     // Catch:{ all -> 0x01b8 }
            int r6 = r16.getCode()     // Catch:{ all -> 0x01b8 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x01b8 }
            r15.writeByte(r6)     // Catch:{ all -> 0x01b8 }
            r6 = r15
            io.ktor.utils.io.core.Output r6 = (io.ktor.utils.io.core.Output) r6     // Catch:{ all -> 0x01b8 }
            short r7 = r0.getCode()     // Catch:{ all -> 0x01b8 }
            io.ktor.utils.io.core.OutputPrimitivesKt.writeShort(r6, r7)     // Catch:{ all -> 0x01b8 }
            int r6 = r0.getFieldSize()     // Catch:{ all -> 0x01b8 }
            io.ktor.network.tls.RenderKt.writeECPoint(r15, r11, r6)     // Catch:{ all -> 0x01b8 }
            io.ktor.utils.io.core.ByteReadPacket r6 = r15.build()     // Catch:{ all -> 0x01b8 }
            java.lang.String r7 = r13.getName()
            java.security.Signature r7 = java.security.Signature.getInstance(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            T r13 = r2.element
            java.security.cert.Certificate r13 = (java.security.cert.Certificate) r13
            r7.initVerify(r13)
            io.ktor.utils.io.core.BytePacketBuilder r13 = new io.ktor.utils.io.core.BytePacketBuilder
            r15 = 1
            r13.<init>(r8, r15, r8)
            r17 = r13
            io.ktor.utils.io.core.Output r17 = (io.ktor.utils.io.core.Output) r17     // Catch:{ all -> 0x01b3 }
            byte[] r15 = r9.clientSeed     // Catch:{ all -> 0x01b3 }
            r21 = 6
            r22 = 0
            r19 = 0
            r20 = 0
            r18 = r15
            io.ktor.utils.io.core.OutputKt.writeFully$default((io.ktor.utils.io.core.Output) r17, (byte[]) r18, (int) r19, (int) r20, (int) r21, (java.lang.Object) r22)     // Catch:{ all -> 0x01b3 }
            r23 = r13
            io.ktor.utils.io.core.Output r23 = (io.ktor.utils.io.core.Output) r23     // Catch:{ all -> 0x01b3 }
            io.ktor.network.tls.TLSServerHello r15 = r9.serverHello     // Catch:{ all -> 0x01b3 }
            if (r15 != 0) goto L_0x016d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ all -> 0x01b3 }
            r15 = r8
        L_0x016d:
            byte[] r24 = r15.getServerSeed()     // Catch:{ all -> 0x01b3 }
            r27 = 6
            r28 = 0
            r25 = 0
            r26 = 0
            io.ktor.utils.io.core.OutputKt.writeFully$default((io.ktor.utils.io.core.Output) r23, (byte[]) r24, (int) r25, (int) r26, (int) r27, (java.lang.Object) r28)     // Catch:{ all -> 0x01b3 }
            r13.writePacket(r6)     // Catch:{ all -> 0x01b3 }
            io.ktor.utils.io.core.ByteReadPacket r6 = r13.build()     // Catch:{ all -> 0x01b3 }
            r13 = 0
            r15 = 1
            byte[] r6 = io.ktor.utils.io.core.StringsKt.readBytes$default(r6, r13, r15, r8)
            r7.update(r6)
            r6 = r4
            io.ktor.utils.io.core.Input r6 = (io.ktor.utils.io.core.Input) r6
            short r6 = io.ktor.utils.io.core.InputPrimitivesKt.readShort(r6)
            r13 = 65535(0xffff, float:9.1834E-41)
            r6 = r6 & r13
            byte[] r4 = io.ktor.utils.io.core.StringsKt.readBytes((io.ktor.utils.io.core.ByteReadPacket) r4, (int) r6)
            boolean r4 = r7.verify(r4)
            if (r4 == 0) goto L_0x01aa
            io.ktor.network.tls.EncryptionInfo r4 = io.ktor.network.tls.TLSClientHandshakeKt.generateECKeys(r0, r11)
            r11 = r10
            r6 = 2
            r7 = 1
            goto L_0x029a
        L_0x01aa:
            io.ktor.network.tls.TLSException r0 = new io.ktor.network.tls.TLSException
            java.lang.String r2 = "Failed to verify signed message"
            r3 = 2
            r0.<init>(r2, r8, r3, r8)
            throw r0
        L_0x01b3:
            r0 = move-exception
            r13.release()
            throw r0
        L_0x01b8:
            r0 = move-exception
            r15.release()
            throw r0
        L_0x01bd:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Unknown hash and sign type."
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x01c9:
            io.ktor.network.tls.CertificateInfo r0 = io.ktor.network.tls.TLSClientHandshakeKt.readClientCertificateRequest(r4)
            r12 = r9
            r11 = r10
            r4 = r13
            r6 = 2
            r7 = 1
            r9 = r0
            r10 = r2
            r2 = r14
            goto L_0x007b
        L_0x01d7:
            java.util.List r0 = io.ktor.network.tls.ParserKt.readTLSCertificate(r4)
            r4 = r0
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r4 = r4.iterator()
        L_0x01e9:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x01fb
            java.lang.Object r7 = r4.next()
            boolean r11 = r7 instanceof java.security.cert.X509Certificate
            if (r11 == 0) goto L_0x01e9
            r6.add(r7)
            goto L_0x01e9
        L_0x01fb:
            java.util.List r6 = (java.util.List) r6
            boolean r4 = r6.isEmpty()
            if (r4 != 0) goto L_0x02b9
            io.ktor.network.tls.TLSConfig r4 = r9.config
            javax.net.ssl.X509TrustManager r4 = r4.getTrustManager()
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            r11 = 0
            java.security.cert.X509Certificate[] r11 = new java.security.cert.X509Certificate[r11]
            java.lang.Object[] r7 = r7.toArray(r11)
            java.security.cert.X509Certificate[] r7 = (java.security.cert.X509Certificate[]) r7
            java.lang.String r11 = r10.getJvmName()
            r4.checkServerTrusted(r7, r11)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r4 = r6.iterator()
        L_0x0222:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0276
            java.lang.Object r6 = r4.next()
            r7 = r6
            java.security.cert.X509Certificate r7 = (java.security.cert.X509Certificate) r7
            java.util.List r11 = io.ktor.network.tls.extensions.SignatureAlgorithmKt.getSupportedSignatureAlgorithms()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            boolean r15 = r11 instanceof java.util.Collection
            if (r15 == 0) goto L_0x0244
            r15 = r11
            java.util.Collection r15 = (java.util.Collection) r15
            boolean r15 = r15.isEmpty()
            if (r15 == 0) goto L_0x0244
        L_0x0242:
            r1 = 1
            goto L_0x0272
        L_0x0244:
            java.util.Iterator r11 = r11.iterator()
        L_0x0248:
            boolean r15 = r11.hasNext()
            if (r15 == 0) goto L_0x0242
            java.lang.Object r15 = r11.next()
            io.ktor.network.tls.extensions.HashAndSign r15 = (io.ktor.network.tls.extensions.HashAndSign) r15
            io.ktor.network.tls.OID r15 = r15.getOid()
            if (r15 == 0) goto L_0x026d
            java.lang.String r15 = r15.getIdentifier()
            if (r15 != 0) goto L_0x0261
            goto L_0x026d
        L_0x0261:
            java.lang.String r8 = r7.getSigAlgOID()
            r1 = 1
            boolean r8 = kotlin.text.StringsKt.equals(r15, r8, r1)
            if (r8 == 0) goto L_0x026e
            goto L_0x0278
        L_0x026d:
            r1 = 1
        L_0x026e:
            r1 = r30
            r8 = 0
            goto L_0x0248
        L_0x0272:
            r1 = r30
            r8 = 0
            goto L_0x0222
        L_0x0276:
            r1 = 1
            r6 = 0
        L_0x0278:
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6
            if (r6 == 0) goto L_0x02a3
            r2.element = r6
            io.ktor.network.tls.TLSConfig r0 = r9.config
            java.lang.String r0 = r0.getServerName()
            if (r0 == 0) goto L_0x0293
            io.ktor.network.tls.TLSConfig r0 = r9.config
            java.lang.String r0 = r0.getServerName()
            T r4 = r2.element
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4
            io.ktor.network.tls.HostnameUtilsKt.verifyHostnameInCertificate(r0, r4)
        L_0x0293:
            r1 = r30
            r11 = r10
            r4 = r13
            r6 = 2
            r7 = 1
            r8 = 0
        L_0x029a:
            r10 = r2
            r2 = r14
            r29 = r12
            r12 = r9
            r9 = r29
            goto L_0x007b
        L_0x02a3:
            io.ktor.network.tls.TLSException r1 = new io.ktor.network.tls.TLSException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "No suitable server certificate received: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r2 = 2
            r3 = 0
            r1.<init>(r0, r3, r2, r3)
            throw r1
        L_0x02b9:
            r3 = r8
            r2 = 2
            io.ktor.network.tls.TLSException r0 = new io.ktor.network.tls.TLSException
            java.lang.String r1 = "Server sent no certificate"
            r0.<init>(r1, r3, r2, r3)
            goto L_0x02c4
        L_0x02c3:
            throw r0
        L_0x02c4:
            goto L_0x02c3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.handleCertificatesAndKeys(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0147 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x015c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleServerDone(io.ktor.network.tls.SecretExchangeType r19, java.security.cert.Certificate r20, io.ktor.network.tls.CertificateInfo r21, io.ktor.network.tls.EncryptionInfo r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            r18 = this;
            r0 = r18
            r1 = r21
            r2 = r23
            boolean r3 = r2 instanceof io.ktor.network.tls.TLSClientHandshake$handleServerDone$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            io.ktor.network.tls.TLSClientHandshake$handleServerDone$1 r3 = (io.ktor.network.tls.TLSClientHandshake$handleServerDone$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            io.ktor.network.tls.TLSClientHandshake$handleServerDone$1 r3 = new io.ktor.network.tls.TLSClientHandshake$handleServerDone$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r3.label
            r11 = 5
            r12 = 4
            r13 = 3
            r5 = 2
            r6 = 1
            r14 = 0
            if (r4 == 0) goto L_0x0091
            if (r4 == r6) goto L_0x0071
            if (r4 == r5) goto L_0x0058
            if (r4 == r13) goto L_0x004f
            if (r4 == r12) goto L_0x0046
            if (r4 != r11) goto L_0x003e
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x015d
        L_0x003e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0046:
            java.lang.Object r1 = r3.L$0
            io.ktor.network.tls.TLSClientHandshake r1 = (io.ktor.network.tls.TLSClientHandshake) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0148
        L_0x004f:
            java.lang.Object r1 = r3.L$0
            io.ktor.network.tls.TLSClientHandshake r1 = (io.ktor.network.tls.TLSClientHandshake) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0137
        L_0x0058:
            java.lang.Object r1 = r3.L$3
            byte[] r1 = (byte[]) r1
            java.lang.Object r4 = r3.L$2
            io.ktor.network.tls.CertificateAndKey r4 = (io.ktor.network.tls.CertificateAndKey) r4
            java.lang.Object r5 = r3.L$1
            io.ktor.network.tls.CertificateInfo r5 = (io.ktor.network.tls.CertificateInfo) r5
            java.lang.Object r6 = r3.L$0
            io.ktor.network.tls.TLSClientHandshake r6 = (io.ktor.network.tls.TLSClientHandshake) r6
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r4
            r15 = r5
            r4 = r1
            r1 = r6
            goto L_0x00eb
        L_0x0071:
            java.lang.Object r1 = r3.L$4
            io.ktor.network.tls.EncryptionInfo r1 = (io.ktor.network.tls.EncryptionInfo) r1
            java.lang.Object r4 = r3.L$3
            io.ktor.network.tls.CertificateInfo r4 = (io.ktor.network.tls.CertificateInfo) r4
            java.lang.Object r6 = r3.L$2
            java.security.cert.Certificate r6 = (java.security.cert.Certificate) r6
            java.lang.Object r7 = r3.L$1
            io.ktor.network.tls.SecretExchangeType r7 = (io.ktor.network.tls.SecretExchangeType) r7
            java.lang.Object r8 = r3.L$0
            io.ktor.network.tls.TLSClientHandshake r8 = (io.ktor.network.tls.TLSClientHandshake) r8
            kotlin.ResultKt.throwOnFailure(r2)
            r17 = r7
            r7 = r1
            r1 = r4
            r4 = r6
            r6 = r2
            r2 = r17
            goto L_0x00b0
        L_0x0091:
            kotlin.ResultKt.throwOnFailure(r2)
            if (r1 == 0) goto L_0x00bb
            r3.L$0 = r0
            r2 = r19
            r3.L$1 = r2
            r4 = r20
            r3.L$2 = r4
            r3.L$3 = r1
            r7 = r22
            r3.L$4 = r7
            r3.label = r6
            java.lang.Object r6 = r0.sendClientCertificate(r1, r3)
            if (r6 != r10) goto L_0x00af
            return r10
        L_0x00af:
            r8 = r0
        L_0x00b0:
            io.ktor.network.tls.CertificateAndKey r6 = (io.ktor.network.tls.CertificateAndKey) r6
            r15 = r8
            r8 = r7
            r7 = r4
            r17 = r6
            r6 = r2
            r2 = r17
            goto L_0x00c6
        L_0x00bb:
            r2 = r19
            r4 = r20
            r7 = r22
            r15 = r0
            r6 = r2
            r8 = r7
            r2 = r14
            r7 = r4
        L_0x00c6:
            byte[] r9 = r15.generatePreSecret(r8)
            r3.L$0 = r15
            r3.L$1 = r1
            r3.L$2 = r2
            r3.L$3 = r9
            r3.L$4 = r14
            r3.label = r5
            r4 = r15
            r5 = r6
            r6 = r7
            r7 = r9
            r16 = r9
            r9 = r3
            java.lang.Object r4 = r4.sendClientKeyExchange(r5, r6, r7, r8, r9)
            if (r4 != r10) goto L_0x00e4
            return r10
        L_0x00e4:
            r4 = r16
            r17 = r15
            r15 = r1
            r1 = r17
        L_0x00eb:
            javax.crypto.spec.SecretKeySpec r5 = new javax.crypto.spec.SecretKeySpec
            io.ktor.network.tls.TLSServerHello r6 = r1.serverHello
            java.lang.String r7 = "serverHello"
            if (r6 != 0) goto L_0x00f7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r6 = r14
        L_0x00f7:
            io.ktor.network.tls.CipherSuite r6 = r6.getCipherSuite()
            io.ktor.network.tls.extensions.HashAlgorithm r6 = r6.getHash()
            java.lang.String r6 = r6.getMacName()
            r5.<init>(r4, r6)
            javax.crypto.SecretKey r5 = (javax.crypto.SecretKey) r5
            byte[] r6 = r1.clientSeed
            io.ktor.network.tls.TLSServerHello r8 = r1.serverHello
            if (r8 != 0) goto L_0x0112
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r8 = r14
        L_0x0112:
            byte[] r7 = r8.getServerSeed()
            javax.crypto.spec.SecretKeySpec r5 = io.ktor.network.tls.KeysKt.masterSecret(r5, r6, r7)
            r1.masterSecret = r5
            r8 = 6
            r9 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            kotlin.collections.ArraysKt.fill$default((byte[]) r4, (byte) r5, (int) r6, (int) r7, (int) r8, (java.lang.Object) r9)
            if (r2 == 0) goto L_0x0137
            r3.L$0 = r1
            r3.L$1 = r14
            r3.L$2 = r14
            r3.L$3 = r14
            r3.label = r13
            java.lang.Object r2 = r1.sendClientCertificateVerify(r15, r2, r3)
            if (r2 != r10) goto L_0x0137
            return r10
        L_0x0137:
            r3.L$0 = r1
            r3.L$1 = r14
            r3.L$2 = r14
            r3.L$3 = r14
            r3.label = r12
            java.lang.Object r2 = r1.sendChangeCipherSpec(r3)
            if (r2 != r10) goto L_0x0148
            return r10
        L_0x0148:
            javax.crypto.spec.SecretKeySpec r2 = r1.masterSecret
            if (r2 != 0) goto L_0x0152
            java.lang.String r2 = "masterSecret"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r2 = r14
        L_0x0152:
            r3.L$0 = r14
            r3.label = r11
            java.lang.Object r1 = r1.sendClientFinished(r2, r3)
            if (r1 != r10) goto L_0x015d
            return r10
        L_0x015d:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.handleServerDone(io.ktor.network.tls.SecretExchangeType, java.security.cert.Certificate, io.ktor.network.tls.CertificateInfo, io.ktor.network.tls.EncryptionInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final byte[] generatePreSecret(EncryptionInfo encryptionInfo) {
        TLSServerHello tLSServerHello = this.serverHello;
        if (tLSServerHello == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serverHello");
            tLSServerHello = null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[tLSServerHello.getCipherSuite().getExchangeType().ordinal()];
        if (i == 1) {
            KeyAgreement instance = KeyAgreement.getInstance("ECDH");
            Intrinsics.checkNotNull(instance);
            if (encryptionInfo != null) {
                instance.init(encryptionInfo.getClientPrivate());
                instance.doPhase(encryptionInfo.getServerPublic(), true);
                byte[] generateSecret = instance.generateSecret();
                Intrinsics.checkNotNull(generateSecret);
                return generateSecret;
            }
            throw new TLSException("ECDHE_ECDSA: Encryption info should be provided", (Throwable) null, 2, (DefaultConstructorMarker) null);
        } else if (i == 2) {
            byte[] bArr = new byte[48];
            this.config.getRandom().nextBytes(bArr);
            bArr[0] = 3;
            bArr[1] = 3;
            return bArr;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* access modifiers changed from: private */
    public final Object sendClientKeyExchange(SecretExchangeType secretExchangeType, Certificate certificate, byte[] bArr, EncryptionInfo encryptionInfo, Continuation<? super Unit> continuation) {
        ByteReadPacket byteReadPacket;
        int i = WhenMappings.$EnumSwitchMapping$0[secretExchangeType.ordinal()];
        if (i == 1) {
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            if (encryptionInfo != null) {
                try {
                    RenderKt.writePublicKeyUncompressed(bytePacketBuilder, encryptionInfo.getClientPublic());
                    byteReadPacket = bytePacketBuilder.build();
                } catch (Throwable th) {
                    bytePacketBuilder.release();
                    throw th;
                }
            } else {
                throw new TLSException("ECDHE: Encryption info should be provided", (Throwable) null, 2, (DefaultConstructorMarker) null);
            }
        } else if (i == 2) {
            BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            try {
                PublicKey publicKey = certificate.getPublicKey();
                Intrinsics.checkNotNullExpressionValue(publicKey, "serverCertificate.publicKey");
                RenderKt.writeEncryptedPreMasterSecret(bytePacketBuilder2, bArr, publicKey, this.config.getRandom());
                byteReadPacket = bytePacketBuilder2.build();
            } catch (Throwable th2) {
                bytePacketBuilder2.release();
                throw th2;
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Object sendHandshakeRecord = sendHandshakeRecord(TLSHandshakeType.ClientKeyExchange, new TLSClientHandshake$sendClientKeyExchange$2(byteReadPacket), continuation);
        return sendHandshakeRecord == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendHandshakeRecord : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendClientCertificate(io.ktor.network.tls.CertificateInfo r13, kotlin.coroutines.Continuation<? super io.ktor.network.tls.CertificateAndKey> r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof io.ktor.network.tls.TLSClientHandshake$sendClientCertificate$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ktor.network.tls.TLSClientHandshake$sendClientCertificate$1 r0 = (io.ktor.network.tls.TLSClientHandshake$sendClientCertificate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSClientHandshake$sendClientCertificate$1 r0 = new io.ktor.network.tls.TLSClientHandshake$sendClientCertificate$1
            r0.<init>(r12, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r13 = r0.L$0
            io.ktor.network.tls.CertificateAndKey r13 = (io.ktor.network.tls.CertificateAndKey) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0125
        L_0x002f:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r14)
            io.ktor.network.tls.TLSConfig r14 = r12.config
            java.util.List r14 = r14.getCertificates()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.Iterator r14 = r14.iterator()
        L_0x0046:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto L_0x010d
            java.lang.Object r2 = r14.next()
            r4 = r2
            io.ktor.network.tls.CertificateAndKey r4 = (io.ktor.network.tls.CertificateAndKey) r4
            java.security.cert.X509Certificate[] r5 = r4.getCertificateChain()
            java.lang.Object r5 = kotlin.collections.ArraysKt.first((T[]) r5)
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5
            java.security.PublicKey r6 = r5.getPublicKey()
            java.lang.String r6 = r6.getAlgorithm()
            java.lang.String r7 = "RSA"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x007c
            byte[] r6 = r13.getTypes()
            io.ktor.network.tls.CertificateType r7 = io.ktor.network.tls.CertificateType.INSTANCE
            byte r7 = r7.getRSA()
            boolean r6 = kotlin.collections.ArraysKt.contains((byte[]) r6, (byte) r7)
            goto L_0x0092
        L_0x007c:
            java.lang.String r7 = "DSS"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r6 == 0) goto L_0x0046
            byte[] r6 = r13.getTypes()
            io.ktor.network.tls.CertificateType r7 = io.ktor.network.tls.CertificateType.INSTANCE
            byte r7 = r7.getDSS()
            boolean r6 = kotlin.collections.ArraysKt.contains((byte[]) r6, (byte) r7)
        L_0x0092:
            if (r6 != 0) goto L_0x0095
            goto L_0x0046
        L_0x0095:
            io.ktor.network.tls.extensions.HashAndSign[] r6 = r13.getHashAndSign()
            int r7 = r6.length
            r8 = 0
            r9 = 0
        L_0x009c:
            if (r9 >= r7) goto L_0x0046
            r10 = r6[r9]
            java.lang.String r10 = r10.getName()
            java.lang.String r11 = r5.getSigAlgName()
            boolean r10 = kotlin.text.StringsKt.equals(r10, r11, r3)
            if (r10 == 0) goto L_0x010a
            java.util.Set r5 = r13.getAuthorities()
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x010e
            java.security.cert.X509Certificate[] r4 = r4.getCertificateChain()
            java.util.ArrayList r5 = new java.util.ArrayList
            int r6 = r4.length
            r5.<init>(r6)
            java.util.Collection r5 = (java.util.Collection) r5
            int r6 = r4.length
        L_0x00c5:
            if (r8 >= r6) goto L_0x00dc
            r7 = r4[r8]
            javax.security.auth.x500.X500Principal r9 = new javax.security.auth.x500.X500Principal
            javax.security.auth.x500.X500Principal r7 = r7.getIssuerX500Principal()
            java.lang.String r7 = r7.getName()
            r9.<init>(r7)
            r5.add(r9)
            int r8 = r8 + 1
            goto L_0x00c5
        L_0x00dc:
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r4 = r5 instanceof java.util.Collection
            if (r4 == 0) goto L_0x00ef
            r4 = r5
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x00ef
            goto L_0x0046
        L_0x00ef:
            java.util.Iterator r4 = r5.iterator()
        L_0x00f3:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0046
            java.lang.Object r5 = r4.next()
            javax.security.auth.x500.X500Principal r5 = (javax.security.auth.x500.X500Principal) r5
            java.util.Set r6 = r13.getAuthorities()
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L_0x00f3
            goto L_0x010e
        L_0x010a:
            int r9 = r9 + 1
            goto L_0x009c
        L_0x010d:
            r2 = 0
        L_0x010e:
            r13 = r2
            io.ktor.network.tls.CertificateAndKey r13 = (io.ktor.network.tls.CertificateAndKey) r13
            io.ktor.network.tls.TLSHandshakeType r14 = io.ktor.network.tls.TLSHandshakeType.Certificate
            io.ktor.network.tls.TLSClientHandshake$sendClientCertificate$2 r2 = new io.ktor.network.tls.TLSClientHandshake$sendClientCertificate$2
            r2.<init>(r13)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            r0.L$0 = r13
            r0.label = r3
            java.lang.Object r14 = r12.sendHandshakeRecord(r14, r2, r0)
            if (r14 != r1) goto L_0x0125
            return r1
        L_0x0125:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.sendClientCertificate(io.ktor.network.tls.CertificateInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object sendClientCertificateVerify(CertificateInfo certificateInfo, CertificateAndKey certificateAndKey, Continuation<? super Unit> continuation) {
        HashAndSign hashAndSign;
        X509Certificate x509Certificate = (X509Certificate) ArraysKt.first((T[]) certificateAndKey.getCertificateChain());
        HashAndSign[] hashAndSign2 = certificateInfo.getHashAndSign();
        int length = hashAndSign2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                hashAndSign = null;
                break;
            }
            hashAndSign = hashAndSign2[i];
            if (StringsKt.equals(hashAndSign.getName(), x509Certificate.getSigAlgName(), true)) {
                break;
            }
            i++;
        }
        if (hashAndSign == null) {
            return Unit.INSTANCE;
        }
        if (hashAndSign.getSign() == SignatureAlgorithm.DSA) {
            return Unit.INSTANCE;
        }
        Signature instance = Signature.getInstance(((X509Certificate) ArraysKt.first((T[]) certificateAndKey.getCertificateChain())).getSigAlgName());
        Intrinsics.checkNotNull(instance);
        instance.initSign(certificateAndKey.getKey());
        Object sendHandshakeRecord = sendHandshakeRecord(TLSHandshakeType.CertificateVerify, new TLSClientHandshake$sendClientCertificateVerify$2(hashAndSign, this, instance), continuation);
        return sendHandshakeRecord == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendHandshakeRecord : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendChangeCipherSpec(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof io.ktor.network.tls.TLSClientHandshake$sendChangeCipherSpec$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            io.ktor.network.tls.TLSClientHandshake$sendChangeCipherSpec$1 r0 = (io.ktor.network.tls.TLSClientHandshake$sendChangeCipherSpec$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSClientHandshake$sendChangeCipherSpec$1 r0 = new io.ktor.network.tls.TLSClientHandshake$sendChangeCipherSpec$1
            r0.<init>(r12, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.core.ByteReadPacket r0 = (io.ktor.utils.io.core.ByteReadPacket) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x002e }
            goto L_0x0061
        L_0x002e:
            r13 = move-exception
            goto L_0x0068
        L_0x0030:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r13)
            io.ktor.utils.io.core.BytePacketBuilder r13 = new io.ktor.utils.io.core.BytePacketBuilder
            r2 = 0
            r13.<init>(r2, r3, r2)
            r13.writeByte(r3)     // Catch:{ all -> 0x006c }
            io.ktor.utils.io.core.ByteReadPacket r13 = r13.build()     // Catch:{ all -> 0x006c }
            kotlinx.coroutines.channels.SendChannel<io.ktor.network.tls.TLSRecord> r2 = r12.output     // Catch:{ all -> 0x0064 }
            io.ktor.network.tls.TLSRecord r10 = new io.ktor.network.tls.TLSRecord     // Catch:{ all -> 0x0064 }
            io.ktor.network.tls.TLSRecordType r5 = io.ktor.network.tls.TLSRecordType.ChangeCipherSpec     // Catch:{ all -> 0x0064 }
            r8 = 2
            r9 = 0
            r6 = 0
            r4 = r10
            r7 = r13
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0064 }
            r0.L$0 = r13     // Catch:{ all -> 0x0064 }
            r0.label = r3     // Catch:{ all -> 0x0064 }
            java.lang.Object r13 = r2.send(r10, r0)     // Catch:{ all -> 0x0064 }
            if (r13 != r1) goto L_0x0061
            return r1
        L_0x0061:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x0064:
            r0 = move-exception
            r11 = r0
            r0 = r13
            r13 = r11
        L_0x0068:
            r0.release()
            throw r13
        L_0x006c:
            r0 = move-exception
            r13.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.sendChangeCipherSpec(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object sendClientFinished(SecretKeySpec secretKeySpec, Continuation<? super Unit> continuation) {
        BytePacketBuilder bytePacketBuilder = this.digest;
        TLSServerHello tLSServerHello = this.serverHello;
        if (tLSServerHello == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serverHello");
            tLSServerHello = null;
        }
        Object sendHandshakeRecord = sendHandshakeRecord(TLSHandshakeType.Finished, new TLSClientHandshake$sendClientFinished$2(RenderKt.finished(Digest.m1465doHashimpl(bytePacketBuilder, tLSServerHello.getCipherSuite().getHash().getOpenSSLName()), secretKeySpec)), continuation);
        return sendHandshakeRecord == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendHandshakeRecord : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object receiveServerFinished(kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            boolean r2 = r1 instanceof io.ktor.network.tls.TLSClientHandshake$receiveServerFinished$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            io.ktor.network.tls.TLSClientHandshake$receiveServerFinished$1 r2 = (io.ktor.network.tls.TLSClientHandshake$receiveServerFinished$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            io.ktor.network.tls.TLSClientHandshake$receiveServerFinished$1 r2 = new io.ktor.network.tls.TLSClientHandshake$receiveServerFinished$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x003a
            if (r4 != r5) goto L_0x0032
            java.lang.Object r2 = r2.L$0
            io.ktor.network.tls.TLSClientHandshake r2 = (io.ktor.network.tls.TLSClientHandshake) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x004b
        L_0x0032:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.channels.ReceiveChannel<io.ktor.network.tls.TLSHandshake> r1 = r0.handshakes
            r2.L$0 = r0
            r2.label = r5
            java.lang.Object r1 = r1.receive(r2)
            if (r1 != r3) goto L_0x004a
            return r3
        L_0x004a:
            r2 = r0
        L_0x004b:
            io.ktor.network.tls.TLSHandshake r1 = (io.ktor.network.tls.TLSHandshake) r1
            io.ktor.network.tls.TLSHandshakeType r3 = r1.getType()
            io.ktor.network.tls.TLSHandshakeType r4 = io.ktor.network.tls.TLSHandshakeType.Finished
            r6 = 2
            r7 = 0
            if (r3 != r4) goto L_0x00d4
            io.ktor.utils.io.core.ByteReadPacket r1 = r1.getPacket()
            r3 = 0
            byte[] r8 = io.ktor.utils.io.core.StringsKt.readBytes$default(r1, r3, r5, r7)
            io.ktor.utils.io.core.BytePacketBuilder r1 = r2.digest
            io.ktor.network.tls.TLSServerHello r3 = r2.serverHello
            if (r3 != 0) goto L_0x006c
            java.lang.String r3 = "serverHello"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r3 = r7
        L_0x006c:
            io.ktor.network.tls.CipherSuite r3 = r3.getCipherSuite()
            io.ktor.network.tls.extensions.HashAlgorithm r3 = r3.getHash()
            java.lang.String r3 = r3.getOpenSSLName()
            byte[] r1 = io.ktor.network.tls.Digest.m1465doHashimpl(r1, r3)
            javax.crypto.spec.SecretKeySpec r2 = r2.masterSecret
            if (r2 != 0) goto L_0x0086
            java.lang.String r2 = "masterSecret"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r2 = r7
        L_0x0086:
            javax.crypto.SecretKey r2 = (javax.crypto.SecretKey) r2
            int r3 = r8.length
            byte[] r9 = io.ktor.network.tls.RenderKt.serverFinished(r1, r2, r3)
            boolean r1 = java.util.Arrays.equals(r8, r9)
            if (r1 == 0) goto L_0x0096
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x0096:
            io.ktor.network.tls.TLSException r1 = new io.ktor.network.tls.TLSException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Handshake: ServerFinished verification failed:\n                |Expected: "
            r2.<init>(r3)
            r16 = 63
            r17 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            java.lang.String r3 = kotlin.collections.ArraysKt.joinToString$default((byte[]) r9, (java.lang.CharSequence) r10, (java.lang.CharSequence) r11, (java.lang.CharSequence) r12, (int) r13, (java.lang.CharSequence) r14, (kotlin.jvm.functions.Function1) r15, (int) r16, (java.lang.Object) r17)
            r2.append(r3)
            java.lang.String r3 = "\n                |Actual: "
            r2.append(r3)
            r15 = 63
            r16 = 0
            r9 = 0
            r12 = 0
            r13 = 0
            java.lang.String r3 = kotlin.collections.ArraysKt.joinToString$default((byte[]) r8, (java.lang.CharSequence) r9, (java.lang.CharSequence) r10, (java.lang.CharSequence) r11, (int) r12, (java.lang.CharSequence) r13, (kotlin.jvm.functions.Function1) r14, (int) r15, (java.lang.Object) r16)
            r2.append(r3)
            java.lang.String r3 = "\n                "
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = kotlin.text.StringsKt.trimMargin$default(r2, r7, r5, r7)
            r1.<init>(r2, r7, r6, r7)
            throw r1
        L_0x00d4:
            io.ktor.network.tls.TLSException r2 = new io.ktor.network.tls.TLSException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Finished handshake expected, received: "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1, r7, r6, r7)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.receiveServerFinished(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendHandshakeRecord(io.ktor.network.tls.TLSHandshakeType r11, kotlin.jvm.functions.Function1<? super io.ktor.utils.io.core.BytePacketBuilder, kotlin.Unit> r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof io.ktor.network.tls.TLSClientHandshake$sendHandshakeRecord$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            io.ktor.network.tls.TLSClientHandshake$sendHandshakeRecord$1 r0 = (io.ktor.network.tls.TLSClientHandshake$sendHandshakeRecord$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSClientHandshake$sendHandshakeRecord$1 r0 = new io.ktor.network.tls.TLSClientHandshake$sendHandshakeRecord$1
            r0.<init>(r10, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r11 = r0.L$0
            io.ktor.network.tls.TLSRecord r11 = (io.ktor.network.tls.TLSRecord) r11
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x002e }
            goto L_0x0079
        L_0x002e:
            r12 = move-exception
            goto L_0x007c
        L_0x0030:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r13)
            io.ktor.utils.io.core.BytePacketBuilder r13 = new io.ktor.utils.io.core.BytePacketBuilder
            r2 = 0
            r13.<init>(r2, r3, r2)
            r12.invoke(r13)     // Catch:{ all -> 0x0089 }
            io.ktor.utils.io.core.ByteReadPacket r12 = r13.build()     // Catch:{ all -> 0x0089 }
            io.ktor.utils.io.core.BytePacketBuilder r13 = new io.ktor.utils.io.core.BytePacketBuilder
            r13.<init>(r2, r3, r2)
            long r4 = r12.getRemaining()     // Catch:{ all -> 0x0084 }
            int r2 = (int) r4     // Catch:{ all -> 0x0084 }
            io.ktor.network.tls.RenderKt.writeTLSHandshakeType(r13, r11, r2)     // Catch:{ all -> 0x0084 }
            r13.writePacket(r12)     // Catch:{ all -> 0x0084 }
            io.ktor.utils.io.core.ByteReadPacket r7 = r13.build()     // Catch:{ all -> 0x0084 }
            io.ktor.utils.io.core.BytePacketBuilder r11 = r10.digest
            io.ktor.network.tls.Digest.m1470updateimpl(r11, r7)
            io.ktor.network.tls.TLSRecord r11 = new io.ktor.network.tls.TLSRecord
            io.ktor.network.tls.TLSRecordType r5 = io.ktor.network.tls.TLSRecordType.Handshake
            r8 = 2
            r9 = 0
            r6 = 0
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            kotlinx.coroutines.channels.SendChannel<io.ktor.network.tls.TLSRecord> r12 = r10.output     // Catch:{ all -> 0x002e }
            r0.L$0 = r11     // Catch:{ all -> 0x002e }
            r0.label = r3     // Catch:{ all -> 0x002e }
            java.lang.Object r11 = r12.send(r11, r0)     // Catch:{ all -> 0x002e }
            if (r11 != r1) goto L_0x0079
            return r1
        L_0x0079:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x007c:
            io.ktor.utils.io.core.ByteReadPacket r11 = r11.getPacket()
            r11.release()
            throw r12
        L_0x0084:
            r11 = move-exception
            r13.release()
            throw r11
        L_0x0089:
            r11 = move-exception
            r13.release()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake.sendHandshakeRecord(io.ktor.network.tls.TLSHandshakeType, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
