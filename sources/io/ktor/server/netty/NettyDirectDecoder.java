package io.ktor.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0014¨\u0006\f"}, d2 = {"Lio/ktor/server/netty/NettyDirectDecoder;", "Lio/netty/handler/codec/ByteToMessageDecoder;", "()V", "decode", "", "ctx", "Lio/netty/channel/ChannelHandlerContext;", "buf", "Lio/netty/buffer/ByteBuf;", "out", "", "", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyDirectDecoder.kt */
public final class NettyDirectDecoder extends ByteToMessageDecoder {
    /* access modifiers changed from: protected */
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "ctx");
        Intrinsics.checkNotNullParameter(byteBuf, "buf");
        Intrinsics.checkNotNullParameter(list, "out");
        ByteBuf copy = byteBuf.copy();
        Intrinsics.checkNotNullExpressionValue(copy, "buf.copy()");
        list.add(copy);
        byteBuf.readerIndex(byteBuf.writerIndex());
    }
}
