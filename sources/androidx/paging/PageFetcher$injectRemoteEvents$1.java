package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00060\u0005H@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "Landroidx/paging/SimpleProducerScope;", "Landroidx/paging/PageEvent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcher$injectRemoteEvents$1", f = "PageFetcher.kt", i = {}, l = {203}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PageFetcher.kt */
final class PageFetcher$injectRemoteEvents$1 extends SuspendLambda implements Function2<SimpleProducerScope<PageEvent<Value>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ RemoteMediatorAccessor<Key, Value> $accessor;
    final /* synthetic */ MutableLoadStateCollection $sourceStates;
    final /* synthetic */ PageFetcherSnapshot<Key, Value> $this_injectRemoteEvents;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcher$injectRemoteEvents$1(RemoteMediatorAccessor<Key, Value> remoteMediatorAccessor, PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, MutableLoadStateCollection mutableLoadStateCollection, Continuation<? super PageFetcher$injectRemoteEvents$1> continuation) {
        super(2, continuation);
        this.$accessor = remoteMediatorAccessor;
        this.$this_injectRemoteEvents = pageFetcherSnapshot;
        this.$sourceStates = mutableLoadStateCollection;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PageFetcher$injectRemoteEvents$1 pageFetcher$injectRemoteEvents$1 = new PageFetcher$injectRemoteEvents$1(this.$accessor, this.$this_injectRemoteEvents, this.$sourceStates, continuation);
        pageFetcher$injectRemoteEvents$1.L$0 = obj;
        return pageFetcher$injectRemoteEvents$1;
    }

    public final Object invoke(SimpleProducerScope<PageEvent<Value>> simpleProducerScope, Continuation<? super Unit> continuation) {
        return ((PageFetcher$injectRemoteEvents$1) create(simpleProducerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
            this.label = 1;
            if (SimpleChannelFlowKt.simpleChannelFlow(new PageFetcher$injectRemoteEvents$1$invokeSuspend$$inlined$combineWithoutBatching$1(this.$accessor.getState(), this.$this_injectRemoteEvents.getPageEventFlow(), (Continuation) null, this.$sourceStates)).collect(new FlowCollector() {
                public final Object emit(PageEvent<Value> pageEvent, Continuation<? super Unit> continuation) {
                    Object send = simpleProducerScope.send(pageEvent, continuation);
                    return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
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
