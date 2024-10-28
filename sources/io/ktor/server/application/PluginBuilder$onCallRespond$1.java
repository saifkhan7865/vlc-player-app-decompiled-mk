package io.ktor.server.application;

import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PluginBuilder.kt */
/* synthetic */ class PluginBuilder$onCallRespond$1 extends FunctionReferenceImpl implements Function2<PluginConfig, PipelineContext<Object, ApplicationCall>, OnCallRespondContext<PluginConfig>> {
    public static final PluginBuilder$onCallRespond$1 INSTANCE = new PluginBuilder$onCallRespond$1();

    PluginBuilder$onCallRespond$1() {
        super(2, OnCallRespondContext.class, "<init>", "<init>(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", 0);
    }

    public final OnCallRespondContext<PluginConfig> invoke(PluginConfig pluginconfig, PipelineContext<Object, ApplicationCall> pipelineContext) {
        Intrinsics.checkNotNullParameter(pluginconfig, "p0");
        Intrinsics.checkNotNullParameter(pipelineContext, "p1");
        return new OnCallRespondContext<>(pluginconfig, pipelineContext);
    }
}
