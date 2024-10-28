package io.ktor.utils.io.internal;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"EmptyByteBuffer", "Ljava/nio/ByteBuffer;", "getEmptyByteBuffer", "()Ljava/nio/ByteBuffer;", "EmptyCapacity", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "getEmptyCapacity", "()Lio/ktor/utils/io/internal/RingBufferCapacity;", "RESERVED_SIZE", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ReadWriteBufferState.kt */
public final class ReadWriteBufferStateKt {
    private static final ByteBuffer EmptyByteBuffer;
    private static final RingBufferCapacity EmptyCapacity = new RingBufferCapacity(0);
    public static final int RESERVED_SIZE = 8;

    static {
        ByteBuffer allocate = ByteBuffer.allocate(0);
        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(0)");
        EmptyByteBuffer = allocate;
    }

    public static final ByteBuffer getEmptyByteBuffer() {
        return EmptyByteBuffer;
    }

    public static final RingBufferCapacity getEmptyCapacity() {
        return EmptyCapacity;
    }
}
