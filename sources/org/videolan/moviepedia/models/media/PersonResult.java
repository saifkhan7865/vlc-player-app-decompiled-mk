package org.videolan.moviepedia.models.media;

import com.squareup.moshi.Json;
import io.ktor.http.ContentDisposition;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b3\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BÁ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003¢\u0006\u0002\u0010\u0019J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eHÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eHÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eHÆ\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eHÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\tHÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\tHÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eHÆ\u0003Jë\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u0003HÆ\u0001J\u0013\u0010?\u001a\u00020\t2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010A\u001a\u00020BHÖ\u0001J\t\u0010C\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0016\u0010\f\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010%R\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010%R\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010%R\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010%R\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010%R\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010%R\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0016\u0010\u0015\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001bR\u0016\u0010\u0016\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001bR\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0016\u0010\u0018\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001b¨\u0006D"}, d2 = {"Lorg/videolan/moviepedia/models/media/PersonResult;", "", "backdrop", "", "birthdate", "Ljava/util/Date;", "deathdate", "genre", "hasImage", "", "imageEndpoint", "imdbId", "imdbidMatched", "isActorOf", "", "isDirectorOf", "isMusicianOf", "isProducerOf", "isStarringIn", "isWriterOf", "name", "personId", "picto", "poster", "square", "(Ljava/lang/String;Ljava/util/Date;Ljava/util/Date;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBackdrop", "()Ljava/lang/String;", "getBirthdate", "()Ljava/util/Date;", "getDeathdate", "getGenre", "getHasImage", "()Z", "getImageEndpoint", "getImdbId", "getImdbidMatched", "()Ljava/util/List;", "getName", "getPersonId", "getPicto", "getPoster", "getSquare", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaResult.kt */
public final class PersonResult {
    @Json(name = "backdrop")
    private final String backdrop;
    @Json(name = "birthdate")
    private final Date birthdate;
    @Json(name = "deathdate")
    private final Date deathdate;
    @Json(name = "genre")
    private final String genre;
    @Json(name = "hasImage")
    private final boolean hasImage;
    @Json(name = "imageEndpoint")
    private final String imageEndpoint;
    @Json(name = "imdbId")
    private final String imdbId;
    @Json(name = "imdbid_matched")
    private final boolean imdbidMatched;
    @Json(name = "is_actor_of")
    private final List<Object> isActorOf;
    @Json(name = "is_director_of")
    private final List<String> isDirectorOf;
    @Json(name = "is_musician_of")
    private final List<Object> isMusicianOf;
    @Json(name = "is_producer_of")
    private final List<Object> isProducerOf;
    @Json(name = "is_starring_in")
    private final List<Object> isStarringIn;
    @Json(name = "is_writer_of")
    private final List<Object> isWriterOf;
    @Json(name = "name")
    private final String name;
    @Json(name = "personId")
    private final String personId;
    @Json(name = "picto")
    private final String picto;
    @Json(name = "poster")
    private final String poster;
    @Json(name = "square")
    private final String square;

    public static /* synthetic */ PersonResult copy$default(PersonResult personResult, String str, Date date, Date date2, String str2, boolean z, String str3, String str4, boolean z2, List list, List list2, List list3, List list4, List list5, List list6, String str5, String str6, String str7, String str8, String str9, int i, Object obj) {
        PersonResult personResult2 = personResult;
        int i2 = i;
        return personResult.copy((i2 & 1) != 0 ? personResult2.backdrop : str, (i2 & 2) != 0 ? personResult2.birthdate : date, (i2 & 4) != 0 ? personResult2.deathdate : date2, (i2 & 8) != 0 ? personResult2.genre : str2, (i2 & 16) != 0 ? personResult2.hasImage : z, (i2 & 32) != 0 ? personResult2.imageEndpoint : str3, (i2 & 64) != 0 ? personResult2.imdbId : str4, (i2 & 128) != 0 ? personResult2.imdbidMatched : z2, (i2 & 256) != 0 ? personResult2.isActorOf : list, (i2 & 512) != 0 ? personResult2.isDirectorOf : list2, (i2 & 1024) != 0 ? personResult2.isMusicianOf : list3, (i2 & 2048) != 0 ? personResult2.isProducerOf : list4, (i2 & 4096) != 0 ? personResult2.isStarringIn : list5, (i2 & 8192) != 0 ? personResult2.isWriterOf : list6, (i2 & 16384) != 0 ? personResult2.name : str5, (i2 & 32768) != 0 ? personResult2.personId : str6, (i2 & 65536) != 0 ? personResult2.picto : str7, (i2 & 131072) != 0 ? personResult2.poster : str8, (i2 & 262144) != 0 ? personResult2.square : str9);
    }

    public final String component1() {
        return this.backdrop;
    }

    public final List<String> component10() {
        return this.isDirectorOf;
    }

    public final List<Object> component11() {
        return this.isMusicianOf;
    }

    public final List<Object> component12() {
        return this.isProducerOf;
    }

    public final List<Object> component13() {
        return this.isStarringIn;
    }

    public final List<Object> component14() {
        return this.isWriterOf;
    }

    public final String component15() {
        return this.name;
    }

    public final String component16() {
        return this.personId;
    }

    public final String component17() {
        return this.picto;
    }

    public final String component18() {
        return this.poster;
    }

    public final String component19() {
        return this.square;
    }

    public final Date component2() {
        return this.birthdate;
    }

    public final Date component3() {
        return this.deathdate;
    }

    public final String component4() {
        return this.genre;
    }

    public final boolean component5() {
        return this.hasImage;
    }

    public final String component6() {
        return this.imageEndpoint;
    }

    public final String component7() {
        return this.imdbId;
    }

    public final boolean component8() {
        return this.imdbidMatched;
    }

    public final List<Object> component9() {
        return this.isActorOf;
    }

    public final PersonResult copy(String str, Date date, Date date2, String str2, boolean z, String str3, String str4, boolean z2, List<? extends Object> list, List<String> list2, List<? extends Object> list3, List<? extends Object> list4, List<? extends Object> list5, List<? extends Object> list6, String str5, String str6, String str7, String str8, String str9) {
        String str10 = str;
        Intrinsics.checkNotNullParameter(str10, "backdrop");
        Intrinsics.checkNotNullParameter(date, "birthdate");
        Intrinsics.checkNotNullParameter(date2, "deathdate");
        Intrinsics.checkNotNullParameter(str2, "genre");
        Intrinsics.checkNotNullParameter(str3, "imageEndpoint");
        Intrinsics.checkNotNullParameter(str4, "imdbId");
        Intrinsics.checkNotNullParameter(list, "isActorOf");
        Intrinsics.checkNotNullParameter(list2, "isDirectorOf");
        Intrinsics.checkNotNullParameter(list3, "isMusicianOf");
        Intrinsics.checkNotNullParameter(list4, "isProducerOf");
        Intrinsics.checkNotNullParameter(list5, "isStarringIn");
        Intrinsics.checkNotNullParameter(list6, "isWriterOf");
        Intrinsics.checkNotNullParameter(str5, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str6, "personId");
        Intrinsics.checkNotNullParameter(str7, "picto");
        Intrinsics.checkNotNullParameter(str8, "poster");
        Intrinsics.checkNotNullParameter(str9, "square");
        return new PersonResult(str10, date, date2, str2, z, str3, str4, z2, list, list2, list3, list4, list5, list6, str5, str6, str7, str8, str9);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonResult)) {
            return false;
        }
        PersonResult personResult = (PersonResult) obj;
        return Intrinsics.areEqual((Object) this.backdrop, (Object) personResult.backdrop) && Intrinsics.areEqual((Object) this.birthdate, (Object) personResult.birthdate) && Intrinsics.areEqual((Object) this.deathdate, (Object) personResult.deathdate) && Intrinsics.areEqual((Object) this.genre, (Object) personResult.genre) && this.hasImage == personResult.hasImage && Intrinsics.areEqual((Object) this.imageEndpoint, (Object) personResult.imageEndpoint) && Intrinsics.areEqual((Object) this.imdbId, (Object) personResult.imdbId) && this.imdbidMatched == personResult.imdbidMatched && Intrinsics.areEqual((Object) this.isActorOf, (Object) personResult.isActorOf) && Intrinsics.areEqual((Object) this.isDirectorOf, (Object) personResult.isDirectorOf) && Intrinsics.areEqual((Object) this.isMusicianOf, (Object) personResult.isMusicianOf) && Intrinsics.areEqual((Object) this.isProducerOf, (Object) personResult.isProducerOf) && Intrinsics.areEqual((Object) this.isStarringIn, (Object) personResult.isStarringIn) && Intrinsics.areEqual((Object) this.isWriterOf, (Object) personResult.isWriterOf) && Intrinsics.areEqual((Object) this.name, (Object) personResult.name) && Intrinsics.areEqual((Object) this.personId, (Object) personResult.personId) && Intrinsics.areEqual((Object) this.picto, (Object) personResult.picto) && Intrinsics.areEqual((Object) this.poster, (Object) personResult.poster) && Intrinsics.areEqual((Object) this.square, (Object) personResult.square);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((this.backdrop.hashCode() * 31) + this.birthdate.hashCode()) * 31) + this.deathdate.hashCode()) * 31) + this.genre.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.hasImage)) * 31) + this.imageEndpoint.hashCode()) * 31) + this.imdbId.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.imdbidMatched)) * 31) + this.isActorOf.hashCode()) * 31) + this.isDirectorOf.hashCode()) * 31) + this.isMusicianOf.hashCode()) * 31) + this.isProducerOf.hashCode()) * 31) + this.isStarringIn.hashCode()) * 31) + this.isWriterOf.hashCode()) * 31) + this.name.hashCode()) * 31) + this.personId.hashCode()) * 31) + this.picto.hashCode()) * 31) + this.poster.hashCode()) * 31) + this.square.hashCode();
    }

    public String toString() {
        return "PersonResult(backdrop=" + this.backdrop + ", birthdate=" + this.birthdate + ", deathdate=" + this.deathdate + ", genre=" + this.genre + ", hasImage=" + this.hasImage + ", imageEndpoint=" + this.imageEndpoint + ", imdbId=" + this.imdbId + ", imdbidMatched=" + this.imdbidMatched + ", isActorOf=" + this.isActorOf + ", isDirectorOf=" + this.isDirectorOf + ", isMusicianOf=" + this.isMusicianOf + ", isProducerOf=" + this.isProducerOf + ", isStarringIn=" + this.isStarringIn + ", isWriterOf=" + this.isWriterOf + ", name=" + this.name + ", personId=" + this.personId + ", picto=" + this.picto + ", poster=" + this.poster + ", square=" + this.square + ')';
    }

    public PersonResult(String str, Date date, Date date2, String str2, boolean z, String str3, String str4, boolean z2, List<? extends Object> list, List<String> list2, List<? extends Object> list3, List<? extends Object> list4, List<? extends Object> list5, List<? extends Object> list6, String str5, String str6, String str7, String str8, String str9) {
        String str10 = str;
        Date date3 = date;
        Date date4 = date2;
        String str11 = str2;
        String str12 = str3;
        String str13 = str4;
        List<? extends Object> list7 = list;
        List<String> list8 = list2;
        List<? extends Object> list9 = list3;
        List<? extends Object> list10 = list4;
        List<? extends Object> list11 = list5;
        List<? extends Object> list12 = list6;
        String str14 = str5;
        String str15 = str6;
        String str16 = str8;
        Intrinsics.checkNotNullParameter(str10, "backdrop");
        Intrinsics.checkNotNullParameter(date3, "birthdate");
        Intrinsics.checkNotNullParameter(date4, "deathdate");
        Intrinsics.checkNotNullParameter(str11, "genre");
        Intrinsics.checkNotNullParameter(str12, "imageEndpoint");
        Intrinsics.checkNotNullParameter(str13, "imdbId");
        Intrinsics.checkNotNullParameter(list7, "isActorOf");
        Intrinsics.checkNotNullParameter(list8, "isDirectorOf");
        Intrinsics.checkNotNullParameter(list9, "isMusicianOf");
        Intrinsics.checkNotNullParameter(list10, "isProducerOf");
        Intrinsics.checkNotNullParameter(list11, "isStarringIn");
        Intrinsics.checkNotNullParameter(list12, "isWriterOf");
        Intrinsics.checkNotNullParameter(str14, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str15, "personId");
        Intrinsics.checkNotNullParameter(str7, "picto");
        Intrinsics.checkNotNullParameter(str8, "poster");
        Intrinsics.checkNotNullParameter(str9, "square");
        this.backdrop = str10;
        this.birthdate = date3;
        this.deathdate = date4;
        this.genre = str11;
        this.hasImage = z;
        this.imageEndpoint = str12;
        this.imdbId = str13;
        this.imdbidMatched = z2;
        this.isActorOf = list7;
        this.isDirectorOf = list8;
        this.isMusicianOf = list9;
        this.isProducerOf = list10;
        this.isStarringIn = list11;
        this.isWriterOf = list12;
        this.name = str14;
        this.personId = str15;
        this.picto = str7;
        this.poster = str8;
        this.square = str9;
    }

    public final String getBackdrop() {
        return this.backdrop;
    }

    public final Date getBirthdate() {
        return this.birthdate;
    }

    public final Date getDeathdate() {
        return this.deathdate;
    }

    public final String getGenre() {
        return this.genre;
    }

    public final boolean getHasImage() {
        return this.hasImage;
    }

    public final String getImageEndpoint() {
        return this.imageEndpoint;
    }

    public final String getImdbId() {
        return this.imdbId;
    }

    public final boolean getImdbidMatched() {
        return this.imdbidMatched;
    }

    public final List<Object> isActorOf() {
        return this.isActorOf;
    }

    public final List<String> isDirectorOf() {
        return this.isDirectorOf;
    }

    public final List<Object> isMusicianOf() {
        return this.isMusicianOf;
    }

    public final List<Object> isProducerOf() {
        return this.isProducerOf;
    }

    public final List<Object> isStarringIn() {
        return this.isStarringIn;
    }

    public final List<Object> isWriterOf() {
        return this.isWriterOf;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPersonId() {
        return this.personId;
    }

    public final String getPicto() {
        return this.picto;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final String getSquare() {
        return this.square;
    }
}
