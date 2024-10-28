package io.netty.channel;

import io.netty.util.internal.ThrowableUtil;
import java.nio.channels.ClosedChannelException;

final class StacklessClosedChannelException extends ClosedChannelException {
    private static final long serialVersionUID = -2214806025529435136L;

    public Throwable fillInStackTrace() {
        return this;
    }

    private StacklessClosedChannelException() {
    }

    static StacklessClosedChannelException newInstance(Class<?> cls, String str) {
        return (StacklessClosedChannelException) ThrowableUtil.unknownStackTrace(new StacklessClosedChannelException(), cls, str);
    }
}
