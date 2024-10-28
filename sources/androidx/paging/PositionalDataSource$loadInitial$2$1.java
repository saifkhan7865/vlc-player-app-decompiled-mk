package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J&\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u001e\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0002¨\u0006\u000e"}, d2 = {"androidx/paging/PositionalDataSource$loadInitial$2$1", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "onResult", "", "data", "", "position", "", "totalCount", "resume", "params", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "result", "Landroidx/paging/DataSource$BaseResult;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PositionalDataSource.kt */
public final class PositionalDataSource$loadInitial$2$1 extends PositionalDataSource.LoadInitialCallback<T> {
    final /* synthetic */ CancellableContinuation<DataSource.BaseResult<T>> $cont;
    final /* synthetic */ PositionalDataSource.LoadInitialParams $params;
    final /* synthetic */ PositionalDataSource<T> this$0;

    PositionalDataSource$loadInitial$2$1(PositionalDataSource<T> positionalDataSource, CancellableContinuation<? super DataSource.BaseResult<T>> cancellableContinuation, PositionalDataSource.LoadInitialParams loadInitialParams) {
        this.this$0 = positionalDataSource;
        this.$cont = cancellableContinuation;
        this.$params = loadInitialParams;
    }

    public void onResult(List<? extends T> list, int i, int i2) {
        Integer num;
        Integer num2;
        Intrinsics.checkNotNullParameter(list, "data");
        if (this.this$0.isInvalid()) {
            Result.Companion companion = Result.Companion;
            this.$cont.resumeWith(Result.m1774constructorimpl(DataSource.BaseResult.Companion.empty$paging_common()));
            return;
        }
        int size = list.size() + i;
        PositionalDataSource.LoadInitialParams loadInitialParams = this.$params;
        if (i == 0) {
            num = null;
        } else {
            num = Integer.valueOf(i);
        }
        if (size == i2) {
            num2 = null;
        } else {
            num2 = Integer.valueOf(size);
        }
        resume(loadInitialParams, new DataSource.BaseResult(list, num, num2, i, (i2 - list.size()) - i));
    }

    public void onResult(List<? extends T> list, int i) {
        Integer num;
        Intrinsics.checkNotNullParameter(list, "data");
        if (this.this$0.isInvalid()) {
            Result.Companion companion = Result.Companion;
            this.$cont.resumeWith(Result.m1774constructorimpl(DataSource.BaseResult.Companion.empty$paging_common()));
            return;
        }
        PositionalDataSource.LoadInitialParams loadInitialParams = this.$params;
        if (i == 0) {
            num = null;
        } else {
            num = Integer.valueOf(i);
        }
        resume(loadInitialParams, new DataSource.BaseResult(list, num, Integer.valueOf(list.size() + i), i, Integer.MIN_VALUE));
    }

    private final void resume(PositionalDataSource.LoadInitialParams loadInitialParams, DataSource.BaseResult<T> baseResult) {
        if (loadInitialParams.placeholdersEnabled) {
            baseResult.validateForInitialTiling$paging_common(loadInitialParams.pageSize);
        }
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1774constructorimpl(baseResult));
    }
}
