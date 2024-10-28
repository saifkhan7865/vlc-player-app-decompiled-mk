package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.queues.IndexedQueueSizeUtil;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators;
import io.netty.util.internal.shaded.org.jctools.queues.SupportsIterator;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;

abstract class AtomicReferenceArrayQueue<E> extends AbstractQueue<E> implements IndexedQueueSizeUtil.IndexedQueue, QueueProgressIndicators, MessagePassingQueue<E>, SupportsIterator {
    protected final AtomicReferenceArray<E> buffer;
    protected final int mask;

    public AtomicReferenceArrayQueue(int i) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        this.mask = roundToPowerOfTwo - 1;
        this.buffer = new AtomicReferenceArray<>(roundToPowerOfTwo);
    }

    public String toString() {
        return getClass().getName();
    }

    public void clear() {
        do {
        } while (poll() != null);
    }

    public final int capacity() {
        return this.mask + 1;
    }

    public final int size() {
        return IndexedQueueSizeUtil.size(this);
    }

    public final boolean isEmpty() {
        return IndexedQueueSizeUtil.isEmpty(this);
    }

    public final long currentProducerIndex() {
        return lvProducerIndex();
    }

    public final long currentConsumerIndex() {
        return lvConsumerIndex();
    }

    public final Iterator<E> iterator() {
        return new WeakIterator(lvConsumerIndex(), lvProducerIndex(), this.mask, this.buffer);
    }

    private static class WeakIterator<E> implements Iterator<E> {
        private final AtomicReferenceArray<E> buffer;
        private final int mask;
        private E nextElement = getNext();
        private long nextIndex;
        private final long pIndex;

        WeakIterator(long j, long j2, int i, AtomicReferenceArray<E> atomicReferenceArray) {
            this.nextIndex = j;
            this.pIndex = j2;
            this.mask = i;
            this.buffer = atomicReferenceArray;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        public boolean hasNext() {
            return this.nextElement != null;
        }

        public E next() {
            E e = this.nextElement;
            if (e != null) {
                this.nextElement = getNext();
                return e;
            }
            throw new NoSuchElementException();
        }

        private E getNext() {
            E lvRefElement;
            int i = this.mask;
            AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
            do {
                long j = this.nextIndex;
                if (j >= this.pIndex) {
                    return null;
                }
                this.nextIndex = 1 + j;
                lvRefElement = AtomicQueueUtil.lvRefElement(atomicReferenceArray, AtomicQueueUtil.calcCircularRefElementOffset(j, (long) i));
            } while (lvRefElement == null);
            return lvRefElement;
        }
    }
}
