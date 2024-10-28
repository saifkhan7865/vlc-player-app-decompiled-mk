package io.ktor.server.routing;

import androidx.core.app.NotificationCompat;
import io.ktor.http.CodecsKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.http.URLDecodeException;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import io.ktor.server.routing.RoutingResolveResult;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010!\u001a\u00020\"H\u0002J8\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00112\u0016\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u00192\u0006\u0010(\u001a\u00020$H\u0002J\u0016\u0010)\u001a\u00020\u00132\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00180\u0007H\u0002J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00072\u0006\u0010,\u001a\u00020\u001dH\u0002J\u0006\u0010-\u001a\u00020\"J(\u0010.\u001a\u00020\n2\u0006\u0010*\u001a\u00020\u000f2\u0016\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u0019H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u0019X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lio/ktor/server/routing/RoutingResolveContext;", "", "routing", "Lio/ktor/server/routing/Route;", "call", "Lio/ktor/server/application/ApplicationCall;", "tracers", "", "Lkotlin/Function1;", "Lio/ktor/server/routing/RoutingResolveTrace;", "", "(Lio/ktor/server/routing/Route;Lio/ktor/server/application/ApplicationCall;Ljava/util/List;)V", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "failedEvaluation", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "failedEvaluationDepth", "", "hasTrailingSlash", "", "getHasTrailingSlash", "()Z", "resolveResult", "Ljava/util/ArrayList;", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "Lkotlin/collections/ArrayList;", "getRouting", "()Lio/ktor/server/routing/Route;", "segments", "", "getSegments", "()Ljava/util/List;", "trace", "findBestRoute", "Lio/ktor/server/routing/RoutingResolveResult;", "handleRoute", "", "entry", "segmentIndex", "trait", "matchedQuality", "isBetterResolve", "new", "parse", "path", "resolve", "updateFailedEvaluation", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingResolveContext.kt */
public final class RoutingResolveContext {
    private final ApplicationCall call;
    private RouteSelectorEvaluation.Failure failedEvaluation = RouteSelectorEvaluation.Companion.getFailedPath();
    private int failedEvaluationDepth;
    private final boolean hasTrailingSlash;
    private final ArrayList<RoutingResolveResult.Success> resolveResult = new ArrayList<>(16);
    private final Route routing;
    private final List<String> segments;
    private final RoutingResolveTrace trace;
    private final List<Function1<RoutingResolveTrace, Unit>> tracers;

    public RoutingResolveContext(Route route, ApplicationCall applicationCall, List<? extends Function1<? super RoutingResolveTrace, Unit>> list) {
        Intrinsics.checkNotNullParameter(route, "routing");
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(list, "tracers");
        this.routing = route;
        this.call = applicationCall;
        this.tracers = list;
        RoutingResolveTrace routingResolveTrace = null;
        this.hasTrailingSlash = StringsKt.endsWith$default((CharSequence) ApplicationRequestPropertiesKt.path(applicationCall.getRequest()), '/', false, 2, (Object) null);
        try {
            List<String> parse = parse(ApplicationRequestPropertiesKt.path(applicationCall.getRequest()));
            this.segments = parse;
            if (!list.isEmpty()) {
                routingResolveTrace = new RoutingResolveTrace(applicationCall, parse);
            }
            this.trace = routingResolveTrace;
        } catch (URLDecodeException e) {
            throw new BadRequestException("Url decode failed for " + ApplicationRequestPropertiesKt.getUri(this.call.getRequest()), e);
        }
    }

    public final Route getRouting() {
        return this.routing;
    }

    public final ApplicationCall getCall() {
        return this.call;
    }

    public final List<String> getSegments() {
        return this.segments;
    }

    public final boolean getHasTrailingSlash() {
        return this.hasTrailingSlash;
    }

    private final List<String> parse(String str) {
        CharSequence charSequence = str;
        if (charSequence.length() == 0 || Intrinsics.areEqual((Object) str, (Object) "/")) {
            return CollectionsKt.emptyList();
        }
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (charSequence.charAt(i2) == '/') {
                i++;
            }
        }
        ArrayList arrayList = new ArrayList(i);
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int indexOf$default = StringsKt.indexOf$default(charSequence, '/', i4, false, 4, (Object) null);
            int i5 = indexOf$default == -1 ? length : indexOf$default;
            if (i5 != i4) {
                arrayList.add(CodecsKt.decodeURLPart$default(str, i4, i5, (Charset) null, 4, (Object) null));
            }
            i4 = i5 + 1;
            i3 = i5;
        }
        if (!IgnoreTrailingSlashKt.getIgnoreTrailingSlash(this.call) && StringsKt.endsWith$default(str, "/", false, 2, (Object) null)) {
            arrayList.add("");
        }
        return arrayList;
    }

    public final RoutingResolveResult resolve() {
        handleRoute(this.routing, 0, new ArrayList(), -1.7976931348623157E308d);
        RoutingResolveResult findBestRoute = findBestRoute();
        RoutingResolveTrace routingResolveTrace = this.trace;
        if (routingResolveTrace != null) {
            routingResolveTrace.registerFinalResult(findBestRoute);
        }
        RoutingResolveTrace routingResolveTrace2 = this.trace;
        if (routingResolveTrace2 != null) {
            for (Function1 invoke : this.tracers) {
                invoke.invoke(routingResolveTrace2);
            }
        }
        return findBestRoute;
    }

    private final double handleRoute(Route route, int i, ArrayList<RoutingResolveResult.Success> arrayList, double d) {
        double d2;
        Route route2 = route;
        int i2 = i;
        ArrayList<RoutingResolveResult.Success> arrayList2 = arrayList;
        RouteSelectorEvaluation evaluate = route.getSelector().evaluate(this, i2);
        if (evaluate instanceof RouteSelectorEvaluation.Failure) {
            RoutingResolveTrace routingResolveTrace = this.trace;
            if (routingResolveTrace != null) {
                routingResolveTrace.skip(route2, i2, new RoutingResolveResult.Failure(route2, "Selector didn't match", ((RouteSelectorEvaluation.Failure) evaluate).getFailureStatusCode()));
            }
            if (i2 == this.segments.size()) {
                updateFailedEvaluation((RouteSelectorEvaluation.Failure) evaluate, arrayList2);
            }
            return -1.7976931348623157E308d;
        } else if (evaluate instanceof RouteSelectorEvaluation.Success) {
            RouteSelectorEvaluation.Success success = (RouteSelectorEvaluation.Success) evaluate;
            if (success.getQuality() != -1.0d && success.getQuality() < d) {
                RoutingResolveTrace routingResolveTrace2 = this.trace;
                if (routingResolveTrace2 != null) {
                    routingResolveTrace2.skip(route2, i2, new RoutingResolveResult.Failure(route2, "Better match was already found", HttpStatusCode.Companion.getNotFound()));
                }
                return -1.7976931348623157E308d;
            }
            RoutingResolveResult.Success success2 = new RoutingResolveResult.Success(route2, success.getParameters(), success.getQuality());
            int segmentIncrement = i2 + success.getSegmentIncrement();
            if (!route.getChildren().isEmpty() || segmentIncrement == this.segments.size()) {
                RoutingResolveTrace routingResolveTrace3 = this.trace;
                if (routingResolveTrace3 != null) {
                    routingResolveTrace3.begin(route2, segmentIncrement);
                }
                arrayList2.add(success2);
                if (!(!route.getHandlers$ktor_server_core().isEmpty()) || segmentIncrement != this.segments.size()) {
                    d2 = -1.7976931348623157E308d;
                } else {
                    if (this.resolveResult.isEmpty() || isBetterResolve(arrayList2)) {
                        d2 = success.getQuality();
                        this.resolveResult.clear();
                        this.resolveResult.addAll(arrayList2);
                        this.failedEvaluation = null;
                    } else {
                        d2 = -1.7976931348623157E308d;
                    }
                    RoutingResolveTrace routingResolveTrace4 = this.trace;
                    if (routingResolveTrace4 != null) {
                        routingResolveTrace4.addCandidate(arrayList2);
                    }
                }
                int lastIndex = CollectionsKt.getLastIndex(route.getChildren());
                if (lastIndex >= 0) {
                    double d3 = d2;
                    int i3 = 0;
                    while (true) {
                        double d4 = d3;
                        int i4 = i3;
                        double handleRoute = handleRoute(route.getChildren().get(i3), segmentIncrement, arrayList, d4);
                        d3 = handleRoute > 0.0d ? Math.max(d4, handleRoute) : d4;
                        if (i4 == lastIndex) {
                            break;
                        }
                        i3 = i4 + 1;
                    }
                    d2 = d3;
                }
                CollectionsKt.removeLast(arrayList2);
                RoutingResolveTrace routingResolveTrace5 = this.trace;
                if (routingResolveTrace5 != null) {
                    routingResolveTrace5.finish(route2, segmentIncrement, success2);
                }
                if (d2 > 0.0d) {
                    return success.getQuality();
                }
                return -1.7976931348623157E308d;
            }
            RoutingResolveTrace routingResolveTrace6 = this.trace;
            if (routingResolveTrace6 != null) {
                routingResolveTrace6.skip(route2, segmentIncrement, new RoutingResolveResult.Failure(route2, "Not all segments matched", HttpStatusCode.Companion.getNotFound()));
            }
            return -1.7976931348623157E308d;
        } else {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    private final RoutingResolveResult findBestRoute() {
        double d;
        HttpStatusCode httpStatusCode;
        ArrayList<RoutingResolveResult.Success> arrayList = this.resolveResult;
        if (arrayList.isEmpty()) {
            Route route = this.routing;
            RouteSelectorEvaluation.Failure failure = this.failedEvaluation;
            if (failure == null || (httpStatusCode = failure.getFailureStatusCode()) == null) {
                httpStatusCode = HttpStatusCode.Companion.getNotFound();
            }
            return new RoutingResolveResult.Failure(route, "No matched subtrees found", httpStatusCode);
        }
        int i = 0;
        ParametersBuilder ParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, (Object) null);
        List list = arrayList;
        int lastIndex = CollectionsKt.getLastIndex(list);
        double d2 = Double.MAX_VALUE;
        if (lastIndex >= 0) {
            while (true) {
                RoutingResolveResult.Success success = arrayList.get(i);
                Intrinsics.checkNotNullExpressionValue(success, "finalResolve[index]");
                RoutingResolveResult.Success success2 = success;
                ParametersBuilder$default.appendAll(success2.getParameters());
                if (success2.getQuality$ktor_server_core() == -1.0d) {
                    d = 1.0d;
                } else {
                    d = success2.getQuality$ktor_server_core();
                }
                d2 = Math.min(d2, d);
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return new RoutingResolveResult.Success(((RoutingResolveResult.Success) CollectionsKt.last(list)).getRoute(), ParametersBuilder$default.build(), d2);
    }

    private final boolean isBetterResolve(List<RoutingResolveResult.Success> list) {
        int i;
        int i2;
        ArrayList<RoutingResolveResult.Success> arrayList = this.resolveResult;
        int i3 = 0;
        int i4 = 0;
        while (i3 < arrayList.size() && i4 < list.size()) {
            double quality$ktor_server_core = arrayList.get(i3).getQuality$ktor_server_core();
            double quality$ktor_server_core2 = list.get(i4).getQuality$ktor_server_core();
            if (quality$ktor_server_core == -1.0d) {
                i3++;
            } else {
                if (quality$ktor_server_core2 != -1.0d) {
                    if (quality$ktor_server_core == quality$ktor_server_core2) {
                        i3++;
                    } else if (quality$ktor_server_core2 > quality$ktor_server_core) {
                        return true;
                    } else {
                        return false;
                    }
                }
                i4++;
            }
        }
        Iterable<RoutingResolveResult.Success> iterable = arrayList;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            i = 0;
            for (RoutingResolveResult.Success quality$ktor_server_core3 : iterable) {
                if ((!(quality$ktor_server_core3.getQuality$ktor_server_core() == -1.0d)) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        } else {
            i = 0;
        }
        Iterable<RoutingResolveResult.Success> iterable2 = list;
        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
            i2 = 0;
            for (RoutingResolveResult.Success quality$ktor_server_core4 : iterable2) {
                if ((!(quality$ktor_server_core4.getQuality$ktor_server_core() == -1.0d)) && (i2 = i2 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        } else {
            i2 = 0;
        }
        if (i2 > i) {
            return true;
        }
        return false;
    }

    private final void updateFailedEvaluation(RouteSelectorEvaluation.Failure failure, ArrayList<RoutingResolveResult.Success> arrayList) {
        RouteSelectorEvaluation.Failure failure2 = this.failedEvaluation;
        if (failure2 != null) {
            if (failure2.getQuality() < failure.getQuality() || this.failedEvaluationDepth < arrayList.size()) {
                Iterable<RoutingResolveResult.Success> iterable = arrayList;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    for (RoutingResolveResult.Success success : iterable) {
                        if (success.getQuality$ktor_server_core() != -1.0d && success.getQuality$ktor_server_core() != 1.0d) {
                            return;
                        }
                    }
                }
                this.failedEvaluation = failure;
                this.failedEvaluationDepth = arrayList.size();
            }
        }
    }
}
