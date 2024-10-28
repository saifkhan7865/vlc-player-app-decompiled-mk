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
@DebugMetadata(c = "io.ktor.websocket.PingPongKt$pinger$1$rc$1", f = "PingPong.kt", i = {}, l = {75, 79}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PingPong.kt */
final class PingPongKt$pinger$1$rc$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel<Frame.Pong> $channel;
    final /* synthetic */ SendChannel<Frame> $outgoing;
    final /* synthetic */ String $pingMessage;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PingPongKt$pinger$1$rc$1(SendChannel<? super Frame> sendChannel, String str, Channel<Frame.Pong> channel, Continuation<? super PingPongKt$pinger$1$rc$1> continuation) {
        super(2, continuation);
        this.$outgoing = sendChannel;
        this.$pingMessage = str;
        this.$channel = channel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PingPongKt$pinger$1$rc$1(this.$outgoing, this.$pingMessage, this.$channel, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PingPongKt$pinger$1$rc$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x001f
            if (r1 == r4) goto L_0x001b
            if (r1 != r3) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0071
        L_0x0013:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0063
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r9)
            org.slf4j.Logger r9 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.String r1 = "WebSocket Pinger: sending ping frame"
            r9.trace(r1)
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame> r9 = r8.$outgoing
            io.ktor.websocket.Frame$Ping r1 = new io.ktor.websocket.Frame$Ping
            java.lang.String r5 = r8.$pingMessage
            java.nio.charset.Charset r6 = kotlin.text.Charsets.ISO_8859_1
            java.nio.charset.Charset r7 = kotlin.text.Charsets.UTF_8
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x0040
            byte[] r5 = kotlin.text.StringsKt.encodeToByteArray(r5)
            goto L_0x0054
        L_0x0040:
            java.nio.charset.CharsetEncoder r6 = r6.newEncoder()
            java.lang.String r7 = "charset.newEncoder()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r7 = r5
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            int r5 = r5.length()
            byte[] r5 = io.ktor.utils.io.charsets.CharsetJVMKt.encodeToByteArray(r6, r7, r2, r5)
        L_0x0054:
            r1.<init>((byte[]) r5)
            r5 = r8
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r8.label = r4
            java.lang.Object r9 = r9.send(r1, r5)
            if (r9 != r0) goto L_0x0063
            return r0
        L_0x0063:
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame$Pong> r9 = r8.$channel
            r1 = r8
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r8.label = r3
            java.lang.Object r9 = r9.receive(r1)
            if (r9 != r0) goto L_0x0071
            return r0
        L_0x0071:
            io.ktor.websocket.Frame$Pong r9 = (io.ktor.websocket.Frame.Pong) r9
            byte[] r1 = r9.getData()
            java.nio.charset.Charset r4 = kotlin.text.Charsets.ISO_8859_1
            int r5 = r1.length
            java.lang.String r6 = new java.lang.String
            r6.<init>(r1, r2, r5, r4)
            java.lang.String r1 = r8.$pingMessage
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r1)
            if (r1 == 0) goto L_0x009f
            org.slf4j.Logger r0 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "WebSocket Pinger: received valid pong frame "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.trace(r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x009f:
            org.slf4j.Logger r1 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "WebSocket Pinger: received invalid pong frame "
            r4.<init>(r5)
            r4.append(r9)
            java.lang.String r9 = ", continue waiting"
            r4.append(r9)
            java.lang.String r9 = r4.toString()
            r1.trace(r9)
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.PingPongKt$pinger$1$rc$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
