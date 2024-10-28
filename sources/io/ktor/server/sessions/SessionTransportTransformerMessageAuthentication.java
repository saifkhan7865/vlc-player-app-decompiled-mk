package io.ktor.server.sessions;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.util.CryptoKt;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lio/ktor/server/sessions/SessionTransportTransformerMessageAuthentication;", "Lio/ktor/server/sessions/SessionTransportTransformer;", "key", "", "algorithm", "", "([BLjava/lang/String;)V", "keySpec", "Ljavax/crypto/spec/SecretKeySpec;", "(Ljavax/crypto/spec/SecretKeySpec;Ljava/lang/String;)V", "getAlgorithm", "()Ljava/lang/String;", "getKeySpec", "()Ljavax/crypto/spec/SecretKeySpec;", "mac", "value", "transformRead", "transportValue", "transformWrite", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTransportTransformerMessageAuthentication.kt */
public final class SessionTransportTransformerMessageAuthentication implements SessionTransportTransformer {
    private final String algorithm;
    private final SecretKeySpec keySpec;

    public SessionTransportTransformerMessageAuthentication(SecretKeySpec secretKeySpec, String str) {
        Intrinsics.checkNotNullParameter(secretKeySpec, "keySpec");
        Intrinsics.checkNotNullParameter(str, "algorithm");
        this.keySpec = secretKeySpec;
        this.algorithm = str;
    }

    public final SecretKeySpec getKeySpec() {
        return this.keySpec;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SessionTransportTransformerMessageAuthentication(SecretKeySpec secretKeySpec, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(secretKeySpec, (i & 2) != 0 ? "HmacSHA256" : str);
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SessionTransportTransformerMessageAuthentication(byte[] bArr, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i & 2) != 0 ? "HmacSHA256" : str);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SessionTransportTransformerMessageAuthentication(byte[] bArr, String str) {
        this(new SecretKeySpec(bArr, str), str);
        Intrinsics.checkNotNullParameter(bArr, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str, "algorithm");
    }

    public String transformRead(String str) {
        Intrinsics.checkNotNullParameter(str, "transportValue");
        String substringAfterLast = StringsKt.substringAfterLast(str, '/', "");
        String substringBeforeLast$default = StringsKt.substringBeforeLast$default(str, '/', (String) null, 2, (Object) null);
        byte[] bytes = mac(substringBeforeLast$default).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] bytes2 = substringAfterLast.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        if (MessageDigest.isEqual(bytes, bytes2)) {
            return substringBeforeLast$default;
        }
        return null;
    }

    public String transformWrite(String str) {
        Intrinsics.checkNotNullParameter(str, "transportValue");
        return str + '/' + mac(str);
    }

    private final String mac(String str) {
        Mac instance = Mac.getInstance(this.algorithm);
        instance.init(this.keySpec);
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] doFinal = instance.doFinal(bytes);
        Intrinsics.checkNotNullExpressionValue(doFinal, "mac.doFinal(value.toByteArray())");
        return CryptoKt.hex(doFinal);
    }
}
