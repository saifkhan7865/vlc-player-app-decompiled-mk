package io.ktor.utils.io.core;

import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.charsets.EncodingKt;
import io.ktor.utils.io.core.internal.CharArraySequence;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.EncodeResult;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0002\u001a\r\u0010\t\u001a\u00020\n*\u00020\u000bH\b\u001a\u0014\u0010\f\u001a\u00020\r*\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u001a\n\u0010\f\u001a\u00020\r*\u00020\u0010\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0003\u001a\u001e\u0010\u0011\u001a\u00020\r*\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00102\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00102\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u0007\u001a.\u0010\u0014\u001a\u00020\u0003*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010 \u001a\u00020\u0015*\u00020\u00102\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u000f\u001a\u00020\u0003H\u0007\u001a\"\u0010!\u001a\u00020\u0015*\u00020\u00102\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\"\u001a\u00020\u0003H\u0007\u001a \u0010!\u001a\u00020\u0015*\u00020\u00102\u0006\u0010#\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a \u0010$\u001a\u00020\u0015*\u00020\u00102\u0006\u0010\b\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a \u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u000e2\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a \u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u00102\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u001e\u0010'\u001a\u00020\n*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001c\u0010(\u001a\u00020\u0015*\u00020\u00102\u0006\u0010)\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a$\u0010*\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001d\u001a\u00020+2\u0006\u0010)\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a(\u0010*\u001a\u00020\u0003*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010)\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a,\u0010,\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001d\u001a\u00020+2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0002\u001a0\u0010,\u001a\u00020\u0003*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0002\u001a$\u0010.\u001a\u00020\u0003*\u00020\u00102\u0006\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020+H\u0002\u001a\u001b\u0010/\u001a\u00020\r*\u00020\u00152\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\b\u001a4\u00100\u001a\u000201*\u00020+2\u0006\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a4\u00100\u001a\u000201*\u00020+2\u0006\u00102\u001a\u0002062\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a$\u00107\u001a\u000201*\u00020+2\u0006\u00102\u001a\u0002062\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u0003H\u0002¨\u00068"}, d2 = {"bufferLimitExceeded", "", "limit", "", "prematureEndOfStream", "size", "", "prematureEndOfStreamToReadChars", "charactersCount", "isAsciiChar", "", "", "readBytes", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "n", "Lio/ktor/utils/io/core/Input;", "readBytesOf", "min", "max", "readText", "", "Lio/ktor/utils/io/core/Buffer;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "decoder", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "readTextExact", "readTextExactBytes", "bytes", "bytesCount", "readTextExactCharacters", "readUTF8Line", "estimate", "readUTF8LineTo", "readUTF8UntilDelimiter", "delimiters", "readUTF8UntilDelimiterTo", "Lio/ktor/utils/io/core/Output;", "readUTF8UntilDelimiterToSlowUtf8", "decoded0", "readUTFUntilDelimiterToSlowAscii", "toByteArray", "writeText", "", "text", "", "fromIndex", "toIndex", "", "writeTextUtf8", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Strings.kt */
public final class StringsKt {
    private static final boolean isAsciiChar(char c) {
        return c <= 127;
    }

    public static /* synthetic */ byte[] toByteArray$default(String str, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
            return kotlin.text.StringsKt.encodeToByteArray(str);
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        return CharsetJVMKt.encodeToByteArray(newEncoder, str, 0, str.length());
    }

    public static final byte[] toByteArray(String str, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
            return kotlin.text.StringsKt.encodeToByteArray(str);
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        return CharsetJVMKt.encodeToByteArray(newEncoder, str, 0, str.length());
    }

    public static /* synthetic */ String readUTF8Line$default(ByteReadPacket byteReadPacket, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 16;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readUTF8Line(byteReadPacket, i, i2);
    }

    public static /* synthetic */ String readUTF8Line$default(Input input, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 16;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readUTF8Line(input, i, i2);
    }

    public static final String readUTF8Line(Input input, int i, int i2) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        StringBuilder sb = new StringBuilder(i);
        if (readUTF8LineTo(input, sb, i2)) {
            return sb.toString();
        }
        return null;
    }

    public static /* synthetic */ String readUTF8UntilDelimiter$default(Input input, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiter(input, str, i);
    }

    public static final String readUTF8UntilDelimiter(Input input, String str, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(str, "delimiters");
        StringBuilder sb = new StringBuilder();
        readUTF8UntilDelimiterTo(input, (Appendable) sb, str, i);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static /* synthetic */ int readUTF8UntilDelimiterTo$default(Input input, Appendable appendable, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiterTo(input, appendable, str, i);
    }

    public static /* synthetic */ int readUTF8UntilDelimiterTo$default(Input input, Output output, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiterTo(input, output, str, i);
    }

    public static final int readUTF8UntilDelimiterTo(Input input, Output output, String str, int i) {
        long readUntilDelimiters;
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(output, "out");
        Intrinsics.checkNotNullParameter(str, "delimiters");
        int length = str.length();
        if (length == 1 && str.charAt(0) <= 127) {
            readUntilDelimiters = ScannerKt.readUntilDelimiter(input, (byte) str.charAt(0), output);
        } else if (length != 2 || str.charAt(0) > 127 || str.charAt(1) > 127) {
            return readUTFUntilDelimiterToSlowAscii(input, str, i, output);
        } else {
            readUntilDelimiters = ScannerKt.readUntilDelimiters(input, (byte) str.charAt(0), (byte) str.charAt(1), output);
        }
        return (int) readUntilDelimiters;
    }

    public static /* synthetic */ byte[] readBytes$default(ByteReadPacket byteReadPacket, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            long remaining = byteReadPacket.getRemaining();
            if (remaining <= 2147483647L) {
                i = (int) remaining;
            } else {
                throw new IllegalArgumentException("Unable to convert to a ByteArray: packet is too big");
            }
        }
        return readBytes(byteReadPacket, i);
    }

    public static final byte[] readBytes(ByteReadPacket byteReadPacket, int i) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        if (i == 0) {
            return UnsafeKt.EmptyByteArray;
        }
        byte[] bArr = new byte[i];
        InputArraysKt.readFully((Input) byteReadPacket, bArr, 0, i);
        return bArr;
    }

    public static final byte[] readBytes(Input input, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        return readBytesOf(input, i, i);
    }

    public static final byte[] readBytes(Input input) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        return readBytesOf$default(input, 0, 0, 3, (Object) null);
    }

    public static /* synthetic */ byte[] readBytesOf$default(Input input, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readBytesOf(input, i, i2);
    }

    public static final byte[] readBytesOf(Input input, int i, int i2) {
        int readAvailable;
        Intrinsics.checkNotNullParameter(input, "<this>");
        if (i == i2 && i == 0) {
            return UnsafeKt.EmptyByteArray;
        }
        int i3 = 0;
        if (i == i2) {
            byte[] bArr = new byte[i];
            InputArraysKt.readFully(input, bArr, 0, i);
            return bArr;
        }
        byte[] bArr2 = new byte[((int) RangesKt.coerceAtLeast(RangesKt.coerceAtMost((long) i2, EncodingKt.sizeEstimate(input)), (long) i))];
        while (i3 < i2 && (readAvailable = InputArraysKt.readAvailable(input, bArr2, i3, Math.min(i2, bArr2.length) - i3)) > 0) {
            i3 += readAvailable;
            if (bArr2.length == i3) {
                bArr2 = Arrays.copyOf(bArr2, i3 * 2);
                Intrinsics.checkNotNullExpressionValue(bArr2, "copyOf(this, newSize)");
            }
        }
        if (i3 < i) {
            throw new EOFException("Not enough bytes available to read " + i + " bytes: " + (i - i3) + " more required");
        } else if (i3 == bArr2.length) {
            return bArr2;
        } else {
            byte[] copyOf = Arrays.copyOf(bArr2, i3);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            return copyOf;
        }
    }

    public static /* synthetic */ int readText$default(Input input, Appendable appendable, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, appendable, charset, i);
    }

    public static final int readText(Input input, Appendable appendable, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(appendable, "out");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        return CharsetJVMKt.decode(newDecoder, input, appendable, i);
    }

    public static /* synthetic */ String readText$default(Input input, CharsetDecoder charsetDecoder, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, charsetDecoder, i);
    }

    @Deprecated(message = "Use CharsetDecoder.decode instead", replaceWith = @ReplaceWith(expression = "decoder.decode(this, max)", imports = {"io.ktor.utils.io.charsets.decode"}))
    public static final String readText(Input input, CharsetDecoder charsetDecoder, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charsetDecoder, "decoder");
        return EncodingKt.decode(charsetDecoder, input, i);
    }

    public static /* synthetic */ String readText$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, charset, i);
    }

    public static final String readText(Input input, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        return EncodingKt.decode(newDecoder, input, i);
    }

    public static final String readText(Buffer buffer, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        StringBuilder sb = new StringBuilder();
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        CharsetJVMKt.decodeBuffer(newDecoder, buffer, sb, true, i);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static /* synthetic */ String readText$default(Buffer buffer, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(buffer, charset, i);
    }

    public static /* synthetic */ String readTextExact$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExact(input, charset, i);
    }

    @Deprecated(message = "Use readTextExactCharacters instead.", replaceWith = @ReplaceWith(expression = "readTextExactCharacters(n, charset)", imports = {}))
    public static final String readTextExact(Input input, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readTextExactCharacters(input, i, charset);
    }

    public static /* synthetic */ String readTextExactCharacters$default(Input input, int i, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactCharacters(input, i, charset);
    }

    public static final String readTextExactCharacters(Input input, int i, Charset charset) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String readText = readText(input, charset, i);
        if (readText.length() >= i) {
            return readText;
        }
        prematureEndOfStreamToReadChars(i);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ String readTextExactBytes$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactBytes(input, charset, i);
    }

    @Deprecated(message = "Parameters order is changed.", replaceWith = @ReplaceWith(expression = "readTextExactBytes(bytes, charset)", imports = {}))
    public static final String readTextExactBytes(Input input, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readTextExactBytes(input, i, charset);
    }

    public static /* synthetic */ String readTextExactBytes$default(Input input, int i, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactBytes(input, i, charset);
    }

    public static final String readTextExactBytes(Input input, int i, Charset charset) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        return CharsetJVMKt.decodeExactBytes(newDecoder, input, i);
    }

    public static /* synthetic */ void writeText$default(Output output, CharSequence charSequence, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(output, charSequence, i, i2, charset);
    }

    public static final void writeText(Output output, CharSequence charSequence, int i, int i2, Charset charset) {
        Intrinsics.checkNotNullParameter(output, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (charset == Charsets.UTF_8) {
            writeTextUtf8(output, charSequence, i, i2);
            return;
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        EncodingKt.encodeToImpl(newEncoder, output, charSequence, i, i2);
    }

    public static /* synthetic */ void writeText$default(Output output, char[] cArr, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(output, cArr, i, i2, charset);
    }

    public static final void writeText(Output output, char[] cArr, int i, int i2, Charset charset) {
        Intrinsics.checkNotNullParameter(output, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (charset == Charsets.UTF_8) {
            writeTextUtf8(output, new CharArraySequence(cArr, 0, cArr.length), i, i2);
            return;
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        EncodingKt.encode(newEncoder, cArr, i, i2, output);
    }

    private static final Void bufferLimitExceeded(int i) {
        throw new BufferLimitExceededException("Too many characters before delimiter: limit " + i + " exceeded");
    }

    public static final Void prematureEndOfStream(int i) {
        throw new EOFException("Premature end of stream: expected " + i + " bytes");
    }

    public static final Void prematureEndOfStream(long j) {
        throw new EOFException("Premature end of stream: expected " + j + " bytes");
    }

    private static final Void prematureEndOfStreamToReadChars(int i) {
        throw new EOFException("Not enough input bytes to read " + i + " characters.");
    }

    public static final String readUTF8Line(ByteReadPacket byteReadPacket, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        if (byteReadPacket.getEndOfInput()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(i);
        if (readUTF8LineTo(byteReadPacket, sb, i2)) {
            return sb.toString();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x016f, code lost:
        r6.discardExact(((r13 - r11) - r16) + 1);
        r3 = r17;
        r10 = r19;
     */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x016f A[EDGE_INSN: B:164:0x016f->B:102:0x016f ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0161 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x00fe A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x007f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0191 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x013c A[Catch:{ all -> 0x01c0, all -> 0x01cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0162 A[Catch:{ all -> 0x01c0, all -> 0x01cb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean readUTF8LineTo(io.ktor.utils.io.core.Input r21, java.lang.Appendable r22, int r23) {
        /*
            r1 = r21
            r0 = r22
            r2 = r23
            java.lang.String r3 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "out"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            r3 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r3)
            if (r4 != 0) goto L_0x001b
            r5 = 1
            r8 = 0
            goto L_0x0202
        L_0x001b:
            r6 = 1
            r7 = 0
            r8 = 0
            r9 = 1
            r10 = 0
        L_0x0020:
            r11 = r4
            io.ktor.utils.io.core.Buffer r11 = (io.ktor.utils.io.core.Buffer) r11     // Catch:{ all -> 0x021a }
            int r12 = r11.getWritePosition()     // Catch:{ all -> 0x021a }
            int r11 = r11.getReadPosition()     // Catch:{ all -> 0x021a }
            int r12 = r12 - r11
            if (r12 < r6) goto L_0x01cd
            r6 = r4
            io.ktor.utils.io.core.Buffer r6 = (io.ktor.utils.io.core.Buffer) r6     // Catch:{ all -> 0x01c0 }
            java.nio.ByteBuffer r9 = r6.m155getMemorySK3TCg8()     // Catch:{ all -> 0x01c0 }
            int r11 = r6.getReadPosition()     // Catch:{ all -> 0x01c0 }
            int r12 = r6.getWritePosition()     // Catch:{ all -> 0x01c0 }
            r13 = r11
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
        L_0x0044:
            if (r13 >= r12) goto L_0x0198
            byte r5 = r9.get(r13)     // Catch:{ all -> 0x01c0 }
            r3 = r5 & 255(0xff, float:3.57E-43)
            r18 = r9
            r9 = r5 & 128(0x80, float:1.794E-43)
            r19 = r10
            r10 = 13
            r20 = -1
            if (r9 != 0) goto L_0x009a
            if (r14 != 0) goto L_0x0091
            char r3 = (char) r3     // Catch:{ all -> 0x01c0 }
            if (r3 != r10) goto L_0x0067
            if (r7 == 0) goto L_0x0062
        L_0x005f:
            r3 = 0
            r10 = 1
            goto L_0x007d
        L_0x0062:
            r10 = r19
            r3 = 1
            r7 = 1
            goto L_0x007d
        L_0x0067:
            r5 = 10
            if (r3 != r5) goto L_0x0070
            r3 = 0
            r10 = 1
            r17 = 1
            goto L_0x007d
        L_0x0070:
            if (r7 == 0) goto L_0x0073
            goto L_0x005f
        L_0x0073:
            if (r8 == r2) goto L_0x0088
            int r8 = r8 + 1
            r0.append(r3)     // Catch:{ all -> 0x01c0 }
            r10 = r19
            r3 = 1
        L_0x007d:
            if (r3 != 0) goto L_0x0191
            int r13 = r13 - r11
            r6.discardExact(r13)     // Catch:{ all -> 0x01c0 }
        L_0x0083:
            r3 = r17
        L_0x0085:
            r9 = -1
            goto L_0x01a3
        L_0x0088:
            bufferLimitExceeded(r23)     // Catch:{ all -> 0x01c0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x01c0 }
            r0.<init>()     // Catch:{ all -> 0x01c0 }
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x0091:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedByteCount(r14)     // Catch:{ all -> 0x01c0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x01c0 }
            r0.<init>()     // Catch:{ all -> 0x01c0 }
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x009a:
            if (r14 != 0) goto L_0x00c9
            r5 = 128(0x80, float:1.794E-43)
            r15 = r3
            r3 = 1
        L_0x00a0:
            r9 = 7
            if (r3 >= r9) goto L_0x00b1
            r9 = r15 & r5
            if (r9 == 0) goto L_0x00b1
            r9 = r5 ^ -1
            r15 = r15 & r9
            int r5 = r5 >> 1
            int r14 = r14 + 1
            int r3 = r3 + 1
            goto L_0x00a0
        L_0x00b1:
            int r3 = r14 + -1
            int r5 = r12 - r13
            if (r14 <= r5) goto L_0x00c2
            int r13 = r13 - r11
            r6.discardExact(r13)     // Catch:{ all -> 0x01c0 }
            r9 = r14
            r3 = r17
            r10 = r19
            goto L_0x01a3
        L_0x00c2:
            r16 = r14
            r10 = r19
            r14 = r3
            goto L_0x0191
        L_0x00c9:
            int r3 = r15 << 6
            r5 = r5 & 127(0x7f, float:1.78E-43)
            r15 = r3 | r5
            int r14 = r14 + -1
            if (r14 != 0) goto L_0x018f
            boolean r3 = io.ktor.utils.io.core.internal.UTF8Kt.isBmpCodePoint(r15)     // Catch:{ all -> 0x01c0 }
            if (r3 == 0) goto L_0x0111
            char r3 = (char) r15     // Catch:{ all -> 0x01c0 }
            if (r3 != r10) goto L_0x00e6
            if (r7 == 0) goto L_0x00e1
        L_0x00de:
            r3 = 0
            r10 = 1
            goto L_0x00fc
        L_0x00e1:
            r10 = r19
            r3 = 1
            r7 = 1
            goto L_0x00fc
        L_0x00e6:
            r5 = 10
            if (r3 != r5) goto L_0x00ef
            r3 = 0
            r10 = 1
            r17 = 1
            goto L_0x00fc
        L_0x00ef:
            if (r7 == 0) goto L_0x00f2
            goto L_0x00de
        L_0x00f2:
            if (r8 == r2) goto L_0x0108
            int r8 = r8 + 1
            r0.append(r3)     // Catch:{ all -> 0x01c0 }
            r10 = r19
            r3 = 1
        L_0x00fc:
            if (r3 != 0) goto L_0x0164
            int r13 = r13 - r11
            int r13 = r13 - r16
            r3 = 1
            int r13 = r13 + r3
            r6.discardExact(r13)     // Catch:{ all -> 0x01c0 }
            goto L_0x0083
        L_0x0108:
            bufferLimitExceeded(r23)     // Catch:{ all -> 0x01c0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x01c0 }
            r0.<init>()     // Catch:{ all -> 0x01c0 }
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x0111:
            boolean r3 = io.ktor.utils.io.core.internal.UTF8Kt.isValidCodePoint(r15)     // Catch:{ all -> 0x01c0 }
            if (r3 == 0) goto L_0x0186
            int r3 = io.ktor.utils.io.core.internal.UTF8Kt.highSurrogate(r15)     // Catch:{ all -> 0x01c0 }
            char r3 = (char) r3     // Catch:{ all -> 0x01c0 }
            if (r3 != r10) goto L_0x0127
            if (r7 == 0) goto L_0x0124
        L_0x0120:
            r3 = 0
        L_0x0121:
            r19 = 1
            goto L_0x013a
        L_0x0124:
            r3 = 1
            r7 = 1
            goto L_0x013a
        L_0x0127:
            r5 = 10
            if (r3 != r5) goto L_0x012f
            r3 = 0
            r17 = 1
            goto L_0x0121
        L_0x012f:
            if (r7 == 0) goto L_0x0132
            goto L_0x0120
        L_0x0132:
            if (r8 == r2) goto L_0x017d
            int r8 = r8 + 1
            r0.append(r3)     // Catch:{ all -> 0x01c0 }
            r3 = 1
        L_0x013a:
            if (r3 == 0) goto L_0x016f
            int r3 = io.ktor.utils.io.core.internal.UTF8Kt.lowSurrogate(r15)     // Catch:{ all -> 0x01c0 }
            char r3 = (char) r3     // Catch:{ all -> 0x01c0 }
            if (r3 != r10) goto L_0x014c
            if (r7 == 0) goto L_0x0149
        L_0x0145:
            r3 = 0
        L_0x0146:
            r19 = 1
            goto L_0x015f
        L_0x0149:
            r3 = 1
            r7 = 1
            goto L_0x015f
        L_0x014c:
            r5 = 10
            if (r3 != r5) goto L_0x0154
            r3 = 0
            r17 = 1
            goto L_0x0146
        L_0x0154:
            if (r7 == 0) goto L_0x0157
            goto L_0x0145
        L_0x0157:
            if (r8 == r2) goto L_0x0166
            int r8 = r8 + 1
            r0.append(r3)     // Catch:{ all -> 0x01c0 }
            r3 = 1
        L_0x015f:
            if (r3 != 0) goto L_0x0162
            goto L_0x016f
        L_0x0162:
            r10 = r19
        L_0x0164:
            r15 = 0
            goto L_0x0191
        L_0x0166:
            bufferLimitExceeded(r23)     // Catch:{ all -> 0x01c0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x01c0 }
            r0.<init>()     // Catch:{ all -> 0x01c0 }
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x016f:
            int r13 = r13 - r11
            int r13 = r13 - r16
            r3 = 1
            int r13 = r13 + r3
            r6.discardExact(r13)     // Catch:{ all -> 0x01c0 }
            r3 = r17
            r10 = r19
            goto L_0x0085
        L_0x017d:
            bufferLimitExceeded(r23)     // Catch:{ all -> 0x01c0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x01c0 }
            r0.<init>()     // Catch:{ all -> 0x01c0 }
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x0186:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedCodePoint(r15)     // Catch:{ all -> 0x01c0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x01c0 }
            r0.<init>()     // Catch:{ all -> 0x01c0 }
            throw r0     // Catch:{ all -> 0x01c0 }
        L_0x018f:
            r10 = r19
        L_0x0191:
            int r13 = r13 + 1
            r9 = r18
            r3 = 1
            goto L_0x0044
        L_0x0198:
            r19 = r10
            int r12 = r12 - r11
            r6.discardExact(r12)     // Catch:{ all -> 0x01c0 }
            r3 = r17
            r10 = r19
            r9 = 0
        L_0x01a3:
            if (r3 <= 0) goto L_0x01a8
            r6.discardExact(r3)     // Catch:{ all -> 0x01c0 }
        L_0x01a8:
            if (r10 == 0) goto L_0x01ac
            r6 = 0
            goto L_0x01b2
        L_0x01ac:
            r3 = 1
            int r5 = kotlin.ranges.RangesKt.coerceAtLeast((int) r9, (int) r3)     // Catch:{ all -> 0x01c0 }
            r6 = r5
        L_0x01b2:
            r3 = r4
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x01cb }
            int r5 = r3.getWritePosition()     // Catch:{ all -> 0x01cb }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x01cb }
            int r12 = r5 - r3
            goto L_0x01cd
        L_0x01c0:
            r0 = move-exception
            r2 = r4
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x01cb }
            r2.getWritePosition()     // Catch:{ all -> 0x01cb }
            r2.getReadPosition()     // Catch:{ all -> 0x01cb }
            throw r0     // Catch:{ all -> 0x01cb }
        L_0x01cb:
            r0 = move-exception
            goto L_0x021c
        L_0x01cd:
            if (r12 != 0) goto L_0x01d7
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r4)     // Catch:{ all -> 0x01d4 }
            goto L_0x01f3
        L_0x01d4:
            r0 = move-exception
            r3 = 0
            goto L_0x021d
        L_0x01d7:
            if (r12 < r6) goto L_0x01ec
            r3 = r4
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x01d4 }
            int r5 = r3.getCapacity()     // Catch:{ all -> 0x01d4 }
            int r3 = r3.getLimit()     // Catch:{ all -> 0x01d4 }
            int r5 = r5 - r3
            r3 = 8
            if (r5 >= r3) goto L_0x01ea
            goto L_0x01ec
        L_0x01ea:
            r3 = r4
            goto L_0x01f3
        L_0x01ec:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r4)     // Catch:{ all -> 0x01d4 }
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r6)     // Catch:{ all -> 0x01d4 }
        L_0x01f3:
            if (r3 != 0) goto L_0x01f7
            r3 = 0
            goto L_0x01fb
        L_0x01f7:
            r4 = r3
            r3 = 1
            if (r6 > 0) goto L_0x0020
        L_0x01fb:
            if (r3 == 0) goto L_0x0200
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r4)
        L_0x0200:
            r3 = r9
            r5 = 1
        L_0x0202:
            if (r3 > r5) goto L_0x0211
            if (r8 > 0) goto L_0x020f
            boolean r0 = r21.getEndOfInput()
            if (r0 != 0) goto L_0x020d
            goto L_0x020f
        L_0x020d:
            r3 = 0
            goto L_0x0210
        L_0x020f:
            r3 = 1
        L_0x0210:
            return r3
        L_0x0211:
            prematureEndOfStream((int) r3)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x021a:
            r0 = move-exception
            r5 = 1
        L_0x021c:
            r3 = 1
        L_0x021d:
            if (r3 == 0) goto L_0x0222
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r4)
        L_0x0222:
            goto L_0x0224
        L_0x0223:
            throw r0
        L_0x0224:
            goto L_0x0223
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8LineTo(io.ktor.utils.io.core.Input, java.lang.Appendable, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readUTF8UntilDelimiterTo(io.ktor.utils.io.core.Input r17, java.lang.Appendable r18, java.lang.String r19, int r20) {
        /*
            r1 = r17
            r0 = r18
            r2 = r19
            r3 = r20
            java.lang.String r4 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "out"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "delimiters"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            r4 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r4)
            r6 = 0
            if (r5 != 0) goto L_0x0022
            r7 = 0
            goto L_0x008a
        L_0x0022:
            r7 = 0
            r8 = 0
        L_0x0024:
            r9 = r5
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x0096 }
            java.nio.ByteBuffer r10 = r9.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0096 }
            int r11 = r9.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r12 = r9.getWritePosition()     // Catch:{ all -> 0x0096 }
            r13 = r11
        L_0x0034:
            if (r13 >= r12) goto L_0x0074
            byte r14 = r10.get(r13)     // Catch:{ all -> 0x0096 }
            r15 = r14 & 255(0xff, float:3.57E-43)
            r4 = 128(0x80, float:1.794E-43)
            r14 = r14 & r4
            if (r14 == r4) goto L_0x006c
            char r4 = (char) r15     // Catch:{ all -> 0x0096 }
            r14 = r2
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14     // Catch:{ all -> 0x0096 }
            r15 = 2
            r16 = r8
            r8 = 0
            boolean r8 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r14, (char) r4, (boolean) r6, (int) r15, (java.lang.Object) r8)     // Catch:{ all -> 0x0096 }
            if (r8 == 0) goto L_0x0052
            r4 = 0
            r8 = 1
            goto L_0x005c
        L_0x0052:
            if (r7 == r3) goto L_0x0063
            int r7 = r7 + 1
            r0.append(r4)     // Catch:{ all -> 0x0096 }
            r8 = r16
            r4 = 1
        L_0x005c:
            if (r4 != 0) goto L_0x005f
            goto L_0x006e
        L_0x005f:
            int r13 = r13 + 1
            r4 = 1
            goto L_0x0034
        L_0x0063:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0096 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0096 }
            r0.<init>()     // Catch:{ all -> 0x0096 }
            throw r0     // Catch:{ all -> 0x0096 }
        L_0x006c:
            r16 = r8
        L_0x006e:
            int r13 = r13 - r11
            r9.discardExact(r13)     // Catch:{ all -> 0x0096 }
            r4 = 0
            goto L_0x007d
        L_0x0074:
            r16 = r8
            int r12 = r12 - r11
            r9.discardExact(r12)     // Catch:{ all -> 0x0096 }
            r8 = r16
            r4 = 1
        L_0x007d:
            if (r4 != 0) goto L_0x0083
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
            goto L_0x0089
        L_0x0083:
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r5)     // Catch:{ all -> 0x0093 }
            if (r5 != 0) goto L_0x0091
        L_0x0089:
            r6 = r8
        L_0x008a:
            if (r6 != 0) goto L_0x0090
            int r7 = readUTF8UntilDelimiterToSlowUtf8((io.ktor.utils.io.core.Input) r1, (java.lang.Appendable) r0, (java.lang.String) r2, (int) r3, (int) r7)
        L_0x0090:
            return r7
        L_0x0091:
            r4 = 1
            goto L_0x0024
        L_0x0093:
            r0 = move-exception
            r4 = 0
            goto L_0x0098
        L_0x0096:
            r0 = move-exception
            r4 = 1
        L_0x0098:
            if (r4 == 0) goto L_0x009d
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
        L_0x009d:
            goto L_0x009f
        L_0x009e:
            throw r0
        L_0x009f:
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8UntilDelimiterTo(io.ktor.utils.io.core.Input, java.lang.Appendable, java.lang.String, int):int");
    }

    private static final void writeTextUtf8(Output output, CharSequence charSequence, int i, int i2) {
        ChunkBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, (ChunkBuffer) null);
        while (true) {
            try {
                Buffer buffer = prepareWriteHead;
                int r2 = UTF8Kt.m1762encodeUTF8lBXzO7A(buffer.m155getMemorySK3TCg8(), charSequence, i, i2, buffer.getWritePosition(), buffer.getLimit());
                short r3 = EncodeResult.m1751component1Mh2AYeg(r2) & 65535;
                i += r3;
                buffer.commitWritten(EncodeResult.m1752component2Mh2AYeg(r2) & 65535);
                int i3 = (r3 != 0 || i >= i2) ? i < i2 ? 1 : 0 : 8;
                if (i3 > 0) {
                    prepareWriteHead = UnsafeKt.prepareWriteHead(output, i3, prepareWriteHead);
                } else {
                    return;
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int readUTFUntilDelimiterToSlowAscii(io.ktor.utils.io.core.Input r18, java.lang.String r19, int r20, io.ktor.utils.io.core.Output r21) {
        /*
            r1 = r18
            r0 = r19
            r2 = r20
            r3 = r21
            r4 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r4)
            if (r5 != 0) goto L_0x0013
            r6 = 0
            r7 = 0
            goto L_0x009a
        L_0x0013:
            r7 = 0
            r8 = 0
        L_0x0015:
            r9 = r5
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x00ad }
            int r10 = r9.getWritePosition()     // Catch:{ all -> 0x00ad }
            int r11 = r9.getReadPosition()     // Catch:{ all -> 0x00ad }
            int r10 = r10 - r11
            java.nio.ByteBuffer r11 = r9.m155getMemorySK3TCg8()     // Catch:{ all -> 0x00ad }
            int r12 = r9.getReadPosition()     // Catch:{ all -> 0x00ad }
            int r13 = r9.getWritePosition()     // Catch:{ all -> 0x00ad }
            r14 = r12
        L_0x002e:
            if (r14 >= r13) goto L_0x0071
            byte r15 = r11.get(r14)     // Catch:{ all -> 0x00ad }
            r4 = r15 & 255(0xff, float:3.57E-43)
            r6 = 128(0x80, float:1.794E-43)
            r15 = r15 & r6
            if (r15 == r6) goto L_0x0068
            char r4 = (char) r4     // Catch:{ all -> 0x00ad }
            r6 = r0
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x00ad }
            r15 = 2
            r16 = r8
            r8 = 0
            r17 = r11
            r11 = 0
            boolean r4 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r6, (char) r4, (boolean) r11, (int) r15, (java.lang.Object) r8)     // Catch:{ all -> 0x00ad }
            if (r4 == 0) goto L_0x004f
            r4 = 0
            r8 = 1
            goto L_0x0056
        L_0x004f:
            if (r7 == r2) goto L_0x005f
            int r7 = r7 + 1
            r8 = r16
            r4 = 1
        L_0x0056:
            if (r4 != 0) goto L_0x0059
            goto L_0x006b
        L_0x0059:
            int r14 = r14 + 1
            r11 = r17
            r4 = 1
            goto L_0x002e
        L_0x005f:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x00ad }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x00ad }
            r0.<init>()     // Catch:{ all -> 0x00ad }
            throw r0     // Catch:{ all -> 0x00ad }
        L_0x0068:
            r16 = r8
            r11 = 0
        L_0x006b:
            int r14 = r14 - r12
            r9.discardExact(r14)     // Catch:{ all -> 0x00ad }
            r4 = 0
            goto L_0x007b
        L_0x0071:
            r16 = r8
            r11 = 0
            int r13 = r13 - r12
            r9.discardExact(r13)     // Catch:{ all -> 0x00ad }
            r8 = r16
            r4 = 1
        L_0x007b:
            int r6 = r9.getWritePosition()     // Catch:{ all -> 0x00ad }
            int r12 = r9.getReadPosition()     // Catch:{ all -> 0x00ad }
            int r6 = r6 - r12
            int r10 = r10 - r6
            if (r10 <= 0) goto L_0x008d
            r9.rewind(r10)     // Catch:{ all -> 0x00ad }
            io.ktor.utils.io.core.OutputKt.writeFully(r3, r9, r10)     // Catch:{ all -> 0x00ad }
        L_0x008d:
            if (r4 != 0) goto L_0x0093
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
            goto L_0x0099
        L_0x0093:
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r5)     // Catch:{ all -> 0x00aa }
            if (r5 != 0) goto L_0x00a7
        L_0x0099:
            r6 = r8
        L_0x009a:
            if (r6 != 0) goto L_0x00a6
            boolean r4 = r18.getEndOfInput()
            if (r4 != 0) goto L_0x00a6
            int r7 = readUTF8UntilDelimiterToSlowUtf8((io.ktor.utils.io.core.Input) r1, (io.ktor.utils.io.core.Output) r3, (java.lang.String) r0, (int) r2, (int) r7)
        L_0x00a6:
            return r7
        L_0x00a7:
            r4 = 1
            goto L_0x0015
        L_0x00aa:
            r0 = move-exception
            r4 = 0
            goto L_0x00af
        L_0x00ad:
            r0 = move-exception
            r4 = 1
        L_0x00af:
            if (r4 == 0) goto L_0x00b4
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
        L_0x00b4:
            goto L_0x00b6
        L_0x00b5:
            throw r0
        L_0x00b6:
            goto L_0x00b5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTFUntilDelimiterToSlowAscii(io.ktor.utils.io.core.Input, java.lang.String, int, io.ktor.utils.io.core.Output):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x01e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input r17, io.ktor.utils.io.core.Output r18, java.lang.String r19, int r20, int r21) {
        /*
            r1 = r17
            r0 = r20
            r2 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r2)
            if (r3 != 0) goto L_0x0011
            r3 = r21
            r1 = 1
        L_0x000e:
            r9 = 1
            goto L_0x01d1
        L_0x0011:
            r4 = r3
            r5 = 1
            r6 = 1
            r3 = r21
        L_0x0016:
            r7 = r4
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7     // Catch:{ all -> 0x01e0 }
            int r8 = r7.getWritePosition()     // Catch:{ all -> 0x01e0 }
            int r7 = r7.getReadPosition()     // Catch:{ all -> 0x01e0 }
            int r8 = r8 - r7
            if (r8 < r5) goto L_0x0195
            r5 = r4
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0185 }
            int r6 = r5.getWritePosition()     // Catch:{ all -> 0x0185 }
            int r8 = r5.getReadPosition()     // Catch:{ all -> 0x0185 }
            int r6 = r6 - r8
            java.nio.ByteBuffer r8 = r5.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0185 }
            int r9 = r5.getReadPosition()     // Catch:{ all -> 0x0185 }
            int r10 = r5.getWritePosition()     // Catch:{ all -> 0x0185 }
            r11 = r9
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0040:
            if (r11 >= r10) goto L_0x014e
            byte r15 = r8.get(r11)     // Catch:{ all -> 0x0185 }
            r2 = r15 & 255(0xff, float:3.57E-43)
            r7 = r15 & 128(0x80, float:1.794E-43)
            r16 = r8
            r8 = 2
            if (r7 != 0) goto L_0x007f
            if (r12 != 0) goto L_0x0076
            char r2 = (char) r2     // Catch:{ all -> 0x0185 }
            r7 = r19
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ all -> 0x0185 }
            r1 = 0
            r15 = 0
            boolean r2 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r7, (char) r2, (boolean) r1, (int) r8, (java.lang.Object) r15)     // Catch:{ all -> 0x0185 }
            if (r2 == 0) goto L_0x0060
            r1 = 0
            goto L_0x0065
        L_0x0060:
            if (r3 == r0) goto L_0x006d
            int r3 = r3 + 1
            r1 = 1
        L_0x0065:
            if (r1 != 0) goto L_0x0144
            int r11 = r11 - r9
            r5.discardExact(r11)     // Catch:{ all -> 0x0185 }
        L_0x006b:
            r1 = -1
            goto L_0x00a1
        L_0x006d:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0185 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0185 }
            r0.<init>()     // Catch:{ all -> 0x0185 }
            throw r0     // Catch:{ all -> 0x0185 }
        L_0x0076:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedByteCount(r12)     // Catch:{ all -> 0x0185 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0185 }
            r0.<init>()     // Catch:{ all -> 0x0185 }
            throw r0     // Catch:{ all -> 0x0185 }
        L_0x007f:
            if (r12 != 0) goto L_0x00a9
            r1 = 128(0x80, float:1.794E-43)
            r13 = r2
            r2 = 1
        L_0x0085:
            r7 = 7
            if (r2 >= r7) goto L_0x0096
            r7 = r13 & r1
            if (r7 == 0) goto L_0x0096
            r7 = r1 ^ -1
            r13 = r13 & r7
            int r1 = r1 >> 1
            int r12 = r12 + 1
            int r2 = r2 + 1
            goto L_0x0085
        L_0x0096:
            int r1 = r12 + -1
            int r2 = r10 - r11
            if (r12 <= r2) goto L_0x00a4
            int r11 = r11 - r9
            r5.discardExact(r11)     // Catch:{ all -> 0x0185 }
            r1 = r12
        L_0x00a1:
            r7 = 0
            goto L_0x0154
        L_0x00a4:
            r14 = r12
            r7 = 0
            r12 = r1
            goto L_0x0145
        L_0x00a9:
            int r1 = r13 << 6
            r2 = r15 & 127(0x7f, float:1.78E-43)
            r13 = r1 | r2
            int r12 = r12 + -1
            if (r12 != 0) goto L_0x0144
            boolean r1 = io.ktor.utils.io.core.internal.UTF8Kt.isBmpCodePoint(r13)     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x00e2
            char r1 = (char) r13     // Catch:{ all -> 0x0185 }
            r2 = r19
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x0185 }
            r7 = 0
            r13 = 0
            boolean r1 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r2, (char) r1, (boolean) r13, (int) r8, (java.lang.Object) r7)     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x00c8
            r1 = 0
            goto L_0x00cd
        L_0x00c8:
            if (r3 == r0) goto L_0x00d9
            int r3 = r3 + 1
            r1 = 1
        L_0x00cd:
            if (r1 != 0) goto L_0x00d7
            int r11 = r11 - r9
            int r11 = r11 - r14
            r1 = 1
            int r11 = r11 + r1
            r5.discardExact(r11)     // Catch:{ all -> 0x0185 }
            goto L_0x006b
        L_0x00d7:
            r7 = 0
            goto L_0x011d
        L_0x00d9:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0185 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0185 }
            r0.<init>()     // Catch:{ all -> 0x0185 }
            throw r0     // Catch:{ all -> 0x0185 }
        L_0x00e2:
            boolean r1 = io.ktor.utils.io.core.internal.UTF8Kt.isValidCodePoint(r13)     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x013b
            int r1 = io.ktor.utils.io.core.internal.UTF8Kt.highSurrogate(r13)     // Catch:{ all -> 0x0185 }
            char r1 = (char) r1     // Catch:{ all -> 0x0185 }
            r2 = r19
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x0185 }
            r7 = 0
            r15 = 0
            boolean r1 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r2, (char) r1, (boolean) r15, (int) r8, (java.lang.Object) r7)     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x00fb
            r1 = 0
            goto L_0x0100
        L_0x00fb:
            if (r3 == r0) goto L_0x0132
            int r3 = r3 + 1
            r1 = 1
        L_0x0100:
            if (r1 == 0) goto L_0x0128
            int r1 = io.ktor.utils.io.core.internal.UTF8Kt.lowSurrogate(r13)     // Catch:{ all -> 0x0185 }
            char r1 = (char) r1     // Catch:{ all -> 0x0185 }
            r2 = r19
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x0185 }
            r7 = 0
            r13 = 0
            boolean r1 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r2, (char) r1, (boolean) r7, (int) r8, (java.lang.Object) r13)     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x0115
            r1 = 0
            goto L_0x011a
        L_0x0115:
            if (r3 == r0) goto L_0x011f
            int r3 = r3 + 1
            r1 = 1
        L_0x011a:
            if (r1 != 0) goto L_0x011d
            goto L_0x0129
        L_0x011d:
            r13 = 0
            goto L_0x0145
        L_0x011f:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0185 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0185 }
            r0.<init>()     // Catch:{ all -> 0x0185 }
            throw r0     // Catch:{ all -> 0x0185 }
        L_0x0128:
            r7 = 0
        L_0x0129:
            int r11 = r11 - r9
            int r11 = r11 - r14
            r1 = 1
            int r11 = r11 + r1
            r5.discardExact(r11)     // Catch:{ all -> 0x0185 }
            r1 = -1
            goto L_0x0154
        L_0x0132:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0185 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0185 }
            r0.<init>()     // Catch:{ all -> 0x0185 }
            throw r0     // Catch:{ all -> 0x0185 }
        L_0x013b:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedCodePoint(r13)     // Catch:{ all -> 0x0185 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0185 }
            r0.<init>()     // Catch:{ all -> 0x0185 }
            throw r0     // Catch:{ all -> 0x0185 }
        L_0x0144:
            r7 = 0
        L_0x0145:
            int r11 = r11 + 1
            r2 = 1
            r1 = r17
            r8 = r16
            goto L_0x0040
        L_0x014e:
            r7 = 0
            int r10 = r10 - r9
            r5.discardExact(r10)     // Catch:{ all -> 0x0185 }
            r1 = 0
        L_0x0154:
            int r2 = r5.getWritePosition()     // Catch:{ all -> 0x0185 }
            int r8 = r5.getReadPosition()     // Catch:{ all -> 0x0185 }
            int r2 = r2 - r8
            int r6 = r6 - r2
            if (r6 <= 0) goto L_0x0169
            r5.rewind(r6)     // Catch:{ all -> 0x0185 }
            r2 = r18
            io.ktor.utils.io.core.OutputKt.writeFully(r2, r5, r6)     // Catch:{ all -> 0x0185 }
            goto L_0x016b
        L_0x0169:
            r2 = r18
        L_0x016b:
            r5 = -1
            if (r1 != r5) goto L_0x0170
            r5 = 0
            goto L_0x0176
        L_0x0170:
            r5 = 1
            int r1 = kotlin.ranges.RangesKt.coerceAtLeast((int) r1, (int) r5)     // Catch:{ all -> 0x0185 }
            r5 = r1
        L_0x0176:
            r1 = r4
            io.ktor.utils.io.core.Buffer r1 = (io.ktor.utils.io.core.Buffer) r1     // Catch:{ all -> 0x0190 }
            int r6 = r1.getWritePosition()     // Catch:{ all -> 0x0190 }
            int r1 = r1.getReadPosition()     // Catch:{ all -> 0x0190 }
            int r8 = r6 - r1
            r6 = r5
            goto L_0x0198
        L_0x0185:
            r0 = move-exception
            r1 = r4
            io.ktor.utils.io.core.Buffer r1 = (io.ktor.utils.io.core.Buffer) r1     // Catch:{ all -> 0x0190 }
            r1.getWritePosition()     // Catch:{ all -> 0x0190 }
            r1.getReadPosition()     // Catch:{ all -> 0x0190 }
            throw r0     // Catch:{ all -> 0x0190 }
        L_0x0190:
            r0 = move-exception
            r2 = 1
            r1 = r17
            goto L_0x01e3
        L_0x0195:
            r2 = r18
            r7 = 0
        L_0x0198:
            if (r8 != 0) goto L_0x01a4
            r1 = r17
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r4)     // Catch:{ all -> 0x01a1 }
            goto L_0x01c2
        L_0x01a1:
            r0 = move-exception
            r2 = 0
            goto L_0x01e3
        L_0x01a4:
            r1 = r17
            if (r8 < r5) goto L_0x01bb
            r8 = r4
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x01a1 }
            int r9 = r8.getCapacity()     // Catch:{ all -> 0x01a1 }
            int r8 = r8.getLimit()     // Catch:{ all -> 0x01a1 }
            int r9 = r9 - r8
            r8 = 8
            if (r9 >= r8) goto L_0x01b9
            goto L_0x01bb
        L_0x01b9:
            r8 = r4
            goto L_0x01c2
        L_0x01bb:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r4)     // Catch:{ all -> 0x01a1 }
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r5)     // Catch:{ all -> 0x01a1 }
        L_0x01c2:
            if (r8 != 0) goto L_0x01c5
            goto L_0x01c9
        L_0x01c5:
            r4 = r8
            if (r5 > 0) goto L_0x01dd
            r7 = 1
        L_0x01c9:
            if (r7 == 0) goto L_0x01ce
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r4)
        L_0x01ce:
            r1 = r6
            goto L_0x000e
        L_0x01d1:
            if (r1 > r9) goto L_0x01d4
            return r3
        L_0x01d4:
            prematureEndOfStream((int) r1)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x01dd:
            r2 = 1
            goto L_0x0016
        L_0x01e0:
            r0 = move-exception
            r9 = 1
            r2 = 1
        L_0x01e3:
            if (r2 == 0) goto L_0x01e8
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r4)
        L_0x01e8:
            goto L_0x01ea
        L_0x01e9:
            throw r0
        L_0x01ea:
            goto L_0x01e9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input, io.ktor.utils.io.core.Output, java.lang.String, int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:124:0x01d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input r17, java.lang.Appendable r18, java.lang.String r19, int r20, int r21) {
        /*
            r1 = r17
            r0 = r18
            r2 = r20
            r3 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r3)
            if (r4 != 0) goto L_0x0013
            r4 = r21
            r1 = 1
        L_0x0010:
            r9 = 1
            goto L_0x01be
        L_0x0013:
            r5 = r4
            r6 = 1
            r7 = 1
            r4 = r21
        L_0x0018:
            r8 = r5
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x01cd }
            int r9 = r8.getWritePosition()     // Catch:{ all -> 0x01cd }
            int r8 = r8.getReadPosition()     // Catch:{ all -> 0x01cd }
            int r9 = r9 - r8
            if (r9 < r6) goto L_0x0184
            r6 = r5
            io.ktor.utils.io.core.Buffer r6 = (io.ktor.utils.io.core.Buffer) r6     // Catch:{ all -> 0x0174 }
            java.nio.ByteBuffer r7 = r6.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0174 }
            int r9 = r6.getReadPosition()     // Catch:{ all -> 0x0174 }
            int r10 = r6.getWritePosition()     // Catch:{ all -> 0x0174 }
            r11 = r9
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0039:
            if (r11 >= r10) goto L_0x0154
            byte r15 = r7.get(r11)     // Catch:{ all -> 0x0174 }
            r3 = r15 & 255(0xff, float:3.57E-43)
            r8 = r15 & 128(0x80, float:1.794E-43)
            r16 = r7
            r7 = 2
            if (r8 != 0) goto L_0x007b
            if (r12 != 0) goto L_0x0072
            char r3 = (char) r3     // Catch:{ all -> 0x0174 }
            r8 = r19
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ all -> 0x0174 }
            r1 = 0
            r15 = 0
            boolean r7 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r8, (char) r3, (boolean) r1, (int) r7, (java.lang.Object) r15)     // Catch:{ all -> 0x0174 }
            if (r7 == 0) goto L_0x0059
            r1 = 0
            goto L_0x0061
        L_0x0059:
            if (r4 == r2) goto L_0x0069
            int r4 = r4 + 1
            r0.append(r3)     // Catch:{ all -> 0x0174 }
            r1 = 1
        L_0x0061:
            if (r1 != 0) goto L_0x014a
            int r11 = r11 - r9
            r6.discardExact(r11)     // Catch:{ all -> 0x0174 }
        L_0x0067:
            r1 = -1
            goto L_0x009d
        L_0x0069:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0174 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0174 }
            r0.<init>()     // Catch:{ all -> 0x0174 }
            throw r0     // Catch:{ all -> 0x0174 }
        L_0x0072:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedByteCount(r12)     // Catch:{ all -> 0x0174 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0174 }
            r0.<init>()     // Catch:{ all -> 0x0174 }
            throw r0     // Catch:{ all -> 0x0174 }
        L_0x007b:
            if (r12 != 0) goto L_0x00a6
            r1 = 128(0x80, float:1.794E-43)
            r13 = r3
            r3 = 1
        L_0x0081:
            r7 = 7
            if (r3 >= r7) goto L_0x0092
            r7 = r13 & r1
            if (r7 == 0) goto L_0x0092
            r7 = r1 ^ -1
            r13 = r13 & r7
            int r1 = r1 >> 1
            int r12 = r12 + 1
            int r3 = r3 + 1
            goto L_0x0081
        L_0x0092:
            int r1 = r12 + -1
            int r3 = r10 - r11
            if (r12 <= r3) goto L_0x00a1
            int r11 = r11 - r9
            r6.discardExact(r11)     // Catch:{ all -> 0x0174 }
            r1 = r12
        L_0x009d:
            r3 = -1
            r8 = 0
            goto L_0x015b
        L_0x00a1:
            r14 = r12
            r8 = 0
            r12 = r1
            goto L_0x014b
        L_0x00a6:
            int r1 = r13 << 6
            r3 = r15 & 127(0x7f, float:1.78E-43)
            r13 = r1 | r3
            int r12 = r12 + -1
            if (r12 != 0) goto L_0x014a
            boolean r1 = io.ktor.utils.io.core.internal.UTF8Kt.isBmpCodePoint(r13)     // Catch:{ all -> 0x0174 }
            if (r1 == 0) goto L_0x00e2
            char r1 = (char) r13     // Catch:{ all -> 0x0174 }
            r3 = r19
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x0174 }
            r8 = 0
            r13 = 0
            boolean r3 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r3, (char) r1, (boolean) r13, (int) r7, (java.lang.Object) r8)     // Catch:{ all -> 0x0174 }
            if (r3 == 0) goto L_0x00c5
            r1 = 0
            goto L_0x00cd
        L_0x00c5:
            if (r4 == r2) goto L_0x00d9
            int r4 = r4 + 1
            r0.append(r1)     // Catch:{ all -> 0x0174 }
            r1 = 1
        L_0x00cd:
            if (r1 != 0) goto L_0x00d7
            int r11 = r11 - r9
            int r11 = r11 - r14
            r1 = 1
            int r11 = r11 + r1
            r6.discardExact(r11)     // Catch:{ all -> 0x0174 }
            goto L_0x0067
        L_0x00d7:
            r8 = 0
            goto L_0x0123
        L_0x00d9:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0174 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0174 }
            r0.<init>()     // Catch:{ all -> 0x0174 }
            throw r0     // Catch:{ all -> 0x0174 }
        L_0x00e2:
            boolean r1 = io.ktor.utils.io.core.internal.UTF8Kt.isValidCodePoint(r13)     // Catch:{ all -> 0x0174 }
            if (r1 == 0) goto L_0x0141
            int r1 = io.ktor.utils.io.core.internal.UTF8Kt.highSurrogate(r13)     // Catch:{ all -> 0x0174 }
            char r1 = (char) r1     // Catch:{ all -> 0x0174 }
            r3 = r19
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x0174 }
            r8 = 0
            r15 = 0
            boolean r3 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r3, (char) r1, (boolean) r15, (int) r7, (java.lang.Object) r8)     // Catch:{ all -> 0x0174 }
            if (r3 == 0) goto L_0x00fb
            r1 = 0
            goto L_0x0103
        L_0x00fb:
            if (r4 == r2) goto L_0x0138
            int r4 = r4 + 1
            r0.append(r1)     // Catch:{ all -> 0x0174 }
            r1 = 1
        L_0x0103:
            if (r1 == 0) goto L_0x012e
            int r1 = io.ktor.utils.io.core.internal.UTF8Kt.lowSurrogate(r13)     // Catch:{ all -> 0x0174 }
            char r1 = (char) r1     // Catch:{ all -> 0x0174 }
            r3 = r19
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x0174 }
            r8 = 0
            r13 = 0
            boolean r3 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r3, (char) r1, (boolean) r8, (int) r7, (java.lang.Object) r13)     // Catch:{ all -> 0x0174 }
            if (r3 == 0) goto L_0x0118
            r1 = 0
            goto L_0x0120
        L_0x0118:
            if (r4 == r2) goto L_0x0125
            int r4 = r4 + 1
            r0.append(r1)     // Catch:{ all -> 0x0174 }
            r1 = 1
        L_0x0120:
            if (r1 != 0) goto L_0x0123
            goto L_0x012f
        L_0x0123:
            r13 = 0
            goto L_0x014b
        L_0x0125:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0174 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0174 }
            r0.<init>()     // Catch:{ all -> 0x0174 }
            throw r0     // Catch:{ all -> 0x0174 }
        L_0x012e:
            r8 = 0
        L_0x012f:
            int r11 = r11 - r9
            int r11 = r11 - r14
            r1 = 1
            int r11 = r11 + r1
            r6.discardExact(r11)     // Catch:{ all -> 0x0174 }
            r1 = -1
            goto L_0x015a
        L_0x0138:
            bufferLimitExceeded(r20)     // Catch:{ all -> 0x0174 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0174 }
            r0.<init>()     // Catch:{ all -> 0x0174 }
            throw r0     // Catch:{ all -> 0x0174 }
        L_0x0141:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedCodePoint(r13)     // Catch:{ all -> 0x0174 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0174 }
            r0.<init>()     // Catch:{ all -> 0x0174 }
            throw r0     // Catch:{ all -> 0x0174 }
        L_0x014a:
            r8 = 0
        L_0x014b:
            int r11 = r11 + 1
            r3 = 1
            r1 = r17
            r7 = r16
            goto L_0x0039
        L_0x0154:
            r8 = 0
            int r10 = r10 - r9
            r6.discardExact(r10)     // Catch:{ all -> 0x0174 }
            r1 = 0
        L_0x015a:
            r3 = -1
        L_0x015b:
            if (r1 != r3) goto L_0x015f
            r6 = 0
            goto L_0x0165
        L_0x015f:
            r3 = 1
            int r1 = kotlin.ranges.RangesKt.coerceAtLeast((int) r1, (int) r3)     // Catch:{ all -> 0x0174 }
            r6 = r1
        L_0x0165:
            r1 = r5
            io.ktor.utils.io.core.Buffer r1 = (io.ktor.utils.io.core.Buffer) r1     // Catch:{ all -> 0x017f }
            int r3 = r1.getWritePosition()     // Catch:{ all -> 0x017f }
            int r1 = r1.getReadPosition()     // Catch:{ all -> 0x017f }
            int r9 = r3 - r1
            r7 = r6
            goto L_0x0185
        L_0x0174:
            r0 = move-exception
            r1 = r5
            io.ktor.utils.io.core.Buffer r1 = (io.ktor.utils.io.core.Buffer) r1     // Catch:{ all -> 0x017f }
            r1.getWritePosition()     // Catch:{ all -> 0x017f }
            r1.getReadPosition()     // Catch:{ all -> 0x017f }
            throw r0     // Catch:{ all -> 0x017f }
        L_0x017f:
            r0 = move-exception
            r3 = 1
            r1 = r17
            goto L_0x01d0
        L_0x0184:
            r8 = 0
        L_0x0185:
            if (r9 != 0) goto L_0x0191
            r1 = r17
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r5)     // Catch:{ all -> 0x018e }
            goto L_0x01af
        L_0x018e:
            r0 = move-exception
            r3 = 0
            goto L_0x01d0
        L_0x0191:
            r1 = r17
            if (r9 < r6) goto L_0x01a8
            r3 = r5
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x018e }
            int r9 = r3.getCapacity()     // Catch:{ all -> 0x018e }
            int r3 = r3.getLimit()     // Catch:{ all -> 0x018e }
            int r9 = r9 - r3
            r3 = 8
            if (r9 >= r3) goto L_0x01a6
            goto L_0x01a8
        L_0x01a6:
            r3 = r5
            goto L_0x01af
        L_0x01a8:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)     // Catch:{ all -> 0x018e }
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r6)     // Catch:{ all -> 0x018e }
        L_0x01af:
            if (r3 != 0) goto L_0x01b2
            goto L_0x01b6
        L_0x01b2:
            r5 = r3
            if (r6 > 0) goto L_0x01ca
            r8 = 1
        L_0x01b6:
            if (r8 == 0) goto L_0x01bb
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
        L_0x01bb:
            r1 = r7
            goto L_0x0010
        L_0x01be:
            if (r1 > r9) goto L_0x01c1
            return r4
        L_0x01c1:
            prematureEndOfStream((int) r1)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x01ca:
            r3 = 1
            goto L_0x0018
        L_0x01cd:
            r0 = move-exception
            r9 = 1
            r3 = 1
        L_0x01d0:
            if (r3 == 0) goto L_0x01d5
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
        L_0x01d5:
            goto L_0x01d7
        L_0x01d6:
            throw r0
        L_0x01d7:
            goto L_0x01d6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input, java.lang.Appendable, java.lang.String, int, int):int");
    }
}
