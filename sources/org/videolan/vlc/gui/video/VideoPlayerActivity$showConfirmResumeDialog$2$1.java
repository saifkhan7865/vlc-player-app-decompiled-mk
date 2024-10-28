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
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.media.WaitConfirmation;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$showConfirmResumeDialog$2$1", f = "VideoPlayerActivity.kt", i = {}, l = {2340}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$showConfirmResumeDialog$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WaitConfirmation $confirmation;
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$showConfirmResumeDialog$2$1(VideoPlayerActivity videoPlayerActivity, WaitConfirmation waitConfirmation, Continuation<? super VideoPlayerActivity$showConfirmResumeDialog$2$1> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerActivity;
        this.$confirmation = waitConfirmation;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$showConfirmResumeDialog$2$1(this.this$0, this.$confirmation, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerActivity$showConfirmResumeDialog$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PlaylistManager playlistManager;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService service = this.this$0.getService();
            if (!(service == null || (playlistManager = service.getPlaylistManager()) == null)) {
                this.label = 1;
                if (PlaylistManager.playIndex$default(playlistManager, this.$confirmation.getIndex(), this.$confirmation.getFlags(), true, false, this, 8, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
