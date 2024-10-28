package io.ktor.server.netty.http2;

import io.netty.buffer.ByteBuf;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "bb", "Ljava/nio/ByteBuffer;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpFrameAdapter.kt */
final class HttpFrameAdapterKt$http2frameLoop$2 extends Lambda implements Function1<ByteBuffer, Unit> {
    final /* synthetic */ ByteBuf $content;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpFrameAdapterKt$http2frameLoop$2(ByteBuf byteBuf) {
        super(1);
        this.$content = byteBuf;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ByteBuffer) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "bb");
        int readableBytes = this.$content.readableBytes();
        if (byteBuffer.remaining() > readableBytes) {
            int limit = byteBuffer.limit();
            byteBuffer.limit(byteBuffer.position() + readableBytes);
            this.$content.readBytes(byteBuffer);
            byteBuffer.limit(limit);
            return;
        }
        this.$content.readBytes(byteBuffer);
    }
}
