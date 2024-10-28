package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.util.InternalAPI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002$\u0012 \u0012\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0007J<\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lio/ktor/server/application/hooks/Metrics;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function2;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "", "", "()V", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "(Lio/ktor/server/application/ApplicationCallPipeline;Lkotlin/jvm/functions/Function2;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@InternalAPI
/* compiled from: CommonHooks.kt */
public final class Metrics implements Hook<Function2<? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object>> {
    public static final Metrics INSTANCE = new Metrics();

    private Metrics() {
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function2<? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function2, "handler");
        applicationCallPipeline.intercept(ApplicationCallPipeline.ApplicationPhase.getMonitoring(), new Metrics$install$1(function2, (Continuation<? super Metrics$install$1>) null));
    }
}
