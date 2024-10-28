package io.netty.handler.timeout;

import io.netty.channel.ChannelException;

public class TimeoutException extends ChannelException {
    private static final long serialVersionUID = 4673641882869672533L;

    public Throwable fillInStackTrace() {
        return this;
    }

    TimeoutException() {
    }

    TimeoutException(String str, boolean z) {
        super(str, (Throwable) null, z);
    }
}
