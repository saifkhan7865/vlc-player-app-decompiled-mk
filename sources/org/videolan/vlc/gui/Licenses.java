package org.videolan.vlc.gui;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/Licenses;", "", "libraries", "", "Lorg/videolan/vlc/gui/Library;", "licenses", "Lorg/videolan/vlc/gui/License;", "(Ljava/util/List;Ljava/util/List;)V", "getLibraries", "()Ljava/util/List;", "getLicenses", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
public final class Licenses {
    private final List<Library> libraries;
    private final List<License> licenses;

    public static /* synthetic */ Licenses copy$default(Licenses licenses2, List<Library> list, List<License> list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = licenses2.libraries;
        }
        if ((i & 2) != 0) {
            list2 = licenses2.licenses;
        }
        return licenses2.copy(list, list2);
    }

    public final List<Library> component1() {
        return this.libraries;
    }

    public final List<License> component2() {
        return this.licenses;
    }

    public final Licenses copy(@Json(name = "libraries") List<Library> list, @Json(name = "licenses") List<License> list2) {
        Intrinsics.checkNotNullParameter(list, "libraries");
        Intrinsics.checkNotNullParameter(list2, "licenses");
        return new Licenses(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Licenses)) {
            return false;
        }
        Licenses licenses2 = (Licenses) obj;
        return Intrinsics.areEqual((Object) this.libraries, (Object) licenses2.libraries) && Intrinsics.areEqual((Object) this.licenses, (Object) licenses2.licenses);
    }

    public int hashCode() {
        return (this.libraries.hashCode() * 31) + this.licenses.hashCode();
    }

    public String toString() {
        return "Licenses(libraries=" + this.libraries + ", licenses=" + this.licenses + ')';
    }

    public Licenses(@Json(name = "libraries") List<Library> list, @Json(name = "licenses") List<License> list2) {
        Intrinsics.checkNotNullParameter(list, "libraries");
        Intrinsics.checkNotNullParameter(list2, "licenses");
        this.libraries = list;
        this.licenses = list2;
    }

    public final List<Library> getLibraries() {
        return this.libraries;
    }

    public final List<License> getLicenses() {
        return this.licenses;
    }
}
