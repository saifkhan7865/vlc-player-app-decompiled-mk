package io.netty.handler.codec.http2;

import io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.internal.ObjectUtil;

public abstract class AbstractHttp2ConnectionHandlerBuilder<T extends Http2ConnectionHandler, B extends AbstractHttp2ConnectionHandlerBuilder<T, B>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Http2HeadersEncoder.SensitivityDetector DEFAULT_HEADER_SENSITIVITY_DETECTOR = Http2HeadersEncoder.NEVER_SENSITIVE;
    private static final int DEFAULT_MAX_RST_FRAMES_PER_CONNECTION_FOR_SERVER = 200;
    private boolean autoAckPingFrame = true;
    private boolean autoAckSettingsFrame = true;
    private Http2Connection connection;
    private Http2ConnectionDecoder decoder;
    private boolean decoupleCloseAndGoAway;
    private Http2ConnectionEncoder encoder;
    private Boolean encoderEnforceMaxConcurrentStreams;
    private Boolean encoderIgnoreMaxHeaderListSize;
    private boolean flushPreface = true;
    private Http2FrameListener frameListener;
    private Http2FrameLogger frameLogger;
    private long gracefulShutdownTimeoutMillis = Http2CodecUtil.DEFAULT_GRACEFUL_SHUTDOWN_TIMEOUT_MILLIS;
    private Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector;
    private Http2Settings initialSettings = Http2Settings.defaultSettings();
    private Boolean isServer;
    private int maxConsecutiveEmptyFrames = 2;
    private int maxQueuedControlFrames = 10000;
    private Integer maxReservedStreams;
    private Integer maxRstFramesPerWindow;
    private Http2PromisedRequestVerifier promisedRequestVerifier = Http2PromisedRequestVerifier.ALWAYS_VERIFY;
    private int secondsPerWindow = 30;
    private Boolean validateHeaders;

    /* access modifiers changed from: protected */
    public abstract T build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) throws Exception;

    /* access modifiers changed from: protected */
    public final B self() {
        return this;
    }

    /* access modifiers changed from: protected */
    public Http2Settings initialSettings() {
        return this.initialSettings;
    }

    /* access modifiers changed from: protected */
    public B initialSettings(Http2Settings http2Settings) {
        this.initialSettings = (Http2Settings) ObjectUtil.checkNotNull(http2Settings, "settings");
        return self();
    }

    /* access modifiers changed from: protected */
    public Http2FrameListener frameListener() {
        return this.frameListener;
    }

    /* access modifiers changed from: protected */
    public B frameListener(Http2FrameListener http2FrameListener) {
        this.frameListener = (Http2FrameListener) ObjectUtil.checkNotNull(http2FrameListener, "frameListener");
        return self();
    }

    /* access modifiers changed from: protected */
    public long gracefulShutdownTimeoutMillis() {
        return this.gracefulShutdownTimeoutMillis;
    }

    /* access modifiers changed from: protected */
    public B gracefulShutdownTimeoutMillis(long j) {
        if (j >= -1) {
            this.gracefulShutdownTimeoutMillis = j;
            return self();
        }
        throw new IllegalArgumentException("gracefulShutdownTimeoutMillis: " + j + " (expected: -1 for indefinite or >= 0)");
    }

    /* access modifiers changed from: protected */
    public boolean isServer() {
        Boolean bool = this.isServer;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public B server(boolean z) {
        enforceConstraint("server", "connection", this.connection);
        enforceConstraint("server", "codec", this.decoder);
        enforceConstraint("server", "codec", this.encoder);
        this.isServer = Boolean.valueOf(z);
        return self();
    }

    /* access modifiers changed from: protected */
    public int maxReservedStreams() {
        Integer num = this.maxReservedStreams;
        if (num != null) {
            return num.intValue();
        }
        return 100;
    }

    /* access modifiers changed from: protected */
    public B maxReservedStreams(int i) {
        enforceConstraint("server", "connection", this.connection);
        enforceConstraint("server", "codec", this.decoder);
        enforceConstraint("server", "codec", this.encoder);
        this.maxReservedStreams = Integer.valueOf(ObjectUtil.checkPositiveOrZero(i, "maxReservedStreams"));
        return self();
    }

    /* access modifiers changed from: protected */
    public Http2Connection connection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public B connection(Http2Connection http2Connection) {
        enforceConstraint("connection", "maxReservedStreams", this.maxReservedStreams);
        enforceConstraint("connection", "server", this.isServer);
        enforceConstraint("connection", "codec", this.decoder);
        enforceConstraint("connection", "codec", this.encoder);
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        return self();
    }

    /* access modifiers changed from: protected */
    public Http2ConnectionDecoder decoder() {
        return this.decoder;
    }

    /* access modifiers changed from: protected */
    public Http2ConnectionEncoder encoder() {
        return this.encoder;
    }

    /* access modifiers changed from: protected */
    public B codec(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder) {
        enforceConstraint("codec", "server", this.isServer);
        enforceConstraint("codec", "maxReservedStreams", this.maxReservedStreams);
        enforceConstraint("codec", "connection", this.connection);
        enforceConstraint("codec", "frameLogger", this.frameLogger);
        enforceConstraint("codec", "validateHeaders", this.validateHeaders);
        enforceConstraint("codec", "headerSensitivityDetector", this.headerSensitivityDetector);
        enforceConstraint("codec", "encoderEnforceMaxConcurrentStreams", this.encoderEnforceMaxConcurrentStreams);
        ObjectUtil.checkNotNull(http2ConnectionDecoder, "decoder");
        ObjectUtil.checkNotNull(http2ConnectionEncoder, "encoder");
        if (http2ConnectionDecoder.connection() == http2ConnectionEncoder.connection()) {
            this.decoder = http2ConnectionDecoder;
            this.encoder = http2ConnectionEncoder;
            return self();
        }
        throw new IllegalArgumentException("The specified encoder and decoder have different connections.");
    }

    /* access modifiers changed from: protected */
    public boolean isValidateHeaders() {
        Boolean bool = this.validateHeaders;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public B validateHeaders(boolean z) {
        enforceNonCodecConstraints("validateHeaders");
        this.validateHeaders = Boolean.valueOf(z);
        return self();
    }

    /* access modifiers changed from: protected */
    public Http2FrameLogger frameLogger() {
        return this.frameLogger;
    }

    /* access modifiers changed from: protected */
    public B frameLogger(Http2FrameLogger http2FrameLogger) {
        enforceNonCodecConstraints("frameLogger");
        this.frameLogger = (Http2FrameLogger) ObjectUtil.checkNotNull(http2FrameLogger, "frameLogger");
        return self();
    }

    /* access modifiers changed from: protected */
    public boolean encoderEnforceMaxConcurrentStreams() {
        Boolean bool = this.encoderEnforceMaxConcurrentStreams;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public B encoderEnforceMaxConcurrentStreams(boolean z) {
        enforceNonCodecConstraints("encoderEnforceMaxConcurrentStreams");
        this.encoderEnforceMaxConcurrentStreams = Boolean.valueOf(z);
        return self();
    }

    /* access modifiers changed from: protected */
    public int encoderEnforceMaxQueuedControlFrames() {
        return this.maxQueuedControlFrames;
    }

    /* access modifiers changed from: protected */
    public B encoderEnforceMaxQueuedControlFrames(int i) {
        enforceNonCodecConstraints("encoderEnforceMaxQueuedControlFrames");
        this.maxQueuedControlFrames = ObjectUtil.checkPositiveOrZero(i, "maxQueuedControlFrames");
        return self();
    }

    /* access modifiers changed from: protected */
    public Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
        Http2HeadersEncoder.SensitivityDetector sensitivityDetector = this.headerSensitivityDetector;
        return sensitivityDetector != null ? sensitivityDetector : DEFAULT_HEADER_SENSITIVITY_DETECTOR;
    }

    /* access modifiers changed from: protected */
    public B headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        enforceNonCodecConstraints("headerSensitivityDetector");
        this.headerSensitivityDetector = (Http2HeadersEncoder.SensitivityDetector) ObjectUtil.checkNotNull(sensitivityDetector, "headerSensitivityDetector");
        return self();
    }

    /* access modifiers changed from: protected */
    public B encoderIgnoreMaxHeaderListSize(boolean z) {
        enforceNonCodecConstraints("encoderIgnoreMaxHeaderListSize");
        this.encoderIgnoreMaxHeaderListSize = Boolean.valueOf(z);
        return self();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public B initialHuffmanDecodeCapacity(int i) {
        return self();
    }

    /* access modifiers changed from: protected */
    public B promisedRequestVerifier(Http2PromisedRequestVerifier http2PromisedRequestVerifier) {
        enforceNonCodecConstraints("promisedRequestVerifier");
        this.promisedRequestVerifier = (Http2PromisedRequestVerifier) ObjectUtil.checkNotNull(http2PromisedRequestVerifier, "promisedRequestVerifier");
        return self();
    }

    /* access modifiers changed from: protected */
    public Http2PromisedRequestVerifier promisedRequestVerifier() {
        return this.promisedRequestVerifier;
    }

    /* access modifiers changed from: protected */
    public int decoderEnforceMaxConsecutiveEmptyDataFrames() {
        return this.maxConsecutiveEmptyFrames;
    }

    /* access modifiers changed from: protected */
    public B decoderEnforceMaxConsecutiveEmptyDataFrames(int i) {
        enforceNonCodecConstraints("maxConsecutiveEmptyFrames");
        this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositiveOrZero(i, "maxConsecutiveEmptyFrames");
        return self();
    }

    /* access modifiers changed from: protected */
    public B decoderEnforceMaxRstFramesPerWindow(int i, int i2) {
        enforceNonCodecConstraints("decoderEnforceMaxRstFramesPerWindow");
        this.maxRstFramesPerWindow = Integer.valueOf(ObjectUtil.checkPositiveOrZero(i, "maxRstFramesPerWindow"));
        this.secondsPerWindow = ObjectUtil.checkPositiveOrZero(i2, "secondsPerWindow");
        return self();
    }

    /* access modifiers changed from: protected */
    public B autoAckSettingsFrame(boolean z) {
        enforceNonCodecConstraints("autoAckSettingsFrame");
        this.autoAckSettingsFrame = z;
        return self();
    }

    /* access modifiers changed from: protected */
    public boolean isAutoAckSettingsFrame() {
        return this.autoAckSettingsFrame;
    }

    /* access modifiers changed from: protected */
    public B autoAckPingFrame(boolean z) {
        enforceNonCodecConstraints("autoAckPingFrame");
        this.autoAckPingFrame = z;
        return self();
    }

    /* access modifiers changed from: protected */
    public boolean isAutoAckPingFrame() {
        return this.autoAckPingFrame;
    }

    /* access modifiers changed from: protected */
    public B decoupleCloseAndGoAway(boolean z) {
        this.decoupleCloseAndGoAway = z;
        return self();
    }

    /* access modifiers changed from: protected */
    public boolean decoupleCloseAndGoAway() {
        return this.decoupleCloseAndGoAway;
    }

    /* access modifiers changed from: protected */
    public B flushPreface(boolean z) {
        this.flushPreface = z;
        return self();
    }

    /* access modifiers changed from: protected */
    public boolean flushPreface() {
        return this.flushPreface;
    }

    /* access modifiers changed from: protected */
    public T build() {
        Http2ConnectionEncoder http2ConnectionEncoder = this.encoder;
        if (http2ConnectionEncoder != null) {
            return buildFromCodec(this.decoder, http2ConnectionEncoder);
        }
        Http2Connection http2Connection = this.connection;
        if (http2Connection == null) {
            http2Connection = new DefaultHttp2Connection(isServer(), maxReservedStreams());
        }
        return buildFromConnection(http2Connection);
    }

    private T buildFromConnection(Http2Connection http2Connection) {
        long j;
        Http2OutboundFrameLogger http2OutboundFrameLogger;
        Http2FrameReader http2FrameReader;
        Http2ConnectionEncoder http2ConnectionEncoder;
        Long maxHeaderListSize = this.initialSettings.maxHeaderListSize();
        boolean isValidateHeaders = isValidateHeaders();
        if (maxHeaderListSize == null) {
            j = 8192;
        } else {
            j = maxHeaderListSize.longValue();
        }
        Http2FrameReader defaultHttp2FrameReader = new DefaultHttp2FrameReader((Http2HeadersDecoder) new DefaultHttp2HeadersDecoder(isValidateHeaders, j, -1));
        if (this.encoderIgnoreMaxHeaderListSize == null) {
            http2OutboundFrameLogger = new DefaultHttp2FrameWriter(headerSensitivityDetector());
        } else {
            http2OutboundFrameLogger = new DefaultHttp2FrameWriter(headerSensitivityDetector(), this.encoderIgnoreMaxHeaderListSize.booleanValue());
        }
        Http2FrameLogger http2FrameLogger = this.frameLogger;
        if (http2FrameLogger != null) {
            Http2FrameReader http2InboundFrameLogger = new Http2InboundFrameLogger(defaultHttp2FrameReader, http2FrameLogger);
            http2OutboundFrameLogger = new Http2OutboundFrameLogger(http2OutboundFrameLogger, this.frameLogger);
            http2FrameReader = http2InboundFrameLogger;
        } else {
            http2FrameReader = defaultHttp2FrameReader;
        }
        Http2ConnectionEncoder defaultHttp2ConnectionEncoder = new DefaultHttp2ConnectionEncoder(http2Connection, http2OutboundFrameLogger);
        boolean encoderEnforceMaxConcurrentStreams2 = encoderEnforceMaxConcurrentStreams();
        if (this.maxQueuedControlFrames != 0) {
            defaultHttp2ConnectionEncoder = new Http2ControlFrameLimitEncoder(defaultHttp2ConnectionEncoder, this.maxQueuedControlFrames);
        }
        if (!encoderEnforceMaxConcurrentStreams2) {
            http2ConnectionEncoder = defaultHttp2ConnectionEncoder;
        } else if (!http2Connection.isServer()) {
            http2ConnectionEncoder = new StreamBufferingEncoder(defaultHttp2ConnectionEncoder);
        } else {
            defaultHttp2ConnectionEncoder.close();
            http2FrameReader.close();
            throw new IllegalArgumentException("encoderEnforceMaxConcurrentStreams: " + encoderEnforceMaxConcurrentStreams2 + " not supported for server");
        }
        return buildFromCodec(new DefaultHttp2ConnectionDecoder(http2Connection, http2ConnectionEncoder, http2FrameReader, promisedRequestVerifier(), isAutoAckSettingsFrame(), isAutoAckPingFrame(), isValidateHeaders()), http2ConnectionEncoder);
    }

    private T buildFromCodec(Http2MaxRstFrameDecoder http2MaxRstFrameDecoder, Http2ConnectionEncoder http2ConnectionEncoder) {
        int i;
        int i2;
        int decoderEnforceMaxConsecutiveEmptyDataFrames = decoderEnforceMaxConsecutiveEmptyDataFrames();
        if (decoderEnforceMaxConsecutiveEmptyDataFrames > 0) {
            http2MaxRstFrameDecoder = new Http2EmptyDataFrameConnectionDecoder(http2MaxRstFrameDecoder, decoderEnforceMaxConsecutiveEmptyDataFrames);
        }
        Integer num = this.maxRstFramesPerWindow;
        if (num == null) {
            i = isServer() ? 200 : 0;
        } else {
            i = num.intValue();
        }
        if (i > 0 && (i2 = this.secondsPerWindow) > 0) {
            http2MaxRstFrameDecoder = new Http2MaxRstFrameDecoder(http2MaxRstFrameDecoder, i, i2);
        }
        try {
            T build = build(http2MaxRstFrameDecoder, http2ConnectionEncoder, this.initialSettings);
            build.gracefulShutdownTimeoutMillis(this.gracefulShutdownTimeoutMillis);
            if (build.decoder().frameListener() == null) {
                build.decoder().frameListener(this.frameListener);
            }
            return build;
        } catch (Throwable th) {
            http2ConnectionEncoder.close();
            http2MaxRstFrameDecoder.close();
            throw new IllegalStateException("failed to build an Http2ConnectionHandler", th);
        }
    }

    private void enforceNonCodecConstraints(String str) {
        enforceConstraint(str, "server/connection", this.decoder);
        enforceConstraint(str, "server/connection", this.encoder);
    }

    private static void enforceConstraint(String str, String str2, Object obj) {
        if (obj != null) {
            throw new IllegalStateException(str + "() cannot be called because " + str2 + "() has been called already.");
        }
    }
}
