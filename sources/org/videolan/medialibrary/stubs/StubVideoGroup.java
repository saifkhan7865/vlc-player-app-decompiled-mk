package org.videolan.medialibrary.stubs;

import android.os.Parcel;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;

public class StubVideoGroup extends VideoGroup {
    public boolean add(long j) {
        return false;
    }

    public boolean destroy() {
        return false;
    }

    public long duration() {
        return 0;
    }

    public String getName() {
        return null;
    }

    public boolean remove(long j) {
        return false;
    }

    public boolean rename(String str) {
        return false;
    }

    public int searchTracksCount(String str) {
        return 0;
    }

    public boolean userInteracted() {
        return false;
    }

    public StubVideoGroup(String str, int i, int i2, int i3, boolean z) {
        super(0, str, i, i2, i3, z);
    }

    public StubVideoGroup(Parcel parcel) {
        super(parcel);
    }

    public MediaWrapper[] media(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return new MediaWrapper[0];
    }

    public MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return new MediaWrapper[0];
    }
}
