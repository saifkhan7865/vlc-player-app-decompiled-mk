package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.OAuth2Exception;
import io.ktor.server.auth.OAuthAccessTokenResponse;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import io.ktor.util.pipeline.PipelineContext;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt$oauthHandleCallback$6", f = "OAuth.kt", i = {}, l = {169, 177, 180, 183}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OAuth.kt */
final class OAuthKt$oauthHandleCallback$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<OAuthAccessTokenResponse, Continuation<? super Unit>, Object> $block;
    final /* synthetic */ String $callbackUrl;
    final /* synthetic */ HttpClient $client;
    final /* synthetic */ OAuthCallback.TokenSingle $code;
    final /* synthetic */ Function1<HttpRequestBuilder, Unit> $configure;
    final /* synthetic */ String $loginPageUrl;
    final /* synthetic */ OAuthServerSettings $provider;
    final /* synthetic */ PipelineContext<Unit, ApplicationCall> $this_oauthHandleCallback;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuthKt$oauthHandleCallback$6(HttpClient httpClient, OAuthServerSettings oAuthServerSettings, String str, OAuthCallback.TokenSingle tokenSingle, Function1<? super HttpRequestBuilder, Unit> function1, Function2<? super OAuthAccessTokenResponse, ? super Continuation<? super Unit>, ? extends Object> function2, PipelineContext<Unit, ApplicationCall> pipelineContext, String str2, Continuation<? super OAuthKt$oauthHandleCallback$6> continuation) {
        super(2, continuation);
        this.$client = httpClient;
        this.$provider = oAuthServerSettings;
        this.$callbackUrl = str;
        this.$code = tokenSingle;
        this.$configure = function1;
        this.$block = function2;
        this.$this_oauthHandleCallback = pipelineContext;
        this.$loginPageUrl = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OAuthKt$oauthHandleCallback$6(this.$client, this.$provider, this.$callbackUrl, this.$code, this.$configure, this.$block, this.$this_oauthHandleCallback, this.$loginPageUrl, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OAuthKt$oauthHandleCallback$6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HttpClient httpClient = this.$client;
            this.label = 1;
            obj = OAuth2Kt.oauth2RequestAccessToken(httpClient, (OAuthServerSettings.OAuth2ServerSettings) this.$provider, this.$callbackUrl, this.$code, this.$configure, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            if (i == 2) {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (OAuth2Exception.InvalidGrant e) {
                    Logger access$getLogger$p = OAuthKt.Logger;
                    access$getLogger$p.trace("Redirected to the login page due to invalid_grant error: " + e.getMessage());
                    this.label = 3;
                    if (OAuthKt.oauthHandleFail(this.$this_oauthHandleCallback.getContext(), this.$loginPageUrl, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } catch (IOException e2) {
                    OAuthKt.Logger.trace("Redirected to the login page due to IO error", (Throwable) e2);
                    this.label = 4;
                    if (OAuthKt.oauthHandleFail(this.$this_oauthHandleCallback.getContext(), this.$loginPageUrl, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 3 || i == 4) {
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
        if (function2.invoke((OAuthAccessTokenResponse.OAuth2) obj, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
