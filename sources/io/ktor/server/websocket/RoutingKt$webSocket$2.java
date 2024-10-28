package io.ktor.server.websocket;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/websocket/WebSocketServerSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.websocket.RoutingKt$webSocket$2", f = "Routing.kt", i = {}, l = {202}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Routing.kt */
final class RoutingKt$webSocket$2 extends SuspendLambda implements Function2<WebSocketServerSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<DefaultWebSocketServerSession, Continuation<? super Unit>, Object> $handler;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoutingKt$webSocket$2(Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super RoutingKt$webSocket$2> continuation) {
        super(2, continuation);
        this.$handler = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RoutingKt$webSocket$2 routingKt$webSocket$2 = new RoutingKt$webSocket$2(this.$handler, continuation);
        routingKt$webSocket$2.L$0 = obj;
        return routingKt$webSocket$2;
    }

    public final Object invoke(WebSocketServerSession webSocketServerSession, Continuation<? super Unit> continuation) {
        return ((RoutingKt$webSocket$2) create(webSocketServerSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (RoutingKt.proceedWebSocket((WebSocketServerSession) this.L$0, this.$handler, this) == coroutine_suspended) {
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
