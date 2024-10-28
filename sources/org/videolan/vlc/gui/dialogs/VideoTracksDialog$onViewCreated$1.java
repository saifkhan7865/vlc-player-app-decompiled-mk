package org.videolan.vlc.gui.dialogs;

import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.databinding.PlayerOverlayTracksBinding;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTracksDialog.kt */
final class VideoTracksDialog$onViewCreated$1 extends Lambda implements Function1<Float, Unit> {
    final /* synthetic */ VideoTracksDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoTracksDialog$onViewCreated$1(VideoTracksDialog videoTracksDialog) {
        super(1);
        this.this$0 = videoTracksDialog;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).floatValue());
        return Unit.INSTANCE;
    }

    public final void invoke(float f) {
        PlayerOverlayTracksBinding access$getBinding$p = this.this$0.binding;
        PlayerOverlayTracksBinding playerOverlayTracksBinding = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        ImageView imageView = access$getBinding$p.audioTracks.trackMore;
        PlayerOverlayTracksBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            playerOverlayTracksBinding = access$getBinding$p2;
        }
        imageView.setRotation(playerOverlayTracksBinding.audioTracks.options.isCollapsed() ? 180.0f - (f * 180.0f) : 180.0f * f);
    }
}
