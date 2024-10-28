package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\u001a\b\u0002\u0010\u0005 \u0001*\b\u0012\u0004\u0012\u0002H\u00040\u0006*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Ljava/lang/Thread;", "K", "", "V", "R", "Ljava/lang/ref/Reference;", "Lio/ktor/server/sessions/CacheReference;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
final class ReferenceCache$workerThread$2 extends Lambda implements Function0<Thread> {
    final /* synthetic */ ReferenceCache<K, V, R> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReferenceCache$workerThread$2(ReferenceCache<K, V, ? extends R> referenceCache) {
        super(0);
        this.this$0 = referenceCache;
    }

    public final Thread invoke() {
        Thread thread = new Thread(new ReferenceWorker(this.this$0.container, this.this$0.queue));
        thread.setDaemon(true);
        thread.start();
        return thread;
    }
}
