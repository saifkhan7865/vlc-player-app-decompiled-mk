package org.videolan.moviepedia.models.identify;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.resolver.ResolverBatchResult;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\b\u0010\u0007\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lorg/videolan/moviepedia/models/identify/IdentifyBatchResult;", "Lorg/videolan/moviepedia/models/resolver/ResolverBatchResult;", "id", "", "lucky", "Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "(Ljava/lang/String;Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;)V", "getId", "()Ljava/lang/String;", "getLucky", "()Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "component1", "component2", "copy", "equals", "", "other", "", "", "getMedia", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyBatchResult.kt */
public final class IdentifyBatchResult extends ResolverBatchResult {
    private final String id;
    private final MoviepediaMedia lucky;

    public static /* synthetic */ IdentifyBatchResult copy$default(IdentifyBatchResult identifyBatchResult, String str, MoviepediaMedia moviepediaMedia, int i, Object obj) {
        if ((i & 1) != 0) {
            str = identifyBatchResult.id;
        }
        if ((i & 2) != 0) {
            moviepediaMedia = identifyBatchResult.lucky;
        }
        return identifyBatchResult.copy(str, moviepediaMedia);
    }

    public final String component1() {
        return this.id;
    }

    public final MoviepediaMedia component2() {
        return this.lucky;
    }

    public final IdentifyBatchResult copy(@Json(name = "id") String str, @Json(name = "lucky") MoviepediaMedia moviepediaMedia) {
        Intrinsics.checkNotNullParameter(str, "id");
        return new IdentifyBatchResult(str, moviepediaMedia);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IdentifyBatchResult)) {
            return false;
        }
        IdentifyBatchResult identifyBatchResult = (IdentifyBatchResult) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) identifyBatchResult.id) && Intrinsics.areEqual((Object) this.lucky, (Object) identifyBatchResult.lucky);
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        MoviepediaMedia moviepediaMedia = this.lucky;
        return hashCode + (moviepediaMedia == null ? 0 : moviepediaMedia.hashCode());
    }

    public String toString() {
        return "IdentifyBatchResult(id=" + this.id + ", lucky=" + this.lucky + ')';
    }

    /* renamed from: getId  reason: collision with other method in class */
    public final String m2433getId() {
        return this.id;
    }

    public final MoviepediaMedia getLucky() {
        return this.lucky;
    }

    public IdentifyBatchResult(@Json(name = "id") String str, @Json(name = "lucky") MoviepediaMedia moviepediaMedia) {
        Intrinsics.checkNotNullParameter(str, "id");
        this.id = str;
        this.lucky = moviepediaMedia;
    }

    public long getId() {
        return Long.parseLong(this.id);
    }

    public MoviepediaMedia getMedia() {
        return this.lucky;
    }
}
