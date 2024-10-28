package io.netty.handler.codec.compression;

import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class FastLzFrameDecoder extends ByteToMessageDecoder {
    private final ByteBufChecksum checksum;
    private int chunkLength;
    private int currentChecksum;
    private State currentState;
    private boolean hasChecksum;
    private boolean isCompressed;
    private int originalLength;

    private enum State {
        INIT_BLOCK,
        INIT_BLOCK_PARAMS,
        DECOMPRESS_DATA,
        CORRUPTED
    }

    public FastLzFrameDecoder() {
        this(false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FastLzFrameDecoder(boolean z) {
        this((Checksum) z ? new Adler32() : null);
    }

    public FastLzFrameDecoder(Checksum checksum2) {
        this.currentState = State.INIT_BLOCK;
        this.checksum = checksum2 == null ? null : ByteBufChecksum.wrapChecksum(checksum2);
    }

    /* renamed from: io.netty.handler.codec.compression.FastLzFrameDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                io.netty.handler.codec.compression.FastLzFrameDecoder$State[] r0 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State = r0
                io.netty.handler.codec.compression.FastLzFrameDecoder$State r1 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.INIT_BLOCK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.compression.FastLzFrameDecoder$State r1 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.INIT_BLOCK_PARAMS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.compression.FastLzFrameDecoder$State r1 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.DECOMPRESS_DATA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.compression.FastLzFrameDecoder$State r1 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.CORRUPTED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.FastLzFrameDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008f A[Catch:{ Exception -> 0x013c }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0091 A[Catch:{ Exception -> 0x013c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(io.netty.channel.ChannelHandlerContext r13, io.netty.buffer.ByteBuf r14, java.util.List<java.lang.Object> r15) throws java.lang.Exception {
        /*
            r12 = this;
            int[] r0 = io.netty.handler.codec.compression.FastLzFrameDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State     // Catch:{ Exception -> 0x013c }
            io.netty.handler.codec.compression.FastLzFrameDecoder$State r1 = r12.currentState     // Catch:{ Exception -> 0x013c }
            int r1 = r1.ordinal()     // Catch:{ Exception -> 0x013c }
            r0 = r0[r1]     // Catch:{ Exception -> 0x013c }
            r1 = 4
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 == r4) goto L_0x0026
            if (r0 == r2) goto L_0x0052
            r5 = 3
            if (r0 == r5) goto L_0x0087
            if (r0 != r1) goto L_0x0020
            int r13 = r14.readableBytes()     // Catch:{ Exception -> 0x013c }
            r14.skipBytes(r13)     // Catch:{ Exception -> 0x013c }
            goto L_0x0129
        L_0x0020:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x013c }
            r13.<init>()     // Catch:{ Exception -> 0x013c }
            throw r13     // Catch:{ Exception -> 0x013c }
        L_0x0026:
            int r0 = r14.readableBytes()     // Catch:{ Exception -> 0x013c }
            if (r0 >= r1) goto L_0x002e
            goto L_0x0129
        L_0x002e:
            int r0 = r14.readUnsignedMedium()     // Catch:{ Exception -> 0x013c }
            r5 = 4607066(0x464c5a, float:6.455875E-39)
            if (r0 != r5) goto L_0x0134
            byte r0 = r14.readByte()     // Catch:{ Exception -> 0x013c }
            r5 = r0 & 1
            if (r5 != r4) goto L_0x0041
            r5 = 1
            goto L_0x0042
        L_0x0041:
            r5 = 0
        L_0x0042:
            r12.isCompressed = r5     // Catch:{ Exception -> 0x013c }
            r5 = 16
            r0 = r0 & r5
            if (r0 != r5) goto L_0x004b
            r0 = 1
            goto L_0x004c
        L_0x004b:
            r0 = 0
        L_0x004c:
            r12.hasChecksum = r0     // Catch:{ Exception -> 0x013c }
            io.netty.handler.codec.compression.FastLzFrameDecoder$State r0 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.INIT_BLOCK_PARAMS     // Catch:{ Exception -> 0x013c }
            r12.currentState = r0     // Catch:{ Exception -> 0x013c }
        L_0x0052:
            int r0 = r14.readableBytes()     // Catch:{ Exception -> 0x013c }
            boolean r5 = r12.isCompressed     // Catch:{ Exception -> 0x013c }
            if (r5 == 0) goto L_0x005c
            r5 = 2
            goto L_0x005d
        L_0x005c:
            r5 = 0
        L_0x005d:
            int r5 = r5 + r2
            boolean r6 = r12.hasChecksum     // Catch:{ Exception -> 0x013c }
            if (r6 == 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            r1 = 0
        L_0x0064:
            int r5 = r5 + r1
            if (r0 >= r5) goto L_0x0069
            goto L_0x0129
        L_0x0069:
            if (r6 == 0) goto L_0x0070
            int r0 = r14.readInt()     // Catch:{ Exception -> 0x013c }
            goto L_0x0071
        L_0x0070:
            r0 = 0
        L_0x0071:
            r12.currentChecksum = r0     // Catch:{ Exception -> 0x013c }
            int r0 = r14.readUnsignedShort()     // Catch:{ Exception -> 0x013c }
            r12.chunkLength = r0     // Catch:{ Exception -> 0x013c }
            boolean r1 = r12.isCompressed     // Catch:{ Exception -> 0x013c }
            if (r1 == 0) goto L_0x0081
            int r0 = r14.readUnsignedShort()     // Catch:{ Exception -> 0x013c }
        L_0x0081:
            r12.originalLength = r0     // Catch:{ Exception -> 0x013c }
            io.netty.handler.codec.compression.FastLzFrameDecoder$State r0 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.DECOMPRESS_DATA     // Catch:{ Exception -> 0x013c }
            r12.currentState = r0     // Catch:{ Exception -> 0x013c }
        L_0x0087:
            int r0 = r12.chunkLength     // Catch:{ Exception -> 0x013c }
            int r1 = r14.readableBytes()     // Catch:{ Exception -> 0x013c }
            if (r1 >= r0) goto L_0x0091
            goto L_0x0129
        L_0x0091:
            int r6 = r14.readerIndex()     // Catch:{ Exception -> 0x013c }
            int r1 = r12.originalLength     // Catch:{ Exception -> 0x013c }
            r11 = 0
            boolean r5 = r12.isCompressed     // Catch:{ all -> 0x012d }
            if (r5 == 0) goto L_0x00d5
            io.netty.buffer.ByteBufAllocator r13 = r13.alloc()     // Catch:{ all -> 0x012d }
            io.netty.buffer.ByteBuf r13 = r13.buffer(r1)     // Catch:{ all -> 0x012d }
            int r9 = r13.writerIndex()     // Catch:{ all -> 0x012a }
            r5 = r14
            r7 = r0
            r8 = r13
            r10 = r1
            int r5 = io.netty.handler.codec.compression.FastLz.decompress(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x012a }
            if (r1 != r5) goto L_0x00bb
            int r1 = r13.writerIndex()     // Catch:{ all -> 0x012a }
            int r1 = r1 + r5
            r13.writerIndex(r1)     // Catch:{ all -> 0x012a }
            goto L_0x00d9
        L_0x00bb:
            io.netty.handler.codec.compression.DecompressionException r14 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ all -> 0x012a }
            java.lang.String r15 = "stream corrupted: originalLength(%d) and actual length(%d) mismatch"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x012a }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x012a }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x012a }
            r2[r3] = r0     // Catch:{ all -> 0x012a }
            r2[r4] = r1     // Catch:{ all -> 0x012a }
            java.lang.String r15 = java.lang.String.format(r15, r2)     // Catch:{ all -> 0x012a }
            r14.<init>((java.lang.String) r15)     // Catch:{ all -> 0x012a }
            throw r14     // Catch:{ all -> 0x012a }
        L_0x00d5:
            io.netty.buffer.ByteBuf r13 = r14.retainedSlice(r6, r0)     // Catch:{ all -> 0x012d }
        L_0x00d9:
            io.netty.handler.codec.compression.ByteBufChecksum r1 = r12.checksum     // Catch:{ all -> 0x012a }
            boolean r5 = r12.hasChecksum     // Catch:{ all -> 0x012a }
            if (r5 == 0) goto L_0x0115
            if (r1 == 0) goto L_0x0115
            r1.reset()     // Catch:{ all -> 0x012a }
            int r5 = r13.readerIndex()     // Catch:{ all -> 0x012a }
            int r6 = r13.readableBytes()     // Catch:{ all -> 0x012a }
            r1.update(r13, r5, r6)     // Catch:{ all -> 0x012a }
            long r5 = r1.getValue()     // Catch:{ all -> 0x012a }
            int r1 = (int) r5     // Catch:{ all -> 0x012a }
            int r5 = r12.currentChecksum     // Catch:{ all -> 0x012a }
            if (r1 != r5) goto L_0x00f9
            goto L_0x0115
        L_0x00f9:
            io.netty.handler.codec.compression.DecompressionException r14 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ all -> 0x012a }
            java.lang.String r15 = "stream corrupted: mismatching checksum: %d (expected: %d)"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x012a }
            int r1 = r12.currentChecksum     // Catch:{ all -> 0x012a }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x012a }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x012a }
            r2[r3] = r0     // Catch:{ all -> 0x012a }
            r2[r4] = r1     // Catch:{ all -> 0x012a }
            java.lang.String r15 = java.lang.String.format(r15, r2)     // Catch:{ all -> 0x012a }
            r14.<init>((java.lang.String) r15)     // Catch:{ all -> 0x012a }
            throw r14     // Catch:{ all -> 0x012a }
        L_0x0115:
            int r1 = r13.readableBytes()     // Catch:{ all -> 0x012a }
            if (r1 <= 0) goto L_0x011f
            r15.add(r13)     // Catch:{ all -> 0x012a }
            goto L_0x0122
        L_0x011f:
            r13.release()     // Catch:{ all -> 0x012a }
        L_0x0122:
            r14.skipBytes(r0)     // Catch:{ all -> 0x012d }
            io.netty.handler.codec.compression.FastLzFrameDecoder$State r13 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.INIT_BLOCK     // Catch:{ all -> 0x012d }
            r12.currentState = r13     // Catch:{ all -> 0x012d }
        L_0x0129:
            return
        L_0x012a:
            r14 = move-exception
            r11 = r13
            goto L_0x012e
        L_0x012d:
            r14 = move-exception
        L_0x012e:
            if (r11 == 0) goto L_0x0133
            r11.release()     // Catch:{ Exception -> 0x013c }
        L_0x0133:
            throw r14     // Catch:{ Exception -> 0x013c }
        L_0x0134:
            io.netty.handler.codec.compression.DecompressionException r13 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x013c }
            java.lang.String r14 = "unexpected block identifier"
            r13.<init>((java.lang.String) r14)     // Catch:{ Exception -> 0x013c }
            throw r13     // Catch:{ Exception -> 0x013c }
        L_0x013c:
            r13 = move-exception
            io.netty.handler.codec.compression.FastLzFrameDecoder$State r14 = io.netty.handler.codec.compression.FastLzFrameDecoder.State.CORRUPTED
            r12.currentState = r14
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.FastLzFrameDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }
}
