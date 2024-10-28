package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.sessions.CacheStorage", f = "CacheStorage.kt", i = {0, 0, 0}, l = {24, 30}, m = "write", n = {"this", "id", "value"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: CacheStorage.kt */
final class CacheStorage$write$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CacheStorage this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CacheStorage$write$1(CacheStorage cacheStorage, Continuation<? super CacheStorage$write$1> continuation) {
        super(continuation);
        this.this$0 = cacheStorage;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.write((String) null, (String) null, this);
    }
}
