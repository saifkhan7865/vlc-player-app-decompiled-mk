package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/ScanProgress;", "", "parsing", "", "progressText", "", "inDiscovery", "", "(FLjava/lang/String;Z)V", "getInDiscovery", "()Z", "getParsing", "()F", "getProgressText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaParsingService.kt */
public final class ScanProgress {
    private final boolean inDiscovery;
    private final float parsing;
    private final String progressText;

    public static /* synthetic */ ScanProgress copy$default(ScanProgress scanProgress, float f, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            f = scanProgress.parsing;
        }
        if ((i & 2) != 0) {
            str = scanProgress.progressText;
        }
        if ((i & 4) != 0) {
            z = scanProgress.inDiscovery;
        }
        return scanProgress.copy(f, str, z);
    }

    public final float component1() {
        return this.parsing;
    }

    public final String component2() {
        return this.progressText;
    }

    public final boolean component3() {
        return this.inDiscovery;
    }

    public final ScanProgress copy(float f, String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "progressText");
        return new ScanProgress(f, str, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScanProgress)) {
            return false;
        }
        ScanProgress scanProgress = (ScanProgress) obj;
        return Float.compare(this.parsing, scanProgress.parsing) == 0 && Intrinsics.areEqual((Object) this.progressText, (Object) scanProgress.progressText) && this.inDiscovery == scanProgress.inDiscovery;
    }

    public int hashCode() {
        return (((Float.floatToIntBits(this.parsing) * 31) + this.progressText.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.inDiscovery);
    }

    public String toString() {
        return "ScanProgress(parsing=" + this.parsing + ", progressText=" + this.progressText + ", inDiscovery=" + this.inDiscovery + ')';
    }

    public ScanProgress(float f, String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "progressText");
        this.parsing = f;
        this.progressText = str;
        this.inDiscovery = z;
    }

    public final boolean getInDiscovery() {
        return this.inDiscovery;
    }

    public final float getParsing() {
        return this.parsing;
    }

    public final String getProgressText() {
        return this.progressText;
    }
}
