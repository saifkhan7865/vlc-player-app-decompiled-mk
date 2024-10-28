package org.videolan.vlc.gui.browser;

import android.content.DialogInterface;
import androidx.appcompat.widget.AppCompatEditText;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MLStorageBrowserFragment$$ExternalSyntheticLambda1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ AppCompatEditText f$0;
    public final /* synthetic */ MLStorageBrowserFragment f$1;

    public /* synthetic */ MLStorageBrowserFragment$$ExternalSyntheticLambda1(AppCompatEditText appCompatEditText, MLStorageBrowserFragment mLStorageBrowserFragment) {
        this.f$0 = appCompatEditText;
        this.f$1 = mLStorageBrowserFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        MLStorageBrowserFragment.showAddDirectoryDialog$lambda$3(this.f$0, this.f$1, dialogInterface, i);
    }
}
