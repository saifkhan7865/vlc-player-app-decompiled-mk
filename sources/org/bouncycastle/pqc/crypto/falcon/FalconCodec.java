package org.bouncycastle.pqc.crypto.falcon;

import com.google.common.base.Ascii;

class FalconCodec {
    final byte[] max_FG_bits = {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
    final byte[] max_fg_bits = {0, 8, 8, 8, 8, 8, 7, 7, 6, 6, 5};
    final byte[] max_sig_bits = {0, 10, Ascii.VT, Ascii.VT, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF};

    FalconCodec() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (r7 == 0) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        if (r6 != 0) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        r8 = r11 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (r7 == 0) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        r6 = -r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        r10[r8] = (short) r6;
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int comp_decode(short[] r10, int r11, int r12, byte[] r13, int r14, int r15) {
        /*
            r9 = this;
            r0 = 1
            int r12 = r0 << r12
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0008:
            if (r2 >= r12) goto L_0x004e
            if (r5 < r15) goto L_0x000d
            return r1
        L_0x000d:
            int r3 = r3 << 8
            int r6 = r14 + r5
            byte r6 = r13[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r3 = r3 | r6
            int r5 = r5 + 1
            int r6 = r3 >>> r4
            r7 = r6 & 128(0x80, float:1.794E-43)
            r6 = r6 & 127(0x7f, float:1.78E-43)
        L_0x001e:
            if (r4 != 0) goto L_0x0030
            if (r5 < r15) goto L_0x0023
            return r1
        L_0x0023:
            int r3 = r3 << 8
            int r4 = r14 + r5
            byte r4 = r13[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r3 = r3 | r4
            int r5 = r5 + 1
            r4 = 8
        L_0x0030:
            int r4 = r4 + -1
            int r8 = r3 >>> r4
            r8 = r8 & r0
            if (r8 == 0) goto L_0x0047
            if (r7 == 0) goto L_0x003c
            if (r6 != 0) goto L_0x003c
            return r1
        L_0x003c:
            int r8 = r11 + r2
            if (r7 == 0) goto L_0x0041
            int r6 = -r6
        L_0x0041:
            short r6 = (short) r6
            r10[r8] = r6
            int r2 = r2 + 1
            goto L_0x0008
        L_0x0047:
            int r6 = r6 + 128
            r8 = 2047(0x7ff, float:2.868E-42)
            if (r6 <= r8) goto L_0x001e
            return r1
        L_0x004e:
            int r10 = r0 << r4
            int r10 = r10 - r0
            r10 = r10 & r3
            if (r10 == 0) goto L_0x0055
            return r1
        L_0x0055:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.falcon.FalconCodec.comp_decode(short[], int, int, byte[], int, int):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: short} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int comp_encode(byte[] r10, int r11, int r12, short[] r13, int r14, int r15) {
        /*
            r9 = this;
            r0 = 1
            int r15 = r0 << r15
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r2 >= r15) goto L_0x0018
            int r3 = r14 + r2
            short r3 = r13[r3]
            r4 = -2047(0xfffffffffffff801, float:NaN)
            if (r3 < r4) goto L_0x0017
            r4 = 2047(0x7ff, float:2.868E-42)
            if (r3 <= r4) goto L_0x0014
            goto L_0x0017
        L_0x0014:
            int r2 = r2 + 1
            goto L_0x0005
        L_0x0017:
            return r1
        L_0x0018:
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x001c:
            r6 = 8
            if (r2 >= r15) goto L_0x004e
            int r4 = r4 << 1
            int r7 = r14 + r2
            short r7 = r13[r7]
            if (r7 >= 0) goto L_0x002b
            int r7 = -r7
            r4 = r4 | 1
        L_0x002b:
            int r4 = r4 << 7
            r8 = r7 & 127(0x7f, float:1.78E-43)
            r4 = r4 | r8
            int r7 = r7 >>> 7
            int r3 = r3 + 8
            int r7 = r7 + r0
            int r4 = r4 << r7
            r4 = r4 | r0
            int r3 = r3 + r7
        L_0x0038:
            if (r3 < r6) goto L_0x004b
            int r3 = r3 + -8
            if (r10 == 0) goto L_0x0048
            if (r5 < r12) goto L_0x0041
            return r1
        L_0x0041:
            int r7 = r11 + r5
            int r8 = r4 >>> r3
            byte r8 = (byte) r8
            r10[r7] = r8
        L_0x0048:
            int r5 = r5 + 1
            goto L_0x0038
        L_0x004b:
            int r2 = r2 + 1
            goto L_0x001c
        L_0x004e:
            if (r3 <= 0) goto L_0x005e
            if (r10 == 0) goto L_0x005c
            if (r5 < r12) goto L_0x0055
            return r1
        L_0x0055:
            int r11 = r11 + r5
            int r6 = r6 - r3
            int r12 = r4 << r6
            byte r12 = (byte) r12
            r10[r11] = r12
        L_0x005c:
            int r5 = r5 + 1
        L_0x005e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.falcon.FalconCodec.comp_encode(byte[], int, int, short[], int, int):int");
    }

    /* access modifiers changed from: package-private */
    public int modq_decode(short[] sArr, int i, int i2, byte[] bArr, int i3, int i4) {
        int i5 = 1 << i2;
        int i6 = ((i5 * 14) + 7) >> 3;
        if (i6 > i4) {
            return 0;
        }
        int i7 = 0;
        byte b = 0;
        int i8 = 0;
        while (i7 < i5) {
            int i9 = i3 + 1;
            b = (b << 8) | (bArr[i3] & 255);
            int i10 = i8 + 8;
            if (i10 >= 14) {
                i8 -= 6;
                int i11 = (b >>> i8) & 16383;
                if (i11 >= 12289) {
                    return 0;
                }
                sArr[i + i7] = (short) i11;
                i7++;
            } else {
                i8 = i10;
            }
            i3 = i9;
        }
        if ((((1 << i8) - 1) & b) != 0) {
            return 0;
        }
        return i6;
    }

    /* access modifiers changed from: package-private */
    public int modq_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4) {
        int i5 = 1 << i4;
        for (int i6 = 0; i6 < i5; i6++) {
            if ((65535 & sArr[i3 + i6]) >= 12289) {
                return 0;
            }
        }
        int i7 = ((i5 * 14) + 7) >> 3;
        if (bArr == null) {
            return i7;
        }
        if (i7 > i2) {
            return 0;
        }
        int i8 = 0;
        short s = 0;
        for (int i9 = 0; i9 < i5; i9++) {
            s = (s << 14) | (sArr[i3 + i9] & 65535);
            i8 += 14;
            while (i8 >= 8) {
                i8 -= 8;
                bArr[i] = (byte) (s >> i8);
                i++;
            }
        }
        if (i8 > 0) {
            bArr[i] = (byte) (s << (8 - i8));
        }
        return i7;
    }

    /* access modifiers changed from: package-private */
    public int trim_i16_decode(short[] sArr, int i, int i2, int i3, byte[] bArr, int i4, int i5) {
        int i6 = i3;
        int i7 = 1 << i2;
        int i8 = ((i7 * i6) + 7) >> 3;
        if (i8 > i5) {
            return 0;
        }
        int i9 = (1 << i6) - 1;
        int i10 = 1 << (i6 - 1);
        int i11 = i4;
        int i12 = 0;
        byte b = 0;
        int i13 = 0;
        while (i12 < i7) {
            int i14 = i11 + 1;
            b = (b << 8) | (bArr[i11] & 255);
            i13 += 8;
            while (i13 >= i6 && i12 < i7) {
                i13 -= i6;
                int i15 = (b >>> i13) & i9;
                int i16 = i15 | (-(i15 & i10));
                if (i16 == (-i10)) {
                    return 0;
                }
                sArr[i + i12] = (short) (i16 | (-(i16 & i10)));
                i12++;
            }
            i11 = i14;
        }
        if ((((1 << i13) - 1) & b) != 0) {
            return 0;
        }
        return i8;
    }

    /* access modifiers changed from: package-private */
    public int trim_i16_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4, int i5) {
        int i6 = 1 << i4;
        int i7 = (1 << (i5 - 1)) - 1;
        int i8 = -i7;
        for (int i9 = 0; i9 < i6; i9++) {
            short s = sArr[i3 + i9];
            if (s < i8 || s > i7) {
                return 0;
            }
        }
        int i10 = ((i6 * i5) + 7) >> 3;
        if (bArr == null) {
            return i10;
        }
        if (i10 > i2) {
            return 0;
        }
        int i11 = (1 << i5) - 1;
        int i12 = 0;
        short s2 = 0;
        for (int i13 = 0; i13 < i6; i13++) {
            s2 = (s2 << i5) | (sArr[i3 + i13] & 4095 & i11);
            i12 += i5;
            while (i12 >= 8) {
                i12 -= 8;
                bArr[i] = (byte) (s2 >> i12);
                i++;
            }
        }
        if (i12 > 0) {
            bArr[i] = (byte) (s2 << (8 - i12));
        }
        return i10;
    }

    /* access modifiers changed from: package-private */
    public int trim_i8_decode(byte[] bArr, int i, int i2, int i3, byte[] bArr2, int i4, int i5) {
        int i6 = i3;
        int i7 = 1 << i2;
        int i8 = ((i7 * i6) + 7) >> 3;
        if (i8 > i5) {
            return 0;
        }
        int i9 = (1 << i6) - 1;
        int i10 = 1 << (i6 - 1);
        int i11 = i4;
        int i12 = 0;
        byte b = 0;
        int i13 = 0;
        while (i12 < i7) {
            int i14 = i11 + 1;
            b = (b << 8) | (bArr2[i11] & 255);
            i13 += 8;
            while (i13 >= i6 && i12 < i7) {
                i13 -= i6;
                int i15 = (b >>> i13) & i9;
                int i16 = i15 | (-(i15 & i10));
                if (i16 == (-i10)) {
                    return 0;
                }
                bArr[i + i12] = (byte) i16;
                i12++;
            }
            i11 = i14;
        }
        if ((((1 << i13) - 1) & b) != 0) {
            return 0;
        }
        return i8;
    }

    /* access modifiers changed from: package-private */
    public int trim_i8_encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, int i5) {
        int i6 = 1 << i4;
        int i7 = (1 << (i5 - 1)) - 1;
        int i8 = -i7;
        for (int i9 = 0; i9 < i6; i9++) {
            byte b = bArr2[i3 + i9];
            if (b < i8 || b > i7) {
                return 0;
            }
        }
        int i10 = ((i6 * i5) + 7) >> 3;
        if (bArr == null) {
            return i10;
        }
        if (i10 > i2) {
            return 0;
        }
        int i11 = (1 << i5) - 1;
        int i12 = 0;
        byte b2 = 0;
        for (int i13 = 0; i13 < i6; i13++) {
            b2 = (b2 << i5) | (bArr2[i3 + i13] & 65535 & i11);
            i12 += i5;
            while (i12 >= 8) {
                i12 -= 8;
                bArr[i] = (byte) (b2 >>> i12);
                i++;
            }
        }
        if (i12 > 0) {
            bArr[i] = (byte) (b2 << (8 - i12));
        }
        return i10;
    }
}
