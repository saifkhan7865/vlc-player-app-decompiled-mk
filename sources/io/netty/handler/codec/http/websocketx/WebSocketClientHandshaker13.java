package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.URI;

public class WebSocketClientHandshaker13 extends WebSocketClientHandshaker {
    public static final String MAGIC_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocketClientHandshaker13.class);
    private final boolean allowExtensions;
    private final boolean allowMaskMismatch;
    private String expectedChallengeResponseString;
    private final boolean performMasking;

    public WebSocketClientHandshaker13(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, true, false);
    }

    public WebSocketClientHandshaker13(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, 10000);
    }

    public WebSocketClientHandshaker13(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, j, false);
    }

    WebSocketClientHandshaker13(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j, boolean z4) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, j, z4, true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebSocketClientHandshaker13(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j, boolean z4, boolean z5) {
        super(uri, webSocketVersion, str, httpHeaders, i, j, z4, z5);
        this.allowExtensions = z;
        this.performMasking = z2;
        this.allowMaskMismatch = z3;
    }

    /* access modifiers changed from: protected */
    public FullHttpRequest newHandshakeRequest() {
        URI uri = uri();
        String base64 = WebSocketUtil.base64(WebSocketUtil.randomBytes(16));
        this.expectedChallengeResponseString = WebSocketUtil.base64(WebSocketUtil.sha1((base64 + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(CharsetUtil.US_ASCII)));
        InternalLogger internalLogger = logger;
        if (internalLogger.isDebugEnabled()) {
            internalLogger.debug("WebSocket version 13 client handshake key: {}, expected response: {}", base64, this.expectedChallengeResponseString);
        }
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, upgradeUrl(uri), Unpooled.EMPTY_BUFFER);
        HttpHeaders headers = defaultFullHttpRequest.headers();
        if (this.customHeaders != null) {
            headers.add(this.customHeaders);
            if (!headers.contains((CharSequence) HttpHeaderNames.HOST)) {
                headers.set((CharSequence) HttpHeaderNames.HOST, (Object) websocketHostValue(uri));
            }
        } else {
            headers.set((CharSequence) HttpHeaderNames.HOST, (Object) websocketHostValue(uri));
        }
        headers.set((CharSequence) HttpHeaderNames.UPGRADE, (Object) HttpHeaderValues.WEBSOCKET).set((CharSequence) HttpHeaderNames.CONNECTION, (Object) HttpHeaderValues.UPGRADE).set((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY, (Object) base64);
        if (this.generateOriginHeader && !headers.contains((CharSequence) HttpHeaderNames.ORIGIN)) {
            headers.set((CharSequence) HttpHeaderNames.ORIGIN, (Object) websocketOriginValue(uri));
        }
        String expectedSubprotocol = expectedSubprotocol();
        if (expectedSubprotocol != null && !expectedSubprotocol.isEmpty()) {
            headers.set((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL, (Object) expectedSubprotocol);
        }
        headers.set((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_VERSION, (Object) version().toAsciiString());
        return defaultFullHttpRequest;
    }

    /* access modifiers changed from: protected */
    public void verify(FullHttpResponse fullHttpResponse) {
        HttpResponseStatus status = fullHttpResponse.status();
        if (HttpResponseStatus.SWITCHING_PROTOCOLS.equals(status)) {
            HttpHeaders headers = fullHttpResponse.headers();
            String str = headers.get((CharSequence) HttpHeaderNames.UPGRADE);
            if (!HttpHeaderValues.WEBSOCKET.contentEqualsIgnoreCase(str)) {
                throw new WebSocketClientHandshakeException("Invalid handshake response upgrade: " + str, fullHttpResponse);
            } else if (headers.containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true)) {
                String str2 = headers.get((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_ACCEPT);
                if (str2 == null || !str2.equals(this.expectedChallengeResponseString)) {
                    throw new WebSocketClientHandshakeException(String.format("Invalid challenge. Actual: %s. Expected: %s", new Object[]{str2, this.expectedChallengeResponseString}), fullHttpResponse);
                }
            } else {
                throw new WebSocketClientHandshakeException("Invalid handshake response connection: " + headers.get((CharSequence) HttpHeaderNames.CONNECTION), fullHttpResponse);
            }
        } else {
            throw new WebSocketClientHandshakeException("Invalid handshake response getStatus: " + status, fullHttpResponse);
        }
    }

    /* access modifiers changed from: protected */
    public WebSocketFrameDecoder newWebsocketDecoder() {
        return new WebSocket13FrameDecoder(false, this.allowExtensions, maxFramePayloadLength(), this.allowMaskMismatch);
    }

    /* access modifiers changed from: protected */
    public WebSocketFrameEncoder newWebSocketEncoder() {
        return new WebSocket13FrameEncoder(this.performMasking);
    }

    public WebSocketClientHandshaker13 setForceCloseTimeoutMillis(long j) {
        super.setForceCloseTimeoutMillis(j);
        return this;
    }

    public boolean isAllowExtensions() {
        return this.allowExtensions;
    }

    public boolean isPerformMasking() {
        return this.performMasking;
    }

    public boolean isAllowMaskMismatch() {
        return this.allowMaskMismatch;
    }
}
