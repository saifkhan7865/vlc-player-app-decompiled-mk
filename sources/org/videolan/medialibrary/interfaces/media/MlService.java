package org.videolan.medialibrary.interfaces.media;

public abstract class MlService {
    public Type type;

    public abstract boolean addSubscription(String str);

    public abstract long getMaxCacheSize();

    public abstract MediaWrapper[] getMedia(int i, boolean z, boolean z2, boolean z3);

    public abstract int getNbMedia();

    public abstract int getNbSubscriptions();

    public abstract int getNbUnplayedMedia();

    public abstract Subscription[] getSubscriptions(int i, boolean z, boolean z2, boolean z3);

    public abstract boolean isAutoDownloadEnabled();

    public abstract boolean isNewMediaNotificationEnabled();

    public abstract boolean refresh();

    public abstract boolean setAutoDownloadEnabled(boolean z);

    public abstract boolean setMaxCacheSize(long j);

    public abstract boolean setNewMediaNotificationEnabled(boolean z);

    public enum Type {
        PODCAST(1);
        
        public final int value;

        private Type(int i) {
            this.value = i;
        }

        public static Type getValue(int i) {
            for (Type type : values()) {
                if (type.value == i) {
                    return type;
                }
            }
            return null;
        }
    }

    protected MlService(Type type2) {
        this.type = type2;
    }

    protected MlService(int i) {
        this.type = Type.getValue(i);
    }
}
