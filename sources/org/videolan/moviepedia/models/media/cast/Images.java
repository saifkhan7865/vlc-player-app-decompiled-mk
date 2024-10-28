package org.videolan.moviepedia.models.media.cast;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/videolan/moviepedia/models/media/cast/Images;", "", "profiles", "", "Lorg/videolan/moviepedia/models/media/cast/Profile;", "(Ljava/util/List;)V", "getProfiles", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CastResult.kt */
public final class Images {
    @Json(name = "profiles")
    private final List<Profile> profiles;

    public static /* synthetic */ Images copy$default(Images images, List<Profile> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = images.profiles;
        }
        return images.copy(list);
    }

    public final List<Profile> component1() {
        return this.profiles;
    }

    public final Images copy(List<Profile> list) {
        Intrinsics.checkNotNullParameter(list, "profiles");
        return new Images(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Images) && Intrinsics.areEqual((Object) this.profiles, (Object) ((Images) obj).profiles);
    }

    public int hashCode() {
        return this.profiles.hashCode();
    }

    public String toString() {
        return "Images(profiles=" + this.profiles + ')';
    }

    public Images(List<Profile> list) {
        Intrinsics.checkNotNullParameter(list, "profiles");
        this.profiles = list;
    }

    public final List<Profile> getProfiles() {
        return this.profiles;
    }
}
