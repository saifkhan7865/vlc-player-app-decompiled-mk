package kotlin.reflect.jvm.internal;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u001f\u0012\u0016\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\t\u001a\u00028\u00002\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rR$\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR$\u0010\u0010\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00028\u00000\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lkotlin/reflect/jvm/internal/ConcurrentHashMapCache;", "V", "Lkotlin/reflect/jvm/internal/CacheByClass;", "Lkotlin/Function1;", "Ljava/lang/Class;", "compute", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "key", "get", "(Ljava/lang/Class;)Ljava/lang/Object;", "", "clear", "()V", "Lkotlin/jvm/functions/Function1;", "j$/util/concurrent/ConcurrentHashMap", "cache", "Lj$/util/concurrent/ConcurrentHashMap;", "kotlin-reflection"}, k = 1, mv = {1, 9, 0})
/* compiled from: CacheByClass.kt */
final class ConcurrentHashMapCache<V> extends CacheByClass<V> {
    private final ConcurrentHashMap<Class<?>, V> cache = new ConcurrentHashMap<>();
    private final Function1<Class<?>, V> compute;

    public ConcurrentHashMapCache(Function1<? super Class<?>, ? extends V> function1) {
        Intrinsics.checkNotNullParameter(function1, "compute");
        this.compute = function1;
    }

    public V get(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, LeanbackPreferenceDialogFragment.ARG_KEY);
        ConcurrentMap concurrentMap = this.cache;
        V v = concurrentMap.get(cls);
        if (v != null) {
            return v;
        }
        V invoke = this.compute.invoke(cls);
        V putIfAbsent = concurrentMap.putIfAbsent(cls, invoke);
        return putIfAbsent == null ? invoke : putIfAbsent;
    }

    public void clear() {
        this.cache.clear();
    }
}
