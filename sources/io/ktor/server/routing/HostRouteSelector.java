package io.ktor.server.routing;

import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.server.plugins.OriginConnectionPointKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0016J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\b\u0010\u001c\u001a\u00020\u0004H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u001e"}, d2 = {"Lio/ktor/server/routing/HostRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "hostList", "", "", "hostPatterns", "Lkotlin/text/Regex;", "portsList", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getHostList", "()Ljava/util/List;", "getHostPatterns", "getPortsList", "component1", "component2", "component3", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "hashCode", "toString", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HostsRoutingBuilder.kt */
public final class HostRouteSelector extends RouteSelector {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String HostNameParameter = "$RequestHost";
    public static final String PortParameter = "$RequestPort";
    private final List<String> hostList;
    private final List<Regex> hostPatterns;
    private final List<Integer> portsList;

    public static /* synthetic */ HostRouteSelector copy$default(HostRouteSelector hostRouteSelector, List<String> list, List<Regex> list2, List<Integer> list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = hostRouteSelector.hostList;
        }
        if ((i & 2) != 0) {
            list2 = hostRouteSelector.hostPatterns;
        }
        if ((i & 4) != 0) {
            list3 = hostRouteSelector.portsList;
        }
        return hostRouteSelector.copy(list, list2, list3);
    }

    public final List<String> component1() {
        return this.hostList;
    }

    public final List<Regex> component2() {
        return this.hostPatterns;
    }

    public final List<Integer> component3() {
        return this.portsList;
    }

    public final HostRouteSelector copy(List<String> list, List<Regex> list2, List<Integer> list3) {
        Intrinsics.checkNotNullParameter(list, "hostList");
        Intrinsics.checkNotNullParameter(list2, "hostPatterns");
        Intrinsics.checkNotNullParameter(list3, "portsList");
        return new HostRouteSelector(list, list2, list3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HostRouteSelector)) {
            return false;
        }
        HostRouteSelector hostRouteSelector = (HostRouteSelector) obj;
        return Intrinsics.areEqual((Object) this.hostList, (Object) hostRouteSelector.hostList) && Intrinsics.areEqual((Object) this.hostPatterns, (Object) hostRouteSelector.hostPatterns) && Intrinsics.areEqual((Object) this.portsList, (Object) hostRouteSelector.portsList);
    }

    public int hashCode() {
        return (((this.hostList.hashCode() * 31) + this.hostPatterns.hashCode()) * 31) + this.portsList.hashCode();
    }

    public final List<String> getHostList() {
        return this.hostList;
    }

    public final List<Regex> getHostPatterns() {
        return this.hostPatterns;
    }

    public final List<Integer> getPortsList() {
        return this.portsList;
    }

    public HostRouteSelector(List<String> list, List<Regex> list2, List<Integer> list3) {
        Intrinsics.checkNotNullParameter(list, "hostList");
        Intrinsics.checkNotNullParameter(list2, "hostPatterns");
        Intrinsics.checkNotNullParameter(list3, "portsList");
        this.hostList = list;
        this.hostPatterns = list2;
        this.portsList = list3;
        if (!(!list.isEmpty()) && !(!list2.isEmpty()) && !(!list3.isEmpty())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        boolean z;
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        String serverHost = OriginConnectionPointKt.getOrigin(routingResolveContext.getCall().getRequest()).getServerHost();
        int serverPort = OriginConnectionPointKt.getOrigin(routingResolveContext.getCall().getRequest()).getServerPort();
        if ((!this.hostList.isEmpty()) || (!this.hostPatterns.isEmpty())) {
            boolean contains = this.hostList.contains(serverHost);
            if (!contains) {
                Iterable iterable = this.hostPatterns;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (((Regex) it.next()).matches(serverHost)) {
                                z = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            z = false;
            if (!contains && !z) {
                return RouteSelectorEvaluation.Companion.getFailed();
            }
        }
        if ((!this.portsList.isEmpty()) && !this.portsList.contains(Integer.valueOf(serverPort))) {
            return RouteSelectorEvaluation.Companion.getFailed();
        }
        Parameters.Companion companion = Parameters.Companion;
        ParametersBuilder ParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, (Object) null);
        ParametersBuilder$default.append(HostNameParameter, serverHost);
        ParametersBuilder$default.append(PortParameter, String.valueOf(serverPort));
        return new RouteSelectorEvaluation.Success(1.0d, ParametersBuilder$default.build(), 0, 4, (DefaultConstructorMarker) null);
    }

    public String toString() {
        return "(" + this.hostList + ", " + this.hostPatterns + ", " + this.portsList + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lio/ktor/server/routing/HostRouteSelector$Companion;", "", "()V", "HostNameParameter", "", "PortParameter", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HostsRoutingBuilder.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
