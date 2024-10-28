package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2FrameReader;
import io.netty.handler.codec.http2.Http2HeadersDecoder;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.List;

public class DefaultHttp2ConnectionDecoder implements Http2ConnectionDecoder {
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultHttp2ConnectionDecoder.class);
    /* access modifiers changed from: private */
    public final boolean autoAckPing;
    /* access modifiers changed from: private */
    public final Http2Connection connection;
    /* access modifiers changed from: private */
    public final Http2Connection.PropertyKey contentLengthKey;
    /* access modifiers changed from: private */
    public final Http2ConnectionEncoder encoder;
    /* access modifiers changed from: private */
    public final Http2FrameReader frameReader;
    /* access modifiers changed from: private */
    public Http2FrameListener internalFrameListener;
    /* access modifiers changed from: private */
    public Http2LifecycleManager lifecycleManager;
    /* access modifiers changed from: private */
    public Http2FrameListener listener;
    /* access modifiers changed from: private */
    public final Http2PromisedRequestVerifier requestVerifier;
    /* access modifiers changed from: private */
    public final Http2SettingsReceivedConsumer settingsReceivedConsumer;
    /* access modifiers changed from: private */
    public final boolean validateHeaders;

    public DefaultHttp2ConnectionDecoder(Http2Connection http2Connection, Http2ConnectionEncoder http2ConnectionEncoder, Http2FrameReader http2FrameReader) {
        this(http2Connection, http2ConnectionEncoder, http2FrameReader, Http2PromisedRequestVerifier.ALWAYS_VERIFY);
    }

    public DefaultHttp2ConnectionDecoder(Http2Connection http2Connection, Http2ConnectionEncoder http2ConnectionEncoder, Http2FrameReader http2FrameReader, Http2PromisedRequestVerifier http2PromisedRequestVerifier) {
        this(http2Connection, http2ConnectionEncoder, http2FrameReader, http2PromisedRequestVerifier, true);
    }

    public DefaultHttp2ConnectionDecoder(Http2Connection http2Connection, Http2ConnectionEncoder http2ConnectionEncoder, Http2FrameReader http2FrameReader, Http2PromisedRequestVerifier http2PromisedRequestVerifier, boolean z) {
        this(http2Connection, http2ConnectionEncoder, http2FrameReader, http2PromisedRequestVerifier, z, true);
    }

    @Deprecated
    public DefaultHttp2ConnectionDecoder(Http2Connection http2Connection, Http2ConnectionEncoder http2ConnectionEncoder, Http2FrameReader http2FrameReader, Http2PromisedRequestVerifier http2PromisedRequestVerifier, boolean z, boolean z2) {
        this(http2Connection, http2ConnectionEncoder, http2FrameReader, http2PromisedRequestVerifier, z, true, true);
    }

    public DefaultHttp2ConnectionDecoder(Http2Connection http2Connection, Http2ConnectionEncoder http2ConnectionEncoder, Http2FrameReader http2FrameReader, Http2PromisedRequestVerifier http2PromisedRequestVerifier, boolean z, boolean z2, boolean z3) {
        this.internalFrameListener = new PrefaceFrameListener(this, (AnonymousClass1) null);
        this.validateHeaders = z3;
        this.autoAckPing = z2;
        if (z) {
            this.settingsReceivedConsumer = null;
        } else if (http2ConnectionEncoder instanceof Http2SettingsReceivedConsumer) {
            this.settingsReceivedConsumer = (Http2SettingsReceivedConsumer) http2ConnectionEncoder;
        } else {
            throw new IllegalArgumentException("disabling autoAckSettings requires the encoder to be a " + Http2SettingsReceivedConsumer.class);
        }
        Http2Connection http2Connection2 = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        this.connection = http2Connection2;
        this.contentLengthKey = http2Connection2.newKey();
        this.frameReader = (Http2FrameReader) ObjectUtil.checkNotNull(http2FrameReader, "frameReader");
        this.encoder = (Http2ConnectionEncoder) ObjectUtil.checkNotNull(http2ConnectionEncoder, "encoder");
        this.requestVerifier = (Http2PromisedRequestVerifier) ObjectUtil.checkNotNull(http2PromisedRequestVerifier, "requestVerifier");
        if (http2Connection.local().flowController() == null) {
            http2Connection.local().flowController(new DefaultHttp2LocalFlowController(http2Connection));
        }
        http2Connection.local().flowController().frameWriter(http2ConnectionEncoder.frameWriter());
    }

    public void lifecycleManager(Http2LifecycleManager http2LifecycleManager) {
        this.lifecycleManager = (Http2LifecycleManager) ObjectUtil.checkNotNull(http2LifecycleManager, "lifecycleManager");
    }

    public Http2Connection connection() {
        return this.connection;
    }

    public final Http2LocalFlowController flowController() {
        return this.connection.local().flowController();
    }

    public void frameListener(Http2FrameListener http2FrameListener) {
        this.listener = (Http2FrameListener) ObjectUtil.checkNotNull(http2FrameListener, "listener");
    }

    public Http2FrameListener frameListener() {
        return this.listener;
    }

    public boolean prefaceReceived() {
        return FrameReadListener.class == this.internalFrameListener.getClass();
    }

    public void decodeFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Http2Exception {
        this.frameReader.readFrame(channelHandlerContext, byteBuf, this.internalFrameListener);
    }

    public Http2Settings localSettings() {
        Http2Settings http2Settings = new Http2Settings();
        Http2FrameReader.Configuration configuration = this.frameReader.configuration();
        Http2HeadersDecoder.Configuration headersConfiguration = configuration.headersConfiguration();
        Http2FrameSizePolicy frameSizePolicy = configuration.frameSizePolicy();
        http2Settings.initialWindowSize(flowController().initialWindowSize());
        http2Settings.maxConcurrentStreams((long) this.connection.remote().maxActiveStreams());
        http2Settings.headerTableSize(headersConfiguration.maxHeaderTableSize());
        http2Settings.maxFrameSize(frameSizePolicy.maxFrameSize());
        http2Settings.maxHeaderListSize(headersConfiguration.maxHeaderListSize());
        if (!this.connection.isServer()) {
            http2Settings.pushEnabled(this.connection.local().allowPushTo());
        }
        return http2Settings;
    }

    public void close() {
        this.frameReader.close();
    }

    /* access modifiers changed from: protected */
    public long calculateMaxHeaderListSizeGoAway(long j) {
        return Http2CodecUtil.calculateMaxHeaderListSizeGoAway(j);
    }

    /* access modifiers changed from: private */
    public int unconsumedBytes(Http2Stream http2Stream) {
        return flowController().unconsumedBytes(http2Stream);
    }

    /* access modifiers changed from: package-private */
    public void onGoAwayRead0(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) throws Http2Exception {
        this.listener.onGoAwayRead(channelHandlerContext, i, j, byteBuf);
        this.connection.goAwayReceived(i, j, byteBuf);
    }

    /* access modifiers changed from: package-private */
    public void onUnknownFrame0(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) throws Http2Exception {
        this.listener.onUnknownFrame(channelHandlerContext, b, i, http2Flags, byteBuf);
    }

    /* access modifiers changed from: private */
    public void verifyContentLength(Http2Stream http2Stream, int i, boolean z) throws Http2Exception {
        ContentLength contentLength = (ContentLength) http2Stream.getProperty(this.contentLengthKey);
        if (contentLength != null) {
            try {
                contentLength.increaseReceivedBytes(this.connection.isServer(), http2Stream.id(), i, z);
            } finally {
                if (z) {
                    http2Stream.removeProperty(this.contentLengthKey);
                }
            }
        }
    }

    private final class FrameReadListener implements Http2FrameListener {
        private FrameReadListener() {
        }

        /* synthetic */ FrameReadListener(DefaultHttp2ConnectionDecoder defaultHttp2ConnectionDecoder, AnonymousClass1 r2) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:46:0x010d, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x010e, code lost:
            r13.receiveFlowControlledFrame(r12, r9, r10, r11);
            r13.consumeBytes(r12, r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0114, code lost:
            throw r0;
         */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x010d A[ExcHandler: Http2Exception (r0v4 'e' io.netty.handler.codec.http2.Http2Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x002f] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int onDataRead(io.netty.channel.ChannelHandlerContext r18, int r19, io.netty.buffer.ByteBuf r20, int r21, boolean r22) throws io.netty.handler.codec.http2.Http2Exception {
            /*
                r17 = this;
                r7 = r17
                r8 = r19
                r9 = r20
                r10 = r21
                r11 = r22
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r0 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2Connection r0 = r0.connection
                io.netty.handler.codec.http2.Http2Stream r12 = r0.stream(r8)
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r0 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2LocalFlowController r13 = r0.flowController()
                int r0 = r20.readableBytes()
                int r14 = r0 + r10
                r6 = 1
                java.lang.String r16 = "DATA"
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r12
                r5 = r22
                r15 = 1
                r6 = r16
                boolean r1 = r1.shouldIgnoreHeadersOrDataFrame(r2, r3, r4, r5, r6)     // Catch:{ Http2Exception -> 0x010d, all -> 0x00f7 }
                if (r1 == 0) goto L_0x0041
                r13.receiveFlowControlledFrame(r12, r9, r10, r11)
                r13.consumeBytes(r12, r14)
                java.lang.String r0 = "DATA"
                r7.verifyStreamMayHaveExisted(r8, r11, r0)
                return r14
            L_0x0041:
                int[] r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State
                io.netty.handler.codec.http2.Http2Stream$State r2 = r12.state()
                int r2 = r2.ordinal()
                r1 = r1[r2]
                if (r1 == r15) goto L_0x0098
                r2 = 2
                if (r1 == r2) goto L_0x0098
                r3 = 3
                java.lang.String r4 = "Stream %d in unexpected state: %s"
                if (r1 == r3) goto L_0x0079
                r3 = 4
                if (r1 == r3) goto L_0x0079
                int r1 = r12.id()
                io.netty.handler.codec.http2.Http2Error r3 = io.netty.handler.codec.http2.Http2Error.PROTOCOL_ERROR
                int r5 = r12.id()
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                io.netty.handler.codec.http2.Http2Stream$State r6 = r12.state()
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r16 = 0
                r2[r16] = r5
                r2[r15] = r6
                io.netty.handler.codec.http2.Http2Exception r1 = io.netty.handler.codec.http2.Http2Exception.streamError(r1, r3, r4, r2)
                goto L_0x0099
            L_0x0079:
                int r1 = r12.id()
                io.netty.handler.codec.http2.Http2Error r3 = io.netty.handler.codec.http2.Http2Error.STREAM_CLOSED
                int r5 = r12.id()
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                io.netty.handler.codec.http2.Http2Stream$State r6 = r12.state()
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r16 = 0
                r2[r16] = r5
                r2[r15] = r6
                io.netty.handler.codec.http2.Http2Exception r1 = io.netty.handler.codec.http2.Http2Exception.streamError(r1, r3, r4, r2)
                goto L_0x0099
            L_0x0098:
                r1 = 0
            L_0x0099:
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r2 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                int r2 = r2.unconsumedBytes(r12)
                r13.receiveFlowControlledFrame(r12, r9, r10, r11)     // Catch:{ Http2Exception -> 0x00e9, RuntimeException -> 0x00df }
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r3 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this     // Catch:{ Http2Exception -> 0x00e9, RuntimeException -> 0x00df }
                int r15 = r3.unconsumedBytes(r12)     // Catch:{ Http2Exception -> 0x00e9, RuntimeException -> 0x00df }
                if (r1 != 0) goto L_0x00dc
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                r1.verifyContentLength(r12, r0, r11)     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r0 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                io.netty.handler.codec.http2.Http2FrameListener r1 = r0.listener     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                r2 = r18
                r3 = r19
                r4 = r20
                r5 = r21
                r6 = r22
                int r14 = r1.onDataRead(r2, r3, r4, r5, r6)     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                if (r11 == 0) goto L_0x00d2
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r0 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                io.netty.handler.codec.http2.Http2LifecycleManager r0 = r0.lifecycleManager     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                io.netty.channel.ChannelFuture r1 = r18.newSucceededFuture()     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
                r0.closeStreamRemote(r12, r1)     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
            L_0x00d2:
                r13.consumeBytes(r12, r14)
                return r14
            L_0x00d6:
                r0 = move-exception
                r2 = r15
                goto L_0x00e0
            L_0x00d9:
                r0 = move-exception
                r2 = r15
                goto L_0x00ea
            L_0x00dc:
                throw r1     // Catch:{ Http2Exception -> 0x00d9, RuntimeException -> 0x00d6 }
            L_0x00dd:
                r0 = move-exception
                goto L_0x00f3
            L_0x00df:
                r0 = move-exception
            L_0x00e0:
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this     // Catch:{ all -> 0x00dd }
                int r1 = r1.unconsumedBytes(r12)     // Catch:{ all -> 0x00dd }
                int r2 = r2 - r1
                int r14 = r14 - r2
                throw r0     // Catch:{ all -> 0x00dd }
            L_0x00e9:
                r0 = move-exception
            L_0x00ea:
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this     // Catch:{ all -> 0x00dd }
                int r1 = r1.unconsumedBytes(r12)     // Catch:{ all -> 0x00dd }
                int r2 = r2 - r1
                int r14 = r14 - r2
                throw r0     // Catch:{ all -> 0x00dd }
            L_0x00f3:
                r13.consumeBytes(r12, r14)
                throw r0
            L_0x00f7:
                r0 = move-exception
                goto L_0x00fb
            L_0x00f9:
                r0 = move-exception
                r15 = 1
            L_0x00fb:
                io.netty.handler.codec.http2.Http2Error r1 = io.netty.handler.codec.http2.Http2Error.INTERNAL_ERROR
                java.lang.Integer r2 = java.lang.Integer.valueOf(r19)
                java.lang.Object[] r3 = new java.lang.Object[r15]
                r4 = 0
                r3[r4] = r2
                java.lang.String r2 = "Unhandled error on data stream id %d"
                io.netty.handler.codec.http2.Http2Exception r0 = io.netty.handler.codec.http2.Http2Exception.connectionError(r1, r0, r2, r3)
                throw r0
            L_0x010d:
                r0 = move-exception
                r13.receiveFlowControlledFrame(r12, r9, r10, r11)
                r13.consumeBytes(r12, r14)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.FrameReadListener.onDataRead(io.netty.channel.ChannelHandlerContext, int, io.netty.buffer.ByteBuf, int, boolean):int");
        }

        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) throws Http2Exception {
            onHeadersRead(channelHandlerContext, i, http2Headers, 0, 16, false, i2, z);
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x005c A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x005d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onHeadersRead(io.netty.channel.ChannelHandlerContext r18, int r19, io.netty.handler.codec.http2.Http2Headers r20, int r21, short r22, boolean r23, int r24, boolean r25) throws io.netty.handler.codec.http2.Http2Exception {
            /*
                r17 = this;
                r7 = r17
                r0 = r19
                r11 = r20
                r15 = r25
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2Connection r1 = r1.connection
                io.netty.handler.codec.http2.Http2Stream r1 = r1.stream(r0)
                r8 = 1
                r9 = 0
                if (r1 != 0) goto L_0x003e
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r2 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2Connection r2 = r2.connection
                boolean r2 = r2.streamMayHaveExisted(r0)
                if (r2 != 0) goto L_0x003e
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2Connection r1 = r1.connection
                io.netty.handler.codec.http2.Http2Connection$Endpoint r1 = r1.remote()
                io.netty.handler.codec.http2.Http2Stream r1 = r1.createStream(r0, r15)
                io.netty.handler.codec.http2.Http2Stream$State r2 = r1.state()
                io.netty.handler.codec.http2.Http2Stream$State r3 = io.netty.handler.codec.http2.Http2Stream.State.HALF_CLOSED_REMOTE
                if (r2 != r3) goto L_0x003a
                r2 = 1
                goto L_0x003b
            L_0x003a:
                r2 = 0
            L_0x003b:
                r14 = r1
                r10 = r2
                goto L_0x004a
            L_0x003e:
                if (r1 == 0) goto L_0x0048
                boolean r2 = r1.isHeadersReceived()
                r14 = r1
                r12 = r2
                r10 = 0
                goto L_0x004b
            L_0x0048:
                r14 = r1
                r10 = 0
            L_0x004a:
                r12 = 0
            L_0x004b:
                java.lang.String r6 = "HEADERS"
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r14
                r5 = r25
                boolean r1 = r1.shouldIgnoreHeadersOrDataFrame(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L_0x005d
                return
            L_0x005d:
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2Connection r1 = r1.connection
                boolean r1 = r1.isServer()
                if (r1 != 0) goto L_0x0077
                java.lang.CharSequence r1 = r20.status()
                io.netty.handler.codec.http.HttpStatusClass r1 = io.netty.handler.codec.http.HttpStatusClass.valueOf((java.lang.CharSequence) r1)
                io.netty.handler.codec.http.HttpStatusClass r2 = io.netty.handler.codec.http.HttpStatusClass.INFORMATIONAL
                if (r1 != r2) goto L_0x0077
                r1 = 1
                goto L_0x0078
            L_0x0077:
                r1 = 0
            L_0x0078:
                r2 = 3
                r3 = 2
                if (r1 != 0) goto L_0x007e
                if (r15 != 0) goto L_0x0084
            L_0x007e:
                boolean r4 = r14.isHeadersReceived()
                if (r4 != 0) goto L_0x01bd
            L_0x0084:
                boolean r4 = r14.isTrailersReceived()
                if (r4 != 0) goto L_0x01bd
                int[] r4 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State
                io.netty.handler.codec.http2.Http2Stream$State r5 = r14.state()
                int r5 = r5.ordinal()
                r4 = r4[r5]
                if (r4 == r8) goto L_0x00fe
                if (r4 == r3) goto L_0x00fe
                java.lang.String r5 = "Stream %d in unexpected state: %s"
                if (r4 == r2) goto L_0x00de
                r2 = 4
                if (r4 == r2) goto L_0x00c1
                r2 = 5
                if (r4 != r2) goto L_0x00a8
                r14.open(r15)
                goto L_0x00fe
            L_0x00a8:
                io.netty.handler.codec.http2.Http2Error r0 = io.netty.handler.codec.http2.Http2Error.PROTOCOL_ERROR
                int r1 = r14.id()
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                io.netty.handler.codec.http2.Http2Stream$State r2 = r14.state()
                java.lang.Object[] r3 = new java.lang.Object[r3]
                r3[r9] = r1
                r3[r8] = r2
                io.netty.handler.codec.http2.Http2Exception r0 = io.netty.handler.codec.http2.Http2Exception.connectionError(r0, r5, r3)
                throw r0
            L_0x00c1:
                int r0 = r14.id()
                io.netty.handler.codec.http2.Http2Error r1 = io.netty.handler.codec.http2.Http2Error.STREAM_CLOSED
                int r2 = r14.id()
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                io.netty.handler.codec.http2.Http2Stream$State r4 = r14.state()
                java.lang.Object[] r3 = new java.lang.Object[r3]
                r3[r9] = r2
                r3[r8] = r4
                io.netty.handler.codec.http2.Http2Exception r0 = io.netty.handler.codec.http2.Http2Exception.streamError(r0, r1, r5, r3)
                throw r0
            L_0x00de:
                if (r10 == 0) goto L_0x00e1
                goto L_0x00fe
            L_0x00e1:
                int r0 = r14.id()
                io.netty.handler.codec.http2.Http2Error r1 = io.netty.handler.codec.http2.Http2Error.STREAM_CLOSED
                int r2 = r14.id()
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                io.netty.handler.codec.http2.Http2Stream$State r4 = r14.state()
                java.lang.Object[] r3 = new java.lang.Object[r3]
                r3[r9] = r2
                r3[r8] = r4
                io.netty.handler.codec.http2.Http2Exception r0 = io.netty.handler.codec.http2.Http2Exception.streamError(r0, r1, r5, r3)
                throw r0
            L_0x00fe:
                if (r12 != 0) goto L_0x013c
                io.netty.util.AsciiString r2 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH
                java.util.List r2 = r11.getAll(r2)
                if (r2 == 0) goto L_0x0178
                boolean r3 = r2.isEmpty()
                if (r3 != 0) goto L_0x0178
                long r2 = io.netty.handler.codec.http.HttpUtil.normalizeAndGetContentLength(r2, r9, r8)     // Catch:{ IllegalArgumentException -> 0x012c }
                r4 = -1
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 == 0) goto L_0x0178
                io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH     // Catch:{ IllegalArgumentException -> 0x012c }
                r11.setLong(r4, r2)     // Catch:{ IllegalArgumentException -> 0x012c }
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r4 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this     // Catch:{ IllegalArgumentException -> 0x012c }
                io.netty.handler.codec.http2.Http2Connection$PropertyKey r4 = r4.contentLengthKey     // Catch:{ IllegalArgumentException -> 0x012c }
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder$ContentLength r5 = new io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder$ContentLength     // Catch:{ IllegalArgumentException -> 0x012c }
                r5.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x012c }
                r14.setProperty(r4, r5)     // Catch:{ IllegalArgumentException -> 0x012c }
                goto L_0x0178
            L_0x012c:
                r0 = move-exception
                int r1 = r14.id()
                io.netty.handler.codec.http2.Http2Error r2 = io.netty.handler.codec.http2.Http2Error.PROTOCOL_ERROR
                java.lang.String r3 = "Multiple content-length headers received"
                java.lang.Object[] r4 = new java.lang.Object[r9]
                io.netty.handler.codec.http2.Http2Exception r0 = io.netty.handler.codec.http2.Http2Exception.streamError(r1, r2, r0, r3, r4)
                throw r0
            L_0x013c:
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r2 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                boolean r2 = r2.validateHeaders
                if (r2 == 0) goto L_0x0178
                boolean r2 = r20.isEmpty()
                if (r2 != 0) goto L_0x0178
                java.util.Iterator r2 = r20.iterator()
            L_0x014e:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0178
                java.lang.Object r3 = r2.next()
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r3 = r3.getKey()
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                boolean r4 = io.netty.handler.codec.http2.Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(r3)
                if (r4 != 0) goto L_0x0167
                goto L_0x014e
            L_0x0167:
                int r0 = r14.id()
                io.netty.handler.codec.http2.Http2Error r1 = io.netty.handler.codec.http2.Http2Error.PROTOCOL_ERROR
                java.lang.Object[] r2 = new java.lang.Object[r8]
                r2[r9] = r3
                java.lang.String r3 = "Found invalid Pseudo-Header in trailers: %s"
                io.netty.handler.codec.http2.Http2Exception r0 = io.netty.handler.codec.http2.Http2Exception.streamError(r0, r1, r3, r2)
                throw r0
            L_0x0178:
                r14.headersReceived(r1)
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                r1.verifyContentLength(r14, r9, r15)
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2ConnectionEncoder r1 = r1.encoder
                io.netty.handler.codec.http2.Http2RemoteFlowController r1 = r1.flowController()
                r2 = r21
                r3 = r22
                r4 = r23
                r1.updateDependencyTree(r0, r2, r3, r4)
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r1 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2FrameListener r8 = r1.listener
                r9 = r18
                r10 = r19
                r11 = r20
                r12 = r21
                r13 = r22
                r1 = r14
                r14 = r23
                r15 = r24
                r16 = r25
                r8.onHeadersRead(r9, r10, r11, r12, r13, r14, r15, r16)
                if (r25 == 0) goto L_0x01bc
                io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r0 = io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.this
                io.netty.handler.codec.http2.Http2LifecycleManager r0 = r0.lifecycleManager
                io.netty.channel.ChannelFuture r2 = r18.newSucceededFuture()
                r0.closeStreamRemote(r1, r2)
            L_0x01bc:
                return
            L_0x01bd:
                r1 = r14
                io.netty.handler.codec.http2.Http2Error r4 = io.netty.handler.codec.http2.Http2Error.PROTOCOL_ERROR
                java.lang.Integer r5 = java.lang.Integer.valueOf(r19)
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r25)
                io.netty.handler.codec.http2.Http2Stream$State r1 = r1.state()
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r2[r9] = r5
                r2[r8] = r6
                r2[r3] = r1
                java.lang.String r1 = "Stream %d received too many headers EOS: %s state: %s"
                io.netty.handler.codec.http2.Http2Exception r0 = io.netty.handler.codec.http2.Http2Exception.streamError(r0, r4, r1, r2)
                goto L_0x01dc
            L_0x01db:
                throw r0
            L_0x01dc:
                goto L_0x01db
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.FrameReadListener.onHeadersRead(io.netty.channel.ChannelHandlerContext, int, io.netty.handler.codec.http2.Http2Headers, int, short, boolean, int, boolean):void");
        }

        public void onPriorityRead(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) throws Http2Exception {
            DefaultHttp2ConnectionDecoder.this.encoder.flowController().updateDependencyTree(i, i2, s, z);
            DefaultHttp2ConnectionDecoder.this.listener.onPriorityRead(channelHandlerContext, i, i2, s, z);
        }

        public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) throws Http2Exception {
            Http2Stream stream = DefaultHttp2ConnectionDecoder.this.connection.stream(i);
            if (stream == null) {
                verifyStreamMayHaveExisted(i, false, "RST_STREAM");
                return;
            }
            int i2 = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[stream.state().ordinal()];
            if (i2 == 4) {
                return;
            }
            if (i2 != 6) {
                DefaultHttp2ConnectionDecoder.this.listener.onRstStreamRead(channelHandlerContext, i, j);
                DefaultHttp2ConnectionDecoder.this.lifecycleManager.closeStream(stream, channelHandlerContext.newSucceededFuture());
                return;
            }
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "RST_STREAM received for IDLE stream %d", Integer.valueOf(i));
        }

        public void onSettingsAckRead(ChannelHandlerContext channelHandlerContext) throws Http2Exception {
            Http2Settings pollSentSettings = DefaultHttp2ConnectionDecoder.this.encoder.pollSentSettings();
            if (pollSentSettings != null) {
                applyLocalSettings(pollSentSettings);
            }
            DefaultHttp2ConnectionDecoder.this.listener.onSettingsAckRead(channelHandlerContext);
        }

        private void applyLocalSettings(Http2Settings http2Settings) throws Http2Exception {
            Boolean pushEnabled = http2Settings.pushEnabled();
            Http2FrameReader.Configuration configuration = DefaultHttp2ConnectionDecoder.this.frameReader.configuration();
            Http2HeadersDecoder.Configuration headersConfiguration = configuration.headersConfiguration();
            Http2FrameSizePolicy frameSizePolicy = configuration.frameSizePolicy();
            if (pushEnabled != null) {
                if (!DefaultHttp2ConnectionDecoder.this.connection.isServer()) {
                    DefaultHttp2ConnectionDecoder.this.connection.local().allowPushTo(pushEnabled.booleanValue());
                } else {
                    throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Server sending SETTINGS frame with ENABLE_PUSH specified", new Object[0]);
                }
            }
            Long maxConcurrentStreams = http2Settings.maxConcurrentStreams();
            if (maxConcurrentStreams != null) {
                DefaultHttp2ConnectionDecoder.this.connection.remote().maxActiveStreams((int) Math.min(maxConcurrentStreams.longValue(), 2147483647L));
            }
            Long headerTableSize = http2Settings.headerTableSize();
            if (headerTableSize != null) {
                headersConfiguration.maxHeaderTableSize(headerTableSize.longValue());
            }
            Long maxHeaderListSize = http2Settings.maxHeaderListSize();
            if (maxHeaderListSize != null) {
                headersConfiguration.maxHeaderListSize(maxHeaderListSize.longValue(), DefaultHttp2ConnectionDecoder.this.calculateMaxHeaderListSizeGoAway(maxHeaderListSize.longValue()));
            }
            Integer maxFrameSize = http2Settings.maxFrameSize();
            if (maxFrameSize != null) {
                frameSizePolicy.maxFrameSize(maxFrameSize.intValue());
            }
            Integer initialWindowSize = http2Settings.initialWindowSize();
            if (initialWindowSize != null) {
                DefaultHttp2ConnectionDecoder.this.flowController().initialWindowSize(initialWindowSize.intValue());
            }
        }

        public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) throws Http2Exception {
            if (DefaultHttp2ConnectionDecoder.this.settingsReceivedConsumer == null) {
                DefaultHttp2ConnectionDecoder.this.encoder.writeSettingsAck(channelHandlerContext, channelHandlerContext.newPromise());
                DefaultHttp2ConnectionDecoder.this.encoder.remoteSettings(http2Settings);
            } else {
                DefaultHttp2ConnectionDecoder.this.settingsReceivedConsumer.consumeReceivedSettings(http2Settings);
            }
            DefaultHttp2ConnectionDecoder.this.listener.onSettingsRead(channelHandlerContext, http2Settings);
        }

        public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            if (DefaultHttp2ConnectionDecoder.this.autoAckPing) {
                DefaultHttp2ConnectionDecoder.this.encoder.writePing(channelHandlerContext, true, j, channelHandlerContext.newPromise());
            }
            DefaultHttp2ConnectionDecoder.this.listener.onPingRead(channelHandlerContext, j);
        }

        public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            DefaultHttp2ConnectionDecoder.this.listener.onPingAckRead(channelHandlerContext, j);
        }

        public void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) throws Http2Exception {
            if (!DefaultHttp2ConnectionDecoder.this.connection().isServer()) {
                Http2Stream stream = DefaultHttp2ConnectionDecoder.this.connection.stream(i);
                if (!shouldIgnoreHeadersOrDataFrame(channelHandlerContext, i, stream, false, "PUSH_PROMISE")) {
                    int i4 = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[stream.state().ordinal()];
                    if (i4 != 1 && i4 != 2) {
                        throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d in unexpected state for receiving push promise: %s", Integer.valueOf(stream.id()), stream.state());
                    } else if (!DefaultHttp2ConnectionDecoder.this.requestVerifier.isAuthoritative(channelHandlerContext, http2Headers)) {
                        throw Http2Exception.streamError(i2, Http2Error.PROTOCOL_ERROR, "Promised request on stream %d for promised stream %d is not authoritative", Integer.valueOf(i), Integer.valueOf(i2));
                    } else if (!DefaultHttp2ConnectionDecoder.this.requestVerifier.isCacheable(http2Headers)) {
                        throw Http2Exception.streamError(i2, Http2Error.PROTOCOL_ERROR, "Promised request on stream %d for promised stream %d is not known to be cacheable", Integer.valueOf(i), Integer.valueOf(i2));
                    } else if (DefaultHttp2ConnectionDecoder.this.requestVerifier.isSafe(http2Headers)) {
                        DefaultHttp2ConnectionDecoder.this.connection.remote().reservePushStream(i2, stream);
                        DefaultHttp2ConnectionDecoder.this.listener.onPushPromiseRead(channelHandlerContext, i, i2, http2Headers, i3);
                    } else {
                        throw Http2Exception.streamError(i2, Http2Error.PROTOCOL_ERROR, "Promised request on stream %d for promised stream %d is not known to be safe", Integer.valueOf(i), Integer.valueOf(i2));
                    }
                }
            } else {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A client cannot push.", new Object[0]);
            }
        }

        public void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) throws Http2Exception {
            DefaultHttp2ConnectionDecoder.this.onGoAwayRead0(channelHandlerContext, i, j, byteBuf);
        }

        public void onWindowUpdateRead(ChannelHandlerContext channelHandlerContext, int i, int i2) throws Http2Exception {
            Http2Stream stream = DefaultHttp2ConnectionDecoder.this.connection.stream(i);
            if (stream == null || stream.state() == Http2Stream.State.CLOSED || streamCreatedAfterGoAwaySent(i)) {
                verifyStreamMayHaveExisted(i, false, "WINDOW_UPDATE");
                return;
            }
            DefaultHttp2ConnectionDecoder.this.encoder.flowController().incrementWindowSize(stream, i2);
            DefaultHttp2ConnectionDecoder.this.listener.onWindowUpdateRead(channelHandlerContext, i, i2);
        }

        public void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) throws Http2Exception {
            DefaultHttp2ConnectionDecoder.this.onUnknownFrame0(channelHandlerContext, b, i, http2Flags, byteBuf);
        }

        private boolean shouldIgnoreHeadersOrDataFrame(ChannelHandlerContext channelHandlerContext, int i, Http2Stream http2Stream, boolean z, String str) throws Http2Exception {
            String str2;
            if (http2Stream == null) {
                if (streamCreatedAfterGoAwaySent(i)) {
                    DefaultHttp2ConnectionDecoder.logger.info("{} ignoring {} frame for stream {}. Stream sent after GOAWAY sent", channelHandlerContext.channel(), str, Integer.valueOf(i));
                    return true;
                }
                verifyStreamMayHaveExisted(i, z, str);
                throw Http2Exception.streamError(i, Http2Error.STREAM_CLOSED, "Received %s frame for an unknown stream %d", str, Integer.valueOf(i));
            } else if (!http2Stream.isResetSent() && !streamCreatedAfterGoAwaySent(i)) {
                return false;
            } else {
                if (DefaultHttp2ConnectionDecoder.logger.isInfoEnabled()) {
                    InternalLogger access$1300 = DefaultHttp2ConnectionDecoder.logger;
                    Channel channel = channelHandlerContext.channel();
                    if (http2Stream.isResetSent()) {
                        str2 = "RST_STREAM sent.";
                    } else {
                        str2 = "Stream created after GOAWAY sent. Last known stream by peer " + DefaultHttp2ConnectionDecoder.this.connection.remote().lastStreamKnownByPeer();
                    }
                    access$1300.info("{} ignoring {} frame for stream {}", channel, str, str2);
                }
                return true;
            }
        }

        private boolean streamCreatedAfterGoAwaySent(int i) {
            Http2Connection.Endpoint<Http2RemoteFlowController> remote = DefaultHttp2ConnectionDecoder.this.connection.remote();
            return DefaultHttp2ConnectionDecoder.this.connection.goAwaySent() && remote.isValidStreamId(i) && i > remote.lastStreamKnownByPeer();
        }

        private void verifyStreamMayHaveExisted(int i, boolean z, String str) throws Http2Exception {
            if (!DefaultHttp2ConnectionDecoder.this.connection.streamMayHaveExisted(i)) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d does not exist for inbound frame %s, endOfStream = %b", Integer.valueOf(i), str, Boolean.valueOf(z));
            }
        }
    }

    /* renamed from: io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.netty.handler.codec.http2.Http2Stream$State[] r0 = io.netty.handler.codec.http2.Http2Stream.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = r0
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.OPEN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.HALF_CLOSED_LOCAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.HALF_CLOSED_REMOTE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.CLOSED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x003e }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.RESERVED_REMOTE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.IDLE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    private final class PrefaceFrameListener implements Http2FrameListener {
        private PrefaceFrameListener() {
        }

        /* synthetic */ PrefaceFrameListener(DefaultHttp2ConnectionDecoder defaultHttp2ConnectionDecoder, AnonymousClass1 r2) {
            this();
        }

        private void verifyPrefaceReceived() throws Http2Exception {
            if (!DefaultHttp2ConnectionDecoder.this.prefaceReceived()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Received non-SETTINGS as first frame.", new Object[0]);
            }
        }

        public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
            verifyPrefaceReceived();
            return DefaultHttp2ConnectionDecoder.this.internalFrameListener.onDataRead(channelHandlerContext, i, byteBuf, i2, z);
        }

        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onHeadersRead(channelHandlerContext, i, http2Headers, i2, z);
        }

        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onHeadersRead(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2);
        }

        public void onPriorityRead(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPriorityRead(channelHandlerContext, i, i2, s, z);
        }

        public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onRstStreamRead(channelHandlerContext, i, j);
        }

        public void onSettingsAckRead(ChannelHandlerContext channelHandlerContext) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onSettingsAckRead(channelHandlerContext);
        }

        public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) throws Http2Exception {
            if (!DefaultHttp2ConnectionDecoder.this.prefaceReceived()) {
                DefaultHttp2ConnectionDecoder defaultHttp2ConnectionDecoder = DefaultHttp2ConnectionDecoder.this;
                Http2FrameListener unused = defaultHttp2ConnectionDecoder.internalFrameListener = new FrameReadListener(defaultHttp2ConnectionDecoder, (AnonymousClass1) null);
            }
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onSettingsRead(channelHandlerContext, http2Settings);
        }

        public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPingRead(channelHandlerContext, j);
        }

        public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPingAckRead(channelHandlerContext, j);
        }

        public void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPushPromiseRead(channelHandlerContext, i, i2, http2Headers, i3);
        }

        public void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) throws Http2Exception {
            DefaultHttp2ConnectionDecoder.this.onGoAwayRead0(channelHandlerContext, i, j, byteBuf);
        }

        public void onWindowUpdateRead(ChannelHandlerContext channelHandlerContext, int i, int i2) throws Http2Exception {
            verifyPrefaceReceived();
            DefaultHttp2ConnectionDecoder.this.internalFrameListener.onWindowUpdateRead(channelHandlerContext, i, i2);
        }

        public void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) throws Http2Exception {
            DefaultHttp2ConnectionDecoder.this.onUnknownFrame0(channelHandlerContext, b, i, http2Flags, byteBuf);
        }
    }

    private static final class ContentLength {
        private final long expected;
        private long seen;

        ContentLength(long j) {
            this.expected = j;
        }

        /* access modifiers changed from: package-private */
        public void increaseReceivedBytes(boolean z, int i, int i2, boolean z2) throws Http2Exception {
            long j = this.seen + ((long) i2);
            this.seen = j;
            if (j >= 0) {
                long j2 = this.expected;
                if (j > j2) {
                    throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Received amount of data %d does not match content-length header %d", Long.valueOf(this.seen), Long.valueOf(this.expected));
                } else if (!z2) {
                } else {
                    if ((j != 0 || z) && j2 > j) {
                        throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Received amount of data %d does not match content-length header %d", Long.valueOf(this.seen), Long.valueOf(this.expected));
                    }
                }
            } else {
                throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Received amount of data did overflow and so not match content-length header %d", Long.valueOf(this.expected));
            }
        }
    }
}
