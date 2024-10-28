package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

final class AtomicQueueUtil {
    static int calcCircularLongElementOffset(long j, int i) {
        return (int) (j & ((long) i));
    }

    static int calcCircularRefElementOffset(long j, long j2) {
        return (int) (j & j2);
    }

    static int calcLongElementOffset(long j) {
        return (int) j;
    }

    static int calcRefElementOffset(long j) {
        return (int) j;
    }

    static int modifiedCalcCircularRefElementOffset(long j, long j2) {
        return ((int) (j & j2)) >> 1;
    }

    AtomicQueueUtil() {
    }

    static <E> E lvRefElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    static <E> E lpRefElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    static <E> void spRefElement(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.lazySet(i, e);
    }

    static void soRefElement(AtomicReferenceArray atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    static <E> void svRefElement(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.set(i, e);
    }

    static <E> AtomicReferenceArray<E> allocateRefArray(int i) {
        return new AtomicReferenceArray<>(i);
    }

    static void spLongElement(AtomicLongArray atomicLongArray, int i, long j) {
        atomicLongArray.lazySet(i, j);
    }

    static void soLongElement(AtomicLongArray atomicLongArray, int i, long j) {
        atomicLongArray.lazySet(i, j);
    }

    static long lpLongElement(AtomicLongArray atomicLongArray, int i) {
        return atomicLongArray.get(i);
    }

    static long lvLongElement(AtomicLongArray atomicLongArray, int i) {
        return atomicLongArray.get(i);
    }

    static AtomicLongArray allocateLongArray(int i) {
        return new AtomicLongArray(i);
    }

    static int length(AtomicReferenceArray<?> atomicReferenceArray) {
        return atomicReferenceArray.length();
    }

    static int nextArrayOffset(AtomicReferenceArray<?> atomicReferenceArray) {
        return length(atomicReferenceArray) - 1;
    }
}
