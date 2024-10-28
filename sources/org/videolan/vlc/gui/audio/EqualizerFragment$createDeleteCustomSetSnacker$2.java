package org.videolan.vlc.gui.audio;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.resources.VLCOptions;
import org.videolan.vlc.databinding.EqualizerBinding;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerFragment.kt */
final class EqualizerFragment$createDeleteCustomSetSnacker$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $oldName;
    final /* synthetic */ int $oldPos;
    final /* synthetic */ MediaPlayer.Equalizer $savedEqualizerSet;
    final /* synthetic */ EqualizerFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EqualizerFragment$createDeleteCustomSetSnacker$2(EqualizerFragment equalizerFragment, MediaPlayer.Equalizer equalizer, String str, int i) {
        super(0);
        this.this$0 = equalizerFragment;
        this.$savedEqualizerSet = equalizer;
        this.$oldName = str;
        this.$oldPos = i;
    }

    public final void invoke() {
        VLCOptions vLCOptions = VLCOptions.INSTANCE;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        vLCOptions.saveCustomSet(requireActivity, this.$savedEqualizerSet, this.$oldName);
        this.this$0.equalizer = this.$savedEqualizerSet;
        this.this$0.allSets.add(this.$oldPos, this.$oldName);
        EqualizerFragment equalizerFragment = this.this$0;
        equalizerFragment.customCount = equalizerFragment.customCount + 1;
        EqualizerBinding access$getBinding$p = this.this$0.binding;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        access$getBinding$p.equalizerPresets.setSelection(this.$oldPos);
    }
}
