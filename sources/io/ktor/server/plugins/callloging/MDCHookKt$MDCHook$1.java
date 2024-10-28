package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002@\u0012<\u0012:\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u001a\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00020\u0001JX\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2>\u0010\u000b\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u001a\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"io/ktor/server/plugins/callloging/MDCHookKt$MDCHook$1", "Lio/ktor/server/application/Hook;", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "(Lio/ktor/server/application/ApplicationCallPipeline;Lkotlin/jvm/functions/Function3;)V", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MDCHook.kt */
public final class MDCHookKt$MDCHook$1 implements Hook<Function3<? super ApplicationCall, ? super Function1<? super Continuation<? super Unit>, ? extends Object>, ? super Continuation<? super Unit>, ? extends Object>> {
    final /* synthetic */ PipelinePhase $phase;

    MDCHookKt$MDCHook$1(PipelinePhase pipelinePhase) {
        this.$phase = pipelinePhase;
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function3<? super ApplicationCall, ? super Function1<? super Continuation<? super Unit>, ? extends Object>, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function3, "handler");
        PipelinePhase pipelinePhase = new PipelinePhase(this.$phase.getName() + "MDC");
        applicationCallPipeline.insertPhaseBefore(this.$phase, pipelinePhase);
        applicationCallPipeline.intercept(pipelinePhase, new MDCHookKt$MDCHook$1$install$1(function3, (Continuation<? super MDCHookKt$MDCHook$1$install$1>) null));
    }
}
