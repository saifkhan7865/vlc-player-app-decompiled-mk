package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.paging.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001'\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0004/012B\u0005¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H&¢\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\n\u0010\bJ'\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J$\u0010\u0011\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0017H&J'\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0014J$\u0010\u0018\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0017H&J'\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ$\u0010\u001a\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u001eH&J0\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H 0\"J0\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H 0#J<\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u001e\u0010!\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0%0\"J<\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u001e\u0010!\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0%0#J)\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010'*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f0(H\u0002¢\u0006\u0002\u0010)J\u001b\u0010*\u001a\u0004\u0018\u00018\u0000*\b\u0012\u0004\u0012\u00028\u00010%H\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010-\u001a\u0004\u0018\u00018\u0000*\b\u0012\u0004\u0012\u00028\u00010%H\u0000¢\u0006\u0004\b.\u0010,\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Landroidx/paging/ItemKeyedDataSource;", "Key", "", "Value", "Landroidx/paging/DataSource;", "()V", "getKey", "item", "(Ljava/lang/Object;)Ljava/lang/Object;", "getKeyInternal", "getKeyInternal$paging_common", "load", "Landroidx/paging/DataSource$BaseResult;", "params", "Landroidx/paging/DataSource$Params;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadAfter", "Landroidx/paging/ItemKeyedDataSource$LoadParams;", "loadAfter$paging_common", "(Landroidx/paging/ItemKeyedDataSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "callback", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "loadBefore", "loadBefore$paging_common", "loadInitial", "Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "loadInitial$paging_common", "(Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "map", "ToValue", "function", "Lkotlin/Function1;", "Landroidx/arch/core/util/Function;", "mapByPage", "", "asCallback", "androidx/paging/ItemKeyedDataSource$asCallback$1", "Lkotlinx/coroutines/CancellableContinuation;", "(Lkotlinx/coroutines/CancellableContinuation;)Landroidx/paging/ItemKeyedDataSource$asCallback$1;", "getNextKey", "getNextKey$paging_common", "(Ljava/util/List;)Ljava/lang/Object;", "getPrevKey", "getPrevKey$paging_common", "LoadCallback", "LoadInitialCallback", "LoadInitialParams", "LoadParams", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "ItemKeyedDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Key, Value>", imports = {"androidx.paging.PagingSource"}))
/* compiled from: ItemKeyedDataSource.kt */
public abstract class ItemKeyedDataSource<Key, Value> extends DataSource<Key, Value> {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007H&¨\u0006\b"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "Value", "", "()V", "onResult", "", "data", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ItemKeyedDataSource.kt */
    public static abstract class LoadCallback<Value> {
        public abstract void onResult(List<? extends Value> list);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&¨\u0006\u000b"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "Value", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "()V", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ItemKeyedDataSource.kt */
    public static abstract class LoadInitialCallback<Value> extends LoadCallback<Value> {
        public abstract void onResult(List<? extends Value> list, int i, int i2);
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ItemKeyedDataSource.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                androidx.paging.LoadType[] r0 = androidx.paging.LoadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.ItemKeyedDataSource.WhenMappings.<clinit>():void");
        }
    }

    public abstract Key getKey(Value value);

    public abstract void loadAfter(LoadParams<Key> loadParams, LoadCallback<Value> loadCallback);

    public abstract void loadBefore(LoadParams<Key> loadParams, LoadCallback<Value> loadCallback);

    public abstract void loadInitial(LoadInitialParams<Key> loadInitialParams, LoadInitialCallback<Value> loadInitialCallback);

    public ItemKeyedDataSource() {
        super(DataSource.KeyType.ITEM_KEYED);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u0004\u0018\u00018\u00028\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "Key", "", "requestedInitialKey", "requestedLoadSize", "", "placeholdersEnabled", "", "(Ljava/lang/Object;IZ)V", "Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ItemKeyedDataSource.kt */
    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        public final Key requestedInitialKey;
        public final int requestedLoadSize;

        public LoadInitialParams(Key key, int i, boolean z) {
            this.requestedInitialKey = key;
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0003\u001a\u00028\u00028\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadParams;", "Key", "", "key", "requestedLoadSize", "", "(Ljava/lang/Object;I)V", "Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ItemKeyedDataSource.kt */
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
        int i = WhenMappings.$EnumSwitchMapping$0[params.getType$paging_common().ordinal()];
        if (i == 1) {
            return loadInitial$paging_common(new LoadInitialParams(params.getKey(), params.getInitialLoadSize(), params.getPlaceholdersEnabled()), continuation);
        }
        if (i == 2) {
            Key key = params.getKey();
            Intrinsics.checkNotNull(key);
            return loadBefore$paging_common(new LoadParams(key, params.getPageSize()), continuation);
        } else if (i == 3) {
            Key key2 = params.getKey();
            Intrinsics.checkNotNull(key2);
            return loadAfter$paging_common(new LoadParams(key2, params.getPageSize()), continuation);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public final Key getPrevKey$paging_common(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Object firstOrNull = CollectionsKt.firstOrNull(list);
        if (firstOrNull != null) {
            return getKey(firstOrNull);
        }
        return null;
    }

    public final Key getNextKey$paging_common(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Object lastOrNull = CollectionsKt.lastOrNull(list);
        if (lastOrNull != null) {
            return getKey(lastOrNull);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final ItemKeyedDataSource$asCallback$1 asCallback(CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation) {
        return new ItemKeyedDataSource$asCallback$1(cancellableContinuation, this);
    }

    public Key getKeyInternal$paging_common(Value value) {
        Intrinsics.checkNotNullParameter(value, "item");
        return getKey(value);
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperItemKeyedDataSource<>(this, function);
    }

    /* access modifiers changed from: private */
    public static final List mapByPage$lambda$5(Function1 function1, List list) {
        Intrinsics.checkNotNullParameter(function1, "$function");
        Intrinsics.checkNotNullExpressionValue(list, "it");
        return (List) function1.invoke(list);
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mapByPage(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new ItemKeyedDataSource$$ExternalSyntheticLambda1(function1));
    }

    /* access modifiers changed from: private */
    public static final List map$lambda$7(Function function, List list) {
        Intrinsics.checkNotNullParameter(function, "$function");
        Intrinsics.checkNotNullExpressionValue(list, "list");
        Iterable<Object> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object apply : iterable) {
            arrayList.add(function.apply(apply));
        }
        return (List) arrayList;
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> map(Function<Value, ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new ItemKeyedDataSource$$ExternalSyntheticLambda2(function));
    }

    /* access modifiers changed from: private */
    public static final List map$lambda$8(Function1 function1, List list) {
        Intrinsics.checkNotNullParameter(function1, "$function");
        Intrinsics.checkNotNullExpressionValue(list, "list");
        Iterable<Object> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object invoke : iterable) {
            arrayList.add(function1.invoke(invoke));
        }
        return (List) arrayList;
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> map(Function1<? super Value, ? extends ToValue> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new ItemKeyedDataSource$$ExternalSyntheticLambda0(function1));
    }

    public final Object loadInitial$paging_common(LoadInitialParams<Key> loadInitialParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new ItemKeyedDataSource$loadInitial$2$1(cancellableContinuationImpl, this));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object loadBefore$paging_common(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadBefore(loadParams, asCallback(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object loadAfter$paging_common(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadAfter(loadParams, asCallback(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
