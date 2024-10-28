package org.videolan.medialibrary.stubs;

import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.MlService;
import org.videolan.medialibrary.interfaces.media.Subscription;

public class StubMlService extends MlService {
    public boolean addSubscription(String str) {
        return false;
    }

    public long getMaxCacheSize() {
        return 0;
    }

    public int getNbMedia() {
        return 0;
    }

    public int getNbSubscriptions() {
        return 0;
    }

    public int getNbUnplayedMedia() {
        return 0;
    }

    public boolean isAutoDownloadEnabled() {
        return false;
    }

    public boolean isNewMediaNotificationEnabled() {
        return false;
    }

    public boolean refresh() {
        return false;
    }

    public boolean setAutoDownloadEnabled(boolean z) {
        return false;
    }

    public boolean setMaxCacheSize(long j) {
        return false;
    }

    public boolean setNewMediaNotificationEnabled(boolean z) {
        return false;
    }

    StubMlService(MlService.Type type) {
        super(type);
    }

    StubMlService(int i) {
        super(i);
    }

    public Subscription[] getSubscriptions(int i, boolean z, boolean z2, boolean z3) {
        return new Subscription[0];
    }

    public MediaWrapper[] getMedia(int i, boolean z, boolean z2, boolean z3) {
        return new MediaWrapper[0];
    }
}
