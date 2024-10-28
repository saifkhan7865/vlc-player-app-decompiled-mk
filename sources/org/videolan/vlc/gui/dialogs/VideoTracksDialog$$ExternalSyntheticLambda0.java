package org.videolan.vlc.gui.dialogs;

import android.view.View;
import org.videolan.vlc.gui.dialogs.VideoTracksDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoTracksDialog$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ VideoTracksDialog f$0;
    public final /* synthetic */ VideoTracksDialog.VideoTrackOption f$1;

    public /* synthetic */ VideoTracksDialog$$ExternalSyntheticLambda0(VideoTracksDialog videoTracksDialog, VideoTracksDialog.VideoTrackOption videoTrackOption) {
        this.f$0 = videoTracksDialog;
        this.f$1 = videoTrackOption;
    }

    public final void onClick(View view) {
        VideoTracksDialog.generateOptionItem$lambda$10(this.f$0, this.f$1, view);
    }
}
