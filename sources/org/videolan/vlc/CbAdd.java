package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/CbAdd;", "Lorg/videolan/vlc/CbAction;", "cb", "Lorg/videolan/vlc/PlaybackService$Callback;", "(Lorg/videolan/vlc/PlaybackService$Callback;)V", "getCb", "()Lorg/videolan/vlc/PlaybackService$Callback;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
final class CbAdd extends CbAction {
    private final PlaybackService.Callback cb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CbAdd(PlaybackService.Callback callback) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(callback, "cb");
        this.cb = callback;
    }

    public final PlaybackService.Callback getCb() {
        return this.cb;
    }
}
