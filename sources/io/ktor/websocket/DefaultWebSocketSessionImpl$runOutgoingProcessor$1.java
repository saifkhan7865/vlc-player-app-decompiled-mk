package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.DefaultWebSocketSessionImpl$runOutgoingProcessor$1", f = "DefaultWebSocketSession.kt", i = {}, l = {236, 247, 247, 247, 240, 247, 247, 244, 247, 247}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DefaultWebSocketSession.kt */
final class DefaultWebSocketSessionImpl$runOutgoingProcessor$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ DefaultWebSocketSessionImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultWebSocketSessionImpl$runOutgoingProcessor$1(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, Continuation<? super DefaultWebSocketSessionImpl$runOutgoingProcessor$1> continuation) {
        super(2, continuation);
        this.this$0 = defaultWebSocketSessionImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DefaultWebSocketSessionImpl$runOutgoingProcessor$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultWebSocketSessionImpl$runOutgoingProcessor$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004d, code lost:
        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r10.this$0.outgoingToBeProcessed, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
        r10.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close$default(r10.this$0.raw, (io.ktor.websocket.CloseReason) null, r10, 1, (java.lang.Object) null) != r0) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r10.this$0.outgoingToBeProcessed.cancel(kotlinx.coroutines.ExceptionsKt.CancellationException("Failed to send frame", r11));
        r10.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008b, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.closeExceptionally(r10.this$0.raw, r11, r10) == r0) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008e, code lost:
        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r10.this$0.outgoingToBeProcessed, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
        r10.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00aa, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close$default(r10.this$0.raw, (io.ktor.websocket.CloseReason) null, r10, 1, (java.lang.Object) null) != r0) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ac, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ad, code lost:
        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r10.this$0.outgoingToBeProcessed, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
        r10.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c8, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close$default(r10.this$0.raw, (io.ktor.websocket.CloseReason) null, r10, 1, (java.lang.Object) null) == r0) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ca, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r10.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e3, code lost:
        if (io.ktor.websocket.DefaultWebSocketSessionImpl.sendCloseSequence$default(r10.this$0, new io.ktor.websocket.CloseReason(io.ktor.websocket.CloseReason.Codes.NORMAL, ""), (java.lang.Throwable) null, r10, 2, (java.lang.Object) null) == r0) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e5, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e6, code lost:
        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r10.this$0.outgoingToBeProcessed, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
        r10.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0101, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close$default(r10.this$0.raw, (io.ktor.websocket.CloseReason) null, r10, 1, (java.lang.Object) null) != r0) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0103, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0104, code lost:
        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r10.this$0.outgoingToBeProcessed, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
        r10.L$0 = r11;
        r10.label = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0122, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close$default(r10.this$0.raw, (io.ktor.websocket.CloseReason) null, r10, 1, (java.lang.Object) null) == r0) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0124, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0125, code lost:
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0126, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0127, code lost:
        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r10.this$0.outgoingToBeProcessed, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
        r10.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0142, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close$default(r10.this$0.raw, (io.ktor.websocket.CloseReason) null, r10, 1, (java.lang.Object) null) == r0) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0144, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0145, code lost:
        kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r10.this$0.outgoingToBeProcessed, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
        r10.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0160, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close$default(r10.this$0.raw, (io.ktor.websocket.CloseReason) null, r10, 1, (java.lang.Object) null) == r0) goto L_0x0162;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0162, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0165, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x001c, B:10:0x002d, B:34:0x00cb] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00cb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 1
            r3 = 0
            switch(r1) {
                case 0: goto L_0x003c;
                case 1: goto L_0x002d;
                case 2: goto L_0x0028;
                case 3: goto L_0x0028;
                case 4: goto L_0x0028;
                case 5: goto L_0x0020;
                case 6: goto L_0x0028;
                case 7: goto L_0x0028;
                case 8: goto L_0x001c;
                case 9: goto L_0x0028;
                case 10: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0013:
            java.lang.Object r0 = r10.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0126
        L_0x001c:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0025 }
            goto L_0x008e
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0025 }
            goto L_0x00e6
        L_0x0025:
            r11 = move-exception
            goto L_0x0104
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0163
        L_0x002d:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ ClosedSendChannelException -> 0x0039, ClosedReceiveChannelException -> 0x0036, CancellationException -> 0x00cb, ChannelIOException -> 0x0033, all -> 0x0031 }
            goto L_0x004d
        L_0x0031:
            r11 = move-exception
            goto L_0x006b
        L_0x0033:
            goto L_0x00ad
        L_0x0036:
            goto L_0x0127
        L_0x0039:
            goto L_0x0145
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0     // Catch:{ ClosedSendChannelException -> 0x0039, ClosedReceiveChannelException -> 0x0036, CancellationException -> 0x00cb, ChannelIOException -> 0x0033, all -> 0x0031 }
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ ClosedSendChannelException -> 0x0039, ClosedReceiveChannelException -> 0x0036, CancellationException -> 0x00cb, ChannelIOException -> 0x0033, all -> 0x0031 }
            r10.label = r2     // Catch:{ ClosedSendChannelException -> 0x0039, ClosedReceiveChannelException -> 0x0036, CancellationException -> 0x00cb, ChannelIOException -> 0x0033, all -> 0x0031 }
            java.lang.Object r11 = r11.outgoingProcessorLoop(r1)     // Catch:{ ClosedSendChannelException -> 0x0039, ClosedReceiveChannelException -> 0x0036, CancellationException -> 0x00cb, ChannelIOException -> 0x0033, all -> 0x0031 }
            if (r11 != r0) goto L_0x004d
            return r0
        L_0x004d:
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            kotlinx.coroutines.channels.Channel r11 = r11.outgoingToBeProcessed
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r11, (java.util.concurrent.CancellationException) r3, (int) r2, (java.lang.Object) r3)
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            io.ktor.websocket.WebSocketSession r11 = r11.raw
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 2
            r10.label = r4
            java.lang.Object r11 = io.ktor.websocket.WebSocketSessionKt.close$default(r11, r3, r1, r2, r3)
            if (r11 != r0) goto L_0x0163
            return r0
        L_0x006b:
            io.ktor.websocket.DefaultWebSocketSessionImpl r1 = r10.this$0     // Catch:{ all -> 0x0025 }
            kotlinx.coroutines.channels.Channel r1 = r1.outgoingToBeProcessed     // Catch:{ all -> 0x0025 }
            java.lang.String r4 = "Failed to send frame"
            java.util.concurrent.CancellationException r4 = kotlinx.coroutines.ExceptionsKt.CancellationException(r4, r11)     // Catch:{ all -> 0x0025 }
            r1.cancel((java.util.concurrent.CancellationException) r4)     // Catch:{ all -> 0x0025 }
            io.ktor.websocket.DefaultWebSocketSessionImpl r1 = r10.this$0     // Catch:{ all -> 0x0025 }
            io.ktor.websocket.WebSocketSession r1 = r1.raw     // Catch:{ all -> 0x0025 }
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x0025 }
            r5 = 8
            r10.label = r5     // Catch:{ all -> 0x0025 }
            java.lang.Object r11 = io.ktor.websocket.WebSocketSessionKt.closeExceptionally(r1, r11, r4)     // Catch:{ all -> 0x0025 }
            if (r11 != r0) goto L_0x008e
            return r0
        L_0x008e:
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            kotlinx.coroutines.channels.Channel r11 = r11.outgoingToBeProcessed
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r11, (java.util.concurrent.CancellationException) r3, (int) r2, (java.lang.Object) r3)
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            io.ktor.websocket.WebSocketSession r11 = r11.raw
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 9
            r10.label = r4
            java.lang.Object r11 = io.ktor.websocket.WebSocketSessionKt.close$default(r11, r3, r1, r2, r3)
            if (r11 != r0) goto L_0x0163
            return r0
        L_0x00ad:
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            kotlinx.coroutines.channels.Channel r11 = r11.outgoingToBeProcessed
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r11, (java.util.concurrent.CancellationException) r3, (int) r2, (java.lang.Object) r3)
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            io.ktor.websocket.WebSocketSession r11 = r11.raw
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 7
            r10.label = r4
            java.lang.Object r11 = io.ktor.websocket.WebSocketSessionKt.close$default(r11, r3, r1, r2, r3)
            if (r11 != r0) goto L_0x0163
            return r0
        L_0x00cb:
            io.ktor.websocket.DefaultWebSocketSessionImpl r4 = r10.this$0     // Catch:{ all -> 0x0025 }
            io.ktor.websocket.CloseReason r5 = new io.ktor.websocket.CloseReason     // Catch:{ all -> 0x0025 }
            io.ktor.websocket.CloseReason$Codes r11 = io.ktor.websocket.CloseReason.Codes.NORMAL     // Catch:{ all -> 0x0025 }
            java.lang.String r1 = ""
            r5.<init>((io.ktor.websocket.CloseReason.Codes) r11, (java.lang.String) r1)     // Catch:{ all -> 0x0025 }
            r7 = r10
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch:{ all -> 0x0025 }
            r11 = 5
            r10.label = r11     // Catch:{ all -> 0x0025 }
            r6 = 0
            r8 = 2
            r9 = 0
            java.lang.Object r11 = io.ktor.websocket.DefaultWebSocketSessionImpl.sendCloseSequence$default(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0025 }
            if (r11 != r0) goto L_0x00e6
            return r0
        L_0x00e6:
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            kotlinx.coroutines.channels.Channel r11 = r11.outgoingToBeProcessed
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r11, (java.util.concurrent.CancellationException) r3, (int) r2, (java.lang.Object) r3)
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            io.ktor.websocket.WebSocketSession r11 = r11.raw
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 6
            r10.label = r4
            java.lang.Object r11 = io.ktor.websocket.WebSocketSessionKt.close$default(r11, r3, r1, r2, r3)
            if (r11 != r0) goto L_0x0163
            return r0
        L_0x0104:
            io.ktor.websocket.DefaultWebSocketSessionImpl r1 = r10.this$0
            kotlinx.coroutines.channels.Channel r1 = r1.outgoingToBeProcessed
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r1, (java.util.concurrent.CancellationException) r3, (int) r2, (java.lang.Object) r3)
            io.ktor.websocket.DefaultWebSocketSessionImpl r1 = r10.this$0
            io.ktor.websocket.WebSocketSession r1 = r1.raw
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r10.L$0 = r11
            r5 = 10
            r10.label = r5
            java.lang.Object r1 = io.ktor.websocket.WebSocketSessionKt.close$default(r1, r3, r4, r2, r3)
            if (r1 != r0) goto L_0x0125
            return r0
        L_0x0125:
            r0 = r11
        L_0x0126:
            throw r0
        L_0x0127:
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            kotlinx.coroutines.channels.Channel r11 = r11.outgoingToBeProcessed
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r11, (java.util.concurrent.CancellationException) r3, (int) r2, (java.lang.Object) r3)
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            io.ktor.websocket.WebSocketSession r11 = r11.raw
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 4
            r10.label = r4
            java.lang.Object r11 = io.ktor.websocket.WebSocketSessionKt.close$default(r11, r3, r1, r2, r3)
            if (r11 != r0) goto L_0x0163
            return r0
        L_0x0145:
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            kotlinx.coroutines.channels.Channel r11 = r11.outgoingToBeProcessed
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r11, (java.util.concurrent.CancellationException) r3, (int) r2, (java.lang.Object) r3)
            io.ktor.websocket.DefaultWebSocketSessionImpl r11 = r10.this$0
            io.ktor.websocket.WebSocketSession r11 = r11.raw
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r4 = 3
            r10.label = r4
            java.lang.Object r11 = io.ktor.websocket.WebSocketSessionKt.close$default(r11, r3, r1, r2, r3)
            if (r11 != r0) goto L_0x0163
            return r0
        L_0x0163:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl$runOutgoingProcessor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
