package io.ktor.websocket;

import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.PingPongKt$ponger$1", f = "PingPong.kt", i = {0, 1}, l = {119, 32}, m = "invokeSuspend", n = {"$this$consume$iv$iv", "$this$consume$iv$iv"}, s = {"L$1", "L$1"})
/* compiled from: PingPong.kt */
final class PingPongKt$ponger$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel<Frame.Ping> $channel;
    final /* synthetic */ SendChannel<Frame.Pong> $outgoing;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PingPongKt$ponger$1(Channel<Frame.Ping> channel, SendChannel<? super Frame.Pong> sendChannel, Continuation<? super PingPongKt$ponger$1> continuation) {
        super(2, continuation);
        this.$channel = channel;
        this.$outgoing = sendChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PingPongKt$ponger$1(this.$channel, this.$outgoing, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PingPongKt$ponger$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0095, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005a A[Catch:{ all -> 0x0095 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065 A[Catch:{ all -> 0x0095 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 1
            r3 = 2
            r4 = 0
            if (r1 == 0) goto L_0x003c
            if (r1 == r2) goto L_0x0028
            if (r1 != r3) goto L_0x0020
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r11.L$1
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.SendChannel r6 = (kotlinx.coroutines.channels.SendChannel) r6
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x003a }
            r12 = r6
            goto L_0x004a
        L_0x0020:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0028:
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r11.L$1
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.SendChannel r6 = (kotlinx.coroutines.channels.SendChannel) r6
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x003a }
            r7 = r6
            r6 = r4
            goto L_0x005d
        L_0x003a:
            r12 = move-exception
            goto L_0x0094
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame$Ping> r12 = r11.$channel     // Catch:{ ClosedSendChannelException -> 0x009a }
            r5 = r12
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5     // Catch:{ ClosedSendChannelException -> 0x009a }
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Pong> r12 = r11.$outgoing     // Catch:{ ClosedSendChannelException -> 0x009a }
            kotlinx.coroutines.channels.ChannelIterator r1 = r5.iterator()     // Catch:{ all -> 0x003a }
        L_0x004a:
            r6 = r4
        L_0x004b:
            r11.L$0 = r12     // Catch:{ all -> 0x003a }
            r11.L$1 = r5     // Catch:{ all -> 0x003a }
            r11.L$2 = r1     // Catch:{ all -> 0x003a }
            r11.label = r2     // Catch:{ all -> 0x003a }
            java.lang.Object r7 = r1.hasNext(r11)     // Catch:{ all -> 0x003a }
            if (r7 != r0) goto L_0x005a
            return r0
        L_0x005a:
            r10 = r7
            r7 = r12
            r12 = r10
        L_0x005d:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x003a }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x003a }
            if (r12 == 0) goto L_0x008e
            java.lang.Object r12 = r1.next()     // Catch:{ all -> 0x003a }
            io.ktor.websocket.Frame$Ping r12 = (io.ktor.websocket.Frame.Ping) r12     // Catch:{ all -> 0x003a }
            org.slf4j.Logger r8 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()     // Catch:{ all -> 0x003a }
            java.lang.String r9 = "Received ping message, sending pong message"
            r8.trace(r9)     // Catch:{ all -> 0x003a }
            io.ktor.websocket.Frame$Pong r8 = new io.ktor.websocket.Frame$Pong     // Catch:{ all -> 0x003a }
            byte[] r12 = r12.getData()     // Catch:{ all -> 0x003a }
            r8.<init>((byte[]) r12, (kotlinx.coroutines.DisposableHandle) r4, (int) r3, (kotlin.jvm.internal.DefaultConstructorMarker) r4)     // Catch:{ all -> 0x003a }
            r11.L$0 = r7     // Catch:{ all -> 0x003a }
            r11.L$1 = r5     // Catch:{ all -> 0x003a }
            r11.L$2 = r1     // Catch:{ all -> 0x003a }
            r11.label = r3     // Catch:{ all -> 0x003a }
            java.lang.Object r12 = r7.send(r8, r11)     // Catch:{ all -> 0x003a }
            if (r12 != r0) goto L_0x008c
            return r0
        L_0x008c:
            r12 = r7
            goto L_0x004b
        L_0x008e:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003a }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)     // Catch:{ ClosedSendChannelException -> 0x009a }
            goto L_0x009a
        L_0x0094:
            throw r12     // Catch:{ all -> 0x0095 }
        L_0x0095:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r12)     // Catch:{ ClosedSendChannelException -> 0x009a }
            throw r0     // Catch:{ ClosedSendChannelException -> 0x009a }
        L_0x009a:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.PingPongKt$ponger$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
