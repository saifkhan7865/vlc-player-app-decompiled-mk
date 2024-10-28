package io.ktor.server.netty.http2;

import io.ktor.http.Headers;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.netty.NettyApplicationRequest;
import io.ktor.server.request.RequestCookies;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import io.netty.handler.codec.http2.Http2DataFrame;
import io.netty.handler.codec.http2.Http2Headers;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010&\u001a\u00020'H\u0014R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8VX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010 \u001a\u00020!X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006("}, d2 = {"Lio/ktor/server/netty/http2/NettyHttp2ApplicationRequest;", "Lio/ktor/server/netty/NettyApplicationRequest;", "call", "Lio/ktor/server/application/ApplicationCall;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "context", "Lio/netty/channel/ChannelHandlerContext;", "nettyHeaders", "Lio/netty/handler/codec/http2/Http2Headers;", "contentByteChannel", "Lio/ktor/utils/io/ByteChannel;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/CoroutineContext;Lio/netty/channel/ChannelHandlerContext;Lio/netty/handler/codec/http2/Http2Headers;Lio/ktor/utils/io/ByteChannel;)V", "contentActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lio/netty/handler/codec/http2/Http2DataFrame;", "getContentActor$annotations", "()V", "getContentActor", "()Lkotlinx/coroutines/channels/SendChannel;", "getContentByteChannel", "()Lio/ktor/utils/io/ByteChannel;", "cookies", "Lio/ktor/server/request/RequestCookies;", "getCookies", "()Lio/ktor/server/request/RequestCookies;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers$delegate", "Lkotlin/Lazy;", "local", "Lio/ktor/server/netty/http2/Http2LocalConnectionPoint;", "getLocal", "()Lio/ktor/server/netty/http2/Http2LocalConnectionPoint;", "getNettyHeaders", "()Lio/netty/handler/codec/http2/Http2Headers;", "newDecoder", "Lio/netty/handler/codec/http/multipart/HttpPostMultipartRequestDecoder;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp2ApplicationRequest.kt */
public final class NettyHttp2ApplicationRequest extends NettyApplicationRequest {
    private final SendChannel<Http2DataFrame> contentActor;
    private final ByteChannel contentByteChannel;
    private final RequestCookies cookies;
    private final Lazy headers$delegate;
    private final Http2LocalConnectionPoint local;
    private final Http2Headers nettyHeaders;

    public static /* synthetic */ void getContentActor$annotations() {
    }

    public final Http2Headers getNettyHeaders() {
        return this.nettyHeaders;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NettyHttp2ApplicationRequest(ApplicationCall applicationCall, CoroutineContext coroutineContext, ChannelHandlerContext channelHandlerContext, Http2Headers http2Headers, ByteChannel byteChannel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(applicationCall, coroutineContext, channelHandlerContext, http2Headers, (i & 16) != 0 ? ByteChannelKt.ByteChannel$default(false, 1, (Object) null) : byteChannel);
    }

    public final ByteChannel getContentByteChannel() {
        return this.contentByteChannel;
    }

    /* JADX WARNING: type inference failed for: r11v2, types: [java.net.SocketAddress] */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0026, code lost:
        r0 = r0.toString();
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NettyHttp2ApplicationRequest(io.ktor.server.application.ApplicationCall r9, kotlin.coroutines.CoroutineContext r10, io.netty.channel.ChannelHandlerContext r11, io.netty.handler.codec.http2.Http2Headers r12, io.ktor.utils.io.ByteChannel r13) {
        /*
            r8 = this;
            java.lang.String r0 = "call"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "coroutineContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "nettyHeaders"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "contentByteChannel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            r5 = r13
            io.ktor.utils.io.ByteReadChannel r5 = (io.ktor.utils.io.ByteReadChannel) r5
            java.lang.String r0 = ":path"
            java.lang.Object r0 = r12.get(r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x002c
            java.lang.String r0 = r0.toString()
            if (r0 != 0) goto L_0x002e
        L_0x002c:
            java.lang.String r0 = "/"
        L_0x002e:
            r6 = r0
            r7 = 1
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r8.nettyHeaders = r12
            r8.contentByteChannel = r13
            io.ktor.server.netty.http2.NettyHttp2ApplicationRequest$headers$2 r9 = new io.ktor.server.netty.http2.NettyHttp2ApplicationRequest$headers$2
            r9.<init>(r8)
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            kotlin.Lazy r9 = kotlin.LazyKt.lazy(r9)
            r8.headers$delegate = r9
            r0 = r8
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getUnconfined()
            r1 = r9
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            io.ktor.server.netty.http2.NettyHttp2ApplicationRequest$contentActor$1 r9 = new io.ktor.server.netty.http2.NettyHttp2ApplicationRequest$contentActor$1
            r10 = 0
            r9.<init>(r8, r10)
            r5 = r9
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 12
            r7 = 0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            r4 = 0
            kotlinx.coroutines.channels.SendChannel r9 = kotlinx.coroutines.channels.ActorKt.actor$default(r0, r1, r2, r3, r4, r5, r6, r7)
            r8.contentActor = r9
            io.ktor.server.netty.http2.Http2LocalConnectionPoint r9 = new io.ktor.server.netty.http2.Http2LocalConnectionPoint
            io.netty.channel.Channel r13 = r11.channel()
            java.net.SocketAddress r13 = r13.localAddress()
            boolean r0 = r13 instanceof java.net.InetSocketAddress
            if (r0 == 0) goto L_0x007a
            java.net.InetSocketAddress r13 = (java.net.InetSocketAddress) r13
            goto L_0x007b
        L_0x007a:
            r13 = r10
        L_0x007b:
            io.netty.channel.Channel r11 = r11.channel()
            java.net.SocketAddress r11 = r11.remoteAddress()
            boolean r0 = r11 instanceof java.net.InetSocketAddress
            if (r0 == 0) goto L_0x008a
            r10 = r11
            java.net.InetSocketAddress r10 = (java.net.InetSocketAddress) r10
        L_0x008a:
            r9.<init>(r12, r13, r10)
            r8.local = r9
            io.ktor.server.netty.NettyApplicationRequestCookies r9 = new io.ktor.server.netty.NettyApplicationRequestCookies
            r10 = r8
            io.ktor.server.request.ApplicationRequest r10 = (io.ktor.server.request.ApplicationRequest) r10
            r9.<init>(r10)
            io.ktor.server.request.RequestCookies r9 = (io.ktor.server.request.RequestCookies) r9
            r8.cookies = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.NettyHttp2ApplicationRequest.<init>(io.ktor.server.application.ApplicationCall, kotlin.coroutines.CoroutineContext, io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http2.Http2Headers, io.ktor.utils.io.ByteChannel):void");
    }

    public Headers getHeaders() {
        return (Headers) this.headers$delegate.getValue();
    }

    public final SendChannel<Http2DataFrame> getContentActor() {
        return this.contentActor;
    }

    public Http2LocalConnectionPoint getLocal() {
        return this.local;
    }

    public RequestCookies getCookies() {
        return this.cookies;
    }

    /* access modifiers changed from: protected */
    public HttpPostMultipartRequestDecoder newDecoder() {
        DefaultHttpHeaders defaultHttpHeaders = new DefaultHttpHeaders(false);
        for (Map.Entry next : this.nettyHeaders) {
            Intrinsics.checkNotNullExpressionValue(next, "nettyHeaders");
            defaultHttpHeaders.add((CharSequence) next.getKey(), (Object) (CharSequence) next.getValue());
        }
        return new HttpPostMultipartRequestDecoder(new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, getUri(), (HttpHeaders) defaultHttpHeaders));
    }
}
