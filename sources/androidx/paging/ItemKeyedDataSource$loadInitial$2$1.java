package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J&\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"androidx/paging/ItemKeyedDataSource$loadInitial$2$1", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ItemKeyedDataSource.kt */
public final class ItemKeyedDataSource$loadInitial$2$1 extends ItemKeyedDataSource.LoadInitialCallback<Value> {
    final /* synthetic */ CancellableContinuation<DataSource.BaseResult<Value>> $cont;
    final /* synthetic */ ItemKeyedDataSource<Key, Value> this$0;

    ItemKeyedDataSource$loadInitial$2$1(CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation, ItemKeyedDataSource<Key, Value> itemKeyedDataSource) {
        this.$cont = cancellableContinuation;
        this.this$0 = itemKeyedDataSource;
    }

    public void onResult(List<? extends Value> list, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "data");
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1774constructorimpl(new DataSource.BaseResult(list, this.this$0.getPrevKey$paging_common(list), this.this$0.getNextKey$paging_common(list), i, (i2 - list.size()) - i)));
    }

    public void onResult(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1774constructorimpl(new DataSource.BaseResult(list, this.this$0.getPrevKey$paging_common(list), this.this$0.getNextKey$paging_common(list), 0, 0, 24, (DefaultConstructorMarker) null)));
    }
}
