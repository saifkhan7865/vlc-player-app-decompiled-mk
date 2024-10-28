package org.videolan.vlc;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$updateMediaQueueSlidingWindow$1", f = "PlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$updateMediaQueueSlidingWindow$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $mediaListChanged;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$updateMediaQueueSlidingWindow$1(PlaybackService playbackService, boolean z, Continuation<? super PlaybackService$updateMediaQueueSlidingWindow$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
        this.$mediaListChanged = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$updateMediaQueueSlidingWindow$1(this.this$0, this.$mediaListChanged, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$updateMediaQueueSlidingWindow$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int i = 0;
            if (this.this$0.isCarMode()) {
                List<MediaWrapper> mediaList = this.this$0.getPlaylistManager().getMediaList();
                int currentMediaPosition = this.this$0.getCurrentMediaPosition();
                int i2 = currentMediaPosition + 1;
                int coerceAtMost = RangesKt.coerceAtMost(mediaList.size(), 15);
                if (i2 > 7) {
                    coerceAtMost = RangesKt.coerceAtMost(currentMediaPosition + 8, mediaList.size());
                    i = RangesKt.coerceAtLeast(coerceAtMost - 15, 0);
                }
                if (!mediaList.isEmpty()) {
                    Job unused = this.this$0.buildQueue(mediaList, i, coerceAtMost);
                }
                this.this$0.prevUpdateInCarMode = true;
            } else if (this.$mediaListChanged || this.this$0.prevUpdateInCarMode) {
                PlaybackService playbackService = this.this$0;
                PlaybackService.buildQueue$default(playbackService, playbackService.getPlaylistManager().getMediaList(), 0, 0, 6, (Object) null);
                this.this$0.prevUpdateInCarMode = false;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
