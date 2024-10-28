package io.ktor.server.auth;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/auth/AuthenticateProvidersRegistration;", "", "names", "", "", "strategy", "Lio/ktor/server/auth/AuthenticationStrategy;", "(Ljava/util/List;Lio/ktor/server/auth/AuthenticationStrategy;)V", "getNames", "()Ljava/util/List;", "getStrategy", "()Lio/ktor/server/auth/AuthenticationStrategy;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
public final class AuthenticateProvidersRegistration {
    private final List<String> names;
    private final AuthenticationStrategy strategy;

    public AuthenticateProvidersRegistration(List<String> list, AuthenticationStrategy authenticationStrategy) {
        Intrinsics.checkNotNullParameter(list, "names");
        Intrinsics.checkNotNullParameter(authenticationStrategy, "strategy");
        this.names = list;
        this.strategy = authenticationStrategy;
    }

    public final List<String> getNames() {
        return this.names;
    }

    public final AuthenticationStrategy getStrategy() {
        return this.strategy;
    }
}
