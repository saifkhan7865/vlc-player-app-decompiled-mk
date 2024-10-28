package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/media/Progress;", "", "time", "", "length", "(JJ)V", "getLength", "()J", "setLength", "(J)V", "getTime", "setTime", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerController.kt */
public final class Progress {
    private long length;
    private long time;

    public Progress() {
        this(0, 0, 3, (DefaultConstructorMarker) null);
    }

    public Progress(long j, long j2) {
        this.time = j;
        this.length = j2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Progress(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0 : j, (i & 2) != 0 ? 0 : j2);
    }

    public final long getLength() {
        return this.length;
    }

    public final long getTime() {
        return this.time;
    }

    public final void setLength(long j) {
        this.length = j;
    }

    public final void setTime(long j) {
        this.time = j;
    }
}
