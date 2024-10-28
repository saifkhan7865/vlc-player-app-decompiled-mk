package org.videolan.moviepedia.database.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaPersonJoin;", "", "mediaId", "", "personId", "type", "Lorg/videolan/moviepedia/database/models/PersonType;", "(Ljava/lang/String;Ljava/lang/String;Lorg/videolan/moviepedia/database/models/PersonType;)V", "getMediaId", "()Ljava/lang/String;", "getPersonId", "getType", "()Lorg/videolan/moviepedia/database/models/PersonType;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public final class MediaPersonJoin {
    private final String mediaId;
    private final String personId;
    private final PersonType type;

    public static /* synthetic */ MediaPersonJoin copy$default(MediaPersonJoin mediaPersonJoin, String str, String str2, PersonType personType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaPersonJoin.mediaId;
        }
        if ((i & 2) != 0) {
            str2 = mediaPersonJoin.personId;
        }
        if ((i & 4) != 0) {
            personType = mediaPersonJoin.type;
        }
        return mediaPersonJoin.copy(str, str2, personType);
    }

    public final String component1() {
        return this.mediaId;
    }

    public final String component2() {
        return this.personId;
    }

    public final PersonType component3() {
        return this.type;
    }

    public final MediaPersonJoin copy(String str, String str2, PersonType personType) {
        Intrinsics.checkNotNullParameter(str, "mediaId");
        Intrinsics.checkNotNullParameter(str2, "personId");
        Intrinsics.checkNotNullParameter(personType, "type");
        return new MediaPersonJoin(str, str2, personType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPersonJoin)) {
            return false;
        }
        MediaPersonJoin mediaPersonJoin = (MediaPersonJoin) obj;
        return Intrinsics.areEqual((Object) this.mediaId, (Object) mediaPersonJoin.mediaId) && Intrinsics.areEqual((Object) this.personId, (Object) mediaPersonJoin.personId) && this.type == mediaPersonJoin.type;
    }

    public int hashCode() {
        return (((this.mediaId.hashCode() * 31) + this.personId.hashCode()) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "MediaPersonJoin(mediaId=" + this.mediaId + ", personId=" + this.personId + ", type=" + this.type + ')';
    }

    public MediaPersonJoin(String str, String str2, PersonType personType) {
        Intrinsics.checkNotNullParameter(str, "mediaId");
        Intrinsics.checkNotNullParameter(str2, "personId");
        Intrinsics.checkNotNullParameter(personType, "type");
        this.mediaId = str;
        this.personId = str2;
        this.type = personType;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final String getPersonId() {
        return this.personId;
    }

    public final PersonType getType() {
        return this.type;
    }
}
