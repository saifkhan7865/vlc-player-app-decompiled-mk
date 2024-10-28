package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public abstract class SpdyHeaderBlockDecoder {
    /* access modifiers changed from: package-private */
    public abstract void decode(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, SpdyHeadersFrame spdyHeadersFrame) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void end();

    /* access modifiers changed from: package-private */
    public abstract void endHeaderBlock(SpdyHeadersFrame spdyHeadersFrame) throws Exception;

    static SpdyHeaderBlockDecoder newInstance(SpdyVersion spdyVersion, int i) {
        return new SpdyHeaderBlockZlibDecoder(spdyVersion, i);
    }
}
