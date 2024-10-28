package io.ktor.utils.io.charsets;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okio.Utf8;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0007H\u0000\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\u001a \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a\"\u0010\u001f\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001\u001a&\u0010#\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u0001\u001a$\u0010$\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a$\u0010%\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a$\u0010&\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a9\u0010&\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00150(H\b\u001a$\u0010*\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a9\u0010*\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00150(H\b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"HighSurrogateMagic", "", "MaxCodePoint", "MinHighSurrogate", "MinLowSurrogate", "MinSupplementary", "decodeUtf8Result", "", "numberOfChars", "requireBytes", "decodeUtf8ResultAcc", "preDecoded", "result", "highSurrogate", "cp", "indexOutOfBounds", "", "offset", "length", "arrayLength", "isBmpCodePoint", "", "isValidCodePoint", "codePoint", "lowSurrogate", "malformedCodePoint", "", "value", "unsupportedByteCount", "b", "", "decodeUTF", "Ljava/nio/ByteBuffer;", "out", "", "decodeUTF8Line", "decodeUTF8Line_array", "decodeUTF8Line_buffer", "decodeUTF8_array", "predicate", "Lkotlin/Function1;", "", "decodeUTF8_buffer", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: UTF.kt */
public final class UTFKt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    public static final long decodeUtf8Result(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    private static final int highSurrogate(int i) {
        return (i >>> 10) + 55232;
    }

    private static final boolean isBmpCodePoint(int i) {
        return (i >>> 16) == 0;
    }

    private static final boolean isValidCodePoint(int i) {
        return i <= MaxCodePoint;
    }

    private static final int lowSurrogate(int i) {
        return (i & 1023) + 56320;
    }

    public static final long decodeUtf8ResultAcc(int i, long j) {
        return decodeUtf8Result(i + ((int) (j >> 32)), (int) (j & 4294967295L));
    }

    public static final long decodeUTF(ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "out");
        int decodeASCII = StringsKt.decodeASCII(byteBuffer, cArr, i, i2);
        if (!byteBuffer.hasRemaining() || decodeASCII == i2) {
            return decodeUtf8Result(decodeASCII, 0);
        }
        if (byteBuffer.hasArray()) {
            return decodeUtf8ResultAcc(decodeASCII, decodeUTF8_array(byteBuffer, cArr, i + decodeASCII, i2 - decodeASCII));
        }
        return decodeUtf8ResultAcc(decodeASCII, decodeUTF8_buffer(byteBuffer, cArr, i + decodeASCII, i2 - decodeASCII));
    }

    public static /* synthetic */ long decodeUTF8Line$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeUTF8Line(byteBuffer, cArr, i, i2);
    }

    public static final long decodeUTF8Line(ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "out");
        if (byteBuffer.hasArray()) {
            return decodeUTF8Line_array(byteBuffer, cArr, i, i2);
        }
        return decodeUTF8Line_buffer(byteBuffer, cArr, i, i2);
    }

    private static final long decodeUTF8_array(ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        int i3;
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int remaining = byteBuffer.remaining() + arrayOffset;
        if (arrayOffset > remaining) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (remaining <= array.length) {
            int i4 = i + i2;
            if (i4 <= cArr.length) {
                int i5 = i;
                while (arrayOffset < remaining && i5 < i4) {
                    int i6 = arrayOffset + 1;
                    byte b = array[arrayOffset];
                    if (b >= 0) {
                        cArr[i5] = (char) b;
                        i5++;
                        arrayOffset = i6;
                    } else {
                        if ((b & 224) == 192) {
                            if (i6 >= remaining) {
                                byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                                return decodeUtf8Result(i5 - i, 2);
                            }
                            arrayOffset += 2;
                            byte b2 = array[i6];
                            i3 = i5 + 1;
                            cArr[i5] = (char) ((b2 & Utf8.REPLACEMENT_BYTE) | ((b & Ascii.US) << 6));
                        } else if ((b & 240) == 224) {
                            if (remaining - i6 < 2) {
                                byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                                return decodeUtf8Result(i5 - i, 3);
                            }
                            int i7 = arrayOffset + 2;
                            byte b3 = array[i6];
                            arrayOffset += 3;
                            byte b4 = array[i7];
                            byte b5 = b & Ascii.SI;
                            int i8 = b5 << Ascii.FF;
                            byte b6 = (b4 & Utf8.REPLACEMENT_BYTE) | ((b3 & Utf8.REPLACEMENT_BYTE) << 6) | i8;
                            if (b5 == 0 || isBmpCodePoint(b6)) {
                                i3 = i5 + 1;
                                cArr[i5] = (char) b6;
                            } else {
                                malformedCodePoint(b6);
                                throw new KotlinNothingValueException();
                            }
                        } else if ((b & 248) != 240) {
                            unsupportedByteCount(b);
                            throw new KotlinNothingValueException();
                        } else if (remaining - i6 < 3) {
                            byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                            return decodeUtf8Result(i5 - i, 4);
                        } else {
                            byte b7 = array[i6];
                            byte b8 = array[arrayOffset + 2];
                            int i9 = arrayOffset + 4;
                            byte b9 = array[arrayOffset + 3];
                            byte b10 = ((b7 & Utf8.REPLACEMENT_BYTE) << Ascii.FF) | ((b & 7) << Ascii.DC2) | ((b8 & Utf8.REPLACEMENT_BYTE) << 6) | (b9 & Utf8.REPLACEMENT_BYTE);
                            if (!isValidCodePoint(b10)) {
                                malformedCodePoint(b10);
                                throw new KotlinNothingValueException();
                            } else if (i4 - i5 >= 2) {
                                int highSurrogate = highSurrogate(b10);
                                int lowSurrogate = lowSurrogate(b10);
                                int i10 = i5 + 1;
                                cArr[i5] = (char) highSurrogate;
                                i5 += 2;
                                cArr[i10] = (char) lowSurrogate;
                                arrayOffset = i9;
                            } else {
                                byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                                return decodeUtf8Result(i5 - i, 0);
                            }
                        }
                        i5 = i3;
                    }
                }
                byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                return decodeUtf8Result(i5 - i, 0);
            }
            throw indexOutOfBounds(i, i2, cArr.length);
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private static final long decodeUTF8_buffer(ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        int i3 = i + i2;
        if (i3 <= cArr.length) {
            int i4 = i;
            while (byteBuffer.hasRemaining() && i4 < i3) {
                byte b = byteBuffer.get();
                if (b >= 0) {
                    cArr[i4] = (char) b;
                    i4++;
                } else if ((b & 224) == 192) {
                    if (byteBuffer.hasRemaining()) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i4 - i, 2);
                    }
                    cArr[i4] = (char) (((b & Ascii.US) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE));
                    i4++;
                } else if ((b & 240) == 224) {
                    if (byteBuffer.remaining() < 2) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i4 - i, 3);
                    }
                    byte b2 = byteBuffer.get();
                    byte b3 = byteBuffer.get();
                    byte b4 = b & Ascii.SI;
                    byte b5 = ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (b4 << Ascii.FF) | (b3 & Utf8.REPLACEMENT_BYTE);
                    if (b4 == 0 || isBmpCodePoint(b5)) {
                        cArr[i4] = (char) b5;
                        i4++;
                    } else {
                        malformedCodePoint(b5);
                        throw new KotlinNothingValueException();
                    }
                } else if ((b & 248) != 240) {
                    unsupportedByteCount(b);
                    throw new KotlinNothingValueException();
                } else if (byteBuffer.remaining() < 3) {
                    byteBuffer.position(byteBuffer.position() - 1);
                    return decodeUtf8Result(i4 - i, 4);
                } else {
                    byte b6 = ((b & 7) << Ascii.DC2) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << Ascii.FF) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE);
                    if (!isValidCodePoint(b6)) {
                        malformedCodePoint(b6);
                        throw new KotlinNothingValueException();
                    } else if (i3 - i4 >= 2) {
                        int highSurrogate = highSurrogate(b6);
                        int lowSurrogate = lowSurrogate(b6);
                        int i5 = i4 + 1;
                        cArr[i4] = (char) highSurrogate;
                        i4 += 2;
                        cArr[i5] = (char) lowSurrogate;
                    } else {
                        byteBuffer.position(byteBuffer.position() - 4);
                        return decodeUtf8Result(i4 - i, 0);
                    }
                }
            }
            return decodeUtf8Result(i4 - i, 0);
        }
        throw indexOutOfBounds(i, i2, cArr.length);
    }

    private static final long decodeUTF8_array(ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        ByteBuffer byteBuffer2 = byteBuffer;
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        Function1<? super Character, Boolean> function12 = function1;
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int remaining = byteBuffer.remaining() + arrayOffset;
        if (arrayOffset > remaining) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (remaining <= array.length) {
            int i5 = i3 + i4;
            if (i5 <= cArr2.length) {
                int i6 = i3;
                while (arrayOffset < remaining && i6 < i5) {
                    int i7 = arrayOffset + 1;
                    byte b = array[arrayOffset];
                    if (b >= 0) {
                        char c = (char) b;
                        if (!function12.invoke(Character.valueOf(c)).booleanValue()) {
                            byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                            return decodeUtf8Result(i6 - i3, -1);
                        }
                        cArr2[i6] = c;
                        i6++;
                        arrayOffset = i7;
                    } else if ((b & 224) == 192) {
                        if (i7 >= remaining) {
                            byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                            return decodeUtf8Result(i6 - i3, 2);
                        }
                        int i8 = arrayOffset + 2;
                        char c2 = (char) ((array[i7] & Utf8.REPLACEMENT_BYTE) | ((b & Ascii.US) << 6));
                        if (!function12.invoke(Character.valueOf(c2)).booleanValue()) {
                            byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                            return decodeUtf8Result(i6 - i3, -1);
                        }
                        cArr2[i6] = c2;
                        i6++;
                        arrayOffset = i8;
                    } else if ((b & 240) == 224) {
                        if (remaining - i7 < 2) {
                            byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                            return decodeUtf8Result(i6 - i3, 3);
                        }
                        byte b2 = array[i7];
                        int i9 = arrayOffset + 3;
                        byte b3 = array[arrayOffset + 2];
                        byte b4 = b & Ascii.SI;
                        int i10 = b4 << Ascii.FF;
                        byte b5 = (b3 & Utf8.REPLACEMENT_BYTE) | ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | i10;
                        if (b4 == 0 || isBmpCodePoint(b5)) {
                            char c3 = (char) b5;
                            if (!function12.invoke(Character.valueOf(c3)).booleanValue()) {
                                byteBuffer2.position((arrayOffset - 1) - byteBuffer.arrayOffset());
                                return decodeUtf8Result(i6 - i3, -1);
                            }
                            cArr2[i6] = c3;
                            i6++;
                            arrayOffset = i9;
                        } else {
                            malformedCodePoint(b5);
                            throw new KotlinNothingValueException();
                        }
                    } else if ((b & 248) != 240) {
                        unsupportedByteCount(b);
                        throw new KotlinNothingValueException();
                    } else if (remaining - i7 < 3) {
                        byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                        return decodeUtf8Result(i6 - i3, 4);
                    } else {
                        byte b6 = array[i7];
                        byte b7 = array[arrayOffset + 2];
                        int i11 = arrayOffset + 4;
                        byte b8 = array[arrayOffset + 3];
                        int i12 = (b & 7) << Ascii.DC2;
                        byte b9 = ((b7 & Utf8.REPLACEMENT_BYTE) << 6) | ((b6 & Utf8.REPLACEMENT_BYTE) << Ascii.FF) | i12 | (b8 & Utf8.REPLACEMENT_BYTE);
                        if (!isValidCodePoint(b9)) {
                            malformedCodePoint(b9);
                            throw new KotlinNothingValueException();
                        } else if (i5 - i6 >= 2) {
                            char highSurrogate = (char) highSurrogate(b9);
                            char lowSurrogate = (char) lowSurrogate(b9);
                            if (!function12.invoke(Character.valueOf(highSurrogate)).booleanValue() || !function12.invoke(Character.valueOf(lowSurrogate)).booleanValue()) {
                                byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                                return decodeUtf8Result(i6 - i3, -1);
                            }
                            int i13 = i6 + 1;
                            cArr2[i6] = highSurrogate;
                            i6 += 2;
                            cArr2[i13] = lowSurrogate;
                            arrayOffset = i11;
                        } else {
                            byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                            return decodeUtf8Result(i6 - i3, 0);
                        }
                    }
                }
                byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                return decodeUtf8Result(i6 - i3, 0);
            }
            throw indexOutOfBounds(i3, i4, cArr2.length);
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private static final long decodeUTF8_buffer(ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        int i3;
        int i4 = i + i2;
        if (i4 <= cArr.length) {
            int i5 = i;
            while (byteBuffer.hasRemaining() && i5 < i4) {
                byte b = byteBuffer.get();
                if (b >= 0) {
                    char c = (char) b;
                    if (!function1.invoke(Character.valueOf(c)).booleanValue()) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i5 - i, -1);
                    }
                    i3 = i5 + 1;
                    cArr[i5] = c;
                } else if ((b & 224) == 192) {
                    if (!byteBuffer.hasRemaining()) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i5 - i, 2);
                    }
                    char c2 = (char) (((b & Ascii.US) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE));
                    if (!function1.invoke(Character.valueOf(c2)).booleanValue()) {
                        byteBuffer.position(byteBuffer.position() - 2);
                        return decodeUtf8Result(i5 - i, -1);
                    }
                    i3 = i5 + 1;
                    cArr[i5] = c2;
                } else if ((b & 240) == 224) {
                    if (byteBuffer.remaining() < 2) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i5 - i, 3);
                    }
                    byte b2 = byteBuffer.get();
                    byte b3 = byteBuffer.get();
                    byte b4 = b & Ascii.SI;
                    byte b5 = ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (b4 << Ascii.FF) | (b3 & Utf8.REPLACEMENT_BYTE);
                    if (b4 == 0 || isBmpCodePoint(b5)) {
                        char c3 = (char) b5;
                        if (!function1.invoke(Character.valueOf(c3)).booleanValue()) {
                            byteBuffer.position(byteBuffer.position() - 3);
                            return decodeUtf8Result(i5 - i, -1);
                        }
                        i3 = i5 + 1;
                        cArr[i5] = c3;
                    } else {
                        malformedCodePoint(b5);
                        throw new KotlinNothingValueException();
                    }
                } else if ((b & 248) != 240) {
                    unsupportedByteCount(b);
                    throw new KotlinNothingValueException();
                } else if (byteBuffer.remaining() < 3) {
                    byteBuffer.position(byteBuffer.position() - 1);
                    return decodeUtf8Result(i5 - i, 4);
                } else {
                    byte b6 = ((b & 7) << Ascii.DC2) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << Ascii.FF) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE);
                    if (!isValidCodePoint(b6)) {
                        malformedCodePoint(b6);
                        throw new KotlinNothingValueException();
                    } else if (i4 - i5 >= 2) {
                        char highSurrogate = (char) highSurrogate(b6);
                        char lowSurrogate = (char) lowSurrogate(b6);
                        if (!function1.invoke(Character.valueOf(highSurrogate)).booleanValue() || !function1.invoke(Character.valueOf(lowSurrogate)).booleanValue()) {
                            byteBuffer.position(byteBuffer.position() - 4);
                            return decodeUtf8Result(i5 - i, -1);
                        }
                        int i6 = i5 + 1;
                        cArr[i5] = highSurrogate;
                        i5 += 2;
                        cArr[i6] = lowSurrogate;
                    } else {
                        byteBuffer.position(byteBuffer.position() - 4);
                        return decodeUtf8Result(i5 - i, 0);
                    }
                }
                i5 = i3;
            }
            return decodeUtf8Result(i5 - i, 0);
        }
        throw indexOutOfBounds(i, i2, cArr.length);
    }

    private static final Throwable indexOutOfBounds(int i, int i2, int i3) {
        return new IndexOutOfBoundsException(i + " (offset) + " + i2 + " (length) > " + i3 + " (array.length)");
    }

    private static final Void malformedCodePoint(int i) {
        throw new IllegalArgumentException("Malformed code-point " + Integer.toHexString(i) + " found");
    }

    private static final Void unsupportedByteCount(byte b) {
        StringBuilder sb = new StringBuilder("Unsupported byte code, first byte is 0x");
        String num = Integer.toString(b & 255, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        sb.append(StringsKt.padStart(num, 2, '0'));
        throw new IllegalStateException(sb.toString().toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        r9 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a4, code lost:
        r9 = r15;
        r0.position(r5 - r16.arrayOffset());
        r2 = decodeUtf8Result(r8 - r2, -1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long decodeUTF8Line_array(java.nio.ByteBuffer r16, char[] r17, int r18, int r19) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            byte[] r4 = r16.array()
            int r5 = r16.arrayOffset()
            int r6 = r16.position()
            int r5 = r5 + r6
            int r6 = r16.remaining()
            int r6 = r6 + r5
            java.lang.String r7 = "Failed requirement."
            if (r5 > r6) goto L_0x0238
            int r8 = r4.length
            if (r6 > r8) goto L_0x022e
            int r7 = r2 + r3
            int r8 = r1.length
            if (r7 > r8) goto L_0x0228
            r8 = r2
            r9 = 0
        L_0x0028:
            r10 = 13
            r11 = 2
            r12 = -1
            if (r5 >= r6) goto L_0x01d6
            if (r8 >= r7) goto L_0x01d6
            int r14 = r5 + 1
            byte r15 = r4[r5]
            r13 = 10
            if (r15 < 0) goto L_0x0064
            char r15 = (char) r15
            if (r15 != r10) goto L_0x003e
            r9 = 1
            r13 = 1
            goto L_0x004a
        L_0x003e:
            if (r15 != r13) goto L_0x0043
            r9 = 0
            r13 = 0
            goto L_0x004a
        L_0x0043:
            if (r9 == 0) goto L_0x0048
            r13 = r9
            r9 = 0
            goto L_0x004a
        L_0x0048:
            r13 = r9
            r9 = 1
        L_0x004a:
            if (r9 != 0) goto L_0x005c
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            long r2 = decodeUtf8Result(r8, r12)
        L_0x0059:
            r9 = r13
            goto L_0x01e4
        L_0x005c:
            int r5 = r8 + 1
            r1[r8] = r15
            r8 = r5
            r9 = r13
            r5 = r14
            goto L_0x0028
        L_0x0064:
            r3 = r15 & 224(0xe0, float:3.14E-43)
            r12 = 192(0xc0, float:2.69E-43)
            if (r3 != r12) goto L_0x00b3
            if (r14 < r6) goto L_0x007b
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            long r2 = decodeUtf8Result(r8, r11)
            goto L_0x01e4
        L_0x007b:
            int r3 = r5 + 2
            byte r12 = r4[r14]
            r14 = r15 & 31
            int r14 = r14 << 6
            r12 = r12 & 63
            r12 = r12 | r14
            char r12 = (char) r12
            if (r12 != r10) goto L_0x008c
            r9 = 1
            r13 = 1
            goto L_0x0098
        L_0x008c:
            if (r12 != r13) goto L_0x0091
            r9 = 0
            r13 = 0
            goto L_0x0098
        L_0x0091:
            if (r9 == 0) goto L_0x0096
            r13 = r9
            r9 = 0
            goto L_0x0098
        L_0x0096:
            r13 = r9
            r9 = 1
        L_0x0098:
            if (r9 != 0) goto L_0x00aa
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            r2 = -1
            long r3 = decodeUtf8Result(r8, r2)
            r2 = r3
            goto L_0x0059
        L_0x00aa:
            int r5 = r8 + 1
            r1[r8] = r12
            r8 = r5
            r9 = r13
            r5 = r3
            goto L_0x0028
        L_0x00b3:
            r3 = r15 & 240(0xf0, float:3.36E-43)
            r12 = 224(0xe0, float:3.14E-43)
            r13 = 3
            if (r3 != r12) goto L_0x0124
            int r3 = r6 - r14
            if (r3 >= r11) goto L_0x00cd
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            long r2 = decodeUtf8Result(r8, r13)
            goto L_0x01e4
        L_0x00cd:
            int r3 = r5 + 2
            byte r12 = r4[r14]
            int r13 = r5 + 3
            byte r3 = r4[r3]
            r14 = r15 & 15
            int r15 = r14 << 12
            r12 = r12 & 63
            int r12 = r12 << 6
            r12 = r12 | r15
            r3 = r3 & 63
            r3 = r3 | r12
            if (r14 == 0) goto L_0x00f3
            boolean r12 = isBmpCodePoint(r3)
            if (r12 == 0) goto L_0x00ea
            goto L_0x00f3
        L_0x00ea:
            malformedCodePoint(r3)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x00f3:
            char r3 = (char) r3
            if (r3 != r10) goto L_0x00f9
            r9 = 1
            r12 = 1
            goto L_0x0107
        L_0x00f9:
            r12 = 10
            if (r3 != r12) goto L_0x0100
            r9 = 0
            r12 = 0
            goto L_0x0107
        L_0x0100:
            if (r9 == 0) goto L_0x0105
            r12 = r9
            r9 = 0
            goto L_0x0107
        L_0x0105:
            r12 = r9
            r9 = 1
        L_0x0107:
            if (r9 != 0) goto L_0x011b
            r9 = -1
            int r5 = r5 + r9
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            long r2 = decodeUtf8Result(r8, r9)
            r9 = r12
            goto L_0x01e4
        L_0x011b:
            int r5 = r8 + 1
            r1[r8] = r3
            r8 = r5
            r9 = r12
            r5 = r13
            goto L_0x0028
        L_0x0124:
            r3 = r15 & 248(0xf8, float:3.48E-43)
            r12 = 240(0xf0, float:3.36E-43)
            if (r3 != r12) goto L_0x01cd
            int r3 = r6 - r14
            if (r3 >= r13) goto L_0x013e
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            r2 = 4
            long r2 = decodeUtf8Result(r8, r2)
            goto L_0x01e4
        L_0x013e:
            int r3 = r5 + 2
            byte r12 = r4[r14]
            int r13 = r5 + 3
            byte r3 = r4[r3]
            int r14 = r5 + 4
            byte r13 = r4[r13]
            r15 = r15 & 7
            int r15 = r15 << 18
            r12 = r12 & 63
            int r12 = r12 << 12
            r12 = r12 | r15
            r3 = r3 & 63
            int r3 = r3 << 6
            r3 = r3 | r12
            r12 = r13 & 63
            r3 = r3 | r12
            boolean r12 = isValidCodePoint(r3)
            if (r12 == 0) goto L_0x01c4
            int r12 = r7 - r8
            if (r12 < r11) goto L_0x01b5
            int r12 = highSurrogate(r3)
            char r12 = (char) r12
            int r3 = lowSurrogate(r3)
            char r3 = (char) r3
            if (r12 != r10) goto L_0x0176
            r9 = 1
            r13 = 10
            r15 = 1
            goto L_0x0184
        L_0x0176:
            r13 = 10
            if (r12 != r13) goto L_0x017d
            r9 = 0
            r15 = 0
            goto L_0x0184
        L_0x017d:
            if (r9 == 0) goto L_0x0182
            r15 = r9
            r9 = 0
            goto L_0x0184
        L_0x0182:
            r15 = r9
            r9 = 1
        L_0x0184:
            if (r9 == 0) goto L_0x01a4
            if (r3 != r10) goto L_0x018b
            r9 = 1
            r15 = 1
            goto L_0x0195
        L_0x018b:
            if (r3 != r13) goto L_0x0190
            r9 = 0
            r15 = 0
            goto L_0x0195
        L_0x0190:
            if (r15 == 0) goto L_0x0194
            r9 = 0
            goto L_0x0195
        L_0x0194:
            r9 = 1
        L_0x0195:
            if (r9 != 0) goto L_0x0198
            goto L_0x01a4
        L_0x0198:
            int r5 = r8 + 1
            r1[r8] = r12
            int r8 = r8 + 2
            r1[r5] = r3
            r5 = r14
            r9 = r15
            goto L_0x0028
        L_0x01a4:
            r9 = r15
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            r2 = -1
            long r3 = decodeUtf8Result(r8, r2)
            r2 = r3
            goto L_0x01e4
        L_0x01b5:
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            r2 = 0
            long r2 = decodeUtf8Result(r8, r2)
            goto L_0x01e4
        L_0x01c4:
            malformedCodePoint(r3)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x01cd:
            unsupportedByteCount(r15)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x01d6:
            int r3 = r16.arrayOffset()
            int r5 = r5 - r3
            r0.position(r5)
            int r8 = r8 - r2
            r2 = 0
            long r2 = decodeUtf8Result(r8, r2)
        L_0x01e4:
            r4 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r4 = r4 & r2
            int r5 = (int) r4
            r4 = 32
            r6 = -1
            if (r5 != r6) goto L_0x0211
            long r4 = r2 >> r4
            int r5 = (int) r4
            if (r9 == 0) goto L_0x01fc
            r4 = 1
            int r5 = r5 - r4
            long r0 = decodeUtf8Result(r5, r6)
            return r0
        L_0x01fc:
            r4 = 1
            int r7 = r16.position()
            int r7 = r7 + r4
            r0.position(r7)
            if (r5 <= 0) goto L_0x0227
            int r5 = r5 - r4
            char r0 = r1[r5]
            if (r0 != r10) goto L_0x0227
            long r0 = decodeUtf8Result(r5, r6)
            return r0
        L_0x0211:
            if (r5 != 0) goto L_0x0227
            if (r9 == 0) goto L_0x0227
            long r1 = r2 >> r4
            int r2 = (int) r1
            int r1 = r16.position()
            r3 = 1
            int r1 = r1 - r3
            r0.position(r1)
            int r2 = r2 - r3
            long r0 = decodeUtf8Result(r2, r11)
            return r0
        L_0x0227:
            return r2
        L_0x0228:
            int r0 = r1.length
            java.lang.Throwable r0 = indexOutOfBounds(r2, r3, r0)
            throw r0
        L_0x022e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r7.toString()
            r0.<init>(r1)
            throw r0
        L_0x0238:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r7.toString()
            r0.<init>(r1)
            goto L_0x0243
        L_0x0242:
            throw r0
        L_0x0243:
            goto L_0x0242
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.UTFKt.decodeUTF8Line_array(java.nio.ByteBuffer, char[], int, int):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (r6 != false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007b, code lost:
        if (r6 != false) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e2, code lost:
        if (r6 != false) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0153, code lost:
        if (r6 != false) goto L_0x0151;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0162, code lost:
        if (r6 != false) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0172, code lost:
        r0.position(r17.position() - 4);
        r2 = decodeUtf8Result(r5 - r2, -1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0035 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0172 A[EDGE_INSN: B:122:0x0172->B:85:0x0172 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x00e7 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0172 A[EDGE_INSN: B:124:0x0172->B:85:0x0172 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0080 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0168  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long decodeUTF8Line_buffer(java.nio.ByteBuffer r17, char[] r18, int r19, int r20) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            int r4 = r2 + r3
            int r5 = r1.length
            if (r4 > r5) goto L_0x01e5
            r3 = 0
            r5 = r2
            r6 = 0
        L_0x0010:
            boolean r7 = r17.hasRemaining()
            r8 = 2
            r9 = 13
            r10 = -1
            r11 = 1
            if (r7 == 0) goto L_0x01a0
            if (r5 >= r4) goto L_0x01a0
            byte r7 = r17.get()
            r12 = 10
            if (r7 < 0) goto L_0x004a
            char r7 = (char) r7
            if (r7 != r9) goto L_0x002b
            r6 = 1
        L_0x0029:
            r12 = 1
            goto L_0x0033
        L_0x002b:
            if (r7 != r12) goto L_0x0030
            r6 = 0
        L_0x002e:
            r12 = 0
            goto L_0x0033
        L_0x0030:
            if (r6 == 0) goto L_0x0029
            goto L_0x002e
        L_0x0033:
            if (r12 != 0) goto L_0x0044
            int r3 = r17.position()
            int r3 = r3 - r11
            r0.position(r3)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r10)
            goto L_0x01a5
        L_0x0044:
            int r8 = r5 + 1
            r1[r5] = r7
        L_0x0048:
            r5 = r8
            goto L_0x0010
        L_0x004a:
            r13 = r7 & 224(0xe0, float:3.14E-43)
            r14 = 192(0xc0, float:2.69E-43)
            if (r13 != r14) goto L_0x0094
            boolean r13 = r17.hasRemaining()
            if (r13 != 0) goto L_0x0065
            int r3 = r17.position()
            int r3 = r3 - r11
            r0.position(r3)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r8)
            goto L_0x01a5
        L_0x0065:
            byte r13 = r17.get()
            r7 = r7 & 31
            int r7 = r7 << 6
            r13 = r13 & 63
            r7 = r7 | r13
            char r7 = (char) r7
            if (r7 != r9) goto L_0x0076
            r6 = 1
        L_0x0074:
            r12 = 1
            goto L_0x007e
        L_0x0076:
            if (r7 != r12) goto L_0x007b
            r6 = 0
        L_0x0079:
            r12 = 0
            goto L_0x007e
        L_0x007b:
            if (r6 == 0) goto L_0x0074
            goto L_0x0079
        L_0x007e:
            if (r12 != 0) goto L_0x008f
            int r3 = r17.position()
            int r3 = r3 - r8
            r0.position(r3)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r10)
            goto L_0x01a5
        L_0x008f:
            int r8 = r5 + 1
            r1[r5] = r7
            goto L_0x0048
        L_0x0094:
            r13 = r7 & 240(0xf0, float:3.36E-43)
            r14 = 224(0xe0, float:3.14E-43)
            r15 = 3
            if (r13 != r14) goto L_0x00fc
            int r13 = r17.remaining()
            if (r13 >= r8) goto L_0x00b0
            int r3 = r17.position()
            int r3 = r3 - r11
            r0.position(r3)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r15)
            goto L_0x01a5
        L_0x00b0:
            byte r13 = r17.get()
            byte r14 = r17.get()
            r7 = r7 & 15
            int r16 = r7 << 12
            r13 = r13 & 63
            int r13 = r13 << 6
            r13 = r16 | r13
            r14 = r14 & 63
            r13 = r13 | r14
            if (r7 == 0) goto L_0x00d7
            boolean r7 = isBmpCodePoint(r13)
            if (r7 == 0) goto L_0x00ce
            goto L_0x00d7
        L_0x00ce:
            malformedCodePoint(r13)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x00d7:
            char r7 = (char) r13
            if (r7 != r9) goto L_0x00dd
            r6 = 1
        L_0x00db:
            r12 = 1
            goto L_0x00e5
        L_0x00dd:
            if (r7 != r12) goto L_0x00e2
            r6 = 0
        L_0x00e0:
            r12 = 0
            goto L_0x00e5
        L_0x00e2:
            if (r6 == 0) goto L_0x00db
            goto L_0x00e0
        L_0x00e5:
            if (r12 != 0) goto L_0x00f6
            int r3 = r17.position()
            int r3 = r3 - r15
            r0.position(r3)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r10)
            goto L_0x01a5
        L_0x00f6:
            int r8 = r5 + 1
            r1[r5] = r7
            goto L_0x0048
        L_0x00fc:
            r13 = r7 & 248(0xf8, float:3.48E-43)
            r14 = 240(0xf0, float:3.36E-43)
            if (r13 != r14) goto L_0x0197
            int r13 = r17.remaining()
            r14 = 4
            if (r13 >= r15) goto L_0x0118
            int r3 = r17.position()
            int r3 = r3 - r11
            r0.position(r3)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r14)
            goto L_0x01a5
        L_0x0118:
            byte r13 = r17.get()
            byte r15 = r17.get()
            byte r16 = r17.get()
            r7 = r7 & 7
            int r7 = r7 << 18
            r13 = r13 & 63
            int r13 = r13 << 12
            r7 = r7 | r13
            r13 = r15 & 63
            int r13 = r13 << 6
            r7 = r7 | r13
            r13 = r16 & 63
            r7 = r7 | r13
            boolean r13 = isValidCodePoint(r7)
            if (r13 == 0) goto L_0x018e
            int r13 = r4 - r5
            if (r13 < r8) goto L_0x0180
            int r13 = highSurrogate(r7)
            char r13 = (char) r13
            int r7 = lowSurrogate(r7)
            char r7 = (char) r7
            if (r13 != r9) goto L_0x014e
            r6 = 1
        L_0x014c:
            r15 = 1
            goto L_0x0156
        L_0x014e:
            if (r13 != r12) goto L_0x0153
            r6 = 0
        L_0x0151:
            r15 = 0
            goto L_0x0156
        L_0x0153:
            if (r6 == 0) goto L_0x014c
            goto L_0x0151
        L_0x0156:
            if (r15 == 0) goto L_0x0172
            if (r7 != r9) goto L_0x015d
            r6 = 1
        L_0x015b:
            r12 = 1
            goto L_0x0165
        L_0x015d:
            if (r7 != r12) goto L_0x0162
            r6 = 0
        L_0x0160:
            r12 = 0
            goto L_0x0165
        L_0x0162:
            if (r6 == 0) goto L_0x015b
            goto L_0x0160
        L_0x0165:
            if (r12 != 0) goto L_0x0168
            goto L_0x0172
        L_0x0168:
            int r8 = r5 + 1
            r1[r5] = r13
            int r5 = r5 + 2
            r1[r8] = r7
            goto L_0x0010
        L_0x0172:
            int r3 = r17.position()
            int r3 = r3 - r14
            r0.position(r3)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r10)
            goto L_0x01a5
        L_0x0180:
            int r4 = r17.position()
            int r4 = r4 - r14
            r0.position(r4)
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r3)
            goto L_0x01a5
        L_0x018e:
            malformedCodePoint(r7)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x0197:
            unsupportedByteCount(r7)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x01a0:
            int r5 = r5 - r2
            long r2 = decodeUtf8Result(r5, r3)
        L_0x01a5:
            r4 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r4 = r4 & r2
            int r5 = (int) r4
            r4 = 32
            if (r5 != r10) goto L_0x01cf
            long r4 = r2 >> r4
            int r5 = (int) r4
            if (r6 == 0) goto L_0x01bb
            int r5 = r5 - r11
            long r0 = decodeUtf8Result(r5, r10)
            return r0
        L_0x01bb:
            int r4 = r17.position()
            int r4 = r4 + r11
            r0.position(r4)
            if (r5 <= 0) goto L_0x01e4
            int r5 = r5 - r11
            char r0 = r1[r5]
            if (r0 != r9) goto L_0x01e4
            long r0 = decodeUtf8Result(r5, r10)
            return r0
        L_0x01cf:
            if (r5 != 0) goto L_0x01e4
            if (r6 == 0) goto L_0x01e4
            long r1 = r2 >> r4
            int r2 = (int) r1
            int r1 = r17.position()
            int r1 = r1 - r11
            r0.position(r1)
            int r2 = r2 - r11
            long r0 = decodeUtf8Result(r2, r8)
            return r0
        L_0x01e4:
            return r2
        L_0x01e5:
            int r0 = r1.length
            java.lang.Throwable r0 = indexOutOfBounds(r2, r3, r0)
            goto L_0x01ec
        L_0x01eb:
            throw r0
        L_0x01ec:
            goto L_0x01eb
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.UTFKt.decodeUTF8Line_buffer(java.nio.ByteBuffer, char[], int, int):long");
    }
}
