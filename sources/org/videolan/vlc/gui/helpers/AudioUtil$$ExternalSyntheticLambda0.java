package org.videolan.vlc.gui.helpers;

import androidx.fragment.app.FragmentActivity;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioUtil$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FragmentActivity f$0;
    public final /* synthetic */ MediaWrapper f$1;

    public /* synthetic */ AudioUtil$$ExternalSyntheticLambda0(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper) {
        this.f$0 = fragmentActivity;
        this.f$1 = mediaWrapper;
    }

    public final void run() {
        AudioUtil.setRingtone$lambda$0(this.f$0, this.f$1);
    }
}
