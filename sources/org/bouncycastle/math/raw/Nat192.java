package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

public abstract class Nat192 {
    private static final long M = 4294967295L;

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (((long) iArr3[4]) & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L) + (((long) iArr3[5]) & 4294967295L);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addTo(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        long j = (((long) i3) & 4294967295L) + (((long) iArr[i]) & 4294967295L) + (((long) iArr2[i2]) & 4294967295L);
        iArr2[i2] = (int) j;
        int i4 = i2 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i + 1]) & 4294967295L) + (((long) iArr2[i4]) & 4294967295L);
        iArr2[i4] = (int) j2;
        int i5 = i2 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i + 2]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j3;
        int i6 = i2 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i + 3]) & 4294967295L) + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j4;
        int i7 = i2 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i + 4]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j5;
        int i8 = i2 + 5;
        long j6 = (j5 >>> 32) + (((long) iArr[i + 5]) & 4294967295L) + (4294967295L & ((long) iArr2[i8]));
        iArr2[i8] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr2[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr2[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr2[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr2[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L);
        iArr2[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & 4294967295L) + (4294967295L & ((long) iArr2[5]));
        iArr2[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i, int[] iArr2, int i2) {
        long j = (((long) iArr[i]) & 4294967295L) + (((long) iArr2[i2]) & 4294967295L);
        int i3 = (int) j;
        iArr[i] = i3;
        iArr2[i2] = i3;
        int i4 = i + 1;
        int i5 = i2 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i4]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L);
        int i6 = (int) j2;
        iArr[i4] = i6;
        iArr2[i5] = i6;
        int i7 = i + 2;
        int i8 = i2 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i7]) & 4294967295L) + (((long) iArr2[i8]) & 4294967295L);
        int i9 = (int) j3;
        iArr[i7] = i9;
        iArr2[i8] = i9;
        int i10 = i + 3;
        int i11 = i2 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i10]) & 4294967295L) + (((long) iArr2[i11]) & 4294967295L);
        int i12 = (int) j4;
        iArr[i10] = i12;
        iArr2[i11] = i12;
        int i13 = i + 4;
        int i14 = i2 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i13]) & 4294967295L) + (((long) iArr2[i14]) & 4294967295L);
        int i15 = (int) j5;
        iArr[i13] = i15;
        iArr2[i14] = i15;
        int i16 = i + 5;
        int i17 = i2 + 5;
        long j6 = (j5 >>> 32) + (((long) iArr[i16]) & 4294967295L) + (4294967295L & ((long) iArr2[i17]));
        int i18 = (int) j6;
        iArr[i16] = i18;
        iArr2[i17] = i18;
        return (int) (j6 >>> 32);
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        iArr2[i2] = iArr[i];
        iArr2[i2 + 1] = iArr[i + 1];
        iArr2[i2 + 2] = iArr[i + 2];
        iArr2[i2 + 3] = iArr[i + 3];
        iArr2[i2 + 4] = iArr[i + 4];
        iArr2[i2 + 5] = iArr[i + 5];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
    }

    public static void copy64(long[] jArr, int i, long[] jArr2, int i2) {
        jArr2[i2] = jArr[i];
        jArr2[i2 + 1] = jArr[i + 1];
        jArr2[i2 + 2] = jArr[i + 2];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    public static int[] create() {
        return new int[6];
    }

    public static long[] create64() {
        return new long[3];
    }

    public static int[] createExt() {
        return new int[12];
    }

    public static long[] createExt64() {
        return new long[6];
    }

    public static boolean diff(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        boolean gte = gte(iArr, i, iArr2, i2);
        if (gte) {
            sub(iArr, i, iArr2, i2, iArr3, i3);
        } else {
            sub(iArr2, i2, iArr, i, iArr3, i3);
        }
        return gte;
    }

    public static boolean eq(int[] iArr, int[] iArr2) {
        for (int i = 5; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i = 2; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        for (int i = 0; i < 6; i++) {
            create[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        for (int i = 0; i < 3; i++) {
            create64[i] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return create64;
    }

    public static int getBit(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            int i3 = i >> 5;
            if (i3 < 0 || i3 >= 6) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    public static boolean gte(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 5; i3 >= 0; i3--) {
            int i4 = iArr[i + i3] ^ Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE ^ iArr2[i2 + i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i = 5; i >= 0; i--) {
            int i2 = iArr[i] ^ Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 6; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 3; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 6; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i = 0; i < 3; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((long) iArr2[i2]) & 4294967295L;
        long j2 = ((long) iArr2[i2 + 1]) & 4294967295L;
        long j3 = ((long) iArr2[i2 + 2]) & 4294967295L;
        long j4 = ((long) iArr2[i2 + 3]) & 4294967295L;
        long j5 = ((long) iArr2[i2 + 4]) & 4294967295L;
        long j6 = ((long) iArr2[i2 + 5]) & 4294967295L;
        long j7 = ((long) iArr[i]) & 4294967295L;
        long j8 = j7 * j;
        long j9 = j;
        iArr3[i3] = (int) j8;
        long j10 = (j8 >>> 32) + (j7 * j2);
        long j11 = j2;
        iArr3[i3 + 1] = (int) j10;
        long j12 = (j10 >>> 32) + (j7 * j3);
        iArr3[i3 + 2] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j4);
        iArr3[i3 + 3] = (int) j13;
        long j14 = (j13 >>> 32) + (j7 * j5);
        iArr3[i3 + 4] = (int) j14;
        long j15 = (j14 >>> 32) + (j7 * j6);
        iArr3[i3 + 5] = (int) j15;
        iArr3[i3 + 6] = (int) (j15 >>> 32);
        int i4 = i3;
        int i5 = 1;
        while (i5 < 6) {
            int i6 = i4 + 1;
            long j16 = ((long) iArr[i + i5]) & 4294967295L;
            long j17 = j6;
            long j18 = (j16 * j9) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j18;
            int i7 = i4 + 2;
            int i8 = i5;
            int i9 = i6;
            long j19 = (j18 >>> 32) + (j16 * j11) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j19;
            int i10 = i4 + 3;
            long j20 = (j19 >>> 32) + (j16 * j3) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j20;
            int i11 = i4 + 4;
            int i12 = i4;
            long j21 = (j20 >>> 32) + (j16 * j4) + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j21;
            int i13 = i12 + 5;
            long j22 = (j21 >>> 32) + (j16 * j5) + (((long) iArr3[i13]) & 4294967295L);
            iArr3[i13] = (int) j22;
            int i14 = i12 + 6;
            long j23 = (j22 >>> 32) + (j16 * j17) + (((long) iArr3[i14]) & 4294967295L);
            iArr3[i14] = (int) j23;
            iArr3[i12 + 7] = (int) (j23 >>> 32);
            i5 = i8 + 1;
            i4 = i9;
            j6 = j17;
            j3 = j3;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((long) iArr2[0]) & 4294967295L;
        long j2 = ((long) iArr2[1]) & 4294967295L;
        long j3 = ((long) iArr2[3]) & 4294967295L;
        long j4 = ((long) iArr2[4]) & 4294967295L;
        long j5 = ((long) iArr2[2]) & 4294967295L;
        long j6 = ((long) iArr2[5]) & 4294967295L;
        long j7 = ((long) iArr[0]) & 4294967295L;
        long j8 = j7 * j;
        iArr3[0] = (int) j8;
        char c = ' ';
        long j9 = (j8 >>> 32) + (j7 * j2);
        iArr3[1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j5);
        iArr3[2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j3);
        iArr3[3] = (int) j11;
        long j12 = (j11 >>> 32) + (j7 * j4);
        iArr3[4] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j6);
        iArr3[5] = (int) j13;
        int i = (int) (j13 >>> 32);
        iArr3[6] = i;
        int i2 = 1;
        for (int i3 = 6; i2 < i3; i3 = 6) {
            long j14 = ((long) iArr[i2]) & 4294967295L;
            long j15 = (j14 * j) + (((long) iArr3[i2]) & 4294967295L);
            long j16 = j;
            iArr3[i2] = (int) j15;
            int i4 = i2 + 1;
            long j17 = j2;
            long j18 = (j15 >>> c) + (j14 * j2) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j18;
            int i5 = i2 + 2;
            long j19 = (j18 >>> 32) + (j14 * j5) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j19;
            int i6 = i2 + 3;
            long j20 = (j19 >>> 32) + (j14 * j3) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j20;
            int i7 = i2 + 4;
            long j21 = (j20 >>> 32) + (j14 * j4) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j21;
            c = ' ';
            int i8 = i2 + 5;
            long j22 = (j21 >>> 32) + (j14 * j6) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j22;
            iArr3[i2 + 6] = (int) (j22 >>> 32);
            j = j16;
            i2 = i4;
            j2 = j17;
        }
    }

    public static long mul33Add(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = ((long) i) & 4294967295L;
        long j2 = ((long) iArr[i2]) & 4294967295L;
        long j3 = (j * j2) + (((long) iArr2[i3]) & 4294967295L);
        iArr3[i4] = (int) j3;
        long j4 = ((long) iArr[i2 + 1]) & 4294967295L;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (((long) iArr2[i3 + 1]) & 4294967295L);
        iArr3[i4 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = ((long) iArr[i2 + 2]) & 4294967295L;
        long j8 = j6 + (j * j7) + j4 + (((long) iArr2[i3 + 2]) & 4294967295L);
        iArr3[i4 + 2] = (int) j8;
        long j9 = ((long) iArr[i2 + 3]) & 4294967295L;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (((long) iArr2[i3 + 3]) & 4294967295L);
        iArr3[i4 + 3] = (int) j10;
        long j11 = ((long) iArr[i2 + 4]) & 4294967295L;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (((long) iArr2[i3 + 4]) & 4294967295L);
        iArr3[i4 + 4] = (int) j12;
        long j13 = ((long) iArr[i2 + 5]) & 4294967295L;
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (4294967295L & ((long) iArr2[i3 + 5]));
        iArr3[i4 + 5] = (int) j14;
        return (j14 >>> 32) + j13;
    }

    public static int mul33DWordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = ((long) i) & 4294967295L;
        long j3 = j & 4294967295L;
        long j4 = (j2 * j3) + (((long) iArr[i2]) & 4294967295L);
        iArr[i2] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int i3 = i2 + 1;
        long j7 = (j4 >>> 32) + j6 + (((long) iArr[i3]) & 4294967295L);
        iArr[i3] = (int) j7;
        int i4 = i2 + 2;
        long j8 = (j7 >>> 32) + j5 + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j8;
        long j9 = j8 >>> 32;
        int i5 = i2 + 3;
        long j10 = j9 + (4294967295L & ((long) iArr[i5]));
        iArr[i5] = (int) j10;
        if ((j10 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(6, iArr, i2, 4);
    }

    public static int mul33WordAdd(int i, int i2, int[] iArr, int i3) {
        long j = ((long) i2) & 4294967295L;
        long j2 = ((((long) i) & 4294967295L) * j) + (((long) iArr[i3]) & 4294967295L);
        iArr[i3] = (int) j2;
        int i4 = i3 + 1;
        long j3 = (j2 >>> 32) + j + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j3;
        long j4 = j3 >>> 32;
        int i5 = i3 + 2;
        long j5 = j4 + (4294967295L & ((long) iArr[i5]));
        iArr[i5] = (int) j5;
        if ((j5 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(6, iArr, i3, 3);
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((long) iArr2[i2]) & 4294967295L;
        long j2 = ((long) iArr2[i2 + 1]) & 4294967295L;
        long j3 = ((long) iArr2[i2 + 2]) & 4294967295L;
        long j4 = ((long) iArr2[i2 + 3]) & 4294967295L;
        long j5 = ((long) iArr2[i2 + 4]) & 4294967295L;
        long j6 = ((long) iArr2[i2 + 5]) & 4294967295L;
        long j7 = 0;
        int i4 = 0;
        int i5 = i3;
        while (i4 < 6) {
            long j8 = ((long) iArr[i + i4]) & 4294967295L;
            long j9 = j;
            long j10 = (j8 * j) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j10;
            int i6 = i5 + 1;
            long j11 = (j10 >>> 32) + (j8 * j2) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j11;
            int i7 = i5 + 2;
            long j12 = (j11 >>> 32) + (j8 * j3) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j12;
            int i8 = i5 + 3;
            long j13 = (j12 >>> 32) + (j8 * j4) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j13;
            int i9 = i5 + 4;
            long j14 = (j13 >>> 32) + (j8 * j5) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j14;
            int i10 = i5 + 5;
            long j15 = (j14 >>> 32) + (j8 * j6) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j15;
            int i11 = i5 + 6;
            long j16 = (j15 >>> 32) + (((long) iArr3[i11]) & 4294967295L) + j7;
            iArr3[i11] = (int) j16;
            j7 = j16 >>> 32;
            i4++;
            j = j9;
            i5 = i6;
            j2 = j2;
        }
        return (int) j7;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = 0;
        long j = ((long) iArr2[0]) & 4294967295L;
        long j2 = ((long) iArr2[1]) & 4294967295L;
        long j3 = ((long) iArr2[2]) & 4294967295L;
        long j4 = ((long) iArr2[3]) & 4294967295L;
        long j5 = ((long) iArr2[4]) & 4294967295L;
        long j6 = ((long) iArr2[5]) & 4294967295L;
        long j7 = 0;
        while (i < 6) {
            long j8 = j6;
            long j9 = ((long) iArr[i]) & 4294967295L;
            long j10 = j;
            long j11 = (((long) iArr3[i]) & 4294967295L) + (j9 * j);
            iArr3[i] = (int) j11;
            int i2 = i + 1;
            long j12 = (j11 >>> 32) + (j9 * j2) + (((long) iArr3[i2]) & 4294967295L);
            iArr3[i2] = (int) j12;
            int i3 = i + 2;
            long j13 = (j12 >>> 32) + (j9 * j3) + (((long) iArr3[i3]) & 4294967295L);
            iArr3[i3] = (int) j13;
            int i4 = i + 3;
            long j14 = (j13 >>> 32) + (j9 * j4) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j14;
            int i5 = i + 4;
            long j15 = (j14 >>> 32) + (j9 * j5) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j15;
            int i6 = i + 5;
            long j16 = (j15 >>> 32) + (j9 * j8) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j16;
            int i7 = i + 6;
            long j17 = (j16 >>> 32) + (((long) iArr3[i7]) & 4294967295L) + j7;
            iArr3[i7] = (int) j17;
            j7 = j17 >>> 32;
            i = i2;
            j6 = j8;
            j = j10;
            j2 = j2;
        }
        return (int) j7;
    }

    public static int mulWord(int i, int[] iArr, int[] iArr2, int i2) {
        long j = ((long) i) & 4294967295L;
        long j2 = 0;
        int i3 = 0;
        do {
            long j3 = j2 + ((((long) iArr[i3]) & 4294967295L) * j);
            iArr2[i2 + i3] = (int) j3;
            j2 = j3 >>> 32;
            i3++;
        } while (i3 < 6);
        return (int) j2;
    }

    public static int mulWordAddExt(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = ((long) i) & 4294967295L;
        long j2 = ((((long) iArr[i2]) & 4294967295L) * j) + (((long) iArr2[i3]) & 4294967295L);
        iArr2[i3] = (int) j2;
        int i4 = i3 + 1;
        long j3 = (j2 >>> 32) + ((((long) iArr[i2 + 1]) & 4294967295L) * j) + (((long) iArr2[i4]) & 4294967295L);
        iArr2[i4] = (int) j3;
        int i5 = i3 + 2;
        long j4 = (j3 >>> 32) + ((((long) iArr[i2 + 2]) & 4294967295L) * j) + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j4;
        int i6 = i3 + 3;
        long j5 = (j4 >>> 32) + ((((long) iArr[i2 + 3]) & 4294967295L) * j) + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j5;
        int i7 = i3 + 4;
        long j6 = (j5 >>> 32) + ((((long) iArr[i2 + 4]) & 4294967295L) * j) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j6;
        int i8 = i3 + 5;
        long j7 = (j6 >>> 32) + (j * (((long) iArr[i2 + 5]) & 4294967295L)) + (((long) iArr2[i8]) & 4294967295L);
        iArr2[i8] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int mulWordDwordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = ((long) i) & 4294967295L;
        long j3 = ((j & 4294967295L) * j2) + (((long) iArr[i2]) & 4294967295L);
        iArr[i2] = (int) j3;
        long j4 = j2 * (j >>> 32);
        int i3 = i2 + 1;
        long j5 = (j3 >>> 32) + j4 + (((long) iArr[i3]) & 4294967295L);
        iArr[i3] = (int) j5;
        int i4 = i2 + 2;
        long j6 = (j5 >>> 32) + (4294967295L & ((long) iArr[i4]));
        iArr[i4] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(6, iArr, i2, 3);
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        long j = ((long) iArr[i]) & 4294967295L;
        int i3 = 0;
        int i4 = 12;
        int i5 = 5;
        while (true) {
            int i6 = i5 - 1;
            long j2 = ((long) iArr[i + i5]) & 4294967295L;
            long j3 = j2 * j2;
            iArr2[i2 + (i4 - 1)] = (i3 << 31) | ((int) (j3 >>> 33));
            i4 -= 2;
            iArr2[i2 + i4] = (int) (j3 >>> 1);
            i3 = (int) j3;
            if (i6 <= 0) {
                long j4 = j * j;
                iArr2[i2] = (int) j4;
                long j5 = ((long) iArr[i + 1]) & 4294967295L;
                int i7 = i2 + 2;
                long j6 = ((j4 >>> 33) | (((long) (i3 << 31)) & 4294967295L)) + (j5 * j);
                int i8 = (int) j6;
                iArr2[i2 + 1] = (i8 << 1) | (((int) (j4 >>> 32)) & 1);
                int i9 = i8 >>> 31;
                long j7 = (((long) iArr2[i7]) & 4294967295L) + (j6 >>> 32);
                long j8 = ((long) iArr[i + 2]) & 4294967295L;
                int i10 = i2 + 3;
                int i11 = i2 + 4;
                int i12 = i10;
                long j9 = ((long) iArr2[i10]) & 4294967295L;
                long j10 = j7 + (j8 * j);
                int i13 = (int) j10;
                iArr2[i7] = (i13 << 1) | i9;
                long j11 = j9 + (j10 >>> 32) + (j8 * j5);
                long j12 = (((long) iArr2[i11]) & 4294967295L) + (j11 >>> 32);
                long j13 = j8;
                long j14 = ((long) iArr[i + 3]) & 4294967295L;
                int i14 = i2 + 5;
                long j15 = j5;
                int i15 = i11;
                long j16 = (((long) iArr2[i14]) & 4294967295L) + (j12 >>> 32);
                int i16 = i2 + 6;
                long j17 = j12 & 4294967295L;
                long j18 = (((long) iArr2[i16]) & 4294967295L) + (j16 >>> 32);
                long j19 = (j11 & 4294967295L) + (j14 * j);
                int i17 = (int) j19;
                iArr2[i12] = (i17 << 1) | (i13 >>> 31);
                long j20 = j17 + (j19 >>> 32) + (j14 * j15);
                long j21 = (j16 & 4294967295L) + (j20 >>> 32) + (j14 * j13);
                long j22 = j18 + (j21 >>> 32);
                long j23 = j14;
                long j24 = ((long) iArr[i + 4]) & 4294967295L;
                int i18 = i2 + 7;
                long j25 = (((long) iArr2[i18]) & 4294967295L) + (j22 >>> 32);
                int i19 = i2 + 8;
                int i20 = i18;
                long j26 = (((long) iArr2[i19]) & 4294967295L) + (j25 >>> 32);
                long j27 = (j20 & 4294967295L) + (j24 * j);
                int i21 = (int) j27;
                iArr2[i15] = (i21 << 1) | (i17 >>> 31);
                int i22 = i21 >>> 31;
                long j28 = (j21 & 4294967295L) + (j27 >>> 32) + (j24 * j15);
                long j29 = (j22 & 4294967295L) + (j28 >>> 32) + (j24 * j13);
                long j30 = (j25 & 4294967295L) + (j29 >>> 32) + (j24 * j23);
                long j31 = j26 + (j30 >>> 32);
                long j32 = j24;
                long j33 = ((long) iArr[i + 5]) & 4294967295L;
                int i23 = i2 + 9;
                int i24 = i19;
                int i25 = i23;
                long j34 = (((long) iArr2[i23]) & 4294967295L) + (j31 >>> 32);
                int i26 = i2 + 10;
                long j35 = (j28 & 4294967295L) + (j * j33);
                int i27 = (int) j35;
                iArr2[i14] = (i27 << 1) | i22;
                long j36 = (j29 & 4294967295L) + (j35 >>> 32) + (j33 * j15);
                long j37 = (j30 & 4294967295L) + (j36 >>> 32) + (j33 * j13);
                long j38 = (j31 & 4294967295L) + (j37 >>> 32) + (j33 * j23);
                long j39 = (j34 & 4294967295L) + (j38 >>> 32) + (j33 * j32);
                long j40 = (((long) iArr2[i26]) & 4294967295L) + (j34 >>> 32) + (j39 >>> 32);
                int i28 = (int) j36;
                iArr2[i16] = (i27 >>> 31) | (i28 << 1);
                int i29 = (int) j37;
                iArr2[i20] = (i28 >>> 31) | (i29 << 1);
                int i30 = (int) j38;
                iArr2[i24] = (i29 >>> 31) | (i30 << 1);
                int i31 = i30 >>> 31;
                int i32 = (int) j39;
                iArr2[i25] = i31 | (i32 << 1);
                int i33 = i32 >>> 31;
                int i34 = (int) j40;
                iArr2[i26] = i33 | (i34 << 1);
                int i35 = i34 >>> 31;
                int i36 = i2 + 11;
                iArr2[i36] = i35 | ((iArr2[i36] + ((int) (j40 >>> 32))) << 1);
                return;
            }
            i5 = i6;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & 4294967295L;
        int i = 12;
        int i2 = 5;
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            long j2 = ((long) iArr[i2]) & 4294967295L;
            long j3 = j2 * j2;
            iArr2[i - 1] = (i3 << 31) | ((int) (j3 >>> 33));
            i -= 2;
            iArr2[i] = (int) (j3 >>> 1);
            i3 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | (((long) (i3 << 31)) & 4294967295L);
                iArr2[0] = (int) j4;
                long j6 = ((long) iArr[1]) & 4294967295L;
                long j7 = j5 + (j6 * j);
                int i5 = (int) j7;
                iArr2[1] = (i5 << 1) | (((int) (j4 >>> 32)) & 1);
                long j8 = (((long) iArr2[2]) & 4294967295L) + (j7 >>> 32);
                long j9 = ((long) iArr[2]) & 4294967295L;
                long j10 = j6;
                long j11 = j8 + (j9 * j);
                int i6 = (int) j11;
                iArr2[2] = (i6 << 1) | (i5 >>> 31);
                long j12 = (((long) iArr2[3]) & 4294967295L) + (j11 >>> 32) + (j9 * j10);
                long j13 = (((long) iArr2[4]) & 4294967295L) + (j12 >>> 32);
                long j14 = ((long) iArr[3]) & 4294967295L;
                long j15 = j9;
                long j16 = (((long) iArr2[5]) & 4294967295L) + (j13 >>> 32);
                long j17 = j13 & 4294967295L;
                long j18 = (((long) iArr2[6]) & 4294967295L) + (j16 >>> 32);
                long j19 = (j12 & 4294967295L) + (j14 * j);
                int i7 = (int) j19;
                iArr2[3] = (i7 << 1) | (i6 >>> 31);
                long j20 = j17 + (j19 >>> 32) + (j14 * j10);
                long j21 = (j16 & 4294967295L) + (j20 >>> 32) + (j14 * j15);
                long j22 = j18 + (j21 >>> 32);
                long j23 = ((long) iArr[4]) & 4294967295L;
                long j24 = j14;
                long j25 = (((long) iArr2[7]) & 4294967295L) + (j22 >>> 32);
                long j26 = j22 & 4294967295L;
                long j27 = (((long) iArr2[8]) & 4294967295L) + (j25 >>> 32);
                long j28 = (j20 & 4294967295L) + (j23 * j);
                int i8 = (int) j28;
                iArr2[4] = (i8 << 1) | (i7 >>> 31);
                long j29 = (j21 & 4294967295L) + (j28 >>> 32) + (j23 * j10);
                long j30 = j26 + (j29 >>> 32) + (j23 * j15);
                long j31 = (j25 & 4294967295L) + (j30 >>> 32) + (j23 * j24);
                long j32 = j27 + (j31 >>> 32);
                long j33 = j23;
                long j34 = ((long) iArr[5]) & 4294967295L;
                long j35 = j31 & 4294967295L;
                long j36 = (((long) iArr2[9]) & 4294967295L) + (j32 >>> 32);
                long j37 = 4294967295L & j36;
                long j38 = (j29 & 4294967295L) + (j * j34);
                int i9 = (int) j38;
                iArr2[5] = (i9 << 1) | (i8 >>> 31);
                long j39 = (j30 & 4294967295L) + (j38 >>> 32) + (j34 * j10);
                long j40 = j35 + (j39 >>> 32) + (j34 * j15);
                long j41 = (j32 & 4294967295L) + (j40 >>> 32) + (j34 * j24);
                long j42 = j37 + (j41 >>> 32) + (j34 * j33);
                long j43 = (((long) iArr2[10]) & 4294967295L) + (j36 >>> 32) + (j42 >>> 32);
                int i10 = (int) j39;
                iArr2[6] = (i9 >>> 31) | (i10 << 1);
                int i11 = (int) j40;
                iArr2[7] = (i10 >>> 31) | (i11 << 1);
                int i12 = (int) j41;
                iArr2[8] = (i11 >>> 31) | (i12 << 1);
                int i13 = (int) j42;
                iArr2[9] = (i12 >>> 31) | (i13 << 1);
                int i14 = i13 >>> 31;
                int i15 = (int) j43;
                iArr2[10] = i14 | (i15 << 1);
                iArr2[11] = (i15 >>> 31) | ((iArr2[11] + ((int) (j43 >>> 32))) << 1);
                return;
            }
            i2 = i4;
        }
    }

    public static int sub(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = (((long) iArr[i]) & 4294967295L) - (((long) iArr2[i2]) & 4294967295L);
        iArr3[i3] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[i + 1]) & 4294967295L) - (((long) iArr2[i2 + 1]) & 4294967295L));
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[i + 2]) & 4294967295L) - (((long) iArr2[i2 + 2]) & 4294967295L));
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[i + 3]) & 4294967295L) - (((long) iArr2[i2 + 3]) & 4294967295L));
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[i + 4]) & 4294967295L) - (((long) iArr2[i2 + 4]) & 4294967295L));
        iArr3[i3 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr[i + 5]) & 4294967295L) - (((long) iArr2[i2 + 5]) & 4294967295L));
        iArr3[i3 + 5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[4]) & 4294967295L) - (((long) iArr2[4]) & 4294967295L));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr[5]) & 4294967295L) - (((long) iArr2[5]) & 4294967295L));
        iArr3[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((((long) iArr3[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L)) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + (((((long) iArr3[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L)) - (((long) iArr2[1]) & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + (((((long) iArr3[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L)) - (((long) iArr2[2]) & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + (((((long) iArr3[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L)) - (((long) iArr2[3]) & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + (((((long) iArr3[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L)) - (((long) iArr2[4]) & 4294967295L));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + (((((long) iArr3[5]) & 4294967295L) - (((long) iArr[5]) & 4294967295L)) - (((long) iArr2[5]) & 4294967295L));
        iArr3[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int subFrom(int[] iArr, int i, int[] iArr2, int i2) {
        long j = (((long) iArr2[i2]) & 4294967295L) - (((long) iArr[i]) & 4294967295L);
        iArr2[i2] = (int) j;
        int i3 = i2 + 1;
        long j2 = (j >> 32) + ((((long) iArr2[i3]) & 4294967295L) - (((long) iArr[i + 1]) & 4294967295L));
        iArr2[i3] = (int) j2;
        int i4 = i2 + 2;
        long j3 = (j2 >> 32) + ((((long) iArr2[i4]) & 4294967295L) - (((long) iArr[i + 2]) & 4294967295L));
        iArr2[i4] = (int) j3;
        int i5 = i2 + 3;
        long j4 = (j3 >> 32) + ((((long) iArr2[i5]) & 4294967295L) - (((long) iArr[i + 3]) & 4294967295L));
        iArr2[i5] = (int) j4;
        int i6 = i2 + 4;
        long j5 = (j4 >> 32) + ((((long) iArr2[i6]) & 4294967295L) - (((long) iArr[i + 4]) & 4294967295L));
        iArr2[i6] = (int) j5;
        int i7 = i2 + 5;
        long j6 = (j5 >> 32) + ((((long) iArr2[i7]) & 4294967295L) - (((long) iArr[i + 5]) & 4294967295L));
        iArr2[i7] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j = (((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr2[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr2[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr2[5]) & 4294967295L) - (4294967295L & ((long) iArr[5])));
        iArr2[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[24];
        for (int i = 0; i < 6; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (5 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[24];
        for (int i = 0; i < 3; i++) {
            long j = jArr[i];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (2 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
    }
}
