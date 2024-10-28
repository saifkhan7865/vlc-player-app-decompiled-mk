package io.netty.handler.codec.http2;

import io.netty.util.internal.StringUtil;

public class DefaultHttp2WindowUpdateFrame extends AbstractHttp2StreamFrame implements Http2WindowUpdateFrame {
    private final int windowUpdateIncrement;

    public DefaultHttp2WindowUpdateFrame(int i) {
        this.windowUpdateIncrement = i;
    }

    public DefaultHttp2WindowUpdateFrame stream(Http2FrameStream http2FrameStream) {
        super.stream(http2FrameStream);
        return this;
    }

    public String name() {
        return "WINDOW_UPDATE";
    }

    public int windowSizeIncrement() {
        return this.windowUpdateIncrement;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + "(stream=" + stream() + ", windowUpdateIncrement=" + this.windowUpdateIncrement + ')';
    }
}
