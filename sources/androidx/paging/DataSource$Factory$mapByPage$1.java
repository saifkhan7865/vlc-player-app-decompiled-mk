package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\u0016¨\u0006\u0004"}, d2 = {"androidx/paging/DataSource$Factory$mapByPage$1", "Landroidx/paging/DataSource$Factory;", "create", "Landroidx/paging/DataSource;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DataSource.kt */
public final class DataSource$Factory$mapByPage$1 extends DataSource.Factory<Key, ToValue> {
    final /* synthetic */ Function<List<Value>, List<ToValue>> $function;
    final /* synthetic */ DataSource.Factory<Key, Value> this$0;

    DataSource$Factory$mapByPage$1(DataSource.Factory<Key, Value> factory, Function<List<Value>, List<ToValue>> function) {
        this.this$0 = factory;
        this.$function = function;
    }

    public DataSource<Key, ToValue> create() {
        return this.this$0.create().mapByPage(this.$function);
    }
}
