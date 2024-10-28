package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.util.internal.StringUtil;

public abstract class WebSocketFrame extends DefaultByteBufHolder {
    private final boolean finalFragment;
    private final int rsv;

    public abstract WebSocketFrame replace(ByteBuf byteBuf);

    protected WebSocketFrame(ByteBuf byteBuf) {
        this(true, 0, byteBuf);
    }

    protected WebSocketFrame(boolean z, int i, ByteBuf byteBuf) {
        super(byteBuf);
        this.finalFragment = z;
        this.rsv = i;
    }

    public boolean isFinalFragment() {
        return this.finalFragment;
    }

    public int rsv() {
        return this.rsv;
    }

    public WebSocketFrame copy() {
        return (WebSocketFrame) super.copy();
    }

    public WebSocketFrame duplicate() {
        return (WebSocketFrame) super.duplicate();
    }

    public WebSocketFrame retainedDuplicate() {
        return (WebSocketFrame) super.retainedDuplicate();
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + "(data: " + contentToString() + ')';
    }

    public WebSocketFrame retain() {
        super.retain();
        return this;
    }

    public WebSocketFrame retain(int i) {
        super.retain(i);
        return this;
    }

    public WebSocketFrame touch() {
        super.touch();
        return this;
    }

    public WebSocketFrame touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
