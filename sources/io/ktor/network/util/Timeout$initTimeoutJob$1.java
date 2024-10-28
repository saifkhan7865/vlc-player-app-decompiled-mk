package io.ktor.network.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.util.Timeout$initTimeoutJob$1", f = "Utils.kt", i = {}, l = {57, 59, 60}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Utils.kt */
final class Timeout$initTimeoutJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ Timeout this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Timeout$initTimeoutJob$1(Timeout timeout, Continuation<? super Timeout$initTimeoutJob$1> continuation) {
        super(2, continuation);
        this.this$0 = timeout;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Timeout$initTimeoutJob$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Timeout$initTimeoutJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f A[Catch:{ all -> 0x0090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084 A[Catch:{ all -> 0x0090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0069 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0026
            if (r1 == r4) goto L_0x0022
            if (r1 == r3) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0090 }
            goto L_0x0090
        L_0x0016:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0090 }
            goto L_0x0075
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0090 }
            goto L_0x0029
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r10)
        L_0x0029:
            io.ktor.network.util.Timeout r10 = r9.this$0     // Catch:{ all -> 0x0090 }
            int r10 = r10.isStarted     // Catch:{ all -> 0x0090 }
            if (r10 != 0) goto L_0x0041
            io.ktor.network.util.Timeout r10 = r9.this$0     // Catch:{ all -> 0x0090 }
            kotlin.jvm.functions.Function0 r1 = r10.clock     // Catch:{ all -> 0x0090 }
            java.lang.Object r1 = r1.invoke()     // Catch:{ all -> 0x0090 }
            java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ all -> 0x0090 }
            long r5 = r1.longValue()     // Catch:{ all -> 0x0090 }
            r10.lastActivityTime = r5     // Catch:{ all -> 0x0090 }
        L_0x0041:
            io.ktor.network.util.Timeout r10 = r9.this$0     // Catch:{ all -> 0x0090 }
            long r5 = r10.lastActivityTime     // Catch:{ all -> 0x0090 }
            io.ktor.network.util.Timeout r10 = r9.this$0     // Catch:{ all -> 0x0090 }
            long r7 = r10.timeoutMs     // Catch:{ all -> 0x0090 }
            long r5 = r5 + r7
            io.ktor.network.util.Timeout r10 = r9.this$0     // Catch:{ all -> 0x0090 }
            kotlin.jvm.functions.Function0 r10 = r10.clock     // Catch:{ all -> 0x0090 }
            java.lang.Object r10 = r10.invoke()     // Catch:{ all -> 0x0090 }
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ all -> 0x0090 }
            long r7 = r10.longValue()     // Catch:{ all -> 0x0090 }
            long r5 = r5 - r7
            r7 = 0
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 > 0) goto L_0x0084
            io.ktor.network.util.Timeout r10 = r9.this$0     // Catch:{ all -> 0x0090 }
            int r10 = r10.isStarted     // Catch:{ all -> 0x0090 }
            if (r10 == 0) goto L_0x0084
            r10 = r9
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10     // Catch:{ all -> 0x0090 }
            r9.label = r3     // Catch:{ all -> 0x0090 }
            java.lang.Object r10 = kotlinx.coroutines.YieldKt.yield(r10)     // Catch:{ all -> 0x0090 }
            if (r10 != r0) goto L_0x0075
            return r0
        L_0x0075:
            io.ktor.network.util.Timeout r10 = r9.this$0     // Catch:{ all -> 0x0090 }
            kotlin.jvm.functions.Function1 r10 = r10.onTimeout     // Catch:{ all -> 0x0090 }
            r9.label = r2     // Catch:{ all -> 0x0090 }
            java.lang.Object r10 = r10.invoke(r9)     // Catch:{ all -> 0x0090 }
            if (r10 != r0) goto L_0x0090
            return r0
        L_0x0084:
            r10 = r9
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10     // Catch:{ all -> 0x0090 }
            r9.label = r4     // Catch:{ all -> 0x0090 }
            java.lang.Object r10 = kotlinx.coroutines.DelayKt.delay(r5, r10)     // Catch:{ all -> 0x0090 }
            if (r10 != r0) goto L_0x0029
            return r0
        L_0x0090:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.util.Timeout$initTimeoutJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
