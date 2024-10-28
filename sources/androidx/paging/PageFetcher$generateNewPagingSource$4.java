package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PageFetcher.kt */
/* synthetic */ class PageFetcher$generateNewPagingSource$4 extends FunctionReferenceImpl implements Function0<Unit> {
    PageFetcher$generateNewPagingSource$4(Object obj) {
        super(0, obj, PageFetcher.class, "invalidate", "invalidate()V", 0);
    }

    public final void invoke() {
        ((PageFetcher) this.receiver).invalidate();
    }
}
