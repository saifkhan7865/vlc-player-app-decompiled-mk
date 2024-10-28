package io.ktor.server.netty;

import androidx.core.app.NotificationCompat;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationKt;
import io.ktor.server.engine.EnginePipeline;
import io.ktor.server.netty.NettyDispatcher;
import io.ktor.server.netty.http1.NettyHttp1ApplicationCall;
import io.ktor.utils.io.ByteReadChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DecoderResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0001\u0019B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0016H\u0002J\u0019\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018R\u0014\u0010\b\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/server/netty/NettyApplicationCallHandler;", "Lio/netty/channel/ChannelInboundHandlerAdapter;", "Lkotlinx/coroutines/CoroutineScope;", "userCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "enginePipeline", "Lio/ktor/server/engine/EnginePipeline;", "(Lkotlin/coroutines/CoroutineContext;Lio/ktor/server/engine/EnginePipeline;)V", "coroutineContext", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "channelRead", "", "ctx", "Lio/netty/channel/ChannelHandlerContext;", "msg", "", "handleRequest", "context", "call", "Lio/ktor/server/application/ApplicationCall;", "logCause", "Lio/ktor/server/netty/http1/NettyHttp1ApplicationCall;", "respondError400BadRequest", "(Lio/ktor/server/netty/http1/NettyHttp1ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationCallHandler.kt */
public final class NettyApplicationCallHandler extends ChannelInboundHandlerAdapter implements CoroutineScope {
    /* access modifiers changed from: private */
    public static final CoroutineName CallHandlerCoroutineName = new CoroutineName("call-handler");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final CoroutineContext coroutineContext;
    /* access modifiers changed from: private */
    public final EnginePipeline enginePipeline;

    public NettyApplicationCallHandler(CoroutineContext coroutineContext2, EnginePipeline enginePipeline2) {
        Intrinsics.checkNotNullParameter(coroutineContext2, "userCoroutineContext");
        Intrinsics.checkNotNullParameter(enginePipeline2, "enginePipeline");
        this.enginePipeline = enginePipeline2;
        this.coroutineContext = coroutineContext2;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "ctx");
        Intrinsics.checkNotNullParameter(obj, NotificationCompat.CATEGORY_MESSAGE);
        if (obj instanceof ApplicationCall) {
            handleRequest(channelHandlerContext, (ApplicationCall) obj);
        } else {
            channelHandlerContext.fireChannelRead(obj);
        }
    }

    private final void handleRequest(ChannelHandlerContext channelHandlerContext, ApplicationCall applicationCall) {
        BuildersKt.launch(this, CallHandlerCoroutineName.plus(new NettyDispatcher.CurrentContext(channelHandlerContext)), CoroutineStart.UNDISPATCHED, new NettyApplicationCallHandler$handleRequest$1(applicationCall, this, (Continuation<? super NettyApplicationCallHandler$handleRequest$1>) null));
    }

    /* access modifiers changed from: private */
    public final Object respondError400BadRequest(NettyHttp1ApplicationCall nettyHttp1ApplicationCall, Continuation<? super Unit> continuation) {
        logCause(nettyHttp1ApplicationCall);
        nettyHttp1ApplicationCall.getResponse().status(HttpStatusCode.Companion.getBadRequest());
        nettyHttp1ApplicationCall.getResponse().getHeaders().append(HttpHeaders.INSTANCE.getContentLength(), Constants.GROUP_VIDEOS_FOLDER, false);
        nettyHttp1ApplicationCall.getResponse().getHeaders().append(HttpHeaders.INSTANCE.getConnection(), "close", false);
        nettyHttp1ApplicationCall.getResponse().sendResponse$ktor_server_netty(false, ByteReadChannel.Companion.getEmpty());
        Object finish$ktor_server_netty = nettyHttp1ApplicationCall.finish$ktor_server_netty(continuation);
        return finish$ktor_server_netty == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? finish$ktor_server_netty : Unit.INSTANCE;
    }

    private final void logCause(NettyHttp1ApplicationCall nettyHttp1ApplicationCall) {
        if (ApplicationKt.getLog(nettyHttp1ApplicationCall.getApplication()).isTraceEnabled()) {
            DecoderResult decoderResult = nettyHttp1ApplicationCall.getRequest().getHttpRequest().decoderResult();
            Throwable cause = decoderResult != null ? decoderResult.cause() : null;
            if (cause != null) {
                ApplicationKt.getLog(nettyHttp1ApplicationCall.getApplication()).trace("Failed to decode request", cause);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/netty/NettyApplicationCallHandler$Companion;", "", "()V", "CallHandlerCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "getCallHandlerCoroutineName$ktor_server_netty", "()Lkotlinx/coroutines/CoroutineName;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyApplicationCallHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CoroutineName getCallHandlerCoroutineName$ktor_server_netty() {
            return NettyApplicationCallHandler.CallHandlerCoroutineName;
        }
    }
}
