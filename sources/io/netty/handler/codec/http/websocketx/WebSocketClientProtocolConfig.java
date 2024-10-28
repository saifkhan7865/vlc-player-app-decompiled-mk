package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.internal.ObjectUtil;
import java.net.URI;
import org.videolan.moviepedia.BuildConfig;

public final class WebSocketClientProtocolConfig {
    static final boolean DEFAULT_ALLOW_MASK_MISMATCH = false;
    static final boolean DEFAULT_DROP_PONG_FRAMES = true;
    static final boolean DEFAULT_GENERATE_ORIGIN_HEADER = true;
    static final boolean DEFAULT_HANDLE_CLOSE_FRAMES = true;
    static final boolean DEFAULT_PERFORM_MASKING = true;
    private final boolean absoluteUpgradeUrl;
    private final boolean allowExtensions;
    private final boolean allowMaskMismatch;
    private final HttpHeaders customHeaders;
    private final boolean dropPongFrames;
    private final long forceCloseTimeoutMillis;
    private final boolean generateOriginHeader;
    private final boolean handleCloseFrames;
    private final long handshakeTimeoutMillis;
    private final int maxFramePayloadLength;
    private final boolean performMasking;
    private final WebSocketCloseStatus sendCloseFrame;
    private final String subprotocol;
    private final WebSocketVersion version;
    private final URI webSocketUri;
    private final boolean withUTF8Validator;

    private WebSocketClientProtocolConfig(URI uri, String str, WebSocketVersion webSocketVersion, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4, WebSocketCloseStatus webSocketCloseStatus, boolean z5, long j, long j2, boolean z6, boolean z7, boolean z8) {
        this.webSocketUri = uri;
        this.subprotocol = str;
        this.version = webSocketVersion;
        this.allowExtensions = z;
        this.customHeaders = httpHeaders;
        this.maxFramePayloadLength = i;
        this.performMasking = z2;
        this.allowMaskMismatch = z3;
        this.forceCloseTimeoutMillis = j2;
        this.handleCloseFrames = z4;
        this.sendCloseFrame = webSocketCloseStatus;
        this.dropPongFrames = z5;
        this.handshakeTimeoutMillis = ObjectUtil.checkPositive(j, "handshakeTimeoutMillis");
        this.absoluteUpgradeUrl = z6;
        this.generateOriginHeader = z7;
        this.withUTF8Validator = z8;
    }

    public URI webSocketUri() {
        return this.webSocketUri;
    }

    public String subprotocol() {
        return this.subprotocol;
    }

    public WebSocketVersion version() {
        return this.version;
    }

    public boolean allowExtensions() {
        return this.allowExtensions;
    }

    public HttpHeaders customHeaders() {
        return this.customHeaders;
    }

    public int maxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    public boolean performMasking() {
        return this.performMasking;
    }

    public boolean allowMaskMismatch() {
        return this.allowMaskMismatch;
    }

    public boolean handleCloseFrames() {
        return this.handleCloseFrames;
    }

    public WebSocketCloseStatus sendCloseFrame() {
        return this.sendCloseFrame;
    }

    public boolean dropPongFrames() {
        return this.dropPongFrames;
    }

    public long handshakeTimeoutMillis() {
        return this.handshakeTimeoutMillis;
    }

    public long forceCloseTimeoutMillis() {
        return this.forceCloseTimeoutMillis;
    }

    public boolean absoluteUpgradeUrl() {
        return this.absoluteUpgradeUrl;
    }

    public boolean generateOriginHeader() {
        return this.generateOriginHeader;
    }

    public boolean withUTF8Validator() {
        return this.withUTF8Validator;
    }

    public String toString() {
        return "WebSocketClientProtocolConfig {webSocketUri=" + this.webSocketUri + ", subprotocol=" + this.subprotocol + ", version=" + this.version + ", allowExtensions=" + this.allowExtensions + ", customHeaders=" + this.customHeaders + ", maxFramePayloadLength=" + this.maxFramePayloadLength + ", performMasking=" + this.performMasking + ", allowMaskMismatch=" + this.allowMaskMismatch + ", handleCloseFrames=" + this.handleCloseFrames + ", sendCloseFrame=" + this.sendCloseFrame + ", dropPongFrames=" + this.dropPongFrames + ", handshakeTimeoutMillis=" + this.handshakeTimeoutMillis + ", forceCloseTimeoutMillis=" + this.forceCloseTimeoutMillis + ", absoluteUpgradeUrl=" + this.absoluteUpgradeUrl + ", generateOriginHeader=" + this.generateOriginHeader + "}";
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder(URI.create(BuildConfig.MOVIEPEDIA_API_URL), (String) null, WebSocketVersion.V13, false, EmptyHttpHeaders.INSTANCE, 65536, true, false, true, WebSocketCloseStatus.NORMAL_CLOSURE, true, 10000, -1, false, true, true);
    }

    public static final class Builder {
        private boolean absoluteUpgradeUrl;
        private boolean allowExtensions;
        private boolean allowMaskMismatch;
        private HttpHeaders customHeaders;
        private boolean dropPongFrames;
        private long forceCloseTimeoutMillis;
        private boolean generateOriginHeader;
        private boolean handleCloseFrames;
        private long handshakeTimeoutMillis;
        private int maxFramePayloadLength;
        private boolean performMasking;
        private WebSocketCloseStatus sendCloseFrame;
        private String subprotocol;
        private WebSocketVersion version;
        private URI webSocketUri;
        private boolean withUTF8Validator;

        private Builder(WebSocketClientProtocolConfig webSocketClientProtocolConfig) {
            this(((WebSocketClientProtocolConfig) ObjectUtil.checkNotNull(webSocketClientProtocolConfig, "clientConfig")).webSocketUri(), webSocketClientProtocolConfig.subprotocol(), webSocketClientProtocolConfig.version(), webSocketClientProtocolConfig.allowExtensions(), webSocketClientProtocolConfig.customHeaders(), webSocketClientProtocolConfig.maxFramePayloadLength(), webSocketClientProtocolConfig.performMasking(), webSocketClientProtocolConfig.allowMaskMismatch(), webSocketClientProtocolConfig.handleCloseFrames(), webSocketClientProtocolConfig.sendCloseFrame(), webSocketClientProtocolConfig.dropPongFrames(), webSocketClientProtocolConfig.handshakeTimeoutMillis(), webSocketClientProtocolConfig.forceCloseTimeoutMillis(), webSocketClientProtocolConfig.absoluteUpgradeUrl(), webSocketClientProtocolConfig.generateOriginHeader(), webSocketClientProtocolConfig.withUTF8Validator());
        }

        private Builder(URI uri, String str, WebSocketVersion webSocketVersion, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4, WebSocketCloseStatus webSocketCloseStatus, boolean z5, long j, long j2, boolean z6, boolean z7, boolean z8) {
            this.webSocketUri = uri;
            this.subprotocol = str;
            this.version = webSocketVersion;
            this.allowExtensions = z;
            this.customHeaders = httpHeaders;
            this.maxFramePayloadLength = i;
            this.performMasking = z2;
            this.allowMaskMismatch = z3;
            this.handleCloseFrames = z4;
            this.sendCloseFrame = webSocketCloseStatus;
            this.dropPongFrames = z5;
            this.handshakeTimeoutMillis = j;
            this.forceCloseTimeoutMillis = j2;
            this.absoluteUpgradeUrl = z6;
            this.generateOriginHeader = z7;
            this.withUTF8Validator = z8;
        }

        public Builder webSocketUri(String str) {
            return webSocketUri(URI.create(str));
        }

        public Builder webSocketUri(URI uri) {
            this.webSocketUri = uri;
            return this;
        }

        public Builder subprotocol(String str) {
            this.subprotocol = str;
            return this;
        }

        public Builder version(WebSocketVersion webSocketVersion) {
            this.version = webSocketVersion;
            return this;
        }

        public Builder allowExtensions(boolean z) {
            this.allowExtensions = z;
            return this;
        }

        public Builder customHeaders(HttpHeaders httpHeaders) {
            this.customHeaders = httpHeaders;
            return this;
        }

        public Builder maxFramePayloadLength(int i) {
            this.maxFramePayloadLength = i;
            return this;
        }

        public Builder performMasking(boolean z) {
            this.performMasking = z;
            return this;
        }

        public Builder allowMaskMismatch(boolean z) {
            this.allowMaskMismatch = z;
            return this;
        }

        public Builder handleCloseFrames(boolean z) {
            this.handleCloseFrames = z;
            return this;
        }

        public Builder sendCloseFrame(WebSocketCloseStatus webSocketCloseStatus) {
            this.sendCloseFrame = webSocketCloseStatus;
            return this;
        }

        public Builder dropPongFrames(boolean z) {
            this.dropPongFrames = z;
            return this;
        }

        public Builder handshakeTimeoutMillis(long j) {
            this.handshakeTimeoutMillis = j;
            return this;
        }

        public Builder forceCloseTimeoutMillis(long j) {
            this.forceCloseTimeoutMillis = j;
            return this;
        }

        public Builder absoluteUpgradeUrl(boolean z) {
            this.absoluteUpgradeUrl = z;
            return this;
        }

        public Builder generateOriginHeader(boolean z) {
            this.generateOriginHeader = z;
            return this;
        }

        public Builder withUTF8Validator(boolean z) {
            this.withUTF8Validator = z;
            return this;
        }

        public WebSocketClientProtocolConfig build() {
            return new WebSocketClientProtocolConfig(this.webSocketUri, this.subprotocol, this.version, this.allowExtensions, this.customHeaders, this.maxFramePayloadLength, this.performMasking, this.allowMaskMismatch, this.handleCloseFrames, this.sendCloseFrame, this.dropPongFrames, this.handshakeTimeoutMillis, this.forceCloseTimeoutMillis, this.absoluteUpgradeUrl, this.generateOriginHeader, this.withUTF8Validator);
        }
    }
}
