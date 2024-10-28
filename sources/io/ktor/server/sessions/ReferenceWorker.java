package io.ktor.server.sessions;

import io.ktor.server.sessions.CacheReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00060\u0005j\u0002`\u0006B%\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R4\u0010\u0007\u001a(\u0012$\u0012\"\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001 \r*\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b0\b0\fX\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lio/ktor/server/sessions/ReferenceWorker;", "K", "", "R", "Lio/ktor/server/sessions/CacheReference;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "owner", "Lio/ktor/server/sessions/Cache;", "queue", "Ljava/lang/ref/ReferenceQueue;", "(Lio/ktor/server/sessions/Cache;Ljava/lang/ref/ReferenceQueue;)V", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getQueue", "()Ljava/lang/ref/ReferenceQueue;", "run", "", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
final class ReferenceWorker<K, R extends CacheReference<? extends K>> implements Runnable {
    private final WeakReference<Cache<K, R>> owner;
    private final ReferenceQueue<?> queue;

    public ReferenceWorker(Cache<? super K, R> cache, ReferenceQueue<?> referenceQueue) {
        Intrinsics.checkNotNullParameter(cache, "owner");
        Intrinsics.checkNotNullParameter(referenceQueue, "queue");
        this.queue = referenceQueue;
        this.owner = new WeakReference<>(cache);
    }

    public final ReferenceQueue<?> getQueue() {
        return this.queue;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r3 = this;
        L_0x0000:
            java.lang.ref.ReferenceQueue<?> r0 = r3.queue
            r1 = 60000(0xea60, double:2.9644E-319)
            java.lang.ref.Reference r0 = r0.remove(r1)
            boolean r1 = r0 instanceof io.ktor.server.sessions.CacheReference
            if (r1 == 0) goto L_0x0021
            io.ktor.server.sessions.CacheReference r0 = (io.ktor.server.sessions.CacheReference) r0
            java.lang.ref.WeakReference<io.ktor.server.sessions.Cache<K, R>> r1 = r3.owner
            java.lang.Object r1 = r1.get()
            io.ktor.server.sessions.Cache r1 = (io.ktor.server.sessions.Cache) r1
            if (r1 != 0) goto L_0x001a
            goto L_0x002f
        L_0x001a:
            java.lang.Object r2 = r0.getKey()
            r1.invalidate(r2, r0)
        L_0x0021:
            boolean r0 = java.lang.Thread.interrupted()
            if (r0 != 0) goto L_0x002f
            java.lang.ref.WeakReference<io.ktor.server.sessions.Cache<K, R>> r0 = r3.owner
            java.lang.Object r0 = r0.get()
            if (r0 != 0) goto L_0x0000
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.ReferenceWorker.run():void");
    }
}
