package org.videolan.moviepedia.models.identify;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lorg/videolan/moviepedia/models/identify/Images;", "", "backdrops", "", "Lorg/videolan/moviepedia/models/identify/Backdrop;", "posters", "Lorg/videolan/moviepedia/models/identify/Poster;", "(Ljava/util/List;Ljava/util/List;)V", "getBackdrops", "()Ljava/util/List;", "getPosters", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public final class Images {
    @Json(name = "backdrops")
    private final List<Backdrop> backdrops;
    @Json(name = "posters")
    private final List<Poster> posters;

    public static /* synthetic */ Images copy$default(Images images, List<Backdrop> list, List<Poster> list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = images.backdrops;
        }
        if ((i & 2) != 0) {
            list2 = images.posters;
        }
        return images.copy(list, list2);
    }

    public final List<Backdrop> component1() {
        return this.backdrops;
    }

    public final List<Poster> component2() {
        return this.posters;
    }

    public final Images copy(List<Backdrop> list, List<Poster> list2) {
        Intrinsics.checkNotNullParameter(list, "backdrops");
        Intrinsics.checkNotNullParameter(list2, "posters");
        return new Images(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Images)) {
            return false;
        }
        Images images = (Images) obj;
        return Intrinsics.areEqual((Object) this.backdrops, (Object) images.backdrops) && Intrinsics.areEqual((Object) this.posters, (Object) images.posters);
    }

    public int hashCode() {
        return (this.backdrops.hashCode() * 31) + this.posters.hashCode();
    }

    public String toString() {
        return "Images(backdrops=" + this.backdrops + ", posters=" + this.posters + ')';
    }

    public Images(List<Backdrop> list, List<Poster> list2) {
        Intrinsics.checkNotNullParameter(list, "backdrops");
        Intrinsics.checkNotNullParameter(list2, "posters");
        this.backdrops = list;
        this.posters = list2;
    }

    public final List<Backdrop> getBackdrops() {
        return this.backdrops;
    }

    public final List<Poster> getPosters() {
        return this.posters;
    }
}
