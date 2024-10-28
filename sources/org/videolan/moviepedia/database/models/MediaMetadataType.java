package org.videolan.moviepedia.database.models;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "", "key", "", "(Ljava/lang/String;II)V", "getKey", "()I", "MOVIE", "TV_EPISODE", "TV_SHOW", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public enum MediaMetadataType {
    MOVIE(0),
    TV_EPISODE(1),
    TV_SHOW(2);
    
    public static final Companion Companion = null;
    private final int key;

    public static EnumEntries<MediaMetadataType> getEntries() {
        return $ENTRIES;
    }

    private MediaMetadataType(int i) {
        this.key = i;
    }

    public final int getKey() {
        return this.key;
    }

    static {
        MediaMetadataType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaMetadataType$Companion;", "", "()V", "fromKey", "Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "key", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaMetadata.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaMetadataType fromKey(int i) {
            for (MediaMetadataType mediaMetadataType : MediaMetadataType.values()) {
                if (mediaMetadataType.getKey() == i) {
                    return mediaMetadataType;
                }
            }
            return MediaMetadataType.MOVIE;
        }
    }
}
