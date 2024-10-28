package androidx.car.app.navigation.model;

import androidx.car.app.model.Toggle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NavigationTemplate$Builder$$ExternalSyntheticLambda0 implements Toggle.OnCheckedChangeListener {
    public final /* synthetic */ PanModeListener f$0;

    public /* synthetic */ NavigationTemplate$Builder$$ExternalSyntheticLambda0(PanModeListener panModeListener) {
        this.f$0 = panModeListener;
    }

    public final void onCheckedChange(boolean z) {
        this.f$0.onPanModeChanged(z);
    }
}
