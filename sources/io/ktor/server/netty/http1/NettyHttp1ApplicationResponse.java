package io.ktor.server.netty.http1;

import androidx.core.app.NotificationCompat;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.server.netty.NettyApplicationResponse;
import io.ktor.server.response.ResponseHeaders;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0014J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0014J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'H\u0014R\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Lio/ktor/server/netty/http1/NettyHttp1ApplicationResponse;", "Lio/ktor/server/netty/NettyApplicationResponse;", "call", "Lio/ktor/server/netty/NettyApplicationCall;", "context", "Lio/netty/channel/ChannelHandlerContext;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "protocol", "Lio/netty/handler/codec/http/HttpVersion;", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/netty/channel/ChannelHandlerContext;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Lio/netty/handler/codec/http/HttpVersion;)V", "headers", "Lio/ktor/server/response/ResponseHeaders;", "getHeaders", "()Lio/ktor/server/response/ResponseHeaders;", "getProtocol", "()Lio/netty/handler/codec/http/HttpVersion;", "responseHeaders", "Lio/netty/handler/codec/http/DefaultHttpHeaders;", "responseStatus", "Lio/netty/handler/codec/http/HttpResponseStatus;", "respondUpgrade", "", "upgrade", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "(Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "responseMessage", "", "chunked", "", "last", "data", "", "setChunked", "message", "Lio/netty/handler/codec/http/HttpResponse;", "setStatus", "statusCode", "Lio/ktor/http/HttpStatusCode;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp1ApplicationResponse.kt */
public final class NettyHttp1ApplicationResponse extends NettyApplicationResponse {
    private final ResponseHeaders headers = new NettyHttp1ApplicationResponse$headers$1(this);
    private final HttpVersion protocol;
    /* access modifiers changed from: private */
    public final DefaultHttpHeaders responseHeaders = new DefaultHttpHeaders();
    private HttpResponseStatus responseStatus;

    public final HttpVersion getProtocol() {
        return this.protocol;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyHttp1ApplicationResponse(NettyApplicationCall nettyApplicationCall, ChannelHandlerContext channelHandlerContext, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, HttpVersion httpVersion) {
        super(nettyApplicationCall, channelHandlerContext, coroutineContext, coroutineContext2);
        Intrinsics.checkNotNullParameter(nettyApplicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(coroutineContext, "engineContext");
        Intrinsics.checkNotNullParameter(coroutineContext2, "userContext");
        Intrinsics.checkNotNullParameter(httpVersion, "protocol");
        this.protocol = httpVersion;
        HttpResponseStatus httpResponseStatus = HttpResponseStatus.OK;
        Intrinsics.checkNotNullExpressionValue(httpResponseStatus, "OK");
        this.responseStatus = httpResponseStatus;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        if (r2 == null) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStatus(io.ktor.http.HttpStatusCode r5) {
        /*
            r4 = this;
            java.lang.String r0 = "statusCode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = r5.getValue()
            r1 = 1
            r2 = 0
            if (r1 > r0) goto L_0x0022
            io.ktor.server.netty.NettyApplicationResponse$Companion r1 = io.ktor.server.netty.NettyApplicationResponse.Companion
            io.netty.handler.codec.http.HttpResponseStatus[] r1 = r1.getResponseStatusCache()
            int r1 = kotlin.collections.ArraysKt.getLastIndex((T[]) r1)
            if (r0 > r1) goto L_0x0022
            io.ktor.server.netty.NettyApplicationResponse$Companion r1 = io.ktor.server.netty.NettyApplicationResponse.Companion
            io.netty.handler.codec.http.HttpResponseStatus[] r1 = r1.getResponseStatusCache()
            r0 = r1[r0]
            goto L_0x0023
        L_0x0022:
            r0 = r2
        L_0x0023:
            if (r0 == 0) goto L_0x0036
            java.lang.String r1 = r0.reasonPhrase()
            java.lang.String r3 = r5.getDescription()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 == 0) goto L_0x0034
            r2 = r0
        L_0x0034:
            if (r2 != 0) goto L_0x0043
        L_0x0036:
            io.netty.handler.codec.http.HttpResponseStatus r2 = new io.netty.handler.codec.http.HttpResponseStatus
            int r0 = r5.getValue()
            java.lang.String r5 = r5.getDescription()
            r2.<init>(r0, r5)
        L_0x0043:
            r4.responseStatus = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http1.NettyHttp1ApplicationResponse.setStatus(io.ktor.http.HttpStatusCode):void");
    }

    public ResponseHeaders getHeaders() {
        return this.headers;
    }

    /* access modifiers changed from: protected */
    public Object responseMessage(boolean z, boolean z2) {
        DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(this.protocol, this.responseStatus, (HttpHeaders) this.responseHeaders);
        if (z) {
            setChunked(defaultHttpResponse);
        }
        return defaultHttpResponse;
    }

    /* access modifiers changed from: protected */
    public Object responseMessage(boolean z, byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(this.protocol, this.responseStatus, Unpooled.wrappedBuffer(bArr), (HttpHeaders) this.responseHeaders, (HttpHeaders) EmptyHttpHeaders.INSTANCE);
        if (z) {
            setChunked(defaultFullHttpResponse);
        }
        return defaultFullHttpResponse;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00fd A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x010c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object respondUpgrade(io.ktor.http.content.OutgoingContent.ProtocolUpgrade r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof io.ktor.server.netty.http1.NettyHttp1ApplicationResponse$respondUpgrade$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.server.netty.http1.NettyHttp1ApplicationResponse$respondUpgrade$1 r0 = (io.ktor.server.netty.http1.NettyHttp1ApplicationResponse$respondUpgrade$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.http1.NettyHttp1ApplicationResponse$respondUpgrade$1 r0 = new io.ktor.server.netty.http1.NettyHttp1ApplicationResponse$respondUpgrade$1
            r0.<init>(r13, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 3
            r9 = 2
            r10 = 0
            r2 = 1
            if (r1 == 0) goto L_0x0060
            if (r1 == r2) goto L_0x004b
            if (r1 == r9) goto L_0x003e
            if (r1 != r8) goto L_0x0036
            java.lang.Object r14 = r0.L$0
            io.ktor.server.netty.http1.NettyHttp1ApplicationResponse r14 = (io.ktor.server.netty.http1.NettyHttp1ApplicationResponse) r14
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x010e
        L_0x0036:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L_0x003e:
            java.lang.Object r14 = r0.L$1
            kotlinx.coroutines.Job r14 = (kotlinx.coroutines.Job) r14
            java.lang.Object r1 = r0.L$0
            io.ktor.server.netty.http1.NettyHttp1ApplicationResponse r1 = (io.ktor.server.netty.http1.NettyHttp1ApplicationResponse) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0100
        L_0x004b:
            java.lang.Object r14 = r0.L$3
            io.ktor.utils.io.ByteChannel r14 = (io.ktor.utils.io.ByteChannel) r14
            java.lang.Object r1 = r0.L$2
            io.ktor.utils.io.ByteReadChannel r1 = (io.ktor.utils.io.ByteReadChannel) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.server.netty.cio.RequestBodyHandler r2 = (io.ktor.server.netty.cio.RequestBodyHandler) r2
            java.lang.Object r3 = r0.L$0
            io.ktor.server.netty.http1.NettyHttp1ApplicationResponse r3 = (io.ktor.server.netty.http1.NettyHttp1ApplicationResponse) r3
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00d2
        L_0x0060:
            kotlin.ResultKt.throwOnFailure(r15)
            io.netty.channel.ChannelHandlerContext r15 = r13.getContext()
            io.netty.channel.Channel r1 = r15.channel()
            kotlin.coroutines.CoroutineContext r3 = r13.getUserContext()
            io.ktor.server.netty.NettyDispatcher$CurrentContext r4 = new io.ktor.server.netty.NettyDispatcher$CurrentContext
            r4.<init>(r15)
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            kotlin.coroutines.CoroutineContext r5 = r3.plus(r4)
            io.netty.channel.ChannelPipeline r15 = r15.pipeline()
            java.lang.Class<io.ktor.server.netty.cio.RequestBodyHandler> r3 = io.ktor.server.netty.cio.RequestBodyHandler.class
            io.netty.channel.ChannelHandler r15 = r15.get(r3)
            io.ktor.server.netty.cio.RequestBodyHandler r15 = (io.ktor.server.netty.cio.RequestBodyHandler) r15
            io.ktor.utils.io.ByteReadChannel r11 = r15.upgrade()
            r3 = 0
            io.ktor.utils.io.ByteChannel r12 = io.ktor.utils.io.ByteChannelKt.ByteChannel$default(r3, r2, r10)
            r4 = r12
            io.ktor.utils.io.ByteReadChannel r4 = (io.ktor.utils.io.ByteReadChannel) r4
            r13.sendResponse$ktor_server_netty(r3, r4)
            io.netty.channel.ChannelPipeline r1 = r1.pipeline()
            java.lang.Class<io.ktor.server.netty.http1.NettyHttp1Handler> r4 = io.ktor.server.netty.http1.NettyHttp1Handler.class
            io.netty.channel.ChannelHandler r4 = r1.get(r4)
            if (r4 == 0) goto L_0x011c
            java.lang.Class<io.ktor.server.netty.http1.NettyHttp1Handler> r4 = io.ktor.server.netty.http1.NettyHttp1Handler.class
            r1.remove(r4)
            io.netty.channel.ChannelHandler[] r4 = new io.netty.channel.ChannelHandler[r2]
            io.ktor.server.netty.NettyDirectDecoder r6 = new io.ktor.server.netty.NettyDirectDecoder
            r6.<init>()
            r4[r3] = r6
            r1.addFirst(r4)
            r3 = r12
            io.ktor.utils.io.ByteWriteChannel r3 = (io.ktor.utils.io.ByteWriteChannel) r3
            kotlin.coroutines.CoroutineContext r4 = r13.getEngineContext()
            r0.L$0 = r13
            r0.L$1 = r15
            r0.L$2 = r11
            r0.L$3 = r12
            r0.label = r2
            r1 = r14
            r2 = r11
            r6 = r0
            java.lang.Object r14 = r1.upgrade(r2, r3, r4, r5, r6)
            if (r14 != r7) goto L_0x00cd
            return r7
        L_0x00cd:
            r3 = r13
            r2 = r15
            r1 = r11
            r15 = r14
            r14 = r12
        L_0x00d2:
            kotlinx.coroutines.Job r15 = (kotlinx.coroutines.Job) r15
            io.ktor.server.netty.http1.NettyHttp1ApplicationResponse$respondUpgrade$3 r4 = new io.ktor.server.netty.http1.NettyHttp1ApplicationResponse$respondUpgrade$3
            r4.<init>(r14, r2, r1)
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            r15.invokeOnCompletion(r4)
            io.ktor.server.application.ApplicationCall r14 = r3.getCall()
            java.lang.String r1 = "null cannot be cast to non-null type io.ktor.server.netty.NettyApplicationCall"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r1)
            io.ktor.server.netty.NettyApplicationCall r14 = (io.ktor.server.netty.NettyApplicationCall) r14
            kotlinx.coroutines.Job r14 = r14.getResponseWriteJob()
            r0.L$0 = r3
            r0.L$1 = r15
            r0.L$2 = r10
            r0.L$3 = r10
            r0.label = r9
            java.lang.Object r14 = r14.join(r0)
            if (r14 != r7) goto L_0x00fe
            return r7
        L_0x00fe:
            r14 = r15
            r1 = r3
        L_0x0100:
            r0.L$0 = r1
            r0.L$1 = r10
            r0.label = r8
            java.lang.Object r14 = r14.join(r0)
            if (r14 != r7) goto L_0x010d
            return r7
        L_0x010d:
            r14 = r1
        L_0x010e:
            io.netty.channel.ChannelHandlerContext r14 = r14.getContext()
            io.netty.channel.Channel r14 = r14.channel()
            r14.close()
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x011c:
            r13.cancel()
            java.util.concurrent.CancellationException r14 = new java.util.concurrent.CancellationException
            java.lang.String r15 = "HTTP upgrade has been cancelled"
            r14.<init>(r15)
            r15 = r14
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            r12.cancel(r15)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http1.NettyHttp1ApplicationResponse.respondUpgrade(io.ktor.http.content.OutgoingContent$ProtocolUpgrade, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void setChunked(HttpResponse httpResponse) {
        if (httpResponse.status().code() != HttpStatusCode.Companion.getSwitchingProtocols().getValue()) {
            HttpUtil.setTransferEncodingChunked(httpResponse, true);
        }
    }
}
