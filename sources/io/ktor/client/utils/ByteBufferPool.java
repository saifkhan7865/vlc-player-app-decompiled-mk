package io.ktor.client.utils;

import io.ktor.util.InternalAPI;
import io.ktor.utils.io.pool.DefaultPool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@InternalAPI
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0014J\b\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lio/ktor/client/utils/ByteBufferPool;", "Lio/ktor/utils/io/pool/DefaultPool;", "Ljava/nio/ByteBuffer;", "()V", "clearInstance", "instance", "produceInstance", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.ERROR, message = "ByteBufferPool is moved to `io` module", replaceWith = @ReplaceWith(expression = "ByteBufferPool", imports = {"io.ktor.utils.io.pool.ByteBufferPool"}))
/* compiled from: CIOJvm.kt */
public final class ByteBufferPool extends DefaultPool<ByteBuffer> {
    public ByteBufferPool() {
        super(1000);
    }

    /* access modifiers changed from: protected */
    public ByteBuffer produceInstance() {
        ByteBuffer allocate = ByteBuffer.allocate(4096);
        Intrinsics.checkNotNull(allocate);
        return allocate;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer clearInstance(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "instance");
        byteBuffer.clear();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        return byteBuffer;
    }
}
