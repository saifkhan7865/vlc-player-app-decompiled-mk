package org.videolan.vlc.gui.audio;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.PlaybackServiceKt;
import org.videolan.vlc.databinding.AudioPlayerBinding;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$onViewCreated$5 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$onViewCreated$5(AudioPlayer audioPlayer) {
        super(1);
        this.this$0 = audioPlayer;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        AudioPlayerBinding access$getBinding$p = this.this$0.binding;
        AudioPlayerBinding audioPlayerBinding = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        ConstraintLayout constraintLayout = access$getBinding$p.abRepeatMarkerGuidelineContainer;
        Intrinsics.checkNotNull(bool);
        int i = 0;
        constraintLayout.setVisibility(bool.booleanValue() ? 0 : 8);
        this.this$0.getAbRepeatAddMarker().setVisibility(bool.booleanValue() ? 0 : 8);
        AudioPlayerBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p2 = null;
        }
        TextView textView = access$getBinding$p2.audioPlayProgress;
        if (this.this$0.shouldHidePlayProgress()) {
            i = 8;
        }
        textView.setVisibility(i);
        PlaybackService service = this.this$0.getPlaylistModel().getService();
        if (service != null) {
            AudioPlayerBinding access$getBinding$p3 = this.this$0.binding;
            if (access$getBinding$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p3 = null;
            }
            ImageView imageView = access$getBinding$p3.abRepeatReset;
            Intrinsics.checkNotNullExpressionValue(imageView, "abRepeatReset");
            View view = imageView;
            AudioPlayerBinding access$getBinding$p4 = this.this$0.binding;
            if (access$getBinding$p4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p4 = null;
            }
            ImageView imageView2 = access$getBinding$p4.abRepeatStop;
            Intrinsics.checkNotNullExpressionValue(imageView2, "abRepeatStop");
            View view2 = imageView2;
            AudioPlayerBinding access$getBinding$p5 = this.this$0.binding;
            if (access$getBinding$p5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                audioPlayerBinding = access$getBinding$p5;
            }
            View view3 = audioPlayerBinding.abRepeatContainer;
            Intrinsics.checkNotNullExpressionValue(view3, "abRepeatContainer");
            PlaybackServiceKt.manageAbRepeatStep(service, view, view2, view3, this.this$0.getAbRepeatAddMarker());
        }
    }
}
