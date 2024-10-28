package j$.util;

import j$.util.Spliterator;
import java.util.Arrays;

public abstract /* synthetic */ class DesugarArrays {
    static boolean deepEquals0(Object obj, Object obj2) {
        if (obj != null) {
            return (!(obj instanceof Object[]) || !(obj2 instanceof Object[])) ? (!(obj instanceof byte[]) || !(obj2 instanceof byte[])) ? (!(obj instanceof short[]) || !(obj2 instanceof short[])) ? (!(obj instanceof int[]) || !(obj2 instanceof int[])) ? (!(obj instanceof long[]) || !(obj2 instanceof long[])) ? (!(obj instanceof char[]) || !(obj2 instanceof char[])) ? (!(obj instanceof float[]) || !(obj2 instanceof float[])) ? (!(obj instanceof double[]) || !(obj2 instanceof double[])) ? (!(obj instanceof boolean[]) || !(obj2 instanceof boolean[])) ? obj.equals(obj2) : Arrays.equals((boolean[]) obj, (boolean[]) obj2) : Arrays.equals((double[]) obj, (double[]) obj2) : Arrays.equals((float[]) obj, (float[]) obj2) : Arrays.equals((char[]) obj, (char[]) obj2) : Arrays.equals((long[]) obj, (long[]) obj2) : Arrays.equals((int[]) obj, (int[]) obj2) : Arrays.equals((short[]) obj, (short[]) obj2) : Arrays.equals((byte[]) obj, (byte[]) obj2) : Arrays.deepEquals((Object[]) obj, (Object[]) obj2);
        }
        throw new NullPointerException("e1 is null!");
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i, int i2) {
        return Spliterators.spliterator(dArr, i, i2, 1040);
    }

    public static Spliterator.OfInt spliterator(int[] iArr, int i, int i2) {
        return Spliterators.spliterator(iArr, i, i2, 1040);
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i, int i2) {
        return Spliterators.spliterator(jArr, i, i2, 1040);
    }

    public static Spliterator spliterator(Object[] objArr, int i, int i2) {
        return Spliterators.spliterator(objArr, i, i2, 1040);
    }
}
