package org.videolan.medialibrary.media;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class FolderImpl extends Folder {
    private native int nativeGetSearchCount(Medialibrary medialibrary, long j, String str, int i);

    private native MediaWrapper[] nativeMedia(Medialibrary medialibrary, long j, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    private native int nativeMediaCount(Medialibrary medialibrary, long j, int i);

    private native MediaWrapper[] nativeSearch(Medialibrary medialibrary, long j, String str, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4);

    private native boolean nativeSetFavorite(Medialibrary medialibrary, long j, boolean z);

    private native Folder[] nativeSubfolders(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    private native int nativeSubfoldersCount(Medialibrary medialibrary, long j, int i);

    public FolderImpl(long j, String str, String str2, int i, boolean z) {
        super(j, str, str2, i, z);
    }

    public FolderImpl(Parcel parcel) {
        super(parcel);
    }

    public MediaWrapper[] media(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeMedia(instance, this.mId, i, i2, z, z2, z3, i3, i4);
    }

    public int mediaCount(int i) {
        if (i == TYPE_FOLDER_VIDEO) {
            return this.mMediaCount;
        }
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeMediaCount(instance, this.mId, i);
        }
        return 0;
    }

    public Folder[] subfolders(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return new FolderImpl[0];
        }
        return nativeSubfolders(instance, this.mId, i, z, z2, z3, i2, i3);
    }

    public int subfoldersCount(int i) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeSubfoldersCount(instance, this.mId, i);
        }
        return 0;
    }

    public MediaWrapper[] searchTracks(String str, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeSearch(instance, this.mId, str, i, i2, z, z2, z3, i3, i4);
    }

    public int searchTracksCount(String str, int i) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return 0;
        }
        return nativeGetSearchCount(instance, this.mId, str, i);
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
