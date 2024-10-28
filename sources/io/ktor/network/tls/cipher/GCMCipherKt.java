package io.ktor.network.tls.cipher;

import io.ktor.network.tls.CipherSuite;
import io.ktor.network.tls.KeysKt;
import io.ktor.network.tls.TLSRecordType;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a8\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¨\u0006\u000e"}, d2 = {"gcmDecryptCipher", "Ljavax/crypto/Cipher;", "suite", "Lio/ktor/network/tls/CipherSuite;", "keyMaterial", "", "recordType", "Lio/ktor/network/tls/TLSRecordType;", "recordLength", "", "recordIv", "", "recordId", "gcmEncryptCipher", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: GCMCipher.kt */
public final class GCMCipherKt {
    /* access modifiers changed from: private */
    public static final Cipher gcmEncryptCipher(CipherSuite cipherSuite, byte[] bArr, TLSRecordType tLSRecordType, int i, long j, long j2) {
        Cipher instance = Cipher.getInstance(cipherSuite.getJdkCipherName());
        Intrinsics.checkNotNull(instance);
        SecretKeySpec clientKey = KeysKt.clientKey(bArr, cipherSuite);
        byte[] copyOf = Arrays.copyOf(KeysKt.clientIV(bArr, cipherSuite), cipherSuite.getIvLength());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        CipherKt.set(copyOf, cipherSuite.getFixedIvLength(), j);
        instance.init(1, clientKey, new GCMParameterSpec(cipherSuite.getCipherTagSizeInBytes() * 8, copyOf));
        byte[] bArr2 = new byte[13];
        CipherKt.set(bArr2, 0, j2);
        bArr2[8] = (byte) tLSRecordType.getCode();
        bArr2[9] = 3;
        bArr2[10] = 3;
        CipherKt.set(bArr2, 11, (short) i);
        instance.updateAAD(bArr2);
        return instance;
    }

    /* access modifiers changed from: private */
    public static final Cipher gcmDecryptCipher(CipherSuite cipherSuite, byte[] bArr, TLSRecordType tLSRecordType, int i, long j, long j2) {
        Cipher instance = Cipher.getInstance(cipherSuite.getJdkCipherName());
        Intrinsics.checkNotNull(instance);
        SecretKeySpec serverKey = KeysKt.serverKey(bArr, cipherSuite);
        byte[] copyOf = Arrays.copyOf(KeysKt.serverIV(bArr, cipherSuite), cipherSuite.getIvLength());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        CipherKt.set(copyOf, cipherSuite.getFixedIvLength(), j);
        instance.init(2, serverKey, new GCMParameterSpec(cipherSuite.getCipherTagSizeInBytes() * 8, copyOf));
        int ivLength = (i - (cipherSuite.getIvLength() - cipherSuite.getFixedIvLength())) - cipherSuite.getCipherTagSizeInBytes();
        if (ivLength < 65536) {
            byte[] bArr2 = new byte[13];
            CipherKt.set(bArr2, 0, j2);
            bArr2[8] = (byte) tLSRecordType.getCode();
            bArr2[9] = 3;
            bArr2[10] = 3;
            CipherKt.set(bArr2, 11, (short) ivLength);
            instance.updateAAD(bArr2);
            return instance;
        }
        throw new IllegalStateException(("Content size should fit in 2 bytes, actual: " + ivLength).toString());
    }
}
