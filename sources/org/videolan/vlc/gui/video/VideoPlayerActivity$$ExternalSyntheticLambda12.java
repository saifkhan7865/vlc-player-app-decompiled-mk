package org.videolan.vlc.gui.video;

import android.content.DialogInterface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$$ExternalSyntheticLambda12 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ VideoPlayerActivity f$0;

    public /* synthetic */ VideoPlayerActivity$$ExternalSyntheticLambda12(VideoPlayerActivity videoPlayerActivity) {
        this.f$0 = videoPlayerActivity;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        VideoPlayerActivity.encounteredError$lambda$32(this.f$0, dialogInterface);
    }
}
