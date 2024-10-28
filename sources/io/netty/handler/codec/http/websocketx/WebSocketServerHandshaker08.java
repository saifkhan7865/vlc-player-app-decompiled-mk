package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class WebSocketServerHandshaker08 extends WebSocketServerHandshaker {
    public static final String WEBSOCKET_08_ACCEPT_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

    public WebSocketServerHandshaker08(String str, String str2, boolean z, int i) {
        this(str, str2, z, i, false);
    }

    public WebSocketServerHandshaker08(String str, String str2, boolean z, int i, boolean z2) {
        this(str, str2, WebSocketDecoderConfig.newBuilder().allowExtensions(z).maxFramePayloadLength(i).allowMaskMismatch(z2).build());
    }

    public WebSocketServerHandshaker08(String str, String str2, WebSocketDecoderConfig webSocketDecoderConfig) {
        super(WebSocketVersion.V08, str, str2, webSocketDecoderConfig);
    }

    /* access modifiers changed from: protected */
    public FullHttpResponse newHandshakeResponse(FullHttpRequest fullHttpRequest, HttpHeaders httpHeaders) {
        HttpMethod method = fullHttpRequest.method();
        if (HttpMethod.GET.equals(method)) {
            String str = fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY);
            if (str != null) {
                DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS, fullHttpRequest.content().alloc().buffer(0));
                if (httpHeaders != null) {
                    defaultFullHttpResponse.headers().add(httpHeaders);
                }
                String base64 = WebSocketUtil.base64(WebSocketUtil.sha1((str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(CharsetUtil.US_ASCII)));
                if (logger.isDebugEnabled()) {
                    logger.debug("WebSocket version 08 server handshake key: {}, response: {}", str, base64);
                }
                defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.UPGRADE, (Object) HttpHeaderValues.WEBSOCKET).set((CharSequence) HttpHeaderNames.CONNECTION, (Object) HttpHeaderValues.UPGRADE).set((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_ACCEPT, (Object) base64);
                String str2 = fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
                if (str2 != null) {
                    String selectSubprotocol = selectSubprotocol(str2);
                    if (selectSubprotocol != null) {
                        defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL, (Object) selectSubprotocol);
                    } else if (logger.isDebugEnabled()) {
                        logger.debug("Requested subprotocol(s) not supported: {}", (Object) str2);
                    }
                }
                return defaultFullHttpResponse;
            }
            throw new WebSocketServerHandshakeException("not a WebSocket request: missing key", fullHttpRequest);
        }
        throw new WebSocketServerHandshakeException("Invalid WebSocket handshake method: " + method, fullHttpRequest);
    }

    /* access modifiers changed from: protected */
    public WebSocketFrameDecoder newWebsocketDecoder() {
        return new WebSocket08FrameDecoder(decoderConfig());
    }

    /* access modifiers changed from: protected */
    public WebSocketFrameEncoder newWebSocketEncoder() {
        return new WebSocket08FrameEncoder(false);
    }
}
