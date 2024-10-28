package org.bouncycastle.pqc.crypto.ntruprime;

import com.google.common.base.Ascii;
import io.netty.handler.codec.http2.Http2CodecUtil;
import java.security.SecureRandom;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CTRModeCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

class Utils {
    Utils() {
    }

    static int bToUnsignedInt(byte b) {
        return b & 255;
    }

    protected static void checkForSmallPolynomial(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 != bArr2.length; i4++) {
            i3 += bArr2[i4] & 1;
        }
        int checkNotEqualToZero = checkNotEqualToZero(i3 - i2);
        for (int i5 = 0; i5 < i2; i5++) {
            bArr[i5] = (byte) (((bArr2[i5] ^ 1) & (checkNotEqualToZero ^ -1)) ^ 1);
        }
        while (i2 < i) {
            bArr[i2] = (byte) (bArr2[i2] & (checkNotEqualToZero ^ -1));
            i2++;
        }
    }

    private static int checkLessThanZero(int i) {
        return -(i >>> 31);
    }

    private static int checkNotEqualToZero(int i) {
        return -((int) ((-iToUnsignedLong(i)) >>> 63));
    }

    protected static void cryptoSort(int[] iArr, int i) {
        if (i >= 2) {
            int i2 = 1;
            while (i2 < i - i2) {
                i2 += i2;
            }
            for (int i3 = i2; i3 > 0; i3 >>>= 1) {
                for (int i4 = 0; i4 < i - i3; i4++) {
                    if ((i4 & i3) == 0) {
                        minmax(iArr, i4, i4 + i3);
                    }
                }
                for (int i5 = i2; i5 > i3; i5 >>>= 1) {
                    for (int i6 = 0; i6 < i - i5; i6++) {
                        if ((i6 & i3) == 0) {
                            minmax(iArr, i6 + i3, i6 + i5);
                        }
                    }
                }
            }
        }
    }

    private static void decode(short[] sArr, byte[] bArr, short[] sArr2, int i, int i2, int i3) {
        int i4;
        int i5 = i;
        if (i5 == 1) {
            short s = sArr2[0];
            if (s == 1) {
                sArr[i2] = 0;
            } else if (s <= 256) {
                sArr[i2] = (short) getUnsignedMod(bToUnsignedInt(bArr[i3]), sArr2[0]);
            } else {
                sArr[i2] = (short) getUnsignedMod(bToUnsignedInt(bArr[i3]) + (bArr[i3 + 1] << 8), sArr2[0]);
            }
        }
        if (i5 > 1) {
            int i6 = (i5 + 1) / 2;
            short[] sArr3 = new short[i6];
            short[] sArr4 = new short[i6];
            int i7 = i5 / 2;
            short[] sArr5 = new short[i7];
            int[] iArr = new int[i7];
            int i8 = i3;
            int i9 = 0;
            while (true) {
                i4 = i5 - 1;
                if (i9 >= i4) {
                    break;
                }
                int i10 = sArr2[i9] * sArr2[i9 + 1];
                if (i10 > 4194048) {
                    int i11 = i9 / 2;
                    iArr[i11] = 65536;
                    sArr5[i11] = (short) (bToUnsignedInt(bArr[i8]) + (bToUnsignedInt(bArr[i8 + 1]) * 256));
                    i8 += 2;
                    sArr4[i11] = (short) ((((i10 + 255) >>> 8) + 255) >>> 8);
                } else if (i10 >= 16384) {
                    int i12 = i9 / 2;
                    iArr[i12] = 256;
                    sArr5[i12] = (short) bToUnsignedInt(bArr[i8]);
                    i8++;
                    sArr4[i12] = (short) ((i10 + 255) >>> 8);
                } else {
                    int i13 = i9 / 2;
                    iArr[i13] = 1;
                    sArr5[i13] = 0;
                    sArr4[i13] = (short) i10;
                }
                i9 += 2;
            }
            if (i9 < i5) {
                sArr4[i9 / 2] = sArr2[i9];
            }
            decode(sArr3, bArr, sArr4, i6, i2, i8);
            int i14 = i2;
            int i15 = 0;
            while (i15 < i4) {
                int i16 = i15 / 2;
                int[] unsignedDivMod = getUnsignedDivMod(sToUnsignedInt(sArr5[i16]) + (iArr[i16] * sToUnsignedInt(sArr3[i16])), sArr2[i15]);
                int i17 = i14 + 1;
                sArr[i14] = (short) unsignedDivMod[1];
                i14 += 2;
                sArr[i17] = (short) getUnsignedMod(unsignedDivMod[0], sArr2[i15 + 1]);
                i15 += 2;
            }
            if (i15 < i5) {
                sArr[i14] = sArr3[i15 / 2];
            }
        }
    }

    private static void encode(byte[] bArr, short[] sArr, short[] sArr2, int i, int i2) {
        int i3 = 0;
        if (i == 1) {
            short s = sArr[0];
            short s2 = sArr2[0];
            while (s2 > 1) {
                bArr[i2] = (byte) s;
                s = (short) (s >>> 8);
                s2 = (short) ((s2 + Http2CodecUtil.MAX_UNSIGNED_BYTE) >>> 8);
                i2++;
            }
        }
        if (i > 1) {
            int i4 = (i + 1) / 2;
            short[] sArr3 = new short[i4];
            short[] sArr4 = new short[i4];
            while (i3 < i - 1) {
                short s3 = sArr2[i3];
                int i5 = i3 + 1;
                int i6 = sArr[i3] + (sArr[i5] * s3);
                int i7 = sArr2[i5] * s3;
                while (i7 >= 16384) {
                    bArr[i2] = (byte) i6;
                    i6 >>>= 8;
                    i7 = (i7 + 255) >>> 8;
                    i2++;
                }
                int i8 = i3 / 2;
                sArr3[i8] = (short) i6;
                sArr4[i8] = (short) i7;
                i3 += 2;
            }
            if (i3 < i) {
                int i9 = i3 / 2;
                sArr3[i9] = sArr[i3];
                sArr4[i9] = sArr2[i3];
            }
            encode(bArr, sArr3, sArr4, i4, i2);
        }
    }

    protected static void expand(int[] iArr, byte[] bArr) {
        byte[] bArr2 = new byte[(iArr.length * 4)];
        generateAES256CTRStream(new byte[(iArr.length * 4)], bArr2, new byte[16], bArr);
        for (int i = 0; i < iArr.length; i++) {
            int i2 = i * 4;
            iArr[i] = bToUnsignedInt(bArr2[i2]) + (bToUnsignedInt(bArr2[i2 + 1]) << 8) + (bToUnsignedInt(bArr2[i2 + 2]) << 16) + (bToUnsignedInt(bArr2[i2 + 3]) << 24);
        }
    }

    private static void generateAES256CTRStream(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        CTRModeCipher newInstance = SICBlockCipher.newInstance(AESEngine.newInstance());
        newInstance.init(true, new ParametersWithIV(new KeyParameter(bArr4), bArr3));
        newInstance.processBytes(bArr, 0, bArr2.length, bArr2, 0);
    }

    protected static void generatePolynomialInRQFromSeed(short[] sArr, byte[] bArr, int i, int i2) {
        int[] iArr = new int[i];
        expand(iArr, bArr);
        for (int i3 = 0; i3 < i; i3++) {
            sArr[i3] = (short) (getUnsignedMod(iArr[i3], i2) - ((i2 - 1) / 2));
        }
    }

    protected static void getDecodedPolynomial(short[] sArr, byte[] bArr, int i, int i2) {
        short[] sArr2 = new short[i];
        short[] sArr3 = new short[i];
        for (int i3 = 0; i3 < i; i3++) {
            sArr3[i3] = (short) i2;
        }
        decode(sArr2, bArr, sArr3, i, 0, 0);
        for (int i4 = 0; i4 < i; i4++) {
            sArr[i4] = (short) (sArr2[i4] - ((i2 - 1) / 2));
        }
    }

    protected static void getDecodedSmallPolynomial(byte[] bArr, byte[] bArr2, int i) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i / 4) {
            int i5 = i3 + 1;
            byte b = bArr2[i3];
            bArr[i4] = (byte) ((bToUnsignedInt(b) & 3) - 1);
            byte b2 = (byte) (b >>> 2);
            bArr[i4 + 1] = (byte) ((bToUnsignedInt(b2) & 3) - 1);
            byte b3 = (byte) (b2 >>> 2);
            int i6 = i4 + 3;
            bArr[i4 + 2] = (byte) ((bToUnsignedInt(b3) & 3) - 1);
            i4 += 4;
            bArr[i6] = (byte) ((bToUnsignedInt((byte) (b3 >>> 2)) & 3) - 1);
            i2++;
            i3 = i5;
        }
        bArr[i4] = (byte) ((bToUnsignedInt(bArr2[i3]) & 3) - 1);
    }

    protected static void getEncodedInputs(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr2.length; i++) {
            int i2 = i >>> 3;
            bArr[i2] = (byte) (bArr[i2] | (bArr2[i] << (i & 7)));
        }
    }

    protected static void getEncodedPolynomial(byte[] bArr, short[] sArr, int i, int i2) {
        short[] sArr2 = new short[i];
        short[] sArr3 = new short[i];
        for (int i3 = 0; i3 < i; i3++) {
            sArr2[i3] = (short) (sArr[i3] + ((i2 - 1) / 2));
        }
        for (int i4 = 0; i4 < i; i4++) {
            sArr3[i4] = (short) i2;
        }
        encode(bArr, sArr2, sArr3, i, 0);
    }

    protected static void getEncodedSmallPolynomial(byte[] bArr, byte[] bArr2, int i) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i / 4) {
            byte b = (byte) (((byte) (bArr2[i4] + 1)) + (((byte) (bArr2[i4 + 1] + 1)) << 2));
            int i5 = i4 + 3;
            i4 += 4;
            bArr[i3] = (byte) (((byte) (b + (((byte) (bArr2[i4 + 2] + 1)) << 4))) + (((byte) (bArr2[i5] + 1)) << 6));
            i2++;
            i3++;
        }
        bArr[i3] = (byte) (bArr2[i4] + 1);
    }

    protected static byte[] getHashWithPrefix(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[64];
        int length = bArr.length + bArr2.length;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        SHA512Digest sHA512Digest = new SHA512Digest();
        sHA512Digest.update(bArr4, 0, length);
        sHA512Digest.doFinal(bArr3, 0);
        return bArr3;
    }

    protected static int getInverseInRQ(int i, int i2) {
        int i3 = i;
        for (int i4 = 1; i4 < i2 - 2; i4++) {
            i3 = getModFreeze(i3 * i, i2);
        }
        return i3;
    }

    protected static int getModFreeze(int i, int i2) {
        int i3 = (i2 - 1) / 2;
        return getSignedDivMod(i + i3, i2)[1] - i3;
    }

    protected static void getOneThirdInverseInRQ(short[] sArr, byte[] bArr, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        int i5 = i3 + 1;
        short[] sArr2 = new short[i5];
        short[] sArr3 = new short[i5];
        short[] sArr4 = new short[i5];
        short[] sArr5 = new short[i5];
        sArr4[0] = (short) getInverseInRQ(3, i4);
        sArr2[0] = 1;
        int i6 = i3 - 1;
        sArr2[i6] = -1;
        sArr2[i3] = -1;
        for (int i7 = 0; i7 < i3; i7++) {
            sArr3[i6 - i7] = (short) bArr[i7];
        }
        sArr3[i3] = 0;
        int i8 = 1;
        for (int i9 = 0; i9 < (i3 * 2) - 1; i9++) {
            System.arraycopy(sArr5, 0, sArr5, 1, i3);
            sArr5[0] = 0;
            int i10 = -i8;
            short checkLessThanZero = checkLessThanZero(i10) & checkNotEqualToZero(sArr3[0]);
            i8 = (i8 ^ ((i10 ^ i8) & checkLessThanZero)) + 1;
            for (int i11 = 0; i11 < i5; i11++) {
                short s = sArr2[i11];
                short s2 = (sArr3[i11] ^ s) & checkLessThanZero;
                sArr2[i11] = (short) (s ^ s2);
                sArr3[i11] = (short) (sArr3[i11] ^ s2);
                short s3 = sArr5[i11];
                short s4 = (sArr4[i11] ^ s3) & checkLessThanZero;
                sArr5[i11] = (short) (s3 ^ s4);
                sArr4[i11] = (short) (sArr4[i11] ^ s4);
            }
            short s5 = sArr2[0];
            short s6 = sArr3[0];
            for (int i12 = 0; i12 < i5; i12++) {
                sArr3[i12] = (short) getModFreeze((sArr3[i12] * s5) - (sArr2[i12] * s6), i4);
            }
            for (int i13 = 0; i13 < i5; i13++) {
                sArr4[i13] = (short) getModFreeze((sArr4[i13] * s5) - (sArr5[i13] * s6), i4);
            }
            int i14 = 0;
            while (i14 < i3) {
                int i15 = i14 + 1;
                sArr3[i14] = sArr3[i15];
                i14 = i15;
            }
            sArr3[i3] = 0;
        }
        int inverseInRQ = getInverseInRQ(sArr2[0], i4);
        for (int i16 = 0; i16 < i3; i16++) {
            sArr[i16] = (short) getModFreeze(sArr5[i6 - i16] * inverseInRQ, i4);
        }
    }

    protected static void getRandomInputs(SecureRandom secureRandom, byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length / 8)];
        secureRandom.nextBytes(bArr2);
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((bArr2[i >>> 3] >>> (i & 7)) & 1);
        }
    }

    protected static void getRandomShortPolynomial(SecureRandom secureRandom, byte[] bArr, int i, int i2) {
        int[] iArr = new int[i];
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = getRandomUnsignedInteger(secureRandom);
        }
        sortGenerateShortPolynomial(bArr, iArr, i, i2);
    }

    protected static void getRandomSmallPolynomial(SecureRandom secureRandom, byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((((getRandomUnsignedInteger(secureRandom) & LockFreeTaskQueueCore.MAX_CAPACITY_MASK) * 3) >>> 30) - 1);
        }
    }

    protected static int getRandomUnsignedInteger(SecureRandom secureRandom) {
        byte[] bArr = new byte[4];
        secureRandom.nextBytes(bArr);
        return bToUnsignedInt(bArr[0]) + (bToUnsignedInt(bArr[1]) << 8) + (bToUnsignedInt(bArr[2]) << 16) + (bToUnsignedInt(bArr[3]) << 24);
    }

    protected static void getRoundedDecodedPolynomial(short[] sArr, byte[] bArr, int i, int i2) {
        short[] sArr2 = new short[i];
        short[] sArr3 = new short[i];
        for (int i3 = 0; i3 < i; i3++) {
            sArr3[i3] = (short) ((i2 + 2) / 3);
        }
        decode(sArr2, bArr, sArr3, i, 0, 0);
        for (int i4 = 0; i4 < i; i4++) {
            sArr[i4] = (short) ((sArr2[i4] * 3) - ((i2 - 1) / 2));
        }
    }

    protected static void getRoundedEncodedPolynomial(byte[] bArr, short[] sArr, int i, int i2) {
        short[] sArr2 = new short[i];
        short[] sArr3 = new short[i];
        for (int i3 = 0; i3 < i; i3++) {
            sArr2[i3] = (short) (((sArr[i3] + ((i2 - 1) / 2)) * 10923) >>> 15);
            sArr3[i3] = (short) ((i2 + 2) / 3);
        }
        encode(bArr, sArr2, sArr3, i, 0);
    }

    private static int[] getSignedDivMod(int i, int i2) {
        int[] unsignedDivMod = getUnsignedDivMod(toIntExact(iToUnsignedLong(i) - 2147483648L), i2);
        int[] unsignedDivMod2 = getUnsignedDivMod(Integer.MIN_VALUE, i2);
        int intExact = toIntExact(iToUnsignedLong(unsignedDivMod[0]) - iToUnsignedLong(unsignedDivMod2[0]));
        int intExact2 = toIntExact(iToUnsignedLong(unsignedDivMod[1]) - iToUnsignedLong(unsignedDivMod2[1]));
        int i3 = -(intExact2 >>> 31);
        return new int[]{intExact + i3, intExact2 + (i2 & i3)};
    }

    protected static void getTopDecodedPolynomial(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr2.length; i++) {
            int i2 = i * 2;
            bArr[i2] = (byte) (bArr2[i] & Ascii.SI);
            bArr[i2 + 1] = (byte) (bArr2[i] >>> 4);
        }
    }

    protected static void getTopEncodedPolynomial(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (bArr2[i2] + (bArr2[i2 + 1] << 4));
        }
    }

    private static int[] getUnsignedDivMod(int i, int i2) {
        long iToUnsignedLong = iToUnsignedLong(i);
        long j = (long) i2;
        long iToUnsignedLong2 = iToUnsignedLong(Integer.MIN_VALUE) / j;
        long j2 = (iToUnsignedLong * iToUnsignedLong2) >>> 31;
        long j3 = iToUnsignedLong - (j2 * j);
        long j4 = (iToUnsignedLong2 * j3) >>> 31;
        long j5 = (j3 - (j4 * j)) - j;
        long j6 = -(j5 >>> 63);
        return new int[]{toIntExact(j2 + j4 + 1 + j6), toIntExact(j5 + (j & j6))};
    }

    private static int getUnsignedMod(int i, int i2) {
        return getUnsignedDivMod(i, i2)[1];
    }

    static long iToUnsignedLong(int i) {
        return ((long) i) & 4294967295L;
    }

    protected static boolean isInvertiblePolynomialInR3(byte[] bArr, byte[] bArr2, int i) {
        int i2 = i;
        int i3 = i2 + 1;
        byte[] bArr3 = new byte[i3];
        byte[] bArr4 = new byte[i3];
        byte[] bArr5 = new byte[i3];
        byte[] bArr6 = new byte[i3];
        bArr5[0] = 1;
        bArr3[0] = 1;
        int i4 = i2 - 1;
        bArr3[i4] = -1;
        bArr3[i2] = -1;
        for (int i5 = 0; i5 < i2; i5++) {
            bArr4[i4 - i5] = bArr[i5];
        }
        bArr4[i2] = 0;
        int i6 = 1;
        for (int i7 = 0; i7 < (i2 * 2) - 1; i7++) {
            System.arraycopy(bArr6, 0, bArr6, 1, i2);
            bArr6[0] = 0;
            int i8 = (-bArr4[0]) * bArr3[0];
            int i9 = -i6;
            byte checkLessThanZero = checkLessThanZero(i9) & checkNotEqualToZero(bArr4[0]);
            i6 = (i6 ^ ((i9 ^ i6) & checkLessThanZero)) + 1;
            for (int i10 = 0; i10 < i3; i10++) {
                byte b = bArr3[i10];
                byte b2 = (bArr4[i10] ^ b) & checkLessThanZero;
                bArr3[i10] = (byte) (b ^ b2);
                bArr4[i10] = (byte) (bArr4[i10] ^ b2);
                byte b3 = bArr6[i10];
                byte b4 = (bArr5[i10] ^ b3) & checkLessThanZero;
                bArr6[i10] = (byte) (b3 ^ b4);
                bArr5[i10] = (byte) (bArr5[i10] ^ b4);
            }
            for (int i11 = 0; i11 < i3; i11++) {
                bArr4[i11] = (byte) getModFreeze(bArr4[i11] + (bArr3[i11] * i8), 3);
            }
            for (int i12 = 0; i12 < i3; i12++) {
                bArr5[i12] = (byte) getModFreeze(bArr5[i12] + (bArr6[i12] * i8), 3);
            }
            int i13 = 0;
            while (i13 < i2) {
                int i14 = i13 + 1;
                bArr4[i13] = bArr4[i14];
                i13 = i14;
            }
            bArr4[i2] = 0;
        }
        byte b5 = bArr3[0];
        for (int i15 = 0; i15 < i2; i15++) {
            bArr2[i15] = (byte) (bArr6[i4 - i15] * b5);
        }
        return i6 == 0;
    }

    protected static void minmax(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i2];
        int i5 = i3 ^ i4;
        int i6 = i4 - i3;
        int i7 = i5 & (-((i6 ^ (((i6 ^ i4) ^ Integer.MIN_VALUE) & i5)) >>> 31));
        iArr[i] = i3 ^ i7;
        iArr[i2] = i4 ^ i7;
    }

    protected static void multiplicationInR3(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = i + i;
        int i3 = i2 - 1;
        byte[] bArr4 = new byte[i3];
        for (int i4 = 0; i4 < i; i4++) {
            byte b = 0;
            for (int i5 = 0; i5 <= i4; i5++) {
                b = (byte) getModFreeze(b + (bArr2[i5] * bArr3[i4 - i5]), 3);
            }
            bArr4[i4] = b;
        }
        for (int i6 = i; i6 < i3; i6++) {
            byte b2 = 0;
            for (int i7 = (i6 - i) + 1; i7 < i; i7++) {
                b2 = (byte) getModFreeze(b2 + (bArr2[i7] * bArr3[i6 - i7]), 3);
            }
            bArr4[i6] = b2;
        }
        for (int i8 = i2 - 2; i8 >= i; i8--) {
            int i9 = i8 - i;
            bArr4[i9] = (byte) getModFreeze(bArr4[i9] + bArr4[i8], 3);
            int i10 = i9 + 1;
            bArr4[i10] = (byte) getModFreeze(bArr4[i10] + bArr4[i8], 3);
        }
        for (int i11 = 0; i11 < i; i11++) {
            bArr[i11] = bArr4[i11];
        }
    }

    protected static void multiplicationInRQ(short[] sArr, short[] sArr2, byte[] bArr, int i, int i2) {
        int i3 = i + i;
        int i4 = i3 - 1;
        short[] sArr3 = new short[i4];
        for (int i5 = 0; i5 < i; i5++) {
            short s = 0;
            for (int i6 = 0; i6 <= i5; i6++) {
                s = (short) getModFreeze(s + (sArr2[i6] * bArr[i5 - i6]), i2);
            }
            sArr3[i5] = s;
        }
        for (int i7 = i; i7 < i4; i7++) {
            short s2 = 0;
            for (int i8 = (i7 - i) + 1; i8 < i; i8++) {
                s2 = (short) getModFreeze(s2 + (sArr2[i8] * bArr[i7 - i8]), i2);
            }
            sArr3[i7] = s2;
        }
        for (int i9 = i3 - 2; i9 >= i; i9--) {
            int i10 = i9 - i;
            sArr3[i10] = (short) getModFreeze(sArr3[i10] + sArr3[i9], i2);
            int i11 = i10 + 1;
            sArr3[i11] = (short) getModFreeze(sArr3[i11] + sArr3[i9], i2);
        }
        for (int i12 = 0; i12 < i; i12++) {
            sArr[i12] = sArr3[i12];
        }
    }

    protected static void right(byte[] bArr, short[] sArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < bArr.length; i5++) {
            bArr[i5] = (byte) (-checkLessThanZero(getModFreeze((getModFreeze((bArr2[i5] * i4) - i3, i) - sArr[i5]) + (i2 * 4) + 1, i)));
        }
    }

    protected static void roundPolynomial(short[] sArr, short[] sArr2) {
        for (int i = 0; i < sArr.length; i++) {
            short s = sArr2[i];
            sArr[i] = (short) (s - getModFreeze(s, 3));
        }
    }

    static int sToUnsignedInt(short s) {
        return s & 65535;
    }

    protected static void scalarMultiplicationInRQ(short[] sArr, short[] sArr2, int i, int i2) {
        for (int i3 = 0; i3 < sArr2.length; i3++) {
            sArr[i3] = (short) getModFreeze(sArr2[i3] * i, i2);
        }
    }

    protected static void sortGenerateShortPolynomial(byte[] bArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = iArr[i3] & -2;
        }
        while (i2 < i) {
            iArr[i2] = (iArr[i2] & -3) | 1;
            i2++;
        }
        cryptoSort(iArr, i);
        for (int i4 = 0; i4 < i; i4++) {
            bArr[i4] = (byte) ((iArr[i4] & 3) - 1);
        }
    }

    static int toIntExact(long j) {
        int i = (int) j;
        if (((long) i) == j) {
            return i;
        }
        throw new IllegalStateException("value out of integer range");
    }

    protected static void top(byte[] bArr, short[] sArr, byte[] bArr2, int i, int i2, int i3) {
        for (int i4 = 0; i4 < bArr.length; i4++) {
            bArr[i4] = (byte) ((((getModFreeze(sArr[i4] + (bArr2[i4] * ((i - 1) / 2)), i) + i2) * i3) + 16384) >>> 15);
        }
    }

    protected static void transformRQToR3(byte[] bArr, short[] sArr) {
        for (int i = 0; i < sArr.length; i++) {
            bArr[i] = (byte) getModFreeze(sArr[i], 3);
        }
    }

    protected static void updateDiffMask(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b = bArr[i2];
            bArr[i2] = (byte) (b ^ ((bArr2[i2] ^ b) & i));
        }
    }
}
