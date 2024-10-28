package androidx.paging;

import androidx.paging.ActiveFlowTracker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H@"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/paging/PagingData;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.CachedPagingDataKt$cachedIn$5", f = "CachedPagingData.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CachedPagingData.kt */
final class CachedPagingDataKt$cachedIn$5 extends SuspendLambda implements Function3<FlowCollector<? super PagingData<T>>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ ActiveFlowTracker $tracker;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CachedPagingDataKt$cachedIn$5(ActiveFlowTracker activeFlowTracker, Continuation<? super CachedPagingDataKt$cachedIn$5> continuation) {
        super(3, continuation);
        this.$tracker = activeFlowTracker;
    }

    public final Object invoke(FlowCollector<? super PagingData<T>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        return new CachedPagingDataKt$cachedIn$5(this.$tracker, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ActiveFlowTracker activeFlowTracker = this.$tracker;
            if (activeFlowTracker != null) {
                this.label = 1;
                if (activeFlowTracker.onComplete(ActiveFlowTracker.FlowType.PAGED_DATA_FLOW, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
