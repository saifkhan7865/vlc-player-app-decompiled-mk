package io.netty.handler.codec.spdy;

public enum SpdyVersion {
    SPDY_3_1(3, 1);
    
    private final int minorVersion;
    private final int version;

    private SpdyVersion(int i, int i2) {
        this.version = i;
        this.minorVersion = i2;
    }

    /* access modifiers changed from: package-private */
    public int getVersion() {
        return this.version;
    }

    /* access modifiers changed from: package-private */
    public int getMinorVersion() {
        return this.minorVersion;
    }
}
