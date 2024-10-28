package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuth1aKt", f = "OAuth1a.kt", i = {}, l = {379, 380}, m = "requestOAuth1aAccessToken", n = {}, s = {})
/* compiled from: OAuth1a.kt */
final class OAuth1aKt$requestOAuth1aAccessToken$2 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    OAuth1aKt$requestOAuth1aAccessToken$2(Continuation<? super OAuth1aKt$requestOAuth1aAccessToken$2> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuth1aKt.requestOAuth1aAccessToken((HttpClient) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (Map<String, String>) null, (Function1<? super HttpRequestBuilder, Unit>) null, this);
    }
}
