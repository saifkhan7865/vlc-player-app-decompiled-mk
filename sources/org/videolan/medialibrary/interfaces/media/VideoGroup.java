package org.videolan.medialibrary.interfaces.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.media.MediaLibraryItem;

public abstract class VideoGroup extends MediaLibraryItem {
    public static Parcelable.Creator<VideoGroup> CREATOR = new Parcelable.Creator<VideoGroup>() {
        public VideoGroup createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractVideoGroup(parcel);
        }

        public VideoGroup[] newArray(int i) {
            return new VideoGroup[i];
        }
    };
    public boolean isNetwork = false;
    public int mCount;
    public int mPresentCount;
    public int mPresentSeen;

    public abstract boolean add(long j);

    public abstract boolean destroy();

    public abstract long duration();

    public int getItemType() {
        return 2048;
    }

    public abstract String getName();

    public abstract MediaWrapper[] media(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract boolean remove(long j);

    public abstract boolean rename(String str);

    public abstract MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int searchTracksCount(String str);

    public boolean setFavorite(boolean z) {
        return false;
    }

    public abstract boolean userInteracted();

    public VideoGroup(long j, String str, int i, int i2, int i3, boolean z) {
        super(j, str);
        this.mCount = i;
        this.mPresentCount = i2;
        this.mPresentSeen = i3;
        this.mFavorite = z;
    }

    public String getDisplayTitle() {
        return super.getTitle();
    }

    public int mediaCount() {
        return this.mCount;
    }

    public int getPresentCount() {
        return this.mPresentCount;
    }

    public int getPresentSeen() {
        return this.mPresentSeen;
    }

    public MediaWrapper[] getTracks() {
        return new MediaWrapper[0];
    }

    public int getTracksCount() {
        return this.mCount;
    }

    public boolean isNetwork() {
        return this.isNetwork;
    }

    public void setNetwork(boolean z) {
        this.isNetwork = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mCount);
        parcel.writeInt(this.mFavorite ? 1 : 0);
    }

    public VideoGroup(Parcel parcel) {
        super(parcel);
        boolean z = false;
        this.mCount = parcel.readInt();
        this.mFavorite = parcel.readInt() == 1 ? true : z;
    }

    public boolean equals(VideoGroup videoGroup) {
        return TextUtils.equals(this.mTitle, videoGroup.getTitle());
    }

    public boolean equals(Object obj) {
        if (obj instanceof VideoGroup) {
            return equals((VideoGroup) obj);
        }
        return super.equals(obj);
    }

    public boolean equals(MediaLibraryItem mediaLibraryItem) {
        if (mediaLibraryItem instanceof VideoGroup) {
            return equals((VideoGroup) mediaLibraryItem);
        }
        return super.equals(mediaLibraryItem);
    }
}
