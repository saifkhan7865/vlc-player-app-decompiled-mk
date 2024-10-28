package org.bouncycastle.pqc.crypto.cmce;

abstract class BENES {
    private static final long[] TRANSPOSE_MASKS = {6148914691236517205L, 3689348814741910323L, 1085102592571150095L, 71777214294589695L, 281470681808895L, 4294967295L};
    protected final int GFBITS;
    protected final int SYS_N;
    protected final int SYS_T;

    public BENES(int i, int i2, int i3) {
        this.SYS_N = i;
        this.SYS_T = i2;
        this.GFBITS = i3;
    }

    static void transpose_64x64(long[] jArr, long[] jArr2) {
        transpose_64x64(jArr, jArr2, 0);
    }

    static void transpose_64x64(long[] jArr, long[] jArr2, int i) {
        int i2;
        long[] jArr3 = jArr;
        int i3 = i;
        System.arraycopy(jArr2, i3, jArr3, i3, 64);
        int i4 = 5;
        do {
            long j = TRANSPOSE_MASKS[i4];
            int i5 = 1 << i4;
            int i6 = i3;
            while (true) {
                i2 = i3 + 64;
                if (i6 >= i2) {
                    break;
                }
                for (int i7 = i6; i7 < i6 + i5; i7 += 4) {
                    long j2 = jArr3[i7];
                    int i8 = i7 + 1;
                    long j3 = jArr3[i8];
                    int i9 = i7 + 2;
                    long j4 = jArr3[i9];
                    int i10 = i7 + 3;
                    long j5 = jArr3[i10];
                    int i11 = i7 + i5;
                    long j6 = jArr3[i11];
                    int i12 = i11 + 1;
                    long j7 = jArr3[i12];
                    int i13 = i11 + 2;
                    long j8 = jArr3[i13];
                    int i14 = i11 + 3;
                    long j9 = jArr3[i14];
                    long j10 = ((j2 >>> i5) ^ j6) & j;
                    long j11 = ((j3 >>> i5) ^ j7) & j;
                    long j12 = ((j4 >>> i5) ^ j8) & j;
                    long j13 = ((j5 >>> i5) ^ j9) & j;
                    jArr3[i7] = j2 ^ (j10 << i5);
                    jArr3[i8] = (j11 << i5) ^ j3;
                    jArr3[i9] = (j12 << i5) ^ j4;
                    jArr3[i10] = j5 ^ (j13 << i5);
                    jArr3[i11] = j6 ^ j10;
                    jArr3[i12] = j7 ^ j11;
                    jArr3[i13] = j8 ^ j12;
                    jArr3[i14] = j9 ^ j13;
                }
                i6 += i5 * 2;
            }
            i4--;
        } while (i4 >= 2);
        do {
            long j14 = TRANSPOSE_MASKS[i4];
            int i15 = 1 << i4;
            for (int i16 = i3; i16 < i2; i16 += i15 * 2) {
                for (int i17 = i16; i17 < i16 + i15; i17++) {
                    long j15 = jArr3[i17];
                    int i18 = i17 + i15;
                    long j16 = jArr3[i18];
                    long j17 = ((j15 >>> i15) ^ j16) & j14;
                    jArr3[i17] = j15 ^ (j17 << i15);
                    jArr3[i18] = j16 ^ j17;
                }
            }
            i4--;
        } while (i4 >= 0);
    }

    /* access modifiers changed from: protected */
    public abstract void support_gen(short[] sArr, byte[] bArr);
}
