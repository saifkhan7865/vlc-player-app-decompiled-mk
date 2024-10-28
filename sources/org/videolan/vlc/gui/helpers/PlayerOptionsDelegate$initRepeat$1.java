package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.PlayerOptionsDelegate$initRepeat$1", f = "PlayerOptionsDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlayerOptionsDelegate.kt */
final class PlayerOptionsDelegate$initRepeat$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PlayerOptionsDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerOptionsDelegate$initRepeat$1(PlayerOptionsDelegate playerOptionsDelegate, Continuation<? super PlayerOptionsDelegate$initRepeat$1> continuation) {
        super(2, continuation);
        this.this$0 = playerOptionsDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerOptionsDelegate$initRepeat$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerOptionsDelegate$initRepeat$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlayerOptionItemBinding access$getRepeatBinding$p = this.this$0.repeatBinding;
            PlayerOptionItemBinding playerOptionItemBinding = null;
            if (access$getRepeatBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                access$getRepeatBinding$p = null;
            }
            ImageView imageView = access$getRepeatBinding$p.optionIcon;
            int repeatType = this.this$0.getService().getRepeatType();
            if (repeatType == 1) {
                i = R.drawable.ic_repeat_one;
            } else if (repeatType != 2) {
                i = R.drawable.ic_repeat;
            } else {
                i = R.drawable.ic_repeat_all;
            }
            imageView.setImageResource(i);
            PlayerOptionItemBinding access$getRepeatBinding$p2 = this.this$0.repeatBinding;
            if (access$getRepeatBinding$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                access$getRepeatBinding$p2 = null;
            }
            View root = access$getRepeatBinding$p2.getRoot();
            PlayerOptionItemBinding access$getRepeatBinding$p3 = this.this$0.repeatBinding;
            if (access$getRepeatBinding$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
            } else {
                playerOptionItemBinding = access$getRepeatBinding$p3;
            }
            Context context = playerOptionItemBinding.getRoot().getContext();
            int repeatType2 = this.this$0.getService().getRepeatType();
            if (repeatType2 == 1) {
                i2 = R.string.repeat_single;
            } else if (repeatType2 != 2) {
                i2 = R.string.repeat_none;
            } else {
                i2 = R.string.repeat_all;
            }
            root.setContentDescription(context.getString(i2));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
