package io.ktor.server.netty.http1;

import io.ktor.server.application.Application;
import io.ktor.server.engine.BaseApplicationCall;
import io.ktor.server.engine.BaseApplicationResponse;
import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.server.netty.NettyDirectEncoder;
import io.ktor.utils.io.ByteReadChannel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\r\u0010\u0016\u001a\u00020\u0017H\u0010¢\u0006\u0002\b\u0018J\u0017\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0017H\u0010¢\u0006\u0002\b\u001cJ\u001d\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0017H\u0010¢\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0005H\u0010¢\u0006\u0002\b%R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006&"}, d2 = {"Lio/ktor/server/netty/http1/NettyHttp1ApplicationCall;", "Lio/ktor/server/netty/NettyApplicationCall;", "application", "Lio/ktor/server/application/Application;", "context", "Lio/netty/channel/ChannelHandlerContext;", "httpRequest", "Lio/netty/handler/codec/http/HttpRequest;", "requestBodyChannel", "Lio/ktor/utils/io/ByteReadChannel;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "(Lio/ktor/server/application/Application;Lio/netty/channel/ChannelHandlerContext;Lio/netty/handler/codec/http/HttpRequest;Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)V", "request", "Lio/ktor/server/netty/http1/NettyHttp1ApplicationRequest;", "getRequest", "()Lio/ktor/server/netty/http1/NettyHttp1ApplicationRequest;", "response", "Lio/ktor/server/netty/http1/NettyHttp1ApplicationResponse;", "getResponse", "()Lio/ktor/server/netty/http1/NettyHttp1ApplicationResponse;", "isContextCloseRequired", "", "isContextCloseRequired$ktor_server_netty", "prepareEndOfStreamMessage", "", "lastTransformed", "prepareEndOfStreamMessage$ktor_server_netty", "prepareMessage", "buf", "Lio/netty/buffer/ByteBuf;", "isLastContent", "prepareMessage$ktor_server_netty", "upgrade", "", "dst", "upgrade$ktor_server_netty", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp1ApplicationCall.kt */
public final class NettyHttp1ApplicationCall extends NettyApplicationCall {
    private final NettyHttp1ApplicationRequest request;
    private final NettyHttp1ApplicationResponse response;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyHttp1ApplicationCall(Application application, ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest, ByteReadChannel byteReadChannel, CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        super(application, channelHandlerContext, httpRequest);
        Application application2 = application;
        Intrinsics.checkNotNullParameter(application, "application");
        ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        HttpRequest httpRequest2 = httpRequest;
        Intrinsics.checkNotNullParameter(httpRequest, "httpRequest");
        Intrinsics.checkNotNullParameter(coroutineContext, "engineContext");
        Intrinsics.checkNotNullParameter(coroutineContext2, "userContext");
        this.request = new NettyHttp1ApplicationRequest(this, coroutineContext, channelHandlerContext, httpRequest, byteReadChannel == null ? ByteReadChannel.Companion.getEmpty() : byteReadChannel);
        HttpVersion protocolVersion = httpRequest.protocolVersion();
        Intrinsics.checkNotNullExpressionValue(protocolVersion, "httpRequest.protocolVersion()");
        this.response = new NettyHttp1ApplicationResponse(this, channelHandlerContext, coroutineContext, coroutineContext2, protocolVersion);
        BaseApplicationCall.putResponseAttribute$default(this, (BaseApplicationResponse) null, 1, (Object) null);
    }

    public NettyHttp1ApplicationRequest getRequest() {
        return this.request;
    }

    public NettyHttp1ApplicationResponse getResponse() {
        return this.response;
    }

    public Object prepareMessage$ktor_server_netty(ByteBuf byteBuf, boolean z) {
        Intrinsics.checkNotNullParameter(byteBuf, "buf");
        if (isByteBufferContent$ktor_server_netty()) {
            return super.prepareMessage$ktor_server_netty(byteBuf, z);
        }
        return new DefaultHttpContent(byteBuf);
    }

    public Object prepareEndOfStreamMessage$ktor_server_netty(boolean z) {
        if (isByteBufferContent$ktor_server_netty()) {
            return super.prepareEndOfStreamMessage$ktor_server_netty(z);
        }
        return LastHttpContent.EMPTY_LAST_CONTENT;
    }

    public void upgrade$ktor_server_netty(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "dst");
        if (isByteBufferContent$ktor_server_netty()) {
            super.upgrade$ktor_server_netty(channelHandlerContext);
        } else {
            channelHandlerContext.pipeline().replace(HttpServerCodec.class, "direct-encoder", (ChannelHandler) new NettyDirectEncoder());
        }
    }

    public boolean isContextCloseRequired$ktor_server_netty() {
        return !isByteBufferContent$ktor_server_netty();
    }
}
