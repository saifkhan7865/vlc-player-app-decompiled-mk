package org.videolan.medialibrary.interfaces.media;

import android.os.Parcel;
import android.os.Parcelable;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.media.MediaLibraryItem;

public abstract class Genre extends MediaLibraryItem {
    public static Parcelable.Creator<Genre> CREATOR = new Parcelable.Creator<Genre>() {
        public Genre createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractGenre(parcel);
        }

        public Genre[] newArray(int i) {
            return new Genre[i];
        }
    };
    private int mPresentTracksCount;
    private int mTracksCount;

    public abstract Album[] getAlbums(int i, boolean z, boolean z2, boolean z3);

    public abstract int getAlbumsCount();

    public abstract Artist[] getArtists(int i, boolean z, boolean z2, boolean z3);

    public int getItemType() {
        return 8;
    }

    public abstract Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract MediaWrapper[] getPagedTracks(boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3);

    public abstract MediaWrapper[] getTracks(boolean z, int i, boolean z2, boolean z3, boolean z4);

    public abstract Album[] searchAlbums(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int searchAlbumsCount(String str);

    public abstract MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int searchTracksCount(String str);

    public Genre(long j, String str, int i, int i2, boolean z) {
        super(j, str);
        this.mTracksCount = i;
        this.mPresentTracksCount = i2;
        this.mFavorite = z;
    }

    public Genre(Parcel parcel) {
        super(parcel);
        this.mTracksCount = parcel.readInt();
        this.mPresentTracksCount = parcel.readInt();
        this.mFavorite = parcel.readInt() != 1 ? false : true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mTracksCount);
        parcel.writeInt(this.mPresentTracksCount);
        parcel.writeInt(this.mFavorite ? 1 : 0);
    }

    public MediaWrapper[] getPagedTracks(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return getPagedTracks(false, i, z, z2, z3, i2, i3);
    }

    public MediaWrapper[] getTracks(int i, boolean z, boolean z2, boolean z3) {
        return getTracks(false, i, z, z2, z3);
    }

    public Album[] getAlbums() {
        return getAlbums(0, false, true, false);
    }

    public Artist[] getArtists() {
        return getArtists(0, false, true, false);
    }

    public MediaWrapper[] getTracks() {
        return getTracks(false, 9, false, true, false);
    }

    public int getTracksCount() {
        return this.mPresentTracksCount;
    }

    public int getPresentTracksCount() {
        return this.mPresentTracksCount;
    }
}
