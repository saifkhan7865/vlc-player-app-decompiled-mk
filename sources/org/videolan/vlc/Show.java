package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/Show;", "Lorg/videolan/vlc/Notification;", "done", "", "scheduled", "(II)V", "getDone", "()I", "getScheduled", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaParsingService.kt */
final class Show extends Notification {
    private final int done;
    private final int scheduled;

    public Show(int i, int i2) {
        super((DefaultConstructorMarker) null);
        this.done = i;
        this.scheduled = i2;
    }

    public final int getDone() {
        return this.done;
    }

    public final int getScheduled() {
        return this.scheduled;
    }
}
