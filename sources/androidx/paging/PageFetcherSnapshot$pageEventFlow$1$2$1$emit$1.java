package androidx.paging;

import androidx.paging.PageFetcherSnapshot$pageEventFlow$1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2$1", f = "PageFetcherSnapshot.kt", i = {}, l = {95}, m = "emit", n = {}, s = {})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$pageEventFlow$1$2$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageFetcherSnapshot$pageEventFlow$1.AnonymousClass2.AnonymousClass1<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$pageEventFlow$1$2$1$emit$1(PageFetcherSnapshot$pageEventFlow$1.AnonymousClass2.AnonymousClass1<? super T> r1, Continuation<? super PageFetcherSnapshot$pageEventFlow$1$2$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = r1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((PageEvent) null, (Continuation<? super Unit>) this);
    }
}
