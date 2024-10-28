package org.videolan.vlc;

import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.List;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$buildQueue$1", f = "PlaybackService.kt", i = {}, l = {1516}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$buildQueue$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $fromIndex;
    final /* synthetic */ List<MediaWrapper> $mediaList;
    final /* synthetic */ int $toIndex;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$buildQueue$1(PlaybackService playbackService, int i, int i2, List<? extends MediaWrapper> list, Continuation<? super PlaybackService$buildQueue$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
        this.$toIndex = i;
        this.$fromIndex = i2;
        this.$mediaList = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$buildQueue$1(this.this$0, this.$toIndex, this.$fromIndex, this.$mediaList, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$buildQueue$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.this$0.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                return Unit.INSTANCE;
            }
            PlaybackService playbackService = this.this$0;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getDefault(), new PlaybackService$buildQueue$1$queue$1(this.$toIndex, this.$fromIndex, this.$mediaList, playbackService, this.this$0, (Continuation<? super PlaybackService$buildQueue$1$queue$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.getMediaSession$vlc_android_release().setQueue((ArrayList) obj);
        return Unit.INSTANCE;
    }
}
