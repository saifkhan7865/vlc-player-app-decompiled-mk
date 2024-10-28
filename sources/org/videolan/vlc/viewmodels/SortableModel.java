package org.videolan.vlc.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.vlc.util.RefreshModel;
import org.videolan.vlc.util.SortModule;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0010H&J\u0006\u0010$\u001a\u00020\u0010J\b\u0010%\u001a\u00020\"H&J\u0010\u0010\u0019\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0012¨\u0006&"}, d2 = {"Lorg/videolan/vlc/viewmodels/SortableModel;", "Landroidx/lifecycle/ViewModel;", "Lorg/videolan/vlc/util/RefreshModel;", "Lorg/videolan/vlc/util/SortModule;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "desc", "", "getDesc", "()Z", "setDesc", "(Z)V", "filterQuery", "", "getFilterQuery", "()Ljava/lang/String;", "setFilterQuery", "(Ljava/lang/String;)V", "settings", "Landroid/content/SharedPreferences;", "getSettings", "()Landroid/content/SharedPreferences;", "sort", "", "getSort", "()I", "setSort", "(I)V", "sortKey", "getSortKey", "filter", "", "query", "getKey", "restore", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SortableModel.kt */
public abstract class SortableModel extends ViewModel implements RefreshModel, SortModule {
    private final Context context;
    private boolean desc;
    private String filterQuery;
    private final SharedPreferences settings;
    private int sort = getSettings().getInt(getSortKey(), 0);
    private final String sortKey;

    public abstract void filter(String str);

    public abstract void restore();

    public SortableModel(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.settings = (SharedPreferences) Settings.INSTANCE.getInstance(context2);
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        this.sortKey = simpleName;
        SharedPreferences settings2 = getSettings();
        this.desc = settings2.getBoolean(getSortKey() + "_desc", false);
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

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return this.context;
    }

    public SharedPreferences getSettings() {
        return this.settings;
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

    public final String getFilterQuery() {
        return this.filterQuery;
    }

    public final void setFilterQuery(String str) {
        this.filterQuery = str;
    }

    public final String getKey() {
        return getSortKey();
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
            SharedPreferences.Editor edit = getSettings().edit();
            edit.putInt(getSortKey(), i);
            edit.putBoolean(getSortKey() + "_desc", this.desc);
            edit.apply();
        }
    }
}
