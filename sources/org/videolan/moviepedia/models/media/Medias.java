package org.videolan.moviepedia.models.media;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J=\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001c"}, d2 = {"Lorg/videolan/moviepedia/models/media/Medias;", "", "phrases", "", "Lorg/videolan/moviepedia/models/media/Phrase;", "resultCount", "", "results", "Lorg/videolan/moviepedia/models/media/MediaResult;", "total", "(Ljava/util/List;ILjava/util/List;I)V", "getPhrases", "()Ljava/util/List;", "getResultCount", "()I", "getResults", "getTotal", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaResult.kt */
public final class Medias {
    @Json(name = "phrases")
    private final List<Phrase> phrases;
    @Json(name = "resultCount")
    private final int resultCount;
    @Json(name = "results")
    private final List<MediaResult> results;
    @Json(name = "total")
    private final int total;

    public static /* synthetic */ Medias copy$default(Medias medias, List<Phrase> list, int i, List<MediaResult> list2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = medias.phrases;
        }
        if ((i3 & 2) != 0) {
            i = medias.resultCount;
        }
        if ((i3 & 4) != 0) {
            list2 = medias.results;
        }
        if ((i3 & 8) != 0) {
            i2 = medias.total;
        }
        return medias.copy(list, i, list2, i2);
    }

    public final List<Phrase> component1() {
        return this.phrases;
    }

    public final int component2() {
        return this.resultCount;
    }

    public final List<MediaResult> component3() {
        return this.results;
    }

    public final int component4() {
        return this.total;
    }

    public final Medias copy(List<Phrase> list, int i, List<MediaResult> list2, int i2) {
        Intrinsics.checkNotNullParameter(list, "phrases");
        Intrinsics.checkNotNullParameter(list2, "results");
        return new Medias(list, i, list2, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Medias)) {
            return false;
        }
        Medias medias = (Medias) obj;
        return Intrinsics.areEqual((Object) this.phrases, (Object) medias.phrases) && this.resultCount == medias.resultCount && Intrinsics.areEqual((Object) this.results, (Object) medias.results) && this.total == medias.total;
    }

    public int hashCode() {
        return (((((this.phrases.hashCode() * 31) + this.resultCount) * 31) + this.results.hashCode()) * 31) + this.total;
    }

    public String toString() {
        return "Medias(phrases=" + this.phrases + ", resultCount=" + this.resultCount + ", results=" + this.results + ", total=" + this.total + ')';
    }

    public Medias(List<Phrase> list, int i, List<MediaResult> list2, int i2) {
        Intrinsics.checkNotNullParameter(list, "phrases");
        Intrinsics.checkNotNullParameter(list2, "results");
        this.phrases = list;
        this.resultCount = i;
        this.results = list2;
        this.total = i2;
    }

    public final List<Phrase> getPhrases() {
        return this.phrases;
    }

    public final int getResultCount() {
        return this.resultCount;
    }

    public final List<MediaResult> getResults() {
        return this.results;
    }

    public final int getTotal() {
        return this.total;
    }
}
