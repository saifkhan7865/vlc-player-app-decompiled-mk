package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.FlowExtKt$simpleRunningReduce$1", f = "FlowExt.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FlowExt.kt */
final class FlowExtKt$simpleRunningReduce$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<T, T, Continuation<? super T>, Object> $operation;
    final /* synthetic */ Flow<T> $this_simpleRunningReduce;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowExtKt$simpleRunningReduce$1(Flow<? extends T> flow, Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3, Continuation<? super FlowExtKt$simpleRunningReduce$1> continuation) {
        super(2, continuation);
        this.$this_simpleRunningReduce = flow;
        this.$operation = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowExtKt$simpleRunningReduce$1 flowExtKt$simpleRunningReduce$1 = new FlowExtKt$simpleRunningReduce$1(this.$this_simpleRunningReduce, this.$operation, continuation);
        flowExtKt$simpleRunningReduce$1.L$0 = obj;
        return flowExtKt$simpleRunningReduce$1;
    }

    public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return ((FlowExtKt$simpleRunningReduce$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final FlowCollector flowCollector = (FlowCollector) this.L$0;
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = FlowExtKt.NULL;
            Flow<T> flow = this.$this_simpleRunningReduce;
            final Function3<T, T, Continuation<? super T>, Object> function3 = this.$operation;
            this.label = 1;
            if (flow.collect(new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
                /* JADX WARNING: Removed duplicated region for block: B:24:0x007f A[RETURN] */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(T r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof androidx.paging.FlowExtKt$simpleRunningReduce$1$1$emit$1
                        if (r0 == 0) goto L_0x0014
                        r0 = r9
                        androidx.paging.FlowExtKt$simpleRunningReduce$1$1$emit$1 r0 = (androidx.paging.FlowExtKt$simpleRunningReduce$1$1$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L_0x0014
                        int r9 = r0.label
                        int r9 = r9 - r2
                        r0.label = r9
                        goto L_0x0019
                    L_0x0014:
                        androidx.paging.FlowExtKt$simpleRunningReduce$1$1$emit$1 r0 = new androidx.paging.FlowExtKt$simpleRunningReduce$1$1$emit$1
                        r0.<init>(r7, r9)
                    L_0x0019:
                        java.lang.Object r9 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L_0x0041
                        if (r2 == r4) goto L_0x0035
                        if (r2 != r3) goto L_0x002d
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L_0x0080
                    L_0x002d:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L_0x0035:
                        java.lang.Object r8 = r0.L$1
                        kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
                        java.lang.Object r2 = r0.L$0
                        androidx.paging.FlowExtKt$simpleRunningReduce$1$1 r2 = (androidx.paging.FlowExtKt$simpleRunningReduce$1.AnonymousClass1) r2
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L_0x0067
                    L_0x0041:
                        kotlin.ResultKt.throwOnFailure(r9)
                        kotlin.jvm.internal.Ref$ObjectRef<java.lang.Object> r9 = r1
                        T r2 = r9.element
                        java.lang.Object r5 = androidx.paging.FlowExtKt.NULL
                        if (r2 != r5) goto L_0x0050
                        r2 = r7
                        goto L_0x006a
                    L_0x0050:
                        kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super T>, java.lang.Object> r2 = r5
                        kotlin.jvm.internal.Ref$ObjectRef<java.lang.Object> r5 = r1
                        T r5 = r5.element
                        r0.L$0 = r7
                        r0.L$1 = r9
                        r0.label = r4
                        java.lang.Object r8 = r2.invoke(r5, r8, r0)
                        if (r8 != r1) goto L_0x0063
                        return r1
                    L_0x0063:
                        r2 = r7
                        r6 = r9
                        r9 = r8
                        r8 = r6
                    L_0x0067:
                        r6 = r9
                        r9 = r8
                        r8 = r6
                    L_0x006a:
                        r9.element = r8
                        kotlinx.coroutines.flow.FlowCollector<T> r8 = r7
                        kotlin.jvm.internal.Ref$ObjectRef<java.lang.Object> r9 = r1
                        T r9 = r9.element
                        r2 = 0
                        r0.L$0 = r2
                        r0.L$1 = r2
                        r0.label = r3
                        java.lang.Object r8 = r8.emit(r9, r0)
                        if (r8 != r1) goto L_0x0080
                        return r1
                    L_0x0080:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt$simpleRunningReduce$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
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
