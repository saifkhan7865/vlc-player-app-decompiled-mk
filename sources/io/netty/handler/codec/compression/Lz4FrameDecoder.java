package io.netty.handler.codec.compression;

import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.ObjectUtil;
import java.util.zip.Checksum;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class Lz4FrameDecoder extends ByteToMessageDecoder {
    private int blockType;
    private ByteBufChecksum checksum;
    private int compressedLength;
    private int currentChecksum;
    private State currentState;
    private int decompressedLength;
    private LZ4FastDecompressor decompressor;

    private enum State {
        INIT_BLOCK,
        DECOMPRESS_DATA,
        FINISHED,
        CORRUPTED
    }

    public Lz4FrameDecoder() {
        this(false);
    }

    public Lz4FrameDecoder(boolean z) {
        this(LZ4Factory.fastestInstance(), z);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Lz4FrameDecoder(LZ4Factory lZ4Factory, boolean z) {
        this(lZ4Factory, (Checksum) z ? new Lz4XXHash32(-1756908916) : null);
    }

    public Lz4FrameDecoder(LZ4Factory lZ4Factory, Checksum checksum2) {
        ByteBufChecksum byteBufChecksum;
        this.currentState = State.INIT_BLOCK;
        this.decompressor = ((LZ4Factory) ObjectUtil.checkNotNull(lZ4Factory, "factory")).fastDecompressor();
        if (checksum2 == null) {
            byteBufChecksum = null;
        } else {
            byteBufChecksum = ByteBufChecksum.wrapChecksum(checksum2);
        }
        this.checksum = byteBufChecksum;
    }

    /* renamed from: io.netty.handler.codec.compression.Lz4FrameDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$Lz4FrameDecoder$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                io.netty.handler.codec.compression.Lz4FrameDecoder$State[] r0 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$compression$Lz4FrameDecoder$State = r0
                io.netty.handler.codec.compression.Lz4FrameDecoder$State r1 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.INIT_BLOCK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$Lz4FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.compression.Lz4FrameDecoder$State r1 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.DECOMPRESS_DATA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$Lz4FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.compression.Lz4FrameDecoder$State r1 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.FINISHED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$Lz4FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.compression.Lz4FrameDecoder$State r1 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.CORRUPTED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.Lz4FrameDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0145 A[SYNTHETIC, Splitter:B:81:0x0145] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(io.netty.channel.ChannelHandlerContext r13, io.netty.buffer.ByteBuf r14, java.util.List<java.lang.Object> r15) throws java.lang.Exception {
        /*
            r12 = this;
            int[] r0 = io.netty.handler.codec.compression.Lz4FrameDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$Lz4FrameDecoder$State     // Catch:{ Exception -> 0x0185 }
            io.netty.handler.codec.compression.Lz4FrameDecoder$State r1 = r12.currentState     // Catch:{ Exception -> 0x0185 }
            int r1 = r1.ordinal()     // Catch:{ Exception -> 0x0185 }
            r0 = r0[r1]     // Catch:{ Exception -> 0x0185 }
            r1 = 3
            r2 = 16
            r3 = 0
            r4 = 0
            r5 = 2
            r6 = 1
            if (r0 == r6) goto L_0x002a
            if (r0 == r5) goto L_0x00ba
            if (r0 == r1) goto L_0x0021
            r13 = 4
            if (r0 != r13) goto L_0x001b
            goto L_0x0021
        L_0x001b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0185 }
            r13.<init>()     // Catch:{ Exception -> 0x0185 }
            throw r13     // Catch:{ Exception -> 0x0185 }
        L_0x0021:
            int r13 = r14.readableBytes()     // Catch:{ Exception -> 0x0185 }
            r14.skipBytes(r13)     // Catch:{ Exception -> 0x0185 }
            goto L_0x0139
        L_0x002a:
            int r0 = r14.readableBytes()     // Catch:{ Exception -> 0x0185 }
            r7 = 21
            if (r0 >= r7) goto L_0x0034
            goto L_0x0139
        L_0x0034:
            long r7 = r14.readLong()     // Catch:{ Exception -> 0x0185 }
            r9 = 5501767354678207339(0x4c5a34426c6f636b, double:6.579441740982069E59)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 != 0) goto L_0x017d
            byte r0 = r14.readByte()     // Catch:{ Exception -> 0x0185 }
            r7 = r0 & 15
            int r7 = r7 + 10
            r0 = r0 & 240(0xf0, float:3.36E-43)
            int r8 = r14.readInt()     // Catch:{ Exception -> 0x0185 }
            int r8 = java.lang.Integer.reverseBytes(r8)     // Catch:{ Exception -> 0x0185 }
            r9 = 33554432(0x2000000, float:9.403955E-38)
            if (r8 < 0) goto L_0x0163
            if (r8 > r9) goto L_0x0163
            int r9 = r14.readInt()     // Catch:{ Exception -> 0x0185 }
            int r9 = java.lang.Integer.reverseBytes(r9)     // Catch:{ Exception -> 0x0185 }
            int r7 = r6 << r7
            if (r9 < 0) goto L_0x0149
            if (r9 > r7) goto L_0x0149
            if (r9 != 0) goto L_0x006b
            if (r8 != 0) goto L_0x0074
        L_0x006b:
            if (r9 == 0) goto L_0x006f
            if (r8 == 0) goto L_0x0074
        L_0x006f:
            if (r0 != r2) goto L_0x008e
            if (r9 != r8) goto L_0x0074
            goto L_0x008e
        L_0x0074:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = "stream corrupted: compressedLength(%d) and decompressedLength(%d) mismatch"
            java.lang.Integer r15 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0185 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0185 }
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x0185 }
            r1[r3] = r15     // Catch:{ Exception -> 0x0185 }
            r1[r6] = r0     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = java.lang.String.format(r14, r1)     // Catch:{ Exception -> 0x0185 }
            r13.<init>((java.lang.String) r14)     // Catch:{ Exception -> 0x0185 }
            throw r13     // Catch:{ Exception -> 0x0185 }
        L_0x008e:
            int r7 = r14.readInt()     // Catch:{ Exception -> 0x0185 }
            int r7 = java.lang.Integer.reverseBytes(r7)     // Catch:{ Exception -> 0x0185 }
            if (r9 != 0) goto L_0x00ae
            if (r8 != 0) goto L_0x00ae
            if (r7 != 0) goto L_0x00a6
            io.netty.handler.codec.compression.Lz4FrameDecoder$State r13 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.FINISHED     // Catch:{ Exception -> 0x0185 }
            r12.currentState = r13     // Catch:{ Exception -> 0x0185 }
            r12.decompressor = r4     // Catch:{ Exception -> 0x0185 }
            r12.checksum = r4     // Catch:{ Exception -> 0x0185 }
            goto L_0x0139
        L_0x00a6:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = "stream corrupted: checksum error"
            r13.<init>((java.lang.String) r14)     // Catch:{ Exception -> 0x0185 }
            throw r13     // Catch:{ Exception -> 0x0185 }
        L_0x00ae:
            r12.blockType = r0     // Catch:{ Exception -> 0x0185 }
            r12.compressedLength = r8     // Catch:{ Exception -> 0x0185 }
            r12.decompressedLength = r9     // Catch:{ Exception -> 0x0185 }
            r12.currentChecksum = r7     // Catch:{ Exception -> 0x0185 }
            io.netty.handler.codec.compression.Lz4FrameDecoder$State r0 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.DECOMPRESS_DATA     // Catch:{ Exception -> 0x0185 }
            r12.currentState = r0     // Catch:{ Exception -> 0x0185 }
        L_0x00ba:
            int r0 = r12.blockType     // Catch:{ Exception -> 0x0185 }
            int r7 = r12.compressedLength     // Catch:{ Exception -> 0x0185 }
            int r8 = r12.decompressedLength     // Catch:{ Exception -> 0x0185 }
            int r9 = r12.currentChecksum     // Catch:{ Exception -> 0x0185 }
            int r10 = r14.readableBytes()     // Catch:{ Exception -> 0x0185 }
            if (r10 >= r7) goto L_0x00ca
            goto L_0x0139
        L_0x00ca:
            io.netty.handler.codec.compression.ByteBufChecksum r10 = r12.checksum     // Catch:{ Exception -> 0x0185 }
            if (r0 == r2) goto L_0x0122
            r11 = 32
            if (r0 != r11) goto L_0x00fa
            io.netty.buffer.ByteBufAllocator r13 = r13.alloc()     // Catch:{ LZ4Exception -> 0x013c }
            io.netty.buffer.ByteBuf r13 = r13.buffer(r8, r8)     // Catch:{ LZ4Exception -> 0x013c }
            net.jpountz.lz4.LZ4FastDecompressor r0 = r12.decompressor     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            java.nio.ByteBuffer r1 = io.netty.handler.codec.compression.CompressionUtil.safeReadableNioBuffer(r14)     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            int r2 = r13.writerIndex()     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            java.nio.ByteBuffer r2 = r13.internalNioBuffer(r2, r8)     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            r0.decompress(r1, r2)     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            int r0 = r13.writerIndex()     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            int r0 = r0 + r8
            r13.writerIndex(r0)     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            goto L_0x012a
        L_0x00f4:
            r14 = move-exception
            r4 = r13
            goto L_0x0143
        L_0x00f7:
            r14 = move-exception
            r4 = r13
            goto L_0x013d
        L_0x00fa:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ LZ4Exception -> 0x013c }
            java.lang.String r14 = "unexpected blockType: %d (expected: %d or %d)"
            java.lang.Integer r15 = java.lang.Integer.valueOf(r0)     // Catch:{ LZ4Exception -> 0x013c }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch:{ LZ4Exception -> 0x013c }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch:{ LZ4Exception -> 0x013c }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ LZ4Exception -> 0x0120, all -> 0x011e }
            r1[r3] = r15     // Catch:{ LZ4Exception -> 0x0120, all -> 0x011e }
            r1[r6] = r0     // Catch:{ LZ4Exception -> 0x0120, all -> 0x011e }
            r1[r5] = r2     // Catch:{ LZ4Exception -> 0x0120, all -> 0x011e }
            java.lang.String r14 = java.lang.String.format(r14, r1)     // Catch:{ LZ4Exception -> 0x013c }
            r13.<init>((java.lang.String) r14)     // Catch:{ LZ4Exception -> 0x013c }
            throw r13     // Catch:{ LZ4Exception -> 0x013c }
        L_0x011a:
            r14 = r13
            goto L_0x0143
        L_0x011c:
            r14 = r13
            goto L_0x013d
        L_0x011e:
            r13 = move-exception
            goto L_0x011a
        L_0x0120:
            r13 = move-exception
            goto L_0x011c
        L_0x0122:
            int r13 = r14.readerIndex()     // Catch:{ LZ4Exception -> 0x013c }
            io.netty.buffer.ByteBuf r13 = r14.retainedSlice(r13, r8)     // Catch:{ LZ4Exception -> 0x013c }
        L_0x012a:
            r14.skipBytes(r7)     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            if (r10 == 0) goto L_0x0132
            io.netty.handler.codec.compression.CompressionUtil.checkChecksum(r10, r13, r9)     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
        L_0x0132:
            r15.add(r13)     // Catch:{ LZ4Exception -> 0x00f7, all -> 0x00f4 }
            io.netty.handler.codec.compression.Lz4FrameDecoder$State r13 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.INIT_BLOCK     // Catch:{ LZ4Exception -> 0x013c }
            r12.currentState = r13     // Catch:{ LZ4Exception -> 0x013c }
        L_0x0139:
            return
        L_0x013a:
            r14 = move-exception
            goto L_0x0143
        L_0x013c:
            r14 = move-exception
        L_0x013d:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ all -> 0x013a }
            r13.<init>((java.lang.Throwable) r14)     // Catch:{ all -> 0x013a }
            throw r13     // Catch:{ all -> 0x013a }
        L_0x0143:
            if (r4 == 0) goto L_0x0148
            r4.release()     // Catch:{ Exception -> 0x0185 }
        L_0x0148:
            throw r14     // Catch:{ Exception -> 0x0185 }
        L_0x0149:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = "invalid decompressedLength: %d (expected: 0-%d)"
            java.lang.Integer r15 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0185 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x0185 }
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x0185 }
            r1[r3] = r15     // Catch:{ Exception -> 0x0185 }
            r1[r6] = r0     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = java.lang.String.format(r14, r1)     // Catch:{ Exception -> 0x0185 }
            r13.<init>((java.lang.String) r14)     // Catch:{ Exception -> 0x0185 }
            throw r13     // Catch:{ Exception -> 0x0185 }
        L_0x0163:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = "invalid compressedLength: %d (expected: 0-%d)"
            java.lang.Integer r15 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0185 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0185 }
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x0185 }
            r1[r3] = r15     // Catch:{ Exception -> 0x0185 }
            r1[r6] = r0     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = java.lang.String.format(r14, r1)     // Catch:{ Exception -> 0x0185 }
            r13.<init>((java.lang.String) r14)     // Catch:{ Exception -> 0x0185 }
            throw r13     // Catch:{ Exception -> 0x0185 }
        L_0x017d:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x0185 }
            java.lang.String r14 = "unexpected block identifier"
            r13.<init>((java.lang.String) r14)     // Catch:{ Exception -> 0x0185 }
            throw r13     // Catch:{ Exception -> 0x0185 }
        L_0x0185:
            r13 = move-exception
            io.netty.handler.codec.compression.Lz4FrameDecoder$State r14 = io.netty.handler.codec.compression.Lz4FrameDecoder.State.CORRUPTED
            r12.currentState = r14
            goto L_0x018c
        L_0x018b:
            throw r13
        L_0x018c:
            goto L_0x018b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.Lz4FrameDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }

    public boolean isClosed() {
        return this.currentState == State.FINISHED;
    }
}
