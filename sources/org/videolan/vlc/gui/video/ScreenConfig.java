package org.videolan.vlc.gui.video;

import android.util.DisplayMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/gui/video/ScreenConfig;", "", "metrics", "Landroid/util/DisplayMetrics;", "xRange", "", "yRange", "orientation", "(Landroid/util/DisplayMetrics;III)V", "getMetrics", "()Landroid/util/DisplayMetrics;", "getOrientation", "()I", "getXRange", "getYRange", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTouchDelegate.kt */
public final class ScreenConfig {
    private final DisplayMetrics metrics;
    private final int orientation;
    private final int xRange;
    private final int yRange;

    public static /* synthetic */ ScreenConfig copy$default(ScreenConfig screenConfig, DisplayMetrics displayMetrics, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            displayMetrics = screenConfig.metrics;
        }
        if ((i4 & 2) != 0) {
            i = screenConfig.xRange;
        }
        if ((i4 & 4) != 0) {
            i2 = screenConfig.yRange;
        }
        if ((i4 & 8) != 0) {
            i3 = screenConfig.orientation;
        }
        return screenConfig.copy(displayMetrics, i, i2, i3);
    }

    public final DisplayMetrics component1() {
        return this.metrics;
    }

    public final int component2() {
        return this.xRange;
    }

    public final int component3() {
        return this.yRange;
    }

    public final int component4() {
        return this.orientation;
    }

    public final ScreenConfig copy(DisplayMetrics displayMetrics, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(displayMetrics, "metrics");
        return new ScreenConfig(displayMetrics, i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScreenConfig)) {
            return false;
        }
        ScreenConfig screenConfig = (ScreenConfig) obj;
        return Intrinsics.areEqual((Object) this.metrics, (Object) screenConfig.metrics) && this.xRange == screenConfig.xRange && this.yRange == screenConfig.yRange && this.orientation == screenConfig.orientation;
    }

    public int hashCode() {
        return (((((this.metrics.hashCode() * 31) + this.xRange) * 31) + this.yRange) * 31) + this.orientation;
    }

    public String toString() {
        return "ScreenConfig(metrics=" + this.metrics + ", xRange=" + this.xRange + ", yRange=" + this.yRange + ", orientation=" + this.orientation + ')';
    }

    public ScreenConfig(DisplayMetrics displayMetrics, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(displayMetrics, "metrics");
        this.metrics = displayMetrics;
        this.xRange = i;
        this.yRange = i2;
        this.orientation = i3;
    }

    public final DisplayMetrics getMetrics() {
        return this.metrics;
    }

    public final int getOrientation() {
        return this.orientation;
    }

    public final int getXRange() {
        return this.xRange;
    }

    public final int getYRange() {
        return this.yRange;
    }
}
