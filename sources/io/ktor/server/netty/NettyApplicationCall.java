package io.ktor.server.netty;

import io.ktor.server.application.Application;
import io.ktor.server.engine.BaseApplicationCall;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\b&\u0018\u00002\u00020FB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u000f\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0010\u0010\u000bJ\u0013\u0010\u0011\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000eJ\u000f\u0010\u0015\u001a\u00020\u0012H ¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u0012H\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0012H\u0010¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\tH\u0002¢\u0006\u0004\b \u0010\u000bJ\u0017\u0010$\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u0003H\u0010¢\u0006\u0004\b\"\u0010#R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b&\u0010'R\"\u0010)\u001a\u00020(8\u0000@\u0000X.¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010/\u001a\u00020\u00128\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u0010\u0014\"\u0004\b2\u00103R\"\u00104\u001a\u00020(8\u0000@\u0000X.¢\u0006\u0012\n\u0004\b4\u0010*\u001a\u0004\b5\u0010,\"\u0004\b6\u0010.R\u0014\u0010:\u001a\u0002078&X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010;R\u0014\u0010?\u001a\u00020<8&X¦\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0017\u0010A\u001a\u00020@8\u0006¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D\u0002\u0004\n\u0002\b\u0019¨\u0006E"}, d2 = {"Lio/ktor/server/netty/NettyApplicationCall;", "Lio/ktor/server/application/Application;", "application", "Lio/netty/channel/ChannelHandlerContext;", "context", "", "requestMessage", "<init>", "(Lio/ktor/server/application/Application;Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Object;)V", "", "dispose$ktor_server_netty", "()V", "dispose", "finish$ktor_server_netty", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finish", "finishComplete", "finishSuspend", "", "isContextCloseRequired$ktor_server_netty", "()Z", "isContextCloseRequired", "lastTransformed", "prepareEndOfStreamMessage$ktor_server_netty", "(Z)Ljava/lang/Object;", "prepareEndOfStreamMessage", "Lio/netty/buffer/ByteBuf;", "buf", "isLastContent", "prepareMessage$ktor_server_netty", "(Lio/netty/buffer/ByteBuf;Z)Ljava/lang/Object;", "prepareMessage", "releaseRequestMessage", "dst", "upgrade$ktor_server_netty", "(Lio/netty/channel/ChannelHandlerContext;)V", "upgrade", "Lio/netty/channel/ChannelHandlerContext;", "getContext", "()Lio/netty/channel/ChannelHandlerContext;", "Lio/netty/channel/ChannelPromise;", "finishedEvent", "Lio/netty/channel/ChannelPromise;", "getFinishedEvent$ktor_server_netty", "()Lio/netty/channel/ChannelPromise;", "setFinishedEvent$ktor_server_netty", "(Lio/netty/channel/ChannelPromise;)V", "isByteBufferContent", "Z", "isByteBufferContent$ktor_server_netty", "setByteBufferContent$ktor_server_netty", "(Z)V", "previousCallFinished", "getPreviousCallFinished$ktor_server_netty", "setPreviousCallFinished$ktor_server_netty", "Lio/ktor/server/netty/NettyApplicationRequest;", "getRequest", "()Lio/ktor/server/netty/NettyApplicationRequest;", "request", "Ljava/lang/Object;", "Lio/ktor/server/netty/NettyApplicationResponse;", "getResponse", "()Lio/ktor/server/netty/NettyApplicationResponse;", "response", "Lkotlinx/coroutines/Job;", "responseWriteJob", "Lkotlinx/coroutines/Job;", "getResponseWriteJob", "()Lkotlinx/coroutines/Job;", "ktor-server-netty", "Lio/ktor/server/engine/BaseApplicationCall;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationCall.kt */
public abstract class NettyApplicationCall extends BaseApplicationCall {
    private static final /* synthetic */ AtomicIntegerFieldUpdater messageReleased$FU = AtomicIntegerFieldUpdater.newUpdater(NettyApplicationCall.class, "messageReleased");
    private final ChannelHandlerContext context;
    public ChannelPromise finishedEvent;
    private boolean isByteBufferContent;
    private volatile /* synthetic */ int messageReleased = 0;
    public ChannelPromise previousCallFinished;
    private final Object requestMessage;
    private final Job responseWriteJob = JobKt.Job$default((Job) null, 1, (Object) null);

    public abstract NettyApplicationRequest getRequest();

    public abstract NettyApplicationResponse getResponse();

    public abstract boolean isContextCloseRequired$ktor_server_netty();

    public Object prepareEndOfStreamMessage$ktor_server_netty(boolean z) {
        return null;
    }

    public Object prepareMessage$ktor_server_netty(ByteBuf byteBuf, boolean z) {
        Intrinsics.checkNotNullParameter(byteBuf, "buf");
        return byteBuf;
    }

    public final ChannelHandlerContext getContext() {
        return this.context;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyApplicationCall(Application application, ChannelHandlerContext channelHandlerContext, Object obj) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(obj, "requestMessage");
        this.context = channelHandlerContext;
        this.requestMessage = obj;
    }

    public final ChannelPromise getPreviousCallFinished$ktor_server_netty() {
        ChannelPromise channelPromise = this.previousCallFinished;
        if (channelPromise != null) {
            return channelPromise;
        }
        Intrinsics.throwUninitializedPropertyAccessException("previousCallFinished");
        return null;
    }

    public final void setPreviousCallFinished$ktor_server_netty(ChannelPromise channelPromise) {
        Intrinsics.checkNotNullParameter(channelPromise, "<set-?>");
        this.previousCallFinished = channelPromise;
    }

    public final ChannelPromise getFinishedEvent$ktor_server_netty() {
        ChannelPromise channelPromise = this.finishedEvent;
        if (channelPromise != null) {
            return channelPromise;
        }
        Intrinsics.throwUninitializedPropertyAccessException("finishedEvent");
        return null;
    }

    public final void setFinishedEvent$ktor_server_netty(ChannelPromise channelPromise) {
        Intrinsics.checkNotNullParameter(channelPromise, "<set-?>");
        this.finishedEvent = channelPromise;
    }

    public final Job getResponseWriteJob() {
        return this.responseWriteJob;
    }

    public final boolean isByteBufferContent$ktor_server_netty() {
        return this.isByteBufferContent;
    }

    public final void setByteBufferContent$ktor_server_netty(boolean z) {
        this.isByteBufferContent = z;
    }

    public void upgrade$ktor_server_netty(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "dst");
        throw new IllegalStateException("Already upgraded");
    }

    public final Object finish$ktor_server_netty(Continuation<? super Unit> continuation) {
        try {
            getResponse().ensureResponseSent$ktor_server_netty();
            if (this.responseWriteJob.isCompleted()) {
                finishComplete();
                return Unit.INSTANCE;
            }
            Object finishSuspend = finishSuspend(continuation);
            return finishSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? finishSuspend : Unit.INSTANCE;
        } catch (Throwable th) {
            getFinishedEvent$ktor_server_netty().setFailure(th);
            finishComplete();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object finishSuspend(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.server.netty.NettyApplicationCall$finishSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            io.ktor.server.netty.NettyApplicationCall$finishSuspend$1 r0 = (io.ktor.server.netty.NettyApplicationCall$finishSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.NettyApplicationCall$finishSuspend$1 r0 = new io.ktor.server.netty.NettyApplicationCall$finishSuspend$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r0 = r0.L$0
            io.ktor.server.netty.NettyApplicationCall r0 = (io.ktor.server.netty.NettyApplicationCall) r0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ all -> 0x002e }
            goto L_0x0049
        L_0x002e:
            r5 = move-exception
            goto L_0x0051
        L_0x0030:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.Job r5 = r4.responseWriteJob     // Catch:{ all -> 0x004f }
            r0.L$0 = r4     // Catch:{ all -> 0x004f }
            r0.label = r3     // Catch:{ all -> 0x004f }
            java.lang.Object r5 = r5.join(r0)     // Catch:{ all -> 0x004f }
            if (r5 != r1) goto L_0x0048
            return r1
        L_0x0048:
            r0 = r4
        L_0x0049:
            r0.finishComplete()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x004f:
            r5 = move-exception
            r0 = r4
        L_0x0051:
            r0.finishComplete()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.NettyApplicationCall.finishSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void finishComplete() {
        Job.DefaultImpls.cancel$default(this.responseWriteJob, (CancellationException) null, 1, (Object) null);
        getRequest().close();
        releaseRequestMessage();
    }

    public final void dispose$ktor_server_netty() {
        getResponse().close$ktor_server_netty();
        getRequest().close();
        releaseRequestMessage();
    }

    private final void releaseRequestMessage() {
        if (messageReleased$FU.compareAndSet(this, 0, 1)) {
            ReferenceCountUtil.release(this.requestMessage);
        }
    }
}
