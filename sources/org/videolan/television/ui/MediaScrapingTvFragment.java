package org.videolan.television.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.SearchSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.models.identify.MoviepediaMedia;
import org.videolan.moviepedia.viewmodel.MediaScrapingModel;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020 H\u0016J\u0012\u0010#\u001a\u00020\u00142\b\u0010$\u001a\u0004\u0018\u00010 H\u0002J\u0006\u0010%\u001a\u00020\u0014J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u001eH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvFragment;", "Landroidx/leanback/app/SearchSupportFragment;", "Landroidx/leanback/app/SearchSupportFragment$SearchResultProvider;", "()V", "defaultItemClickedListener", "Landroidx/leanback/widget/OnItemViewClickedListener;", "getDefaultItemClickedListener", "()Landroidx/leanback/widget/OnItemViewClickedListener;", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setMedia", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "viewModel", "Lorg/videolan/moviepedia/viewmodel/MediaScrapingModel;", "getResultsAdapter", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onQueryTextChange", "", "newQuery", "", "onQueryTextSubmit", "query", "queryByWords", "words", "refresh", "updateEmptyView", "empty", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvFragment.kt */
public final class MediaScrapingTvFragment extends SearchSupportFragment implements SearchSupportFragment.SearchResultProvider {
    public MediaWrapper media;
    /* access modifiers changed from: private */
    public final ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter((Presenter) new ListRowPresenter());
    private MediaScrapingModel viewModel;

    public boolean onQueryTextChange(String str) {
        Intrinsics.checkNotNullParameter(str, "newQuery");
        return false;
    }

    public final MediaWrapper getMedia() {
        MediaWrapper mediaWrapper = this.media;
        if (mediaWrapper != null) {
            return mediaWrapper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("media");
        return null;
    }

    public final void setMedia(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "<set-?>");
        this.media = mediaWrapper;
    }

    private final OnItemViewClickedListener getDefaultItemClickedListener() {
        return new MediaScrapingTvFragment$$ExternalSyntheticLambda0(this);
    }

    /* access modifiers changed from: private */
    public static final void _get_defaultItemClickedListener_$lambda$0(MediaScrapingTvFragment mediaScrapingTvFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        Intrinsics.checkNotNullParameter(mediaScrapingTvFragment, "this$0");
        if (obj instanceof MoviepediaMedia) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(mediaScrapingTvFragment), (CoroutineContext) null, (CoroutineStart) null, new MediaScrapingTvFragment$defaultItemClickedListener$1$1(mediaScrapingTvFragment, obj, (Continuation<? super MediaScrapingTvFragment$defaultItemClickedListener$1$1>) null), 3, (Object) null);
        }
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        String stringExtra;
        super.onCreate(bundle);
        setSearchResultProvider(this);
        setOnItemViewClickedListener(getDefaultItemClickedListener());
        Intent intent = requireActivity().getIntent();
        if ((Intrinsics.areEqual((Object) "android.intent.action.SEARCH", (Object) intent.getAction()) || Intrinsics.areEqual((Object) Constants.ACTION_SEARCH_GMS, (Object) intent.getAction())) && (stringExtra = intent.getStringExtra("query")) != null) {
            onQueryTextSubmit(stringExtra);
        }
        Bundle extras = requireActivity().getIntent().getExtras();
        if (extras != null) {
            bundle = extras;
        } else if (bundle == null) {
            return;
        }
        MediaScrapingModel mediaScrapingModel = null;
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, MediaScrapingTvActivity.MEDIA, MediaWrapper.class);
        } else {
            Parcelable parcelable2 = bundle.getParcelable(MediaScrapingTvActivity.MEDIA);
            if (!(parcelable2 instanceof MediaWrapper)) {
                parcelable2 = null;
            }
            parcelable = (MediaWrapper) parcelable2;
        }
        MediaWrapper mediaWrapper = (MediaWrapper) parcelable;
        if (mediaWrapper != null) {
            setMedia(mediaWrapper);
            ViewModelProvider viewModelProvider = new ViewModelProvider(this);
            String path = getMedia().getUri().getPath();
            if (path == null) {
                path = "";
            }
            this.viewModel = (MediaScrapingModel) viewModelProvider.get(path, MediaScrapingModel.class);
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(requireActivity, true, false, 4, (DefaultConstructorMarker) null));
            MediaScrapingModel mediaScrapingModel2 = this.viewModel;
            if (mediaScrapingModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                mediaScrapingModel2 = null;
            }
            LifecycleOwner lifecycleOwner = this;
            mediaScrapingModel2.getApiResult().observe(lifecycleOwner, new MediaScrapingTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MediaScrapingTvFragment$onCreate$2(arrayObjectAdapter, this)));
            MediaScrapingModel mediaScrapingModel3 = this.viewModel;
            if (mediaScrapingModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                mediaScrapingModel3 = null;
            }
            mediaScrapingModel3.getExceptionLiveData().observe(lifecycleOwner, new MediaScrapingTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MediaScrapingTvFragment$onCreate$3(this)));
            setSearchQuery(getMedia().getTitle(), false);
            MediaScrapingModel mediaScrapingModel4 = this.viewModel;
            if (mediaScrapingModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                mediaScrapingModel = mediaScrapingModel4;
            }
            Uri uri = getMedia().getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            mediaScrapingModel.search(uri);
        }
    }

    public ArrayObjectAdapter getResultsAdapter() {
        return this.rowsAdapter;
    }

    private final void queryByWords(String str) {
        if (str != null && str.length() >= 3) {
            this.rowsAdapter.clear();
            MediaScrapingModel mediaScrapingModel = this.viewModel;
            if (mediaScrapingModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                mediaScrapingModel = null;
            }
            mediaScrapingModel.search(str);
        }
    }

    public boolean onQueryTextSubmit(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        queryByWords(str);
        return true;
    }

    /* access modifiers changed from: private */
    public final void updateEmptyView(boolean z) {
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

    public final void refresh() {
        MediaScrapingModel mediaScrapingModel = this.viewModel;
        if (mediaScrapingModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            mediaScrapingModel = null;
        }
        Uri uri = getMedia().getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        mediaScrapingModel.search(uri);
    }
}
