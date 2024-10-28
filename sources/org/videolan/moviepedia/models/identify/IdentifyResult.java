package org.videolan.moviepedia.models.identify;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.resolver.ResolverResult;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J%\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/videolan/moviepedia/models/identify/IdentifyResult;", "Lorg/videolan/moviepedia/models/resolver/ResolverResult;", "lucky", "Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "results", "", "(Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;Ljava/util/List;)V", "getLucky", "()Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "getResults", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public final class IdentifyResult extends ResolverResult {
    @Json(name = "lucky")
    private final MoviepediaMedia lucky;
    @Json(name = "results")
    private final List<MoviepediaMedia> results;

    public static /* synthetic */ IdentifyResult copy$default(IdentifyResult identifyResult, MoviepediaMedia moviepediaMedia, List<MoviepediaMedia> list, int i, Object obj) {
        if ((i & 1) != 0) {
            moviepediaMedia = identifyResult.lucky;
        }
        if ((i & 2) != 0) {
            list = identifyResult.results;
        }
        return identifyResult.copy(moviepediaMedia, list);
    }

    public final MoviepediaMedia component1() {
        return this.lucky;
    }

    public final List<MoviepediaMedia> component2() {
        return this.results;
    }

    public final IdentifyResult copy(MoviepediaMedia moviepediaMedia, List<MoviepediaMedia> list) {
        Intrinsics.checkNotNullParameter(list, "results");
        return new IdentifyResult(moviepediaMedia, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IdentifyResult)) {
            return false;
        }
        IdentifyResult identifyResult = (IdentifyResult) obj;
        return Intrinsics.areEqual((Object) this.lucky, (Object) identifyResult.lucky) && Intrinsics.areEqual((Object) this.results, (Object) identifyResult.results);
    }

    public int hashCode() {
        MoviepediaMedia moviepediaMedia = this.lucky;
        return ((moviepediaMedia == null ? 0 : moviepediaMedia.hashCode()) * 31) + this.results.hashCode();
    }

    public String toString() {
        return "IdentifyResult(lucky=" + this.lucky + ", results=" + this.results + ')';
    }

    public final MoviepediaMedia getLucky() {
        return this.lucky;
    }

    public final List<MoviepediaMedia> getResults() {
        return this.results;
    }

    public IdentifyResult(MoviepediaMedia moviepediaMedia, List<MoviepediaMedia> list) {
        Intrinsics.checkNotNullParameter(list, "results");
        this.lucky = moviepediaMedia;
        this.results = list;
    }

    public MoviepediaMedia lucky() {
        return this.lucky;
    }

    public List<MoviepediaMedia> results() {
        return this.results;
    }
}
