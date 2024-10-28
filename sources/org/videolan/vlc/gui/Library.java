package org.videolan.vlc.gui;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/Library;", "", "copyright", "", "license", "title", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCopyright", "()Ljava/lang/String;", "getLicense", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
public final class Library {
    private final String copyright;
    private final String license;
    private final String title;

    public static /* synthetic */ Library copy$default(Library library, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = library.copyright;
        }
        if ((i & 2) != 0) {
            str2 = library.license;
        }
        if ((i & 4) != 0) {
            str3 = library.title;
        }
        return library.copy(str, str2, str3);
    }

    public final String component1() {
        return this.copyright;
    }

    public final String component2() {
        return this.license;
    }

    public final String component3() {
        return this.title;
    }

    public final Library copy(@Json(name = "copyright") String str, @Json(name = "license") String str2, @Json(name = "title") String str3) {
        Intrinsics.checkNotNullParameter(str, "copyright");
        Intrinsics.checkNotNullParameter(str2, "license");
        Intrinsics.checkNotNullParameter(str3, "title");
        return new Library(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Library)) {
            return false;
        }
        Library library = (Library) obj;
        return Intrinsics.areEqual((Object) this.copyright, (Object) library.copyright) && Intrinsics.areEqual((Object) this.license, (Object) library.license) && Intrinsics.areEqual((Object) this.title, (Object) library.title);
    }

    public int hashCode() {
        return (((this.copyright.hashCode() * 31) + this.license.hashCode()) * 31) + this.title.hashCode();
    }

    public String toString() {
        return "Library(copyright=" + this.copyright + ", license=" + this.license + ", title=" + this.title + ')';
    }

    public Library(@Json(name = "copyright") String str, @Json(name = "license") String str2, @Json(name = "title") String str3) {
        Intrinsics.checkNotNullParameter(str, "copyright");
        Intrinsics.checkNotNullParameter(str2, "license");
        Intrinsics.checkNotNullParameter(str3, "title");
        this.copyright = str;
        this.license = str2;
        this.title = str3;
    }

    public final String getCopyright() {
        return this.copyright;
    }

    public final String getLicense() {
        return this.license;
    }

    public final String getTitle() {
        return this.title;
    }
}
