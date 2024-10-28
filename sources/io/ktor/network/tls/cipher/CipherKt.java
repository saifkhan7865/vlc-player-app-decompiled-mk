package io.ktor.network.tls.cipher;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\u001a\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\f"}, d2 = {"EmptyByteBuffer", "Ljava/nio/ByteBuffer;", "getEmptyByteBuffer", "()Ljava/nio/ByteBuffer;", "set", "", "", "offset", "", "data", "", "", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Cipher.kt */
public final class CipherKt {
    private static final ByteBuffer EmptyByteBuffer;

    public static final void set(byte[] bArr, int i, long j) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2 + i] = (byte) ((int) (j >>> ((7 - i2) * 8)));
        }
    }

    public static final void set(byte[] bArr, int i, short s) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        for (int i2 = 0; i2 < 2; i2++) {
            bArr[i2 + i] = (byte) (s >>> ((1 - i2) * 8));
        }
    }

    static {
        ByteBuffer allocate = ByteBuffer.allocate(0);
        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(0)");
        EmptyByteBuffer = allocate;
    }

    public static final ByteBuffer getEmptyByteBuffer() {
        return EmptyByteBuffer;
    }
}
