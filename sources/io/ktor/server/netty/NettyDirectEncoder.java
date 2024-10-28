package io.ktor.server.netty;

import androidx.core.app.NotificationCompat;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.HttpContent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\nH\u0014J \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0005H\u0014¨\u0006\u000e"}, d2 = {"Lio/ktor/server/netty/NettyDirectEncoder;", "Lio/netty/handler/codec/MessageToByteEncoder;", "Lio/netty/handler/codec/http/HttpContent;", "()V", "allocateBuffer", "Lio/netty/buffer/ByteBuf;", "ctx", "Lio/netty/channel/ChannelHandlerContext;", "msg", "preferDirect", "", "encode", "", "out", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyDirectEncoder.kt */
public final class NettyDirectEncoder extends MessageToByteEncoder<HttpContent> {
    /* access modifiers changed from: protected */
    public void encode(ChannelHandlerContext channelHandlerContext, HttpContent httpContent, ByteBuf byteBuf) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "ctx");
        Intrinsics.checkNotNullParameter(httpContent, NotificationCompat.CATEGORY_MESSAGE);
        Intrinsics.checkNotNullParameter(byteBuf, "out");
        byteBuf.writeBytes(httpContent.content());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r3 = r3.content();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.netty.buffer.ByteBuf allocateBuffer(io.netty.channel.ChannelHandlerContext r2, io.netty.handler.codec.http.HttpContent r3, boolean r4) {
        /*
            r1 = this;
            java.lang.String r0 = "ctx"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            if (r3 == 0) goto L_0x0012
            io.netty.buffer.ByteBuf r3 = r3.content()
            if (r3 == 0) goto L_0x0012
            int r3 = r3.readableBytes()
            goto L_0x0013
        L_0x0012:
            r3 = 0
        L_0x0013:
            if (r3 != 0) goto L_0x001d
            io.netty.buffer.ByteBuf r2 = io.netty.buffer.Unpooled.EMPTY_BUFFER
            java.lang.String r3 = "{\n            Unpooled.EMPTY_BUFFER\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            goto L_0x003a
        L_0x001d:
            if (r4 == 0) goto L_0x002d
            io.netty.buffer.ByteBufAllocator r2 = r2.alloc()
            io.netty.buffer.ByteBuf r2 = r2.ioBuffer(r3)
            java.lang.String r3 = "{\n            ctx.alloc().ioBuffer(size)\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            goto L_0x003a
        L_0x002d:
            io.netty.buffer.ByteBufAllocator r2 = r2.alloc()
            io.netty.buffer.ByteBuf r2 = r2.heapBuffer(r3)
            java.lang.String r3 = "ctx.alloc().heapBuffer(size)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        L_0x003a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.NettyDirectEncoder.allocateBuffer(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.HttpContent, boolean):io.netty.buffer.ByteBuf");
    }
}
