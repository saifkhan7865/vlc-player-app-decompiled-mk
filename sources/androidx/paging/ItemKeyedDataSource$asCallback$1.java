package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/paging/ItemKeyedDataSource$asCallback$1", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "onResult", "", "data", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ItemKeyedDataSource.kt */
public final class ItemKeyedDataSource$asCallback$1 extends ItemKeyedDataSource.LoadCallback<Value> {
    final /* synthetic */ CancellableContinuation<DataSource.BaseResult<Value>> $this_asCallback;
    final /* synthetic */ ItemKeyedDataSource<Key, Value> this$0;

    ItemKeyedDataSource$asCallback$1(CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation, ItemKeyedDataSource<Key, Value> itemKeyedDataSource) {
        this.$this_asCallback = cancellableContinuation;
        this.this$0 = itemKeyedDataSource;
    }

    public void onResult(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        Result.Companion companion = Result.Companion;
        this.$this_asCallback.resumeWith(Result.m1774constructorimpl(new DataSource.BaseResult(list, this.this$0.getPrevKey$paging_common(list), this.this$0.getNextKey$paging_common(list), 0, 0, 24, (DefaultConstructorMarker) null)));
    }
}
