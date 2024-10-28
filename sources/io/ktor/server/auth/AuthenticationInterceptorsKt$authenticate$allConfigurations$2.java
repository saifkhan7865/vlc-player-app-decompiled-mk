package io.ktor.server.auth;

import io.ktor.server.routing.Route;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/server/auth/AuthenticateProvidersRegistration;", "it", "Lio/ktor/server/routing/Route;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationInterceptorsKt$authenticate$allConfigurations$2 extends Lambda implements Function1<Route, AuthenticateProvidersRegistration> {
    public static final AuthenticationInterceptorsKt$authenticate$allConfigurations$2 INSTANCE = new AuthenticationInterceptorsKt$authenticate$allConfigurations$2();

    AuthenticationInterceptorsKt$authenticate$allConfigurations$2() {
        super(1);
    }

    public final AuthenticateProvidersRegistration invoke(Route route) {
        Intrinsics.checkNotNullParameter(route, "it");
        return (AuthenticateProvidersRegistration) route.getAttributes().getOrNull(AuthenticationInterceptorsKt.AuthenticateProvidersKey);
    }
}
