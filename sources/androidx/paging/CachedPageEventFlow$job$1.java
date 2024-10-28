package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.CachedPageEventFlow$job$1", f = "CachedPageEventFlow.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CachedPageEventFlow.kt */
final class CachedPageEventFlow$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<PageEvent<T>> $src;
    int label;
    final /* synthetic */ CachedPageEventFlow<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CachedPageEventFlow$job$1(Flow<? extends PageEvent<T>> flow, CachedPageEventFlow<T> cachedPageEventFlow, Continuation<? super CachedPageEventFlow$job$1> continuation) {
        super(2, continuation);
        this.$src = flow;
        this.this$0 = cachedPageEventFlow;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CachedPageEventFlow$job$1(this.$src, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CachedPageEventFlow$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<IndexedValue<PageEvent<T>>> withIndex = FlowKt.withIndex(this.$src);
            final CachedPageEventFlow<T> cachedPageEventFlow = this.this$0;
            this.label = 1;
            if (withIndex.collect(new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
                /* JADX WARNING: Removed duplicated region for block: B:20:0x006b A[RETURN] */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(kotlin.collections.IndexedValue<? extends androidx.paging.PageEvent<T>> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof androidx.paging.CachedPageEventFlow$job$1$1$emit$1
                        if (r0 == 0) goto L_0x0014
                        r0 = r7
                        androidx.paging.CachedPageEventFlow$job$1$1$emit$1 r0 = (androidx.paging.CachedPageEventFlow$job$1$1$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L_0x0014
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L_0x0019
                    L_0x0014:
                        androidx.paging.CachedPageEventFlow$job$1$1$emit$1 r0 = new androidx.paging.CachedPageEventFlow$job$1$1$emit$1
                        r0.<init>(r5, r7)
                    L_0x0019:
                        java.lang.Object r7 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L_0x0041
                        if (r2 == r4) goto L_0x0035
                        if (r2 != r3) goto L_0x002d
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L_0x006c
                    L_0x002d:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L_0x0035:
                        java.lang.Object r6 = r0.L$1
                        kotlin.collections.IndexedValue r6 = (kotlin.collections.IndexedValue) r6
                        java.lang.Object r2 = r0.L$0
                        androidx.paging.CachedPageEventFlow$job$1$1 r2 = (androidx.paging.CachedPageEventFlow$job$1.AnonymousClass1) r2
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L_0x0058
                    L_0x0041:
                        kotlin.ResultKt.throwOnFailure(r7)
                        androidx.paging.CachedPageEventFlow<T> r7 = r3
                        kotlinx.coroutines.flow.MutableSharedFlow r7 = r7.mutableSharedSrc
                        r0.L$0 = r5
                        r0.L$1 = r6
                        r0.label = r4
                        java.lang.Object r7 = r7.emit(r6, r0)
                        if (r7 != r1) goto L_0x0057
                        return r1
                    L_0x0057:
                        r2 = r5
                    L_0x0058:
                        androidx.paging.CachedPageEventFlow<T> r7 = r3
                        androidx.paging.FlattenedPageController r7 = r7.pageController
                        r2 = 0
                        r0.L$0 = r2
                        r0.L$1 = r2
                        r0.label = r3
                        java.lang.Object r6 = r7.record(r6, r0)
                        if (r6 != r1) goto L_0x006c
                        return r1
                    L_0x006c:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.CachedPageEventFlow$job$1.AnonymousClass1.emit(kotlin.collections.IndexedValue, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
