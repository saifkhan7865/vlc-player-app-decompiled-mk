package io.ktor.server.sessions;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B)\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\nJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0019\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0017\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010!J\u001d\u0010 \u001a\u00020\b2\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u0010\"\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010#J\b\u0010$\u001a\u00020\u001cH\u0016J\u0017\u0010%\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010!J\u001f\u0010&\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00028\u00002\b\b\u0002\u0010'\u001a\u00020\bH\u0002¢\u0006\u0002\u0010(J\u0015\u0010)\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010*R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00100\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lio/ktor/server/sessions/BaseTimeoutCache;", "K", "", "V", "Lio/ktor/server/sessions/Cache;", "timeoutValue", "", "touchOnGet", "", "delegate", "(JZLio/ktor/server/sessions/Cache;)V", "cond", "Ljava/util/concurrent/locks/Condition;", "kotlin.jvm.PlatformType", "items", "Lio/ktor/server/sessions/PullableLinkedList;", "Lio/ktor/server/sessions/KeyState;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "map", "Ljava/util/WeakHashMap;", "workerThread", "Ljava/lang/Thread;", "getWorkerThread", "()Ljava/lang/Thread;", "workerThread$delegate", "Lkotlin/Lazy;", "forkIfNeeded", "", "getOrCompute", "key", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidate", "(Ljava/lang/Object;)Ljava/lang/Object;", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "invalidateAll", "peek", "pull", "create", "(Ljava/lang/Object;Z)V", "remove", "(Ljava/lang/Object;)V", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
public final class BaseTimeoutCache<K, V> implements Cache<K, V> {
    /* access modifiers changed from: private */
    public final Condition cond;
    private final Cache<K, V> delegate;
    /* access modifiers changed from: private */
    public final PullableLinkedList<KeyState<K>> items = new PullableLinkedList<>();
    /* access modifiers changed from: private */
    public final ReentrantLock lock;
    private final WeakHashMap<K, KeyState<K>> map = new WeakHashMap<>();
    private final long timeoutValue;
    private final boolean touchOnGet;
    private final Lazy workerThread$delegate = LazyKt.lazy(new BaseTimeoutCache$workerThread$2(this));

    public BaseTimeoutCache(long j, boolean z, Cache<? super K, V> cache) {
        Intrinsics.checkNotNullParameter(cache, "delegate");
        this.timeoutValue = j;
        this.touchOnGet = z;
        this.delegate = cache;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.cond = reentrantLock.newCondition();
    }

    private final Thread getWorkerThread() {
        return (Thread) this.workerThread$delegate.getValue();
    }

    public Object getOrCompute(K k, Continuation<? super V> continuation) {
        if (this.touchOnGet) {
            pull$default(this, k, false, 2, (Object) null);
        }
        return this.delegate.getOrCompute(k, continuation);
    }

    public V peek(K k) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (this.touchOnGet) {
            pull(k, false);
        }
        return this.delegate.peek(k);
    }

    public V invalidate(K k) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        remove(k);
        return this.delegate.invalidate(k);
    }

    public boolean invalidate(K k, V v) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(v, "value");
        if (!this.delegate.invalidate(k, v)) {
            return false;
        }
        remove(k);
        return true;
    }

    public void invalidateAll() {
        this.delegate.invalidateAll();
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            this.items.clear();
            this.cond.signalAll();
            Unit unit = Unit.INSTANCE;
        } finally {
            lock2.unlock();
        }
    }

    private final void forkIfNeeded() {
        if (!this.items.isEmpty() && !getWorkerThread().isAlive()) {
            throw new IllegalStateException("Daemon thread is already dead");
        }
    }

    static /* synthetic */ void pull$default(BaseTimeoutCache baseTimeoutCache, Object obj, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            z = true;
        }
        baseTimeoutCache.pull(obj, z);
    }

    private final void pull(K k, boolean z) {
        KeyState keyState;
        Lock lock2 = this.lock;
        lock2.lock();
        if (z) {
            try {
                Map map2 = this.map;
                Object obj = map2.get(k);
                if (obj == null) {
                    obj = new KeyState(k, this.timeoutValue);
                    map2.put(k, obj);
                }
                keyState = (KeyState) obj;
            } catch (Throwable th) {
                lock2.unlock();
                throw th;
            }
        } else {
            keyState = this.map.get(k);
        }
        if (keyState != null) {
            keyState.touch();
            this.items.pull(keyState);
            this.cond.signalAll();
        }
        Unit unit = Unit.INSTANCE;
        lock2.unlock();
        forkIfNeeded();
    }

    private final void remove(K k) {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            KeyState remove = this.map.remove(k);
            if (remove != null) {
                PullableLinkedList<KeyState<K>> pullableLinkedList = this.items;
                Intrinsics.checkNotNullExpressionValue(remove, "it");
                pullableLinkedList.remove(remove);
                this.cond.signalAll();
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            lock2.unlock();
        }
    }
}
