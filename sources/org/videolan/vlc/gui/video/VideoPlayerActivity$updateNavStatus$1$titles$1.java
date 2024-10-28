package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/libvlc/MediaPlayer$Title;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$updateNavStatus$1$titles$1", f = "VideoPlayerActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$updateNavStatus$1$titles$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaPlayer.Title[]>, Object> {
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$updateNavStatus$1$titles$1(VideoPlayerActivity videoPlayerActivity, Continuation<? super VideoPlayerActivity$updateNavStatus$1$titles$1> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$updateNavStatus$1$titles$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaPlayer.Title[]> continuation) {
        return ((VideoPlayerActivity$updateNavStatus$1$titles$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService service = this.this$0.getService();
            if (service != null) {
                return service.getTitles();
            }
            return null;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
