package org.bouncycastle.pqc.crypto.picnic;

import androidx.core.view.MotionEventCompat;
import org.bouncycastle.util.Integers;

class Utils {
    Utils() {
    }

    protected static int ceil_log2(int i) {
        if (i == 0) {
            return 0;
        }
        return 32 - nlz(i - 1);
    }

    protected static byte getBit(byte[] bArr, int i) {
        return (byte) ((bArr[i >>> 3] >>> ((i & 7) ^ 7)) & 1);
    }

    protected static int getBit(int i, int i2) {
        return (i >>> (i2 ^ 7)) & 1;
    }

    protected static int getBit(int[] iArr, int i) {
        return (iArr[i >>> 5] >>> ((i & 31) ^ 7)) & 1;
    }

    protected static int getBitFromWordArray(int[] iArr, int i) {
        return getBit(iArr, i);
    }

    protected static byte getCrumbAligned(byte[] bArr, int i) {
        int i2 = bArr[i >>> 2] >>> (((i << 1) & 6) ^ 6);
        return (byte) (((i2 & 2) >> 1) | ((i2 & 1) << 1));
    }

    protected static int getTrailingBitsMask(int i) {
        int i2 = i & -8;
        int i3 = -1 ^ (-1 << i2);
        int i4 = i & 7;
        return i4 != 0 ? i3 ^ (((MotionEventCompat.ACTION_POINTER_INDEX_MASK >>> i4) & 255) << i2) : i3;
    }

    private static int nlz(int i) {
        int i2;
        if (i == 0) {
            return 32;
        }
        if ((i >>> 16) == 0) {
            i <<= 16;
            i2 = 17;
        } else {
            i2 = 1;
        }
        if ((i >>> 24) == 0) {
            i2 += 8;
            i <<= 8;
        }
        if ((i >>> 28) == 0) {
            i2 += 4;
            i <<= 4;
        }
        if ((i >>> 30) == 0) {
            i2 += 2;
            i <<= 2;
        }
        return i2 - (i >>> 31);
    }

    protected static int numBytes(int i) {
        if (i == 0) {
            return 0;
        }
        return ((i - 1) / 8) + 1;
    }

    protected static int parity(byte[] bArr, int i) {
        byte b = bArr[0];
        for (int i2 = 1; i2 < i; i2++) {
            b = (byte) (b ^ bArr[i2]);
        }
        return Integers.bitCount(b & 255) & 1;
    }

    protected static int parity16(int i) {
        return Integers.bitCount(i & 65535) & 1;
    }

    protected static int parity32(int i) {
        return Integers.bitCount(i) & 1;
    }

    protected static int setBit(int i, int i2, int i3) {
        int i4 = i2 ^ 7;
        return (i & ((1 << i4) ^ -1)) | (i3 << i4);
    }

    protected static void setBit(byte[] bArr, int i, byte b) {
        int i2 = i >>> 3;
        byte b2 = (i & 7) ^ 7;
        bArr[i2] = (byte) ((b << b2) | (bArr[i2] & ((1 << b2) ^ -1)));
    }

    protected static void setBit(int[] iArr, int i, int i2) {
        int i3 = i >>> 5;
        int i4 = (i & 31) ^ 7;
        iArr[i3] = (i2 << i4) | (iArr[i3] & ((1 << i4) ^ -1));
    }

    protected static void setBitInWordArray(int[] iArr, int i, int i2) {
        setBit(iArr, i, i2);
    }

    protected static void zeroTrailingBits(int[] iArr, int i) {
        if ((i & 31) != 0) {
            int i2 = i >>> 5;
            iArr[i2] = getTrailingBitsMask(i) & iArr[i2];
        }
    }
}
