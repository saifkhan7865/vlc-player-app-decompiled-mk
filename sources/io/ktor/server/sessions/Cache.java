package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002J\u0019\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\bJ\u001d\u0010\u0007\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH&J\u0017\u0010\u000e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\b\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/server/sessions/Cache;", "K", "", "V", "getOrCompute", "key", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidate", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "invalidateAll", "", "peek", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Cache.kt */
public interface Cache<K, V> {
    Object getOrCompute(K k, Continuation<? super V> continuation);

    V invalidate(K k);

    boolean invalidate(K k, V v);

    void invalidateAll();

    V peek(K k);
}
