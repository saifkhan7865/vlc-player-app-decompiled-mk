package io.ktor.server.websocket;

import io.ktor.server.application.ApplicationCall;
import io.ktor.websocket.DefaultWebSocketSession;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.websocket.RoutingKt", f = "Routing.kt", i = {0, 0, 1}, l = {253, 254}, m = "handleServerSession", n = {"$this$handleServerSession", "call", "call"}, s = {"L$0", "L$1", "L$0"})
/* compiled from: Routing.kt */
final class RoutingKt$handleServerSession$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    RoutingKt$handleServerSession$1(Continuation<? super RoutingKt$handleServerSession$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RoutingKt.handleServerSession((DefaultWebSocketSession) null, (ApplicationCall) null, (Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
