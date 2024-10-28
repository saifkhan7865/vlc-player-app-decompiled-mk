package org.videolan.moviepedia;

import android.content.Context;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.MediaMetadataKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007¨\u0006\t"}, d2 = {"getHeaderMoviepedia", "", "context", "Landroid/content/Context;", "sort", "", "item", "Lorg/videolan/moviepedia/database/models/MediaMetadata;", "aboveItem", "moviepedia_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Helpers.kt */
public final class HelpersKt {
    public static final String getHeaderMoviepedia(Context context, int i, MediaMetadata mediaMetadata, MediaMetadata mediaMetadata2) {
        String str;
        if (context == null || mediaMetadata == null) {
            return null;
        }
        if (!(i == 0 || i == 1)) {
            if (i == 5) {
                str = MediaMetadataKt.getYear(mediaMetadata);
                if (mediaMetadata2 != null && !(true ^ Intrinsics.areEqual((Object) str, (Object) MediaMetadataKt.getYear(mediaMetadata2)))) {
                    return null;
                }
                return str;
            } else if (i != 10) {
                return null;
            }
        }
        String str2 = "#";
        if (mediaMetadata.getTitle().length() != 0 && Character.isLetter(mediaMetadata.getTitle().charAt(0))) {
            String substring = mediaMetadata.getTitle().substring(0, 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            str = substring.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(str, "toUpperCase(...)");
        } else {
            str = str2;
        }
        if (mediaMetadata2 != null) {
            if (mediaMetadata2.getTitle().length() != 0 && Character.isLetter(mediaMetadata2.getTitle().charAt(0))) {
                String substring2 = mediaMetadata2.getTitle().substring(0, 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                str2 = substring2.toUpperCase(locale2);
                Intrinsics.checkNotNullExpressionValue(str2, "toUpperCase(...)");
            }
            if (!(true ^ Intrinsics.areEqual((Object) str, (Object) str2))) {
                return null;
            }
        }
        return str;
    }
}
