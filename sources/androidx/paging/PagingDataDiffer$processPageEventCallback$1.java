package androidx.paging;

import androidx.paging.PagePresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J \u0010\t\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, d2 = {"androidx/paging/PagingDataDiffer$processPageEventCallback$1", "Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "onChanged", "", "position", "", "count", "onInserted", "onRemoved", "onStateUpdate", "source", "Landroidx/paging/LoadStates;", "mediator", "loadType", "Landroidx/paging/LoadType;", "fromMediator", "", "loadState", "Landroidx/paging/LoadState;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PagingDataDiffer.kt */
public final class PagingDataDiffer$processPageEventCallback$1 implements PagePresenter.ProcessPageEventCallback {
    final /* synthetic */ PagingDataDiffer<T> this$0;

    PagingDataDiffer$processPageEventCallback$1(PagingDataDiffer<T> pagingDataDiffer) {
        this.this$0 = pagingDataDiffer;
    }

    public void onChanged(int i, int i2) {
        this.this$0.differCallback.onChanged(i, i2);
    }

    public void onInserted(int i, int i2) {
        this.this$0.differCallback.onInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.this$0.differCallback.onRemoved(i, i2);
    }

    public void onStateUpdate(LoadStates loadStates, LoadStates loadStates2) {
        Intrinsics.checkNotNullParameter(loadStates, "source");
        this.this$0.dispatchLoadStates$paging_common(loadStates, loadStates2);
    }

    public void onStateUpdate(LoadType loadType, boolean z, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        this.this$0.combinedLoadStatesCollection.set(loadType, z, loadState);
    }
}
