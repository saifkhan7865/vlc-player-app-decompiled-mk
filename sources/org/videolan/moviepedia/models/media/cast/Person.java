package org.videolan.moviepedia.models.media.cast;

import com.squareup.moshi.Json;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.resolver.ResolverPerson;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001c"}, d2 = {"Lorg/videolan/moviepedia/models/media/cast/Person;", "Lorg/videolan/moviepedia/models/resolver/ResolverPerson;", "imageEndpoint", "", "images", "Lorg/videolan/moviepedia/models/media/cast/Images;", "name", "personId", "(Ljava/lang/String;Lorg/videolan/moviepedia/models/media/cast/Images;Ljava/lang/String;Ljava/lang/String;)V", "getImageEndpoint", "()Ljava/lang/String;", "getImages", "()Lorg/videolan/moviepedia/models/media/cast/Images;", "getName", "getPersonId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "image", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CastResult.kt */
public final class Person extends ResolverPerson {
    @Json(name = "imageEndpoint")
    private final String imageEndpoint;
    @Json(name = "images")
    private final Images images;
    @Json(name = "name")
    private final String name;
    @Json(name = "personId")
    private final String personId;

    public static /* synthetic */ Person copy$default(Person person, String str, Images images2, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = person.imageEndpoint;
        }
        if ((i & 2) != 0) {
            images2 = person.images;
        }
        if ((i & 4) != 0) {
            str2 = person.name;
        }
        if ((i & 8) != 0) {
            str3 = person.personId;
        }
        return person.copy(str, images2, str2, str3);
    }

    public final String component1() {
        return this.imageEndpoint;
    }

    public final Images component2() {
        return this.images;
    }

    public final String component3() {
        return this.name;
    }

    public final String component4() {
        return this.personId;
    }

    public final Person copy(String str, Images images2, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "imageEndpoint");
        Intrinsics.checkNotNullParameter(str2, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str3, "personId");
        return new Person(str, images2, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        return Intrinsics.areEqual((Object) this.imageEndpoint, (Object) person.imageEndpoint) && Intrinsics.areEqual((Object) this.images, (Object) person.images) && Intrinsics.areEqual((Object) this.name, (Object) person.name) && Intrinsics.areEqual((Object) this.personId, (Object) person.personId);
    }

    public int hashCode() {
        int hashCode = this.imageEndpoint.hashCode() * 31;
        Images images2 = this.images;
        return ((((hashCode + (images2 == null ? 0 : images2.hashCode())) * 31) + this.name.hashCode()) * 31) + this.personId.hashCode();
    }

    public String toString() {
        return "Person(imageEndpoint=" + this.imageEndpoint + ", images=" + this.images + ", name=" + this.name + ", personId=" + this.personId + ')';
    }

    public final String getImageEndpoint() {
        return this.imageEndpoint;
    }

    public final Images getImages() {
        return this.images;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPersonId() {
        return this.personId;
    }

    public Person(String str, Images images2, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "imageEndpoint");
        Intrinsics.checkNotNullParameter(str2, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str3, "personId");
        this.imageEndpoint = str;
        this.images = images2;
        this.name = str2;
        this.personId = str3;
    }

    public String name() {
        return this.name;
    }

    public String image() {
        List<Profile> profiles;
        Images images2 = this.images;
        if (images2 == null || (profiles = images2.getProfiles()) == null || profiles.isEmpty()) {
            return null;
        }
        return this.imageEndpoint + "img" + this.images.getProfiles().get(0).getPath();
    }

    public String personId() {
        return this.personId;
    }
}
