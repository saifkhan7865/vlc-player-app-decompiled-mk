package org.videolan.medialibrary.interfaces.media;

import android.os.Parcel;
import android.os.Parcelable;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.media.MediaLibraryItem;

public abstract class Folder extends MediaLibraryItem {
    public static Parcelable.Creator<Folder> CREATOR = new Parcelable.Creator<Folder>() {
        public Folder createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractFolder(parcel);
        }

        public Folder[] newArray(int i) {
            return new Folder[i];
        }
    };
    public static int TYPE_FOLDER_AUDIO = 2;
    public static int TYPE_FOLDER_EXTERNAL = 3;
    public static int TYPE_FOLDER_STREAM = 4;
    public static int TYPE_FOLDER_UNKNOWN = 0;
    public static int TYPE_FOLDER_VIDEO = 1;
    protected int mMediaCount;
    public String mMrl;

    public int getItemType() {
        return 1024;
    }

    public int getTracksCount() {
        return 0;
    }

    public abstract MediaWrapper[] media(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    public abstract int mediaCount(int i);

    public abstract MediaWrapper[] searchTracks(String str, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    public abstract int searchTracksCount(String str, int i);

    public boolean setFavorite(boolean z) {
        return false;
    }

    public abstract Folder[] subfolders(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int subfoldersCount(int i);

    public Folder(long j, String str, String str2, int i, boolean z) {
        super(j, str);
        this.mMrl = str2;
        this.mMediaCount = i;
        this.mFavorite = z;
    }

    public String getDisplayTitle() {
        return super.getTitle();
    }

    public MediaWrapper[] getTracks() {
        return new MediaWrapper[0];
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mMrl);
        parcel.writeInt(this.mMediaCount);
        parcel.writeInt(this.mFavorite ? 1 : 0);
    }

    public Folder(Parcel parcel) {
        super(parcel);
        this.mMrl = parcel.readString();
        this.mMediaCount = parcel.readInt();
        this.mFavorite = parcel.readInt() != 1 ? false : true;
    }

    public boolean equals(Folder folder) {
        return this.mId == folder.getId();
    }

    public boolean equals(MediaLibraryItem mediaLibraryItem) {
        if (mediaLibraryItem instanceof Folder) {
            return equals((Folder) mediaLibraryItem);
        }
        return super.equals(mediaLibraryItem);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Folder) {
            return equals((Folder) obj);
        }
        return super.equals(obj);
    }
}
