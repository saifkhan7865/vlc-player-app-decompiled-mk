package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/routing/Route;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingBuilder.kt */
final class RoutingBuilderKt$accept$1 extends Lambda implements Function1<Route, Unit> {
    final /* synthetic */ Function1<Route, Unit> $build;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoutingBuilderKt$accept$1(Function1<? super Route, Unit> function1) {
        super(1);
        this.$build = function1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Route) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Route route) {
        Intrinsics.checkNotNullParameter(route, "$this$accept");
        this.$build.invoke(route);
    }
}
