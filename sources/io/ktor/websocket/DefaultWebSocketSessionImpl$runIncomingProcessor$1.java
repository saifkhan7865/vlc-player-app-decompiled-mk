package io.ktor.websocket;

import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.DefaultWebSocketSessionImpl$runIncomingProcessor$1", f = "DefaultWebSocketSession.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7}, l = {352, 172, 226, 178, 179, 181, 196, 211, 226, 226, 226, 226}, m = "invokeSuspend", n = {"$this$launch", "firstFrame", "frameBody", "closeFramePresented", "$this$consume$iv$iv", "frameBody", "closeFramePresented", "$this$consume$iv$iv", "$this$launch", "firstFrame", "frameBody", "closeFramePresented", "$this$consume$iv$iv", "$this$launch", "firstFrame", "frameBody", "closeFramePresented", "$this$consume$iv$iv", "$this$launch", "firstFrame", "frameBody", "closeFramePresented", "$this$consume$iv$iv", "frame", "$this$launch", "firstFrame", "frameBody", "closeFramePresented", "$this$consume$iv$iv", "$this$launch", "firstFrame", "frameBody", "closeFramePresented", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$6", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$6", "L$0", "L$1", "L$2", "L$3", "L$6", "L$0", "L$1", "L$2", "L$3", "L$6", "L$8", "L$0", "L$1", "L$2", "L$3", "L$6", "L$0", "L$1", "L$2", "L$3", "L$6"})
/* compiled from: DefaultWebSocketSession.kt */
final class DefaultWebSocketSessionImpl$runIncomingProcessor$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SendChannel<Frame.Ping> $ponger;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ DefaultWebSocketSessionImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultWebSocketSessionImpl$runIncomingProcessor$1(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, SendChannel<? super Frame.Ping> sendChannel, Continuation<? super DefaultWebSocketSessionImpl$runIncomingProcessor$1> continuation) {
        super(2, continuation);
        this.this$0 = defaultWebSocketSessionImpl;
        this.$ponger = sendChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DefaultWebSocketSessionImpl$runIncomingProcessor$1 defaultWebSocketSessionImpl$runIncomingProcessor$1 = new DefaultWebSocketSessionImpl$runIncomingProcessor$1(this.this$0, this.$ponger, continuation);
        defaultWebSocketSessionImpl$runIncomingProcessor$1.L$0 = obj;
        return defaultWebSocketSessionImpl$runIncomingProcessor$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultWebSocketSessionImpl$runIncomingProcessor$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v79, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: kotlinx.coroutines.channels.ReceiveChannel<io.ktor.websocket.Frame>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v80, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: kotlin.jvm.internal.Ref$BooleanRef} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v81, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: kotlin.jvm.internal.Ref$ObjectRef} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0321, code lost:
        r0 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r11;
        r11 = r12;
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0329, code lost:
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x032c, code lost:
        r4 = r11.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r4);
        io.ktor.utils.io.core.OutputKt.writeFully$default(r4, r0.getData(), 0, 0, 6, (java.lang.Object) null);
        r22 = io.ktor.websocket.Frame.Companion;
        r0 = r12.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r24 = r0.getFrameType();
        r0 = r11.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r25 = io.ktor.utils.io.core.StringsKt.readBytes$default(r0.build(), 0, 1, (java.lang.Object) null);
        r0 = r12.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r26 = r0.getRsv1();
        r0 = r12.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r27 = r0.getRsv2();
        r0 = r12.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r0 = r22.byType(true, r24, r25, r26, r27, r0.getRsv3());
        r12.element = null;
        r4 = r9.filtered;
        r0 = r9.processIncomingExtensions(r0);
        r1.L$0 = r13;
        r1.L$1 = r12;
        r1.L$2 = r11;
        r1.L$3 = r10;
        r1.L$4 = r9;
        r1.L$5 = r8;
        r1.L$6 = r7;
        r1.L$7 = r6;
        r1.L$8 = null;
        r1.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x03b0, code lost:
        if (r4.send(r0, r1) != r2) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x03b2, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x03b3, code lost:
        r4 = 1;
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x03bc, code lost:
        kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r1.$ponger, (java.lang.Throwable) null, 1, (java.lang.Object) null);
        r0 = r10.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x03c7, code lost:
        if (r0 == null) goto L_0x03ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x03c9, code lost:
        r0.release();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x03ce, code lost:
        kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r1.this$0.filtered, (java.lang.Throwable) null, 1, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x03db, code lost:
        if (r9.element != false) goto L_0x0506;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x03dd, code lost:
        r1.L$0 = null;
        r1.L$1 = null;
        r1.L$2 = null;
        r1.L$3 = null;
        r1.L$4 = null;
        r1.L$5 = null;
        r1.L$6 = null;
        r1.L$7 = null;
        r1.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0403, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close(r1.this$0, new io.ktor.websocket.CloseReason(io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY, "Connection was closed without close frame"), (kotlin.coroutines.Continuation<? super kotlin.Unit>) r1) != r2) goto L_0x0506;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0405, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0508, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x012b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x012c, code lost:
        r4 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0158, code lost:
        r1.L$0 = r0;
        r1.L$1 = r6;
        r1.L$2 = r10;
        r1.L$3 = r9;
        r1.L$4 = r8;
        r1.L$5 = r11;
        r1.L$6 = r7;
        r1.L$7 = r12;
        r1.L$8 = r5;
        r1.label = r4;
        r14 = r12.hasNext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0170, code lost:
        if (r14 != r2) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0172, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0173, code lost:
        r29 = r12;
        r12 = r0;
        r0 = r29;
        r30 = r11;
        r11 = r6;
        r6 = r7;
        r7 = r30;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0184, code lost:
        if (((java.lang.Boolean) r14).booleanValue() == false) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0186, code lost:
        r14 = r0.next();
        io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER().trace("WebSocketSession(" + r12 + ") receiving frame " + r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01ae, code lost:
        if ((r14 instanceof io.ktor.websocket.Frame.Close) == false) goto L_0x023b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01b8, code lost:
        if (r8.getOutgoing().isClosedForSend() != false) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01ba, code lost:
        r0 = r8.getOutgoing();
        r5 = io.ktor.websocket.FrameCommonKt.readReason((io.ktor.websocket.Frame.Close) r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01c6, code lost:
        if (r5 != null) goto L_0x01cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01c8, code lost:
        r5 = io.ktor.websocket.DefaultWebSocketSessionKt.NORMAL_CLOSE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01cc, code lost:
        r4 = new io.ktor.websocket.Frame.Close(r5);
        r1.L$0 = r10;
        r1.L$1 = r9;
        r1.L$2 = r6;
        r1.L$3 = null;
        r1.L$4 = null;
        r1.L$5 = null;
        r1.L$6 = null;
        r1.L$7 = null;
        r1.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01e7, code lost:
        if (r0.send(r4, r1) != r2) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01e9, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01ea, code lost:
        r9.element = true;
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01f2, code lost:
        kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r1.$ponger, (java.lang.Throwable) null, 1, (java.lang.Object) null);
        r5 = r10.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01fc, code lost:
        if (r5 == null) goto L_0x0203;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01fe, code lost:
        r5.release();
        r5 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0203, code lost:
        kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r1.this$0.filtered, (java.lang.Throwable) null, 1, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0210, code lost:
        if (r9.element != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0212, code lost:
        r1.L$0 = r0;
        r1.L$1 = null;
        r1.L$2 = null;
        r1.L$3 = null;
        r1.L$4 = null;
        r1.L$5 = null;
        r1.L$6 = null;
        r1.L$7 = null;
        r1.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0237, code lost:
        if (io.ktor.websocket.WebSocketSessionKt.close(r1.this$0, new io.ktor.websocket.CloseReason(io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY, "Connection was closed without close frame"), (kotlin.coroutines.Continuation<? super kotlin.Unit>) r1) != r2) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0239, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x023d, code lost:
        if ((r14 instanceof io.ktor.websocket.Frame.Pong) == false) goto L_0x0262;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x023f, code lost:
        r4 = (kotlinx.coroutines.channels.SendChannel) r8.pinger;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0243, code lost:
        if (r4 == null) goto L_0x0280;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0245, code lost:
        r1.L$0 = r12;
        r1.L$1 = r11;
        r1.L$2 = r10;
        r1.L$3 = r9;
        r1.L$4 = r8;
        r1.L$5 = r7;
        r1.L$6 = r6;
        r1.L$7 = r0;
        r1.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x025c, code lost:
        if (r4.send(r14, r1) != r2) goto L_0x025f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x025e, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x025f, code lost:
        r4 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0264, code lost:
        if ((r14 instanceof io.ktor.websocket.Frame.Ping) == false) goto L_0x028d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0266, code lost:
        r1.L$0 = r12;
        r1.L$1 = r11;
        r1.L$2 = r10;
        r1.L$3 = r9;
        r1.L$4 = r8;
        r1.L$5 = r7;
        r1.L$6 = r6;
        r1.L$7 = r0;
        r1.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x027d, code lost:
        if (r7.send(r14, r1) != r2) goto L_0x0280;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x027f, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0280, code lost:
        r29 = r12;
        r12 = r0;
        r0 = r29;
        r30 = r7;
        r7 = r6;
        r6 = r11;
        r11 = r30;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x028d, code lost:
        r1.L$0 = r12;
        r1.L$1 = r11;
        r1.L$2 = r10;
        r1.L$3 = r9;
        r1.L$4 = r8;
        r1.L$5 = r7;
        r1.L$6 = r6;
        r1.L$7 = r0;
        r1.L$8 = r14;
        r1.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02aa, code lost:
        if (r8.checkMaxFrameSize(r10.element, r14, r1) != r2) goto L_0x02ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x02ac, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x02ad, code lost:
        r5 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
        r10 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r0;
        r0 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x02bb, code lost:
        if (r0.getFin() != false) goto L_0x02f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02bf, code lost:
        if (r12.element != null) goto L_0x02c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        kotlin.ResultKt.throwOnFailure(r32);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02c1, code lost:
        r12.element = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x02c5, code lost:
        if (r11.element != null) goto L_0x02d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02c7, code lost:
        r11.element = new io.ktor.utils.io.core.BytePacketBuilder((io.ktor.utils.io.pool.ObjectPool) null, 1, (kotlin.jvm.internal.DefaultConstructorMarker) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02d0, code lost:
        r4 = r11.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r4);
        io.ktor.utils.io.core.OutputKt.writeFully$default(r4, r0.getData(), 0, 0, 6, (java.lang.Object) null);
        r0 = r13;
        r13 = r5;
        r29 = r12;
        r12 = r6;
        r6 = r29;
        r30 = r11;
        r11 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r30;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02fa, code lost:
        if (r12.element != null) goto L_0x032c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02fc, code lost:
        r4 = r9.filtered;
        r0 = r9.processIncomingExtensions(r0);
        r1.L$0 = r13;
        r1.L$1 = r12;
        r1.L$2 = r11;
        r1.L$3 = r10;
        r1.L$4 = r9;
        r1.L$5 = r8;
        r1.L$6 = r7;
        r1.L$7 = r6;
        r1.L$8 = null;
        r1.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x031e, code lost:
        if (r4.send(r0, r1) != r2) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0320, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r32) {
        /*
            r31 = this;
            r1 = r31
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            java.lang.String r3 = "Connection was closed without close frame"
            r4 = 1
            r5 = 0
            switch(r0) {
                case 0: goto L_0x012f;
                case 1: goto L_0x0104;
                case 2: goto L_0x00ef;
                case 3: goto L_0x00e6;
                case 4: goto L_0x00c0;
                case 5: goto L_0x009b;
                case 6: goto L_0x006b;
                case 7: goto L_0x004a;
                case 8: goto L_0x0025;
                case 9: goto L_0x0020;
                case 10: goto L_0x0020;
                case 11: goto L_0x0020;
                case 12: goto L_0x0017;
                default: goto L_0x000f;
            }
        L_0x000f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0017:
            java.lang.Object r0 = r1.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r32)
            goto L_0x04b8
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r32)
            goto L_0x0506
        L_0x0025:
            java.lang.Object r0 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r1.L$4
            io.ktor.websocket.DefaultWebSocketSessionImpl r8 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r8
            java.lang.Object r9 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r32)     // Catch:{ all -> 0x012b }
            goto L_0x0329
        L_0x004a:
            java.lang.Object r0 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r1.L$4
            io.ktor.websocket.DefaultWebSocketSessionImpl r8 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r8
            java.lang.Object r9 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            goto L_0x0045
        L_0x006b:
            java.lang.Object r0 = r1.L$8
            io.ktor.websocket.Frame r0 = (io.ktor.websocket.Frame) r0
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            java.lang.Object r9 = r1.L$4
            io.ktor.websocket.DefaultWebSocketSessionImpl r9 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.CoroutineScope r13 = (kotlinx.coroutines.CoroutineScope) r13
            kotlin.ResultKt.throwOnFailure(r32)     // Catch:{ all -> 0x0094 }
            goto L_0x02b7
        L_0x0094:
            r0 = move-exception
            r4 = r0
            r6 = r7
            r9 = r10
            r10 = r11
            goto L_0x0409
        L_0x009b:
            java.lang.Object r0 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r1.L$4
            io.ktor.websocket.DefaultWebSocketSessionImpl r8 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r8
            java.lang.Object r9 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlin.ResultKt.throwOnFailure(r32)     // Catch:{ all -> 0x012b }
            goto L_0x0329
        L_0x00c0:
            java.lang.Object r0 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r1.L$4
            io.ktor.websocket.DefaultWebSocketSessionImpl r8 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r8
            java.lang.Object r9 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlin.ResultKt.throwOnFailure(r32)     // Catch:{ all -> 0x012b }
            r13 = r5
            goto L_0x025f
        L_0x00e6:
            java.lang.Object r0 = r1.L$0
            kotlin.Unit r0 = (kotlin.Unit) r0
            kotlin.ResultKt.throwOnFailure(r32)
            goto L_0x023a
        L_0x00ef:
            java.lang.Object r0 = r1.L$2
            r6 = r0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r0 = r1.L$1
            r9 = r0
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r0 = r1.L$0
            r10 = r0
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            kotlin.ResultKt.throwOnFailure(r32)     // Catch:{ all -> 0x012b }
            r13 = r5
            goto L_0x01ea
        L_0x0104:
            java.lang.Object r0 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            java.lang.Object r8 = r1.L$4
            io.ktor.websocket.DefaultWebSocketSessionImpl r8 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r8
            java.lang.Object r9 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlin.ResultKt.throwOnFailure(r32)     // Catch:{ all -> 0x012b }
            r14 = r32
            r13 = r5
            goto L_0x017e
        L_0x012b:
            r0 = move-exception
            r4 = r0
            goto L_0x0409
        L_0x012f:
            kotlin.ResultKt.throwOnFailure(r32)
            java.lang.Object r0 = r1.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.jvm.internal.Ref$ObjectRef r6 = new kotlin.jvm.internal.Ref$ObjectRef
            r6.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r9 = new kotlin.jvm.internal.Ref$BooleanRef
            r9.<init>()
            io.ktor.websocket.DefaultWebSocketSessionImpl r7 = r1.this$0     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            io.ktor.websocket.WebSocketSession r7 = r7.raw     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            kotlinx.coroutines.channels.ReceiveChannel r7 = r7.getIncoming()     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            io.ktor.websocket.DefaultWebSocketSessionImpl r8 = r1.this$0     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Ping> r11 = r1.$ponger     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            kotlinx.coroutines.channels.ChannelIterator r12 = r7.iterator()     // Catch:{ all -> 0x0406 }
            r13 = r5
        L_0x0158:
            r1.L$0 = r0     // Catch:{ all -> 0x0406 }
            r1.L$1 = r6     // Catch:{ all -> 0x0406 }
            r1.L$2 = r10     // Catch:{ all -> 0x0406 }
            r1.L$3 = r9     // Catch:{ all -> 0x0406 }
            r1.L$4 = r8     // Catch:{ all -> 0x0406 }
            r1.L$5 = r11     // Catch:{ all -> 0x0406 }
            r1.L$6 = r7     // Catch:{ all -> 0x0406 }
            r1.L$7 = r12     // Catch:{ all -> 0x0406 }
            r1.L$8 = r5     // Catch:{ all -> 0x0406 }
            r1.label = r4     // Catch:{ all -> 0x0406 }
            java.lang.Object r14 = r12.hasNext(r1)     // Catch:{ all -> 0x0406 }
            if (r14 != r2) goto L_0x0173
            return r2
        L_0x0173:
            r29 = r12
            r12 = r0
            r0 = r29
            r30 = r11
            r11 = r6
            r6 = r7
            r7 = r30
        L_0x017e:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x012b }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x012b }
            if (r14 == 0) goto L_0x03b7
            java.lang.Object r14 = r0.next()     // Catch:{ all -> 0x012b }
            io.ktor.websocket.Frame r14 = (io.ktor.websocket.Frame) r14     // Catch:{ all -> 0x012b }
            org.slf4j.Logger r15 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()     // Catch:{ all -> 0x012b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x012b }
            r4.<init>()     // Catch:{ all -> 0x012b }
            java.lang.String r5 = "WebSocketSession("
            r4.append(r5)     // Catch:{ all -> 0x012b }
            r4.append(r12)     // Catch:{ all -> 0x012b }
            java.lang.String r5 = ") receiving frame "
            r4.append(r5)     // Catch:{ all -> 0x012b }
            r4.append(r14)     // Catch:{ all -> 0x012b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x012b }
            r15.trace(r4)     // Catch:{ all -> 0x012b }
            boolean r4 = r14 instanceof io.ktor.websocket.Frame.Close     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x023b
            kotlinx.coroutines.channels.SendChannel r0 = r8.getOutgoing()     // Catch:{ all -> 0x012b }
            boolean r0 = r0.isClosedForSend()     // Catch:{ all -> 0x012b }
            if (r0 != 0) goto L_0x01ea
            kotlinx.coroutines.channels.SendChannel r0 = r8.getOutgoing()     // Catch:{ all -> 0x012b }
            io.ktor.websocket.Frame$Close r4 = new io.ktor.websocket.Frame$Close     // Catch:{ all -> 0x012b }
            io.ktor.websocket.Frame$Close r14 = (io.ktor.websocket.Frame.Close) r14     // Catch:{ all -> 0x012b }
            io.ktor.websocket.CloseReason r5 = io.ktor.websocket.FrameCommonKt.readReason(r14)     // Catch:{ all -> 0x012b }
            if (r5 != 0) goto L_0x01cc
            io.ktor.websocket.CloseReason r5 = io.ktor.websocket.DefaultWebSocketSessionKt.NORMAL_CLOSE     // Catch:{ all -> 0x012b }
        L_0x01cc:
            r4.<init>((io.ktor.websocket.CloseReason) r5)     // Catch:{ all -> 0x012b }
            r1.L$0 = r10     // Catch:{ all -> 0x012b }
            r1.L$1 = r9     // Catch:{ all -> 0x012b }
            r1.L$2 = r6     // Catch:{ all -> 0x012b }
            r5 = 0
            r1.L$3 = r5     // Catch:{ all -> 0x012b }
            r1.L$4 = r5     // Catch:{ all -> 0x012b }
            r1.L$5 = r5     // Catch:{ all -> 0x012b }
            r1.L$6 = r5     // Catch:{ all -> 0x012b }
            r1.L$7 = r5     // Catch:{ all -> 0x012b }
            r5 = 2
            r1.label = r5     // Catch:{ all -> 0x012b }
            java.lang.Object r0 = r0.send(r4, r1)     // Catch:{ all -> 0x012b }
            if (r0 != r2) goto L_0x01ea
            return r2
        L_0x01ea:
            r4 = 1
            r9.element = r4     // Catch:{ all -> 0x012b }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012b }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r13)     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Ping> r5 = r1.$ponger
            r6 = 0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r5, r6, r4, r6)
            T r5 = r10.element
            io.ktor.utils.io.core.BytePacketBuilder r5 = (io.ktor.utils.io.core.BytePacketBuilder) r5
            if (r5 == 0) goto L_0x0203
            r5.release()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L_0x0203:
            io.ktor.websocket.DefaultWebSocketSessionImpl r5 = r1.this$0
            kotlinx.coroutines.channels.Channel r5 = r5.filtered
            kotlinx.coroutines.channels.SendChannel r5 = (kotlinx.coroutines.channels.SendChannel) r5
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r5, r6, r4, r6)
            boolean r4 = r9.element
            if (r4 != 0) goto L_0x023a
            io.ktor.websocket.DefaultWebSocketSessionImpl r4 = r1.this$0
            io.ktor.websocket.WebSocketSession r4 = (io.ktor.websocket.WebSocketSession) r4
            io.ktor.websocket.CloseReason r5 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r7 = io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY
            r5.<init>((io.ktor.websocket.CloseReason.Codes) r7, (java.lang.String) r3)
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.L$0 = r0
            r1.L$1 = r6
            r1.L$2 = r6
            r1.L$3 = r6
            r1.L$4 = r6
            r1.L$5 = r6
            r1.L$6 = r6
            r1.L$7 = r6
            r6 = 3
            r1.label = r6
            java.lang.Object r3 = io.ktor.websocket.WebSocketSessionKt.close((io.ktor.websocket.WebSocketSession) r4, (io.ktor.websocket.CloseReason) r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r3)
            if (r3 != r2) goto L_0x023a
            return r2
        L_0x023a:
            return r0
        L_0x023b:
            boolean r4 = r14 instanceof io.ktor.websocket.Frame.Pong     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x0262
            java.lang.Object r4 = r8.pinger     // Catch:{ all -> 0x012b }
            kotlinx.coroutines.channels.SendChannel r4 = (kotlinx.coroutines.channels.SendChannel) r4     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x0280
            r1.L$0 = r12     // Catch:{ all -> 0x012b }
            r1.L$1 = r11     // Catch:{ all -> 0x012b }
            r1.L$2 = r10     // Catch:{ all -> 0x012b }
            r1.L$3 = r9     // Catch:{ all -> 0x012b }
            r1.L$4 = r8     // Catch:{ all -> 0x012b }
            r1.L$5 = r7     // Catch:{ all -> 0x012b }
            r1.L$6 = r6     // Catch:{ all -> 0x012b }
            r1.L$7 = r0     // Catch:{ all -> 0x012b }
            r5 = 4
            r1.label = r5     // Catch:{ all -> 0x012b }
            java.lang.Object r4 = r4.send(r14, r1)     // Catch:{ all -> 0x012b }
            if (r4 != r2) goto L_0x025f
            return r2
        L_0x025f:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012b }
            goto L_0x0280
        L_0x0262:
            boolean r4 = r14 instanceof io.ktor.websocket.Frame.Ping     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x028d
            r1.L$0 = r12     // Catch:{ all -> 0x012b }
            r1.L$1 = r11     // Catch:{ all -> 0x012b }
            r1.L$2 = r10     // Catch:{ all -> 0x012b }
            r1.L$3 = r9     // Catch:{ all -> 0x012b }
            r1.L$4 = r8     // Catch:{ all -> 0x012b }
            r1.L$5 = r7     // Catch:{ all -> 0x012b }
            r1.L$6 = r6     // Catch:{ all -> 0x012b }
            r1.L$7 = r0     // Catch:{ all -> 0x012b }
            r4 = 5
            r1.label = r4     // Catch:{ all -> 0x012b }
            java.lang.Object r4 = r7.send(r14, r1)     // Catch:{ all -> 0x012b }
            if (r4 != r2) goto L_0x0280
            return r2
        L_0x0280:
            r29 = r12
            r12 = r0
            r0 = r29
            r30 = r7
            r7 = r6
            r6 = r11
            r11 = r30
            goto L_0x03b3
        L_0x028d:
            T r4 = r10.element     // Catch:{ all -> 0x012b }
            io.ktor.utils.io.core.BytePacketBuilder r4 = (io.ktor.utils.io.core.BytePacketBuilder) r4     // Catch:{ all -> 0x012b }
            r1.L$0 = r12     // Catch:{ all -> 0x012b }
            r1.L$1 = r11     // Catch:{ all -> 0x012b }
            r1.L$2 = r10     // Catch:{ all -> 0x012b }
            r1.L$3 = r9     // Catch:{ all -> 0x012b }
            r1.L$4 = r8     // Catch:{ all -> 0x012b }
            r1.L$5 = r7     // Catch:{ all -> 0x012b }
            r1.L$6 = r6     // Catch:{ all -> 0x012b }
            r1.L$7 = r0     // Catch:{ all -> 0x012b }
            r1.L$8 = r14     // Catch:{ all -> 0x012b }
            r5 = 6
            r1.label = r5     // Catch:{ all -> 0x012b }
            java.lang.Object r4 = r8.checkMaxFrameSize(r4, r14, r1)     // Catch:{ all -> 0x012b }
            if (r4 != r2) goto L_0x02ad
            return r2
        L_0x02ad:
            r5 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r0
            r0 = r14
        L_0x02b7:
            boolean r4 = r0.getFin()     // Catch:{ all -> 0x0094 }
            if (r4 != 0) goto L_0x02f8
            T r4 = r12.element     // Catch:{ all -> 0x0094 }
            if (r4 != 0) goto L_0x02c3
            r12.element = r0     // Catch:{ all -> 0x0094 }
        L_0x02c3:
            T r4 = r11.element     // Catch:{ all -> 0x0094 }
            if (r4 != 0) goto L_0x02d0
            io.ktor.utils.io.core.BytePacketBuilder r4 = new io.ktor.utils.io.core.BytePacketBuilder     // Catch:{ all -> 0x0094 }
            r14 = 1
            r15 = 0
            r4.<init>(r15, r14, r15)     // Catch:{ all -> 0x0094 }
            r11.element = r4     // Catch:{ all -> 0x0094 }
        L_0x02d0:
            T r4 = r11.element     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0094 }
            r16 = r4
            io.ktor.utils.io.core.Output r16 = (io.ktor.utils.io.core.Output) r16     // Catch:{ all -> 0x0094 }
            byte[] r17 = r0.getData()     // Catch:{ all -> 0x0094 }
            r20 = 6
            r21 = 0
            r18 = 0
            r19 = 0
            io.ktor.utils.io.core.OutputKt.writeFully$default((io.ktor.utils.io.core.Output) r16, (byte[]) r17, (int) r18, (int) r19, (int) r20, (java.lang.Object) r21)     // Catch:{ all -> 0x0094 }
            r0 = r13
            r13 = r5
            r29 = r12
            r12 = r6
            r6 = r29
            r30 = r11
            r11 = r8
            r8 = r9
            r9 = r10
            r10 = r30
            goto L_0x03b3
        L_0x02f8:
            T r4 = r12.element     // Catch:{ all -> 0x0094 }
            if (r4 != 0) goto L_0x032c
            kotlinx.coroutines.channels.Channel r4 = r9.filtered     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.Frame r0 = r9.processIncomingExtensions(r0)     // Catch:{ all -> 0x0094 }
            r1.L$0 = r13     // Catch:{ all -> 0x0094 }
            r1.L$1 = r12     // Catch:{ all -> 0x0094 }
            r1.L$2 = r11     // Catch:{ all -> 0x0094 }
            r1.L$3 = r10     // Catch:{ all -> 0x0094 }
            r1.L$4 = r9     // Catch:{ all -> 0x0094 }
            r1.L$5 = r8     // Catch:{ all -> 0x0094 }
            r1.L$6 = r7     // Catch:{ all -> 0x0094 }
            r1.L$7 = r6     // Catch:{ all -> 0x0094 }
            r14 = 0
            r1.L$8 = r14     // Catch:{ all -> 0x0094 }
            r14 = 7
            r1.label = r14     // Catch:{ all -> 0x0094 }
            java.lang.Object r0 = r4.send(r0, r1)     // Catch:{ all -> 0x0094 }
            if (r0 != r2) goto L_0x0321
            return r2
        L_0x0321:
            r0 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
        L_0x0329:
            r13 = r5
            goto L_0x0280
        L_0x032c:
            T r4 = r11.element     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0094 }
            r16 = r4
            io.ktor.utils.io.core.Output r16 = (io.ktor.utils.io.core.Output) r16     // Catch:{ all -> 0x0094 }
            byte[] r17 = r0.getData()     // Catch:{ all -> 0x0094 }
            r20 = 6
            r21 = 0
            r18 = 0
            r19 = 0
            io.ktor.utils.io.core.OutputKt.writeFully$default((io.ktor.utils.io.core.Output) r16, (byte[]) r17, (int) r18, (int) r19, (int) r20, (java.lang.Object) r21)     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.Frame$Companion r22 = io.ktor.websocket.Frame.Companion     // Catch:{ all -> 0x0094 }
            T r0 = r12.element     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.Frame r0 = (io.ktor.websocket.Frame) r0     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.FrameType r24 = r0.getFrameType()     // Catch:{ all -> 0x0094 }
            T r0 = r11.element     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0094 }
            io.ktor.utils.io.core.BytePacketBuilder r0 = (io.ktor.utils.io.core.BytePacketBuilder) r0     // Catch:{ all -> 0x0094 }
            io.ktor.utils.io.core.ByteReadPacket r0 = r0.build()     // Catch:{ all -> 0x0094 }
            r4 = 0
            r14 = 1
            r15 = 0
            byte[] r25 = io.ktor.utils.io.core.StringsKt.readBytes$default(r0, r4, r14, r15)     // Catch:{ all -> 0x0094 }
            T r0 = r12.element     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.Frame r0 = (io.ktor.websocket.Frame) r0     // Catch:{ all -> 0x0094 }
            boolean r26 = r0.getRsv1()     // Catch:{ all -> 0x0094 }
            T r0 = r12.element     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.Frame r0 = (io.ktor.websocket.Frame) r0     // Catch:{ all -> 0x0094 }
            boolean r27 = r0.getRsv2()     // Catch:{ all -> 0x0094 }
            T r0 = r12.element     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.Frame r0 = (io.ktor.websocket.Frame) r0     // Catch:{ all -> 0x0094 }
            boolean r28 = r0.getRsv3()     // Catch:{ all -> 0x0094 }
            r23 = 1
            io.ktor.websocket.Frame r0 = r22.byType(r23, r24, r25, r26, r27, r28)     // Catch:{ all -> 0x0094 }
            r4 = 0
            r12.element = r4     // Catch:{ all -> 0x0094 }
            kotlinx.coroutines.channels.Channel r4 = r9.filtered     // Catch:{ all -> 0x0094 }
            io.ktor.websocket.Frame r0 = r9.processIncomingExtensions(r0)     // Catch:{ all -> 0x0094 }
            r1.L$0 = r13     // Catch:{ all -> 0x0094 }
            r1.L$1 = r12     // Catch:{ all -> 0x0094 }
            r1.L$2 = r11     // Catch:{ all -> 0x0094 }
            r1.L$3 = r10     // Catch:{ all -> 0x0094 }
            r1.L$4 = r9     // Catch:{ all -> 0x0094 }
            r1.L$5 = r8     // Catch:{ all -> 0x0094 }
            r1.L$6 = r7     // Catch:{ all -> 0x0094 }
            r1.L$7 = r6     // Catch:{ all -> 0x0094 }
            r14 = 0
            r1.L$8 = r14     // Catch:{ all -> 0x0094 }
            r14 = 8
            r1.label = r14     // Catch:{ all -> 0x0094 }
            java.lang.Object r0 = r4.send(r0, r1)     // Catch:{ all -> 0x0094 }
            if (r0 != r2) goto L_0x0321
            return r2
        L_0x03b3:
            r4 = 1
            r5 = 0
            goto L_0x0158
        L_0x03b7:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012b }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r13)     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Ping> r0 = r1.$ponger
            r4 = 1
            r5 = 0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r5, r4, r5)
            T r0 = r10.element
            io.ktor.utils.io.core.BytePacketBuilder r0 = (io.ktor.utils.io.core.BytePacketBuilder) r0
            if (r0 == 0) goto L_0x03ce
            r0.release()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x03ce:
            io.ktor.websocket.DefaultWebSocketSessionImpl r0 = r1.this$0
            kotlinx.coroutines.channels.Channel r0 = r0.filtered
            kotlinx.coroutines.channels.SendChannel r0 = (kotlinx.coroutines.channels.SendChannel) r0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r5, r4, r5)
            boolean r0 = r9.element
            if (r0 != 0) goto L_0x0506
            io.ktor.websocket.DefaultWebSocketSessionImpl r0 = r1.this$0
            io.ktor.websocket.WebSocketSession r0 = (io.ktor.websocket.WebSocketSession) r0
            io.ktor.websocket.CloseReason r4 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r6 = io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY
            r4.<init>((io.ktor.websocket.CloseReason.Codes) r6, (java.lang.String) r3)
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.L$0 = r5
            r1.L$1 = r5
            r1.L$2 = r5
            r1.L$3 = r5
            r1.L$4 = r5
            r1.L$5 = r5
            r1.L$6 = r5
            r1.L$7 = r5
            r5 = 9
            r1.label = r5
            java.lang.Object r0 = io.ktor.websocket.WebSocketSessionKt.close((io.ktor.websocket.WebSocketSession) r0, (io.ktor.websocket.CloseReason) r4, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r3)
            if (r0 != r2) goto L_0x0506
            return r2
        L_0x0406:
            r0 = move-exception
            r4 = r0
            r6 = r7
        L_0x0409:
            throw r4     // Catch:{ all -> 0x040a }
        L_0x040a:
            r0 = move-exception
            r5 = r0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r4)     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
            throw r5     // Catch:{ ClosedSendChannelException -> 0x04b9, all -> 0x0410 }
        L_0x0410:
            r0 = move-exception
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Ping> r4 = r1.$ponger     // Catch:{ all -> 0x046b }
            r5 = 1
            r6 = 0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r4, r6, r5, r6)     // Catch:{ all -> 0x046b }
            io.ktor.websocket.DefaultWebSocketSessionImpl r4 = r1.this$0     // Catch:{ all -> 0x046b }
            kotlinx.coroutines.channels.Channel r4 = r4.filtered     // Catch:{ all -> 0x046b }
            r4.close(r0)     // Catch:{ all -> 0x046b }
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Ping> r0 = r1.$ponger
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r6, r5, r6)
            T r0 = r10.element
            io.ktor.utils.io.core.BytePacketBuilder r0 = (io.ktor.utils.io.core.BytePacketBuilder) r0
            if (r0 == 0) goto L_0x0431
            r0.release()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x0431:
            io.ktor.websocket.DefaultWebSocketSessionImpl r0 = r1.this$0
            kotlinx.coroutines.channels.Channel r0 = r0.filtered
            kotlinx.coroutines.channels.SendChannel r0 = (kotlinx.coroutines.channels.SendChannel) r0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r6, r5, r6)
            boolean r0 = r9.element
            if (r0 != 0) goto L_0x0506
            io.ktor.websocket.DefaultWebSocketSessionImpl r0 = r1.this$0
            io.ktor.websocket.WebSocketSession r0 = (io.ktor.websocket.WebSocketSession) r0
            io.ktor.websocket.CloseReason r4 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r5 = io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY
            r4.<init>((io.ktor.websocket.CloseReason.Codes) r5, (java.lang.String) r3)
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.L$0 = r6
            r1.L$1 = r6
            r1.L$2 = r6
            r1.L$3 = r6
            r1.L$4 = r6
            r1.L$5 = r6
            r1.L$6 = r6
            r1.L$7 = r6
            r1.L$8 = r6
            r5 = 11
            r1.label = r5
            java.lang.Object r0 = io.ktor.websocket.WebSocketSessionKt.close((io.ktor.websocket.WebSocketSession) r0, (io.ktor.websocket.CloseReason) r4, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r3)
            if (r0 != r2) goto L_0x0506
            return r2
        L_0x046b:
            r0 = move-exception
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Ping> r4 = r1.$ponger
            r5 = 1
            r6 = 0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r4, r6, r5, r6)
            T r4 = r10.element
            io.ktor.utils.io.core.BytePacketBuilder r4 = (io.ktor.utils.io.core.BytePacketBuilder) r4
            if (r4 == 0) goto L_0x047e
            r4.release()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x047e:
            io.ktor.websocket.DefaultWebSocketSessionImpl r4 = r1.this$0
            kotlinx.coroutines.channels.Channel r4 = r4.filtered
            kotlinx.coroutines.channels.SendChannel r4 = (kotlinx.coroutines.channels.SendChannel) r4
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r4, r6, r5, r6)
            boolean r4 = r9.element
            if (r4 != 0) goto L_0x04b8
            io.ktor.websocket.DefaultWebSocketSessionImpl r4 = r1.this$0
            io.ktor.websocket.WebSocketSession r4 = (io.ktor.websocket.WebSocketSession) r4
            io.ktor.websocket.CloseReason r5 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r7 = io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY
            r5.<init>((io.ktor.websocket.CloseReason.Codes) r7, (java.lang.String) r3)
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.L$0 = r0
            r1.L$1 = r6
            r1.L$2 = r6
            r1.L$3 = r6
            r1.L$4 = r6
            r1.L$5 = r6
            r1.L$6 = r6
            r1.L$7 = r6
            r1.L$8 = r6
            r6 = 12
            r1.label = r6
            java.lang.Object r3 = io.ktor.websocket.WebSocketSessionKt.close((io.ktor.websocket.WebSocketSession) r4, (io.ktor.websocket.CloseReason) r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r3)
            if (r3 != r2) goto L_0x04b8
            return r2
        L_0x04b8:
            throw r0
        L_0x04b9:
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame$Ping> r0 = r1.$ponger
            r4 = 1
            r5 = 0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r5, r4, r5)
            T r0 = r10.element
            io.ktor.utils.io.core.BytePacketBuilder r0 = (io.ktor.utils.io.core.BytePacketBuilder) r0
            if (r0 == 0) goto L_0x04cc
            r0.release()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x04cc:
            io.ktor.websocket.DefaultWebSocketSessionImpl r0 = r1.this$0
            kotlinx.coroutines.channels.Channel r0 = r0.filtered
            kotlinx.coroutines.channels.SendChannel r0 = (kotlinx.coroutines.channels.SendChannel) r0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r5, r4, r5)
            boolean r0 = r9.element
            if (r0 != 0) goto L_0x0506
            io.ktor.websocket.DefaultWebSocketSessionImpl r0 = r1.this$0
            io.ktor.websocket.WebSocketSession r0 = (io.ktor.websocket.WebSocketSession) r0
            io.ktor.websocket.CloseReason r4 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r6 = io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY
            r4.<init>((io.ktor.websocket.CloseReason.Codes) r6, (java.lang.String) r3)
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.L$0 = r5
            r1.L$1 = r5
            r1.L$2 = r5
            r1.L$3 = r5
            r1.L$4 = r5
            r1.L$5 = r5
            r1.L$6 = r5
            r1.L$7 = r5
            r1.L$8 = r5
            r5 = 10
            r1.label = r5
            java.lang.Object r0 = io.ktor.websocket.WebSocketSessionKt.close((io.ktor.websocket.WebSocketSession) r0, (io.ktor.websocket.CloseReason) r4, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r3)
            if (r0 != r2) goto L_0x0506
            return r2
        L_0x0506:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl$runIncomingProcessor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
