package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;
import org.bouncycastle.asn1.BERTags;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "jvm"}, k = 2, mv = {1, 1, 11})
/* compiled from: -Utf8.kt */
public final class _Utf8Kt {
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0080, code lost:
        if ((r0[r5] & 192) == 128) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e2, code lost:
        if ((r0[r5] & 192) == 128) goto L_0x00e4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String commonToUtf8String(byte[] r16) {
        /*
            r0 = r16
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
            int r1 = r0.length
            char[] r1 = new char[r1]
            int r2 = r0.length
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x000e:
            if (r4 >= r2) goto L_0x0168
            byte r6 = r0[r4]
            if (r6 < 0) goto L_0x002c
            char r6 = (char) r6
            int r7 = r5 + 1
            r1[r5] = r6
            int r4 = r4 + 1
        L_0x001b:
            if (r4 >= r2) goto L_0x002a
            byte r5 = r0[r4]
            if (r5 < 0) goto L_0x002a
            int r4 = r4 + 1
            char r5 = (char) r5
            int r6 = r7 + 1
            r1[r7] = r5
            r7 = r6
            goto L_0x001b
        L_0x002a:
            r5 = r7
            goto L_0x000e
        L_0x002c:
            int r7 = r6 >> 5
            r8 = -2
            r10 = 128(0x80, float:1.794E-43)
            r11 = 65533(0xfffd, float:9.1831E-41)
            r12 = 1
            if (r7 != r8) goto L_0x0064
            int r7 = r4 + 1
            if (r2 > r7) goto L_0x0042
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
        L_0x0040:
            r9 = 1
            goto L_0x0062
        L_0x0042:
            byte r7 = r0[r7]
            r8 = r7 & 192(0xc0, float:2.69E-43)
            if (r8 != r10) goto L_0x005c
            r7 = r7 ^ 3968(0xf80, float:5.56E-42)
            int r6 = r6 << 6
            r6 = r6 ^ r7
            if (r6 >= r10) goto L_0x0055
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
            goto L_0x005a
        L_0x0055:
            char r6 = (char) r6
            int r7 = r5 + 1
            r1[r5] = r6
        L_0x005a:
            r9 = 2
            goto L_0x0062
        L_0x005c:
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
            goto L_0x0040
        L_0x0062:
            int r4 = r4 + r9
            goto L_0x002a
        L_0x0064:
            int r7 = r6 >> 4
            r13 = 55296(0xd800, float:7.7486E-41)
            r14 = 57343(0xdfff, float:8.0355E-41)
            r15 = 3
            if (r7 != r8) goto L_0x00c4
            int r7 = r4 + 2
            if (r2 > r7) goto L_0x0083
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
            int r5 = r4 + 1
            if (r2 <= r5) goto L_0x0040
            byte r5 = r0[r5]
            r5 = r5 & 192(0xc0, float:2.69E-43)
            if (r5 != r10) goto L_0x0040
        L_0x0082:
            goto L_0x005a
        L_0x0083:
            int r8 = r4 + 1
            byte r8 = r0[r8]
            r9 = r8 & 192(0xc0, float:2.69E-43)
            if (r9 != r10) goto L_0x00bd
            byte r7 = r0[r7]
            r9 = r7 & 192(0xc0, float:2.69E-43)
            if (r9 != r10) goto L_0x00b7
            r9 = -123008(0xfffffffffffe1f80, float:NaN)
            r7 = r7 ^ r9
            int r8 = r8 << 6
            r7 = r7 ^ r8
            int r6 = r6 << 12
            r6 = r6 ^ r7
            r7 = 2048(0x800, float:2.87E-42)
            if (r6 >= r7) goto L_0x00a5
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
            goto L_0x00b5
        L_0x00a5:
            if (r13 <= r6) goto L_0x00a8
            goto L_0x00b0
        L_0x00a8:
            if (r14 < r6) goto L_0x00b0
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
            goto L_0x00b5
        L_0x00b0:
            char r6 = (char) r6
            int r7 = r5 + 1
            r1[r5] = r6
        L_0x00b5:
            r9 = 3
            goto L_0x0062
        L_0x00b7:
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
            goto L_0x0082
        L_0x00bd:
            char r6 = (char) r11
            int r7 = r5 + 1
            r1[r5] = r6
            goto L_0x0040
        L_0x00c4:
            int r7 = r6 >> 3
            if (r7 != r8) goto L_0x015f
            int r7 = r4 + 3
            if (r2 > r7) goto L_0x00ed
            int r6 = r5 + 1
            r1[r5] = r11
            int r5 = r4 + 1
            if (r2 <= r5) goto L_0x00ea
            byte r5 = r0[r5]
            r5 = r5 & 192(0xc0, float:2.69E-43)
            if (r5 != r10) goto L_0x00ea
            int r5 = r4 + 2
            if (r2 <= r5) goto L_0x00e7
            byte r5 = r0[r5]
            r5 = r5 & 192(0xc0, float:2.69E-43)
            if (r5 != r10) goto L_0x00e7
        L_0x00e4:
            r9 = 3
            goto L_0x015d
        L_0x00e7:
            r9 = 2
            goto L_0x015d
        L_0x00ea:
            r9 = 1
            goto L_0x015d
        L_0x00ed:
            int r8 = r4 + 1
            byte r8 = r0[r8]
            r9 = r8 & 192(0xc0, float:2.69E-43)
            if (r9 != r10) goto L_0x0158
            int r9 = r4 + 2
            byte r9 = r0[r9]
            r12 = r9 & 192(0xc0, float:2.69E-43)
            if (r12 != r10) goto L_0x0153
            byte r7 = r0[r7]
            r12 = r7 & 192(0xc0, float:2.69E-43)
            if (r12 != r10) goto L_0x014e
            r10 = 3678080(0x381f80, float:5.154088E-39)
            r7 = r7 ^ r10
            int r9 = r9 << 6
            r7 = r7 ^ r9
            int r8 = r8 << 12
            r7 = r7 ^ r8
            int r6 = r6 << 18
            r6 = r6 ^ r7
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r6 <= r7) goto L_0x011a
            int r6 = r5 + 1
            r1[r5] = r11
            goto L_0x014c
        L_0x011a:
            if (r13 <= r6) goto L_0x011d
            goto L_0x0124
        L_0x011d:
            if (r14 < r6) goto L_0x0124
            int r6 = r5 + 1
            r1[r5] = r11
            goto L_0x014c
        L_0x0124:
            r7 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r7) goto L_0x012d
            int r6 = r5 + 1
            r1[r5] = r11
            goto L_0x014c
        L_0x012d:
            if (r6 == r11) goto L_0x0146
            int r7 = r6 >>> 10
            r8 = 55232(0xd7c0, float:7.7397E-41)
            int r7 = r7 + r8
            char r7 = (char) r7
            int r8 = r5 + 1
            r1[r5] = r7
            r6 = r6 & 1023(0x3ff, float:1.434E-42)
            r7 = 56320(0xdc00, float:7.8921E-41)
            int r6 = r6 + r7
            char r6 = (char) r6
            int r5 = r5 + 2
            r1[r8] = r6
            goto L_0x014b
        L_0x0146:
            int r6 = r5 + 1
            r1[r5] = r11
            r5 = r6
        L_0x014b:
            r6 = r5
        L_0x014c:
            r9 = 4
            goto L_0x015d
        L_0x014e:
            int r6 = r5 + 1
            r1[r5] = r11
            goto L_0x00e4
        L_0x0153:
            int r6 = r5 + 1
            r1[r5] = r11
            goto L_0x00e7
        L_0x0158:
            int r6 = r5 + 1
            r1[r5] = r11
            goto L_0x00ea
        L_0x015d:
            int r4 = r4 + r9
            goto L_0x0165
        L_0x015f:
            int r6 = r5 + 1
            r1[r5] = r11
            int r4 = r4 + 1
        L_0x0165:
            r5 = r6
            goto L_0x000e
        L_0x0168:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1, r3, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._Utf8Kt.commonToUtf8String(byte[]):java.lang.String");
    }

    public static final byte[] commonAsUtf8ToByteArray(String str) {
        int i;
        int i2;
        char charAt;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        byte[] bArr = new byte[(str.length() * 4)];
        int length = str.length();
        int i3 = 0;
        while (i < length) {
            char charAt2 = str.charAt(i);
            if (charAt2 >= 128) {
                int length2 = str.length();
                int i4 = i;
                while (i < length2) {
                    char charAt3 = str.charAt(i);
                    if (charAt3 < 128) {
                        int i5 = i4 + 1;
                        bArr[i4] = (byte) charAt3;
                        i++;
                        while (i < length2 && str.charAt(i) < 128) {
                            bArr[i5] = (byte) str.charAt(i);
                            i++;
                            i5++;
                        }
                        i4 = i5;
                    } else {
                        if (charAt3 < 2048) {
                            bArr[i4] = (byte) ((charAt3 >> 6) | 192);
                            i4 += 2;
                            bArr[i4 + 1] = (byte) ((charAt3 & '?') | 128);
                        } else if (55296 > charAt3 || 57343 < charAt3) {
                            bArr[i4] = (byte) ((charAt3 >> 12) | BERTags.FLAGS);
                            bArr[i4 + 1] = (byte) (((charAt3 >> 6) & 63) | 128);
                            i4 += 3;
                            bArr[i4 + 2] = (byte) ((charAt3 & '?') | 128);
                        } else if (charAt3 > 56319 || length2 <= (i2 = i + 1) || 56320 > (charAt = str.charAt(i2)) || 57343 < charAt) {
                            bArr[i4] = Utf8.REPLACEMENT_BYTE;
                            i++;
                            i4++;
                        } else {
                            int charAt4 = ((charAt3 << 10) + str.charAt(i2)) - 56613888;
                            bArr[i4] = (byte) ((charAt4 >> 18) | 240);
                            bArr[i4 + 1] = (byte) (((charAt4 >> 12) & 63) | 128);
                            bArr[i4 + 2] = (byte) (((charAt4 >> 6) & 63) | 128);
                            i4 += 4;
                            bArr[i4 + 3] = (byte) ((charAt4 & 63) | 128);
                            i += 2;
                        }
                        i++;
                    }
                }
                byte[] copyOf = Arrays.copyOf(bArr, i4);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                return copyOf;
            }
            bArr[i] = (byte) charAt2;
            i3 = i + 1;
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, str.length());
        Intrinsics.checkExpressionValueIsNotNull(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf2;
    }
}
