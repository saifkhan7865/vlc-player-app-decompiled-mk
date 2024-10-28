package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.ObjectUtil;

public abstract class ZlibDecoder extends ByteToMessageDecoder {
    protected final int maxAllocation;

    /* access modifiers changed from: protected */
    public void decompressionBufferExhausted(ByteBuf byteBuf) {
    }

    public abstract boolean isClosed();

    public ZlibDecoder() {
        this(0);
    }

    public ZlibDecoder(int i) {
        this.maxAllocation = ObjectUtil.checkPositiveOrZero(i, "maxAllocation");
    }

    /* access modifiers changed from: protected */
    public ByteBuf prepareDecompressBuffer(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, int i) {
        if (byteBuf == null) {
            if (this.maxAllocation == 0) {
                return channelHandlerContext.alloc().heapBuffer(i);
            }
            return channelHandlerContext.alloc().heapBuffer(Math.min(i, this.maxAllocation), this.maxAllocation);
        } else if (byteBuf.ensureWritable(i, true) != 1) {
            return byteBuf;
        } else {
            decompressionBufferExhausted(byteBuf.duplicate());
            byteBuf.skipBytes(byteBuf.readableBytes());
            throw new DecompressionException("Decompression buffer has reached maximum size: " + byteBuf.maxCapacity());
        }
    }
}
