package org.videolan.moviepedia.models.identify;

import com.squareup.moshi.Json;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/videolan/moviepedia/models/identify/Video;", "", "language", "", "type", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLanguage", "()Ljava/lang/String;", "getType", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public final class Video {
    @Json(name = "language")
    private final String language;
    @Json(name = "type")
    private final String type;
    @Json(name = "url")
    private final String url;

    public static /* synthetic */ Video copy$default(Video video, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = video.language;
        }
        if ((i & 2) != 0) {
            str2 = video.type;
        }
        if ((i & 4) != 0) {
            str3 = video.url;
        }
        return video.copy(str, str2, str3);
    }

    public final String component1() {
        return this.language;
    }

    public final String component2() {
        return this.type;
    }

    public final String component3() {
        return this.url;
    }

    public final Video copy(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "language");
        Intrinsics.checkNotNullParameter(str2, "type");
        Intrinsics.checkNotNullParameter(str3, RtspHeaders.Values.URL);
        return new Video(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Video)) {
            return false;
        }
        Video video = (Video) obj;
        return Intrinsics.areEqual((Object) this.language, (Object) video.language) && Intrinsics.areEqual((Object) this.type, (Object) video.type) && Intrinsics.areEqual((Object) this.url, (Object) video.url);
    }

    public int hashCode() {
        return (((this.language.hashCode() * 31) + this.type.hashCode()) * 31) + this.url.hashCode();
    }

    public String toString() {
        return "Video(language=" + this.language + ", type=" + this.type + ", url=" + this.url + ')';
    }

    public Video(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "language");
        Intrinsics.checkNotNullParameter(str2, "type");
        Intrinsics.checkNotNullParameter(str3, RtspHeaders.Values.URL);
        this.language = str;
        this.type = str2;
        this.url = str3;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }
}
