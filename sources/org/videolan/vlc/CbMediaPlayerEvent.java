package org.videolan.vlc;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.MediaPlayer;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/CbMediaPlayerEvent;", "Lorg/videolan/vlc/CbAction;", "event", "Lorg/videolan/libvlc/MediaPlayer$Event;", "(Lorg/videolan/libvlc/MediaPlayer$Event;)V", "getEvent", "()Lorg/videolan/libvlc/MediaPlayer$Event;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
final class CbMediaPlayerEvent extends CbAction {
    private final MediaPlayer.Event event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CbMediaPlayerEvent(MediaPlayer.Event event2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(event2, NotificationCompat.CATEGORY_EVENT);
        this.event = event2;
    }

    public final MediaPlayer.Event getEvent() {
        return this.event;
    }
}
