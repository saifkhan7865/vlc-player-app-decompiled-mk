package org.videolan.vlc.gui.audio;

import android.view.View;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.PlaybackServiceKt;
import org.videolan.vlc.databinding.AudioPlayerBinding;
import org.videolan.vlc.media.ABRepeat;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "abvalues", "Lorg/videolan/vlc/media/ABRepeat;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$onViewCreated$4 extends Lambda implements Function1<ABRepeat, Unit> {
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$onViewCreated$4(AudioPlayer audioPlayer) {
        super(1);
        this.this$0 = audioPlayer;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ABRepeat) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ABRepeat aBRepeat) {
        Float f;
        Float f2;
        AudioPlayerBinding access$getBinding$p = this.this$0.binding;
        AudioPlayerBinding audioPlayerBinding = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        if (aBRepeat.getStart() == -1) {
            f = Float.valueOf(-1.0f);
        } else {
            PlaybackService service = this.this$0.getPlaylistModel().getService();
            Intrinsics.checkNotNull(service);
            f = Float.valueOf(((float) aBRepeat.getStart()) / ((float) service.getPlaylistManager().getPlayer().getLength()));
        }
        access$getBinding$p.setAbRepeatA(f);
        AudioPlayerBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p2 = null;
        }
        if (aBRepeat.getStop() == -1) {
            f2 = Float.valueOf(-1.0f);
        } else {
            PlaybackService service2 = this.this$0.getPlaylistModel().getService();
            Intrinsics.checkNotNull(service2);
            f2 = Float.valueOf(((float) aBRepeat.getStop()) / ((float) service2.getPlaylistManager().getPlayer().getLength()));
        }
        access$getBinding$p2.setAbRepeatB(f2);
        AudioPlayerBinding access$getBinding$p3 = this.this$0.binding;
        if (access$getBinding$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p3 = null;
        }
        int i = 8;
        access$getBinding$p3.abRepeatMarkerA.setVisibility(aBRepeat.getStart() == -1 ? 8 : 0);
        AudioPlayerBinding access$getBinding$p4 = this.this$0.binding;
        if (access$getBinding$p4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p4 = null;
        }
        ImageView imageView = access$getBinding$p4.abRepeatMarkerB;
        if (aBRepeat.getStop() != -1) {
            i = 0;
        }
        imageView.setVisibility(i);
        PlaybackService service3 = this.this$0.getPlaylistModel().getService();
        if (service3 != null) {
            AudioPlayerBinding access$getBinding$p5 = this.this$0.binding;
            if (access$getBinding$p5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p5 = null;
            }
            ImageView imageView2 = access$getBinding$p5.abRepeatReset;
            Intrinsics.checkNotNullExpressionValue(imageView2, "abRepeatReset");
            View view = imageView2;
            AudioPlayerBinding access$getBinding$p6 = this.this$0.binding;
            if (access$getBinding$p6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p6 = null;
            }
            ImageView imageView3 = access$getBinding$p6.abRepeatStop;
            Intrinsics.checkNotNullExpressionValue(imageView3, "abRepeatStop");
            View view2 = imageView3;
            AudioPlayerBinding access$getBinding$p7 = this.this$0.binding;
            if (access$getBinding$p7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                audioPlayerBinding = access$getBinding$p7;
            }
            View view3 = audioPlayerBinding.abRepeatContainer;
            Intrinsics.checkNotNullExpressionValue(view3, "abRepeatContainer");
            PlaybackServiceKt.manageAbRepeatStep(service3, view, view2, view3, this.this$0.getAbRepeatAddMarker());
        }
    }
}
