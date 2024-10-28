package androidx.paging;

import androidx.paging.PagedList;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "T", "", "it", "Ljava/lang/ref/WeakReference;", "Landroidx/paging/PagedList$Callback;", "invoke", "(Ljava/lang/ref/WeakReference;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PagedList.kt */
final class PagedList$removeWeakCallback$1 extends Lambda implements Function1<WeakReference<PagedList.Callback>, Boolean> {
    final /* synthetic */ PagedList.Callback $callback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagedList$removeWeakCallback$1(PagedList.Callback callback) {
        super(1);
        this.$callback = callback;
    }

    public final Boolean invoke(WeakReference<PagedList.Callback> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "it");
        return Boolean.valueOf(weakReference.get() == null || weakReference.get() == this.$callback);
    }
}
