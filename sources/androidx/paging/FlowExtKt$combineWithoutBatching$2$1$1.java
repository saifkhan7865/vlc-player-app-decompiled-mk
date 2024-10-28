package androidx.paging;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1", f = "FlowExt.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FlowExt.kt */
public final class FlowExtKt$combineWithoutBatching$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SimpleProducerScope<R> $$this$simpleChannelFlow;
    final /* synthetic */ Flow<Object> $flow;
    final /* synthetic */ AtomicInteger $incompleteFlows;
    final /* synthetic */ int $index;
    final /* synthetic */ UnbatchedFlowCombiner<T1, T2> $unbatchedFlowCombiner;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowExtKt$combineWithoutBatching$2$1$1(Flow<? extends Object> flow, AtomicInteger atomicInteger, SimpleProducerScope<R> simpleProducerScope, UnbatchedFlowCombiner<T1, T2> unbatchedFlowCombiner, int i, Continuation<? super FlowExtKt$combineWithoutBatching$2$1$1> continuation) {
        super(2, continuation);
        this.$flow = flow;
        this.$incompleteFlows = atomicInteger;
        this.$$this$simpleChannelFlow = simpleProducerScope;
        this.$unbatchedFlowCombiner = unbatchedFlowCombiner;
        this.$index = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FlowExtKt$combineWithoutBatching$2$1$1(this.$flow, this.$incompleteFlows, this.$$this$simpleChannelFlow, this.$unbatchedFlowCombiner, this.$index, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FlowExtKt$combineWithoutBatching$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<Object> flow = this.$flow;
            final UnbatchedFlowCombiner<T1, T2> unbatchedFlowCombiner = this.$unbatchedFlowCombiner;
            final int i2 = this.$index;
            this.label = 1;
            if (flow.collect(new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
                /* JADX WARNING: Removed duplicated region for block: B:19:0x0051 A[RETURN] */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1
                        if (r0 == 0) goto L_0x0014
                        r0 = r7
                        androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1 r0 = (androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L_0x0014
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L_0x0019
                    L_0x0014:
                        androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1 r0 = new androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1
                        r0.<init>(r5, r7)
                    L_0x0019:
                        java.lang.Object r7 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L_0x0039
                        if (r2 == r4) goto L_0x0035
                        if (r2 != r3) goto L_0x002d
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L_0x0052
                    L_0x002d:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L_0x0035:
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L_0x0049
                    L_0x0039:
                        kotlin.ResultKt.throwOnFailure(r7)
                        androidx.paging.UnbatchedFlowCombiner<T1, T2> r7 = r4
                        int r2 = r5
                        r0.label = r4
                        java.lang.Object r6 = r7.onNext(r2, r6, r0)
                        if (r6 != r1) goto L_0x0049
                        return r1
                    L_0x0049:
                        r0.label = r3
                        java.lang.Object r6 = kotlinx.coroutines.YieldKt.yield(r0)
                        if (r6 != r1) goto L_0x0052
                        return r1
                    L_0x0052:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                if (this.$incompleteFlows.decrementAndGet() == 0) {
                    SendChannel.DefaultImpls.close$default(this.$$this$simpleChannelFlow, (Throwable) null, 1, (Object) null);
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (this.$incompleteFlows.decrementAndGet() == 0) {
            SendChannel.DefaultImpls.close$default(this.$$this$simpleChannelFlow, (Throwable) null, 1, (Object) null);
        }
        return Unit.INSTANCE;
    }
}
