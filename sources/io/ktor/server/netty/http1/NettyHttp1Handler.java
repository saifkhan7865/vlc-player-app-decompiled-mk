package io.ktor.server.netty.http1;

import io.ktor.server.application.ApplicationKt;
import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.EnginePipeline;
import io.ktor.server.netty.NettyApplicationCallHandler;
import io.ktor.server.netty.NettyHttpHandlerState;
import io.ktor.server.netty.cio.NettyHttpResponsePipeline;
import io.ktor.server.netty.cio.RequestBodyHandler;
import io.ktor.util.cio.ChannelIOException;
import io.ktor.utils.io.ByteReadChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.concurrent.EventExecutorGroup;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010!\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0016J\u0012\u0010$\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0018\u0010%\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'H\u0016J\u0018\u0010(\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020)H\u0002J\u0018\u0010*\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020)H\u0002J\u0018\u0010,\u001a\u00020-2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020)H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lio/ktor/server/netty/http1/NettyHttp1Handler;", "Lio/netty/channel/ChannelInboundHandlerAdapter;", "Lkotlinx/coroutines/CoroutineScope;", "enginePipeline", "Lio/ktor/server/engine/EnginePipeline;", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "callEventGroup", "Lio/netty/util/concurrent/EventExecutorGroup;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "runningLimit", "", "(Lio/ktor/server/engine/EnginePipeline;Lio/ktor/server/engine/ApplicationEngineEnvironment;Lio/netty/util/concurrent/EventExecutorGroup;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;I)V", "coroutineContext", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "handlerJob", "Lkotlinx/coroutines/CompletableDeferred;", "", "responseWriter", "Lio/ktor/server/netty/cio/NettyHttpResponsePipeline;", "skipEmpty", "", "state", "Lio/ktor/server/netty/NettyHttpHandlerState;", "callReadIfNeeded", "", "context", "Lio/netty/channel/ChannelHandlerContext;", "channelActive", "channelInactive", "channelRead", "message", "", "channelReadComplete", "exceptionCaught", "cause", "", "handleRequest", "Lio/netty/handler/codec/http/HttpRequest;", "prepareCallFromRequest", "Lio/ktor/server/netty/http1/NettyHttp1ApplicationCall;", "prepareRequestContentChannel", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp1Handler.kt */
public final class NettyHttp1Handler extends ChannelInboundHandlerAdapter implements CoroutineScope {
    private final EventExecutorGroup callEventGroup;
    private final CoroutineContext engineContext;
    private final EnginePipeline enginePipeline;
    private final ApplicationEngineEnvironment environment;
    private final CompletableDeferred handlerJob = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
    private NettyHttpResponsePipeline responseWriter;
    private final int runningLimit;
    private boolean skipEmpty;
    private final NettyHttpHandlerState state;
    private final CoroutineContext userContext;

    public NettyHttp1Handler(EnginePipeline enginePipeline2, ApplicationEngineEnvironment applicationEngineEnvironment, EventExecutorGroup eventExecutorGroup, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, int i) {
        Intrinsics.checkNotNullParameter(enginePipeline2, "enginePipeline");
        Intrinsics.checkNotNullParameter(applicationEngineEnvironment, "environment");
        Intrinsics.checkNotNullParameter(eventExecutorGroup, "callEventGroup");
        Intrinsics.checkNotNullParameter(coroutineContext, "engineContext");
        Intrinsics.checkNotNullParameter(coroutineContext2, "userContext");
        this.enginePipeline = enginePipeline2;
        this.environment = applicationEngineEnvironment;
        this.callEventGroup = eventExecutorGroup;
        this.engineContext = coroutineContext;
        this.userContext = coroutineContext2;
        this.runningLimit = i;
        this.state = new NettyHttpHandlerState(i);
    }

    public CoroutineContext getCoroutineContext() {
        return this.handlerJob;
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        this.responseWriter = new NettyHttpResponsePipeline(channelHandlerContext, this.state, getCoroutineContext());
        channelHandlerContext.channel().config().setAutoRead(false);
        channelHandlerContext.channel().read();
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        pipeline.addLast(new RequestBodyHandler(channelHandlerContext));
        pipeline.addLast(this.callEventGroup, new NettyApplicationCallHandler(this.userContext, this.enginePipeline));
        channelHandlerContext.fireChannelActive();
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(obj, "message");
        boolean z = obj instanceof LastHttpContent;
        if (z) {
            NettyHttpHandlerState.isCurrentRequestFullyRead$FU$internal.compareAndSet(this.state, 0, 1);
        }
        if (obj instanceof HttpRequest) {
            if (!z) {
                NettyHttpHandlerState.isCurrentRequestFullyRead$FU$internal.compareAndSet(this.state, 1, 0);
            }
            NettyHttpHandlerState.isChannelReadCompleted$FU$internal.compareAndSet(this.state, 1, 0);
            NettyHttpHandlerState.activeRequests$FU$internal.incrementAndGet(this.state);
            handleRequest(channelHandlerContext, (HttpRequest) obj);
            callReadIfNeeded(channelHandlerContext);
            return;
        }
        if (z) {
            LastHttpContent lastHttpContent = (LastHttpContent) obj;
            if (!lastHttpContent.content().isReadable() && this.skipEmpty) {
                this.skipEmpty = false;
                lastHttpContent.release();
                callReadIfNeeded(channelHandlerContext);
                return;
            }
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        channelHandlerContext.pipeline().remove(NettyApplicationCallHandler.class);
        channelHandlerContext.fireChannelInactive();
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(th, "cause");
        if ((th instanceof IOException) || (th instanceof ChannelIOException)) {
            ApplicationKt.getLog(this.environment.getApplication()).debug("I/O operation failed", th);
            Job.DefaultImpls.cancel$default((Job) this.handlerJob, (CancellationException) null, 1, (Object) null);
        } else {
            this.handlerJob.completeExceptionally(th);
        }
        channelHandlerContext.close();
    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
        NettyHttpHandlerState.isChannelReadCompleted$FU$internal.compareAndSet(this.state, 0, 1);
        NettyHttpResponsePipeline nettyHttpResponsePipeline = this.responseWriter;
        if (nettyHttpResponsePipeline == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseWriter");
            nettyHttpResponsePipeline = null;
        }
        nettyHttpResponsePipeline.flushIfNeeded$ktor_server_netty();
        super.channelReadComplete(channelHandlerContext);
    }

    private final void handleRequest(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        NettyHttp1ApplicationCall prepareCallFromRequest = prepareCallFromRequest(channelHandlerContext, httpRequest);
        channelHandlerContext.fireChannelRead(prepareCallFromRequest);
        NettyHttpResponsePipeline nettyHttpResponsePipeline = this.responseWriter;
        if (nettyHttpResponsePipeline == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseWriter");
            nettyHttpResponsePipeline = null;
        }
        nettyHttpResponsePipeline.processResponse$ktor_server_netty(prepareCallFromRequest);
    }

    private final NettyHttp1ApplicationCall prepareCallFromRequest(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        ByteReadChannel byteReadChannel = null;
        if (!(httpRequest instanceof LastHttpContent) || ((LastHttpContent) httpRequest).content().isReadable()) {
            if (httpRequest.method() == HttpMethod.GET) {
                HttpMessage httpMessage = httpRequest;
                if (!HttpUtil.isContentLengthSet(httpMessage) && !HttpUtil.isTransferEncodingChunked(httpMessage)) {
                    this.skipEmpty = true;
                }
            }
            byteReadChannel = prepareRequestContentChannel(channelHandlerContext, httpRequest);
        }
        return new NettyHttp1ApplicationCall(this.environment.getApplication(), channelHandlerContext, httpRequest, byteReadChannel, this.engineContext, this.userContext);
    }

    private final ByteReadChannel prepareRequestContentChannel(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        RequestBodyHandler requestBodyHandler = (RequestBodyHandler) channelHandlerContext.pipeline().get(RequestBodyHandler.class);
        ByteReadChannel newChannel = requestBodyHandler.newChannel();
        if (httpRequest instanceof HttpContent) {
            requestBodyHandler.channelRead(channelHandlerContext, httpRequest);
        }
        return newChannel;
    }

    private final void callReadIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (this.state.activeRequests$internal < ((long) this.runningLimit)) {
            channelHandlerContext.read();
            this.state.skippedRead$internal = 0;
            return;
        }
        this.state.skippedRead$internal = 1;
    }
}
