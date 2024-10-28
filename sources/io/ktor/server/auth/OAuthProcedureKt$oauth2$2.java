package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "challenge", "Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthProcedureKt$oauth2$2", f = "OAuthProcedure.kt", i = {0, 0, 1, 1, 1, 2}, l = {89, 90, 91}, m = "invokeSuspend", n = {"challenge", "call", "challenge", "call", "state", "challenge"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0"})
/* compiled from: OAuthProcedure.kt */
final class OAuthProcedureKt$oauth2$2 extends SuspendLambda implements Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $callbackRedirectUrl;
    final /* synthetic */ OAuthServerSettings $provider;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuthProcedureKt$oauth2$2(OAuthServerSettings oAuthServerSettings, String str, Continuation<? super OAuthProcedureKt$oauth2$2> continuation) {
        super(3, continuation);
        this.$provider = oAuthServerSettings;
        this.$callbackRedirectUrl = str;
    }

    public final Object invoke(AuthenticationProcedureChallenge authenticationProcedureChallenge, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        OAuthProcedureKt$oauth2$2 oAuthProcedureKt$oauth2$2 = new OAuthProcedureKt$oauth2$2(this.$provider, this.$callbackRedirectUrl, continuation);
        oAuthProcedureKt$oauth2$2.L$0 = authenticationProcedureChallenge;
        oAuthProcedureKt$oauth2$2.L$1 = applicationCall;
        return oAuthProcedureKt$oauth2$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00b2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0040
            if (r1 == r4) goto L_0x0034
            if (r1 == r3) goto L_0x0022
            if (r1 != r2) goto L_0x001a
            java.lang.Object r0 = r14.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r0 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r0
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00b4
        L_0x001a:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0022:
            java.lang.Object r1 = r14.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r14.L$1
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3
            java.lang.Object r4 = r14.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r4 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r4
            kotlin.ResultKt.throwOnFailure(r15)
            r8 = r1
            r5 = r3
            goto L_0x0081
        L_0x0034:
            java.lang.Object r1 = r14.L$1
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            java.lang.Object r4 = r14.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r4 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r4
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0066
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r15 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r15
            java.lang.Object r1 = r14.L$1
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            io.ktor.server.auth.OAuthServerSettings r5 = r14.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r5 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r5
            io.ktor.util.NonceManager r5 = r5.getNonceManager()
            r6 = r14
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r14.L$0 = r15
            r14.L$1 = r1
            r14.label = r4
            java.lang.Object r4 = r5.newNonce(r6)
            if (r4 != r0) goto L_0x0063
            return r0
        L_0x0063:
            r13 = r4
            r4 = r15
            r15 = r13
        L_0x0066:
            java.lang.String r15 = (java.lang.String) r15
            io.ktor.server.auth.OAuthServerSettings r5 = r14.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r5 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r5
            kotlin.jvm.functions.Function3 r5 = r5.getOnStateCreated()
            r14.L$0 = r4
            r14.L$1 = r1
            r14.L$2 = r15
            r14.label = r3
            java.lang.Object r3 = r5.invoke(r1, r15, r14)
            if (r3 != r0) goto L_0x007f
            return r0
        L_0x007f:
            r8 = r15
            r5 = r1
        L_0x0081:
            io.ktor.server.auth.OAuthServerSettings r15 = r14.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r15 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r15
            java.util.List r10 = r15.getDefaultScopes()
            io.ktor.server.auth.OAuthServerSettings r15 = r14.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r15 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r15
            java.util.List r9 = r15.getExtraAuthParameters()
            io.ktor.server.auth.OAuthServerSettings r15 = r14.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r15 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r15
            kotlin.jvm.functions.Function1 r11 = r15.getAuthorizeUrlInterceptor()
            io.ktor.server.auth.OAuthServerSettings r15 = r14.$provider
            r6 = r15
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r6 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r6
            java.lang.String r7 = r14.$callbackRedirectUrl
            r12 = r14
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r14.L$0 = r4
            r15 = 0
            r14.L$1 = r15
            r14.L$2 = r15
            r14.label = r2
            java.lang.Object r15 = io.ktor.server.auth.OAuth2Kt.redirectAuthenticateOAuth2(r5, r6, r7, r8, r9, r10, r11, r12)
            if (r15 != r0) goto L_0x00b3
            return r0
        L_0x00b3:
            r0 = r4
        L_0x00b4:
            r0.complete()
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthProcedureKt$oauth2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
