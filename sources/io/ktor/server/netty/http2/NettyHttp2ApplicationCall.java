package io.ktor.server.netty.http2;

import io.ktor.server.application.Application;
import io.ktor.server.engine.BaseApplicationCall;
import io.ktor.server.engine.BaseApplicationResponse;
import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.utils.io.ByteChannel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.DefaultHttp2DataFrame;
import io.netty.handler.codec.http2.Http2Headers;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\r\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001aJ\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001eJ\u001d\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0019H\u0010¢\u0006\u0002\b#J\u0015\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0005H\u0010¢\u0006\u0002\b'R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006("}, d2 = {"Lio/ktor/server/netty/http2/NettyHttp2ApplicationCall;", "Lio/ktor/server/netty/NettyApplicationCall;", "application", "Lio/ktor/server/application/Application;", "context", "Lio/netty/channel/ChannelHandlerContext;", "headers", "Lio/netty/handler/codec/http2/Http2Headers;", "handler", "Lio/ktor/server/netty/http2/NettyHttp2Handler;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "(Lio/ktor/server/application/Application;Lio/netty/channel/ChannelHandlerContext;Lio/netty/handler/codec/http2/Http2Headers;Lio/ktor/server/netty/http2/NettyHttp2Handler;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)V", "getHeaders", "()Lio/netty/handler/codec/http2/Http2Headers;", "request", "Lio/ktor/server/netty/http2/NettyHttp2ApplicationRequest;", "getRequest", "()Lio/ktor/server/netty/http2/NettyHttp2ApplicationRequest;", "response", "Lio/ktor/server/netty/http2/NettyHttp2ApplicationResponse;", "getResponse", "()Lio/ktor/server/netty/http2/NettyHttp2ApplicationResponse;", "isContextCloseRequired", "", "isContextCloseRequired$ktor_server_netty", "prepareEndOfStreamMessage", "", "lastTransformed", "prepareEndOfStreamMessage$ktor_server_netty", "prepareMessage", "buf", "Lio/netty/buffer/ByteBuf;", "isLastContent", "prepareMessage$ktor_server_netty", "upgrade", "", "dst", "upgrade$ktor_server_netty", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp2ApplicationCall.kt */
public final class NettyHttp2ApplicationCall extends NettyApplicationCall {
    private final Http2Headers headers;
    private final NettyHttp2ApplicationRequest request;
    private final NettyHttp2ApplicationResponse response;

    public boolean isContextCloseRequired$ktor_server_netty() {
        return false;
    }

    public final Http2Headers getHeaders() {
        return this.headers;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyHttp2ApplicationCall(Application application, ChannelHandlerContext channelHandlerContext, Http2Headers http2Headers, NettyHttp2Handler nettyHttp2Handler, CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        super(application, channelHandlerContext, http2Headers);
        Http2Headers http2Headers2 = http2Headers;
        Application application2 = application;
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(http2Headers2, "headers");
        Intrinsics.checkNotNullParameter(nettyHttp2Handler, "handler");
        Intrinsics.checkNotNullParameter(coroutineContext, "engineContext");
        Intrinsics.checkNotNullParameter(coroutineContext2, "userContext");
        this.headers = http2Headers2;
        this.request = new NettyHttp2ApplicationRequest(this, coroutineContext, channelHandlerContext, http2Headers2, (ByteChannel) null, 16, (DefaultConstructorMarker) null);
        this.response = new NettyHttp2ApplicationResponse(this, nettyHttp2Handler, channelHandlerContext, coroutineContext, coroutineContext2);
        BaseApplicationCall.putResponseAttribute$default(this, (BaseApplicationResponse) null, 1, (Object) null);
    }

    public NettyHttp2ApplicationRequest getRequest() {
        return this.request;
    }

    public NettyHttp2ApplicationResponse getResponse() {
        return this.response;
    }

    public Object prepareMessage$ktor_server_netty(ByteBuf byteBuf, boolean z) {
        Intrinsics.checkNotNullParameter(byteBuf, "buf");
        if (isByteBufferContent$ktor_server_netty()) {
            return super.prepareMessage$ktor_server_netty(byteBuf, z);
        }
        return new DefaultHttp2DataFrame(byteBuf, z);
    }

    public Object prepareEndOfStreamMessage$ktor_server_netty(boolean z) {
        if (isByteBufferContent$ktor_server_netty()) {
            return super.prepareEndOfStreamMessage$ktor_server_netty(z);
        }
        if (z) {
            return null;
        }
        return new DefaultHttp2DataFrame(true);
    }

    public void upgrade$ktor_server_netty(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "dst");
        if (isByteBufferContent$ktor_server_netty()) {
            super.upgrade$ktor_server_netty(channelHandlerContext);
            return;
        }
        throw new IllegalStateException("HTTP/2 doesn't support upgrade");
    }
}
