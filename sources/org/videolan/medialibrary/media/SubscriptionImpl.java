package org.videolan.medialibrary.media;

import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.MlService;
import org.videolan.medialibrary.interfaces.media.Subscription;

public class SubscriptionImpl extends Subscription {
    private native Subscription[] nativeGetChildSubscriptions(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3);

    private native Subscription nativeGetParent(Medialibrary medialibrary, long j);

    private native long nativeGetSubscriptionCachedSize(Medialibrary medialibrary, long j);

    private native long nativeGetSubscriptionMaxCacheSize(Medialibrary medialibrary, long j);

    private native MediaWrapper[] nativeGetSubscriptionMedia(Medialibrary medialibrary, long j, int i, boolean z, boolean z2, boolean z3);

    private native int nativeGetSubscriptionNbMedia(Medialibrary medialibrary, long j);

    private native int nativeGetSubscriptionNbUnplayedMedia(Medialibrary medialibrary, long j);

    private native boolean nativeSetSubscriptionMaxCacheSize(Medialibrary medialibrary, long j, long j2);

    private native boolean nativeSetSubscriptionNewMediaNotification(Medialibrary medialibrary, long j, int i);

    private native int nativeSubscriptionNewMediaNotification(Medialibrary medialibrary, long j);

    private native boolean nativeSubscriptionRefresh(Medialibrary medialibrary, long j);

    SubscriptionImpl(long j, MlService.Type type, String str, long j2) {
        super(j, type, str, j2);
    }

    SubscriptionImpl(long j, int i, String str, long j2) {
        super(j, i, str, j2);
    }

    public int getNewMediaNotification() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeSubscriptionNewMediaNotification(instance, this.id);
        }
        return -1;
    }

    public boolean setNewMediaNotification(int i) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeSetSubscriptionNewMediaNotification(instance, this.id, i);
    }

    public long getCachedSize() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetSubscriptionCachedSize(instance, this.id);
        }
        return -2;
    }

    public long getMaxCacheSize() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetSubscriptionMaxCacheSize(instance, this.id);
        }
        return -2;
    }

    public boolean setMaxCacheSize(long j) {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            if (nativeSetSubscriptionMaxCacheSize(instance, this.id, j)) {
                return true;
            }
        }
        return false;
    }

    public int getNbUnplayedMedia() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetSubscriptionNbUnplayedMedia(instance, this.id);
        }
        return -1;
    }

    public Subscription[] getChildSubscriptions(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return new Subscription[0];
        }
        return nativeGetChildSubscriptions(instance, this.id, i, z, z2, z3);
    }

    public Subscription getParent() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetParent(instance, this.id);
        }
        return null;
    }

    public boolean refresh() {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeSubscriptionRefresh(instance, this.id);
    }

    public MediaWrapper[] getMedia(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetSubscriptionMedia(instance, this.id, i, z, z2, z3);
    }

    public int getNbMedia() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetSubscriptionNbMedia(instance, this.id);
        }
        return -1;
    }
}
