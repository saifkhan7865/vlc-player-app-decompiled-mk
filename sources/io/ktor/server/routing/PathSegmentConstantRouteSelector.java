package io.ktor.server.routing;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0017\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, d2 = {"Lio/ktor/server/routing/PathSegmentConstantRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "value", "", "hasTrailingSlash", "", "(Ljava/lang/String;Z)V", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class PathSegmentConstantRouteSelector extends RouteSelector {
    private final String value;

    public static /* synthetic */ PathSegmentConstantRouteSelector copy$default(PathSegmentConstantRouteSelector pathSegmentConstantRouteSelector, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pathSegmentConstantRouteSelector.value;
        }
        return pathSegmentConstantRouteSelector.copy(str);
    }

    public final String component1() {
        return this.value;
    }

    public final PathSegmentConstantRouteSelector copy(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        return new PathSegmentConstantRouteSelector(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PathSegmentConstantRouteSelector) && Intrinsics.areEqual((Object) this.value, (Object) ((PathSegmentConstantRouteSelector) obj).value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public final String getValue() {
        return this.value;
    }

    public PathSegmentConstantRouteSelector(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.value = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "hasTrailingSlash is not used anymore. This is going to be removed", replaceWith = @ReplaceWith(expression = "PathSegmentConstantRouteSelector(value)", imports = {}))
    public PathSegmentConstantRouteSelector(String str, boolean z) {
        this(str);
        Intrinsics.checkNotNullParameter(str, "value");
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        if (i >= routingResolveContext.getSegments().size() || !Intrinsics.areEqual((Object) routingResolveContext.getSegments().get(i), (Object) this.value)) {
            return RouteSelectorEvaluation.Companion.getFailedPath();
        }
        return RouteSelectorEvaluation.Companion.getConstantPath();
    }

    public String toString() {
        return this.value;
    }
}
