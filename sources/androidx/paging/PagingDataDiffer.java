package androidx.paging;

import androidx.paging.PageEvent;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000¥\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000*\u0001&\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u001a\u0010-\u001a\u00020\f2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0/J\u0014\u00100\u001a\u00020\f2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\"J\u001f\u00101\u001a\u00020\f2\f\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000\bH@ø\u0001\u0000¢\u0006\u0002\u00103J\u001f\u00104\u001a\u00020\f2\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000106H\u0000¢\u0006\u0002\b8J\u001a\u00109\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010:\u001a\u00020\u0014H\u0002¢\u0006\u0002\u0010;J\u0019\u0010<\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010:\u001a\u00020\u0014H\u0007¢\u0006\u0002\u0010;J\b\u0010=\u001a\u00020\u0016H\u0016JE\u0010>\u001a\u0004\u0018\u00010\u00142\f\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000@2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\f0\"H¦@ø\u0001\u0000¢\u0006\u0002\u0010CJY\u0010>\u001a\u00020\f2\u0012\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000F0E2\u0006\u0010G\u001a\u00020\u00142\u0006\u0010H\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u00162\b\u0010I\u001a\u0004\u0018\u0001062\b\u0010J\u001a\u0004\u0018\u0001062\u0006\u0010K\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0006\u0010M\u001a\u00020\fJ\u001a\u0010N\u001a\u00020\f2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0/J\u0014\u0010O\u001a\u00020\f2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\"J\u0006\u0010P\u001a\u00020\fJ\f\u0010Q\u001a\b\u0012\u0004\u0012\u00028\u00000RR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\"0!X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&X\u0004¢\u0006\u0004\n\u0002\u0010'R\u0011\u0010(\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006S"}, d2 = {"Landroidx/paging/PagingDataDiffer;", "T", "", "differCallback", "Landroidx/paging/DifferCallback;", "mainContext", "Lkotlin/coroutines/CoroutineContext;", "cachedPagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/DifferCallback;Lkotlin/coroutines/CoroutineContext;Landroidx/paging/PagingData;)V", "_onPagesUpdatedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "collectFromRunner", "Landroidx/paging/SingleRunner;", "combinedLoadStatesCollection", "Landroidx/paging/MutableCombinedLoadStateCollection;", "hintReceiver", "Landroidx/paging/HintReceiver;", "lastAccessedIndex", "", "lastAccessedIndexUnfulfilled", "", "loadStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "Landroidx/paging/CombinedLoadStates;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "onPagesUpdatedFlow", "Lkotlinx/coroutines/flow/Flow;", "getOnPagesUpdatedFlow", "()Lkotlinx/coroutines/flow/Flow;", "onPagesUpdatedListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function0;", "presenter", "Landroidx/paging/PagePresenter;", "processPageEventCallback", "androidx/paging/PagingDataDiffer$processPageEventCallback$1", "Landroidx/paging/PagingDataDiffer$processPageEventCallback$1;", "size", "getSize", "()I", "uiReceiver", "Landroidx/paging/UiReceiver;", "addLoadStateListener", "listener", "Lkotlin/Function1;", "addOnPagesUpdatedListener", "collectFrom", "pagingData", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchLoadStates", "source", "Landroidx/paging/LoadStates;", "mediator", "dispatchLoadStates$paging_common", "get", "index", "(I)Ljava/lang/Object;", "peek", "postEvents", "presentNewList", "previousList", "Landroidx/paging/NullPaddedList;", "newList", "onListPresentable", "(Landroidx/paging/NullPaddedList;Landroidx/paging/NullPaddedList;ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pages", "", "Landroidx/paging/TransformablePage;", "placeholdersBefore", "placeholdersAfter", "sourceLoadStates", "mediatorLoadStates", "newHintReceiver", "(Ljava/util/List;IIZLandroidx/paging/LoadStates;Landroidx/paging/LoadStates;Landroidx/paging/HintReceiver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refresh", "removeLoadStateListener", "removeOnPagesUpdatedListener", "retry", "snapshot", "Landroidx/paging/ItemSnapshotList;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PagingDataDiffer.kt */
public abstract class PagingDataDiffer<T> {
    /* access modifiers changed from: private */
    public final MutableSharedFlow<Unit> _onPagesUpdatedFlow;
    private final SingleRunner collectFromRunner;
    /* access modifiers changed from: private */
    public final MutableCombinedLoadStateCollection combinedLoadStatesCollection;
    /* access modifiers changed from: private */
    public final DifferCallback differCallback;
    /* access modifiers changed from: private */
    public HintReceiver hintReceiver;
    /* access modifiers changed from: private */
    public volatile int lastAccessedIndex;
    /* access modifiers changed from: private */
    public volatile boolean lastAccessedIndexUnfulfilled;
    private final StateFlow<CombinedLoadStates> loadStateFlow;
    /* access modifiers changed from: private */
    public final CoroutineContext mainContext;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<Function0<Unit>> onPagesUpdatedListeners;
    /* access modifiers changed from: private */
    public PagePresenter<T> presenter;
    /* access modifiers changed from: private */
    public final PagingDataDiffer$processPageEventCallback$1 processPageEventCallback;
    /* access modifiers changed from: private */
    public UiReceiver uiReceiver;

    public boolean postEvents() {
        return false;
    }

    public abstract Object presentNewList(NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2, int i, Function0<Unit> function0, Continuation<? super Integer> continuation);

    public PagingDataDiffer(DifferCallback differCallback2, CoroutineContext coroutineContext, PagingData<T> pagingData) {
        PageEvent.Insert<T> cachedEvent$paging_common;
        Intrinsics.checkNotNullParameter(differCallback2, "differCallback");
        Intrinsics.checkNotNullParameter(coroutineContext, "mainContext");
        this.differCallback = differCallback2;
        this.mainContext = coroutineContext;
        this.presenter = PagePresenter.Companion.initial$paging_common(pagingData != null ? pagingData.cachedEvent$paging_common() : null);
        MutableCombinedLoadStateCollection mutableCombinedLoadStateCollection = new MutableCombinedLoadStateCollection();
        if (!(pagingData == null || (cachedEvent$paging_common = pagingData.cachedEvent$paging_common()) == null)) {
            mutableCombinedLoadStateCollection.set(cachedEvent$paging_common.getSourceLoadStates(), cachedEvent$paging_common.getMediatorLoadStates());
        }
        this.combinedLoadStatesCollection = mutableCombinedLoadStateCollection;
        this.onPagesUpdatedListeners = new CopyOnWriteArrayList<>();
        this.collectFromRunner = new SingleRunner(false, 1, (DefaultConstructorMarker) null);
        this.processPageEventCallback = new PagingDataDiffer$processPageEventCallback$1(this);
        this.loadStateFlow = mutableCombinedLoadStateCollection.getStateFlow();
        this._onPagesUpdatedFlow = SharedFlowKt.MutableSharedFlow(0, 64, BufferOverflow.DROP_OLDEST);
        addOnPagesUpdatedListener(new Function0<Unit>(this) {
            final /* synthetic */ PagingDataDiffer<T> this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                this.this$0._onPagesUpdatedFlow.tryEmit(Unit.INSTANCE);
            }
        });
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PagingDataDiffer(DifferCallback differCallback2, CoroutineContext coroutineContext, PagingData pagingData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(differCallback2, (i & 2) != 0 ? Dispatchers.getMain() : coroutineContext, (i & 4) != 0 ? null : pagingData);
    }

    public final void dispatchLoadStates$paging_common(LoadStates loadStates, LoadStates loadStates2) {
        Intrinsics.checkNotNullParameter(loadStates, "source");
        this.combinedLoadStatesCollection.set(loadStates, loadStates2);
    }

    public final Object collectFrom(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        Object runInIsolation$default = SingleRunner.runInIsolation$default(this.collectFromRunner, 0, new PagingDataDiffer$collectFrom$2(this, pagingData, (Continuation<? super PagingDataDiffer$collectFrom$2>) null), continuation, 1, (Object) null);
        return runInIsolation$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? runInIsolation$default : Unit.INSTANCE;
    }

    public final T get(int i) {
        this.lastAccessedIndexUnfulfilled = true;
        this.lastAccessedIndex = i;
        Logger logger = LoggerKt.getLOGGER();
        if (logger != null && logger.isLoggable(2)) {
            logger.log(2, "Accessing item index[" + i + AbstractJsonLexerKt.END_LIST, (Throwable) null);
        }
        HintReceiver hintReceiver2 = this.hintReceiver;
        if (hintReceiver2 != null) {
            hintReceiver2.accessHint(this.presenter.accessHintForPresenterIndex(i));
        }
        return this.presenter.get(i);
    }

    public final T peek(int i) {
        return this.presenter.get(i);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.presenter.snapshot();
    }

    public final int getSize() {
        return this.presenter.getSize();
    }

    public final StateFlow<CombinedLoadStates> getLoadStateFlow() {
        return this.loadStateFlow;
    }

    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return FlowKt.asSharedFlow(this._onPagesUpdatedFlow);
    }

    public final void addOnPagesUpdatedListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.onPagesUpdatedListeners.add(function0);
    }

    public final void removeOnPagesUpdatedListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.onPagesUpdatedListeners.remove(function0);
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.combinedLoadStatesCollection.addListener(function1);
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.combinedLoadStatesCollection.removeListener(function1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object presentNewList(java.util.List<androidx.paging.TransformablePage<T>> r22, int r23, int r24, boolean r25, androidx.paging.LoadStates r26, androidx.paging.LoadStates r27, androidx.paging.HintReceiver r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            r21 = this;
            r10 = r21
            r11 = r25
            r12 = r26
            r0 = r29
            boolean r1 = r0 instanceof androidx.paging.PagingDataDiffer$presentNewList$1
            if (r1 == 0) goto L_0x001c
            r1 = r0
            androidx.paging.PagingDataDiffer$presentNewList$1 r1 = (androidx.paging.PagingDataDiffer$presentNewList$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x001c
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x0021
        L_0x001c:
            androidx.paging.PagingDataDiffer$presentNewList$1 r1 = new androidx.paging.PagingDataDiffer$presentNewList$1
            r1.<init>(r10, r0)
        L_0x0021:
            r13 = r1
            java.lang.Object r0 = r13.result
            java.lang.Object r14 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r15 = 1
            if (r1 == 0) goto L_0x0054
            if (r1 != r15) goto L_0x004c
            boolean r1 = r13.Z$0
            java.lang.Object r2 = r13.L$4
            kotlin.jvm.internal.Ref$BooleanRef r2 = (kotlin.jvm.internal.Ref.BooleanRef) r2
            java.lang.Object r3 = r13.L$3
            androidx.paging.PagePresenter r3 = (androidx.paging.PagePresenter) r3
            java.lang.Object r4 = r13.L$2
            androidx.paging.LoadStates r4 = (androidx.paging.LoadStates) r4
            java.lang.Object r5 = r13.L$1
            androidx.paging.LoadStates r5 = (androidx.paging.LoadStates) r5
            java.lang.Object r6 = r13.L$0
            androidx.paging.PagingDataDiffer r6 = (androidx.paging.PagingDataDiffer) r6
            kotlin.ResultKt.throwOnFailure(r0)
            r11 = r1
            r12 = r5
            goto L_0x00c9
        L_0x004c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0054:
            kotlin.ResultKt.throwOnFailure(r0)
            if (r11 == 0) goto L_0x0068
            if (r12 == 0) goto L_0x005c
            goto L_0x0068
        L_0x005c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot dispatch LoadStates in PagingDataDiffer without source LoadStates set."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0068:
            r0 = 0
            r10.lastAccessedIndexUnfulfilled = r0
            androidx.paging.PagePresenter r9 = new androidx.paging.PagePresenter
            r6 = r22
            r7 = r23
            r8 = r24
            r9.<init>(r6, r7, r8)
            kotlin.jvm.internal.Ref$BooleanRef r5 = new kotlin.jvm.internal.Ref$BooleanRef
            r5.<init>()
            androidx.paging.PagePresenter<T> r0 = r10.presenter
            r16 = r0
            androidx.paging.NullPaddedList r16 = (androidx.paging.NullPaddedList) r16
            r17 = r9
            androidx.paging.NullPaddedList r17 = (androidx.paging.NullPaddedList) r17
            int r4 = r10.lastAccessedIndex
            androidx.paging.PagingDataDiffer$presentNewList$transformedLastAccessedIndex$1 r18 = new androidx.paging.PagingDataDiffer$presentNewList$transformedLastAccessedIndex$1
            r0 = r18
            r1 = r21
            r2 = r9
            r3 = r5
            r19 = r4
            r4 = r28
            r15 = r5
            r5 = r27
            r20 = r14
            r14 = r9
            r9 = r26
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r4 = r18
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r13.L$0 = r10
            r13.L$1 = r12
            r6 = r27
            r13.L$2 = r6
            r13.L$3 = r14
            r13.L$4 = r15
            r13.Z$0 = r11
            r0 = 1
            r13.label = r0
            r0 = r21
            r1 = r16
            r2 = r17
            r3 = r19
            r5 = r13
            java.lang.Object r0 = r0.presentNewList(r1, r2, r3, r4, r5)
            r1 = r20
            if (r0 != r1) goto L_0x00c5
            return r1
        L_0x00c5:
            r4 = r6
            r6 = r10
            r3 = r14
            r2 = r15
        L_0x00c9:
            java.lang.Integer r0 = (java.lang.Integer) r0
            boolean r1 = r2.element
            if (r1 == 0) goto L_0x0101
            if (r11 == 0) goto L_0x00d7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
            r6.dispatchLoadStates$paging_common(r12, r4)
        L_0x00d7:
            if (r0 != 0) goto L_0x00e7
            androidx.paging.HintReceiver r0 = r6.hintReceiver
            if (r0 == 0) goto L_0x00fe
            androidx.paging.ViewportHint$Initial r1 = r3.initializeHint()
            androidx.paging.ViewportHint r1 = (androidx.paging.ViewportHint) r1
            r0.accessHint(r1)
            goto L_0x00fe
        L_0x00e7:
            int r1 = r0.intValue()
            r6.lastAccessedIndex = r1
            androidx.paging.HintReceiver r1 = r6.hintReceiver
            if (r1 == 0) goto L_0x00fe
            int r0 = r0.intValue()
            androidx.paging.ViewportHint$Access r0 = r3.accessHintForPresenterIndex(r0)
            androidx.paging.ViewportHint r0 = (androidx.paging.ViewportHint) r0
            r1.accessHint(r0)
        L_0x00fe:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0101:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Missing call to onListPresentable after new list was presented. If you are seeing\n this exception, it is generally an indication of an issue with Paging.\n Please file a bug so we can fix it at:\n https://issuetracker.google.com/issues/new?component=413106"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataDiffer.presentNewList(java.util.List, int, int, boolean, androidx.paging.LoadStates, androidx.paging.LoadStates, androidx.paging.HintReceiver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void retry() {
        Logger logger = LoggerKt.getLOGGER();
        if (logger != null && logger.isLoggable(3)) {
            logger.log(3, "Retry signal received", (Throwable) null);
        }
        UiReceiver uiReceiver2 = this.uiReceiver;
        if (uiReceiver2 != null) {
            uiReceiver2.retry();
        }
    }

    public final void refresh() {
        Logger logger = LoggerKt.getLOGGER();
        if (logger != null && logger.isLoggable(3)) {
            logger.log(3, "Refresh signal received", (Throwable) null);
        }
        UiReceiver uiReceiver2 = this.uiReceiver;
        if (uiReceiver2 != null) {
            uiReceiver2.refresh();
        }
    }
}
