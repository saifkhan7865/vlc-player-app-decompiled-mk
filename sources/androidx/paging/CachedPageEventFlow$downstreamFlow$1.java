package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H@"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/paging/PageEvent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.CachedPageEventFlow$downstreamFlow$1", f = "CachedPageEventFlow.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CachedPageEventFlow.kt */
final class CachedPageEventFlow$downstreamFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super PageEvent<T>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CachedPageEventFlow<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CachedPageEventFlow$downstreamFlow$1(CachedPageEventFlow<T> cachedPageEventFlow, Continuation<? super CachedPageEventFlow$downstreamFlow$1> continuation) {
        super(2, continuation);
        this.this$0 = cachedPageEventFlow;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CachedPageEventFlow$downstreamFlow$1 cachedPageEventFlow$downstreamFlow$1 = new CachedPageEventFlow$downstreamFlow$1(this.this$0, continuation);
        cachedPageEventFlow$downstreamFlow$1.L$0 = obj;
        return cachedPageEventFlow$downstreamFlow$1;
    }

    public final Object invoke(FlowCollector<? super PageEvent<T>> flowCollector, Continuation<? super Unit> continuation) {
        return ((CachedPageEventFlow$downstreamFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final FlowCollector flowCollector = (FlowCollector) this.L$0;
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = Integer.MIN_VALUE;
            this.label = 1;
            if (FlowKt.takeWhile(this.this$0.sharedForDownstream, new AnonymousClass1((Continuation<? super AnonymousClass1>) null)).collect(new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(kotlin.collections.IndexedValue<? extends androidx.paging.PageEvent<T>> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof androidx.paging.CachedPageEventFlow$downstreamFlow$1$2$emit$1
                        if (r0 == 0) goto L_0x0014
                        r0 = r6
                        androidx.paging.CachedPageEventFlow$downstreamFlow$1$2$emit$1 r0 = (androidx.paging.CachedPageEventFlow$downstreamFlow$1$2$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L_0x0014
                        int r6 = r0.label
                        int r6 = r6 - r2
                        r0.label = r6
                        goto L_0x0019
                    L_0x0014:
                        androidx.paging.CachedPageEventFlow$downstreamFlow$1$2$emit$1 r0 = new androidx.paging.CachedPageEventFlow$downstreamFlow$1$2$emit$1
                        r0.<init>(r4, r6)
                    L_0x0019:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L_0x003a
                        if (r2 != r3) goto L_0x0032
                        java.lang.Object r5 = r0.L$1
                        kotlin.collections.IndexedValue r5 = (kotlin.collections.IndexedValue) r5
                        java.lang.Object r0 = r0.L$0
                        androidx.paging.CachedPageEventFlow$downstreamFlow$1$2 r0 = (androidx.paging.CachedPageEventFlow$downstreamFlow$1.AnonymousClass2) r0
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L_0x005e
                    L_0x0032:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L_0x003a:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                        int r6 = r5.getIndex()
                        kotlin.jvm.internal.Ref$IntRef r2 = r1
                        int r2 = r2.element
                        if (r6 <= r2) goto L_0x0066
                        kotlinx.coroutines.flow.FlowCollector<androidx.paging.PageEvent<T>> r6 = r7
                        java.lang.Object r2 = r5.getValue()
                        r0.L$0 = r4
                        r0.L$1 = r5
                        r0.label = r3
                        java.lang.Object r6 = r6.emit(r2, r0)
                        if (r6 != r1) goto L_0x005d
                        return r1
                    L_0x005d:
                        r0 = r4
                    L_0x005e:
                        kotlin.jvm.internal.Ref$IntRef r6 = r1
                        int r5 = r5.getIndex()
                        r6.element = r5
                    L_0x0066:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.CachedPageEventFlow$downstreamFlow$1.AnonymousClass2.emit(kotlin.collections.IndexedValue, kotlin.coroutines.Continuation):java.lang.Object");
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

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0018\u00010\u0005H@"}, d2 = {"<anonymous>", "", "T", "", "it", "Lkotlin/collections/IndexedValue;", "Landroidx/paging/PageEvent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.paging.CachedPageEventFlow$downstreamFlow$1$1", f = "CachedPageEventFlow.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.paging.CachedPageEventFlow$downstreamFlow$1$1  reason: invalid class name */
    /* compiled from: CachedPageEventFlow.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<IndexedValue<? extends PageEvent<T>>, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(IndexedValue<? extends PageEvent<T>> indexedValue, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(indexedValue, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(((IndexedValue) this.L$0) != null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
