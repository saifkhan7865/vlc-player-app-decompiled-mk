package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/PlaybackService;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.VideoTracksDialog$onViewCreated$7", f = "VideoTracksDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoTracksDialog.kt */
final class VideoTracksDialog$onViewCreated$7 extends SuspendLambda implements Function2<PlaybackService, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoTracksDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoTracksDialog$onViewCreated$7(VideoTracksDialog videoTracksDialog, Continuation<? super VideoTracksDialog$onViewCreated$7> continuation) {
        super(2, continuation);
        this.this$0 = videoTracksDialog;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoTracksDialog$onViewCreated$7 videoTracksDialog$onViewCreated$7 = new VideoTracksDialog$onViewCreated$7(this.this$0, continuation);
        videoTracksDialog$onViewCreated$7.L$0 = obj;
        return videoTracksDialog$onViewCreated$7;
    }

    public final Object invoke(PlaybackService playbackService, Continuation<? super Unit> continuation) {
        return ((VideoTracksDialog$onViewCreated$7) create(playbackService, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.onServiceChanged((PlaybackService) this.L$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
