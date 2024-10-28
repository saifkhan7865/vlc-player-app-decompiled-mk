package io.netty.channel.unix;

final class LimitsStaticallyReferencedJniMethods {
    static native int iovMax();

    static native int sizeOfjlong();

    static native long ssizeMax();

    static native int udsSunPathSize();

    static native int uioMaxIov();

    private LimitsStaticallyReferencedJniMethods() {
    }
}
