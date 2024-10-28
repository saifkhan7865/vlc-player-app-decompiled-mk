package org.videolan.vlc.gui.onboarding;

import android.widget.Button;
import android.widget.CompoundButton;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OnboardingScanningFragment$$ExternalSyntheticLambda0 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ OnboardingScanningFragment f$0;
    public final /* synthetic */ Button f$1;

    public /* synthetic */ OnboardingScanningFragment$$ExternalSyntheticLambda0(OnboardingScanningFragment onboardingScanningFragment, Button button) {
        this.f$0 = onboardingScanningFragment;
        this.f$1 = button;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        OnboardingScanningFragment.onViewCreated$lambda$0(this.f$0, this.f$1, compoundButton, z);
    }
}
