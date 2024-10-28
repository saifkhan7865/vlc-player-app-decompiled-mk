package io.ktor.network.selector;

import java.nio.channels.Selector;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.selector.ActorSelectorManager", f = "ActorSelectorManager.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, l = {69, 73, 89}, m = "process", n = {"this", "mb", "selector", "this", "mb", "selector", "this", "mb", "selector"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: ActorSelectorManager.kt */
final class ActorSelectorManager$process$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ActorSelectorManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ActorSelectorManager$process$1(ActorSelectorManager actorSelectorManager, Continuation<? super ActorSelectorManager$process$1> continuation) {
        super(continuation);
        this.this$0 = actorSelectorManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.process((LockFreeMPSCQueue<Selectable>) null, (Selector) null, this);
    }
}
