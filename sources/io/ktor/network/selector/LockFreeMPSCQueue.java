package io.ktor.network.selector;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u0004J\u000f\u0010\u000b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueue;", "", "E", "<init>", "()V", "element", "", "addLast", "(Ljava/lang/Object;)Z", "", "close", "removeFirstOrNull", "()Ljava/lang/Object;", "isEmpty", "()Z", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LockFreeMPSCQueue.kt */
public final class LockFreeMPSCQueue<E> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _cur$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeMPSCQueue.class, Object.class, "_cur");
    private volatile /* synthetic */ Object _cur = new LockFreeMPSCQueueCore(8);

    public final boolean isEmpty() {
        return ((LockFreeMPSCQueueCore) this._cur).isEmpty();
    }

    public final void close() {
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            if (!lockFreeMPSCQueueCore.close()) {
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, lockFreeMPSCQueueCore, lockFreeMPSCQueueCore.next());
            } else {
                return;
            }
        }
    }

    public final boolean addLast(E e) {
        Intrinsics.checkNotNullParameter(e, "element");
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            int addLast = lockFreeMPSCQueueCore.addLast(e);
            if (addLast == 0) {
                return true;
            }
            if (addLast == 1) {
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, lockFreeMPSCQueueCore, lockFreeMPSCQueueCore.next());
            } else if (addLast == 2) {
                return false;
            }
        }
    }

    public final E removeFirstOrNull() {
        while (true) {
            LockFreeMPSCQueueCore lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._cur;
            E removeFirstOrNull = lockFreeMPSCQueueCore.removeFirstOrNull();
            if (removeFirstOrNull != LockFreeMPSCQueueCore.REMOVE_FROZEN) {
                return removeFirstOrNull;
            }
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_cur$FU, this, lockFreeMPSCQueueCore, lockFreeMPSCQueueCore.next());
        }
    }
}
