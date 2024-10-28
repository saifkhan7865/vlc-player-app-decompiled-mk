package org.bouncycastle.pqc.crypto.hqc;

class GF2PolynomialCalculator {
    private final int PARAM_N;
    private final long RED_MASK;
    private final int VEC_N_SIZE_64;

    GF2PolynomialCalculator(int i, int i2, long j) {
        this.VEC_N_SIZE_64 = i;
        this.PARAM_N = i2;
        this.RED_MASK = j;
    }

    static void addLongs(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < jArr2.length; i++) {
            jArr[i] = jArr2[i] ^ jArr3[i];
        }
    }

    private void base_mul(long[] jArr, int i, long j, long j2) {
        int i2 = 16;
        long j3 = j2 & 1152921504606846975L;
        long j4 = j3 << 1;
        long j5 = j4 ^ j3;
        long j6 = j3 << 2;
        long j7 = j6 ^ j3;
        long j8 = j5 << 1;
        long j9 = j8 ^ j3;
        long j10 = j3 << 3;
        long j11 = j7 << 1;
        long j12 = j5 << 2;
        long j13 = j9 << 1;
        long[] jArr2 = {0, j3, j4, j5, j6, j7, j8, j9, j10, j10 ^ j3, j11, j11 ^ j3, j12, j12 ^ j3, j13, j3 ^ j13};
        long j14 = 15;
        long j15 = j & 15;
        long j16 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            long j17 = j15 - ((long) i3);
            j16 ^= jArr2[i3] & (-(1 - ((j17 | (-j17)) >>> 63)));
        }
        byte b = 4;
        long j18 = 0;
        while (b < 64) {
            long j19 = (j >> b) & j14;
            int i4 = 0;
            long j20 = 0;
            while (i4 < i2) {
                long j21 = j19 - ((long) i4);
                j20 ^= jArr2[i4] & (-(1 - (((-j21) | j21) >>> 63)));
                i4++;
                jArr2 = jArr2;
                i2 = 16;
            }
            j16 ^= j20 << b;
            j18 ^= j20 >>> (64 - b);
            b = (byte) (b + 4);
            jArr2 = jArr2;
            i2 = 16;
            j14 = 15;
        }
        long[] jArr3 = {-((j2 >> 60) & 1), -((j2 >> 61) & 1), -((j2 >> 62) & 1), -((j2 >> 63) & 1)};
        long j22 = jArr3[0];
        long j23 = (j22 & (j >>> 4)) ^ j18;
        long j24 = jArr3[1];
        long j25 = jArr3[2];
        long j26 = ((((j << 60) & j22) ^ j16) ^ ((j << 61) & j24)) ^ ((j << 62) & j25);
        long j27 = jArr3[3];
        jArr[i] = j26 ^ ((j << 63) & j27);
        jArr[i + 1] = ((j23 ^ ((j >>> 3) & j24)) ^ ((j >>> 2) & j25)) ^ ((j >>> 1) & j27);
    }

    private void karatsuba(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4, long[] jArr4, int i5) {
        int i6 = i4;
        if (i6 == 1) {
            base_mul(jArr, i, jArr2[i2], jArr3[i3]);
            return;
        }
        int i7 = i6 / 2;
        int i8 = (i6 + 1) / 2;
        int i9 = i5 + i8;
        int i10 = i9 + i8;
        int i11 = i + (i8 * 2);
        int i12 = i5 + (i8 * 4);
        karatsuba(jArr, i, jArr2, i2, jArr3, i3, i8, jArr4, i12);
        long[] jArr5 = jArr2;
        long[] jArr6 = jArr3;
        karatsuba(jArr, i11, jArr5, i2 + i8, jArr6, i3 + i8, i7, jArr4, i12);
        long[] jArr7 = jArr4;
        long[] jArr8 = jArr4;
        karatsuba_add1(jArr7, i5, jArr8, i9, jArr5, i2, jArr6, i3, i8, i7);
        karatsuba(jArr7, i10, jArr8, i5, jArr4, i9, i8, jArr4, i12);
        karatsuba_add2(jArr, i, jArr4, i10, jArr, i11, i8, i7);
    }

    private void karatsuba_add1(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, long[] jArr4, int i4, int i5, int i6) {
        int i7 = i5;
        int i8 = i6;
        for (int i9 = 0; i9 < i8; i9++) {
            int i10 = i9 + i7;
            jArr[i9 + i] = jArr3[i9 + i3] ^ jArr3[i10 + i3];
            jArr2[i9 + i2] = jArr4[i9 + i4] ^ jArr4[i10 + i4];
        }
        if (i8 < i7) {
            jArr[i8 + i] = jArr3[i8 + i3];
            jArr2[i8 + i2] = jArr4[i8 + i4];
        }
    }

    private void karatsuba_add2(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4, int i5) {
        int i6;
        int i7 = 0;
        while (true) {
            i6 = i4 * 2;
            if (i7 >= i6) {
                break;
            }
            int i8 = i7 + i2;
            jArr2[i8] = jArr2[i8] ^ jArr[i7 + i];
            i7++;
        }
        for (int i9 = 0; i9 < i5 * 2; i9++) {
            int i10 = i9 + i2;
            jArr2[i10] = jArr2[i10] ^ jArr3[i9 + i3];
        }
        for (int i11 = 0; i11 < i6; i11++) {
            int i12 = i11 + i4 + i;
            jArr[i12] = jArr[i12] ^ jArr2[i11 + i2];
        }
    }

    private void reduce(long[] jArr, long[] jArr2) {
        int i = 0;
        while (true) {
            int i2 = this.VEC_N_SIZE_64;
            if (i < i2) {
                long j = jArr2[(i + i2) - 1];
                int i3 = this.PARAM_N;
                jArr[i] = ((j >>> (i3 & 63)) ^ jArr2[i]) ^ (jArr2[i2 + i] << ((int) (64 - (((long) i3) & 63))));
                i++;
            } else {
                int i4 = i2 - 1;
                jArr[i4] = jArr[i4] & this.RED_MASK;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void multLongs(long[] jArr, long[] jArr2, long[] jArr3) {
        int i = this.VEC_N_SIZE_64;
        long[] jArr4 = new long[((i << 1) + 1)];
        karatsuba(jArr4, 0, jArr2, 0, jArr3, 0, i, new long[(i << 3)], 0);
        reduce(jArr, jArr4);
    }
}
