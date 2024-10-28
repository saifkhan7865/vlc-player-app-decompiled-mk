package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.PagingDataDiffer$collectFrom$2", f = "PagingDataDiffer.kt", i = {}, l = {140}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PagingDataDiffer.kt */
final class PagingDataDiffer$collectFrom$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ PagingData<T> $pagingData;
    int label;
    final /* synthetic */ PagingDataDiffer<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataDiffer$collectFrom$2(PagingDataDiffer<T> pagingDataDiffer, PagingData<T> pagingData, Continuation<? super PagingDataDiffer$collectFrom$2> continuation) {
        super(1, continuation);
        this.this$0 = pagingDataDiffer;
        this.$pagingData = pagingData;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new PagingDataDiffer$collectFrom$2(this.this$0, this.$pagingData, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((PagingDataDiffer$collectFrom$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.uiReceiver = this.$pagingData.getUiReceiver$paging_common();
            Flow<PageEvent<T>> flow$paging_common = this.$pagingData.getFlow$paging_common();
            final PagingDataDiffer<T> pagingDataDiffer = this.this$0;
            final PagingData<T> pagingData = this.$pagingData;
            this.label = 1;
            if (flow$paging_common.collect(new FlowCollector() {

                @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.paging.PagingDataDiffer$collectFrom$2$1$2", f = "PagingDataDiffer.kt", i = {}, l = {159, 169, 186}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.paging.PagingDataDiffer$collectFrom$2$1$2  reason: invalid class name */
                /* compiled from: PagingDataDiffer.kt */
                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(pageEvent, pagingDataDiffer, pagingData, continuation);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:85:0x0215 A[LOOP:1: B:83:0x020f->B:85:0x0215, LOOP_END] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
                        /*
                            r17 = this;
                            r0 = r17
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 3
                            r4 = 2
                            r5 = 1
                            r6 = 0
                            if (r2 == 0) goto L_0x0026
                            if (r2 == r5) goto L_0x0021
                            if (r2 == r4) goto L_0x0021
                            if (r2 != r3) goto L_0x0019
                            kotlin.ResultKt.throwOnFailure(r18)
                            goto L_0x00e0
                        L_0x0019:
                            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                            r1.<init>(r2)
                            throw r1
                        L_0x0021:
                            kotlin.ResultKt.throwOnFailure(r18)
                            goto L_0x01f5
                        L_0x0026:
                            kotlin.ResultKt.throwOnFailure(r18)
                            androidx.paging.PageEvent<T> r2 = r6
                            boolean r7 = r2 instanceof androidx.paging.PageEvent.Insert
                            if (r7 == 0) goto L_0x0077
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            androidx.paging.LoadType r2 = r2.getLoadType()
                            androidx.paging.LoadType r7 = androidx.paging.LoadType.REFRESH
                            if (r2 != r7) goto L_0x0077
                            androidx.paging.PagingDataDiffer<T> r8 = r3
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            java.util.List r9 = r2.getPages()
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            int r10 = r2.getPlaceholdersBefore()
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            int r11 = r2.getPlaceholdersAfter()
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            androidx.paging.LoadStates r13 = r2.getSourceLoadStates()
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            androidx.paging.LoadStates r14 = r2.getMediatorLoadStates()
                            androidx.paging.PagingData<T> r2 = r4
                            androidx.paging.HintReceiver r15 = r2.getHintReceiver$paging_common()
                            r16 = r0
                            kotlin.coroutines.Continuation r16 = (kotlin.coroutines.Continuation) r16
                            r0.label = r5
                            r12 = 1
                            java.lang.Object r2 = r8.presentNewList(r9, r10, r11, r12, r13, r14, r15, r16)
                            if (r2 != r1) goto L_0x01f5
                            return r1
                        L_0x0077:
                            androidx.paging.PageEvent<T> r2 = r6
                            boolean r2 = r2 instanceof androidx.paging.PageEvent.StaticList
                            if (r2 == 0) goto L_0x00cc
                            androidx.paging.PagingDataDiffer<T> r7 = r3
                            androidx.paging.TransformablePage r2 = new androidx.paging.TransformablePage
                            androidx.paging.PageEvent<T> r3 = r6
                            androidx.paging.PageEvent$StaticList r3 = (androidx.paging.PageEvent.StaticList) r3
                            java.util.List r3 = r3.getData()
                            r2.<init>(r6, r3)
                            java.util.List r8 = kotlin.collections.CollectionsKt.listOf(r2)
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$StaticList r2 = (androidx.paging.PageEvent.StaticList) r2
                            androidx.paging.LoadStates r2 = r2.getSourceLoadStates()
                            if (r2 != 0) goto L_0x00a7
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$StaticList r2 = (androidx.paging.PageEvent.StaticList) r2
                            androidx.paging.LoadStates r2 = r2.getMediatorLoadStates()
                            if (r2 == 0) goto L_0x00a5
                            goto L_0x00a7
                        L_0x00a5:
                            r11 = 0
                            goto L_0x00a8
                        L_0x00a7:
                            r11 = 1
                        L_0x00a8:
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$StaticList r2 = (androidx.paging.PageEvent.StaticList) r2
                            androidx.paging.LoadStates r12 = r2.getSourceLoadStates()
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$StaticList r2 = (androidx.paging.PageEvent.StaticList) r2
                            androidx.paging.LoadStates r13 = r2.getMediatorLoadStates()
                            androidx.paging.PagingData<T> r2 = r4
                            androidx.paging.HintReceiver r14 = r2.getHintReceiver$paging_common()
                            r15 = r0
                            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
                            r0.label = r4
                            r9 = 0
                            r10 = 0
                            java.lang.Object r2 = r7.presentNewList(r8, r9, r10, r11, r12, r13, r14, r15)
                            if (r2 != r1) goto L_0x01f5
                            return r1
                        L_0x00cc:
                            androidx.paging.PagingDataDiffer<T> r2 = r3
                            boolean r2 = r2.postEvents()
                            if (r2 == 0) goto L_0x00e0
                            r2 = r0
                            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                            r0.label = r3
                            java.lang.Object r2 = kotlinx.coroutines.YieldKt.yield(r2)
                            if (r2 != r1) goto L_0x00e0
                            return r1
                        L_0x00e0:
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            androidx.paging.PagePresenter r1 = r1.presenter
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PagingDataDiffer<T> r3 = r3
                            androidx.paging.PagingDataDiffer$processPageEventCallback$1 r3 = r3.processPageEventCallback
                            androidx.paging.PagePresenter$ProcessPageEventCallback r3 = (androidx.paging.PagePresenter.ProcessPageEventCallback) r3
                            r1.processEvent(r2, r3)
                            androidx.paging.PageEvent<T> r1 = r6
                            boolean r1 = r1 instanceof androidx.paging.PageEvent.Drop
                            if (r1 == 0) goto L_0x00fe
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            r1.lastAccessedIndexUnfulfilled = r6
                        L_0x00fe:
                            androidx.paging.PageEvent<T> r1 = r6
                            boolean r1 = r1 instanceof androidx.paging.PageEvent.Insert
                            if (r1 == 0) goto L_0x01f5
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            androidx.paging.MutableCombinedLoadStateCollection r1 = r1.combinedLoadStatesCollection
                            kotlinx.coroutines.flow.StateFlow r1 = r1.getStateFlow()
                            java.lang.Object r1 = r1.getValue()
                            androidx.paging.CombinedLoadStates r1 = (androidx.paging.CombinedLoadStates) r1
                            if (r1 == 0) goto L_0x011b
                            androidx.paging.LoadStates r1 = r1.getSource()
                            goto L_0x011c
                        L_0x011b:
                            r1 = 0
                        L_0x011c:
                            if (r1 == 0) goto L_0x01e9
                            androidx.paging.LoadState r2 = r1.getPrepend()
                            boolean r2 = r2.getEndOfPaginationReached()
                            androidx.paging.LoadState r1 = r1.getAppend()
                            boolean r1 = r1.getEndOfPaginationReached()
                            androidx.paging.PageEvent<T> r3 = r6
                            androidx.paging.PageEvent$Insert r3 = (androidx.paging.PageEvent.Insert) r3
                            androidx.paging.LoadType r3 = r3.getLoadType()
                            androidx.paging.LoadType r4 = androidx.paging.LoadType.PREPEND
                            if (r3 != r4) goto L_0x013c
                            if (r2 != 0) goto L_0x014b
                        L_0x013c:
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            androidx.paging.LoadType r2 = r2.getLoadType()
                            androidx.paging.LoadType r3 = androidx.paging.LoadType.APPEND
                            if (r2 != r3) goto L_0x014d
                            if (r1 != 0) goto L_0x014b
                            goto L_0x014d
                        L_0x014b:
                            r1 = 0
                            goto L_0x014e
                        L_0x014d:
                            r1 = 1
                        L_0x014e:
                            androidx.paging.PageEvent<T> r2 = r6
                            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
                            java.util.List r2 = r2.getPages()
                            java.lang.Iterable r2 = (java.lang.Iterable) r2
                            boolean r3 = r2 instanceof java.util.Collection
                            if (r3 == 0) goto L_0x0166
                            r3 = r2
                            java.util.Collection r3 = (java.util.Collection) r3
                            boolean r3 = r3.isEmpty()
                            if (r3 == 0) goto L_0x0166
                            goto L_0x0181
                        L_0x0166:
                            java.util.Iterator r2 = r2.iterator()
                        L_0x016a:
                            boolean r3 = r2.hasNext()
                            if (r3 == 0) goto L_0x0181
                            java.lang.Object r3 = r2.next()
                            androidx.paging.TransformablePage r3 = (androidx.paging.TransformablePage) r3
                            java.util.List r3 = r3.getData()
                            boolean r3 = r3.isEmpty()
                            if (r3 != 0) goto L_0x016a
                            r5 = 0
                        L_0x0181:
                            if (r1 != 0) goto L_0x0189
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            r1.lastAccessedIndexUnfulfilled = r6
                            goto L_0x01f5
                        L_0x0189:
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            boolean r1 = r1.lastAccessedIndexUnfulfilled
                            if (r1 != 0) goto L_0x0193
                            if (r5 == 0) goto L_0x01f5
                        L_0x0193:
                            if (r5 != 0) goto L_0x01cb
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            int r1 = r1.lastAccessedIndex
                            androidx.paging.PagingDataDiffer<T> r2 = r3
                            androidx.paging.PagePresenter r2 = r2.presenter
                            int r2 = r2.getPlaceholdersBefore()
                            if (r1 < r2) goto L_0x01cb
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            int r1 = r1.lastAccessedIndex
                            androidx.paging.PagingDataDiffer<T> r2 = r3
                            androidx.paging.PagePresenter r2 = r2.presenter
                            int r2 = r2.getPlaceholdersBefore()
                            androidx.paging.PagingDataDiffer<T> r3 = r3
                            androidx.paging.PagePresenter r3 = r3.presenter
                            int r3 = r3.getStorageCount()
                            int r2 = r2 + r3
                            if (r1 <= r2) goto L_0x01c5
                            goto L_0x01cb
                        L_0x01c5:
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            r1.lastAccessedIndexUnfulfilled = r6
                            goto L_0x01f5
                        L_0x01cb:
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            androidx.paging.HintReceiver r1 = r1.hintReceiver
                            if (r1 == 0) goto L_0x01f5
                            androidx.paging.PagingDataDiffer<T> r2 = r3
                            androidx.paging.PagePresenter r2 = r2.presenter
                            androidx.paging.PagingDataDiffer<T> r3 = r3
                            int r3 = r3.lastAccessedIndex
                            androidx.paging.ViewportHint$Access r2 = r2.accessHintForPresenterIndex(r3)
                            androidx.paging.ViewportHint r2 = (androidx.paging.ViewportHint) r2
                            r1.accessHint(r2)
                            goto L_0x01f5
                        L_0x01e9:
                            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                            java.lang.String r2 = "PagingDataDiffer.combinedLoadStatesCollection.stateFlow shouldnot hold null CombinedLoadStates after Insert event."
                            java.lang.String r2 = r2.toString()
                            r1.<init>(r2)
                            throw r1
                        L_0x01f5:
                            androidx.paging.PageEvent<T> r1 = r6
                            boolean r2 = r1 instanceof androidx.paging.PageEvent.Insert
                            if (r2 != 0) goto L_0x0203
                            boolean r2 = r1 instanceof androidx.paging.PageEvent.Drop
                            if (r2 != 0) goto L_0x0203
                            boolean r1 = r1 instanceof androidx.paging.PageEvent.StaticList
                            if (r1 == 0) goto L_0x021f
                        L_0x0203:
                            androidx.paging.PagingDataDiffer<T> r1 = r3
                            java.util.concurrent.CopyOnWriteArrayList r1 = r1.onPagesUpdatedListeners
                            java.lang.Iterable r1 = (java.lang.Iterable) r1
                            java.util.Iterator r1 = r1.iterator()
                        L_0x020f:
                            boolean r2 = r1.hasNext()
                            if (r2 == 0) goto L_0x021f
                            java.lang.Object r2 = r1.next()
                            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
                            r2.invoke()
                            goto L_0x020f
                        L_0x021f:
                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataDiffer$collectFrom$2.AnonymousClass1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                public final Object emit(final PageEvent<T> pageEvent, Continuation<? super Unit> continuation) {
                    Logger logger = LoggerKt.getLOGGER();
                    if (logger != null && logger.isLoggable(2)) {
                        logger.log(2, "Collected " + pageEvent, (Throwable) null);
                    }
                    CoroutineContext access$getMainContext$p = pagingDataDiffer.mainContext;
                    final PagingDataDiffer<T> pagingDataDiffer = pagingDataDiffer;
                    final PagingData<T> pagingData = pagingData;
                    Object withContext = BuildersKt.withContext(access$getMainContext$p, new AnonymousClass2((Continuation<? super AnonymousClass2>) null), continuation);
                    return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
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
