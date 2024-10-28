package org.videolan.vlc.mediadb.models;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/mediadb/models/BrowserFav;", "", "uri", "Landroid/net/Uri;", "type", "", "title", "", "iconUrl", "(Landroid/net/Uri;ILjava/lang/String;Ljava/lang/String;)V", "getIconUrl", "()Ljava/lang/String;", "getTitle", "getType", "()I", "getUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFav.kt */
public final class BrowserFav {
    private final String iconUrl;
    private final String title;
    private final int type;
    private final Uri uri;

    public static /* synthetic */ BrowserFav copy$default(BrowserFav browserFav, Uri uri2, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            uri2 = browserFav.uri;
        }
        if ((i2 & 2) != 0) {
            i = browserFav.type;
        }
        if ((i2 & 4) != 0) {
            str = browserFav.title;
        }
        if ((i2 & 8) != 0) {
            str2 = browserFav.iconUrl;
        }
        return browserFav.copy(uri2, i, str, str2);
    }

    public final Uri component1() {
        return this.uri;
    }

    public final int component2() {
        return this.type;
    }

    public final String component3() {
        return this.title;
    }

    public final String component4() {
        return this.iconUrl;
    }

    public final BrowserFav copy(Uri uri2, int i, String str, String str2) {
        Intrinsics.checkNotNullParameter(uri2, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(str, "title");
        return new BrowserFav(uri2, i, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BrowserFav)) {
            return false;
        }
        BrowserFav browserFav = (BrowserFav) obj;
        return Intrinsics.areEqual((Object) this.uri, (Object) browserFav.uri) && this.type == browserFav.type && Intrinsics.areEqual((Object) this.title, (Object) browserFav.title) && Intrinsics.areEqual((Object) this.iconUrl, (Object) browserFav.iconUrl);
    }

    public int hashCode() {
        int hashCode = ((((this.uri.hashCode() * 31) + this.type) * 31) + this.title.hashCode()) * 31;
        String str = this.iconUrl;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "BrowserFav(uri=" + this.uri + ", type=" + this.type + ", title=" + this.title + ", iconUrl=" + this.iconUrl + ')';
    }

    public BrowserFav(Uri uri2, int i, String str, String str2) {
        Intrinsics.checkNotNullParameter(uri2, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(str, "title");
        this.uri = uri2;
        this.type = i;
        this.title = str;
        this.iconUrl = str2;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final int getType() {
        return this.type;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getIconUrl() {
        return this.iconUrl;
    }
}
