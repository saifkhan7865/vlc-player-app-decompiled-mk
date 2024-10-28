package org.videolan.medialibrary.interfaces.media;

import android.os.Parcel;
import android.os.Parcelable;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.media.MediaLibraryItem;

public abstract class Bookmark extends MediaLibraryItem {
    public static Parcelable.Creator<Bookmark> CREATOR = new Parcelable.Creator<Bookmark>() {
        public Bookmark createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractBookmark(parcel);
        }

        public Bookmark[] newArray(int i) {
            return new Bookmark[i];
        }
    };
    public long mTime;
    public long mediaId;

    public int getItemType() {
        return 4096;
    }

    public int getTracksCount() {
        return 0;
    }

    public abstract boolean move(long j);

    public boolean setFavorite(boolean z) {
        return false;
    }

    public abstract boolean setName(String str);

    public abstract boolean setNameAndDescription(String str, String str2);

    public abstract boolean updateDescription(String str);

    public Bookmark(long j, String str, String str2, long j2, long j3) {
        super(j, str);
        this.mediaId = j2;
        this.mTime = j3;
        setDescription(str2);
    }

    public MediaWrapper[] getTracks() {
        return new MediaWrapper[0];
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mediaId);
    }

    public Bookmark(Parcel parcel) {
        super(parcel);
        this.mediaId = parcel.readLong();
        this.mTime = parcel.readLong();
    }

    public boolean equals(Bookmark bookmark) {
        return this.mId == bookmark.getId();
    }

    public boolean equals(MediaLibraryItem mediaLibraryItem) {
        if (mediaLibraryItem instanceof Bookmark) {
            return equals((Bookmark) mediaLibraryItem);
        }
        return super.equals(mediaLibraryItem);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Bookmark) {
            return equals((Bookmark) obj);
        }
        return super.equals(obj);
    }
}
