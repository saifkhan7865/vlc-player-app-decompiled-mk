package org.bouncycastle.math.ec.rfc8032;

abstract class Wnaf {
    Wnaf() {
    }

    static void getSignedVar(int[] iArr, int i, byte[] bArr) {
        int i2;
        int length = iArr.length * 2;
        int[] iArr2 = new int[length];
        int i3 = iArr[iArr.length - 1] >> 31;
        int length2 = iArr.length;
        int i4 = length;
        while (true) {
            length2--;
            if (length2 < 0) {
                break;
            }
            int i5 = iArr[length2];
            iArr2[i4 - 1] = (i3 << 16) | (i5 >>> 16);
            i4 -= 2;
            iArr2[i4] = i5;
            i3 = i5;
        }
        int i6 = 32 - i;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < length) {
            int i10 = iArr2[i7];
            while (i2 < 16) {
                int i11 = i10 >>> i2;
                if ((i11 & 1) == i9) {
                    i2++;
                } else {
                    int i12 = (i11 | 1) << i6;
                    bArr[(i7 << 4) + i2] = (byte) (i12 >> i6);
                    i2 += i;
                    i9 = i12 >>> 31;
                }
            }
            i7++;
            i8 = i2 - 16;
        }
    }
}
