package org.videolan.moviepedia.database.models;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/videolan/moviepedia/database/models/Person;", "", "moviepediaId", "", "name", "image", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getImage", "()Ljava/lang/String;", "getMoviepediaId", "getName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public final class Person {
    private final String image;
    private final String moviepediaId;
    private final String name;

    public static /* synthetic */ Person copy$default(Person person, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = person.moviepediaId;
        }
        if ((i & 2) != 0) {
            str2 = person.name;
        }
        if ((i & 4) != 0) {
            str3 = person.image;
        }
        return person.copy(str, str2, str3);
    }

    public final String component1() {
        return this.moviepediaId;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.image;
    }

    public final Person copy(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "moviepediaId");
        Intrinsics.checkNotNullParameter(str2, ContentDisposition.Parameters.Name);
        return new Person(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        return Intrinsics.areEqual((Object) this.moviepediaId, (Object) person.moviepediaId) && Intrinsics.areEqual((Object) this.name, (Object) person.name) && Intrinsics.areEqual((Object) this.image, (Object) person.image);
    }

    public int hashCode() {
        int hashCode = ((this.moviepediaId.hashCode() * 31) + this.name.hashCode()) * 31;
        String str = this.image;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "Person(moviepediaId=" + this.moviepediaId + ", name=" + this.name + ", image=" + this.image + ')';
    }

    public Person(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "moviepediaId");
        Intrinsics.checkNotNullParameter(str2, ContentDisposition.Parameters.Name);
        this.moviepediaId = str;
        this.name = str2;
        this.image = str3;
    }

    public final String getMoviepediaId() {
        return this.moviepediaId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getImage() {
        return this.image;
    }
}
