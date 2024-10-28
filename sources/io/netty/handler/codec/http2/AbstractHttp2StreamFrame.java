package io.netty.handler.codec.http2;

public abstract class AbstractHttp2StreamFrame implements Http2StreamFrame {
    private Http2FrameStream stream;

    public AbstractHttp2StreamFrame stream(Http2FrameStream http2FrameStream) {
        this.stream = http2FrameStream;
        return this;
    }

    public Http2FrameStream stream() {
        return this.stream;
    }

    public boolean equals(Object obj) {
        Http2FrameStream http2FrameStream;
        if (!(obj instanceof Http2StreamFrame)) {
            return false;
        }
        Http2StreamFrame http2StreamFrame = (Http2StreamFrame) obj;
        if (this.stream == http2StreamFrame.stream() || ((http2FrameStream = this.stream) != null && http2FrameStream.equals(http2StreamFrame.stream()))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Http2FrameStream http2FrameStream = this.stream;
        if (http2FrameStream == null) {
            return super.hashCode();
        }
        return http2FrameStream.hashCode();
    }
}
