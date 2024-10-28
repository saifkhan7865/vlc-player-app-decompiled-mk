package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/media/ABRepeat;", "", "start", "", "stop", "(JJ)V", "getStart", "()J", "setStart", "(J)V", "getStop", "setStop", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
public final class ABRepeat {
    private long start;
    private long stop;

    public ABRepeat() {
        this(0, 0, 3, (DefaultConstructorMarker) null);
    }

    public ABRepeat(long j, long j2) {
        this.start = j;
        this.stop = j2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ABRepeat(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? -1 : j, (i & 2) != 0 ? -1 : j2);
    }

    public final long getStart() {
        return this.start;
    }

    public final long getStop() {
        return this.stop;
    }

    public final void setStart(long j) {
        this.start = j;
    }

    public final void setStop(long j) {
        this.stop = j;
    }
}
