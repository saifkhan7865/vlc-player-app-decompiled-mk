package io.ktor.client.plugins.cache;

import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.HttpCacheLegacyKt", f = "HttpCacheLegacy.kt", i = {}, l = {111}, m = "cacheResponse", n = {}, s = {})
/* compiled from: HttpCacheLegacy.kt */
final class HttpCacheLegacyKt$cacheResponse$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    HttpCacheLegacyKt$cacheResponse$1(Continuation<? super HttpCacheLegacyKt$cacheResponse$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpCacheLegacyKt.cacheResponse((HttpCache) null, (HttpResponse) null, this);
    }
}
