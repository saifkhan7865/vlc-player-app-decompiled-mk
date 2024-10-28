package io.ktor.server.auth;

import io.ktor.server.auth.SessionAuthenticationProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0002\u001a\u00020\u0003\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\b\u001aG\u0010\u0002\u001a\u00020\u0003\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\b*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00012\u001d\u0010\t\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0002\b\fH\bø\u0001\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000*b\u0010\r\u001a\u0004\b\u0000\u0010\u0004\"+\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000e¢\u0006\u0002\b\f2+\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000e¢\u0006\u0002\b\f\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0011"}, d2 = {"SessionAuthChallengeKey", "", "session", "", "T", "Lio/ktor/server/auth/Principal;", "Lio/ktor/server/auth/AuthenticationConfig;", "name", "", "configure", "Lkotlin/Function1;", "Lio/ktor/server/auth/SessionAuthenticationProvider$Config;", "Lkotlin/ExtensionFunctionType;", "SessionAuthChallengeFunction", "Lkotlin/Function3;", "Lio/ktor/server/auth/SessionChallengeContext;", "Lkotlin/coroutines/Continuation;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionAuth.kt */
public final class SessionAuthKt {
    public static final String SessionAuthChallengeKey = "SessionAuth";

    public static /* synthetic */ void session$default(AuthenticationConfig authenticationConfig, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        SessionAuthenticationProvider.Config config = new SessionAuthenticationProvider.Config(str, Reflection.getOrCreateKotlinClass(Object.class));
        SessionAuthenticationProvider.Config config2 = config;
        Intrinsics.needClassReification();
        config.validate(new SessionAuthKt$session$1$1((Continuation<? super SessionAuthKt$session$1$1>) null));
        authenticationConfig.register(config.buildProvider());
    }

    public static /* synthetic */ void session$default(AuthenticationConfig authenticationConfig, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configure");
        Intrinsics.reifiedOperationMarker(4, "T");
        SessionAuthenticationProvider.Config config = new SessionAuthenticationProvider.Config(str, Reflection.getOrCreateKotlinClass(Object.class));
        function1.invoke(config);
        SessionAuthenticationProvider.Config config2 = config;
        authenticationConfig.register(config.buildProvider());
    }

    public static final /* synthetic */ <T> void session(AuthenticationConfig authenticationConfig, String str, Function1<? super SessionAuthenticationProvider.Config<T>, Unit> function1) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configure");
        Intrinsics.reifiedOperationMarker(4, "T");
        SessionAuthenticationProvider.Config config = new SessionAuthenticationProvider.Config(str, Reflection.getOrCreateKotlinClass(Object.class));
        function1.invoke(config);
        SessionAuthenticationProvider.Config config2 = config;
        authenticationConfig.register(config.buildProvider());
    }

    public static final /* synthetic */ <T extends Principal> void session(AuthenticationConfig authenticationConfig, String str) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        SessionAuthenticationProvider.Config config = new SessionAuthenticationProvider.Config(str, Reflection.getOrCreateKotlinClass(Object.class));
        SessionAuthenticationProvider.Config config2 = config;
        Intrinsics.needClassReification();
        config.validate(new SessionAuthKt$session$1$1((Continuation<? super SessionAuthKt$session$1$1>) null));
        authenticationConfig.register(config.buildProvider());
    }
}
