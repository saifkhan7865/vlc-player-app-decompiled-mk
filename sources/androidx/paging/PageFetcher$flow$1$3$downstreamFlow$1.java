package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006H@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "it", "Landroidx/paging/PageEvent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcher$flow$1$3$downstreamFlow$1", f = "PageFetcher.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PageFetcher.kt */
final class PageFetcher$flow$1$3$downstreamFlow$1 extends SuspendLambda implements Function2<PageEvent<Value>, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;

    PageFetcher$flow$1$3$downstreamFlow$1(Continuation<? super PageFetcher$flow$1$3$downstreamFlow$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PageFetcher$flow$1$3$downstreamFlow$1 pageFetcher$flow$1$3$downstreamFlow$1 = new PageFetcher$flow$1$3$downstreamFlow$1(continuation);
        pageFetcher$flow$1$3$downstreamFlow$1.L$0 = obj;
        return pageFetcher$flow$1$3$downstreamFlow$1;
    }

    public final Object invoke(PageEvent<Value> pageEvent, Continuation<? super Unit> continuation) {
        return ((PageFetcher$flow$1$3$downstreamFlow$1) create(pageEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PageEvent pageEvent = (PageEvent) this.L$0;
            Logger logger = LoggerKt.getLOGGER();
            if (logger != null && logger.isLoggable(2)) {
                logger.log(2, "Sent " + pageEvent, (Throwable) null);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
