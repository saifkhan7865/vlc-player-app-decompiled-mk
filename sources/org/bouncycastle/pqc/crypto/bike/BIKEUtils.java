package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Pack;

class BIKEUtils {
    BIKEUtils() {
    }

    protected static int CHECK_BIT(byte[] bArr, int i) {
        return (bArr[i / 8] >>> (i % 8)) & 1;
    }

    protected static void SET_BIT(byte[] bArr, int i) {
        int i2 = i / 8;
        bArr[i2] = (byte) ((int) (((long) bArr[i2]) | (1 << ((int) ((long) (i % 8))))));
    }

    static void fromBitArrayToByteArray(byte[] bArr, byte[] bArr2, int i, int i2) {
        long j = (long) i2;
        int i3 = 0;
        int i4 = 0;
        while (((long) i3) < j) {
            int i5 = i3 + 8;
            if (i5 >= i2) {
                int i6 = i + i3;
                byte b = bArr2[i6];
                for (int i7 = (i2 - i3) - 1; i7 >= 1; i7--) {
                    b |= bArr2[i6 + i7] << i7;
                }
                bArr[i4] = (byte) b;
            } else {
                int i8 = i3 + i;
                byte b2 = bArr2[i8];
                for (int i9 = 7; i9 >= 1; i9--) {
                    b2 |= bArr2[i8 + i9] << i9;
                }
                bArr[i4] = (byte) b2;
            }
            i4++;
            i3 = i5;
        }
    }

    static void generateRandomByteArray(byte[] bArr, int i, int i2, Xof xof) {
        byte[] bArr2 = new byte[4];
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            xof.doOutput(bArr2, 0, 4);
            int littleEndianToInt = ((int) (((((long) Pack.littleEndianToInt(bArr2, 0)) & 4294967295L) * ((long) (i - i3))) >> 32)) + i3;
            if (CHECK_BIT(bArr, littleEndianToInt) != 0) {
                littleEndianToInt = i3;
            }
            SET_BIT(bArr, littleEndianToInt);
        }
    }

    static int getHammingWeight(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i += b;
        }
        return i;
    }
}
