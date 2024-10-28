package org.videolan.vlc.gui.dialogs;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.VlcQuestionDialogBinding;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0005\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VlcQuestionDialog;", "Lorg/videolan/vlc/gui/dialogs/VlcDialog;", "Lorg/videolan/libvlc/Dialog$QuestionDialog;", "Lorg/videolan/vlc/databinding/VlcQuestionDialogBinding;", "()V", "layout", "", "getLayout", "()I", "onAction1", "", "v", "Landroid/view/View;", "onAction2", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VlcQuestionDialog.kt */
public final class VlcQuestionDialog extends VlcDialog<Dialog.QuestionDialog, VlcQuestionDialogBinding> {
    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.vlc_question_dialog;
    }

    public final void onAction1(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        ((Dialog.QuestionDialog) getVlcDialog()).postAction(1);
        dismiss();
    }

    public final void onAction2(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        ((Dialog.QuestionDialog) getVlcDialog()).postAction(2);
        dismiss();
    }
}
