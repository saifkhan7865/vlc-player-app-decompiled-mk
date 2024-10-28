package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00060\u0005H@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/paging/PageEvent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$pageEventFlow$2", f = "PageFetcherSnapshot.kt", i = {0, 0}, l = {645, 179}, m = "invokeSuspend", n = {"this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1"})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$pageEventFlow$2 extends SuspendLambda implements Function2<FlowCollector<? super PageEvent<Value>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$pageEventFlow$2(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super PageFetcherSnapshot$pageEventFlow$2> continuation) {
        super(2, continuation);
        this.this$0 = pageFetcherSnapshot;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PageFetcherSnapshot$pageEventFlow$2 pageFetcherSnapshot$pageEventFlow$2 = new PageFetcherSnapshot$pageEventFlow$2(this.this$0, continuation);
        pageFetcherSnapshot$pageEventFlow$2.L$0 = obj;
        return pageFetcherSnapshot$pageEventFlow$2;
    }

    public final Object invoke(FlowCollector<? super PageEvent<Value>> flowCollector, Continuation<? super Unit> continuation) {
        return ((PageFetcherSnapshot$pageEventFlow$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: kotlinx.coroutines.flow.FlowCollector} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            r3 = 2
            r4 = 0
            if (r1 == 0) goto L_0x002b
            if (r1 == r2) goto L_0x001b
            if (r1 != r3) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0076
        L_0x0013:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001b:
            java.lang.Object r1 = r7.L$2
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            java.lang.Object r2 = r7.L$1
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r7.L$0
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0050
        L_0x002b:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r1 = r8
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            androidx.paging.PageFetcherSnapshot<Key, Value> r8 = r7.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r5 = r8.stateHolder
            kotlinx.coroutines.sync.Mutex r8 = r5.lock
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7.L$0 = r5
            r7.L$1 = r8
            r7.L$2 = r1
            r7.label = r2
            java.lang.Object r2 = r8.lock(r4, r6)
            if (r2 != r0) goto L_0x004f
            return r0
        L_0x004f:
            r2 = r8
        L_0x0050:
            androidx.paging.PageFetcherSnapshotState r8 = r5.state     // Catch:{ all -> 0x0079 }
            androidx.paging.MutableLoadStateCollection r8 = r8.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0079 }
            androidx.paging.LoadStates r8 = r8.snapshot()     // Catch:{ all -> 0x0079 }
            r2.unlock(r4)
            androidx.paging.PageEvent$LoadStateUpdate r2 = new androidx.paging.PageEvent$LoadStateUpdate
            r2.<init>(r8, r4, r3, r4)
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.L$0 = r4
            r7.L$1 = r4
            r7.L$2 = r4
            r7.label = r3
            java.lang.Object r8 = r1.emit(r2, r8)
            if (r8 != r0) goto L_0x0076
            return r0
        L_0x0076:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0079:
            r8 = move-exception
            r2.unlock(r4)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot$pageEventFlow$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
