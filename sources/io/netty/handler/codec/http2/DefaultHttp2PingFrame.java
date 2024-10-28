package io.netty.handler.codec.http2;

import io.netty.util.internal.StringUtil;

public class DefaultHttp2PingFrame implements Http2PingFrame {
    private final boolean ack;
    private final long content;

    public DefaultHttp2PingFrame(long j) {
        this(j, false);
    }

    public DefaultHttp2PingFrame(long j, boolean z) {
        this.content = j;
        this.ack = z;
    }

    public boolean ack() {
        return this.ack;
    }

    public String name() {
        return "PING";
    }

    public long content() {
        return this.content;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Http2PingFrame)) {
            return false;
        }
        Http2PingFrame http2PingFrame = (Http2PingFrame) obj;
        if (this.ack == http2PingFrame.ack() && this.content == http2PingFrame.content()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (this.ack ? 1 : 0);
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + "(content=" + this.content + ", ack=" + this.ack + ')';
    }
}
