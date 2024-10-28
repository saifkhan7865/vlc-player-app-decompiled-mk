package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.DisplaySettingsDialog$updateSorts$1$1$1", f = "DisplaySettingsDialog.kt", i = {}, l = {294}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DisplaySettingsDialog.kt */
final class DisplaySettingsDialog$updateSorts$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $sort;
    int label;
    final /* synthetic */ DisplaySettingsDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DisplaySettingsDialog$updateSorts$1$1$1(DisplaySettingsDialog displaySettingsDialog, int i, Continuation<? super DisplaySettingsDialog$updateSorts$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = displaySettingsDialog;
        this.$sort = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DisplaySettingsDialog$updateSorts$1$1$1(this.this$0, this.$sort, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisplaySettingsDialog$updateSorts$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.getDisplaySettingsViewModel().send(DisplaySettingsDialogKt.CURRENT_SORT, new Pair(Boxing.boxInt(this.$sort), Boxing.boxBoolean(false)), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
