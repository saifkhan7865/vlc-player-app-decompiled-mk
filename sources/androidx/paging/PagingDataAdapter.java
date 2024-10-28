package androidx.paging;

import androidx.lifecycle.Lifecycle;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
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
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00030\u0005B\u001f\b\u0017\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0017\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fB)\b\u0007\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\r\u0012\b\b\u0002\u0010\u000b\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u001b\u001a\u00020\u00172\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00170\u001dJ\u0014\u0010\u001e\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00170\u001fJ\u0019\u0010 \u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010!\u001a\u00020\"H\u0005¢\u0006\u0002\u0010#J\b\u0010$\u001a\u00020\"H\u0016J\u000e\u0010%\u001a\u00020&2\u0006\u0010!\u001a\u00020\"J\u0019\u0010'\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010(\u001a\u00020\"H\u0007¢\u0006\u0002\u0010#J\u0006\u0010)\u001a\u00020\u0017J\u001a\u0010*\u001a\u00020\u00172\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00170\u001dJ\u0014\u0010+\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00170\u001fJ\u0006\u0010,\u001a\u00020\u0017J\u000e\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u001aJ\u0010\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u000201H\u0016J\f\u00102\u001a\b\u0012\u0004\u0012\u00028\u000003J\u001c\u00104\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u00028\u000008J\u001f\u00104\u001a\u00020\u00172\f\u00107\u001a\b\u0012\u0004\u0012\u00028\u000008H@ø\u0001\u0000¢\u0006\u0002\u00109J\u0012\u0010:\u001a\u00020;2\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=J\u0012\u0010>\u001a\u00020;2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030=J\u001e\u0010@\u001a\u00020;2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030=2\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u000e\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006A"}, d2 = {"Landroidx/paging/PagingDataAdapter;", "T", "", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Lkotlinx/coroutines/CoroutineDispatcher;)V", "workerDispatcher", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)V", "differ", "Landroidx/paging/AsyncPagingDataDiffer;", "loadStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/CombinedLoadStates;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "onPagesUpdatedFlow", "", "getOnPagesUpdatedFlow", "userSetRestorationPolicy", "", "addLoadStateListener", "listener", "Lkotlin/Function1;", "addOnPagesUpdatedListener", "Lkotlin/Function0;", "getItem", "position", "", "(I)Ljava/lang/Object;", "getItemCount", "getItemId", "", "peek", "index", "refresh", "removeLoadStateListener", "removeOnPagesUpdatedListener", "retry", "setHasStableIds", "hasStableIds", "setStateRestorationPolicy", "strategy", "Landroidx/recyclerview/widget/RecyclerView$Adapter$StateRestorationPolicy;", "snapshot", "Landroidx/paging/ItemSnapshotList;", "submitData", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "pagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withLoadStateFooter", "Landroidx/recyclerview/widget/ConcatAdapter;", "footer", "Landroidx/paging/LoadStateAdapter;", "withLoadStateHeader", "header", "withLoadStateHeaderAndFooter", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PagingDataAdapter.kt */
public abstract class PagingDataAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncPagingDataDiffer<T> differ;
    private final Flow<CombinedLoadStates> loadStateFlow;
    private final Flow<Unit> onPagesUpdatedFlow;
    private boolean userSetRestorationPolicy;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PagingDataAdapter(DiffUtil.ItemCallback<T> itemCallback) {
        this((DiffUtil.ItemCallback) itemCallback, (CoroutineContext) null, (CoroutineContext) null, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PagingDataAdapter(DiffUtil.ItemCallback<T> itemCallback, CoroutineContext coroutineContext) {
        this((DiffUtil.ItemCallback) itemCallback, coroutineContext, (CoroutineContext) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(coroutineContext, "mainDispatcher");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PagingDataAdapter(DiffUtil.ItemCallback itemCallback, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, (i & 2) != 0 ? Dispatchers.getMain() : coroutineContext, (i & 4) != 0 ? Dispatchers.getDefault() : coroutineContext2);
    }

    public PagingDataAdapter(DiffUtil.ItemCallback<T> itemCallback, CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(coroutineContext, "mainDispatcher");
        Intrinsics.checkNotNullParameter(coroutineContext2, "workerDispatcher");
        AsyncPagingDataDiffer<T> asyncPagingDataDiffer = new AsyncPagingDataDiffer<>(itemCallback, (ListUpdateCallback) new AdapterListUpdateCallback(this), coroutineContext, coroutineContext2);
        this.differ = asyncPagingDataDiffer;
        super.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT);
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(this) {
            final /* synthetic */ PagingDataAdapter<T, VH> this$0;

            {
                this.this$0 = r1;
            }

            public void onItemRangeInserted(int i, int i2) {
                PagingDataAdapter._init_$considerAllowingStateRestoration(this.this$0);
                this.this$0.unregisterAdapterDataObserver(this);
                super.onItemRangeInserted(i, i2);
            }
        });
        addLoadStateListener(new Function1<CombinedLoadStates, Unit>(this) {
            private boolean ignoreNextEvent = true;
            final /* synthetic */ PagingDataAdapter<T, VH> this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((CombinedLoadStates) obj);
                return Unit.INSTANCE;
            }

            public void invoke(CombinedLoadStates combinedLoadStates) {
                Intrinsics.checkNotNullParameter(combinedLoadStates, "loadStates");
                if (this.ignoreNextEvent) {
                    this.ignoreNextEvent = false;
                } else if (combinedLoadStates.getSource().getRefresh() instanceof LoadState.NotLoading) {
                    PagingDataAdapter._init_$considerAllowingStateRestoration(this.this$0);
                    this.this$0.removeLoadStateListener(this);
                }
            }
        });
        this.loadStateFlow = asyncPagingDataDiffer.getLoadStateFlow();
        this.onPagesUpdatedFlow = asyncPagingDataDiffer.getOnPagesUpdatedFlow();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PagingDataAdapter(DiffUtil.ItemCallback itemCallback, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, (i & 2) != 0 ? Dispatchers.getMain() : coroutineDispatcher);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Superseded by constructors which accept CoroutineContext")
    public /* synthetic */ PagingDataAdapter(DiffUtil.ItemCallback itemCallback, CoroutineDispatcher coroutineDispatcher) {
        this(itemCallback, (CoroutineContext) coroutineDispatcher, (CoroutineContext) Dispatchers.getDefault());
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "mainDispatcher");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PagingDataAdapter(DiffUtil.ItemCallback itemCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, (i & 2) != 0 ? Dispatchers.getMain() : coroutineDispatcher, (i & 4) != 0 ? Dispatchers.getDefault() : coroutineDispatcher2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Superseded by constructors which accept CoroutineContext")
    public /* synthetic */ PagingDataAdapter(DiffUtil.ItemCallback itemCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
        this(itemCallback, (CoroutineContext) coroutineDispatcher, (CoroutineContext) coroutineDispatcher2);
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "mainDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "workerDispatcher");
    }

    public void setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy stateRestorationPolicy) {
        Intrinsics.checkNotNullParameter(stateRestorationPolicy, "strategy");
        this.userSetRestorationPolicy = true;
        super.setStateRestorationPolicy(stateRestorationPolicy);
    }

    /* access modifiers changed from: private */
    public static final <T, VH extends RecyclerView.ViewHolder> void _init_$considerAllowingStateRestoration(PagingDataAdapter<T, VH> pagingDataAdapter) {
        if (pagingDataAdapter.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT && !pagingDataAdapter.userSetRestorationPolicy) {
            pagingDataAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
        }
    }

    public final long getItemId(int i) {
        return super.getItemId(i);
    }

    public final void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Stable ids are unsupported on PagingDataAdapter.");
    }

    public final Object submitData(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        Object submitData = this.differ.submitData(pagingData, continuation);
        return submitData == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? submitData : Unit.INSTANCE;
    }

    public final void submitData(Lifecycle lifecycle, PagingData<T> pagingData) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(pagingData, "pagingData");
        this.differ.submitData(lifecycle, pagingData);
    }

    public final void retry() {
        this.differ.retry();
    }

    public final void refresh() {
        this.differ.refresh();
    }

    /* access modifiers changed from: protected */
    public final T getItem(int i) {
        return this.differ.getItem(i);
    }

    public final T peek(int i) {
        return this.differ.peek(i);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.differ.snapshot();
    }

    public int getItemCount() {
        return this.differ.getItemCount();
    }

    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.loadStateFlow;
    }

    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return this.onPagesUpdatedFlow;
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differ.addLoadStateListener(function1);
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differ.removeLoadStateListener(function1);
    }

    public final void addOnPagesUpdatedListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.differ.addOnPagesUpdatedListener(function0);
    }

    public final void removeOnPagesUpdatedListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.differ.removeOnPagesUpdatedListener(function0);
    }

    public final ConcatAdapter withLoadStateHeader(LoadStateAdapter<?> loadStateAdapter) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "header");
        addLoadStateListener(new PagingDataAdapter$withLoadStateHeader$1(loadStateAdapter));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{loadStateAdapter, this});
    }

    public final ConcatAdapter withLoadStateFooter(LoadStateAdapter<?> loadStateAdapter) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "footer");
        addLoadStateListener(new PagingDataAdapter$withLoadStateFooter$1(loadStateAdapter));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{this, loadStateAdapter});
    }

    public final ConcatAdapter withLoadStateHeaderAndFooter(LoadStateAdapter<?> loadStateAdapter, LoadStateAdapter<?> loadStateAdapter2) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "header");
        Intrinsics.checkNotNullParameter(loadStateAdapter2, "footer");
        addLoadStateListener(new PagingDataAdapter$withLoadStateHeaderAndFooter$1(loadStateAdapter, loadStateAdapter2));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{loadStateAdapter, this, loadStateAdapter2});
    }
}
