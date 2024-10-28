package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$onMediaPlayerEvent$1$3", f = "VideoPlayerActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$onMediaPlayerEvent$1$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PlaybackService $service;
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$onMediaPlayerEvent$1$3(PlaybackService playbackService, VideoPlayerActivity videoPlayerActivity, Continuation<? super VideoPlayerActivity$onMediaPlayerEvent$1$3> continuation) {
        super(2, continuation);
        this.$service = playbackService;
        this.this$0 = videoPlayerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$onMediaPlayerEvent$1$3(this.$service, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerActivity$onMediaPlayerEvent$1$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper currentMediaWrapper = this.$service.getCurrentMediaWrapper();
            if (currentMediaWrapper != null) {
                VideoPlayerActivity videoPlayerActivity = this.this$0;
                PlaybackService playbackService = this.$service;
                MediaWrapper findMedia = videoPlayerActivity.getMedialibrary().findMedia(currentMediaWrapper);
                String metaString = findMedia.getMetaString(100);
                if (metaString == null) {
                    metaString = Constants.GROUP_VIDEOS_FOLDER;
                } else {
                    Intrinsics.checkNotNull(metaString);
                }
                if (!Intrinsics.areEqual((Object) metaString, (Object) Constants.GROUP_VIDEOS_FOLDER) && findMedia.getId() != 0) {
                    playbackService.setVideoTrack(metaString);
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
