package io.netty.handler.codec.compression;

import com.ning.compress.BufferRecycler;
import com.ning.compress.lzf.ChunkDecoder;
import com.ning.compress.lzf.util.ChunkDecoderFactory;
import io.netty.handler.codec.ByteToMessageDecoder;

public class LzfDecoder extends ByteToMessageDecoder {
    private static final short MAGIC_NUMBER = 23126;
    private int chunkLength;
    private State currentState;
    private ChunkDecoder decoder;
    private boolean isCompressed;
    private int originalLength;
    private BufferRecycler recycler;

    private enum State {
        INIT_BLOCK,
        INIT_ORIGINAL_LENGTH,
        DECOMPRESS_DATA,
        CORRUPTED
    }

    public LzfDecoder() {
        this(false);
    }

    public LzfDecoder(boolean z) {
        ChunkDecoder chunkDecoder;
        this.currentState = State.INIT_BLOCK;
        if (z) {
            chunkDecoder = ChunkDecoderFactory.safeInstance();
        } else {
            chunkDecoder = ChunkDecoderFactory.optimalInstance();
        }
        this.decoder = chunkDecoder;
        this.recycler = BufferRecycler.instance();
    }

    /* renamed from: io.netty.handler.codec.compression.LzfDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                io.netty.handler.codec.compression.LzfDecoder$State[] r0 = io.netty.handler.codec.compression.LzfDecoder.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State = r0
                io.netty.handler.codec.compression.LzfDecoder$State r1 = io.netty.handler.codec.compression.LzfDecoder.State.INIT_BLOCK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.compression.LzfDecoder$State r1 = io.netty.handler.codec.compression.LzfDecoder.State.INIT_ORIGINAL_LENGTH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.compression.LzfDecoder$State r1 = io.netty.handler.codec.compression.LzfDecoder.State.DECOMPRESS_DATA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.compression.LzfDecoder$State r1 = io.netty.handler.codec.compression.LzfDecoder.State.CORRUPTED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.LzfDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0097 A[Catch:{ all -> 0x010b, Exception -> 0x015e }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0099 A[Catch:{ all -> 0x010b, Exception -> 0x015e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(io.netty.channel.ChannelHandlerContext r12, io.netty.buffer.ByteBuf r13, java.util.List<java.lang.Object> r14) throws java.lang.Exception {
        /*
            r11 = this;
            int[] r0 = io.netty.handler.codec.compression.LzfDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State     // Catch:{ Exception -> 0x015e }
            io.netty.handler.codec.compression.LzfDecoder$State r1 = r11.currentState     // Catch:{ Exception -> 0x015e }
            int r1 = r1.ordinal()     // Catch:{ Exception -> 0x015e }
            r0 = r0[r1]     // Catch:{ Exception -> 0x015e }
            r1 = 3
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 2
            r4 = 0
            r5 = 1
            if (r0 == r5) goto L_0x0029
            if (r0 == r3) goto L_0x007b
            if (r0 == r1) goto L_0x008f
            r12 = 4
            if (r0 != r12) goto L_0x0023
            int r12 = r13.readableBytes()     // Catch:{ Exception -> 0x015e }
            r13.skipBytes(r12)     // Catch:{ Exception -> 0x015e }
            goto L_0x011d
        L_0x0023:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x015e }
            r12.<init>()     // Catch:{ Exception -> 0x015e }
            throw r12     // Catch:{ Exception -> 0x015e }
        L_0x0029:
            int r0 = r13.readableBytes()     // Catch:{ Exception -> 0x015e }
            r6 = 5
            if (r0 >= r6) goto L_0x0032
            goto L_0x011d
        L_0x0032:
            int r0 = r13.readUnsignedShort()     // Catch:{ Exception -> 0x015e }
            r6 = 23126(0x5a56, float:3.2406E-41)
            if (r0 != r6) goto L_0x0156
            byte r0 = r13.readByte()     // Catch:{ Exception -> 0x015e }
            if (r0 == 0) goto L_0x0069
            if (r0 != r5) goto L_0x0049
            r11.isCompressed = r5     // Catch:{ Exception -> 0x015e }
            io.netty.handler.codec.compression.LzfDecoder$State r1 = io.netty.handler.codec.compression.LzfDecoder.State.INIT_ORIGINAL_LENGTH     // Catch:{ Exception -> 0x015e }
            r11.currentState = r1     // Catch:{ Exception -> 0x015e }
            goto L_0x006f
        L_0x0049:
            io.netty.handler.codec.compression.DecompressionException r12 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x015e }
            java.lang.String r13 = "unknown type of chunk: %d (expected: %d or %d)"
            java.lang.Integer r14 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x015e }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x015e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x015e }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x015e }
            r1[r4] = r14     // Catch:{ Exception -> 0x015e }
            r1[r5] = r0     // Catch:{ Exception -> 0x015e }
            r1[r3] = r2     // Catch:{ Exception -> 0x015e }
            java.lang.String r13 = java.lang.String.format(r13, r1)     // Catch:{ Exception -> 0x015e }
            r12.<init>((java.lang.String) r13)     // Catch:{ Exception -> 0x015e }
            throw r12     // Catch:{ Exception -> 0x015e }
        L_0x0069:
            r11.isCompressed = r4     // Catch:{ Exception -> 0x015e }
            io.netty.handler.codec.compression.LzfDecoder$State r1 = io.netty.handler.codec.compression.LzfDecoder.State.DECOMPRESS_DATA     // Catch:{ Exception -> 0x015e }
            r11.currentState = r1     // Catch:{ Exception -> 0x015e }
        L_0x006f:
            int r1 = r13.readUnsignedShort()     // Catch:{ Exception -> 0x015e }
            r11.chunkLength = r1     // Catch:{ Exception -> 0x015e }
            if (r1 > r2) goto L_0x013a
            if (r0 == r5) goto L_0x007b
            goto L_0x011d
        L_0x007b:
            int r0 = r13.readableBytes()     // Catch:{ Exception -> 0x015e }
            if (r0 >= r3) goto L_0x0083
            goto L_0x011d
        L_0x0083:
            int r0 = r13.readUnsignedShort()     // Catch:{ Exception -> 0x015e }
            r11.originalLength = r0     // Catch:{ Exception -> 0x015e }
            if (r0 > r2) goto L_0x011e
            io.netty.handler.codec.compression.LzfDecoder$State r0 = io.netty.handler.codec.compression.LzfDecoder.State.DECOMPRESS_DATA     // Catch:{ Exception -> 0x015e }
            r11.currentState = r0     // Catch:{ Exception -> 0x015e }
        L_0x008f:
            int r0 = r11.chunkLength     // Catch:{ Exception -> 0x015e }
            int r1 = r13.readableBytes()     // Catch:{ Exception -> 0x015e }
            if (r1 >= r0) goto L_0x0099
            goto L_0x011d
        L_0x0099:
            int r1 = r11.originalLength     // Catch:{ Exception -> 0x015e }
            boolean r2 = r11.isCompressed     // Catch:{ Exception -> 0x015e }
            if (r2 == 0) goto L_0x0110
            int r2 = r13.readerIndex()     // Catch:{ Exception -> 0x015e }
            boolean r3 = r13.hasArray()     // Catch:{ Exception -> 0x015e }
            if (r3 == 0) goto L_0x00b4
            byte[] r3 = r13.array()     // Catch:{ Exception -> 0x015e }
            int r5 = r13.arrayOffset()     // Catch:{ Exception -> 0x015e }
            int r5 = r5 + r2
            r7 = r5
            goto L_0x00be
        L_0x00b4:
            com.ning.compress.BufferRecycler r3 = r11.recycler     // Catch:{ Exception -> 0x015e }
            byte[] r3 = r3.allocInputBuffer(r0)     // Catch:{ Exception -> 0x015e }
            r13.getBytes((int) r2, (byte[]) r3, (int) r4, (int) r0)     // Catch:{ Exception -> 0x015e }
            r7 = 0
        L_0x00be:
            io.netty.buffer.ByteBufAllocator r12 = r12.alloc()     // Catch:{ Exception -> 0x015e }
            io.netty.buffer.ByteBuf r12 = r12.heapBuffer(r1, r1)     // Catch:{ Exception -> 0x015e }
            boolean r2 = r12.hasArray()     // Catch:{ Exception -> 0x015e }
            if (r2 == 0) goto L_0x00db
            byte[] r2 = r12.array()     // Catch:{ Exception -> 0x015e }
            int r4 = r12.arrayOffset()     // Catch:{ Exception -> 0x015e }
            int r5 = r12.writerIndex()     // Catch:{ Exception -> 0x015e }
            int r4 = r4 + r5
            r9 = r4
            goto L_0x00de
        L_0x00db:
            byte[] r2 = new byte[r1]     // Catch:{ Exception -> 0x015e }
            r9 = 0
        L_0x00de:
            com.ning.compress.lzf.ChunkDecoder r5 = r11.decoder     // Catch:{ all -> 0x010b }
            int r10 = r9 + r1
            r6 = r3
            r8 = r2
            r5.decodeChunk(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x010b }
            boolean r4 = r12.hasArray()     // Catch:{ all -> 0x010b }
            if (r4 == 0) goto L_0x00f6
            int r2 = r12.writerIndex()     // Catch:{ all -> 0x010b }
            int r2 = r2 + r1
            r12.writerIndex(r2)     // Catch:{ all -> 0x010b }
            goto L_0x00f9
        L_0x00f6:
            r12.writeBytes((byte[]) r2)     // Catch:{ all -> 0x010b }
        L_0x00f9:
            r14.add(r12)     // Catch:{ all -> 0x010b }
            r13.skipBytes(r0)     // Catch:{ all -> 0x010b }
            boolean r12 = r13.hasArray()     // Catch:{ Exception -> 0x015e }
            if (r12 != 0) goto L_0x0119
            com.ning.compress.BufferRecycler r12 = r11.recycler     // Catch:{ Exception -> 0x015e }
            r12.releaseInputBuffer(r3)     // Catch:{ Exception -> 0x015e }
            goto L_0x0119
        L_0x010b:
            r13 = move-exception
            r12.release()     // Catch:{ Exception -> 0x015e }
            throw r13     // Catch:{ Exception -> 0x015e }
        L_0x0110:
            if (r0 <= 0) goto L_0x0119
            io.netty.buffer.ByteBuf r12 = r13.readRetainedSlice(r0)     // Catch:{ Exception -> 0x015e }
            r14.add(r12)     // Catch:{ Exception -> 0x015e }
        L_0x0119:
            io.netty.handler.codec.compression.LzfDecoder$State r12 = io.netty.handler.codec.compression.LzfDecoder.State.INIT_BLOCK     // Catch:{ Exception -> 0x015e }
            r11.currentState = r12     // Catch:{ Exception -> 0x015e }
        L_0x011d:
            return
        L_0x011e:
            io.netty.handler.codec.compression.DecompressionException r12 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x015e }
            java.lang.String r13 = "original length exceeds maximum: %d (expected: =< %d)"
            int r14 = r11.chunkLength     // Catch:{ Exception -> 0x015e }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ Exception -> 0x015e }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x015e }
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x015e }
            r1[r4] = r14     // Catch:{ Exception -> 0x015e }
            r1[r5] = r0     // Catch:{ Exception -> 0x015e }
            java.lang.String r13 = java.lang.String.format(r13, r1)     // Catch:{ Exception -> 0x015e }
            r12.<init>((java.lang.String) r13)     // Catch:{ Exception -> 0x015e }
            throw r12     // Catch:{ Exception -> 0x015e }
        L_0x013a:
            io.netty.handler.codec.compression.DecompressionException r12 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x015e }
            java.lang.String r13 = "chunk length exceeds maximum: %d (expected: =< %d)"
            int r14 = r11.chunkLength     // Catch:{ Exception -> 0x015e }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ Exception -> 0x015e }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x015e }
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x015e }
            r1[r4] = r14     // Catch:{ Exception -> 0x015e }
            r1[r5] = r0     // Catch:{ Exception -> 0x015e }
            java.lang.String r13 = java.lang.String.format(r13, r1)     // Catch:{ Exception -> 0x015e }
            r12.<init>((java.lang.String) r13)     // Catch:{ Exception -> 0x015e }
            throw r12     // Catch:{ Exception -> 0x015e }
        L_0x0156:
            io.netty.handler.codec.compression.DecompressionException r12 = new io.netty.handler.codec.compression.DecompressionException     // Catch:{ Exception -> 0x015e }
            java.lang.String r13 = "unexpected block identifier"
            r12.<init>((java.lang.String) r13)     // Catch:{ Exception -> 0x015e }
            throw r12     // Catch:{ Exception -> 0x015e }
        L_0x015e:
            r12 = move-exception
            io.netty.handler.codec.compression.LzfDecoder$State r13 = io.netty.handler.codec.compression.LzfDecoder.State.CORRUPTED
            r11.currentState = r13
            r13 = 0
            r11.decoder = r13
            r11.recycler = r13
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.LzfDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }
}
