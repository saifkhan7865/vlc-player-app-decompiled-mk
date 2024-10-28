package io.ktor.server.application;

import io.ktor.server.routing.Route;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"io/ktor/server/application/CreatePluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1", "Lio/ktor/server/application/RouteScopedPluginBuilder;", "application", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "getPipeline$ktor_server_core", "()Lio/ktor/server/application/ApplicationCallPipeline;", "pluginConfig", "getPluginConfig", "()Ljava/lang/Object;", "Ljava/lang/Object;", "route", "Lio/ktor/server/routing/Route;", "getRoute", "()Lio/ktor/server/routing/Route;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePluginUtils.kt */
public final class CreatePluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1 extends RouteScopedPluginBuilder<PluginConfigT> {
    private final Application application;
    private final ApplicationCallPipeline pipeline;
    private final PluginConfigT pluginConfig;
    private final Route route;

    CreatePluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1(Application application2, ApplicationCallPipeline applicationCallPipeline, PluginConfigT pluginconfigt, AttributeKey<PluginInstance> attributeKey) {
        super(attributeKey);
        this.application = application2;
        this.pipeline = applicationCallPipeline;
        this.pluginConfig = pluginconfigt;
        this.route = applicationCallPipeline instanceof Route ? (Route) applicationCallPipeline : null;
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

    public Route getRoute() {
        return this.route;
    }
}
