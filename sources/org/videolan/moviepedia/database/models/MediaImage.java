package org.videolan.moviepedia.database.models;

import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/videolan/moviepedia/database/models/MediaImage;", "", "url", "", "mediaId", "imageType", "Lorg/videolan/moviepedia/database/models/MediaImageType;", "language", "(Ljava/lang/String;Ljava/lang/String;Lorg/videolan/moviepedia/database/models/MediaImageType;Ljava/lang/String;)V", "getImageType", "()Lorg/videolan/moviepedia/database/models/MediaImageType;", "getLanguage", "()Ljava/lang/String;", "getMediaId", "getUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadata.kt */
public final class MediaImage {
    private final MediaImageType imageType;
    private final String language;
    private final String mediaId;
    private final String url;

    public static /* synthetic */ MediaImage copy$default(MediaImage mediaImage, String str, String str2, MediaImageType mediaImageType, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaImage.url;
        }
        if ((i & 2) != 0) {
            str2 = mediaImage.mediaId;
        }
        if ((i & 4) != 0) {
            mediaImageType = mediaImage.imageType;
        }
        if ((i & 8) != 0) {
            str3 = mediaImage.language;
        }
        return mediaImage.copy(str, str2, mediaImageType, str3);
    }

    public final String component1() {
        return this.url;
    }

    public final String component2() {
        return this.mediaId;
    }

    public final MediaImageType component3() {
        return this.imageType;
    }

    public final String component4() {
        return this.language;
    }

    public final MediaImage copy(String str, String str2, MediaImageType mediaImageType, String str3) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        Intrinsics.checkNotNullParameter(str2, "mediaId");
        Intrinsics.checkNotNullParameter(mediaImageType, "imageType");
        Intrinsics.checkNotNullParameter(str3, "language");
        return new MediaImage(str, str2, mediaImageType, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaImage)) {
            return false;
        }
        MediaImage mediaImage = (MediaImage) obj;
        return Intrinsics.areEqual((Object) this.url, (Object) mediaImage.url) && Intrinsics.areEqual((Object) this.mediaId, (Object) mediaImage.mediaId) && this.imageType == mediaImage.imageType && Intrinsics.areEqual((Object) this.language, (Object) mediaImage.language);
    }

    public int hashCode() {
        return (((((this.url.hashCode() * 31) + this.mediaId.hashCode()) * 31) + this.imageType.hashCode()) * 31) + this.language.hashCode();
    }

    public String toString() {
        return "MediaImage(url=" + this.url + ", mediaId=" + this.mediaId + ", imageType=" + this.imageType + ", language=" + this.language + ')';
    }

    public MediaImage(String str, String str2, MediaImageType mediaImageType, String str3) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        Intrinsics.checkNotNullParameter(str2, "mediaId");
        Intrinsics.checkNotNullParameter(mediaImageType, "imageType");
        Intrinsics.checkNotNullParameter(str3, "language");
        this.url = str;
        this.mediaId = str2;
        this.imageType = mediaImageType;
        this.language = str3;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final MediaImageType getImageType() {
        return this.imageType;
    }

    public final String getLanguage() {
        return this.language;
    }
}
