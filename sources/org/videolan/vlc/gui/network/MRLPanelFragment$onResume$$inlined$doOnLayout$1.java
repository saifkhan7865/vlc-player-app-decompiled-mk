package org.videolan.vlc.gui.network;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.databinding.MrlPanelBinding;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t*\u0001\u0000\b\n\u0018\u00002\u00020\u0001JP\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0016¨\u0006\u000f¸\u0006\u0010"}, d2 = {"androidx/core/view/ViewKt$doOnNextLayout$1", "Landroid/view/View$OnLayoutChangeListener;", "onLayoutChange", "", "view", "Landroid/view/View;", "left", "", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "core-ktx_release", "androidx/core/view/ViewKt$doOnLayout$$inlined$doOnNextLayout$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: View.kt */
public final class MRLPanelFragment$onResume$$inlined$doOnLayout$1 implements View.OnLayoutChangeListener {
    final /* synthetic */ MRLPanelFragment this$0;

    public MRLPanelFragment$onResume$$inlined$doOnLayout$1(MRLPanelFragment mRLPanelFragment) {
        this.this$0 = mRLPanelFragment;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        ClipData primaryClip;
        ClipData.Item itemAt;
        CharSequence text;
        view.removeOnLayoutChangeListener(this);
        try {
            Object systemService = this.this$0.requireContext().getSystemService("clipboard");
            MrlPanelBinding mrlPanelBinding = null;
            ClipboardManager clipboardManager = systemService instanceof ClipboardManager ? (ClipboardManager) systemService : null;
            String obj = (clipboardManager == null || (primaryClip = clipboardManager.getPrimaryClip()) == null || (itemAt = primaryClip.getItemAt(0)) == null || (text = itemAt.getText()) == null) ? null : text.toString();
            if (KotlinExtensionsKt.isValidUrl(obj)) {
                StreamsModel access$getViewModel$p = this.this$0.viewModel;
                if (access$getViewModel$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    access$getViewModel$p = null;
                }
                access$getViewModel$p.getObservableSearchText().set(obj);
                MrlPanelBinding access$getBinding$p = this.this$0.binding;
                if (access$getBinding$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    mrlPanelBinding = access$getBinding$p;
                }
                KotlinExtensionsKt.setVisible(mrlPanelBinding.clipboardIndicator);
            }
        } catch (Exception unused) {
        }
    }
}
