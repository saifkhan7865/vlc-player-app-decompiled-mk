package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt", f = "OAuth.kt", i = {1}, l = {94, 104, 101}, m = "oauthRespondRedirect", n = {"provider"}, s = {"L$0"})
/* compiled from: OAuth.kt */
final class OAuthKt$oauthRespondRedirect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    OAuthKt$oauthRespondRedirect$1(Continuation<? super OAuthKt$oauthRespondRedirect$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuthKt.oauthRespondRedirect((PipelineContext<Unit, ApplicationCall>) null, (HttpClient) null, (CoroutineDispatcher) null, (OAuthServerSettings) null, (String) null, this);
    }
}
