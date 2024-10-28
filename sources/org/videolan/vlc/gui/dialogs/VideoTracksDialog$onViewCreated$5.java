package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.databinding.PlayerOverlayTracksBinding;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTracksDialog.kt */
final class VideoTracksDialog$onViewCreated$5 extends Lambda implements Function0<Unit> {
    final /* synthetic */ VideoTracksDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoTracksDialog$onViewCreated$5(VideoTracksDialog videoTracksDialog) {
        super(0);
        this.this$0 = videoTracksDialog;
    }

    public final void invoke() {
        PlayerOverlayTracksBinding access$getBinding$p = this.this$0.binding;
        PlayerOverlayTracksBinding playerOverlayTracksBinding = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        KotlinExtensionsKt.setGone(access$getBinding$p.audioTracks.trackMore);
        PlayerOverlayTracksBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            playerOverlayTracksBinding = access$getBinding$p2;
        }
        playerOverlayTracksBinding.audioTracks.options.lock();
    }
}
