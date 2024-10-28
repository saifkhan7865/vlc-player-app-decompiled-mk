package org.bouncycastle.pqc.crypto.hqc;

class FastFourierTransform {
    FastFourierTransform() {
    }

    static void computeFFTBetas(int[] iArr, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i2 < i3) {
                iArr[i2] = 1 << (i3 - i2);
                i2++;
            } else {
                return;
            }
        }
    }

    static void computeFFTRec(int[] iArr, int[] iArr2, int i, int i2, int i3, int[] iArr3, int i4, int i5) {
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = 1;
        int i11 = 1 << (i9 - 2);
        int i12 = i5 - 2;
        int i13 = 1 << i12;
        int[] iArr6 = new int[i11];
        int[] iArr7 = new int[i11];
        int[] iArr8 = new int[i12];
        int[] iArr9 = new int[i12];
        int[] iArr10 = new int[i13];
        int[] iArr11 = new int[i13];
        int[] iArr12 = new int[i13];
        int[] iArr13 = new int[((i5 - i9) + 1)];
        if (i8 == 1) {
            for (int i14 = 0; i14 < i7; i14++) {
                iArr13[i14] = GFCalculator.mult(iArr3[i14], iArr5[1]);
            }
            iArr4[0] = iArr5[0];
            for (int i15 = 0; i15 < i7; i15++) {
                for (int i16 = 0; i16 < i10; i16++) {
                    iArr4[i10 + i16] = iArr4[i16] ^ iArr13[i15];
                }
                i10 <<= 1;
            }
            return;
        }
        int i17 = i7 - 1;
        if (iArr3[i17] != 1) {
            int i18 = 1;
            for (int i19 = 1 << i8; i18 < i19; i19 = i19) {
                i10 = GFCalculator.mult(i10, iArr3[i17]);
                iArr5[i18] = GFCalculator.mult(i10, iArr5[i18]);
                i18++;
            }
        }
        computeRadix(iArr6, iArr7, iArr5, i8, i9);
        for (int i20 = 0; i20 < i17; i20++) {
            int mult = GFCalculator.mult(iArr3[i20], GFCalculator.inverse(iArr3[i17]));
            iArr8[i20] = mult;
            iArr9[i20] = GFCalculator.mult(mult, mult) ^ iArr8[i20];
        }
        computeSubsetSum(iArr10, iArr8, i17);
        int i21 = i8 - 1;
        int i22 = i17;
        int[] iArr14 = iArr12;
        int[] iArr15 = iArr11;
        computeFFTRec(iArr11, iArr6, (i6 + 1) / 2, i17, i21, iArr9, i4, i5);
        int i23 = 1 << (i22 & 15);
        if (i6 <= 3) {
            iArr4[0] = iArr15[0];
            iArr4[i23] = iArr15[0] ^ iArr7[0];
            for (int i24 = 1; i24 < i23; i24++) {
                int mult2 = iArr15[i24] ^ GFCalculator.mult(iArr10[i24], iArr7[0]);
                iArr4[i24] = mult2;
                iArr4[i23 + i24] = mult2 ^ iArr7[0];
            }
            return;
        }
        int[] iArr16 = iArr14;
        computeFFTRec(iArr16, iArr7, i6 / 2, i22, i21, iArr9, i4, i5);
        System.arraycopy(iArr16, 0, iArr4, i23, i23);
        iArr4[0] = iArr15[0];
        iArr4[i23] = iArr4[i23] ^ iArr15[0];
        for (int i25 = 1; i25 < i23; i25++) {
            int mult3 = iArr15[i25] ^ GFCalculator.mult(iArr10[i25], iArr16[i25]);
            iArr4[i25] = mult3;
            int i26 = i23 + i25;
            iArr4[i26] = mult3 ^ iArr4[i26];
        }
    }

    static void computeRadix(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        int i3 = i;
        if (i3 == 1) {
            iArr[0] = iArr3[0];
            iArr2[0] = iArr3[1];
        } else if (i3 == 2) {
            iArr[0] = iArr3[0];
            int i4 = iArr3[2];
            int i5 = iArr3[3];
            int i6 = i4 ^ i5;
            iArr[1] = i6;
            iArr2[0] = i6 ^ iArr3[1];
            iArr2[1] = i5;
        } else if (i3 == 3) {
            iArr[0] = iArr3[0];
            int i7 = iArr3[4];
            int i8 = iArr3[6];
            iArr[2] = i7 ^ i8;
            int i9 = iArr3[7];
            iArr[3] = i8 ^ i9;
            int i10 = iArr3[3];
            int i11 = iArr3[5];
            int i12 = (i10 ^ i11) ^ i9;
            iArr2[1] = i12;
            iArr2[2] = i11 ^ i8;
            iArr2[3] = i9;
            int i13 = (iArr3[2] ^ iArr[2]) ^ i12;
            iArr[1] = i13;
            iArr2[0] = i13 ^ iArr3[1];
        } else if (i3 != 4) {
            computeRadixBig(iArr, iArr2, iArr3, i, i2);
        } else {
            int i14 = iArr3[8];
            int i15 = iArr3[12];
            iArr[4] = i14 ^ i15;
            int i16 = iArr3[14];
            iArr[6] = i15 ^ i16;
            int i17 = iArr3[15];
            iArr[7] = i16 ^ i17;
            int i18 = iArr3[11];
            int i19 = iArr3[13];
            int i20 = i18 ^ i19;
            iArr2[5] = i20;
            iArr2[6] = i19 ^ i16;
            iArr2[7] = i17;
            int i21 = iArr3[10];
            int i22 = (i15 ^ i21) ^ i20;
            iArr[5] = i22;
            int i23 = iArr3[9];
            iArr2[4] = i22 ^ (i23 ^ i19);
            iArr[0] = iArr3[0];
            int i24 = (iArr3[7] ^ i18) ^ i17;
            iArr2[3] = i24;
            int i25 = (i16 ^ (iArr3[6] ^ i21)) ^ i24;
            iArr[3] = i25;
            int i26 = iArr2[3];
            iArr[2] = ((iArr3[4] ^ iArr[4]) ^ i25) ^ i26;
            int i27 = iArr3[3];
            int i28 = (((iArr3[5] ^ i27) ^ i23) ^ i19) ^ i26;
            iArr2[1] = i28;
            iArr2[2] = i25 ^ (i27 ^ i28);
            int i29 = (iArr3[2] ^ iArr[2]) ^ i28;
            iArr[1] = i29;
            iArr2[0] = i29 ^ iArr3[1];
        }
    }

    static void computeRadixBig(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        int[] iArr6 = iArr3;
        int i3 = i2;
        int i4 = 1 << (i - 2);
        int i5 = 1 << (i3 - 2);
        int i6 = i5 * 2;
        int[] iArr7 = new int[i6];
        int[] iArr8 = new int[i6];
        int[] iArr9 = new int[i5];
        int[] iArr10 = new int[i5];
        int[] iArr11 = new int[i5];
        int[] iArr12 = new int[i5];
        int i7 = i4 * 3;
        int i8 = i4 * 2;
        Utils.copyBytes(iArr6, i7, iArr7, 0, i8);
        Utils.copyBytes(iArr6, i7, iArr7, i4, i8);
        Utils.copyBytes(iArr6, 0, iArr8, 0, i4 * 4);
        for (int i9 = 0; i9 < i4; i9++) {
            int i10 = iArr7[i9] ^ iArr6[i8 + i9];
            iArr7[i9] = i10;
            int i11 = i4 + i9;
            iArr8[i11] = iArr8[i11] ^ i10;
        }
        int i12 = i - 1;
        computeRadix(iArr9, iArr10, iArr7, i12, i3);
        computeRadix(iArr11, iArr12, iArr8, i12, i3);
        Utils.copyBytes(iArr11, 0, iArr4, 0, i8);
        Utils.copyBytes(iArr9, 0, iArr4, i4, i8);
        Utils.copyBytes(iArr12, 0, iArr5, 0, i8);
        Utils.copyBytes(iArr10, 0, iArr5, i4, i8);
    }

    static void computeSubsetSum(int[] iArr, int[] iArr2, int i) {
        iArr[0] = 0;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 0;
            while (true) {
                int i4 = 1 << i2;
                if (i3 >= i4) {
                    break;
                }
                iArr[i4 + i3] = iArr2[i2] ^ iArr[i3];
                i3++;
            }
        }
    }

    static void fastFourierTransform(int[] iArr, int[] iArr2, int i, int i2) {
        int[] iArr3 = iArr;
        int i3 = i2;
        int i4 = 1 << i3;
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[7];
        int[] iArr7 = new int[128];
        int[] iArr8 = new int[128];
        int[] iArr9 = new int[7];
        int[] iArr10 = new int[128];
        computeFFTBetas(iArr9, 8);
        computeSubsetSum(iArr10, iArr9, 7);
        computeRadix(iArr4, iArr5, iArr2, i3, i3);
        for (int i5 = 0; i5 < 7; i5++) {
            int i6 = iArr9[i5];
            iArr6[i5] = GFCalculator.mult(i6, i6) ^ iArr9[i5];
        }
        int i7 = i3 - 1;
        int[] iArr11 = iArr6;
        int[] iArr12 = iArr10;
        int i8 = i2;
        computeFFTRec(iArr7, iArr4, (i + 1) / 2, 7, i7, iArr11, i8, 8);
        int[] iArr13 = iArr8;
        computeFFTRec(iArr13, iArr5, i / 2, 7, i7, iArr11, i8, 8);
        System.arraycopy(iArr13, 0, iArr3, 128, 128);
        iArr3[0] = iArr7[0];
        iArr3[128] = iArr3[128] ^ iArr7[0];
        for (int i9 = 1; i9 < 128; i9++) {
            int mult = iArr7[i9] ^ GFCalculator.mult(iArr12[i9], iArr13[i9]);
            iArr3[i9] = mult;
            int i10 = 128 + i9;
            iArr3[i10] = mult ^ iArr3[i10];
        }
    }

    static void fastFourierTransformGetError(byte[] bArr, int[] iArr, int i, int[] iArr2) {
        int[] iArr3 = new int[7];
        int[] iArr4 = new int[i];
        computeFFTBetas(iArr3, 8);
        computeSubsetSum(iArr4, iArr3, 7);
        byte unsigned16Bits = (byte) (bArr[0] ^ (Utils.toUnsigned16Bits((-iArr[0]) >> 15) ^ 1));
        bArr[0] = unsigned16Bits;
        bArr[0] = (byte) (unsigned16Bits ^ (Utils.toUnsigned16Bits((-iArr[i]) >> 15) ^ 1));
        for (int i2 = 1; i2 < i; i2++) {
            int i3 = 255 - iArr2[iArr4[i2]];
            bArr[i3] = (byte) (bArr[i3] ^ (Math.abs((-iArr[i2]) >> 15) ^ 1));
            int i4 = 255 - iArr2[iArr4[i2] ^ 1];
            bArr[i4] = (byte) (bArr[i4] ^ (Math.abs((-iArr[i + i2]) >> 15) ^ 1));
        }
    }
}
