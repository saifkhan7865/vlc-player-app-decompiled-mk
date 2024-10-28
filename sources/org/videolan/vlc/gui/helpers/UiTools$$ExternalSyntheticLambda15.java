package org.videolan.vlc.gui.helpers;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UiTools$$ExternalSyntheticLambda15 implements Runnable {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ InputMethodManager f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ UiTools$$ExternalSyntheticLambda15(boolean z, InputMethodManager inputMethodManager, View view) {
        this.f$0 = z;
        this.f$1 = inputMethodManager;
        this.f$2 = view;
    }

    public final void run() {
        UiTools.setKeyboardVisibility$lambda$17(this.f$0, this.f$1, this.f$2);
    }
}
