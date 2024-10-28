package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002$\u0012 \u0012\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0007J<\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/server/auth/AuthenticationChecked;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function2;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "", "", "()V", "AfterAuthenticationPhase", "Lio/ktor/util/pipeline/PipelinePhase;", "getAfterAuthenticationPhase$ktor_server_auth", "()Lio/ktor/util/pipeline/PipelinePhase;", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "(Lio/ktor/server/application/ApplicationCallPipeline;Lkotlin/jvm/functions/Function2;)V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
public final class AuthenticationChecked implements Hook<Function2<? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object>> {
    private static final PipelinePhase AfterAuthenticationPhase = new PipelinePhase("AfterAuthentication");
    public static final AuthenticationChecked INSTANCE = new AuthenticationChecked();

    private AuthenticationChecked() {
    }

    public final PipelinePhase getAfterAuthenticationPhase$ktor_server_auth() {
        return AfterAuthenticationPhase;
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function2<? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function2, "handler");
        applicationCallPipeline.insertPhaseAfter(ApplicationCallPipeline.ApplicationPhase.getPlugins(), AuthenticationHook.INSTANCE.getAuthenticatePhase$ktor_server_auth());
        PipelinePhase authenticatePhase$ktor_server_auth = AuthenticationHook.INSTANCE.getAuthenticatePhase$ktor_server_auth();
        PipelinePhase pipelinePhase = AfterAuthenticationPhase;
        applicationCallPipeline.insertPhaseAfter(authenticatePhase$ktor_server_auth, pipelinePhase);
        applicationCallPipeline.intercept(pipelinePhase, new AuthenticationChecked$install$1(function2, (Continuation<? super AuthenticationChecked$install$1>) null));
    }
}
