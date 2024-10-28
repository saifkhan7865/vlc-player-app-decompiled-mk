package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B/\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\t\u0010\u001b\u001a\u00020\u001aHÖ\u0001J\b\u0010\u001c\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u001d"}, d2 = {"Lio/ktor/server/routing/PathSegmentOptionalParameterRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "name", "", "prefix", "suffix", "hasTrailingSlash", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPrefix", "getSuffix", "component1", "component2", "component3", "copy", "equals", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class PathSegmentOptionalParameterRouteSelector extends RouteSelector {
    private final String name;
    private final String prefix;
    private final String suffix;

    public static /* synthetic */ PathSegmentOptionalParameterRouteSelector copy$default(PathSegmentOptionalParameterRouteSelector pathSegmentOptionalParameterRouteSelector, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pathSegmentOptionalParameterRouteSelector.name;
        }
        if ((i & 2) != 0) {
            str2 = pathSegmentOptionalParameterRouteSelector.prefix;
        }
        if ((i & 4) != 0) {
            str3 = pathSegmentOptionalParameterRouteSelector.suffix;
        }
        return pathSegmentOptionalParameterRouteSelector.copy(str, str2, str3);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.prefix;
    }

    public final String component3() {
        return this.suffix;
    }

    public final PathSegmentOptionalParameterRouteSelector copy(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return new PathSegmentOptionalParameterRouteSelector(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegmentOptionalParameterRouteSelector)) {
            return false;
        }
        PathSegmentOptionalParameterRouteSelector pathSegmentOptionalParameterRouteSelector = (PathSegmentOptionalParameterRouteSelector) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) pathSegmentOptionalParameterRouteSelector.name) && Intrinsics.areEqual((Object) this.prefix, (Object) pathSegmentOptionalParameterRouteSelector.prefix) && Intrinsics.areEqual((Object) this.suffix, (Object) pathSegmentOptionalParameterRouteSelector.suffix);
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        String str = this.prefix;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.suffix;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PathSegmentOptionalParameterRouteSelector(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public final String getSuffix() {
        return this.suffix;
    }

    public PathSegmentOptionalParameterRouteSelector(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        this.name = str;
        this.prefix = str2;
        this.suffix = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PathSegmentOptionalParameterRouteSelector(String str, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, z);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "hasTrailingSlash is not used anymore. This is going to be removed", replaceWith = @ReplaceWith(expression = "PathSegmentOptionalParameterRouteSelector(value, prefix, suffix)", imports = {}))
    public PathSegmentOptionalParameterRouteSelector(String str, String str2, String str3, boolean z) {
        this(str, str2, str3);
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        return RouteSelectorKt.evaluatePathSegmentParameter(routingResolveContext.getSegments(), i, this.name, this.prefix, this.suffix, true);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.prefix;
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        sb.append(str);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        sb.append(this.name);
        sb.append("?}");
        String str3 = this.suffix;
        if (str3 != null) {
            str2 = str3;
        }
        sb.append(str2);
        return sb.toString();
    }
}
