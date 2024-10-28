package org.videolan.vlc.gui.video;

import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$updateNavStatus$1", f = "VideoPlayerActivity.kt", i = {}, l = {2424}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$updateNavStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$updateNavStatus$1(VideoPlayerActivity videoPlayerActivity, Continuation<? super VideoPlayerActivity$updateNavStatus$1> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$updateNavStatus$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerActivity$updateNavStatus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        MediaPlayer mediaplayer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z2 = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new VideoPlayerActivity$updateNavStatus$1$titles$1(this.this$0, (Continuation<? super VideoPlayerActivity$updateNavStatus$1$titles$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MediaPlayer.Title[] titleArr = (MediaPlayer.Title[]) obj;
        if (this.this$0.isFinishing()) {
            return Unit.INSTANCE;
        }
        int i2 = 0;
        this.this$0.setNavMenu(false);
        if (titleArr != null) {
            PlaybackService service = this.this$0.getService();
            if (service == null) {
                return Unit.INSTANCE;
            }
            int titleIdx = service.getTitleIdx();
            int length = titleArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                } else if (titleArr[i3].isMenu()) {
                    this.this$0.setMenuIdx(i3);
                    break;
                } else {
                    i3++;
                }
            }
            PlaybackService service2 = this.this$0.getService();
            if (!(service2 == null || (mediaplayer = service2.getMediaplayer()) == null)) {
                try {
                    MediaPlayer.Title title = mediaplayer.getTitles()[mediaplayer.getTitle()];
                    if (title != null) {
                        z = title.isInteractive();
                        VideoPlayerActivity videoPlayerActivity = this.this$0;
                        if (videoPlayerActivity.getMenuIdx() != titleIdx && !z) {
                            z2 = false;
                        }
                        videoPlayerActivity.setNavMenu(z2);
                    }
                } catch (NullPointerException unused) {
                }
            }
            z = false;
            VideoPlayerActivity videoPlayerActivity2 = this.this$0;
            z2 = false;
            videoPlayerActivity2.setNavMenu(z2);
        }
        if (this.this$0.isNavMenu()) {
            VideoPlayerOverlayDelegate.hideOverlay$default(this.this$0.getOverlayDelegate(), false, false, 2, (Object) null);
        } else if (this.this$0.getMenuIdx() != -1) {
            this.this$0.setESTracks();
        }
        if (this.this$0.getOverlayDelegate().isHudRightBindingInitialized()) {
            ImageView imageView = this.this$0.getOverlayDelegate().getHudRightBinding().playerOverlayNavmenu;
            if (this.this$0.getMenuIdx() < 0) {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }
        this.this$0.supportInvalidateOptionsMenu();
        return Unit.INSTANCE;
    }
}
