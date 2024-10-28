package io.ktor.server.routing;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\u001a5\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b\u001aA\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b\u001aO\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b\u001a5\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b\u001a/\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\n\u0010\u000b\u001a\u00020\u000f\"\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¨\u0006\u0010"}, d2 = {"host", "Lio/ktor/server/routing/Route;", "", "port", "", "build", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "hosts", "", "ports", "hostPatterns", "Lkotlin/text/Regex;", "hostPattern", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HostsRoutingBuilder.kt */
public final class HostsRoutingBuilderKt {
    public static /* synthetic */ Route host$default(Route route, String str, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return host(route, str, i, (Function1<? super Route, Unit>) function1);
    }

    public static final Route host(Route route, String str, int i, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(function1, "build");
        return host(route, CollectionsKt.listOf(str), CollectionsKt.emptyList(), i > 0 ? CollectionsKt.listOf(Integer.valueOf(i)) : CollectionsKt.emptyList(), function1);
    }

    public static /* synthetic */ Route host$default(Route route, Regex regex, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return host(route, regex, i, (Function1<? super Route, Unit>) function1);
    }

    public static final Route host(Route route, Regex regex, int i, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(regex, "hostPattern");
        Intrinsics.checkNotNullParameter(function1, "build");
        return host(route, CollectionsKt.emptyList(), CollectionsKt.listOf(regex), i > 0 ? CollectionsKt.listOf(Integer.valueOf(i)) : CollectionsKt.emptyList(), function1);
    }

    public static /* synthetic */ Route host$default(Route route, List list, List list2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return host(route, (List<String>) list, (List<Integer>) list2, (Function1<? super Route, Unit>) function1);
    }

    public static final Route host(Route route, List<String> list, List<Integer> list2, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(list, "hosts");
        Intrinsics.checkNotNullParameter(list2, "ports");
        Intrinsics.checkNotNullParameter(function1, "build");
        return host(route, list, CollectionsKt.emptyList(), list2, function1);
    }

    public static /* synthetic */ Route host$default(Route route, List list, List list2, List list3, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            list3 = CollectionsKt.emptyList();
        }
        return host(route, list, list2, list3, function1);
    }

    public static final Route host(Route route, List<String> list, List<Regex> list2, List<Integer> list3, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(list, "hosts");
        Intrinsics.checkNotNullParameter(list2, "hostPatterns");
        Intrinsics.checkNotNullParameter(list3, "ports");
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = route.createChild(new HostRouteSelector(list, list2, list3));
        function1.invoke(createChild);
        return createChild;
    }

    public static final Route port(Route route, int[] iArr, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(iArr, "ports");
        Intrinsics.checkNotNullParameter(function1, "build");
        if (!(iArr.length == 0)) {
            Route createChild = route.createChild(new HostRouteSelector(CollectionsKt.emptyList(), CollectionsKt.emptyList(), ArraysKt.toList(iArr)));
            function1.invoke(createChild);
            return createChild;
        }
        throw new IllegalArgumentException("At least one port need to be specified".toString());
    }
}
