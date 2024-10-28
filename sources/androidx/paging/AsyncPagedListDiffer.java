package androidx.paging;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.paging.LoadState;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002_`B!\b\u0017\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007B\u001d\b\u0017\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0002\u0010\fJ\"\u0010H\u001a\u00020(2\u0018\u0010I\u001a\u0014\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020(0+H\u0016J0\u0010J\u001a\u00020(2(\u0010K\u001a$\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012\u0012\u0004\u0012\u00020(0+J\u0016\u0010J\u001a\u00020(2\f\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0016J\u0017\u0010L\u001a\u0004\u0018\u00018\u00002\u0006\u0010M\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010NJK\u0010O\u001a\u00020(2\f\u0010P\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\f\u0010Q\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020\u00172\b\u0010W\u001a\u0004\u0018\u00010XH\u0000¢\u0006\u0002\bYJ2\u0010Z\u001a\u00020(2\u000e\u0010[\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00122\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00122\b\u0010W\u001a\u0004\u0018\u00010XH\u0002J\"\u0010\\\u001a\u00020(2\u0018\u0010I\u001a\u0014\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020(0+H\u0016J0\u0010]\u001a\u00020(2(\u0010K\u001a$\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012\u0012\u0004\u0012\u00020(0+J\u0016\u0010]\u001a\u00020(2\f\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0016J\u0018\u0010^\u001a\u00020(2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012H\u0016J\"\u0010^\u001a\u00020(2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00122\b\u0010W\u001a\u0004\u0018\u00010XH\u0016R \u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00128VX\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R(\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001c0\u001b8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001e\u0010\u001fR>\u0010 \u001a2\u0012\u0013\u0012\u00110\"¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0013\u0012\u00110&¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b('\u0012\u0004\u0012\u00020(0!X\u0004¢\u0006\u0002\n\u0000R,\u0010)\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020(0+0*X\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/X\u0004¢\u0006\b\n\u0000\u0012\u0004\b0\u0010\u000eR\u001a\u00101\u001a\u000202X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R \u00107\u001a\u00020\u0017X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b8\u0010\u000e\u001a\u0004\b9\u0010\u0019\"\u0004\b:\u0010;R\u001c\u0010<\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012X\u000e¢\u0006\b\n\u0000\u0012\u0004\b=\u0010\u000eR\u0014\u0010>\u001a\u00020?X\u0004¢\u0006\b\n\u0000\u0012\u0004\b@\u0010\u000eR\u001c\u0010A\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012X\u000e¢\u0006\b\n\u0000\u0012\u0004\bB\u0010\u000eR\u001a\u0010C\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010G¨\u0006a"}, d2 = {"Landroidx/paging/AsyncPagedListDiffer;", "T", "", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "(Landroidx/recyclerview/widget/RecyclerView$Adapter;Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "listUpdateCallback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "config", "Landroidx/recyclerview/widget/AsyncDifferConfig;", "(Landroidx/recyclerview/widget/ListUpdateCallback;Landroidx/recyclerview/widget/AsyncDifferConfig;)V", "getConfig$paging_runtime_release$annotations", "()V", "getConfig$paging_runtime_release", "()Landroidx/recyclerview/widget/AsyncDifferConfig;", "currentList", "Landroidx/paging/PagedList;", "getCurrentList$annotations", "getCurrentList", "()Landroidx/paging/PagedList;", "itemCount", "", "getItemCount", "()I", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/paging/AsyncPagedListDiffer$PagedListListener;", "getListeners$paging_runtime_release$annotations", "getListeners$paging_runtime_release", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "loadStateListener", "Lkotlin/reflect/KFunction2;", "Landroidx/paging/LoadType;", "Lkotlin/ParameterName;", "name", "type", "Landroidx/paging/LoadState;", "state", "", "loadStateListeners", "", "Lkotlin/Function2;", "getLoadStateListeners$paging_runtime_release", "()Ljava/util/List;", "loadStateManager", "Landroidx/paging/PagedList$LoadStateManager;", "getLoadStateManager$annotations", "mainThreadExecutor", "Ljava/util/concurrent/Executor;", "getMainThreadExecutor$paging_runtime_release", "()Ljava/util/concurrent/Executor;", "setMainThreadExecutor$paging_runtime_release", "(Ljava/util/concurrent/Executor;)V", "maxScheduledGeneration", "getMaxScheduledGeneration$paging_runtime_release$annotations", "getMaxScheduledGeneration$paging_runtime_release", "setMaxScheduledGeneration$paging_runtime_release", "(I)V", "pagedList", "getPagedList$annotations", "pagedListCallback", "Landroidx/paging/PagedList$Callback;", "getPagedListCallback$annotations", "snapshot", "getSnapshot$annotations", "updateCallback", "getUpdateCallback$paging_runtime_release", "()Landroidx/recyclerview/widget/ListUpdateCallback;", "setUpdateCallback$paging_runtime_release", "(Landroidx/recyclerview/widget/ListUpdateCallback;)V", "addLoadStateListener", "listener", "addPagedListListener", "callback", "getItem", "index", "(I)Ljava/lang/Object;", "latchPagedList", "newList", "diffSnapshot", "diffResult", "Landroidx/paging/NullPaddedDiffResult;", "recordingCallback", "Landroidx/paging/RecordingCallback;", "lastAccessIndex", "commitCallback", "Ljava/lang/Runnable;", "latchPagedList$paging_runtime_release", "onCurrentListChanged", "previousList", "removeLoadStateListener", "removePagedListListener", "submitList", "OnCurrentListChangedWrapper", "PagedListListener", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "AsyncPagedListDiffer is deprecated and has been replaced by AsyncPagingDataDiffer", replaceWith = @ReplaceWith(expression = "AsyncPagingDataDiffer<T>", imports = {"androidx.paging.AsyncPagingDataDiffer"}))
/* compiled from: AsyncPagedListDiffer.kt */
public class AsyncPagedListDiffer<T> {
    private final AsyncDifferConfig<T> config;
    private final CopyOnWriteArrayList<PagedListListener<T>> listeners = new CopyOnWriteArrayList<>();
    private final KFunction<Unit> loadStateListener;
    private final List<Function2<LoadType, LoadState, Unit>> loadStateListeners;
    private final PagedList.LoadStateManager loadStateManager;
    private Executor mainThreadExecutor;
    private int maxScheduledGeneration;
    private PagedList<T> pagedList;
    private final PagedList.Callback pagedListCallback;
    private PagedList<T> snapshot;
    public ListUpdateCallback updateCallback;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0002J(\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006H&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/paging/AsyncPagedListDiffer$PagedListListener;", "T", "", "onCurrentListChanged", "", "previousList", "Landroidx/paging/PagedList;", "currentList", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData")
    /* compiled from: AsyncPagedListDiffer.kt */
    public interface PagedListListener<T> {
        void onCurrentListChanged(PagedList<T> pagedList, PagedList<T> pagedList2);
    }

    public static /* synthetic */ void getConfig$paging_runtime_release$annotations() {
    }

    public static /* synthetic */ void getCurrentList$annotations() {
    }

    public static /* synthetic */ void getListeners$paging_runtime_release$annotations() {
    }

    private static /* synthetic */ void getLoadStateManager$annotations() {
    }

    public static /* synthetic */ void getMaxScheduledGeneration$paging_runtime_release$annotations() {
    }

    private static /* synthetic */ void getPagedList$annotations() {
    }

    private static /* synthetic */ void getPagedListCallback$annotations() {
    }

    private static /* synthetic */ void getSnapshot$annotations() {
    }

    public final ListUpdateCallback getUpdateCallback$paging_runtime_release() {
        ListUpdateCallback listUpdateCallback = this.updateCallback;
        if (listUpdateCallback != null) {
            return listUpdateCallback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("updateCallback");
        return null;
    }

    public final void setUpdateCallback$paging_runtime_release(ListUpdateCallback listUpdateCallback) {
        Intrinsics.checkNotNullParameter(listUpdateCallback, "<set-?>");
        this.updateCallback = listUpdateCallback;
    }

    public final AsyncDifferConfig<T> getConfig$paging_runtime_release() {
        return this.config;
    }

    public final Executor getMainThreadExecutor$paging_runtime_release() {
        return this.mainThreadExecutor;
    }

    public final void setMainThreadExecutor$paging_runtime_release(Executor executor) {
        Intrinsics.checkNotNullParameter(executor, "<set-?>");
        this.mainThreadExecutor = executor;
    }

    public final CopyOnWriteArrayList<PagedListListener<T>> getListeners$paging_runtime_release() {
        return this.listeners;
    }

    public final int getMaxScheduledGeneration$paging_runtime_release() {
        return this.maxScheduledGeneration;
    }

    public final void setMaxScheduledGeneration$paging_runtime_release(int i) {
        this.maxScheduledGeneration = i;
    }

    public final List<Function2<LoadType, LoadState, Unit>> getLoadStateListeners$paging_runtime_release() {
        return this.loadStateListeners;
    }

    public int getItemCount() {
        PagedList currentList = getCurrentList();
        if (currentList != null) {
            return currentList.size();
        }
        return 0;
    }

    public PagedList<T> getCurrentList() {
        PagedList<T> pagedList2 = this.snapshot;
        return pagedList2 == null ? this.pagedList : pagedList2;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B/\u0012(\u0010\u0004\u001a$\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ(\u0010\u000b\u001a\u00020\u00072\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00062\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006H\u0016R3\u0010\u0004\u001a$\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Landroidx/paging/AsyncPagedListDiffer$OnCurrentListChangedWrapper;", "T", "", "Landroidx/paging/AsyncPagedListDiffer$PagedListListener;", "callback", "Lkotlin/Function2;", "Landroidx/paging/PagedList;", "", "(Lkotlin/jvm/functions/Function2;)V", "getCallback", "()Lkotlin/jvm/functions/Function2;", "onCurrentListChanged", "previousList", "currentList", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AsyncPagedListDiffer.kt */
    private static final class OnCurrentListChangedWrapper<T> implements PagedListListener<T> {
        private final Function2<PagedList<T>, PagedList<T>, Unit> callback;

        public OnCurrentListChangedWrapper(Function2<? super PagedList<T>, ? super PagedList<T>, Unit> function2) {
            Intrinsics.checkNotNullParameter(function2, "callback");
            this.callback = function2;
        }

        public final Function2<PagedList<T>, PagedList<T>, Unit> getCallback() {
            return this.callback;
        }

        public void onCurrentListChanged(PagedList<T> pagedList, PagedList<T> pagedList2) {
            this.callback.invoke(pagedList, pagedList2);
        }
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "AsyncPagingDataDiffer(\n                Dispatchers.Main,\n                Dispatchers.IO,\n                diffCallback,\n                listUpdateCallback\n            )", imports = {"androidx.paging.AsyncPagingDataDiffer", "kotlinx.coroutines.Dispatchers"}))
    public AsyncPagedListDiffer(RecyclerView.Adapter<?> adapter, DiffUtil.ItemCallback<T> itemCallback) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Executor mainThreadExecutor2 = ArchTaskExecutor.getMainThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(mainThreadExecutor2, "getMainThreadExecutor()");
        this.mainThreadExecutor = mainThreadExecutor2;
        PagedList.LoadStateManager asyncPagedListDiffer$loadStateManager$1 = new AsyncPagedListDiffer$loadStateManager$1(this);
        this.loadStateManager = asyncPagedListDiffer$loadStateManager$1;
        this.loadStateListener = new AsyncPagedListDiffer$loadStateListener$1(asyncPagedListDiffer$loadStateManager$1);
        this.loadStateListeners = new CopyOnWriteArrayList();
        this.pagedListCallback = new AsyncPagedListDiffer$pagedListCallback$1(this);
        setUpdateCallback$paging_runtime_release(new AdapterListUpdateCallback(adapter));
        AsyncDifferConfig<T> build = new AsyncDifferConfig.Builder(itemCallback).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(diffCallback).build()");
        this.config = build;
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "AsyncPagingDataDiffer(\n                Dispatchers.Main,\n                Dispatchers.IO,\n                config.diffCallback,\n                listUpdateCallback\n            )", imports = {"androidx.paging.AsyncPagingDataDiffer", "kotlinx.coroutines.Dispatchers"}))
    public AsyncPagedListDiffer(ListUpdateCallback listUpdateCallback, AsyncDifferConfig<T> asyncDifferConfig) {
        Intrinsics.checkNotNullParameter(listUpdateCallback, "listUpdateCallback");
        Intrinsics.checkNotNullParameter(asyncDifferConfig, "config");
        Executor mainThreadExecutor2 = ArchTaskExecutor.getMainThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(mainThreadExecutor2, "getMainThreadExecutor()");
        this.mainThreadExecutor = mainThreadExecutor2;
        PagedList.LoadStateManager asyncPagedListDiffer$loadStateManager$1 = new AsyncPagedListDiffer$loadStateManager$1(this);
        this.loadStateManager = asyncPagedListDiffer$loadStateManager$1;
        this.loadStateListener = new AsyncPagedListDiffer$loadStateListener$1(asyncPagedListDiffer$loadStateManager$1);
        this.loadStateListeners = new CopyOnWriteArrayList();
        this.pagedListCallback = new AsyncPagedListDiffer$pagedListCallback$1(this);
        setUpdateCallback$paging_runtime_release(listUpdateCallback);
        this.config = asyncDifferConfig;
    }

    public T getItem(int i) {
        PagedList<T> pagedList2 = this.snapshot;
        PagedList<T> pagedList3 = this.pagedList;
        if (pagedList2 != null) {
            return pagedList2.get(i);
        }
        if (pagedList3 != null) {
            pagedList3.loadAround(i);
            return pagedList3.get(i);
        }
        throw new IndexOutOfBoundsException("Item count is zero, getItem() call is invalid");
    }

    public void submitList(PagedList<T> pagedList2) {
        submitList(pagedList2, (Runnable) null);
    }

    public void submitList(PagedList<T> pagedList2, Runnable runnable) {
        int i = this.maxScheduledGeneration + 1;
        this.maxScheduledGeneration = i;
        PagedList<T> pagedList3 = this.pagedList;
        if (pagedList2 == pagedList3) {
            if (runnable != null) {
                runnable.run();
            }
        } else if (pagedList3 == null || !(pagedList2 instanceof InitialPagedList)) {
            PagedList currentList = getCurrentList();
            if (pagedList2 == null) {
                int itemCount = getItemCount();
                if (pagedList3 != null) {
                    pagedList3.removeWeakCallback(this.pagedListCallback);
                    pagedList3.removeWeakLoadStateListener((Function2) this.loadStateListener);
                    this.pagedList = null;
                } else if (this.snapshot != null) {
                    this.snapshot = null;
                }
                getUpdateCallback$paging_runtime_release().onRemoved(0, itemCount);
                onCurrentListChanged(currentList, (PagedList) null, runnable);
            } else if (getCurrentList() == null) {
                this.pagedList = pagedList2;
                pagedList2.addWeakLoadStateListener((Function2) this.loadStateListener);
                pagedList2.addWeakCallback(this.pagedListCallback);
                getUpdateCallback$paging_runtime_release().onInserted(0, pagedList2.size());
                onCurrentListChanged((PagedList) null, pagedList2, runnable);
            } else {
                PagedList<T> pagedList4 = this.pagedList;
                if (pagedList4 != null) {
                    pagedList4.removeWeakCallback(this.pagedListCallback);
                    pagedList4.removeWeakLoadStateListener((Function2) this.loadStateListener);
                    List<T> snapshot2 = pagedList4.snapshot();
                    Intrinsics.checkNotNull(snapshot2, "null cannot be cast to non-null type androidx.paging.PagedList<T of androidx.paging.AsyncPagedListDiffer.submitList$lambda$0>");
                    this.snapshot = (PagedList) snapshot2;
                    this.pagedList = null;
                }
                PagedList<T> pagedList5 = this.snapshot;
                if (pagedList5 == null || this.pagedList != null) {
                    throw new IllegalStateException("must be in snapshot state to diff");
                }
                List<T> snapshot3 = pagedList2.snapshot();
                Intrinsics.checkNotNull(snapshot3, "null cannot be cast to non-null type androidx.paging.PagedList<T of androidx.paging.AsyncPagedListDiffer>");
                RecordingCallback recordingCallback = new RecordingCallback();
                pagedList2.addWeakCallback(recordingCallback);
                this.config.getBackgroundThreadExecutor().execute(new AsyncPagedListDiffer$$ExternalSyntheticLambda1(pagedList5, (PagedList) snapshot3, this, i, pagedList2, recordingCallback, runnable));
            }
        } else {
            pagedList3.removeWeakCallback(this.pagedListCallback);
            pagedList3.removeWeakLoadStateListener((Function2) this.loadStateListener);
            this.loadStateManager.setState(LoadType.REFRESH, LoadState.Loading.INSTANCE);
            this.loadStateManager.setState(LoadType.PREPEND, new LoadState.NotLoading(false));
            this.loadStateManager.setState(LoadType.APPEND, new LoadState.NotLoading(false));
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void submitList$lambda$2(PagedList pagedList2, PagedList pagedList3, AsyncPagedListDiffer asyncPagedListDiffer, int i, PagedList pagedList4, RecordingCallback recordingCallback, Runnable runnable) {
        AsyncPagedListDiffer asyncPagedListDiffer2 = asyncPagedListDiffer;
        Intrinsics.checkNotNullParameter(pagedList3, "$newSnapshot");
        Intrinsics.checkNotNullParameter(asyncPagedListDiffer, "this$0");
        RecordingCallback recordingCallback2 = recordingCallback;
        Intrinsics.checkNotNullParameter(recordingCallback2, "$recordingCallback");
        NullPaddedList nullPaddedList = pagedList2.getNullPaddedList();
        NullPaddedList nullPaddedList2 = pagedList3.getNullPaddedList();
        DiffUtil.ItemCallback<T> diffCallback = asyncPagedListDiffer2.config.getDiffCallback();
        Intrinsics.checkNotNullExpressionValue(diffCallback, "config.diffCallback");
        NullPaddedDiffResult computeDiff = NullPaddedListDiffHelperKt.computeDiff(nullPaddedList, nullPaddedList2, diffCallback);
        asyncPagedListDiffer2.mainThreadExecutor.execute(new AsyncPagedListDiffer$$ExternalSyntheticLambda0(asyncPagedListDiffer2, i, pagedList4, pagedList3, computeDiff, recordingCallback2, pagedList2, runnable));
    }

    /* access modifiers changed from: private */
    public static final void submitList$lambda$2$lambda$1(AsyncPagedListDiffer asyncPagedListDiffer, int i, PagedList pagedList2, PagedList pagedList3, NullPaddedDiffResult nullPaddedDiffResult, RecordingCallback recordingCallback, PagedList pagedList4, Runnable runnable) {
        Intrinsics.checkNotNullParameter(asyncPagedListDiffer, "this$0");
        Intrinsics.checkNotNullParameter(pagedList3, "$newSnapshot");
        Intrinsics.checkNotNullParameter(nullPaddedDiffResult, "$result");
        Intrinsics.checkNotNullParameter(recordingCallback, "$recordingCallback");
        if (asyncPagedListDiffer.maxScheduledGeneration == i) {
            asyncPagedListDiffer.latchPagedList$paging_runtime_release(pagedList2, pagedList3, nullPaddedDiffResult, recordingCallback, pagedList4.lastLoad(), runnable);
        }
    }

    public final void latchPagedList$paging_runtime_release(PagedList<T> pagedList2, PagedList<T> pagedList3, NullPaddedDiffResult nullPaddedDiffResult, RecordingCallback recordingCallback, int i, Runnable runnable) {
        Intrinsics.checkNotNullParameter(pagedList2, "newList");
        Intrinsics.checkNotNullParameter(pagedList3, "diffSnapshot");
        Intrinsics.checkNotNullParameter(nullPaddedDiffResult, "diffResult");
        Intrinsics.checkNotNullParameter(recordingCallback, "recordingCallback");
        PagedList<T> pagedList4 = this.snapshot;
        if (pagedList4 == null || this.pagedList != null) {
            throw new IllegalStateException("must be in snapshot state to apply diff");
        }
        this.pagedList = pagedList2;
        pagedList2.addWeakLoadStateListener((Function2) this.loadStateListener);
        this.snapshot = null;
        NullPaddedListDiffHelperKt.dispatchDiff(pagedList4.getNullPaddedList(), getUpdateCallback$paging_runtime_release(), pagedList3.getNullPaddedList(), nullPaddedDiffResult);
        recordingCallback.dispatchRecordingTo(this.pagedListCallback);
        pagedList2.addWeakCallback(this.pagedListCallback);
        if (!pagedList2.isEmpty()) {
            pagedList2.loadAround(RangesKt.coerceIn(NullPaddedListDiffHelperKt.transformAnchorIndex(pagedList4.getNullPaddedList(), nullPaddedDiffResult, pagedList3.getNullPaddedList(), i), 0, pagedList2.size() - 1));
        }
        onCurrentListChanged(pagedList4, this.pagedList, runnable);
    }

    private final void onCurrentListChanged(PagedList<T> pagedList2, PagedList<T> pagedList3, Runnable runnable) {
        for (PagedListListener onCurrentListChanged : this.listeners) {
            onCurrentListChanged.onCurrentListChanged(pagedList2, pagedList3);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public void addPagedListListener(PagedListListener<T> pagedListListener) {
        Intrinsics.checkNotNullParameter(pagedListListener, "listener");
        this.listeners.add(pagedListListener);
    }

    public final void addPagedListListener(Function2<? super PagedList<T>, ? super PagedList<T>, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "callback");
        this.listeners.add(new OnCurrentListChangedWrapper(function2));
    }

    public void removePagedListListener(PagedListListener<T> pagedListListener) {
        Intrinsics.checkNotNullParameter(pagedListListener, "listener");
        this.listeners.remove(pagedListListener);
    }

    public final void removePagedListListener(Function2<? super PagedList<T>, ? super PagedList<T>, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "callback");
        CollectionsKt.removeAll(this.listeners, new AsyncPagedListDiffer$removePagedListListener$1(function2));
    }

    public void addLoadStateListener(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "listener");
        PagedList<T> pagedList2 = this.pagedList;
        if (pagedList2 != null) {
            pagedList2.addWeakLoadStateListener(function2);
        } else {
            this.loadStateManager.dispatchCurrentLoadState(function2);
        }
        this.loadStateListeners.add(function2);
    }

    public void removeLoadStateListener(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "listener");
        this.loadStateListeners.remove(function2);
        PagedList<T> pagedList2 = this.pagedList;
        if (pagedList2 != null) {
            pagedList2.removeWeakLoadStateListener(function2);
        }
    }
}
