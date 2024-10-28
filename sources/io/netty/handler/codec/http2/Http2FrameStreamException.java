package io.netty.handler.codec.http2;

import io.ktor.server.auth.OAuth2ResponseParameters;
import io.netty.util.internal.ObjectUtil;

public final class Http2FrameStreamException extends Exception {
    private static final long serialVersionUID = -4407186173493887044L;
    private final Http2Error error;
    private final Http2FrameStream stream;

    public Http2FrameStreamException(Http2FrameStream http2FrameStream, Http2Error http2Error, Throwable th) {
        super(th.getMessage(), th);
        this.stream = (Http2FrameStream) ObjectUtil.checkNotNull(http2FrameStream, "stream");
        this.error = (Http2Error) ObjectUtil.checkNotNull(http2Error, OAuth2ResponseParameters.Error);
    }

    public Http2Error error() {
        return this.error;
    }

    public Http2FrameStream stream() {
        return this.stream;
    }
}
