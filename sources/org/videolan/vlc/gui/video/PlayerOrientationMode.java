package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/video/PlayerOrientationMode;", "", "locked", "", "orientation", "", "(ZI)V", "getLocked", "()Z", "setLocked", "(Z)V", "getOrientation", "()I", "setOrientation", "(I)V", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public final class PlayerOrientationMode {
    private boolean locked;
    private int orientation;

    public PlayerOrientationMode() {
        this(false, 0, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ PlayerOrientationMode copy$default(PlayerOrientationMode playerOrientationMode, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = playerOrientationMode.locked;
        }
        if ((i2 & 2) != 0) {
            i = playerOrientationMode.orientation;
        }
        return playerOrientationMode.copy(z, i);
    }

    public final boolean component1() {
        return this.locked;
    }

    public final int component2() {
        return this.orientation;
    }

    public final PlayerOrientationMode copy(boolean z, int i) {
        return new PlayerOrientationMode(z, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerOrientationMode)) {
            return false;
        }
        PlayerOrientationMode playerOrientationMode = (PlayerOrientationMode) obj;
        return this.locked == playerOrientationMode.locked && this.orientation == playerOrientationMode.orientation;
    }

    public int hashCode() {
        return (UInt$$ExternalSyntheticBackport0.m(this.locked) * 31) + this.orientation;
    }

    public String toString() {
        return "PlayerOrientationMode(locked=" + this.locked + ", orientation=" + this.orientation + ')';
    }

    public PlayerOrientationMode(boolean z, int i) {
        this.locked = z;
        this.orientation = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PlayerOrientationMode(boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? -1 : i);
    }

    public final boolean getLocked() {
        return this.locked;
    }

    public final void setLocked(boolean z) {
        this.locked = z;
    }

    public final int getOrientation() {
        return this.orientation;
    }

    public final void setOrientation(int i) {
        this.orientation = i;
    }
}
