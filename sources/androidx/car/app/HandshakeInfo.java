package androidx.car.app;

import j$.util.Objects;

public final class HandshakeInfo {
    private final int mHostCarAppApiLevel;
    private final String mHostPackageName;

    public HandshakeInfo(String str, int i) {
        this.mHostPackageName = str;
        this.mHostCarAppApiLevel = i;
    }

    private HandshakeInfo() {
        this.mHostPackageName = null;
        this.mHostCarAppApiLevel = 0;
    }

    public String getHostPackageName() {
        return (String) Objects.requireNonNull(this.mHostPackageName);
    }

    public int getHostCarAppApiLevel() {
        return this.mHostCarAppApiLevel;
    }
}
