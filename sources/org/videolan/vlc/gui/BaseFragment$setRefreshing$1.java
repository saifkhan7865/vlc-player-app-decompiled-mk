package org.videolan.vlc.gui;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.BaseFragment$setRefreshing$1", f = "BaseFragment.kt", i = {}, l = {139}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseFragment.kt */
final class BaseFragment$setRefreshing$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Boolean, Unit> $action;
    final /* synthetic */ boolean $refreshing;
    int label;
    final /* synthetic */ BaseFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseFragment$setRefreshing$1(boolean z, BaseFragment baseFragment, Function1<? super Boolean, Unit> function1, Continuation<? super BaseFragment$setRefreshing$1> continuation) {
        super(2, continuation);
        this.$refreshing = z;
        this.this$0 = baseFragment;
        this.$action = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseFragment$setRefreshing$1(this.$refreshing, this.this$0, this.$action, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseFragment$setRefreshing$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$refreshing) {
                this.label = 1;
                if (DelayKt.delay(300, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.getSwipeRefreshLayout().setRefreshing(this.$refreshing);
        FragmentActivity activity = this.this$0.getActivity();
        MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
        if (mainActivity != null) {
            mainActivity.setRefreshing(this.$refreshing);
        }
        Function1<Boolean, Unit> function1 = this.$action;
        if (function1 != null) {
            function1.invoke(Boxing.boxBoolean(this.$refreshing));
        }
        return Unit.INSTANCE;
    }
}
