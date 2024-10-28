package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001e*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001\u001eB!\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0002\u0010\tJ#\u0010\u0012\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0002J+\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Landroidx/paging/LegacyPagingSource;", "Key", "", "Value", "Landroidx/paging/PagingSource;", "fetchContext", "Lkotlin/coroutines/CoroutineContext;", "dataSource", "Landroidx/paging/DataSource;", "(Lkotlin/coroutines/CoroutineContext;Landroidx/paging/DataSource;)V", "getDataSource$paging_common", "()Landroidx/paging/DataSource;", "jumpingSupported", "", "getJumpingSupported", "()Z", "pageSize", "", "getRefreshKey", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Object;", "guessPageSize", "params", "Landroidx/paging/PagingSource$LoadParams;", "load", "Landroidx/paging/PagingSource$LoadResult;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setPageSize", "", "Companion", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LegacyPagingSource.kt */
public final class LegacyPagingSource<Key, Value> extends PagingSource<Key, Value> {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int PAGE_SIZE_NOT_SET = Integer.MIN_VALUE;
    private final DataSource<Key, Value> dataSource;
    private final CoroutineContext fetchContext;
    private int pageSize = Integer.MIN_VALUE;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LegacyPagingSource.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                androidx.paging.DataSource$KeyType[] r0 = androidx.paging.DataSource.KeyType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.DataSource$KeyType r1 = androidx.paging.DataSource.KeyType.POSITIONAL     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.DataSource$KeyType r1 = androidx.paging.DataSource.KeyType.PAGE_KEYED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.paging.DataSource$KeyType r1 = androidx.paging.DataSource.KeyType.ITEM_KEYED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.LegacyPagingSource.WhenMappings.<clinit>():void");
        }
    }

    public final DataSource<Key, Value> getDataSource$paging_common() {
        return this.dataSource;
    }

    public LegacyPagingSource(CoroutineContext coroutineContext, DataSource<Key, Value> dataSource2) {
        Intrinsics.checkNotNullParameter(coroutineContext, "fetchContext");
        Intrinsics.checkNotNullParameter(dataSource2, "dataSource");
        this.fetchContext = coroutineContext;
        this.dataSource = dataSource2;
        dataSource2.addInvalidatedCallback(new Object(this) {
            final /* synthetic */ LegacyPagingSource<Key, Value> $tmp0;

            {
                this.$tmp0 = r1;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof DataSource.InvalidatedCallback) || !(obj instanceof FunctionAdapter)) {
                    return false;
                }
                return Intrinsics.areEqual((Object) getFunctionDelegate(), (Object) ((FunctionAdapter) obj).getFunctionDelegate());
            }

            public final Function<?> getFunctionDelegate() {
                return new FunctionReferenceImpl(0, this.$tmp0, LegacyPagingSource.class, "invalidate", "invalidate()V", 0);
            }

            public final int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            public final void onInvalidated() {
                this.$tmp0.invalidate();
            }
        });
        registerInvalidatedCallback(new Function0<Unit>(this) {
            final /* synthetic */ LegacyPagingSource<Key, Value> this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                DataSource<Key, Value> dataSource$paging_common = this.this$0.getDataSource$paging_common();
                final LegacyPagingSource<Key, Value> legacyPagingSource = this.this$0;
                dataSource$paging_common.removeInvalidatedCallback(new Object() {
                    public final boolean equals(Object obj) {
                        if (!(obj instanceof DataSource.InvalidatedCallback) || !(obj instanceof FunctionAdapter)) {
                            return false;
                        }
                        return Intrinsics.areEqual((Object) getFunctionDelegate(), (Object) ((FunctionAdapter) obj).getFunctionDelegate());
                    }

                    public final Function<?> getFunctionDelegate() {
                        return new FunctionReferenceImpl(0, legacyPagingSource, LegacyPagingSource.class, "invalidate", "invalidate()V", 0);
                    }

                    public final int hashCode() {
                        return getFunctionDelegate().hashCode();
                    }

                    public final void onInvalidated() {
                        legacyPagingSource.invalidate();
                    }
                });
                this.this$0.getDataSource$paging_common().invalidate();
            }
        });
    }

    public final void setPageSize(int i) {
        int i2 = this.pageSize;
        if (i2 == Integer.MIN_VALUE || i == i2) {
            this.pageSize = i;
            return;
        }
        throw new IllegalStateException(("Page size is already set to " + this.pageSize + '.').toString());
    }

    private final int guessPageSize(PagingSource.LoadParams<Key> loadParams) {
        if (!(loadParams instanceof PagingSource.LoadParams.Refresh) || loadParams.getLoadSize() % 3 != 0) {
            return loadParams.getLoadSize();
        }
        return loadParams.getLoadSize() / 3;
    }

    public Object load(PagingSource.LoadParams<Key> loadParams, Continuation<? super PagingSource.LoadResult<Key, Value>> continuation) {
        LoadType loadType;
        if (loadParams instanceof PagingSource.LoadParams.Refresh) {
            loadType = LoadType.REFRESH;
        } else if (loadParams instanceof PagingSource.LoadParams.Append) {
            loadType = LoadType.APPEND;
        } else if (loadParams instanceof PagingSource.LoadParams.Prepend) {
            loadType = LoadType.PREPEND;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        LoadType loadType2 = loadType;
        if (this.pageSize == Integer.MIN_VALUE) {
            System.out.println("WARNING: pageSize on the LegacyPagingSource is not set.\nWhen using legacy DataSource / DataSourceFactory with Paging3, page size\nshould've been set by the paging library but it is not set yet.\n\nIf you are seeing this message in tests where you are testing DataSource\nin isolation (without a Pager), it is expected and page size will be estimated\nbased on parameters.\n\nIf you are seeing this message despite using a Pager, please file a bug:\nhttps://issuetracker.google.com/issues/new?component=413106");
            this.pageSize = guessPageSize(loadParams);
        }
        return BuildersKt.withContext(this.fetchContext, new LegacyPagingSource$load$2(this, new DataSource.Params(loadType2, loadParams.getKey(), loadParams.getLoadSize(), loadParams.getPlaceholdersEnabled(), this.pageSize), loadParams, (Continuation<? super LegacyPagingSource$load$2>) null), continuation);
    }

    public Key getRefreshKey(PagingState<Key, Value> pagingState) {
        int i;
        Value closestItemToPosition;
        Intrinsics.checkNotNullParameter(pagingState, OAuth2RequestParameters.State);
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.dataSource.getType$paging_common().ordinal()];
        if (i2 == 1) {
            Integer anchorPosition = pagingState.getAnchorPosition();
            if (anchorPosition == null) {
                return null;
            }
            int intValue = anchorPosition.intValue();
            int access$getLeadingPlaceholderCount$p = intValue - pagingState.leadingPlaceholderCount;
            int i3 = 0;
            while (i3 < CollectionsKt.getLastIndex(pagingState.getPages()) && access$getLeadingPlaceholderCount$p > CollectionsKt.getLastIndex(pagingState.getPages().get(i3).getData())) {
                access$getLeadingPlaceholderCount$p -= pagingState.getPages().get(i3).getData().size();
                i3++;
            }
            PagingSource.LoadResult.Page<Key, Value> closestPageToPosition = pagingState.closestPageToPosition(intValue);
            if (closestPageToPosition == null || (i = closestPageToPosition.getPrevKey()) == null) {
                i = 0;
            }
            Intrinsics.checkNotNull(i, "null cannot be cast to non-null type kotlin.Int");
            return Integer.valueOf(((Integer) i).intValue() + access$getLeadingPlaceholderCount$p);
        } else if (i2 == 2) {
            return null;
        } else {
            if (i2 == 3) {
                Integer anchorPosition2 = pagingState.getAnchorPosition();
                if (anchorPosition2 == null || (closestItemToPosition = pagingState.closestItemToPosition(anchorPosition2.intValue())) == null) {
                    return null;
                }
                return this.dataSource.getKeyInternal$paging_common(closestItemToPosition);
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public boolean getJumpingSupported() {
        return this.dataSource.getType$paging_common() == DataSource.KeyType.POSITIONAL;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/paging/LegacyPagingSource$Companion;", "", "()V", "PAGE_SIZE_NOT_SET", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LegacyPagingSource.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
