package org.videolan.medialibrary.stubs;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.media.Bookmark;

public class StubBookmark extends Bookmark {
    private StubDataSource dt = StubDataSource.getInstance();

    public boolean move(long j) {
        return false;
    }

    public boolean setName(String str) {
        return false;
    }

    public boolean setNameAndDescription(String str, String str2) {
        return false;
    }

    public boolean updateDescription(String str) {
        return false;
    }

    public StubBookmark(long j, String str, String str2, long j2, long j3) {
        super(j, str, str2, j2, j3);
    }

    public StubBookmark(Parcel parcel) {
        super(parcel);
    }
}
