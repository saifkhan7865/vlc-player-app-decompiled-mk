package io.ktor.server.netty.cio;

import androidx.core.app.NotificationCompat;
import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.server.netty.NettyApplicationResponse;
import io.ktor.server.netty.NettyHttpHandlerState;
import io.ktor.util.cio.ChannelIOException;
import io.ktor.util.cio.ChannelWriteException;
import io.ktor.utils.io.ByteReadChannel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http2.Http2HeadersFrame;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0000\u0018\u00002\u00020NB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u001c\u0010\u0018J\u0017\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u001d\u0010\u0018J+\u0010\"\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J+\u0010$\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0004\b$\u0010#Jc\u0010-\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\t26\u0010,\u001a2\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0013\u0012\u00110*¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00190%H@ø\u0001\u0000¢\u0006\u0004\b-\u0010.J3\u00100\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010/\u001a\u00020*2\u0006\u0010!\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u001f\u00102\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b2\u00103J\u001f\u00106\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u00105\u001a\u000204H\u0002¢\u0006\u0004\b6\u00107J\u0017\u00109\u001a\u00020\t2\u0006\u00108\u001a\u00020\u0013H\u0002¢\u0006\u0004\b9\u0010:J+\u0010<\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010;\u001a\u00020*H@ø\u0001\u0000¢\u0006\u0004\b<\u0010=J\u001f\u0010>\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0013H\u0002¢\u0006\u0004\b>\u0010?J\u000f\u0010@\u001a\u00020\u000bH\u0002¢\u0006\u0004\b@\u0010\u000fJ%\u0010C\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u000b0AH\u0002¢\u0006\u0004\bC\u0010DR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010ER\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010F\u001a\u0004\bG\u0010HR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010IR\u0016\u0010K\u001a\u00020J8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bK\u0010L\u0002\u0004\n\u0002\b\u0019¨\u0006M"}, d2 = {"Lio/ktor/server/netty/cio/NettyHttpResponsePipeline;", "Lio/netty/channel/ChannelHandlerContext;", "context", "Lio/ktor/server/netty/NettyHttpHandlerState;", "httpHandlerState", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "<init>", "(Lio/netty/channel/ChannelHandlerContext;Lio/ktor/server/netty/NettyHttpHandlerState;Lkotlin/coroutines/CoroutineContext;)V", "Lio/netty/channel/ChannelFuture;", "lastFuture", "", "close", "(Lio/netty/channel/ChannelFuture;)V", "flushIfNeeded$ktor_server_netty", "()V", "flushIfNeeded", "Lio/ktor/server/netty/NettyApplicationCall;", "call", "", "lastMessage", "handleLastResponseMessage", "(Lio/ktor/server/netty/NettyApplicationCall;Ljava/lang/Object;Lio/netty/channel/ChannelFuture;)V", "handleRequestMessage", "(Lio/ktor/server/netty/NettyApplicationCall;)V", "", "isHeaderFlushNeeded", "()Z", "processElement", "processResponse$ktor_server_netty", "processResponse", "Lio/ktor/server/netty/NettyApplicationResponse;", "response", "requestMessageFuture", "respondBodyWithFlushOnLimit", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/ktor/server/netty/NettyApplicationResponse;Lio/netty/channel/ChannelFuture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondBodyWithFlushOnLimitOrEmptyChannel", "Lkotlin/Function2;", "Lio/ktor/utils/io/ByteReadChannel;", "Lkotlin/ParameterName;", "name", "channel", "", "unflushedBytes", "shouldFlush", "respondWithBigBody", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/ktor/server/netty/NettyApplicationResponse;Lio/netty/channel/ChannelFuture;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bodySize", "respondWithBodyAndTrailerMessage", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/ktor/server/netty/NettyApplicationResponse;ILio/netty/channel/ChannelFuture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondWithEmptyBody", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/netty/channel/ChannelFuture;)V", "", "actualException", "respondWithFailure", "(Lio/ktor/server/netty/NettyApplicationCall;Ljava/lang/Throwable;)V", "responseMessage", "respondWithHeader", "(Ljava/lang/Object;)Lio/netty/channel/ChannelFuture;", "size", "respondWithSmallBody", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/ktor/server/netty/NettyApplicationResponse;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondWithUpgrade", "(Lio/ktor/server/netty/NettyApplicationCall;Ljava/lang/Object;)Lio/netty/channel/ChannelFuture;", "scheduleFlush", "Lkotlin/Function0;", "block", "setOnResponseReadyHandler", "(Lio/ktor/server/netty/NettyApplicationCall;Lkotlin/jvm/functions/Function0;)V", "Lio/netty/channel/ChannelHandlerContext;", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "Lio/ktor/server/netty/NettyHttpHandlerState;", "Lio/netty/channel/ChannelPromise;", "previousCallHandled", "Lio/netty/channel/ChannelPromise;", "ktor-server-netty", "Lkotlinx/coroutines/CoroutineScope;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttpResponsePipeline.kt */
public final class NettyHttpResponsePipeline implements CoroutineScope {
    static final /* synthetic */ AtomicIntegerFieldUpdater isDataNotFlushed$FU = AtomicIntegerFieldUpdater.newUpdater(NettyHttpResponsePipeline.class, "isDataNotFlushed");
    /* access modifiers changed from: private */
    public final ChannelHandlerContext context;
    private final CoroutineContext coroutineContext;
    private final NettyHttpHandlerState httpHandlerState;
    volatile /* synthetic */ int isDataNotFlushed = 0;
    private ChannelPromise previousCallHandled;

    public NettyHttpResponsePipeline(ChannelHandlerContext channelHandlerContext, NettyHttpHandlerState nettyHttpHandlerState, CoroutineContext coroutineContext2) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(nettyHttpHandlerState, "httpHandlerState");
        Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
        this.context = channelHandlerContext;
        this.httpHandlerState = nettyHttpHandlerState;
        this.coroutineContext = coroutineContext2;
        ChannelPromise newPromise = channelHandlerContext.newPromise();
        newPromise.setSuccess();
        Intrinsics.checkNotNullExpressionValue(newPromise, "context.newPromise().als…    it.setSuccess()\n    }");
        this.previousCallHandled = newPromise;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final void flushIfNeeded$ktor_server_netty() {
        if (this.isDataNotFlushed != 0 && this.httpHandlerState.isChannelReadCompleted$internal != 0 && this.httpHandlerState.activeRequests$internal == 0) {
            this.context.flush();
            isDataNotFlushed$FU.compareAndSet(this, 1, 0);
        }
    }

    public final void processResponse$ktor_server_netty(NettyApplicationCall nettyApplicationCall) {
        Intrinsics.checkNotNullParameter(nettyApplicationCall, NotificationCompat.CATEGORY_CALL);
        nettyApplicationCall.setPreviousCallFinished$ktor_server_netty(this.previousCallHandled);
        ChannelPromise newPromise = this.context.newPromise();
        Intrinsics.checkNotNullExpressionValue(newPromise, "context.newPromise()");
        nettyApplicationCall.setFinishedEvent$ktor_server_netty(newPromise);
        this.previousCallHandled = nettyApplicationCall.getFinishedEvent$ktor_server_netty();
        processElement(nettyApplicationCall);
    }

    private final void processElement(NettyApplicationCall nettyApplicationCall) {
        setOnResponseReadyHandler(nettyApplicationCall, new NettyHttpResponsePipeline$processElement$1(this, nettyApplicationCall));
    }

    private final void setOnResponseReadyHandler(NettyApplicationCall nettyApplicationCall, Function0<Unit> function0) {
        nettyApplicationCall.getResponse().getResponseReady$ktor_server_netty().addListener(new NettyHttpResponsePipeline$$ExternalSyntheticLambda3(nettyApplicationCall, this, function0));
    }

    /* access modifiers changed from: private */
    public static final void setOnResponseReadyHandler$lambda$2(NettyApplicationCall nettyApplicationCall, NettyHttpResponsePipeline nettyHttpResponsePipeline, Function0 function0, Future future) {
        Intrinsics.checkNotNullParameter(nettyApplicationCall, "$call");
        Intrinsics.checkNotNullParameter(nettyHttpResponsePipeline, "this$0");
        Intrinsics.checkNotNullParameter(function0, "$block");
        nettyApplicationCall.getPreviousCallFinished$ktor_server_netty().addListener(new NettyHttpResponsePipeline$$ExternalSyntheticLambda2(nettyHttpResponsePipeline, nettyApplicationCall, future, function0));
    }

    /* access modifiers changed from: private */
    public static final void setOnResponseReadyHandler$lambda$2$lambda$1(NettyHttpResponsePipeline nettyHttpResponsePipeline, NettyApplicationCall nettyApplicationCall, Future future, Function0 function0, Future future2) {
        Intrinsics.checkNotNullParameter(nettyHttpResponsePipeline, "this$0");
        Intrinsics.checkNotNullParameter(nettyApplicationCall, "$call");
        Intrinsics.checkNotNullParameter(function0, "$block");
        if (!future2.isSuccess()) {
            Throwable cause = future2.cause();
            Intrinsics.checkNotNullExpressionValue(cause, "previousCallResult.cause()");
            nettyHttpResponsePipeline.respondWithFailure(nettyApplicationCall, cause);
        } else if (!future.isSuccess()) {
            Throwable cause2 = future.cause();
            Intrinsics.checkNotNullExpressionValue(cause2, "responseFlagResult.cause()");
            nettyHttpResponsePipeline.respondWithFailure(nettyApplicationCall, cause2);
        } else {
            function0.invoke();
        }
    }

    /* access modifiers changed from: private */
    public final void respondWithFailure(NettyApplicationCall nettyApplicationCall, Throwable th) {
        if ((th instanceof IOException) && !(th instanceof ChannelIOException)) {
            th = new ChannelWriteException((String) null, th, 1, (DefaultConstructorMarker) null);
        }
        nettyApplicationCall.getResponse().getResponseChannel$ktor_server_netty().cancel(th);
        Job.DefaultImpls.cancel$default(nettyApplicationCall.getResponseWriteJob(), (CancellationException) null, 1, (Object) null);
        nettyApplicationCall.getResponse().cancel();
        nettyApplicationCall.dispose$ktor_server_netty();
        nettyApplicationCall.getFinishedEvent$ktor_server_netty().setFailure(th);
    }

    private final ChannelFuture respondWithUpgrade(NettyApplicationCall nettyApplicationCall, Object obj) {
        ChannelFuture write = this.context.write(obj);
        nettyApplicationCall.upgrade$ktor_server_netty(this.context);
        nettyApplicationCall.setByteBufferContent$ktor_server_netty(true);
        this.context.flush();
        isDataNotFlushed$FU.compareAndSet(this, 1, 0);
        Intrinsics.checkNotNullExpressionValue(write, "future");
        return write;
    }

    private final void handleLastResponseMessage(NettyApplicationCall nettyApplicationCall, Object obj, ChannelFuture channelFuture) {
        ChannelFuture channelFuture2;
        boolean z = nettyApplicationCall.isContextCloseRequired$ktor_server_netty() && (!nettyApplicationCall.getRequest().getKeepAlive$ktor_server_netty() || NettyHttpResponsePipelineKt.isUpgradeResponse(nettyApplicationCall.getResponse()));
        if (obj != null) {
            channelFuture2 = this.context.write(obj);
            isDataNotFlushed$FU.compareAndSet(this, 0, 1);
        } else {
            channelFuture2 = null;
        }
        this.httpHandlerState.onLastResponseMessage$ktor_server_netty(this.context);
        nettyApplicationCall.getFinishedEvent$ktor_server_netty().setSuccess();
        if (channelFuture2 != null) {
            channelFuture2.addListener(new NettyHttpResponsePipeline$$ExternalSyntheticLambda4(z, this, channelFuture));
        }
        if (z) {
            close(channelFuture);
        } else {
            scheduleFlush();
        }
    }

    /* access modifiers changed from: private */
    public static final void handleLastResponseMessage$lambda$3(boolean z, NettyHttpResponsePipeline nettyHttpResponsePipeline, ChannelFuture channelFuture, Future future) {
        Intrinsics.checkNotNullParameter(nettyHttpResponsePipeline, "this$0");
        Intrinsics.checkNotNullParameter(channelFuture, "$lastFuture");
        if (z) {
            nettyHttpResponsePipeline.close(channelFuture);
        }
    }

    public final void close(ChannelFuture channelFuture) {
        Intrinsics.checkNotNullParameter(channelFuture, "lastFuture");
        this.context.flush();
        isDataNotFlushed$FU.compareAndSet(this, 1, 0);
        channelFuture.addListener(new NettyHttpResponsePipeline$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void close$lambda$4(NettyHttpResponsePipeline nettyHttpResponsePipeline, Future future) {
        Intrinsics.checkNotNullParameter(nettyHttpResponsePipeline, "this$0");
        nettyHttpResponsePipeline.context.close();
    }

    private final void scheduleFlush() {
        this.context.executor().execute(new NettyHttpResponsePipeline$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final void scheduleFlush$lambda$5(NettyHttpResponsePipeline nettyHttpResponsePipeline) {
        Intrinsics.checkNotNullParameter(nettyHttpResponsePipeline, "this$0");
        nettyHttpResponsePipeline.flushIfNeeded$ktor_server_netty();
    }

    /* access modifiers changed from: private */
    public final void handleRequestMessage(NettyApplicationCall nettyApplicationCall) {
        ChannelFuture channelFuture;
        int i;
        int i2;
        Object responseMessage = nettyApplicationCall.getResponse().getResponseMessage();
        NettyApplicationResponse response = nettyApplicationCall.getResponse();
        if (NettyHttpResponsePipelineKt.isUpgradeResponse(response)) {
            channelFuture = respondWithUpgrade(nettyApplicationCall, responseMessage);
        } else {
            channelFuture = respondWithHeader(responseMessage);
        }
        ChannelFuture channelFuture2 = channelFuture;
        if (responseMessage instanceof FullHttpResponse) {
            handleLastResponseMessage(nettyApplicationCall, (Object) null, channelFuture2);
            return;
        }
        boolean z = responseMessage instanceof Http2HeadersFrame;
        if (!z || !((Http2HeadersFrame) responseMessage).isEndStream()) {
            if (response.getResponseChannel$ktor_server_netty() == ByteReadChannel.Companion.getEmpty()) {
                i = 0;
            } else {
                if (responseMessage instanceof HttpResponse) {
                    i2 = ((HttpResponse) responseMessage).headers().getInt("Content-Length", -1);
                } else if (z) {
                    i2 = ((Http2HeadersFrame) responseMessage).headers().getInt("content-length", -1);
                } else {
                    i = -1;
                }
                i = i2;
            }
            EventExecutor executor = this.context.executor();
            Intrinsics.checkNotNullExpressionValue(executor, "context.executor()");
            BuildersKt.launch(this, ExecutorsKt.from((ExecutorService) executor), CoroutineStart.UNDISPATCHED, new NettyHttpResponsePipeline$handleRequestMessage$1(this, nettyApplicationCall, response, i, channelFuture2, (Continuation<? super NettyHttpResponsePipeline$handleRequestMessage$1>) null));
            return;
        }
        handleLastResponseMessage(nettyApplicationCall, (Object) null, channelFuture2);
    }

    private final ChannelFuture respondWithHeader(Object obj) {
        if (isHeaderFlushNeeded()) {
            ChannelFuture writeAndFlush = this.context.writeAndFlush(obj);
            isDataNotFlushed$FU.compareAndSet(this, 1, 0);
            Intrinsics.checkNotNullExpressionValue(writeAndFlush, "{\n            val future…         future\n        }");
            return writeAndFlush;
        }
        ChannelFuture write = this.context.write(obj);
        isDataNotFlushed$FU.compareAndSet(this, 0, 1);
        Intrinsics.checkNotNullExpressionValue(write, "{\n            val future…         future\n        }");
        return write;
    }

    private final boolean isHeaderFlushNeeded() {
        return this.httpHandlerState.isChannelReadCompleted$internal != 0 && this.httpHandlerState.isCurrentRequestFullyRead$internal == 0 && this.httpHandlerState.activeRequests$internal == 1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object respondWithBodyAndTrailerMessage(io.ktor.server.netty.NettyApplicationCall r7, io.ktor.server.netty.NettyApplicationResponse r8, int r9, io.netty.channel.ChannelFuture r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r6 = this;
            boolean r0 = r11 instanceof io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1 r0 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1 r0 = new io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1
            r0.<init>(r6, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0054
            if (r2 == r5) goto L_0x0049
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r7 = r0.L$1
            io.ktor.server.netty.NettyApplicationCall r7 = (io.ktor.server.netty.NettyApplicationCall) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r8 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline) r8
            goto L_0x0045
        L_0x0035:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003d:
            java.lang.Object r7 = r0.L$1
            io.ktor.server.netty.NettyApplicationCall r7 = (io.ktor.server.netty.NettyApplicationCall) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r8 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline) r8
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0052 }
            goto L_0x0094
        L_0x0049:
            java.lang.Object r7 = r0.L$1
            io.ktor.server.netty.NettyApplicationCall r7 = (io.ktor.server.netty.NettyApplicationCall) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r8 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline) r8
            goto L_0x0045
        L_0x0052:
            r9 = move-exception
            goto L_0x0091
        L_0x0054:
            kotlin.ResultKt.throwOnFailure(r11)
            if (r9 != 0) goto L_0x0060
            r6.respondWithEmptyBody(r7, r10)     // Catch:{ all -> 0x005d }
            goto L_0x0094
        L_0x005d:
            r9 = move-exception
            r8 = r6
            goto L_0x0091
        L_0x0060:
            if (r5 > r9) goto L_0x0074
            r11 = 65537(0x10001, float:9.1837E-41)
            if (r9 >= r11) goto L_0x0074
            r0.L$0 = r6     // Catch:{ all -> 0x005d }
            r0.L$1 = r7     // Catch:{ all -> 0x005d }
            r0.label = r5     // Catch:{ all -> 0x005d }
            java.lang.Object r7 = r6.respondWithSmallBody(r7, r8, r9, r0)     // Catch:{ all -> 0x005d }
            if (r7 != r1) goto L_0x0094
            return r1
        L_0x0074:
            r11 = -1
            if (r9 != r11) goto L_0x0084
            r0.L$0 = r6     // Catch:{ all -> 0x005d }
            r0.L$1 = r7     // Catch:{ all -> 0x005d }
            r0.label = r4     // Catch:{ all -> 0x005d }
            java.lang.Object r7 = r6.respondBodyWithFlushOnLimitOrEmptyChannel(r7, r8, r10, r0)     // Catch:{ all -> 0x005d }
            if (r7 != r1) goto L_0x0094
            return r1
        L_0x0084:
            r0.L$0 = r6     // Catch:{ all -> 0x005d }
            r0.L$1 = r7     // Catch:{ all -> 0x005d }
            r0.label = r3     // Catch:{ all -> 0x005d }
            java.lang.Object r7 = r6.respondBodyWithFlushOnLimit(r7, r8, r10, r0)     // Catch:{ all -> 0x005d }
            if (r7 != r1) goto L_0x0094
            return r1
        L_0x0091:
            r8.respondWithFailure(r7, r9)
        L_0x0094:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.NettyHttpResponsePipeline.respondWithBodyAndTrailerMessage(io.ktor.server.netty.NettyApplicationCall, io.ktor.server.netty.NettyApplicationResponse, int, io.netty.channel.ChannelFuture, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void respondWithEmptyBody(NettyApplicationCall nettyApplicationCall, ChannelFuture channelFuture) {
        handleLastResponseMessage(nettyApplicationCall, nettyApplicationCall.prepareEndOfStreamMessage$ktor_server_netty(false), channelFuture);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object respondWithSmallBody(io.ktor.server.netty.NettyApplicationCall r8, io.ktor.server.netty.NettyApplicationResponse r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithSmallBody$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithSmallBody$1 r0 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithSmallBody$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithSmallBody$1 r0 = new io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithSmallBody$1
            r0.<init>(r7, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            int r8 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r9 = r0.L$3
            io.netty.buffer.ByteBuf r9 = (io.netty.buffer.ByteBuf) r9
            java.lang.Object r1 = r0.L$2
            io.ktor.server.netty.NettyApplicationResponse r1 = (io.ktor.server.netty.NettyApplicationResponse) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.server.netty.NettyApplicationCall r2 = (io.ktor.server.netty.NettyApplicationCall) r2
            java.lang.Object r0 = r0.L$0
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r0 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0082
        L_0x003e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r11)
            io.netty.channel.ChannelHandlerContext r11 = r7.context
            io.netty.buffer.ByteBufAllocator r11 = r11.alloc()
            io.netty.buffer.ByteBuf r11 = r11.buffer(r10)
            io.ktor.utils.io.ByteReadChannel r2 = r9.getResponseChannel$ktor_server_netty()
            int r4 = r11.writerIndex()
            int r5 = r11.writableBytes()
            java.nio.ByteBuffer r5 = r11.nioBuffer(r4, r5)
            java.lang.String r6 = "buffer.nioBuffer(start, buffer.writableBytes())"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r11
            r0.I$0 = r10
            r0.I$1 = r4
            r0.label = r3
            java.lang.Object r0 = r2.readFully(r5, r0)
            if (r0 != r1) goto L_0x007d
            return r1
        L_0x007d:
            r0 = r7
            r2 = r8
            r1 = r9
            r9 = r11
            r8 = r4
        L_0x0082:
            int r8 = r8 + r10
            r9.writerIndex(r8)
            io.netty.channel.ChannelHandlerContext r8 = r0.context
            java.lang.String r10 = "buffer"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            java.lang.Object r9 = r2.prepareMessage$ktor_server_netty(r9, r3)
            io.netty.channel.ChannelFuture r8 = r8.write(r9)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r9 = isDataNotFlushed$FU
            r10 = 0
            r9.compareAndSet(r0, r10, r3)
            java.lang.Object r9 = r1.prepareTrailerMessage$ktor_server_netty()
            if (r9 != 0) goto L_0x00a5
            java.lang.Object r9 = r2.prepareEndOfStreamMessage$ktor_server_netty(r3)
        L_0x00a5:
            java.lang.String r10 = "future"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r10)
            r0.handleLastResponseMessage(r2, r9, r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.NettyHttpResponsePipeline.respondWithSmallBody(io.ktor.server.netty.NettyApplicationCall, io.ktor.server.netty.NettyApplicationResponse, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object respondBodyWithFlushOnLimit(NettyApplicationCall nettyApplicationCall, NettyApplicationResponse nettyApplicationResponse, ChannelFuture channelFuture, Continuation<? super Unit> continuation) {
        Object respondWithBigBody = respondWithBigBody(nettyApplicationCall, nettyApplicationResponse, channelFuture, NettyHttpResponsePipeline$respondBodyWithFlushOnLimit$2.INSTANCE, continuation);
        return respondWithBigBody == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondWithBigBody : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final Object respondBodyWithFlushOnLimitOrEmptyChannel(NettyApplicationCall nettyApplicationCall, NettyApplicationResponse nettyApplicationResponse, ChannelFuture channelFuture, Continuation<? super Unit> continuation) {
        Object respondWithBigBody = respondWithBigBody(nettyApplicationCall, nettyApplicationResponse, channelFuture, NettyHttpResponsePipeline$respondBodyWithFlushOnLimitOrEmptyChannel$2.INSTANCE, continuation);
        return respondWithBigBody == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondWithBigBody : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object respondWithBigBody(io.ktor.server.netty.NettyApplicationCall r16, io.ktor.server.netty.NettyApplicationResponse r17, io.netty.channel.ChannelFuture r18, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.ByteReadChannel, ? super java.lang.Integer, java.lang.Boolean> r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r15 = this;
            r8 = r15
            r0 = r20
            boolean r1 = r0 instanceof io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$1 r1 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0017
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001c
        L_0x0017:
            io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$1 r1 = new io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$1
            r1.<init>(r15, r0)
        L_0x001c:
            r9 = r1
            java.lang.Object r0 = r9.result
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r11 = 1
            if (r1 == 0) goto L_0x0049
            if (r1 != r11) goto L_0x0041
            java.lang.Object r1 = r9.L$3
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
            java.lang.Object r2 = r9.L$2
            io.ktor.server.netty.NettyApplicationResponse r2 = (io.ktor.server.netty.NettyApplicationResponse) r2
            java.lang.Object r3 = r9.L$1
            io.ktor.server.netty.NettyApplicationCall r3 = (io.ktor.server.netty.NettyApplicationCall) r3
            java.lang.Object r4 = r9.L$0
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r4 = (io.ktor.server.netty.cio.NettyHttpResponsePipeline) r4
            kotlin.ResultKt.throwOnFailure(r0)
            r13 = r1
            r1 = r2
            r0 = r3
            goto L_0x0084
        L_0x0041:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.utils.io.ByteReadChannel r12 = r17.getResponseChannel$ktor_server_netty()
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r13 = new kotlin.jvm.internal.Ref$ObjectRef
            r13.<init>()
            r0 = r18
            r13.element = r0
            io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$2 r14 = new io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$2
            r7 = 0
            r0 = r14
            r1 = r15
            r3 = r16
            r4 = r19
            r5 = r12
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r9.L$0 = r8
            r0 = r16
            r9.L$1 = r0
            r1 = r17
            r9.L$2 = r1
            r9.L$3 = r13
            r9.label = r11
            java.lang.Object r2 = r12.lookAheadSuspend(r14, r9)
            if (r2 != r10) goto L_0x0083
            return r10
        L_0x0083:
            r4 = r8
        L_0x0084:
            java.lang.Object r1 = r1.prepareTrailerMessage$ktor_server_netty()
            if (r1 != 0) goto L_0x008f
            r1 = 0
            java.lang.Object r1 = r0.prepareEndOfStreamMessage$ktor_server_netty(r1)
        L_0x008f:
            T r2 = r13.element
            io.netty.channel.ChannelFuture r2 = (io.netty.channel.ChannelFuture) r2
            r4.handleLastResponseMessage(r0, r1, r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.NettyHttpResponsePipeline.respondWithBigBody(io.ktor.server.netty.NettyApplicationCall, io.ktor.server.netty.NettyApplicationResponse, io.netty.channel.ChannelFuture, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
