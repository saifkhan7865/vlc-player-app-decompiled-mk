package org.bouncycastle.math.ec.rfc8032;

import org.bouncycastle.util.Integers;

abstract class ScalarUtil {
    private static final long M = 4294967295L;

    ScalarUtil() {
    }

    static void addShifted_NP(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        int i3 = i;
        int i4 = i2 >>> 5;
        int i5 = i2 & 31;
        long j = 0;
        if (i5 == 0) {
            long j2 = 0;
            for (int i6 = i4; i6 <= i3; i6++) {
                int i7 = i6 - i4;
                long j3 = j + (((long) iArr[i6]) & 4294967295L) + (((long) iArr3[i7]) & 4294967295L);
                long j4 = j2 + (((long) iArr3[i6]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L);
                iArr3[i6] = (int) j4;
                j2 = j4 >>> 32;
                long j5 = j3 + (((long) iArr3[i7]) & 4294967295L);
                iArr[i6] = (int) j5;
                j = j5 >>> 32;
            }
            return;
        }
        int i8 = i4;
        long j6 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i8 <= i3) {
            int i12 = i8 - i4;
            int i13 = iArr3[i12];
            int i14 = -i5;
            long j7 = j + (((long) iArr[i8]) & 4294967295L) + (((long) ((i13 << i5) | (i9 >>> i14))) & 4294967295L);
            int i15 = iArr2[i12];
            long j8 = j6 + (((long) iArr3[i8]) & 4294967295L) + (((long) ((i15 << i5) | (i10 >>> i14))) & 4294967295L);
            iArr3[i8] = (int) j8;
            j6 = j8 >>> 32;
            int i16 = iArr3[i12];
            long j9 = j7 + (((long) ((i11 >>> i14) | (i16 << i5))) & 4294967295L);
            iArr[i8] = (int) j9;
            j = j9 >>> 32;
            i8++;
            i10 = i15;
            i11 = i16;
            i9 = i13;
            i4 = i4;
            i3 = i;
        }
    }

    static void addShifted_UV(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i;
        int i4 = i2 >>> 5;
        int i5 = i2 & 31;
        long j = 0;
        if (i5 == 0) {
            long j2 = 0;
            for (int i6 = i4; i6 <= i3; i6++) {
                long j3 = j + (((long) iArr[i6]) & 4294967295L);
                long j4 = j2 + (((long) iArr2[i6]) & 4294967295L);
                int i7 = i6 - i4;
                long j5 = j3 + (((long) iArr3[i7]) & 4294967295L);
                long j6 = j4 + (((long) iArr4[i7]) & 4294967295L);
                iArr[i6] = (int) j5;
                j = j5 >>> 32;
                iArr2[i6] = (int) j6;
                j2 = j6 >>> 32;
            }
            return;
        }
        int i8 = i4;
        long j7 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 <= i3) {
            int i11 = i8 - i4;
            int i12 = iArr3[i11];
            int i13 = iArr4[i11];
            int i14 = -i5;
            long j8 = j + (((long) iArr[i8]) & 4294967295L);
            long j9 = j8 + (((long) ((i9 >>> i14) | (i12 << i5))) & 4294967295L);
            long j10 = j7 + (((long) iArr2[i8]) & 4294967295L) + (((long) ((i10 >>> i14) | (i13 << i5))) & 4294967295L);
            iArr[i8] = (int) j9;
            j = j9 >>> 32;
            iArr2[i8] = (int) j10;
            j7 = j10 >>> 32;
            i8++;
            i3 = i;
            i10 = i13;
            i9 = i12;
            i4 = i4;
        }
    }

    static int getBitLength(int i, int[] iArr) {
        int i2 = iArr[i] >> 31;
        while (i > 0 && iArr[i] == i2) {
            i--;
        }
        return ((i * 32) + 32) - Integers.numberOfLeadingZeros(iArr[i] ^ i2);
    }

    static int getBitLengthPositive(int i, int[] iArr) {
        while (i > 0 && iArr[i] == 0) {
            i--;
        }
        return ((i * 32) + 32) - Integers.numberOfLeadingZeros(iArr[i]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK, PHI: r3 
      PHI: (r3v1 int) = (r3v0 int), (r3v3 int) binds: [B:0:0x0000, B:7:0x0012] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean lessThan(int r3, int[] r4, int[] r5) {
        /*
        L_0x0000:
            r0 = r4[r3]
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            int r0 = r0 + r1
            r2 = r5[r3]
            int r2 = r2 + r1
            if (r0 >= r2) goto L_0x000c
            r3 = 1
            return r3
        L_0x000c:
            r1 = 0
            if (r0 <= r2) goto L_0x0010
            return r1
        L_0x0010:
            int r3 = r3 + -1
            if (r3 >= 0) goto L_0x0000
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.math.ec.rfc8032.ScalarUtil.lessThan(int, int[], int[]):boolean");
    }

    static void subShifted_NP(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        int i3 = i;
        int i4 = i2 >>> 5;
        int i5 = i2 & 31;
        long j = 0;
        if (i5 == 0) {
            long j2 = 0;
            for (int i6 = i4; i6 <= i3; i6++) {
                int i7 = i6 - i4;
                long j3 = (j + (((long) iArr[i6]) & 4294967295L)) - (((long) iArr3[i7]) & 4294967295L);
                long j4 = (j2 + (((long) iArr3[i6]) & 4294967295L)) - (((long) iArr2[i7]) & 4294967295L);
                iArr3[i6] = (int) j4;
                j2 = j4 >> 32;
                long j5 = j3 - (((long) iArr3[i7]) & 4294967295L);
                iArr[i6] = (int) j5;
                j = j5 >> 32;
            }
            return;
        }
        int i8 = i4;
        long j6 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i8 <= i3) {
            int i12 = i8 - i4;
            int i13 = iArr3[i12];
            int i14 = -i5;
            long j7 = (j + (((long) iArr[i8]) & 4294967295L)) - (((long) ((i13 << i5) | (i9 >>> i14))) & 4294967295L);
            int i15 = iArr2[i12];
            long j8 = (j6 + (((long) iArr3[i8]) & 4294967295L)) - (((long) ((i15 << i5) | (i10 >>> i14))) & 4294967295L);
            iArr3[i8] = (int) j8;
            j6 = j8 >> 32;
            int i16 = iArr3[i12];
            long j9 = j7 - (((long) ((i11 >>> i14) | (i16 << i5))) & 4294967295L);
            iArr[i8] = (int) j9;
            j = j9 >> 32;
            i8++;
            i10 = i15;
            i11 = i16;
            i9 = i13;
            i4 = i4;
            i3 = i;
        }
    }

    static void subShifted_UV(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i;
        int i4 = i2 >>> 5;
        int i5 = i2 & 31;
        long j = 0;
        if (i5 == 0) {
            long j2 = 0;
            for (int i6 = i4; i6 <= i3; i6++) {
                long j3 = j + (((long) iArr[i6]) & 4294967295L);
                long j4 = j2 + (((long) iArr2[i6]) & 4294967295L);
                int i7 = i6 - i4;
                long j5 = j3 - (((long) iArr3[i7]) & 4294967295L);
                long j6 = j4 - (((long) iArr4[i7]) & 4294967295L);
                iArr[i6] = (int) j5;
                j = j5 >> 32;
                iArr2[i6] = (int) j6;
                j2 = j6 >> 32;
            }
            return;
        }
        int i8 = i4;
        long j7 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 <= i3) {
            int i11 = i8 - i4;
            int i12 = iArr3[i11];
            int i13 = iArr4[i11];
            int i14 = -i5;
            long j8 = j + (((long) iArr[i8]) & 4294967295L);
            long j9 = j8 - (((long) ((i9 >>> i14) | (i12 << i5))) & 4294967295L);
            long j10 = (j7 + (((long) iArr2[i8]) & 4294967295L)) - (((long) ((i10 >>> i14) | (i13 << i5))) & 4294967295L);
            iArr[i8] = (int) j9;
            j = j9 >> 32;
            iArr2[i8] = (int) j10;
            j7 = j10 >> 32;
            i8++;
            i3 = i;
            i10 = i13;
            i9 = i12;
            i4 = i4;
        }
    }
}
