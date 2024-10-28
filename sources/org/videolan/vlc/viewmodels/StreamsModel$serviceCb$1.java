package org.videolan.vlc.viewmodels;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.util.EmptyPBSCallback;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001J\u0011\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0001J\b\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t"}, d2 = {"org/videolan/vlc/viewmodels/StreamsModel$serviceCb$1", "Lorg/videolan/vlc/PlaybackService$Callback;", "onMediaEvent", "", "event", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onMediaPlayerEvent", "Lorg/videolan/libvlc/MediaPlayer$Event;", "update", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StreamsModel.kt */
public final class StreamsModel$serviceCb$1 implements PlaybackService.Callback {
    private final /* synthetic */ EmptyPBSCallback $$delegate_0 = EmptyPBSCallback.INSTANCE;
    final /* synthetic */ StreamsModel this$0;

    public void onMediaEvent(IMedia.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        this.$$delegate_0.onMediaEvent(event);
    }

    public void onMediaPlayerEvent(MediaPlayer.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        this.$$delegate_0.onMediaPlayerEvent(event);
    }

    StreamsModel$serviceCb$1(StreamsModel streamsModel) {
        this.this$0 = streamsModel;
    }

    public void update() {
        this.this$0.refresh();
    }
}
