package org.videolan.vlc.gui.helpers;

import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import nl.dionsegijn.konfetti.xml.KonfettiView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UiTools$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ ImageView f$0;
    public final /* synthetic */ FragmentActivity f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ KonfettiView f$3;

    public /* synthetic */ UiTools$$ExternalSyntheticLambda11(ImageView imageView, FragmentActivity fragmentActivity, int i, KonfettiView konfettiView) {
        this.f$0 = imageView;
        this.f$1 = fragmentActivity;
        this.f$2 = i;
        this.f$3 = konfettiView;
    }

    public final void run() {
        UiTools.fillAboutView$lambda$8$lambda$7(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
