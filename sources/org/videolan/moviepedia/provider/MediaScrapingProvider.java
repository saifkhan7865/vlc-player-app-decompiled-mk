package org.videolan.moviepedia.provider;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00150.2\u0006\u0010/\u001a\u00020\u001d¢\u0006\u0002\u00100J\u0006\u00101\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020,2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R,\u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00060'0\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0011\"\u0004\b)\u0010*¨\u00062"}, d2 = {"Lorg/videolan/moviepedia/provider/MediaScrapingProvider;", "Lorg/videolan/resources/util/HeaderProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "desc", "", "getDesc", "()Z", "setDesc", "(Z)V", "value", "isRefreshing", "setRefreshing", "loading", "Landroidx/lifecycle/MutableLiveData;", "getLoading", "()Landroidx/lifecycle/MutableLiveData;", "pagedList", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "getPagedList", "()Landroidx/lifecycle/LiveData;", "setPagedList", "(Landroidx/lifecycle/LiveData;)V", "settings", "Landroid/content/SharedPreferences;", "sort", "", "getSort", "()I", "setSort", "(I)V", "sortKey", "", "getSortKey", "()Ljava/lang/String;", "sortQuery", "Lkotlin/Pair;", "getSortQuery", "setSortQuery", "(Landroidx/lifecycle/MutableLiveData;)V", "completeHeaders", "", "list", "", "startposition", "([Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;I)V", "refresh", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingProvider.kt */
public abstract class MediaScrapingProvider extends HeaderProvider {
    private final Context context;
    private boolean desc;
    private boolean isRefreshing;
    private final MutableLiveData<Boolean> loading;
    private final SharedPreferences settings;
    private int sort;
    private final String sortKey;
    private MutableLiveData<Pair<Integer, Boolean>> sortQuery;

    public abstract LiveData<PagedList<MediaMetadataWithImages>> getPagedList();

    public abstract void setPagedList(LiveData<PagedList<MediaMetadataWithImages>> liveData);

    public MediaScrapingProvider(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(true);
        this.loading = mutableLiveData;
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(context2);
        this.settings = sharedPreferences;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        this.sortKey = simpleName;
        this.sort = sharedPreferences.getInt(getSortKey(), 0);
        this.desc = sharedPreferences.getBoolean(getSortKey() + "_desc", false);
        MutableLiveData<Pair<Integer, Boolean>> mutableLiveData2 = new MutableLiveData<>();
        mutableLiveData2.setValue(new Pair(Integer.valueOf(this.sort), Boolean.valueOf(this.desc)));
        this.sortQuery = mutableLiveData2;
    }

    public final MutableLiveData<Boolean> getLoading() {
        return this.loading;
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

    public final MutableLiveData<Pair<Integer, Boolean>> getSortQuery() {
        return this.sortQuery;
    }

    public final void setSortQuery(MutableLiveData<Pair<Integer, Boolean>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.sortQuery = mutableLiveData;
    }

    public final boolean isRefreshing() {
        return this.isRefreshing;
    }

    private final void setRefreshing(boolean z) {
        this.loading.postValue(Boolean.valueOf(z));
        this.isRefreshing = z;
    }

    public final void sort(int i) {
        int i2 = this.sort;
        boolean z = false;
        if (i2 != 0 ? !(i2 != i || this.desc) : i == 1) {
            z = true;
        }
        this.desc = z;
        this.sort = i;
        this.sortQuery.setValue(new Pair(Integer.valueOf(i), Boolean.valueOf(this.desc)));
        SharedPreferences.Editor edit = this.settings.edit();
        edit.putInt(getSortKey(), i);
        edit.putBoolean(getSortKey() + "_desc", this.desc);
        edit.apply();
    }

    public final boolean refresh() {
        DataSource dataSource;
        DataSource dataSource2;
        if (this.isRefreshing) {
            return false;
        }
        PagedList value = getPagedList().getValue();
        Boolean bool = null;
        if ((value != null ? value.getDataSource() : null) == null) {
            return false;
        }
        getPrivateHeaders().clear();
        PagedList value2 = getPagedList().getValue();
        if (!(value2 == null || (dataSource2 = value2.getDataSource()) == null)) {
            bool = Boolean.valueOf(dataSource2.isInvalid());
        }
        Intrinsics.checkNotNull(bool);
        if (!bool.booleanValue()) {
            setRefreshing(true);
            PagedList value3 = getPagedList().getValue();
            if (!(value3 == null || (dataSource = value3.getDataSource()) == null)) {
                dataSource.invalidate();
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
        r4 = getPagedList().getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void completeHeaders(org.videolan.moviepedia.database.models.MediaMetadataWithImages[] r8, int r9) {
        /*
            r7 = this;
            java.lang.String r0 = "list"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            int r0 = r8.length
            r1 = 0
        L_0x0007:
            if (r1 >= r0) goto L_0x004f
            r2 = r8[r1]
            r3 = 0
            if (r1 <= 0) goto L_0x0013
            int r4 = r1 + -1
            r4 = r8[r4]
            goto L_0x002f
        L_0x0013:
            if (r9 <= 0) goto L_0x002e
            androidx.lifecycle.LiveData r4 = r7.getPagedList()
            java.lang.Object r4 = r4.getValue()
            androidx.paging.PagedList r4 = (androidx.paging.PagedList) r4
            if (r4 == 0) goto L_0x002e
            java.util.List r4 = (java.util.List) r4
            int r5 = r9 + r1
            int r5 = r5 + -1
            java.lang.Object r4 = kotlin.collections.CollectionsKt.getOrNull(r4, r5)
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r4 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r4
            goto L_0x002f
        L_0x002e:
            r4 = r3
        L_0x002f:
            android.content.Context r5 = r7.context
            int r6 = r7.sort
            org.videolan.moviepedia.database.models.MediaMetadata r2 = r2.getMetadata()
            if (r4 == 0) goto L_0x003d
            org.videolan.moviepedia.database.models.MediaMetadata r3 = r4.getMetadata()
        L_0x003d:
            java.lang.String r2 = org.videolan.moviepedia.HelpersKt.getHeaderMoviepedia(r5, r6, r2, r3)
            if (r2 == 0) goto L_0x004c
            androidx.collection.SparseArrayCompat r3 = r7.getPrivateHeaders()
            int r4 = r9 + r1
            r3.put(r4, r2)
        L_0x004c:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x004f:
            androidx.lifecycle.LiveData r8 = r7.getLiveHeaders()
            java.lang.String r9 = "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.collection.SparseArrayCompat<kotlin.String>{ org.videolan.resources.util.HeaderProviderKt.HeadersIndex }>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r9)
            androidx.lifecycle.MutableLiveData r8 = (androidx.lifecycle.MutableLiveData) r8
            androidx.collection.SparseArrayCompat r9 = r7.getPrivateHeaders()
            androidx.collection.SparseArrayCompat r9 = r9.clone()
            r8.postValue(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.provider.MediaScrapingProvider.completeHeaders(org.videolan.moviepedia.database.models.MediaMetadataWithImages[], int):void");
    }
}
