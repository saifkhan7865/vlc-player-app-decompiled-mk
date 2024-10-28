package org.videolan.vlc.util;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "it", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilterDelegate.kt */
final class PlaylistFilterDelegate$filteringJob$2$1$1$queryStrings$2 extends Lambda implements Function1<String, String> {
    public static final PlaylistFilterDelegate$filteringJob$2$1$1$queryStrings$2 INSTANCE = new PlaylistFilterDelegate$filteringJob$2$1$1$queryStrings$2();

    PlaylistFilterDelegate$filteringJob$2$1$1$queryStrings$2() {
        super(1);
    }

    public final String invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }
}
