package org.videolan.medialibrary.media;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Bookmark;

public class BookmarkImpl extends Bookmark {
    private native boolean nativeMove(Medialibrary medialibrary, long j, long j2);

    private native boolean nativeSetDescription(Medialibrary medialibrary, long j, String str);

    private native boolean nativeSetName(Medialibrary medialibrary, long j, String str);

    private native boolean nativeSetNameAndDescription(Medialibrary medialibrary, long j, String str, String str2);

    public BookmarkImpl(long j, String str, String str2, long j2, long j3) {
        super(j, str, str2, j2, j3);
    }

    public BookmarkImpl(Parcel parcel) {
        super(parcel);
    }

    public boolean setName(String str) {
        setTitle(str);
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeSetName(instance, this.mId, str);
    }

    public boolean updateDescription(String str) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeSetDescription(instance, this.mId, str);
    }

    public boolean setNameAndDescription(String str, String str2) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativeSetNameAndDescription(instance, this.mId, str, str2)) {
                return true;
            }
        }
        return false;
    }

    public boolean move(long j) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativeMove(instance, this.mId, j)) {
                return true;
            }
        }
        return false;
    }
}
