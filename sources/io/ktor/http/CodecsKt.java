package io.ktor.http;

import com.google.common.base.Ascii;
import io.ktor.util.date.GMTDateParser;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.charsets.EncodingKt;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0002\u001a\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000eH\u0002\u001a8\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001cH\u0002\u001a0\u0010\u001d\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001cH\u0002\u001a,\u0010\u001e\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u000e2\f\b\u0002\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c\u001a6\u0010\u001f\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\f\b\u0002\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c\u001a\n\u0010 \u001a\u00020\u0013*\u00020\u0013\u001a\u0014\u0010!\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\"\u001a\u00020\u0019\u001a\f\u0010#\u001a\u00020\u0013*\u00020\u0013H\u0000\u001a\n\u0010$\u001a\u00020\u0013*\u00020\u0013\u001a\u0014\u0010$\u001a\u00020\u0013*\u00020\u00132\u0006\u0010%\u001a\u00020\u0019H\u0000\u001a\n\u0010&\u001a\u00020\u0013*\u00020\u0013\u001a,\u0010'\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010(\u001a\u00020\u00192\b\b\u0002\u0010\"\u001a\u00020\u00192\f\b\u0002\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c\u001a \u0010)\u001a\u00020**\u00020+2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020*0-H\u0002\u001a\f\u0010.\u001a\u00020\u0013*\u00020\bH\u0002\u001a\u001a\u0010.\u001a\u00020\u0013*\u00020\u00132\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"ATTRIBUTE_CHARACTERS", "", "", "getATTRIBUTE_CHARACTERS", "()Ljava/util/Set;", "HEX_ALPHABET", "SPECIAL_SYMBOLS", "", "", "URL_ALPHABET", "URL_ALPHABET_CHARS", "URL_PROTOCOL_PART", "VALID_PATH_PART", "charToHexDigit", "", "c2", "hexDigitToChar", "digit", "decodeImpl", "", "", "start", "end", "prefixEnd", "plusIsSpace", "", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "decodeScan", "decodeURLPart", "decodeURLQueryComponent", "encodeOAuth", "encodeURLParameter", "spaceToPlus", "encodeURLParameterValue", "encodeURLPath", "encodeSlash", "encodeURLPathPart", "encodeURLQueryComponent", "encodeFull", "forEach", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "block", "Lkotlin/Function1;", "percentEncode", "allowedSet", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Codecs.kt */
public final class CodecsKt {
    private static final Set<Character> ATTRIBUTE_CHARACTERS = SetsKt.plus(URL_ALPHABET_CHARS, SetsKt.setOf('!', '#', Character.valueOf(Typography.dollar), Character.valueOf(Typography.amp), '+', '-', '.', '^', '_', '`', '|', '~'));
    private static final Set<Character> HEX_ALPHABET = CollectionsKt.toSet(CollectionsKt.plus(CollectionsKt.plus(new CharRange('a', 'f'), new CharRange('A', 'F')), new CharRange('0', '9')));
    /* access modifiers changed from: private */
    public static final List<Byte> SPECIAL_SYMBOLS;
    /* access modifiers changed from: private */
    public static final Set<Byte> URL_ALPHABET;
    private static final Set<Character> URL_ALPHABET_CHARS = CollectionsKt.toSet(CollectionsKt.plus(CollectionsKt.plus(new CharRange('a', GMTDateParser.ZONE), new CharRange('A', Matrix.MATRIX_TYPE_ZERO)), new CharRange('0', '9')));
    /* access modifiers changed from: private */
    public static final List<Byte> URL_PROTOCOL_PART;
    private static final Set<Character> VALID_PATH_PART = SetsKt.setOf(Character.valueOf(AbstractJsonLexerKt.COLON), '@', '!', Character.valueOf(Typography.dollar), Character.valueOf(Typography.amp), '\'', '(', ')', Character.valueOf(GMTDateParser.ANY), '+', ',', ';', '=', '-', '.', '_', '~');

    private static final int charToHexDigit(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('A' <= c && c < 'G') {
            return c - '7';
        }
        if ('a' > c || c >= 'g') {
            return -1;
        }
        return c - 'W';
    }

    private static final char hexDigitToChar(int i) {
        return (char) ((i < 0 || i >= 10) ? ((char) (i + 65)) - 10 : i + 48);
    }

    static {
        Iterable<Character> plus = CollectionsKt.plus(CollectionsKt.plus(new CharRange('a', GMTDateParser.ZONE), new CharRange('A', Matrix.MATRIX_TYPE_ZERO)), new CharRange('0', '9'));
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
        for (Character charValue : plus) {
            arrayList.add(Byte.valueOf((byte) charValue.charValue()));
        }
        URL_ALPHABET = CollectionsKt.toSet((List) arrayList);
        Iterable<Character> of = SetsKt.setOf(Character.valueOf(AbstractJsonLexerKt.COLON), '/', '?', '#', Character.valueOf(AbstractJsonLexerKt.BEGIN_LIST), Character.valueOf(AbstractJsonLexerKt.END_LIST), '@', '!', Character.valueOf(Typography.dollar), Character.valueOf(Typography.amp), '\'', '(', ')', Character.valueOf(GMTDateParser.ANY), ',', ';', '=', '-', '.', '_', '~', '+');
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(of, 10));
        for (Character charValue2 : of) {
            arrayList2.add(Byte.valueOf((byte) charValue2.charValue()));
        }
        URL_PROTOCOL_PART = (List) arrayList2;
        Iterable<Character> listOf = CollectionsKt.listOf('-', '.', '_', '~');
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
        for (Character charValue3 : listOf) {
            arrayList3.add(Byte.valueOf((byte) charValue3.charValue()));
        }
        SPECIAL_SYMBOLS = (List) arrayList3;
    }

    public static final Set<Character> getATTRIBUTE_CHARACTERS() {
        return ATTRIBUTE_CHARACTERS;
    }

    public static /* synthetic */ String encodeURLQueryComponent$default(String str, boolean z, boolean z2, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return encodeURLQueryComponent(str, z, z2, charset);
    }

    public static final String encodeURLQueryComponent(String str, boolean z, boolean z2, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        StringBuilder sb = new StringBuilder();
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        forEach(EncodingKt.encode$default(newEncoder, str, 0, 0, 6, (Object) null), new CodecsKt$encodeURLQueryComponent$1$1(z2, sb, z));
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static final String encodeURLPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return encodeURLPath(str, false);
    }

    public static final String encodeURLPathPart(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return encodeURLPath(str, true);
    }

    public static final String encodeURLPath(String str, boolean z) {
        int i;
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringBuilder sb = new StringBuilder();
        Charset charset = Charsets.UTF_8;
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if ((z || charAt != '/') && !URL_ALPHABET_CHARS.contains(Character.valueOf(charAt)) && !VALID_PATH_PART.contains(Character.valueOf(charAt))) {
                if (charAt == '%' && (i = i2 + 2) < str.length()) {
                    Set<Character> set = HEX_ALPHABET;
                    int i3 = i2 + 1;
                    if (set.contains(Character.valueOf(str.charAt(i3))) && set.contains(Character.valueOf(str.charAt(i)))) {
                        sb.append(charAt);
                        sb.append(str.charAt(i3));
                        sb.append(str.charAt(i));
                        i2 += 3;
                    }
                }
                int i4 = CharsKt.isSurrogate(charAt) ? 2 : 1;
                CharsetEncoder newEncoder = charset.newEncoder();
                Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
                int i5 = i4 + i2;
                forEach(EncodingKt.encode(newEncoder, str, i2, i5), new CodecsKt$encodeURLPath$1$1(sb));
                i2 = i5;
            } else {
                sb.append(charAt);
                i2++;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static final String encodeOAuth(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return encodeURLParameter$default(str, false, 1, (Object) null);
    }

    public static /* synthetic */ String encodeURLParameter$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return encodeURLParameter(str, z);
    }

    public static final String encodeURLParameter(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringBuilder sb = new StringBuilder();
        CharsetEncoder newEncoder = Charsets.UTF_8.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "UTF_8.newEncoder()");
        forEach(EncodingKt.encode$default(newEncoder, str, 0, 0, 6, (Object) null), new CodecsKt$encodeURLParameter$1$1(sb, z));
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static final String percentEncode(String str, Set<Character> set) {
        byte[] bArr;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(set, "allowedSet");
        CharSequence charSequence = str;
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (!set.contains(Character.valueOf(charSequence.charAt(i2)))) {
                i++;
            }
        }
        if (i == 0) {
            return str;
        }
        Charset charset = Charsets.UTF_8;
        if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
            bArr = StringsKt.encodeToByteArray(str);
        } else {
            CharsetEncoder newEncoder = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            bArr = CharsetJVMKt.encodeToByteArray(newEncoder, charSequence, 0, str.length());
        }
        int length = str.length() - i;
        char[] cArr = new char[(length + ((bArr.length - length) * 3))];
        int i3 = 0;
        for (byte b : bArr) {
            char c = (char) b;
            if (set.contains(Character.valueOf(c))) {
                cArr[i3] = c;
                i3++;
            } else {
                cArr[i3] = '%';
                int i4 = i3 + 2;
                cArr[i3 + 1] = hexDigitToChar((b & 255) >> 4);
                i3 += 3;
                cArr[i4] = hexDigitToChar(b & Ascii.SI);
            }
        }
        return StringsKt.concatToString(cArr);
    }

    public static final String encodeURLParameterValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return encodeURLParameter(str, true);
    }

    public static /* synthetic */ String decodeURLQueryComponent$default(String str, int i, int i2, boolean z, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLQueryComponent(str, i, i2, z, charset);
    }

    public static final String decodeURLQueryComponent(String str, int i, int i2, boolean z, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return decodeScan(str, i, i2, z, charset);
    }

    public static /* synthetic */ String decodeURLPart$default(String str, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLPart(str, i, i2, charset);
    }

    public static final String decodeURLPart(String str, int i, int i2, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return decodeScan(str, i, i2, false, charset);
    }

    private static final String decodeScan(String str, int i, int i2, boolean z, Charset charset) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (z && charAt == '+')) {
                return decodeImpl(str, i, i2, i3, z, charset);
            }
        }
        if (i == 0 && i2 == str.length()) {
            return str.toString();
        }
        String substring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    private static final String decodeImpl(CharSequence charSequence, int i, int i2, int i3, boolean z, Charset charset) {
        int i4 = i2 - i;
        if (i4 > 255) {
            i4 /= 3;
        }
        StringBuilder sb = new StringBuilder(i4);
        if (i3 > i) {
            sb.append(charSequence, i, i3);
        }
        byte[] bArr = null;
        while (i3 < i2) {
            char charAt = charSequence.charAt(i3);
            if (z && charAt == '+') {
                sb.append(' ');
            } else if (charAt == '%') {
                if (bArr == null) {
                    bArr = new byte[((i2 - i3) / 3)];
                }
                int i5 = 0;
                while (i3 < i2 && charSequence.charAt(i3) == '%') {
                    int i6 = i3 + 2;
                    if (i6 < i2) {
                        int i7 = i3 + 1;
                        int charToHexDigit = charToHexDigit(charSequence.charAt(i7));
                        int charToHexDigit2 = charToHexDigit(charSequence.charAt(i6));
                        if (charToHexDigit == -1 || charToHexDigit2 == -1) {
                            throw new URLDecodeException("Wrong HEX escape: %" + charSequence.charAt(i7) + charSequence.charAt(i6) + ", in " + charSequence + ", at " + i3);
                        }
                        bArr[i5] = (byte) ((charToHexDigit * 16) + charToHexDigit2);
                        i3 += 3;
                        i5++;
                    } else {
                        throw new URLDecodeException("Incomplete trailing HEX escape: " + charSequence.subSequence(i3, charSequence.length()).toString() + ", in " + charSequence + " at " + i3);
                    }
                }
                sb.append(new String(bArr, 0, i5, charset));
            } else {
                sb.append(charAt);
            }
            i3++;
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }

    /* access modifiers changed from: private */
    public static final String percentEncode(byte b) {
        return StringsKt.concatToString(new char[]{'%', hexDigitToChar((b & 255) >> 4), hexDigitToChar(b & Ascii.SI)});
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void forEach(io.ktor.utils.io.core.ByteReadPacket r5, kotlin.jvm.functions.Function1<? super java.lang.Byte, kotlin.Unit> r6) {
        /*
            io.ktor.utils.io.core.Input r5 = (io.ktor.utils.io.core.Input) r5
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r5, r0)
            if (r1 != 0) goto L_0x000a
            goto L_0x0029
        L_0x000a:
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x002d }
        L_0x000d:
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x002d }
            int r4 = r2.getReadPosition()     // Catch:{ all -> 0x002d }
            if (r3 <= r4) goto L_0x0023
            byte r3 = r2.readByte()     // Catch:{ all -> 0x002d }
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)     // Catch:{ all -> 0x002d }
            r6.invoke(r3)     // Catch:{ all -> 0x002d }
            goto L_0x000d
        L_0x0023:
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r5, r1)     // Catch:{ all -> 0x002a }
            if (r1 != 0) goto L_0x000a
        L_0x0029:
            return
        L_0x002a:
            r6 = move-exception
            r0 = 0
            goto L_0x002e
        L_0x002d:
            r6 = move-exception
        L_0x002e:
            if (r0 == 0) goto L_0x0033
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r1)
        L_0x0033:
            goto L_0x0035
        L_0x0034:
            throw r6
        L_0x0035:
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.CodecsKt.forEach(io.ktor.utils.io.core.ByteReadPacket, kotlin.jvm.functions.Function1):void");
    }
}
