package io.netty.channel;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;

public class ChannelException extends RuntimeException {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 2908618315971075004L;

    public ChannelException() {
    }

    public ChannelException(String str, Throwable th) {
        super(str, th);
    }

    public ChannelException(String str) {
        super(str);
    }

    public ChannelException(Throwable th) {
        super(th);
    }

    protected ChannelException(String str, Throwable th, boolean z) {
        super(str, th, false, true);
    }

    static ChannelException newStatic(String str, Class<?> cls, String str2) {
        StacklessChannelException stacklessChannelException;
        if (PlatformDependent.javaVersion() >= 7) {
            stacklessChannelException = new StacklessChannelException(str, (Throwable) null, true);
        } else {
            stacklessChannelException = new StacklessChannelException(str, (Throwable) null);
        }
        return (ChannelException) ThrowableUtil.unknownStackTrace(stacklessChannelException, cls, str2);
    }

    private static final class StacklessChannelException extends ChannelException {
        private static final long serialVersionUID = -6384642137753538579L;

        public Throwable fillInStackTrace() {
            return this;
        }

        StacklessChannelException(String str, Throwable th) {
            super(str, th);
        }

        StacklessChannelException(String str, Throwable th, boolean z) {
            super(str, th, z);
        }
    }
}
