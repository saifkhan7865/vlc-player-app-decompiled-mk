package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuth1aKt", f = "OAuth1a.kt", i = {1}, l = {362, 101}, m = "simpleOAuth1aStep1", n = {"response"}, s = {"L$0"})
/* compiled from: OAuth1a.kt */
final class OAuth1aKt$simpleOAuth1aStep1$2 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    OAuth1aKt$simpleOAuth1aStep1$2(Continuation<? super OAuth1aKt$simpleOAuth1aStep1$2> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuth1aKt.simpleOAuth1aStep1((HttpClient) null, (String) null, (String) null, (String) null, (String) null, (String) null, (List<Pair<String, String>>) null, this);
    }
}
