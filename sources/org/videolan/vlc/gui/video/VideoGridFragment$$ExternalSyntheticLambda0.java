package org.videolan.vlc.gui.video;

import androidx.lifecycle.Observer;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoGridFragment$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ VideoGridFragment f$0;

    public /* synthetic */ VideoGridFragment$$ExternalSyntheticLambda0(VideoGridFragment videoGridFragment) {
        this.f$0 = videoGridFragment;
    }

    public final void onChanged(Object obj) {
        VideoGridFragment.thumbObs$lambda$18(this.f$0, (MediaWrapper) obj);
    }
}
