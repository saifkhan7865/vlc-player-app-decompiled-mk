package org.videolan.vlc.media;

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
import org.videolan.libvlc.interfaces.IMedia;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlayerController$startPlayback$2", f = "PlayerController.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlayerController.kt */
final class PlayerController$startPlayback$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ IMedia $media;
    int label;
    final /* synthetic */ PlayerController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerController$startPlayback$2(PlayerController playerController, IMedia iMedia, Continuation<? super PlayerController$startPlayback$2> continuation) {
        super(2, continuation);
        this.this$0 = playerController;
        this.$media = iMedia;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerController$startPlayback$2(this.this$0, this.$media, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerController$startPlayback$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.this$0.getMediaplayer().isReleased()) {
                MediaPlayer mediaplayer = this.this$0.getMediaplayer();
                IMedia iMedia = this.$media;
                if (this.this$0.getHasRenderer()) {
                    iMedia.parse();
                }
                mediaplayer.setMedia(iMedia);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
