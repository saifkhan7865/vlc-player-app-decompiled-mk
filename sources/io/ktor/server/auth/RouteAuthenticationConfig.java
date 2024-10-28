package io.ktor.server.auth;

import io.ktor.util.KtorDsl;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/server/auth/RouteAuthenticationConfig;", "", "()V", "providers", "", "Lio/ktor/server/auth/AuthenticateProvidersRegistration;", "getProviders$ktor_server_auth", "()Ljava/util/List;", "setProviders$ktor_server_auth", "(Ljava/util/List;)V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: AuthenticationInterceptors.kt */
public final class RouteAuthenticationConfig {
    private List<AuthenticateProvidersRegistration> providers = CollectionsKt.listOf(new AuthenticateProvidersRegistration(CollectionsKt.listOf(null), AuthenticationStrategy.FirstSuccessful));

    public final List<AuthenticateProvidersRegistration> getProviders$ktor_server_auth() {
        return this.providers;
    }

    public final void setProviders$ktor_server_auth(List<AuthenticateProvidersRegistration> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.providers = list;
    }
}
