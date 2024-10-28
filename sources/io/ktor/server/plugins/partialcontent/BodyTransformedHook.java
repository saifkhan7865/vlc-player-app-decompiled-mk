package io.ktor.server.plugins.partialcontent;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002S\u0012O\u0012M\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\f0\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\rJk\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102Q\u0010\u0011\u001aM\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\fH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/server/plugins/partialcontent/BodyTransformedHook;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function4;", "Lio/ktor/server/plugins/partialcontent/BodyTransformedHook$Context;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "name", "call", "", "message", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "()V", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "(Lio/ktor/server/application/ApplicationCallPipeline;Lkotlin/jvm/functions/Function4;)V", "Context", "ktor-server-partial-content"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BodyTransformedHook.kt */
public final class BodyTransformedHook implements Hook<Function4<? super Context, ? super ApplicationCall, ? super Object, ? super Continuation<? super Unit>, ? extends Object>> {
    public static final BodyTransformedHook INSTANCE = new BodyTransformedHook();

    private BodyTransformedHook() {
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lio/ktor/server/plugins/partialcontent/BodyTransformedHook$Context;", "", "context", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/util/pipeline/PipelineContext;)V", "call", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "transformBodyTo", "", "newValue", "ktor-server-partial-content"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BodyTransformedHook.kt */
    public static final class Context {
        private final ApplicationCall call;
        private final PipelineContext<Object, ApplicationCall> context;

        public Context(PipelineContext<Object, ApplicationCall> pipelineContext) {
            Intrinsics.checkNotNullParameter(pipelineContext, "context");
            this.context = pipelineContext;
            this.call = pipelineContext.getContext();
        }

        public final ApplicationCall getCall() {
            return this.call;
        }

        public final void transformBodyTo(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "newValue");
            this.context.setSubject(obj);
        }
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function4<? super Context, ? super ApplicationCall, Object, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function4, "handler");
        PipelinePhase pipelinePhase = new PipelinePhase("PartialContent");
        applicationCallPipeline.getSendPipeline().insertPhaseAfter(ApplicationSendPipeline.Phases.getContentEncoding(), pipelinePhase);
        applicationCallPipeline.getSendPipeline().intercept(pipelinePhase, new BodyTransformedHook$install$1(function4, (Continuation<? super BodyTransformedHook$install$1>) null));
    }
}
