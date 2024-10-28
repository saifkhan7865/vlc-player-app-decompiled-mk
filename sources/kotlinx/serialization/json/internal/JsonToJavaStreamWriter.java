package kotlinx.serialization.json.internal;

import java.io.OutputStream;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.BERTags;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0011\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\nH\bJ\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002J\b\u0010\u0016\u001a\u00020\fH\u0016J\t\u0010\u0017\u001a\u00020\nH\bJ\u0011\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\nH\bJ\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0018\u0010\"\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010#\u001a\u00020\nH\u0002J\u0010\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/serialization/json/internal/JsonToJavaStreamWriter;", "Lkotlinx/serialization/json/internal/JsonWriter;", "stream", "Ljava/io/OutputStream;", "(Ljava/io/OutputStream;)V", "buffer", "", "charArray", "", "indexInBuffer", "", "appendStringSlowPath", "", "currentSize", "string", "", "ensure", "bytesCount", "ensureTotalCapacity", "oldSize", "additional", "flush", "release", "rest", "write", "byte", "text", "writeChar", "char", "", "writeLong", "value", "", "writeQuoted", "writeUtf8", "count", "writeUtf8CodePoint", "codePoint", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JvmJsonStreams.kt */
public final class JsonToJavaStreamWriter implements JsonWriter {
    private final byte[] buffer = ByteArrayPool.INSTANCE.take();
    private char[] charArray = CharArrayPool.INSTANCE.take();
    private int indexInBuffer;
    private final OutputStream stream;

    public JsonToJavaStreamWriter(OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(outputStream, "stream");
        this.stream = outputStream;
    }

    public void writeLong(long j) {
        write(String.valueOf(j));
    }

    public void writeChar(char c) {
        writeUtf8CodePoint(c);
    }

    public void write(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        int length = str.length();
        ensureTotalCapacity(0, length);
        str.getChars(0, length, this.charArray, 0);
        writeUtf8(this.charArray, length);
    }

    public void writeQuoted(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        ensureTotalCapacity(0, str.length() + 2);
        char[] cArr = this.charArray;
        cArr[0] = '\"';
        int length = str.length();
        int i = 1;
        str.getChars(0, length, cArr, 1);
        int i2 = length + 1;
        while (i < i2) {
            char c = cArr[i];
            if (c >= StringOpsKt.getESCAPE_MARKERS().length || StringOpsKt.getESCAPE_MARKERS()[c] == 0) {
                i++;
            } else {
                appendStringSlowPath(i, str);
                return;
            }
        }
        cArr[i2] = '\"';
        writeUtf8(cArr, length + 2);
        flush();
    }

    private final void appendStringSlowPath(int i, String str) {
        int i2;
        int length = str.length();
        for (int i3 = i - 1; i3 < length; i3++) {
            int ensureTotalCapacity = ensureTotalCapacity(i, 2);
            char charAt = str.charAt(i3);
            if (charAt < StringOpsKt.getESCAPE_MARKERS().length) {
                byte b = StringOpsKt.getESCAPE_MARKERS()[charAt];
                if (b == 0) {
                    i2 = ensureTotalCapacity + 1;
                    this.charArray[ensureTotalCapacity] = (char) charAt;
                } else {
                    if (b == 1) {
                        String str2 = StringOpsKt.getESCAPE_STRINGS()[charAt];
                        Intrinsics.checkNotNull(str2);
                        int ensureTotalCapacity2 = ensureTotalCapacity(ensureTotalCapacity, str2.length());
                        str2.getChars(0, str2.length(), this.charArray, ensureTotalCapacity2);
                        i = ensureTotalCapacity2 + str2.length();
                    } else {
                        char[] cArr = this.charArray;
                        cArr[ensureTotalCapacity] = AbstractJsonLexerKt.STRING_ESC;
                        cArr[ensureTotalCapacity + 1] = (char) b;
                        i = ensureTotalCapacity + 2;
                    }
                }
            } else {
                i2 = ensureTotalCapacity + 1;
                this.charArray[ensureTotalCapacity] = (char) charAt;
            }
            i = i2;
        }
        ensureTotalCapacity(i, 1);
        char[] cArr2 = this.charArray;
        cArr2[i] = '\"';
        writeUtf8(cArr2, i + 1);
        flush();
    }

    private final int ensureTotalCapacity(int i, int i2) {
        int i3 = i2 + i;
        char[] cArr = this.charArray;
        if (cArr.length <= i3) {
            char[] copyOf = Arrays.copyOf(cArr, RangesKt.coerceAtLeast(i3, i * 2));
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.charArray = copyOf;
        }
        return i;
    }

    public void release() {
        flush();
        CharArrayPool.INSTANCE.release(this.charArray);
        ByteArrayPool.INSTANCE.release(this.buffer);
    }

    private final void flush() {
        this.stream.write(this.buffer, 0, this.indexInBuffer);
        this.indexInBuffer = 0;
    }

    private final void write(int i) {
        byte[] bArr = this.buffer;
        int i2 = this.indexInBuffer;
        this.indexInBuffer = i2 + 1;
        bArr[i2] = (byte) i;
    }

    private final int rest() {
        return this.buffer.length - this.indexInBuffer;
    }

    private final void writeUtf8(char[] cArr, int i) {
        char c;
        if (i < 0) {
            throw new IllegalArgumentException("count < 0".toString());
        } else if (i <= cArr.length) {
            int i2 = 0;
            while (i2 < i) {
                char c2 = cArr[i2];
                if (c2 < 128) {
                    if (this.buffer.length - this.indexInBuffer < 1) {
                        flush();
                    }
                    byte[] bArr = this.buffer;
                    int i3 = this.indexInBuffer;
                    int i4 = i3 + 1;
                    this.indexInBuffer = i4;
                    bArr[i3] = (byte) c2;
                    i2++;
                    int min = Math.min(i, (bArr.length - i4) + i2);
                    while (i2 < min) {
                        char c3 = cArr[i2];
                        if (c3 >= 128) {
                            break;
                        }
                        byte[] bArr2 = this.buffer;
                        int i5 = this.indexInBuffer;
                        this.indexInBuffer = i5 + 1;
                        bArr2[i5] = (byte) c3;
                        i2++;
                    }
                } else {
                    if (c2 < 2048) {
                        if (this.buffer.length - this.indexInBuffer < 2) {
                            flush();
                        }
                        byte[] bArr3 = this.buffer;
                        int i6 = this.indexInBuffer;
                        bArr3[i6] = (byte) ((c2 >> 6) | 192);
                        this.indexInBuffer = i6 + 2;
                        bArr3[i6 + 1] = (byte) ((c2 & '?') | 128);
                    } else if (c2 < 55296 || c2 > 57343) {
                        if (this.buffer.length - this.indexInBuffer < 3) {
                            flush();
                        }
                        int i7 = (c2 >> 12) | BERTags.FLAGS;
                        byte[] bArr4 = this.buffer;
                        int i8 = this.indexInBuffer;
                        bArr4[i8] = (byte) i7;
                        bArr4[i8 + 1] = (byte) (((c2 >> 6) & 63) | 128);
                        this.indexInBuffer = i8 + 3;
                        bArr4[i8 + 2] = (byte) ((c2 & '?') | 128);
                    } else {
                        int i9 = i2 + 1;
                        if (i9 < i) {
                            c = cArr[i9];
                        } else {
                            c = 0;
                        }
                        if (c2 > 56319 || 56320 > c || c >= 57344) {
                            if (this.buffer.length - this.indexInBuffer < 1) {
                                flush();
                            }
                            byte[] bArr5 = this.buffer;
                            int i10 = this.indexInBuffer;
                            this.indexInBuffer = i10 + 1;
                            bArr5[i10] = (byte) 63;
                            i2 = i9;
                        } else {
                            int i11 = (((c2 & 1023) << 10) | (c & 1023)) + 0;
                            if (this.buffer.length - this.indexInBuffer < 4) {
                                flush();
                            }
                            byte[] bArr6 = this.buffer;
                            int i12 = this.indexInBuffer;
                            bArr6[i12] = (byte) ((i11 >> 18) | 240);
                            bArr6[i12 + 1] = (byte) (((i11 >> 12) & 63) | 128);
                            bArr6[i12 + 2] = (byte) (((i11 >> 6) & 63) | 128);
                            this.indexInBuffer = i12 + 4;
                            bArr6[i12 + 3] = (byte) ((i11 & 63) | 128);
                            i2 += 2;
                        }
                    }
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException(("count > string.length: " + i + " > " + cArr.length).toString());
        }
    }

    private final void ensure(int i) {
        if (this.buffer.length - this.indexInBuffer < i) {
            flush();
        }
    }

    private final void writeUtf8CodePoint(int i) {
        if (i < 128) {
            if (this.buffer.length - this.indexInBuffer < 1) {
                flush();
            }
            byte[] bArr = this.buffer;
            int i2 = this.indexInBuffer;
            this.indexInBuffer = i2 + 1;
            bArr[i2] = (byte) i;
        } else if (i < 2048) {
            if (this.buffer.length - this.indexInBuffer < 2) {
                flush();
            }
            byte[] bArr2 = this.buffer;
            int i3 = this.indexInBuffer;
            bArr2[i3] = (byte) ((i >> 6) | 192);
            this.indexInBuffer = i3 + 2;
            bArr2[i3 + 1] = (byte) ((i & 63) | 128);
        } else if (55296 <= i && i < 57344) {
            if (this.buffer.length - this.indexInBuffer < 1) {
                flush();
            }
            byte[] bArr3 = this.buffer;
            int i4 = this.indexInBuffer;
            this.indexInBuffer = i4 + 1;
            bArr3[i4] = (byte) 63;
        } else if (i < 65536) {
            if (this.buffer.length - this.indexInBuffer < 3) {
                flush();
            }
            int i5 = (i >> 12) | BERTags.FLAGS;
            byte[] bArr4 = this.buffer;
            int i6 = this.indexInBuffer;
            bArr4[i6] = (byte) i5;
            bArr4[i6 + 1] = (byte) (((i >> 6) & 63) | 128);
            this.indexInBuffer = i6 + 3;
            bArr4[i6 + 2] = (byte) ((i & 63) | 128);
        } else if (i <= 1114111) {
            if (this.buffer.length - this.indexInBuffer < 4) {
                flush();
            }
            byte[] bArr5 = this.buffer;
            int i7 = this.indexInBuffer;
            bArr5[i7] = (byte) ((i >> 18) | 240);
            bArr5[i7 + 1] = (byte) (((i >> 12) & 63) | 128);
            bArr5[i7 + 2] = (byte) (((i >> 6) & 63) | 128);
            this.indexInBuffer = i7 + 4;
            bArr5[i7 + 3] = (byte) ((i & 63) | 128);
        } else {
            throw new JsonEncodingException("Unexpected code point: " + i);
        }
    }
}
