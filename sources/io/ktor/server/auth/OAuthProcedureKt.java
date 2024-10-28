package io.ktor.server.auth;

import io.ktor.http.auth.AuthScheme;
import io.ktor.server.auth.OAuthAuthenticationProvider;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0007\u001a\u00020\b*\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b0\r¢\u0006\u0002\b\u000f\u001a'\u0010\u0010\u001a\u00020\b*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aA\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u001d\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Logger", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "OAuthKey", "", "getOAuthKey", "()Ljava/lang/Object;", "oauth", "", "Lio/ktor/server/auth/AuthenticationConfig;", "name", "", "configure", "Lkotlin/Function1;", "Lio/ktor/server/auth/OAuthAuthenticationProvider$Config;", "Lkotlin/ExtensionFunctionType;", "oauth2", "Lio/ktor/server/auth/OAuthAuthenticationProvider;", "authProviderName", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/OAuthAuthenticationProvider;Ljava/lang/String;Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauth2RequestToken", "Lio/ktor/server/auth/AuthenticationFailedCause$InvalidCredentials;", "provider", "Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;", "callbackRedirectUrl", "token", "Lio/ktor/server/auth/OAuthCallback$TokenSingle;", "(Lio/ktor/server/auth/OAuthAuthenticationProvider;Ljava/lang/String;Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;Ljava/lang/String;Lio/ktor/server/auth/OAuthCallback$TokenSingle;Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuthProcedure.kt */
public final class OAuthProcedureKt {
    private static final Logger Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.auth.oauth");
    private static final Object OAuthKey = AuthScheme.OAuth;

    public static final Object getOAuthKey() {
        return OAuthKey;
    }

    public static /* synthetic */ void oauth$default(AuthenticationConfig authenticationConfig, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        oauth(authenticationConfig, str, function1);
    }

    public static final void oauth(AuthenticationConfig authenticationConfig, String str, Function1<? super OAuthAuthenticationProvider.Config, Unit> function1) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configure");
        OAuthAuthenticationProvider.Config config = new OAuthAuthenticationProvider.Config(str);
        function1.invoke(config);
        authenticationConfig.register(config.build$ktor_server_auth());
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth2(io.ktor.server.auth.OAuthAuthenticationProvider r11, java.lang.String r12, io.ktor.server.auth.AuthenticationContext r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            boolean r0 = r14 instanceof io.ktor.server.auth.OAuthProcedureKt$oauth2$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ktor.server.auth.OAuthProcedureKt$oauth2$1 r0 = (io.ktor.server.auth.OAuthProcedureKt$oauth2$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.OAuthProcedureKt$oauth2$1 r0 = new io.ktor.server.auth.OAuthProcedureKt$oauth2$1
            r0.<init>(r14)
        L_0x0019:
            r7 = r0
            java.lang.Object r14 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            r8 = 0
            if (r1 == 0) goto L_0x005f
            if (r1 == r3) goto L_0x0044
            if (r1 != r2) goto L_0x003c
            java.lang.Object r11 = r7.L$2
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r7.L$1
            io.ktor.server.auth.OAuthServerSettings r12 = (io.ktor.server.auth.OAuthServerSettings) r12
            java.lang.Object r13 = r7.L$0
            io.ktor.server.auth.AuthenticationContext r13 = (io.ktor.server.auth.AuthenticationContext) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00c2
        L_0x003c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0044:
            java.lang.Object r11 = r7.L$4
            io.ktor.server.auth.OAuthServerSettings r11 = (io.ktor.server.auth.OAuthServerSettings) r11
            java.lang.Object r12 = r7.L$3
            io.ktor.server.application.ApplicationCall r12 = (io.ktor.server.application.ApplicationCall) r12
            java.lang.Object r13 = r7.L$2
            io.ktor.server.auth.AuthenticationContext r13 = (io.ktor.server.auth.AuthenticationContext) r13
            java.lang.Object r1 = r7.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r7.L$0
            io.ktor.server.auth.OAuthAuthenticationProvider r3 = (io.ktor.server.auth.OAuthAuthenticationProvider) r3
            kotlin.ResultKt.throwOnFailure(r14)
            r9 = r3
            r3 = r1
            r1 = r9
            goto L_0x0091
        L_0x005f:
            kotlin.ResultKt.throwOnFailure(r14)
            io.ktor.server.application.ApplicationCall r14 = r13.getCall()
            kotlin.jvm.functions.Function1 r1 = r11.getProviderLookup$ktor_server_auth()
            java.lang.Object r1 = r1.invoke(r14)
            io.ktor.server.auth.OAuthServerSettings r1 = (io.ktor.server.auth.OAuthServerSettings) r1
            boolean r4 = r1 instanceof io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings
            if (r4 != 0) goto L_0x0077
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0077:
            r7.L$0 = r11
            r7.L$1 = r12
            r7.L$2 = r13
            r7.L$3 = r14
            r7.L$4 = r1
            r7.label = r3
            java.lang.Object r3 = io.ktor.server.auth.OAuth2Kt.oauth2HandleCallback(r14, r7)
            if (r3 != r0) goto L_0x008a
            return r0
        L_0x008a:
            r9 = r1
            r1 = r11
            r11 = r9
            r10 = r3
            r3 = r12
            r12 = r14
            r14 = r10
        L_0x0091:
            r5 = r14
            io.ktor.server.auth.OAuthCallback$TokenSingle r5 = (io.ktor.server.auth.OAuthCallback.TokenSingle) r5
            kotlin.jvm.functions.Function2 r14 = r1.getUrlProvider$ktor_server_auth()
            java.lang.Object r12 = r14.invoke(r12, r11)
            java.lang.String r12 = (java.lang.String) r12
            if (r5 != 0) goto L_0x00a5
            io.ktor.server.auth.AuthenticationFailedCause$NoCredentials r14 = io.ktor.server.auth.AuthenticationFailedCause.NoCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r14 = (io.ktor.server.auth.AuthenticationFailedCause) r14
            goto L_0x00c7
        L_0x00a5:
            r14 = r11
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r14 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r14
            r7.L$0 = r13
            r7.L$1 = r11
            r7.L$2 = r12
            r7.L$3 = r8
            r7.L$4 = r8
            r7.label = r2
            r2 = r3
            r3 = r14
            r4 = r12
            r6 = r13
            java.lang.Object r14 = oauth2RequestToken(r1, r2, r3, r4, r5, r6, r7)
            if (r14 != r0) goto L_0x00bf
            return r0
        L_0x00bf:
            r9 = r12
            r12 = r11
            r11 = r9
        L_0x00c2:
            io.ktor.server.auth.AuthenticationFailedCause r14 = (io.ktor.server.auth.AuthenticationFailedCause) r14
            r9 = r12
            r12 = r11
            r11 = r9
        L_0x00c7:
            if (r14 != 0) goto L_0x00cc
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00cc:
            java.lang.Object r0 = OAuthKey
            io.ktor.server.auth.OAuthProcedureKt$oauth2$2 r1 = new io.ktor.server.auth.OAuthProcedureKt$oauth2$2
            r1.<init>(r11, r12, r8)
            kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1
            r13.challenge(r0, r14, r1)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthProcedureKt.oauth2(io.ktor.server.auth.OAuthAuthenticationProvider, java.lang.String, io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0070, code lost:
        r11 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0094, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0095, code lost:
        Logger.trace("OAuth invalid grant reported: {}", (java.lang.Throwable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:10:0x0032, B:18:0x0048] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:10:0x0032, B:21:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0094 A[ExcHandler: InvalidGrant (r0v2 'e' io.ktor.server.auth.OAuth2Exception$InvalidGrant A[CUSTOM_DECLARE]), Splitter:B:10:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth2RequestToken(io.ktor.server.auth.OAuthAuthenticationProvider r13, java.lang.String r14, io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings r15, java.lang.String r16, io.ktor.server.auth.OAuthCallback.TokenSingle r17, io.ktor.server.auth.AuthenticationContext r18, kotlin.coroutines.Continuation<? super io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof io.ktor.server.auth.OAuthProcedureKt$oauth2RequestToken$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.server.auth.OAuthProcedureKt$oauth2RequestToken$1 r1 = (io.ktor.server.auth.OAuthProcedureKt$oauth2RequestToken$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.server.auth.OAuthProcedureKt$oauth2RequestToken$1 r1 = new io.ktor.server.auth.OAuthProcedureKt$oauth2RequestToken$1
            r1.<init>(r0)
        L_0x001b:
            r7 = r1
            java.lang.Object r0 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            r10 = 0
            r3 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r3) goto L_0x003d
            java.lang.Object r1 = r7.L$1
            io.ktor.server.auth.AuthenticationContext r1 = (io.ktor.server.auth.AuthenticationContext) r1
            java.lang.Object r2 = r7.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ InvalidGrant -> 0x0094, all -> 0x003a }
            r11 = r1
            r12 = r2
            r2 = r0
            r0 = r12
            goto L_0x0065
        L_0x003a:
            r0 = move-exception
            r11 = r1
            goto L_0x0072
        L_0x003d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.client.HttpClient r2 = r13.getClient$ktor_server_auth()     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006f }
            r0 = r14
            r7.L$0 = r0     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006f }
            r11 = r18
            r7.L$1 = r11     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006d }
            r7.label = r3     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006d }
            r6 = 0
            r8 = 16
            r9 = 0
            r3 = r15
            r4 = r16
            r5 = r17
            java.lang.Object r2 = io.ktor.server.auth.OAuth2Kt.oauth2RequestAccessToken$default(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006d }
            if (r2 != r1) goto L_0x0065
            return r1
        L_0x0065:
            io.ktor.server.auth.OAuthAccessTokenResponse$OAuth2 r2 = (io.ktor.server.auth.OAuthAccessTokenResponse.OAuth2) r2     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006d }
            io.ktor.server.auth.Principal r2 = (io.ktor.server.auth.Principal) r2     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006d }
            r11.principal((java.lang.String) r0, (io.ktor.server.auth.Principal) r2)     // Catch:{ InvalidGrant -> 0x0094, all -> 0x006d }
            goto L_0x00a0
        L_0x006d:
            r0 = move-exception
            goto L_0x0072
        L_0x006f:
            r0 = move-exception
            r11 = r18
        L_0x0072:
            org.slf4j.Logger r1 = Logger
            java.lang.String r2 = "OAuth2 request access token failed"
            r1.trace((java.lang.String) r2, (java.lang.Throwable) r0)
            java.lang.Object r1 = OAuthKey
            io.ktor.server.auth.AuthenticationFailedCause$Error r2 = new io.ktor.server.auth.AuthenticationFailedCause$Error
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Failed to request OAuth2 access token due to "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            io.ktor.server.auth.AuthenticationFailedCause r2 = (io.ktor.server.auth.AuthenticationFailedCause) r2
            r11.error(r1, r2)
            goto L_0x00a0
        L_0x0094:
            r0 = move-exception
            org.slf4j.Logger r1 = Logger
            java.lang.String r2 = "OAuth invalid grant reported: {}"
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1.trace((java.lang.String) r2, (java.lang.Throwable) r0)
            io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r10 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
        L_0x00a0:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthProcedureKt.oauth2RequestToken(io.ktor.server.auth.OAuthAuthenticationProvider, java.lang.String, io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings, java.lang.String, io.ktor.server.auth.OAuthCallback$TokenSingle, io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
