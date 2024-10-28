package androidx.paging;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AsyncPagedListDiffer.kt */
/* synthetic */ class AsyncPagedListDiffer$loadStateListener$1 extends FunctionReferenceImpl implements Function2<LoadType, LoadState, Unit> {
    AsyncPagedListDiffer$loadStateListener$1(Object obj) {
        super(2, obj, PagedList.LoadStateManager.class, "setState", "setState(Landroidx/paging/LoadType;Landroidx/paging/LoadState;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LoadType) obj, (LoadState) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "p0");
        Intrinsics.checkNotNullParameter(loadState, "p1");
        ((PagedList.LoadStateManager) this.receiver).setState(loadType, loadState);
    }
}
