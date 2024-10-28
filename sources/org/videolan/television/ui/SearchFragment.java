package org.videolan.television.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.SearchSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\tH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000eH\u0016J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u001aH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/television/ui/SearchFragment;", "Landroidx/leanback/app/SearchSupportFragment;", "Landroidx/leanback/app/SearchSupportFragment$SearchResultProvider;", "()V", "defaultItemClickedListener", "Landroidx/leanback/widget/OnItemViewClickedListener;", "getDefaultItemClickedListener", "()Landroidx/leanback/widget/OnItemViewClickedListener;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getResultsAdapter", "loadRows", "Lkotlinx/coroutines/Job;", "query", "", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onQueryTextChange", "", "newQuery", "onQueryTextSubmit", "queryByWords", "words", "updateEmtyView", "empty", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SearchFragment.kt */
public final class SearchFragment extends SearchSupportFragment implements SearchSupportFragment.SearchResultProvider {
    /* access modifiers changed from: private */
    public final ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter((Presenter) new ListRowPresenter());

    public boolean onQueryTextChange(String str) {
        Intrinsics.checkNotNullParameter(str, "newQuery");
        return false;
    }

    private final OnItemViewClickedListener getDefaultItemClickedListener() {
        return new SearchFragment$$ExternalSyntheticLambda0(this);
    }

    /* access modifiers changed from: private */
    public static final void _get_defaultItemClickedListener_$lambda$0(SearchFragment searchFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        Intrinsics.checkNotNullParameter(searchFragment, "this$0");
        if (obj instanceof MediaWrapper) {
            TvUtil tvUtil = TvUtil.INSTANCE;
            FragmentActivity requireActivity = searchFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            tvUtil.openMedia(requireActivity, obj);
        } else {
            TvUtil tvUtil2 = TvUtil.INSTANCE;
            FragmentActivity requireActivity2 = searchFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.media.MediaLibraryItem");
            tvUtil2.openAudioCategory(requireActivity2, (MediaLibraryItem) obj);
        }
        searchFragment.requireActivity().finish();
    }

    public void onCreate(Bundle bundle) {
        String stringExtra;
        super.onCreate(bundle);
        setSearchResultProvider(this);
        setOnItemViewClickedListener(getDefaultItemClickedListener());
        Intent intent = requireActivity().getIntent();
        if ((Intrinsics.areEqual((Object) "android.intent.action.SEARCH", (Object) intent.getAction()) || Intrinsics.areEqual((Object) Constants.ACTION_SEARCH_GMS, (Object) intent.getAction())) && (stringExtra = intent.getStringExtra("query")) != null) {
            onQueryTextSubmit(stringExtra);
        }
    }

    public ArrayObjectAdapter getResultsAdapter() {
        return this.rowsAdapter;
    }

    private final void queryByWords(String str) {
        if (str != null) {
            CharSequence charSequence = str;
            if (charSequence.length() != 0) {
                this.rowsAdapter.clear();
                if (charSequence.length() > 0) {
                    loadRows(str);
                }
            }
        }
    }

    public boolean onQueryTextSubmit(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        queryByWords(str);
        return true;
    }

    private final Job loadRows(String str) {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new SearchFragment$loadRows$1(str, this, (Continuation<? super SearchFragment$loadRows$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void updateEmtyView(boolean z) {
        FragmentActivity activity = getActivity();
        SearchActivity searchActivity = activity instanceof SearchActivity ? (SearchActivity) activity : null;
        if (searchActivity != null) {
            searchActivity.updateEmptyView(z);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            setSearchQuery(intent, true);
        }
    }
}
