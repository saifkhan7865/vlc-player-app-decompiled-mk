package org.videolan.vlc.providers.medialibrary;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"checkIsNetwork", "", "videoGroup", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "sanitizeGroups", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "([Lorg/videolan/medialibrary/interfaces/media/VideoGroup;)[Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoGroupsProvider.kt */
public final class VideoGroupsProviderKt {
    private static final void checkIsNetwork(VideoGroup videoGroup) {
        String scheme;
        MediaWrapper[] media = videoGroup.media(0, false, true, false, videoGroup.mediaCount(), 0);
        Intrinsics.checkNotNullExpressionValue(media, "media(...)");
        for (MediaWrapper uri : ArraysKt.filterNotNull((Object[]) media)) {
            Uri uri2 = uri.getUri();
            if (!(uri2 == null || (scheme = uri2.getScheme()) == null || BrowserutilsKt.isSchemeFile(scheme))) {
                videoGroup.isNetwork = true;
            }
        }
    }

    public static final MediaLibraryItem[] sanitizeGroups(VideoGroup[] videoGroupArr) {
        MediaLibraryItem mediaLibraryItem;
        Intrinsics.checkNotNullParameter(videoGroupArr, "<this>");
        Collection arrayList = new ArrayList(videoGroupArr.length);
        for (VideoGroup videoGroup : videoGroupArr) {
            if (videoGroup.mediaCount() == 1) {
                MediaWrapper[] media = videoGroup.media(0, false, true, false, 1, 0);
                Intrinsics.checkNotNullExpressionValue(media, "media(...)");
                mediaLibraryItem = (MediaWrapper) ArraysKt.getOrNull((T[]) (Object[]) media, 0);
                if (mediaLibraryItem == null) {
                    checkIsNetwork(videoGroup);
                    mediaLibraryItem = videoGroup;
                }
            } else {
                checkIsNetwork(videoGroup);
                mediaLibraryItem = videoGroup;
            }
            arrayList.add(mediaLibraryItem);
        }
        return (MediaLibraryItem[]) ((List) arrayList).toArray(new MediaLibraryItem[0]);
    }
}
