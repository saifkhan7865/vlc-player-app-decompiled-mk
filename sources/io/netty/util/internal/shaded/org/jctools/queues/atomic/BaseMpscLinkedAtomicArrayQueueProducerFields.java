package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* compiled from: BaseMpscLinkedAtomicArrayQueue */
abstract class BaseMpscLinkedAtomicArrayQueueProducerFields<E> extends BaseMpscLinkedAtomicArrayQueuePad1<E> {
    private static final AtomicLongFieldUpdater<BaseMpscLinkedAtomicArrayQueueProducerFields> P_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(BaseMpscLinkedAtomicArrayQueueProducerFields.class, "producerIndex");
    private volatile long producerIndex;

    BaseMpscLinkedAtomicArrayQueueProducerFields() {
    }

    public final long lvProducerIndex() {
        return this.producerIndex;
    }

    /* access modifiers changed from: package-private */
    public final void soProducerIndex(long j) {
        P_INDEX_UPDATER.lazySet(this, j);
    }

    /* access modifiers changed from: package-private */
    public final boolean casProducerIndex(long j, long j2) {
        return P_INDEX_UPDATER.compareAndSet(this, j, j2);
    }
}
