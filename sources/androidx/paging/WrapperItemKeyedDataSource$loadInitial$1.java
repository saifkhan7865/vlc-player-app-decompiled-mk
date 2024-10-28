package androidx.paging;

import androidx.paging.ItemKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J&\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"androidx/paging/WrapperItemKeyedDataSource$loadInitial$1", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WrapperItemKeyedDataSource.kt */
public final class WrapperItemKeyedDataSource$loadInitial$1 extends ItemKeyedDataSource.LoadInitialCallback<A> {
    final /* synthetic */ ItemKeyedDataSource.LoadInitialCallback<B> $callback;
    final /* synthetic */ WrapperItemKeyedDataSource<K, A, B> this$0;

    WrapperItemKeyedDataSource$loadInitial$1(ItemKeyedDataSource.LoadInitialCallback<B> loadInitialCallback, WrapperItemKeyedDataSource<K, A, B> wrapperItemKeyedDataSource) {
        this.$callback = loadInitialCallback;
        this.this$0 = wrapperItemKeyedDataSource;
    }

    public void onResult(List<? extends A> list, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.$callback.onResult(this.this$0.convertWithStashedKeys(list), i, i2);
    }

    public void onResult(List<? extends A> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.$callback.onResult(this.this$0.convertWithStashedKeys(list));
    }
}
