package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR'\u0010\t\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0002\b\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR+\u0010\u0010\u001a\u0019\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lio/ktor/server/auth/OAuthAuthenticationProvider;", "Lio/ktor/server/auth/AuthenticationProvider;", "config", "Lio/ktor/server/auth/OAuthAuthenticationProvider$Config;", "(Lio/ktor/server/auth/OAuthAuthenticationProvider$Config;)V", "client", "Lio/ktor/client/HttpClient;", "getClient$ktor_server_auth", "()Lio/ktor/client/HttpClient;", "providerLookup", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/OAuthServerSettings;", "Lkotlin/ExtensionFunctionType;", "getProviderLookup$ktor_server_auth", "()Lkotlin/jvm/functions/Function1;", "urlProvider", "Lkotlin/Function2;", "", "getUrlProvider$ktor_server_auth", "()Lkotlin/jvm/functions/Function2;", "onAuthenticate", "", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuthProcedure.kt */
public final class OAuthAuthenticationProvider extends AuthenticationProvider {
    private final HttpClient client;
    private final Function1<ApplicationCall, OAuthServerSettings> providerLookup;
    private final Function2<ApplicationCall, OAuthServerSettings, String> urlProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OAuthAuthenticationProvider(Config config) {
        super(config);
        Intrinsics.checkNotNullParameter(config, "config");
        this.client = config.getClient();
        this.providerLookup = config.getProviderLookup();
        this.urlProvider = config.getUrlProvider();
    }

    public final HttpClient getClient$ktor_server_auth() {
        return this.client;
    }

    public final Function1<ApplicationCall, OAuthServerSettings> getProviderLookup$ktor_server_auth() {
        return this.providerLookup;
    }

    public final Function2<ApplicationCall, OAuthServerSettings, String> getUrlProvider$ktor_server_auth() {
        return this.urlProvider;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object onAuthenticate(io.ktor.server.auth.AuthenticationContext r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.server.auth.OAuthAuthenticationProvider$onAuthenticate$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.auth.OAuthAuthenticationProvider$onAuthenticate$1 r0 = (io.ktor.server.auth.OAuthAuthenticationProvider$onAuthenticate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.OAuthAuthenticationProvider$onAuthenticate$1 r0 = new io.ktor.server.auth.OAuthAuthenticationProvider$onAuthenticate$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0070
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            java.lang.Object r6 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r6 = (io.ktor.server.auth.AuthenticationContext) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.server.auth.OAuthAuthenticationProvider r2 = (io.ktor.server.auth.OAuthAuthenticationProvider) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005e
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            io.ktor.util.PlatformUtils r7 = io.ktor.util.PlatformUtils.INSTANCE
            boolean r7 = r7.getIS_JVM()
            if (r7 == 0) goto L_0x005d
            java.lang.String r7 = r5.getName()
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = io.ktor.server.auth.OAuthKt.oauth1a(r5, r7, r6, r0)
            if (r7 != r1) goto L_0x005d
            return r1
        L_0x005d:
            r2 = r5
        L_0x005e:
            java.lang.String r7 = r2.getName()
            r4 = 0
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r6 = io.ktor.server.auth.OAuthProcedureKt.oauth2(r2, r7, r6, r0)
            if (r6 != r1) goto L_0x0070
            return r1
        L_0x0070:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthAuthenticationProvider.onAuthenticate(io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cR\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR-\u0010\u000b\u001a\u0015\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f¢\u0006\u0002\b\u000fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R1\u0010\u0014\u001a\u0019\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0015¢\u0006\u0002\b\u000fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lio/ktor/server/auth/OAuthAuthenticationProvider$Config;", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "name", "", "(Ljava/lang/String;)V", "client", "Lio/ktor/client/HttpClient;", "getClient", "()Lio/ktor/client/HttpClient;", "setClient", "(Lio/ktor/client/HttpClient;)V", "providerLookup", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/OAuthServerSettings;", "Lkotlin/ExtensionFunctionType;", "getProviderLookup", "()Lkotlin/jvm/functions/Function1;", "setProviderLookup", "(Lkotlin/jvm/functions/Function1;)V", "urlProvider", "Lkotlin/Function2;", "getUrlProvider", "()Lkotlin/jvm/functions/Function2;", "setUrlProvider", "(Lkotlin/jvm/functions/Function2;)V", "build", "Lio/ktor/server/auth/OAuthAuthenticationProvider;", "build$ktor_server_auth", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuthProcedure.kt */
    public static final class Config extends AuthenticationProvider.Config {
        public HttpClient client;
        public Function1<? super ApplicationCall, ? extends OAuthServerSettings> providerLookup;
        public Function2<? super ApplicationCall, ? super OAuthServerSettings, String> urlProvider;

        public Config(String str) {
            super(str);
        }

        public final HttpClient getClient() {
            HttpClient httpClient = this.client;
            if (httpClient != null) {
                return httpClient;
            }
            Intrinsics.throwUninitializedPropertyAccessException("client");
            return null;
        }

        public final void setClient(HttpClient httpClient) {
            Intrinsics.checkNotNullParameter(httpClient, "<set-?>");
            this.client = httpClient;
        }

        public final Function1<ApplicationCall, OAuthServerSettings> getProviderLookup() {
            Function1<? super ApplicationCall, ? extends OAuthServerSettings> function1 = this.providerLookup;
            if (function1 != null) {
                return function1;
            }
            Intrinsics.throwUninitializedPropertyAccessException("providerLookup");
            return null;
        }

        public final void setProviderLookup(Function1<? super ApplicationCall, ? extends OAuthServerSettings> function1) {
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            this.providerLookup = function1;
        }

        public final Function2<ApplicationCall, OAuthServerSettings, String> getUrlProvider() {
            Function2<? super ApplicationCall, ? super OAuthServerSettings, String> function2 = this.urlProvider;
            if (function2 != null) {
                return function2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("urlProvider");
            return null;
        }

        public final void setUrlProvider(Function2<? super ApplicationCall, ? super OAuthServerSettings, String> function2) {
            Intrinsics.checkNotNullParameter(function2, "<set-?>");
            this.urlProvider = function2;
        }

        public final OAuthAuthenticationProvider build$ktor_server_auth() {
            return new OAuthAuthenticationProvider(this);
        }
    }
}
