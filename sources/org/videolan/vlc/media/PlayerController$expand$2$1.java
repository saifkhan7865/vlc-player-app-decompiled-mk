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
import org.videolan.libvlc.interfaces.IMediaList;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lorg/videolan/libvlc/interfaces/IMediaList;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlayerController$expand$2$1", f = "PlayerController.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlayerController.kt */
final class PlayerController$expand$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IMediaList>, Object> {
    final /* synthetic */ IMedia $it;
    int label;
    final /* synthetic */ PlayerController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerController$expand$2$1(PlayerController playerController, IMedia iMedia, Continuation<? super PlayerController$expand$2$1> continuation) {
        super(2, continuation);
        this.this$0 = playerController;
        this.$it = iMedia;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerController$expand$2$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super IMediaList> continuation) {
        return ((PlayerController$expand$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.getMediaplayer().setEventListener((MediaPlayer.EventListener) null);
            IMediaList subItems = this.$it.subItems();
            this.$it.release();
            this.this$0.getMediaplayer().setEventListener(this.this$0);
            return subItems;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
