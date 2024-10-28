package io.ktor.network.tls;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.bouncycastle.jcajce.spec.TLSKeyMaterialSpec;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a0\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0000\u001a \u0010\t\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a\u0014\u0010\u0017\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a\u0014\u0010\u0019\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a\u0014\u0010\u001a\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a\u0014\u0010\u001b\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003¨\u0006\u001c"}, d2 = {"CLIENT_FINISHED_LABEL", "", "getCLIENT_FINISHED_LABEL", "()[B", "KEY_EXPANSION_LABEL", "MASTER_SECRET_LABEL", "SERVER_FINISHED_LABEL", "getSERVER_FINISHED_LABEL", "keyMaterial", "masterSecret", "Ljavax/crypto/SecretKey;", "seed", "keySize", "", "macSize", "ivSize", "Ljavax/crypto/spec/SecretKeySpec;", "preMasterSecret", "clientRandom", "serverRandom", "clientIV", "suite", "Lio/ktor/network/tls/CipherSuite;", "clientKey", "clientMacKey", "serverIV", "serverKey", "serverMacKey", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Keys.kt */
public final class KeysKt {
    private static final byte[] CLIENT_FINISHED_LABEL;
    private static final byte[] KEY_EXPANSION_LABEL;
    private static final byte[] MASTER_SECRET_LABEL;
    private static final byte[] SERVER_FINISHED_LABEL;

    static {
        byte[] bytes = TLSKeyMaterialSpec.MASTER_SECRET.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        MASTER_SECRET_LABEL = bytes;
        byte[] bytes2 = TLSKeyMaterialSpec.KEY_EXPANSION.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        KEY_EXPANSION_LABEL = bytes2;
        byte[] bytes3 = "client finished".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");
        CLIENT_FINISHED_LABEL = bytes3;
        byte[] bytes4 = "server finished".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes4, "this as java.lang.String).getBytes(charset)");
        SERVER_FINISHED_LABEL = bytes4;
    }

    public static final byte[] getCLIENT_FINISHED_LABEL() {
        return CLIENT_FINISHED_LABEL;
    }

    public static final byte[] getSERVER_FINISHED_LABEL() {
        return SERVER_FINISHED_LABEL;
    }

    public static final SecretKeySpec clientMacKey(byte[] bArr, CipherSuite cipherSuite) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        return new SecretKeySpec(bArr, 0, cipherSuite.getMacStrengthInBytes(), cipherSuite.getHash().getMacName());
    }

    public static final SecretKeySpec serverMacKey(byte[] bArr, CipherSuite cipherSuite) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        return new SecretKeySpec(bArr, cipherSuite.getMacStrengthInBytes(), cipherSuite.getMacStrengthInBytes(), cipherSuite.getHash().getMacName());
    }

    public static final SecretKeySpec serverKey(byte[] bArr, CipherSuite cipherSuite) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        return new SecretKeySpec(bArr, (cipherSuite.getMacStrengthInBytes() * 2) + cipherSuite.getKeyStrengthInBytes(), cipherSuite.getKeyStrengthInBytes(), StringsKt.substringBefore$default(cipherSuite.getJdkCipherName(), "/", (String) null, 2, (Object) null));
    }

    public static final SecretKeySpec clientKey(byte[] bArr, CipherSuite cipherSuite) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        return new SecretKeySpec(bArr, cipherSuite.getMacStrengthInBytes() * 2, cipherSuite.getKeyStrengthInBytes(), StringsKt.substringBefore$default(cipherSuite.getJdkCipherName(), "/", (String) null, 2, (Object) null));
    }

    public static final byte[] clientIV(byte[] bArr, CipherSuite cipherSuite) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        return ArraysKt.copyOfRange(bArr, (cipherSuite.getMacStrengthInBytes() * 2) + (cipherSuite.getKeyStrengthInBytes() * 2), (cipherSuite.getMacStrengthInBytes() * 2) + (cipherSuite.getKeyStrengthInBytes() * 2) + cipherSuite.getFixedIvLength());
    }

    public static final byte[] serverIV(byte[] bArr, CipherSuite cipherSuite) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(cipherSuite, "suite");
        return ArraysKt.copyOfRange(bArr, (cipherSuite.getMacStrengthInBytes() * 2) + (cipherSuite.getKeyStrengthInBytes() * 2) + cipherSuite.getFixedIvLength(), (cipherSuite.getMacStrengthInBytes() * 2) + (cipherSuite.getKeyStrengthInBytes() * 2) + (cipherSuite.getFixedIvLength() * 2));
    }

    public static final byte[] keyMaterial(SecretKey secretKey, byte[] bArr, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(secretKey, "masterSecret");
        Intrinsics.checkNotNullParameter(bArr, "seed");
        return HashesKt.PRF(secretKey, KEY_EXPANSION_LABEL, bArr, (i2 * 2) + (i * 2) + (i3 * 2));
    }

    public static final SecretKeySpec masterSecret(SecretKey secretKey, byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNullParameter(secretKey, "preMasterSecret");
        Intrinsics.checkNotNullParameter(bArr, "clientRandom");
        Intrinsics.checkNotNullParameter(bArr2, "serverRandom");
        return new SecretKeySpec(HashesKt.PRF(secretKey, MASTER_SECRET_LABEL, ArraysKt.plus(bArr, bArr2), 48), secretKey.getAlgorithm());
    }
}
