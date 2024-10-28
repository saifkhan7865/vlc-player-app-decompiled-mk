package org.bouncycastle.math.ec.rfc8032;

import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

abstract class Scalar25519 {
    private static final int[] L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int L0 = -50998291;
    private static final int L1 = 19280294;
    private static final int L2 = 127719000;
    private static final int L3 = -6428113;
    private static final int L4 = 5343;
    private static final int[] LSq = {-1424848535, -487721339, 580428573, 1745064566, -770181698, 1036971123, 461123738, -1582065343, 1268693629, -889041821, -731974758, 43769659, 0, 0, 0, 16777216};
    private static final long M08L = 255;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int SCALAR_BYTES = 32;
    static final int SIZE = 8;
    private static final int TARGET_LENGTH = 254;

    Scalar25519() {
    }

    static boolean checkVar(byte[] bArr, int[] iArr) {
        decode(bArr, iArr);
        return !Nat256.gte(iArr, L);
    }

    static void decode(byte[] bArr, int[] iArr) {
        Codec.decode32(bArr, 0, iArr, 0, 8);
    }

    static void getOrderWnafVar(int i, byte[] bArr) {
        Wnaf.getSignedVar(L, i, bArr);
    }

    static void multiply128Var(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[12];
        Nat256.mul128(iArr, iArr2, iArr4);
        if (iArr2[3] < 0) {
            Nat256.addTo(L, 0, iArr4, 4, 0);
            Nat256.subFrom(iArr, 0, iArr4, 4, 0);
        }
        byte[] bArr = new byte[48];
        Codec.encode32(iArr4, 0, 12, bArr, 0);
        decode(reduce384(bArr), iArr3);
    }

    static byte[] reduce384(byte[] bArr) {
        byte[] bArr2 = bArr;
        long decode32 = ((long) Codec.decode32(bArr2, 7)) & 4294967295L;
        long decode24 = (long) (Codec.decode24(bArr2, 32) << 4);
        long j = decode24 & 4294967295L;
        long j2 = decode24;
        long decode322 = (long) Codec.decode32(bArr2, 35);
        long j3 = decode322 & 4294967295L;
        long j4 = decode322;
        long decode242 = (long) (Codec.decode24(bArr2, 39) << 4);
        long decode243 = ((long) (Codec.decode24(bArr2, 11) << 4)) & 4294967295L;
        long decode323 = (long) Codec.decode32(bArr2, 42);
        long j5 = decode242;
        long decode16 = (4294967295L & ((long) (Codec.decode16(bArr2, 46) << 4))) + ((decode323 & 4294967295L) >> 28);
        long j6 = (decode323 & M28L) + ((decode242 & 4294967295L) >> 28);
        long decode324 = ((((long) Codec.decode32(bArr2, 14)) & 4294967295L) - (decode16 * -50998291)) - (j6 * 19280294);
        long decode244 = ((((long) (Codec.decode24(bArr2, 18) << 4)) & 4294967295L) - (decode16 * 19280294)) - (j6 * 127719000);
        long decode245 = ((((long) (Codec.decode24(bArr2, 25) << 4)) & 4294967295L) - (decode16 * -6428113)) - (j6 * 5343);
        long j7 = (j5 & M28L) + (j3 >> 28);
        long j8 = decode32 - (j7 * -50998291);
        long j9 = (decode243 - (j6 * -50998291)) - (j7 * 19280294);
        long j10 = decode324 - (j7 * 127719000);
        long decode325 = (((((long) Codec.decode32(bArr2, 21)) & 4294967295L) - (decode16 * 127719000)) - (j6 * -6428113)) - (j7 * 5343);
        long j11 = (j4 & M28L) + (j >> 28);
        long j12 = j2 & M28L;
        long decode246 = (((long) (Codec.decode24(bArr2, 4) << 4)) & 4294967295L) - (j11 * -50998291);
        long j13 = (decode244 - (j7 * -6428113)) - (j11 * 5343);
        long decode326 = ((((long) Codec.decode32(bArr2, 28)) & 4294967295L) - (decode16 * 5343)) + (decode245 >> 28);
        long j14 = decode245 & M28L;
        long j15 = j12 + (decode326 >> 28);
        long j16 = decode326 & M28L;
        long j17 = j16 >>> 27;
        long j18 = j15 + j17;
        long decode327 = (((long) Codec.decode32(bArr2, 0)) & 4294967295L) - (j18 * -50998291);
        long j19 = (j8 - (j11 * 19280294)) - (j18 * 127719000);
        long j20 = (j10 - (j11 * -6428113)) - (j18 * 5343);
        long j21 = (decode246 - (j18 * 19280294)) + (decode327 >> 28);
        long j22 = decode327 & M28L;
        long j23 = j19 + (j21 >> 28);
        long j24 = j21 & M28L;
        long j25 = ((j9 - (j11 * 127719000)) - (j18 * -6428113)) + (j23 >> 28);
        long j26 = j23 & M28L;
        long j27 = j20 + (j25 >> 28);
        long j28 = j25 & M28L;
        long j29 = j13 + (j27 >> 28);
        long j30 = j27 & M28L;
        long j31 = decode325 + (j29 >> 28);
        long j32 = j29 & M28L;
        long j33 = j14 + (j31 >> 28);
        long j34 = j31 & M28L;
        long j35 = j16 + (j33 >> 28);
        long j36 = j33 & M28L;
        long j37 = j35 >> 28;
        long j38 = j35 & M28L;
        long j39 = j37 - j17;
        long j40 = j22 + (j39 & -50998291);
        long j41 = j24 + (j39 & 19280294) + (j40 >> 28);
        long j42 = j40 & M28L;
        long j43 = j26 + (j39 & 127719000) + (j41 >> 28);
        long j44 = j41 & M28L;
        long j45 = j28 + (j39 & -6428113) + (j43 >> 28);
        long j46 = j43 & M28L;
        long j47 = j30 + (j39 & 5343) + (j45 >> 28);
        long j48 = j45 & M28L;
        long j49 = j32 + (j47 >> 28);
        long j50 = j47 & M28L;
        long j51 = j34 + (j49 >> 28);
        long j52 = j49 & M28L;
        long j53 = j36 + (j51 >> 28);
        long j54 = j51 & M28L;
        long j55 = j53 & M28L;
        byte[] bArr3 = new byte[64];
        Codec.encode56(j42 | (j44 << 28), bArr3, 0);
        Codec.encode56((j48 << 28) | j46, bArr3, 7);
        Codec.encode56((j52 << 28) | j50, bArr3, 14);
        Codec.encode56((j55 << 28) | j54, bArr3, 21);
        Codec.encode32((int) (j38 + (j53 >> 28)), bArr3, 28);
        return bArr3;
    }

    static byte[] reduce512(byte[] bArr) {
        byte[] bArr2 = bArr;
        long decode32 = ((long) Codec.decode32(bArr2, 7)) & 4294967295L;
        long decode24 = ((long) (Codec.decode24(bArr2, 18) << 4)) & 4294967295L;
        long decode322 = ((long) Codec.decode32(bArr2, 21)) & 4294967295L;
        long decode323 = ((long) Codec.decode32(bArr2, 28)) & 4294967295L;
        long decode242 = ((long) (Codec.decode24(bArr2, 46) << 4)) & 4294967295L;
        long decode324 = (long) Codec.decode32(bArr2, 49);
        long j = decode324 & 4294967295L;
        long j2 = decode324;
        long decode243 = ((long) (Codec.decode24(bArr2, 53) << 4)) & 4294967295L;
        long decode325 = (long) Codec.decode32(bArr2, 56);
        long j3 = decode325 & 4294967295L;
        long j4 = decode325;
        long decode244 = 4294967295L & ((long) (Codec.decode24(bArr2, 60) << 4));
        long j5 = ((long) bArr2[63]) & M08L;
        long j6 = decode242 - (j5 * 5343);
        long j7 = decode244 + (j3 >> 28);
        long j8 = j4 & M28L;
        long j9 = decode323 - (j7 * -50998291);
        long decode245 = ((((long) (Codec.decode24(bArr2, 32) << 4)) & 4294967295L) - (j5 * -50998291)) - (j7 * 19280294);
        long decode326 = ((((long) Codec.decode32(bArr2, 35)) & 4294967295L) - (j5 * 19280294)) - (j7 * 127719000);
        long decode327 = ((((long) Codec.decode32(bArr2, 42)) & 4294967295L) - (j5 * -6428113)) - (j7 * 5343);
        long decode246 = (((long) (Codec.decode24(bArr2, 25) << 4)) & 4294967295L) - (j8 * -50998291);
        long j10 = j9 - (j8 * 19280294);
        long j11 = decode245 - (j8 * 127719000);
        long j12 = decode326 - (j8 * -6428113);
        long decode247 = (((((long) (Codec.decode24(bArr2, 39) << 4)) & 4294967295L) - (j5 * 127719000)) - (j7 * -6428113)) - (j8 * 5343);
        long j13 = decode243 + (j >> 28);
        long j14 = j2 & M28L;
        long j15 = decode322 - (j13 * -50998291);
        long j16 = decode246 - (j13 * 19280294);
        long j17 = j10 - (j13 * 127719000);
        long j18 = j12 - (j13 * 5343);
        long j19 = (j11 - (j13 * -6428113)) - (j14 * 5343);
        long j20 = j6 + (decode327 >> 28);
        long decode328 = (((long) Codec.decode32(bArr2, 14)) & 4294967295L) - (j20 * -50998291);
        long j21 = (decode24 - (j14 * -50998291)) - (j20 * 19280294);
        long j22 = (j15 - (j14 * 19280294)) - (j20 * 127719000);
        long j23 = (j17 - (j14 * -6428113)) - (j20 * 5343);
        long j24 = (decode327 & M28L) + (decode247 >> 28);
        long decode248 = (((long) (Codec.decode24(bArr2, 11) << 4)) & 4294967295L) - (j24 * -50998291);
        long j25 = decode328 - (j24 * 19280294);
        long j26 = j21 - (j24 * 127719000);
        long j27 = ((j16 - (j14 * 127719000)) - (j20 * -6428113)) - (j24 * 5343);
        long j28 = (decode247 & M28L) + (j18 >> 28);
        long j29 = j18 & M28L;
        long j30 = (j22 - (j24 * -6428113)) - (j28 * 5343);
        long j31 = j29 + (j19 >> 28);
        long j32 = j19 & M28L;
        long j33 = (decode32 - (j28 * -50998291)) - (j31 * 19280294);
        long j34 = (decode248 - (j28 * 19280294)) - (j31 * 127719000);
        long j35 = (j25 - (j28 * 127719000)) - (j31 * -6428113);
        long j36 = (j26 - (j28 * -6428113)) - (j31 * 5343);
        long j37 = j23 + (j27 >> 28);
        long j38 = j27 & M28L;
        long j39 = j37 & M28L;
        long j40 = j39 >>> 27;
        long j41 = j32 + (j37 >> 28) + j40;
        long decode329 = (((long) Codec.decode32(bArr2, 0)) & 4294967295L) - (j41 * -50998291);
        long decode249 = (((((long) (Codec.decode24(bArr2, 4) << 4)) & 4294967295L) - (j31 * -50998291)) - (j41 * 19280294)) + (decode329 >> 28);
        long j42 = decode329 & M28L;
        long j43 = (j33 - (j41 * 127719000)) + (decode249 >> 28);
        long j44 = decode249 & M28L;
        long j45 = (j34 - (j41 * -6428113)) + (j43 >> 28);
        long j46 = j43 & M28L;
        long j47 = (j35 - (j41 * 5343)) + (j45 >> 28);
        long j48 = j45 & M28L;
        long j49 = j36 + (j47 >> 28);
        long j50 = j47 & M28L;
        long j51 = j30 + (j49 >> 28);
        long j52 = j49 & M28L;
        long j53 = j38 + (j51 >> 28);
        long j54 = j51 & M28L;
        long j55 = j39 + (j53 >> 28);
        long j56 = j53 & M28L;
        long j57 = j55 >> 28;
        long j58 = j55 & M28L;
        long j59 = j57 - j40;
        long j60 = j42 + (j59 & -50998291);
        long j61 = j44 + (j59 & 19280294) + (j60 >> 28);
        long j62 = j60 & M28L;
        long j63 = j46 + (j59 & 127719000) + (j61 >> 28);
        long j64 = j61 & M28L;
        long j65 = j48 + (j59 & -6428113) + (j63 >> 28);
        long j66 = j63 & M28L;
        long j67 = j50 + (j59 & 5343) + (j65 >> 28);
        long j68 = j65 & M28L;
        long j69 = j52 + (j67 >> 28);
        long j70 = j67 & M28L;
        long j71 = j54 + (j69 >> 28);
        long j72 = j69 & M28L;
        long j73 = j56 + (j71 >> 28);
        long j74 = j71 & M28L;
        long j75 = j73 & M28L;
        byte[] bArr3 = new byte[32];
        Codec.encode56(j62 | (j64 << 28), bArr3, 0);
        Codec.encode56((j68 << 28) | j66, bArr3, 7);
        Codec.encode56(j70 | (j72 << 28), bArr3, 14);
        Codec.encode56(j74 | (j75 << 28), bArr3, 21);
        Codec.encode32((int) (j58 + (j73 >> 28)), bArr3, 28);
        return bArr3;
    }

    static void reduceBasisVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = iArr;
        int[] iArr5 = new int[16];
        System.arraycopy(LSq, 0, iArr5, 0, 16);
        int[] iArr6 = new int[16];
        Nat256.square(iArr4, iArr6);
        iArr6[0] = iArr6[0] + 1;
        int[] iArr7 = new int[16];
        int[] iArr8 = L;
        Nat256.mul(iArr8, iArr4, iArr7);
        int[] iArr9 = new int[4];
        System.arraycopy(iArr8, 0, iArr9, 0, 4);
        int[] iArr10 = new int[4];
        int[] iArr11 = new int[4];
        System.arraycopy(iArr4, 0, iArr11, 0, 4);
        int[] iArr12 = new int[4];
        iArr12[0] = 1;
        int i = 15;
        int bitLengthPositive = ScalarUtil.getBitLengthPositive(15, iArr6);
        while (bitLengthPositive > 254) {
            int bitLength = ScalarUtil.getBitLength(i, iArr7) - bitLengthPositive;
            int i2 = ((bitLength >> 31) ^ -1) & bitLength;
            if (iArr7[i] < 0) {
                ScalarUtil.addShifted_NP(i, i2, iArr5, iArr6, iArr7);
                ScalarUtil.addShifted_UV(3, i2, iArr9, iArr10, iArr11, iArr12);
            } else {
                ScalarUtil.subShifted_NP(i, i2, iArr5, iArr6, iArr7);
                ScalarUtil.subShifted_UV(3, i2, iArr9, iArr10, iArr11, iArr12);
            }
            if (ScalarUtil.lessThan(i, iArr5, iArr6)) {
                i = bitLengthPositive >>> 5;
                bitLengthPositive = ScalarUtil.getBitLengthPositive(i, iArr5);
                int[] iArr13 = iArr10;
                iArr10 = iArr12;
                iArr12 = iArr13;
                int[] iArr14 = iArr6;
                iArr6 = iArr5;
                iArr5 = iArr14;
                int[] iArr15 = iArr11;
                iArr11 = iArr9;
                iArr9 = iArr15;
            }
        }
        System.arraycopy(iArr11, 0, iArr2, 0, 4);
        System.arraycopy(iArr12, 0, iArr3, 0, 4);
    }

    static void toSignedDigits(int i, int[] iArr) {
        Nat.caddTo(8, (iArr[0] ^ -1) & 1, L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
    }
}
