package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Parameters;
import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B#\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\t\u0010\u0018\u001a\u00020\u0017HÖ\u0001J\b\u0010\u0019\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u001a"}, d2 = {"Lio/ktor/server/routing/PathSegmentTailcardRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "name", "", "prefix", "hasTrailingSlash", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPrefix", "component1", "component2", "copy", "equals", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class PathSegmentTailcardRouteSelector extends RouteSelector {
    private final String name;
    private final String prefix;

    public PathSegmentTailcardRouteSelector() {
        this((String) null, (String) null, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ PathSegmentTailcardRouteSelector copy$default(PathSegmentTailcardRouteSelector pathSegmentTailcardRouteSelector, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pathSegmentTailcardRouteSelector.name;
        }
        if ((i & 2) != 0) {
            str2 = pathSegmentTailcardRouteSelector.prefix;
        }
        return pathSegmentTailcardRouteSelector.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.prefix;
    }

    public final PathSegmentTailcardRouteSelector copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "prefix");
        return new PathSegmentTailcardRouteSelector(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegmentTailcardRouteSelector)) {
            return false;
        }
        PathSegmentTailcardRouteSelector pathSegmentTailcardRouteSelector = (PathSegmentTailcardRouteSelector) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) pathSegmentTailcardRouteSelector.name) && Intrinsics.areEqual((Object) this.prefix, (Object) pathSegmentTailcardRouteSelector.prefix);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.prefix.hashCode();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PathSegmentTailcardRouteSelector(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public PathSegmentTailcardRouteSelector(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "prefix");
        this.name = str;
        this.prefix = str2;
        CharSequence charSequence = str2;
        int i = 0;
        while (i < charSequence.length()) {
            if (charSequence.charAt(i) != '/') {
                i++;
            } else {
                throw new IllegalArgumentException("Multisegment prefix is not supported".toString());
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "hasTrailingSlash is not used anymore. This is going to be removed", replaceWith = @ReplaceWith(expression = "PathSegmentTailcardRouteSelector(name, prefix)", imports = {}))
    public PathSegmentTailcardRouteSelector(String str, String str2, boolean z) {
        this(str, str2);
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "prefix");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PathSegmentTailcardRouteSelector(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, z);
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        List list;
        Parameters parameters;
        String str;
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        List<String> segments = routingResolveContext.getSegments();
        if (!segments.isEmpty()) {
            ListIterator<String> listIterator = segments.listIterator(segments.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        list = CollectionsKt.take(segments, listIterator.nextIndex() + 1);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        list = CollectionsKt.emptyList();
        int i2 = 0;
        if (this.prefix.length() > 0 && ((str = (String) CollectionsKt.getOrNull(list, i)) == null || !StringsKt.startsWith$default(str, this.prefix, false, 2, (Object) null))) {
            return RouteSelectorEvaluation.Companion.getFailedPath();
        }
        if (this.name.length() == 0) {
            parameters = ParametersKt.parametersOf();
        } else {
            String str2 = this.name;
            Iterable drop = CollectionsKt.drop(list, i);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(drop, 10));
            for (Object next : drop) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str3 = (String) next;
                if (i2 == 0) {
                    str3 = StringsKt.drop(str3, this.prefix.length());
                }
                arrayList.add(str3);
                i2 = i3;
            }
            parameters = ParametersKt.parametersOf(str2, (List<String>) (List) arrayList);
        }
        return new RouteSelectorEvaluation.Success(i < list.size() ? 0.1d : 0.2d, parameters, list.size() - i);
    }

    public String toString() {
        return "{...}";
    }
}
