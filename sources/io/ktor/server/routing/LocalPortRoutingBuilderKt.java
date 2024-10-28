package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"localPort", "Lio/ktor/server/routing/Route;", "port", "", "build", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: LocalPortRoutingBuilder.kt */
public final class LocalPortRoutingBuilderKt {
    public static final Route localPort(Route route, int i, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function1, "build");
        if (1 > i || i >= 65536) {
            throw new IllegalArgumentException(("Port " + i + " must be a positive number between 1 and 65,535").toString());
        }
        Route createChild = route.createChild(new LocalPortRouteSelector(i));
        function1.invoke(createChild);
        return createChild;
    }
}
