package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.tools.WorkersKt;
import org.videolan.vlc.MainVersionKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.gui.dialogs.VideoTracksDialog;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "trackID", "", "trackType", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$TrackType;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOverlayDelegate.kt */
final class VideoPlayerOverlayDelegate$showTracks$2 extends Lambda implements Function2<String, VideoTracksDialog.TrackType, Unit> {
    final /* synthetic */ VideoPlayerOverlayDelegate this$0;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlayerOverlayDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType[] r0 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.AUDIO     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.SPU     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.VIDEO     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate$showTracks$2.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerOverlayDelegate$showTracks$2(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate) {
        super(2);
        this.this$0 = videoPlayerOverlayDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (VideoTracksDialog.TrackType) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(String str, VideoTracksDialog.TrackType trackType) {
        PlaybackService service;
        Intrinsics.checkNotNullParameter(str, "trackID");
        Intrinsics.checkNotNullParameter(trackType, "trackType");
        int i = WhenMappings.$EnumSwitchMapping$0[trackType.ordinal()];
        if (i == 1) {
            PlaybackService service2 = this.this$0.player.getService();
            if (service2 != null) {
                VideoPlayerOverlayDelegate videoPlayerOverlayDelegate = this.this$0;
                if (!MainVersionKt.isVLC4() || !Intrinsics.areEqual((Object) str, (Object) Constants.GROUP_VIDEOS_NONE)) {
                    service2.setAudioTrack(str);
                } else {
                    service2.unselectTrackType(trackType);
                }
                WorkersKt.runIO(new VideoPlayerOverlayDelegate$showTracks$2$$ExternalSyntheticLambda0(videoPlayerOverlayDelegate, service2, str));
            }
        } else if (i == 2) {
            PlaybackService service3 = this.this$0.player.getService();
            if (service3 != null) {
                VideoPlayerOverlayDelegate videoPlayerOverlayDelegate2 = this.this$0;
                if (!MainVersionKt.isVLC4() || !Intrinsics.areEqual((Object) str, (Object) Constants.GROUP_VIDEOS_NONE)) {
                    service3.setSpuTrack(str);
                } else {
                    service3.unselectTrackType(trackType);
                }
                WorkersKt.runIO(new VideoPlayerOverlayDelegate$showTracks$2$$ExternalSyntheticLambda1(videoPlayerOverlayDelegate2, service3, str));
            }
        } else if (i == 3 && (service = this.this$0.player.getService()) != null) {
            VideoPlayerOverlayDelegate videoPlayerOverlayDelegate3 = this.this$0;
            VideoPlayerActivity.seek$default(videoPlayerOverlayDelegate3.player, service.getTime(), false, 2, (Object) null);
            if (!MainVersionKt.isVLC4() || !Intrinsics.areEqual((Object) str, (Object) Constants.GROUP_VIDEOS_NONE)) {
                service.setVideoTrack(str);
            } else {
                service.unselectTrackType(trackType);
            }
            WorkersKt.runIO(new VideoPlayerOverlayDelegate$showTracks$2$$ExternalSyntheticLambda2(videoPlayerOverlayDelegate3, service, str));
        }
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, PlaybackService playbackService, String str) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        Intrinsics.checkNotNullParameter(playbackService, "$service");
        Intrinsics.checkNotNullParameter(str, "$trackID");
        MediaWrapper findMedia = videoPlayerOverlayDelegate.player.getMedialibrary().findMedia(playbackService.getCurrentMediaWrapper());
        if (findMedia != null && findMedia.getId() != 0) {
            findMedia.setStringMeta(150, str);
        }
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$3$lambda$2(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, PlaybackService playbackService, String str) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        Intrinsics.checkNotNullParameter(playbackService, "$service");
        Intrinsics.checkNotNullParameter(str, "$trackID");
        MediaWrapper findMedia = videoPlayerOverlayDelegate.player.getMedialibrary().findMedia(playbackService.getCurrentMediaWrapper());
        if (findMedia != null && findMedia.getId() != 0) {
            findMedia.setStringMeta(200, str);
        }
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$5$lambda$4(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, PlaybackService playbackService, String str) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        Intrinsics.checkNotNullParameter(playbackService, "$service");
        Intrinsics.checkNotNullParameter(str, "$trackID");
        MediaWrapper findMedia = videoPlayerOverlayDelegate.player.getMedialibrary().findMedia(playbackService.getCurrentMediaWrapper());
        if (findMedia != null && findMedia.getId() != 0) {
            findMedia.setStringMeta(100, str);
        }
    }
}
