package org.videolan.medialibrary.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public abstract class MediaLibraryItem implements Parcelable {
    public static final int FLAG_FAVORITE = 2;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_SELECTED = 1;
    public static final int FLAG_STORAGE = 4;
    public static final int TYPE_ALBUM = 2;
    public static final int TYPE_ARTIST = 4;
    public static final int TYPE_BOOKMARK = 4096;
    public static final int TYPE_DUMMY = 64;
    public static final int TYPE_FOLDER = 1024;
    public static final int TYPE_GENRE = 8;
    public static final int TYPE_HISTORY = 512;
    public static final int TYPE_MEDIA = 32;
    public static final int TYPE_PLAYLIST = 16;
    public static final int TYPE_STORAGE = 128;
    public static final int TYPE_VIDEO_GROUP = 2048;
    protected String mDescription;
    protected boolean mFavorite;
    private int mFlags;
    protected long mId;
    protected String mTitle;

    public enum MediaType {
        Unknown,
        Video,
        Audio,
        External,
        Stream
    }

    public int describeContents() {
        return 0;
    }

    public String getArtworkMrl() {
        return null;
    }

    public abstract int getItemType();

    public abstract MediaWrapper[] getTracks();

    public abstract int getTracksCount();

    public abstract boolean setFavorite(boolean z);

    protected MediaLibraryItem() {
    }

    protected MediaLibraryItem(long j, String str) {
        this.mId = j;
        this.mTitle = str;
    }

    public long getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setStateFlags(int i) {
        this.mFlags = i;
    }

    public void addStateFlags(int i) {
        this.mFlags = i | this.mFlags;
    }

    public boolean hasStateFlags(int i) {
        return (i & this.mFlags) != 0;
    }

    public boolean isFavorite() {
        return this.mFavorite;
    }

    public void toggleStateFlag(int i) {
        if (hasStateFlags(i)) {
            removeStateFlags(i);
        } else {
            addStateFlags(i);
        }
    }

    public void removeStateFlags(int i) {
        this.mFlags = (i ^ -1) & this.mFlags;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mId);
        parcel.writeString(this.mTitle);
        parcel.writeInt(this.mFlags);
    }

    protected MediaLibraryItem(Parcel parcel) {
        this.mId = parcel.readLong();
        this.mTitle = parcel.readString();
        this.mFlags = parcel.readInt();
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof MediaLibraryItem) && this.mId == ((MediaLibraryItem) obj).getId());
    }

    public boolean equals(MediaLibraryItem mediaLibraryItem) {
        if (this == mediaLibraryItem) {
            return true;
        }
        if (mediaLibraryItem == null || getItemType() != mediaLibraryItem.getItemType()) {
            return false;
        }
        if (getItemType() == 64) {
            return TextUtils.equals(getTitle(), mediaLibraryItem.getTitle());
        }
        long j = this.mId;
        if (j != 0) {
            if (j == mediaLibraryItem.getId()) {
                return true;
            }
            return false;
        } else if (getItemType() == 32) {
            return TextUtils.equals(((MediaWrapper) this).getLocation(), ((MediaWrapper) mediaLibraryItem).getLocation());
        } else {
            if (getItemType() == 128) {
                return TextUtils.equals(((Storage) this).getName(), ((Storage) mediaLibraryItem).getName());
            }
            return false;
        }
    }
}
