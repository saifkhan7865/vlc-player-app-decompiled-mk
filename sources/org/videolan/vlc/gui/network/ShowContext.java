package org.videolan.vlc.gui.network;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/network/ShowContext;", "Lorg/videolan/vlc/gui/network/MrlAction;", "position", "", "(I)V", "getPosition", "()I", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MRLAdapter.kt */
public final class ShowContext extends MrlAction {
    private final int position;

    public ShowContext(int i) {
        super((DefaultConstructorMarker) null);
        this.position = i;
    }

    public final int getPosition() {
        return this.position;
    }
}
