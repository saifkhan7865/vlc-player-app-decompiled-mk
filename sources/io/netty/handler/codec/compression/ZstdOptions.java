package io.netty.handler.codec.compression;

import io.netty.util.internal.ObjectUtil;

public class ZstdOptions implements CompressionOptions {
    static final ZstdOptions DEFAULT = new ZstdOptions(3, 65536, 33554432);
    private final int blockSize;
    private final int compressionLevel;
    private final int maxEncodeSize;

    ZstdOptions(int i, int i2, int i3) {
        if (Zstd.isAvailable()) {
            this.compressionLevel = ObjectUtil.checkInRange(i, 0, 22, "compressionLevel");
            this.blockSize = ObjectUtil.checkPositive(i2, "blockSize");
            this.maxEncodeSize = ObjectUtil.checkPositive(i3, "maxEncodeSize");
            return;
        }
        throw new IllegalStateException("zstd-jni is not available", Zstd.cause());
    }

    public int compressionLevel() {
        return this.compressionLevel;
    }

    public int blockSize() {
        return this.blockSize;
    }

    public int maxEncodeSize() {
        return this.maxEncodeSize;
    }
}
