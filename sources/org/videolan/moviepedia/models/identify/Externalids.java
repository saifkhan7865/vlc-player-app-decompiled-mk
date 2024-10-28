package org.videolan.moviepedia.models.identify;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/videolan/moviepedia/models/identify/Externalids;", "", "imdb", "", "themoviedb", "(Ljava/lang/String;Ljava/lang/String;)V", "getImdb", "()Ljava/lang/String;", "getThemoviedb", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public final class Externalids {
    @Json(name = "imdb")
    private final String imdb;
    @Json(name = "themoviedb")
    private final String themoviedb;

    public static /* synthetic */ Externalids copy$default(Externalids externalids, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = externalids.imdb;
        }
        if ((i & 2) != 0) {
            str2 = externalids.themoviedb;
        }
        return externalids.copy(str, str2);
    }

    public final String component1() {
        return this.imdb;
    }

    public final String component2() {
        return this.themoviedb;
    }

    public final Externalids copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "imdb");
        Intrinsics.checkNotNullParameter(str2, "themoviedb");
        return new Externalids(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Externalids)) {
            return false;
        }
        Externalids externalids = (Externalids) obj;
        return Intrinsics.areEqual((Object) this.imdb, (Object) externalids.imdb) && Intrinsics.areEqual((Object) this.themoviedb, (Object) externalids.themoviedb);
    }

    public int hashCode() {
        return (this.imdb.hashCode() * 31) + this.themoviedb.hashCode();
    }

    public String toString() {
        return "Externalids(imdb=" + this.imdb + ", themoviedb=" + this.themoviedb + ')';
    }

    public Externalids(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "imdb");
        Intrinsics.checkNotNullParameter(str2, "themoviedb");
        this.imdb = str;
        this.themoviedb = str2;
    }

    public final String getImdb() {
        return this.imdb;
    }

    public final String getThemoviedb() {
        return this.themoviedb;
    }
}
