package org.videolan.vlc.gui.browser;

import android.widget.CheckBox;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageFragmentDelegate$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ CheckBox f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ StorageFragmentDelegate f$2;

    public /* synthetic */ StorageFragmentDelegate$$ExternalSyntheticLambda3(CheckBox checkBox, boolean z, StorageFragmentDelegate storageFragmentDelegate) {
        this.f$0 = checkBox;
        this.f$1 = z;
        this.f$2 = storageFragmentDelegate;
    }

    public final void run() {
        StorageFragmentDelegate.onRootRemoved$lambda$4$lambda$3(this.f$0, this.f$1, this.f$2);
    }
}
