package org.videolan.vlc.gui.audio;

import android.animation.ValueAnimator;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.databinding.PlaylistItemBinding;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/ValueAnimator;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlaylistTipsDelegate.kt */
final class AudioPlaylistTipsDelegate$next$1 extends Lambda implements Function1<ValueAnimator, Unit> {
    final /* synthetic */ AudioPlaylistTipsDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlaylistTipsDelegate$next$1(AudioPlaylistTipsDelegate audioPlaylistTipsDelegate) {
        super(1);
        this.this$0 = audioPlaylistTipsDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ValueAnimator) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "it");
        PlaylistItemBinding access$getSecondItemBinding$p = this.this$0.secondItemBinding;
        if (access$getSecondItemBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondItemBinding");
            access$getSecondItemBinding$p = null;
        }
        ConstraintLayout constraintLayout = access$getSecondItemBinding$p.itemContainer;
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        constraintLayout.setTranslationX(((Float) animatedValue).floatValue() * ((float) 4));
    }
}
