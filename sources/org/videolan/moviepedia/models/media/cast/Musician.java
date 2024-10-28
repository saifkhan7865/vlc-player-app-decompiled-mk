package org.videolan.moviepedia.models.media.cast;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/videolan/moviepedia/models/media/cast/Musician;", "", "person", "Lorg/videolan/moviepedia/models/media/cast/Person;", "source", "", "(Lorg/videolan/moviepedia/models/media/cast/Person;Ljava/lang/String;)V", "getPerson", "()Lorg/videolan/moviepedia/models/media/cast/Person;", "getSource", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CastResult.kt */
public final class Musician {
    @Json(name = "person")
    private final Person person;
    @Json(name = "source")
    private final String source;

    public static /* synthetic */ Musician copy$default(Musician musician, Person person2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            person2 = musician.person;
        }
        if ((i & 2) != 0) {
            str = musician.source;
        }
        return musician.copy(person2, str);
    }

    public final Person component1() {
        return this.person;
    }

    public final String component2() {
        return this.source;
    }

    public final Musician copy(Person person2, String str) {
        Intrinsics.checkNotNullParameter(person2, "person");
        Intrinsics.checkNotNullParameter(str, "source");
        return new Musician(person2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Musician)) {
            return false;
        }
        Musician musician = (Musician) obj;
        return Intrinsics.areEqual((Object) this.person, (Object) musician.person) && Intrinsics.areEqual((Object) this.source, (Object) musician.source);
    }

    public int hashCode() {
        return (this.person.hashCode() * 31) + this.source.hashCode();
    }

    public String toString() {
        return "Musician(person=" + this.person + ", source=" + this.source + ')';
    }

    public Musician(Person person2, String str) {
        Intrinsics.checkNotNullParameter(person2, "person");
        Intrinsics.checkNotNullParameter(str, "source");
        this.person = person2;
        this.source = str;
    }

    public final Person getPerson() {
        return this.person;
    }

    public final String getSource() {
        return this.source;
    }
}
