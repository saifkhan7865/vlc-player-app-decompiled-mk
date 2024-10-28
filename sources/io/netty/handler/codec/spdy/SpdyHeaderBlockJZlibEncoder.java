package io.netty.handler.codec.spdy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.JZlib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.compression.CompressionException;
import io.netty.util.internal.ObjectUtil;

class SpdyHeaderBlockJZlibEncoder extends SpdyHeaderBlockRawEncoder {
    private boolean finished;
    private final Deflater z;

    SpdyHeaderBlockJZlibEncoder(SpdyVersion spdyVersion, int i, int i2, int i3) {
        super(spdyVersion);
        Deflater deflater = new Deflater();
        this.z = deflater;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        } else if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        } else if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        } else {
            int deflateInit = deflater.deflateInit(i, i2, i3, JZlib.W_ZLIB);
            if (deflateInit == 0) {
                int deflateSetDictionary = deflater.deflateSetDictionary(SpdyCodecUtil.SPDY_DICT, SpdyCodecUtil.SPDY_DICT.length);
                if (deflateSetDictionary != 0) {
                    throw new CompressionException("failed to set the SPDY dictionary: " + deflateSetDictionary);
                }
                return;
            }
            throw new CompressionException("failed to initialize an SPDY header block deflater: " + deflateInit);
        }
    }

    private void setInput(ByteBuf byteBuf) {
        int i;
        byte[] bArr;
        int readableBytes = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            bArr = byteBuf.array();
            i = byteBuf.arrayOffset() + byteBuf.readerIndex();
        } else {
            bArr = new byte[readableBytes];
            byteBuf.getBytes(byteBuf.readerIndex(), bArr);
            i = 0;
        }
        this.z.next_in = bArr;
        this.z.next_in_index = i;
        this.z.avail_in = readableBytes;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.netty.buffer.ByteBuf encode(io.netty.buffer.ByteBufAllocator r9) {
        /*
            r8 = this;
            java.lang.String r0 = "compression failure: "
            r1 = 0
            com.jcraft.jzlib.Deflater r2 = r8.z     // Catch:{ all -> 0x0086 }
            int r2 = r2.next_in_index     // Catch:{ all -> 0x0086 }
            com.jcraft.jzlib.Deflater r3 = r8.z     // Catch:{ all -> 0x0086 }
            int r3 = r3.next_out_index     // Catch:{ all -> 0x0086 }
            com.jcraft.jzlib.Deflater r4 = r8.z     // Catch:{ all -> 0x0086 }
            byte[] r4 = r4.next_in     // Catch:{ all -> 0x0086 }
            int r4 = r4.length     // Catch:{ all -> 0x0086 }
            double r4 = (double) r4
            r6 = 4607186922399644778(0x3ff004189374bc6a, double:1.001)
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r6
            double r4 = java.lang.Math.ceil(r4)     // Catch:{ all -> 0x0086 }
            int r4 = (int) r4     // Catch:{ all -> 0x0086 }
            int r4 = r4 + 12
            io.netty.buffer.ByteBuf r9 = r9.heapBuffer(r4)     // Catch:{ all -> 0x0086 }
            com.jcraft.jzlib.Deflater r5 = r8.z     // Catch:{ all -> 0x0084 }
            byte[] r6 = r9.array()     // Catch:{ all -> 0x0084 }
            r5.next_out = r6     // Catch:{ all -> 0x0084 }
            com.jcraft.jzlib.Deflater r5 = r8.z     // Catch:{ all -> 0x0084 }
            int r6 = r9.arrayOffset()     // Catch:{ all -> 0x0084 }
            int r7 = r9.writerIndex()     // Catch:{ all -> 0x0084 }
            int r6 = r6 + r7
            r5.next_out_index = r6     // Catch:{ all -> 0x0084 }
            com.jcraft.jzlib.Deflater r5 = r8.z     // Catch:{ all -> 0x0084 }
            r5.avail_out = r4     // Catch:{ all -> 0x0084 }
            com.jcraft.jzlib.Deflater r4 = r8.z     // Catch:{ all -> 0x007a }
            r5 = 2
            int r4 = r4.deflate(r5)     // Catch:{ all -> 0x007a }
            com.jcraft.jzlib.Deflater r5 = r8.z     // Catch:{ all -> 0x0084 }
            int r5 = r5.next_in_index     // Catch:{ all -> 0x0084 }
            int r5 = r5 - r2
            r9.skipBytes(r5)     // Catch:{ all -> 0x0084 }
            if (r4 != 0) goto L_0x0068
            com.jcraft.jzlib.Deflater r0 = r8.z     // Catch:{ all -> 0x0084 }
            int r0 = r0.next_out_index     // Catch:{ all -> 0x0084 }
            int r0 = r0 - r3
            if (r0 <= 0) goto L_0x005f
            int r2 = r9.writerIndex()     // Catch:{ all -> 0x0084 }
            int r2 = r2 + r0
            r9.writerIndex(r2)     // Catch:{ all -> 0x0084 }
        L_0x005f:
            com.jcraft.jzlib.Deflater r0 = r8.z
            r0.next_in = r1
            com.jcraft.jzlib.Deflater r0 = r8.z
            r0.next_out = r1
            return r9
        L_0x0068:
            io.netty.handler.codec.compression.CompressionException r2 = new io.netty.handler.codec.compression.CompressionException     // Catch:{ all -> 0x0084 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0084 }
            r3.<init>(r0)     // Catch:{ all -> 0x0084 }
            r3.append(r4)     // Catch:{ all -> 0x0084 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0084 }
            r2.<init>((java.lang.String) r0)     // Catch:{ all -> 0x0084 }
            throw r2     // Catch:{ all -> 0x0084 }
        L_0x007a:
            r0 = move-exception
            com.jcraft.jzlib.Deflater r3 = r8.z     // Catch:{ all -> 0x0084 }
            int r3 = r3.next_in_index     // Catch:{ all -> 0x0084 }
            int r3 = r3 - r2
            r9.skipBytes(r3)     // Catch:{ all -> 0x0084 }
            throw r0     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r0 = move-exception
            goto L_0x0088
        L_0x0086:
            r0 = move-exception
            r9 = r1
        L_0x0088:
            com.jcraft.jzlib.Deflater r2 = r8.z
            r2.next_in = r1
            com.jcraft.jzlib.Deflater r2 = r8.z
            r2.next_out = r1
            if (r9 == 0) goto L_0x0095
            r9.release()
        L_0x0095:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.spdy.SpdyHeaderBlockJZlibEncoder.encode(io.netty.buffer.ByteBufAllocator):io.netty.buffer.ByteBuf");
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
            setInput(encode);
            ByteBuf encode2 = encode(byteBufAllocator);
            encode.release();
            return encode2;
        } finally {
            encode.release();
        }
    }

    public void end() {
        if (!this.finished) {
            this.finished = true;
            this.z.deflateEnd();
            this.z.next_in = null;
            this.z.next_out = null;
        }
    }
}
