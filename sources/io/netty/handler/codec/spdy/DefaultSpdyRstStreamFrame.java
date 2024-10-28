package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;

public class DefaultSpdyRstStreamFrame extends DefaultSpdyStreamFrame implements SpdyRstStreamFrame {
    private SpdyStreamStatus status;

    public DefaultSpdyRstStreamFrame(int i, int i2) {
        this(i, SpdyStreamStatus.valueOf(i2));
    }

    public DefaultSpdyRstStreamFrame(int i, SpdyStreamStatus spdyStreamStatus) {
        super(i);
        setStatus(spdyStreamStatus);
    }

    public SpdyRstStreamFrame setStreamId(int i) {
        super.setStreamId(i);
        return this;
    }

    public SpdyRstStreamFrame setLast(boolean z) {
        super.setLast(z);
        return this;
    }

    public SpdyStreamStatus status() {
        return this.status;
    }

    public SpdyRstStreamFrame setStatus(SpdyStreamStatus spdyStreamStatus) {
        this.status = spdyStreamStatus;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + StringUtil.NEWLINE + "--> Stream-ID = " + streamId() + StringUtil.NEWLINE + "--> Status: " + status();
    }
}
