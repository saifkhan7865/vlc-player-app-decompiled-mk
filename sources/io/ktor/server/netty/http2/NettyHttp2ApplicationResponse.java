package io.ktor.server.netty.http2;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.server.netty.NettyApplicationResponse;
import io.ktor.server.response.ResponseHeaders;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import io.ktor.util.TextKt;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.DefaultHttp2HeadersFrame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001.B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0010¢\u0006\u0002\b\u0018J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017J\u0019\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0018\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0014J\u0018\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0014J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-H\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lio/ktor/server/netty/http2/NettyHttp2ApplicationResponse;", "Lio/ktor/server/netty/NettyApplicationResponse;", "call", "Lio/ktor/server/netty/NettyApplicationCall;", "handler", "Lio/ktor/server/netty/http2/NettyHttp2Handler;", "context", "Lio/netty/channel/ChannelHandlerContext;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/ktor/server/netty/http2/NettyHttp2Handler;Lio/netty/channel/ChannelHandlerContext;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)V", "getHandler", "()Lio/ktor/server/netty/http2/NettyHttp2Handler;", "headers", "Lio/ktor/server/response/ResponseHeaders;", "getHeaders", "()Lio/ktor/server/response/ResponseHeaders;", "responseHeaders", "Lio/netty/handler/codec/http2/DefaultHttp2Headers;", "responseTrailers", "trailers", "prepareTrailerMessage", "", "prepareTrailerMessage$ktor_server_netty", "push", "", "builder", "Lio/ktor/server/response/ResponsePushBuilder;", "respondOutgoingContent", "content", "Lio/ktor/http/content/OutgoingContent;", "(Lio/ktor/http/content/OutgoingContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondUpgrade", "upgrade", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "(Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "responseMessage", "chunked", "", "last", "data", "", "setStatus", "statusCode", "Lio/ktor/http/HttpStatusCode;", "Http2ResponseHeaders", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp2ApplicationResponse.kt */
public final class NettyHttp2ApplicationResponse extends NettyApplicationResponse {
    private final NettyHttp2Handler handler;
    private final ResponseHeaders headers;
    private final DefaultHttp2Headers responseHeaders;
    private final DefaultHttp2Headers responseTrailers;
    /* access modifiers changed from: private */
    public final ResponseHeaders trailers;

    public final NettyHttp2Handler getHandler() {
        return this.handler;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyHttp2ApplicationResponse(NettyApplicationCall nettyApplicationCall, NettyHttp2Handler nettyHttp2Handler, ChannelHandlerContext channelHandlerContext, CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        super(nettyApplicationCall, channelHandlerContext, coroutineContext, coroutineContext2);
        Intrinsics.checkNotNullParameter(nettyApplicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(nettyHttp2Handler, "handler");
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(coroutineContext, "engineContext");
        Intrinsics.checkNotNullParameter(coroutineContext2, "userContext");
        this.handler = nettyHttp2Handler;
        DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers();
        defaultHttp2Headers.status(String.valueOf(HttpStatusCode.Companion.getOK().getValue()));
        this.responseHeaders = defaultHttp2Headers;
        DefaultHttp2Headers defaultHttp2Headers2 = new DefaultHttp2Headers();
        this.responseTrailers = defaultHttp2Headers2;
        this.headers = new Http2ResponseHeaders(defaultHttp2Headers);
        this.trailers = new Http2ResponseHeaders(defaultHttp2Headers2);
    }

    /* access modifiers changed from: protected */
    public void setStatus(HttpStatusCode httpStatusCode) {
        Intrinsics.checkNotNullParameter(httpStatusCode, "statusCode");
        this.responseHeaders.status(String.valueOf(httpStatusCode.getValue()));
    }

    /* access modifiers changed from: protected */
    public Object responseMessage(boolean z, byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        return responseMessage(false, bArr.length == 0);
    }

    /* access modifiers changed from: protected */
    public Object responseMessage(boolean z, boolean z2) {
        this.responseHeaders.remove("transfer-encoding");
        return new DefaultHttp2HeadersFrame(this.responseHeaders, z2);
    }

    public Object prepareTrailerMessage$ktor_server_netty() {
        if (this.responseTrailers.isEmpty()) {
            return null;
        }
        return new DefaultHttp2HeadersFrame(this.responseTrailers, true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object respondOutgoingContent(io.ktor.http.content.OutgoingContent r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.server.netty.http2.NettyHttp2ApplicationResponse$respondOutgoingContent$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.netty.http2.NettyHttp2ApplicationResponse$respondOutgoingContent$1 r0 = (io.ktor.server.netty.http2.NettyHttp2ApplicationResponse$respondOutgoingContent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.http2.NettyHttp2ApplicationResponse$respondOutgoingContent$1 r0 = new io.ktor.server.netty.http2.NettyHttp2ApplicationResponse$respondOutgoingContent$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            io.ktor.http.content.OutgoingContent r5 = (io.ktor.http.content.OutgoingContent) r5
            java.lang.Object r0 = r0.L$0
            io.ktor.server.netty.http2.NettyHttp2ApplicationResponse r0 = (io.ktor.server.netty.http2.NettyHttp2ApplicationResponse) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004b
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = super.respondOutgoingContent(r5, r0)
            if (r6 != r1) goto L_0x004a
            return r1
        L_0x004a:
            r0 = r4
        L_0x004b:
            io.ktor.http.Headers r5 = r5.trailers()
            if (r5 == 0) goto L_0x005b
            io.ktor.server.netty.http2.NettyHttp2ApplicationResponse$respondOutgoingContent$2$1 r6 = new io.ktor.server.netty.http2.NettyHttp2ApplicationResponse$respondOutgoingContent$2$1
            r6.<init>(r0)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r5.forEach(r6)
        L_0x005b:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.NettyHttp2ApplicationResponse.respondOutgoingContent(io.ktor.http.content.OutgoingContent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Object respondUpgrade(OutgoingContent.ProtocolUpgrade protocolUpgrade, Continuation<? super Unit> continuation) {
        throw new UnsupportedOperationException("HTTP/2 doesn't support upgrade");
    }

    public ResponseHeaders getHeaders() {
        return this.headers;
    }

    @UseHttp2Push
    public void push(ResponsePushBuilder responsePushBuilder) {
        Intrinsics.checkNotNullParameter(responsePushBuilder, "builder");
        getContext().executor().execute(new NettyHttp2ApplicationResponse$$ExternalSyntheticLambda0(this, responsePushBuilder));
    }

    /* access modifiers changed from: private */
    public static final void push$lambda$2(NettyHttp2ApplicationResponse nettyHttp2ApplicationResponse, ResponsePushBuilder responsePushBuilder) {
        Intrinsics.checkNotNullParameter(nettyHttp2ApplicationResponse, "this$0");
        Intrinsics.checkNotNullParameter(responsePushBuilder, "$builder");
        nettyHttp2ApplicationResponse.handler.startHttp2PushPromise$ktor_server_netty(nettyHttp2ApplicationResponse.getContext(), responsePushBuilder);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0014J\u0013\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0014J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0007\u001a\u00020\bH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/server/netty/http2/NettyHttp2ApplicationResponse$Http2ResponseHeaders;", "Lio/ktor/server/response/ResponseHeaders;", "underlying", "Lio/netty/handler/codec/http2/DefaultHttp2Headers;", "(Lio/netty/handler/codec/http2/DefaultHttp2Headers;)V", "engineAppendHeader", "", "name", "", "value", "get", "getEngineHeaderNames", "", "getEngineHeaderValues", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyHttp2ApplicationResponse.kt */
    public static final class Http2ResponseHeaders extends ResponseHeaders {
        private final DefaultHttp2Headers underlying;

        public Http2ResponseHeaders(DefaultHttp2Headers defaultHttp2Headers) {
            Intrinsics.checkNotNullParameter(defaultHttp2Headers, "underlying");
            this.underlying = defaultHttp2Headers;
        }

        /* access modifiers changed from: protected */
        public void engineAppendHeader(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(str2, "value");
            this.underlying.add(TextKt.toLowerCasePreservingASCIIRules(str), str2);
        }

        public String get(String str) {
            CharSequence charSequence;
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            if (!StringsKt.startsWith$default((CharSequence) str, (char) AbstractJsonLexerKt.COLON, false, 2, (Object) null) && (charSequence = (CharSequence) this.underlying.get(str)) != null) {
                return charSequence.toString();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public List<String> getEngineHeaderNames() {
            Set names = this.underlying.names();
            Intrinsics.checkNotNullExpressionValue(names, "underlying.names()");
            Collection arrayList = new ArrayList();
            for (Object next : names) {
                CharSequence charSequence = (CharSequence) next;
                Intrinsics.checkNotNullExpressionValue(charSequence, "it");
                if (!StringsKt.startsWith$default(charSequence, (char) AbstractJsonLexerKt.COLON, false, 2, (Object) null)) {
                    arrayList.add(next);
                }
            }
            Iterable<CharSequence> iterable = (List) arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (CharSequence obj : iterable) {
                arrayList2.add(obj.toString());
            }
            return (List) arrayList2;
        }

        /* access modifiers changed from: protected */
        public List<String> getEngineHeaderValues(String str) {
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            if (StringsKt.startsWith$default((CharSequence) str, (char) AbstractJsonLexerKt.COLON, false, 2, (Object) null)) {
                return CollectionsKt.emptyList();
            }
            List all = this.underlying.getAll(str);
            Intrinsics.checkNotNullExpressionValue(all, "underlying.getAll(name)");
            Iterable<CharSequence> iterable = all;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (CharSequence obj : iterable) {
                arrayList.add(obj.toString());
            }
            return (List) arrayList;
        }
    }
}
