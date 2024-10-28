package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "", "invoke", "()[Ljava/lang/String;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaComparators.kt */
final class MediaComparators$englishArticles$2 extends Lambda implements Function0<String[]> {
    public static final MediaComparators$englishArticles$2 INSTANCE = new MediaComparators$englishArticles$2();

    MediaComparators$englishArticles$2() {
        super(0);
    }

    public final String[] invoke() {
        return new String[]{"a ", "an ", "the "};
    }
}
