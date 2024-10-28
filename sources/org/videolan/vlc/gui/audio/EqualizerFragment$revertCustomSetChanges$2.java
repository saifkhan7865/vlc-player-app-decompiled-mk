package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.vlc.databinding.EqualizerBinding;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerFragment.kt */
final class EqualizerFragment$revertCustomSetChanges$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ int $pos;
    final /* synthetic */ MediaPlayer.Equalizer $temporarySet;
    final /* synthetic */ EqualizerFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EqualizerFragment$revertCustomSetChanges$2(EqualizerFragment equalizerFragment, int i, MediaPlayer.Equalizer equalizer) {
        super(0);
        this.this$0 = equalizerFragment;
        this.$pos = i;
        this.$temporarySet = equalizer;
    }

    public final void invoke() {
        this.this$0.state.update(this.$pos, false);
        EqualizerFragment equalizerFragment = this.this$0;
        MediaPlayer.Equalizer equalizer = this.$temporarySet;
        Intrinsics.checkNotNullExpressionValue(equalizer, "$temporarySet");
        equalizerFragment.equalizer = equalizer;
        this.this$0.updateAlreadyHandled = true;
        if (this.$pos == this.this$0.revertPos) {
            Job unused = this.this$0.updateEqualizer(this.$pos);
            return;
        }
        EqualizerBinding access$getBinding$p = this.this$0.binding;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        access$getBinding$p.equalizerPresets.setSelection(this.$pos);
    }
}
