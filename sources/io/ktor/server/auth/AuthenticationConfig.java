package io.ktor.server.auth;

import io.ktor.util.KtorDsl;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\r\u0010\n\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u000bJ+\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0012J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0005J\u0012\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0002R\"\u0010\u0002\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lio/ktor/server/auth/AuthenticationConfig;", "", "providers", "", "", "Lio/ktor/server/auth/AuthenticationProvider;", "(Ljava/util/Map;)V", "", "getProviders$ktor_server_auth", "()Ljava/util/Map;", "copy", "copy$ktor_server_auth", "provider", "", "name", "configure", "Lkotlin/Function1;", "Lio/ktor/server/auth/DynamicProviderConfig;", "Lkotlin/ExtensionFunctionType;", "register", "requireProviderNotRegistered", "providerName", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: Authentication.kt */
public final class AuthenticationConfig {
    private final Map<String, AuthenticationProvider> providers;

    public AuthenticationConfig() {
        this((Map) null, 1, (DefaultConstructorMarker) null);
    }

    public AuthenticationConfig(Map<String, ? extends AuthenticationProvider> map) {
        Intrinsics.checkNotNullParameter(map, "providers");
        this.providers = MapsKt.toMutableMap(map);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AuthenticationConfig(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, AuthenticationProvider> getProviders$ktor_server_auth() {
        return this.providers;
    }

    public static /* synthetic */ void provider$default(AuthenticationConfig authenticationConfig, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        authenticationConfig.provider(str, function1);
    }

    public final void provider(String str, Function1<? super DynamicProviderConfig, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "configure");
        requireProviderNotRegistered(str);
        DynamicProviderConfig dynamicProviderConfig = new DynamicProviderConfig(str);
        function1.invoke(dynamicProviderConfig);
        AuthenticationProvider buildProvider$ktor_server_auth = dynamicProviderConfig.buildProvider$ktor_server_auth();
        this.providers.put(buildProvider$ktor_server_auth.getName(), buildProvider$ktor_server_auth);
    }

    public final void register(AuthenticationProvider authenticationProvider) {
        Intrinsics.checkNotNullParameter(authenticationProvider, "provider");
        requireProviderNotRegistered(authenticationProvider.getName());
        this.providers.put(authenticationProvider.getName(), authenticationProvider);
    }

    private final void requireProviderNotRegistered(String str) {
        if (this.providers.containsKey(str)) {
            throw new IllegalArgumentException("Provider with the name " + str + " is already registered");
        }
    }

    public final AuthenticationConfig copy$ktor_server_auth() {
        return new AuthenticationConfig(this.providers);
    }
}
