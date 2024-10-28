package androidx.paging;

import androidx.paging.AsyncPagedListDiffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u0002H\u0002 \u0006*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00050\u0005H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "T", "", "it", "Landroidx/paging/AsyncPagedListDiffer$PagedListListener;", "kotlin.jvm.PlatformType", "invoke", "(Landroidx/paging/AsyncPagedListDiffer$PagedListListener;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AsyncPagedListDiffer.kt */
final class AsyncPagedListDiffer$removePagedListListener$1 extends Lambda implements Function1<AsyncPagedListDiffer.PagedListListener<T>, Boolean> {
    final /* synthetic */ Function2<PagedList<T>, PagedList<T>, Unit> $callback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncPagedListDiffer$removePagedListListener$1(Function2<? super PagedList<T>, ? super PagedList<T>, Unit> function2) {
        super(1);
        this.$callback = function2;
    }

    public final Boolean invoke(AsyncPagedListDiffer.PagedListListener<T> pagedListListener) {
        return Boolean.valueOf((pagedListListener instanceof AsyncPagedListDiffer.OnCurrentListChangedWrapper) && ((AsyncPagedListDiffer.OnCurrentListChangedWrapper) pagedListListener).getCallback() == this.$callback);
    }
}
