package io.ktor.server.sessions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002 \u0000*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/lang/Thread;", "K", "", "V", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
final class BaseTimeoutCache$workerThread$2 extends Lambda implements Function0<Thread> {
    final /* synthetic */ BaseTimeoutCache<K, V> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseTimeoutCache$workerThread$2(BaseTimeoutCache<? super K, V> baseTimeoutCache) {
        super(0);
        this.this$0 = baseTimeoutCache;
    }

    public final Thread invoke() {
        BaseTimeoutCache<K, V> baseTimeoutCache = this.this$0;
        ReentrantLock access$getLock$p = baseTimeoutCache.lock;
        Condition access$getCond$p = this.this$0.cond;
        Intrinsics.checkNotNullExpressionValue(access$getCond$p, "cond");
        Thread thread = new Thread(new TimeoutWorker(baseTimeoutCache, access$getLock$p, access$getCond$p, this.this$0.items));
        thread.setDaemon(true);
        thread.start();
        return thread;
    }
}
