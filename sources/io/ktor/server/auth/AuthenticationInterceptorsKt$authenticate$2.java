package io.ktor.server.auth;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/auth/RouteAuthenticationConfig;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationInterceptorsKt$authenticate$2 extends Lambda implements Function1<RouteAuthenticationConfig, Unit> {
    final /* synthetic */ List<AuthenticateProvidersRegistration> $allConfigurations;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AuthenticationInterceptorsKt$authenticate$2(List<AuthenticateProvidersRegistration> list) {
        super(1);
        this.$allConfigurations = list;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RouteAuthenticationConfig) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(RouteAuthenticationConfig routeAuthenticationConfig) {
        Intrinsics.checkNotNullParameter(routeAuthenticationConfig, "$this$install");
        routeAuthenticationConfig.setProviders$ktor_server_auth(this.$allConfigurations);
    }
}
