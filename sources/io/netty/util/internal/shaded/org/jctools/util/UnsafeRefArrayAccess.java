package io.netty.util.internal.shaded.org.jctools.util;

public final class UnsafeRefArrayAccess {
    public static final long REF_ARRAY_BASE = ((long) UnsafeAccess.UNSAFE.arrayBaseOffset(Object[].class));
    public static final int REF_ELEMENT_SHIFT;

    static {
        int arrayIndexScale = UnsafeAccess.UNSAFE.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            REF_ELEMENT_SHIFT = 2;
        } else if (8 == arrayIndexScale) {
            REF_ELEMENT_SHIFT = 3;
        } else {
            throw new IllegalStateException("Unknown pointer size: " + arrayIndexScale);
        }
    }

    public static <E> void spRefElement(E[] eArr, long j, E e) {
        UnsafeAccess.UNSAFE.putObject(eArr, j, e);
    }

    public static <E> void soRefElement(E[] eArr, long j, E e) {
        UnsafeAccess.UNSAFE.putOrderedObject(eArr, j, e);
    }

    public static <E> E lpRefElement(E[] eArr, long j) {
        return UnsafeAccess.UNSAFE.getObject(eArr, j);
    }

    public static <E> E lvRefElement(E[] eArr, long j) {
        return UnsafeAccess.UNSAFE.getObjectVolatile(eArr, j);
    }

    public static long calcRefElementOffset(long j) {
        return REF_ARRAY_BASE + (j << REF_ELEMENT_SHIFT);
    }

    public static long calcCircularRefElementOffset(long j, long j2) {
        return REF_ARRAY_BASE + ((j & j2) << REF_ELEMENT_SHIFT);
    }

    public static <E> E[] allocateRefArray(int i) {
        return (Object[]) new Object[i];
    }
}
