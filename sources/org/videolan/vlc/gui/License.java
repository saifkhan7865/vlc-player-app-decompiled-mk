package org.videolan.vlc.gui;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.squareup.moshi.Json;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/License;", "", "description", "", "id", "link", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getId", "getLink", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
public final class License {
    private final String description;
    private final String id;
    private final String link;
    private final String name;

    public static /* synthetic */ License copy$default(License license, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = license.description;
        }
        if ((i & 2) != 0) {
            str2 = license.id;
        }
        if ((i & 4) != 0) {
            str3 = license.link;
        }
        if ((i & 8) != 0) {
            str4 = license.name;
        }
        return license.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.description;
    }

    public final String component2() {
        return this.id;
    }

    public final String component3() {
        return this.link;
    }

    public final String component4() {
        return this.name;
    }

    public final License copy(@Json(name = "description") String str, @Json(name = "id") String str2, @Json(name = "link") String str3, @Json(name = "name") String str4) {
        Intrinsics.checkNotNullParameter(str, TvContractCompat.Channels.COLUMN_DESCRIPTION);
        Intrinsics.checkNotNullParameter(str2, "id");
        Intrinsics.checkNotNullParameter(str3, "link");
        Intrinsics.checkNotNullParameter(str4, ContentDisposition.Parameters.Name);
        return new License(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof License)) {
            return false;
        }
        License license = (License) obj;
        return Intrinsics.areEqual((Object) this.description, (Object) license.description) && Intrinsics.areEqual((Object) this.id, (Object) license.id) && Intrinsics.areEqual((Object) this.link, (Object) license.link) && Intrinsics.areEqual((Object) this.name, (Object) license.name);
    }

    public int hashCode() {
        return (((((this.description.hashCode() * 31) + this.id.hashCode()) * 31) + this.link.hashCode()) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "License(description=" + this.description + ", id=" + this.id + ", link=" + this.link + ", name=" + this.name + ')';
    }

    public License(@Json(name = "description") String str, @Json(name = "id") String str2, @Json(name = "link") String str3, @Json(name = "name") String str4) {
        Intrinsics.checkNotNullParameter(str, TvContractCompat.Channels.COLUMN_DESCRIPTION);
        Intrinsics.checkNotNullParameter(str2, "id");
        Intrinsics.checkNotNullParameter(str3, "link");
        Intrinsics.checkNotNullParameter(str4, ContentDisposition.Parameters.Name);
        this.description = str;
        this.id = str2;
        this.link = str3;
        this.name = str4;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLink() {
        return this.link;
    }

    public final String getName() {
        return this.name;
    }
}
