package io.ktor.server.routing;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0003*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"getAllRoutes", "", "Lio/ktor/server/routing/Route;", "", "endpoints", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Route.kt */
public final class RouteKt {
    public static final List<Route> getAllRoutes(Route route) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        List<Route> arrayList = new ArrayList<>();
        getAllRoutes(route, arrayList);
        return arrayList;
    }

    private static final void getAllRoutes(Route route, List<Route> list) {
        if (!route.getHandlers$ktor_server_core().isEmpty()) {
            list.add(route);
        }
        for (Route allRoutes : route.getChildren()) {
            getAllRoutes(allRoutes, list);
        }
    }
}
