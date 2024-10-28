package io.ktor.server.netty;

import androidx.core.app.NotificationCompat;
import io.ktor.http.Parameters;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.engine.BaseApplicationRequest;
import io.ktor.server.request.RequestCookies;
import io.ktor.utils.io.ByteReadChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020'H$J\b\u0010(\u001a\u00020\nH\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001e\u001a\u00020\u001b8VX\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001f\u0010\u001dR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006)"}, d2 = {"Lio/ktor/server/netty/NettyApplicationRequest;", "Lio/ktor/server/engine/BaseApplicationRequest;", "Lkotlinx/coroutines/CoroutineScope;", "call", "Lio/ktor/server/application/ApplicationCall;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "context", "Lio/netty/channel/ChannelHandlerContext;", "requestBodyChannel", "Lio/ktor/utils/io/ByteReadChannel;", "uri", "", "keepAlive", "", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/CoroutineContext;Lio/netty/channel/ChannelHandlerContext;Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/String;Z)V", "getContext", "()Lio/netty/channel/ChannelHandlerContext;", "cookies", "Lio/ktor/server/request/RequestCookies;", "getCookies", "()Lio/ktor/server/request/RequestCookies;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getKeepAlive$ktor_server_netty", "()Z", "queryParameters", "Lio/ktor/http/Parameters;", "getQueryParameters", "()Lio/ktor/http/Parameters;", "rawQueryParameters", "getRawQueryParameters", "rawQueryParameters$delegate", "Lkotlin/Lazy;", "getUri", "()Ljava/lang/String;", "close", "", "newDecoder", "Lio/netty/handler/codec/http/multipart/HttpPostMultipartRequestDecoder;", "receiveChannel", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationRequest.kt */
public abstract class NettyApplicationRequest extends BaseApplicationRequest implements CoroutineScope {
    private final ChannelHandlerContext context;
    private final RequestCookies cookies = new NettyApplicationRequestCookies(this);
    private final CoroutineContext coroutineContext;
    private final boolean keepAlive;
    private final Parameters queryParameters = new NettyApplicationRequest$queryParameters$1(this);
    private final Lazy rawQueryParameters$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new NettyApplicationRequest$rawQueryParameters$2(this));
    private final ByteReadChannel requestBodyChannel;
    private final String uri;

    public final void close() {
    }

    /* access modifiers changed from: protected */
    public abstract HttpPostMultipartRequestDecoder newDecoder();

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final ChannelHandlerContext getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public final String getUri() {
        return this.uri;
    }

    public final boolean getKeepAlive$ktor_server_netty() {
        return this.keepAlive;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyApplicationRequest(ApplicationCall applicationCall, CoroutineContext coroutineContext2, ChannelHandlerContext channelHandlerContext, ByteReadChannel byteReadChannel, String str, boolean z) {
        super(applicationCall);
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(byteReadChannel, "requestBodyChannel");
        Intrinsics.checkNotNullParameter(str, Constants.KEY_URI);
        this.coroutineContext = coroutineContext2;
        this.context = channelHandlerContext;
        this.requestBodyChannel = byteReadChannel;
        this.uri = str;
        this.keepAlive = z;
    }

    public final Parameters getQueryParameters() {
        return this.queryParameters;
    }

    public Parameters getRawQueryParameters() {
        return (Parameters) this.rawQueryParameters$delegate.getValue();
    }

    public RequestCookies getCookies() {
        return this.cookies;
    }

    public ByteReadChannel receiveChannel() {
        return this.requestBodyChannel;
    }
}
