package org.videolan.medialibrary.interfaces.media;

import org.videolan.medialibrary.interfaces.media.MlService;

public abstract class Subscription {
    public long id;
    public String name;
    protected long parentId;
    public MlService.Type type;

    public abstract long getCachedSize();

    public abstract Subscription[] getChildSubscriptions(int i, boolean z, boolean z2, boolean z3);

    public abstract long getMaxCacheSize();

    public abstract MediaWrapper[] getMedia(int i, boolean z, boolean z2, boolean z3);

    public abstract int getNbMedia();

    public abstract int getNbUnplayedMedia();

    public abstract int getNewMediaNotification();

    public abstract Subscription getParent();

    public abstract boolean refresh();

    public abstract boolean setMaxCacheSize(long j);

    public abstract boolean setNewMediaNotification(int i);

    public Subscription(long j, MlService.Type type2, String str, long j2) {
        this.id = j;
        this.type = type2;
        this.name = str;
        this.parentId = j2;
    }

    public Subscription(long j, int i, String str, long j2) {
        this.id = j;
        this.type = MlService.Type.getValue(i);
        this.name = str;
        this.parentId = j2;
    }
}
