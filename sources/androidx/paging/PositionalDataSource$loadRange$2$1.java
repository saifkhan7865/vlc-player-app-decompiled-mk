package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/paging/PositionalDataSource$loadRange$2$1", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "onResult", "", "data", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PositionalDataSource.kt */
public final class PositionalDataSource$loadRange$2$1 extends PositionalDataSource.LoadRangeCallback<T> {
    final /* synthetic */ CancellableContinuation<DataSource.BaseResult<T>> $cont;
    final /* synthetic */ PositionalDataSource.LoadRangeParams $params;
    final /* synthetic */ PositionalDataSource<T> this$0;

    PositionalDataSource$loadRange$2$1(PositionalDataSource.LoadRangeParams loadRangeParams, PositionalDataSource<T> positionalDataSource, CancellableContinuation<? super DataSource.BaseResult<T>> cancellableContinuation) {
        this.$params = loadRangeParams;
        this.this$0 = positionalDataSource;
        this.$cont = cancellableContinuation;
    }

    public void onResult(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        Integer valueOf = this.$params.startPosition == 0 ? null : Integer.valueOf(this.$params.startPosition);
        if (this.this$0.isInvalid()) {
            Result.Companion companion = Result.Companion;
            this.$cont.resumeWith(Result.m1774constructorimpl(DataSource.BaseResult.Companion.empty$paging_common()));
            return;
        }
        Result.Companion companion2 = Result.Companion;
        this.$cont.resumeWith(Result.m1774constructorimpl(new DataSource.BaseResult(list, valueOf, Integer.valueOf(this.$params.startPosition + list.size()), 0, 0, 24, (DefaultConstructorMarker) null)));
    }
}
