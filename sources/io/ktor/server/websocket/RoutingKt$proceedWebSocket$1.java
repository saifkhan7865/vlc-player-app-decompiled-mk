package io.ktor.server.websocket;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.websocket.RoutingKt", f = "Routing.kt", i = {0}, l = {238, 239}, m = "proceedWebSocket", n = {"session"}, s = {"L$0"})
/* compiled from: Routing.kt */
final class RoutingKt$proceedWebSocket$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    RoutingKt$proceedWebSocket$1(Continuation<? super RoutingKt$proceedWebSocket$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RoutingKt.proceedWebSocket((WebSocketServerSession) null, (Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
