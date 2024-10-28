package org.videolan.medialibrary.media;

import android.os.Parcel;
import java.util.List;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;

public class PlaylistImpl extends Playlist {
    private native MediaWrapper[] nativeGetPagedTracks(Medialibrary medialibrary, long j, int i, int i2, boolean z, boolean z2);

    private native int nativeGetSearchCount(Medialibrary medialibrary, long j, String str);

    private native MediaWrapper[] nativeGetTracks(Medialibrary medialibrary, long j, boolean z, boolean z2);

    private native int nativeGetTracksCount(Medialibrary medialibrary, long j, boolean z, boolean z2);

    private native boolean nativePlaylistAdd(Medialibrary medialibrary, long j, long j2, int i);

    private native boolean nativePlaylistAppend(Medialibrary medialibrary, long j, long j2);

    private native boolean nativePlaylistAppendGroup(Medialibrary medialibrary, long j, long[] jArr);

    private native boolean nativePlaylistDelete(Medialibrary medialibrary, long j);

    private native boolean nativePlaylistMove(Medialibrary medialibrary, long j, int i, int i2);

    private native boolean nativePlaylistRemove(Medialibrary medialibrary, long j, int i);

    private native MediaWrapper[] nativeSearch(Medialibrary medialibrary, long j, String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native boolean nativeSetFavorite(Medialibrary medialibrary, long j, boolean z);

    public PlaylistImpl(long j, String str, int i, long j2, int i2, int i3, int i4, int i5, boolean z) {
        super(j, str, i, j2, i2, i3, i4, i5, z);
    }

    public PlaylistImpl(Parcel parcel) {
        super(parcel);
    }

    public MediaWrapper[] getTracks() {
        return getTracks(true, false);
    }

    public MediaWrapper[] getTracks(boolean z, boolean z2) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetTracks(instance, this.mId, z, z2);
    }

    public MediaWrapper[] getPagedTracks(int i, int i2, boolean z, boolean z2) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetPagedTracks(instance, this.mId, i, i2, z, z2);
    }

    public int getRealTracksCount(boolean z, boolean z2) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return 0;
        }
        return nativeGetTracksCount(instance, this.mId, z, z2);
    }

    public boolean append(long j) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativePlaylistAppend(instance, this.mId, j)) {
                return true;
            }
        }
        return false;
    }

    public boolean append(long[] jArr) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativePlaylistAppendGroup(instance, this.mId, jArr);
    }

    public boolean append(List<Long> list) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance == null || !instance.isInitiated()) {
            return false;
        }
        int size = list.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = list.get(i).longValue();
        }
        return nativePlaylistAppendGroup(instance, this.mId, jArr);
    }

    public boolean add(long j, int i) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativePlaylistAdd(instance, this.mId, j, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean move(int i, int i2) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativePlaylistMove(instance, this.mId, i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(int i) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativePlaylistRemove(instance, this.mId, i);
    }

    public boolean delete() {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativePlaylistDelete(instance, this.mId);
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
}
