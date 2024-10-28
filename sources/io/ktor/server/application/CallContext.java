package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0010\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\r\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fR\u001e\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lio/ktor/server/application/CallContext;", "PluginConfig", "", "pluginConfig", "context", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", "getContext", "()Lio/ktor/util/pipeline/PipelineContext;", "getPluginConfig", "()Ljava/lang/Object;", "Ljava/lang/Object;", "finish", "", "finish$ktor_server_core", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: KtorCallContexts.kt */
public class CallContext<PluginConfig> {
    private final PipelineContext<?, ApplicationCall> context;
    private final PluginConfig pluginConfig;

    public CallContext(PluginConfig pluginconfig, PipelineContext<?, ApplicationCall> pipelineContext) {
        Intrinsics.checkNotNullParameter(pluginconfig, "pluginConfig");
        Intrinsics.checkNotNullParameter(pipelineContext, "context");
        this.pluginConfig = pluginconfig;
        this.context = pipelineContext;
    }

    public final PluginConfig getPluginConfig() {
        return this.pluginConfig;
    }

    /* access modifiers changed from: protected */
    public PipelineContext<?, ApplicationCall> getContext() {
        return this.context;
    }

    public final void finish$ktor_server_core() {
        getContext().finish();
    }
}
