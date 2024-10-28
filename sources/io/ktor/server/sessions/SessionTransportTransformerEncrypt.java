package io.ktor.server.sessions;

import io.ktor.util.CryptoKt;
import java.nio.charset.Charset;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u0000 -2\u00020\u0001:\u0001-BP\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012#\b\u0002\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eBN\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012#\b\u0002\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0012J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003H\u0002J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H\u0002J \u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003H\u0002J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u0003H\u0002J\u0012\u0010*\u001a\u0004\u0018\u00010\f2\u0006\u0010+\u001a\u00020\fH\u0016J\u0010\u0010,\u001a\u00020\f2\u0006\u0010+\u001a\u00020\fH\u0016R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR,\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001b¨\u0006."}, d2 = {"Lio/ktor/server/sessions/SessionTransportTransformerEncrypt;", "Lio/ktor/server/sessions/SessionTransportTransformer;", "encryptionKey", "", "signKey", "ivGenerator", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "size", "encryptAlgorithm", "", "signAlgorithm", "([B[BLkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;)V", "encryptionKeySpec", "Ljavax/crypto/spec/SecretKeySpec;", "signKeySpec", "(Ljavax/crypto/spec/SecretKeySpec;Ljavax/crypto/spec/SecretKeySpec;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;)V", "charset", "Ljava/nio/charset/Charset;", "getEncryptAlgorithm", "()Ljava/lang/String;", "encryptionKeySize", "getEncryptionKeySize", "()I", "getEncryptionKeySpec", "()Ljavax/crypto/spec/SecretKeySpec;", "getIvGenerator", "()Lkotlin/jvm/functions/Function1;", "getSignAlgorithm", "getSignKeySpec", "decrypt", "initVector", "encrypted", "encrypt", "decrypted", "encryptDecrypt", "mode", "input", "mac", "value", "transformRead", "transportValue", "transformWrite", "Companion", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTransportTransformerEncrypt.kt */
public final class SessionTransportTransformerEncrypt implements SessionTransportTransformer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Logger log = LoggerFactory.getLogger(Reflection.getOrCreateKotlinClass(SessionTransportTransformerEncrypt.class).getQualifiedName());
    private final Charset charset;
    private final String encryptAlgorithm;
    private final SecretKeySpec encryptionKeySpec;
    private final Function1<Integer, byte[]> ivGenerator;
    private final String signAlgorithm;
    private final SecretKeySpec signKeySpec;

    public SessionTransportTransformerEncrypt(SecretKeySpec secretKeySpec, SecretKeySpec secretKeySpec2, Function1<? super Integer, byte[]> function1, String str, String str2) {
        Intrinsics.checkNotNullParameter(secretKeySpec, "encryptionKeySpec");
        Intrinsics.checkNotNullParameter(secretKeySpec2, "signKeySpec");
        Intrinsics.checkNotNullParameter(function1, "ivGenerator");
        Intrinsics.checkNotNullParameter(str, "encryptAlgorithm");
        Intrinsics.checkNotNullParameter(str2, "signAlgorithm");
        this.encryptionKeySpec = secretKeySpec;
        this.signKeySpec = secretKeySpec2;
        this.ivGenerator = function1;
        this.encryptAlgorithm = str;
        this.signAlgorithm = str2;
        this.charset = Charsets.UTF_8;
        encrypt(function1.invoke(Integer.valueOf(getEncryptionKeySize())), new byte[0]);
        mac(new byte[0]);
    }

    public final SecretKeySpec getEncryptionKeySpec() {
        return this.encryptionKeySpec;
    }

    public final SecretKeySpec getSignKeySpec() {
        return this.signKeySpec;
    }

    public final Function1<Integer, byte[]> getIvGenerator() {
        return this.ivGenerator;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SessionTransportTransformerEncrypt(javax.crypto.spec.SecretKeySpec r7, javax.crypto.spec.SecretKeySpec r8, kotlin.jvm.functions.Function1 r9, java.lang.String r10, java.lang.String r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r13 = r12 & 4
            if (r13 == 0) goto L_0x0008
            io.ktor.server.sessions.SessionTransportTransformerEncrypt$1 r9 = io.ktor.server.sessions.SessionTransportTransformerEncrypt.AnonymousClass1.INSTANCE
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
        L_0x0008:
            r3 = r9
            r9 = r12 & 8
            if (r9 == 0) goto L_0x0016
            java.lang.String r10 = r7.getAlgorithm()
            java.lang.String r9 = "encryptionKeySpec.algorithm"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r9)
        L_0x0016:
            r4 = r10
            r9 = r12 & 16
            if (r9 == 0) goto L_0x0024
            java.lang.String r11 = r8.getAlgorithm()
            java.lang.String r9 = "signKeySpec.algorithm"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r9)
        L_0x0024:
            r5 = r11
            r0 = r6
            r1 = r7
            r2 = r8
            r0.<init>((javax.crypto.spec.SecretKeySpec) r1, (javax.crypto.spec.SecretKeySpec) r2, (kotlin.jvm.functions.Function1<? super java.lang.Integer, byte[]>) r3, (java.lang.String) r4, (java.lang.String) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionTransportTransformerEncrypt.<init>(javax.crypto.spec.SecretKeySpec, javax.crypto.spec.SecretKeySpec, kotlin.jvm.functions.Function1, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getEncryptAlgorithm() {
        return this.encryptAlgorithm;
    }

    public final String getSignAlgorithm() {
        return this.signAlgorithm;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lio/ktor/server/sessions/SessionTransportTransformerEncrypt$Companion;", "", "()V", "log", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionTransportTransformerEncrypt.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final int getEncryptionKeySize() {
        return this.encryptionKeySpec.getEncoded().length;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SessionTransportTransformerEncrypt(byte[] bArr, byte[] bArr2, Function1 function1, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, bArr2, (Function1<? super Integer, byte[]>) (i & 4) != 0 ? AnonymousClass2.INSTANCE : function1, (i & 8) != 0 ? "AES" : str, (i & 16) != 0 ? "HmacSHA256" : str2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SessionTransportTransformerEncrypt(byte[] bArr, byte[] bArr2, Function1<? super Integer, byte[]> function1, String str, String str2) {
        this(new SecretKeySpec(bArr, str), new SecretKeySpec(bArr2, str2), (Function1) function1, (String) null, (String) null, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(bArr, "encryptionKey");
        Intrinsics.checkNotNullParameter(bArr2, "signKey");
        Intrinsics.checkNotNullParameter(function1, "ivGenerator");
        Intrinsics.checkNotNullParameter(str, "encryptAlgorithm");
        Intrinsics.checkNotNullParameter(str2, "signAlgorithm");
    }

    public String transformRead(String str) {
        Intrinsics.checkNotNullParameter(str, "transportValue");
        try {
            String substringAfterLast = StringsKt.substringAfterLast(str, '/', "");
            byte[] hex = CryptoKt.hex(StringsKt.substringBeforeLast$default(str, '/', (String) null, 2, (Object) null));
            byte[] hex2 = CryptoKt.hex(StringsKt.substringBeforeLast$default(substringAfterLast, (char) AbstractJsonLexerKt.COLON, (String) null, 2, (Object) null));
            String substringAfterLast2 = StringsKt.substringAfterLast(substringAfterLast, (char) AbstractJsonLexerKt.COLON, "");
            byte[] decrypt = decrypt(hex, hex2);
            if (!Intrinsics.areEqual((Object) CryptoKt.hex(mac(decrypt)), (Object) substringAfterLast2)) {
                return null;
            }
            return new String(decrypt, this.charset);
        } catch (Throwable th) {
            Logger logger = log;
            if (logger.isDebugEnabled()) {
                logger.debug(th.toString());
            }
            return null;
        }
    }

    public String transformWrite(String str) {
        Intrinsics.checkNotNullParameter(str, "transportValue");
        byte[] invoke = this.ivGenerator.invoke(Integer.valueOf(getEncryptionKeySize()));
        byte[] bytes = str.getBytes(this.charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] encrypt = encrypt(invoke, bytes);
        byte[] mac = mac(bytes);
        return CryptoKt.hex(invoke) + '/' + CryptoKt.hex(encrypt) + AbstractJsonLexerKt.COLON + CryptoKt.hex(mac);
    }

    private final byte[] encrypt(byte[] bArr, byte[] bArr2) {
        return encryptDecrypt(1, bArr, bArr2);
    }

    private final byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return encryptDecrypt(2, bArr, bArr2);
    }

    private final byte[] encryptDecrypt(int i, byte[] bArr, byte[] bArr2) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
        Cipher instance = Cipher.getInstance(this.encryptAlgorithm + "/CBC/PKCS5PADDING");
        instance.init(i, this.encryptionKeySpec, ivParameterSpec);
        byte[] doFinal = instance.doFinal(bArr2);
        Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(input)");
        return doFinal;
    }

    private final byte[] mac(byte[] bArr) {
        Mac instance = Mac.getInstance(this.signAlgorithm);
        instance.init(this.signKeySpec);
        byte[] doFinal = instance.doFinal(bArr);
        Intrinsics.checkNotNullExpressionValue(doFinal, "getInstance(signAlgorith…     doFinal(value)\n    }");
        return doFinal;
    }
}
