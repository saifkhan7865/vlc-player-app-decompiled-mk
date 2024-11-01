package io.netty.handler.codec.compression;

final class ZstdConstants {
    static final int DEFAULT_BLOCK_SIZE = 65536;
    static final int DEFAULT_COMPRESSION_LEVEL = 3;
    static final int MAX_BLOCK_SIZE = 33554432;
    static final int MAX_COMPRESSION_LEVEL = 22;

    private ZstdConstants() {
    }
}
