package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.SeparatorState", f = "Separators.kt", i = {0, 1, 2}, l = {214, 216, 217}, m = "onEvent", n = {"this", "this", "this"}, s = {"L$0", "L$0", "L$0"})
/* compiled from: Separators.kt */
final class SeparatorState$onEvent$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SeparatorState<R, T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SeparatorState$onEvent$1(SeparatorState<R, T> separatorState, Continuation<? super SeparatorState$onEvent$1> continuation) {
        super(continuation);
        this.this$0 = separatorState;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onEvent((PageEvent) null, this);
    }
}
