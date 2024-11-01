package androidx.paging;

import androidx.paging.PositionalDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J&\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"androidx/paging/WrapperPositionalDataSource$loadInitial$1", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WrapperPositionalDataSource.kt */
public final class WrapperPositionalDataSource$loadInitial$1 extends PositionalDataSource.LoadInitialCallback<A> {
    final /* synthetic */ PositionalDataSource.LoadInitialCallback<B> $callback;
    final /* synthetic */ WrapperPositionalDataSource<A, B> this$0;

    WrapperPositionalDataSource$loadInitial$1(PositionalDataSource.LoadInitialCallback<B> loadInitialCallback, WrapperPositionalDataSource<A, B> wrapperPositionalDataSource) {
        this.$callback = loadInitialCallback;
        this.this$0 = wrapperPositionalDataSource;
    }

    public void onResult(List<? extends A> list, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.$callback.onResult(DataSource.Companion.convert$paging_common(this.this$0.getListFunction(), list), i, i2);
    }

    public void onResult(List<? extends A> list, int i) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.$callback.onResult(DataSource.Companion.convert$paging_common(this.this$0.getListFunction(), list), i);
    }
}
