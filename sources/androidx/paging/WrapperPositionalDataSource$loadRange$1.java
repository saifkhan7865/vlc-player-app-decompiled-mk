package androidx.paging;

import androidx.paging.PositionalDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/paging/WrapperPositionalDataSource$loadRange$1", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "onResult", "", "data", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WrapperPositionalDataSource.kt */
public final class WrapperPositionalDataSource$loadRange$1 extends PositionalDataSource.LoadRangeCallback<A> {
    final /* synthetic */ PositionalDataSource.LoadRangeCallback<B> $callback;
    final /* synthetic */ WrapperPositionalDataSource<A, B> this$0;

    WrapperPositionalDataSource$loadRange$1(PositionalDataSource.LoadRangeCallback<B> loadRangeCallback, WrapperPositionalDataSource<A, B> wrapperPositionalDataSource) {
        this.$callback = loadRangeCallback;
        this.this$0 = wrapperPositionalDataSource;
    }

    public void onResult(List<? extends A> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.$callback.onResult(DataSource.Companion.convert$paging_common(this.this$0.getListFunction(), list));
    }
}
