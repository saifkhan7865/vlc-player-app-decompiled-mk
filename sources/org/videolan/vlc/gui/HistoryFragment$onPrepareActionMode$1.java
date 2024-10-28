package org.videolan.vlc.gui;

import android.content.Context;
import androidx.appcompat.view.ActionMode;
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
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.gui.helpers.UiToolsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HistoryFragment$onPrepareActionMode$1", f = "HistoryFragment.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HistoryFragment.kt */
final class HistoryFragment$onPrepareActionMode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ActionMode $mode;
    int label;
    final /* synthetic */ HistoryFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HistoryFragment$onPrepareActionMode$1(HistoryFragment historyFragment, ActionMode actionMode, Continuation<? super HistoryFragment$onPrepareActionMode$1> continuation) {
        super(2, continuation);
        this.this$0 = historyFragment;
        this.$mode = actionMode;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HistoryFragment$onPrepareActionMode$1(this.this$0, this.$mode, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HistoryFragment$onPrepareActionMode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            Context context = requireActivity;
            ActionMode actionMode = this.$mode;
            MultiSelectHelper access$getMultiSelectHelper$p = this.this$0.multiSelectHelper;
            if (access$getMultiSelectHelper$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                access$getMultiSelectHelper$p = null;
            }
            this.label = 1;
            if (UiToolsKt.fillActionMode(context, actionMode, access$getMultiSelectHelper$p, this) == coroutine_suspended) {
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
