package androidx.paging;

import androidx.paging.PageEvent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageEvent$Insert", f = "PageEvent.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {158}, m = "filter", n = {"predicate", "this_$iv$iv", "destination$iv$iv$iv", "it", "data", "originalIndices", "t", "index$iv", "index"}, s = {"L$0", "L$1", "L$3", "L$5", "L$6", "L$7", "L$9", "I$0", "I$1"})
/* compiled from: PageEvent.kt */
final class PageEvent$Insert$filter$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageEvent.Insert<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageEvent$Insert$filter$1(PageEvent.Insert<T> insert, Continuation<? super PageEvent$Insert$filter$1> continuation) {
        super(continuation);
        this.this$0 = insert;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.filter((Function2) null, this);
    }
}
