package io.ktor.server.routing;

import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lio/ktor/server/routing/LocalPortRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "port", "", "(I)V", "getPort", "()I", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "hashCode", "toString", "", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LocalPortRoutingBuilder.kt */
public final class LocalPortRouteSelector extends RouteSelector {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String LocalPortParameter = "$LocalPort";
    private final int port;

    public static /* synthetic */ LocalPortRouteSelector copy$default(LocalPortRouteSelector localPortRouteSelector, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = localPortRouteSelector.port;
        }
        return localPortRouteSelector.copy(i);
    }

    public final int component1() {
        return this.port;
    }

    public final LocalPortRouteSelector copy(int i) {
        return new LocalPortRouteSelector(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LocalPortRouteSelector) && this.port == ((LocalPortRouteSelector) obj).port;
    }

    public int hashCode() {
        return this.port;
    }

    public String toString() {
        return "LocalPortRouteSelector(port=" + this.port + ')';
    }

    public LocalPortRouteSelector(int i) {
        this.port = i;
    }

    public final int getPort() {
        return this.port;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        int localPort = routingResolveContext.getCall().getRequest().getLocal().getLocalPort();
        int i2 = this.port;
        if (localPort == i2) {
            return new RouteSelectorEvaluation.Success(1.0d, ParametersKt.parametersOf(LocalPortParameter, String.valueOf(i2)), 0, 4, (DefaultConstructorMarker) null);
        }
        return RouteSelectorEvaluation.Companion.getFailed();
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ktor/server/routing/LocalPortRouteSelector$Companion;", "", "()V", "LocalPortParameter", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LocalPortRoutingBuilder.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
