package org.videolan.vlc.gui.helpers;

import java.util.BitSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/util/BitSet;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaComparators.kt */
final class MediaComparators$asciiPunctuation$2 extends Lambda implements Function0<BitSet> {
    public static final MediaComparators$asciiPunctuation$2 INSTANCE = new MediaComparators$asciiPunctuation$2();

    MediaComparators$asciiPunctuation$2() {
        super(0);
    }

    public final BitSet invoke() {
        BitSet bitSet = new BitSet();
        char[] charArray = "\t !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        for (char c : charArray) {
            bitSet.set(c, true);
        }
        return bitSet;
    }
}
