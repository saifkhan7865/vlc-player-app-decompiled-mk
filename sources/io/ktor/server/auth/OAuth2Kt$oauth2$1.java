package io.ktor.server.auth;

import io.ktor.client.HttpClient;
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
@DebugMetadata(c = "io.ktor.server.auth.OAuth2Kt", f = "OAuth2.kt", i = {0, 0, 0, 0, 0, 1}, l = {36, 42, 39, 47}, m = "oauth2", n = {"$this$oauth2", "client", "dispatcher", "urlProvider", "provider", "provider"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0"})
/* compiled from: OAuth2.kt */
final class OAuth2Kt$oauth2$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    OAuth2Kt$oauth2$1(Continuation<? super OAuth2Kt$oauth2$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuth2Kt.oauth2((PipelineContext<Unit, ApplicationCall>) null, (HttpClient) null, (CoroutineDispatcher) null, (Function1<? super ApplicationCall, ? extends OAuthServerSettings>) null, (Function2<? super ApplicationCall, ? super OAuthServerSettings, String>) null, this);
    }
}
