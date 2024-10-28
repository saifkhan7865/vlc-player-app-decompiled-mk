package io.netty.handler.codec.http2;

public final class Http2MultiplexActiveStreamsException extends Exception {
    public Throwable fillInStackTrace() {
        return this;
    }

    public Http2MultiplexActiveStreamsException(Throwable th) {
        super(th);
    }
}
