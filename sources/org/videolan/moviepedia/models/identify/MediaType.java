package org.videolan.moviepedia.models.identify;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/moviepedia/models/identify/MediaType;", "", "(Ljava/lang/String;I)V", "TV_SHOW", "TV_SEASON", "TV_EPISODE", "MOVIE", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public enum MediaType {
    TV_SHOW,
    TV_SEASON,
    TV_EPISODE,
    MOVIE;

    public static EnumEntries<MediaType> getEntries() {
        return $ENTRIES;
    }

    static {
        MediaType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}