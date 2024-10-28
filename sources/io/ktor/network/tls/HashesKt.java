package io.ktor.network.tls;

import java.security.Key;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0000\u001a*\u0010\b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\f"}, d2 = {"PRF", "", "secret", "Ljavax/crypto/SecretKey;", "label", "seed", "requiredLength", "", "P_hash", "mac", "Ljavax/crypto/Mac;", "secretKey", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Hashes.kt */
public final class HashesKt {
    public static /* synthetic */ byte[] PRF$default(SecretKey secretKey, byte[] bArr, byte[] bArr2, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 12;
        }
        return PRF(secretKey, bArr, bArr2, i);
    }

    public static final byte[] PRF(SecretKey secretKey, byte[] bArr, byte[] bArr2, int i) {
        Intrinsics.checkNotNullParameter(secretKey, "secret");
        Intrinsics.checkNotNullParameter(bArr, AnnotatedPrivateKey.LABEL);
        Intrinsics.checkNotNullParameter(bArr2, "seed");
        byte[] plus = ArraysKt.plus(bArr, bArr2);
        Mac instance = Mac.getInstance(secretKey.getAlgorithm());
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(secret.algorithm)");
        return P_hash(plus, instance, secretKey, i);
    }

    static /* synthetic */ byte[] P_hash$default(byte[] bArr, Mac mac, SecretKey secretKey, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 12;
        }
        return P_hash(bArr, mac, secretKey, i);
    }

    private static final byte[] P_hash(byte[] bArr, Mac mac, SecretKey secretKey, int i) {
        if (i >= 12) {
            byte[] bArr2 = new byte[0];
            byte[] bArr3 = bArr;
            while (bArr2.length < i) {
                mac.reset();
                Key key = secretKey;
                mac.init(key);
                mac.update(bArr3);
                bArr3 = mac.doFinal();
                Intrinsics.checkNotNullExpressionValue(bArr3, "mac.doFinal()");
                mac.reset();
                mac.init(key);
                mac.update(bArr3);
                mac.update(bArr);
                byte[] doFinal = mac.doFinal();
                Intrinsics.checkNotNullExpressionValue(doFinal, "mac.doFinal()");
                bArr2 = ArraysKt.plus(bArr2, doFinal);
            }
            byte[] copyOf = Arrays.copyOf(bArr2, i);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            return copyOf;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
