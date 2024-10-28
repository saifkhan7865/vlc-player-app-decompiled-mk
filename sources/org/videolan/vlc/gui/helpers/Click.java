package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/helpers/Click;", "", "position", "", "(I)V", "getPosition", "()I", "Lorg/videolan/vlc/gui/helpers/CtxClick;", "Lorg/videolan/vlc/gui/helpers/ImageClick;", "Lorg/videolan/vlc/gui/helpers/LongClick;", "Lorg/videolan/vlc/gui/helpers/SimpleClick;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EventsSource.kt */
public abstract class Click {
    private final int position;

    public /* synthetic */ Click(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    private Click(int i) {
        this.position = i;
    }

    public final int getPosition() {
        return this.position;
    }
}
