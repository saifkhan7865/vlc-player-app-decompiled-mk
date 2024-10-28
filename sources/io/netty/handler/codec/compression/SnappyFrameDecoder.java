package io.netty.handler.codec.compression;

import io.netty.handler.codec.ByteToMessageDecoder;

public class SnappyFrameDecoder extends ByteToMessageDecoder {
    private static final int MAX_COMPRESSED_CHUNK_SIZE = 16777215;
    private static final int MAX_DECOMPRESSED_DATA_SIZE = 65536;
    private static final int MAX_UNCOMPRESSED_DATA_SIZE = 65540;
    private static final int SNAPPY_IDENTIFIER_LEN = 6;
    private boolean corrupted;
    private int numBytesToSkip;
    private final Snappy snappy;
    private boolean started;
    private final boolean validateChecksums;

    private enum ChunkType {
        STREAM_IDENTIFIER,
        COMPRESSED_DATA,
        UNCOMPRESSED_DATA,
        RESERVED_UNSKIPPABLE,
        RESERVED_SKIPPABLE
    }

    public SnappyFrameDecoder() {
        this(false);
    }

    public SnappyFrameDecoder(boolean z) {
        this.snappy = new Snappy();
        this.validateChecksums = z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(io.netty.channel.ChannelHandlerContext r10, io.netty.buffer.ByteBuf r11, java.util.List<java.lang.Object> r12) throws java.lang.Exception {
        /*
            r9 = this;
            java.lang.String r0 = "Unexpected length of stream identifier: "
            java.lang.String r1 = "Found reserved unskippable chunk type: 0x"
            boolean r2 = r9.corrupted
            if (r2 == 0) goto L_0x0010
            int r10 = r11.readableBytes()
            r11.skipBytes(r10)
            return
        L_0x0010:
            int r2 = r9.numBytesToSkip
            if (r2 == 0) goto L_0x0025
            int r10 = r11.readableBytes()
            int r10 = java.lang.Math.min(r2, r10)
            r11.skipBytes(r10)
            int r11 = r9.numBytesToSkip
            int r11 = r11 - r10
            r9.numBytesToSkip = r11
            return
        L_0x0025:
            r2 = 1
            int r3 = r11.readerIndex()     // Catch:{ Exception -> 0x01b4 }
            int r4 = r11.readableBytes()     // Catch:{ Exception -> 0x01b4 }
            r5 = 4
            if (r4 >= r5) goto L_0x0032
            return
        L_0x0032:
            short r6 = r11.getUnsignedByte(r3)     // Catch:{ Exception -> 0x01b4 }
            byte r7 = (byte) r6     // Catch:{ Exception -> 0x01b4 }
            io.netty.handler.codec.compression.SnappyFrameDecoder$ChunkType r7 = mapChunkType(r7)     // Catch:{ Exception -> 0x01b4 }
            int r3 = r3 + r2
            int r3 = r11.getUnsignedMediumLE(r3)     // Catch:{ Exception -> 0x01b4 }
            int[] r8 = io.netty.handler.codec.compression.SnappyFrameDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType     // Catch:{ Exception -> 0x01b4 }
            int r7 = r7.ordinal()     // Catch:{ Exception -> 0x01b4 }
            r7 = r8[r7]     // Catch:{ Exception -> 0x01b4 }
            r8 = 5
            if (r7 == r2) goto L_0x014e
            r0 = 2
            if (r7 == r0) goto L_0x012e
            r0 = 3
            if (r7 == r0) goto L_0x0118
            if (r7 == r5) goto L_0x00d8
            if (r7 == r8) goto L_0x0057
            goto L_0x01a1
        L_0x0057:
            boolean r0 = r9.started     // Catch:{ Exception -> 0x01b4 }
            if (r0 == 0) goto L_0x00d0
            r0 = 16777215(0xffffff, float:2.3509886E-38)
            if (r3 > r0) goto L_0x00c8
            int r0 = r3 + 4
            if (r4 >= r0) goto L_0x0065
            return
        L_0x0065:
            r11.skipBytes(r5)     // Catch:{ Exception -> 0x01b4 }
            int r0 = r11.readIntLE()     // Catch:{ Exception -> 0x01b4 }
            io.netty.handler.codec.compression.Snappy r1 = r9.snappy     // Catch:{ Exception -> 0x01b4 }
            int r1 = r1.getPreamble(r11)     // Catch:{ Exception -> 0x01b4 }
            r4 = 65536(0x10000, float:9.18355E-41)
            if (r1 > r4) goto L_0x00c0
            io.netty.buffer.ByteBufAllocator r10 = r10.alloc()     // Catch:{ Exception -> 0x01b4 }
            io.netty.buffer.ByteBuf r10 = r10.buffer(r1, r4)     // Catch:{ Exception -> 0x01b4 }
            boolean r1 = r9.validateChecksums     // Catch:{ all -> 0x00b9 }
            if (r1 == 0) goto L_0x00a5
            int r1 = r11.writerIndex()     // Catch:{ all -> 0x00b9 }
            int r4 = r11.readerIndex()     // Catch:{ all -> 0x00a0 }
            int r4 = r4 + r3
            int r4 = r4 - r5
            r11.writerIndex(r4)     // Catch:{ all -> 0x00a0 }
            io.netty.handler.codec.compression.Snappy r3 = r9.snappy     // Catch:{ all -> 0x00a0 }
            r3.decode(r11, r10)     // Catch:{ all -> 0x00a0 }
            r11.writerIndex(r1)     // Catch:{ all -> 0x00b9 }
            int r11 = r10.writerIndex()     // Catch:{ all -> 0x00b9 }
            r1 = 0
            io.netty.handler.codec.compression.Snappy.validateChecksum(r0, r10, r1, r11)     // Catch:{ all -> 0x00b9 }
            goto L_0x00af
        L_0x00a0:
            r12 = move-exception
            r11.writerIndex(r1)     // Catch:{ all -> 0x00b9 }
            throw r12     // Catch:{ all -> 0x00b9 }
        L_0x00a5:
            io.netty.handler.codec.compression.Snappy r0 = r9.snappy     // Catch:{ all -> 0x00b9 }
            int r3 = r3 - r5
            io.netty.buffer.ByteBuf r11 = r11.readSlice(r3)     // Catch:{ all -> 0x00b9 }
            r0.decode(r11, r10)     // Catch:{ all -> 0x00b9 }
        L_0x00af:
            r12.add(r10)     // Catch:{ all -> 0x00b9 }
            io.netty.handler.codec.compression.Snappy r10 = r9.snappy     // Catch:{ Exception -> 0x01b4 }
            r10.reset()     // Catch:{ Exception -> 0x01b4 }
            goto L_0x01a1
        L_0x00b9:
            r11 = move-exception
            if (r10 == 0) goto L_0x00bf
            r10.release()     // Catch:{ Exception -> 0x01b4 }
        L_0x00bf:
            throw r11     // Catch:{ Exception -> 0x01b4 }
        L_0x00c0:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = "Received COMPRESSED_DATA that contains uncompressed data that exceeds 65536 bytes"
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x00c8:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = "Received COMPRESSED_DATA that contains chunk that exceeds 16777215 bytes"
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x00d0:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = "Received COMPRESSED_DATA tag before STREAM_IDENTIFIER"
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x00d8:
            boolean r10 = r9.started     // Catch:{ Exception -> 0x01b4 }
            if (r10 == 0) goto L_0x0110
            r10 = 65540(0x10004, float:9.1841E-41)
            if (r3 > r10) goto L_0x0108
            int r10 = r3 + 4
            if (r4 >= r10) goto L_0x00e6
            return
        L_0x00e6:
            r11.skipBytes(r5)     // Catch:{ Exception -> 0x01b4 }
            boolean r10 = r9.validateChecksums     // Catch:{ Exception -> 0x01b4 }
            if (r10 == 0) goto L_0x00fb
            int r10 = r11.readIntLE()     // Catch:{ Exception -> 0x01b4 }
            int r0 = r11.readerIndex()     // Catch:{ Exception -> 0x01b4 }
            int r1 = r3 + -4
            io.netty.handler.codec.compression.Snappy.validateChecksum(r10, r11, r0, r1)     // Catch:{ Exception -> 0x01b4 }
            goto L_0x00fe
        L_0x00fb:
            r11.skipBytes(r5)     // Catch:{ Exception -> 0x01b4 }
        L_0x00fe:
            int r3 = r3 - r5
            io.netty.buffer.ByteBuf r10 = r11.readRetainedSlice(r3)     // Catch:{ Exception -> 0x01b4 }
            r12.add(r10)     // Catch:{ Exception -> 0x01b4 }
            goto L_0x01a1
        L_0x0108:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = "Received UNCOMPRESSED_DATA larger than 65540 bytes"
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x0110:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = "Received UNCOMPRESSED_DATA tag before STREAM_IDENTIFIER"
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x0118:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b4 }
            r11.<init>(r1)     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r12 = java.lang.Integer.toHexString(r6)     // Catch:{ Exception -> 0x01b4 }
            r11.append(r12)     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x01b4 }
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x012e:
            boolean r10 = r9.started     // Catch:{ Exception -> 0x01b4 }
            if (r10 == 0) goto L_0x0146
            r11.skipBytes(r5)     // Catch:{ Exception -> 0x01b4 }
            int r10 = r11.readableBytes()     // Catch:{ Exception -> 0x01b4 }
            int r10 = java.lang.Math.min(r3, r10)     // Catch:{ Exception -> 0x01b4 }
            r11.skipBytes(r10)     // Catch:{ Exception -> 0x01b4 }
            if (r10 == r3) goto L_0x01a1
            int r3 = r3 - r10
            r9.numBytesToSkip = r3     // Catch:{ Exception -> 0x01b4 }
            goto L_0x01a1
        L_0x0146:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = "Received RESERVED_SKIPPABLE tag before STREAM_IDENTIFIER"
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x014e:
            r10 = 6
            if (r3 != r10) goto L_0x01a2
            r12 = 10
            if (r4 >= r12) goto L_0x0156
            goto L_0x01a1
        L_0x0156:
            r11.skipBytes(r5)     // Catch:{ Exception -> 0x01b4 }
            int r12 = r11.readerIndex()     // Catch:{ Exception -> 0x01b4 }
            r11.skipBytes(r10)     // Catch:{ Exception -> 0x01b4 }
            int r10 = r12 + 1
            byte r0 = r11.getByte(r12)     // Catch:{ Exception -> 0x01b4 }
            r1 = 115(0x73, float:1.61E-43)
            checkByte(r0, r1)     // Catch:{ Exception -> 0x01b4 }
            int r0 = r12 + 2
            byte r10 = r11.getByte(r10)     // Catch:{ Exception -> 0x01b4 }
            r1 = 78
            checkByte(r10, r1)     // Catch:{ Exception -> 0x01b4 }
            int r10 = r12 + 3
            byte r0 = r11.getByte(r0)     // Catch:{ Exception -> 0x01b4 }
            r1 = 97
            checkByte(r0, r1)     // Catch:{ Exception -> 0x01b4 }
            int r0 = r12 + 4
            byte r10 = r11.getByte(r10)     // Catch:{ Exception -> 0x01b4 }
            r1 = 80
            checkByte(r10, r1)     // Catch:{ Exception -> 0x01b4 }
            int r12 = r12 + r8
            byte r10 = r11.getByte(r0)     // Catch:{ Exception -> 0x01b4 }
            r0 = 112(0x70, float:1.57E-43)
            checkByte(r10, r0)     // Catch:{ Exception -> 0x01b4 }
            byte r10 = r11.getByte(r12)     // Catch:{ Exception -> 0x01b4 }
            r11 = 89
            checkByte(r10, r11)     // Catch:{ Exception -> 0x01b4 }
            r9.started = r2     // Catch:{ Exception -> 0x01b4 }
        L_0x01a1:
            return
        L_0x01a2:
            io.netty.handler.codec.compression.DecompressionException r10 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x01b4 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b4 }
            r11.<init>(r0)     // Catch:{ Exception -> 0x01b4 }
            r11.append(r3)     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x01b4 }
            r10.<init>((java.lang.String) r11)     // Catch:{ Exception -> 0x01b4 }
            throw r10     // Catch:{ Exception -> 0x01b4 }
        L_0x01b4:
            r10 = move-exception
            r9.corrupted = r2
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.SnappyFrameDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }

    /* renamed from: io.netty.handler.codec.compression.SnappyFrameDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.netty.handler.codec.compression.SnappyFrameDecoder$ChunkType[] r0 = io.netty.handler.codec.compression.SnappyFrameDecoder.ChunkType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType = r0
                io.netty.handler.codec.compression.SnappyFrameDecoder$ChunkType r1 = io.netty.handler.codec.compression.SnappyFrameDecoder.ChunkType.STREAM_IDENTIFIER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.compression.SnappyFrameDecoder$ChunkType r1 = io.netty.handler.codec.compression.SnappyFrameDecoder.ChunkType.RESERVED_SKIPPABLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.compression.SnappyFrameDecoder$ChunkType r1 = io.netty.handler.codec.compression.SnappyFrameDecoder.ChunkType.RESERVED_UNSKIPPABLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.compression.SnappyFrameDecoder$ChunkType r1 = io.netty.handler.codec.compression.SnappyFrameDecoder.ChunkType.UNCOMPRESSED_DATA     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType     // Catch:{ NoSuchFieldError -> 0x003e }
                io.netty.handler.codec.compression.SnappyFrameDecoder$ChunkType r1 = io.netty.handler.codec.compression.SnappyFrameDecoder.ChunkType.COMPRESSED_DATA     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.SnappyFrameDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    private static void checkByte(byte b, byte b2) {
        if (b != b2) {
            throw new DecompressionException("Unexpected stream identifier contents. Mismatched snappy protocol version?");
        }
    }

    private static ChunkType mapChunkType(byte b) {
        if (b == 0) {
            return ChunkType.COMPRESSED_DATA;
        }
        if (b == 1) {
            return ChunkType.UNCOMPRESSED_DATA;
        }
        if (b == -1) {
            return ChunkType.STREAM_IDENTIFIER;
        }
        if ((b & 128) == 128) {
            return ChunkType.RESERVED_SKIPPABLE;
        }
        return ChunkType.RESERVED_UNSKIPPABLE;
    }
}
