package j$.util;

import com.google.common.base.Ascii;
import io.ktor.util.date.GMTDateParser;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Utf8;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

public class Base64 {

    public static class Encoder {
        private static final byte[] CRLF;
        static final Encoder RFC2045;
        static final Encoder RFC4648 = new Encoder(false, (byte[]) null, -1, true);
        static final Encoder RFC4648_URLSAFE = new Encoder(true, (byte[]) null, -1, true);
        private static final char[] toBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, GMTDateParser.MONTH, 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', GMTDateParser.YEAR, Matrix.MATRIX_TYPE_ZERO, 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f', 'g', GMTDateParser.HOURS, 'i', 'j', 'k', 'l', GMTDateParser.MINUTES, 'n', 'o', 'p', 'q', 'r', GMTDateParser.SECONDS, 't', AbstractJsonLexerKt.UNICODE_ESC, 'v', 'w', 'x', 'y', GMTDateParser.ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] toBase64URL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, GMTDateParser.MONTH, 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', GMTDateParser.YEAR, Matrix.MATRIX_TYPE_ZERO, 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f', 'g', GMTDateParser.HOURS, 'i', 'j', 'k', 'l', GMTDateParser.MINUTES, 'n', 'o', 'p', 'q', 'r', GMTDateParser.SECONDS, 't', AbstractJsonLexerKt.UNICODE_ESC, 'v', 'w', 'x', 'y', GMTDateParser.ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private final boolean doPadding;
        private final boolean isURL;
        private final int linemax;
        private final byte[] newline;

        static {
            byte[] bArr = {13, 10};
            CRLF = bArr;
            RFC2045 = new Encoder(false, bArr, 76, true);
        }

        private Encoder(boolean z, byte[] bArr, int i, boolean z2) {
            this.isURL = z;
            this.newline = bArr;
            this.linemax = i;
            this.doPadding = z2;
        }

        private int encode0(byte[] bArr, int i, int i2, byte[] bArr2) {
            int i3 = i2;
            char[] cArr = this.isURL ? toBase64URL : toBase64;
            int i4 = ((i3 - i) / 3) * 3;
            int i5 = i + i4;
            int i6 = this.linemax;
            if (i6 > 0 && i4 > (i6 / 4) * 3) {
                i4 = (i6 / 4) * 3;
            }
            int i7 = i4;
            int i8 = i;
            int i9 = 0;
            while (i8 < i5) {
                int min = Math.min(i8 + i7, i5);
                encodeBlock(bArr, i8, min, bArr2, i9, this.isURL);
                int i10 = ((min - i8) / 3) * 4;
                i9 += i10;
                if (i10 == this.linemax && min < i3) {
                    byte[] bArr3 = this.newline;
                    int length = bArr3.length;
                    int i11 = 0;
                    while (i11 < length) {
                        bArr2[i9] = bArr3[i11];
                        i11++;
                        i9++;
                    }
                }
                i8 = min;
            }
            if (i8 >= i3) {
                return i9;
            }
            int i12 = i8 + 1;
            byte b = bArr[i8] & 255;
            int i13 = i9 + 1;
            bArr2[i9] = (byte) cArr[b >> 2];
            if (i12 == i3) {
                int i14 = i9 + 2;
                bArr2[i13] = (byte) cArr[(b << 4) & 63];
                if (!this.doPadding) {
                    return i14;
                }
                int i15 = i9 + 3;
                bArr2[i14] = 61;
                int i16 = i9 + 4;
                bArr2[i15] = 61;
                return i16;
            }
            byte b2 = bArr[i12] & 255;
            bArr2[i13] = (byte) cArr[((b << 4) & 63) | (b2 >> 4)];
            int i17 = i9 + 3;
            bArr2[i9 + 2] = (byte) cArr[(b2 << 2) & 63];
            if (!this.doPadding) {
                return i17;
            }
            int i18 = i9 + 4;
            bArr2[i17] = 61;
            return i18;
        }

        private void encodeBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3, boolean z) {
            char[] cArr = z ? toBase64URL : toBase64;
            while (i < i2) {
                int i4 = (bArr[i] & 255) << Ascii.DLE;
                int i5 = i + 2;
                i += 3;
                byte b = ((bArr[i + 1] & 255) << 8) | i4 | (bArr[i5] & 255);
                bArr2[i3] = (byte) cArr[(b >>> Ascii.DC2) & 63];
                bArr2[i3 + 1] = (byte) cArr[(b >>> Ascii.FF) & 63];
                int i6 = i3 + 3;
                bArr2[i3 + 2] = (byte) cArr[(b >>> 6) & 63];
                i3 += 4;
                bArr2[i6] = (byte) cArr[b & Utf8.REPLACEMENT_BYTE];
            }
        }

        private final int outLength(int i) {
            int i2;
            if (this.doPadding) {
                i2 = ((i + 2) / 3) * 4;
            } else {
                int i3 = i % 3;
                i2 = ((i / 3) * 4) + (i3 == 0 ? 0 : i3 + 1);
            }
            int i4 = this.linemax;
            return i4 > 0 ? i2 + (((i2 - 1) / i4) * this.newline.length) : i2;
        }

        public byte[] encode(byte[] bArr) {
            int outLength = outLength(bArr.length);
            byte[] bArr2 = new byte[outLength];
            int encode0 = encode0(bArr, 0, bArr.length, bArr2);
            return encode0 != outLength ? Arrays.copyOf(bArr2, encode0) : bArr2;
        }

        public String encodeToString(byte[] bArr) {
            byte[] encode = encode(bArr);
            return new String(encode, 0, 0, encode.length);
        }
    }

    public static Encoder getEncoder() {
        return Encoder.RFC4648;
    }
}
