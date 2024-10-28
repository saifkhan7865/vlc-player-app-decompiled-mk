package org.videolan.vlc.gui.dialogs;

import android.widget.Button;
import kotlin.Metadata;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.VlcProgressDialogBinding;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0005\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VlcProgressDialog;", "Lorg/videolan/vlc/gui/dialogs/VlcDialog;", "Lorg/videolan/libvlc/Dialog$ProgressDialog;", "Lorg/videolan/vlc/databinding/VlcProgressDialogBinding;", "()V", "layout", "", "getLayout", "()I", "updateProgress", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VlcProgressDialog.kt */
public final class VlcProgressDialog extends VlcDialog<Dialog.ProgressDialog, VlcProgressDialogBinding> {
    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.vlc_progress_dialog;
    }

    public final void updateProgress() {
        ((VlcProgressDialogBinding) getBinding()).progress.setProgress((int) (((Dialog.ProgressDialog) getVlcDialog()).getPosition() * 100.0f));
        ((VlcProgressDialogBinding) getBinding()).cancel.setText(((Dialog.ProgressDialog) getVlcDialog()).getCancelText());
        Button button = ((VlcProgressDialogBinding) getBinding()).cancel;
        CharSequence cancelText = ((Dialog.ProgressDialog) getVlcDialog()).getCancelText();
        button.setVisibility((cancelText == null || cancelText.length() == 0) ? 8 : 0);
    }
}
