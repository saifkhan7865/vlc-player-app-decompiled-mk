package org.videolan.moviepedia.database.models;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaImageType;", "", "key", "", "(Ljava/lang/String;II)V", "getKey", "()I", "BACKDROP", "POSTER", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public enum MediaImageType {
    BACKDROP(0),
    POSTER(1);
    
    public static final Companion Companion = null;
    private final int key;

    public static EnumEntries<MediaImageType> getEntries() {
        return $ENTRIES;
    }

    private MediaImageType(int i) {
        this.key = i;
    }

    public final int getKey() {
        return this.key;
    }

    static {
        MediaImageType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaImageType$Companion;", "", "()V", "fromKey", "Lorg/videolan/moviepedia/database/models/MediaImageType;", "key", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaMetadata.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaImageType fromKey(int i) {
            for (MediaImageType mediaImageType : MediaImageType.values()) {
                if (mediaImageType.getKey() == i) {
                    return mediaImageType;
                }
            }
            return MediaImageType.BACKDROP;
        }
    }
}
