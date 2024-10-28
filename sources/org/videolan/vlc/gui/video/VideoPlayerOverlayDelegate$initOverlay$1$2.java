package org.videolan.vlc.gui.video;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.PlaybackServiceKt;
import org.videolan.vlc.databinding.PlayerHudBinding;
import org.videolan.vlc.media.ABRepeat;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "abvalues", "Lorg/videolan/vlc/media/ABRepeat;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOverlayDelegate.kt */
final class VideoPlayerOverlayDelegate$initOverlay$1$2 extends Lambda implements Function1<ABRepeat, Unit> {
    final /* synthetic */ PlaybackService $service;
    final /* synthetic */ VideoPlayerOverlayDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerOverlayDelegate$initOverlay$1$2(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, PlaybackService playbackService) {
        super(1);
        this.this$0 = videoPlayerOverlayDelegate;
        this.$service = playbackService;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ABRepeat) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ABRepeat aBRepeat) {
        PlayerHudBinding hudBinding = this.this$0.getHudBinding();
        long start = aBRepeat.getStart();
        Float valueOf = Float.valueOf(-1.0f);
        hudBinding.setAbRepeatA(start == -1 ? valueOf : Float.valueOf(((float) aBRepeat.getStart()) / ((float) this.$service.getPlaylistManager().getPlayer().getLength())));
        PlayerHudBinding hudBinding2 = this.this$0.getHudBinding();
        if (aBRepeat.getStop() != -1) {
            valueOf = Float.valueOf(((float) aBRepeat.getStop()) / ((float) this.$service.getPlaylistManager().getPlayer().getLength()));
        }
        hudBinding2.setAbRepeatB(valueOf);
        int i = 8;
        this.this$0.getHudBinding().abRepeatMarkerA.setVisibility(aBRepeat.getStart() == -1 ? 8 : 0);
        ImageView imageView = this.this$0.getHudBinding().abRepeatMarkerB;
        if (aBRepeat.getStop() != -1) {
            i = 0;
        }
        imageView.setVisibility(i);
        PlaybackService playbackService = this.$service;
        ImageView imageView2 = this.this$0.getHudBinding().abRepeatReset;
        Intrinsics.checkNotNullExpressionValue(imageView2, "abRepeatReset");
        View view = imageView2;
        ImageView imageView3 = this.this$0.getHudBinding().abRepeatStop;
        Intrinsics.checkNotNullExpressionValue(imageView3, "abRepeatStop");
        View view2 = imageView3;
        View view3 = this.this$0.getHudBinding().abRepeatContainer;
        Intrinsics.checkNotNullExpressionValue(view3, "abRepeatContainer");
        Button access$getAbRepeatAddMarker$p = this.this$0.abRepeatAddMarker;
        if (access$getAbRepeatAddMarker$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("abRepeatAddMarker");
            access$getAbRepeatAddMarker$p = null;
        }
        PlaybackServiceKt.manageAbRepeatStep(playbackService, view, view2, view3, access$getAbRepeatAddMarker$p);
        if (this.this$0.player.getSettings().getBoolean(SettingsKt.VIDEO_TRANSITION_SHOW, true)) {
            this.this$0.showOverlayTimeout((aBRepeat.getStart() == -1 || aBRepeat.getStop() == -1) ? -1 : Settings.INSTANCE.getVideoHudDelay() * 1000);
        }
    }
}
