package org.videolan.vlc.mediadb.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/mediadb/models/ExternalSub;", "", "idSubtitle", "", "subtitlePath", "mediaPath", "subLanguageID", "movieReleaseName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIdSubtitle", "()Ljava/lang/String;", "getMediaPath", "getMovieReleaseName", "getSubLanguageID", "getSubtitlePath", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExternalSub.kt */
public final class ExternalSub {
    private final String idSubtitle;
    private final String mediaPath;
    private final String movieReleaseName;
    private final String subLanguageID;
    private final String subtitlePath;

    public static /* synthetic */ ExternalSub copy$default(ExternalSub externalSub, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = externalSub.idSubtitle;
        }
        if ((i & 2) != 0) {
            str2 = externalSub.subtitlePath;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = externalSub.mediaPath;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = externalSub.subLanguageID;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = externalSub.movieReleaseName;
        }
        return externalSub.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.idSubtitle;
    }

    public final String component2() {
        return this.subtitlePath;
    }

    public final String component3() {
        return this.mediaPath;
    }

    public final String component4() {
        return this.subLanguageID;
    }

    public final String component5() {
        return this.movieReleaseName;
    }

    public final ExternalSub copy(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "idSubtitle");
        Intrinsics.checkNotNullParameter(str2, "subtitlePath");
        Intrinsics.checkNotNullParameter(str3, "mediaPath");
        Intrinsics.checkNotNullParameter(str4, "subLanguageID");
        Intrinsics.checkNotNullParameter(str5, "movieReleaseName");
        return new ExternalSub(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExternalSub)) {
            return false;
        }
        ExternalSub externalSub = (ExternalSub) obj;
        return Intrinsics.areEqual((Object) this.idSubtitle, (Object) externalSub.idSubtitle) && Intrinsics.areEqual((Object) this.subtitlePath, (Object) externalSub.subtitlePath) && Intrinsics.areEqual((Object) this.mediaPath, (Object) externalSub.mediaPath) && Intrinsics.areEqual((Object) this.subLanguageID, (Object) externalSub.subLanguageID) && Intrinsics.areEqual((Object) this.movieReleaseName, (Object) externalSub.movieReleaseName);
    }

    public int hashCode() {
        return (((((((this.idSubtitle.hashCode() * 31) + this.subtitlePath.hashCode()) * 31) + this.mediaPath.hashCode()) * 31) + this.subLanguageID.hashCode()) * 31) + this.movieReleaseName.hashCode();
    }

    public String toString() {
        return "ExternalSub(idSubtitle=" + this.idSubtitle + ", subtitlePath=" + this.subtitlePath + ", mediaPath=" + this.mediaPath + ", subLanguageID=" + this.subLanguageID + ", movieReleaseName=" + this.movieReleaseName + ')';
    }

    public ExternalSub(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "idSubtitle");
        Intrinsics.checkNotNullParameter(str2, "subtitlePath");
        Intrinsics.checkNotNullParameter(str3, "mediaPath");
        Intrinsics.checkNotNullParameter(str4, "subLanguageID");
        Intrinsics.checkNotNullParameter(str5, "movieReleaseName");
        this.idSubtitle = str;
        this.subtitlePath = str2;
        this.mediaPath = str3;
        this.subLanguageID = str4;
        this.movieReleaseName = str5;
    }

    public final String getIdSubtitle() {
        return this.idSubtitle;
    }

    public final String getSubtitlePath() {
        return this.subtitlePath;
    }

    public final String getMediaPath() {
        return this.mediaPath;
    }

    public final String getSubLanguageID() {
        return this.subLanguageID;
    }

    public final String getMovieReleaseName() {
        return this.movieReleaseName;
    }
}
