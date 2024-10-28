package io.ktor.server.auth;

import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.util.pipeline.Pipeline;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\b\n\u001a \u0010\u000b\u001a\u0004\u0018\u0001H\f\"\n\b\u0000\u0010\f\u0018\u0001*\u00020\r*\u00020\u0002H\b¢\u0006\u0002\u0010\u000e\u001a*\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\n\b\u0000\u0010\f\u0018\u0001*\u00020\r*\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\b¢\u0006\u0002\u0010\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0012"}, d2 = {"authentication", "Lio/ktor/server/auth/AuthenticationContext;", "Lio/ktor/server/application/ApplicationCall;", "getAuthentication", "(Lio/ktor/server/application/ApplicationCall;)Lio/ktor/server/auth/AuthenticationContext;", "", "Lio/ktor/server/application/Application;", "block", "Lkotlin/Function1;", "Lio/ktor/server/auth/AuthenticationConfig;", "Lkotlin/ExtensionFunctionType;", "principal", "P", "Lio/ktor/server/auth/Principal;", "(Lio/ktor/server/application/ApplicationCall;)Lio/ktor/server/auth/Principal;", "provider", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;)Lio/ktor/server/auth/Principal;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Authentication.kt */
public final class AuthenticationKt {
    public static final AuthenticationContext getAuthentication(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        return AuthenticationContext.Companion.from$ktor_server_auth(applicationCall);
    }

    public static final /* synthetic */ <P extends Principal> P principal(ApplicationCall applicationCall, String str) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        AuthenticationContext authentication = getAuthentication(applicationCall);
        Intrinsics.reifiedOperationMarker(4, "P");
        return authentication.principal(str, Reflection.getOrCreateKotlinClass(Principal.class));
    }

    public static final void authentication(Application application, Function1<? super AuthenticationConfig, Unit> function1) {
        Intrinsics.checkNotNullParameter(application, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        Pipeline pipeline = application;
        Authentication authentication = (Authentication) ApplicationPluginKt.pluginOrNull(pipeline, Authentication.Companion);
        if (authentication != null) {
            authentication.configure(function1);
        } else {
            ApplicationPluginKt.install(pipeline, Authentication.Companion, function1);
        }
    }

    public static final /* synthetic */ <P extends Principal> P principal(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        AuthenticationContext authentication = getAuthentication(applicationCall);
        Intrinsics.reifiedOperationMarker(4, "P");
        return authentication.principal((String) null, Reflection.getOrCreateKotlinClass(Principal.class));
    }
}
