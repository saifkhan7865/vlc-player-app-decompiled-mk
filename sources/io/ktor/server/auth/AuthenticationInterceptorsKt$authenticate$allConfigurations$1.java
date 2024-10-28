package io.ktor.server.auth;

import io.ktor.server.routing.Route;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lio/ktor/server/routing/Route;", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationInterceptorsKt$authenticate$allConfigurations$1 extends Lambda implements Function1<Route, Route> {
    public static final AuthenticationInterceptorsKt$authenticate$allConfigurations$1 INSTANCE = new AuthenticationInterceptorsKt$authenticate$allConfigurations$1();

    AuthenticationInterceptorsKt$authenticate$allConfigurations$1() {
        super(1);
    }

    public final Route invoke(Route route) {
        Intrinsics.checkNotNullParameter(route, "it");
        return route.getParent();
    }
}
