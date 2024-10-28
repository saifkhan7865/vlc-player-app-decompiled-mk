package io.ktor.utils.io.core.internal;

import io.ktor.utils.io.pool.ObjectPool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0007\u001a\u00020\u0002H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"io/ktor/utils/io/core/internal/ChunkBuffer$Companion$EmptyPool$1", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "capacity", "", "getCapacity", "()I", "borrow", "dispose", "", "recycle", "instance", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ChunkBuffer.kt */
public final class ChunkBuffer$Companion$EmptyPool$1 implements ObjectPool<ChunkBuffer> {
    public void dispose() {
    }

    public int getCapacity() {
        return 1;
    }

    ChunkBuffer$Companion$EmptyPool$1() {
    }

    public void close() {
        ObjectPool.DefaultImpls.close(this);
    }

    public ChunkBuffer borrow() {
        return ChunkBuffer.Companion.getEmpty();
    }

    public void recycle(ChunkBuffer chunkBuffer) {
        Intrinsics.checkNotNullParameter(chunkBuffer, "instance");
        if (chunkBuffer != ChunkBuffer.Companion.getEmpty()) {
            throw new IllegalArgumentException("Only ChunkBuffer.Empty instance could be recycled.".toString());
        }
    }
}
