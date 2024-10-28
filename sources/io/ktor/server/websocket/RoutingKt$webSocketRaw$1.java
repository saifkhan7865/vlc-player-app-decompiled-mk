package io.ktor.server.websocket;

import io.ktor.server.routing.Route;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/routing/Route;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Routing.kt */
final class RoutingKt$webSocketRaw$1 extends Lambda implements Function1<Route, Unit> {
    final /* synthetic */ Function2<WebSocketServerSession, Continuation<? super Unit>, Object> $handler;
    final /* synthetic */ boolean $negotiateExtensions;
    final /* synthetic */ String $protocol;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoutingKt$webSocketRaw$1(String str, boolean z, Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        super(1);
        this.$protocol = str;
        this.$negotiateExtensions = z;
        this.$handler = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Route) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Route route) {
        Intrinsics.checkNotNullParameter(route, "$this$route");
        RoutingKt.webSocketRaw(route, this.$protocol, this.$negotiateExtensions, (Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) this.$handler);
    }
}
