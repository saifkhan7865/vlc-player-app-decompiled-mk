package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogVideoControlsSettingsBinding;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J$\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VideoControlsSettingsDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogVideoControlsSettingsBinding;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoControlsSettingsDialog.kt */
public final class VideoControlsSettingsDialog extends VLCBottomSheetDialogFragment {
    private DialogVideoControlsSettingsBinding binding;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    public View initialFocusedView() {
        DialogVideoControlsSettingsBinding dialogVideoControlsSettingsBinding = this.binding;
        DialogVideoControlsSettingsBinding dialogVideoControlsSettingsBinding2 = null;
        if (dialogVideoControlsSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogVideoControlsSettingsBinding = null;
        }
        View findViewById = dialogVideoControlsSettingsBinding.fragmentContainerView.findViewById(R.id.recycler_view);
        if (findViewById != null) {
            return findViewById;
        }
        DialogVideoControlsSettingsBinding dialogVideoControlsSettingsBinding3 = this.binding;
        if (dialogVideoControlsSettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogVideoControlsSettingsBinding2 = dialogVideoControlsSettingsBinding3;
        }
        ConstraintLayout constraintLayout = dialogVideoControlsSettingsBinding2.container;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "container");
        return constraintLayout;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoControlsSettingsDialog$onCreate$1(this, (Continuation<? super VideoControlsSettingsDialog$onCreate$1>) null), 3, (Object) null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogVideoControlsSettingsBinding inflate = DialogVideoControlsSettingsBinding.inflate(getLayoutInflater(), viewGroup, false);
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

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ViewParent parent = view.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        BottomSheetBehavior.from((View) parent).setSkipCollapsed(true);
    }
}
