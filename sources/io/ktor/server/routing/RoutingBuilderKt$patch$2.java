package io.ktor.server.routing;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/routing/Route;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingBuilder.kt */
final class RoutingBuilderKt$patch$2 extends Lambda implements Function1<Route, Unit> {
    final /* synthetic */ Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> $body;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoutingBuilderKt$patch$2(Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        super(1);
        this.$body = function3;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Route) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Route route) {
        Intrinsics.checkNotNullParameter(route, "$this$method");
        route.handle(this.$body);
    }
}
