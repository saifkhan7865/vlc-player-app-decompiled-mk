package org.videolan.moviepedia.models.media.cast;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0007\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/videolan/moviepedia/models/media/cast/Actor;", "", "characters", "", "", "person", "Lorg/videolan/moviepedia/models/media/cast/Person;", "source", "(Ljava/util/List;Lorg/videolan/moviepedia/models/media/cast/Person;Ljava/lang/String;)V", "getCharacters", "()Ljava/util/List;", "getPerson", "()Lorg/videolan/moviepedia/models/media/cast/Person;", "getSource", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CastResult.kt */
public final class Actor {
    @Json(name = "characters")
    private final List<String> characters;
    @Json(name = "person")
    private final Person person;
    @Json(name = "source")
    private final String source;

    public static /* synthetic */ Actor copy$default(Actor actor, List<String> list, Person person2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = actor.characters;
        }
        if ((i & 2) != 0) {
            person2 = actor.person;
        }
        if ((i & 4) != 0) {
            str = actor.source;
        }
        return actor.copy(list, person2, str);
    }

    public final List<String> component1() {
        return this.characters;
    }

    public final Person component2() {
        return this.person;
    }

    public final String component3() {
        return this.source;
    }

    public final Actor copy(List<String> list, Person person2, String str) {
        Intrinsics.checkNotNullParameter(list, "characters");
        Intrinsics.checkNotNullParameter(person2, "person");
        Intrinsics.checkNotNullParameter(str, "source");
        return new Actor(list, person2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Actor)) {
            return false;
        }
        Actor actor = (Actor) obj;
        return Intrinsics.areEqual((Object) this.characters, (Object) actor.characters) && Intrinsics.areEqual((Object) this.person, (Object) actor.person) && Intrinsics.areEqual((Object) this.source, (Object) actor.source);
    }

    public int hashCode() {
        return (((this.characters.hashCode() * 31) + this.person.hashCode()) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "Actor(characters=" + this.characters + ", person=" + this.person + ", source=" + this.source + ')';
    }

    public Actor(List<String> list, Person person2, String str) {
        Intrinsics.checkNotNullParameter(list, "characters");
        Intrinsics.checkNotNullParameter(person2, "person");
        Intrinsics.checkNotNullParameter(str, "source");
        this.characters = list;
        this.person = person2;
        this.source = str;
    }

    public final List<String> getCharacters() {
        return this.characters;
    }

    public final Person getPerson() {
        return this.person;
    }

    public final String getSource() {
        return this.source;
    }
}
