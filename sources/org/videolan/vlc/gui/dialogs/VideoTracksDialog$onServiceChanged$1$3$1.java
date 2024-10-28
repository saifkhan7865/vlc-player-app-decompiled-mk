package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.dialogs.VideoTracksDialog;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "track", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTracksDialog.kt */
final class VideoTracksDialog$onServiceChanged$1$3$1 extends Lambda implements Function1<VlcTrack, Unit> {
    final /* synthetic */ VideoTracksDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoTracksDialog$onServiceChanged$1$3$1(VideoTracksDialog videoTracksDialog) {
        super(1);
        this.this$0 = videoTracksDialog;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((VlcTrack) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(VlcTrack vlcTrack) {
        Intrinsics.checkNotNullParameter(vlcTrack, "track");
        this.this$0.getTrackSelectionListener().invoke(vlcTrack.getId(), VideoTracksDialog.TrackType.SPU);
    }
}
