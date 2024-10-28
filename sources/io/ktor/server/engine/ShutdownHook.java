package io.ktor.server.engine;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/ShutdownHook;", "Ljava/lang/Thread;", "stopFunction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "shouldStop", "Ljava/util/concurrent/atomic/AtomicBoolean;", "run", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ShutdownHookJvm.kt */
final class ShutdownHook extends Thread {
    private final AtomicBoolean shouldStop = new AtomicBoolean(true);
    private final Function0<Unit> stopFunction;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ShutdownHook(Function0<Unit> function0) {
        super("KtorShutdownHook");
        Intrinsics.checkNotNullParameter(function0, "stopFunction");
        this.stopFunction = function0;
    }

    public void run() {
        if (this.shouldStop.compareAndSet(true, false)) {
            this.stopFunction.invoke();
        }
    }
}
