package org.videolan.vlc.util;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/util/EmptyPBSCallback;", "Lorg/videolan/vlc/PlaybackService$Callback;", "()V", "onMediaEvent", "", "event", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onMediaPlayerEvent", "Lorg/videolan/libvlc/MediaPlayer$Event;", "update", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModelsHelper.kt */
public final class EmptyPBSCallback implements PlaybackService.Callback {
    public static final EmptyPBSCallback INSTANCE = new EmptyPBSCallback();

    public void onMediaEvent(IMedia.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
    }

    public void onMediaPlayerEvent(MediaPlayer.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
    }

    public void update() {
    }

    private EmptyPBSCallback() {
    }
}
