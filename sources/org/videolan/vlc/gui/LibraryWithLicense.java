package org.videolan.vlc.gui;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0016HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/LibraryWithLicense;", "Landroid/os/Parcelable;", "title", "", "copyright", "licenseTitle", "licenseDescription", "licenseLink", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCopyright", "()Ljava/lang/String;", "getLicenseDescription", "getLicenseLink", "getLicenseTitle", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
public final class LibraryWithLicense implements Parcelable {
    public static final Parcelable.Creator<LibraryWithLicense> CREATOR = new Creator();
    private final String copyright;
    private final String licenseDescription;
    private final String licenseLink;
    private final String licenseTitle;
    private final String title;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: LibrariesActivity.kt */
    public static final class Creator implements Parcelable.Creator<LibraryWithLicense> {
        public final LibraryWithLicense createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LibraryWithLicense(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final LibraryWithLicense[] newArray(int i) {
            return new LibraryWithLicense[i];
        }
    }

    public static /* synthetic */ LibraryWithLicense copy$default(LibraryWithLicense libraryWithLicense, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = libraryWithLicense.title;
        }
        if ((i & 2) != 0) {
            str2 = libraryWithLicense.copyright;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = libraryWithLicense.licenseTitle;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = libraryWithLicense.licenseDescription;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = libraryWithLicense.licenseLink;
        }
        return libraryWithLicense.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.copyright;
    }

    public final String component3() {
        return this.licenseTitle;
    }

    public final String component4() {
        return this.licenseDescription;
    }

    public final String component5() {
        return this.licenseLink;
    }

    public final LibraryWithLicense copy(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "copyright");
        Intrinsics.checkNotNullParameter(str3, "licenseTitle");
        Intrinsics.checkNotNullParameter(str4, "licenseDescription");
        Intrinsics.checkNotNullParameter(str5, "licenseLink");
        return new LibraryWithLicense(str, str2, str3, str4, str5);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LibraryWithLicense)) {
            return false;
        }
        LibraryWithLicense libraryWithLicense = (LibraryWithLicense) obj;
        return Intrinsics.areEqual((Object) this.title, (Object) libraryWithLicense.title) && Intrinsics.areEqual((Object) this.copyright, (Object) libraryWithLicense.copyright) && Intrinsics.areEqual((Object) this.licenseTitle, (Object) libraryWithLicense.licenseTitle) && Intrinsics.areEqual((Object) this.licenseDescription, (Object) libraryWithLicense.licenseDescription) && Intrinsics.areEqual((Object) this.licenseLink, (Object) libraryWithLicense.licenseLink);
    }

    public int hashCode() {
        return (((((((this.title.hashCode() * 31) + this.copyright.hashCode()) * 31) + this.licenseTitle.hashCode()) * 31) + this.licenseDescription.hashCode()) * 31) + this.licenseLink.hashCode();
    }

    public String toString() {
        return "LibraryWithLicense(title=" + this.title + ", copyright=" + this.copyright + ", licenseTitle=" + this.licenseTitle + ", licenseDescription=" + this.licenseDescription + ", licenseLink=" + this.licenseLink + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.title);
        parcel.writeString(this.copyright);
        parcel.writeString(this.licenseTitle);
        parcel.writeString(this.licenseDescription);
        parcel.writeString(this.licenseLink);
    }

    public LibraryWithLicense(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "copyright");
        Intrinsics.checkNotNullParameter(str3, "licenseTitle");
        Intrinsics.checkNotNullParameter(str4, "licenseDescription");
        Intrinsics.checkNotNullParameter(str5, "licenseLink");
        this.title = str;
        this.copyright = str2;
        this.licenseTitle = str3;
        this.licenseDescription = str4;
        this.licenseLink = str5;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getCopyright() {
        return this.copyright;
    }

    public final String getLicenseTitle() {
        return this.licenseTitle;
    }

    public final String getLicenseDescription() {
        return this.licenseDescription;
    }

    public final String getLicenseLink() {
        return this.licenseLink;
    }
}
