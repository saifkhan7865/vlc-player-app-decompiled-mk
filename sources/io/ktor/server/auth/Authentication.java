package io.ktor.server.auth;

import io.ktor.server.application.Application;
import io.ktor.server.application.BaseApplicationPlugin;
import io.ktor.util.AttributeKey;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\b\u001a\u00020\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u000e"}, d2 = {"Lio/ktor/server/auth/Authentication;", "", "config", "Lio/ktor/server/auth/AuthenticationConfig;", "(Lio/ktor/server/auth/AuthenticationConfig;)V", "getConfig$ktor_server_auth", "()Lio/ktor/server/auth/AuthenticationConfig;", "setConfig$ktor_server_auth", "configure", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Companion", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Authentication.kt */
public final class Authentication {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AttributeKey<Authentication> key = new AttributeKey<>("AuthenticationHolder");
    private AuthenticationConfig config;

    public Authentication(AuthenticationConfig authenticationConfig) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "config");
        this.config = authenticationConfig;
    }

    public final AuthenticationConfig getConfig$ktor_server_auth() {
        return this.config;
    }

    public final void setConfig$ktor_server_auth(AuthenticationConfig authenticationConfig) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "<set-?>");
        this.config = authenticationConfig;
    }

    public final void configure(Function1<? super AuthenticationConfig, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        AuthenticationConfig copy$ktor_server_auth = this.config.copy$ktor_server_auth();
        function1.invoke(copy$ktor_server_auth);
        this.config = copy$ktor_server_auth.copy$ktor_server_auth();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J)\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00022\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lio/ktor/server/auth/Authentication$Companion;", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/application/Application;", "Lio/ktor/server/auth/AuthenticationConfig;", "Lio/ktor/server/auth/Authentication;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "pipeline", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Authentication.kt */
    public static final class Companion implements BaseApplicationPlugin<Application, AuthenticationConfig, Authentication> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public AttributeKey<Authentication> getKey() {
            return Authentication.key;
        }

        public Authentication install(Application application, Function1<? super AuthenticationConfig, Unit> function1) {
            Intrinsics.checkNotNullParameter(application, "pipeline");
            Intrinsics.checkNotNullParameter(function1, "configure");
            AuthenticationConfig authenticationConfig = new AuthenticationConfig((Map) null, 1, (DefaultConstructorMarker) null);
            function1.invoke(authenticationConfig);
            return new Authentication(authenticationConfig);
        }
    }
}
