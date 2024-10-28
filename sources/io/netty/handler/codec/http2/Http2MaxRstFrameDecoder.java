package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

final class Http2MaxRstFrameDecoder extends DecoratingHttp2ConnectionDecoder {
    private final int maxRstFramesPerWindow;
    private final int secondsPerWindow;

    Http2MaxRstFrameDecoder(Http2ConnectionDecoder http2ConnectionDecoder, int i, int i2) {
        super(http2ConnectionDecoder);
        this.maxRstFramesPerWindow = ObjectUtil.checkPositive(i, "maxRstFramesPerWindow");
        this.secondsPerWindow = ObjectUtil.checkPositive(i2, "secondsPerWindow");
    }

    public void frameListener(Http2FrameListener http2FrameListener) {
        if (http2FrameListener != null) {
            super.frameListener(new Http2MaxRstFrameListener(http2FrameListener, this.maxRstFramesPerWindow, this.secondsPerWindow));
        } else {
            super.frameListener((Http2FrameListener) null);
        }
    }

    public Http2FrameListener frameListener() {
        Http2FrameListener frameListener0 = frameListener0();
        return frameListener0 instanceof Http2MaxRstFrameListener ? ((Http2MaxRstFrameListener) frameListener0).listener : frameListener0;
    }

    /* access modifiers changed from: package-private */
    public Http2FrameListener frameListener0() {
        return super.frameListener();
    }
}
