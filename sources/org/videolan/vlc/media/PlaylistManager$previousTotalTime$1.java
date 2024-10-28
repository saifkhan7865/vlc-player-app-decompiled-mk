package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "prevIndex", "", "<anonymous parameter 1>", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "invoke", "(ILorg/videolan/medialibrary/interfaces/media/MediaWrapper;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$previousTotalTime$1 extends Lambda implements Function2<Integer, MediaWrapper, Boolean> {
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$previousTotalTime$1(PlaylistManager playlistManager) {
        super(2);
        this.this$0 = playlistManager;
    }

    public final Boolean invoke(int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "<anonymous parameter 1>");
        return Boolean.valueOf(this.this$0.previous.contains(Integer.valueOf(i)));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).intValue(), (MediaWrapper) obj2);
    }
}
