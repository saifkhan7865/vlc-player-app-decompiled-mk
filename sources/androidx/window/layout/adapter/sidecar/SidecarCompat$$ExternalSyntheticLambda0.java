package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import android.content.res.Configuration;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SidecarCompat$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ SidecarCompat f$0;
    public final /* synthetic */ Activity f$1;

    public /* synthetic */ SidecarCompat$$ExternalSyntheticLambda0(SidecarCompat sidecarCompat, Activity activity) {
        this.f$0 = sidecarCompat;
        this.f$1 = activity;
    }

    public final void accept(Object obj) {
        SidecarCompat.registerConfigurationChangeListener$lambda$0(this.f$0, this.f$1, (Configuration) obj);
    }
}
