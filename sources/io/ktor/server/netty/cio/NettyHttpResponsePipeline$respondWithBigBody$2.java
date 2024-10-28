package io.ktor.server.netty.cio;

import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.LookAheadSuspendSession;
import io.netty.channel.ChannelFuture;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$2", f = "NettyHttpResponsePipeline.kt", i = {0, 1}, l = {328, 348}, m = "invokeSuspend", n = {"$this$lookAheadSuspend", "$this$lookAheadSuspend"}, s = {"L$0", "L$0"})
/* compiled from: NettyHttpResponsePipeline.kt */
final class NettyHttpResponsePipeline$respondWithBigBody$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ NettyApplicationCall $call;
    final /* synthetic */ ByteReadChannel $channel;
    final /* synthetic */ Ref.ObjectRef<ChannelFuture> $lastFuture;
    final /* synthetic */ Function2<ByteReadChannel, Integer, Boolean> $shouldFlush;
    final /* synthetic */ Ref.IntRef $unflushedBytes;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NettyHttpResponsePipeline this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttpResponsePipeline$respondWithBigBody$2(NettyHttpResponsePipeline nettyHttpResponsePipeline, Ref.IntRef intRef, NettyApplicationCall nettyApplicationCall, Function2<? super ByteReadChannel, ? super Integer, Boolean> function2, ByteReadChannel byteReadChannel, Ref.ObjectRef<ChannelFuture> objectRef, Continuation<? super NettyHttpResponsePipeline$respondWithBigBody$2> continuation) {
        super(2, continuation);
        this.this$0 = nettyHttpResponsePipeline;
        this.$unflushedBytes = intRef;
        this.$call = nettyApplicationCall;
        this.$shouldFlush = function2;
        this.$channel = byteReadChannel;
        this.$lastFuture = objectRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NettyHttpResponsePipeline$respondWithBigBody$2 nettyHttpResponsePipeline$respondWithBigBody$2 = new NettyHttpResponsePipeline$respondWithBigBody$2(this.this$0, this.$unflushedBytes, this.$call, this.$shouldFlush, this.$channel, this.$lastFuture, continuation);
        nettyHttpResponsePipeline$respondWithBigBody$2.L$0 = obj;
        return nettyHttpResponsePipeline$respondWithBigBody$2;
    }

    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((NettyHttpResponsePipeline$respondWithBigBody$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d3 A[LOOP:0: B:9:0x0030->B:24:0x00d3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0036 A[EDGE_INSN: B:25:0x0036->B:11:0x0036 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009a A[EDGE_INSN: B:26:0x009a->B:20:0x009a ?: BREAK  , SYNTHETIC] */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0028
            if (r1 == r4) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            java.lang.Object r1 = r8.L$0
            io.ktor.utils.io.LookAheadSuspendSession r1 = (io.ktor.utils.io.LookAheadSuspendSession) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00cd
        L_0x0018:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0020:
            java.lang.Object r1 = r8.L$0
            io.ktor.utils.io.LookAheadSuspendSession r1 = (io.ktor.utils.io.LookAheadSuspendSession) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0044
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            io.ktor.utils.io.LookAheadSuspendSession r9 = (io.ktor.utils.io.LookAheadSuspendSession) r9
            r1 = r9
        L_0x0030:
            java.nio.ByteBuffer r9 = r1.request(r3, r4)
            if (r9 != 0) goto L_0x0050
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.L$0 = r1
            r8.label = r4
            java.lang.Object r9 = r1.awaitAtLeast(r4, r9)
            if (r9 != r0) goto L_0x0044
            return r0
        L_0x0044:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x004d
            goto L_0x0030
        L_0x004d:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x0050:
            int r5 = r9.remaining()
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r6 = r8.this$0
            io.netty.channel.ChannelHandlerContext r6 = r6.context
            io.netty.buffer.ByteBufAllocator r6 = r6.alloc()
            io.netty.buffer.ByteBuf r6 = r6.buffer(r5)
            int r7 = r6.writerIndex()
            r6.setBytes((int) r7, (java.nio.ByteBuffer) r9)
            int r7 = r7 + r5
            r6.writerIndex(r7)
            r1.consumed(r5)
            kotlin.jvm.internal.Ref$IntRef r9 = r8.$unflushedBytes
            int r7 = r9.element
            int r7 = r7 + r5
            r9.element = r7
            io.ktor.server.netty.NettyApplicationCall r9 = r8.$call
            java.lang.String r5 = "buf"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            java.lang.Object r9 = r9.prepareMessage$ktor_server_netty(r6, r3)
            kotlin.jvm.functions.Function2<io.ktor.utils.io.ByteReadChannel, java.lang.Integer, java.lang.Boolean> r5 = r8.$shouldFlush
            io.ktor.utils.io.ByteReadChannel r6 = r8.$channel
            kotlin.jvm.internal.Ref$IntRef r7 = r8.$unflushedBytes
            int r7 = r7.element
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            java.lang.Object r5 = r5.invoke(r6, r7)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x00d3
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r5 = r8.this$0
            io.netty.channel.ChannelHandlerContext r5 = r5.context
            r5.read()
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r5 = r8.this$0
            io.netty.channel.ChannelHandlerContext r5 = r5.context
            io.netty.channel.ChannelFuture r9 = r5.writeAndFlush(r9)
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r5 = r8.this$0
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r6 = io.ktor.server.netty.cio.NettyHttpResponsePipeline.isDataNotFlushed$FU
            r6.compareAndSet(r5, r4, r3)
            kotlin.jvm.internal.Ref$ObjectRef<io.netty.channel.ChannelFuture> r5 = r8.$lastFuture
            java.lang.String r6 = "future"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)
            r5.element = r9
            io.netty.util.concurrent.Future r9 = (io.netty.util.concurrent.Future) r9
            r5 = r8
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r9 = io.ktor.server.netty.CIOKt.suspendAwait(r9, r5)
            if (r9 != r0) goto L_0x00cd
            return r0
        L_0x00cd:
            kotlin.jvm.internal.Ref$IntRef r9 = r8.$unflushedBytes
            r9.element = r3
            goto L_0x0030
        L_0x00d3:
            kotlin.jvm.internal.Ref$ObjectRef<io.netty.channel.ChannelFuture> r5 = r8.$lastFuture
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r6 = r8.this$0
            io.netty.channel.ChannelHandlerContext r6 = r6.context
            io.netty.channel.ChannelFuture r9 = r6.write(r9)
            java.lang.String r6 = "context.write(message)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)
            r5.element = r9
            io.ktor.server.netty.cio.NettyHttpResponsePipeline r9 = r8.this$0
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r5 = io.ktor.server.netty.cio.NettyHttpResponsePipeline.isDataNotFlushed$FU
            r5.compareAndSet(r9, r3, r4)
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.NettyHttpResponsePipeline$respondWithBigBody$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
