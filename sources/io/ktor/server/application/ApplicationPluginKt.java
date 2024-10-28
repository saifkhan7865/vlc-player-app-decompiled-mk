package io.ktor.server.application;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.Routing;
import io.ktor.server.routing.RoutingKt;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import java.io.Closeable;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u001ak\u0010\u000b\u001a\u00020\f\"\b\b\u0000\u0010\r*\u00020\u000e\"\b\b\u0001\u0010\u000f*\u00020\u000e\"\u0004\b\u0002\u0010\u0010\"\u0004\b\u0003\u0010\u0011\"\u0014\b\u0004\u0010\u0012*\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00110\u0007*\u0002H\u00122\u0006\u0010\u0013\u001a\u0002H\u00122\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000f0\u00152\u0006\u0010\u0016\u001a\u0002H\u000fH\u0002¢\u0006\u0002\u0010\u0017\u001ad\u0010\u0018\u001a\u0002H\u000f\"\b\b\u0000\u0010\u0012*\u00020\u0019\"\b\b\u0001\u0010\r*\u00020\u000e\"\b\b\u0002\u0010\u000f*\u00020\u000e*\u0002H\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000f0\u001a2\u0019\b\u0002\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\f0\u001c¢\u0006\u0002\b\u001dH\u0007¢\u0006\u0002\u0010\u001e\u001al\u0010\u0018\u001a\u0002H\u000f\"\u0012\b\u0000\u0010\u0012*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\b0\u0007\"\b\b\u0001\u0010\r*\u00020\u000e\"\b\b\u0002\u0010\u000f*\u00020\u000e*\u0002H\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000f0\u001f2\u0019\b\u0002\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\f0\u001c¢\u0006\u0002\b\u001d¢\u0006\u0002\u0010 \u001aT\u0010!\u001a\u0002H\u000f\"\b\b\u0000\u0010\r*\u00020\u000e\"\b\b\u0001\u0010\u000f*\u00020\u000e*\u00020\u00192\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000f0\u00152\u0019\b\u0002\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\f0\u001c¢\u0006\u0002\b\u001dH\u0002¢\u0006\u0002\u0010\"\u001aC\u0010\u0014\u001a\u0002H\u000f\"\u0012\b\u0000\u0010\u0006*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\b0\u0007\"\b\b\u0001\u0010\u000f*\u00020\u000e*\u0002H\u00062\u0014\u0010\u0014\u001a\u0010\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000f0\u001f¢\u0006\u0002\u0010#\u001aE\u0010$\u001a\u0004\u0018\u0001H\u000f\"\u0012\b\u0000\u0010\u0006*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\b0\u0007\"\b\b\u0001\u0010\u000f*\u00020\u000e*\u0002H\u00062\u0014\u0010\u0014\u001a\u0010\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000f0\u001f¢\u0006\u0002\u0010#\u001aS\u0010%\u001a\u00020\f\"\u0012\b\u0000\u0010\u0006*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\b0\u0007\"\b\b\u0001\u0010\r*\u00020\u000e\"\b\b\u0002\u0010\u000f*\u00020\u000e*\u0002H\u00062\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000f0\u001fH\u0007¢\u0006\u0002\u0010&\u001a%\u0010'\u001a\u00020\f\"\u0012\b\u0000\u0010\u0006*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\b0\u0007*\u0002H\u0006H\u0007¢\u0006\u0002\u0010(\u001a=\u0010)\u001a\u00020\f\"\u0012\b\u0000\u0010\u0006*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\b0\u0007\"\b\b\u0001\u0010\u000f*\u00020\u000e*\u0002H\u00062\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001H\u0007¢\u0006\u0002\u0010+\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\")\u0010\u0005\u001a\u00020\u0002\"\u0012\b\u0000\u0010\u0006*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\b0\u0007*\u0002H\u00068F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006,"}, d2 = {"pluginRegistryKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/util/Attributes;", "getPluginRegistryKey", "()Lio/ktor/util/AttributeKey;", "pluginRegistry", "A", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "getPluginRegistry", "(Lio/ktor/util/pipeline/Pipeline;)Lio/ktor/util/Attributes;", "addAllInterceptors", "", "B", "", "F", "TSubject", "TContext", "P", "fakePipeline", "plugin", "Lio/ktor/server/application/BaseRouteScopedPlugin;", "pluginInstance", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/BaseRouteScopedPlugin;Ljava/lang/Object;)V", "install", "Lio/ktor/server/routing/Route;", "Lio/ktor/server/application/BaseApplicationPlugin;", "configure", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/routing/Route;Lio/ktor/server/application/BaseApplicationPlugin;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lio/ktor/server/application/Plugin;", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/Plugin;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "installIntoRoute", "(Lio/ktor/server/routing/Route;Lio/ktor/server/application/BaseRouteScopedPlugin;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/Plugin;)Ljava/lang/Object;", "pluginOrNull", "uninstall", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/Plugin;)V", "uninstallAllPlugins", "(Lio/ktor/util/pipeline/Pipeline;)V", "uninstallPlugin", "key", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/AttributeKey;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationPlugin.kt */
public final class ApplicationPluginKt {
    private static final AttributeKey<Attributes> pluginRegistryKey = new AttributeKey<>("ApplicationPluginRegistry");

    public static final AttributeKey<Attributes> getPluginRegistryKey() {
        return pluginRegistryKey;
    }

    public static final <A extends Pipeline<?, ApplicationCall>> Attributes getPluginRegistry(A a) {
        Intrinsics.checkNotNullParameter(a, "<this>");
        return (Attributes) a.getAttributes().computeIfAbsent(pluginRegistryKey, ApplicationPluginKt$pluginRegistry$1.INSTANCE);
    }

    public static final <A extends Pipeline<?, ApplicationCall>, F> F plugin(A a, Plugin<?, ?, F> plugin) {
        F f;
        Intrinsics.checkNotNullParameter(a, "<this>");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        if (a instanceof Route) {
            f = RouteScopedPluginKt.findPluginInRoute((Route) a, plugin);
        } else {
            f = pluginOrNull(a, plugin);
        }
        if (f != null) {
            return f;
        }
        throw new MissingApplicationPluginException(plugin.getKey());
    }

    public static final <A extends Pipeline<?, ApplicationCall>, F> F pluginOrNull(A a, Plugin<?, ?, F> plugin) {
        Intrinsics.checkNotNullParameter(a, "<this>");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        return getPluginRegistry(a).getOrNull(plugin.getKey());
    }

    public static /* synthetic */ Object install$default(Pipeline pipeline, Plugin plugin, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = ApplicationPluginKt$install$1.INSTANCE;
        }
        return install(pipeline, plugin, function1);
    }

    public static final <P extends Pipeline<?, ApplicationCall>, B, F> F install(P p, Plugin<? super P, ? extends B, F> plugin, Function1<? super B, Unit> function1) {
        Intrinsics.checkNotNullParameter(p, "<this>");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        Intrinsics.checkNotNullParameter(function1, "configure");
        if ((p instanceof Route) && (plugin instanceof BaseRouteScopedPlugin)) {
            return installIntoRoute((Route) p, (BaseRouteScopedPlugin) plugin, function1);
        }
        Attributes pluginRegistry = getPluginRegistry(p);
        F orNull = pluginRegistry.getOrNull(plugin.getKey());
        if (orNull == null) {
            F install = plugin.install(p, function1);
            pluginRegistry.put(plugin.getKey(), install);
            return install;
        } else if (Intrinsics.areEqual((Object) orNull, (Object) plugin)) {
            return orNull;
        } else {
            throw new DuplicatePluginException("Please make sure that you use unique name for the plugin and don't install it twice. Conflicting application plugin is already installed with the same key as `" + plugin.getKey().getName() + '`');
        }
    }

    static /* synthetic */ Object installIntoRoute$default(Route route, BaseRouteScopedPlugin baseRouteScopedPlugin, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = ApplicationPluginKt$installIntoRoute$1.INSTANCE;
        }
        return installIntoRoute(route, baseRouteScopedPlugin, function1);
    }

    private static final <B, F> F installIntoRoute(Route route, BaseRouteScopedPlugin<B, F> baseRouteScopedPlugin, Function1<? super B, Unit> function1) {
        Route route2;
        Pipeline pipeline = route;
        if (getPluginRegistry(pipeline).getOrNull(baseRouteScopedPlugin.getKey()) != null) {
            throw new DuplicatePluginException("Please make sure that you use unique name for the plugin and don't install it twice. Plugin `" + baseRouteScopedPlugin.getKey().getName() + "` is already installed to the pipeline " + route);
        } else if (getPluginRegistry(RoutingKt.getApplication(route)).getOrNull(baseRouteScopedPlugin.getKey()) == null) {
            if (route instanceof Routing) {
                route2 = new Routing(((Routing) route).getApplication());
            } else {
                route2 = new Route(route.getParent(), route.getSelector(), route.getDevelopmentMode(), route.getEnvironment());
            }
            Pipeline pipeline2 = route2;
            F install = baseRouteScopedPlugin.install(pipeline2, function1);
            getPluginRegistry(pipeline).put(baseRouteScopedPlugin.getKey(), install);
            route.mergePhases(pipeline2);
            route.getReceivePipeline().mergePhases(route2.getReceivePipeline());
            route.getSendPipeline().mergePhases(route2.getSendPipeline());
            addAllInterceptors(pipeline, pipeline2, baseRouteScopedPlugin, install);
            addAllInterceptors(route.getReceivePipeline(), route2.getReceivePipeline(), baseRouteScopedPlugin, install);
            addAllInterceptors(route.getSendPipeline(), route2.getSendPipeline(), baseRouteScopedPlugin, install);
            return install;
        } else {
            throw new DuplicatePluginException("Installing RouteScopedPlugin to application and route is not supported. Consider moving application level install to routing root.");
        }
    }

    private static final <B, F, TSubject, TContext, P extends Pipeline<TSubject, TContext>> void addAllInterceptors(P p, P p2, BaseRouteScopedPlugin<B, F> baseRouteScopedPlugin, F f) {
        for (PipelinePhase pipelinePhase : p.getItems()) {
            for (Function3 applicationPluginKt$addAllInterceptors$1$1$1 : p2.interceptorsForPhase(pipelinePhase)) {
                p.intercept(pipelinePhase, new ApplicationPluginKt$addAllInterceptors$1$1$1(baseRouteScopedPlugin, f, applicationPluginKt$addAllInterceptors$1$1$1, (Continuation<? super ApplicationPluginKt$addAllInterceptors$1$1$1>) null));
            }
        }
    }

    public static /* synthetic */ Object install$default(Route route, BaseApplicationPlugin baseApplicationPlugin, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = ApplicationPluginKt$install$2.INSTANCE;
        }
        return install(route, baseApplicationPlugin, function1);
    }

    @Deprecated(message = "Installing ApplicationPlugin into routing may lead to unexpected behaviour. Consider moving installation to the application level or migrate this plugin to `RouteScopedPlugin` to support installing into route.")
    public static final <P extends Route, B, F> F install(P p, BaseApplicationPlugin<? super P, ? extends B, F> baseApplicationPlugin, Function1<? super B, Unit> function1) {
        Intrinsics.checkNotNullParameter(p, "<this>");
        Intrinsics.checkNotNullParameter(baseApplicationPlugin, "plugin");
        Intrinsics.checkNotNullParameter(function1, "configure");
        return install((Pipeline) p, baseApplicationPlugin, function1);
    }

    @Deprecated(message = "This method is misleading and will be removed. If you have use case that requires this functionaity, please add it in KTOR-2696")
    public static final <A extends Pipeline<?, ApplicationCall>> void uninstallAllPlugins(A a) {
        Intrinsics.checkNotNullParameter(a, "<this>");
        for (AttributeKey attributeKey : getPluginRegistry(a).getAllKeys()) {
            Intrinsics.checkNotNull(attributeKey, "null cannot be cast to non-null type io.ktor.util.AttributeKey<kotlin.Any>");
            uninstallPlugin(a, attributeKey);
        }
    }

    @Deprecated(message = "This method is misleading and will be removed. If you have use case that requires this functionaity, please add it in KTOR-2696")
    public static final <A extends Pipeline<?, ApplicationCall>, B, F> void uninstall(A a, Plugin<? super A, ? extends B, F> plugin) {
        Intrinsics.checkNotNullParameter(a, "<this>");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        uninstallPlugin(a, plugin.getKey());
    }

    @Deprecated(message = "This method is misleading and will be removed. If you have use case that requires this functionaity, please add it in KTOR-2696")
    public static final <A extends Pipeline<?, ApplicationCall>, F> void uninstallPlugin(A a, AttributeKey<F> attributeKey) {
        F orNull;
        Intrinsics.checkNotNullParameter(a, "<this>");
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        Attributes attributes = (Attributes) a.getAttributes().getOrNull(pluginRegistryKey);
        if (attributes != null && (orNull = attributes.getOrNull(attributeKey)) != null) {
            if (orNull instanceof Closeable) {
                ((Closeable) orNull).close();
            }
            attributes.remove(attributeKey);
        }
    }
}
