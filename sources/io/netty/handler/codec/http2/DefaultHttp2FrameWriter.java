package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2FrameWriter;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.collection.CharObjectMap;
import io.netty.util.internal.ObjectUtil;

public class DefaultHttp2FrameWriter implements Http2FrameWriter, Http2FrameSizePolicy, Http2FrameWriter.Configuration {
    private static final String STREAM_DEPENDENCY = "Stream Dependency";
    private static final String STREAM_ID = "Stream ID";
    private static final ByteBuf ZERO_BUFFER = Unpooled.unreleasableBuffer(Unpooled.directBuffer(255).writeZero(255)).asReadOnly();
    private final Http2HeadersEncoder headersEncoder;
    private int maxFrameSize;

    private static int paddingBytes(int i) {
        return i - 1;
    }

    public void close() {
    }

    public Http2FrameWriter.Configuration configuration() {
        return this;
    }

    public Http2FrameSizePolicy frameSizePolicy() {
        return this;
    }

    public DefaultHttp2FrameWriter() {
        this((Http2HeadersEncoder) new DefaultHttp2HeadersEncoder());
    }

    public DefaultHttp2FrameWriter(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        this((Http2HeadersEncoder) new DefaultHttp2HeadersEncoder(sensitivityDetector));
    }

    public DefaultHttp2FrameWriter(Http2HeadersEncoder.SensitivityDetector sensitivityDetector, boolean z) {
        this((Http2HeadersEncoder) new DefaultHttp2HeadersEncoder(sensitivityDetector, z));
    }

    public DefaultHttp2FrameWriter(Http2HeadersEncoder http2HeadersEncoder) {
        this.headersEncoder = http2HeadersEncoder;
        this.maxFrameSize = 16384;
    }

    public Http2HeadersEncoder.Configuration headersConfiguration() {
        return this.headersEncoder.configuration();
    }

    public void maxFrameSize(int i) throws Http2Exception {
        if (Http2CodecUtil.isMaxFrameSizeValid(i)) {
            this.maxFrameSize = i;
            return;
        }
        throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Invalid MAX_FRAME_SIZE specified in sent settings: %d", Integer.valueOf(i));
    }

    public int maxFrameSize() {
        return this.maxFrameSize;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0104 A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0105 A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x011a A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x013f A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016b A[SYNTHETIC, Splitter:B:76:0x016b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.netty.channel.ChannelFuture writeData(io.netty.channel.ChannelHandlerContext r17, int r18, io.netty.buffer.ByteBuf r19, int r20, boolean r21, io.netty.channel.ChannelPromise r22) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            r3 = r19
            r4 = r21
            io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator r5 = new io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator
            io.netty.channel.Channel r6 = r17.channel()
            io.netty.util.concurrent.EventExecutor r7 = r17.executor()
            r8 = r22
            r5.<init>(r8, r6, r7)
            java.lang.String r7 = "Stream ID"
            verifyStreamId(r2, r7)     // Catch:{ all -> 0x0161 }
            io.netty.handler.codec.http2.Http2CodecUtil.verifyPadding(r20)     // Catch:{ all -> 0x0161 }
            int r7 = r19.readableBytes()     // Catch:{ all -> 0x0161 }
            io.netty.handler.codec.http2.Http2Flags r8 = new io.netty.handler.codec.http2.Http2Flags     // Catch:{ all -> 0x0161 }
            r8.<init>()     // Catch:{ all -> 0x0161 }
            r9 = 0
            r8.endOfStream(r9)     // Catch:{ all -> 0x0161 }
            r8.paddingPresent(r9)     // Catch:{ all -> 0x0161 }
            int r10 = r1.maxFrameSize     // Catch:{ all -> 0x0161 }
            r11 = 9
            if (r7 <= r10) goto L_0x0062
            io.netty.buffer.ByteBufAllocator r10 = r17.alloc()     // Catch:{ all -> 0x0161 }
            io.netty.buffer.ByteBuf r10 = r10.buffer(r11)     // Catch:{ all -> 0x0161 }
            int r12 = r1.maxFrameSize     // Catch:{ all -> 0x015d }
            io.netty.handler.codec.http2.Http2CodecUtil.writeFrameHeaderInternal(r10, r12, r9, r8, r2)     // Catch:{ all -> 0x015d }
        L_0x0044:
            io.netty.buffer.ByteBuf r12 = r10.retainedSlice()     // Catch:{ all -> 0x015d }
            io.netty.channel.ChannelPromise r13 = r5.newPromise()     // Catch:{ all -> 0x015d }
            r0.write(r12, r13)     // Catch:{ all -> 0x015d }
            int r12 = r1.maxFrameSize     // Catch:{ all -> 0x015d }
            io.netty.buffer.ByteBuf r12 = r3.readRetainedSlice(r12)     // Catch:{ all -> 0x015d }
            io.netty.channel.ChannelPromise r13 = r5.newPromise()     // Catch:{ all -> 0x015d }
            r0.write(r12, r13)     // Catch:{ all -> 0x015d }
            int r12 = r1.maxFrameSize     // Catch:{ all -> 0x015d }
            int r7 = r7 - r12
            if (r7 > r12) goto L_0x0044
            goto L_0x0063
        L_0x0062:
            r10 = 0
        L_0x0063:
            if (r20 != 0) goto L_0x0093
            if (r10 == 0) goto L_0x006b
            r10.release()     // Catch:{ all -> 0x015d }
            r10 = 0
        L_0x006b:
            io.netty.buffer.ByteBufAllocator r12 = r17.alloc()     // Catch:{ all -> 0x015d }
            io.netty.buffer.ByteBuf r11 = r12.buffer(r11)     // Catch:{ all -> 0x015d }
            r8.endOfStream(r4)     // Catch:{ all -> 0x015d }
            io.netty.handler.codec.http2.Http2CodecUtil.writeFrameHeaderInternal(r11, r7, r9, r8, r2)     // Catch:{ all -> 0x015d }
            io.netty.channel.ChannelPromise r2 = r5.newPromise()     // Catch:{ all -> 0x015d }
            r0.write(r11, r2)     // Catch:{ all -> 0x015d }
            io.netty.buffer.ByteBuf r2 = r3.readSlice(r7)     // Catch:{ all -> 0x015d }
            io.netty.channel.ChannelPromise r3 = r5.newPromise()     // Catch:{ all -> 0x008d }
            r0.write(r2, r3)     // Catch:{ all -> 0x008d }
            goto L_0x0154
        L_0x008d:
            r0 = move-exception
            r2 = r0
            r6 = r10
        L_0x0090:
            r3 = 0
            goto L_0x0164
        L_0x0093:
            int r12 = r1.maxFrameSize     // Catch:{ all -> 0x015d }
            if (r7 == r12) goto L_0x00a4
            if (r10 == 0) goto L_0x009f
            r10.release()     // Catch:{ all -> 0x015d }
            r10 = r7
            r11 = 0
            goto L_0x00a1
        L_0x009f:
            r11 = r10
        L_0x00a0:
            r10 = r7
        L_0x00a1:
            r7 = r20
            goto L_0x00d7
        L_0x00a4:
            int r7 = r7 - r12
            if (r10 != 0) goto L_0x00b5
            io.netty.buffer.ByteBufAllocator r12 = r17.alloc()     // Catch:{ all -> 0x015d }
            io.netty.buffer.ByteBuf r11 = r12.buffer(r11)     // Catch:{ all -> 0x015d }
            int r12 = r1.maxFrameSize     // Catch:{ all -> 0x015d }
            io.netty.handler.codec.http2.Http2CodecUtil.writeFrameHeaderInternal(r11, r12, r9, r8, r2)     // Catch:{ all -> 0x015d }
            goto L_0x00ba
        L_0x00b5:
            io.netty.buffer.ByteBuf r11 = r10.slice()     // Catch:{ all -> 0x015d }
            r10 = 0
        L_0x00ba:
            io.netty.channel.ChannelPromise r12 = r5.newPromise()     // Catch:{ all -> 0x015d }
            r0.write(r11, r12)     // Catch:{ all -> 0x015d }
            int r11 = r19.readableBytes()     // Catch:{ all -> 0x015d }
            int r12 = r1.maxFrameSize     // Catch:{ all -> 0x015d }
            if (r11 == r12) goto L_0x00cd
            io.netty.buffer.ByteBuf r3 = r3.readSlice(r12)     // Catch:{ all -> 0x015d }
        L_0x00cd:
            io.netty.channel.ChannelPromise r11 = r5.newPromise()     // Catch:{ all -> 0x008d }
            r0.write(r3, r11)     // Catch:{ all -> 0x008d }
            r11 = r10
            r3 = 0
            goto L_0x00a0
        L_0x00d7:
            int r12 = r1.maxFrameSize     // Catch:{ all -> 0x0159 }
            int r12 = java.lang.Math.min(r10, r12)     // Catch:{ all -> 0x0159 }
            int r13 = r1.maxFrameSize     // Catch:{ all -> 0x0159 }
            r14 = 1
            int r13 = r13 - r14
            int r13 = r13 - r12
            int r13 = java.lang.Math.max(r9, r13)     // Catch:{ all -> 0x0159 }
            int r13 = java.lang.Math.min(r7, r13)     // Catch:{ all -> 0x0159 }
            int r7 = r7 - r13
            int r10 = r10 - r12
            io.netty.buffer.ByteBufAllocator r15 = r17.alloc()     // Catch:{ all -> 0x0159 }
            r6 = 10
            io.netty.buffer.ByteBuf r6 = r15.buffer(r6)     // Catch:{ all -> 0x0159 }
            if (r4 == 0) goto L_0x00fe
            if (r10 != 0) goto L_0x00fe
            if (r7 != 0) goto L_0x00fe
            r15 = 1
            goto L_0x00ff
        L_0x00fe:
            r15 = 0
        L_0x00ff:
            r8.endOfStream(r15)     // Catch:{ all -> 0x0159 }
            if (r13 <= 0) goto L_0x0105
            goto L_0x0106
        L_0x0105:
            r14 = 0
        L_0x0106:
            r8.paddingPresent(r14)     // Catch:{ all -> 0x0159 }
            int r14 = r13 + r12
            io.netty.handler.codec.http2.Http2CodecUtil.writeFrameHeaderInternal(r6, r14, r9, r8, r2)     // Catch:{ all -> 0x0159 }
            writePaddingLength(r6, r13)     // Catch:{ all -> 0x0159 }
            io.netty.channel.ChannelPromise r14 = r5.newPromise()     // Catch:{ all -> 0x0159 }
            r0.write(r6, r14)     // Catch:{ all -> 0x0159 }
            if (r3 == 0) goto L_0x0139
            if (r10 != 0) goto L_0x012e
            io.netty.buffer.ByteBuf r3 = r3.readSlice(r12)     // Catch:{ all -> 0x0159 }
            io.netty.channel.ChannelPromise r6 = r5.newPromise()     // Catch:{ all -> 0x0129 }
            r0.write(r3, r6)     // Catch:{ all -> 0x0129 }
            r3 = 0
            goto L_0x0139
        L_0x0129:
            r0 = move-exception
            r2 = r0
            r6 = r11
            goto L_0x0090
        L_0x012e:
            io.netty.buffer.ByteBuf r6 = r3.readRetainedSlice(r12)     // Catch:{ all -> 0x0159 }
            io.netty.channel.ChannelPromise r12 = r5.newPromise()     // Catch:{ all -> 0x0159 }
            r0.write(r6, r12)     // Catch:{ all -> 0x0159 }
        L_0x0139:
            int r6 = paddingBytes(r13)     // Catch:{ all -> 0x0159 }
            if (r6 <= 0) goto L_0x0150
            io.netty.buffer.ByteBuf r6 = ZERO_BUFFER     // Catch:{ all -> 0x0159 }
            int r12 = paddingBytes(r13)     // Catch:{ all -> 0x0159 }
            io.netty.buffer.ByteBuf r6 = r6.slice(r9, r12)     // Catch:{ all -> 0x0159 }
            io.netty.channel.ChannelPromise r12 = r5.newPromise()     // Catch:{ all -> 0x0159 }
            r0.write(r6, r12)     // Catch:{ all -> 0x0159 }
        L_0x0150:
            if (r10 != 0) goto L_0x00d7
            if (r7 != 0) goto L_0x00d7
        L_0x0154:
            io.netty.channel.ChannelPromise r0 = r5.doneAllocatingPromises()
            return r0
        L_0x0159:
            r0 = move-exception
            r2 = r0
            r6 = r11
            goto L_0x0164
        L_0x015d:
            r0 = move-exception
            r2 = r0
            r6 = r10
            goto L_0x0164
        L_0x0161:
            r0 = move-exception
            r2 = r0
            r6 = 0
        L_0x0164:
            if (r6 == 0) goto L_0x0169
            r6.release()
        L_0x0169:
            if (r3 == 0) goto L_0x0178
            r3.release()     // Catch:{ all -> 0x016f }
            goto L_0x0178
        L_0x016f:
            r0 = move-exception
            r3 = r0
            r5.setFailure((java.lang.Throwable) r2)
            r5.doneAllocatingPromises()
            throw r3
        L_0x0178:
            r5.setFailure((java.lang.Throwable) r2)
            r5.doneAllocatingPromises()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2FrameWriter.writeData(io.netty.channel.ChannelHandlerContext, int, io.netty.buffer.ByteBuf, int, boolean, io.netty.channel.ChannelPromise):io.netty.channel.ChannelFuture");
    }

    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        return writeHeadersInternal(channelHandlerContext, i, http2Headers, i2, z, false, 0, 0, false, channelPromise);
    }

    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        return writeHeadersInternal(channelHandlerContext, i, http2Headers, i3, z2, true, i2, s, z, channelPromise);
    }

    public ChannelFuture writePriority(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z, ChannelPromise channelPromise) {
        try {
            verifyStreamId(i, STREAM_ID);
            verifyStreamOrConnectionId(i2, STREAM_DEPENDENCY);
            verifyWeight(s);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(14);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 5, (byte) 2, new Http2Flags(), i);
            if (z) {
                i2 = (int) (((long) i2) | 2147483648L);
            }
            buffer.writeInt(i2);
            buffer.writeByte(s - 1);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        try {
            verifyStreamId(i, STREAM_ID);
            verifyErrorCode(j);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(13);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 4, (byte) 3, new Http2Flags(), i);
            buffer.writeInt((int) j);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    public ChannelFuture writeSettings(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings, ChannelPromise channelPromise) {
        try {
            ObjectUtil.checkNotNull(http2Settings, "settings");
            int size = http2Settings.size() * 6;
            ByteBuf buffer = channelHandlerContext.alloc().buffer(size + 9);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, size, (byte) 4, new Http2Flags(), 0);
            for (CharObjectMap.PrimitiveEntry primitiveEntry : http2Settings.entries()) {
                buffer.writeChar(primitiveEntry.key());
                buffer.writeInt(((Long) primitiveEntry.value()).intValue());
            }
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        try {
            ByteBuf buffer = channelHandlerContext.alloc().buffer(9);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 0, (byte) 4, new Http2Flags().ack(true), 0);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        Http2Flags ack = z ? new Http2Flags().ack(true) : new Http2Flags();
        ByteBuf buffer = channelHandlerContext.alloc().buffer(17);
        Http2CodecUtil.writeFrameHeaderInternal(buffer, 8, (byte) 6, ack, 0);
        buffer.writeLong(j);
        return channelHandlerContext.write(buffer, channelPromise);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0098, code lost:
        if (r12 != null) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ac, code lost:
        if (r12 == null) goto L_0x00b1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.netty.channel.ChannelFuture writePushPromise(io.netty.channel.ChannelHandlerContext r7, int r8, int r9, io.netty.handler.codec.http2.Http2Headers r10, int r11, io.netty.channel.ChannelPromise r12) {
        /*
            r6 = this;
            io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator r0 = new io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator
            io.netty.channel.Channel r1 = r7.channel()
            io.netty.util.concurrent.EventExecutor r2 = r7.executor()
            r0.<init>(r12, r1, r2)
            r12 = 0
            java.lang.String r1 = "Stream ID"
            verifyStreamId(r8, r1)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            java.lang.String r1 = "Promised Stream ID"
            verifyStreamId(r9, r1)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.handler.codec.http2.Http2CodecUtil.verifyPadding(r11)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.buffer.ByteBufAllocator r1 = r7.alloc()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.buffer.ByteBuf r12 = r1.buffer()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.handler.codec.http2.Http2HeadersEncoder r1 = r6.headersEncoder     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r1.encodeHeaders(r8, r10, r12)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.handler.codec.http2.Http2Flags r10 = new io.netty.handler.codec.http2.Http2Flags     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r10.<init>()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r1 = 1
            r2 = 0
            if (r11 <= 0) goto L_0x0033
            r3 = 1
            goto L_0x0034
        L_0x0033:
            r3 = 0
        L_0x0034:
            io.netty.handler.codec.http2.Http2Flags r10 = r10.paddingPresent(r3)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            int r3 = r11 + 4
            int r4 = r6.maxFrameSize     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            int r4 = r4 - r3
            int r5 = r12.readableBytes()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            int r4 = java.lang.Math.min(r5, r4)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.buffer.ByteBuf r4 = r12.readRetainedSlice(r4)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            boolean r5 = r12.isReadable()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r1 = r1 ^ r5
            r10.endOfHeaders(r1)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            int r1 = r4.readableBytes()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            int r1 = r1 + r3
            io.netty.buffer.ByteBufAllocator r3 = r7.alloc()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r5 = 14
            io.netty.buffer.ByteBuf r3 = r3.buffer(r5)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r5 = 5
            io.netty.handler.codec.http2.Http2CodecUtil.writeFrameHeaderInternal(r3, r1, r5, r10, r8)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            writePaddingLength(r3, r11)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r3.writeInt(r9)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.channel.ChannelPromise r9 = r0.newPromise()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r7.write(r3, r9)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.channel.ChannelPromise r9 = r0.newPromise()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r7.write(r4, r9)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            int r9 = paddingBytes(r11)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            if (r9 <= 0) goto L_0x008f
            io.netty.buffer.ByteBuf r9 = ZERO_BUFFER     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            int r11 = paddingBytes(r11)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.buffer.ByteBuf r9 = r9.slice(r2, r11)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            io.netty.channel.ChannelPromise r11 = r0.newPromise()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            r7.write(r9, r11)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
        L_0x008f:
            boolean r9 = r10.endOfHeaders()     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
            if (r9 != 0) goto L_0x0098
            r6.writeContinuationFrames(r7, r8, r12, r0)     // Catch:{ Http2Exception -> 0x00a8, all -> 0x009b }
        L_0x0098:
            if (r12 == 0) goto L_0x00b1
            goto L_0x00ae
        L_0x009b:
            r7 = move-exception
            r0.setFailure((java.lang.Throwable) r7)     // Catch:{ all -> 0x00b6 }
            r0.doneAllocatingPromises()     // Catch:{ all -> 0x00b6 }
            io.netty.util.internal.PlatformDependent.throwException(r7)     // Catch:{ all -> 0x00b6 }
            if (r12 == 0) goto L_0x00b1
            goto L_0x00ae
        L_0x00a8:
            r7 = move-exception
            r0.setFailure((java.lang.Throwable) r7)     // Catch:{ all -> 0x00b6 }
            if (r12 == 0) goto L_0x00b1
        L_0x00ae:
            r12.release()
        L_0x00b1:
            io.netty.channel.ChannelPromise r7 = r0.doneAllocatingPromises()
            return r7
        L_0x00b6:
            r7 = move-exception
            if (r12 == 0) goto L_0x00bc
            r12.release()
        L_0x00bc:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2FrameWriter.writePushPromise(io.netty.channel.ChannelHandlerContext, int, int, io.netty.handler.codec.http2.Http2Headers, int, io.netty.channel.ChannelPromise):io.netty.channel.ChannelFuture");
    }

    public ChannelFuture writeGoAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise) {
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        try {
            verifyStreamOrConnectionId(i, "Last Stream ID");
            verifyErrorCode(j);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(17);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, byteBuf.readableBytes() + 8, (byte) 7, new Http2Flags(), 0);
            buffer.writeInt(i);
            buffer.writeInt((int) j);
            channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
            try {
                channelHandlerContext.write(byteBuf, simpleChannelPromiseAggregator.newPromise());
            } catch (Throwable th) {
                simpleChannelPromiseAggregator.setFailure(th);
            }
            return simpleChannelPromiseAggregator.doneAllocatingPromises();
        } catch (Throwable th2) {
            simpleChannelPromiseAggregator.setFailure(th);
            simpleChannelPromiseAggregator.doneAllocatingPromises();
            throw th2;
        }
    }

    public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
        try {
            verifyStreamOrConnectionId(i, STREAM_ID);
            verifyWindowSizeIncrement(i2);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(13);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 4, (byte) 8, new Http2Flags(), i);
            buffer.writeInt(i2);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    public ChannelFuture writeFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf, ChannelPromise channelPromise) {
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        try {
            verifyStreamOrConnectionId(i, STREAM_ID);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(9);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, byteBuf.readableBytes(), b, http2Flags, i);
            channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
            try {
                channelHandlerContext.write(byteBuf, simpleChannelPromiseAggregator.newPromise());
            } catch (Throwable th) {
                simpleChannelPromiseAggregator.setFailure(th);
            }
            return simpleChannelPromiseAggregator.doneAllocatingPromises();
        } catch (Throwable th2) {
            simpleChannelPromiseAggregator.setFailure(th);
            simpleChannelPromiseAggregator.doneAllocatingPromises();
            throw th2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00c9, code lost:
        if (r7 != null) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00dd, code lost:
        if (r7 == null) goto L_0x00e2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.netty.channel.ChannelFuture writeHeadersInternal(io.netty.channel.ChannelHandlerContext r16, int r17, io.netty.handler.codec.http2.Http2Headers r18, int r19, boolean r20, boolean r21, int r22, short r23, boolean r24, io.netty.channel.ChannelPromise r25) {
        /*
            r15 = this;
            r1 = r15
            r0 = r16
            r2 = r17
            r3 = r19
            r4 = r21
            r5 = r22
            io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator r6 = new io.netty.handler.codec.http2.Http2CodecUtil$SimpleChannelPromiseAggregator
            io.netty.channel.Channel r7 = r16.channel()
            io.netty.util.concurrent.EventExecutor r8 = r16.executor()
            r9 = r25
            r6.<init>(r9, r7, r8)
            r7 = 0
            java.lang.String r8 = "Stream ID"
            verifyStreamId(r2, r8)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            if (r4 == 0) goto L_0x002d
            java.lang.String r8 = "Stream Dependency"
            verifyStreamOrConnectionId(r5, r8)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.handler.codec.http2.Http2CodecUtil.verifyPadding(r19)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            verifyWeight(r23)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
        L_0x002d:
            io.netty.buffer.ByteBufAllocator r8 = r16.alloc()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.buffer.ByteBuf r7 = r8.buffer()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.handler.codec.http2.Http2HeadersEncoder r8 = r1.headersEncoder     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r9 = r18
            r8.encodeHeaders(r2, r9, r7)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.handler.codec.http2.Http2Flags r8 = new io.netty.handler.codec.http2.Http2Flags     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r8.<init>()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r9 = r20
            io.netty.handler.codec.http2.Http2Flags r8 = r8.endOfStream(r9)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.handler.codec.http2.Http2Flags r8 = r8.priorityPresent(r4)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r9 = 0
            r10 = 1
            if (r3 <= 0) goto L_0x0051
            r11 = 1
            goto L_0x0052
        L_0x0051:
            r11 = 0
        L_0x0052:
            io.netty.handler.codec.http2.Http2Flags r8 = r8.paddingPresent(r11)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r11 = r8.getNumPriorityBytes()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r11 = r11 + r3
            int r12 = r1.maxFrameSize     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r12 = r12 - r11
            int r13 = r7.readableBytes()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r12 = java.lang.Math.min(r13, r12)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.buffer.ByteBuf r12 = r7.readRetainedSlice(r12)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            boolean r13 = r7.isReadable()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r13 = r13 ^ r10
            r8.endOfHeaders(r13)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r13 = r12.readableBytes()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r13 = r13 + r11
            io.netty.buffer.ByteBufAllocator r11 = r16.alloc()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r14 = 15
            io.netty.buffer.ByteBuf r11 = r11.buffer(r14)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.handler.codec.http2.Http2CodecUtil.writeFrameHeaderInternal(r11, r13, r10, r8, r2)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            writePaddingLength(r11, r3)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            if (r4 == 0) goto L_0x009b
            if (r24 == 0) goto L_0x0093
            r13 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r4 = (long) r5     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            long r4 = r4 | r13
            int r5 = (int) r4     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
        L_0x0093:
            r11.writeInt(r5)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r4 = r23 + -1
            r11.writeByte(r4)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
        L_0x009b:
            io.netty.channel.ChannelPromise r4 = r6.newPromise()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r0.write(r11, r4)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.channel.ChannelPromise r4 = r6.newPromise()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r0.write(r12, r4)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r4 = paddingBytes(r19)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            if (r4 <= 0) goto L_0x00c0
            io.netty.buffer.ByteBuf r4 = ZERO_BUFFER     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            int r3 = paddingBytes(r19)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.buffer.ByteBuf r3 = r4.slice(r9, r3)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            io.netty.channel.ChannelPromise r4 = r6.newPromise()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            r0.write(r3, r4)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
        L_0x00c0:
            boolean r3 = r8.endOfHeaders()     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
            if (r3 != 0) goto L_0x00c9
            r15.writeContinuationFrames(r0, r2, r7, r6)     // Catch:{ Http2Exception -> 0x00d9, all -> 0x00cc }
        L_0x00c9:
            if (r7 == 0) goto L_0x00e2
            goto L_0x00df
        L_0x00cc:
            r0 = move-exception
            r6.setFailure((java.lang.Throwable) r0)     // Catch:{ all -> 0x00e7 }
            r6.doneAllocatingPromises()     // Catch:{ all -> 0x00e7 }
            io.netty.util.internal.PlatformDependent.throwException(r0)     // Catch:{ all -> 0x00e7 }
            if (r7 == 0) goto L_0x00e2
            goto L_0x00df
        L_0x00d9:
            r0 = move-exception
            r6.setFailure((java.lang.Throwable) r0)     // Catch:{ all -> 0x00e7 }
            if (r7 == 0) goto L_0x00e2
        L_0x00df:
            r7.release()
        L_0x00e2:
            io.netty.channel.ChannelPromise r0 = r6.doneAllocatingPromises()
            return r0
        L_0x00e7:
            r0 = move-exception
            if (r7 == 0) goto L_0x00ed
            r7.release()
        L_0x00ed:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2FrameWriter.writeHeadersInternal(io.netty.channel.ChannelHandlerContext, int, io.netty.handler.codec.http2.Http2Headers, int, boolean, boolean, int, short, boolean, io.netty.channel.ChannelPromise):io.netty.channel.ChannelFuture");
    }

    private ChannelFuture writeContinuationFrames(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator) {
        Http2Flags http2Flags = new Http2Flags();
        if (byteBuf.isReadable()) {
            int min = Math.min(byteBuf.readableBytes(), this.maxFrameSize);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(10);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, min, (byte) 9, http2Flags, i);
            do {
                int min2 = Math.min(byteBuf.readableBytes(), this.maxFrameSize);
                ByteBuf readRetainedSlice = byteBuf.readRetainedSlice(min2);
                if (byteBuf.isReadable()) {
                    channelHandlerContext.write(buffer.retain(), simpleChannelPromiseAggregator.newPromise());
                } else {
                    http2Flags = http2Flags.endOfHeaders(true);
                    buffer.release();
                    buffer = channelHandlerContext.alloc().buffer(10);
                    Http2CodecUtil.writeFrameHeaderInternal(buffer, min2, (byte) 9, http2Flags, i);
                    channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
                }
                channelHandlerContext.write(readRetainedSlice, simpleChannelPromiseAggregator.newPromise());
            } while (byteBuf.isReadable());
        }
        return simpleChannelPromiseAggregator;
    }

    private static void writePaddingLength(ByteBuf byteBuf, int i) {
        if (i > 0) {
            byteBuf.writeByte(i - 1);
        }
    }

    private static void verifyStreamId(int i, String str) {
        ObjectUtil.checkPositive(i, str);
    }

    private static void verifyStreamOrConnectionId(int i, String str) {
        ObjectUtil.checkPositiveOrZero(i, str);
    }

    private static void verifyWeight(short s) {
        if (s < 1 || s > 256) {
            throw new IllegalArgumentException("Invalid weight: " + s);
        }
    }

    private static void verifyErrorCode(long j) {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("Invalid errorCode: " + j);
        }
    }

    private static void verifyWindowSizeIncrement(int i) {
        ObjectUtil.checkPositiveOrZero(i, "windowSizeIncrement");
    }
}
