package androidx.paging;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\n"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class PagingDataTransforms$map$$inlined$transform$2 implements Flow<PageEvent<R>> {
    final /* synthetic */ Executor $executor$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ Function1 $transform$inlined$1;

    public PagingDataTransforms$map$$inlined$transform$2(Flow flow, Executor executor, Function1 function1) {
        this.$this_unsafeTransform$inlined = flow;
        this.$executor$inlined = executor;
        this.$transform$inlined$1 = function1;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final Executor executor = this.$executor$inlined;
        final Function1 function1 = this.$transform$inlined$1;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x0071 A[RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
                /*
                    r9 = this;
                    boolean r0 = r11 instanceof androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0014
                    r0 = r11
                    androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r11 = r0.label
                    int r11 = r11 - r2
                    r0.label = r11
                    goto L_0x0019
                L_0x0014:
                    androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1
                    r0.<init>(r9, r11)
                L_0x0019:
                    java.lang.Object r11 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 0
                    r4 = 2
                    r5 = 1
                    if (r2 == 0) goto L_0x003e
                    if (r2 == r5) goto L_0x0036
                    if (r2 != r4) goto L_0x002e
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L_0x0072
                L_0x002e:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r11)
                    throw r10
                L_0x0036:
                    java.lang.Object r10 = r0.L$0
                    kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L_0x0067
                L_0x003e:
                    kotlin.ResultKt.throwOnFailure(r11)
                    kotlinx.coroutines.flow.FlowCollector r11 = r5
                    androidx.paging.PageEvent r10 = (androidx.paging.PageEvent) r10
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    java.util.concurrent.Executor r2 = r2
                    kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from((java.util.concurrent.Executor) r2)
                    kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
                    androidx.paging.PagingDataTransforms$map$2$1 r6 = new androidx.paging.PagingDataTransforms$map$2$1
                    kotlin.jvm.functions.Function1 r7 = r3
                    r6.<init>(r10, r7, r3)
                    kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                    r0.L$0 = r11
                    r0.label = r5
                    java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                    if (r10 != r1) goto L_0x0064
                    return r1
                L_0x0064:
                    r8 = r11
                    r11 = r10
                    r10 = r8
                L_0x0067:
                    r0.L$0 = r3
                    r0.label = r4
                    java.lang.Object r10 = r10.emit(r11, r0)
                    if (r10 != r1) goto L_0x0072
                    return r1
                L_0x0072:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        if (collect == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return collect;
        }
        return Unit.INSTANCE;
    }
}
