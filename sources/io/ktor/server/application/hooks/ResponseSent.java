package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.response.ApplicationSendPipeline;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J$\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016¨\u0006\n"}, d2 = {"Lio/ktor/server/application/hooks/ResponseSent;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "()V", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommonHooks.kt */
public final class ResponseSent implements Hook<Function1<? super ApplicationCall, ? extends Unit>> {
    public static final ResponseSent INSTANCE = new ResponseSent();

    private ResponseSent() {
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function1<? super ApplicationCall, Unit> function1) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function1, "handler");
        applicationCallPipeline.getSendPipeline().intercept(ApplicationSendPipeline.Phases.getEngine(), new ResponseSent$install$1(function1, (Continuation<? super ResponseSent$install$1>) null));
    }
}
