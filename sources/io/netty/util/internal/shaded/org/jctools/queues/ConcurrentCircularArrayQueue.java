package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.IndexedQueueSizeUtil;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class ConcurrentCircularArrayQueue<E> extends ConcurrentCircularArrayQueueL0Pad<E> implements MessagePassingQueue<E>, IndexedQueueSizeUtil.IndexedQueue, QueueProgressIndicators, SupportsIterator {
    protected final E[] buffer;
    protected final long mask;

    ConcurrentCircularArrayQueue(int i) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        this.mask = (long) (roundToPowerOfTwo - 1);
        this.buffer = UnsafeRefArrayAccess.allocateRefArray(roundToPowerOfTwo);
    }

    public int size() {
        return IndexedQueueSizeUtil.size(this);
    }

    public boolean isEmpty() {
        return IndexedQueueSizeUtil.isEmpty(this);
    }

    public String toString() {
        return getClass().getName();
    }

    public void clear() {
        do {
        } while (poll() != null);
    }

    public int capacity() {
        return (int) (this.mask + 1);
    }

    public long currentProducerIndex() {
        return lvProducerIndex();
    }

    public long currentConsumerIndex() {
        return lvConsumerIndex();
    }

    public Iterator<E> iterator() {
        return new WeakIterator(lvConsumerIndex(), lvProducerIndex(), this.mask, this.buffer);
    }

    private static class WeakIterator<E> implements Iterator<E> {
        private final E[] buffer;
        private final long mask;
        private E nextElement = getNext();
        private long nextIndex;
        private final long pIndex;

        WeakIterator(long j, long j2, long j3, E[] eArr) {
            this.nextIndex = j;
            this.pIndex = j2;
            this.mask = j3;
            this.buffer = eArr;
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
            do {
                long j = this.nextIndex;
                if (j >= this.pIndex) {
                    return null;
                }
                this.nextIndex = 1 + j;
                lvRefElement = UnsafeRefArrayAccess.lvRefElement(this.buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(j, this.mask));
            } while (lvRefElement == null);
            return lvRefElement;
        }
    }
}
