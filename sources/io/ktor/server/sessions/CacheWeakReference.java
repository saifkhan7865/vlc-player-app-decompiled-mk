package io.ktor.server.sessions;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B#\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0001\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0002\u0010\tR\u0016\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lio/ktor/server/sessions/CacheWeakReference;", "K", "V", "Ljava/lang/ref/WeakReference;", "Lio/ktor/server/sessions/CacheReference;", "key", "value", "queue", "Ljava/lang/ref/ReferenceQueue;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/ref/ReferenceQueue;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
public final class CacheWeakReference<K, V> extends WeakReference<V> implements CacheReference<K> {
    private final K key;

    public K getKey() {
        return this.key;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CacheWeakReference(K k, V v, ReferenceQueue<V> referenceQueue) {
        super(v, referenceQueue);
        Intrinsics.checkNotNullParameter(referenceQueue, "queue");
        this.key = k;
    }
}
