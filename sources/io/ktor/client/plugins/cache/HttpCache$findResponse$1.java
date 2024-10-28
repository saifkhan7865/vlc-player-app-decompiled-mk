package io.ktor.client.plugins.cache;

import io.ktor.client.plugins.cache.storage.CacheStorage;
import io.ktor.client.request.HttpRequest;
import io.ktor.http.Url;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.HttpCache", f = "HttpCache.kt", i = {1}, l = {311, 316}, m = "findResponse", n = {"requestHeaders"}, s = {"L$0"})
/* compiled from: HttpCache.kt */
final class HttpCache$findResponse$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpCache this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpCache$findResponse$1(HttpCache httpCache, Continuation<? super HttpCache$findResponse$1> continuation) {
        super(continuation);
        this.this$0 = httpCache;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.findResponse((CacheStorage) null, (Map<String, String>) null, (Url) null, (HttpRequest) null, this);
    }
}
