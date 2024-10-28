package io.ktor.http.cio.internals;

import io.ktor.http.HttpMethod;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0002\u001a\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a(\u0010\u0016\u001a\u00020\u0017*\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0010H\u0000\u001a \u0010\u001b\u001a\u00020\u0012*\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0012H\u0000\u001a\n\u0010\u001c\u001a\u00020\u001d*\u00020\u0010\u001a\f\u0010\u001e\u001a\u00020\u001d*\u00020\u0010H\u0002\u001a\f\u0010\u001f\u001a\u00020\u001d*\u00020\u0010H\u0000\u001a\r\u0010 \u001a\u00020\u0012*\u00020\u0012H\b\u001a\u001d\u0010!\u001a\u00020\u0014*\u00020\"2\u0006\u0010#\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010$\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"DefaultHttpMethods", "Lio/ktor/http/cio/internals/AsciiCharTree;", "Lio/ktor/http/HttpMethod;", "getDefaultHttpMethods", "()Lio/ktor/http/cio/internals/AsciiCharTree;", "HTAB", "", "HexLetterTable", "", "getHexLetterTable", "()[B", "HexTable", "", "hexNumberFormatException", "", "s", "", "idx", "", "numberFormatException", "", "cs", "equalsLowerCase", "", "start", "end", "other", "hashCodeLowerCase", "parseDecLong", "", "parseDecLongWithCheck", "parseHexLong", "toLowerCase", "writeIntHex", "Lio/ktor/utils/io/ByteWriteChannel;", "value", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Chars.kt */
public final class CharsKt {
    private static final AsciiCharTree<HttpMethod> DefaultHttpMethods = AsciiCharTree.Companion.build(HttpMethod.Companion.getDefaultMethods(), CharsKt$DefaultHttpMethods$1.INSTANCE, CharsKt$DefaultHttpMethods$2.INSTANCE);
    public static final char HTAB = '\t';
    private static final byte[] HexLetterTable;
    private static final long[] HexTable;

    private static final int toLowerCase(int i) {
        return (65 > i || i >= 91) ? i : i + 32;
    }

    public static /* synthetic */ int hashCodeLowerCase$default(CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = charSequence.length();
        }
        return hashCodeLowerCase(charSequence, i, i2);
    }

    public static final int hashCodeLowerCase(CharSequence charSequence, int i, int i2) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int i3 = 0;
        while (i < i2) {
            int charAt = charSequence.charAt(i);
            if (65 <= charAt && charAt < 91) {
                charAt += 32;
            }
            i3 = (i3 * 31) + charAt;
            i++;
        }
        return i3;
    }

    public static /* synthetic */ boolean equalsLowerCase$default(CharSequence charSequence, int i, int i2, CharSequence charSequence2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = charSequence.length();
        }
        return equalsLowerCase(charSequence, i, i2, charSequence2);
    }

    public static final boolean equalsLowerCase(CharSequence charSequence, int i, int i2, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(charSequence2, "other");
        if (i2 - i != charSequence2.length()) {
            return false;
        }
        for (int i3 = i; i3 < i2; i3++) {
            int charAt = charSequence.charAt(i3);
            if (65 <= charAt && charAt < 91) {
                charAt += 32;
            }
            int charAt2 = charSequence2.charAt(i3 - i);
            if (65 <= charAt2 && charAt2 < 91) {
                charAt2 += 32;
            }
            if (charAt != charAt2) {
                return false;
            }
        }
        return true;
    }

    public static final AsciiCharTree<HttpMethod> getDefaultHttpMethods() {
        return DefaultHttpMethods;
    }

    static {
        long j;
        Iterable intRange = new IntRange(0, 255);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator it = intRange.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            if (48 > nextInt || nextInt >= 58) {
                long j2 = (long) nextInt;
                long j3 = 97;
                if (j2 < 97 || j2 > 102) {
                    j3 = 65;
                    if (j2 < 65 || j2 > 70) {
                        j = -1;
                    }
                }
                j = (j2 - j3) + ((long) 10);
            } else {
                j = ((long) nextInt) - 48;
            }
            arrayList.add(Long.valueOf(j));
        }
        HexTable = CollectionsKt.toLongArray((List) arrayList);
        Iterable intRange2 = new IntRange(0, 15);
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange2, 10));
        Iterator it2 = intRange2.iterator();
        while (it2.hasNext()) {
            int nextInt2 = ((IntIterator) it2).nextInt();
            arrayList2.add(Byte.valueOf((byte) (nextInt2 < 10 ? nextInt2 + 48 : (char) (((char) (nextInt2 + 97)) - 10))));
        }
        HexLetterTable = CollectionsKt.toByteArray((List) arrayList2);
    }

    public static final byte[] getHexLetterTable() {
        return HexLetterTable;
    }

    public static final long parseHexLong(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        long[] jArr = HexTable;
        int length = charSequence.length();
        long j = 0;
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i) & CharCompanionObject.MAX_VALUE;
            long j2 = charAt < 255 ? jArr[charAt] : -1;
            if (j2 != -1) {
                j = (j << 4) | j2;
                i++;
            } else {
                hexNumberFormatException(charSequence, i);
                throw new KotlinNothingValueException();
            }
        }
        return j;
    }

    public static final long parseDecLong(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length();
        if (length > 19) {
            numberFormatException(charSequence);
        }
        if (length == 19) {
            return parseDecLongWithCheck(charSequence);
        }
        long j = 0;
        for (int i = 0; i < length; i++) {
            long charAt = ((long) charSequence.charAt(i)) - 48;
            if (charAt < 0 || charAt > 9) {
                numberFormatException(charSequence, i);
            }
            j = (j << 3) + (j << 1) + charAt;
        }
        return j;
    }

    private static final long parseDecLongWithCheck(CharSequence charSequence) {
        int length = charSequence.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            long charAt = ((long) charSequence.charAt(i)) - 48;
            if (charAt < 0 || charAt > 9) {
                numberFormatException(charSequence, i);
            }
            j = (j << 3) + (j << 1) + charAt;
            if (j < 0) {
                numberFormatException(charSequence);
            }
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object writeIntHex(io.ktor.utils.io.ByteWriteChannel r7, int r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.http.cio.internals.CharsKt$writeIntHex$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.http.cio.internals.CharsKt$writeIntHex$1 r0 = (io.ktor.http.cio.internals.CharsKt$writeIntHex$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.internals.CharsKt$writeIntHex$1 r0 = new io.ktor.http.cio.internals.CharsKt$writeIntHex$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 8
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r5) goto L_0x0034
            if (r2 != r4) goto L_0x002c
            goto L_0x0034
        L_0x002c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0034:
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r2 = r0.L$1
            byte[] r2 = (byte[]) r2
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r7
            r7 = r5
            goto L_0x006d
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r9)
            if (r8 <= 0) goto L_0x008d
            byte[] r2 = HexLetterTable
            r9 = 0
        L_0x004e:
            int r6 = r9 + 1
            if (r9 >= r3) goto L_0x006d
            int r9 = r8 >>> 28
            int r8 = r8 << 4
            if (r9 == 0) goto L_0x006b
            byte r9 = r2[r9]
            r0.L$0 = r7
            r0.L$1 = r2
            r0.I$0 = r8
            r0.I$1 = r6
            r0.label = r5
            java.lang.Object r9 = r7.writeByte(r9, r0)
            if (r9 != r1) goto L_0x006d
            return r1
        L_0x006b:
            r9 = r6
            goto L_0x004e
        L_0x006d:
            int r9 = r6 + 1
            if (r6 >= r3) goto L_0x008a
            int r5 = r8 >>> 28
            int r8 = r8 << 4
            byte r5 = r2[r5]
            r0.L$0 = r7
            r0.L$1 = r2
            r0.I$0 = r8
            r0.I$1 = r9
            r0.label = r4
            java.lang.Object r5 = r7.writeByte(r5, r0)
            if (r5 != r1) goto L_0x0088
            return r1
        L_0x0088:
            r6 = r9
            goto L_0x006d
        L_0x008a:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x008d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Does only work for positive numbers"
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            goto L_0x009a
        L_0x0099:
            throw r7
        L_0x009a:
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.internals.CharsKt.writeIntHex(io.ktor.utils.io.ByteWriteChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Void hexNumberFormatException(CharSequence charSequence, int i) {
        throw new NumberFormatException("Invalid HEX number: " + charSequence + ", wrong digit: " + charSequence.charAt(i));
    }

    private static final void numberFormatException(CharSequence charSequence, int i) {
        throw new NumberFormatException("Invalid number: " + charSequence + ", wrong digit: " + charSequence.charAt(i) + " at position " + i);
    }

    private static final void numberFormatException(CharSequence charSequence) {
        throw new NumberFormatException("Invalid number " + charSequence + ": too large for Long type");
    }
}
