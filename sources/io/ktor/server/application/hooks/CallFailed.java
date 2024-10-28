package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002H\u0012D\u0012B\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\fJ`\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112F\u0010\u0012\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0002H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/server/application/hooks/CallFailed;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "name", "call", "", "cause", "Lkotlin/coroutines/Continuation;", "", "", "()V", "phase", "Lio/ktor/util/pipeline/PipelinePhase;", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "(Lio/ktor/server/application/ApplicationCallPipeline;Lkotlin/jvm/functions/Function3;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommonHooks.kt */
public final class CallFailed implements Hook<Function3<? super ApplicationCall, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object>> {
    public static final CallFailed INSTANCE = new CallFailed();
    private static final PipelinePhase phase = new PipelinePhase("BeforeSetup");

    private CallFailed() {
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function3<? super ApplicationCall, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function3, "handler");
        PipelinePhase setup = ApplicationCallPipeline.ApplicationPhase.getSetup();
        PipelinePhase pipelinePhase = phase;
        applicationCallPipeline.insertPhaseBefore(setup, pipelinePhase);
        applicationCallPipeline.intercept(pipelinePhase, new CallFailed$install$1(function3, (Continuation<? super CallFailed$install$1>) null));
    }
}
