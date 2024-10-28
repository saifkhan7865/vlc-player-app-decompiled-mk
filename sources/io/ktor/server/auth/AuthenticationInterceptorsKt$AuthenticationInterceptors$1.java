package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
/* synthetic */ class AuthenticationInterceptorsKt$AuthenticationInterceptors$1 extends FunctionReferenceImpl implements Function0<RouteAuthenticationConfig> {
    public static final AuthenticationInterceptorsKt$AuthenticationInterceptors$1 INSTANCE = new AuthenticationInterceptorsKt$AuthenticationInterceptors$1();

    AuthenticationInterceptorsKt$AuthenticationInterceptors$1() {
        super(0, RouteAuthenticationConfig.class, "<init>", "<init>()V", 0);
    }

    public final RouteAuthenticationConfig invoke() {
        return new RouteAuthenticationConfig();
    }
}
