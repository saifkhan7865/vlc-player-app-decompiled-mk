package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.dialogs.VideoTracksDialog;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$VideoTrackOption;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOverlayDelegate.kt */
final class VideoPlayerOverlayDelegate$showTracks$1 extends Lambda implements Function1<VideoTracksDialog.VideoTrackOption, Unit> {
    final /* synthetic */ VideoPlayerOverlayDelegate this$0;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlayerOverlayDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$VideoTrackOption[] r0 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.VideoTrackOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$VideoTrackOption r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.VideoTrackOption.AUDIO_DELAY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$VideoTrackOption r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.VideoTrackOption.SUB_DELAY     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$VideoTrackOption r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.VideoTrackOption.SUB_DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$VideoTrackOption r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.VideoTrackOption.SUB_PICK     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate$showTracks$1.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerOverlayDelegate$showTracks$1(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate) {
        super(1);
        this.this$0 = videoPlayerOverlayDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((VideoTracksDialog.VideoTrackOption) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(VideoTracksDialog.VideoTrackOption videoTrackOption) {
        Intrinsics.checkNotNullParameter(videoTrackOption, "it");
        int i = WhenMappings.$EnumSwitchMapping$0[videoTrackOption.ordinal()];
        if (i == 1) {
            this.this$0.player.getDelayDelegate().showAudioDelaySetting();
        } else if (i == 2) {
            this.this$0.player.getDelayDelegate().showSubsDelaySetting();
        } else if (i == 3) {
            Unit unused = this.this$0.downloadSubtitles();
        } else if (i == 4) {
            this.this$0.pickSubtitles();
        }
    }
}
