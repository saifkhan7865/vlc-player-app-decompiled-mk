package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

@ChannelHandler.Sharable
public class WebSocket00FrameEncoder extends MessageToMessageEncoder<WebSocketFrame> implements WebSocketFrameEncoder {
    private static final ByteBuf _0X00 = Unpooled.unreleasableBuffer(Unpooled.directBuffer(1, 1).writeByte(0)).asReadOnly();
    private static final ByteBuf _0XFF = Unpooled.unreleasableBuffer(Unpooled.directBuffer(1, 1).writeByte(-1)).asReadOnly();
    private static final ByteBuf _0XFF_0X00 = Unpooled.unreleasableBuffer(Unpooled.directBuffer(2, 2).writeByte(-1).writeByte(0)).asReadOnly();

    /* access modifiers changed from: protected */
    public void encode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        if (webSocketFrame instanceof TextWebSocketFrame) {
            ByteBuf content = webSocketFrame.content();
            list.add(_0X00.duplicate());
            list.add(content.retain());
            list.add(_0XFF.duplicate());
        } else if (webSocketFrame instanceof CloseWebSocketFrame) {
            list.add(_0XFF_0X00.duplicate());
        } else {
            ByteBuf content2 = webSocketFrame.content();
            int readableBytes = content2.readableBytes();
            ByteBuf buffer = channelHandlerContext.alloc().buffer(5);
            try {
                buffer.writeByte(-128);
                int i = (readableBytes >>> 28) & 127;
                int i2 = (readableBytes >>> 14) & 127;
                int i3 = (readableBytes >>> 7) & 127;
                int i4 = readableBytes & 127;
                if (i != 0) {
                    buffer.writeByte(i | 128);
                    buffer.writeByte(i2 | 128);
                    buffer.writeByte(i3 | 128);
                    buffer.writeByte(i4);
                } else if (i2 == 0) {
                    if (i3 != 0) {
                        buffer.writeByte(i3 | 128);
                    }
                    buffer.writeByte(i4);
                } else {
                    buffer.writeByte(i2 | 128);
                    buffer.writeByte(i3 | 128);
                    buffer.writeByte(i4);
                }
                list.add(buffer);
                list.add(content2.retain());
            } catch (Throwable th) {
                buffer.release();
                throw th;
            }
        }
    }
}
