package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

final class Http2EmptyDataFrameConnectionDecoder extends DecoratingHttp2ConnectionDecoder {
    private final int maxConsecutiveEmptyFrames;

    Http2EmptyDataFrameConnectionDecoder(Http2ConnectionDecoder http2ConnectionDecoder, int i) {
        super(http2ConnectionDecoder);
        this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositive(i, "maxConsecutiveEmptyFrames");
    }

    public void frameListener(Http2FrameListener http2FrameListener) {
        if (http2FrameListener != null) {
            super.frameListener(new Http2EmptyDataFrameListener(http2FrameListener, this.maxConsecutiveEmptyFrames));
        } else {
            super.frameListener((Http2FrameListener) null);
        }
    }

    public Http2FrameListener frameListener() {
        Http2FrameListener frameListener0 = frameListener0();
        return frameListener0 instanceof Http2EmptyDataFrameListener ? ((Http2EmptyDataFrameListener) frameListener0).listener : frameListener0;
    }

    /* access modifiers changed from: package-private */
    public Http2FrameListener frameListener0() {
        return super.frameListener();
    }
}
