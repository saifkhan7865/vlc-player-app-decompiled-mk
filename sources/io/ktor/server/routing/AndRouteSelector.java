package io.ktor.server.routing;

import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0001HÆ\u0003J\t\u0010\t\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/AndRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "first", "second", "(Lio/ktor/server/routing/RouteSelector;Lio/ktor/server/routing/RouteSelector;)V", "getFirst", "()Lio/ktor/server/routing/RouteSelector;", "getSecond", "component1", "component2", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class AndRouteSelector extends RouteSelector {
    private final RouteSelector first;
    private final RouteSelector second;

    public static /* synthetic */ AndRouteSelector copy$default(AndRouteSelector andRouteSelector, RouteSelector routeSelector, RouteSelector routeSelector2, int i, Object obj) {
        if ((i & 1) != 0) {
            routeSelector = andRouteSelector.first;
        }
        if ((i & 2) != 0) {
            routeSelector2 = andRouteSelector.second;
        }
        return andRouteSelector.copy(routeSelector, routeSelector2);
    }

    public final RouteSelector component1() {
        return this.first;
    }

    public final RouteSelector component2() {
        return this.second;
    }

    public final AndRouteSelector copy(RouteSelector routeSelector, RouteSelector routeSelector2) {
        Intrinsics.checkNotNullParameter(routeSelector, "first");
        Intrinsics.checkNotNullParameter(routeSelector2, "second");
        return new AndRouteSelector(routeSelector, routeSelector2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AndRouteSelector)) {
            return false;
        }
        AndRouteSelector andRouteSelector = (AndRouteSelector) obj;
        return Intrinsics.areEqual((Object) this.first, (Object) andRouteSelector.first) && Intrinsics.areEqual((Object) this.second, (Object) andRouteSelector.second);
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

    public AndRouteSelector(RouteSelector routeSelector, RouteSelector routeSelector2) {
        Intrinsics.checkNotNullParameter(routeSelector, "first");
        Intrinsics.checkNotNullParameter(routeSelector2, "second");
        this.first = routeSelector;
        this.second = routeSelector2;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        RouteSelectorEvaluation evaluate = this.first.evaluate(routingResolveContext, i);
        if (!(evaluate instanceof RouteSelectorEvaluation.Success)) {
            return evaluate;
        }
        RouteSelectorEvaluation.Success success = (RouteSelectorEvaluation.Success) evaluate;
        RouteSelectorEvaluation evaluate2 = this.second.evaluate(routingResolveContext, i + success.getSegmentIncrement());
        if (!(evaluate2 instanceof RouteSelectorEvaluation.Success)) {
            return evaluate2;
        }
        RouteSelectorEvaluation.Success success2 = (RouteSelectorEvaluation.Success) evaluate2;
        return new RouteSelectorEvaluation.Success(success.getQuality() * success2.getQuality(), ParametersKt.plus(success.getParameters(), success2.getParameters()), success.getSegmentIncrement() + success2.getSegmentIncrement());
    }

    public String toString() {
        return "{" + this.first + " & " + this.second + AbstractJsonLexerKt.END_OBJ;
    }
}
