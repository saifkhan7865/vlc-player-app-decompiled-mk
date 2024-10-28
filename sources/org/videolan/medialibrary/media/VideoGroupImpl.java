package org.videolan.medialibrary.media;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;

public class VideoGroupImpl extends VideoGroup {
    private native int nativeGetSearchCount(Medialibrary medialibrary, long j, String str);

    private native boolean nativeGroupAddId(Medialibrary medialibrary, long j, long j2);

    private native boolean nativeGroupDestroy(Medialibrary medialibrary, long j);

    private native long nativeGroupDuration(Medialibrary medialibrary, long j);

    private native String nativeGroupName(Medialibrary medialibrary, long j);

    private native boolean nativeGroupRemoveId(Medialibrary medialibrary, long j, long j2);

    private native boolean nativeGroupRename(Medialibrary medialibrary, long j, String str);

    private native boolean nativeGroupUserInteracted(Medialibrary medialibrary, long j);

    private native MediaWrapper[] nativeMedia(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native MediaWrapper[] nativeSearch(Medialibrary medialibrary, long j, String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native boolean nativeSetFavorite(Medialibrary medialibrary, long j, boolean z);

    VideoGroupImpl(long j, String str, int i, int i2, int i3, boolean z) {
        super(j, str, i, i2, i3, z);
    }

    public VideoGroupImpl(Parcel parcel) {
        super(parcel);
    }

    public MediaWrapper[] media(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeMedia(instance, this.mId, i, z, z2, z3, i2, i3);
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

    public boolean add(long j) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativeGroupAddId(instance, this.mId, j)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(long j) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativeGroupRemoveId(instance, this.mId, j)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGroupName(instance, this.mId);
        }
        return null;
    }

    public boolean rename(String str) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeGroupRename(instance, this.mId, str);
    }

    public boolean userInteracted() {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeGroupUserInteracted(instance, this.mId);
    }

    public long duration() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGroupDuration(instance, this.mId);
        }
        return 0;
    }

    public boolean destroy() {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeGroupDestroy(instance, this.mId);
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
