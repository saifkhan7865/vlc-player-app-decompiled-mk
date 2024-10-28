package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u000eH\u0016J\u0018\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\fH\u0016J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\fH\u0002J\u0006\u0010#\u001a\u00020\u000eJ\u0018\u0010$\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010%\u001a\u00020\fH\u0016J\b\u0010&\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lkotlinx/serialization/json/internal/ReaderJsonLexer;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "reader", "Lkotlinx/serialization/json/internal/SerialReader;", "buffer", "", "(Lkotlinx/serialization/json/internal/SerialReader;[C)V", "source", "Lkotlinx/serialization/json/internal/ArrayAsSequence;", "getSource", "()Lkotlinx/serialization/json/internal/ArrayAsSequence;", "threshold", "", "appendRange", "", "fromIndex", "toIndex", "canConsumeValue", "", "consumeKeyString", "", "consumeLeadingMatchingValue", "keyToMatch", "isLenient", "consumeNextToken", "", "ensureHaveChars", "indexOf", "char", "", "startPos", "prefetchOrEof", "position", "preload", "unprocessedCount", "release", "substring", "endPos", "tryConsumeComma", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonLexer.kt */
public final class ReaderJsonLexer extends AbstractJsonLexer {
    private final char[] buffer;
    private final SerialReader reader;
    private final ArrayAsSequence source;
    private int threshold;

    public String consumeLeadingMatchingValue(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "keyToMatch");
        return null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ReaderJsonLexer(SerialReader serialReader, char[] cArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(serialReader, (i & 2) != 0 ? CharArrayPoolBatchSize.INSTANCE.take() : cArr);
    }

    public ReaderJsonLexer(SerialReader serialReader, char[] cArr) {
        Intrinsics.checkNotNullParameter(serialReader, "reader");
        Intrinsics.checkNotNullParameter(cArr, "buffer");
        this.reader = serialReader;
        this.buffer = cArr;
        this.threshold = 128;
        this.source = new ArrayAsSequence(cArr);
        preload(0);
    }

    /* access modifiers changed from: protected */
    public ArrayAsSequence getSource() {
        return this.source;
    }

    public boolean tryConsumeComma() {
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces >= getSource().length() || skipWhitespaces == -1 || getSource().charAt(skipWhitespaces) != ',') {
            return false;
        }
        this.currentPosition++;
        int i = this.currentPosition;
        return true;
    }

    public boolean canConsumeValue() {
        ensureHaveChars();
        int i = this.currentPosition;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i);
            if (prefetchOrEof != -1) {
                char charAt = getSource().charAt(prefetchOrEof);
                if (charAt == ' ' || charAt == 10 || charAt == 13 || charAt == 9) {
                    i = prefetchOrEof + 1;
                } else {
                    this.currentPosition = prefetchOrEof;
                    return isValidValueStart(charAt);
                }
            } else {
                this.currentPosition = prefetchOrEof;
                return false;
            }
        }
    }

    private final void preload(int i) {
        char[] access$getBuffer$p = getSource().buffer;
        if (i != 0) {
            ArraysKt.copyInto(access$getBuffer$p, access$getBuffer$p, 0, this.currentPosition, this.currentPosition + i);
        }
        int length = getSource().length();
        while (true) {
            if (i == length) {
                break;
            }
            int read = this.reader.read(access$getBuffer$p, i, length - i);
            if (read == -1) {
                getSource().trim(i);
                this.threshold = -1;
                break;
            }
            i += read;
        }
        this.currentPosition = 0;
    }

    public int prefetchOrEof(int i) {
        if (i < getSource().length()) {
            return i;
        }
        this.currentPosition = i;
        ensureHaveChars();
        return (this.currentPosition != 0 || getSource().length() == 0) ? -1 : 0;
    }

    public byte consumeNextToken() {
        ensureHaveChars();
        ArrayAsSequence source2 = getSource();
        int i = this.currentPosition;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i);
            if (prefetchOrEof != -1) {
                int i2 = prefetchOrEof + 1;
                byte charToTokenClass = AbstractJsonLexerKt.charToTokenClass(source2.charAt(prefetchOrEof));
                if (charToTokenClass != 3) {
                    this.currentPosition = i2;
                    return charToTokenClass;
                }
                i = i2;
            } else {
                this.currentPosition = prefetchOrEof;
                return 10;
            }
        }
    }

    public void ensureHaveChars() {
        int length = getSource().length() - this.currentPosition;
        if (length <= this.threshold) {
            preload(length);
        }
    }

    public String consumeKeyString() {
        consumeNextToken('\"');
        int i = this.currentPosition;
        int indexOf = indexOf('\"', i);
        if (indexOf == -1) {
            int prefetchOrEof = prefetchOrEof(i);
            if (prefetchOrEof != -1) {
                return consumeString(getSource(), this.currentPosition, prefetchOrEof);
            }
            fail$kotlinx_serialization_json((byte) 1);
            throw new KotlinNothingValueException();
        }
        for (int i2 = i; i2 < indexOf; i2++) {
            if (getSource().charAt(i2) == '\\') {
                return consumeString(getSource(), this.currentPosition, i2);
            }
        }
        this.currentPosition = indexOf + 1;
        return substring(i, indexOf);
    }

    public int indexOf(char c, int i) {
        ArrayAsSequence source2 = getSource();
        int length = source2.length();
        while (i < length) {
            if (source2.charAt(i) == c) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public String substring(int i, int i2) {
        return getSource().substring(i, i2);
    }

    /* access modifiers changed from: protected */
    public void appendRange(int i, int i2) {
        StringBuilder escapedString = getEscapedString();
        escapedString.append(getSource().buffer, i, i2 - i);
        Intrinsics.checkNotNullExpressionValue(escapedString, "this.append(value, start…x, endIndex - startIndex)");
    }

    public final void release() {
        CharArrayPoolBatchSize.INSTANCE.release(this.buffer);
    }
}
