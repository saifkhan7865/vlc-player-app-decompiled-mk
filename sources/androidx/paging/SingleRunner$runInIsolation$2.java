package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.SingleRunner$runInIsolation$2", f = "SingleRunner.kt", i = {0, 1}, l = {53, 59, 61, 61}, m = "invokeSuspend", n = {"myJob", "myJob"}, s = {"L$0", "L$0"})
/* compiled from: SingleRunner.kt */
final class SingleRunner$runInIsolation$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Continuation<? super Unit>, Object> $block;
    final /* synthetic */ int $priority;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SingleRunner this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleRunner$runInIsolation$2(SingleRunner singleRunner, int i, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super SingleRunner$runInIsolation$2> continuation) {
        super(2, continuation);
        this.this$0 = singleRunner;
        this.$priority = i;
        this.$block = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SingleRunner$runInIsolation$2 singleRunner$runInIsolation$2 = new SingleRunner$runInIsolation$2(this.this$0, this.$priority, this.$block, continuation);
        singleRunner$runInIsolation$2.L$0 = obj;
        return singleRunner$runInIsolation$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SingleRunner$runInIsolation$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0095 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x003c
            if (r1 == r5) goto L_0x0034
            if (r1 == r4) goto L_0x002a
            if (r1 == r3) goto L_0x0025
            if (r1 == r2) goto L_0x001c
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x001c:
            java.lang.Object r0 = r9.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ab
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ac
        L_0x002a:
            java.lang.Object r1 = r9.L$0
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0032 }
            goto L_0x0081
        L_0x0032:
            r10 = move-exception
            goto L_0x0096
        L_0x0034:
            java.lang.Object r1 = r9.L$0
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x006c
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
            kotlin.coroutines.CoroutineContext r10 = r10.getCoroutineContext()
            kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.Key
            kotlin.coroutines.CoroutineContext$Key r1 = (kotlin.coroutines.CoroutineContext.Key) r1
            kotlin.coroutines.CoroutineContext$Element r10 = r10.get(r1)
            if (r10 == 0) goto L_0x00af
            kotlinx.coroutines.Job r10 = (kotlinx.coroutines.Job) r10
            androidx.paging.SingleRunner r1 = r9.this$0
            androidx.paging.SingleRunner$Holder r1 = r1.holder
            int r6 = r9.$priority
            r7 = r9
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r9.L$0 = r10
            r9.label = r5
            java.lang.Object r1 = r1.tryEnqueue(r6, r10, r7)
            if (r1 != r0) goto L_0x0069
            return r0
        L_0x0069:
            r8 = r1
            r1 = r10
            r10 = r8
        L_0x006c:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00ac
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r10 = r9.$block     // Catch:{ all -> 0x0032 }
            r9.L$0 = r1     // Catch:{ all -> 0x0032 }
            r9.label = r4     // Catch:{ all -> 0x0032 }
            java.lang.Object r10 = r10.invoke(r9)     // Catch:{ all -> 0x0032 }
            if (r10 != r0) goto L_0x0081
            return r0
        L_0x0081:
            androidx.paging.SingleRunner r10 = r9.this$0
            androidx.paging.SingleRunner$Holder r10 = r10.holder
            r2 = r9
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r4 = 0
            r9.L$0 = r4
            r9.label = r3
            java.lang.Object r10 = r10.onFinish(r1, r2)
            if (r10 != r0) goto L_0x00ac
            return r0
        L_0x0096:
            androidx.paging.SingleRunner r3 = r9.this$0
            androidx.paging.SingleRunner$Holder r3 = r3.holder
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r9.L$0 = r10
            r9.label = r2
            java.lang.Object r1 = r3.onFinish(r1, r4)
            if (r1 != r0) goto L_0x00aa
            return r0
        L_0x00aa:
            r0 = r10
        L_0x00ab:
            throw r0
        L_0x00ac:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00af:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "Internal error. coroutineScope should've created a job."
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner$runInIsolation$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
