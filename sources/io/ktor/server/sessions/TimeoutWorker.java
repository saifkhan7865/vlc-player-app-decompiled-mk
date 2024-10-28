package io.ktor.server.sessions;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00060\u0003j\u0002`\u0004B;\u0012\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\f¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\rH\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R0\u0010\u0005\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003 \u0016*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0018\u00010\u00060\u00060\u0015X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lio/ktor/server/sessions/TimeoutWorker;", "K", "", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "owner", "Lio/ktor/server/sessions/BaseTimeoutCache;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "cond", "Ljava/util/concurrent/locks/Condition;", "items", "Lio/ktor/server/sessions/PullableLinkedList;", "Lio/ktor/server/sessions/KeyState;", "(Lio/ktor/server/sessions/BaseTimeoutCache;Ljava/util/concurrent/locks/ReentrantLock;Ljava/util/concurrent/locks/Condition;Lio/ktor/server/sessions/PullableLinkedList;)V", "getCond", "()Ljava/util/concurrent/locks/Condition;", "getItems", "()Lio/ktor/server/sessions/PullableLinkedList;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "head", "run", "", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
final class TimeoutWorker<K> implements Runnable {
    private final Condition cond;
    private final PullableLinkedList<KeyState<K>> items;
    private final ReentrantLock lock;
    private final WeakReference<BaseTimeoutCache<K, ?>> owner;

    public TimeoutWorker(BaseTimeoutCache<? super K, ?> baseTimeoutCache, ReentrantLock reentrantLock, Condition condition, PullableLinkedList<KeyState<K>> pullableLinkedList) {
        Intrinsics.checkNotNullParameter(baseTimeoutCache, "owner");
        Intrinsics.checkNotNullParameter(reentrantLock, "lock");
        Intrinsics.checkNotNullParameter(condition, "cond");
        Intrinsics.checkNotNullParameter(pullableLinkedList, "items");
        this.lock = reentrantLock;
        this.cond = condition;
        this.items = pullableLinkedList;
        this.owner = new WeakReference<>(baseTimeoutCache);
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    public final Condition getCond() {
        return this.cond;
    }

    public final PullableLinkedList<KeyState<K>> getItems() {
        return this.items;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
        L_0x0000:
            java.util.concurrent.locks.ReentrantLock r0 = r7.lock
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            io.ktor.server.sessions.KeyState r1 = r7.head()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x003e
            long r2 = r1.timeToWait()     // Catch:{ all -> 0x0052 }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0037
            io.ktor.server.sessions.PullableLinkedList<io.ktor.server.sessions.KeyState<K>> r2 = r7.items     // Catch:{ all -> 0x0052 }
            r3 = r1
            io.ktor.server.sessions.ListElement r3 = (io.ktor.server.sessions.ListElement) r3     // Catch:{ all -> 0x0052 }
            r2.remove(r3)     // Catch:{ all -> 0x0052 }
            java.lang.ref.WeakReference r1 = r1.getKey()     // Catch:{ all -> 0x0052 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x003e
            java.lang.ref.WeakReference<io.ktor.server.sessions.BaseTimeoutCache<K, ?>> r2 = r7.owner     // Catch:{ all -> 0x0052 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0052 }
            io.ktor.server.sessions.BaseTimeoutCache r2 = (io.ktor.server.sessions.BaseTimeoutCache) r2     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x003e
            r2.invalidate(r1)     // Catch:{ all -> 0x0052 }
            goto L_0x003e
        L_0x0037:
            java.util.concurrent.locks.Condition r1 = r7.cond     // Catch:{ all -> 0x0052 }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0052 }
            r1.await(r2, r4)     // Catch:{ all -> 0x0052 }
        L_0x003e:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0052 }
            r0.unlock()
            boolean r0 = java.lang.Thread.interrupted()
            if (r0 != 0) goto L_0x0051
            java.lang.ref.WeakReference<io.ktor.server.sessions.BaseTimeoutCache<K, ?>> r0 = r7.owner
            java.lang.Object r0 = r0.get()
            if (r0 != 0) goto L_0x0000
        L_0x0051:
            return
        L_0x0052:
            r1 = move-exception
            r0.unlock()
            goto L_0x0058
        L_0x0057:
            throw r1
        L_0x0058:
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.TimeoutWorker.run():void");
    }

    private final KeyState<K> head() {
        Lock lock2 = this.lock;
        lock2.lock();
        while (this.items.isEmpty() && this.owner.get() != null) {
            try {
                this.cond.await(60, TimeUnit.SECONDS);
            } finally {
                lock2.unlock();
            }
        }
        return this.owner.get() == null ? null : this.items.head();
    }
}
