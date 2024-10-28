package j$.util;

import java.util.Arrays;

public final class Objects {
    public static boolean deepEquals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return DesugarArrays.deepEquals0(obj, obj2);
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static <T> T requireNonNull(T t) {
        t.getClass();
        return t;
    }

    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static Object requireNonNullElse(Object obj, Object obj2) {
        return obj != null ? obj : requireNonNull(obj2, "defaultObj");
    }

    public static String toString(Object obj, String str) {
        return obj != null ? obj.toString() : str;
    }
}
