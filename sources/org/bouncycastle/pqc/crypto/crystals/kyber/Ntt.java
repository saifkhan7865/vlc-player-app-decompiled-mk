package org.bouncycastle.pqc.crypto.crystals.kyber;

class Ntt {
    public static final short[] nttZetas = {2285, 2571, 2970, 1812, 1493, 1422, 287, 202, 3158, 622, 1577, 182, 962, 2127, 1855, 1468, 573, 2004, 264, 383, 2500, 1458, 1727, 3199, 2648, 1017, 732, 608, 1787, 411, 3124, 1758, 1223, 652, 2777, 1015, 2036, 1491, 3047, 1785, 516, 3321, 3009, 2663, 1711, 2167, 126, 1469, 2476, 3239, 3058, 830, 107, 1908, 3082, 2378, 2931, 961, 1821, 2604, 448, 2264, 677, 2054, 2226, 430, 555, 843, 2078, 871, 1550, 105, 422, 587, 177, 3094, 3038, 2869, 1574, 1653, 3083, 778, 1159, 3182, 2552, 1483, 2727, 1119, 1739, 644, 2457, 349, 418, 329, 3173, 3254, 817, 1097, 603, 610, 1322, 2044, 1864, 384, 2114, 3193, 1218, 1994, 2455, 220, 2142, 1670, 2144, 1799, 2051, 794, 1819, 2475, 2459, 478, 3221, 3021, 996, 991, 958, 1869, 1522, 1628};
    public static final short[] nttZetasInv = {1701, 1807, 1460, 2371, 2338, 2333, 308, 108, 2851, 870, 854, 1510, 2535, 1278, 1530, 1185, 1659, 1187, 3109, 874, 1335, 2111, 136, 1215, 2945, 1465, 1285, 2007, 2719, 2726, 2232, 2512, 75, 156, 3000, 2911, 2980, 872, 2685, 1590, 2210, 602, 1846, 777, 147, 2170, 2551, 246, 1676, 1755, 460, 291, 235, 3152, 2742, 2907, 3224, 1779, 2458, 1251, 2486, 2774, 2899, 1103, 1275, 2652, 1065, 2881, 725, 1508, 2368, 398, 951, 247, 1421, 3222, 2499, 271, 90, 853, 1860, 3203, 1162, 1618, 666, 320, 8, 2813, 1544, 282, 1838, 1293, 2314, 552, 2677, 2106, 1571, 205, 2918, 1542, 2721, 2597, 2312, 681, 130, 1602, 1871, 829, 2946, 3065, 1325, 2756, 1861, 1474, 1202, 2367, 3147, 1752, 2707, 171, 3127, 3042, 1907, 1836, 1517, 359, 758, 1441};

    Ntt() {
    }

    public static void baseMult(Poly poly, int i, short s, short s2, short s3, short s4, short s5) {
        poly.setCoeffIndex(i, (short) (factorQMulMont(factorQMulMont(s2, s4), s5) + factorQMulMont(s, s3)));
        poly.setCoeffIndex(i + 1, (short) (factorQMulMont(s, s4) + factorQMulMont(s2, s3)));
    }

    public static short factorQMulMont(short s, short s2) {
        return Reduce.montgomeryReduce(s * s2);
    }

    public static short[] invNtt(short[] sArr) {
        short[] sArr2 = new short[256];
        System.arraycopy(sArr, 0, sArr2, 0, 256);
        int i = 0;
        for (int i2 = 2; i2 <= 128; i2 <<= 1) {
            int i3 = 0;
            while (i3 < 256) {
                int i4 = i + 1;
                short s = nttZetasInv[i];
                int i5 = i3;
                while (i5 < i3 + i2) {
                    short s2 = sArr2[i5];
                    int i6 = i5 + i2;
                    sArr2[i5] = Reduce.barretReduce((short) (sArr2[i6] + s2));
                    short s3 = (short) (s2 - sArr2[i6]);
                    sArr2[i6] = s3;
                    sArr2[i6] = factorQMulMont(s, s3);
                    i5++;
                }
                i3 = i5 + i2;
                i = i4;
            }
        }
        for (int i7 = 0; i7 < 256; i7++) {
            sArr2[i7] = factorQMulMont(sArr2[i7], nttZetasInv[127]);
        }
        return sArr2;
    }

    public static short[] ntt(short[] sArr) {
        short[] sArr2 = new short[256];
        System.arraycopy(sArr, 0, sArr2, 0, 256);
        int i = 1;
        for (int i2 = 128; i2 >= 2; i2 >>= 1) {
            int i3 = 0;
            while (i3 < 256) {
                int i4 = i + 1;
                short s = nttZetas[i];
                int i5 = i3;
                while (i5 < i3 + i2) {
                    int i6 = i5 + i2;
                    short factorQMulMont = factorQMulMont(s, sArr2[i6]);
                    sArr2[i6] = (short) (sArr2[i5] - factorQMulMont);
                    sArr2[i5] = (short) (sArr2[i5] + factorQMulMont);
                    i5++;
                }
                i3 = i5 + i2;
                i = i4;
            }
        }
        return sArr2;
    }
}
