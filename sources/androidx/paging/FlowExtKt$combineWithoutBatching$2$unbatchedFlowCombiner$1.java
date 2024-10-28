package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u0002H\u00032\u0006\u0010\u0007\u001a\u00020\bH@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "t1", "t2", "updateFrom", "Landroidx/paging/CombineSource;"}, k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "androidx.paging.FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1", f = "FlowExt.kt", i = {}, l = {142, 142}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FlowExt.kt */
public final class FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1 extends SuspendLambda implements Function4<T1, T2, CombineSource, Continuation<? super Unit>, Object> {
    final /* synthetic */ SimpleProducerScope<R> $$this$simpleChannelFlow;
    final /* synthetic */ Function4<T1, T2, CombineSource, Continuation<? super R>, Object> $transform;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(SimpleProducerScope<R> simpleProducerScope, Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1> continuation) {
        super(4, continuation);
        this.$$this$simpleChannelFlow = simpleProducerScope;
        this.$transform = function4;
    }

    public final Object invoke(T1 t1, T2 t2, CombineSource combineSource, Continuation<? super Unit> continuation) {
        FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1 flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1 = new FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(this.$$this$simpleChannelFlow, this.$transform, continuation);
        flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.L$0 = t1;
        flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.L$1 = t2;
        flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.L$2 = combineSource;
        return flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        SimpleProducerScope<R> simpleProducerScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SimpleProducerScope<R> simpleProducerScope2 = this.$$this$simpleChannelFlow;
            Function4<T1, T2, CombineSource, Continuation<? super R>, Object> function4 = this.$transform;
            this.L$0 = simpleProducerScope2;
            this.L$1 = null;
            this.label = 1;
            obj = function4.invoke(this.L$0, this.L$1, (CombineSource) this.L$2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            simpleProducerScope = simpleProducerScope2;
        } else if (i == 1) {
            simpleProducerScope = (SimpleProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.L$0 = null;
        this.label = 2;
        if (simpleProducerScope.send(obj, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        SimpleProducerScope<R> simpleProducerScope = this.$$this$simpleChannelFlow;
        Function4<T1, T2, CombineSource, Continuation<? super R>, Object> function4 = this.$transform;
        InlineMarker.mark(0);
        simpleProducerScope.send(function4.invoke(this.L$0, this.L$1, (CombineSource) this.L$2, this), this);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
