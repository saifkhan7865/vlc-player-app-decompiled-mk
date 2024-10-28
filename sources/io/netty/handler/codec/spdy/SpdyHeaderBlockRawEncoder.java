package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;
import java.util.Set;

public class SpdyHeaderBlockRawEncoder extends SpdyHeaderBlockEncoder {
    private final int version;

    /* access modifiers changed from: package-private */
    public void end() {
    }

    public SpdyHeaderBlockRawEncoder(SpdyVersion spdyVersion) {
        this.version = ((SpdyVersion) ObjectUtil.checkNotNull(spdyVersion, "version")).getVersion();
    }

    private static void setLengthField(ByteBuf byteBuf, int i, int i2) {
        byteBuf.setInt(i, i2);
    }

    private static void writeLengthField(ByteBuf byteBuf, int i) {
        byteBuf.writeInt(i);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        Set<CharSequence> names = spdyHeadersFrame.headers().names();
        int size = names.size();
        if (size == 0) {
            return Unpooled.EMPTY_BUFFER;
        }
        if (size <= 65535) {
            ByteBuf heapBuffer = byteBufAllocator.heapBuffer();
            writeLengthField(heapBuffer, size);
            for (CharSequence charSequence : names) {
                writeLengthField(heapBuffer, charSequence.length());
                ByteBufUtil.writeAscii(heapBuffer, charSequence);
                int writerIndex = heapBuffer.writerIndex();
                writeLengthField(heapBuffer, 0);
                int i = 0;
                for (CharSequence charSequence2 : spdyHeadersFrame.headers().getAll(charSequence)) {
                    int length = charSequence2.length();
                    if (length > 0) {
                        ByteBufUtil.writeAscii(heapBuffer, charSequence2);
                        heapBuffer.writeByte(0);
                        i += length + 1;
                    }
                }
                if (i != 0) {
                    i--;
                }
                if (i > 65535) {
                    throw new IllegalArgumentException("header exceeds allowable length: " + charSequence);
                } else if (i > 0) {
                    setLengthField(heapBuffer, writerIndex, i);
                    heapBuffer.writerIndex(heapBuffer.writerIndex() - 1);
                }
            }
            return heapBuffer;
        }
        throw new IllegalArgumentException("header block contains too many headers");
    }
}
