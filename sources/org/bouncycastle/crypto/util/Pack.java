package org.bouncycastle.crypto.util;

import com.google.common.base.Ascii;

public abstract class Pack {
    public static int bigEndianToInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | (bArr[i] << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8);
    }

    public static void bigEndianToInt(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = bigEndianToInt(bArr, i);
            i += 4;
        }
    }

    public static long bigEndianToLong(byte[] bArr, int i) {
        int bigEndianToInt = bigEndianToInt(bArr, i);
        return (((long) bigEndianToInt(bArr, i + 4)) & 4294967295L) | ((((long) bigEndianToInt) & 4294967295L) << 32);
    }

    public static void bigEndianToLong(byte[] bArr, int i, long[] jArr) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = bigEndianToLong(bArr, i);
            i += 8;
        }
    }

    public static void intToBigEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
    }

    public static void intToBigEndian(int[] iArr, byte[] bArr, int i) {
        for (int intToBigEndian : iArr) {
            intToBigEndian(intToBigEndian, bArr, i);
            i += 4;
        }
    }

    public static byte[] intToBigEndian(int i) {
        byte[] bArr = new byte[4];
        intToBigEndian(i, bArr, 0);
        return bArr;
    }

    public static byte[] intToBigEndian(int[] iArr) {
        byte[] bArr = new byte[(iArr.length * 4)];
        intToBigEndian(iArr, bArr, 0);
        return bArr;
    }

    public static void intToLittleEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    public static void intToLittleEndian(int[] iArr, byte[] bArr, int i) {
        for (int intToLittleEndian : iArr) {
            intToLittleEndian(intToLittleEndian, bArr, i);
            i += 4;
        }
    }

    public static byte[] intToLittleEndian(int i) {
        byte[] bArr = new byte[4];
        intToLittleEndian(i, bArr, 0);
        return bArr;
    }

    public static byte[] intToLittleEndian(int[] iArr) {
        byte[] bArr = new byte[(iArr.length * 4)];
        intToLittleEndian(iArr, bArr, 0);
        return bArr;
    }

    public static int littleEndianToInt(byte[] bArr, int i) {
        return (bArr[i + 3] << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    public static void littleEndianToInt(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = littleEndianToInt(bArr, i);
            i += 4;
        }
    }

    public static void littleEndianToInt(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = littleEndianToInt(bArr, i);
            i += 4;
        }
    }

    public static long littleEndianToLong(byte[] bArr, int i) {
        return ((((long) littleEndianToInt(bArr, i + 4)) & 4294967295L) << 32) | (4294967295L & ((long) littleEndianToInt(bArr, i)));
    }

    public static void littleEndianToLong(byte[] bArr, int i, long[] jArr) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = littleEndianToLong(bArr, i);
            i += 8;
        }
    }

    public static void longToBigEndian(long j, byte[] bArr, int i) {
        intToBigEndian((int) (j >>> 32), bArr, i);
        intToBigEndian((int) (j & 4294967295L), bArr, i + 4);
    }

    public static void longToBigEndian(long[] jArr, byte[] bArr, int i) {
        for (long longToBigEndian : jArr) {
            longToBigEndian(longToBigEndian, bArr, i);
            i += 8;
        }
    }

    public static byte[] longToBigEndian(long j) {
        byte[] bArr = new byte[8];
        longToBigEndian(j, bArr, 0);
        return bArr;
    }

    public static byte[] longToBigEndian(long[] jArr) {
        byte[] bArr = new byte[(jArr.length * 8)];
        longToBigEndian(jArr, bArr, 0);
        return bArr;
    }

    public static void longToLittleEndian(long j, byte[] bArr, int i) {
        intToLittleEndian((int) (4294967295L & j), bArr, i);
        intToLittleEndian((int) (j >>> 32), bArr, i + 4);
    }

    public static void longToLittleEndian(long[] jArr, byte[] bArr, int i) {
        for (long longToLittleEndian : jArr) {
            longToLittleEndian(longToLittleEndian, bArr, i);
            i += 8;
        }
    }

    public static byte[] longToLittleEndian(long j) {
        byte[] bArr = new byte[8];
        longToLittleEndian(j, bArr, 0);
        return bArr;
    }

    public static byte[] longToLittleEndian(long[] jArr) {
        byte[] bArr = new byte[(jArr.length * 8)];
        longToLittleEndian(jArr, bArr, 0);
        return bArr;
    }
}
