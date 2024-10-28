package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationFailedCause;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "challenge", "Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt$oauth1a$2", f = "OAuth.kt", i = {0, 0, 1}, l = {37, 38}, m = "invokeSuspend", n = {"challenge", "call", "challenge"}, s = {"L$0", "L$1", "L$0"})
/* compiled from: OAuth.kt */
final class OAuthKt$oauth1a$2 extends SuspendLambda implements Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ AuthenticationContext $context;
    final /* synthetic */ OAuthServerSettings $provider;
    final /* synthetic */ OAuthAuthenticationProvider $this_oauth1a;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuthKt$oauth1a$2(OAuthAuthenticationProvider oAuthAuthenticationProvider, OAuthServerSettings oAuthServerSettings, AuthenticationContext authenticationContext, Continuation<? super OAuthKt$oauth1a$2> continuation) {
        super(3, continuation);
        this.$this_oauth1a = oAuthAuthenticationProvider;
        this.$provider = oAuthServerSettings;
        this.$context = authenticationContext;
    }

    public final Object invoke(AuthenticationProcedureChallenge authenticationProcedureChallenge, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        OAuthKt$oauth1a$2 oAuthKt$oauth1a$2 = new OAuthKt$oauth1a$2(this.$this_oauth1a, this.$provider, this.$context, continuation);
        oAuthKt$oauth1a$2.L$0 = authenticationProcedureChallenge;
        oAuthKt$oauth1a$2.L$1 = applicationCall;
        return oAuthKt$oauth1a$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        AuthenticationProcedureChallenge authenticationProcedureChallenge;
        AuthenticationProcedureChallenge authenticationProcedureChallenge2;
        ApplicationCall applicationCall;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AuthenticationProcedureChallenge authenticationProcedureChallenge3 = (AuthenticationProcedureChallenge) this.L$0;
            applicationCall = (ApplicationCall) this.L$1;
            this.L$0 = authenticationProcedureChallenge3;
            this.L$1 = applicationCall;
            this.label = 1;
            Object simpleOAuth1aStep1$default = OAuth1aKt.simpleOAuth1aStep1$default(this.$this_oauth1a.getClient$ktor_server_auth(), (OAuthServerSettings.OAuth1aServerSettings) this.$provider, this.$this_oauth1a.getUrlProvider$ktor_server_auth().invoke(applicationCall, this.$provider), (String) null, (List) null, this, 24, (Object) null);
            if (simpleOAuth1aStep1$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj2 = simpleOAuth1aStep1$default;
            authenticationProcedureChallenge2 = authenticationProcedureChallenge3;
            obj = obj2;
        } else if (i == 1) {
            applicationCall = (ApplicationCall) this.L$1;
            authenticationProcedureChallenge2 = (AuthenticationProcedureChallenge) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            authenticationProcedureChallenge = (AuthenticationProcedureChallenge) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                authenticationProcedureChallenge.complete();
            } catch (IOException e) {
                AuthenticationContext authenticationContext = this.$context;
                Object oAuthKey = OAuthProcedureKt.getOAuthKey();
                String message = e.getMessage();
                if (message == null) {
                    message = "IOException";
                }
                authenticationContext.error(oAuthKey, new AuthenticationFailedCause.Error(message));
            }
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        OAuthServerSettings.OAuth1aServerSettings oAuth1aServerSettings = (OAuthServerSettings.OAuth1aServerSettings) this.$provider;
        this.L$0 = authenticationProcedureChallenge2;
        this.L$1 = null;
        this.label = 2;
        if (OAuth1aKt.redirectAuthenticateOAuth1a(applicationCall, oAuth1aServerSettings, (OAuthCallback.TokenPair) obj, (Continuation<? super Unit>) this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        authenticationProcedureChallenge = authenticationProcedureChallenge2;
        authenticationProcedureChallenge.complete();
        return Unit.INSTANCE;
    }
}
