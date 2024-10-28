package org.videolan.vlc.gui.helpers.hf;

import androidx.fragment.app.FragmentActivity;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StoragePermissionsDelegate$Companion$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FragmentActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ StoragePermissionsDelegate$Companion$$ExternalSyntheticLambda0(FragmentActivity fragmentActivity, boolean z, boolean z2) {
        this.f$0 = fragmentActivity;
        this.f$1 = z;
        this.f$2 = z2;
    }

    public final void run() {
        StoragePermissionsDelegate.Companion.getAction$lambda$2(this.f$0, this.f$1, this.f$2);
    }
}
