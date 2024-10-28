package org.videolan.vlc.gui.video;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.PlaybackServiceKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOverlayDelegate.kt */
final class VideoPlayerOverlayDelegate$initOverlay$1$3 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ PlaybackService $service;
    final /* synthetic */ VideoPlayerOverlayDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerOverlayDelegate$initOverlay$1$3(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, PlaybackService playbackService) {
        super(1);
        this.this$0 = videoPlayerOverlayDelegate;
        this.$service = playbackService;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        Button access$getAbRepeatAddMarker$p = this.this$0.abRepeatAddMarker;
        Button button = null;
        if (access$getAbRepeatAddMarker$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("abRepeatAddMarker");
            access$getAbRepeatAddMarker$p = null;
        }
        Intrinsics.checkNotNull(bool);
        int i = 0;
        access$getAbRepeatAddMarker$p.setVisibility(bool.booleanValue() ? 0 : 8);
        ConstraintLayout constraintLayout = this.this$0.getHudBinding().abRepeatMarkerGuidelineContainer;
        if (!bool.booleanValue()) {
            i = 8;
        }
        constraintLayout.setVisibility(i);
        if (bool.booleanValue()) {
            this.this$0.showOverlay(true);
        }
        if (bool.booleanValue()) {
            this.this$0.getHudBinding().playerOverlayLength.setNextFocusUpId(R.id.ab_repeat_add_marker);
            this.this$0.getHudBinding().playerOverlayTime.setNextFocusUpId(R.id.ab_repeat_add_marker);
        }
        if (bool.booleanValue()) {
            this.this$0.showOverlayTimeout(-1);
        }
        PlaybackService playbackService = this.$service;
        ImageView imageView = this.this$0.getHudBinding().abRepeatReset;
        Intrinsics.checkNotNullExpressionValue(imageView, "abRepeatReset");
        View view = imageView;
        ImageView imageView2 = this.this$0.getHudBinding().abRepeatStop;
        Intrinsics.checkNotNullExpressionValue(imageView2, "abRepeatStop");
        View view2 = imageView2;
        View view3 = this.this$0.getHudBinding().abRepeatContainer;
        Intrinsics.checkNotNullExpressionValue(view3, "abRepeatContainer");
        Button access$getAbRepeatAddMarker$p2 = this.this$0.abRepeatAddMarker;
        if (access$getAbRepeatAddMarker$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("abRepeatAddMarker");
        } else {
            button = access$getAbRepeatAddMarker$p2;
        }
        PlaybackServiceKt.manageAbRepeatStep(playbackService, view, view2, view3, button);
    }
}
