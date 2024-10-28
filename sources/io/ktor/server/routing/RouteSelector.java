package io.ktor.server.routing;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lio/ktor/server/routing/RouteSelector;", "", "()V", "quality", "", "(D)V", "getQuality$annotations", "getQuality", "()D", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public abstract class RouteSelector {
    private final double quality;

    @Deprecated(message = "This property is not used anymore and will be removed")
    public static /* synthetic */ void getQuality$annotations() {
    }

    public abstract RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i);

    @Deprecated(message = "quality property is not used anymore and will be removed", replaceWith = @ReplaceWith(expression = "RouteSelector()", imports = {}))
    public RouteSelector(double d) {
        this.quality = d;
    }

    public final double getQuality() {
        return this.quality;
    }

    public RouteSelector() {
        this(0.0d);
    }
}
