package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;

/* compiled from: MpscChunkedAtomicArrayQueue */
abstract class MpscChunkedAtomicArrayQueueColdProducerFields<E> extends BaseMpscLinkedAtomicArrayQueue<E> {
    protected final long maxQueueCapacity;

    MpscChunkedAtomicArrayQueueColdProducerFields(int i, int i2) {
        super(i);
        RangeUtil.checkGreaterThanOrEqual(i2, 4, "maxCapacity");
        RangeUtil.checkLessThan(Pow2.roundToPowerOfTwo(i), Pow2.roundToPowerOfTwo(i2), "initialCapacity");
        this.maxQueueCapacity = ((long) Pow2.roundToPowerOfTwo(i2)) << 1;
    }
}
