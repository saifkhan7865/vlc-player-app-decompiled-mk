package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.videolan.vlc.gui.helpers.Click;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/gui/helpers/Click;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HistoryFragment$onViewCreated$4", f = "HistoryFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HistoryFragment.kt */
final class HistoryFragment$onViewCreated$4 extends SuspendLambda implements Function2<Click, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HistoryFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HistoryFragment$onViewCreated$4(HistoryFragment historyFragment, Continuation<? super HistoryFragment$onViewCreated$4> continuation) {
        super(2, continuation);
        this.this$0 = historyFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HistoryFragment$onViewCreated$4 historyFragment$onViewCreated$4 = new HistoryFragment$onViewCreated$4(this.this$0, continuation);
        historyFragment$onViewCreated$4.L$0 = obj;
        return historyFragment$onViewCreated$4;
    }

    public final Object invoke(Click click, Continuation<? super Unit> continuation) {
        return ((HistoryFragment$onViewCreated$4) create(click, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.process((Click) this.L$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
