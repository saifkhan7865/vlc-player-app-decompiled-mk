package io.netty.channel.kqueue;

final class KQueueStaticallyReferencedJniMethods {
    static native int connectDataIdempotent();

    static native int connectResumeOnReadWrite();

    static native short evAdd();

    static native short evClear();

    static native short evDelete();

    static native short evDisable();

    static native short evEOF();

    static native short evEnable();

    static native short evError();

    static native short evfiltRead();

    static native short evfiltSock();

    static native short evfiltUser();

    static native short evfiltWrite();

    static native int fastOpenClient();

    static native int fastOpenServer();

    static native short noteConnReset();

    static native short noteDisconnected();

    static native short noteReadClosed();

    private KQueueStaticallyReferencedJniMethods() {
    }
}
