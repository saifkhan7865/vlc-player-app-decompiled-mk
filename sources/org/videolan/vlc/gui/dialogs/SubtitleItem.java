package org.videolan.vlc.gui.dialogs;

import android.net.Uri;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "", "idSubtitle", "", "mediaUri", "Landroid/net/Uri;", "subLanguageID", "movieReleaseName", "state", "Lorg/videolan/vlc/gui/dialogs/State;", "zipDownloadLink", "(Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Lorg/videolan/vlc/gui/dialogs/State;Ljava/lang/String;)V", "getIdSubtitle", "()Ljava/lang/String;", "getMediaUri", "()Landroid/net/Uri;", "getMovieReleaseName", "getState", "()Lorg/videolan/vlc/gui/dialogs/State;", "getSubLanguageID", "getZipDownloadLink", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitleItem.kt */
public final class SubtitleItem {
    private final String idSubtitle;
    private final Uri mediaUri;
    private final String movieReleaseName;
    private final State state;
    private final String subLanguageID;
    private final String zipDownloadLink;

    public static /* synthetic */ SubtitleItem copy$default(SubtitleItem subtitleItem, String str, Uri uri, String str2, String str3, State state2, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subtitleItem.idSubtitle;
        }
        if ((i & 2) != 0) {
            uri = subtitleItem.mediaUri;
        }
        Uri uri2 = uri;
        if ((i & 4) != 0) {
            str2 = subtitleItem.subLanguageID;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = subtitleItem.movieReleaseName;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            state2 = subtitleItem.state;
        }
        State state3 = state2;
        if ((i & 32) != 0) {
            str4 = subtitleItem.zipDownloadLink;
        }
        return subtitleItem.copy(str, uri2, str5, str6, state3, str4);
    }

    public final String component1() {
        return this.idSubtitle;
    }

    public final Uri component2() {
        return this.mediaUri;
    }

    public final String component3() {
        return this.subLanguageID;
    }

    public final String component4() {
        return this.movieReleaseName;
    }

    public final State component5() {
        return this.state;
    }

    public final String component6() {
        return this.zipDownloadLink;
    }

    public final SubtitleItem copy(String str, Uri uri, String str2, String str3, State state2, String str4) {
        Intrinsics.checkNotNullParameter(str, "idSubtitle");
        Intrinsics.checkNotNullParameter(uri, "mediaUri");
        Intrinsics.checkNotNullParameter(str2, "subLanguageID");
        Intrinsics.checkNotNullParameter(str3, "movieReleaseName");
        Intrinsics.checkNotNullParameter(state2, OAuth2RequestParameters.State);
        Intrinsics.checkNotNullParameter(str4, "zipDownloadLink");
        return new SubtitleItem(str, uri, str2, str3, state2, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubtitleItem)) {
            return false;
        }
        SubtitleItem subtitleItem = (SubtitleItem) obj;
        return Intrinsics.areEqual((Object) this.idSubtitle, (Object) subtitleItem.idSubtitle) && Intrinsics.areEqual((Object) this.mediaUri, (Object) subtitleItem.mediaUri) && Intrinsics.areEqual((Object) this.subLanguageID, (Object) subtitleItem.subLanguageID) && Intrinsics.areEqual((Object) this.movieReleaseName, (Object) subtitleItem.movieReleaseName) && this.state == subtitleItem.state && Intrinsics.areEqual((Object) this.zipDownloadLink, (Object) subtitleItem.zipDownloadLink);
    }

    public int hashCode() {
        return (((((((((this.idSubtitle.hashCode() * 31) + this.mediaUri.hashCode()) * 31) + this.subLanguageID.hashCode()) * 31) + this.movieReleaseName.hashCode()) * 31) + this.state.hashCode()) * 31) + this.zipDownloadLink.hashCode();
    }

    public String toString() {
        return "SubtitleItem(idSubtitle=" + this.idSubtitle + ", mediaUri=" + this.mediaUri + ", subLanguageID=" + this.subLanguageID + ", movieReleaseName=" + this.movieReleaseName + ", state=" + this.state + ", zipDownloadLink=" + this.zipDownloadLink + ')';
    }

    public SubtitleItem(String str, Uri uri, String str2, String str3, State state2, String str4) {
        Intrinsics.checkNotNullParameter(str, "idSubtitle");
        Intrinsics.checkNotNullParameter(uri, "mediaUri");
        Intrinsics.checkNotNullParameter(str2, "subLanguageID");
        Intrinsics.checkNotNullParameter(str3, "movieReleaseName");
        Intrinsics.checkNotNullParameter(state2, OAuth2RequestParameters.State);
        Intrinsics.checkNotNullParameter(str4, "zipDownloadLink");
        this.idSubtitle = str;
        this.mediaUri = uri;
        this.subLanguageID = str2;
        this.movieReleaseName = str3;
        this.state = state2;
        this.zipDownloadLink = str4;
    }

    public final String getIdSubtitle() {
        return this.idSubtitle;
    }

    public final Uri getMediaUri() {
        return this.mediaUri;
    }

    public final String getSubLanguageID() {
        return this.subLanguageID;
    }

    public final String getMovieReleaseName() {
        return this.movieReleaseName;
    }

    public final State getState() {
        return this.state;
    }

    public final String getZipDownloadLink() {
        return this.zipDownloadLink;
    }
}
