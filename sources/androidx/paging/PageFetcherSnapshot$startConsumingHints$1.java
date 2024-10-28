package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$startConsumingHints$1", f = "PageFetcherSnapshot.kt", i = {}, l = {220}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$startConsumingHints$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$startConsumingHints$1(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super PageFetcherSnapshot$startConsumingHints$1> continuation) {
        super(2, continuation);
        this.this$0 = pageFetcherSnapshot;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PageFetcherSnapshot$startConsumingHints$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PageFetcherSnapshot$startConsumingHints$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow[] flowArr = {this.this$0.hintHandler.hintFor(LoadType.APPEND), this.this$0.hintHandler.hintFor(LoadType.PREPEND)};
            this.label = 1;
            obj = FlowKt.firstOrNull(FlowKt.merge((Flow<? extends T>[]) flowArr), new PageFetcherSnapshot$startConsumingHints$1$jumpHint$1(this.this$0, (Continuation<? super PageFetcherSnapshot$startConsumingHints$1$jumpHint$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ViewportHint viewportHint = (ViewportHint) obj;
        if (viewportHint != null) {
            PageFetcherSnapshot<Key, Value> pageFetcherSnapshot = this.this$0;
            Logger logger = LoggerKt.getLOGGER();
            if (logger != null && logger.isLoggable(3)) {
                logger.log(3, "Jump triggered on PagingSource " + pageFetcherSnapshot.getPagingSource$paging_common() + " by " + viewportHint, (Throwable) null);
            }
            this.this$0.jumpCallback.invoke();
        }
        return Unit.INSTANCE;
    }
}
