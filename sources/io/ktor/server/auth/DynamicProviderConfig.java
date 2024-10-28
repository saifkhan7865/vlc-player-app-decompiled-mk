package io.ktor.server.auth;

import io.ktor.server.auth.AuthenticationProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u000b\u001a\u00020\n2!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0006J\r\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fR)\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0006X.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/ktor/server/auth/DynamicProviderConfig;", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "name", "", "(Ljava/lang/String;)V", "authenticateFunction", "Lkotlin/Function1;", "Lio/ktor/server/auth/AuthenticationContext;", "Lkotlin/ParameterName;", "context", "", "authenticate", "block", "buildProvider", "Lio/ktor/server/auth/AuthenticationProvider;", "buildProvider$ktor_server_auth", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DynamicProviderConfig.kt */
public final class DynamicProviderConfig extends AuthenticationProvider.Config {
    /* access modifiers changed from: private */
    public Function1<? super AuthenticationContext, Unit> authenticateFunction;

    public DynamicProviderConfig(String str) {
        super(str);
    }

    public final void authenticate(Function1<? super AuthenticationContext, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        this.authenticateFunction = function1;
    }

    public final AuthenticationProvider buildProvider$ktor_server_auth() {
        if (this.authenticateFunction != null) {
            return new DynamicProviderConfig$buildProvider$3(this);
        }
        throw new IllegalStateException("Please configure authentication by calling authenticate() function".toString());
    }
}
