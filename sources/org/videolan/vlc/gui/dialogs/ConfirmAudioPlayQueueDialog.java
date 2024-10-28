package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0014\u0010\u0018\u001a\u00020\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/ConfirmAudioPlayQueueDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "acceptButton", "Landroid/widget/Button;", "cancelButton", "listener", "Lkotlin/Function0;", "", "title", "Landroid/widget/TextView;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConfirmAudioPlayQueueDialog.kt */
public final class ConfirmAudioPlayQueueDialog extends VLCBottomSheetDialogFragment {
    private Button acceptButton;
    private Button cancelButton;
    private Function0<Unit> listener;
    private TextView title;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    public final void setListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.listener = function0;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_confirm_audio_playqueue, viewGroup);
        View findViewById = inflate.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.title = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.accept_button);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.acceptButton = (Button) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.cancel_button);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.cancelButton = (Button) findViewById3;
        Button button = this.acceptButton;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("acceptButton");
            button = null;
        }
        button.setOnClickListener(new ConfirmAudioPlayQueueDialog$$ExternalSyntheticLambda0(this));
        Button button3 = this.cancelButton;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
        } else {
            button2 = button3;
        }
        button2.setOnClickListener(new ConfirmAudioPlayQueueDialog$$ExternalSyntheticLambda1(this));
        return inflate;
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$0(ConfirmAudioPlayQueueDialog confirmAudioPlayQueueDialog, View view) {
        Intrinsics.checkNotNullParameter(confirmAudioPlayQueueDialog, "this$0");
        Function0<Unit> function0 = confirmAudioPlayQueueDialog.listener;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listener");
            function0 = null;
        }
        function0.invoke();
        confirmAudioPlayQueueDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$1(ConfirmAudioPlayQueueDialog confirmAudioPlayQueueDialog, View view) {
        Intrinsics.checkNotNullParameter(confirmAudioPlayQueueDialog, "this$0");
        confirmAudioPlayQueueDialog.dismiss();
    }

    public View initialFocusedView() {
        TextView textView = this.title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView = null;
        }
        return textView;
    }
}
