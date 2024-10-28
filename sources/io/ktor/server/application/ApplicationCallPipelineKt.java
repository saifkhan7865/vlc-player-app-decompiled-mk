package io.ktor.server.application;

import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\"\u001f\u0010\u0000\u001a\u00020\u0001*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\" \u0010\u0006\u001a\u00020\u0003*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"application", "Lio/ktor/server/application/Application;", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "getApplication", "(Lio/ktor/util/pipeline/PipelineContext;)Lio/ktor/server/application/Application;", "call", "getCall", "(Lio/ktor/util/pipeline/PipelineContext;)Lio/ktor/server/application/ApplicationCall;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationCallPipeline.kt */
public final class ApplicationCallPipelineKt {
    public static final ApplicationCall getCall(PipelineContext<?, ApplicationCall> pipelineContext) {
        Intrinsics.checkNotNullParameter(pipelineContext, "<this>");
        return pipelineContext.getContext();
    }

    public static final Application getApplication(PipelineContext<?, ApplicationCall> pipelineContext) {
        Intrinsics.checkNotNullParameter(pipelineContext, "<this>");
        return pipelineContext.getContext().getApplication();
    }
}
