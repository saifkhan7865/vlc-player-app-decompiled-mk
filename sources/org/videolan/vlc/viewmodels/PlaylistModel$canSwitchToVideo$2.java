package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.media.PlayerController;
import org.videolan.vlc.media.PlaylistManager;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.PlaylistModel$canSwitchToVideo$2", f = "PlaylistModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistModel.kt */
final class PlaylistModel$canSwitchToVideo$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;
    final /* synthetic */ PlaylistModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistModel$canSwitchToVideo$2(PlaylistModel playlistModel, Continuation<? super PlaylistModel$canSwitchToVideo$2> continuation) {
        super(2, continuation);
        this.this$0 = playlistModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistModel$canSwitchToVideo$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((PlaylistModel$canSwitchToVideo$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PlaylistManager playlistManager;
        PlayerController player;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService service = this.this$0.getService();
            boolean z = false;
            if (!(service == null || (playlistManager = service.getPlaylistManager()) == null || (player = playlistManager.getPlayer()) == null || !player.canSwitchToVideo())) {
                z = true;
            }
            return Boxing.boxBoolean(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
