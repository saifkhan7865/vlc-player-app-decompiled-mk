package io.ktor.server.application;

import io.ktor.util.AttributeKey;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"io/ktor/server/application/CreatePluginUtilsKt$createPluginInstance$pluginBuilder$1", "Lio/ktor/server/application/PluginBuilder;", "application", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "getPipeline$ktor_server_core", "()Lio/ktor/server/application/ApplicationCallPipeline;", "pluginConfig", "getPluginConfig", "()Ljava/lang/Object;", "Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePluginUtils.kt */
public final class CreatePluginUtilsKt$createPluginInstance$pluginBuilder$1 extends PluginBuilder<PluginConfigT> {
    private final Application application;
    private final ApplicationCallPipeline pipeline;
    private final PluginConfigT pluginConfig;

    CreatePluginUtilsKt$createPluginInstance$pluginBuilder$1(Application application2, ApplicationCallPipeline applicationCallPipeline, PluginConfigT pluginconfigt, AttributeKey<PluginInstance> attributeKey) {
        super(attributeKey);
        this.application = application2;
        this.pipeline = applicationCallPipeline;
        this.pluginConfig = pluginconfigt;
    }

    public Application getApplication() {
        return this.application;
    }

    public ApplicationCallPipeline getPipeline$ktor_server_core() {
        return this.pipeline;
    }

    public PluginConfigT getPluginConfig() {
        return this.pluginConfig;
    }
}
