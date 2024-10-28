package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.OAuthAccessTokenResponse;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import io.ktor.util.pipeline.PipelineContext;
import java.io.IOException;
import java.util.Map;
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
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt$oauthHandleCallback$5", f = "OAuth.kt", i = {}, l = {153, 154, 156}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OAuth.kt */
final class OAuthKt$oauthHandleCallback$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<OAuthAccessTokenResponse, Continuation<? super Unit>, Object> $block;
    final /* synthetic */ HttpClient $client;
    final /* synthetic */ String $loginPageUrl;
    final /* synthetic */ OAuthServerSettings $provider;
    final /* synthetic */ PipelineContext<Unit, ApplicationCall> $this_oauthHandleCallback;
    final /* synthetic */ OAuthCallback.TokenPair $tokens;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuthKt$oauthHandleCallback$5(HttpClient httpClient, OAuthServerSettings oAuthServerSettings, OAuthCallback.TokenPair tokenPair, Function2<? super OAuthAccessTokenResponse, ? super Continuation<? super Unit>, ? extends Object> function2, PipelineContext<Unit, ApplicationCall> pipelineContext, String str, Continuation<? super OAuthKt$oauthHandleCallback$5> continuation) {
        super(2, continuation);
        this.$client = httpClient;
        this.$provider = oAuthServerSettings;
        this.$tokens = tokenPair;
        this.$block = function2;
        this.$this_oauthHandleCallback = pipelineContext;
        this.$loginPageUrl = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OAuthKt$oauthHandleCallback$5(this.$client, this.$provider, this.$tokens, this.$block, this.$this_oauthHandleCallback, this.$loginPageUrl, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OAuthKt$oauthHandleCallback$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HttpClient httpClient = this.$client;
            this.label = 1;
            obj = OAuth1aKt.requestOAuth1aAccessToken$default(httpClient, (OAuthServerSettings.OAuth1aServerSettings) this.$provider, this.$tokens, (String) null, (Map) null, this, 24, (Object) null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            if (i == 2) {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (IOException unused) {
                    this.label = 3;
                    if (OAuthKt.oauthHandleFail(this.$this_oauthHandleCallback.getContext(), this.$loginPageUrl, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 3) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        } else {
            ResultKt.throwOnFailure(obj);
        }
        Function2<OAuthAccessTokenResponse, Continuation<? super Unit>, Object> function2 = this.$block;
        this.label = 2;
        if (function2.invoke((OAuthAccessTokenResponse.OAuth1a) obj, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
