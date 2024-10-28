package androidx.car.app;

import j$.util.Objects;

public final class HostInfo {
    private final String mPackageName;
    private final int mUid;

    public HostInfo(String str, int i) {
        this.mPackageName = (String) Objects.requireNonNull(str);
        this.mUid = i;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getUid() {
        return this.mUid;
    }

    public String toString() {
        return this.mPackageName + ", uid: " + this.mUid;
    }
}
