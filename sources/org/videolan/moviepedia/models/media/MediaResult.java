package org.videolan.moviepedia.models.media;

import com.squareup.moshi.Json;
import java.util.Date;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.identify.Images;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b,\b\b\u0018\u00002\u00020\u0001Bi\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\u0001¢\u0006\u0002\u0010\u0013J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\tHÆ\u0003J\t\u0010)\u001a\u00020\u0001HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003J\t\u0010,\u001a\u00020\tHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\t\u00100\u001a\u00020\tHÆ\u0003J\t\u00101\u001a\u00020\tHÆ\u0003J\u0001\u00102\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u0001HÆ\u0001J\u0013\u00103\u001a\u00020\u00052\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\u0007HÖ\u0001J\t\u00106\u001a\u00020\tHÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\f\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0016\u0010\r\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0016\u0010\u000e\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0016\u0010\u000f\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0016\u0010\u0010\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0016\u0010\u0011\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0016\u0010\u0012\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u00067"}, d2 = {"Lorg/videolan/moviepedia/models/media/MediaResult;", "", "date", "Ljava/util/Date;", "followed", "", "globalRating", "", "imageEndpoint", "", "images", "Lorg/videolan/moviepedia/models/identify/Images;", "mediaId", "showId", "showTitle", "summary", "title", "type", "wished", "(Ljava/util/Date;ZILjava/lang/String;Lorg/videolan/moviepedia/models/identify/Images;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getDate", "()Ljava/util/Date;", "getFollowed", "()Z", "getGlobalRating", "()I", "getImageEndpoint", "()Ljava/lang/String;", "getImages", "()Lorg/videolan/moviepedia/models/identify/Images;", "getMediaId", "getShowId", "getShowTitle", "getSummary", "getTitle", "getType", "getWished", "()Ljava/lang/Object;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaResult.kt */
public final class MediaResult {
    @Json(name = "date")
    private final Date date;
    @Json(name = "followed")
    private final boolean followed;
    @Json(name = "globalRating")
    private final int globalRating;
    @Json(name = "imageEndpoint")
    private final String imageEndpoint;
    @Json(name = "images")
    private final Images images;
    @Json(name = "mediaId")
    private final String mediaId;
    @Json(name = "showId")
    private final String showId;
    @Json(name = "showTitle")
    private final String showTitle;
    @Json(name = "summary")
    private final String summary;
    @Json(name = "title")
    private final String title;
    @Json(name = "type")
    private final String type;
    @Json(name = "wished")
    private final Object wished;

    public static /* synthetic */ MediaResult copy$default(MediaResult mediaResult, Date date2, boolean z, int i, String str, Images images2, String str2, String str3, String str4, String str5, String str6, String str7, Object obj, int i2, Object obj2) {
        MediaResult mediaResult2 = mediaResult;
        int i3 = i2;
        return mediaResult.copy((i3 & 1) != 0 ? mediaResult2.date : date2, (i3 & 2) != 0 ? mediaResult2.followed : z, (i3 & 4) != 0 ? mediaResult2.globalRating : i, (i3 & 8) != 0 ? mediaResult2.imageEndpoint : str, (i3 & 16) != 0 ? mediaResult2.images : images2, (i3 & 32) != 0 ? mediaResult2.mediaId : str2, (i3 & 64) != 0 ? mediaResult2.showId : str3, (i3 & 128) != 0 ? mediaResult2.showTitle : str4, (i3 & 256) != 0 ? mediaResult2.summary : str5, (i3 & 512) != 0 ? mediaResult2.title : str6, (i3 & 1024) != 0 ? mediaResult2.type : str7, (i3 & 2048) != 0 ? mediaResult2.wished : obj);
    }

    public final Date component1() {
        return this.date;
    }

    public final String component10() {
        return this.title;
    }

    public final String component11() {
        return this.type;
    }

    public final Object component12() {
        return this.wished;
    }

    public final boolean component2() {
        return this.followed;
    }

    public final int component3() {
        return this.globalRating;
    }

    public final String component4() {
        return this.imageEndpoint;
    }

    public final Images component5() {
        return this.images;
    }

    public final String component6() {
        return this.mediaId;
    }

    public final String component7() {
        return this.showId;
    }

    public final String component8() {
        return this.showTitle;
    }

    public final String component9() {
        return this.summary;
    }

    public final MediaResult copy(Date date2, boolean z, int i, String str, Images images2, String str2, String str3, String str4, String str5, String str6, String str7, Object obj) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, "imageEndpoint");
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "mediaId");
        String str10 = str3;
        Intrinsics.checkNotNullParameter(str10, "showId");
        String str11 = str4;
        Intrinsics.checkNotNullParameter(str11, "showTitle");
        String str12 = str5;
        Intrinsics.checkNotNullParameter(str12, "summary");
        String str13 = str6;
        Intrinsics.checkNotNullParameter(str13, "title");
        String str14 = str7;
        Intrinsics.checkNotNullParameter(str14, "type");
        Object obj2 = obj;
        Intrinsics.checkNotNullParameter(obj2, "wished");
        return new MediaResult(date2, z, i, str8, images2, str9, str10, str11, str12, str13, str14, obj2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaResult)) {
            return false;
        }
        MediaResult mediaResult = (MediaResult) obj;
        return Intrinsics.areEqual((Object) this.date, (Object) mediaResult.date) && this.followed == mediaResult.followed && this.globalRating == mediaResult.globalRating && Intrinsics.areEqual((Object) this.imageEndpoint, (Object) mediaResult.imageEndpoint) && Intrinsics.areEqual((Object) this.images, (Object) mediaResult.images) && Intrinsics.areEqual((Object) this.mediaId, (Object) mediaResult.mediaId) && Intrinsics.areEqual((Object) this.showId, (Object) mediaResult.showId) && Intrinsics.areEqual((Object) this.showTitle, (Object) mediaResult.showTitle) && Intrinsics.areEqual((Object) this.summary, (Object) mediaResult.summary) && Intrinsics.areEqual((Object) this.title, (Object) mediaResult.title) && Intrinsics.areEqual((Object) this.type, (Object) mediaResult.type) && Intrinsics.areEqual(this.wished, mediaResult.wished);
    }

    public int hashCode() {
        Date date2 = this.date;
        int i = 0;
        int hashCode = (((((((date2 == null ? 0 : date2.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.followed)) * 31) + this.globalRating) * 31) + this.imageEndpoint.hashCode()) * 31;
        Images images2 = this.images;
        if (images2 != null) {
            i = images2.hashCode();
        }
        return ((((((((((((((hashCode + i) * 31) + this.mediaId.hashCode()) * 31) + this.showId.hashCode()) * 31) + this.showTitle.hashCode()) * 31) + this.summary.hashCode()) * 31) + this.title.hashCode()) * 31) + this.type.hashCode()) * 31) + this.wished.hashCode();
    }

    public String toString() {
        return "MediaResult(date=" + this.date + ", followed=" + this.followed + ", globalRating=" + this.globalRating + ", imageEndpoint=" + this.imageEndpoint + ", images=" + this.images + ", mediaId=" + this.mediaId + ", showId=" + this.showId + ", showTitle=" + this.showTitle + ", summary=" + this.summary + ", title=" + this.title + ", type=" + this.type + ", wished=" + this.wished + ')';
    }

    public MediaResult(Date date2, boolean z, int i, String str, Images images2, String str2, String str3, String str4, String str5, String str6, String str7, Object obj) {
        Intrinsics.checkNotNullParameter(str, "imageEndpoint");
        Intrinsics.checkNotNullParameter(str2, "mediaId");
        Intrinsics.checkNotNullParameter(str3, "showId");
        Intrinsics.checkNotNullParameter(str4, "showTitle");
        Intrinsics.checkNotNullParameter(str5, "summary");
        Intrinsics.checkNotNullParameter(str6, "title");
        Intrinsics.checkNotNullParameter(str7, "type");
        Intrinsics.checkNotNullParameter(obj, "wished");
        this.date = date2;
        this.followed = z;
        this.globalRating = i;
        this.imageEndpoint = str;
        this.images = images2;
        this.mediaId = str2;
        this.showId = str3;
        this.showTitle = str4;
        this.summary = str5;
        this.title = str6;
        this.type = str7;
        this.wished = obj;
    }

    public final Date getDate() {
        return this.date;
    }

    public final boolean getFollowed() {
        return this.followed;
    }

    public final int getGlobalRating() {
        return this.globalRating;
    }

    public final String getImageEndpoint() {
        return this.imageEndpoint;
    }

    public final Images getImages() {
        return this.images;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final String getShowId() {
        return this.showId;
    }

    public final String getShowTitle() {
        return this.showTitle;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public final Object getWished() {
        return this.wished;
    }
}
