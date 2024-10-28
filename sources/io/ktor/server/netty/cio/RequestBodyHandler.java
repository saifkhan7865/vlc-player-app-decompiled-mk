package io.ktor.server.netty.cio;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0000\u0018\u00002\u00020@2\u00020A:\u0001>B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\f\u0010\u000bJ#\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0017\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0004\b\u001d\u0010\u0004J\u0019\u0010\u001e\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0004\b\u001e\u0010\u0004J\r\u0010 \u001a\u00020\u001f¢\u0006\u0004\b \u0010!J#\u0010#\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0004\b#\u0010$J#\u0010#\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020%H@ø\u0001\u0000¢\u0006\u0004\b#\u0010'J\u000f\u0010(\u001a\u00020\u0007H\u0002¢\u0006\u0004\b(\u0010\u000bJ\u0017\u0010*\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u0005H\u0002¢\u0006\u0004\b*\u0010+J\r\u0010,\u001a\u00020\u001f¢\u0006\u0004\b,\u0010!R\u0017\u0010\u0002\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010-\u001a\u0004\b.\u0010/R\u0014\u00103\u001a\u0002008VX\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u001a\u00106\u001a\b\u0012\u0004\u0012\u000205048\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u00109\u001a\u0002088\u0002X\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u001a\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00050;8\u0002X\u0004¢\u0006\u0006\n\u0004\b<\u0010=\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, d2 = {"Lio/ktor/server/netty/cio/RequestBodyHandler;", "Lio/netty/channel/ChannelHandlerContext;", "context", "<init>", "(Lio/netty/channel/ChannelHandlerContext;)V", "", "msg", "", "channelRead", "(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Object;)V", "close", "()V", "consumeAndReleaseQueue", "Lio/netty/buffer/ByteBuf;", "buf", "Lio/ktor/utils/io/ByteWriteChannel;", "dst", "", "copy", "(Lio/netty/buffer/ByteBuf;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ctx", "", "cause", "exceptionCaught", "(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Throwable;)V", "Lio/netty/util/ReferenceCounted;", "content", "handleBytesRead", "(Lio/netty/util/ReferenceCounted;)V", "handlerAdded", "handlerRemoved", "Lio/ktor/utils/io/ByteReadChannel;", "newChannel", "()Lio/ktor/utils/io/ByteReadChannel;", "current", "processContent", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/netty/buffer/ByteBuf;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/netty/buffer/ByteBufHolder;", "event", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/netty/buffer/ByteBufHolder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestMoreEvents", "token", "tryOfferChannelOrToken", "(Ljava/lang/Object;)V", "upgrade", "Lio/netty/channel/ChannelHandlerContext;", "getContext", "()Lio/netty/channel/ChannelHandlerContext;", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "Lkotlinx/coroutines/CompletableDeferred;", "", "handlerJob", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlinx/coroutines/Job;", "job", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/channels/Channel;", "queue", "Lkotlinx/coroutines/channels/Channel;", "Upgrade", "ktor-server-netty", "Lio/netty/channel/ChannelInboundHandlerAdapter;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestBodyHandler.kt */
public final class RequestBodyHandler extends ChannelInboundHandlerAdapter implements CoroutineScope {
    private static final /* synthetic */ AtomicIntegerFieldUpdater buffersInProcessingCount$FU = AtomicIntegerFieldUpdater.newUpdater(RequestBodyHandler.class, "buffersInProcessingCount");
    private volatile /* synthetic */ int buffersInProcessingCount = 0;
    private final ChannelHandlerContext context;
    private final CompletableDeferred handlerJob = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
    private final Job job;
    /* access modifiers changed from: private */
    public final Channel<Object> queue = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);

    public final ChannelHandlerContext getContext() {
        return this.context;
    }

    public RequestBodyHandler(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        this.context = channelHandlerContext;
        EventExecutor executor = channelHandlerContext.executor();
        Intrinsics.checkNotNullExpressionValue(executor, "context.executor()");
        this.job = BuildersKt.launch(this, ExecutorsKt.from((ExecutorService) executor), CoroutineStart.LAZY, new RequestBodyHandler$job$1(this, (Continuation<? super RequestBodyHandler$job$1>) null));
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/netty/cio/RequestBodyHandler$Upgrade;", "", "()V", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RequestBodyHandler.kt */
    private static final class Upgrade {
        public static final Upgrade INSTANCE = new Upgrade();

        private Upgrade() {
        }
    }

    public CoroutineContext getCoroutineContext() {
        return this.handlerJob;
    }

    public final ByteReadChannel upgrade() {
        Object r0 = this.queue.m1139trySendJP2dKIU(Upgrade.INSTANCE);
        if (ChannelResult.m2346isSuccessimpl(r0)) {
            return newChannel();
        }
        if (this.queue.isClosedForSend()) {
            throw ExceptionsKt.CancellationException("HTTP pipeline has been terminated.", ChannelResult.m2340exceptionOrNullimpl(r0));
        }
        throw new IllegalStateException("Unable to start request processing: failed to offer " + Upgrade.INSTANCE + " to the HTTP pipeline queue. Queue closed: " + this.queue.isClosedForSend());
    }

    public final ByteReadChannel newChannel() {
        ByteChannel ByteChannel$default = ByteChannelKt.ByteChannel$default(false, 1, (Object) null);
        tryOfferChannelOrToken(ByteChannel$default);
        return ByteChannel$default;
    }

    private final void tryOfferChannelOrToken(Object obj) {
        Object r0 = this.queue.m1139trySendJP2dKIU(obj);
        if (!ChannelResult.m2346isSuccessimpl(r0)) {
            if (this.queue.isClosedForSend()) {
                throw ExceptionsKt.CancellationException("HTTP pipeline has been terminated.", ChannelResult.m2340exceptionOrNullimpl(r0));
            }
            throw new IllegalStateException("Unable to start request processing: failed to offer " + obj + " to the HTTP pipeline queue. Queue closed: " + this.queue.isClosedForSend());
        }
    }

    public final void close() {
        SendChannel.DefaultImpls.close$default(this.queue, (Throwable) null, 1, (Object) null);
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        if (obj instanceof ByteBufHolder) {
            handleBytesRead((ReferenceCounted) obj);
        } else if (obj instanceof ByteBuf) {
            handleBytesRead((ReferenceCounted) obj);
        } else {
            channelHandlerContext.fireChannelRead(obj);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: io.netty.buffer.ByteBufHolder} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object processContent(io.ktor.utils.io.ByteWriteChannel r5, io.netty.buffer.ByteBufHolder r6, kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.server.netty.cio.RequestBodyHandler$processContent$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.netty.cio.RequestBodyHandler$processContent$1 r0 = (io.ktor.server.netty.cio.RequestBodyHandler$processContent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.cio.RequestBodyHandler$processContent$1 r0 = new io.ktor.server.netty.cio.RequestBodyHandler$processContent$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r5 = r0.L$0
            r6 = r5
            io.netty.buffer.ByteBufHolder r6 = (io.netty.buffer.ByteBufHolder) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0052 }
            goto L_0x004e
        L_0x002f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r7)
            io.netty.buffer.ByteBuf r7 = r6.content()     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "buf"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)     // Catch:{ all -> 0x0052 }
            r0.L$0 = r6     // Catch:{ all -> 0x0052 }
            r0.label = r3     // Catch:{ all -> 0x0052 }
            java.lang.Object r7 = r4.copy(r7, r5, r0)     // Catch:{ all -> 0x0052 }
            if (r7 != r1) goto L_0x004e
            return r1
        L_0x004e:
            r6.release()
            return r7
        L_0x0052:
            r5 = move-exception
            r6.release()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.RequestBodyHandler.processContent(io.ktor.utils.io.ByteWriteChannel, io.netty.buffer.ByteBufHolder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: io.netty.buffer.ByteBuf} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object processContent(io.ktor.utils.io.ByteWriteChannel r5, io.netty.buffer.ByteBuf r6, kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.server.netty.cio.RequestBodyHandler$processContent$2
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.netty.cio.RequestBodyHandler$processContent$2 r0 = (io.ktor.server.netty.cio.RequestBodyHandler$processContent$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.cio.RequestBodyHandler$processContent$2 r0 = new io.ktor.server.netty.cio.RequestBodyHandler$processContent$2
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r5 = r0.L$0
            r6 = r5
            io.netty.buffer.ByteBuf r6 = (io.netty.buffer.ByteBuf) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0049 }
            goto L_0x0045
        L_0x002f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6     // Catch:{ all -> 0x0049 }
            r0.label = r3     // Catch:{ all -> 0x0049 }
            java.lang.Object r7 = r4.copy(r6, r5, r0)     // Catch:{ all -> 0x0049 }
            if (r7 != r1) goto L_0x0045
            return r1
        L_0x0045:
            r6.release()
            return r7
        L_0x0049:
            r5 = move-exception
            r6.release()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.RequestBodyHandler.processContent(io.ktor.utils.io.ByteWriteChannel, io.netty.buffer.ByteBuf, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void requestMoreEvents() {
        if (buffersInProcessingCount$FU.decrementAndGet(this) == 0) {
            this.context.read();
        }
    }

    /* access modifiers changed from: private */
    public final void consumeAndReleaseQueue() {
        Object obj;
        while (!this.queue.isEmpty()) {
            try {
                obj = ChannelResult.m2341getOrNullimpl(this.queue.m1138tryReceivePtdJZtk());
            } catch (Throwable unused) {
                obj = null;
            }
            if (obj != null) {
                if (obj instanceof ByteChannel) {
                    ByteWriteChannelKt.close((ByteWriteChannel) obj);
                } else if (obj instanceof ReferenceCounted) {
                    ((ReferenceCounted) obj).release();
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object copy(io.netty.buffer.ByteBuf r5, io.ktor.utils.io.ByteWriteChannel r6, kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.server.netty.cio.RequestBodyHandler$copy$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.netty.cio.RequestBodyHandler$copy$1 r0 = (io.ktor.server.netty.cio.RequestBodyHandler$copy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.cio.RequestBodyHandler$copy$1 r0 = new io.ktor.server.netty.cio.RequestBodyHandler$copy$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0034
            if (r2 != r3) goto L_0x002c
            int r5 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0056
        L_0x002c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r7)
            int r7 = r5.readableBytes()
            if (r7 <= 0) goto L_0x0057
            int r2 = r5.readerIndex()
            java.nio.ByteBuffer r5 = r5.internalNioBuffer(r2, r7)
            java.lang.String r2 = "buffer"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r2)
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r5 = r6.writeFully((java.nio.ByteBuffer) r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r5 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r5 = r7
        L_0x0056:
            r7 = r5
        L_0x0057:
            r5 = 0
            int r5 = java.lang.Math.max(r7, r5)
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.RequestBodyHandler.copy(io.netty.buffer.ByteBuf, io.ktor.utils.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void handleBytesRead(ReferenceCounted referenceCounted) {
        buffersInProcessingCount$FU.incrementAndGet(this);
        if (!ChannelResult.m2346isSuccessimpl(this.queue.m1139trySendJP2dKIU(referenceCounted))) {
            referenceCounted.release();
            throw new IllegalStateException("Unable to process received buffer: queue offer failed");
        }
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "cause");
        this.handlerJob.completeExceptionally(th);
        this.queue.close(th);
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) {
        if (SendChannel.DefaultImpls.close$default(this.queue, (Throwable) null, 1, (Object) null) && this.job.isCompleted()) {
            consumeAndReleaseQueue();
            Job.DefaultImpls.cancel$default((Job) this.handlerJob, (CancellationException) null, 1, (Object) null);
        }
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        this.job.start();
    }
}
