package androidx.paging.multicast;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.multicast.StoreRealActor", f = "StoreRealActor.kt", i = {0}, l = {74, 76}, m = "close", n = {"this"}, s = {"L$0"})
/* compiled from: StoreRealActor.kt */
final class StoreRealActor$close$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StoreRealActor<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StoreRealActor$close$1(StoreRealActor<T> storeRealActor, Continuation<? super StoreRealActor$close$1> continuation) {
        super(continuation);
        this.this$0 = storeRealActor;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.close(this);
    }
}
