package org.videolan.vlc.providers.medialibrary;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.collection.SparseArrayCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.tools.Settings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.ModelsHelper;
import org.videolan.vlc.util.Permissions;
import org.videolan.vlc.util.SortModule;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u0004:\u0002YZB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010<\u001a\u000200H@¢\u0006\u0002\u0010=J!\u0010>\u001a\u0002002\f\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000@2\u0006\u0010A\u001a\u00020\u000e¢\u0006\u0002\u0010BJ\u0015\u0010C\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000@H&¢\u0006\u0002\u0010DJ#\u0010E\u001a\b\u0012\u0004\u0012\u00028\u00000@2\u0006\u0010F\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020\u000eH&¢\u0006\u0002\u0010GJ\b\u0010H\u001a\u00020\u000eH&J\u0006\u0010I\u001a\u00020\u0010J}\u0010J\u001a\u0002002\u0006\u0010\u0005\u001a\u00020\u00062'\u0010K\u001a#\u0012\u0013\u0012\u00110M¢\u0006\f\bN\u0012\b\bO\u0012\u0004\b\b(P\u0012\n\u0012\b\u0012\u0004\u0012\u00020R0Q0L2<\u0010S\u001a8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020R0Q¢\u0006\f\bN\u0012\b\bO\u0012\u0004\b\b(?\u0012\u0013\u0012\u00110M¢\u0006\f\bN\u0012\b\bO\u0012\u0004\b\b(P\u0012\u0004\u0012\u0002000TH@¢\u0006\u0002\u0010UJ\u0006\u0010V\u001a\u00020\u0010J\u0006\u0010W\u001a\u000200J\u000e\u0010X\u001a\u0002002\u0006\u0010X\u001a\u00020\u0010J\u0010\u00103\u001a\u0002002\u0006\u00103\u001a\u00020\u000eH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00028\u00000\rX.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R'\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'0&8FX\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)R\u000e\u0010,\u001a\u00020-X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0004¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0014\u00108\u001a\u000209X\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;¨\u0006["}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/resources/util/HeaderProvider;", "Lorg/videolan/vlc/util/SortModule;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;)V", "getContext", "()Landroid/content/Context;", "dataSource", "Landroidx/paging/DataSource;", "", "desc", "", "getDesc", "()Z", "setDesc", "(Z)V", "value", "isRefreshing", "setRefreshing", "loading", "Landroidx/lifecycle/MutableLiveData;", "getLoading", "()Landroidx/lifecycle/MutableLiveData;", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getModel", "()Lorg/videolan/vlc/viewmodels/SortableModel;", "onlyFavorites", "getOnlyFavorites", "setOnlyFavorites", "pagedList", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "getPagedList", "()Landroidx/lifecycle/LiveData;", "pagedList$delegate", "Lkotlin/Lazy;", "pagingConfig", "Landroidx/paging/PagedList$Config;", "refreshDeferred", "Lkotlinx/coroutines/CompletableDeferred;", "", "settings", "Landroid/content/SharedPreferences;", "sort", "getSort", "()I", "setSort", "(I)V", "sortKey", "", "getSortKey", "()Ljava/lang/String;", "awaitRefresh", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeHeaders", "list", "", "startposition", "([Lorg/videolan/medialibrary/media/MediaLibraryItem;I)V", "getAll", "()[Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getPage", "loadSize", "(II)[Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getTotalCount", "isEmpty", "loadPagedList", "pageSizeLambda", "Lkotlin/Function1;", "Lorg/videolan/vlc/PlaybackService;", "Lkotlin/ParameterName;", "name", "service", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "loadLambda", "Lkotlin/Function2;", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refresh", "saveSort", "showOnlyFavs", "MLDataSource", "MLDatasourceFactory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MedialibraryProvider.kt */
public abstract class MedialibraryProvider<T extends MediaLibraryItem> extends HeaderProvider implements SortModule {
    private final Context context;
    /* access modifiers changed from: private */
    public DataSource<Integer, T> dataSource;
    private boolean desc;
    private boolean isRefreshing = true;
    private final MutableLiveData<Boolean> loading;
    private final Medialibrary medialibrary;
    private final SortableModel model;
    private boolean onlyFavorites;
    private final Lazy pagedList$delegate;
    /* access modifiers changed from: private */
    public final PagedList.Config pagingConfig;
    private CompletableDeferred<Unit> refreshDeferred;
    private final SharedPreferences settings;
    private int sort;
    private final String sortKey;

    public abstract T[] getAll();

    public abstract T[] getPage(int i, int i2);

    public abstract int getTotalCount();

    public MedialibraryProvider(Context context2, SortableModel sortableModel) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(sortableModel, "model");
        this.context = context2;
        this.model = sortableModel;
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(context2);
        this.settings = sharedPreferences;
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.medialibrary = instance;
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(true);
        this.loading = mutableLiveData;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        this.sortKey = simpleName;
        this.sort = sharedPreferences.getInt(getSortKey(), 0);
        this.desc = sharedPreferences.getBoolean(getSortKey() + "_desc", false);
        this.onlyFavorites = sharedPreferences.getBoolean(getSortKey() + "_only_favs", false);
        this.pagingConfig = new PagedList.Config.Builder().setPageSize(500).setPrefetchDistance(100).setEnablePlaceholders(true).setInitialLoadSizeHint(500).setMaxSize(1000).build();
        this.pagedList$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new MedialibraryProvider$pagedList$2(this));
    }

    public boolean canSortBy(int i) {
        return SortModule.DefaultImpls.canSortBy(this, i);
    }

    public boolean canSortByAlbum() {
        return SortModule.DefaultImpls.canSortByAlbum(this);
    }

    public boolean canSortByArtist() {
        return SortModule.DefaultImpls.canSortByArtist(this);
    }

    public boolean canSortByDuration() {
        return SortModule.DefaultImpls.canSortByDuration(this);
    }

    public boolean canSortByFileNameName() {
        return SortModule.DefaultImpls.canSortByFileNameName(this);
    }

    public boolean canSortByFileSize() {
        return SortModule.DefaultImpls.canSortByFileSize(this);
    }

    public boolean canSortByInsertionDate() {
        return SortModule.DefaultImpls.canSortByInsertionDate(this);
    }

    public boolean canSortByLastModified() {
        return SortModule.DefaultImpls.canSortByLastModified(this);
    }

    public boolean canSortByMediaNumber() {
        return SortModule.DefaultImpls.canSortByMediaNumber(this);
    }

    public boolean canSortByName() {
        return SortModule.DefaultImpls.canSortByName(this);
    }

    public boolean canSortByPlayCount() {
        return SortModule.DefaultImpls.canSortByPlayCount(this);
    }

    public boolean canSortByReleaseDate() {
        return SortModule.DefaultImpls.canSortByReleaseDate(this);
    }

    public boolean canSortByTrackId() {
        return SortModule.DefaultImpls.canSortByTrackId(this);
    }

    public final Context getContext() {
        return this.context;
    }

    public final SortableModel getModel() {
        return this.model;
    }

    /* access modifiers changed from: protected */
    public final Medialibrary getMedialibrary() {
        return this.medialibrary;
    }

    public final MutableLiveData<Boolean> getLoading() {
        return this.loading;
    }

    public final boolean isRefreshing() {
        return this.isRefreshing;
    }

    /* access modifiers changed from: private */
    public final void setRefreshing(boolean z) {
        boolean z2 = true;
        CompletableDeferred<Unit> completableDeferred = null;
        if (z) {
            completableDeferred = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        } else {
            CompletableDeferred<Unit> completableDeferred2 = this.refreshDeferred;
            if (completableDeferred2 != null) {
                completableDeferred2.complete(Unit.INSTANCE);
            }
        }
        this.refreshDeferred = completableDeferred;
        MutableLiveData<Boolean> mutableLiveData = this.loading;
        if ((!z && !this.medialibrary.isWorking()) || !Permissions.INSTANCE.canReadStorage(this.context)) {
            z2 = false;
        }
        mutableLiveData.postValue(Boolean.valueOf(z2));
        this.isRefreshing = z;
    }

    /* access modifiers changed from: protected */
    public String getSortKey() {
        return this.sortKey;
    }

    public final int getSort() {
        return this.sort;
    }

    public final void setSort(int i) {
        this.sort = i;
    }

    public final boolean getDesc() {
        return this.desc;
    }

    public final void setDesc(boolean z) {
        this.desc = z;
    }

    public final boolean getOnlyFavorites() {
        return this.onlyFavorites;
    }

    public final void setOnlyFavorites(boolean z) {
        this.onlyFavorites = z;
    }

    public final LiveData<PagedList<T>> getPagedList() {
        return (LiveData) this.pagedList$delegate.getValue();
    }

    public final Object loadPagedList(Context context2, Function1<? super PlaybackService, ? extends List<? extends MediaWrapper>> function1, Function2<? super List<? extends MediaWrapper>, ? super PlaybackService, Unit> function2, Continuation<? super Unit> continuation) {
        new MediaUtils.SuspendDialogCallback(context2, new MedialibraryProvider$loadPagedList$2(function2, this, function1, (Continuation<? super MedialibraryProvider$loadPagedList$2>) null));
        return Unit.INSTANCE;
    }

    public void sort(int i) {
        if (canSortBy(i)) {
            int i2 = this.sort;
            boolean z = false;
            if (i2 != 0 ? !(i2 != i || this.desc) : i == 1) {
                z = true;
            }
            this.desc = z;
            this.sort = i;
            refresh();
            SharedPreferences.Editor edit = this.settings.edit();
            edit.putInt(getSortKey(), i);
            edit.putBoolean(getSortKey() + "_desc", this.desc);
            edit.apply();
        }
    }

    public final void saveSort() {
        SharedPreferences.Editor edit = this.settings.edit();
        edit.putInt(getSortKey(), this.sort);
        edit.putBoolean(getSortKey() + "_desc", this.desc);
        edit.apply();
    }

    public final void showOnlyFavs(boolean z) {
        this.onlyFavorites = z;
        SharedPreferences.Editor edit = this.settings.edit();
        edit.putBoolean(getSortKey() + "_only_favs", this.onlyFavorites);
        edit.apply();
    }

    public final Object awaitRefresh(Continuation<? super Unit> continuation) {
        refresh();
        CompletableDeferred<Unit> completableDeferred = this.refreshDeferred;
        if (completableDeferred == null) {
            return Unit.INSTANCE;
        }
        Object await = completableDeferred.await(continuation);
        return await == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? await : Unit.INSTANCE;
    }

    public final boolean refresh() {
        if ((this.isRefreshing && this.medialibrary.isWorking()) || !this.medialibrary.isStarted() || this.dataSource == null) {
            return false;
        }
        getPrivateHeaders().clear();
        DataSource<Integer, T> dataSource2 = this.dataSource;
        DataSource<Integer, T> dataSource3 = null;
        if (dataSource2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataSource");
            dataSource2 = null;
        }
        if (!dataSource2.isInvalid()) {
            setRefreshing(true);
            DataSource<Integer, T> dataSource4 = this.dataSource;
            if (dataSource4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataSource");
            } else {
                dataSource3 = dataSource4;
            }
            dataSource3.invalidate();
        }
        return true;
    }

    public final boolean isEmpty() {
        Collection collection = (Collection) getPagedList().getValue();
        return collection == null || collection.isEmpty();
    }

    public final void completeHeaders(T[] tArr, int i) {
        T t;
        PagedList pagedList;
        Intrinsics.checkNotNullParameter(tArr, "list");
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            T t2 = tArr[i2];
            if (i2 > 0) {
                t = tArr[i2 - 1];
            } else {
                t = null;
                if (i > 0 && (pagedList = (PagedList) getPagedList().getValue()) != null) {
                    t = (MediaLibraryItem) CollectionsKt.getOrNull(pagedList, (i + i2) - 1);
                }
            }
            String header = ModelsHelper.INSTANCE.getHeader(this.context, this.sort, t2, t);
            if (header != null) {
                getPrivateHeaders().put(i + i2, header);
            }
        }
        LiveData<SparseArrayCompat<String>> liveHeaders = getLiveHeaders();
        Intrinsics.checkNotNull(liveHeaders, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.collection.SparseArrayCompat<kotlin.String>{ org.videolan.resources.util.HeaderProviderKt.HeadersIndex }>");
        ((MutableLiveData) liveHeaders).postValue(getPrivateHeaders().clone());
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider$MLDataSource;", "Landroidx/paging/PositionalDataSource;", "(Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;)V", "loadInitial", "", "params", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "callback", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "loadRange", "Landroidx/paging/PositionalDataSource$LoadRangeParams;", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MedialibraryProvider.kt */
    public final class MLDataSource extends PositionalDataSource<T> {
        public MLDataSource() {
        }

        public void loadInitial(PositionalDataSource.LoadInitialParams loadInitialParams, PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
            Intrinsics.checkNotNullParameter(loadInitialParams, "params");
            Intrinsics.checkNotNullParameter(loadInitialCallback, "callback");
            MediaLibraryItem[] page = MedialibraryProvider.this.getPage(loadInitialParams.requestedLoadSize, loadInitialParams.requestedStartPosition);
            try {
                loadInitialCallback.onResult(ArraysKt.toList((T[]) page), loadInitialParams.requestedStartPosition, page.length < loadInitialParams.requestedLoadSize ? page.length : MedialibraryProvider.this.getTotalCount());
            } catch (IllegalArgumentException unused) {
            }
            MedialibraryProvider<T> medialibraryProvider = MedialibraryProvider.this;
            medialibraryProvider.setRefreshing(!medialibraryProvider.getMedialibrary().isStarted());
        }

        public void loadRange(PositionalDataSource.LoadRangeParams loadRangeParams, PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
            Intrinsics.checkNotNullParameter(loadRangeParams, "params");
            Intrinsics.checkNotNullParameter(loadRangeCallback, "callback");
            loadRangeCallback.onResult(ArraysKt.toList((T[]) MedialibraryProvider.this.getPage(loadRangeParams.loadSize, loadRangeParams.startPosition)));
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\f0\u0005R\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider$MLDatasourceFactory;", "Landroidx/paging/DataSource$Factory;", "", "(Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;)V", "create", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider$MLDataSource;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MedialibraryProvider.kt */
    public final class MLDatasourceFactory extends DataSource.Factory<Integer, T> {
        public MLDatasourceFactory() {
        }

        public MedialibraryProvider<T>.MLDataSource create() {
            MedialibraryProvider<T>.MLDataSource mLDataSource = new MLDataSource();
            MedialibraryProvider.this.dataSource = mLDataSource;
            return mLDataSource;
        }
    }
}
