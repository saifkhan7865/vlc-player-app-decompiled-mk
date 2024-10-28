package org.videolan.television.util;

import android.net.Uri;
import kotlin.Metadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.FileProviderKt;
import org.videolan.vlc.R;
import org.videolan.vlc.util.ThumbnailsProvider;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"getThumb", "Landroid/net/Uri;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "television_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: TVSearchProvider.kt */
public final class TVSearchProviderKt {
    /* access modifiers changed from: private */
    public static final Uri getThumb(MediaWrapper mediaWrapper) {
        if (!mediaWrapper.isThumbnailGenerated()) {
            ThumbnailsProvider.INSTANCE.getVideoThumbnail(mediaWrapper, 512);
        }
        Uri parse = Uri.parse("android.resource://org.videolan.vlc/" + R.drawable.ic_video_big);
        String artworkMrl = mediaWrapper.getArtworkMrl();
        if (artworkMrl == null) {
            return parse;
        }
        try {
            return FileProviderKt.getFileUri(artworkMrl);
        } catch (IllegalArgumentException unused) {
            return parse;
        }
    }
}
