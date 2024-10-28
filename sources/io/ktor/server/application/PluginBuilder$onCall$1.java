package io.ktor.server.application;

import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PluginBuilder.kt */
/* synthetic */ class PluginBuilder$onCall$1 extends FunctionReferenceImpl implements Function2<PluginConfig, PipelineContext<Unit, ApplicationCall>, OnCallContext<PluginConfig>> {
    public static final PluginBuilder$onCall$1 INSTANCE = new PluginBuilder$onCall$1();

    PluginBuilder$onCall$1() {
        super(2, OnCallContext.class, "<init>", "<init>(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", 0);
    }

    public final OnCallContext<PluginConfig> invoke(PluginConfig pluginconfig, PipelineContext<Unit, ApplicationCall> pipelineContext) {
        Intrinsics.checkNotNullParameter(pluginconfig, "p0");
        Intrinsics.checkNotNullParameter(pipelineContext, "p1");
        return new OnCallContext<>(pluginconfig, pipelineContext);
    }
}
