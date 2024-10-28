package androidx.paging;

import androidx.paging.PageFetcherSnapshotState;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshotState$Holder", f = "PageFetcherSnapshotState.kt", i = {0, 0, 0}, l = {403}, m = "withLock", n = {"this", "block", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: PageFetcherSnapshotState.kt */
final class PageFetcherSnapshotState$Holder$withLock$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageFetcherSnapshotState.Holder<Key, Value> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshotState$Holder$withLock$1(PageFetcherSnapshotState.Holder<Key, Value> holder, Continuation<? super PageFetcherSnapshotState$Holder$withLock$1> continuation) {
        super(continuation);
        this.this$0 = holder;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.withLock((Function1) null, this);
    }
}
