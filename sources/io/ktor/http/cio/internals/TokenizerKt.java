package io.ktor.http.cio.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a \u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0000¨\u0006\r"}, d2 = {"findSpaceOrEnd", "", "text", "", "range", "Lio/ktor/http/cio/internals/MutableRange;", "nextToken", "skipSpaces", "", "skipSpacesAndHorizontalTabs", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "start", "end", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Tokenizer.kt */
public final class TokenizerKt {
    public static final CharSequence nextToken(CharSequence charSequence, MutableRange mutableRange) {
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Intrinsics.checkNotNullParameter(mutableRange, "range");
        int findSpaceOrEnd = findSpaceOrEnd(charSequence, mutableRange);
        CharSequence subSequence = charSequence.subSequence(mutableRange.getStart(), findSpaceOrEnd);
        mutableRange.setStart(findSpaceOrEnd);
        return subSequence;
    }

    public static final int skipSpacesAndHorizontalTabs(CharArrayBuilder charArrayBuilder, int i, int i2) {
        Intrinsics.checkNotNullParameter(charArrayBuilder, "text");
        while (i < i2) {
            char charAt = charArrayBuilder.charAt(i);
            if (!CharsKt.isWhitespace(charAt) && charAt != 9) {
                break;
            }
            i++;
        }
        return i;
    }

    public static final void skipSpaces(CharSequence charSequence, MutableRange mutableRange) {
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Intrinsics.checkNotNullParameter(mutableRange, "range");
        int start = mutableRange.getStart();
        int end = mutableRange.getEnd();
        if (start < end && CharsKt.isWhitespace(charSequence.charAt(start))) {
            do {
                start++;
                if (start >= end || !CharsKt.isWhitespace(charSequence.charAt(start))) {
                    mutableRange.setStart(start);
                }
                start++;
                break;
            } while (!CharsKt.isWhitespace(charSequence.charAt(start)));
            mutableRange.setStart(start);
        }
    }

    public static final int findSpaceOrEnd(CharSequence charSequence, MutableRange mutableRange) {
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Intrinsics.checkNotNullParameter(mutableRange, "range");
        int start = mutableRange.getStart();
        int end = mutableRange.getEnd();
        if (start < end && !CharsKt.isWhitespace(charSequence.charAt(start))) {
            do {
                start++;
                if (start >= end) {
                    break;
                }
            } while (CharsKt.isWhitespace(charSequence.charAt(start)));
        }
        return start;
    }
}
