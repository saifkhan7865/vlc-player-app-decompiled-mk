package androidx.paging;

import androidx.paging.PageEvent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.SeparatorState", f = "Separators.kt", i = {0, 0, 0, 0, 0}, l = {564}, m = "onStaticList", n = {"this", "event", "data", "item", "i"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
/* compiled from: Separators.kt */
final class SeparatorState$onStaticList$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SeparatorState<R, T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SeparatorState$onStaticList$1(SeparatorState<R, T> separatorState, Continuation<? super SeparatorState$onStaticList$1> continuation) {
        super(continuation);
        this.this$0 = separatorState;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onStaticList((PageEvent.StaticList) null, this);
    }
}
