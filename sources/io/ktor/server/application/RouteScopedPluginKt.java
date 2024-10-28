package io.ktor.server.application;

import io.ktor.server.routing.Route;
import io.ktor.server.routing.Routing;
import io.ktor.server.routing.RoutingKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"findPluginInRoute", "F", "", "Lio/ktor/server/routing/Route;", "plugin", "Lio/ktor/server/application/Plugin;", "(Lio/ktor/server/routing/Route;Lio/ktor/server/application/Plugin;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteScopedPlugin.kt */
public final class RouteScopedPluginKt {
    public static final <F> F findPluginInRoute(Route route, Plugin<?, ?, F> plugin) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        Route route2 = route;
        while (true) {
            F pluginOrNull = ApplicationPluginKt.pluginOrNull(route2, plugin);
            if (pluginOrNull != null) {
                return pluginOrNull;
            }
            if (route2.getParent() != null) {
                route2 = route2.getParent();
                Intrinsics.checkNotNull(route2);
            } else if (route2 instanceof Routing) {
                return ApplicationPluginKt.pluginOrNull(RoutingKt.getApplication(route), plugin);
            } else {
                return null;
            }
        }
    }
}
