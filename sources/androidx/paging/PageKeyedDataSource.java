package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.paging.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0004+,-.B\u0005¢\u0006\u0002\u0010\u0005J0\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f2\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u0017\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ*\u0010\u001a\u001a\u00020\u001d2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\fH&J%\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ*\u0010\u001f\u001a\u00020\u001d2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\fH&J%\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000!H@ø\u0001\u0000¢\u0006\u0002\u0010\"J*\u0010 \u001a\u00020\u001d2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000!2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010#H&J0\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H%0\u0000\"\b\b\u0002\u0010%*\u00020\u00022\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H%0'J0\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H%0\u0000\"\b\b\u0002\u0010%*\u00020\u00022\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H%0(J<\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H%0\u0000\"\b\b\u0002\u0010%*\u00020\u00022\u001e\u0010&\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010*\u0012\n\u0012\b\u0012\u0004\u0012\u0002H%0*0'J<\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H%0\u0000\"\b\b\u0002\u0010%*\u00020\u00022\u001e\u0010&\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010*\u0012\n\u0012\b\u0012\u0004\u0012\u0002H%0*0(R\u001a\u0010\u0006\u001a\u00020\u0007XD¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0005\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Landroidx/paging/PageKeyedDataSource;", "Key", "", "Value", "Landroidx/paging/DataSource;", "()V", "supportsPageDropping", "", "getSupportsPageDropping$paging_common$annotations", "getSupportsPageDropping$paging_common", "()Z", "continuationAsCallback", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "Landroidx/paging/DataSource$BaseResult;", "isAppend", "getKeyInternal", "item", "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Object;", "load", "params", "Landroidx/paging/DataSource$Params;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadAfter", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "(Landroidx/paging/PageKeyedDataSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "callback", "loadBefore", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "(Landroidx/paging/PageKeyedDataSource$LoadInitialParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "map", "ToValue", "function", "Lkotlin/Function1;", "Landroidx/arch/core/util/Function;", "mapByPage", "", "LoadCallback", "LoadInitialCallback", "LoadInitialParams", "LoadParams", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "PageKeyedDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Key, Value>", imports = {"androidx.paging.PagingSource"}))
/* compiled from: PageKeyedDataSource.kt */
public abstract class PageKeyedDataSource<Key, Value> extends DataSource<Key, Value> {
    private final boolean supportsPageDropping;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00030\b2\b\u0010\t\u001a\u0004\u0018\u00018\u0002H&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadCallback;", "Key", "Value", "", "()V", "onResult", "", "data", "", "adjacentPageKey", "(Ljava/util/List;Ljava/lang/Object;)V", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageKeyedDataSource.kt */
    public static abstract class LoadCallback<Key, Value> {
        public abstract void onResult(List<? extends Value> list, Key key);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J/\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00030\b2\b\u0010\t\u001a\u0004\u0018\u00018\u00022\b\u0010\n\u001a\u0004\u0018\u00018\u0002H&¢\u0006\u0002\u0010\u000bJ?\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00030\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00018\u00022\b\u0010\n\u001a\u0004\u0018\u00018\u0002H&¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "Key", "Value", "", "()V", "onResult", "", "data", "", "previousPageKey", "nextPageKey", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;)V", "position", "", "totalCount", "(Ljava/util/List;IILjava/lang/Object;Ljava/lang/Object;)V", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageKeyedDataSource.kt */
    public static abstract class LoadInitialCallback<Key, Value> {
        public abstract void onResult(List<? extends Value> list, int i, int i2, Key key, Key key2);

        public abstract void onResult(List<? extends Value> list, Key key, Key key2);
    }

    public static /* synthetic */ void getSupportsPageDropping$paging_common$annotations() {
    }

    public abstract void loadAfter(LoadParams<Key> loadParams, LoadCallback<Key, Value> loadCallback);

    public abstract void loadBefore(LoadParams<Key> loadParams, LoadCallback<Key, Value> loadCallback);

    public abstract void loadInitial(LoadInitialParams<Key> loadInitialParams, LoadInitialCallback<Key, Value> loadInitialCallback);

    public PageKeyedDataSource() {
        super(DataSource.KeyType.PAGE_KEYED);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "Key", "", "requestedLoadSize", "", "placeholdersEnabled", "", "(IZ)V", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageKeyedDataSource.kt */
    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        public final int requestedLoadSize;

        public LoadInitialParams(int i, boolean z) {
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0003\u001a\u00028\u00028\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadParams;", "Key", "", "key", "requestedLoadSize", "", "(Ljava/lang/Object;I)V", "Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageKeyedDataSource.kt */
    public static class LoadParams<Key> {
        public final Key key;
        public final int requestedLoadSize;

        public LoadParams(Key key2, int i) {
            Intrinsics.checkNotNullParameter(key2, LeanbackPreferenceDialogFragment.ARG_KEY);
            this.key = key2;
            this.requestedLoadSize = i;
        }
    }

    public final Object load$paging_common(DataSource.Params<Key> params, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        if (params.getType$paging_common() == LoadType.REFRESH) {
            return loadInitial(new LoadInitialParams(params.getInitialLoadSize(), params.getPlaceholdersEnabled()), continuation);
        }
        if (params.getKey() == null) {
            return DataSource.BaseResult.Companion.empty$paging_common();
        }
        if (params.getType$paging_common() == LoadType.PREPEND) {
            return loadBefore(new LoadParams(params.getKey(), params.getPageSize()), continuation);
        }
        if (params.getType$paging_common() == LoadType.APPEND) {
            return loadAfter(new LoadParams(params.getKey(), params.getPageSize()), continuation);
        }
        throw new IllegalArgumentException("Unsupported type " + params.getType$paging_common());
    }

    /* access modifiers changed from: private */
    public final LoadCallback<Key, Value> continuationAsCallback(CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation, boolean z) {
        return new PageKeyedDataSource$continuationAsCallback$1(cancellableContinuation, z);
    }

    public Key getKeyInternal$paging_common(Value value) {
        Intrinsics.checkNotNullParameter(value, "item");
        throw new IllegalStateException("Cannot get key by item in pageKeyedDataSource");
    }

    public boolean getSupportsPageDropping$paging_common() {
        return this.supportsPageDropping;
    }

    public final <ToValue> PageKeyedDataSource<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperPageKeyedDataSource<>(this, function);
    }

    /* access modifiers changed from: private */
    public static final List mapByPage$lambda$3(Function1 function1, List list) {
        Intrinsics.checkNotNullParameter(function1, "$function");
        Intrinsics.checkNotNullExpressionValue(list, "it");
        return (List) function1.invoke(list);
    }

    public final <ToValue> PageKeyedDataSource<Key, ToValue> mapByPage(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new PageKeyedDataSource$$ExternalSyntheticLambda1(function1));
    }

    /* access modifiers changed from: private */
    public static final List map$lambda$5(Function function, List list) {
        Intrinsics.checkNotNullParameter(function, "$function");
        Intrinsics.checkNotNullExpressionValue(list, "list");
        Iterable<Object> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object apply : iterable) {
            arrayList.add(function.apply(apply));
        }
        return (List) arrayList;
    }

    public final <ToValue> PageKeyedDataSource<Key, ToValue> map(Function<Value, ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new PageKeyedDataSource$$ExternalSyntheticLambda0(function));
    }

    /* access modifiers changed from: private */
    public static final List map$lambda$6(Function1 function1, List list) {
        Intrinsics.checkNotNullParameter(function1, "$function");
        Intrinsics.checkNotNullExpressionValue(list, "list");
        Iterable<Object> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object invoke : iterable) {
            arrayList.add(function1.invoke(invoke));
        }
        return (List) arrayList;
    }

    public final <ToValue> PageKeyedDataSource<Key, ToValue> map(Function1<? super Value, ? extends ToValue> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new PageKeyedDataSource$$ExternalSyntheticLambda2(function1));
    }

    /* access modifiers changed from: private */
    public final Object loadInitial(LoadInitialParams<Key> loadInitialParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new PageKeyedDataSource$loadInitial$2$1(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* access modifiers changed from: private */
    public final Object loadBefore(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadBefore(loadParams, continuationAsCallback(cancellableContinuationImpl, false));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* access modifiers changed from: private */
    public final Object loadAfter(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadAfter(loadParams, continuationAsCallback(cancellableContinuationImpl, true));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
