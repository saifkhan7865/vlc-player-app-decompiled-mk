package androidx.paging;

import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.FlattenedPageController", f = "CachedPageEventFlow.kt", i = {0, 0, 0}, l = {288}, m = "record", n = {"this", "event", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: CachedPageEventFlow.kt */
final class FlattenedPageController$record$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlattenedPageController<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlattenedPageController$record$1(FlattenedPageController<T> flattenedPageController, Continuation<? super FlattenedPageController$record$1> continuation) {
        super(continuation);
        this.this$0 = flattenedPageController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.record((IndexedValue) null, this);
    }
}
