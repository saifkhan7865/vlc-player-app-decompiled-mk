package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogAudioControlsSettingsBinding;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J$\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/AudioControlsSettingsDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogAudioControlsSettingsBinding;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioControlsSettingsDialog.kt */
public final class AudioControlsSettingsDialog extends VLCBottomSheetDialogFragment {
    private DialogAudioControlsSettingsBinding binding;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    public View initialFocusedView() {
        DialogAudioControlsSettingsBinding dialogAudioControlsSettingsBinding = this.binding;
        DialogAudioControlsSettingsBinding dialogAudioControlsSettingsBinding2 = null;
        if (dialogAudioControlsSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioControlsSettingsBinding = null;
        }
        View findViewById = dialogAudioControlsSettingsBinding.fragmentContainerView.findViewById(R.id.recycler_view);
        if (findViewById != null) {
            return findViewById;
        }
        DialogAudioControlsSettingsBinding dialogAudioControlsSettingsBinding3 = this.binding;
        if (dialogAudioControlsSettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogAudioControlsSettingsBinding2 = dialogAudioControlsSettingsBinding3;
        }
        ConstraintLayout constraintLayout = dialogAudioControlsSettingsBinding2.container;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "container");
        return constraintLayout;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new AudioControlsSettingsDialog$onCreate$1(this, (Continuation<? super AudioControlsSettingsDialog$onCreate$1>) null), 3, (Object) null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogAudioControlsSettingsBinding inflate = DialogAudioControlsSettingsBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
}
