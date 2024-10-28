package io.netty.handler.codec.http2;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class DefaultHttp2PushPromiseFrame implements Http2PushPromiseFrame {
    private final Http2Headers http2Headers;
    private final int padding;
    private final int promisedStreamId;
    private Http2FrameStream pushStreamFrame;
    private Http2FrameStream streamFrame;

    public DefaultHttp2PushPromiseFrame(Http2Headers http2Headers2) {
        this(http2Headers2, 0);
    }

    public DefaultHttp2PushPromiseFrame(Http2Headers http2Headers2, int i) {
        this(http2Headers2, i, -1);
    }

    DefaultHttp2PushPromiseFrame(Http2Headers http2Headers2, int i, int i2) {
        this.http2Headers = http2Headers2;
        this.padding = i;
        this.promisedStreamId = i2;
    }

    public Http2StreamFrame pushStream(Http2FrameStream http2FrameStream) {
        this.pushStreamFrame = http2FrameStream;
        return this;
    }

    public Http2FrameStream pushStream() {
        return this.pushStreamFrame;
    }

    public Http2Headers http2Headers() {
        return this.http2Headers;
    }

    public int padding() {
        return this.padding;
    }

    public int promisedStreamId() {
        Http2FrameStream http2FrameStream = this.pushStreamFrame;
        if (http2FrameStream != null) {
            return http2FrameStream.id();
        }
        return this.promisedStreamId;
    }

    public Http2PushPromiseFrame stream(Http2FrameStream http2FrameStream) {
        this.streamFrame = http2FrameStream;
        return this;
    }

    public Http2FrameStream stream() {
        return this.streamFrame;
    }

    public String name() {
        return "PUSH_PROMISE_FRAME";
    }

    public String toString() {
        return "DefaultHttp2PushPromiseFrame{pushStreamFrame=" + this.pushStreamFrame + ", http2Headers=" + this.http2Headers + ", streamFrame=" + this.streamFrame + ", padding=" + this.padding + AbstractJsonLexerKt.END_OBJ;
    }
}
