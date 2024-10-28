package io.netty.handler.codec.http2;

import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.internal.ObjectUtil;

public class Http2FrameCodecBuilder extends AbstractHttp2ConnectionHandlerBuilder<Http2FrameCodec, Http2FrameCodecBuilder> {
    private Http2FrameWriter frameWriter;

    protected Http2FrameCodecBuilder() {
    }

    Http2FrameCodecBuilder(boolean z) {
        server(z);
        gracefulShutdownTimeoutMillis(0);
    }

    public static Http2FrameCodecBuilder forClient() {
        return new Http2FrameCodecBuilder(false);
    }

    public static Http2FrameCodecBuilder forServer() {
        return new Http2FrameCodecBuilder(true);
    }

    /* access modifiers changed from: package-private */
    public Http2FrameCodecBuilder frameWriter(Http2FrameWriter http2FrameWriter) {
        this.frameWriter = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "frameWriter");
        return this;
    }

    public Http2Settings initialSettings() {
        return super.initialSettings();
    }

    public Http2FrameCodecBuilder initialSettings(Http2Settings http2Settings) {
        return (Http2FrameCodecBuilder) super.initialSettings(http2Settings);
    }

    public long gracefulShutdownTimeoutMillis() {
        return super.gracefulShutdownTimeoutMillis();
    }

    public Http2FrameCodecBuilder gracefulShutdownTimeoutMillis(long j) {
        return (Http2FrameCodecBuilder) super.gracefulShutdownTimeoutMillis(j);
    }

    public boolean isServer() {
        return super.isServer();
    }

    public int maxReservedStreams() {
        return super.maxReservedStreams();
    }

    public Http2FrameCodecBuilder maxReservedStreams(int i) {
        return (Http2FrameCodecBuilder) super.maxReservedStreams(i);
    }

    public boolean isValidateHeaders() {
        return super.isValidateHeaders();
    }

    public Http2FrameCodecBuilder validateHeaders(boolean z) {
        return (Http2FrameCodecBuilder) super.validateHeaders(z);
    }

    public Http2FrameLogger frameLogger() {
        return super.frameLogger();
    }

    public Http2FrameCodecBuilder frameLogger(Http2FrameLogger http2FrameLogger) {
        return (Http2FrameCodecBuilder) super.frameLogger(http2FrameLogger);
    }

    public boolean encoderEnforceMaxConcurrentStreams() {
        return super.encoderEnforceMaxConcurrentStreams();
    }

    public Http2FrameCodecBuilder encoderEnforceMaxConcurrentStreams(boolean z) {
        return (Http2FrameCodecBuilder) super.encoderEnforceMaxConcurrentStreams(z);
    }

    public int encoderEnforceMaxQueuedControlFrames() {
        return super.encoderEnforceMaxQueuedControlFrames();
    }

    public Http2FrameCodecBuilder encoderEnforceMaxQueuedControlFrames(int i) {
        return (Http2FrameCodecBuilder) super.encoderEnforceMaxQueuedControlFrames(i);
    }

    public Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
        return super.headerSensitivityDetector();
    }

    public Http2FrameCodecBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        return (Http2FrameCodecBuilder) super.headerSensitivityDetector(sensitivityDetector);
    }

    public Http2FrameCodecBuilder encoderIgnoreMaxHeaderListSize(boolean z) {
        return (Http2FrameCodecBuilder) super.encoderIgnoreMaxHeaderListSize(z);
    }

    @Deprecated
    public Http2FrameCodecBuilder initialHuffmanDecodeCapacity(int i) {
        return (Http2FrameCodecBuilder) super.initialHuffmanDecodeCapacity(i);
    }

    public Http2FrameCodecBuilder autoAckSettingsFrame(boolean z) {
        return (Http2FrameCodecBuilder) super.autoAckSettingsFrame(z);
    }

    public Http2FrameCodecBuilder autoAckPingFrame(boolean z) {
        return (Http2FrameCodecBuilder) super.autoAckPingFrame(z);
    }

    public Http2FrameCodecBuilder decoupleCloseAndGoAway(boolean z) {
        return (Http2FrameCodecBuilder) super.decoupleCloseAndGoAway(z);
    }

    public Http2FrameCodecBuilder flushPreface(boolean z) {
        return (Http2FrameCodecBuilder) super.flushPreface(z);
    }

    public int decoderEnforceMaxConsecutiveEmptyDataFrames() {
        return super.decoderEnforceMaxConsecutiveEmptyDataFrames();
    }

    public Http2FrameCodecBuilder decoderEnforceMaxConsecutiveEmptyDataFrames(int i) {
        return (Http2FrameCodecBuilder) super.decoderEnforceMaxConsecutiveEmptyDataFrames(i);
    }

    public Http2FrameCodecBuilder decoderEnforceMaxRstFramesPerWindow(int i, int i2) {
        return (Http2FrameCodecBuilder) super.decoderEnforceMaxRstFramesPerWindow(i, i2);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [io.netty.handler.codec.http2.Http2EmptyDataFrameConnectionDecoder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.netty.handler.codec.http2.Http2FrameCodec build() {
        /*
            r10 = this;
            io.netty.handler.codec.http2.Http2FrameWriter r0 = r10.frameWriter
            if (r0 == 0) goto L_0x0092
            io.netty.handler.codec.http2.DefaultHttp2Connection r2 = new io.netty.handler.codec.http2.DefaultHttp2Connection
            boolean r1 = r10.isServer()
            int r3 = r10.maxReservedStreams()
            r2.<init>(r1, r3)
            io.netty.handler.codec.http2.Http2Settings r1 = r10.initialSettings()
            java.lang.Long r1 = r1.maxHeaderListSize()
            io.netty.handler.codec.http2.DefaultHttp2FrameReader r3 = new io.netty.handler.codec.http2.DefaultHttp2FrameReader
            if (r1 != 0) goto L_0x0027
            io.netty.handler.codec.http2.DefaultHttp2HeadersDecoder r1 = new io.netty.handler.codec.http2.DefaultHttp2HeadersDecoder
            boolean r4 = r10.isValidateHeaders()
            r1.<init>(r4)
            goto L_0x0035
        L_0x0027:
            io.netty.handler.codec.http2.DefaultHttp2HeadersDecoder r4 = new io.netty.handler.codec.http2.DefaultHttp2HeadersDecoder
            boolean r5 = r10.isValidateHeaders()
            long r6 = r1.longValue()
            r4.<init>((boolean) r5, (long) r6)
            r1 = r4
        L_0x0035:
            r3.<init>((io.netty.handler.codec.http2.Http2HeadersDecoder) r1)
            io.netty.handler.codec.http2.Http2FrameLogger r1 = r10.frameLogger()
            if (r1 == 0) goto L_0x0053
            io.netty.handler.codec.http2.Http2OutboundFrameLogger r1 = new io.netty.handler.codec.http2.Http2OutboundFrameLogger
            io.netty.handler.codec.http2.Http2FrameLogger r4 = r10.frameLogger()
            r1.<init>(r0, r4)
            io.netty.handler.codec.http2.Http2InboundFrameLogger r0 = new io.netty.handler.codec.http2.Http2InboundFrameLogger
            io.netty.handler.codec.http2.Http2FrameLogger r4 = r10.frameLogger()
            r0.<init>(r3, r4)
            r4 = r0
            r0 = r1
            goto L_0x0054
        L_0x0053:
            r4 = r3
        L_0x0054:
            io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder r1 = new io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder
            r1.<init>(r2, r0)
            boolean r0 = r10.encoderEnforceMaxConcurrentStreams()
            if (r0 == 0) goto L_0x0065
            io.netty.handler.codec.http2.StreamBufferingEncoder r0 = new io.netty.handler.codec.http2.StreamBufferingEncoder
            r0.<init>(r1)
            goto L_0x0066
        L_0x0065:
            r0 = r1
        L_0x0066:
            io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder r9 = new io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder
            io.netty.handler.codec.http2.Http2PromisedRequestVerifier r5 = r10.promisedRequestVerifier()
            boolean r6 = r10.isAutoAckSettingsFrame()
            boolean r7 = r10.isAutoAckPingFrame()
            boolean r8 = r10.isValidateHeaders()
            r1 = r9
            r3 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            int r1 = r10.decoderEnforceMaxConsecutiveEmptyDataFrames()
            if (r1 <= 0) goto L_0x0089
            io.netty.handler.codec.http2.Http2EmptyDataFrameConnectionDecoder r2 = new io.netty.handler.codec.http2.Http2EmptyDataFrameConnectionDecoder
            r2.<init>(r9, r1)
            r9 = r2
        L_0x0089:
            io.netty.handler.codec.http2.Http2Settings r1 = r10.initialSettings()
            io.netty.handler.codec.http2.Http2FrameCodec r0 = r10.build((io.netty.handler.codec.http2.Http2ConnectionDecoder) r9, (io.netty.handler.codec.http2.Http2ConnectionEncoder) r0, (io.netty.handler.codec.http2.Http2Settings) r1)
            return r0
        L_0x0092:
            io.netty.handler.codec.http2.Http2ConnectionHandler r0 = super.build()
            io.netty.handler.codec.http2.Http2FrameCodec r0 = (io.netty.handler.codec.http2.Http2FrameCodec) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.Http2FrameCodecBuilder.build():io.netty.handler.codec.http2.Http2FrameCodec");
    }

    /* access modifiers changed from: protected */
    public Http2FrameCodec build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) {
        Http2FrameCodec http2FrameCodec = new Http2FrameCodec(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings, decoupleCloseAndGoAway(), flushPreface());
        http2FrameCodec.gracefulShutdownTimeoutMillis(gracefulShutdownTimeoutMillis());
        return http2FrameCodec;
    }
}
