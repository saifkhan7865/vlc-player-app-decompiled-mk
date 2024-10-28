package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "invoke", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)Ljava/lang/Long;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$previousTotalTime$2 extends Lambda implements Function1<MediaWrapper, Long> {
    public static final PlaylistManager$previousTotalTime$2 INSTANCE = new PlaylistManager$previousTotalTime$2();

    PlaylistManager$previousTotalTime$2() {
        super(1);
    }

    public final Long invoke(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "it");
        return Long.valueOf(mediaWrapper.getLength());
    }
}
