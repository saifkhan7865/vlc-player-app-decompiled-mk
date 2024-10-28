package io.netty.handler.codec.http;

import com.google.common.primitives.SignedBytes;
import io.ktor.util.date.GMTDateParser;
import io.netty.util.AsciiString;
import kotlin.text.Typography;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

public final class HttpHeaderValidationUtil {
    private static final long TOKEN_CHARS_HIGH;
    private static final long TOKEN_CHARS_LOW;

    private HttpHeaderValidationUtil() {
    }

    public static boolean isConnectionHeader(CharSequence charSequence, boolean z) {
        int length = charSequence.length();
        if (length != 2) {
            if (length == 7) {
                return AsciiString.contentEqualsIgnoreCase(charSequence, HttpHeaderNames.UPGRADE);
            }
            if (length != 10) {
                if (length == 16) {
                    return AsciiString.contentEqualsIgnoreCase(charSequence, HttpHeaderNames.PROXY_CONNECTION);
                }
                if (length != 17) {
                    return false;
                }
                return AsciiString.contentEqualsIgnoreCase(charSequence, HttpHeaderNames.TRANSFER_ENCODING);
            } else if (AsciiString.contentEqualsIgnoreCase(charSequence, HttpHeaderNames.CONNECTION) || AsciiString.contentEqualsIgnoreCase(charSequence, HttpHeaderNames.KEEP_ALIVE)) {
                return true;
            } else {
                return false;
            }
        } else if (z) {
            return false;
        } else {
            return AsciiString.contentEqualsIgnoreCase(charSequence, HttpHeaderNames.TE);
        }
    }

    public static boolean isTeNotTrailers(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence.length() != 2 || !AsciiString.contentEqualsIgnoreCase(charSequence, HttpHeaderNames.TE) || AsciiString.contentEqualsIgnoreCase(charSequence2, HttpHeaderValues.TRAILERS)) {
            return false;
        }
        return true;
    }

    public static int validateValidHeaderValue(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            return -1;
        }
        if (charSequence instanceof AsciiString) {
            return verifyValidHeaderValueAsciiString((AsciiString) charSequence);
        }
        return verifyValidHeaderValueCharSequence(charSequence);
    }

    private static int verifyValidHeaderValueAsciiString(AsciiString asciiString) {
        byte[] array = asciiString.array();
        int arrayOffset = asciiString.arrayOffset();
        byte b = array[arrayOffset] & 255;
        if (b < 33 || b == Byte.MAX_VALUE) {
            return 0;
        }
        int length = asciiString.length();
        for (int i = arrayOffset + 1; i < length; i++) {
            byte b2 = array[i] & 255;
            if ((b2 < 32 && b2 != 9) || b2 == Byte.MAX_VALUE) {
                return i - arrayOffset;
            }
        }
        return -1;
    }

    private static int verifyValidHeaderValueCharSequence(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        if (charAt < '!' || charAt == 127) {
            return 0;
        }
        int length = charSequence.length();
        for (int i = 1; i < length; i++) {
            char charAt2 = charSequence.charAt(i);
            if ((charAt2 < ' ' && charAt2 != 9) || charAt2 == 127) {
                return i;
            }
        }
        return -1;
    }

    public static int validateToken(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return validateAsciiStringToken((AsciiString) charSequence);
        }
        return validateCharSequenceToken(charSequence);
    }

    private static int validateAsciiStringToken(AsciiString asciiString) {
        byte[] array = asciiString.array();
        int arrayOffset = asciiString.arrayOffset() + asciiString.length();
        for (int arrayOffset2 = asciiString.arrayOffset(); arrayOffset2 < arrayOffset; arrayOffset2++) {
            if (!BitSet128.contains(array[arrayOffset2], TOKEN_CHARS_HIGH, TOKEN_CHARS_LOW)) {
                return arrayOffset2 - asciiString.arrayOffset();
            }
        }
        return -1;
    }

    private static int validateCharSequenceToken(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!BitSet128.contains((byte) charSequence.charAt(i), TOKEN_CHARS_HIGH, TOKEN_CHARS_LOW)) {
                return i;
            }
        }
        return -1;
    }

    static {
        BitSet128 bits = new BitSet128().range('0', '9').range('a', GMTDateParser.ZONE).range('A', Matrix.MATRIX_TYPE_ZERO).bits('-', '.', '_', '~').bits('!', '#', Typography.dollar, '%', Typography.amp, '\'', GMTDateParser.ANY, '+', '^', '`', '|');
        TOKEN_CHARS_HIGH = bits.high();
        TOKEN_CHARS_LOW = bits.low();
    }

    private static final class BitSet128 {
        private long high;
        private long low;

        static boolean contains(byte b, long j, long j2) {
            if (b < 0) {
                return false;
            }
            return b < 64 ? 0 != ((1 << b) & j2) : 0 != (j & (1 << (b - SignedBytes.MAX_POWER_OF_TWO)));
        }

        private BitSet128() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: char} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: char} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: char} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public io.netty.handler.codec.http.HttpHeaderValidationUtil.BitSet128 range(char r6, char r7) {
            /*
                r5 = this;
            L_0x0000:
                if (r6 > r7) goto L_0x001a
                r0 = 1
                r2 = 64
                if (r6 >= r2) goto L_0x000f
                long r2 = r5.low
                long r0 = r0 << r6
                long r0 = r0 | r2
                r5.low = r0
                goto L_0x0017
            L_0x000f:
                long r2 = r5.high
                int r4 = r6 + -64
                long r0 = r0 << r4
                long r0 = r0 | r2
                r5.high = r0
            L_0x0017:
                int r6 = r6 + 1
                goto L_0x0000
            L_0x001a:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.HttpHeaderValidationUtil.BitSet128.range(char, char):io.netty.handler.codec.http.HttpHeaderValidationUtil$BitSet128");
        }

        /* access modifiers changed from: package-private */
        public BitSet128 bits(char... cArr) {
            for (char c : cArr) {
                if (c < '@') {
                    this.low = (1 << c) | this.low;
                } else {
                    this.high = (1 << (c - '@')) | this.high;
                }
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public long high() {
            return this.high;
        }

        /* access modifiers changed from: package-private */
        public long low() {
            return this.low;
        }
    }
}
