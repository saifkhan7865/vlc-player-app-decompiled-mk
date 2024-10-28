package org.videolan.medialibrary.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class DummyItem extends MediaLibraryItem {
    public static Parcelable.Creator<DummyItem> CREATOR = new Parcelable.Creator<DummyItem>() {
        public DummyItem createFromParcel(Parcel parcel) {
            return new DummyItem(parcel);
        }

        public DummyItem[] newArray(int i) {
            return new DummyItem[i];
        }
    };
    private String mArtWork = null;

    public int getItemType() {
        return 64;
    }

    public int getTracksCount() {
        return 1;
    }

    public boolean setFavorite(boolean z) {
        return false;
    }

    public DummyItem(long j, String str, String str2) {
        super(j, str);
        this.mDescription = str2;
    }

    public DummyItem(String str) {
        super(0, str);
    }

    public MediaWrapper[] getTracks() {
        return Medialibrary.EMPTY_COLLECTION;
    }

    public String getArtworkMrl() {
        return this.mArtWork;
    }

    public void setArtWork(String str) {
        this.mArtWork = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public DummyItem(Parcel parcel) {
        super(parcel);
    }

    public boolean equals(Object obj) {
        return (obj instanceof DummyItem) && TextUtils.equals(this.mTitle, ((DummyItem) obj).getTitle());
    }
}
