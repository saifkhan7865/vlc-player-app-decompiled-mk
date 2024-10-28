package io.netty.handler.codec.spdy;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;

public class SpdyProtocolException extends Exception {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 7870000537743847264L;

    public SpdyProtocolException() {
    }

    public SpdyProtocolException(String str, Throwable th) {
        super(str, th);
    }

    public SpdyProtocolException(String str) {
        super(str);
    }

    public SpdyProtocolException(Throwable th) {
        super(th);
    }

    static SpdyProtocolException newStatic(String str, Class<?> cls, String str2) {
        StacklessSpdyProtocolException stacklessSpdyProtocolException;
        if (PlatformDependent.javaVersion() >= 7) {
            stacklessSpdyProtocolException = new StacklessSpdyProtocolException(str, true);
        } else {
            stacklessSpdyProtocolException = new StacklessSpdyProtocolException(str);
        }
        return (SpdyProtocolException) ThrowableUtil.unknownStackTrace(stacklessSpdyProtocolException, cls, str2);
    }

    private SpdyProtocolException(String str, boolean z) {
        super(str, (Throwable) null, false, true);
    }

    private static final class StacklessSpdyProtocolException extends SpdyProtocolException {
        private static final long serialVersionUID = -6302754207557485099L;

        public Throwable fillInStackTrace() {
            return this;
        }

        StacklessSpdyProtocolException(String str) {
            super(str);
        }

        StacklessSpdyProtocolException(String str, boolean z) {
            super(str, z);
        }
    }
}
