package org.videolan.moviepedia.models.media;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lorg/videolan/moviepedia/models/media/MoviepediaResults;", "", "medias", "Lorg/videolan/moviepedia/models/media/Medias;", "persons", "Lorg/videolan/moviepedia/models/media/Persons;", "(Lorg/videolan/moviepedia/models/media/Medias;Lorg/videolan/moviepedia/models/media/Persons;)V", "getMedias", "()Lorg/videolan/moviepedia/models/media/Medias;", "getPersons", "()Lorg/videolan/moviepedia/models/media/Persons;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaResult.kt */
public final class MoviepediaResults {
    @Json(name = "medias")
    private final Medias medias;
    @Json(name = "persons")
    private final Persons persons;

    public static /* synthetic */ MoviepediaResults copy$default(MoviepediaResults moviepediaResults, Medias medias2, Persons persons2, int i, Object obj) {
        if ((i & 1) != 0) {
            medias2 = moviepediaResults.medias;
        }
        if ((i & 2) != 0) {
            persons2 = moviepediaResults.persons;
        }
        return moviepediaResults.copy(medias2, persons2);
    }

    public final Medias component1() {
        return this.medias;
    }

    public final Persons component2() {
        return this.persons;
    }

    public final MoviepediaResults copy(Medias medias2, Persons persons2) {
        Intrinsics.checkNotNullParameter(medias2, "medias");
        Intrinsics.checkNotNullParameter(persons2, "persons");
        return new MoviepediaResults(medias2, persons2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoviepediaResults)) {
            return false;
        }
        MoviepediaResults moviepediaResults = (MoviepediaResults) obj;
        return Intrinsics.areEqual((Object) this.medias, (Object) moviepediaResults.medias) && Intrinsics.areEqual((Object) this.persons, (Object) moviepediaResults.persons);
    }

    public int hashCode() {
        return (this.medias.hashCode() * 31) + this.persons.hashCode();
    }

    public String toString() {
        return "MoviepediaResults(medias=" + this.medias + ", persons=" + this.persons + ')';
    }

    public MoviepediaResults(Medias medias2, Persons persons2) {
        Intrinsics.checkNotNullParameter(medias2, "medias");
        Intrinsics.checkNotNullParameter(persons2, "persons");
        this.medias = medias2;
        this.persons = persons2;
    }

    public final Medias getMedias() {
        return this.medias;
    }

    public final Persons getPersons() {
        return this.persons;
    }
}
