package io.ktor.server.application;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.MapApplicationConfig;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.RoutingKt;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J)\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"io/ktor/server/application/CreatePluginUtilsKt$createRouteScopedPlugin$2", "Lio/ktor/server/application/RouteScopedPlugin;", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/application/PluginInstance;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePluginUtils.kt */
public final class CreatePluginUtilsKt$createRouteScopedPlugin$2 implements RouteScopedPlugin<PluginConfigT> {
    final /* synthetic */ Function1<RouteScopedPluginBuilder<PluginConfigT>, Unit> $body;
    final /* synthetic */ String $configurationPath;
    final /* synthetic */ Function1<ApplicationConfig, PluginConfigT> $createConfiguration;
    private final AttributeKey<PluginInstance> key;

    CreatePluginUtilsKt$createRouteScopedPlugin$2(String str, String str2, Function1<? super RouteScopedPluginBuilder<PluginConfigT>, Unit> function1, Function1<? super ApplicationConfig, ? extends PluginConfigT> function12) {
        this.$configurationPath = str2;
        this.$body = function1;
        this.$createConfiguration = function12;
        this.key = new AttributeKey<>(str);
    }

    public AttributeKey<PluginInstance> getKey() {
        return this.key;
    }

    public PluginInstance install(ApplicationCallPipeline applicationCallPipeline, Function1<? super PluginConfigT, Unit> function1) {
        ApplicationConfig applicationConfig;
        Application application;
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function1, "configure");
        ApplicationEnvironment environment = applicationCallPipeline.getEnvironment();
        if (environment != null) {
            try {
                applicationConfig = environment.getConfig().config(this.$configurationPath);
            } catch (Throwable unused) {
                applicationConfig = new MapApplicationConfig();
            }
            if (applicationCallPipeline instanceof Route) {
                application = RoutingKt.getApplication((Route) applicationCallPipeline);
            } else if (applicationCallPipeline instanceof Application) {
                application = (Application) applicationCallPipeline;
            } else {
                throw new IllegalStateException(("Unsupported pipeline type: " + Reflection.getOrCreateKotlinClass(applicationCallPipeline.getClass())).toString());
            }
            return CreatePluginUtilsKt.createRouteScopedPluginInstance(this, application, applicationCallPipeline, this.$body, new CreatePluginUtilsKt$createRouteScopedPlugin$2$install$1(this.$createConfiguration, applicationConfig), function1);
        }
        throw new IllegalStateException("Can't install plugin with config: environment is not initialized.".toString());
    }
}
