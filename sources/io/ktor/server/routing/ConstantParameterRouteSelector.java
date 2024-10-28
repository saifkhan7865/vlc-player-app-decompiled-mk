package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/ConstantParameterRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "name", "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getValue", "component1", "component2", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class ConstantParameterRouteSelector extends RouteSelector {
    private final String name;
    private final String value;

    public static /* synthetic */ ConstantParameterRouteSelector copy$default(ConstantParameterRouteSelector constantParameterRouteSelector, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = constantParameterRouteSelector.name;
        }
        if ((i & 2) != 0) {
            str2 = constantParameterRouteSelector.value;
        }
        return constantParameterRouteSelector.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.value;
    }

    public final ConstantParameterRouteSelector copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        return new ConstantParameterRouteSelector(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConstantParameterRouteSelector)) {
            return false;
        }
        ConstantParameterRouteSelector constantParameterRouteSelector = (ConstantParameterRouteSelector) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) constantParameterRouteSelector.name) && Intrinsics.areEqual((Object) this.value, (Object) constantParameterRouteSelector.value);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.value.hashCode();
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    public ConstantParameterRouteSelector(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        this.name = str;
        this.value = str2;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        if (routingResolveContext.getCall().getParameters().contains(this.name, this.value)) {
            return RouteSelectorEvaluation.Companion.getConstant();
        }
        return RouteSelectorEvaluation.Companion.getFailedParameter();
    }

    public String toString() {
        return "[" + this.name + " = " + this.value + AbstractJsonLexerKt.END_LIST;
    }
}
