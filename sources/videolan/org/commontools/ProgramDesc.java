package videolan.org.commontools;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\u0006HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\u000bHÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003J\t\u0010-\u001a\u00020\u000bHÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\u000bHÖ\u0001J\t\u00104\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001c¨\u00065"}, d2 = {"Lvideolan/org/commontools/ProgramDesc;", "", "channelId", "", "id", "title", "", "description", "artUri", "Landroid/net/Uri;", "duration", "", "time", "width", "height", "appId", "contentId", "previewVideoUri", "(JJLjava/lang/String;Ljava/lang/String;Landroid/net/Uri;IIIILjava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V", "getAppId", "()Ljava/lang/String;", "getArtUri", "()Landroid/net/Uri;", "getChannelId", "()J", "getContentId", "getDescription", "getDuration", "()I", "getHeight", "getId", "getPreviewVideoUri", "getTime", "getTitle", "getWidth", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvChannelUtils.kt */
public final class ProgramDesc {
    private final String appId;
    private final Uri artUri;
    private final long channelId;
    private final String contentId;
    private final String description;
    private final int duration;
    private final int height;
    private final long id;
    private final Uri previewVideoUri;
    private final int time;
    private final String title;
    private final int width;

    public static /* synthetic */ ProgramDesc copy$default(ProgramDesc programDesc, long j, long j2, String str, String str2, Uri uri, int i, int i2, int i3, int i4, String str3, String str4, Uri uri2, int i5, Object obj) {
        ProgramDesc programDesc2 = programDesc;
        int i6 = i5;
        return programDesc.copy((i6 & 1) != 0 ? programDesc2.channelId : j, (i6 & 2) != 0 ? programDesc2.id : j2, (i6 & 4) != 0 ? programDesc2.title : str, (i6 & 8) != 0 ? programDesc2.description : str2, (i6 & 16) != 0 ? programDesc2.artUri : uri, (i6 & 32) != 0 ? programDesc2.duration : i, (i6 & 64) != 0 ? programDesc2.time : i2, (i6 & 128) != 0 ? programDesc2.width : i3, (i6 & 256) != 0 ? programDesc2.height : i4, (i6 & 512) != 0 ? programDesc2.appId : str3, (i6 & 1024) != 0 ? programDesc2.contentId : str4, (i6 & 2048) != 0 ? programDesc2.previewVideoUri : uri2);
    }

    public final long component1() {
        return this.channelId;
    }

    public final String component10() {
        return this.appId;
    }

    public final String component11() {
        return this.contentId;
    }

    public final Uri component12() {
        return this.previewVideoUri;
    }

    public final long component2() {
        return this.id;
    }

    public final String component3() {
        return this.title;
    }

    public final String component4() {
        return this.description;
    }

    public final Uri component5() {
        return this.artUri;
    }

    public final int component6() {
        return this.duration;
    }

    public final int component7() {
        return this.time;
    }

    public final int component8() {
        return this.width;
    }

    public final int component9() {
        return this.height;
    }

    public final ProgramDesc copy(long j, long j2, String str, String str2, Uri uri, int i, int i2, int i3, int i4, String str3, String str4, Uri uri2) {
        String str5 = str;
        Intrinsics.checkNotNullParameter(str5, "title");
        Uri uri3 = uri;
        Intrinsics.checkNotNullParameter(uri3, "artUri");
        String str6 = str3;
        Intrinsics.checkNotNullParameter(str6, "appId");
        String str7 = str4;
        Intrinsics.checkNotNullParameter(str7, TvChannelUtilsKt.TV_CHANNEL_QUERY_VIDEO_ID);
        return new ProgramDesc(j, j2, str5, str2, uri3, i, i2, i3, i4, str6, str7, uri2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProgramDesc)) {
            return false;
        }
        ProgramDesc programDesc = (ProgramDesc) obj;
        return this.channelId == programDesc.channelId && this.id == programDesc.id && Intrinsics.areEqual((Object) this.title, (Object) programDesc.title) && Intrinsics.areEqual((Object) this.description, (Object) programDesc.description) && Intrinsics.areEqual((Object) this.artUri, (Object) programDesc.artUri) && this.duration == programDesc.duration && this.time == programDesc.time && this.width == programDesc.width && this.height == programDesc.height && Intrinsics.areEqual((Object) this.appId, (Object) programDesc.appId) && Intrinsics.areEqual((Object) this.contentId, (Object) programDesc.contentId) && Intrinsics.areEqual((Object) this.previewVideoUri, (Object) programDesc.previewVideoUri);
    }

    public int hashCode() {
        int m = ((((UInt$$ExternalSyntheticBackport0.m(this.channelId) * 31) + UInt$$ExternalSyntheticBackport0.m(this.id)) * 31) + this.title.hashCode()) * 31;
        String str = this.description;
        int i = 0;
        int hashCode = (((((((((((((((m + (str == null ? 0 : str.hashCode())) * 31) + this.artUri.hashCode()) * 31) + this.duration) * 31) + this.time) * 31) + this.width) * 31) + this.height) * 31) + this.appId.hashCode()) * 31) + this.contentId.hashCode()) * 31;
        Uri uri = this.previewVideoUri;
        if (uri != null) {
            i = uri.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "ProgramDesc(channelId=" + this.channelId + ", id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", artUri=" + this.artUri + ", duration=" + this.duration + ", time=" + this.time + ", width=" + this.width + ", height=" + this.height + ", appId=" + this.appId + ", contentId=" + this.contentId + ", previewVideoUri=" + this.previewVideoUri + ')';
    }

    public ProgramDesc(long j, long j2, String str, String str2, Uri uri, int i, int i2, int i3, int i4, String str3, String str4, Uri uri2) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(uri, "artUri");
        Intrinsics.checkNotNullParameter(str3, "appId");
        Intrinsics.checkNotNullParameter(str4, TvChannelUtilsKt.TV_CHANNEL_QUERY_VIDEO_ID);
        this.channelId = j;
        this.id = j2;
        this.title = str;
        this.description = str2;
        this.artUri = uri;
        this.duration = i;
        this.time = i2;
        this.width = i3;
        this.height = i4;
        this.appId = str3;
        this.contentId = str4;
        this.previewVideoUri = uri2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ProgramDesc(long j, long j2, String str, String str2, Uri uri, int i, int i2, int i3, int i4, String str3, String str4, Uri uri2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, str, str2, uri, i, i2, i3, i4, str3, str4, (i5 & 2048) != 0 ? null : uri2);
    }

    public final long getChannelId() {
        return this.channelId;
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final Uri getArtUri() {
        return this.artUri;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final int getTime() {
        return this.time;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getAppId() {
        return this.appId;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final Uri getPreviewVideoUri() {
        return this.previewVideoUri;
    }
}
