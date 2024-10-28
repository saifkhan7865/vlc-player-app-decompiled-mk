package org.videolan.vlc.car;

import android.content.SharedPreferences;
import androidx.car.app.model.Toggle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AutoControlScreen$$ExternalSyntheticLambda0 implements Toggle.OnCheckedChangeListener {
    public final /* synthetic */ SharedPreferences f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ AutoControlScreen$$ExternalSyntheticLambda0(SharedPreferences sharedPreferences, String str) {
        this.f$0 = sharedPreferences;
        this.f$1 = str;
    }

    public final void onCheckedChange(boolean z) {
        AutoControlScreen.buildToggleRow$lambda$3(this.f$0, this.f$1, z);
    }
}
