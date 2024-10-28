package org.videolan.vlc.gui.helpers;

import android.view.View;
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
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.PlayerOptionItemBinding;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.PlayerOptionsDelegate$initShuffle$1", f = "PlayerOptionsDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlayerOptionsDelegate.kt */
final class PlayerOptionsDelegate$initShuffle$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PlayerOptionsDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerOptionsDelegate$initShuffle$1(PlayerOptionsDelegate playerOptionsDelegate, Continuation<? super PlayerOptionsDelegate$initShuffle$1> continuation) {
        super(2, continuation);
        this.this$0 = playerOptionsDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerOptionsDelegate$initShuffle$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerOptionsDelegate$initShuffle$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlayerOptionItemBinding access$getShuffleBinding$p = this.this$0.shuffleBinding;
            PlayerOptionItemBinding playerOptionItemBinding = null;
            if (access$getShuffleBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("shuffleBinding");
                access$getShuffleBinding$p = null;
            }
            access$getShuffleBinding$p.optionIcon.setImageResource(this.this$0.getService().isShuffling() ? R.drawable.ic_shuffle_on_48dp : R.drawable.ic_player_shuffle);
            PlayerOptionItemBinding access$getShuffleBinding$p2 = this.this$0.shuffleBinding;
            if (access$getShuffleBinding$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("shuffleBinding");
                access$getShuffleBinding$p2 = null;
            }
            View root = access$getShuffleBinding$p2.getRoot();
            PlayerOptionItemBinding access$getShuffleBinding$p3 = this.this$0.shuffleBinding;
            if (access$getShuffleBinding$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("shuffleBinding");
            } else {
                playerOptionItemBinding = access$getShuffleBinding$p3;
            }
            root.setContentDescription(playerOptionItemBinding.getRoot().getContext().getString(this.this$0.getService().isShuffling() ? R.string.shuffle_on : R.string.shuffle));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
