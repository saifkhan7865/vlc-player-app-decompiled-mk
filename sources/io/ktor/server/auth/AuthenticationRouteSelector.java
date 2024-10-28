package io.ktor.server.auth;

import io.ktor.server.routing.RouteSelector;
import io.ktor.server.routing.RouteSelectorEvaluation;
import io.ktor.server.routing.RoutingResolveContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lio/ktor/server/auth/AuthenticationRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "names", "", "", "(Ljava/util/List;)V", "getNames", "()Ljava/util/List;", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "toString", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
public final class AuthenticationRouteSelector extends RouteSelector {
    private final List<String> names;

    public AuthenticationRouteSelector(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "names");
        this.names = list;
    }

    public final List<String> getNames() {
        return this.names;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        return RouteSelectorEvaluation.Companion.getTransparent();
    }

    public String toString() {
        return "(authenticate " + CollectionsKt.joinToString$default(this.names, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, AuthenticationRouteSelector$toString$1.INSTANCE, 31, (Object) null) + ')';
    }
}
