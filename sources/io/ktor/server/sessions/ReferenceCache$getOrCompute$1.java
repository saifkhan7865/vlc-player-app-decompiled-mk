package io.ktor.server.sessions;

import io.ktor.server.sessions.CacheReference;
import java.lang.ref.Reference;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.sessions.ReferenceCache", f = "CacheJvm.kt", i = {0, 0}, l = {29, 36}, m = "getOrCompute$suspendImpl", n = {"$this", "key"}, s = {"L$0", "L$1"})
/* compiled from: CacheJvm.kt */
final class ReferenceCache$getOrCompute$1<K, V, R extends Reference<V> & CacheReference<? extends K>> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ReferenceCache<K, V, R> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReferenceCache$getOrCompute$1(ReferenceCache<K, V, ? extends R> referenceCache, Continuation<? super ReferenceCache$getOrCompute$1> continuation) {
        super(continuation);
        this.this$0 = referenceCache;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ReferenceCache.getOrCompute$suspendImpl(this.this$0, null, this);
    }
}
