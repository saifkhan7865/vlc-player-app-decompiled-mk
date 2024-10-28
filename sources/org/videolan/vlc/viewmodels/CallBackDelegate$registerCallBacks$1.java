package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.CallBackDelegate$registerCallBacks$1", f = "CallBackDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CallBackDelegate.kt */
final class CallBackDelegate$registerCallBacks$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $refresh;
    int label;
    final /* synthetic */ CallBackDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallBackDelegate$registerCallBacks$1(CallBackDelegate callBackDelegate, Function0<Unit> function0, Continuation<? super CallBackDelegate$registerCallBacks$1> continuation) {
        super(1, continuation);
        this.this$0 = callBackDelegate;
        this.$refresh = function0;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new CallBackDelegate$registerCallBacks$1(this.this$0, this.$refresh, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((CallBackDelegate$registerCallBacks$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.getPaused()) {
                this.this$0.setInvalid(true);
            } else {
                this.$refresh.invoke();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
