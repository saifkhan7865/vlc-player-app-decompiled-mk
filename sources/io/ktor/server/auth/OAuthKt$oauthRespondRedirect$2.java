package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import io.ktor.util.pipeline.PipelineContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt$oauthRespondRedirect$2", f = "OAuth.kt", i = {}, l = {95, 96}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OAuth.kt */
final class OAuthKt$oauthRespondRedirect$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $callbackUrl;
    final /* synthetic */ HttpClient $client;
    final /* synthetic */ OAuthServerSettings $provider;
    final /* synthetic */ PipelineContext<Unit, ApplicationCall> $this_oauthRespondRedirect;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuthKt$oauthRespondRedirect$2(HttpClient httpClient, OAuthServerSettings oAuthServerSettings, String str, PipelineContext<Unit, ApplicationCall> pipelineContext, Continuation<? super OAuthKt$oauthRespondRedirect$2> continuation) {
        super(2, continuation);
        this.$client = httpClient;
        this.$provider = oAuthServerSettings;
        this.$callbackUrl = str;
        this.$this_oauthRespondRedirect = pipelineContext;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OAuthKt$oauthRespondRedirect$2(this.$client, this.$provider, this.$callbackUrl, this.$this_oauthRespondRedirect, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OAuthKt$oauthRespondRedirect$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HttpClient httpClient = this.$client;
            this.label = 1;
            obj = OAuth1aKt.simpleOAuth1aStep1$default(httpClient, (OAuthServerSettings.OAuth1aServerSettings) this.$provider, this.$callbackUrl, (String) null, (List) null, this, 24, (Object) null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ApplicationCall context = this.$this_oauthRespondRedirect.getContext();
        OAuthServerSettings.OAuth1aServerSettings oAuth1aServerSettings = (OAuthServerSettings.OAuth1aServerSettings) this.$provider;
        this.label = 2;
        if (OAuth1aKt.redirectAuthenticateOAuth1a(context, oAuth1aServerSettings, (OAuthCallback.TokenPair) obj, (Continuation<? super Unit>) this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
