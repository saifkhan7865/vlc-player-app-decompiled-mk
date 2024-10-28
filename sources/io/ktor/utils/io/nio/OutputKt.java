package io.ktor.utils.io.nio;

import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"asOutput", "Lio/ktor/utils/io/core/Output;", "Ljava/nio/channels/WritableByteChannel;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Output.kt */
public final class OutputKt {
    public static /* synthetic */ Output asOutput$default(WritableByteChannel writableByteChannel, ObjectPool<ChunkBuffer> objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            objectPool = ChunkBuffer.Companion.getPool();
        }
        return asOutput(writableByteChannel, objectPool);
    }

    public static final Output asOutput(WritableByteChannel writableByteChannel, ObjectPool<ChunkBuffer> objectPool) {
        Intrinsics.checkNotNullParameter(writableByteChannel, "<this>");
        Intrinsics.checkNotNullParameter(objectPool, "pool");
        return new ChannelAsOutput(objectPool, writableByteChannel);
    }
}
