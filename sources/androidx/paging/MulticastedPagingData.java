package androidx.paging;

import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006J\u0011\u0010\u0013\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Landroidx/paging/MulticastedPagingData;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "parent", "Landroidx/paging/PagingData;", "tracker", "Landroidx/paging/ActiveFlowTracker;", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/paging/PagingData;Landroidx/paging/ActiveFlowTracker;)V", "accumulated", "Landroidx/paging/CachedPageEventFlow;", "getParent", "()Landroidx/paging/PagingData;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "getTracker", "()Landroidx/paging/ActiveFlowTracker;", "asPagingData", "close", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CachedPagingData.kt */
final class MulticastedPagingData<T> {
    /* access modifiers changed from: private */
    public final CachedPageEventFlow<T> accumulated;
    private final PagingData<T> parent;
    private final CoroutineScope scope;
    private final ActiveFlowTracker tracker;

    public MulticastedPagingData(CoroutineScope coroutineScope, PagingData<T> pagingData, ActiveFlowTracker activeFlowTracker) {
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        Intrinsics.checkNotNullParameter(pagingData, "parent");
        this.scope = coroutineScope;
        this.parent = pagingData;
        this.tracker = activeFlowTracker;
        CachedPageEventFlow<T> cachedPageEventFlow = new CachedPageEventFlow<>(pagingData.getFlow$paging_common(), coroutineScope);
        if (activeFlowTracker != null) {
            activeFlowTracker.onNewCachedEventFlow(cachedPageEventFlow);
        }
        this.accumulated = cachedPageEventFlow;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MulticastedPagingData(CoroutineScope coroutineScope, PagingData pagingData, ActiveFlowTracker activeFlowTracker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineScope, pagingData, (i & 4) != 0 ? null : activeFlowTracker);
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    public final PagingData<T> getParent() {
        return this.parent;
    }

    public final ActiveFlowTracker getTracker() {
        return this.tracker;
    }

    public final PagingData<T> asPagingData() {
        return new PagingData<>(FlowKt.onCompletion(FlowKt.onStart(this.accumulated.getDownstreamFlow(), new MulticastedPagingData$asPagingData$1(this, (Continuation<? super MulticastedPagingData$asPagingData$1>) null)), new MulticastedPagingData$asPagingData$2(this, (Continuation<? super MulticastedPagingData$asPagingData$2>) null)), this.parent.getUiReceiver$paging_common(), this.parent.getHintReceiver$paging_common(), new MulticastedPagingData$asPagingData$3(this));
    }

    public final Object close(Continuation<? super Unit> continuation) {
        this.accumulated.close();
        return Unit.INSTANCE;
    }
}
