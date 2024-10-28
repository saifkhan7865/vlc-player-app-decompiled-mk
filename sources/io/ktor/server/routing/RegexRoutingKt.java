package io.ktor.server.routing;

import io.ktor.http.HttpMethod;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001aW\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u000329\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aW\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u000329\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aW\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u000329\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aW\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u000329\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aW\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u000329\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ah\u0010\u0012\u001a\u00020\u0001\"\n\b\u0000\u0010\u0013\u0018\u0001*\u00020\f*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032;\b\u0004\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u000e\u001aW\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u000329\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ah\u0010\u0015\u001a\u00020\u0001\"\n\b\u0000\u0010\u0013\u0018\u0001*\u00020\f*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032;\b\u0004\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u000e\u001aW\u0010\u0017\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u000329\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ah\u0010\u0017\u001a\u00020\u0001\"\n\b\u0000\u0010\u0013\u0018\u0001*\u00020\f*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032;\b\u0004\u0010\u0006\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007¢\u0006\u0002\b\rH\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u000e\u001a5\u0010\u0019\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\u001d¢\u0006\u0002\b\rH\u0007\u001a-\u0010\u0019\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\u001d¢\u0006\u0002\b\rH\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"createRouteFromRegexPath", "Lio/ktor/server/routing/Route;", "regex", "Lkotlin/text/Regex;", "delete", "path", "body", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/routing/Route;Lkotlin/text/Regex;Lkotlin/jvm/functions/Function3;)Lio/ktor/server/routing/Route;", "get", "head", "options", "patch", "R", "patchTypedPath", "post", "postTypedPath", "put", "putTypedPath", "route", "method", "Lio/ktor/http/HttpMethod;", "build", "Lkotlin/Function1;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RegexRouting.kt */
public final class RegexRoutingKt {
    @KtorDsl
    public static final Route route(Route route, Regex regex, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createRouteFromRegexPath = createRouteFromRegexPath(route, regex);
        function1.invoke(createRouteFromRegexPath);
        return createRouteFromRegexPath;
    }

    @KtorDsl
    public static final Route route(Route route, Regex regex, HttpMethod httpMethod, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = createRouteFromRegexPath(route, regex).createChild(new HttpMethodRouteSelector(httpMethod));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route get(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, regex, HttpMethod.Companion.getGet(), new RegexRoutingKt$get$1(function3));
    }

    @KtorDsl
    public static final Route post(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, regex, HttpMethod.Companion.getPost(), new RegexRoutingKt$post$1(function3));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route postTypedPath(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return post(route, regex, new RegexRoutingKt$post$2(function3, (Continuation<? super RegexRoutingKt$post$2>) null));
    }

    @KtorDsl
    public static final Route head(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, regex, HttpMethod.Companion.getHead(), new RegexRoutingKt$head$1(function3));
    }

    @KtorDsl
    public static final Route put(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, regex, HttpMethod.Companion.getPut(), new RegexRoutingKt$put$1(function3));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route putTypedPath(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return put(route, regex, new RegexRoutingKt$put$2(function3, (Continuation<? super RegexRoutingKt$put$2>) null));
    }

    @KtorDsl
    public static final Route patch(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, regex, HttpMethod.Companion.getPatch(), new RegexRoutingKt$patch$1(function3));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route patchTypedPath(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return patch(route, regex, new RegexRoutingKt$patch$2(function3, (Continuation<? super RegexRoutingKt$patch$2>) null));
    }

    @KtorDsl
    public static final Route delete(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, regex, HttpMethod.Companion.getDelete(), new RegexRoutingKt$delete$1(function3));
    }

    @KtorDsl
    public static final Route options(Route route, Regex regex, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, regex, HttpMethod.Companion.getOptions(), new RegexRoutingKt$options$1(function3));
    }

    private static final Route createRouteFromRegexPath(Route route, Regex regex) {
        return route.createChild(new PathSegmentRegexRouteSelector(regex));
    }
}
