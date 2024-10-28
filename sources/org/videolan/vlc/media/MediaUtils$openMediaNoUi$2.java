package org.videolan.vlc.media;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/media/MediaUtils$openMediaNoUi$2", "Lorg/videolan/vlc/media/MediaUtils$BaseCallBack;", "onServiceReady", "", "service", "Lorg/videolan/vlc/PlaybackService;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaUtils.kt */
public final class MediaUtils$openMediaNoUi$2 extends MediaUtils.BaseCallBack {
    final /* synthetic */ MediaWrapper $media;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$openMediaNoUi$2(Context context, MediaWrapper mediaWrapper) {
        super(context);
        this.$media = mediaWrapper;
    }

    public void onServiceReady(PlaybackService playbackService) {
        Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
        PlaybackService.load$default(playbackService, this.$media, 0, 2, (Object) null);
    }
}
