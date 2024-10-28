package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/gui/video/VideoAction;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$onCreate$3", f = "VideoGridFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$onCreate$3 extends SuspendLambda implements Function2<VideoAction, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$onCreate$3(VideoGridFragment videoGridFragment, Continuation<? super VideoGridFragment$onCreate$3> continuation) {
        super(2, continuation);
        this.this$0 = videoGridFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoGridFragment$onCreate$3 videoGridFragment$onCreate$3 = new VideoGridFragment$onCreate$3(this.this$0, continuation);
        videoGridFragment$onCreate$3.L$0 = obj;
        return videoGridFragment$onCreate$3;
    }

    public final Object invoke(VideoAction videoAction, Continuation<? super Unit> continuation) {
        return ((VideoGridFragment$onCreate$3) create(videoAction, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.process((VideoAction) this.L$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
