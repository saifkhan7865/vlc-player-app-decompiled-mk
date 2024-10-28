package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: BaseMpscLinkedAtomicArrayQueue */
abstract class BaseMpscLinkedAtomicArrayQueueConsumerFields<E> extends BaseMpscLinkedAtomicArrayQueuePad2<E> {
    private static final AtomicLongFieldUpdater<BaseMpscLinkedAtomicArrayQueueConsumerFields> C_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(BaseMpscLinkedAtomicArrayQueueConsumerFields.class, "consumerIndex");
    protected AtomicReferenceArray<E> consumerBuffer;
    private volatile long consumerIndex;
    protected long consumerMask;

    BaseMpscLinkedAtomicArrayQueueConsumerFields() {
    }

    public final long lvConsumerIndex() {
        return this.consumerIndex;
    }

    /* access modifiers changed from: package-private */
    public final long lpConsumerIndex() {
        return this.consumerIndex;
    }

    /* access modifiers changed from: package-private */
    public final void soConsumerIndex(long j) {
        C_INDEX_UPDATER.lazySet(this, j);
    }
}
