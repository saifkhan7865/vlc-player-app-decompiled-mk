package androidx.paging;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.GlobalScope;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B#\b\u0017\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB#\b\u0017\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0017\u0012\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e0\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000fB)\b\u0017\u0012\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e0\r\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u0010J\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001f0\u001eJ\"\u0010 \u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0012J\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010#\u001a\u00020$J!\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010&\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010'R\u001c\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0012X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0014R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\"\u0010\f\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e\u0018\u00010\rX\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/paging/LivePagedListBuilder;", "Key", "", "Value", "dataSourceFactory", "Landroidx/paging/DataSource$Factory;", "config", "Landroidx/paging/PagedList$Config;", "(Landroidx/paging/DataSource$Factory;Landroidx/paging/PagedList$Config;)V", "pageSize", "", "(Landroidx/paging/DataSource$Factory;I)V", "pagingSourceFactory", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "(Lkotlin/jvm/functions/Function0;Landroidx/paging/PagedList$Config;)V", "(Lkotlin/jvm/functions/Function0;I)V", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "getBoundaryCallback$annotations", "()V", "getConfig$annotations", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$annotations", "fetchDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "initialLoadKey", "Ljava/lang/Object;", "build", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "setBoundaryCallback", "setCoroutineScope", "setFetchExecutor", "fetchExecutor", "Ljava/util/concurrent/Executor;", "setInitialLoadKey", "key", "(Ljava/lang/Object;)Landroidx/paging/LivePagedListBuilder;", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "PagedList is deprecated and has been replaced by PagingData")
/* compiled from: LivePagedListBuilder.kt */
public final class LivePagedListBuilder<Key, Value> {
    private PagedList.BoundaryCallback<Value> boundaryCallback;
    private final PagedList.Config config;
    private CoroutineScope coroutineScope;
    private final DataSource.Factory<Key, Value> dataSourceFactory;
    private CoroutineDispatcher fetchDispatcher;
    private Key initialLoadKey;
    private final Function0<PagingSource<Key, Value>> pagingSourceFactory;

    private static /* synthetic */ void getBoundaryCallback$annotations() {
    }

    private static /* synthetic */ void getConfig$annotations() {
    }

    private static /* synthetic */ void getCoroutineScope$annotations() {
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(\n                    config.pageSize,\n                    config.prefetchDistance,\n                    config.enablePlaceholders,\n                    config.initialLoadSizeHint,\n                    config.maxSize\n                ),\n                initialLoadKey,\n                dataSourceFactory.asPagingSourceFactory(Dispatchers.IO)\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData", "kotlinx.coroutines.Dispatchers"}))
    public LivePagedListBuilder(DataSource.Factory<Key, Value> factory, PagedList.Config config2) {
        Intrinsics.checkNotNullParameter(factory, "dataSourceFactory");
        Intrinsics.checkNotNullParameter(config2, "config");
        this.coroutineScope = GlobalScope.INSTANCE;
        Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "getIOThreadExecutor()");
        this.fetchDispatcher = ExecutorsKt.from(iOThreadExecutor);
        this.pagingSourceFactory = null;
        this.dataSourceFactory = factory;
        this.config = config2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(pageSize),\n                initialLoadKey,\n                dataSourceFactory.asPagingSourceFactory(Dispatchers.IO)\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData", "kotlinx.coroutines.Dispatchers"}))
    public LivePagedListBuilder(DataSource.Factory<Key, Value> factory, int i) {
        this(factory, new PagedList.Config.Builder().setPageSize(i).build());
        Intrinsics.checkNotNullParameter(factory, "dataSourceFactory");
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(\n                    config.pageSize,\n                    config.prefetchDistance,\n                    config.enablePlaceholders,\n                    config.initialLoadSizeHint,\n                    config.maxSize\n                ),\n                initialLoadKey,\n                this\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData"}))
    public LivePagedListBuilder(Function0<? extends PagingSource<Key, Value>> function0, PagedList.Config config2) {
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
        Intrinsics.checkNotNullParameter(config2, "config");
        this.coroutineScope = GlobalScope.INSTANCE;
        Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "getIOThreadExecutor()");
        this.fetchDispatcher = ExecutorsKt.from(iOThreadExecutor);
        this.pagingSourceFactory = function0;
        this.dataSourceFactory = null;
        this.config = config2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(pageSize),\n                initialLoadKey,\n                this\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData"}))
    public LivePagedListBuilder(Function0<? extends PagingSource<Key, Value>> function0, int i) {
        this(function0, new PagedList.Config.Builder().setPageSize(i).build());
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
    }

    public final LivePagedListBuilder<Key, Value> setCoroutineScope(CoroutineScope coroutineScope2) {
        Intrinsics.checkNotNullParameter(coroutineScope2, "coroutineScope");
        LivePagedListBuilder livePagedListBuilder = this;
        this.coroutineScope = coroutineScope2;
        return this;
    }

    public final LivePagedListBuilder<Key, Value> setInitialLoadKey(Key key) {
        LivePagedListBuilder livePagedListBuilder = this;
        this.initialLoadKey = key;
        return this;
    }

    public final LivePagedListBuilder<Key, Value> setBoundaryCallback(PagedList.BoundaryCallback<Value> boundaryCallback2) {
        LivePagedListBuilder livePagedListBuilder = this;
        this.boundaryCallback = boundaryCallback2;
        return this;
    }

    public final LivePagedListBuilder<Key, Value> setFetchExecutor(Executor executor) {
        Intrinsics.checkNotNullParameter(executor, "fetchExecutor");
        LivePagedListBuilder livePagedListBuilder = this;
        this.fetchDispatcher = ExecutorsKt.from(executor);
        return this;
    }

    public final LiveData<PagedList<Value>> build() {
        Function0<PagingSource<Key, Value>> function0 = this.pagingSourceFactory;
        if (function0 == null) {
            DataSource.Factory<Key, Value> factory = this.dataSourceFactory;
            function0 = factory != null ? factory.asPagingSourceFactory(this.fetchDispatcher) : null;
        }
        Function0<PagingSource<Key, Value>> function02 = function0;
        if (function02 != null) {
            CoroutineScope coroutineScope2 = this.coroutineScope;
            Key key = this.initialLoadKey;
            PagedList.Config config2 = this.config;
            PagedList.BoundaryCallback<Value> boundaryCallback2 = this.boundaryCallback;
            Executor mainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "getMainThreadExecutor()");
            return new LivePagedList<>(coroutineScope2, key, config2, boundaryCallback2, function02, ExecutorsKt.from(mainThreadExecutor), this.fetchDispatcher);
        }
        throw new IllegalStateException("LivePagedList cannot be built without a PagingSourceFactory or DataSource.Factory".toString());
    }
}
