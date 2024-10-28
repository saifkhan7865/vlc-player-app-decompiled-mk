package okio;

import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.BERTags;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\b\u001a\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\b\u001a1\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u0017\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u0018\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u0019\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u001a\u001a\u00020\u0016*\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a1\u0010\u001c\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\b\u001a%\u0010\u001d\u001a\u00020\u001e*\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"HIGH_SURROGATE_HEADER", "", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", "", "REPLACEMENT_CHARACTER", "", "REPLACEMENT_CODE_POINT", "isIsoControl", "", "codePoint", "isUtf8Continuation", "byte", "process2Utf8Bytes", "", "beginIndex", "endIndex", "yield", "Lkotlin/Function1;", "", "process3Utf8Bytes", "process4Utf8Bytes", "processUtf16Chars", "processUtf8Bytes", "", "processUtf8CodePoints", "utf8Size", "", "size", "jvm"}, k = 2, mv = {1, 1, 11})
/* compiled from: Utf8.kt */
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = '�';
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int i) {
        return (i >= 0 && 31 >= i) || (127 <= i && 159 >= i);
    }

    public static final boolean isUtf8Continuation(byte b) {
        return (b & 192) == 128;
    }

    public static final long size(String str) {
        return size$default(str, 0, 0, 3, (Object) null);
    }

    public static final long size(String str, int i) {
        return size$default(str, i, 0, 2, (Object) null);
    }

    public static /* bridge */ /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    public static final long size(String str, int i, int i2) {
        int i3;
        char c;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        } else if (i2 < i) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        } else if (i2 <= str.length()) {
            long j = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    j++;
                } else {
                    if (charAt < 2048) {
                        i3 = 2;
                    } else if (charAt < 55296 || charAt > 57343) {
                        i3 = 3;
                    } else {
                        int i4 = i + 1;
                        if (i4 < i2) {
                            c = str.charAt(i4);
                        } else {
                            c = 0;
                        }
                        if (charAt > 56319 || c < 56320 || c > 57343) {
                            j++;
                            i = i4;
                        } else {
                            j += (long) 4;
                            i += 2;
                        }
                    }
                    j += (long) i3;
                }
                i++;
            }
            return j;
        } else {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        }
    }

    public static final void processUtf8Bytes(String str, int i, int i2, Function1<? super Byte, Unit> function1) {
        int i3;
        char charAt;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "yield");
        while (i < i2) {
            char charAt2 = str.charAt(i);
            if (charAt2 < 128) {
                function1.invoke(Byte.valueOf((byte) charAt2));
                i++;
                while (i < i2 && str.charAt(i) < 128) {
                    function1.invoke(Byte.valueOf((byte) str.charAt(i)));
                    i++;
                }
            } else {
                if (charAt2 < 2048) {
                    function1.invoke(Byte.valueOf((byte) ((charAt2 >> 6) | 192)));
                    function1.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (55296 > charAt2 || 57343 < charAt2) {
                    function1.invoke(Byte.valueOf((byte) ((charAt2 >> 12) | BERTags.FLAGS)));
                    function1.invoke(Byte.valueOf((byte) (((charAt2 >> 6) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (charAt2 > 56319 || i2 <= (i3 = i + 1) || 56320 > (charAt = str.charAt(i3)) || 57343 < charAt) {
                    function1.invoke(Byte.valueOf(REPLACEMENT_BYTE));
                } else {
                    int charAt3 = ((charAt2 << 10) + str.charAt(i3)) - 56613888;
                    function1.invoke(Byte.valueOf((byte) ((charAt3 >> 18) | 240)));
                    function1.invoke(Byte.valueOf((byte) (((charAt3 >> 12) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) (((charAt3 >> 6) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) ((charAt3 & 63) | 128)));
                    i += 2;
                }
                i++;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007b, code lost:
        if ((r11[r0] & 192) == 128) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e0, code lost:
        if ((r11[r0] & 192) == 128) goto L_0x00ad;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void processUtf8CodePoints(byte[] r11, int r12, int r13, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r14) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "yield"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
        L_0x000a:
            if (r12 >= r13) goto L_0x014b
            byte r0 = r11[r12]
            if (r0 < 0) goto L_0x0029
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r14.invoke(r0)
            int r12 = r12 + 1
        L_0x0019:
            if (r12 >= r13) goto L_0x000a
            byte r0 = r11[r12]
            if (r0 < 0) goto L_0x000a
            int r12 = r12 + 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r14.invoke(r0)
            goto L_0x0019
        L_0x0029:
            int r1 = r0 >> 5
            r2 = -2
            r3 = 2
            r4 = 128(0x80, float:1.794E-43)
            r5 = 1
            r6 = 65533(0xfffd, float:9.1831E-41)
            if (r1 != r2) goto L_0x005d
            int r1 = r12 + 1
            if (r13 > r1) goto L_0x0042
        L_0x0039:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
        L_0x0040:
            r3 = 1
            goto L_0x005b
        L_0x0042:
            byte r1 = r11[r1]
            r2 = r1 & 192(0xc0, float:2.69E-43)
            if (r2 != r4) goto L_0x0039
            r1 = r1 ^ 3968(0xf80, float:5.56E-42)
            int r0 = r0 << 6
            r0 = r0 ^ r1
            if (r0 >= r4) goto L_0x0054
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            goto L_0x0058
        L_0x0054:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x0058:
            r14.invoke(r0)
        L_0x005b:
            int r12 = r12 + r3
            goto L_0x000a
        L_0x005d:
            int r1 = r0 >> 4
            r7 = 55296(0xd800, float:7.7486E-41)
            r8 = 57343(0xdfff, float:8.0355E-41)
            r9 = 3
            if (r1 != r2) goto L_0x00bf
            int r1 = r12 + 2
            if (r13 > r1) goto L_0x007e
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            int r0 = r12 + 1
            if (r13 <= r0) goto L_0x0040
            byte r0 = r11[r0]
            r0 = r0 & 192(0xc0, float:2.69E-43)
            if (r0 != r4) goto L_0x0040
            goto L_0x005b
        L_0x007e:
            int r2 = r12 + 1
            byte r2 = r11[r2]
            r10 = r2 & 192(0xc0, float:2.69E-43)
            if (r10 != r4) goto L_0x00b7
            byte r1 = r11[r1]
            r5 = r1 & 192(0xc0, float:2.69E-43)
            if (r5 != r4) goto L_0x00af
            r3 = -123008(0xfffffffffffe1f80, float:NaN)
            r1 = r1 ^ r3
            int r2 = r2 << 6
            r1 = r1 ^ r2
            int r0 = r0 << 12
            r0 = r0 ^ r1
            r1 = 2048(0x800, float:2.87E-42)
            if (r0 >= r1) goto L_0x00a2
        L_0x009a:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
        L_0x009e:
            r14.invoke(r0)
            goto L_0x00ad
        L_0x00a2:
            if (r7 <= r0) goto L_0x00a5
            goto L_0x00a8
        L_0x00a5:
            if (r8 < r0) goto L_0x00a8
            goto L_0x009a
        L_0x00a8:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x009e
        L_0x00ad:
            r3 = 3
            goto L_0x005b
        L_0x00af:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            goto L_0x005b
        L_0x00b7:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            goto L_0x0040
        L_0x00bf:
            int r1 = r0 >> 3
            if (r1 != r2) goto L_0x0140
            int r1 = r12 + 3
            if (r13 > r1) goto L_0x00e3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            int r0 = r12 + 1
            if (r13 <= r0) goto L_0x0040
            byte r0 = r11[r0]
            r0 = r0 & 192(0xc0, float:2.69E-43)
            if (r0 != r4) goto L_0x0040
            int r0 = r12 + 2
            if (r13 <= r0) goto L_0x005b
            byte r0 = r11[r0]
            r0 = r0 & 192(0xc0, float:2.69E-43)
            if (r0 != r4) goto L_0x005b
        L_0x00e2:
            goto L_0x00ad
        L_0x00e3:
            int r2 = r12 + 1
            byte r2 = r11[r2]
            r10 = r2 & 192(0xc0, float:2.69E-43)
            if (r10 != r4) goto L_0x0137
            int r5 = r12 + 2
            byte r5 = r11[r5]
            r10 = r5 & 192(0xc0, float:2.69E-43)
            if (r10 != r4) goto L_0x012e
            byte r1 = r11[r1]
            r3 = r1 & 192(0xc0, float:2.69E-43)
            if (r3 != r4) goto L_0x0126
            r3 = 3678080(0x381f80, float:5.154088E-39)
            r1 = r1 ^ r3
            int r3 = r5 << 6
            r1 = r1 ^ r3
            int r2 = r2 << 12
            r1 = r1 ^ r2
            int r0 = r0 << 18
            r0 = r0 ^ r1
            r1 = 1114111(0x10ffff, float:1.561202E-39)
            if (r0 <= r1) goto L_0x0113
        L_0x010b:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
        L_0x010f:
            r14.invoke(r0)
            goto L_0x0123
        L_0x0113:
            if (r7 <= r0) goto L_0x0116
            goto L_0x0119
        L_0x0116:
            if (r8 < r0) goto L_0x0119
            goto L_0x010b
        L_0x0119:
            r1 = 65536(0x10000, float:9.18355E-41)
            if (r0 >= r1) goto L_0x011e
            goto L_0x010b
        L_0x011e:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x010f
        L_0x0123:
            r3 = 4
            goto L_0x005b
        L_0x0126:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            goto L_0x00e2
        L_0x012e:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            goto L_0x005b
        L_0x0137:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            goto L_0x0040
        L_0x0140:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            r14.invoke(r0)
            int r12 = r12 + 1
            goto L_0x000a
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf8CodePoints(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007d, code lost:
        if ((r11[r0] & 192) == 128) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e2, code lost:
        if ((r11[r0] & 192) == 128) goto L_0x00ad;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void processUtf16Chars(byte[] r11, int r12, int r13, kotlin.jvm.functions.Function1<? super java.lang.Character, kotlin.Unit> r14) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "yield"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
        L_0x000a:
            if (r12 >= r13) goto L_0x0166
            byte r0 = r11[r12]
            if (r0 < 0) goto L_0x002b
            char r0 = (char) r0
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
            int r12 = r12 + 1
        L_0x001a:
            if (r12 >= r13) goto L_0x000a
            byte r0 = r11[r12]
            if (r0 < 0) goto L_0x000a
            int r12 = r12 + 1
            char r0 = (char) r0
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
            goto L_0x001a
        L_0x002b:
            int r1 = r0 >> 5
            r2 = -2
            r3 = 2
            r4 = 128(0x80, float:1.794E-43)
            r5 = 1
            r6 = 65533(0xfffd, float:9.1831E-41)
            if (r1 != r2) goto L_0x005e
            int r1 = r12 + 1
            if (r13 > r1) goto L_0x0045
        L_0x003b:
            char r0 = (char) r6
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
        L_0x0043:
            r3 = 1
            goto L_0x005c
        L_0x0045:
            byte r1 = r11[r1]
            r2 = r1 & 192(0xc0, float:2.69E-43)
            if (r2 != r4) goto L_0x003b
            r1 = r1 ^ 3968(0xf80, float:5.56E-42)
            int r0 = r0 << 6
            r0 = r0 ^ r1
            if (r0 >= r4) goto L_0x0054
            char r0 = (char) r6
            goto L_0x0055
        L_0x0054:
            char r0 = (char) r0
        L_0x0055:
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
        L_0x005c:
            int r12 = r12 + r3
            goto L_0x000a
        L_0x005e:
            int r1 = r0 >> 4
            r7 = 55296(0xd800, float:7.7486E-41)
            r8 = 57343(0xdfff, float:8.0355E-41)
            r9 = 3
            if (r1 != r2) goto L_0x00c1
            int r1 = r12 + 2
            if (r13 > r1) goto L_0x0080
            char r0 = (char) r6
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
            int r0 = r12 + 1
            if (r13 <= r0) goto L_0x0043
            byte r0 = r11[r0]
            r0 = r0 & 192(0xc0, float:2.69E-43)
            if (r0 != r4) goto L_0x0043
            goto L_0x005c
        L_0x0080:
            int r2 = r12 + 1
            byte r2 = r11[r2]
            r10 = r2 & 192(0xc0, float:2.69E-43)
            if (r10 != r4) goto L_0x00b8
            byte r1 = r11[r1]
            r5 = r1 & 192(0xc0, float:2.69E-43)
            if (r5 != r4) goto L_0x00af
            r3 = -123008(0xfffffffffffe1f80, float:NaN)
            r1 = r1 ^ r3
            int r2 = r2 << 6
            r1 = r1 ^ r2
            int r0 = r0 << 12
            r0 = r0 ^ r1
            r1 = 2048(0x800, float:2.87E-42)
            if (r0 >= r1) goto L_0x00a5
        L_0x009c:
            char r0 = (char) r6
        L_0x009d:
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
            goto L_0x00ad
        L_0x00a5:
            if (r7 <= r0) goto L_0x00a8
            goto L_0x00ab
        L_0x00a8:
            if (r8 < r0) goto L_0x00ab
            goto L_0x009c
        L_0x00ab:
            char r0 = (char) r0
            goto L_0x009d
        L_0x00ad:
            r3 = 3
            goto L_0x005c
        L_0x00af:
            char r0 = (char) r6
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
            goto L_0x005c
        L_0x00b8:
            char r0 = (char) r6
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
            goto L_0x0043
        L_0x00c1:
            int r1 = r0 >> 3
            if (r1 != r2) goto L_0x015b
            int r1 = r12 + 3
            if (r13 > r1) goto L_0x00e5
            java.lang.Character r0 = java.lang.Character.valueOf(r6)
            r14.invoke(r0)
            int r0 = r12 + 1
            if (r13 <= r0) goto L_0x0043
            byte r0 = r11[r0]
            r0 = r0 & 192(0xc0, float:2.69E-43)
            if (r0 != r4) goto L_0x0043
            int r0 = r12 + 2
            if (r13 <= r0) goto L_0x005c
            byte r0 = r11[r0]
            r0 = r0 & 192(0xc0, float:2.69E-43)
            if (r0 != r4) goto L_0x005c
        L_0x00e4:
            goto L_0x00ad
        L_0x00e5:
            int r2 = r12 + 1
            byte r2 = r11[r2]
            r10 = r2 & 192(0xc0, float:2.69E-43)
            if (r10 != r4) goto L_0x0152
            int r5 = r12 + 2
            byte r5 = r11[r5]
            r10 = r5 & 192(0xc0, float:2.69E-43)
            if (r10 != r4) goto L_0x0149
            byte r1 = r11[r1]
            r3 = r1 & 192(0xc0, float:2.69E-43)
            if (r3 != r4) goto L_0x0141
            r3 = 3678080(0x381f80, float:5.154088E-39)
            r1 = r1 ^ r3
            int r3 = r5 << 6
            r1 = r1 ^ r3
            int r2 = r2 << 12
            r1 = r1 ^ r2
            int r0 = r0 << 18
            r0 = r0 ^ r1
            r1 = 1114111(0x10ffff, float:1.561202E-39)
            if (r0 <= r1) goto L_0x0115
        L_0x010d:
            java.lang.Character r0 = java.lang.Character.valueOf(r6)
            r14.invoke(r0)
            goto L_0x013e
        L_0x0115:
            if (r7 <= r0) goto L_0x0118
            goto L_0x011b
        L_0x0118:
            if (r8 < r0) goto L_0x011b
            goto L_0x010d
        L_0x011b:
            r1 = 65536(0x10000, float:9.18355E-41)
            if (r0 >= r1) goto L_0x0120
            goto L_0x010d
        L_0x0120:
            if (r0 == r6) goto L_0x010d
            int r1 = r0 >>> 10
            r2 = 55232(0xd7c0, float:7.7397E-41)
            int r1 = r1 + r2
            char r1 = (char) r1
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            r14.invoke(r1)
            r0 = r0 & 1023(0x3ff, float:1.434E-42)
            r1 = 56320(0xdc00, float:7.8921E-41)
            int r0 = r0 + r1
            char r0 = (char) r0
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r14.invoke(r0)
        L_0x013e:
            r3 = 4
            goto L_0x005c
        L_0x0141:
            java.lang.Character r0 = java.lang.Character.valueOf(r6)
            r14.invoke(r0)
            goto L_0x00e4
        L_0x0149:
            java.lang.Character r0 = java.lang.Character.valueOf(r6)
            r14.invoke(r0)
            goto L_0x005c
        L_0x0152:
            java.lang.Character r0 = java.lang.Character.valueOf(r6)
            r14.invoke(r0)
            goto L_0x0043
        L_0x015b:
            java.lang.Character r0 = java.lang.Character.valueOf(r6)
            r14.invoke(r0)
            int r12 = r12 + 1
            goto L_0x000a
        L_0x0166:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf16Chars(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    public static final int process2Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "yield");
        int i3 = i + 1;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            function1.invoke(valueOf);
            return 1;
        }
        byte b = bArr[i];
        byte b2 = bArr[i3];
        if ((b2 & 192) == 128) {
            byte b3 = (b2 ^ 3968) ^ (b << 6);
            if (b3 < 128) {
                function1.invoke(valueOf);
                return 2;
            }
            function1.invoke(Integer.valueOf(b3));
            return 2;
        }
        function1.invoke(valueOf);
        return 1;
    }

    public static final int process3Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "yield");
        int i3 = i + 2;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            function1.invoke(valueOf);
            int i4 = i + 1;
            return (i2 <= i4 || (bArr[i4] & 192) != 128) ? 1 : 2;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if ((b2 & 192) == 128) {
            byte b3 = bArr[i3];
            if ((b3 & 192) == 128) {
                byte b4 = ((b3 ^ -123008) ^ (b2 << 6)) ^ (b << Ascii.FF);
                if (b4 < 2048) {
                    function1.invoke(valueOf);
                    return 3;
                } else if (55296 <= b4 && 57343 >= b4) {
                    function1.invoke(valueOf);
                    return 3;
                } else {
                    function1.invoke(Integer.valueOf(b4));
                    return 3;
                }
            } else {
                function1.invoke(valueOf);
                return 2;
            }
        } else {
            function1.invoke(valueOf);
            return 1;
        }
    }

    public static final int process4Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(bArr, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "yield");
        int i3 = i + 3;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            function1.invoke(valueOf);
            int i4 = i + 1;
            if (i2 <= i4 || (bArr[i4] & 192) != 128) {
                return 1;
            }
            int i5 = i + 2;
            return (i2 <= i5 || (bArr[i5] & 192) != 128) ? 2 : 3;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if ((b2 & 192) == 128) {
            byte b3 = bArr[i + 2];
            if ((b3 & 192) == 128) {
                byte b4 = bArr[i3];
                if ((b4 & 192) == 128) {
                    byte b5 = (((b4 ^ 3678080) ^ (b3 << 6)) ^ (b2 << Ascii.FF)) ^ (b << Ascii.DC2);
                    if (b5 > 1114111) {
                        function1.invoke(valueOf);
                        return 4;
                    } else if (55296 <= b5 && 57343 >= b5) {
                        function1.invoke(valueOf);
                        return 4;
                    } else if (b5 < 65536) {
                        function1.invoke(valueOf);
                        return 4;
                    } else {
                        function1.invoke(Integer.valueOf(b5));
                        return 4;
                    }
                } else {
                    function1.invoke(valueOf);
                    return 3;
                }
            } else {
                function1.invoke(valueOf);
                return 2;
            }
        } else {
            function1.invoke(valueOf);
            return 1;
        }
    }
}
