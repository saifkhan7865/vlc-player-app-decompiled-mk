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
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt", f = "OAuth.kt", i = {0, 0, 0, 0, 0}, l = {78, 79}, m = "oauth", n = {"$this$oauth", "client", "dispatcher", "providerLookup", "urlProvider"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: OAuth.kt */
final class OAuthKt$oauth$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    OAuthKt$oauth$1(Continuation<? super OAuthKt$oauth$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuthKt.oauth((PipelineContext<Unit, ApplicationCall>) null, (HttpClient) null, (CoroutineDispatcher) null, (Function1<? super ApplicationCall, ? extends OAuthServerSettings>) null, (Function2<? super ApplicationCall, ? super OAuthServerSettings, String>) null, this);
    }
}
