package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuth2Kt$oauth2$2", f = "OAuth2.kt", i = {}, l = {49, 56, 53}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OAuth2.kt */
final class OAuth2Kt$oauth2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $callbackRedirectUrl;
    final /* synthetic */ HttpClient $client;
    final /* synthetic */ OAuthServerSettings $provider;
    final /* synthetic */ PipelineContext<Unit, ApplicationCall> $this_oauth2;
    final /* synthetic */ OAuthCallback.TokenSingle $token;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuth2Kt$oauth2$2(HttpClient httpClient, OAuthServerSettings oAuthServerSettings, String str, OAuthCallback.TokenSingle tokenSingle, PipelineContext<Unit, ApplicationCall> pipelineContext, Continuation<? super OAuth2Kt$oauth2$2> continuation) {
        super(2, continuation);
        this.$client = httpClient;
        this.$provider = oAuthServerSettings;
        this.$callbackRedirectUrl = str;
        this.$token = tokenSingle;
        this.$this_oauth2 = pipelineContext;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OAuth2Kt$oauth2$2(this.$client, this.$provider, this.$callbackRedirectUrl, this.$token, this.$this_oauth2, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OAuth2Kt$oauth2$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: type inference failed for: r15v18, types: [io.ktor.server.auth.OAuthServerSettings] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0038
            if (r1 == r4) goto L_0x0032
            if (r1 == r3) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00ca
        L_0x0016:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x001e:
            java.lang.Object r1 = r14.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r14.L$1
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r3 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r3
            java.lang.Object r4 = r14.L$0
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            kotlin.ResultKt.throwOnFailure(r15)
            r5 = r1
            r13 = r4
            r4 = r3
            r3 = r13
            goto L_0x00a0
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ InvalidGrant -> 0x0036 }
            goto L_0x0057
        L_0x0036:
            r15 = move-exception
            goto L_0x006b
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r15)
            io.ktor.client.HttpClient r15 = r14.$client     // Catch:{ InvalidGrant -> 0x0036 }
            io.ktor.server.auth.OAuthServerSettings r1 = r14.$provider     // Catch:{ InvalidGrant -> 0x0036 }
            r5 = r1
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r5 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r5     // Catch:{ InvalidGrant -> 0x0036 }
            java.lang.String r6 = r14.$callbackRedirectUrl     // Catch:{ InvalidGrant -> 0x0036 }
            io.ktor.server.auth.OAuthCallback$TokenSingle r7 = r14.$token     // Catch:{ InvalidGrant -> 0x0036 }
            r9 = r14
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch:{ InvalidGrant -> 0x0036 }
            r14.label = r4     // Catch:{ InvalidGrant -> 0x0036 }
            r8 = 0
            r10 = 16
            r11 = 0
            r4 = r15
            java.lang.Object r15 = io.ktor.server.auth.OAuth2Kt.oauth2RequestAccessToken$default(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ InvalidGrant -> 0x0036 }
            if (r15 != r0) goto L_0x0057
            return r0
        L_0x0057:
            io.ktor.server.auth.OAuthAccessTokenResponse$OAuth2 r15 = (io.ktor.server.auth.OAuthAccessTokenResponse.OAuth2) r15     // Catch:{ InvalidGrant -> 0x0036 }
            io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r1 = r14.$this_oauth2     // Catch:{ InvalidGrant -> 0x0036 }
            java.lang.Object r1 = r1.getContext()     // Catch:{ InvalidGrant -> 0x0036 }
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1     // Catch:{ InvalidGrant -> 0x0036 }
            io.ktor.server.auth.AuthenticationContext r1 = io.ktor.server.auth.AuthenticationKt.getAuthentication(r1)     // Catch:{ InvalidGrant -> 0x0036 }
            io.ktor.server.auth.Principal r15 = (io.ktor.server.auth.Principal) r15     // Catch:{ InvalidGrant -> 0x0036 }
            r1.principal((io.ktor.server.auth.Principal) r15)     // Catch:{ InvalidGrant -> 0x0036 }
            goto L_0x00ca
        L_0x006b:
            org.slf4j.Logger r1 = io.ktor.server.auth.OAuth2Kt.Logger
            java.lang.String r4 = "Redirected to OAuth2 server due to error invalid_grant: {}"
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            r1.trace((java.lang.String) r4, (java.lang.Throwable) r15)
            io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r15 = r14.$this_oauth2
            java.lang.Object r15 = r15.getContext()
            r4 = r15
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            io.ktor.server.auth.OAuthServerSettings r15 = r14.$provider
            r1 = r15
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r1 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r1
            java.lang.String r5 = r14.$callbackRedirectUrl
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r15 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r15
            io.ktor.util.NonceManager r15 = r15.getNonceManager()
            r6 = r14
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r14.L$0 = r4
            r14.L$1 = r1
            r14.L$2 = r5
            r14.label = r3
            java.lang.Object r15 = r15.newNonce(r6)
            if (r15 != r0) goto L_0x009e
            return r0
        L_0x009e:
            r3 = r4
            r4 = r1
        L_0x00a0:
            r6 = r15
            java.lang.String r6 = (java.lang.String) r6
            io.ktor.server.auth.OAuthServerSettings r15 = r14.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r15 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r15
            java.util.List r8 = r15.getDefaultScopes()
            io.ktor.server.auth.OAuthServerSettings r15 = r14.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r15 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r15
            kotlin.jvm.functions.Function1 r9 = r15.getAuthorizeUrlInterceptor()
            r10 = r14
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r15 = 0
            r14.L$0 = r15
            r14.L$1 = r15
            r14.L$2 = r15
            r14.label = r2
            r7 = 0
            r11 = 8
            r12 = 0
            java.lang.Object r15 = io.ktor.server.auth.OAuth2Kt.redirectAuthenticateOAuth2$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            if (r15 != r0) goto L_0x00ca
            return r0
        L_0x00ca:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuth2Kt$oauth2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
