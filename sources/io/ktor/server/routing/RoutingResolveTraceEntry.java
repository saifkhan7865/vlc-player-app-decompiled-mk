package io.ktor.server.routing;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0000J\u001c\u0010\u0016\u001a\u00020\u00142\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lio/ktor/server/routing/RoutingResolveTraceEntry;", "", "route", "Lio/ktor/server/routing/Route;", "segmentIndex", "", "result", "Lio/ktor/server/routing/RoutingResolveResult;", "(Lio/ktor/server/routing/Route;ILio/ktor/server/routing/RoutingResolveResult;)V", "children", "", "getResult", "()Lio/ktor/server/routing/RoutingResolveResult;", "setResult", "(Lio/ktor/server/routing/RoutingResolveResult;)V", "getRoute", "()Lio/ktor/server/routing/Route;", "getSegmentIndex", "()I", "append", "", "item", "buildText", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "indent", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingResolveTrace.kt */
public class RoutingResolveTraceEntry {
    private List<RoutingResolveTraceEntry> children;
    private RoutingResolveResult result;
    private final Route route;
    private final int segmentIndex;

    public RoutingResolveTraceEntry(Route route2, int i, RoutingResolveResult routingResolveResult) {
        Intrinsics.checkNotNullParameter(route2, "route");
        this.route = route2;
        this.segmentIndex = i;
        this.result = routingResolveResult;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RoutingResolveTraceEntry(Route route2, int i, RoutingResolveResult routingResolveResult, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(route2, i, (i2 & 4) != 0 ? null : routingResolveResult);
    }

    public final Route getRoute() {
        return this.route;
    }

    public final int getSegmentIndex() {
        return this.segmentIndex;
    }

    public final RoutingResolveResult getResult() {
        return this.result;
    }

    public final void setResult(RoutingResolveResult routingResolveResult) {
        this.result = routingResolveResult;
    }

    public final void append(RoutingResolveTraceEntry routingResolveTraceEntry) {
        Intrinsics.checkNotNullParameter(routingResolveTraceEntry, "item");
        List<RoutingResolveTraceEntry> list = this.children;
        if (list == null) {
            list = new ArrayList<>();
            this.children = list;
        }
        list.add(routingResolveTraceEntry);
    }

    public void buildText(StringBuilder sb, int i) {
        Intrinsics.checkNotNullParameter(sb, "builder");
        sb.append(StringsKt.repeat("  ", i) + this);
        Intrinsics.checkNotNullExpressionValue(sb, "append(value)");
        sb.append(10);
        Intrinsics.checkNotNullExpressionValue(sb, "append('\\n')");
        List<RoutingResolveTraceEntry> list = this.children;
        if (list != null) {
            for (RoutingResolveTraceEntry buildText : list) {
                buildText.buildText(sb, i + 1);
            }
        }
    }

    public String toString() {
        return this.route + ", segment:" + this.segmentIndex + " -> " + this.result;
    }
}
