package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpHeaders;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

public class WebSocketClientProtocolHandler extends WebSocketProtocolHandler {
    private final WebSocketClientProtocolConfig clientConfig;
    private final WebSocketClientHandshaker handshaker;

    public enum ClientHandshakeStateEvent {
        HANDSHAKE_TIMEOUT,
        HANDSHAKE_ISSUED,
        HANDSHAKE_COMPLETE
    }

    public /* bridge */ /* synthetic */ void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        super.bind(channelHandlerContext, socketAddress, channelPromise);
    }

    public /* bridge */ /* synthetic */ void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.close(channelHandlerContext, channelPromise);
    }

    public /* bridge */ /* synthetic */ void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        super.connect(channelHandlerContext, socketAddress, socketAddress2, channelPromise);
    }

    public /* bridge */ /* synthetic */ void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.deregister(channelHandlerContext, channelPromise);
    }

    public /* bridge */ /* synthetic */ void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.disconnect(channelHandlerContext, channelPromise);
    }

    public /* bridge */ /* synthetic */ void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        super.exceptionCaught(channelHandlerContext, th);
    }

    public /* bridge */ /* synthetic */ void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.flush(channelHandlerContext);
    }

    public /* bridge */ /* synthetic */ void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.read(channelHandlerContext);
    }

    public /* bridge */ /* synthetic */ void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        super.write(channelHandlerContext, obj, channelPromise);
    }

    public WebSocketClientHandshaker handshaker() {
        return this.handshaker;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebSocketClientProtocolHandler(io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "clientConfig"
            java.lang.Object r2 = io.netty.util.internal.ObjectUtil.checkNotNull(r1, r2)
            io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig r2 = (io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig) r2
            boolean r2 = r2.dropPongFrames()
            io.netty.handler.codec.http.websocketx.WebSocketCloseStatus r3 = r19.sendCloseFrame()
            long r4 = r19.forceCloseTimeoutMillis()
            r0.<init>(r2, r3, r4)
            java.net.URI r6 = r19.webSocketUri()
            io.netty.handler.codec.http.websocketx.WebSocketVersion r7 = r19.version()
            java.lang.String r8 = r19.subprotocol()
            boolean r9 = r19.allowExtensions()
            io.netty.handler.codec.http.HttpHeaders r10 = r19.customHeaders()
            int r11 = r19.maxFramePayloadLength()
            boolean r12 = r19.performMasking()
            boolean r13 = r19.allowMaskMismatch()
            long r14 = r19.forceCloseTimeoutMillis()
            boolean r16 = r19.absoluteUpgradeUrl()
            boolean r17 = r19.generateOriginHeader()
            io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker r2 = io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory.newHandshaker(r6, r7, r8, r9, r10, r11, r12, r13, r14, r16, r17)
            r0.handshaker = r2
            r0.clientConfig = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler.<init>(io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig):void");
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, z4, 10000);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4, long j) {
        this(WebSocketClientHandshakerFactory.newHandshaker(uri, webSocketVersion, str, z, httpHeaders, i, z3, z4), z2, j);
        boolean z5 = z2;
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, 10000);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, long j) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, true, false, j);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, 10000);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, long j) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, true, j);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z) {
        this(webSocketClientHandshaker, z, 10000);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z, long j) {
        this(webSocketClientHandshaker, z, true, j);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z, boolean z2) {
        this(webSocketClientHandshaker, z, z2, 10000);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z, boolean z2, long j) {
        super(z2);
        this.handshaker = webSocketClientHandshaker;
        this.clientConfig = WebSocketClientProtocolConfig.newBuilder().handleCloseFrames(z).handshakeTimeoutMillis(j).build();
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker) {
        this(webSocketClientHandshaker, 10000);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, long j) {
        this(webSocketClientHandshaker, true, j);
    }

    /* access modifiers changed from: protected */
    public void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        if (!this.clientConfig.handleCloseFrames() || !(webSocketFrame instanceof CloseWebSocketFrame)) {
            super.decode(channelHandlerContext, webSocketFrame, list);
        } else {
            channelHandlerContext.close();
        }
    }

    /* access modifiers changed from: protected */
    public WebSocketClientHandshakeException buildHandshakeException(String str) {
        return new WebSocketClientHandshakeException(str);
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        if (pipeline.get(WebSocketClientProtocolHandshakeHandler.class) == null) {
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), WebSocketClientProtocolHandshakeHandler.class.getName(), new WebSocketClientProtocolHandshakeHandler(this.handshaker, this.clientConfig.handshakeTimeoutMillis()));
        }
        if (this.clientConfig.withUTF8Validator() && pipeline.get(Utf8FrameValidator.class) == null) {
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), Utf8FrameValidator.class.getName(), new Utf8FrameValidator());
        }
    }
}
