package okio.internal;

import com.google.common.base.Ascii;
import io.ktor.util.date.GMTDateParser;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Base64;
import okio.ByteString;
import okio.Platform;
import okio.Util;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0017\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\tH\u0000\u001a\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\f\u0010\u0012\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\u0014\u0010\u0013\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0000\u001a\u000e\u0010\u0015\u001a\u0004\u0018\u00010\u0001*\u00020\u0011H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0011H\u0000\u001a\f\u0010\u0017\u001a\u00020\u0001*\u00020\u0011H\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\tH\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u001b\u001a\u00020\u0019*\u00020\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u001cH\u0000\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0007H\u0000\u001a\f\u0010 \u001a\u00020\u0007*\u00020\u0001H\u0000\u001a\f\u0010!\u001a\u00020\u0007*\u00020\u0001H\u0000\u001a\f\u0010\"\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\u001c\u0010#\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0007H\u0000\u001a\f\u0010%\u001a\u00020\t*\u00020\u0001H\u0000\u001a\u001c\u0010&\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0007H\u0000\u001a,\u0010'\u001a\u00020\u0019*\u00020\u00012\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0000\u001a,\u0010'\u001a\u00020\u0019*\u00020\u00012\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0000\u001a\u0014\u0010+\u001a\u00020\u0019*\u00020\u00012\u0006\u0010,\u001a\u00020\tH\u0000\u001a\u0014\u0010+\u001a\u00020\u0019*\u00020\u00012\u0006\u0010,\u001a\u00020\u0001H\u0000\u001a\u001c\u0010-\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0007H\u0000\u001a\f\u00100\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u00101\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u00102\u001a\u00020\t*\u00020\u0001H\u0000\u001a\f\u00103\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\f\u00104\u001a\u00020\u0011*\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"COMMON_EMPTY", "Lokio/ByteString;", "getCOMMON_EMPTY", "()Lokio/ByteString;", "HEX_DIGITS", "", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonDecodeBase64", "commonDecodeHex", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "offset", "otherOffset", "byteCount", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToString", "commonUtf8", "jvm"}, k = 2, mv = {1, 1, 11})
/* compiled from: ByteString.kt */
public final class ByteStringKt {
    private static final ByteString COMMON_EMPTY = ByteString.Companion.of(new byte[0]);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f'};

    public static final String commonUtf8(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        String utf8$jvm = byteString.getUtf8$jvm();
        if (utf8$jvm != null) {
            return utf8$jvm;
        }
        String utf8String = Platform.toUtf8String(byteString.internalArray$jvm());
        byteString.setUtf8$jvm(utf8String);
        return utf8String;
    }

    public static final String commonBase64(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        return Base64.encodeBase64$default(byteString.getData$jvm(), (byte[]) null, 1, (Object) null);
    }

    public static final String commonBase64Url(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        return Base64.encodeBase64(byteString.getData$jvm(), Base64.getBASE64_URL_SAFE());
    }

    public static final String commonHex(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        char[] cArr = new char[(byteString.getData$jvm().length * 2)];
        int i = 0;
        for (byte b : byteString.getData$jvm()) {
            int i2 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(b >> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & Ascii.SI];
        }
        return new String(cArr);
    }

    public static final ByteString commonToAsciiLowercase(ByteString byteString) {
        byte b;
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        int i = 0;
        while (i < byteString.getData$jvm().length) {
            byte b2 = byteString.getData$jvm()[i];
            byte b3 = (byte) 65;
            if (b2 < b3 || b2 > (b = (byte) 90)) {
                i++;
            } else {
                byte[] data$jvm = byteString.getData$jvm();
                byte[] copyOf = Arrays.copyOf(data$jvm, data$jvm.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i] = (byte) (b2 + 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b4 = copyOf[i2];
                    if (b4 >= b3 && b4 <= b) {
                        copyOf[i2] = (byte) (b4 + 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return byteString;
    }

    public static final ByteString commonToAsciiUppercase(ByteString byteString) {
        byte b;
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        int i = 0;
        while (i < byteString.getData$jvm().length) {
            byte b2 = byteString.getData$jvm()[i];
            byte b3 = (byte) 97;
            if (b2 < b3 || b2 > (b = (byte) 122)) {
                i++;
            } else {
                byte[] data$jvm = byteString.getData$jvm();
                byte[] copyOf = Arrays.copyOf(data$jvm, data$jvm.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i] = (byte) (b2 - 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b4 = copyOf[i2];
                    if (b4 >= b3 && b4 <= b) {
                        copyOf[i2] = (byte) (b4 - 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return byteString;
    }

    public static final ByteString commonSubstring(ByteString byteString, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        } else if (i2 <= byteString.getData$jvm().length) {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex".toString());
            } else if (i == 0 && i2 == byteString.getData$jvm().length) {
                return byteString;
            } else {
                byte[] bArr = new byte[i3];
                Platform.arraycopy(byteString.getData$jvm(), i, bArr, 0, i3);
                return new ByteString(bArr);
            }
        } else {
            throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$jvm().length + ')').toString());
        }
    }

    public static final byte commonGetByte(ByteString byteString, int i) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        return byteString.getData$jvm()[i];
    }

    public static final int commonGetSize(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        return byteString.getData$jvm().length;
    }

    public static final byte[] commonToByteArray(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        byte[] data$jvm = byteString.getData$jvm();
        byte[] copyOf = Arrays.copyOf(data$jvm, data$jvm.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    public static final byte[] commonInternalArray(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        return byteString.getData$jvm();
    }

    public static final boolean commonRangeEquals(ByteString byteString, int i, ByteString byteString2, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(byteString2, "other");
        return byteString2.rangeEquals(i2, byteString.getData$jvm(), i, i3);
    }

    public static final boolean commonRangeEquals(ByteString byteString, int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(bArr, "other");
        return i >= 0 && i <= byteString.getData$jvm().length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && Util.arrayRangeEquals(byteString.getData$jvm(), i, bArr, i2, i3);
    }

    public static final boolean commonStartsWith(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(byteString2, "prefix");
        return byteString.rangeEquals(0, byteString2, 0, byteString2.size());
    }

    public static final boolean commonStartsWith(ByteString byteString, byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(bArr, "prefix");
        return byteString.rangeEquals(0, bArr, 0, bArr.length);
    }

    public static final boolean commonEndsWith(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(byteString2, "suffix");
        return byteString.rangeEquals(byteString.size() - byteString2.size(), byteString2, 0, byteString2.size());
    }

    public static final boolean commonEndsWith(ByteString byteString, byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(bArr, "suffix");
        return byteString.rangeEquals(byteString.size() - bArr.length, bArr, 0, bArr.length);
    }

    public static final int commonIndexOf(ByteString byteString, byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(bArr, "other");
        int length = byteString.getData$jvm().length - bArr.length;
        int max = Math.max(i, 0);
        if (max > length) {
            return -1;
        }
        while (!Util.arrayRangeEquals(byteString.getData$jvm(), max, bArr, 0, bArr.length)) {
            if (max == length) {
                return -1;
            }
            max++;
        }
        return max;
    }

    public static final int commonLastIndexOf(ByteString byteString, byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(bArr, "other");
        for (int min = Math.min(i, byteString.getData$jvm().length - bArr.length); min >= 0; min--) {
            if (Util.arrayRangeEquals(byteString.getData$jvm(), min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(ByteString byteString, Object obj) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        if (obj == byteString) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString2 = (ByteString) obj;
            return byteString2.size() == byteString.getData$jvm().length && byteString2.rangeEquals(0, byteString.getData$jvm(), 0, byteString.getData$jvm().length);
        }
    }

    public static final int commonHashCode(ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        int hashCode$jvm = byteString.getHashCode$jvm();
        if (hashCode$jvm != 0) {
            return hashCode$jvm;
        }
        byteString.setHashCode$jvm(Arrays.hashCode(byteString.getData$jvm()));
        return byteString.getHashCode$jvm();
    }

    public static final int commonCompareTo(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkParameterIsNotNull(byteString, "$receiver");
        Intrinsics.checkParameterIsNotNull(byteString2, "other");
        int size = byteString.size();
        int size2 = byteString2.size();
        int min = Math.min(size, size2);
        int i = 0;
        while (i < min) {
            byte b = byteString.getByte(i) & 255;
            byte b2 = byteString2.getByte(i) & 255;
            if (b == b2) {
                i++;
            } else if (b < b2) {
                return -1;
            } else {
                return 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        if (size < size2) {
            return -1;
        }
        return 1;
    }

    public static final ByteString getCOMMON_EMPTY() {
        return COMMON_EMPTY;
    }

    public static final ByteString commonOf(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new ByteString(copyOf);
    }

    public static final ByteString commonEncodeUtf8(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        ByteString byteString = new ByteString(Platform.asUtf8ToByteArray(str));
        byteString.setUtf8$jvm(str);
        return byteString;
    }

    public static final ByteString commonDecodeBase64(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(str);
        if (decodeBase64ToArray != null) {
            return new ByteString(decodeBase64ToArray);
        }
        return null;
    }

    public static final ByteString commonDecodeHex(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) ((decodeHexDigit(str.charAt(i2)) << 4) + decodeHexDigit(str.charAt(i2 + 1)));
            }
            return new ByteString(bArr);
        }
        throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
    }

    private static final int decodeHexDigit(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        if ('a' <= c && 'f' >= c) {
            return c - 'W';
        }
        if ('A' <= c && 'F' >= c) {
            return c - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static final String commonToString(ByteString byteString) {
        ByteString byteString2 = byteString;
        Intrinsics.checkParameterIsNotNull(byteString2, "$receiver");
        if (byteString.getData$jvm().length == 0) {
            return "[size=0]";
        }
        int codePointIndexToCharIndex = codePointIndexToCharIndex(byteString.getData$jvm(), 64);
        if (codePointIndexToCharIndex != -1) {
            String utf8 = byteString.utf8();
            if (utf8 != null) {
                String substring = utf8.substring(0, codePointIndexToCharIndex);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
                if (codePointIndexToCharIndex < utf8.length()) {
                    return "[size=" + byteString.getData$jvm().length + " text=" + replace$default + "…]";
                }
                return "[text=" + replace$default + AbstractJsonLexerKt.END_LIST;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        } else if (byteString.getData$jvm().length <= 64) {
            return "[hex=" + byteString.hex() + AbstractJsonLexerKt.END_LIST;
        } else {
            return "[size=" + byteString.getData$jvm().length + " hex=" + commonSubstring(byteString2, 0, 64).hex() + "…]";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005c, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int codePointIndexToCharIndex(byte[] r18, int r19) {
        /*
            r0 = r18
            r1 = r19
            int r2 = r0.length
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0008:
            if (r3 >= r2) goto L_0x019b
            byte r6 = r0[r3]
            r7 = 127(0x7f, float:1.78E-43)
            r8 = 159(0x9f, float:2.23E-43)
            r9 = 31
            r10 = 13
            r11 = 65533(0xfffd, float:9.1831E-41)
            r12 = 10
            r13 = 65536(0x10000, float:9.18355E-41)
            r16 = -1
            if (r6 < 0) goto L_0x0064
            int r17 = r5 + 1
            if (r5 != r1) goto L_0x0024
            return r4
        L_0x0024:
            if (r6 == r12) goto L_0x0032
            if (r6 == r10) goto L_0x0032
            if (r6 < 0) goto L_0x002c
            if (r9 >= r6) goto L_0x0034
        L_0x002c:
            if (r7 <= r6) goto L_0x002f
            goto L_0x0032
        L_0x002f:
            if (r8 < r6) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            if (r6 != r11) goto L_0x0035
        L_0x0034:
            return r16
        L_0x0035:
            if (r6 >= r13) goto L_0x0039
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 2
        L_0x003a:
            int r4 = r4 + r5
            int r3 = r3 + 1
        L_0x003d:
            r5 = r17
            if (r3 >= r2) goto L_0x0008
            byte r6 = r0[r3]
            if (r6 < 0) goto L_0x0008
            int r3 = r3 + 1
            int r17 = r5 + 1
            if (r5 != r1) goto L_0x004c
            return r4
        L_0x004c:
            if (r6 == r12) goto L_0x005a
            if (r6 == r10) goto L_0x005a
            if (r6 < 0) goto L_0x0054
            if (r9 >= r6) goto L_0x005c
        L_0x0054:
            if (r7 <= r6) goto L_0x0057
            goto L_0x005a
        L_0x0057:
            if (r8 < r6) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            if (r6 != r11) goto L_0x005d
        L_0x005c:
            return r16
        L_0x005d:
            if (r6 >= r13) goto L_0x0061
            r5 = 1
            goto L_0x0062
        L_0x0061:
            r5 = 2
        L_0x0062:
            int r4 = r4 + r5
            goto L_0x003d
        L_0x0064:
            int r14 = r6 >> 5
            r15 = -2
            r13 = 128(0x80, float:1.794E-43)
            if (r14 != r15) goto L_0x00ab
            int r14 = r3 + 1
            if (r2 > r14) goto L_0x0073
            if (r5 != r1) goto L_0x0072
            return r4
        L_0x0072:
            return r16
        L_0x0073:
            byte r14 = r0[r14]
            r15 = r14 & 192(0xc0, float:2.69E-43)
            if (r15 != r13) goto L_0x00a7
            r14 = r14 ^ 3968(0xf80, float:5.56E-42)
            int r6 = r6 << 6
            r6 = r6 ^ r14
            if (r6 >= r13) goto L_0x0084
            if (r5 != r1) goto L_0x0083
            return r4
        L_0x0083:
            return r16
        L_0x0084:
            int r13 = r5 + 1
            if (r5 != r1) goto L_0x0089
            return r4
        L_0x0089:
            if (r6 == r12) goto L_0x0097
            if (r6 == r10) goto L_0x0097
            if (r6 < 0) goto L_0x0091
            if (r9 >= r6) goto L_0x0099
        L_0x0091:
            if (r7 <= r6) goto L_0x0094
            goto L_0x0097
        L_0x0094:
            if (r8 < r6) goto L_0x0097
            goto L_0x0099
        L_0x0097:
            if (r6 != r11) goto L_0x009a
        L_0x0099:
            return r16
        L_0x009a:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r5) goto L_0x00a0
            r14 = 1
            goto L_0x00a1
        L_0x00a0:
            r14 = 2
        L_0x00a1:
            int r4 = r4 + r14
            int r3 = r3 + 2
            r5 = r13
            goto L_0x0008
        L_0x00a7:
            if (r5 != r1) goto L_0x00aa
            return r4
        L_0x00aa:
            return r16
        L_0x00ab:
            int r14 = r6 >> 4
            r11 = 55296(0xd800, float:7.7486E-41)
            r8 = 57343(0xdfff, float:8.0355E-41)
            if (r14 != r15) goto L_0x0118
            int r14 = r3 + 2
            if (r2 > r14) goto L_0x00bd
            if (r5 != r1) goto L_0x00bc
            return r4
        L_0x00bc:
            return r16
        L_0x00bd:
            int r15 = r3 + 1
            byte r15 = r0[r15]
            r7 = r15 & 192(0xc0, float:2.69E-43)
            if (r7 != r13) goto L_0x0114
            byte r7 = r0[r14]
            r14 = r7 & 192(0xc0, float:2.69E-43)
            if (r14 != r13) goto L_0x0110
            r13 = -123008(0xfffffffffffe1f80, float:NaN)
            r7 = r7 ^ r13
            int r13 = r15 << 6
            r7 = r7 ^ r13
            int r6 = r6 << 12
            r6 = r6 ^ r7
            r7 = 2048(0x800, float:2.87E-42)
            if (r6 >= r7) goto L_0x00dd
            if (r5 != r1) goto L_0x00dc
            return r4
        L_0x00dc:
            return r16
        L_0x00dd:
            if (r11 <= r6) goto L_0x00e0
            goto L_0x00e6
        L_0x00e0:
            if (r8 < r6) goto L_0x00e6
            if (r5 != r1) goto L_0x00e5
            return r4
        L_0x00e5:
            return r16
        L_0x00e6:
            int r7 = r5 + 1
            if (r5 != r1) goto L_0x00eb
            return r4
        L_0x00eb:
            if (r6 == r12) goto L_0x00fd
            if (r6 == r10) goto L_0x00fd
            if (r6 < 0) goto L_0x00f3
            if (r9 >= r6) goto L_0x0102
        L_0x00f3:
            r5 = 127(0x7f, float:1.78E-43)
            if (r5 <= r6) goto L_0x00f8
            goto L_0x00fd
        L_0x00f8:
            r5 = 159(0x9f, float:2.23E-43)
            if (r5 < r6) goto L_0x00fd
            goto L_0x0102
        L_0x00fd:
            r5 = 65533(0xfffd, float:9.1831E-41)
            if (r6 != r5) goto L_0x0103
        L_0x0102:
            return r16
        L_0x0103:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r5) goto L_0x0109
            r14 = 1
            goto L_0x010a
        L_0x0109:
            r14 = 2
        L_0x010a:
            int r4 = r4 + r14
            int r3 = r3 + 3
        L_0x010d:
            r5 = r7
            goto L_0x0008
        L_0x0110:
            if (r5 != r1) goto L_0x0113
            return r4
        L_0x0113:
            return r16
        L_0x0114:
            if (r5 != r1) goto L_0x0117
            return r4
        L_0x0117:
            return r16
        L_0x0118:
            int r7 = r6 >> 3
            if (r7 != r15) goto L_0x0197
            int r7 = r3 + 3
            if (r2 > r7) goto L_0x0124
            if (r5 != r1) goto L_0x0123
            return r4
        L_0x0123:
            return r16
        L_0x0124:
            int r14 = r3 + 1
            byte r14 = r0[r14]
            r15 = r14 & 192(0xc0, float:2.69E-43)
            if (r15 != r13) goto L_0x0193
            int r15 = r3 + 2
            byte r15 = r0[r15]
            r9 = r15 & 192(0xc0, float:2.69E-43)
            if (r9 != r13) goto L_0x018f
            byte r7 = r0[r7]
            r9 = r7 & 192(0xc0, float:2.69E-43)
            if (r9 != r13) goto L_0x018b
            r9 = 3678080(0x381f80, float:5.154088E-39)
            r7 = r7 ^ r9
            int r9 = r15 << 6
            r7 = r7 ^ r9
            int r9 = r14 << 12
            r7 = r7 ^ r9
            int r6 = r6 << 18
            r6 = r6 ^ r7
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r6 <= r7) goto L_0x0150
            if (r5 != r1) goto L_0x014f
            return r4
        L_0x014f:
            return r16
        L_0x0150:
            if (r11 <= r6) goto L_0x0153
            goto L_0x0159
        L_0x0153:
            if (r8 < r6) goto L_0x0159
            if (r5 != r1) goto L_0x0158
            return r4
        L_0x0158:
            return r16
        L_0x0159:
            r7 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r7) goto L_0x0161
            if (r5 != r1) goto L_0x0160
            return r4
        L_0x0160:
            return r16
        L_0x0161:
            int r7 = r5 + 1
            if (r5 != r1) goto L_0x0166
            return r4
        L_0x0166:
            if (r6 == r12) goto L_0x017a
            if (r6 == r10) goto L_0x017a
            if (r6 < 0) goto L_0x0170
            r5 = 31
            if (r5 >= r6) goto L_0x017f
        L_0x0170:
            r5 = 127(0x7f, float:1.78E-43)
            if (r5 <= r6) goto L_0x0175
            goto L_0x017a
        L_0x0175:
            r5 = 159(0x9f, float:2.23E-43)
            if (r5 < r6) goto L_0x017a
            goto L_0x017f
        L_0x017a:
            r5 = 65533(0xfffd, float:9.1831E-41)
            if (r6 != r5) goto L_0x0180
        L_0x017f:
            return r16
        L_0x0180:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r5) goto L_0x0186
            r14 = 1
            goto L_0x0187
        L_0x0186:
            r14 = 2
        L_0x0187:
            int r4 = r4 + r14
            int r3 = r3 + 4
            goto L_0x010d
        L_0x018b:
            if (r5 != r1) goto L_0x018e
            return r4
        L_0x018e:
            return r16
        L_0x018f:
            if (r5 != r1) goto L_0x0192
            return r4
        L_0x0192:
            return r16
        L_0x0193:
            if (r5 != r1) goto L_0x0196
            return r4
        L_0x0196:
            return r16
        L_0x0197:
            if (r5 != r1) goto L_0x019a
            return r4
        L_0x019a:
            return r16
        L_0x019b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ByteStringKt.codePointIndexToCharIndex(byte[], int):int");
    }
}
