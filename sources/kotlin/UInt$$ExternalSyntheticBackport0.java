package kotlin;

import org.videolan.resources.Constants;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UInt$$ExternalSyntheticBackport0 {
    public static /* synthetic */ int m(boolean z) {
        return z ? 1231 : 1237;
    }

    public static /* synthetic */ AssertionError m(String str, Throwable th) {
        try {
            return AssertionError.class.getDeclaredConstructor(new Class[]{String.class, Throwable.class}).newInstance(new Object[]{str, th});
        } catch (Exception unused) {
            return new AssertionError(str);
        }
    }

    public static /* synthetic */ String m(long j, int i) {
        if (j == 0) {
            return Constants.GROUP_VIDEOS_FOLDER;
        }
        if (j > 0) {
            return Long.toString(j, i);
        }
        if (i < 2 || i > 36) {
            i = 10;
        }
        int i2 = 64;
        char[] cArr = new char[64];
        int i3 = i - 1;
        if ((i & i3) == 0) {
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            do {
                i2--;
                cArr[i2] = Character.forDigit(((int) j) & i3, i);
                j >>>= numberOfTrailingZeros;
            } while (j != 0);
        } else {
            long m$1 = (i & 1) == 0 ? (j >>> 1) / ((long) (i >>> 1)) : m$1(j, (long) i);
            long j2 = (long) i;
            cArr[63] = Character.forDigit((int) (j - (m$1 * j2)), i);
            i2 = 63;
            while (m$1 > 0) {
                i2--;
                cArr[i2] = Character.forDigit((int) (m$1 % j2), i);
                m$1 /= j2;
            }
        }
        return new String(cArr, i2, 64 - i2);
    }

    public static /* synthetic */ void m(Throwable th, Throwable th2) {
        try {
            Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class}).invoke(th, new Object[]{th2});
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ int m$1(int i, int i2) {
        return (int) ((((long) i) & 4294967295L) / (((long) i2) & 4294967295L));
    }

    public static /* synthetic */ long m$1(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1;
        }
        if (j >= 0) {
            return j / j2;
        }
        int i = 1;
        long j3 = ((j >>> 1) / j2) << 1;
        if (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE)) {
            i = 0;
        }
        return j3 + ((long) i);
    }

    public static /* synthetic */ String m$1(long j, int i) {
        if (j == 0) {
            return Constants.GROUP_VIDEOS_FOLDER;
        }
        if (j > 0) {
            return Long.toString(j, i);
        }
        if (i < 2 || i > 36) {
            i = 10;
        }
        int i2 = 64;
        char[] cArr = new char[64];
        int i3 = i - 1;
        if ((i & i3) == 0) {
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            do {
                i2--;
                cArr[i2] = Character.forDigit(((int) j) & i3, i);
                j >>>= numberOfTrailingZeros;
            } while (j != 0);
        } else {
            long m$1 = (i & 1) == 0 ? (j >>> 1) / ((long) (i >>> 1)) : m$1(j, (long) i);
            long j2 = (long) i;
            cArr[63] = Character.forDigit((int) (j - (m$1 * j2)), i);
            i2 = 63;
            while (m$1 > 0) {
                i2--;
                cArr[i2] = Character.forDigit((int) (m$1 % j2), i);
                m$1 /= j2;
            }
        }
        return new String(cArr, i2, 64 - i2);
    }

    public static /* synthetic */ int m$2(int i, int i2) {
        return (int) ((((long) i) & 4294967295L) % (((long) i2) & 4294967295L));
    }

    public static /* synthetic */ String m$2(long j, int i) {
        if (j == 0) {
            return Constants.GROUP_VIDEOS_FOLDER;
        }
        if (j > 0) {
            return Long.toString(j, i);
        }
        if (i < 2 || i > 36) {
            i = 10;
        }
        int i2 = 64;
        char[] cArr = new char[64];
        int i3 = i - 1;
        if ((i & i3) == 0) {
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            do {
                i2--;
                cArr[i2] = Character.forDigit(((int) j) & i3, i);
                j >>>= numberOfTrailingZeros;
            } while (j != 0);
        } else {
            long m$1 = (i & 1) == 0 ? (j >>> 1) / ((long) (i >>> 1)) : m$1(j, (long) i);
            long j2 = (long) i;
            cArr[63] = Character.forDigit((int) (j - (m$1 * j2)), i);
            i2 = 63;
            while (m$1 > 0) {
                i2--;
                cArr[i2] = Character.forDigit((int) (m$1 % j2), i);
                m$1 /= j2;
            }
        }
        return new String(cArr, i2, 64 - i2);
    }
}
