package io.ktor.server.routing;

import androidx.core.app.NotificationCompat;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.routing.RoutingResolveResult;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005J\u0016\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0006J\u001e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000bJ\u0010\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0010H\u0002J\u000e\u0010\"\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u000bJ\u001e\u0010#\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000bJ\b\u0010$\u001a\u00020\u0006H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lio/ktor/server/routing/RoutingResolveTrace;", "", "call", "Lio/ktor/server/application/ApplicationCall;", "segments", "", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/util/List;)V", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "finalResult", "Lio/ktor/server/routing/RoutingResolveResult;", "resolveCandidates", "", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "routing", "Lio/ktor/server/routing/RoutingResolveTraceEntry;", "getSegments", "()Ljava/util/List;", "stack", "Lio/ktor/server/routing/Stack;", "addCandidate", "", "trait", "begin", "route", "Lio/ktor/server/routing/Route;", "segmentIndex", "", "buildText", "finish", "result", "register", "entry", "registerFinalResult", "skip", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingResolveTrace.kt */
public final class RoutingResolveTrace {
    private final ApplicationCall call;
    private RoutingResolveResult finalResult;
    private final List<List<RoutingResolveResult.Success>> resolveCandidates = new ArrayList();
    private RoutingResolveTraceEntry routing;
    private final List<String> segments;
    private final Stack<RoutingResolveTraceEntry> stack = new Stack<>();

    public RoutingResolveTrace(ApplicationCall applicationCall, List<String> list) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(list, "segments");
        this.call = applicationCall;
        this.segments = list;
    }

    public final ApplicationCall getCall() {
        return this.call;
    }

    public final List<String> getSegments() {
        return this.segments;
    }

    private final void register(RoutingResolveTraceEntry routingResolveTraceEntry) {
        if (this.stack.empty()) {
            this.routing = routingResolveTraceEntry;
        } else {
            this.stack.peek().append(routingResolveTraceEntry);
        }
    }

    public final void begin(Route route, int i) {
        Intrinsics.checkNotNullParameter(route, "route");
        this.stack.push(new RoutingResolveTraceEntry(route, i, (RoutingResolveResult) null, 4, (DefaultConstructorMarker) null));
    }

    public final void finish(Route route, int i, RoutingResolveResult routingResolveResult) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(routingResolveResult, "result");
        RoutingResolveTraceEntry pop = this.stack.pop();
        if (!Intrinsics.areEqual((Object) pop.getRoute(), (Object) route)) {
            throw new IllegalArgumentException("end should be called for the same route as begin".toString());
        } else if (pop.getSegmentIndex() == i) {
            pop.setResult(routingResolveResult);
            register(pop);
        } else {
            throw new IllegalArgumentException("end should be called for the same segmentIndex as begin".toString());
        }
    }

    public final void skip(Route route, int i, RoutingResolveResult routingResolveResult) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(routingResolveResult, "result");
        register(new RoutingResolveTraceEntry(route, i, routingResolveResult));
    }

    public final void registerFinalResult(RoutingResolveResult routingResolveResult) {
        Intrinsics.checkNotNullParameter(routingResolveResult, "result");
        this.finalResult = routingResolveResult;
    }

    public String toString() {
        return "Trace for " + this.segments;
    }

    public final String buildText() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        Intrinsics.checkNotNullExpressionValue(sb, "append(value)");
        sb.append(10);
        Intrinsics.checkNotNullExpressionValue(sb, "append('\\n')");
        RoutingResolveTraceEntry routingResolveTraceEntry = this.routing;
        if (routingResolveTraceEntry != null) {
            routingResolveTraceEntry.buildText(sb, 0);
        }
        if (this.finalResult != null) {
            sb.append("Matched routes:");
            Intrinsics.checkNotNullExpressionValue(sb, "append(value)");
            sb.append(10);
            Intrinsics.checkNotNullExpressionValue(sb, "append('\\n')");
            if (this.resolveCandidates.isEmpty()) {
                sb.append("  No results");
                Intrinsics.checkNotNullExpressionValue(sb, "append(value)");
                sb.append(10);
                Intrinsics.checkNotNullExpressionValue(sb, "append('\\n')");
            } else {
                sb.append(CollectionsKt.joinToString$default(this.resolveCandidates, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, RoutingResolveTrace$buildText$1$2.INSTANCE, 30, (Object) null));
                Intrinsics.checkNotNullExpressionValue(sb, "append(value)");
                sb.append(10);
                Intrinsics.checkNotNullExpressionValue(sb, "append('\\n')");
            }
            sb.append("Route resolve result:");
            Intrinsics.checkNotNullExpressionValue(sb, "append(value)");
            sb.append(10);
            Intrinsics.checkNotNullExpressionValue(sb, "append('\\n')");
            StringBuilder sb2 = new StringBuilder("  ");
            RoutingResolveResult routingResolveResult = this.finalResult;
            if (routingResolveResult == null) {
                Intrinsics.throwUninitializedPropertyAccessException("finalResult");
                routingResolveResult = null;
            }
            sb2.append(routingResolveResult);
            sb.append(sb2.toString());
        }
        String sb3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "StringBuilder().apply(builderAction).toString()");
        return sb3;
    }

    public final void addCandidate(List<RoutingResolveResult.Success> list) {
        Intrinsics.checkNotNullParameter(list, "trait");
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i));
        }
        this.resolveCandidates.add(arrayList);
    }
}
