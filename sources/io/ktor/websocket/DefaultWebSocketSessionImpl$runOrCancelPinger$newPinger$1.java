package io.ktor.websocket;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/websocket/CloseReason;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1", f = "DefaultWebSocketSession.kt", i = {}, l = {301}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DefaultWebSocketSession.kt */
final class DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1 extends SuspendLambda implements Function2<CloseReason, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DefaultWebSocketSessionImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, Continuation<? super DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1> continuation) {
        super(2, continuation);
        this.this$0 = defaultWebSocketSessionImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1 defaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1 = new DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1(this.this$0, continuation);
        defaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1.L$0 = obj;
        return defaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1;
    }

    public final Object invoke(CloseReason closeReason, Continuation<? super Unit> continuation) {
        return ((DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1) create(closeReason, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DefaultWebSocketSessionImpl defaultWebSocketSessionImpl = this.this$0;
            this.label = 1;
            if (defaultWebSocketSessionImpl.sendCloseSequence((CloseReason) this.L$0, new IOException("Ping timeout"), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
