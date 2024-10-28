package org.videolan.vlc.gui.helpers;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.hf.PinCodeDelegateKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiTools$showPinIfNeeded$1", f = "UiTools.kt", i = {}, l = {531}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: UiTools.kt */
final class UiTools$showPinIfNeeded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $this_showPinIfNeeded;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UiTools$showPinIfNeeded$1(FragmentActivity fragmentActivity, Continuation<? super UiTools$showPinIfNeeded$1> continuation) {
        super(2, continuation);
        this.$this_showPinIfNeeded = fragmentActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UiTools$showPinIfNeeded$1(this.$this_showPinIfNeeded, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UiTools$showPinIfNeeded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = PinCodeDelegateKt.checkPIN(this.$this_showPinIfNeeded, true, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) obj).booleanValue()) {
            UiTools.INSTANCE.snacker(this.$this_showPinIfNeeded, R.string.pin_code_access_granted, false);
        }
        return Unit.INSTANCE;
    }
}
