package androidx.paging;

import androidx.paging.LoadState;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0002)*B\u0005¢\u0006\u0002\u0010\u0004J\"\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u001d\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J \u0010\"\u001a\u001c\u0012\u0004\u0012\u00020\u0017\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0019\u0018\u00010#J\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0019J\u0016\u0010%\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0007J\u0018\u0010'\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010\nR\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u000bR \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006+"}, d2 = {"Landroidx/paging/AccessorState;", "Key", "", "Value", "()V", "blockStates", "", "Landroidx/paging/AccessorState$BlockState;", "[Landroidx/paging/AccessorState$BlockState;", "errors", "Landroidx/paging/LoadState$Error;", "[Landroidx/paging/LoadState$Error;", "pendingRequests", "Lkotlin/collections/ArrayDeque;", "Landroidx/paging/AccessorState$PendingRequest;", "refreshAllowed", "", "getRefreshAllowed", "()Z", "setRefreshAllowed", "(Z)V", "add", "loadType", "Landroidx/paging/LoadType;", "pagingState", "Landroidx/paging/PagingState;", "clearErrors", "", "clearPendingRequest", "clearPendingRequests", "computeLoadStates", "Landroidx/paging/LoadStates;", "computeLoadTypeState", "Landroidx/paging/LoadState;", "getPendingBoundary", "Lkotlin/Pair;", "getPendingRefresh", "setBlockState", "state", "setError", "errorState", "BlockState", "PendingRequest", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RemoteMediatorAccessor.kt */
final class AccessorState<Key, Value> {
    private final BlockState[] blockStates;
    private final LoadState.Error[] errors;
    private final ArrayDeque<PendingRequest<Key, Value>> pendingRequests;
    private boolean refreshAllowed;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/paging/AccessorState$BlockState;", "", "(Ljava/lang/String;I)V", "UNBLOCKED", "COMPLETED", "REQUIRES_REFRESH", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RemoteMediatorAccessor.kt */
    public enum BlockState {
        UNBLOCKED,
        COMPLETED,
        REQUIRES_REFRESH
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RemoteMediatorAccessor.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|(2:1|2)|3|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0021 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
        static {
            /*
                androidx.paging.LoadType[] r0 = androidx.paging.LoadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                $EnumSwitchMapping$0 = r0
                androidx.paging.AccessorState$BlockState[] r0 = androidx.paging.AccessorState.BlockState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.AccessorState$BlockState r2 = androidx.paging.AccessorState.BlockState.COMPLETED     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0021 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                androidx.paging.AccessorState$BlockState r1 = androidx.paging.AccessorState.BlockState.REQUIRES_REFRESH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                androidx.paging.AccessorState$BlockState r1 = androidx.paging.AccessorState.BlockState.UNBLOCKED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.AccessorState.WhenMappings.<clinit>():void");
        }
    }

    public AccessorState() {
        int length = LoadType.values().length;
        BlockState[] blockStateArr = new BlockState[length];
        for (int i = 0; i < length; i++) {
            blockStateArr[i] = BlockState.UNBLOCKED;
        }
        this.blockStates = blockStateArr;
        int length2 = LoadType.values().length;
        LoadState.Error[] errorArr = new LoadState.Error[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            errorArr[i2] = null;
        }
        this.errors = errorArr;
        this.pendingRequests = new ArrayDeque<>();
    }

    public final boolean getRefreshAllowed() {
        return this.refreshAllowed;
    }

    public final void setRefreshAllowed(boolean z) {
        this.refreshAllowed = z;
    }

    public final LoadStates computeLoadStates() {
        return new LoadStates(computeLoadTypeState(LoadType.REFRESH), computeLoadTypeState(LoadType.PREPEND), computeLoadTypeState(LoadType.APPEND));
    }

    private final LoadState computeLoadTypeState(LoadType loadType) {
        LoadState.NotLoading notLoading;
        BlockState blockState = this.blockStates[loadType.ordinal()];
        Iterable iterable = this.pendingRequests;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((PendingRequest) it.next()).getLoadType() == loadType) {
                    if (blockState != BlockState.REQUIRES_REFRESH) {
                        return LoadState.Loading.INSTANCE;
                    }
                }
            }
        }
        LoadState.Error error = this.errors[loadType.ordinal()];
        if (error != null) {
            return error;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[blockState.ordinal()];
        if (i == 1) {
            if (WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()] == 1) {
                notLoading = LoadState.NotLoading.Companion.getIncomplete$paging_common();
            } else {
                notLoading = LoadState.NotLoading.Companion.getComplete$paging_common();
            }
            return notLoading;
        } else if (i == 2) {
            return LoadState.NotLoading.Companion.getIncomplete$paging_common();
        } else {
            if (i == 3) {
                return LoadState.NotLoading.Companion.getIncomplete$paging_common();
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean add(LoadType loadType, PagingState<Key, Value> pagingState) {
        Object obj;
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(pagingState, "pagingState");
        Iterator it = this.pendingRequests.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((PendingRequest) obj).getLoadType() == loadType) {
                break;
            }
        }
        PendingRequest pendingRequest = (PendingRequest) obj;
        if (pendingRequest != null) {
            pendingRequest.setPagingState(pagingState);
            return false;
        }
        BlockState blockState = this.blockStates[loadType.ordinal()];
        if (blockState == BlockState.REQUIRES_REFRESH && loadType != LoadType.REFRESH) {
            this.pendingRequests.add(new PendingRequest(loadType, pagingState));
            return false;
        } else if (blockState != BlockState.UNBLOCKED && loadType != LoadType.REFRESH) {
            return false;
        } else {
            if (loadType == LoadType.REFRESH) {
                setError(LoadType.REFRESH, (LoadState.Error) null);
            }
            if (this.errors[loadType.ordinal()] == null) {
                return this.pendingRequests.add(new PendingRequest(loadType, pagingState));
            }
            return false;
        }
    }

    public final void setBlockState(LoadType loadType, BlockState blockState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(blockState, OAuth2RequestParameters.State);
        this.blockStates[loadType.ordinal()] = blockState;
    }

    public final PagingState<Key, Value> getPendingRefresh() {
        Object obj;
        Iterator it = this.pendingRequests.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((PendingRequest) obj).getLoadType() == LoadType.REFRESH) {
                break;
            }
        }
        PendingRequest pendingRequest = (PendingRequest) obj;
        if (pendingRequest != null) {
            return pendingRequest.getPagingState();
        }
        return null;
    }

    public final Pair<LoadType, PagingState<Key, Value>> getPendingBoundary() {
        Object obj;
        Iterator it = this.pendingRequests.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            PendingRequest pendingRequest = (PendingRequest) obj;
            if (pendingRequest.getLoadType() != LoadType.REFRESH && this.blockStates[pendingRequest.getLoadType().ordinal()] == BlockState.UNBLOCKED) {
                break;
            }
        }
        PendingRequest pendingRequest2 = (PendingRequest) obj;
        if (pendingRequest2 != null) {
            return TuplesKt.to(pendingRequest2.getLoadType(), pendingRequest2.getPagingState());
        }
        return null;
    }

    public final void clearPendingRequests() {
        this.pendingRequests.clear();
    }

    public final void clearPendingRequest(LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        CollectionsKt.removeAll(this.pendingRequests, new AccessorState$clearPendingRequest$1(loadType));
    }

    public final void clearErrors() {
        int length = this.errors.length;
        for (int i = 0; i < length; i++) {
            this.errors[i] = null;
        }
    }

    public final void setError(LoadType loadType, LoadState.Error error) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        this.errors[loadType.ordinal()] = error;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/paging/AccessorState$PendingRequest;", "Key", "", "Value", "loadType", "Landroidx/paging/LoadType;", "pagingState", "Landroidx/paging/PagingState;", "(Landroidx/paging/LoadType;Landroidx/paging/PagingState;)V", "getLoadType", "()Landroidx/paging/LoadType;", "getPagingState", "()Landroidx/paging/PagingState;", "setPagingState", "(Landroidx/paging/PagingState;)V", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RemoteMediatorAccessor.kt */
    public static final class PendingRequest<Key, Value> {
        private final LoadType loadType;
        private PagingState<Key, Value> pagingState;

        public PendingRequest(LoadType loadType2, PagingState<Key, Value> pagingState2) {
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            Intrinsics.checkNotNullParameter(pagingState2, "pagingState");
            this.loadType = loadType2;
            this.pagingState = pagingState2;
        }

        public final LoadType getLoadType() {
            return this.loadType;
        }

        public final PagingState<Key, Value> getPagingState() {
            return this.pagingState;
        }

        public final void setPagingState(PagingState<Key, Value> pagingState2) {
            Intrinsics.checkNotNullParameter(pagingState2, "<set-?>");
            this.pagingState = pagingState2;
        }
    }
}
