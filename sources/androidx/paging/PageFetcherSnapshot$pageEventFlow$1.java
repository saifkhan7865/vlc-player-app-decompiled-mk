package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00060\u0005H@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "Landroidx/paging/SimpleProducerScope;", "Landroidx/paging/PageEvent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$pageEventFlow$1", f = "PageFetcherSnapshot.kt", i = {0, 0, 0, 0, 1, 2, 2, 2}, l = {645, 168, 657}, m = "invokeSuspend", n = {"$this$cancelableChannelFlow", "it", "this_$iv", "$this$withLock_u24default$iv$iv", "$this$cancelableChannelFlow", "$this$cancelableChannelFlow", "this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$0", "L$1", "L$2"})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$pageEventFlow$1 extends SuspendLambda implements Function2<SimpleProducerScope<PageEvent<Value>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$pageEventFlow$1(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super PageFetcherSnapshot$pageEventFlow$1> continuation) {
        super(2, continuation);
        this.this$0 = pageFetcherSnapshot;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PageFetcherSnapshot$pageEventFlow$1 pageFetcherSnapshot$pageEventFlow$1 = new PageFetcherSnapshot$pageEventFlow$1(this.this$0, continuation);
        pageFetcherSnapshot$pageEventFlow$1.L$0 = obj;
        return pageFetcherSnapshot$pageEventFlow$1;
    }

    public final Object invoke(SimpleProducerScope<PageEvent<Value>> simpleProducerScope, Continuation<? super Unit> continuation) {
        return ((PageFetcherSnapshot$pageEventFlow$1) create(simpleProducerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: androidx.paging.SimpleProducerScope} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0105 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x011e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0049
            if (r1 == r4) goto L_0x0034
            if (r1 == r3) goto L_0x002b
            if (r1 != r2) goto L_0x0023
            java.lang.Object r0 = r14.L$2
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r1 = r14.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r1 = (androidx.paging.PageFetcherSnapshotState.Holder) r1
            java.lang.Object r2 = r14.L$0
            androidx.paging.SimpleProducerScope r2 = (androidx.paging.SimpleProducerScope) r2
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0109
        L_0x0023:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x002b:
            java.lang.Object r1 = r14.L$0
            androidx.paging.SimpleProducerScope r1 = (androidx.paging.SimpleProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00ea
        L_0x0034:
            java.lang.Object r1 = r14.L$3
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r4 = r14.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r4 = (androidx.paging.PageFetcherSnapshotState.Holder) r4
            java.lang.Object r6 = r14.L$1
            androidx.paging.RemoteMediatorConnection r6 = (androidx.paging.RemoteMediatorConnection) r6
            java.lang.Object r7 = r14.L$0
            androidx.paging.SimpleProducerScope r7 = (androidx.paging.SimpleProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00be
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r7 = r15
            androidx.paging.SimpleProducerScope r7 = (androidx.paging.SimpleProducerScope) r7
            androidx.paging.PageFetcherSnapshot<Key, Value> r15 = r14.this$0
            java.util.concurrent.atomic.AtomicBoolean r15 = r15.pageEventChCollected
            r1 = 0
            boolean r15 = r15.compareAndSet(r1, r4)
            if (r15 == 0) goto L_0x012d
            r15 = r7
            kotlinx.coroutines.CoroutineScope r15 = (kotlinx.coroutines.CoroutineScope) r15
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2 r6 = new androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2
            androidx.paging.PageFetcherSnapshot<Key, Value> r8 = r14.this$0
            r6.<init>(r8, r7, r5)
            r11 = r6
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r12 = 3
            r13 = 0
            r9 = 0
            r10 = 0
            r8 = r15
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r8, r9, r10, r11, r12, r13)
            r6 = 6
            kotlinx.coroutines.channels.Channel r1 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r1, r5, r5, r6, r5)
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$3 r6 = new androidx.paging.PageFetcherSnapshot$pageEventFlow$1$3
            androidx.paging.PageFetcherSnapshot<Key, Value> r8 = r14.this$0
            r6.<init>(r8, r1, r5)
            r11 = r6
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r8 = r15
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r8, r9, r10, r11, r12, r13)
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r6 = new androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4
            androidx.paging.PageFetcherSnapshot<Key, Value> r8 = r14.this$0
            r6.<init>(r1, r8, r5)
            r11 = r6
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r8 = r15
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r8, r9, r10, r11, r12, r13)
            androidx.paging.PageFetcherSnapshot<Key, Value> r15 = r14.this$0
            androidx.paging.RemoteMediatorConnection r6 = r15.getRemoteMediatorConnection()
            if (r6 == 0) goto L_0x00d3
            androidx.paging.PageFetcherSnapshot<Key, Value> r15 = r14.this$0
            androidx.paging.PagingState r1 = r15.previousPagingState
            if (r1 != 0) goto L_0x00d0
            androidx.paging.PageFetcherSnapshotState$Holder r15 = r15.stateHolder
            kotlinx.coroutines.sync.Mutex r1 = r15.lock
            r14.L$0 = r7
            r14.L$1 = r6
            r14.L$2 = r15
            r14.L$3 = r1
            r14.label = r4
            java.lang.Object r4 = r1.lock(r5, r14)
            if (r4 != r0) goto L_0x00bd
            return r0
        L_0x00bd:
            r4 = r15
        L_0x00be:
            androidx.paging.PageFetcherSnapshotState r15 = r4.state     // Catch:{ all -> 0x00cb }
            androidx.paging.PagingState r15 = r15.currentPagingState$paging_common(r5)     // Catch:{ all -> 0x00cb }
            r1.unlock(r5)
            r1 = r15
            goto L_0x00d0
        L_0x00cb:
            r15 = move-exception
            r1.unlock(r5)
            throw r15
        L_0x00d0:
            r6.requestRefreshIfAllowed(r1)
        L_0x00d3:
            androidx.paging.PageFetcherSnapshot<Key, Value> r15 = r14.this$0
            r1 = r14
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r14.L$0 = r7
            r14.L$1 = r5
            r14.L$2 = r5
            r14.L$3 = r5
            r14.label = r3
            java.lang.Object r15 = r15.doInitialLoad(r1)
            if (r15 != r0) goto L_0x00e9
            return r0
        L_0x00e9:
            r1 = r7
        L_0x00ea:
            androidx.paging.PageFetcherSnapshot<Key, Value> r15 = r14.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r15 = r15.stateHolder
            kotlinx.coroutines.sync.Mutex r3 = r15.lock
            r4 = r14
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r14.L$0 = r1
            r14.L$1 = r15
            r14.L$2 = r3
            r14.label = r2
            java.lang.Object r2 = r3.lock(r5, r4)
            if (r2 != r0) goto L_0x0106
            return r0
        L_0x0106:
            r2 = r1
            r0 = r3
            r1 = r15
        L_0x0109:
            androidx.paging.PageFetcherSnapshotState r15 = r1.state     // Catch:{ all -> 0x0128 }
            androidx.paging.MutableLoadStateCollection r15 = r15.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0128 }
            androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0128 }
            androidx.paging.LoadState r15 = r15.get(r1)     // Catch:{ all -> 0x0128 }
            r0.unlock(r5)
            boolean r15 = r15 instanceof androidx.paging.LoadState.Error
            if (r15 != 0) goto L_0x0125
            androidx.paging.PageFetcherSnapshot<Key, Value> r15 = r14.this$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            r15.startConsumingHints(r2)
        L_0x0125:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        L_0x0128:
            r15 = move-exception
            r0.unlock(r5)
            throw r15
        L_0x012d:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "Attempt to collect twice from pageEventFlow, which is an illegal operation. Did you forget to call Flow<PagingData<*>>.cachedIn(coroutineScope)?"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot$pageEventFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
