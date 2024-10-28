package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "Landroidx/paging/PagingSource$LoadResult$Page;", "Key", "Value", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.LegacyPagingSource$load$2", f = "LegacyPagingSource.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: LegacyPagingSource.kt */
final class LegacyPagingSource$load$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PagingSource.LoadResult.Page<Key, Value>>, Object> {
    final /* synthetic */ DataSource.Params<Key> $dataSourceParams;
    final /* synthetic */ PagingSource.LoadParams<Key> $params;
    int label;
    final /* synthetic */ LegacyPagingSource<Key, Value> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LegacyPagingSource$load$2(LegacyPagingSource<Key, Value> legacyPagingSource, DataSource.Params<Key> params, PagingSource.LoadParams<Key> loadParams, Continuation<? super LegacyPagingSource$load$2> continuation) {
        super(2, continuation);
        this.this$0 = legacyPagingSource;
        this.$dataSourceParams = params;
        this.$params = loadParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LegacyPagingSource$load$2(this.this$0, this.$dataSourceParams, this.$params, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PagingSource.LoadResult.Page<Key, Value>> continuation) {
        return ((LegacyPagingSource$load$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.getDataSource$paging_common().load$paging_common(this.$dataSourceParams, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PagingSource.LoadParams<Key> loadParams = this.$params;
        DataSource.BaseResult baseResult = (DataSource.BaseResult) obj;
        return new PagingSource.LoadResult.Page(baseResult.data, (!baseResult.data.isEmpty() || !(loadParams instanceof PagingSource.LoadParams.Prepend)) ? baseResult.getPrevKey() : null, (!baseResult.data.isEmpty() || !(loadParams instanceof PagingSource.LoadParams.Append)) ? baseResult.getNextKey() : null, baseResult.getItemsBefore(), baseResult.getItemsAfter());
    }
}
