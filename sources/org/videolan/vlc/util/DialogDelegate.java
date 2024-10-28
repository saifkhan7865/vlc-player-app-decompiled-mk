package org.videolan.vlc.util;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.gui.dialogs.VlcProgressDialog;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/util/DialogDelegate;", "Lorg/videolan/vlc/util/IDialogDelegate;", "()V", "observeDialogs", "", "lco", "Landroidx/lifecycle/LifecycleOwner;", "manager", "Lorg/videolan/vlc/util/IDialogManager;", "DialogsListener", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DialogDelegates.kt */
public final class DialogDelegate implements IDialogDelegate {
    public static final DialogsListener DialogsListener = new DialogsListener((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static int dialogCounter;
    /* access modifiers changed from: private */
    public static final LiveEvent<DialogEvt> dialogEvt = new LiveEvent<>();

    public void observeDialogs(LifecycleOwner lifecycleOwner, IDialogManager iDialogManager) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lco");
        Intrinsics.checkNotNullParameter(iDialogManager, "manager");
        dialogEvt.observe(lifecycleOwner, new DialogDelegatesKt$sam$androidx_lifecycle_Observer$0(new DialogDelegate$observeDialogs$1(iDialogManager)));
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0011H\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012H\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0013H\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/util/DialogDelegate$DialogsListener;", "Lorg/videolan/libvlc/Dialog$Callbacks;", "()V", "dialogCounter", "", "getDialogCounter", "()I", "setDialogCounter", "(I)V", "dialogEvt", "Lvideolan/org/commontools/LiveEvent;", "Lorg/videolan/vlc/util/DialogEvt;", "onCanceled", "", "dialog", "Lorg/videolan/libvlc/Dialog;", "onDisplay", "Lorg/videolan/libvlc/Dialog$ErrorMessage;", "Lorg/videolan/libvlc/Dialog$LoginDialog;", "Lorg/videolan/libvlc/Dialog$ProgressDialog;", "Lorg/videolan/libvlc/Dialog$QuestionDialog;", "onProgressUpdate", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DialogDelegates.kt */
    public static final class DialogsListener implements Dialog.Callbacks {
        public /* synthetic */ DialogsListener(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private DialogsListener() {
        }

        public final int getDialogCounter() {
            return DialogDelegate.dialogCounter;
        }

        public final void setDialogCounter(int i) {
            DialogDelegate.dialogCounter = i;
        }

        public void onProgressUpdate(Dialog.ProgressDialog progressDialog) {
            Intrinsics.checkNotNullParameter(progressDialog, "dialog");
            Object context = progressDialog.getContext();
            VlcProgressDialog vlcProgressDialog = context instanceof VlcProgressDialog ? (VlcProgressDialog) context : null;
            if (vlcProgressDialog != null && vlcProgressDialog.isVisible()) {
                vlcProgressDialog.updateProgress();
            }
        }

        public void onDisplay(Dialog.ErrorMessage errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "dialog");
            DialogDelegate.dialogEvt.setValue(new Cancel(errorMessage));
        }

        public void onDisplay(Dialog.LoginDialog loginDialog) {
            Intrinsics.checkNotNullParameter(loginDialog, "dialog");
            DialogDelegate.dialogEvt.setValue(new Show(loginDialog));
        }

        public void onDisplay(Dialog.QuestionDialog questionDialog) {
            Intrinsics.checkNotNullParameter(questionDialog, "dialog");
            DialogDelegate.dialogEvt.setValue(new Show(questionDialog));
        }

        public void onDisplay(Dialog.ProgressDialog progressDialog) {
            Intrinsics.checkNotNullParameter(progressDialog, "dialog");
            DialogDelegate.dialogEvt.setValue(new Show(progressDialog));
        }

        public void onCanceled(Dialog dialog) {
            DialogFragment dialogFragment = null;
            Object context = dialog != null ? dialog.getContext() : null;
            if (context instanceof DialogFragment) {
                dialogFragment = (DialogFragment) context;
            }
            if (dialogFragment != null) {
                dialogFragment.dismiss();
            }
            DialogDelegate.dialogEvt.setValue(new Cancel(dialog));
        }
    }
}
