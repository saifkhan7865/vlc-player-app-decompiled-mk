package org.videolan.medialibrary.media;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class AlbumImpl extends Album {
    public static final String TAG = "VLC/Album";

    private native MediaWrapper[] nativeGetPagedTracks(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native int nativeGetSearchCount(Medialibrary medialibrary, long j, String str);

    private native MediaWrapper[] nativeGetTracks(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3);

    private native int nativeGetTracksCount(Medialibrary medialibrary, long j);

    private native MediaWrapper[] nativeSearch(Medialibrary medialibrary, long j, String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native boolean nativeSetFavorite(Medialibrary medialibrary, long j, boolean z);

    public AlbumImpl(long j, String str, int i, String str2, String str3, long j2, int i2, int i3, long j3, boolean z) {
        super(j, str, i, str2, str3, j2, i2, i3, j3, z);
    }

    public AlbumImpl(Parcel parcel) {
        super(parcel);
    }

    public int getRealTracksCount() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetTracksCount(instance, this.mId);
        }
        return 0;
    }

    public MediaWrapper[] getTracks(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetTracks(instance, this.mId, i, z, z2, z3);
    }

    public MediaWrapper[] getPagedTracks(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetPagedTracks(instance, this.mId, i, z, z2, z3, i2, i3);
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

    public Artist retrieveAlbumArtist() {
        return Medialibrary.getInstance().getArtist(this.albumArtistId);
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
