package org.videolan.vlc.gui.dialogs;

import android.view.KeyEvent;
import android.widget.TextView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RenameDialog$$ExternalSyntheticLambda1 implements TextView.OnEditorActionListener {
    public final /* synthetic */ RenameDialog f$0;

    public /* synthetic */ RenameDialog$$ExternalSyntheticLambda1(RenameDialog renameDialog) {
        this.f$0 = renameDialog;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return RenameDialog.onCreateView$lambda$2(this.f$0, textView, i, keyEvent);
    }
}
