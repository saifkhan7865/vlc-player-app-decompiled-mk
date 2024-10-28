package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/Init;", "Lorg/videolan/vlc/MLAction;", "upgrade", "", "parse", "removeDevices", "(ZZZ)V", "getParse", "()Z", "getRemoveDevices", "getUpgrade", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaParsingService.kt */
final class Init extends MLAction {
    private final boolean parse;
    private final boolean removeDevices;
    private final boolean upgrade;

    public Init(boolean z, boolean z2, boolean z3) {
        super((DefaultConstructorMarker) null);
        this.upgrade = z;
        this.parse = z2;
        this.removeDevices = z3;
    }

    public final boolean getParse() {
        return this.parse;
    }

    public final boolean getRemoveDevices() {
        return this.removeDevices;
    }

    public final boolean getUpgrade() {
        return this.upgrade;
    }
}
