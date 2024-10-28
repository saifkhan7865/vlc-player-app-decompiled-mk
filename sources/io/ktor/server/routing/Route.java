package io.ktor.server.routing;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0017\u0018\u00002\u00020\u0001B%\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\b\u0010\"\u001a\u00020\u0017H\u0016J\r\u0010#\u001a\u00020\u0001H\u0000¢\u0006\u0002\b$J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004JI\u0010&\u001a\u00020\u001729\u0010'\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0015¢\u0006\u0002\b\u001bø\u0001\u0000¢\u0006\u0002\u0010(J\b\u0010)\u001a\u00020\u0017H\u0002J\"\u0010*\u001a\u00020\u00172\u0017\u0010+\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00170,¢\u0006\u0002\b\u001bH\u0002J\b\u0010-\u001a\u00020.H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\rX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013RV\u0010\u0014\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0015¢\u0006\u0002\b\u001b0\rX\u0004ø\u0001\u0000¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001d\u0010\u0013R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lio/ktor/server/routing/Route;", "Lio/ktor/server/application/ApplicationCallPipeline;", "parent", "selector", "Lio/ktor/server/routing/RouteSelector;", "environment", "Lio/ktor/server/application/ApplicationEnvironment;", "(Lio/ktor/server/routing/Route;Lio/ktor/server/routing/RouteSelector;Lio/ktor/server/application/ApplicationEnvironment;)V", "developmentMode", "", "(Lio/ktor/server/routing/Route;Lio/ktor/server/routing/RouteSelector;ZLio/ktor/server/application/ApplicationEnvironment;)V", "cachedPipeline", "childList", "", "getChildList$annotations", "()V", "children", "", "getChildren", "()Ljava/util/List;", "handlers", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "getHandlers$ktor_server_core$annotations", "getHandlers$ktor_server_core", "getParent", "()Lio/ktor/server/routing/Route;", "getSelector", "()Lio/ktor/server/routing/RouteSelector;", "afterIntercepted", "buildPipeline", "buildPipeline$ktor_server_core", "createChild", "handle", "handler", "(Lkotlin/jvm/functions/Function3;)V", "invalidateCachesRecursively", "invoke", "body", "Lkotlin/Function1;", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: Route.kt */
public class Route extends ApplicationCallPipeline {
    private ApplicationCallPipeline cachedPipeline;
    private final List<Route> childList;
    private final List<Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object>> handlers;
    private final Route parent;
    private final RouteSelector selector;

    private static /* synthetic */ void getChildList$annotations() {
    }

    public static /* synthetic */ void getHandlers$ktor_server_core$annotations() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Route(Route route, RouteSelector routeSelector, boolean z, ApplicationEnvironment applicationEnvironment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(route, routeSelector, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : applicationEnvironment);
    }

    public final Route getParent() {
        return this.parent;
    }

    public final RouteSelector getSelector() {
        return this.selector;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Route(Route route, RouteSelector routeSelector, boolean z, ApplicationEnvironment applicationEnvironment) {
        super(z, applicationEnvironment);
        Intrinsics.checkNotNullParameter(routeSelector, "selector");
        this.parent = route;
        this.selector = routeSelector;
        this.childList = new ArrayList();
        this.handlers = new ArrayList();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Route(Route route, RouteSelector routeSelector, ApplicationEnvironment applicationEnvironment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(route, routeSelector, (i & 4) != 0 ? null : applicationEnvironment);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use constructor with developmentMode parameter")
    public /* synthetic */ Route(Route route, RouteSelector routeSelector, ApplicationEnvironment applicationEnvironment) {
        this(route, routeSelector, false, applicationEnvironment);
        Intrinsics.checkNotNullParameter(routeSelector, "selector");
    }

    public final List<Route> getChildren() {
        return this.childList;
    }

    public final List<Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object>> getHandlers$ktor_server_core() {
        return this.handlers;
    }

    public final Route createChild(RouteSelector routeSelector) {
        Object obj;
        Intrinsics.checkNotNullParameter(routeSelector, "selector");
        Iterator it = this.childList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((Route) obj).selector, (Object) routeSelector)) {
                break;
            }
        }
        Route route = (Route) obj;
        if (route != null) {
            return route;
        }
        Route route2 = new Route(this, routeSelector, getDevelopmentMode(), getEnvironment());
        this.childList.add(route2);
        return route2;
    }

    public final void invoke(Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "body");
        function1.invoke(this);
    }

    public final void handle(Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "handler");
        this.handlers.add(function3);
        this.cachedPipeline = null;
    }

    public void afterIntercepted() {
        invalidateCachesRecursively();
    }

    private final void invalidateCachesRecursively() {
        this.cachedPipeline = null;
        for (Route invalidateCachesRecursively : this.childList) {
            invalidateCachesRecursively.invalidateCachesRecursively();
        }
    }

    public final ApplicationCallPipeline buildPipeline$ktor_server_core() {
        ApplicationCallPipeline applicationCallPipeline = this.cachedPipeline;
        if (applicationCallPipeline == null) {
            Route route = this;
            applicationCallPipeline = new ApplicationCallPipeline(getDevelopmentMode(), RoutingKt.getApplication(this).getEnvironment());
            List arrayList = new ArrayList();
            for (Route route2 = this; route2 != null; route2 = route2.parent) {
                arrayList.add(route2);
            }
            for (int lastIndex = CollectionsKt.getLastIndex(arrayList); -1 < lastIndex; lastIndex--) {
                ApplicationCallPipeline applicationCallPipeline2 = (ApplicationCallPipeline) arrayList.get(lastIndex);
                applicationCallPipeline.merge(applicationCallPipeline2);
                applicationCallPipeline.getReceivePipeline().merge(applicationCallPipeline2.getReceivePipeline());
                applicationCallPipeline.getSendPipeline().merge(applicationCallPipeline2.getSendPipeline());
            }
            List<Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object>> list = this.handlers;
            int lastIndex2 = CollectionsKt.getLastIndex(list);
            if (lastIndex2 >= 0) {
                int i = 0;
                while (true) {
                    applicationCallPipeline.intercept(ApplicationCallPipeline.ApplicationPhase.getCall(), new Route$buildPipeline$1$1(list, i, (Continuation<? super Route$buildPipeline$1$1>) null));
                    if (i == lastIndex2) {
                        break;
                    }
                    i++;
                }
            }
            this.cachedPipeline = applicationCallPipeline;
        }
        return applicationCallPipeline;
    }

    public String toString() {
        StringBuilder sb;
        Route route = this.parent;
        String route2 = route != null ? route.toString() : null;
        if (route2 == null) {
            if (this.selector instanceof TrailingSlashRouteSelector) {
                return "/";
            }
            return "/" + this.selector;
        } else if (!(this.selector instanceof TrailingSlashRouteSelector)) {
            if (StringsKt.endsWith$default((CharSequence) route2, '/', false, 2, (Object) null)) {
                sb = new StringBuilder();
                sb.append(route2);
            } else {
                sb = new StringBuilder();
                sb.append(route2);
                sb.append('/');
            }
            sb.append(this.selector);
            return sb.toString();
        } else if (StringsKt.endsWith$default((CharSequence) route2, '/', false, 2, (Object) null)) {
            return route2;
        } else {
            return route2 + '/';
        }
    }
}
