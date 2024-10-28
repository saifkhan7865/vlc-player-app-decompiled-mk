package androidx.paging;

import androidx.paging.PageEvent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageEvent$StaticList", f = "PageEvent.kt", i = {0, 0, 0}, l = {58}, m = "flatMap", n = {"this", "transform", "destination$iv$iv"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: PageEvent.kt */
final class PageEvent$StaticList$flatMap$1<R> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageEvent.StaticList<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageEvent$StaticList$flatMap$1(PageEvent.StaticList<T> staticList, Continuation<? super PageEvent$StaticList$flatMap$1> continuation) {
        super(continuation);
        this.this$0 = staticList;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.flatMap((Function2) null, this);
    }
}
