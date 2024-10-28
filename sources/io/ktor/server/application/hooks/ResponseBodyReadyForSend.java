package io.ktor.server.application.hooks;

import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u000025\u00121\u0012/\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\t0\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\nJM\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r23\u0010\u000e\u001a/\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/server/application/hooks/ResponseBodyReadyForSend;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function4;", "Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/http/content/OutgoingContent;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "()V", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "(Lio/ktor/server/application/ApplicationCallPipeline;Lkotlin/jvm/functions/Function4;)V", "Context", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommonHooks.kt */
public final class ResponseBodyReadyForSend implements Hook<Function4<? super Context, ? super ApplicationCall, ? super OutgoingContent, ? super Continuation<? super Unit>, ? extends Object>> {
    public static final ResponseBodyReadyForSend INSTANCE = new ResponseBodyReadyForSend();

    private ResponseBodyReadyForSend() {
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;", "", "context", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/util/pipeline/PipelineContext;)V", "transformBodyTo", "", "body", "Lio/ktor/http/content/OutgoingContent;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CommonHooks.kt */
    public static final class Context {
        private final PipelineContext<Object, ApplicationCall> context;

        public Context(PipelineContext<Object, ApplicationCall> pipelineContext) {
            Intrinsics.checkNotNullParameter(pipelineContext, "context");
            this.context = pipelineContext;
        }

        public final void transformBodyTo(OutgoingContent outgoingContent) {
            Intrinsics.checkNotNullParameter(outgoingContent, "body");
            this.context.setSubject(outgoingContent);
        }
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function4<? super Context, ? super ApplicationCall, ? super OutgoingContent, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function4, "handler");
        applicationCallPipeline.getSendPipeline().intercept(ApplicationSendPipeline.Phases.getAfter(), new ResponseBodyReadyForSend$install$1(function4, (Continuation<? super ResponseBodyReadyForSend$install$1>) null));
    }
}
