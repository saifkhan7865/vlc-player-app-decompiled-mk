package org.videolan.vlc.viewmodels;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "invoke", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)Ljava/lang/Long;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistModel.kt */
final class PlaylistModel$getTotalTime$1 extends Lambda implements Function1<MediaWrapper, Long> {
    final /* synthetic */ List<MediaWrapper> $mediaList;
    final /* synthetic */ PlaylistModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistModel$getTotalTime$1(PlaylistModel playlistModel, List<? extends MediaWrapper> list) {
        super(1);
        this.this$0 = playlistModel;
        this.$mediaList = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r0 = r0.getPlaylistManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Long invoke(org.videolan.medialibrary.interfaces.media.MediaWrapper r4) {
        /*
            r3 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            org.videolan.vlc.viewmodels.PlaylistModel r0 = r3.this$0
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            if (r0 == 0) goto L_0x001c
            org.videolan.vlc.media.PlaylistManager r0 = r0.getPlaylistManager()
            if (r0 == 0) goto L_0x001c
            int r0 = r0.getStopAfter()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            if (r0 == 0) goto L_0x0036
            r1 = -1
            int r2 = r0.intValue()
            if (r2 == r1) goto L_0x0036
            java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r1 = r3.$mediaList
            int r1 = r1.indexOf(r4)
            int r0 = r0.intValue()
            if (r1 > r0) goto L_0x0033
            goto L_0x0036
        L_0x0033:
            r0 = 0
            goto L_0x003a
        L_0x0036:
            long r0 = r4.getLength()
        L_0x003a:
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.PlaylistModel$getTotalTime$1.invoke(org.videolan.medialibrary.interfaces.media.MediaWrapper):java.lang.Long");
    }
}
