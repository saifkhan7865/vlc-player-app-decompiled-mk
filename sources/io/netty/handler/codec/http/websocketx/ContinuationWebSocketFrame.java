package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ContinuationWebSocketFrame extends WebSocketFrame {
    public ContinuationWebSocketFrame() {
        this(Unpooled.buffer(0));
    }

    public ContinuationWebSocketFrame(ByteBuf byteBuf) {
        super(byteBuf);
    }

    public ContinuationWebSocketFrame(boolean z, int i, ByteBuf byteBuf) {
        super(z, i, byteBuf);
    }

    public ContinuationWebSocketFrame(boolean z, int i, String str) {
        this(z, i, fromText(str));
    }

    public String text() {
        return content().toString(CharsetUtil.UTF_8);
    }

    private static ByteBuf fromText(String str) {
        if (str == null || str.isEmpty()) {
            return Unpooled.EMPTY_BUFFER;
        }
        return Unpooled.copiedBuffer((CharSequence) str, CharsetUtil.UTF_8);
    }

    public ContinuationWebSocketFrame copy() {
        return (ContinuationWebSocketFrame) super.copy();
    }

    public ContinuationWebSocketFrame duplicate() {
        return (ContinuationWebSocketFrame) super.duplicate();
    }

    public ContinuationWebSocketFrame retainedDuplicate() {
        return (ContinuationWebSocketFrame) super.retainedDuplicate();
    }

    public ContinuationWebSocketFrame replace(ByteBuf byteBuf) {
        return new ContinuationWebSocketFrame(isFinalFragment(), rsv(), byteBuf);
    }

    public ContinuationWebSocketFrame retain() {
        super.retain();
        return this;
    }

    public ContinuationWebSocketFrame retain(int i) {
        super.retain(i);
        return this;
    }

    public ContinuationWebSocketFrame touch() {
        super.touch();
        return this;
    }

    public ContinuationWebSocketFrame touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
