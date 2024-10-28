package org.videolan.vlc.gui.browser;

import android.content.DialogInterface;
import androidx.appcompat.widget.AppCompatEditText;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageBrowserFragment$$ExternalSyntheticLambda1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ AppCompatEditText f$0;
    public final /* synthetic */ StorageBrowserFragment f$1;

    public /* synthetic */ StorageBrowserFragment$$ExternalSyntheticLambda1(AppCompatEditText appCompatEditText, StorageBrowserFragment storageBrowserFragment) {
        this.f$0 = appCompatEditText;
        this.f$1 = storageBrowserFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        StorageBrowserFragment.showAddDirectoryDialog$lambda$6(this.f$0, this.f$1, dialogInterface, i);
    }
}
