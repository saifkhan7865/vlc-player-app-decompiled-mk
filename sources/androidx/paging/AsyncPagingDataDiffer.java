package androidx.paging;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u000f\u0018\u0000 A*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001AB'\b\u0017\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB1\b\u0017\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0002\u0010\u000bB1\b\u0007\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\f\u0012\b\b\u0002\u0010\n\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u001a\u0010,\u001a\u00020(2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020(0.J\u0014\u0010/\u001a\u00020(2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020(00J\u0019\u00101\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u00102\u001a\u00020\u001fH\u0007¢\u0006\u0002\u00103J\u0019\u00104\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u00102\u001a\u00020\u001fH\u0007¢\u0006\u0002\u00103J\u0006\u00105\u001a\u00020(J\u001a\u00106\u001a\u00020(2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020(0.J\u0014\u00107\u001a\u00020(2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020(00J\u0006\u00108\u001a\u00020(J\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000:J\u001c\u0010;\u001a\u00020(2\u0006\u0010<\u001a\u00020=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?J\u001f\u0010;\u001a\u00020(2\f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000?H@ø\u0001\u0000¢\u0006\u0002\u0010@R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010\u0007\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020(0#¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R\u000e\u0010*\u001a\u00020+X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006B"}, d2 = {"Landroidx/paging/AsyncPagingDataDiffer;", "T", "", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "updateCallback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Landroidx/recyclerview/widget/ListUpdateCallback;Lkotlinx/coroutines/CoroutineDispatcher;)V", "workerDispatcher", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Landroidx/recyclerview/widget/ListUpdateCallback;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Landroidx/recyclerview/widget/ListUpdateCallback;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)V", "differBase", "androidx/paging/AsyncPagingDataDiffer$differBase$1", "Landroidx/paging/AsyncPagingDataDiffer$differBase$1;", "differCallback", "Landroidx/paging/DifferCallback;", "getDifferCallback$paging_runtime_release$annotations", "()V", "getDifferCallback$paging_runtime_release", "()Landroidx/paging/DifferCallback;", "inGetItem", "", "getInGetItem$paging_runtime_release$annotations", "getInGetItem$paging_runtime_release", "()Z", "setInGetItem$paging_runtime_release", "(Z)V", "itemCount", "", "getItemCount", "()I", "loadStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/CombinedLoadStates;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "onPagesUpdatedFlow", "", "getOnPagesUpdatedFlow", "submitDataId", "Ljava/util/concurrent/atomic/AtomicInteger;", "addLoadStateListener", "listener", "Lkotlin/Function1;", "addOnPagesUpdatedListener", "Lkotlin/Function0;", "getItem", "index", "(I)Ljava/lang/Object;", "peek", "refresh", "removeLoadStateListener", "removeOnPagesUpdatedListener", "retry", "snapshot", "Landroidx/paging/ItemSnapshotList;", "submitData", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "pagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AsyncPagingDataDiffer.kt */
public final class AsyncPagingDataDiffer<T> {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final DiffUtil.ItemCallback<T> diffCallback;
    /* access modifiers changed from: private */
    public final AsyncPagingDataDiffer$differBase$1 differBase;
    private final DifferCallback differCallback;
    private boolean inGetItem;
    private final Flow<CombinedLoadStates> loadStateFlow;
    private final CoroutineContext mainDispatcher;
    private final Flow<Unit> onPagesUpdatedFlow;
    /* access modifiers changed from: private */
    public final AtomicInteger submitDataId;
    /* access modifiers changed from: private */
    public final ListUpdateCallback updateCallback;
    /* access modifiers changed from: private */
    public final CoroutineContext workerDispatcher;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> itemCallback, ListUpdateCallback listUpdateCallback) {
        this((DiffUtil.ItemCallback) itemCallback, listUpdateCallback, (CoroutineContext) null, (CoroutineContext) null, 12, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(listUpdateCallback, "updateCallback");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> itemCallback, ListUpdateCallback listUpdateCallback, CoroutineContext coroutineContext) {
        this((DiffUtil.ItemCallback) itemCallback, listUpdateCallback, coroutineContext, (CoroutineContext) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(listUpdateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(coroutineContext, "mainDispatcher");
    }

    public static /* synthetic */ void getDifferCallback$paging_runtime_release$annotations() {
    }

    public static /* synthetic */ void getInGetItem$paging_runtime_release$annotations() {
    }

    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> itemCallback, ListUpdateCallback listUpdateCallback, CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(listUpdateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(coroutineContext, "mainDispatcher");
        Intrinsics.checkNotNullParameter(coroutineContext2, "workerDispatcher");
        this.diffCallback = itemCallback;
        this.updateCallback = listUpdateCallback;
        this.mainDispatcher = coroutineContext;
        this.workerDispatcher = coroutineContext2;
        DifferCallback asyncPagingDataDiffer$differCallback$1 = new AsyncPagingDataDiffer$differCallback$1(this);
        this.differCallback = asyncPagingDataDiffer$differCallback$1;
        AsyncPagingDataDiffer$differBase$1 asyncPagingDataDiffer$differBase$1 = new AsyncPagingDataDiffer$differBase$1(this, asyncPagingDataDiffer$differCallback$1, coroutineContext);
        this.differBase = asyncPagingDataDiffer$differBase$1;
        this.submitDataId = new AtomicInteger(0);
        this.loadStateFlow = FlowKt.filterNotNull(asyncPagingDataDiffer$differBase$1.getLoadStateFlow());
        this.onPagesUpdatedFlow = asyncPagingDataDiffer$differBase$1.getOnPagesUpdatedFlow();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, listUpdateCallback, (i & 4) != 0 ? Dispatchers.getMain() : coroutineContext, (i & 8) != 0 ? Dispatchers.getDefault() : coroutineContext2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, listUpdateCallback, (i & 4) != 0 ? Dispatchers.getMain() : coroutineDispatcher);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Superseded by constructors which accept CoroutineContext")
    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher) {
        this(itemCallback, listUpdateCallback, (CoroutineContext) coroutineDispatcher, (CoroutineContext) Dispatchers.getDefault());
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(listUpdateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "mainDispatcher");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, listUpdateCallback, (i & 4) != 0 ? Dispatchers.getMain() : coroutineDispatcher, (i & 8) != 0 ? Dispatchers.getDefault() : coroutineDispatcher2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Superseded by constructors which accept CoroutineContext")
    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
        this(itemCallback, listUpdateCallback, (CoroutineContext) coroutineDispatcher, (CoroutineContext) coroutineDispatcher2);
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(listUpdateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "mainDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "workerDispatcher");
    }

    public final DifferCallback getDifferCallback$paging_runtime_release() {
        return this.differCallback;
    }

    public final boolean getInGetItem$paging_runtime_release() {
        return this.inGetItem;
    }

    public final void setInGetItem$paging_runtime_release(boolean z) {
        this.inGetItem = z;
    }

    public final Object submitData(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        this.submitDataId.incrementAndGet();
        Object collectFrom = this.differBase.collectFrom(pagingData, continuation);
        return collectFrom == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collectFrom : Unit.INSTANCE;
    }

    public final void submitData(Lifecycle lifecycle, PagingData<T> pagingData) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(pagingData, "pagingData");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleKt.getCoroutineScope(lifecycle), (CoroutineContext) null, (CoroutineStart) null, new AsyncPagingDataDiffer$submitData$2(this, this.submitDataId.incrementAndGet(), pagingData, (Continuation<? super AsyncPagingDataDiffer$submitData$2>) null), 3, (Object) null);
    }

    public final void retry() {
        this.differBase.retry();
    }

    public final void refresh() {
        this.differBase.refresh();
    }

    /* JADX INFO: finally extract failed */
    public final T getItem(int i) {
        try {
            this.inGetItem = true;
            T t = this.differBase.get(i);
            this.inGetItem = false;
            return t;
        } catch (Throwable th) {
            this.inGetItem = false;
            throw th;
        }
    }

    public final T peek(int i) {
        return this.differBase.peek(i);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.differBase.snapshot();
    }

    public final int getItemCount() {
        return this.differBase.getSize();
    }

    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.loadStateFlow;
    }

    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return this.onPagesUpdatedFlow;
    }

    public final void addOnPagesUpdatedListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.differBase.addOnPagesUpdatedListener(function0);
    }

    public final void removeOnPagesUpdatedListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.differBase.removeOnPagesUpdatedListener(function0);
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differBase.addLoadStateListener(function1);
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differBase.removeLoadStateListener(function1);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/paging/AsyncPagingDataDiffer$Companion;", "", "()V", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AsyncPagingDataDiffer.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Logger logger = LoggerKt.getLOGGER();
        if (logger == null) {
            logger = new Logger() {
                public boolean isLoggable(int i) {
                    return Log.isLoggable(LoggerKt.LOG_TAG, i);
                }

                public void log(int i, String str, Throwable th) {
                    Intrinsics.checkNotNullParameter(str, "message");
                    if (i == 2) {
                        Log.v(LoggerKt.LOG_TAG, str, th);
                    } else if (i == 3) {
                        Log.d(LoggerKt.LOG_TAG, str, th);
                    } else {
                        throw new IllegalArgumentException("debug level " + i + " is requested but Paging only supports default logging for level 2 (DEBUG) or level 3 (VERBOSE)");
                    }
                }
            };
        }
        LoggerKt.setLOGGER(logger);
    }
}
