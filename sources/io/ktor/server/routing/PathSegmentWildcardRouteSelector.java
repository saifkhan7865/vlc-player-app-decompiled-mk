package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lio/ktor/server/routing/PathSegmentWildcardRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "()V", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class PathSegmentWildcardRouteSelector extends RouteSelector {
    public static final PathSegmentWildcardRouteSelector INSTANCE = new PathSegmentWildcardRouteSelector();

    private PathSegmentWildcardRouteSelector() {
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        if (i >= routingResolveContext.getSegments().size() || routingResolveContext.getSegments().get(i).length() <= 0) {
            return RouteSelectorEvaluation.Companion.getFailedPath();
        }
        return RouteSelectorEvaluation.Companion.getWildcardPath();
    }

    public String toString() {
        return "*";
    }
}