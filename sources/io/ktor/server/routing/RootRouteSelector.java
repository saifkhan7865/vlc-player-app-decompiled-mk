package io.ktor.server.routing;

import io.ktor.http.Parameters;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/ktor/server/routing/RootRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "rootPath", "", "(Ljava/lang/String;)V", "parts", "", "successEvaluationResult", "Lio/ktor/server/routing/RouteSelectorEvaluation$Success;", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class RootRouteSelector extends RouteSelector {
    private final List<String> parts;
    private final RouteSelectorEvaluation.Success successEvaluationResult;

    public RootRouteSelector() {
        this((String) null, 1, (DefaultConstructorMarker) null);
    }

    public RootRouteSelector(String str) {
        Intrinsics.checkNotNullParameter(str, "rootPath");
        Iterable<RoutingPathSegment> parts2 = RoutingPath.Companion.parse(str).getParts();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parts2, 10));
        for (RoutingPathSegment routingPathSegment : parts2) {
            if (routingPathSegment.getKind() == RoutingPathSegmentKind.Constant) {
                arrayList.add(routingPathSegment.getValue());
            } else {
                throw new IllegalArgumentException("rootPath should be constant, no wildcards supported.".toString());
            }
        }
        List<String> list = (List) arrayList;
        this.parts = list;
        this.successEvaluationResult = new RouteSelectorEvaluation.Success(1.0d, (Parameters) null, list.size(), 2, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RootRouteSelector(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        if (i != 0) {
            throw new IllegalStateException("Root selector should be evaluated first.".toString());
        } else if (this.parts.isEmpty()) {
            return RouteSelectorEvaluation.Companion.getConstant();
        } else {
            List<String> list = this.parts;
            List<String> segments = routingResolveContext.getSegments();
            if (segments.size() < list.size()) {
                return RouteSelectorEvaluation.Companion.getFailedPath();
            }
            int size = list.size() + i;
            while (i < size) {
                if (!Intrinsics.areEqual((Object) segments.get(i), (Object) list.get(i))) {
                    return RouteSelectorEvaluation.Companion.getFailedPath();
                }
                i++;
            }
            return this.successEvaluationResult;
        }
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.parts, "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
