package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt", f = "OAuth.kt", i = {2, 2, 2, 2, 2, 2, 2, 2}, l = {149, 151, 163, 165, 167}, m = "oauthHandleCallback", n = {"$this$oauthHandleCallback", "client", "dispatcher", "provider", "callbackUrl", "loginPageUrl", "configure", "block"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* compiled from: OAuth.kt */
final class OAuthKt$oauthHandleCallback$3 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;

    OAuthKt$oauthHandleCallback$3(Continuation<? super OAuthKt$oauthHandleCallback$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuthKt.oauthHandleCallback((PipelineContext<Unit, ApplicationCall>) null, (HttpClient) null, (CoroutineDispatcher) null, (OAuthServerSettings) null, (String) null, (String) null, (Function1<? super HttpRequestBuilder, Unit>) null, (Function2<? super OAuthAccessTokenResponse, ? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
