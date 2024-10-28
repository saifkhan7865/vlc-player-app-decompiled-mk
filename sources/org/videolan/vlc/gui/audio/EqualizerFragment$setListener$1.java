package org.videolan.vlc.gui.audio;

import android.view.View;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.videolan.vlc.databinding.EqualizerBinding;

@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¨\u0006\r"}, d2 = {"org/videolan/vlc/gui/audio/EqualizerFragment$setListener$1", "Landroid/widget/AdapterView$OnItemSelectedListener;", "onItemSelected", "", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "pos", "", "id", "", "onNothingSelected", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerFragment.kt */
public final class EqualizerFragment$setListener$1 implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ EqualizerFragment this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
        Intrinsics.checkNotNullParameter(adapterView, "parent");
    }

    EqualizerFragment$setListener$1(EqualizerFragment equalizerFragment) {
        this.this$0 = equalizerFragment;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        Intrinsics.checkNotNullParameter(adapterView, "parent");
        Intrinsics.checkNotNullParameter(view, "view");
        EqualizerBinding access$getBinding$p = this.this$0.binding;
        EqualizerBinding equalizerBinding = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        if (!access$getBinding$p.equalizerButton.isChecked() && !this.this$0.updateAlreadyHandled) {
            EqualizerBinding access$getBinding$p2 = this.this$0.binding;
            if (access$getBinding$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                equalizerBinding = access$getBinding$p2;
            }
            equalizerBinding.equalizerButton.setChecked(true);
        }
        if (this.this$0.savePos >= 0 && !this.this$0.state.getSaved$vlc_android_release() && !this.this$0.updateAlreadyHandled) {
            EqualizerFragment equalizerFragment = this.this$0;
            equalizerFragment.createSaveCustomSetDialog(equalizerFragment.savePos, false, false);
        }
        Job unused = this.this$0.updateEqualizer(i);
    }
}
