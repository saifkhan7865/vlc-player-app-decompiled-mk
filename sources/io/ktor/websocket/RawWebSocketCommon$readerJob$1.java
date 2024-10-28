package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.RawWebSocketCommon$readerJob$1", f = "RawWebSocketCommon.kt", i = {2, 3}, l = {88, 92, 95, 99}, m = "invokeSuspend", n = {"cause", "cause"}, s = {"L$0", "L$0"})
/* compiled from: RawWebSocketCommon.kt */
final class RawWebSocketCommon$readerJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RawWebSocketCommon this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RawWebSocketCommon$readerJob$1(RawWebSocketCommon rawWebSocketCommon, Continuation<? super RawWebSocketCommon$readerJob$1> continuation) {
        super(2, continuation);
        this.this$0 = rawWebSocketCommon;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RawWebSocketCommon$readerJob$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RawWebSocketCommon$readerJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0065 A[Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a, all -> 0x002f }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0072 A[Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a, all -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0098 A[RETURN] */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r1 == 0) goto L_0x0045
            if (r1 == r5) goto L_0x0036
            if (r1 == r4) goto L_0x0032
            if (r1 == r3) goto L_0x0026
            if (r1 != r2) goto L_0x001e
            java.lang.Object r0 = r10.L$0
            io.ktor.websocket.ProtocolViolationException r0 = (io.ktor.websocket.ProtocolViolationException) r0
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x002f }
            goto L_0x00e9
        L_0x001e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0026:
            java.lang.Object r0 = r10.L$0
            io.ktor.websocket.FrameTooBigException r0 = (io.ktor.websocket.FrameTooBigException) r0
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x002f }
            goto L_0x011a
        L_0x002f:
            r11 = move-exception
            goto L_0x0129
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            goto L_0x0048
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            goto L_0x0066
        L_0x003a:
            r11 = move-exception
            goto L_0x0099
        L_0x003c:
            r11 = move-exception
            goto L_0x00ba
        L_0x003f:
            r11 = move-exception
            goto L_0x00c4
        L_0x0042:
            r11 = move-exception
            goto L_0x00f5
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r11)
        L_0x0048:
            io.ktor.websocket.RawWebSocketCommon r11 = r10.this$0     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            io.ktor.utils.io.ByteReadChannel r11 = r11.input     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            io.ktor.websocket.RawWebSocketCommon r1 = r10.this$0     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            long r7 = r1.getMaxFrameSize()     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            io.ktor.websocket.RawWebSocketCommon r1 = r10.this$0     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            int r1 = r1.lastOpcode     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            r9 = r10
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            r10.label = r5     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            java.lang.Object r11 = io.ktor.websocket.RawWebSocketCommonKt.readFrame(r11, r7, r1, r9)     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            if (r11 != r0) goto L_0x0066
            return r0
        L_0x0066:
            io.ktor.websocket.Frame r11 = (io.ktor.websocket.Frame) r11     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            io.ktor.websocket.FrameType r1 = r11.getFrameType()     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            boolean r1 = r1.getControlFrame()     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            if (r1 != 0) goto L_0x0087
            io.ktor.websocket.RawWebSocketCommon r1 = r10.this$0     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            boolean r7 = r11.getFin()     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            if (r7 == 0) goto L_0x007c
            r7 = 0
            goto L_0x0084
        L_0x007c:
            io.ktor.websocket.FrameType r7 = r11.getFrameType()     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            int r7 = r7.getOpcode()     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
        L_0x0084:
            r1.lastOpcode = r7     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
        L_0x0087:
            io.ktor.websocket.RawWebSocketCommon r1 = r10.this$0     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            kotlinx.coroutines.channels.Channel r1 = r1._incoming     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            r7 = r10
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            r10.label = r4     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            java.lang.Object r11 = r1.send(r11, r7)     // Catch:{ FrameTooBigException -> 0x0042, ProtocolViolationException -> 0x003f, CancellationException -> 0x003c, EOFException | ClosedReceiveChannelException -> 0x00ae, ChannelIOException -> 0x00a3, all -> 0x003a }
            if (r11 != r0) goto L_0x0048
            return r0
        L_0x0099:
            io.ktor.websocket.RawWebSocketCommon r0 = r10.this$0     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.Channel r0 = r0._incoming     // Catch:{ all -> 0x002f }
            r0.close(r11)     // Catch:{ all -> 0x002f }
            throw r11     // Catch:{ all -> 0x002f }
        L_0x00a3:
            io.ktor.websocket.RawWebSocketCommon r11 = r10.this$0     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.Channel r11 = r11._incoming     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r11, (java.util.concurrent.CancellationException) r6, (int) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x002f }
        L_0x00ae:
            io.ktor.websocket.RawWebSocketCommon r11 = r10.this$0
            kotlinx.coroutines.channels.Channel r11 = r11._incoming
            kotlinx.coroutines.channels.SendChannel r11 = (kotlinx.coroutines.channels.SendChannel) r11
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r11, r6, r5, r6)
            goto L_0x0126
        L_0x00ba:
            io.ktor.websocket.RawWebSocketCommon r0 = r10.this$0     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.Channel r0 = r0._incoming     // Catch:{ all -> 0x002f }
            r0.cancel((java.util.concurrent.CancellationException) r11)     // Catch:{ all -> 0x002f }
            goto L_0x00ae
        L_0x00c4:
            io.ktor.websocket.RawWebSocketCommon r1 = r10.this$0     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.SendChannel r1 = r1.getOutgoing()     // Catch:{ all -> 0x002f }
            io.ktor.websocket.Frame$Close r3 = new io.ktor.websocket.Frame$Close     // Catch:{ all -> 0x002f }
            io.ktor.websocket.CloseReason r4 = new io.ktor.websocket.CloseReason     // Catch:{ all -> 0x002f }
            io.ktor.websocket.CloseReason$Codes r7 = io.ktor.websocket.CloseReason.Codes.PROTOCOL_ERROR     // Catch:{ all -> 0x002f }
            java.lang.String r8 = r11.getMessage()     // Catch:{ all -> 0x002f }
            r4.<init>((io.ktor.websocket.CloseReason.Codes) r7, (java.lang.String) r8)     // Catch:{ all -> 0x002f }
            r3.<init>((io.ktor.websocket.CloseReason) r4)     // Catch:{ all -> 0x002f }
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x002f }
            r10.L$0 = r11     // Catch:{ all -> 0x002f }
            r10.label = r2     // Catch:{ all -> 0x002f }
            java.lang.Object r1 = r1.send(r3, r4)     // Catch:{ all -> 0x002f }
            if (r1 != r0) goto L_0x00e8
            return r0
        L_0x00e8:
            r0 = r11
        L_0x00e9:
            io.ktor.websocket.RawWebSocketCommon r11 = r10.this$0     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.Channel r11 = r11._incoming     // Catch:{ all -> 0x002f }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x002f }
            r11.close(r0)     // Catch:{ all -> 0x002f }
            goto L_0x00ae
        L_0x00f5:
            io.ktor.websocket.RawWebSocketCommon r1 = r10.this$0     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.SendChannel r1 = r1.getOutgoing()     // Catch:{ all -> 0x002f }
            io.ktor.websocket.Frame$Close r2 = new io.ktor.websocket.Frame$Close     // Catch:{ all -> 0x002f }
            io.ktor.websocket.CloseReason r4 = new io.ktor.websocket.CloseReason     // Catch:{ all -> 0x002f }
            io.ktor.websocket.CloseReason$Codes r7 = io.ktor.websocket.CloseReason.Codes.TOO_BIG     // Catch:{ all -> 0x002f }
            java.lang.String r8 = r11.getMessage()     // Catch:{ all -> 0x002f }
            r4.<init>((io.ktor.websocket.CloseReason.Codes) r7, (java.lang.String) r8)     // Catch:{ all -> 0x002f }
            r2.<init>((io.ktor.websocket.CloseReason) r4)     // Catch:{ all -> 0x002f }
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x002f }
            r10.L$0 = r11     // Catch:{ all -> 0x002f }
            r10.label = r3     // Catch:{ all -> 0x002f }
            java.lang.Object r1 = r1.send(r2, r4)     // Catch:{ all -> 0x002f }
            if (r1 != r0) goto L_0x0119
            return r0
        L_0x0119:
            r0 = r11
        L_0x011a:
            io.ktor.websocket.RawWebSocketCommon r11 = r10.this$0     // Catch:{ all -> 0x002f }
            kotlinx.coroutines.channels.Channel r11 = r11._incoming     // Catch:{ all -> 0x002f }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x002f }
            r11.close(r0)     // Catch:{ all -> 0x002f }
            goto L_0x00ae
        L_0x0126:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0129:
            io.ktor.websocket.RawWebSocketCommon r0 = r10.this$0
            kotlinx.coroutines.channels.Channel r0 = r0._incoming
            kotlinx.coroutines.channels.SendChannel r0 = (kotlinx.coroutines.channels.SendChannel) r0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r6, r5, r6)
            goto L_0x0136
        L_0x0135:
            throw r11
        L_0x0136:
            goto L_0x0135
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommon$readerJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
