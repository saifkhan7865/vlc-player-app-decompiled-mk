package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.EngineContextCancellationHelperKt$launchOnCancellation$1", f = "EngineContextCancellationHelper.kt", i = {0}, l = {36, 42}, m = "invokeSuspend", n = {"cancelled"}, s = {"I$0"})
/* compiled from: EngineContextCancellationHelper.kt */
final class EngineContextCancellationHelperKt$launchOnCancellation$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Continuation<? super Unit>, Object> $block;
    final /* synthetic */ CompletableJob $deferred;
    int I$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EngineContextCancellationHelperKt$launchOnCancellation$1(CompletableJob completableJob, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super EngineContextCancellationHelperKt$launchOnCancellation$1> continuation) {
        super(2, continuation);
        this.$deferred = completableJob;
        this.$block = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EngineContextCancellationHelperKt$launchOnCancellation$1(this.$deferred, this.$block, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EngineContextCancellationHelperKt$launchOnCancellation$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r1 == 0) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        if (r5.$deferred.isCancelled() != false) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004b
        L_0x0012:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x001a:
            int r1 = r5.I$0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x003f }
            if (r1 != 0) goto L_0x0040
            goto L_0x0036
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CompletableJob r6 = r5.$deferred     // Catch:{ all -> 0x003f }
            r1 = r5
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ all -> 0x003f }
            r4 = 0
            r5.I$0 = r4     // Catch:{ all -> 0x003f }
            r5.label = r3     // Catch:{ all -> 0x003f }
            java.lang.Object r6 = r6.join(r1)     // Catch:{ all -> 0x003f }
            if (r6 != r0) goto L_0x0036
            return r0
        L_0x0036:
            kotlinx.coroutines.CompletableJob r6 = r5.$deferred
            boolean r6 = r6.isCancelled()
            if (r6 == 0) goto L_0x004b
            goto L_0x0040
        L_0x003f:
        L_0x0040:
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r6 = r5.$block
            r5.label = r2
            java.lang.Object r6 = r6.invoke(r5)
            if (r6 != r0) goto L_0x004b
            return r0
        L_0x004b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.EngineContextCancellationHelperKt$launchOnCancellation$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
