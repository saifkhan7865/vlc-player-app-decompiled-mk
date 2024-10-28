package org.videolan.vlc;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.SingletonHolder;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003R\u000e\u0010\u0005\u001a\u00020\u0002X.¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/MediaBrowserInstance;", "Lorg/videolan/tools/SingletonHolder;", "Landroid/support/v4/media/MediaBrowserCompat;", "Landroid/content/Context;", "()V", "mediaBrowser", "init", "context", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserInstance.kt */
public final class MediaBrowserInstance extends SingletonHolder<MediaBrowserCompat, Context> {
    public static final MediaBrowserInstance INSTANCE = new MediaBrowserInstance();
    private static MediaBrowserCompat mediaBrowser;

    private MediaBrowserInstance() {
        super(AnonymousClass1.INSTANCE);
    }

    public final MediaBrowserCompat init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(context, new ComponentName(context, PlaybackService.class), new MediaBrowserInstance$init$1(), (Bundle) null);
        mediaBrowserCompat.connect();
        return mediaBrowserCompat;
    }
}
