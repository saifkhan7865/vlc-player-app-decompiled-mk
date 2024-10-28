package org.videolan.vlc.gui.dialogs;

import android.content.DialogInterface;
import androidx.fragment.app.DialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/DismissDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "onDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "getOnDismissListener", "()Landroid/content/DialogInterface$OnDismissListener;", "setOnDismissListener", "(Landroid/content/DialogInterface$OnDismissListener;)V", "onDismiss", "", "dialog", "Landroid/content/DialogInterface;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DismissDialogFragment.kt */
public final class DismissDialogFragment extends DialogFragment {
    private DialogInterface.OnDismissListener onDismissListener;

    public final DialogInterface.OnDismissListener getOnDismissListener() {
        return this.onDismissListener;
    }

    public final void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener2 = this.onDismissListener;
        if (onDismissListener2 != null) {
            Intrinsics.checkNotNull(onDismissListener2);
            onDismissListener2.onDismiss(dialogInterface);
        }
    }
}
