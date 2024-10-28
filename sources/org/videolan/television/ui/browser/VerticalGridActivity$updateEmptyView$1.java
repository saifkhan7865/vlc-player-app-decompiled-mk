package org.videolan.television.ui.browser;

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
import org.videolan.television.databinding.TvVerticalGridBinding;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.browser.VerticalGridActivity$updateEmptyView$1", f = "VerticalGridActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VerticalGridActivity.kt */
final class VerticalGridActivity$updateEmptyView$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $empty;
    int label;
    final /* synthetic */ VerticalGridActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VerticalGridActivity$updateEmptyView$1(VerticalGridActivity verticalGridActivity, boolean z, Continuation<? super VerticalGridActivity$updateEmptyView$1> continuation) {
        super(2, continuation);
        this.this$0 = verticalGridActivity;
        this.$empty = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VerticalGridActivity$updateEmptyView$1(this.this$0, this.$empty, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VerticalGridActivity$updateEmptyView$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            TvVerticalGridBinding access$getBinding$p = this.this$0.binding;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            access$getBinding$p.tvFragmentEmpty.setVisibility(this.$empty ? 0 : 8);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
