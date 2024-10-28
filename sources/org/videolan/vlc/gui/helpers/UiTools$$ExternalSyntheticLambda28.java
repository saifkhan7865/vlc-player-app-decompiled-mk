package org.videolan.vlc.gui.helpers;

import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import nl.dionsegijn.konfetti.xml.KonfettiView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UiTools$$ExternalSyntheticLambda28 implements View.OnClickListener {
    public final /* synthetic */ ImageView f$0;
    public final /* synthetic */ FragmentActivity f$1;
    public final /* synthetic */ KonfettiView f$2;

    public /* synthetic */ UiTools$$ExternalSyntheticLambda28(ImageView imageView, FragmentActivity fragmentActivity, KonfettiView konfettiView) {
        this.f$0 = imageView;
        this.f$1 = fragmentActivity;
        this.f$2 = konfettiView;
    }

    public final void onClick(View view) {
        UiTools.fillAboutView$lambda$8(this.f$0, this.f$1, this.f$2, view);
    }
}
