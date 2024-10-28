package io.netty.handler.codec.spdy;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

public class DefaultSpdyGoAwayFrame implements SpdyGoAwayFrame {
    private int lastGoodStreamId;
    private SpdySessionStatus status;

    public DefaultSpdyGoAwayFrame(int i) {
        this(i, 0);
    }

    public DefaultSpdyGoAwayFrame(int i, int i2) {
        this(i, SpdySessionStatus.valueOf(i2));
    }

    public DefaultSpdyGoAwayFrame(int i, SpdySessionStatus spdySessionStatus) {
        setLastGoodStreamId(i);
        setStatus(spdySessionStatus);
    }

    public int lastGoodStreamId() {
        return this.lastGoodStreamId;
    }

    public SpdyGoAwayFrame setLastGoodStreamId(int i) {
        ObjectUtil.checkPositiveOrZero(i, "lastGoodStreamId");
        this.lastGoodStreamId = i;
        return this;
    }

    public SpdySessionStatus status() {
        return this.status;
    }

    public SpdyGoAwayFrame setStatus(SpdySessionStatus spdySessionStatus) {
        this.status = spdySessionStatus;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + StringUtil.NEWLINE + "--> Last-good-stream-ID = " + lastGoodStreamId() + StringUtil.NEWLINE + "--> Status: " + status();
    }
}
