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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlayerController$release$1", f = "PlayerController.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlayerController.kt */
final class PlayerController$release$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaPlayer $player;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerController$release$1(MediaPlayer mediaPlayer, PlayerController playerController, Continuation<? super PlayerController$release$1> continuation) {
        super(2, continuation);
        this.$player = mediaPlayer;
        this.this$0 = playerController;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerController$release$1 playerController$release$1 = new PlayerController$release$1(this.$player, this.this$0, continuation);
        playerController$release$1.L$0 = obj;
        return playerController$release$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerController$release$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            this.$player.release();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
