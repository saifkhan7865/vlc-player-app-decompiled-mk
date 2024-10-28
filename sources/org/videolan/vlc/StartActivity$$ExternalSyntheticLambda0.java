package org.videolan.vlc;

import android.content.SharedPreferences;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StartActivity$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ StartActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ SharedPreferences f$5;

    public /* synthetic */ StartActivity$$ExternalSyntheticLambda0(StartActivity startActivity, boolean z, boolean z2, boolean z3, boolean z4, SharedPreferences sharedPreferences) {
        this.f$0 = startActivity;
        this.f$1 = z;
        this.f$2 = z2;
        this.f$3 = z3;
        this.f$4 = z4;
        this.f$5 = sharedPreferences;
    }

    public final void run() {
        StartActivity.startApplication$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
