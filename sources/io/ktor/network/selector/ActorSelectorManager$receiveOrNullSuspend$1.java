package io.ktor.network.selector;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.selector.ActorSelectorManager", f = "ActorSelectorManager.kt", i = {0, 0}, l = {165}, m = "receiveOrNullSuspend", n = {"this", "$this$receiveOrNullSuspend"}, s = {"L$0", "L$1"})
/* compiled from: ActorSelectorManager.kt */
final class ActorSelectorManager$receiveOrNullSuspend$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ActorSelectorManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ActorSelectorManager$receiveOrNullSuspend$1(ActorSelectorManager actorSelectorManager, Continuation<? super ActorSelectorManager$receiveOrNullSuspend$1> continuation) {
        super(continuation);
        this.this$0 = actorSelectorManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.receiveOrNullSuspend((LockFreeMPSCQueue<Selectable>) null, this);
    }
}