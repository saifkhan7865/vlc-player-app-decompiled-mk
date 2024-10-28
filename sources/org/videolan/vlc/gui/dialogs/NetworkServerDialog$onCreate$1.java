package org.videolan.vlc.gui.dialogs;

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
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.NetworkServerDialog$onCreate$1", f = "NetworkServerDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NetworkServerDialog.kt */
final class NetworkServerDialog$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ NetworkServerDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkServerDialog$onCreate$1(NetworkServerDialog networkServerDialog, Continuation<? super NetworkServerDialog$onCreate$1> continuation) {
        super(2, continuation);
        this.this$0 = networkServerDialog;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NetworkServerDialog$onCreate$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NetworkServerDialog$onCreate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            if (uiTools.showPinIfNeeded(requireActivity)) {
                this.this$0.dismiss();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}