package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.ObjectUtil;
import java.util.concurrent.TimeUnit;

class WebSocketServerProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
    /* access modifiers changed from: private */
    public ChannelHandlerContext ctx;
    private ChannelPromise handshakePromise;
    private boolean isWebSocketPath;
    private final WebSocketServerProtocolConfig serverConfig;

    WebSocketServerProtocolHandshakeHandler(WebSocketServerProtocolConfig webSocketServerProtocolConfig) {
        this.serverConfig = (WebSocketServerProtocolConfig) ObjectUtil.checkNotNull(webSocketServerProtocolConfig, "serverConfig");
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        this.ctx = channelHandlerContext;
        this.handshakePromise = channelHandlerContext.newPromise();
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        HttpObject httpObject = (HttpObject) obj;
        if (httpObject instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpObject;
            boolean isWebSocketPath2 = isWebSocketPath(httpRequest);
            this.isWebSocketPath = isWebSocketPath2;
            if (!isWebSocketPath2) {
                channelHandlerContext.fireChannelRead(obj);
                return;
            }
            try {
                final WebSocketServerHandshaker newHandshaker = new WebSocketServerHandshakerFactory(getWebSocketLocation(channelHandlerContext.pipeline(), httpRequest, this.serverConfig.websocketPath()), this.serverConfig.subprotocols(), this.serverConfig.decoderConfig()).newHandshaker(httpRequest);
                final ChannelPromise channelPromise = this.handshakePromise;
                if (newHandshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channelHandlerContext.channel());
                } else {
                    WebSocketServerProtocolHandler.setHandshaker(channelHandlerContext.channel(), newHandshaker);
                    channelHandlerContext.pipeline().remove((ChannelHandler) this);
                    final ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
                    final HttpRequest httpRequest2 = httpRequest;
                    newHandshaker.handshake(channelHandlerContext.channel(), httpRequest).addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture channelFuture) {
                            if (!channelFuture.isSuccess()) {
                                channelPromise.tryFailure(channelFuture.cause());
                                channelHandlerContext2.fireExceptionCaught(channelFuture.cause());
                                return;
                            }
                            channelPromise.trySuccess();
                            channelHandlerContext2.fireUserEventTriggered(WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE);
                            channelHandlerContext2.fireUserEventTriggered(new WebSocketServerProtocolHandler.HandshakeComplete(httpRequest2.uri(), httpRequest2.headers(), newHandshaker.selectedSubprotocol()));
                        }
                    });
                    applyHandshakeTimeout();
                }
            } finally {
                ReferenceCountUtil.release(httpRequest);
            }
        } else if (!this.isWebSocketPath) {
            channelHandlerContext.fireChannelRead(obj);
        } else {
            ReferenceCountUtil.release(obj);
        }
    }

    private boolean isWebSocketPath(HttpRequest httpRequest) {
        String websocketPath = this.serverConfig.websocketPath();
        String uri = httpRequest.uri();
        boolean startsWith = uri.startsWith(websocketPath);
        boolean z = "/".equals(websocketPath) || checkNextUri(uri, websocketPath);
        if (!this.serverConfig.checkStartsWith()) {
            return uri.equals(websocketPath);
        }
        if (!startsWith || !z) {
            return false;
        }
        return true;
    }

    private boolean checkNextUri(String str, String str2) {
        char charAt;
        int length = str2.length();
        if (str.length() <= length || (charAt = str.charAt(length)) == '/' || charAt == '?') {
            return true;
        }
        return false;
    }

    private static void sendHttpResponse(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest, HttpResponse httpResponse) {
        ChannelFuture writeAndFlush = channelHandlerContext.writeAndFlush(httpResponse);
        if (!HttpUtil.isKeepAlive(httpRequest) || httpResponse.status().code() != 200) {
            writeAndFlush.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private static String getWebSocketLocation(ChannelPipeline channelPipeline, HttpRequest httpRequest, String str) {
        String str2;
        if (channelPipeline.get(SslHandler.class) != null) {
            str2 = "wss";
        } else {
            str2 = "ws";
        }
        String str3 = httpRequest.headers().get((CharSequence) HttpHeaderNames.HOST);
        return str2 + "://" + str3 + str;
    }

    private void applyHandshakeTimeout() {
        final ChannelPromise channelPromise = this.handshakePromise;
        long handshakeTimeoutMillis = this.serverConfig.handshakeTimeoutMillis();
        if (handshakeTimeoutMillis > 0 && !channelPromise.isDone()) {
            final ScheduledFuture<?> schedule = this.ctx.executor().schedule((Runnable) new Runnable() {
                public void run() {
                    if (!channelPromise.isDone() && channelPromise.tryFailure(new WebSocketServerHandshakeException("handshake timed out"))) {
                        WebSocketServerProtocolHandshakeHandler.this.ctx.flush().fireUserEventTriggered(WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_TIMEOUT).close();
                    }
                }
            }, handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
            channelPromise.addListener(new FutureListener<Void>() {
                public void operationComplete(Future<Void> future) {
                    schedule.cancel(false);
                }
            });
        }
    }
}
