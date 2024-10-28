package org.videolan.vlc.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.gui.dialogs.VlcDialog;
import org.videolan.vlc.gui.dialogs.VlcLoginDialog;
import org.videolan.vlc.gui.dialogs.VlcProgressDialog;
import org.videolan.vlc.gui.dialogs.VlcQuestionDialog;
import org.videolan.vlc.util.DialogDelegate;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"TAG", "", "showVlcDialog", "", "Landroidx/fragment/app/Fragment;", "dialog", "Lorg/videolan/libvlc/Dialog;", "Landroidx/fragment/app/FragmentActivity;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DialogDelegates.kt */
public final class DialogDelegatesKt {
    private static final String TAG = "DialogDelegate";

    public static final void showVlcDialog(Fragment fragment, Dialog dialog) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            showVlcDialog(activity, dialog);
        }
    }

    public static final void showVlcDialog(FragmentActivity fragmentActivity, Dialog dialog) {
        VlcDialog vlcDialog;
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (dialog instanceof Dialog.LoginDialog) {
            VlcLoginDialog vlcLoginDialog = new VlcLoginDialog();
            vlcLoginDialog.setVlcDialog(dialog);
            vlcDialog = vlcLoginDialog;
        } else if (dialog instanceof Dialog.QuestionDialog) {
            VlcQuestionDialog vlcQuestionDialog = new VlcQuestionDialog();
            vlcQuestionDialog.setVlcDialog(dialog);
            vlcDialog = vlcQuestionDialog;
        } else if (dialog instanceof Dialog.ProgressDialog) {
            VlcProgressDialog vlcProgressDialog = new VlcProgressDialog();
            vlcProgressDialog.setVlcDialog(dialog);
            vlcDialog = vlcProgressDialog;
        } else {
            vlcDialog = null;
        }
        if (vlcDialog != null) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
            StringBuilder sb = new StringBuilder("vlc_dialog_");
            DialogDelegate.DialogsListener dialogsListener = DialogDelegate.DialogsListener;
            dialogsListener.setDialogCounter(dialogsListener.getDialogCounter() + 1);
            sb.append(dialogsListener.getDialogCounter());
            vlcDialog.show(supportFragmentManager, sb.toString());
        }
    }
}
