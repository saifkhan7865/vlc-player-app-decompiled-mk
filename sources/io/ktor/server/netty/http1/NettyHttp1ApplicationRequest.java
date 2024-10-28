package io.ktor.server.netty.http1;

import io.ktor.http.Headers;
import io.ktor.server.netty.NettyApplicationRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0017\u001a\u00020\u0018H\u0014R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lio/ktor/server/netty/http1/NettyHttp1ApplicationRequest;", "Lio/ktor/server/netty/NettyApplicationRequest;", "call", "Lio/ktor/server/application/ApplicationCall;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "context", "Lio/netty/channel/ChannelHandlerContext;", "httpRequest", "Lio/netty/handler/codec/http/HttpRequest;", "requestBodyChannel", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/CoroutineContext;Lio/netty/channel/ChannelHandlerContext;Lio/netty/handler/codec/http/HttpRequest;Lio/ktor/utils/io/ByteReadChannel;)V", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "getHttpRequest", "()Lio/netty/handler/codec/http/HttpRequest;", "local", "Lio/ktor/server/netty/http1/NettyConnectionPoint;", "getLocal", "()Lio/ktor/server/netty/http1/NettyConnectionPoint;", "newDecoder", "Lio/netty/handler/codec/http/multipart/HttpPostMultipartRequestDecoder;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp1ApplicationRequest.kt */
public final class NettyHttp1ApplicationRequest extends NettyApplicationRequest {
    private final Headers headers;
    private final HttpRequest httpRequest;
    private final NettyConnectionPoint local;

    public final HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NettyHttp1ApplicationRequest(io.ktor.server.application.ApplicationCall r9, kotlin.coroutines.CoroutineContext r10, io.netty.channel.ChannelHandlerContext r11, io.netty.handler.codec.http.HttpRequest r12, io.ktor.utils.io.ByteReadChannel r13) {
        /*
            r8 = this;
            java.lang.String r0 = "call"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "coroutineContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "httpRequest"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "requestBodyChannel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r6 = r12.uri()
            java.lang.String r0 = "httpRequest.uri()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            r0 = r12
            io.netty.handler.codec.http.HttpMessage r0 = (io.netty.handler.codec.http.HttpMessage) r0
            boolean r7 = io.netty.handler.codec.http.HttpUtil.isKeepAlive(r0)
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r8.httpRequest = r12
            io.ktor.server.netty.http1.NettyConnectionPoint r9 = new io.ktor.server.netty.http1.NettyConnectionPoint
            r9.<init>(r12, r11)
            r8.local = r9
            io.ktor.server.netty.NettyApplicationRequestHeaders r9 = new io.ktor.server.netty.NettyApplicationRequestHeaders
            r9.<init>(r12)
            io.ktor.http.Headers r9 = (io.ktor.http.Headers) r9
            r8.headers = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http1.NettyHttp1ApplicationRequest.<init>(io.ktor.server.application.ApplicationCall, kotlin.coroutines.CoroutineContext, io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.HttpRequest, io.ktor.utils.io.ByteReadChannel):void");
    }

    public NettyConnectionPoint getLocal() {
        return this.local;
    }

    public Headers getHeaders() {
        return this.headers;
    }

    /* access modifiers changed from: protected */
    public HttpPostMultipartRequestDecoder newDecoder() {
        return new HttpPostMultipartRequestDecoder(this.httpRequest);
    }
}
