package io.netty.handler.ssl;

import io.netty.util.internal.ThrowableUtil;
import javax.net.ssl.SSLHandshakeException;

final class StacklessSSLHandshakeException extends SSLHandshakeException {
    private static final long serialVersionUID = -1244781947804415549L;

    public Throwable fillInStackTrace() {
        return this;
    }

    private StacklessSSLHandshakeException(String str) {
        super(str);
    }

    static StacklessSSLHandshakeException newInstance(String str, Class<?> cls, String str2) {
        return (StacklessSSLHandshakeException) ThrowableUtil.unknownStackTrace(new StacklessSSLHandshakeException(str), cls, str2);
    }
}
