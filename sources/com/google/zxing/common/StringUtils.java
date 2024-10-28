package com.google.zxing.common;

import java.nio.charset.Charset;

public final class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final String EUC_JP = "EUC_JP";
    public static final String GB2312 = "GB2312";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING;
    public static final String SHIFT_JIS = "SJIS";
    private static final String UTF8 = "UTF8";

    static {
        String name = Charset.defaultCharset().name();
        PLATFORM_DEFAULT_ENCODING = name;
        ASSUME_SHIFT_JIS = SHIFT_JIS.equalsIgnoreCase(name) || EUC_JP.equalsIgnoreCase(name);
    }

    private StringUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:132:0x00f2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String guessEncoding(byte[] r21, java.util.Map<com.google.zxing.DecodeHintType, ?> r22) {
        /*
            r0 = r21
            r1 = r22
            if (r1 == 0) goto L_0x0019
            com.google.zxing.DecodeHintType r2 = com.google.zxing.DecodeHintType.CHARACTER_SET
            boolean r2 = r1.containsKey(r2)
            if (r2 == 0) goto L_0x0019
            com.google.zxing.DecodeHintType r0 = com.google.zxing.DecodeHintType.CHARACTER_SET
            java.lang.Object r0 = r1.get(r0)
            java.lang.String r0 = r0.toString()
            return r0
        L_0x0019:
            int r1 = r0.length
            int r2 = r0.length
            r3 = 2
            r4 = 3
            r5 = 1
            r6 = 0
            if (r2 <= r4) goto L_0x0035
            byte r2 = r0[r6]
            r7 = -17
            if (r2 != r7) goto L_0x0035
            byte r2 = r0[r5]
            r7 = -69
            if (r2 != r7) goto L_0x0035
            byte r2 = r0[r3]
            r7 = -65
            if (r2 != r7) goto L_0x0035
            r2 = 1
            goto L_0x0036
        L_0x0035:
            r2 = 0
        L_0x0036:
            r3 = 0
            r7 = 1
            r8 = 1
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
        L_0x0046:
            if (r9 >= r1) goto L_0x00fb
            if (r5 != 0) goto L_0x004e
            if (r7 != 0) goto L_0x004e
            if (r8 == 0) goto L_0x00fb
        L_0x004e:
            byte r4 = r0[r9]
            r0 = r4 & 255(0xff, float:3.57E-43)
            if (r8 == 0) goto L_0x005c
            if (r10 <= 0) goto L_0x0062
            r4 = r4 & 128(0x80, float:1.794E-43)
            if (r4 == 0) goto L_0x005f
            int r10 = r10 + -1
        L_0x005c:
            r19 = r1
            goto L_0x0089
        L_0x005f:
            r19 = r1
            goto L_0x0088
        L_0x0062:
            r19 = r1
            r1 = r4 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0089
            r1 = r4 & 64
            if (r1 == 0) goto L_0x0088
            int r1 = r10 + 1
            r20 = r4 & 32
            if (r20 != 0) goto L_0x0076
            int r12 = r12 + 1
        L_0x0074:
            r10 = r1
            goto L_0x0089
        L_0x0076:
            int r1 = r10 + 2
            r20 = r4 & 16
            if (r20 != 0) goto L_0x007f
            int r13 = r13 + 1
            goto L_0x0074
        L_0x007f:
            int r10 = r10 + 3
            r1 = r4 & 8
            if (r1 != 0) goto L_0x0088
            int r14 = r14 + 1
            goto L_0x0089
        L_0x0088:
            r8 = 0
        L_0x0089:
            r1 = 160(0xa0, float:2.24E-43)
            r4 = 127(0x7f, float:1.78E-43)
            if (r5 == 0) goto L_0x00a7
            if (r0 <= r4) goto L_0x0095
            if (r0 >= r1) goto L_0x0095
            r5 = 0
            goto L_0x00a7
        L_0x0095:
            r1 = 159(0x9f, float:2.23E-43)
            if (r0 <= r1) goto L_0x00a7
            r1 = 192(0xc0, float:2.69E-43)
            if (r0 < r1) goto L_0x00a5
            r1 = 215(0xd7, float:3.01E-43)
            if (r0 == r1) goto L_0x00a5
            r1 = 247(0xf7, float:3.46E-43)
            if (r0 != r1) goto L_0x00a7
        L_0x00a5:
            int r16 = r16 + 1
        L_0x00a7:
            if (r7 == 0) goto L_0x00f2
            if (r11 <= 0) goto L_0x00b9
            r1 = 64
            if (r0 < r1) goto L_0x00f1
            if (r0 == r4) goto L_0x00f1
            r1 = 252(0xfc, float:3.53E-43)
            if (r0 <= r1) goto L_0x00b6
            goto L_0x00f1
        L_0x00b6:
            int r11 = r11 + -1
            goto L_0x00f2
        L_0x00b9:
            r1 = 128(0x80, float:1.794E-43)
            if (r0 == r1) goto L_0x00f1
            r1 = 160(0xa0, float:2.24E-43)
            if (r0 == r1) goto L_0x00f1
            r4 = 239(0xef, float:3.35E-43)
            if (r0 <= r4) goto L_0x00c6
            goto L_0x00f1
        L_0x00c6:
            if (r0 <= r1) goto L_0x00db
            r1 = 224(0xe0, float:3.14E-43)
            if (r0 >= r1) goto L_0x00db
            int r3 = r3 + 1
            int r0 = r18 + 1
            if (r0 <= r15) goto L_0x00d6
            r15 = r0
            r18 = r15
            goto L_0x00d8
        L_0x00d6:
            r18 = r0
        L_0x00d8:
            r17 = 0
            goto L_0x00f2
        L_0x00db:
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L_0x00ec
            int r11 = r11 + 1
            int r0 = r17 + 1
            if (r0 <= r6) goto L_0x00e9
            r6 = r0
            r17 = r6
            goto L_0x00ee
        L_0x00e9:
            r17 = r0
            goto L_0x00ee
        L_0x00ec:
            r17 = 0
        L_0x00ee:
            r18 = 0
            goto L_0x00f2
        L_0x00f1:
            r7 = 0
        L_0x00f2:
            int r9 = r9 + 1
            r0 = r21
            r1 = r19
            r4 = 3
            goto L_0x0046
        L_0x00fb:
            r19 = r1
            if (r8 == 0) goto L_0x0102
            if (r10 <= 0) goto L_0x0102
            r8 = 0
        L_0x0102:
            if (r7 == 0) goto L_0x0107
            if (r11 <= 0) goto L_0x0107
            r7 = 0
        L_0x0107:
            java.lang.String r0 = "UTF8"
            if (r8 == 0) goto L_0x0112
            if (r2 != 0) goto L_0x0111
            int r12 = r12 + r13
            int r12 = r12 + r14
            if (r12 <= 0) goto L_0x0112
        L_0x0111:
            return r0
        L_0x0112:
            java.lang.String r1 = "SJIS"
            if (r7 == 0) goto L_0x0120
            boolean r2 = ASSUME_SHIFT_JIS
            if (r2 != 0) goto L_0x011f
            r2 = 3
            if (r15 >= r2) goto L_0x011f
            if (r6 < r2) goto L_0x0120
        L_0x011f:
            return r1
        L_0x0120:
            java.lang.String r2 = "ISO8859_1"
            if (r5 == 0) goto L_0x0133
            if (r7 == 0) goto L_0x0133
            r4 = 2
            if (r15 != r4) goto L_0x012b
            if (r3 == r4) goto L_0x0131
        L_0x012b:
            int r0 = r16 * 10
            r3 = r19
            if (r0 < r3) goto L_0x0132
        L_0x0131:
            return r1
        L_0x0132:
            return r2
        L_0x0133:
            if (r5 == 0) goto L_0x0136
            return r2
        L_0x0136:
            if (r7 == 0) goto L_0x0139
            return r1
        L_0x0139:
            if (r8 == 0) goto L_0x013c
            return r0
        L_0x013c:
            java.lang.String r0 = PLATFORM_DEFAULT_ENCODING
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.StringUtils.guessEncoding(byte[], java.util.Map):java.lang.String");
    }
}
