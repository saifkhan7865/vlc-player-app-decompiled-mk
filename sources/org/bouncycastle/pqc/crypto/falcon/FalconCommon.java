package org.bouncycastle.pqc.crypto.falcon;

import androidx.core.app.FrameMetricsAggregator;

class FalconCommon {
    static final int[] l2bound = {0, 101498, 208714, 428865, 892039, 1852696, 3842630, 7959734, 16468416, 34034726, 70265242};

    FalconCommon() {
    }

    /* access modifiers changed from: package-private */
    public void hash_to_point_ct(SHAKE256 shake256, short[] sArr, int i, int i2, short[] sArr2, int i3) {
        char c;
        short s;
        int i4;
        char c2;
        short s2;
        int i5;
        short[] sArr3 = new short[63];
        int i6 = 1 << i2;
        int i7 = i6 << 1;
        short s3 = new short[]{0, 65, 67, 71, 77, 86, 100, 122, 154, 205, 287}[i2];
        int i8 = i6 + s3;
        for (int i9 = 0; i9 < i8; i9++) {
            byte[] bArr = new byte[2];
            shake256.inner_shake256_extract(bArr, 0, 2);
            byte b = ((bArr[0] & 255) << 8) | (bArr[1] & 255);
            int i10 = b - ((((b - 24578) >>> 31) - 1) & 24578);
            int i11 = i10 - ((((i10 - 24578) >>> 31) - 1) & 24578);
            int i12 = (((b - 61445) >>> 31) - 1) | (i11 - ((((i11 - 12289) >>> 31) - 1) & 12289));
            if (i9 < i6) {
                sArr[i + i9] = (short) i12;
            } else if (i9 < i7) {
                sArr2[(i3 + i9) - i6] = (short) i12;
            } else {
                sArr3[i9 - i7] = (short) i12;
            }
        }
        for (int i13 = 1; i13 <= s3; i13 <<= 1) {
            int i14 = 0;
            for (int i15 = 0; i15 < i8; i15++) {
                if (i15 < i6) {
                    i4 = i + i15;
                    s = sArr[i4];
                    c = 1;
                } else if (i15 < i7) {
                    i4 = (i3 + i15) - i6;
                    s = sArr2[i4];
                    c = 2;
                } else {
                    i4 = i15 - i7;
                    s = sArr3[i4];
                    c = 3;
                }
                int i16 = i15 - i14;
                int i17 = (s >>> 15) - 1;
                i14 -= i17;
                if (i15 >= i13) {
                    int i18 = i15 - i13;
                    if (i18 < i6) {
                        i5 = (i + i15) - i13;
                        s2 = sArr[i5];
                        c2 = 1;
                    } else if (i18 < i7) {
                        i5 = (i3 + i18) - i6;
                        s2 = sArr2[i5];
                        c2 = 2;
                    } else {
                        i5 = i18 - i7;
                        s2 = sArr3[i5];
                        c2 = 3;
                    }
                    short s4 = i17 & (-(((i16 & i13) + FrameMetricsAggregator.EVERY_DURATION) >> 9));
                    if (c == 1) {
                        sArr[i4] = (short) (((s ^ s2) & s4) ^ s);
                    } else if (c == 2) {
                        sArr2[i4] = (short) (((s ^ s2) & s4) ^ s);
                    } else {
                        sArr3[i4] = (short) (((s ^ s2) & s4) ^ s);
                    }
                    char c3 = c2;
                    if (c3 == 1) {
                        sArr[i5] = (short) (s2 ^ ((s ^ s2) & s4));
                    } else if (c3 == 2) {
                        sArr2[i5] = (short) (s2 ^ ((s ^ s2) & s4));
                    } else {
                        sArr3[i5] = (short) (s2 ^ ((s ^ s2) & s4));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void hash_to_point_vartime(SHAKE256 shake256, short[] sArr, int i, int i2) {
        int i3 = 1 << i2;
        while (i3 > 0) {
            byte[] bArr = new byte[2];
            shake256.inner_shake256_extract(bArr, 0, 2);
            int i4 = ((bArr[0] & 255) << 8) | (bArr[1] & 255);
            if (i4 < 61445) {
                while (i4 >= 12289) {
                    i4 -= 12289;
                }
                sArr[i] = (short) i4;
                i3--;
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int is_short(short[] sArr, int i, short[] sArr2, int i2, int i3) {
        int i4 = 1 << i3;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            short s = sArr[i + i7];
            int i8 = i5 + (s * s);
            int i9 = i6 | i8;
            short s2 = sArr2[i2 + i7];
            i5 = i8 + (s2 * s2);
            i6 = i9 | i5;
        }
        return (((long) ((-(i6 >>> 31)) | i5)) & 4294967295L) <= ((long) l2bound[i3]) ? 1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int is_short_half(int i, short[] sArr, int i2, int i3) {
        int i4 = 1 << i3;
        int i5 = -(i >>> 31);
        for (int i6 = 0; i6 < i4; i6++) {
            short s = sArr[i2 + i6];
            i += s * s;
            i5 |= i;
        }
        return (((long) (i | (-(i5 >>> 31)))) & 4294967295L) <= ((long) l2bound[i3]) ? 1 : 0;
    }
}
