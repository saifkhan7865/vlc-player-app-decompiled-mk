package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\r\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J3\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\b2!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u000f0\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001d"}, d2 = {"Lkotlinx/serialization/json/internal/StringJsonLexer;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "source", "", "(Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "canConsumeValue", "", "consumeKeyString", "consumeLeadingMatchingValue", "keyToMatch", "isLenient", "consumeNextToken", "", "", "expected", "", "consumeStringChunked", "consumeChunk", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "stringChunk", "prefetchOrEof", "", "position", "skipWhitespaces", "tryConsumeComma", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: StringJsonLexer.kt */
public final class StringJsonLexer extends AbstractJsonLexer {
    private final String source;

    public StringJsonLexer(String str) {
        Intrinsics.checkNotNullParameter(str, "source");
        this.source = str;
    }

    /* access modifiers changed from: protected */
    public String getSource() {
        return this.source;
    }

    public int prefetchOrEof(int i) {
        if (i < getSource().length()) {
            return i;
        }
        return -1;
    }

    public byte consumeNextToken() {
        String source2 = getSource();
        while (this.currentPosition != -1 && this.currentPosition < source2.length()) {
            int i = this.currentPosition;
            this.currentPosition = i + 1;
            byte charToTokenClass = AbstractJsonLexerKt.charToTokenClass(source2.charAt(i));
            if (charToTokenClass != 3) {
                return charToTokenClass;
            }
        }
        return 10;
    }

    public boolean tryConsumeComma() {
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces == getSource().length() || skipWhitespaces == -1 || getSource().charAt(skipWhitespaces) != ',') {
            return false;
        }
        this.currentPosition++;
        int i = this.currentPosition;
        return true;
    }

    public boolean canConsumeValue() {
        int i = this.currentPosition;
        if (i == -1) {
            return false;
        }
        while (i < getSource().length()) {
            char charAt = getSource().charAt(i);
            if (charAt == ' ' || charAt == 10 || charAt == 13 || charAt == 9) {
                i++;
            } else {
                this.currentPosition = i;
                return isValidValueStart(charAt);
            }
        }
        this.currentPosition = i;
        return false;
    }

    public int skipWhitespaces() {
        int i = this.currentPosition;
        if (i == -1) {
            return i;
        }
        while (i < getSource().length() && ((r1 = getSource().charAt(i)) == ' ' || r1 == 10 || r1 == 13 || r1 == 9)) {
            i++;
        }
        this.currentPosition = i;
        return i;
    }

    public void consumeNextToken(char c) {
        if (this.currentPosition == -1) {
            unexpectedToken(c);
        }
        String source2 = getSource();
        while (this.currentPosition < source2.length()) {
            int i = this.currentPosition;
            this.currentPosition = i + 1;
            char charAt = source2.charAt(i);
            if (!(charAt == ' ' || charAt == 10 || charAt == 13 || charAt == 9)) {
                if (charAt != c) {
                    unexpectedToken(c);
                } else {
                    return;
                }
            }
        }
        unexpectedToken(c);
    }

    public String consumeKeyString() {
        consumeNextToken('\"');
        int i = this.currentPosition;
        int indexOf$default = StringsKt.indexOf$default((CharSequence) getSource(), '\"', i, false, 4, (Object) null);
        if (indexOf$default != -1) {
            for (int i2 = i; i2 < indexOf$default; i2++) {
                if (getSource().charAt(i2) == '\\') {
                    return consumeString(getSource(), this.currentPosition, i2);
                }
            }
            this.currentPosition = indexOf$default + 1;
            String substring = getSource().substring(i, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        fail$kotlinx_serialization_json((byte) 1);
        throw new KotlinNothingValueException();
    }

    public void consumeStringChunked(boolean z, Function1<? super String, Unit> function1) {
        String str;
        Intrinsics.checkNotNullParameter(function1, "consumeChunk");
        if (z) {
            str = consumeStringLenient();
        } else {
            str = consumeString();
        }
        for (Object invoke : StringsKt.chunked(str, 16384)) {
            function1.invoke(invoke);
        }
    }

    public String consumeLeadingMatchingValue(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "keyToMatch");
        int i = this.currentPosition;
        try {
            if (consumeNextToken() != 6) {
                return null;
            }
            if (!Intrinsics.areEqual((Object) z ? consumeKeyString() : consumeStringLenientNotNull(), (Object) str)) {
                this.currentPosition = i;
                return null;
            } else if (consumeNextToken() != 5) {
                this.currentPosition = i;
                return null;
            } else {
                String consumeString = z ? consumeString() : consumeStringLenientNotNull();
                this.currentPosition = i;
                return consumeString;
            }
        } finally {
            this.currentPosition = i;
        }
    }
}
