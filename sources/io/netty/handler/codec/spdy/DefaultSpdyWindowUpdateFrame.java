package io.netty.handler.codec.spdy;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

public class DefaultSpdyWindowUpdateFrame implements SpdyWindowUpdateFrame {
    private int deltaWindowSize;
    private int streamId;

    public DefaultSpdyWindowUpdateFrame(int i, int i2) {
        setStreamId(i);
        setDeltaWindowSize(i2);
    }

    public int streamId() {
        return this.streamId;
    }

    public SpdyWindowUpdateFrame setStreamId(int i) {
        ObjectUtil.checkPositiveOrZero(i, "streamId");
        this.streamId = i;
        return this;
    }

    public int deltaWindowSize() {
        return this.deltaWindowSize;
    }

    public SpdyWindowUpdateFrame setDeltaWindowSize(int i) {
        ObjectUtil.checkPositive(i, "deltaWindowSize");
        this.deltaWindowSize = i;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + StringUtil.NEWLINE + "--> Stream-ID = " + streamId() + StringUtil.NEWLINE + "--> Delta-Window-Size = " + deltaWindowSize();
    }
}
