package io.ktor.client.plugins.cache.storage;

import io.ktor.http.Url;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage", f = "FileCacheStorage.kt", i = {0}, l = {81}, m = "find", n = {"varyKeys"}, s = {"L$0"})
/* compiled from: FileCacheStorage.kt */
final class FileCacheStorage$find$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FileCacheStorage this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileCacheStorage$find$1(FileCacheStorage fileCacheStorage, Continuation<? super FileCacheStorage$find$1> continuation) {
        super(continuation);
        this.this$0 = fileCacheStorage;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.find((Url) null, (Map<String, String>) null, this);
    }
}
