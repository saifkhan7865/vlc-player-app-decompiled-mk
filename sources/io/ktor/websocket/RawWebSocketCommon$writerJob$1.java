package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.RawWebSocketCommon$writerJob$1", f = "RawWebSocketCommon.kt", i = {1}, l = {58, 60}, m = "invokeSuspend", n = {"message"}, s = {"L$0"})
/* compiled from: RawWebSocketCommon.kt */
final class RawWebSocketCommon$writerJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RawWebSocketCommon this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RawWebSocketCommon$writerJob$1(RawWebSocketCommon rawWebSocketCommon, Continuation<? super RawWebSocketCommon$writerJob$1> continuation) {
        super(2, continuation);
        this.this$0 = rawWebSocketCommon;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RawWebSocketCommon$writerJob$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RawWebSocketCommon$writerJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061 A[Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023, all -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006f A[Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023, all -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007b A[Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023, all -> 0x00ef }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 1
            java.lang.String r4 = "WebSocket closed."
            r5 = 0
            if (r1 == 0) goto L_0x0029
            if (r1 == r3) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            java.lang.Object r1 = r9.L$0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            goto L_0x0062
        L_0x0017:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            goto L_0x0040
        L_0x0023:
            r10 = move-exception
            goto L_0x009c
        L_0x0026:
            r10 = move-exception
            goto L_0x00be
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r10)
        L_0x002c:
            io.ktor.websocket.RawWebSocketCommon r10 = r9.this$0     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            kotlinx.coroutines.channels.Channel r10 = r10._outgoing     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r1 = r9
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r9.L$0 = r5     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r9.label = r3     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            java.lang.Object r10 = r10.receive(r1)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            if (r10 != r0) goto L_0x0040
            return r0
        L_0x0040:
            boolean r1 = r10 instanceof io.ktor.websocket.Frame     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            if (r1 == 0) goto L_0x007b
            io.ktor.websocket.RawWebSocketCommon r1 = r9.this$0     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            io.ktor.utils.io.ByteWriteChannel r1 = r1.output     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r6 = r10
            io.ktor.websocket.Frame r6 = (io.ktor.websocket.Frame) r6     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            io.ktor.websocket.RawWebSocketCommon r7 = r9.this$0     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            boolean r7 = r7.getMasking()     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r8 = r9
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r9.L$0 = r10     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r9.label = r2     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            java.lang.Object r1 = io.ktor.websocket.RawWebSocketCommonKt.writeFrame(r1, r6, r7, r8)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            if (r1 != r0) goto L_0x0061
            return r0
        L_0x0061:
            r1 = r10
        L_0x0062:
            io.ktor.websocket.RawWebSocketCommon r10 = r9.this$0     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            io.ktor.utils.io.ByteWriteChannel r10 = r10.output     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r10.flush()     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            boolean r10 = r1 instanceof io.ktor.websocket.Frame.Close     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            if (r10 == 0) goto L_0x002c
            io.ktor.websocket.RawWebSocketCommon r10 = r9.this$0     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            kotlinx.coroutines.channels.Channel r10 = r10._outgoing     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            kotlinx.coroutines.channels.SendChannel r10 = (kotlinx.coroutines.channels.SendChannel) r10     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r10, r5, r3, r5)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            goto L_0x00a5
        L_0x007b:
            boolean r1 = r10 instanceof io.ktor.websocket.RawWebSocketCommon.FlushRequest     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            if (r1 == 0) goto L_0x0085
            io.ktor.websocket.RawWebSocketCommon$FlushRequest r10 = (io.ktor.websocket.RawWebSocketCommon.FlushRequest) r10     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r10.complete()     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            goto L_0x002c
        L_0x0085:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r1.<init>()     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            java.lang.String r2 = "unknown message "
            r1.append(r2)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r1.append(r10)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            java.lang.String r10 = r1.toString()     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            r0.<init>(r10)     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
            throw r0     // Catch:{ ChannelWriteException -> 0x0026, all -> 0x0023 }
        L_0x009c:
            io.ktor.websocket.RawWebSocketCommon r0 = r9.this$0     // Catch:{ all -> 0x00ef }
            kotlinx.coroutines.channels.Channel r0 = r0._outgoing     // Catch:{ all -> 0x00ef }
            r0.close(r10)     // Catch:{ all -> 0x00ef }
        L_0x00a5:
            io.ktor.websocket.RawWebSocketCommon r10 = r9.this$0
            kotlinx.coroutines.channels.Channel r10 = r10._outgoing
            java.util.concurrent.CancellationException r0 = kotlinx.coroutines.ExceptionsKt.CancellationException(r4, r5)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r10.close(r0)
            io.ktor.websocket.RawWebSocketCommon r10 = r9.this$0
            io.ktor.utils.io.ByteWriteChannel r10 = r10.output
            io.ktor.utils.io.ByteWriteChannelKt.close(r10)
            goto L_0x00d2
        L_0x00be:
            io.ktor.websocket.RawWebSocketCommon r0 = r9.this$0     // Catch:{ all -> 0x00ef }
            kotlinx.coroutines.channels.Channel r0 = r0._outgoing     // Catch:{ all -> 0x00ef }
            java.lang.String r1 = "Failed to write to WebSocket."
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ all -> 0x00ef }
            java.util.concurrent.CancellationException r10 = kotlinx.coroutines.ExceptionsKt.CancellationException(r1, r10)     // Catch:{ all -> 0x00ef }
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ all -> 0x00ef }
            r0.close(r10)     // Catch:{ all -> 0x00ef }
            goto L_0x00a5
        L_0x00d2:
            io.ktor.websocket.RawWebSocketCommon r10 = r9.this$0
            kotlinx.coroutines.channels.Channel r10 = r10._outgoing
            java.lang.Object r10 = r10.m1138tryReceivePtdJZtk()
            java.lang.Object r10 = kotlinx.coroutines.channels.ChannelResult.m2341getOrNullimpl(r10)
            if (r10 != 0) goto L_0x00e5
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00e5:
            boolean r0 = r10 instanceof io.ktor.websocket.RawWebSocketCommon.FlushRequest
            if (r0 == 0) goto L_0x00d2
            io.ktor.websocket.RawWebSocketCommon$FlushRequest r10 = (io.ktor.websocket.RawWebSocketCommon.FlushRequest) r10
            r10.complete()
            goto L_0x00d2
        L_0x00ef:
            r10 = move-exception
            io.ktor.websocket.RawWebSocketCommon r0 = r9.this$0
            kotlinx.coroutines.channels.Channel r0 = r0._outgoing
            java.util.concurrent.CancellationException r1 = kotlinx.coroutines.ExceptionsKt.CancellationException(r4, r5)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r0.close(r1)
            io.ktor.websocket.RawWebSocketCommon r0 = r9.this$0
            io.ktor.utils.io.ByteWriteChannel r0 = r0.output
            io.ktor.utils.io.ByteWriteChannelKt.close(r0)
            goto L_0x010a
        L_0x0109:
            throw r10
        L_0x010a:
            goto L_0x0109
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommon$writerJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
