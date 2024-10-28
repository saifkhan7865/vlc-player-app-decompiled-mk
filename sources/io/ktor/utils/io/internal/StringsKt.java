package io.ktor.utils.io.internal;

import io.ktor.utils.io.charsets.UTFKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a$\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\b\u001a$\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\r\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\b\u001a(\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a$\u0010\u000f\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a$\u0010\u0010\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¨\u0006\u0011"}, d2 = {"decodeASCII", "", "Ljava/nio/ByteBuffer;", "out", "", "offset", "length", "decodeASCII3_array", "", "predicate", "Lkotlin/Function1;", "", "", "decodeASCII3_buffer", "decodeASCIILine", "decodeASCIILine_array", "decodeASCIILine_buffer", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Strings.kt */
public final class StringsKt {
    public static /* synthetic */ int decodeASCII$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeASCII(byteBuffer, cArr, i, i2);
    }

    public static final int decodeASCII(ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "out");
        if (byteBuffer.hasArray()) {
            return decodeASCII3_array(byteBuffer, cArr, i, i2);
        }
        return decodeASCII3_buffer(byteBuffer, cArr, i, i2);
    }

    public static /* synthetic */ long decodeASCIILine$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeASCIILine(byteBuffer, cArr, i, i2);
    }

    public static final long decodeASCIILine(ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "out");
        if (byteBuffer.hasArray()) {
            return decodeASCIILine_array(byteBuffer, cArr, i, i2);
        }
        return decodeASCIILine_buffer(byteBuffer, cArr, i, i2);
    }

    private static final int decodeASCII3_array(ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        int i3;
        int i4 = i2 + i;
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int remaining = byteBuffer.remaining() + arrayOffset;
        if (i4 > cArr.length || remaining > array.length) {
            i3 = i;
        } else {
            i3 = i;
            while (arrayOffset < remaining && i3 < i4) {
                byte b = array[arrayOffset];
                if (b < 0) {
                    break;
                }
                cArr[i3] = (char) b;
                i3++;
                arrayOffset++;
            }
            byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
        }
        return i3 - i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r2 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int decodeASCII3_buffer(java.nio.ByteBuffer r4, char[] r5, int r6, int r7) {
        /*
            int r7 = r7 + r6
            int r0 = r5.length
            r1 = 1
            r2 = 0
            if (r7 > r0) goto L_0x001e
            r0 = r6
        L_0x0007:
            boolean r3 = r4.hasRemaining()
            if (r3 == 0) goto L_0x001f
            byte r3 = r4.get()
            if (r3 >= 0) goto L_0x0015
        L_0x0013:
            r2 = 1
            goto L_0x001f
        L_0x0015:
            if (r0 < r7) goto L_0x0018
            goto L_0x0013
        L_0x0018:
            char r3 = (char) r3
            r5[r0] = r3
            int r0 = r0 + 1
            goto L_0x0007
        L_0x001e:
            r0 = r6
        L_0x001f:
            if (r2 == 0) goto L_0x0029
            int r5 = r4.position()
            int r5 = r5 - r1
            r4.position(r5)
        L_0x0029:
            int r0 = r0 - r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.StringsKt.decodeASCII3_buffer(java.nio.ByteBuffer, char[], int, int):int");
    }

    private static final long decodeASCII3_array(ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        int i3;
        int i4 = i2 + i;
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int remaining = byteBuffer.remaining() + arrayOffset;
        if (i4 > cArr.length || remaining > array.length) {
            i3 = i;
        } else {
            i3 = i;
            while (arrayOffset < remaining) {
                byte b = array[arrayOffset];
                if (b < 0) {
                    break;
                }
                char c = (char) b;
                if (!function1.invoke(Character.valueOf(c)).booleanValue()) {
                    byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                    return UTFKt.decodeUtf8Result(i3 - i, -1);
                } else if (i3 >= i4) {
                    break;
                } else {
                    cArr[i3] = c;
                    i3++;
                    arrayOffset++;
                }
            }
            byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
        }
        return UTFKt.decodeUtf8Result(i3 - i, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r6 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long decodeASCII3_buffer(java.nio.ByteBuffer r5, char[] r6, int r7, int r8, kotlin.jvm.functions.Function1<? super java.lang.Character, java.lang.Boolean> r9) {
        /*
            int r8 = r8 + r7
            int r0 = r6.length
            r1 = 1
            r2 = 0
            if (r8 > r0) goto L_0x0031
            r0 = r7
        L_0x0007:
            boolean r3 = r5.hasRemaining()
            if (r3 == 0) goto L_0x0032
            byte r3 = r5.get()
            if (r3 >= 0) goto L_0x0015
        L_0x0013:
            r6 = 1
            goto L_0x0033
        L_0x0015:
            char r3 = (char) r3
            java.lang.Character r4 = java.lang.Character.valueOf(r3)
            java.lang.Object r4 = r9.invoke(r4)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L_0x0029
            r6 = 1
            r8 = 1
            goto L_0x0034
        L_0x0029:
            if (r0 < r8) goto L_0x002c
            goto L_0x0013
        L_0x002c:
            r6[r0] = r3
            int r0 = r0 + 1
            goto L_0x0007
        L_0x0031:
            r0 = r7
        L_0x0032:
            r6 = 0
        L_0x0033:
            r8 = 0
        L_0x0034:
            if (r6 == 0) goto L_0x003e
            int r6 = r5.position()
            int r6 = r6 - r1
            r5.position(r6)
        L_0x003e:
            int r0 = r0 - r7
            if (r8 == 0) goto L_0x0042
            r2 = -1
        L_0x0042:
            long r5 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r0, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.StringsKt.decodeASCII3_buffer(java.nio.ByteBuffer, char[], int, int, kotlin.jvm.functions.Function1):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        if (r3 != false) goto L_0x0031;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0038 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long decodeASCIILine_array(java.nio.ByteBuffer r11, char[] r12, int r13, int r14) {
        /*
            int r14 = r14 + r13
            byte[] r0 = r11.array()
            int r1 = r11.arrayOffset()
            int r2 = r11.position()
            int r1 = r1 + r2
            int r2 = r11.remaining()
            int r2 = r2 + r1
            int r3 = r12.length
            r4 = 13
            r5 = -1
            r6 = 1
            r7 = 0
            if (r14 > r3) goto L_0x0059
            int r3 = r0.length
            if (r2 > r3) goto L_0x0059
            r8 = r13
            r3 = 0
        L_0x0020:
            if (r1 >= r2) goto L_0x0050
            byte r9 = r0[r1]
            if (r9 < 0) goto L_0x0050
            char r9 = (char) r9
            if (r9 != r4) goto L_0x002c
            r3 = 1
        L_0x002a:
            r10 = 1
            goto L_0x0036
        L_0x002c:
            r10 = 10
            if (r9 != r10) goto L_0x0033
            r3 = 0
        L_0x0031:
            r10 = 0
            goto L_0x0036
        L_0x0033:
            if (r3 == 0) goto L_0x002a
            goto L_0x0031
        L_0x0036:
            if (r10 != 0) goto L_0x0046
            int r14 = r11.arrayOffset()
            int r1 = r1 - r14
            r11.position(r1)
            int r8 = r8 - r13
            long r13 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r8, r5)
            goto L_0x0060
        L_0x0046:
            if (r8 < r14) goto L_0x0049
            goto L_0x0050
        L_0x0049:
            r12[r8] = r9
            int r8 = r8 + 1
            int r1 = r1 + 1
            goto L_0x0020
        L_0x0050:
            int r14 = r11.arrayOffset()
            int r1 = r1 - r14
            r11.position(r1)
            goto L_0x005b
        L_0x0059:
            r8 = r13
            r3 = 0
        L_0x005b:
            int r8 = r8 - r13
            long r13 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r8, r7)
        L_0x0060:
            r0 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r0 = r0 & r13
            int r1 = (int) r0
            r0 = 32
            if (r1 != r5) goto L_0x008a
            long r0 = r13 >> r0
            int r1 = (int) r0
            if (r3 == 0) goto L_0x0076
            int r1 = r1 - r6
            long r11 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r1, r5)
            return r11
        L_0x0076:
            int r0 = r11.position()
            int r0 = r0 + r6
            r11.position(r0)
            if (r1 <= 0) goto L_0x009e
            int r1 = r1 - r6
            char r11 = r12[r1]
            if (r11 != r4) goto L_0x009e
            long r11 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r1, r5)
            return r11
        L_0x008a:
            if (r3 == 0) goto L_0x009e
            long r12 = r13 >> r0
            int r13 = (int) r12
            int r12 = r11.position()
            int r12 = r12 - r6
            r11.position(r12)
            int r13 = r13 - r6
            r11 = 2
            long r11 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r13, r11)
            return r11
        L_0x009e:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.StringsKt.decodeASCIILine_array(java.nio.ByteBuffer, char[], int, int):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        if (r0 != false) goto L_0x0023;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x002a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long decodeASCIILine_buffer(java.nio.ByteBuffer r7, char[] r8, int r9, int r10) {
        /*
            int r10 = r10 + r9
            int r0 = r8.length
            r1 = 13
            r2 = 1
            r3 = 0
            r4 = r9
            if (r10 > r0) goto L_0x0037
            r0 = 0
        L_0x000a:
            boolean r5 = r7.hasRemaining()
            if (r5 == 0) goto L_0x0035
            byte r5 = r7.get()
            if (r5 >= 0) goto L_0x0018
        L_0x0016:
            r10 = 1
            goto L_0x0039
        L_0x0018:
            char r5 = (char) r5
            if (r5 != r1) goto L_0x001e
            r0 = 1
        L_0x001c:
            r6 = 1
            goto L_0x0028
        L_0x001e:
            r6 = 10
            if (r5 != r6) goto L_0x0025
            r0 = 0
        L_0x0023:
            r6 = 0
            goto L_0x0028
        L_0x0025:
            if (r0 == 0) goto L_0x001c
            goto L_0x0023
        L_0x0028:
            if (r6 != 0) goto L_0x002d
            r10 = 1
            r5 = 1
            goto L_0x003a
        L_0x002d:
            if (r4 < r10) goto L_0x0030
            goto L_0x0016
        L_0x0030:
            r8[r4] = r5
            int r4 = r4 + 1
            goto L_0x000a
        L_0x0035:
            r10 = 0
            goto L_0x0039
        L_0x0037:
            r10 = 0
            r0 = 0
        L_0x0039:
            r5 = 0
        L_0x003a:
            if (r10 == 0) goto L_0x0044
            int r10 = r7.position()
            int r10 = r10 - r2
            r7.position(r10)
        L_0x0044:
            int r4 = r4 - r9
            r9 = -1
            if (r5 == 0) goto L_0x0049
            r3 = -1
        L_0x0049:
            long r3 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r4, r3)
            r5 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r5 = r5 & r3
            int r10 = (int) r5
            r5 = 32
            if (r10 != r9) goto L_0x0077
            long r5 = r3 >> r5
            int r10 = (int) r5
            if (r0 == 0) goto L_0x0063
            int r10 = r10 - r2
            long r7 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r10, r9)
            return r7
        L_0x0063:
            int r0 = r7.position()
            int r0 = r0 + r2
            r7.position(r0)
            if (r10 <= 0) goto L_0x008b
            int r10 = r10 - r2
            char r7 = r8[r10]
            if (r7 != r1) goto L_0x008b
            long r7 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r10, r9)
            return r7
        L_0x0077:
            if (r0 == 0) goto L_0x008b
            long r8 = r3 >> r5
            int r9 = (int) r8
            int r8 = r7.position()
            int r8 = r8 - r2
            r7.position(r8)
            int r9 = r9 - r2
            r7 = 2
            long r7 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r9, r7)
            return r7
        L_0x008b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.StringsKt.decodeASCIILine_buffer(java.nio.ByteBuffer, char[], int, int):long");
    }
}
