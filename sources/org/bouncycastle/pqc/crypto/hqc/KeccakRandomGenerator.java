package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;

class KeccakRandomGenerator {
    private static long[] KeccakRoundConstants = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    protected int bitsInQueue;
    protected byte[] dataQueue;
    protected int fixedOutputLength;
    protected int rate;
    protected long[] state;

    public KeccakRandomGenerator() {
        this(288);
    }

    public KeccakRandomGenerator(int i) {
        this.state = new long[26];
        this.dataQueue = new byte[192];
        init(i);
    }

    private void init(int i) {
        if (i == 128 || i == 224 || i == 256 || i == 288 || i == 384 || i == 512) {
            initSponge(1600 - (i << 1));
            return;
        }
        throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
    }

    private void initSponge(int i) {
        if (i <= 0 || i >= 1600 || i % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.rate = i;
        Arrays.fill(this.state, 0);
        Arrays.fill(this.dataQueue, (byte) 0);
        this.bitsInQueue = 0;
        this.fixedOutputLength = (1600 - i) / 2;
    }

    private void keccakIncAbsorb(byte[] bArr, int i) {
        long j;
        long[] jArr;
        long j2;
        int i2 = this.rate >> 3;
        int i3 = 0;
        int i4 = i;
        int i5 = 0;
        while (true) {
            j = (long) i4;
            long j3 = (long) i2;
            if (this.state[25] + j < j3) {
                break;
            }
            int i6 = 0;
            while (true) {
                long j4 = (long) i6;
                jArr = this.state;
                j2 = jArr[25];
                if (j4 >= j3 - j2) {
                    break;
                }
                int i7 = ((int) (j2 + j4)) >> 3;
                jArr[i7] = jArr[i7] ^ (toUnsignedLong(bArr[i6 + i5] & 255) << ((int) (((this.state[25] + j4) & 7) * 8)));
                i6++;
            }
            i4 = (int) (j - (j3 - j2));
            i5 = (int) (((long) i5) + (j3 - j2));
            jArr[25] = 0;
            keccakPermutation(jArr);
        }
        while (true) {
            long[] jArr2 = this.state;
            if (i3 < i4) {
                long j5 = (long) i3;
                int i8 = ((int) (jArr2[25] + j5)) >> 3;
                jArr2[i8] = jArr2[i8] ^ (toUnsignedLong(bArr[i3 + i5] & 255) << ((int) (((this.state[25] + j5) & 7) * 8)));
                i3++;
            } else {
                jArr2[25] = jArr2[25] + j;
                return;
            }
        }
    }

    private void keccakIncFinalize(int i) {
        long[] jArr = this.state;
        int i2 = ((int) jArr[25]) >> 3;
        long j = jArr[i2];
        long unsignedLong = toUnsignedLong(i);
        long[] jArr2 = this.state;
        jArr[i2] = j ^ (unsignedLong << ((int) ((jArr2[25] & 7) * 8)));
        int i3 = (this.rate >> 3) - 1;
        int i4 = i3 >> 3;
        jArr2[i4] = jArr2[i4] ^ (toUnsignedLong(128) << ((i3 & 7) * 8));
        this.state[25] = 0;
    }

    private void keccakIncSqueeze(byte[] bArr, int i) {
        int i2 = i;
        int i3 = this.rate >> 3;
        int i4 = 0;
        while (i4 < i2) {
            long j = (long) i4;
            long[] jArr = this.state;
            long j2 = jArr[25];
            if (j >= j2) {
                break;
            }
            long j3 = (long) i3;
            bArr[i4] = (byte) ((int) (jArr[(int) (((j3 - j2) + j) >> 3)] >> ((int) ((7 & ((j3 - j2) + j)) * 8))));
            i4++;
        }
        int i5 = i2 - i4;
        long[] jArr2 = this.state;
        jArr2[25] = jArr2[25] - ((long) i4);
        while (i5 > 0) {
            keccakPermutation(this.state);
            int i6 = 0;
            while (i6 < i5 && i6 < i3) {
                bArr[i4 + i6] = (byte) ((int) (this.state[i6 >> 3] >> ((i6 & 7) * 8)));
                i6++;
            }
            i4 += i6;
            i5 -= i6;
            this.state[25] = (long) (i3 - i6);
        }
    }

    private static void keccakPermutation(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[8];
        long j10 = jArr[9];
        long j11 = jArr[10];
        long j12 = jArr[11];
        long j13 = jArr[12];
        long j14 = jArr[13];
        long j15 = jArr[14];
        long j16 = jArr[15];
        long j17 = jArr[16];
        long j18 = jArr[17];
        long j19 = jArr[18];
        long j20 = jArr[19];
        long j21 = jArr[20];
        long j22 = jArr[21];
        long j23 = jArr[22];
        long j24 = jArr[23];
        long j25 = jArr[24];
        int i = 0;
        while (i < 24) {
            long j26 = (((j ^ j6) ^ j11) ^ j16) ^ j21;
            long j27 = (((j2 ^ j7) ^ j12) ^ j17) ^ j22;
            long j28 = (((j3 ^ j8) ^ j13) ^ j18) ^ j23;
            long j29 = (((j4 ^ j9) ^ j14) ^ j19) ^ j24;
            long j30 = (((j5 ^ j10) ^ j15) ^ j20) ^ j25;
            long j31 = ((j27 << 1) | (j27 >>> -1)) ^ j30;
            long j32 = ((j28 << 1) | (j28 >>> -1)) ^ j26;
            long j33 = ((j29 << 1) | (j29 >>> -1)) ^ j27;
            long j34 = ((j30 << 1) | (j30 >>> -1)) ^ j28;
            long j35 = ((j26 << 1) | (j26 >>> -1)) ^ j29;
            long j36 = j ^ j31;
            long j37 = j6 ^ j31;
            long j38 = j11 ^ j31;
            long j39 = j16 ^ j31;
            long j40 = j21 ^ j31;
            long j41 = j2 ^ j32;
            long j42 = j7 ^ j32;
            long j43 = j12 ^ j32;
            long j44 = j17 ^ j32;
            long j45 = j22 ^ j32;
            long j46 = j3 ^ j33;
            long j47 = j8 ^ j33;
            long j48 = j13 ^ j33;
            long j49 = j18 ^ j33;
            long j50 = j23 ^ j33;
            long j51 = j4 ^ j34;
            long j52 = j9 ^ j34;
            long j53 = j14 ^ j34;
            long j54 = j19 ^ j34;
            long j55 = j24 ^ j34;
            long j56 = j5 ^ j35;
            long j57 = j10 ^ j35;
            long j58 = j15 ^ j35;
            long j59 = j20 ^ j35;
            long j60 = j25 ^ j35;
            long j61 = (j41 << 1) | (j41 >>> 63);
            long j62 = (j42 << 44) | (j42 >>> 20);
            long j63 = (j57 << 20) | (j57 >>> 44);
            long j64 = (j50 << 61) | (j50 >>> 3);
            long j65 = (j58 << 39) | (j58 >>> 25);
            long j66 = (j40 << 18) | (j40 >>> 46);
            long j67 = (j46 << 62) | (j46 >>> 2);
            long j68 = (j48 << 43) | (j48 >>> 21);
            long j69 = (j53 << 25) | (j53 >>> 39);
            long j70 = (j59 << 8) | (j59 >>> 56);
            long j71 = (j55 << 56) | (j55 >>> 8);
            long j72 = (j39 << 41) | (j39 >>> 23);
            long j73 = (j56 << 27) | (j56 >>> 37);
            long j74 = (j60 << 14) | (j60 >>> 50);
            long j75 = (j45 << 2) | (j45 >>> 62);
            long j76 = (j52 << 55) | (j52 >>> 9);
            long j77 = (j44 << 45) | (j44 >>> 19);
            long j78 = (j37 << 36) | (j37 >>> 28);
            long j79 = (j51 << 28) | (j51 >>> 36);
            long j80 = (j54 << 21) | (j54 >>> 43);
            long j81 = (j49 << 15) | (j49 >>> 49);
            long j82 = (j43 << 10) | (j43 >>> 54);
            long j83 = (j47 << 6) | (j47 >>> 58);
            long j84 = (j38 << 3) | (j38 >>> 61);
            long j85 = j36 ^ ((j62 ^ -1) & j68);
            long j86 = j62 ^ ((j68 ^ -1) & j80);
            long j87 = j68 ^ ((j80 ^ -1) & j74);
            long j88 = j80 ^ ((j74 ^ -1) & j36);
            long j89 = j74 ^ ((j36 ^ -1) & j62);
            long j90 = j79 ^ ((j63 ^ -1) & j84);
            long j91 = j63 ^ ((j84 ^ -1) & j77);
            long j92 = j84 ^ ((j77 ^ -1) & j64);
            long j93 = j77 ^ ((j64 ^ -1) & j79);
            j10 = j64 ^ ((j79 ^ -1) & j63);
            long j94 = ((j83 ^ -1) & j69) ^ j61;
            long j95 = j83 ^ ((j69 ^ -1) & j70);
            long j96 = j69 ^ ((j70 ^ -1) & j66);
            long j97 = j70 ^ ((j66 ^ -1) & j61);
            long j98 = j66 ^ ((j61 ^ -1) & j83);
            long j99 = j73 ^ ((j78 ^ -1) & j82);
            long j100 = j78 ^ ((j82 ^ -1) & j81);
            long j101 = j82 ^ ((j81 ^ -1) & j71);
            long j102 = j81 ^ ((j71 ^ -1) & j73);
            long j103 = j71 ^ ((j73 ^ -1) & j78);
            long j104 = j67 ^ ((j76 ^ -1) & j65);
            long j105 = j76 ^ ((j65 ^ -1) & j72);
            long j106 = j65 ^ ((j72 ^ -1) & j75);
            long j107 = j75 ^ ((j67 ^ -1) & j76);
            long j108 = j85 ^ KeccakRoundConstants[i];
            i++;
            j22 = j105;
            j24 = j72 ^ ((j75 ^ -1) & j67);
            j16 = j99;
            j8 = j92;
            j11 = j94;
            j4 = j88;
            j19 = j102;
            j18 = j101;
            j12 = j95;
            j23 = j106;
            j15 = j98;
            j2 = j86;
            long j109 = j90;
            j7 = j91;
            j25 = j107;
            j3 = j87;
            j13 = j96;
            j14 = j97;
            j20 = j103;
            j5 = j89;
            j = j108;
            j9 = j93;
            j17 = j100;
            j21 = j104;
            j6 = j109;
        }
        jArr[0] = j;
        jArr[1] = j2;
        jArr[2] = j3;
        jArr[3] = j4;
        jArr[4] = j5;
        jArr[5] = j6;
        jArr[6] = j7;
        jArr[7] = j8;
        jArr[8] = j9;
        jArr[9] = j10;
        jArr[10] = j11;
        jArr[11] = j12;
        jArr[12] = j13;
        jArr[13] = j14;
        jArr[14] = j15;
        jArr[15] = j16;
        jArr[16] = j17;
        jArr[17] = j18;
        jArr[18] = j19;
        jArr[19] = j20;
        jArr[20] = j21;
        jArr[21] = j22;
        jArr[22] = j23;
        jArr[23] = j24;
        jArr[24] = j25;
    }

    private static long toUnsignedLong(int i) {
        return ((long) i) & 4294967295L;
    }

    public void SHAKE256_512_ds(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        Arrays.fill(this.state, 0);
        keccakIncAbsorb(bArr2, i);
        keccakIncAbsorb(bArr3, bArr3.length);
        keccakIncFinalize(31);
        keccakIncSqueeze(bArr, 64);
    }

    public void expandSeed(byte[] bArr, int i) {
        int i2 = i & 7;
        int i3 = i - i2;
        keccakIncSqueeze(bArr, i3);
        if (i2 != 0) {
            byte[] bArr2 = new byte[8];
            keccakIncSqueeze(bArr2, 8);
            System.arraycopy(bArr2, 0, bArr, i3, i2);
        }
    }

    public void randomGeneratorInit(byte[] bArr, byte[] bArr2, int i, int i2) {
        keccakIncAbsorb(bArr, i);
        keccakIncAbsorb(bArr2, i2);
        keccakIncAbsorb(new byte[]{1}, 1);
        keccakIncFinalize(31);
    }

    public void seedExpanderInit(byte[] bArr, int i) {
        keccakIncAbsorb(bArr, i);
        keccakIncAbsorb(new byte[]{2}, 1);
        keccakIncFinalize(31);
    }

    public void squeeze(byte[] bArr, int i) {
        keccakIncSqueeze(bArr, i);
    }
}
