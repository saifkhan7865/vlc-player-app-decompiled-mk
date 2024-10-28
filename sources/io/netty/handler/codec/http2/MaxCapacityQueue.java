package io.netty.handler.codec.http2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

final class MaxCapacityQueue<E> implements Queue<E> {
    private final int maxCapacity;
    private final Queue<E> queue;

    MaxCapacityQueue(Queue<E> queue2, int i) {
        this.queue = queue2;
        this.maxCapacity = i;
    }

    public boolean add(E e) {
        if (offer(e)) {
            return true;
        }
        throw new IllegalStateException();
    }

    public boolean offer(E e) {
        if (this.maxCapacity <= this.queue.size()) {
            return false;
        }
        return this.queue.offer(e);
    }

    public E remove() {
        return this.queue.remove();
    }

    public E poll() {
        return this.queue.poll();
    }

    public E element() {
        return this.queue.element();
    }

    public E peek() {
        return this.queue.peek();
    }

    public int size() {
        return this.queue.size();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.queue.contains(obj);
    }

    public Iterator<E> iterator() {
        return this.queue.iterator();
    }

    public Object[] toArray() {
        return this.queue.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.queue.toArray(tArr);
    }

    public boolean remove(Object obj) {
        return this.queue.remove(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.queue.containsAll(collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        if (this.maxCapacity >= size() + collection.size()) {
            return this.queue.addAll(collection);
        }
        throw new IllegalStateException();
    }

    public boolean removeAll(Collection<?> collection) {
        return this.queue.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return this.queue.retainAll(collection);
    }

    public void clear() {
        this.queue.clear();
    }
}
