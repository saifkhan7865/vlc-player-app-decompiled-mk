package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.SimpleProducerScopeImpl", f = "SimpleChannelFlow.kt", i = {0, 0}, l = {100}, m = "awaitClose", n = {"block", "job"}, s = {"L$0", "L$1"})
/* compiled from: SimpleChannelFlow.kt */
final class SimpleProducerScopeImpl$awaitClose$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SimpleProducerScopeImpl<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SimpleProducerScopeImpl$awaitClose$1(SimpleProducerScopeImpl<T> simpleProducerScopeImpl, Continuation<? super SimpleProducerScopeImpl$awaitClose$1> continuation) {
        super(continuation);
        this.this$0 = simpleProducerScopeImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.awaitClose((Function0<Unit>) null, this);
    }
}
