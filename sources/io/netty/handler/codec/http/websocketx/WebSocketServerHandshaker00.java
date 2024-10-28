package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.util.regex.Pattern;

public class WebSocketServerHandshaker00 extends WebSocketServerHandshaker {
    private static final Pattern BEGINNING_DIGIT = Pattern.compile("[^0-9]");
    private static final Pattern BEGINNING_SPACE = Pattern.compile("[^ ]");

    public WebSocketServerHandshaker00(String str, String str2, int i) {
        this(str, str2, WebSocketDecoderConfig.newBuilder().maxFramePayloadLength(i).build());
    }

    public WebSocketServerHandshaker00(String str, String str2, WebSocketDecoderConfig webSocketDecoderConfig) {
        super(WebSocketVersion.V00, str, str2, webSocketDecoderConfig);
    }

    /* access modifiers changed from: protected */
    public FullHttpResponse newHandshakeResponse(FullHttpRequest fullHttpRequest, HttpHeaders httpHeaders) {
        HttpMethod method = fullHttpRequest.method();
        if (HttpMethod.GET.equals(method)) {
            boolean z = true;
            if (!fullHttpRequest.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true) || !HttpHeaderValues.WEBSOCKET.contentEqualsIgnoreCase(fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.UPGRADE))) {
                throw new WebSocketServerHandshakeException("not a WebSocket handshake request: missing upgrade", fullHttpRequest);
            }
            if (!fullHttpRequest.headers().contains((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY1) || !fullHttpRequest.headers().contains((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY2)) {
                z = false;
            }
            String str = fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.ORIGIN);
            if (str != null || z) {
                DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, new HttpResponseStatus(101, z ? "WebSocket Protocol Handshake" : "Web Socket Protocol Handshake"), fullHttpRequest.content().alloc().buffer(0));
                if (httpHeaders != null) {
                    defaultFullHttpResponse.headers().add(httpHeaders);
                }
                defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.UPGRADE, (Object) HttpHeaderValues.WEBSOCKET).set((CharSequence) HttpHeaderNames.CONNECTION, (Object) HttpHeaderValues.UPGRADE);
                if (z) {
                    defaultFullHttpResponse.headers().add((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_ORIGIN, (Object) str);
                    defaultFullHttpResponse.headers().add((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_LOCATION, (Object) uri());
                    String str2 = fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
                    if (str2 != null) {
                        String selectSubprotocol = selectSubprotocol(str2);
                        if (selectSubprotocol != null) {
                            defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL, (Object) selectSubprotocol);
                        } else if (logger.isDebugEnabled()) {
                            logger.debug("Requested subprotocol(s) not supported: {}", (Object) str2);
                        }
                    }
                    String str3 = fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY1);
                    String str4 = fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_KEY2);
                    Pattern pattern = BEGINNING_DIGIT;
                    long parseLong = Long.parseLong(pattern.matcher(str3).replaceAll(""));
                    Pattern pattern2 = BEGINNING_SPACE;
                    int length = (int) (parseLong / ((long) pattern2.matcher(str3).replaceAll("").length()));
                    long readLong = fullHttpRequest.content().readLong();
                    ByteBuf index = Unpooled.wrappedBuffer(new byte[16]).setIndex(0, 0);
                    index.writeInt(length);
                    index.writeInt((int) (Long.parseLong(pattern.matcher(str4).replaceAll("")) / ((long) pattern2.matcher(str4).replaceAll("").length())));
                    index.writeLong(readLong);
                    defaultFullHttpResponse.content().writeBytes(WebSocketUtil.md5(index.array()));
                } else {
                    defaultFullHttpResponse.headers().add((CharSequence) HttpHeaderNames.WEBSOCKET_ORIGIN, (Object) str);
                    defaultFullHttpResponse.headers().add((CharSequence) HttpHeaderNames.WEBSOCKET_LOCATION, (Object) uri());
                    String str5 = fullHttpRequest.headers().get((CharSequence) HttpHeaderNames.WEBSOCKET_PROTOCOL);
                    if (str5 != null) {
                        defaultFullHttpResponse.headers().set((CharSequence) HttpHeaderNames.WEBSOCKET_PROTOCOL, (Object) selectSubprotocol(str5));
                    }
                }
                return defaultFullHttpResponse;
            }
            throw new WebSocketServerHandshakeException("Missing origin header, got only " + fullHttpRequest.headers().names(), fullHttpRequest);
        }
        throw new WebSocketServerHandshakeException("Invalid WebSocket handshake method: " + method, fullHttpRequest);
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        return channel.writeAndFlush(closeWebSocketFrame, channelPromise);
    }

    public ChannelFuture close(ChannelHandlerContext channelHandlerContext, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        return channelHandlerContext.writeAndFlush(closeWebSocketFrame, channelPromise);
    }

    /* access modifiers changed from: protected */
    public WebSocketFrameDecoder newWebsocketDecoder() {
        return new WebSocket00FrameDecoder(decoderConfig());
    }

    /* access modifiers changed from: protected */
    public WebSocketFrameEncoder newWebSocketEncoder() {
        return new WebSocket00FrameEncoder();
    }
}
