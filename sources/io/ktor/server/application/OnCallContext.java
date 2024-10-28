package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/server/application/OnCallContext;", "PluginConfig", "", "Lio/ktor/server/application/CallContext;", "pluginConfig", "context", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: KtorCallContexts.kt */
public final class OnCallContext<PluginConfig> extends CallContext<PluginConfig> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnCallContext(PluginConfig pluginconfig, PipelineContext<Unit, ApplicationCall> pipelineContext) {
        super(pluginconfig, pipelineContext);
        Intrinsics.checkNotNullParameter(pluginconfig, "pluginConfig");
        Intrinsics.checkNotNullParameter(pipelineContext, "context");
    }
}
