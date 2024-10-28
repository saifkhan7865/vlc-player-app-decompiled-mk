package org.videolan.medialibrary.media;

import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.MlService;
import org.videolan.medialibrary.interfaces.media.Subscription;

public class MlServiceImpl extends MlService {
    private native boolean nativeAddSubscription(Medialibrary medialibrary, int i, String str);

    private native int nativeGetNbMedia(Medialibrary medialibrary, int i);

    private native int nativeGetNbSubscriptions(Medialibrary medialibrary, int i);

    private native int nativeGetNbUnplayedMedia(Medialibrary medialibrary, int i);

    private native long nativeGetServiceMaxCacheSize(Medialibrary medialibrary, int i);

    private native MediaWrapper[] nativeGetServiceMedia(Medialibrary medialibrary, int i, int i2, boolean z, boolean z2, boolean z3);

    private native Subscription[] nativeGetSubscriptions(Medialibrary medialibrary, int i, int i2, boolean z, boolean z2, boolean z3);

    private native boolean nativeIsAutoDownloadEnabled(Medialibrary medialibrary, int i);

    private native boolean nativeIsNewMediaNotificationEnabled(Medialibrary medialibrary, int i);

    private native boolean nativeServiceRefresh(Medialibrary medialibrary, int i);

    private native boolean nativeSetAutoDownloadEnabled(Medialibrary medialibrary, int i, boolean z);

    private native boolean nativeSetNewMediaNotificationEnabled(Medialibrary medialibrary, int i, boolean z);

    private native boolean nativeSetServiceMaxCacheSize(Medialibrary medialibrary, int i, long j);

    MlServiceImpl(MlService.Type type) {
        super(type);
    }

    MlServiceImpl(int i) {
        super(i);
    }

    public boolean addSubscription(String str) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeAddSubscription(instance, this.type.value, str);
    }

    public boolean isAutoDownloadEnabled() {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeIsAutoDownloadEnabled(instance, this.type.value);
    }

    public boolean setAutoDownloadEnabled(boolean z) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeSetAutoDownloadEnabled(instance, this.type.value, z);
    }

    public boolean isNewMediaNotificationEnabled() {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeIsNewMediaNotificationEnabled(instance, this.type.value);
    }

    public boolean setNewMediaNotificationEnabled(boolean z) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeSetNewMediaNotificationEnabled(instance, this.type.value, z);
    }

    public long getMaxCacheSize() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetServiceMaxCacheSize(instance, this.type.value);
        }
        return -2;
    }

    public boolean setMaxCacheSize(long j) {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeSetServiceMaxCacheSize(instance, this.type.value, j);
    }

    public int getNbSubscriptions() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetNbSubscriptions(instance, this.type.value);
        }
        return -1;
    }

    public int getNbUnplayedMedia() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetNbUnplayedMedia(instance, this.type.value);
        }
        return -1;
    }

    public Subscription[] getSubscriptions(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return new Subscription[0];
        }
        return nativeGetSubscriptions(instance, this.type.value, i, z, z2, z3);
    }

    public int getNbMedia() {
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeGetNbMedia(instance, this.type.value);
        }
        return -1;
    }

    public MediaWrapper[] getMedia(int i, boolean z, boolean z2, boolean z3) {
        Medialibrary instance = Medialibrary.getInstance();
        if (!instance.isInitiated()) {
            return Medialibrary.EMPTY_COLLECTION;
        }
        return nativeGetServiceMedia(instance, this.type.value, i, z, z2, z3);
    }

    public boolean refresh() {
        Medialibrary instance = Medialibrary.getInstance();
        return instance.isInitiated() && nativeServiceRefresh(instance, this.type.value);
    }
}
