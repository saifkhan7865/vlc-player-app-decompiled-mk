package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0001HÆ\u0003J\t\u0010\t\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/OrRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "first", "second", "(Lio/ktor/server/routing/RouteSelector;Lio/ktor/server/routing/RouteSelector;)V", "getFirst", "()Lio/ktor/server/routing/RouteSelector;", "getSecond", "component1", "component2", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class OrRouteSelector extends RouteSelector {
    private final RouteSelector first;
    private final RouteSelector second;

    public static /* synthetic */ OrRouteSelector copy$default(OrRouteSelector orRouteSelector, RouteSelector routeSelector, RouteSelector routeSelector2, int i, Object obj) {
        if ((i & 1) != 0) {
            routeSelector = orRouteSelector.first;
        }
        if ((i & 2) != 0) {
            routeSelector2 = orRouteSelector.second;
        }
        return orRouteSelector.copy(routeSelector, routeSelector2);
    }

    public final RouteSelector component1() {
        return this.first;
    }

    public final RouteSelector component2() {
        return this.second;
    }

    public final OrRouteSelector copy(RouteSelector routeSelector, RouteSelector routeSelector2) {
        Intrinsics.checkNotNullParameter(routeSelector, "first");
        Intrinsics.checkNotNullParameter(routeSelector2, "second");
        return new OrRouteSelector(routeSelector, routeSelector2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrRouteSelector)) {
            return false;
        }
        OrRouteSelector orRouteSelector = (OrRouteSelector) obj;
        return Intrinsics.areEqual((Object) this.first, (Object) orRouteSelector.first) && Intrinsics.areEqual((Object) this.second, (Object) orRouteSelector.second);
    }

    public int hashCode() {
        return (this.first.hashCode() * 31) + this.second.hashCode();
    }

    public final RouteSelector getFirst() {
        return this.first;
    }

    public final RouteSelector getSecond() {
        return this.second;
    }

    public OrRouteSelector(RouteSelector routeSelector, RouteSelector routeSelector2) {
        Intrinsics.checkNotNullParameter(routeSelector, "first");
        Intrinsics.checkNotNullParameter(routeSelector2, "second");
        this.first = routeSelector;
        this.second = routeSelector2;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        RouteSelectorEvaluation evaluate = this.first.evaluate(routingResolveContext, i);
        if (evaluate.getSucceeded()) {
            return evaluate;
        }
        return this.second.evaluate(routingResolveContext, i);
    }

    public String toString() {
        return "{" + this.first + " | " + this.second + AbstractJsonLexerKt.END_OBJ;
    }
}
