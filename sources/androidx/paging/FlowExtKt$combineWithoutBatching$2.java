package androidx.paging;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Landroidx/paging/SimpleProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "androidx.paging.FlowExtKt$combineWithoutBatching$2", f = "FlowExt.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FlowExt.kt */
public final class FlowExtKt$combineWithoutBatching$2 extends SuspendLambda implements Function2<SimpleProducerScope<R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T2> $otherFlow;
    final /* synthetic */ Flow<T1> $this_combineWithoutBatching;
    final /* synthetic */ Function4<T1, T2, CombineSource, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowExtKt$combineWithoutBatching$2(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super FlowExtKt$combineWithoutBatching$2> continuation) {
        super(2, continuation);
        this.$this_combineWithoutBatching = flow;
        this.$otherFlow = flow2;
        this.$transform = function4;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowExtKt$combineWithoutBatching$2 flowExtKt$combineWithoutBatching$2 = new FlowExtKt$combineWithoutBatching$2(this.$this_combineWithoutBatching, this.$otherFlow, this.$transform, continuation);
        flowExtKt$combineWithoutBatching$2.L$0 = obj;
        return flowExtKt$combineWithoutBatching$2;
    }

    public final Object invoke(SimpleProducerScope<R> simpleProducerScope, Continuation<? super Unit> continuation) {
        return ((FlowExtKt$combineWithoutBatching$2) create(simpleProducerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
            AtomicInteger atomicInteger = new AtomicInteger(2);
            UnbatchedFlowCombiner unbatchedFlowCombiner = new UnbatchedFlowCombiner(new FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(simpleProducerScope, this.$transform, (Continuation<? super FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1>) null));
            final CompletableJob Job$default = JobKt.Job$default((Job) null, 1, (Object) null);
            Flow[] flowArr = {this.$this_combineWithoutBatching, this.$otherFlow};
            int i2 = 0;
            int i3 = 0;
            while (i3 < 2) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(simpleProducerScope, Job$default, (CoroutineStart) null, new FlowExtKt$combineWithoutBatching$2$1$1(flowArr[i3], atomicInteger, simpleProducerScope, unbatchedFlowCombiner, i2, (Continuation<? super FlowExtKt$combineWithoutBatching$2$1$1>) null), 2, (Object) null);
                i3++;
                i2++;
            }
            this.label = 1;
            if (simpleProducerScope.awaitClose(new Function0<Unit>() {
                public final void invoke() {
                    Job.DefaultImpls.cancel$default((Job) r13, (CancellationException) null, 1, (Object) null);
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

    public final Object invokeSuspend$$forInline(Object obj) {
        SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
        AtomicInteger atomicInteger = new AtomicInteger(2);
        UnbatchedFlowCombiner unbatchedFlowCombiner = new UnbatchedFlowCombiner(new FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(simpleProducerScope, this.$transform, (Continuation<? super FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1>) null));
        final CompletableJob Job$default = JobKt.Job$default((Job) null, 1, (Object) null);
        Flow[] flowArr = {this.$this_combineWithoutBatching, this.$otherFlow};
        int i = 0;
        int i2 = 0;
        while (i2 < 2) {
            Flow flow = flowArr[i2];
            int i3 = i + 1;
            Flow flow2 = flow;
            Job unused = BuildersKt__Builders_commonKt.launch$default(simpleProducerScope, Job$default, (CoroutineStart) null, new FlowExtKt$combineWithoutBatching$2$1$1(flow, atomicInteger, simpleProducerScope, unbatchedFlowCombiner, Integer.valueOf(i).intValue(), (Continuation<? super FlowExtKt$combineWithoutBatching$2$1$1>) null), 2, (Object) null);
            Unit unit = Unit.INSTANCE;
            i2++;
            i = i3;
        }
        InlineMarker.mark(0);
        simpleProducerScope.awaitClose(new Function0<Unit>() {
            public final void invoke() {
                Job.DefaultImpls.cancel$default((Job) Job$default, (CancellationException) null, 1, (Object) null);
            }
        }, this);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
