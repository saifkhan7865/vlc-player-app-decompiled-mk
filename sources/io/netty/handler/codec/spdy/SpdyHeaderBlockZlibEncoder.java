package io.netty.handler.codec.spdy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.util.zip.Deflater;

class SpdyHeaderBlockZlibEncoder extends SpdyHeaderBlockRawEncoder {
    private final Deflater compressor;
    private boolean finished;

    SpdyHeaderBlockZlibEncoder(SpdyVersion spdyVersion, int i) {
        super(spdyVersion);
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        Deflater deflater = new Deflater(i);
        this.compressor = deflater;
        deflater.setDictionary(SpdyCodecUtil.SPDY_DICT);
    }

    private int setInput(ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            this.compressor.setInput(byteBuf.array(), byteBuf.arrayOffset() + byteBuf.readerIndex(), readableBytes);
        } else {
            byte[] bArr = new byte[readableBytes];
            byteBuf.getBytes(byteBuf.readerIndex(), bArr);
            this.compressor.setInput(bArr, 0, readableBytes);
        }
        return readableBytes;
    }

    private ByteBuf encode(ByteBufAllocator byteBufAllocator, int i) {
        ByteBuf heapBuffer = byteBufAllocator.heapBuffer(i);
        while (compressInto(heapBuffer)) {
            try {
                heapBuffer.ensureWritable(heapBuffer.capacity() << 1);
            } catch (Throwable th) {
                heapBuffer.release();
                throw th;
            }
        }
        return heapBuffer;
    }

    private boolean compressInto(ByteBuf byteBuf) {
        int i;
        byte[] array = byteBuf.array();
        int arrayOffset = byteBuf.arrayOffset() + byteBuf.writerIndex();
        int writableBytes = byteBuf.writableBytes();
        if (PlatformDependent.javaVersion() >= 7) {
            i = this.compressor.deflate(array, arrayOffset, writableBytes, 2);
        } else {
            i = this.compressor.deflate(array, arrayOffset, writableBytes);
        }
        byteBuf.writerIndex(byteBuf.writerIndex() + i);
        return i == writableBytes;
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        ObjectUtil.checkNotNullWithIAE(byteBufAllocator, "alloc");
        ObjectUtil.checkNotNullWithIAE(spdyHeadersFrame, TypedValues.AttributesType.S_FRAME);
        if (this.finished) {
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBuf encode = super.encode(byteBufAllocator, spdyHeadersFrame);
        try {
            if (!encode.isReadable()) {
                return Unpooled.EMPTY_BUFFER;
            }
            ByteBuf encode2 = encode(byteBufAllocator, setInput(encode));
            encode.release();
            return encode2;
        } finally {
            encode.release();
        }
    }

    public void end() {
        if (!this.finished) {
            this.finished = true;
            this.compressor.end();
            super.end();
        }
    }
}
