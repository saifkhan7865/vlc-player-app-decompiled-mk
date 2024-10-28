package io.netty.channel;

import java.nio.channels.ClosedChannelException;

final class ExtendedClosedChannelException extends ClosedChannelException {
    public Throwable fillInStackTrace() {
        return this;
    }

    ExtendedClosedChannelException(Throwable th) {
        if (th != null) {
            initCause(th);
        }
    }
}
