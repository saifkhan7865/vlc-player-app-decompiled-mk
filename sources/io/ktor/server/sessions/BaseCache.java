package io.ktor.server.sessions;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.util.collections.ConcurrentMap;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Deferred;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B,\u0012\"\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0019\u0010\u000f\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u001d\u0010\u0012\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0017\u0010\u0019\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013R2\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR \u0010\f\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u000e0\rX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/server/sessions/BaseCache;", "K", "", "V", "Lio/ktor/server/sessions/Cache;", "calc", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;)V", "getCalc", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "container", "Lio/ktor/util/collections/ConcurrentMap;", "Lkotlinx/coroutines/Deferred;", "getOrCompute", "key", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidate", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "invalidateAll", "", "peek", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Cache.kt */
public final class BaseCache<K, V> implements Cache<K, V> {
    private final Function2<K, Continuation<? super V>, Object> calc;
    private final ConcurrentMap<K, Deferred<V>> container = new ConcurrentMap<>(0, 1, (DefaultConstructorMarker) null);

    public BaseCache(Function2<? super K, ? super Continuation<? super V>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(function2, "calc");
        this.calc = function2;
    }

    public final Function2<K, Continuation<? super V>, Object> getCalc() {
        return this.calc;
    }

    public Object getOrCompute(K k, Continuation<? super V> continuation) {
        return this.container.computeIfAbsent(k, new BaseCache$getOrCompute$2(continuation.getContext(), this, k)).await(continuation);
    }

    public V peek(K k) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        Deferred deferred = this.container.get(k);
        if (deferred == null || deferred.isActive()) {
            return null;
        }
        return deferred.getCompleted();
    }

    public V invalidate(K k) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        Deferred remove = this.container.remove(k);
        if (remove == null || remove.isActive()) {
            return null;
        }
        try {
            remove.getCompleted();
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean invalidate(K k, V v) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(v, "value");
        Deferred deferred = this.container.get(k);
        if (deferred != null && !deferred.isActive()) {
            try {
                if (!Intrinsics.areEqual(deferred.getCompleted(), (Object) v) || !this.container.remove(k, deferred)) {
                    return false;
                }
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public void invalidateAll() {
        this.container.clear();
    }
}
