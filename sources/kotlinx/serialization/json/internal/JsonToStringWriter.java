package kotlinx.serialization.json.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\b\u0010\u0012\u001a\u00020\bH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlinx/serialization/json/internal/JsonToStringWriter;", "Lkotlinx/serialization/json/internal/JsonWriter;", "()V", "array", "", "size", "", "appendStringSlowPath", "", "firstEscapedChar", "currentSize", "string", "", "ensureAdditionalCapacity", "expected", "ensureTotalCapacity", "oldSize", "additional", "release", "toString", "write", "text", "writeChar", "char", "", "writeLong", "value", "", "writeQuoted", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonToStringWriter.kt */
public final class JsonToStringWriter implements JsonWriter {
    private char[] array = CharArrayPool.INSTANCE.take();
    private int size;

    public void writeLong(long j) {
        write(String.valueOf(j));
    }

    public void writeChar(char c) {
        ensureAdditionalCapacity(1);
        char[] cArr = this.array;
        int i = this.size;
        this.size = i + 1;
        cArr[i] = c;
    }

    public void write(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        int length = str.length();
        if (length != 0) {
            ensureAdditionalCapacity(length);
            str.getChars(0, str.length(), this.array, this.size);
            this.size += length;
        }
    }

    public void writeQuoted(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        ensureAdditionalCapacity(str.length() + 2);
        char[] cArr = this.array;
        int i = this.size;
        int i2 = i + 1;
        cArr[i] = '\"';
        int length = str.length();
        str.getChars(0, length, cArr, i2);
        int i3 = length + i2;
        int i4 = i2;
        while (i4 < i3) {
            char c = cArr[i4];
            if (c >= StringOpsKt.getESCAPE_MARKERS().length || StringOpsKt.getESCAPE_MARKERS()[c] == 0) {
                i4++;
            } else {
                appendStringSlowPath(i4 - i2, i4, str);
                return;
            }
        }
        cArr[i3] = '\"';
        this.size = i3 + 1;
    }

    private final void appendStringSlowPath(int i, int i2, String str) {
        int i3;
        int length = str.length();
        while (i < length) {
            int ensureTotalCapacity = ensureTotalCapacity(i2, 2);
            char charAt = str.charAt(i);
            if (charAt < StringOpsKt.getESCAPE_MARKERS().length) {
                byte b = StringOpsKt.getESCAPE_MARKERS()[charAt];
                if (b == 0) {
                    i3 = ensureTotalCapacity + 1;
                    this.array[ensureTotalCapacity] = (char) charAt;
                } else {
                    if (b == 1) {
                        String str2 = StringOpsKt.getESCAPE_STRINGS()[charAt];
                        Intrinsics.checkNotNull(str2);
                        int ensureTotalCapacity2 = ensureTotalCapacity(ensureTotalCapacity, str2.length());
                        str2.getChars(0, str2.length(), this.array, ensureTotalCapacity2);
                        i2 = ensureTotalCapacity2 + str2.length();
                        this.size = i2;
                    } else {
                        char[] cArr = this.array;
                        cArr[ensureTotalCapacity] = AbstractJsonLexerKt.STRING_ESC;
                        cArr[ensureTotalCapacity + 1] = (char) b;
                        i2 = ensureTotalCapacity + 2;
                        this.size = i2;
                    }
                    i++;
                }
            } else {
                i3 = ensureTotalCapacity + 1;
                this.array[ensureTotalCapacity] = (char) charAt;
            }
            i2 = i3;
            i++;
        }
        int ensureTotalCapacity3 = ensureTotalCapacity(i2, 1);
        this.array[ensureTotalCapacity3] = '\"';
        this.size = ensureTotalCapacity3 + 1;
    }

    public void release() {
        CharArrayPool.INSTANCE.release(this.array);
    }

    public String toString() {
        return new String(this.array, 0, this.size);
    }

    private final void ensureAdditionalCapacity(int i) {
        ensureTotalCapacity(this.size, i);
    }

    private final int ensureTotalCapacity(int i, int i2) {
        int i3 = i2 + i;
        char[] cArr = this.array;
        if (cArr.length <= i3) {
            char[] copyOf = Arrays.copyOf(cArr, RangesKt.coerceAtLeast(i3, i * 2));
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.array = copyOf;
        }
        return i;
    }
}
