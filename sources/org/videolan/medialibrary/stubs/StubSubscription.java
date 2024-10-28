package org.videolan.medialibrary.stubs;

import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.MlService;
import org.videolan.medialibrary.interfaces.media.Subscription;

public class StubSubscription extends Subscription {
    public long getCachedSize() {
        return 0;
    }

    public Subscription[] getChildSubscriptions(int i, boolean z, boolean z2, boolean z3) {
        return null;
    }

    public long getMaxCacheSize() {
        return 0;
    }

    public MediaWrapper[] getMedia(int i, boolean z, boolean z2, boolean z3) {
        return null;
    }

    public int getNbMedia() {
        return 0;
    }

    public int getNbUnplayedMedia() {
        return 0;
    }

    public int getNewMediaNotification() {
        return -1;
    }

    public Subscription getParent() {
        return null;
    }

    public boolean refresh() {
        return false;
    }

    public boolean setMaxCacheSize(long j) {
        return false;
    }

    public boolean setNewMediaNotification(int i) {
        return false;
    }

    StubSubscription(long j, MlService.Type type, String str, long j2) {
        super(j, type, str, j2);
    }

    StubSubscription(long j, int i, String str, long j2) {
        super(j, i, str, j2);
    }
}
