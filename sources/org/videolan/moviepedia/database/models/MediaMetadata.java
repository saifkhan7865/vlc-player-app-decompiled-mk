package org.videolan.moviepedia.database.models;

import java.util.Date;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b5\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\f¢\u0006\u0002\u0010\u0017J\t\u00105\u001a\u00020\u0003HÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010 J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0015HÆ\u0003J\t\u0010;\u001a\u00020\fHÆ\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010*J\t\u0010=\u001a\u00020\u0007HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\fHÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010 J®\u0001\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\fHÆ\u0001¢\u0006\u0002\u0010EJ\u0013\u0010F\u001a\u00020\u00152\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020\u000fHÖ\u0001J\t\u0010I\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0012\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0019\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0011\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001cR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006X\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0016\u0010\u0016\u001a\u00020\f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0019R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010(R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b.\u0010 R \u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0019\"\u0004\b0\u0010\u001cR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0019R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0019R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104¨\u0006J"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaMetadata;", "", "moviepediaId", "", "mlId", "", "type", "Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "title", "summary", "genres", "releaseDate", "Ljava/util/Date;", "countries", "season", "", "episode", "currentPoster", "currentBackdrop", "showId", "hasCast", "", "insertDate", "(Ljava/lang/String;Ljava/lang/Long;Lorg/videolan/moviepedia/database/models/MediaMetadataType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Date;)V", "getCountries", "()Ljava/lang/String;", "getCurrentBackdrop", "setCurrentBackdrop", "(Ljava/lang/String;)V", "getCurrentPoster", "setCurrentPoster", "getEpisode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getGenres", "getHasCast", "()Z", "setHasCast", "(Z)V", "getInsertDate", "()Ljava/util/Date;", "getMlId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMoviepediaId", "getReleaseDate", "getSeason", "getShowId", "setShowId", "getSummary", "getTitle", "getType", "()Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Long;Lorg/videolan/moviepedia/database/models/MediaMetadataType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Date;)Lorg/videolan/moviepedia/database/models/MediaMetadata;", "equals", "other", "hashCode", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public final class MediaMetadata {
    private final String countries;
    private String currentBackdrop;
    private String currentPoster;
    private final Integer episode;
    private final String genres;
    private boolean hasCast;
    private final Date insertDate;
    private final Long mlId;
    private final String moviepediaId;
    private final Date releaseDate;
    private final Integer season;
    private String showId;
    private final String summary;
    private final String title;
    private final MediaMetadataType type;

    public static /* synthetic */ MediaMetadata copy$default(MediaMetadata mediaMetadata, String str, Long l, MediaMetadataType mediaMetadataType, String str2, String str3, String str4, Date date, String str5, Integer num, Integer num2, String str6, String str7, String str8, boolean z, Date date2, int i, Object obj) {
        MediaMetadata mediaMetadata2 = mediaMetadata;
        int i2 = i;
        return mediaMetadata.copy((i2 & 1) != 0 ? mediaMetadata2.moviepediaId : str, (i2 & 2) != 0 ? mediaMetadata2.mlId : l, (i2 & 4) != 0 ? mediaMetadata2.type : mediaMetadataType, (i2 & 8) != 0 ? mediaMetadata2.title : str2, (i2 & 16) != 0 ? mediaMetadata2.summary : str3, (i2 & 32) != 0 ? mediaMetadata2.genres : str4, (i2 & 64) != 0 ? mediaMetadata2.releaseDate : date, (i2 & 128) != 0 ? mediaMetadata2.countries : str5, (i2 & 256) != 0 ? mediaMetadata2.season : num, (i2 & 512) != 0 ? mediaMetadata2.episode : num2, (i2 & 1024) != 0 ? mediaMetadata2.currentPoster : str6, (i2 & 2048) != 0 ? mediaMetadata2.currentBackdrop : str7, (i2 & 4096) != 0 ? mediaMetadata2.showId : str8, (i2 & 8192) != 0 ? mediaMetadata2.hasCast : z, (i2 & 16384) != 0 ? mediaMetadata2.insertDate : date2);
    }

    public final String component1() {
        return this.moviepediaId;
    }

    public final Integer component10() {
        return this.episode;
    }

    public final String component11() {
        return this.currentPoster;
    }

    public final String component12() {
        return this.currentBackdrop;
    }

    public final String component13() {
        return this.showId;
    }

    public final boolean component14() {
        return this.hasCast;
    }

    public final Date component15() {
        return this.insertDate;
    }

    public final Long component2() {
        return this.mlId;
    }

    public final MediaMetadataType component3() {
        return this.type;
    }

    public final String component4() {
        return this.title;
    }

    public final String component5() {
        return this.summary;
    }

    public final String component6() {
        return this.genres;
    }

    public final Date component7() {
        return this.releaseDate;
    }

    public final String component8() {
        return this.countries;
    }

    public final Integer component9() {
        return this.season;
    }

    public final MediaMetadata copy(String str, Long l, MediaMetadataType mediaMetadataType, String str2, String str3, String str4, Date date, String str5, Integer num, Integer num2, String str6, String str7, String str8, boolean z, Date date2) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "moviepediaId");
        MediaMetadataType mediaMetadataType2 = mediaMetadataType;
        Intrinsics.checkNotNullParameter(mediaMetadataType2, "type");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "title");
        String str11 = str3;
        Intrinsics.checkNotNullParameter(str11, "summary");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "genres");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "countries");
        String str14 = str6;
        Intrinsics.checkNotNullParameter(str14, "currentPoster");
        String str15 = str7;
        Intrinsics.checkNotNullParameter(str15, "currentBackdrop");
        Intrinsics.checkNotNullParameter(date2, "insertDate");
        return new MediaMetadata(str9, l, mediaMetadataType2, str10, str11, str12, date, str13, num, num2, str14, str15, str8, z, date2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return Intrinsics.areEqual((Object) this.moviepediaId, (Object) mediaMetadata.moviepediaId) && Intrinsics.areEqual((Object) this.mlId, (Object) mediaMetadata.mlId) && this.type == mediaMetadata.type && Intrinsics.areEqual((Object) this.title, (Object) mediaMetadata.title) && Intrinsics.areEqual((Object) this.summary, (Object) mediaMetadata.summary) && Intrinsics.areEqual((Object) this.genres, (Object) mediaMetadata.genres) && Intrinsics.areEqual((Object) this.releaseDate, (Object) mediaMetadata.releaseDate) && Intrinsics.areEqual((Object) this.countries, (Object) mediaMetadata.countries) && Intrinsics.areEqual((Object) this.season, (Object) mediaMetadata.season) && Intrinsics.areEqual((Object) this.episode, (Object) mediaMetadata.episode) && Intrinsics.areEqual((Object) this.currentPoster, (Object) mediaMetadata.currentPoster) && Intrinsics.areEqual((Object) this.currentBackdrop, (Object) mediaMetadata.currentBackdrop) && Intrinsics.areEqual((Object) this.showId, (Object) mediaMetadata.showId) && this.hasCast == mediaMetadata.hasCast && Intrinsics.areEqual((Object) this.insertDate, (Object) mediaMetadata.insertDate);
    }

    public int hashCode() {
        int hashCode = this.moviepediaId.hashCode() * 31;
        Long l = this.mlId;
        int i = 0;
        int hashCode2 = (((((((((hashCode + (l == null ? 0 : l.hashCode())) * 31) + this.type.hashCode()) * 31) + this.title.hashCode()) * 31) + this.summary.hashCode()) * 31) + this.genres.hashCode()) * 31;
        Date date = this.releaseDate;
        int hashCode3 = (((hashCode2 + (date == null ? 0 : date.hashCode())) * 31) + this.countries.hashCode()) * 31;
        Integer num = this.season;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int hashCode5 = (((((hashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.currentPoster.hashCode()) * 31) + this.currentBackdrop.hashCode()) * 31;
        String str = this.showId;
        if (str != null) {
            i = str.hashCode();
        }
        return ((((hashCode5 + i) * 31) + UInt$$ExternalSyntheticBackport0.m(this.hasCast)) * 31) + this.insertDate.hashCode();
    }

    public String toString() {
        return "MediaMetadata(moviepediaId=" + this.moviepediaId + ", mlId=" + this.mlId + ", type=" + this.type + ", title=" + this.title + ", summary=" + this.summary + ", genres=" + this.genres + ", releaseDate=" + this.releaseDate + ", countries=" + this.countries + ", season=" + this.season + ", episode=" + this.episode + ", currentPoster=" + this.currentPoster + ", currentBackdrop=" + this.currentBackdrop + ", showId=" + this.showId + ", hasCast=" + this.hasCast + ", insertDate=" + this.insertDate + ')';
    }

    public MediaMetadata(String str, Long l, MediaMetadataType mediaMetadataType, String str2, String str3, String str4, Date date, String str5, Integer num, Integer num2, String str6, String str7, String str8, boolean z, Date date2) {
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        String str13 = str7;
        Date date3 = date2;
        Intrinsics.checkNotNullParameter(str, "moviepediaId");
        Intrinsics.checkNotNullParameter(mediaMetadataType, "type");
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(str9, "summary");
        Intrinsics.checkNotNullParameter(str10, "genres");
        Intrinsics.checkNotNullParameter(str11, "countries");
        Intrinsics.checkNotNullParameter(str12, "currentPoster");
        Intrinsics.checkNotNullParameter(str13, "currentBackdrop");
        Intrinsics.checkNotNullParameter(date3, "insertDate");
        this.moviepediaId = str;
        this.mlId = l;
        this.type = mediaMetadataType;
        this.title = str2;
        this.summary = str9;
        this.genres = str10;
        this.releaseDate = date;
        this.countries = str11;
        this.season = num;
        this.episode = num2;
        this.currentPoster = str12;
        this.currentBackdrop = str13;
        this.showId = str8;
        this.hasCast = z;
        this.insertDate = date3;
    }

    public final String getMoviepediaId() {
        return this.moviepediaId;
    }

    public final Long getMlId() {
        return this.mlId;
    }

    public final MediaMetadataType getType() {
        return this.type;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final String getGenres() {
        return this.genres;
    }

    public final Date getReleaseDate() {
        return this.releaseDate;
    }

    public final String getCountries() {
        return this.countries;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getCurrentPoster() {
        return this.currentPoster;
    }

    public final void setCurrentPoster(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currentPoster = str;
    }

    public final String getCurrentBackdrop() {
        return this.currentBackdrop;
    }

    public final void setCurrentBackdrop(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currentBackdrop = str;
    }

    public final String getShowId() {
        return this.showId;
    }

    public final void setShowId(String str) {
        this.showId = str;
    }

    public final boolean getHasCast() {
        return this.hasCast;
    }

    public final void setHasCast(boolean z) {
        this.hasCast = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MediaMetadata(String str, Long l, MediaMetadataType mediaMetadataType, String str2, String str3, String str4, Date date, String str5, Integer num, Integer num2, String str6, String str7, String str8, boolean z, Date date2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, l, mediaMetadataType, str2, str3, str4, date, str5, num, num2, str6, str7, str8, z, (i & 16384) != 0 ? new Date() : date2);
    }

    public final Date getInsertDate() {
        return this.insertDate;
    }
}
