package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.internal.PlatformDependent;

public abstract class SpdyHeaderBlockEncoder {
    /* access modifiers changed from: package-private */
    public abstract ByteBuf encode(ByteBufAllocator byteBufAllocator, SpdyHeadersFrame spdyHeadersFrame) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void end();

    static SpdyHeaderBlockEncoder newInstance(SpdyVersion spdyVersion, int i, int i2, int i3) {
        if (PlatformDependent.javaVersion() >= 7) {
            return new SpdyHeaderBlockZlibEncoder(spdyVersion, i);
        }
        return new SpdyHeaderBlockJZlibEncoder(spdyVersion, i, i2, i3);
    }
}
