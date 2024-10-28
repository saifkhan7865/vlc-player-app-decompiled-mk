package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/auth/AuthenticationStrategy;", "invoke", "(Lio/ktor/server/auth/AuthenticationStrategy;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationInterceptorsKt$AuthenticationInterceptors$2$notRequiredProviders$1 extends Lambda implements Function1<AuthenticationStrategy, Boolean> {
    public static final AuthenticationInterceptorsKt$AuthenticationInterceptors$2$notRequiredProviders$1 INSTANCE = new AuthenticationInterceptorsKt$AuthenticationInterceptors$2$notRequiredProviders$1();

    AuthenticationInterceptorsKt$AuthenticationInterceptors$2$notRequiredProviders$1() {
        super(1);
    }

    public final Boolean invoke(AuthenticationStrategy authenticationStrategy) {
        Intrinsics.checkNotNullParameter(authenticationStrategy, "it");
        return Boolean.valueOf(authenticationStrategy != AuthenticationStrategy.Required);
    }
}
