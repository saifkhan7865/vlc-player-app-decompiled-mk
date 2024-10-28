package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ByteArrayPoolKt;
import io.ktor.utils.io.pool.ObjectPool;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0014J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/http/cio/MultipartInput;", "Lio/ktor/utils/io/core/Input;", "head", "Ljava/nio/ByteBuffer;", "tail", "Lio/ktor/utils/io/ByteReadChannel;", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;)V", "closeSource", "", "fill", "", "destination", "Lio/ktor/utils/io/bits/Memory;", "offset", "length", "fill-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOMultipartDataBase.kt */
final class MultipartInput extends Input {
    private final ByteBuffer head;
    /* access modifiers changed from: private */
    public final ByteReadChannel tail;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MultipartInput(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel) {
        super((ChunkBuffer) null, 0, (ObjectPool) null, 7, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(byteBuffer, "head");
        Intrinsics.checkNotNullParameter(byteReadChannel, "tail");
        this.head = byteBuffer;
        this.tail = byteReadChannel;
    }

    /* access modifiers changed from: protected */
    /* renamed from: fill-62zg_DM  reason: not valid java name */
    public int m1444fill62zg_DM(ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, RtspHeaders.Values.DESTINATION);
        if (!this.head.hasRemaining()) {
            return ((Number) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new MultipartInput$fill$1(this, i2, byteBuffer, i, (Continuation<? super MultipartInput$fill$1>) null), 1, (Object) null)).intValue();
        }
        if (!byteBuffer.hasArray() || byteBuffer.isReadOnly()) {
            byte[] borrow = ByteArrayPoolKt.getByteArrayPool().borrow();
            try {
                int min = Math.min(this.head.remaining(), i2);
                this.head.get(borrow, 0, min);
                ByteBuffer order = ByteBuffer.wrap(borrow, 0, min).slice().order(ByteOrder.BIG_ENDIAN);
                Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
                Memory.m1510copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, min, i);
                return min;
            } finally {
                ByteArrayPoolKt.getByteArrayPool().recycle(borrow);
            }
        } else {
            int min2 = Math.min(this.head.remaining(), i2);
            this.head.get(byteBuffer.array(), i, min2);
            return RangesKt.coerceAtLeast(min2, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void closeSource() {
        ByteReadChannelKt.cancel(this.tail);
    }
}
