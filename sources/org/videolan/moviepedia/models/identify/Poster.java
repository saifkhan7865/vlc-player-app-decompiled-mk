package org.videolan.moviepedia.models.identify;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.resolver.ResolverImage;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lorg/videolan/moviepedia/models/identify/Poster;", "Lorg/videolan/moviepedia/models/resolver/ResolverImage;", "language", "", "path", "ratio", "", "(Ljava/lang/String;Ljava/lang/String;D)V", "getLanguage", "()Ljava/lang/String;", "getPath", "getRatio", "()D", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public final class Poster extends ResolverImage {
    @Json(name = "language")
    private final String language;
    @Json(name = "path")
    private final String path;
    @Json(name = "ratio")
    private final double ratio;

    public static /* synthetic */ Poster copy$default(Poster poster, String str, String str2, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            str = poster.language;
        }
        if ((i & 2) != 0) {
            str2 = poster.path;
        }
        if ((i & 4) != 0) {
            d = poster.ratio;
        }
        return poster.copy(str, str2, d);
    }

    public final String component1() {
        return this.language;
    }

    public final String component2() {
        return this.path;
    }

    public final double component3() {
        return this.ratio;
    }

    public final Poster copy(String str, String str2, double d) {
        Intrinsics.checkNotNullParameter(str, "language");
        Intrinsics.checkNotNullParameter(str2, ArtworkProvider.PATH);
        return new Poster(str, str2, d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Poster)) {
            return false;
        }
        Poster poster = (Poster) obj;
        return Intrinsics.areEqual((Object) this.language, (Object) poster.language) && Intrinsics.areEqual((Object) this.path, (Object) poster.path) && Double.compare(this.ratio, poster.ratio) == 0;
    }

    public int hashCode() {
        return (((this.language.hashCode() * 31) + this.path.hashCode()) * 31) + Double.doubleToLongBits(this.ratio);
    }

    public String toString() {
        return "Poster(language=" + this.language + ", path=" + this.path + ", ratio=" + this.ratio + ')';
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getPath() {
        return this.path;
    }

    public final double getRatio() {
        return this.ratio;
    }

    public Poster(String str, String str2, double d) {
        Intrinsics.checkNotNullParameter(str, "language");
        Intrinsics.checkNotNullParameter(str2, ArtworkProvider.PATH);
        this.language = str;
        this.path = str2;
        this.ratio = d;
    }

    public String language() {
        return this.language;
    }

    public String path() {
        return this.path;
    }
}
