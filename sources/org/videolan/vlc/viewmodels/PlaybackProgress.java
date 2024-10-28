package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/viewmodels/PlaybackProgress;", "", "time", "", "length", "timeText", "", "lengthText", "(JJLjava/lang/String;Ljava/lang/String;)V", "getLength", "()J", "getLengthText", "()Ljava/lang/String;", "getTime", "getTimeText", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistModel.kt */
public final class PlaybackProgress {
    private final long length;
    private final String lengthText;
    private final long time;
    private final String timeText;

    public static /* synthetic */ PlaybackProgress copy$default(PlaybackProgress playbackProgress, long j, long j2, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = playbackProgress.time;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = playbackProgress.length;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            str = playbackProgress.timeText;
        }
        String str3 = str;
        if ((i & 8) != 0) {
            str2 = playbackProgress.lengthText;
        }
        return playbackProgress.copy(j3, j4, str3, str2);
    }

    public final long component1() {
        return this.time;
    }

    public final long component2() {
        return this.length;
    }

    public final String component3() {
        return this.timeText;
    }

    public final String component4() {
        return this.lengthText;
    }

    public final PlaybackProgress copy(long j, long j2, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "timeText");
        Intrinsics.checkNotNullParameter(str2, "lengthText");
        return new PlaybackProgress(j, j2, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaybackProgress)) {
            return false;
        }
        PlaybackProgress playbackProgress = (PlaybackProgress) obj;
        return this.time == playbackProgress.time && this.length == playbackProgress.length && Intrinsics.areEqual((Object) this.timeText, (Object) playbackProgress.timeText) && Intrinsics.areEqual((Object) this.lengthText, (Object) playbackProgress.lengthText);
    }

    public int hashCode() {
        return (((((UInt$$ExternalSyntheticBackport0.m(this.time) * 31) + UInt$$ExternalSyntheticBackport0.m(this.length)) * 31) + this.timeText.hashCode()) * 31) + this.lengthText.hashCode();
    }

    public String toString() {
        return "PlaybackProgress(time=" + this.time + ", length=" + this.length + ", timeText=" + this.timeText + ", lengthText=" + this.lengthText + ')';
    }

    public PlaybackProgress(long j, long j2, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "timeText");
        Intrinsics.checkNotNullParameter(str2, "lengthText");
        this.time = j;
        this.length = j2;
        this.timeText = str;
        this.lengthText = str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PlaybackProgress(long r10, long r12, java.lang.String r14, java.lang.String r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r9 = this;
            r0 = r16 & 4
            java.lang.String r1 = "millisToString(...)"
            if (r0 == 0) goto L_0x000f
            java.lang.String r0 = org.videolan.medialibrary.Tools.millisToString(r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r7 = r0
            goto L_0x0010
        L_0x000f:
            r7 = r14
        L_0x0010:
            r0 = r16 & 8
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = org.videolan.medialibrary.Tools.millisToString(r12)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r8 = r0
            goto L_0x001e
        L_0x001d:
            r8 = r15
        L_0x001e:
            r2 = r9
            r3 = r10
            r5 = r12
            r2.<init>(r3, r5, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.PlaybackProgress.<init>(long, long, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final long getTime() {
        return this.time;
    }

    public final long getLength() {
        return this.length;
    }

    public final String getTimeText() {
        return this.timeText;
    }

    public final String getLengthText() {
        return this.lengthText;
    }
}
