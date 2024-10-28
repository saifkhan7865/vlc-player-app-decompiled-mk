package org.videolan.medialibrary.media;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class GenreImpl extends Genre {
    private native Album[] nativeGetAlbums(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3);

    private native int nativeGetAlbumsCount(Medialibrary medialibrary, long j);

    private native Artist[] nativeGetArtists(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3);

    private native int nativeGetArtistsCount(Medialibrary medialibrary, long j);

    private native Album[] nativeGetPagedAlbums(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Artist[] nativeGetPagedArtists(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native MediaWrapper[] nativeGetPagedTracks(Medialibrary medialibrary, long j, boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3);

    private native int nativeGetSearchAlbumCount(Medialibrary medialibrary, long j, String str);

    private native int nativeGetSearchCount(Medialibrary medialibrary, long j, String str);

    private native MediaWrapper[] nativeGetTracks(Medialibrary medialibrary, long j, boolean z, int i, boolean z2, boolean z3, boolean z4);

    private native MediaWrapper[] nativeSearch(Medialibrary medialibrary, long j, String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native Album[] nativeSearchAlbums(Medialibrary medialibrary, long j, String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native boolean nativeSetFavorite(Medialibrary medialibrary, long j, boolean z);

    public GenreImpl(long j, String str, int i, int i2, boolean z) {
        super(j, str, i, i2, z);
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

    public Artist[] getArtists(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return new Artist[0];
        }
        return nativeGetArtists(instance, this.mId, i, z, z2, z3);
    }

    public MediaWrapper[] getTracks(boolean z, int i, boolean z2, boolean z3, boolean z4) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetTracks(instance, this.mId, z, i, z2, z3, z4);
    }

    public MediaWrapper[] getPagedTracks(boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetPagedTracks(instance, this.mId, z, i, z2, z3, z4, i2, i3);
    }

    public int getAlbumsCount() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetAlbumsCount(instance, this.mId);
        }
        return 0;
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

    public GenreImpl(Parcel parcel) {
        super(parcel);
    }
}
