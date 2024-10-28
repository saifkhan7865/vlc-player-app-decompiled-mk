package io.ktor.server.websocket;

import io.ktor.websocket.WebSocketSession;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.websocket.WebSocketUpgrade$upgrade$2", f = "WebSocketUpgrade.kt", i = {}, l = {98, 99}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: WebSocketUpgrade.kt */
final class WebSocketUpgrade$upgrade$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WebSocketSession $webSocket;
    int label;
    final /* synthetic */ WebSocketUpgrade this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebSocketUpgrade$upgrade$2(WebSocketUpgrade webSocketUpgrade, WebSocketSession webSocketSession, Continuation<? super WebSocketUpgrade$upgrade$2> continuation) {
        super(2, continuation);
        this.this$0 = webSocketUpgrade;
        this.$webSocket = webSocketSession;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WebSocketUpgrade$upgrade$2(this.this$0, this.$webSocket, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WebSocketUpgrade$upgrade$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<WebSocketSession, Continuation<? super Unit>, Object> handle = this.this$0.getHandle();
            WebSocketSession webSocketSession = this.$webSocket;
            this.label = 1;
            if (handle.invoke(webSocketSession, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable unused) {
            }
            CoroutineScopeKt.cancel$default(this.$webSocket, (CancellationException) null, 1, (Object) null);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.label = 2;
        if (this.$webSocket.flush(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        CoroutineScopeKt.cancel$default(this.$webSocket, (CancellationException) null, 1, (Object) null);
        return Unit.INSTANCE;
    }
}
