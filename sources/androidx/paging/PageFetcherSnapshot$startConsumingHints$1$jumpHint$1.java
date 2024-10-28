package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "hint", "Landroidx/paging/ViewportHint;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$startConsumingHints$1$jumpHint$1", f = "PageFetcherSnapshot.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$startConsumingHints$1$jumpHint$1 extends SuspendLambda implements Function2<ViewportHint, Continuation<? super Boolean>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$startConsumingHints$1$jumpHint$1(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super PageFetcherSnapshot$startConsumingHints$1$jumpHint$1> continuation) {
        super(2, continuation);
        this.this$0 = pageFetcherSnapshot;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PageFetcherSnapshot$startConsumingHints$1$jumpHint$1 pageFetcherSnapshot$startConsumingHints$1$jumpHint$1 = new PageFetcherSnapshot$startConsumingHints$1$jumpHint$1(this.this$0, continuation);
        pageFetcherSnapshot$startConsumingHints$1$jumpHint$1.L$0 = obj;
        return pageFetcherSnapshot$startConsumingHints$1$jumpHint$1;
    }

    public final Object invoke(ViewportHint viewportHint, Continuation<? super Boolean> continuation) {
        return ((PageFetcherSnapshot$startConsumingHints$1$jumpHint$1) create(viewportHint, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ViewportHint viewportHint = (ViewportHint) this.L$0;
            return Boxing.boxBoolean(viewportHint.getPresentedItemsBefore() * -1 > this.this$0.config.jumpThreshold || viewportHint.getPresentedItemsAfter() * -1 > this.this$0.config.jumpThreshold);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
