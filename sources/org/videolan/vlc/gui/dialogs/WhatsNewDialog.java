package org.videolan.vlc.gui.dialogs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogWhatsNewBinding;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J$\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/WhatsNewDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogWhatsNewBinding;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WhatsNewDialog.kt */
public final class WhatsNewDialog extends VLCBottomSheetDialogFragment {
    private DialogWhatsNewBinding binding;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public View initialFocusedView() {
        DialogWhatsNewBinding dialogWhatsNewBinding = this.binding;
        if (dialogWhatsNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogWhatsNewBinding = null;
        }
        TextView textView = dialogWhatsNewBinding.title;
        Intrinsics.checkNotNullExpressionValue(textView, "title");
        return textView;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogWhatsNewBinding inflate = DialogWhatsNewBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogWhatsNewBinding dialogWhatsNewBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        inflate.title.setText(getString(R.string.whats_new_title, "3.6"));
        DialogWhatsNewBinding dialogWhatsNewBinding2 = this.binding;
        if (dialogWhatsNewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogWhatsNewBinding2 = null;
        }
        dialogWhatsNewBinding2.webserverMore.setOnClickListener(new WhatsNewDialog$$ExternalSyntheticLambda0(this));
        DialogWhatsNewBinding dialogWhatsNewBinding3 = this.binding;
        if (dialogWhatsNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogWhatsNewBinding3 = null;
        }
        dialogWhatsNewBinding3.neverAgain.setOnCheckedChangeListener(new WhatsNewDialog$$ExternalSyntheticLambda1(this));
        DialogWhatsNewBinding dialogWhatsNewBinding4 = this.binding;
        if (dialogWhatsNewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogWhatsNewBinding = dialogWhatsNewBinding4;
        }
        View root = dialogWhatsNewBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$0(WhatsNewDialog whatsNewDialog, View view) {
        Intrinsics.checkNotNullParameter(whatsNewDialog, "this$0");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(whatsNewDialog), (CoroutineContext) null, (CoroutineStart) null, new WhatsNewDialog$onCreateView$1$1(whatsNewDialog, (Continuation<? super WhatsNewDialog$onCreateView$1$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$1(WhatsNewDialog whatsNewDialog, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(whatsNewDialog, "this$0");
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = whatsNewDialog.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), SettingsKt.KEY_SHOW_WHATS_NEW, Boolean.valueOf(!z));
    }
}
