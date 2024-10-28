package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;

final class LinkedArrayQueueUtil {
    LinkedArrayQueueUtil() {
    }

    static int length(Object[] objArr) {
        return objArr.length;
    }

    static long modifiedCalcCircularRefElementOffset(long j, long j2) {
        return UnsafeRefArrayAccess.REF_ARRAY_BASE + ((j & j2) << (UnsafeRefArrayAccess.REF_ELEMENT_SHIFT - 1));
    }

    static long nextArrayOffset(Object[] objArr) {
        return UnsafeRefArrayAccess.REF_ARRAY_BASE + (((long) (length(objArr) - 1)) << UnsafeRefArrayAccess.REF_ELEMENT_SHIFT);
    }
}
