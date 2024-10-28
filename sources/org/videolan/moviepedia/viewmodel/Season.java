package org.videolan.moviepedia.viewmodel;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lorg/videolan/moviepedia/viewmodel/Season;", "", "seasonNumber", "", "episodes", "Ljava/util/ArrayList;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "Lkotlin/collections/ArrayList;", "(ILjava/util/ArrayList;)V", "getEpisodes", "()Ljava/util/ArrayList;", "setEpisodes", "(Ljava/util/ArrayList;)V", "getSeasonNumber", "()I", "setSeasonNumber", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataModel.kt */
public final class Season {
    private ArrayList<MediaMetadataWithImages> episodes;
    private int seasonNumber;

    public Season() {
        this(0, (ArrayList) null, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Season copy$default(Season season, int i, ArrayList<MediaMetadataWithImages> arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = season.seasonNumber;
        }
        if ((i2 & 2) != 0) {
            arrayList = season.episodes;
        }
        return season.copy(i, arrayList);
    }

    public final int component1() {
        return this.seasonNumber;
    }

    public final ArrayList<MediaMetadataWithImages> component2() {
        return this.episodes;
    }

    public final Season copy(int i, ArrayList<MediaMetadataWithImages> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "episodes");
        return new Season(i, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Season)) {
            return false;
        }
        Season season = (Season) obj;
        return this.seasonNumber == season.seasonNumber && Intrinsics.areEqual((Object) this.episodes, (Object) season.episodes);
    }

    public int hashCode() {
        return (this.seasonNumber * 31) + this.episodes.hashCode();
    }

    public String toString() {
        return "Season(seasonNumber=" + this.seasonNumber + ", episodes=" + this.episodes + ')';
    }

    public Season(int i, ArrayList<MediaMetadataWithImages> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "episodes");
        this.seasonNumber = i;
        this.episodes = arrayList;
    }

    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    public final void setSeasonNumber(int i) {
        this.seasonNumber = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Season(int i, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? new ArrayList() : arrayList);
    }

    public final ArrayList<MediaMetadataWithImages> getEpisodes() {
        return this.episodes;
    }

    public final void setEpisodes(ArrayList<MediaMetadataWithImages> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.episodes = arrayList;
    }
}
