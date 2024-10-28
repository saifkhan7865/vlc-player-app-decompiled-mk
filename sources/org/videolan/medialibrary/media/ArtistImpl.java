package org.videolan.medialibrary.media;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class ArtistImpl extends Artist {
    private native Album[] nativeGetAlbums(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3);

    private native MediaWrapper[] nativeGetMedia(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3);

    private native Album[] nativeGetPagedAlbums(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native MediaWrapper[] nativeGetPagedMedia(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native int nativeGetSearchAlbumCount(Medialibrary medialibrary, long j, String str);

    private native int nativeGetSearchCount(Medialibrary medialibrary, long j, String str);

    private native MediaWrapper[] nativeSearch(Medialibrary medialibrary, long j, String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Album[] nativeSearchAlbums(Medialibrary medialibrary, long j, String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native boolean nativeSetFavorite(Medialibrary medialibrary, long j, boolean z);

    public ArtistImpl(long j, String str, String str2, String str3, String str4, int i, int i2, int i3, boolean z) {
        super(j, str, str2, str3, str4, i, i2, i3, z);
    }

    public ArtistImpl(Parcel parcel) {
        super(parcel);
    }

    public Album[] getAlbums(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return new Album[0];
        }
        return nativeGetAlbums(instance, this.mId, i, z, z2, z3);
    }

    public Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return new Album[0];
        }
        return nativeGetPagedAlbums(instance, this.mId, i, z, z2, z3, i2, i3);
    }

    public Album[] searchAlbums(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return new Album[0];
        }
        return nativeSearchAlbums(instance, this.mId, str, i, z, z2, z3, i2, i3);
    }

    public int searchAlbumsCount(String str) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetSearchAlbumCount(instance, this.mId, str);
        }
        return 0;
    }

    public MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeSearch(instance, this.mId, str, i, z, z2, z3, i2, i3);
    }

    public int searchTracksCount(String str) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetSearchCount(instance, this.mId, str);
        }
        return 0;
    }

    public MediaWrapper[] getTracks(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetMedia(instance, this.mId, i, z, z2, z3);
    }

    public MediaWrapper[] getPagedTracks(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetPagedMedia(instance, this.mId, i, z, z2, z3, i2, i3);
    }

    public boolean setFavorite(boolean z) {
        if (this.mId == 0) {
            return false;
        }
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeSetFavorite(instance, this.mId, z);
        }
        return false;
    }
}
