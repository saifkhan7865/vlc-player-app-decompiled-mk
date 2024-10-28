package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lio/ktor/server/routing/ParameterRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class ParameterRouteSelector extends RouteSelector {
    private final String name;

    public static /* synthetic */ ParameterRouteSelector copy$default(ParameterRouteSelector parameterRouteSelector, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = parameterRouteSelector.name;
        }
        return parameterRouteSelector.copy(str);
    }

    public final String component1() {
        return this.name;
    }

    public final ParameterRouteSelector copy(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return new ParameterRouteSelector(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ParameterRouteSelector) && Intrinsics.areEqual((Object) this.name, (Object) ((ParameterRouteSelector) obj).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public final String getName() {
        return this.name;
    }

    public ParameterRouteSelector(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        this.name = str;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        List<String> all = routingResolveContext.getCall().getParameters().getAll(this.name);
        if (all != null) {
            return new RouteSelectorEvaluation.Success(1.0d, ParametersKt.parametersOf(this.name, all), 0, 4, (DefaultConstructorMarker) null);
        }
        return RouteSelectorEvaluation.Companion.getFailedParameter();
    }

    public String toString() {
        return "[" + this.name + AbstractJsonLexerKt.END_LIST;
    }
}
