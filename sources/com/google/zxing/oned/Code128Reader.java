package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int i = nextSet;
        boolean z = false;
        int i2 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 == 5) {
                    int i3 = -1;
                    float f = MAX_AVG_VARIANCE;
                    for (int i4 = 103; i4 <= 105; i4++) {
                        float patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i4], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < f) {
                            i3 = i4;
                            f = patternMatchVariance;
                        }
                    }
                    if (i3 >= 0 && bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                        return new int[]{i, nextSet, i3};
                    }
                    i += iArr[0] + iArr[1];
                    int i5 = i2 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i2] = 0;
                    i2--;
                } else {
                    i2++;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i3 >= iArr2.length) {
                break;
            }
            float patternMatchVariance = patternMatchVariance(iArr, iArr2[i3], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < f) {
                i2 = i3;
                f = patternMatchVariance;
            }
            i3++;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x016c, code lost:
        r14 = io.ktor.util.date.GMTDateParser.DAY_OF_MONTH;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0173, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0176, code lost:
        if (r18 == false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0178, code lost:
        if (r14 != 'e') goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x017a, code lost:
        r14 = io.ktor.util.date.GMTDateParser.DAY_OF_MONTH;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x017d, code lost:
        r14 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x017f, code lost:
        r18 = r5;
        r15 = 6;
        r23 = r12;
        r12 = r8;
        r8 = r21;
        r21 = r19;
        r19 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00df, code lost:
        if (r9 != false) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00fa, code lost:
        r5 = false;
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x011f, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0123, code lost:
        if (r9 != false) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0125, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0127, code lost:
        r5 = false;
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x012a, code lost:
        r5 = false;
        r14 = 'c';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0134, code lost:
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0136, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0168, code lost:
        r14 = 'e';
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r25, com.google.zxing.common.BitArray r26, java.util.Map<com.google.zxing.DecodeHintType, ?> r27) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            r24 = this;
            r0 = r26
            r1 = r27
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0012
            com.google.zxing.DecodeHintType r4 = com.google.zxing.DecodeHintType.ASSUME_GS1
            boolean r1 = r1.containsKey(r4)
            if (r1 == 0) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            int[] r4 = findStartPattern(r26)
            r5 = 2
            r6 = r4[r5]
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 20
            r7.<init>(r8)
            byte r9 = (byte) r6
            java.lang.Byte r9 = java.lang.Byte.valueOf(r9)
            r7.add(r9)
            switch(r6) {
                case 103: goto L_0x0037;
                case 104: goto L_0x0034;
                case 105: goto L_0x0031;
                default: goto L_0x002c;
            }
        L_0x002c:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0031:
            r12 = 99
            goto L_0x0039
        L_0x0034:
            r12 = 100
            goto L_0x0039
        L_0x0037:
            r12 = 101(0x65, float:1.42E-43)
        L_0x0039:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r8)
            r8 = r4[r3]
            r14 = r4[r2]
            r15 = 6
            int[] r2 = new int[r15]
            r9 = 0
            r16 = 0
            r17 = 1
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r23 = r12
            r12 = r8
            r8 = r14
            r14 = r23
        L_0x0058:
            if (r16 != 0) goto L_0x018e
            int r12 = decodeCode(r0, r2, r8)
            byte r5 = (byte) r12
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            r7.add(r5)
            r5 = 106(0x6a, float:1.49E-43)
            if (r12 == r5) goto L_0x006c
            r17 = 1
        L_0x006c:
            if (r12 == r5) goto L_0x0074
            int r20 = r20 + 1
            int r21 = r20 * r12
            int r6 = r6 + r21
        L_0x0074:
            r21 = r8
            r11 = 0
        L_0x0077:
            if (r11 >= r15) goto L_0x0080
            r22 = r2[r11]
            int r21 = r21 + r22
            int r11 = r11 + 1
            goto L_0x0077
        L_0x0080:
            switch(r12) {
                case 103: goto L_0x008e;
                case 104: goto L_0x008e;
                case 105: goto L_0x008e;
                default: goto L_0x0083;
            }
        L_0x0083:
            r11 = 96
            java.lang.String r15 = "]C1"
            switch(r14) {
                case 99: goto L_0x013a;
                case 100: goto L_0x00e9;
                case 101: goto L_0x0093;
                default: goto L_0x008a;
            }
        L_0x008a:
            r10 = 100
            goto L_0x0173
        L_0x008e:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0093:
            r10 = 64
            if (r12 >= r10) goto L_0x00a7
            if (r9 != r3) goto L_0x00a0
            int r5 = r12 + 32
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fa
        L_0x00a0:
            int r5 = r12 + 160
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fa
        L_0x00a7:
            if (r12 >= r11) goto L_0x00b9
            if (r9 != r3) goto L_0x00b2
            int r5 = r12 + -64
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fa
        L_0x00b2:
            int r5 = r12 + 64
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fa
        L_0x00b9:
            if (r12 == r5) goto L_0x00bd
            r17 = 0
        L_0x00bd:
            if (r12 == r5) goto L_0x0134
            switch(r12) {
                case 96: goto L_0x0136;
                case 97: goto L_0x0136;
                case 98: goto L_0x00e4;
                case 99: goto L_0x012a;
                case 100: goto L_0x00e2;
                case 101: goto L_0x00d8;
                case 102: goto L_0x00c4;
                default: goto L_0x00c2;
            }
        L_0x00c2:
            goto L_0x0136
        L_0x00c4:
            if (r1 == 0) goto L_0x0136
            int r5 = r13.length()
            if (r5 != 0) goto L_0x00d1
            r13.append(r15)
            goto L_0x0136
        L_0x00d1:
            r5 = 29
            r13.append(r5)
            goto L_0x0136
        L_0x00d8:
            if (r3 != 0) goto L_0x00dd
            if (r9 == 0) goto L_0x00dd
            goto L_0x011f
        L_0x00dd:
            if (r3 == 0) goto L_0x0127
            if (r9 == 0) goto L_0x0127
            goto L_0x0125
        L_0x00e2:
            r5 = 0
            goto L_0x00e5
        L_0x00e4:
            r5 = 1
        L_0x00e5:
            r10 = 100
            goto L_0x016c
        L_0x00e9:
            if (r12 >= r11) goto L_0x00fd
            if (r9 != r3) goto L_0x00f4
            int r5 = r12 + 32
            char r5 = (char) r5
            r13.append(r5)
            goto L_0x00fa
        L_0x00f4:
            int r5 = r12 + 160
            char r5 = (char) r5
            r13.append(r5)
        L_0x00fa:
            r5 = 0
            r9 = 0
            goto L_0x0137
        L_0x00fd:
            if (r12 == r5) goto L_0x0101
            r17 = 0
        L_0x0101:
            if (r12 == r5) goto L_0x0134
            switch(r12) {
                case 96: goto L_0x0136;
                case 97: goto L_0x0136;
                case 98: goto L_0x0130;
                case 99: goto L_0x012a;
                case 100: goto L_0x011b;
                case 101: goto L_0x0119;
                case 102: goto L_0x0107;
                default: goto L_0x0106;
            }
        L_0x0106:
            goto L_0x0136
        L_0x0107:
            if (r1 == 0) goto L_0x0136
            int r5 = r13.length()
            if (r5 != 0) goto L_0x0113
            r13.append(r15)
            goto L_0x0136
        L_0x0113:
            r5 = 29
            r13.append(r5)
            goto L_0x0136
        L_0x0119:
            r5 = 0
            goto L_0x0131
        L_0x011b:
            if (r3 != 0) goto L_0x0121
            if (r9 == 0) goto L_0x0121
        L_0x011f:
            r3 = 1
            goto L_0x00fa
        L_0x0121:
            if (r3 == 0) goto L_0x0127
            if (r9 == 0) goto L_0x0127
        L_0x0125:
            r3 = 0
            goto L_0x00fa
        L_0x0127:
            r5 = 0
            r9 = 1
            goto L_0x0137
        L_0x012a:
            r5 = 0
            r10 = 100
            r14 = 99
            goto L_0x0174
        L_0x0130:
            r5 = 1
        L_0x0131:
            r10 = 100
            goto L_0x0168
        L_0x0134:
            r16 = 1
        L_0x0136:
            r5 = 0
        L_0x0137:
            r10 = 100
            goto L_0x0174
        L_0x013a:
            r10 = 100
            if (r12 >= r10) goto L_0x014b
            r5 = 10
            if (r12 >= r5) goto L_0x0147
            r5 = 48
            r13.append(r5)
        L_0x0147:
            r13.append(r12)
            goto L_0x0173
        L_0x014b:
            if (r12 == r5) goto L_0x014f
            r17 = 0
        L_0x014f:
            if (r12 == r5) goto L_0x016f
            switch(r12) {
                case 100: goto L_0x016b;
                case 101: goto L_0x0167;
                case 102: goto L_0x0155;
                default: goto L_0x0154;
            }
        L_0x0154:
            goto L_0x0173
        L_0x0155:
            if (r1 == 0) goto L_0x0173
            int r5 = r13.length()
            if (r5 != 0) goto L_0x0161
            r13.append(r15)
            goto L_0x0173
        L_0x0161:
            r5 = 29
            r13.append(r5)
            goto L_0x0173
        L_0x0167:
            r5 = 0
        L_0x0168:
            r14 = 101(0x65, float:1.42E-43)
            goto L_0x0174
        L_0x016b:
            r5 = 0
        L_0x016c:
            r14 = 100
            goto L_0x0174
        L_0x016f:
            r5 = 0
            r16 = 1
            goto L_0x0174
        L_0x0173:
            r5 = 0
        L_0x0174:
            r11 = 101(0x65, float:1.42E-43)
            if (r18 == 0) goto L_0x017f
            if (r14 != r11) goto L_0x017d
            r14 = 100
            goto L_0x017f
        L_0x017d:
            r14 = 101(0x65, float:1.42E-43)
        L_0x017f:
            r18 = r5
            r5 = 2
            r15 = 6
            r23 = r12
            r12 = r8
            r8 = r21
            r21 = r19
            r19 = r23
            goto L_0x0058
        L_0x018e:
            int r1 = r8 - r12
            int r2 = r0.getNextUnset(r8)
            int r3 = r26.getSize()
            int r5 = r2 - r12
            r8 = 2
            int r5 = r5 / r8
            int r5 = r5 + r2
            int r3 = java.lang.Math.min(r3, r5)
            r5 = 0
            boolean r0 = r0.isRange(r2, r3, r5)
            if (r0 == 0) goto L_0x021e
            r3 = r21
            int r20 = r20 * r3
            int r6 = r6 - r20
            int r6 = r6 % 103
            if (r6 != r3) goto L_0x0219
            int r0 = r13.length()
            if (r0 == 0) goto L_0x0214
            if (r0 <= 0) goto L_0x01cb
            if (r17 == 0) goto L_0x01cb
            r2 = 99
            if (r14 != r2) goto L_0x01c6
            int r2 = r0 + -2
            r13.delete(r2, r0)
            goto L_0x01cb
        L_0x01c6:
            int r2 = r0 + -1
            r13.delete(r2, r0)
        L_0x01cb:
            r0 = 1
            r2 = r4[r0]
            r0 = 0
            r3 = r4[r0]
            int r2 = r2 + r3
            float r0 = (float) r2
            r2 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r2
            float r3 = (float) r12
            float r1 = (float) r1
            float r1 = r1 / r2
            float r3 = r3 + r1
            int r1 = r7.size()
            byte[] r2 = new byte[r1]
            r5 = 0
        L_0x01e1:
            if (r5 >= r1) goto L_0x01f2
            java.lang.Object r4 = r7.get(r5)
            java.lang.Byte r4 = (java.lang.Byte) r4
            byte r4 = r4.byteValue()
            r2[r5] = r4
            int r5 = r5 + 1
            goto L_0x01e1
        L_0x01f2:
            com.google.zxing.Result r1 = new com.google.zxing.Result
            java.lang.String r4 = r13.toString()
            r5 = 2
            com.google.zxing.ResultPoint[] r5 = new com.google.zxing.ResultPoint[r5]
            com.google.zxing.ResultPoint r6 = new com.google.zxing.ResultPoint
            r7 = r25
            float r7 = (float) r7
            r6.<init>(r0, r7)
            r0 = 0
            r5[r0] = r6
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint
            r0.<init>(r3, r7)
            r3 = 1
            r5[r3] = r0
            com.google.zxing.BarcodeFormat r0 = com.google.zxing.BarcodeFormat.CODE_128
            r1.<init>(r4, r2, r5, r0)
            return r1
        L_0x0214:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x0219:
            com.google.zxing.ChecksumException r0 = com.google.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x021e:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            goto L_0x0224
        L_0x0223:
            throw r0
        L_0x0224:
            goto L_0x0223
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
