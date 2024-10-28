package io.ktor.network.util;

import io.ktor.utils.io.pool.DefaultPool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0014J\b\u0010\t\u001a\u00020\u0002H\u0014J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0002H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lio/ktor/network/util/DirectByteBufferPool;", "Lio/ktor/utils/io/pool/DefaultPool;", "Ljava/nio/ByteBuffer;", "bufferSize", "", "size", "(II)V", "clearInstance", "instance", "produceInstance", "validateInstance", "", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.ERROR, message = "ByteBufferPool is moved to `io` module", replaceWith = @ReplaceWith(expression = "ByteBufferPool", imports = {"io.ktor.utils.io.pool.ByteBufferPool"}))
/* compiled from: Pools.kt */
public final class DirectByteBufferPool extends DefaultPool<ByteBuffer> {
    private final int bufferSize;

    public DirectByteBufferPool(int i, int i2) {
        super(i2);
        this.bufferSize = i;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer produceInstance() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.bufferSize);
        Intrinsics.checkNotNullExpressionValue(allocateDirect, "allocateDirect(bufferSize)");
        return allocateDirect;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer clearInstance(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "instance");
        byteBuffer.clear();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        return byteBuffer;
    }

    /* access modifiers changed from: protected */
    public void validateInstance(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "instance");
        if (!byteBuffer.isDirect()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (byteBuffer.capacity() != this.bufferSize) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }
}
