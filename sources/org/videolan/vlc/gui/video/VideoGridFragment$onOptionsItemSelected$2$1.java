package org.videolan.vlc.gui.video;

import androidx.fragment.app.FragmentActivity;
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
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$onOptionsItemSelected$2$1", f = "VideoGridFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$onOptionsItemSelected$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoGroup $it;
    int label;
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$onOptionsItemSelected$2$1(VideoGridFragment videoGridFragment, VideoGroup videoGroup, Continuation<? super VideoGridFragment$onOptionsItemSelected$2$1> continuation) {
        super(2, continuation);
        this.this$0 = videoGridFragment;
        this.$it = videoGroup;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoGridFragment$onOptionsItemSelected$2$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoGridFragment$onOptionsItemSelected$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            if (uiTools.showPinIfNeeded(requireActivity)) {
                ((VideosViewModel) this.this$0.getViewModel()).ungroup(this.$it);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
