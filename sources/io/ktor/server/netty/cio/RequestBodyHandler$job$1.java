package io.ktor.server.netty.cio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.cio.RequestBodyHandler$job$1", f = "RequestBodyHandler.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {38, 44, 54}, m = "invokeSuspend", n = {"$this$launch", "current", "upgraded", "$this$launch", "current", "event", "upgraded", "$this$launch", "current", "upgraded"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "I$0"})
/* compiled from: RequestBodyHandler.kt */
final class RequestBodyHandler$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ RequestBodyHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RequestBodyHandler$job$1(RequestBodyHandler requestBodyHandler, Continuation<? super RequestBodyHandler$job$1> continuation) {
        super(2, continuation);
        this.this$0 = requestBodyHandler;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RequestBodyHandler$job$1 requestBodyHandler$job$1 = new RequestBodyHandler$job$1(this.this$0, continuation);
        requestBodyHandler$job$1.L$0 = obj;
        return requestBodyHandler$job$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RequestBodyHandler$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a1, code lost:
        if (r14 != null) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a3, code lost:
        kotlin.coroutines.jvm.internal.Boxing.boxBoolean(io.ktor.utils.io.ByteWriteChannelKt.close(r14));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00aa, code lost:
        kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(io.ktor.server.netty.cio.RequestBodyHandler.access$getQueue$p(r13.this$0), (java.lang.Throwable) null, 1, (java.lang.Object) null);
        io.ktor.server.netty.cio.RequestBodyHandler.access$consumeAndReleaseQueue(r13.this$0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0173, code lost:
        if (r14 == null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0179, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c5 A[SYNTHETIC, Splitter:B:41:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0109 A[Catch:{ all -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0168 A[Catch:{ all -> 0x017a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0057
            if (r1 == r4) goto L_0x0040
            if (r1 == r3) goto L_0x0029
            if (r1 != r2) goto L_0x0021
            int r1 = r13.I$0
            java.lang.Object r6 = r13.L$1
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0054 }
            goto L_0x012c
        L_0x0021:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0029:
            int r1 = r13.I$0
            java.lang.Object r6 = r13.L$2
            java.lang.Object r7 = r13.L$1
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r13.L$0
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x003c }
            r14 = r7
            r7 = r8
            goto L_0x00e4
        L_0x003c:
            r14 = move-exception
            r6 = r7
            goto L_0x0159
        L_0x0040:
            int r1 = r13.I$0
            java.lang.Object r6 = r13.L$1
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0054 }
            kotlinx.coroutines.channels.ChannelResult r14 = (kotlinx.coroutines.channels.ChannelResult) r14     // Catch:{ all -> 0x0054 }
            java.lang.Object r14 = r14.m2348unboximpl()     // Catch:{ all -> 0x0054 }
            goto L_0x0097
        L_0x0054:
            r14 = move-exception
            goto L_0x0159
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
            r1.<init>()
            r6 = 0
            r7 = r14
            r6 = r1
            r1 = 0
        L_0x0067:
            io.ktor.server.netty.cio.RequestBodyHandler r14 = r13.this$0     // Catch:{ all -> 0x0054 }
            kotlinx.coroutines.channels.Channel r14 = r14.queue     // Catch:{ all -> 0x0054 }
            java.lang.Object r14 = r14.m1138tryReceivePtdJZtk()     // Catch:{ all -> 0x0054 }
            java.lang.Object r14 = kotlinx.coroutines.channels.ChannelResult.m2341getOrNullimpl(r14)     // Catch:{ all -> 0x0054 }
            if (r14 != 0) goto L_0x00bc
            io.ktor.server.netty.cio.RequestBodyHandler r14 = r13.this$0     // Catch:{ all -> 0x0054 }
            T r8 = r6.element     // Catch:{ all -> 0x0054 }
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8     // Catch:{ all -> 0x0054 }
            if (r8 == 0) goto L_0x0082
            r8.flush()     // Catch:{ all -> 0x0054 }
        L_0x0082:
            kotlinx.coroutines.channels.Channel r14 = r14.queue     // Catch:{ all -> 0x0054 }
            r13.L$0 = r7     // Catch:{ all -> 0x0054 }
            r13.L$1 = r6     // Catch:{ all -> 0x0054 }
            r13.L$2 = r5     // Catch:{ all -> 0x0054 }
            r13.I$0 = r1     // Catch:{ all -> 0x0054 }
            r13.label = r4     // Catch:{ all -> 0x0054 }
            java.lang.Object r14 = r14.m1137receiveCatchingJP2dKIU(r13)     // Catch:{ all -> 0x0054 }
            if (r14 != r0) goto L_0x0097
            return r0
        L_0x0097:
            java.lang.Object r14 = kotlinx.coroutines.channels.ChannelResult.m2341getOrNullimpl(r14)     // Catch:{ all -> 0x0054 }
            if (r14 != 0) goto L_0x00bc
            T r14 = r6.element
            io.ktor.utils.io.ByteWriteChannel r14 = (io.ktor.utils.io.ByteWriteChannel) r14
            if (r14 == 0) goto L_0x00aa
        L_0x00a3:
            boolean r14 = io.ktor.utils.io.ByteWriteChannelKt.close(r14)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r14)
        L_0x00aa:
            io.ktor.server.netty.cio.RequestBodyHandler r14 = r13.this$0
            kotlinx.coroutines.channels.Channel r14 = r14.queue
            kotlinx.coroutines.channels.SendChannel r14 = (kotlinx.coroutines.channels.SendChannel) r14
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r14, r5, r4, r5)
            io.ktor.server.netty.cio.RequestBodyHandler r14 = r13.this$0
            r14.consumeAndReleaseQueue()
            goto L_0x0177
        L_0x00bc:
            r12 = r6
            r6 = r14
            r14 = r12
            boolean r8 = r6 instanceof io.netty.buffer.ByteBufHolder     // Catch:{ all -> 0x00fb }
            java.lang.String r9 = "No current channel but received a byte buf"
            if (r8 == 0) goto L_0x0109
            T r8 = r14.element     // Catch:{ all -> 0x00fb }
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8     // Catch:{ all -> 0x00fb }
            if (r8 == 0) goto L_0x00ff
            io.ktor.server.netty.cio.RequestBodyHandler r9 = r13.this$0     // Catch:{ all -> 0x00fb }
            r10 = r6
            io.netty.buffer.ByteBufHolder r10 = (io.netty.buffer.ByteBufHolder) r10     // Catch:{ all -> 0x00fb }
            r11 = r13
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11     // Catch:{ all -> 0x00fb }
            r13.L$0 = r7     // Catch:{ all -> 0x00fb }
            r13.L$1 = r14     // Catch:{ all -> 0x00fb }
            r13.L$2 = r6     // Catch:{ all -> 0x00fb }
            r13.I$0 = r1     // Catch:{ all -> 0x00fb }
            r13.label = r3     // Catch:{ all -> 0x00fb }
            java.lang.Object r8 = r9.processContent((io.ktor.utils.io.ByteWriteChannel) r8, (io.netty.buffer.ByteBufHolder) r10, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r11)     // Catch:{ all -> 0x00fb }
            if (r8 != r0) goto L_0x00e4
            return r0
        L_0x00e4:
            if (r1 != 0) goto L_0x00f3
            boolean r6 = r6 instanceof io.netty.handler.codec.http.LastHttpContent     // Catch:{ all -> 0x00fb }
            if (r6 == 0) goto L_0x00f3
            T r6 = r14.element     // Catch:{ all -> 0x00fb }
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6     // Catch:{ all -> 0x00fb }
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)     // Catch:{ all -> 0x00fb }
            r14.element = r5     // Catch:{ all -> 0x00fb }
        L_0x00f3:
            io.ktor.server.netty.cio.RequestBodyHandler r6 = r13.this$0     // Catch:{ all -> 0x00fb }
            r6.requestMoreEvents()     // Catch:{ all -> 0x00fb }
        L_0x00f8:
            r6 = r14
            goto L_0x0067
        L_0x00fb:
            r0 = move-exception
            r6 = r14
            r14 = r0
            goto L_0x0159
        L_0x00ff:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fb }
            java.lang.String r1 = r9.toString()     // Catch:{ all -> 0x00fb }
            r0.<init>(r1)     // Catch:{ all -> 0x00fb }
            throw r0     // Catch:{ all -> 0x00fb }
        L_0x0109:
            boolean r8 = r6 instanceof io.netty.buffer.ByteBuf     // Catch:{ all -> 0x00fb }
            if (r8 == 0) goto L_0x013d
            T r8 = r14.element     // Catch:{ all -> 0x00fb }
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8     // Catch:{ all -> 0x00fb }
            if (r8 == 0) goto L_0x0133
            io.ktor.server.netty.cio.RequestBodyHandler r9 = r13.this$0     // Catch:{ all -> 0x00fb }
            io.netty.buffer.ByteBuf r6 = (io.netty.buffer.ByteBuf) r6     // Catch:{ all -> 0x00fb }
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10     // Catch:{ all -> 0x00fb }
            r13.L$0 = r7     // Catch:{ all -> 0x00fb }
            r13.L$1 = r14     // Catch:{ all -> 0x00fb }
            r13.L$2 = r5     // Catch:{ all -> 0x00fb }
            r13.I$0 = r1     // Catch:{ all -> 0x00fb }
            r13.label = r2     // Catch:{ all -> 0x00fb }
            java.lang.Object r6 = r9.processContent((io.ktor.utils.io.ByteWriteChannel) r8, (io.netty.buffer.ByteBuf) r6, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r10)     // Catch:{ all -> 0x00fb }
            if (r6 != r0) goto L_0x012b
            return r0
        L_0x012b:
            r6 = r14
        L_0x012c:
            io.ktor.server.netty.cio.RequestBodyHandler r14 = r13.this$0     // Catch:{ all -> 0x0054 }
            r14.requestMoreEvents()     // Catch:{ all -> 0x0054 }
            goto L_0x0067
        L_0x0133:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fb }
            java.lang.String r1 = r9.toString()     // Catch:{ all -> 0x00fb }
            r0.<init>(r1)     // Catch:{ all -> 0x00fb }
            throw r0     // Catch:{ all -> 0x00fb }
        L_0x013d:
            boolean r8 = r6 instanceof io.ktor.utils.io.ByteWriteChannel     // Catch:{ all -> 0x00fb }
            if (r8 == 0) goto L_0x0151
            T r8 = r14.element     // Catch:{ all -> 0x00fb }
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8     // Catch:{ all -> 0x00fb }
            if (r8 == 0) goto L_0x014e
            boolean r8 = io.ktor.utils.io.ByteWriteChannelKt.close(r8)     // Catch:{ all -> 0x00fb }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)     // Catch:{ all -> 0x00fb }
        L_0x014e:
            r14.element = r6     // Catch:{ all -> 0x00fb }
            goto L_0x00f8
        L_0x0151:
            boolean r6 = r6 instanceof io.ktor.server.netty.cio.RequestBodyHandler.Upgrade     // Catch:{ all -> 0x00fb }
            if (r6 == 0) goto L_0x00f8
            r6 = r14
            r1 = 1
            goto L_0x0067
        L_0x0159:
            io.ktor.server.netty.cio.RequestBodyHandler r0 = r13.this$0     // Catch:{ all -> 0x017a }
            kotlinx.coroutines.channels.Channel r0 = r0.queue     // Catch:{ all -> 0x017a }
            r0.close(r14)     // Catch:{ all -> 0x017a }
            T r0 = r6.element     // Catch:{ all -> 0x017a }
            io.ktor.utils.io.ByteWriteChannel r0 = (io.ktor.utils.io.ByteWriteChannel) r0     // Catch:{ all -> 0x017a }
            if (r0 == 0) goto L_0x016f
            boolean r14 = r0.close(r14)     // Catch:{ all -> 0x017a }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r14)     // Catch:{ all -> 0x017a }
        L_0x016f:
            T r14 = r6.element
            io.ktor.utils.io.ByteWriteChannel r14 = (io.ktor.utils.io.ByteWriteChannel) r14
            if (r14 == 0) goto L_0x00aa
            goto L_0x00a3
        L_0x0177:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x017a:
            r14 = move-exception
            T r0 = r6.element
            io.ktor.utils.io.ByteWriteChannel r0 = (io.ktor.utils.io.ByteWriteChannel) r0
            if (r0 == 0) goto L_0x0188
            boolean r0 = io.ktor.utils.io.ByteWriteChannelKt.close(r0)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
        L_0x0188:
            io.ktor.server.netty.cio.RequestBodyHandler r0 = r13.this$0
            kotlinx.coroutines.channels.Channel r0 = r0.queue
            kotlinx.coroutines.channels.SendChannel r0 = (kotlinx.coroutines.channels.SendChannel) r0
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r5, r4, r5)
            io.ktor.server.netty.cio.RequestBodyHandler r0 = r13.this$0
            r0.consumeAndReleaseQueue()
            goto L_0x019a
        L_0x0199:
            throw r14
        L_0x019a:
            goto L_0x0199
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.cio.RequestBodyHandler$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
