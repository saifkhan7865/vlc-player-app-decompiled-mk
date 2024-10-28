package io.netty.handler.codec.http.websocketx;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundInvoker;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.NetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.ObjectUtil;
import java.net.URI;
import java.nio.channels.ClosedChannelException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import org.fusesource.jansi.AnsiRenderer;

public abstract class WebSocketClientHandshaker {
    protected static final int DEFAULT_FORCE_CLOSE_TIMEOUT_MILLIS = 10000;
    /* access modifiers changed from: private */
    public static final AtomicIntegerFieldUpdater<WebSocketClientHandshaker> FORCE_CLOSE_INIT_UPDATER = AtomicIntegerFieldUpdater.newUpdater(WebSocketClientHandshaker.class, "forceCloseInit");
    private static final String HTTPS_SCHEME_PREFIX = (HttpScheme.HTTPS + "://");
    private static final String HTTP_SCHEME_PREFIX = (HttpScheme.HTTP + "://");
    private final boolean absoluteUpgradeUrl;
    private volatile String actualSubprotocol;
    protected final HttpHeaders customHeaders;
    private final String expectedSubprotocol;
    /* access modifiers changed from: private */
    public volatile boolean forceCloseComplete;
    private volatile int forceCloseInit;
    private volatile long forceCloseTimeoutMillis;
    protected final boolean generateOriginHeader;
    private volatile boolean handshakeComplete;
    private final int maxFramePayloadLength;
    private final URI uri;
    private final WebSocketVersion version;

    /* access modifiers changed from: protected */
    public abstract FullHttpRequest newHandshakeRequest();

    /* access modifiers changed from: protected */
    public abstract WebSocketFrameEncoder newWebSocketEncoder();

    /* access modifiers changed from: protected */
    public abstract WebSocketFrameDecoder newWebsocketDecoder();

    /* access modifiers changed from: protected */
    public abstract void verify(FullHttpResponse fullHttpResponse);

    protected WebSocketClientHandshaker(URI uri2, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i) {
        this(uri2, webSocketVersion, str, httpHeaders, i, 10000);
    }

    protected WebSocketClientHandshaker(URI uri2, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i, long j) {
        this(uri2, webSocketVersion, str, httpHeaders, i, j, false);
    }

    protected WebSocketClientHandshaker(URI uri2, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i, long j, boolean z) {
        this(uri2, webSocketVersion, str, httpHeaders, i, j, z, true);
    }

    protected WebSocketClientHandshaker(URI uri2, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i, long j, boolean z, boolean z2) {
        this.forceCloseTimeoutMillis = 10000;
        this.uri = uri2;
        this.version = webSocketVersion;
        this.expectedSubprotocol = str;
        this.customHeaders = httpHeaders;
        this.maxFramePayloadLength = i;
        this.forceCloseTimeoutMillis = j;
        this.absoluteUpgradeUrl = z;
        this.generateOriginHeader = z2;
    }

    public URI uri() {
        return this.uri;
    }

    public WebSocketVersion version() {
        return this.version;
    }

    public int maxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    public boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    private void setHandshakeComplete() {
        this.handshakeComplete = true;
    }

    public String expectedSubprotocol() {
        return this.expectedSubprotocol;
    }

    public String actualSubprotocol() {
        return this.actualSubprotocol;
    }

    private void setActualSubprotocol(String str) {
        this.actualSubprotocol = str;
    }

    public long forceCloseTimeoutMillis() {
        return this.forceCloseTimeoutMillis;
    }

    /* access modifiers changed from: protected */
    public boolean isForceCloseComplete() {
        return this.forceCloseComplete;
    }

    public WebSocketClientHandshaker setForceCloseTimeoutMillis(long j) {
        this.forceCloseTimeoutMillis = j;
        return this;
    }

    public ChannelFuture handshake(Channel channel) {
        ObjectUtil.checkNotNull(channel, TvContractCompat.PARAM_CHANNEL);
        return handshake(channel, channel.newPromise());
    }

    public final ChannelFuture handshake(Channel channel, final ChannelPromise channelPromise) {
        String str;
        ChannelPipeline pipeline = channel.pipeline();
        if (((HttpResponseDecoder) pipeline.get(HttpResponseDecoder.class)) == null && ((HttpClientCodec) pipeline.get(HttpClientCodec.class)) == null) {
            channelPromise.setFailure(new IllegalStateException("ChannelPipeline does not contain an HttpResponseDecoder or HttpClientCodec"));
            return channelPromise;
        }
        if (this.uri.getHost() == null) {
            HttpHeaders httpHeaders = this.customHeaders;
            if (httpHeaders == null || !httpHeaders.contains((CharSequence) HttpHeaderNames.HOST)) {
                channelPromise.setFailure(new IllegalArgumentException("Cannot generate the 'host' header value, webSocketURI should contain host or passed through customHeaders"));
                return channelPromise;
            } else if (this.generateOriginHeader && !this.customHeaders.contains((CharSequence) HttpHeaderNames.ORIGIN)) {
                if (this.version == WebSocketVersion.V07 || this.version == WebSocketVersion.V08) {
                    str = HttpHeaderNames.SEC_WEBSOCKET_ORIGIN.toString();
                } else {
                    str = HttpHeaderNames.ORIGIN.toString();
                }
                channelPromise.setFailure(new IllegalArgumentException("Cannot generate the '" + str + "' header value, webSocketURI should contain host or disable generateOriginHeader or pass value through customHeaders"));
                return channelPromise;
            }
        }
        channel.writeAndFlush(newHandshakeRequest()).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) {
                if (channelFuture.isSuccess()) {
                    ChannelPipeline pipeline = channelFuture.channel().pipeline();
                    ChannelHandlerContext context = pipeline.context((Class<? extends ChannelHandler>) HttpRequestEncoder.class);
                    if (context == null) {
                        context = pipeline.context((Class<? extends ChannelHandler>) HttpClientCodec.class);
                    }
                    if (context == null) {
                        channelPromise.setFailure(new IllegalStateException("ChannelPipeline does not contain an HttpRequestEncoder or HttpClientCodec"));
                        return;
                    }
                    pipeline.addAfter(context.name(), "ws-encoder", WebSocketClientHandshaker.this.newWebSocketEncoder());
                    channelPromise.setSuccess();
                    return;
                }
                channelPromise.setFailure(channelFuture.cause());
            }
        });
        return channelPromise;
    }

    public final void finishHandshake(Channel channel, FullHttpResponse fullHttpResponse) {
        verify(fullHttpResponse);
        String str = fullHttpResponse.headers().get((CharSequence) HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
        String trim = str != null ? str.trim() : null;
        String str2 = this.expectedSubprotocol;
        if (str2 == null) {
            str2 = "";
        }
        if (!str2.isEmpty() || trim != null) {
            if (!str2.isEmpty() && trim != null && !trim.isEmpty()) {
                String[] split = str2.split(AnsiRenderer.CODE_LIST_SEPARATOR);
                int length = split.length;
                int i = 0;
                while (i < length) {
                    if (split[i].trim().equals(trim)) {
                        setActualSubprotocol(trim);
                    } else {
                        i++;
                    }
                }
            }
            throw new WebSocketClientHandshakeException(String.format("Invalid subprotocol. Actual: %s. Expected one of: %s", new Object[]{trim, this.expectedSubprotocol}), fullHttpResponse);
        }
        setActualSubprotocol(this.expectedSubprotocol);
        setHandshakeComplete();
        final ChannelPipeline pipeline = channel.pipeline();
        HttpContentDecompressor httpContentDecompressor = (HttpContentDecompressor) pipeline.get(HttpContentDecompressor.class);
        if (httpContentDecompressor != null) {
            pipeline.remove((ChannelHandler) httpContentDecompressor);
        }
        HttpObjectAggregator httpObjectAggregator = (HttpObjectAggregator) pipeline.get(HttpObjectAggregator.class);
        if (httpObjectAggregator != null) {
            pipeline.remove((ChannelHandler) httpObjectAggregator);
        }
        final ChannelHandlerContext context = pipeline.context((Class<? extends ChannelHandler>) HttpResponseDecoder.class);
        if (context == null) {
            ChannelHandlerContext context2 = pipeline.context((Class<? extends ChannelHandler>) HttpClientCodec.class);
            if (context2 != null) {
                final HttpClientCodec httpClientCodec = (HttpClientCodec) context2.handler();
                httpClientCodec.removeOutboundHandler();
                pipeline.addAfter(context2.name(), "ws-decoder", newWebsocketDecoder());
                channel.eventLoop().execute(new Runnable() {
                    public void run() {
                        pipeline.remove((ChannelHandler) httpClientCodec);
                    }
                });
                return;
            }
            throw new IllegalStateException("ChannelPipeline does not contain an HttpRequestEncoder or HttpClientCodec");
        }
        if (pipeline.get(HttpRequestEncoder.class) != null) {
            pipeline.remove(HttpRequestEncoder.class);
        }
        pipeline.addAfter(context.name(), "ws-decoder", newWebsocketDecoder());
        channel.eventLoop().execute(new Runnable() {
            public void run() {
                pipeline.remove(context.handler());
            }
        });
    }

    public final ChannelFuture processHandshake(Channel channel, HttpResponse httpResponse) {
        return processHandshake(channel, httpResponse, channel.newPromise());
    }

    public final ChannelFuture processHandshake(final Channel channel, HttpResponse httpResponse, final ChannelPromise channelPromise) {
        if (httpResponse instanceof FullHttpResponse) {
            try {
                finishHandshake(channel, (FullHttpResponse) httpResponse);
                channelPromise.setSuccess();
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            ChannelPipeline pipeline = channel.pipeline();
            ChannelHandlerContext context = pipeline.context((Class<? extends ChannelHandler>) HttpResponseDecoder.class);
            if (context == null && (context = pipeline.context((Class<? extends ChannelHandler>) HttpClientCodec.class)) == null) {
                return channelPromise.setFailure(new IllegalStateException("ChannelPipeline does not contain an HttpResponseDecoder or HttpClientCodec"));
            }
            String name = context.name();
            if (this.version == WebSocketVersion.V00) {
                pipeline.addAfter(context.name(), "httpAggregator", new HttpObjectAggregator(8192));
                name = "httpAggregator";
            }
            pipeline.addAfter(name, "handshaker", new ChannelInboundHandlerAdapter() {
                static final /* synthetic */ boolean $assertionsDisabled = false;
                private FullHttpResponse fullHttpResponse;

                static {
                    Class<WebSocketClientHandshaker> cls = WebSocketClientHandshaker.class;
                }

                public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
                    if (obj instanceof HttpObject) {
                        try {
                            handleHandshakeResponse(channelHandlerContext, (HttpObject) obj);
                        } finally {
                            ReferenceCountUtil.release(obj);
                        }
                    } else {
                        super.channelRead(channelHandlerContext, obj);
                    }
                }

                public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
                    channelHandlerContext.pipeline().remove((ChannelHandler) this);
                    channelPromise.setFailure(th);
                }

                public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
                    try {
                        if (!channelPromise.isDone()) {
                            channelPromise.tryFailure(new ClosedChannelException());
                        }
                        channelHandlerContext.fireChannelInactive();
                    } finally {
                        releaseFullHttpResponse();
                    }
                }

                public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
                    releaseFullHttpResponse();
                }

                private void handleHandshakeResponse(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) {
                    if (httpObject instanceof FullHttpResponse) {
                        channelHandlerContext.pipeline().remove((ChannelHandler) this);
                        tryFinishHandshake((FullHttpResponse) httpObject);
                    } else if (httpObject instanceof LastHttpContent) {
                        FullHttpResponse fullHttpResponse2 = this.fullHttpResponse;
                        this.fullHttpResponse = null;
                        try {
                            channelHandlerContext.pipeline().remove((ChannelHandler) this);
                            tryFinishHandshake(fullHttpResponse2);
                        } finally {
                            fullHttpResponse2.release();
                        }
                    } else if (httpObject instanceof HttpResponse) {
                        HttpResponse httpResponse = (HttpResponse) httpObject;
                        this.fullHttpResponse = new DefaultFullHttpResponse(httpResponse.protocolVersion(), httpResponse.status(), Unpooled.EMPTY_BUFFER, httpResponse.headers(), (HttpHeaders) EmptyHttpHeaders.INSTANCE);
                        if (httpResponse.decoderResult().isFailure()) {
                            this.fullHttpResponse.setDecoderResult(httpResponse.decoderResult());
                        }
                    }
                }

                private void tryFinishHandshake(FullHttpResponse fullHttpResponse2) {
                    try {
                        WebSocketClientHandshaker.this.finishHandshake(channel, fullHttpResponse2);
                        channelPromise.setSuccess();
                    } catch (Throwable th) {
                        channelPromise.setFailure(th);
                    }
                }

                private void releaseFullHttpResponse() {
                    FullHttpResponse fullHttpResponse2 = this.fullHttpResponse;
                    if (fullHttpResponse2 != null) {
                        fullHttpResponse2.release();
                        this.fullHttpResponse = null;
                    }
                }
            });
            try {
                context.fireChannelRead(ReferenceCountUtil.retain(httpResponse));
            } catch (Throwable th2) {
                channelPromise.setFailure(th2);
            }
        }
        return channelPromise;
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame) {
        ObjectUtil.checkNotNull(channel, TvContractCompat.PARAM_CHANNEL);
        return close(channel, closeWebSocketFrame, channel.newPromise());
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channel, TvContractCompat.PARAM_CHANNEL);
        return close0(channel, channel, closeWebSocketFrame, channelPromise);
    }

    public ChannelFuture close(ChannelHandlerContext channelHandlerContext, CloseWebSocketFrame closeWebSocketFrame) {
        ObjectUtil.checkNotNull(channelHandlerContext, "ctx");
        return close(channelHandlerContext, closeWebSocketFrame, channelHandlerContext.newPromise());
    }

    public ChannelFuture close(ChannelHandlerContext channelHandlerContext, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channelHandlerContext, "ctx");
        return close0(channelHandlerContext, channelHandlerContext.channel(), closeWebSocketFrame, channelPromise);
    }

    private ChannelFuture close0(ChannelOutboundInvoker channelOutboundInvoker, Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        channelOutboundInvoker.writeAndFlush(closeWebSocketFrame, channelPromise);
        final long j = this.forceCloseTimeoutMillis;
        if (j > 0 && channel.isActive() && this.forceCloseInit == 0) {
            final Channel channel2 = channel;
            final ChannelOutboundInvoker channelOutboundInvoker2 = channelOutboundInvoker;
            channelPromise.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) {
                    if (channelFuture.isSuccess() && channel2.isActive() && WebSocketClientHandshaker.FORCE_CLOSE_INIT_UPDATER.compareAndSet(this, 0, 1)) {
                        final ScheduledFuture<?> schedule = channel2.eventLoop().schedule((Runnable) new Runnable() {
                            public void run() {
                                if (channel2.isActive()) {
                                    channelOutboundInvoker2.close();
                                    boolean unused = WebSocketClientHandshaker.this.forceCloseComplete = true;
                                }
                            }
                        }, j, TimeUnit.MILLISECONDS);
                        channel2.closeFuture().addListener(new ChannelFutureListener() {
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                schedule.cancel(false);
                            }
                        });
                    }
                }
            });
        }
        return channelPromise;
    }

    /* access modifiers changed from: protected */
    public String upgradeUrl(URI uri2) {
        if (this.absoluteUpgradeUrl) {
            return uri2.toString();
        }
        String rawPath = uri2.getRawPath();
        if (rawPath == null || rawPath.isEmpty()) {
            rawPath = "/";
        }
        String rawQuery = uri2.getRawQuery();
        if (rawQuery == null || rawQuery.isEmpty()) {
            return rawPath;
        }
        return rawPath + '?' + rawQuery;
    }

    static CharSequence websocketHostValue(URI uri2) {
        int port = uri2.getPort();
        if (port == -1) {
            return uri2.getHost();
        }
        String host = uri2.getHost();
        String scheme = uri2.getScheme();
        if (port == HttpScheme.HTTP.port()) {
            return (HttpScheme.HTTP.name().contentEquals(scheme) || WebSocketScheme.WS.name().contentEquals(scheme)) ? host : NetUtil.toSocketAddressString(host, port);
        }
        if (port == HttpScheme.HTTPS.port()) {
            return (HttpScheme.HTTPS.name().contentEquals(scheme) || WebSocketScheme.WSS.name().contentEquals(scheme)) ? host : NetUtil.toSocketAddressString(host, port);
        }
        return NetUtil.toSocketAddressString(host, port);
    }

    static CharSequence websocketOriginValue(URI uri2) {
        int i;
        String str;
        String scheme = uri2.getScheme();
        int port = uri2.getPort();
        if (WebSocketScheme.WSS.name().contentEquals(scheme) || HttpScheme.HTTPS.name().contentEquals(scheme) || (scheme == null && port == WebSocketScheme.WSS.port())) {
            str = HTTPS_SCHEME_PREFIX;
            i = WebSocketScheme.WSS.port();
        } else {
            str = HTTP_SCHEME_PREFIX;
            i = WebSocketScheme.WS.port();
        }
        String lowerCase = uri2.getHost().toLowerCase(Locale.US);
        if (port == i || port == -1) {
            return str + lowerCase;
        }
        return str + NetUtil.toSocketAddressString(lowerCase, port);
    }
}
