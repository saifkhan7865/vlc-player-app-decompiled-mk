package org.videolan.vlc.gui.preferences.search;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003Je\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020\u0005HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\u0019\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006/"}, d2 = {"Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "Landroid/os/Parcelable;", "key", "", "parentScreen", "", "title", "summary", "titleEng", "summaryEng", "category", "categoryEng", "defaultValue", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "getCategoryEng", "getDefaultValue", "getKey", "getParentScreen", "()I", "getSummary", "getSummaryEng", "getTitle", "getTitleEng", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceParser.kt */
public final class PreferenceItem implements Parcelable {
    public static final Parcelable.Creator<PreferenceItem> CREATOR = new Creator();
    private final String category;
    private final String categoryEng;
    private final String defaultValue;
    private final String key;
    private final int parentScreen;
    private final String summary;
    private final String summaryEng;
    private final String title;
    private final String titleEng;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PreferenceParser.kt */
    public static final class Creator implements Parcelable.Creator<PreferenceItem> {
        public final PreferenceItem createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PreferenceItem(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final PreferenceItem[] newArray(int i) {
            return new PreferenceItem[i];
        }
    }

    public static /* synthetic */ PreferenceItem copy$default(PreferenceItem preferenceItem, String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, Object obj) {
        PreferenceItem preferenceItem2 = preferenceItem;
        int i3 = i2;
        return preferenceItem.copy((i3 & 1) != 0 ? preferenceItem2.key : str, (i3 & 2) != 0 ? preferenceItem2.parentScreen : i, (i3 & 4) != 0 ? preferenceItem2.title : str2, (i3 & 8) != 0 ? preferenceItem2.summary : str3, (i3 & 16) != 0 ? preferenceItem2.titleEng : str4, (i3 & 32) != 0 ? preferenceItem2.summaryEng : str5, (i3 & 64) != 0 ? preferenceItem2.category : str6, (i3 & 128) != 0 ? preferenceItem2.categoryEng : str7, (i3 & 256) != 0 ? preferenceItem2.defaultValue : str8);
    }

    public final String component1() {
        return this.key;
    }

    public final int component2() {
        return this.parentScreen;
    }

    public final String component3() {
        return this.title;
    }

    public final String component4() {
        return this.summary;
    }

    public final String component5() {
        return this.titleEng;
    }

    public final String component6() {
        return this.summaryEng;
    }

    public final String component7() {
        return this.category;
    }

    public final String component8() {
        return this.categoryEng;
    }

    public final String component9() {
        return this.defaultValue;
    }

    public final PreferenceItem copy(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(str3, "summary");
        String str9 = str4;
        Intrinsics.checkNotNullParameter(str9, "titleEng");
        String str10 = str5;
        Intrinsics.checkNotNullParameter(str10, "summaryEng");
        String str11 = str6;
        Intrinsics.checkNotNullParameter(str11, Constants.CATEGORY);
        String str12 = str7;
        Intrinsics.checkNotNullParameter(str12, "categoryEng");
        return new PreferenceItem(str, i, str2, str3, str9, str10, str11, str12, str8);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PreferenceItem)) {
            return false;
        }
        PreferenceItem preferenceItem = (PreferenceItem) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) preferenceItem.key) && this.parentScreen == preferenceItem.parentScreen && Intrinsics.areEqual((Object) this.title, (Object) preferenceItem.title) && Intrinsics.areEqual((Object) this.summary, (Object) preferenceItem.summary) && Intrinsics.areEqual((Object) this.titleEng, (Object) preferenceItem.titleEng) && Intrinsics.areEqual((Object) this.summaryEng, (Object) preferenceItem.summaryEng) && Intrinsics.areEqual((Object) this.category, (Object) preferenceItem.category) && Intrinsics.areEqual((Object) this.categoryEng, (Object) preferenceItem.categoryEng) && Intrinsics.areEqual((Object) this.defaultValue, (Object) preferenceItem.defaultValue);
    }

    public int hashCode() {
        int hashCode = ((((((((((((((this.key.hashCode() * 31) + this.parentScreen) * 31) + this.title.hashCode()) * 31) + this.summary.hashCode()) * 31) + this.titleEng.hashCode()) * 31) + this.summaryEng.hashCode()) * 31) + this.category.hashCode()) * 31) + this.categoryEng.hashCode()) * 31;
        String str = this.defaultValue;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "PreferenceItem(key=" + this.key + ", parentScreen=" + this.parentScreen + ", title=" + this.title + ", summary=" + this.summary + ", titleEng=" + this.titleEng + ", summaryEng=" + this.summaryEng + ", category=" + this.category + ", categoryEng=" + this.categoryEng + ", defaultValue=" + this.defaultValue + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.key);
        parcel.writeInt(this.parentScreen);
        parcel.writeString(this.title);
        parcel.writeString(this.summary);
        parcel.writeString(this.titleEng);
        parcel.writeString(this.summaryEng);
        parcel.writeString(this.category);
        parcel.writeString(this.categoryEng);
        parcel.writeString(this.defaultValue);
    }

    public PreferenceItem(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(str3, "summary");
        Intrinsics.checkNotNullParameter(str4, "titleEng");
        Intrinsics.checkNotNullParameter(str5, "summaryEng");
        Intrinsics.checkNotNullParameter(str6, Constants.CATEGORY);
        Intrinsics.checkNotNullParameter(str7, "categoryEng");
        this.key = str;
        this.parentScreen = i;
        this.title = str2;
        this.summary = str3;
        this.titleEng = str4;
        this.summaryEng = str5;
        this.category = str6;
        this.categoryEng = str7;
        this.defaultValue = str8;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getCategoryEng() {
        return this.categoryEng;
    }

    public final String getDefaultValue() {
        return this.defaultValue;
    }

    public final String getKey() {
        return this.key;
    }

    public final int getParentScreen() {
        return this.parentScreen;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final String getSummaryEng() {
        return this.summaryEng;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getTitleEng() {
        return this.titleEng;
    }
}
