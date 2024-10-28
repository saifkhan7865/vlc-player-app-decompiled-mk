package org.videolan.television.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.DetailsSupportFragment;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SparseArrayObjectAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.database.models.MediaImage;
import org.videolan.moviepedia.database.models.MediaImageType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.provider.MediaScrapingTvshowProvider;
import org.videolan.moviepedia.viewmodel.MediaMetadataFull;
import org.videolan.moviepedia.viewmodel.MediaMetadataModel;
import org.videolan.moviepedia.viewmodel.Season;
import org.videolan.vlc.R;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.repository.BrowserFavRepository;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0002\u0016\u0019\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\n\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u0014\u0010&\u001a\u00020!2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u001dH\u0002J\u0012\u0010(\u001a\u00020!2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J0\u0010+\u001a\u00020!2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u0001012\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020!H\u0016J\b\u00105\u001a\u00020!H\u0016J\u0010\u00106\u001a\u00020!2\u0006\u00107\u001a\u00020*H\u0016J\u0012\u00108\u001a\u00020!2\b\u00109\u001a\u0004\u0018\u00010#H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000eX\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvshowDetailsFragment;", "Landroidx/leanback/app/DetailsSupportFragment;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "()V", "actionsAdapter", "Landroidx/leanback/widget/SparseArrayObjectAdapter;", "arrayObjectAdapterPosters", "Landroidx/leanback/widget/ArrayObjectAdapter;", "backgroundManager", "Landroidx/leanback/app/BackgroundManager;", "browserFavRepository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "detailsDescriptionPresenter", "Lorg/videolan/television/ui/TvShowDescriptionPresenter;", "detailsOverview", "Landroidx/leanback/widget/DetailsOverviewRow;", "imageDiffCallback", "org/videolan/television/ui/MediaScrapingTvshowDetailsFragment$imageDiffCallback$1", "Lorg/videolan/television/ui/MediaScrapingTvshowDetailsFragment$imageDiffCallback$1;", "personsDiffCallback", "org/videolan/television/ui/MediaScrapingTvshowDetailsFragment$personsDiffCallback$1", "Lorg/videolan/television/ui/MediaScrapingTvshowDetailsFragment$personsDiffCallback$1;", "rowsAdapter", "showId", "", "viewModel", "Lorg/videolan/moviepedia/viewmodel/MediaMetadataModel;", "buildDetails", "", "tvShow", "Lorg/videolan/moviepedia/viewmodel/MediaMetadataFull;", "getFirstResumableEpisode", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "loadBackdrop", "url", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "onPause", "onResume", "onSaveInstanceState", "outState", "updateMetadata", "tvshow", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
public final class MediaScrapingTvshowDetailsFragment extends DetailsSupportFragment implements CoroutineScope, OnItemViewClickedListener {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    /* access modifiers changed from: private */
    public SparseArrayObjectAdapter actionsAdapter;
    private ArrayObjectAdapter arrayObjectAdapterPosters;
    /* access modifiers changed from: private */
    public BackgroundManager backgroundManager;
    private BrowserFavRepository browserFavRepository;
    private TvShowDescriptionPresenter detailsDescriptionPresenter;
    /* access modifiers changed from: private */
    public DetailsOverviewRow detailsOverview;
    private final MediaScrapingTvshowDetailsFragment$imageDiffCallback$1 imageDiffCallback = new MediaScrapingTvshowDetailsFragment$imageDiffCallback$1();
    private final MediaScrapingTvshowDetailsFragment$personsDiffCallback$1 personsDiffCallback = new MediaScrapingTvshowDetailsFragment$personsDiffCallback$1();
    /* access modifiers changed from: private */
    public ArrayObjectAdapter rowsAdapter;
    private String showId;
    private MediaMetadataModel viewModel;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        BackgroundManager instance = BackgroundManager.getInstance(requireActivity());
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.backgroundManager = instance;
        MediaMetadataModel mediaMetadataModel = null;
        if (instance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            instance = null;
        }
        instance.setAutoReleaseOnStop(false);
        BrowserFavRepository.Companion companion = BrowserFavRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.browserFavRepository = (BrowserFavRepository) companion.getInstance(requireContext);
        this.detailsDescriptionPresenter = new TvShowDescriptionPresenter();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.arrayObjectAdapterPosters = new ArrayObjectAdapter((Presenter) new MediaImageCardPresenter(requireActivity, MediaImageType.POSTER));
        Bundle extras = requireActivity().getIntent().getExtras();
        if (extras != null) {
            bundle = extras;
        } else if (bundle == null) {
            return;
        }
        String string = bundle.getString(MediaScrapingTvshowDetailsActivityKt.TV_SHOW_ID);
        if (string != null) {
            this.showId = string;
            ViewModelStoreOwner viewModelStoreOwner = this;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
            Context context = requireActivity2;
            String str2 = this.showId;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("showId");
                str = null;
            } else {
                str = str2;
            }
            ViewModelProvider viewModelProvider = new ViewModelProvider(viewModelStoreOwner, (ViewModelProvider.Factory) new MediaMetadataModel.Factory(context, (Long) null, str, 2, (DefaultConstructorMarker) null));
            String str3 = this.showId;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("showId");
                str3 = null;
            }
            MediaMetadataModel mediaMetadataModel2 = (MediaMetadataModel) viewModelProvider.get(str3, MediaMetadataModel.class);
            this.viewModel = mediaMetadataModel2;
            if (mediaMetadataModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                mediaMetadataModel = mediaMetadataModel2;
            }
            mediaMetadataModel.getUpdateLiveData().observe(this, new MediaScrapingTvshowDetailsFragmentKt$sam$androidx_lifecycle_Observer$0(new MediaScrapingTvshowDetailsFragment$onCreate$1(this)));
            setOnItemViewClickedListener(this);
        }
    }

    public void onResume() {
        super.onResume();
        BackgroundManager backgroundManager2 = this.backgroundManager;
        BackgroundManager backgroundManager3 = null;
        if (backgroundManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            backgroundManager2 = null;
        }
        if (!backgroundManager2.isAttached()) {
            BackgroundManager backgroundManager4 = this.backgroundManager;
            if (backgroundManager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            } else {
                backgroundManager3 = backgroundManager4;
            }
            backgroundManager3.attachToView(getView());
        }
    }

    public void onPause() {
        BackgroundManager backgroundManager2 = this.backgroundManager;
        if (backgroundManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            backgroundManager2 = null;
        }
        backgroundManager2.release();
        super.onPause();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        String str = this.showId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("showId");
            str = null;
        }
        bundle.putString(MediaScrapingTvshowDetailsActivityKt.TV_SHOW_ID, str);
        super.onSaveInstanceState(bundle);
    }

    public void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        MediaMetadataModel mediaMetadataModel = null;
        if (obj instanceof MediaImage) {
            MediaMetadataModel mediaMetadataModel2 = this.viewModel;
            if (mediaMetadataModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                mediaMetadataModel = mediaMetadataModel2;
            }
            mediaMetadataModel.updateMetadataImage((MediaImage) obj);
        } else if (obj instanceof MediaMetadataWithImages) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new MediaScrapingTvshowDetailsFragment$onItemClicked$1(obj, this, (Continuation<? super MediaScrapingTvshowDetailsFragment$onItemClicked$1>) null), 3, (Object) null);
        }
    }

    static /* synthetic */ void loadBackdrop$default(MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        mediaScrapingTvshowDetailsFragment.loadBackdrop(str);
    }

    private final void loadBackdrop(String str) {
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new MediaScrapingTvshowDetailsFragment$loadBackdrop$1(str, this, (Continuation<? super MediaScrapingTvshowDetailsFragment$loadBackdrop$1>) null));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r4 = r4.getMetadata();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateMetadata(org.videolan.moviepedia.viewmodel.MediaMetadataFull r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0432
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r4 = r17.getMetadata()
            if (r4 == 0) goto L_0x0019
            org.videolan.moviepedia.database.models.MediaMetadata r4 = r4.getMetadata()
            if (r4 == 0) goto L_0x0019
            java.lang.String r4 = r4.getCurrentBackdrop()
            goto L_0x001a
        L_0x0019:
            r4 = r3
        L_0x001a:
            r0.loadBackdrop(r4)
            r4 = r0
            androidx.lifecycle.LifecycleOwner r4 = (androidx.lifecycle.LifecycleOwner) r4
            androidx.lifecycle.LifecycleCoroutineScope r4 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r4)
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$updateMetadata$1$1 r5 = new org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$updateMetadata$1$1
            r5.<init>(r1, r0, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r4.launchWhenStarted(r5)
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r4 = r17.getMetadata()
            if (r4 == 0) goto L_0x003f
            org.videolan.moviepedia.database.models.MediaMetadata r4 = r4.getMetadata()
            if (r4 == 0) goto L_0x003f
            java.lang.String r4 = r4.getTitle()
            goto L_0x0040
        L_0x003f:
            r4 = r3
        L_0x0040:
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0.setTitle(r4)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            androidx.leanback.widget.DetailsOverviewRow r5 = r0.detailsOverview
            if (r5 != 0) goto L_0x0054
            java.lang.String r5 = "detailsOverview"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r5 = r3
        L_0x0054:
            r4.add(r5)
            java.util.List r5 = r17.getSeasons()
            r6 = 0
            java.lang.String r7 = "requireActivity(...)"
            r8 = 36
            if (r5 == 0) goto L_0x00d7
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x0068:
            boolean r11 = r5.hasNext()
            if (r11 == 0) goto L_0x00d7
            java.lang.Object r11 = r5.next()
            org.videolan.moviepedia.viewmodel.Season r11 = (org.videolan.moviepedia.viewmodel.Season) r11
            androidx.leanback.widget.ArrayObjectAdapter r12 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.MetadataCardPresenter r13 = new org.videolan.television.ui.MetadataCardPresenter
            androidx.fragment.app.FragmentActivity r14 = r16.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r7)
            android.app.Activity r14 = (android.app.Activity) r14
            r13.<init>(r14)
            androidx.leanback.widget.Presenter r13 = (androidx.leanback.widget.Presenter) r13
            r12.<init>((androidx.leanback.widget.Presenter) r13)
            java.util.ArrayList r13 = r11.getEpisodes()
            java.util.List r13 = (java.util.List) r13
            org.videolan.television.ui.TvUtil r14 = org.videolan.television.ui.TvUtil.INSTANCE
            androidx.leanback.widget.DiffCallback r14 = r14.getMetadataDiffCallback()
            r12.setItems(r13, r14)
            androidx.leanback.widget.HeaderItem r13 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r14 = r17.getMetadata()
            if (r14 == 0) goto L_0x00b5
            org.videolan.moviepedia.database.models.MediaMetadata r14 = r14.getMetadata()
            if (r14 == 0) goto L_0x00b5
            java.lang.String r14 = r14.getMoviepediaId()
            if (r14 == 0) goto L_0x00b5
            int r15 = kotlin.text.CharsKt.checkRadix(r8)
            long r14 = java.lang.Long.parseLong(r14, r15)
            goto L_0x00b7
        L_0x00b5:
            r14 = 0
        L_0x00b7:
            int r9 = org.videolan.vlc.R.string.season_number
            int r10 = r11.getSeasonNumber()
            java.lang.String r10 = java.lang.String.valueOf(r10)
            java.lang.Object[] r11 = new java.lang.Object[r2]
            r11[r6] = r10
            java.lang.String r9 = r0.getString(r9, r11)
            r13.<init>(r14, r9)
            androidx.leanback.widget.ListRow r9 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r12 = (androidx.leanback.widget.ObjectAdapter) r12
            r9.<init>(r13, r12)
            r4.add(r9)
            goto L_0x0068
        L_0x00d7:
            java.util.List r5 = r17.getWriters()
            java.util.Collection r5 = (java.util.Collection) r5
            if (r5 == 0) goto L_0x0138
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x00e6
            goto L_0x0138
        L_0x00e6:
            androidx.leanback.widget.ArrayObjectAdapter r5 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r9 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r10 = r16.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r7)
            android.app.Activity r10 = (android.app.Activity) r10
            r9.<init>(r10)
            androidx.leanback.widget.Presenter r9 = (androidx.leanback.widget.Presenter) r9
            r5.<init>((androidx.leanback.widget.Presenter) r9)
            java.util.List r9 = r17.getWriters()
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$personsDiffCallback$1 r10 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r10 = (androidx.leanback.widget.DiffCallback) r10
            r5.setItems(r9, r10)
            androidx.leanback.widget.HeaderItem r9 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = r17.getMetadata()
            if (r10 == 0) goto L_0x0123
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r10.getMetadata()
            if (r10 == 0) goto L_0x0123
            java.lang.String r10 = r10.getMoviepediaId()
            if (r10 == 0) goto L_0x0123
            int r11 = kotlin.text.CharsKt.checkRadix(r8)
            long r10 = java.lang.Long.parseLong(r10, r11)
            goto L_0x0125
        L_0x0123:
            r10 = 0
        L_0x0125:
            int r12 = org.videolan.vlc.R.string.written_by
            java.lang.String r12 = r0.getString(r12)
            r9.<init>(r10, r12)
            androidx.leanback.widget.ListRow r10 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r5 = (androidx.leanback.widget.ObjectAdapter) r5
            r10.<init>(r9, r5)
            r4.add(r10)
        L_0x0138:
            java.util.List r5 = r17.getActors()
            java.util.Collection r5 = (java.util.Collection) r5
            if (r5 == 0) goto L_0x0199
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0147
            goto L_0x0199
        L_0x0147:
            androidx.leanback.widget.ArrayObjectAdapter r5 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r9 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r10 = r16.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r7)
            android.app.Activity r10 = (android.app.Activity) r10
            r9.<init>(r10)
            androidx.leanback.widget.Presenter r9 = (androidx.leanback.widget.Presenter) r9
            r5.<init>((androidx.leanback.widget.Presenter) r9)
            java.util.List r9 = r17.getActors()
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$personsDiffCallback$1 r10 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r10 = (androidx.leanback.widget.DiffCallback) r10
            r5.setItems(r9, r10)
            androidx.leanback.widget.HeaderItem r9 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = r17.getMetadata()
            if (r10 == 0) goto L_0x0184
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r10.getMetadata()
            if (r10 == 0) goto L_0x0184
            java.lang.String r10 = r10.getMoviepediaId()
            if (r10 == 0) goto L_0x0184
            int r11 = kotlin.text.CharsKt.checkRadix(r8)
            long r10 = java.lang.Long.parseLong(r10, r11)
            goto L_0x0186
        L_0x0184:
            r10 = 0
        L_0x0186:
            int r12 = org.videolan.vlc.R.string.casting
            java.lang.String r12 = r0.getString(r12)
            r9.<init>(r10, r12)
            androidx.leanback.widget.ListRow r10 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r5 = (androidx.leanback.widget.ObjectAdapter) r5
            r10.<init>(r9, r5)
            r4.add(r10)
        L_0x0199:
            java.util.List r5 = r17.getDirectors()
            java.util.Collection r5 = (java.util.Collection) r5
            if (r5 == 0) goto L_0x01fa
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x01a8
            goto L_0x01fa
        L_0x01a8:
            androidx.leanback.widget.ArrayObjectAdapter r5 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r9 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r10 = r16.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r7)
            android.app.Activity r10 = (android.app.Activity) r10
            r9.<init>(r10)
            androidx.leanback.widget.Presenter r9 = (androidx.leanback.widget.Presenter) r9
            r5.<init>((androidx.leanback.widget.Presenter) r9)
            java.util.List r9 = r17.getDirectors()
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$personsDiffCallback$1 r10 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r10 = (androidx.leanback.widget.DiffCallback) r10
            r5.setItems(r9, r10)
            androidx.leanback.widget.HeaderItem r9 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = r17.getMetadata()
            if (r10 == 0) goto L_0x01e5
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r10.getMetadata()
            if (r10 == 0) goto L_0x01e5
            java.lang.String r10 = r10.getMoviepediaId()
            if (r10 == 0) goto L_0x01e5
            int r11 = kotlin.text.CharsKt.checkRadix(r8)
            long r10 = java.lang.Long.parseLong(r10, r11)
            goto L_0x01e7
        L_0x01e5:
            r10 = 0
        L_0x01e7:
            int r12 = org.videolan.vlc.R.string.directed_by
            java.lang.String r12 = r0.getString(r12)
            r9.<init>(r10, r12)
            androidx.leanback.widget.ListRow r10 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r5 = (androidx.leanback.widget.ObjectAdapter) r5
            r10.<init>(r9, r5)
            r4.add(r10)
        L_0x01fa:
            java.util.List r5 = r17.getProducers()
            java.util.Collection r5 = (java.util.Collection) r5
            if (r5 == 0) goto L_0x025b
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0209
            goto L_0x025b
        L_0x0209:
            androidx.leanback.widget.ArrayObjectAdapter r5 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r9 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r10 = r16.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r7)
            android.app.Activity r10 = (android.app.Activity) r10
            r9.<init>(r10)
            androidx.leanback.widget.Presenter r9 = (androidx.leanback.widget.Presenter) r9
            r5.<init>((androidx.leanback.widget.Presenter) r9)
            java.util.List r9 = r17.getProducers()
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$personsDiffCallback$1 r10 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r10 = (androidx.leanback.widget.DiffCallback) r10
            r5.setItems(r9, r10)
            androidx.leanback.widget.HeaderItem r9 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = r17.getMetadata()
            if (r10 == 0) goto L_0x0246
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r10.getMetadata()
            if (r10 == 0) goto L_0x0246
            java.lang.String r10 = r10.getMoviepediaId()
            if (r10 == 0) goto L_0x0246
            int r11 = kotlin.text.CharsKt.checkRadix(r8)
            long r10 = java.lang.Long.parseLong(r10, r11)
            goto L_0x0248
        L_0x0246:
            r10 = 0
        L_0x0248:
            int r12 = org.videolan.vlc.R.string.produced_by
            java.lang.String r12 = r0.getString(r12)
            r9.<init>(r10, r12)
            androidx.leanback.widget.ListRow r10 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r5 = (androidx.leanback.widget.ObjectAdapter) r5
            r10.<init>(r9, r5)
            r4.add(r10)
        L_0x025b:
            java.util.List r5 = r17.getMusicians()
            java.util.Collection r5 = (java.util.Collection) r5
            if (r5 == 0) goto L_0x02bc
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x026a
            goto L_0x02bc
        L_0x026a:
            androidx.leanback.widget.ArrayObjectAdapter r5 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r9 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r10 = r16.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r7)
            android.app.Activity r10 = (android.app.Activity) r10
            r9.<init>(r10)
            androidx.leanback.widget.Presenter r9 = (androidx.leanback.widget.Presenter) r9
            r5.<init>((androidx.leanback.widget.Presenter) r9)
            java.util.List r9 = r17.getMusicians()
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$personsDiffCallback$1 r10 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r10 = (androidx.leanback.widget.DiffCallback) r10
            r5.setItems(r9, r10)
            androidx.leanback.widget.HeaderItem r9 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = r17.getMetadata()
            if (r10 == 0) goto L_0x02a7
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r10.getMetadata()
            if (r10 == 0) goto L_0x02a7
            java.lang.String r10 = r10.getMoviepediaId()
            if (r10 == 0) goto L_0x02a7
            int r11 = kotlin.text.CharsKt.checkRadix(r8)
            long r10 = java.lang.Long.parseLong(r10, r11)
            goto L_0x02a9
        L_0x02a7:
            r10 = 0
        L_0x02a9:
            int r12 = org.videolan.vlc.R.string.music_by
            java.lang.String r12 = r0.getString(r12)
            r9.<init>(r10, r12)
            androidx.leanback.widget.ListRow r10 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r5 = (androidx.leanback.widget.ObjectAdapter) r5
            r10.<init>(r9, r5)
            r4.add(r10)
        L_0x02bc:
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r5 = r17.getMetadata()
            if (r5 == 0) goto L_0x040f
            java.util.List r9 = r5.getImages()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            boolean r10 = r9 instanceof java.util.Collection
            if (r10 == 0) goto L_0x02d7
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x02d7
            goto L_0x0366
        L_0x02d7:
            java.util.Iterator r9 = r9.iterator()
        L_0x02db:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0366
            java.lang.Object r10 = r9.next()
            org.videolan.moviepedia.database.models.MediaImage r10 = (org.videolan.moviepedia.database.models.MediaImage) r10
            org.videolan.moviepedia.database.models.MediaImageType r10 = r10.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r11 = org.videolan.moviepedia.database.models.MediaImageType.POSTER
            if (r10 != r11) goto L_0x02db
            androidx.leanback.widget.ArrayObjectAdapter r9 = r0.arrayObjectAdapterPosters
            java.lang.String r10 = "arrayObjectAdapterPosters"
            if (r9 != 0) goto L_0x02f9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r9 = r3
        L_0x02f9:
            java.util.List r11 = r5.getImages()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.Collection r12 = (java.util.Collection) r12
            java.util.Iterator r11 = r11.iterator()
        L_0x030a:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x0323
            java.lang.Object r13 = r11.next()
            r14 = r13
            org.videolan.moviepedia.database.models.MediaImage r14 = (org.videolan.moviepedia.database.models.MediaImage) r14
            org.videolan.moviepedia.database.models.MediaImageType r14 = r14.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r15 = org.videolan.moviepedia.database.models.MediaImageType.POSTER
            if (r14 != r15) goto L_0x030a
            r12.add(r13)
            goto L_0x030a
        L_0x0323:
            java.util.List r12 = (java.util.List) r12
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$imageDiffCallback$1 r11 = r0.imageDiffCallback
            androidx.leanback.widget.DiffCallback r11 = (androidx.leanback.widget.DiffCallback) r11
            r9.setItems(r12, r11)
            androidx.leanback.widget.HeaderItem r9 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r11 = r17.getMetadata()
            if (r11 == 0) goto L_0x0349
            org.videolan.moviepedia.database.models.MediaMetadata r11 = r11.getMetadata()
            if (r11 == 0) goto L_0x0349
            java.lang.String r11 = r11.getMoviepediaId()
            if (r11 == 0) goto L_0x0349
            int r12 = kotlin.text.CharsKt.checkRadix(r8)
            long r11 = java.lang.Long.parseLong(r11, r12)
            goto L_0x034b
        L_0x0349:
            r11 = 0
        L_0x034b:
            int r13 = org.videolan.vlc.R.string.posters
            java.lang.String r13 = r0.getString(r13)
            r9.<init>(r11, r13)
            androidx.leanback.widget.ListRow r11 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ArrayObjectAdapter r12 = r0.arrayObjectAdapterPosters
            if (r12 != 0) goto L_0x035e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r12 = r3
        L_0x035e:
            androidx.leanback.widget.ObjectAdapter r12 = (androidx.leanback.widget.ObjectAdapter) r12
            r11.<init>(r9, r12)
            r4.add(r11)
        L_0x0366:
            java.util.List r9 = r5.getImages()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            boolean r10 = r9 instanceof java.util.Collection
            if (r10 == 0) goto L_0x037b
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x037b
            goto L_0x040f
        L_0x037b:
            java.util.Iterator r9 = r9.iterator()
        L_0x037f:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x040f
            java.lang.Object r10 = r9.next()
            org.videolan.moviepedia.database.models.MediaImage r10 = (org.videolan.moviepedia.database.models.MediaImage) r10
            org.videolan.moviepedia.database.models.MediaImageType r10 = r10.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r11 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP
            if (r10 != r11) goto L_0x037f
            androidx.leanback.widget.ArrayObjectAdapter r9 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.MediaImageCardPresenter r10 = new org.videolan.television.ui.MediaImageCardPresenter
            androidx.fragment.app.FragmentActivity r11 = r16.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r7)
            android.app.Activity r11 = (android.app.Activity) r11
            org.videolan.moviepedia.database.models.MediaImageType r7 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP
            r10.<init>(r11, r7)
            androidx.leanback.widget.Presenter r10 = (androidx.leanback.widget.Presenter) r10
            r9.<init>((androidx.leanback.widget.Presenter) r10)
            java.util.List r5 = r5.getImages()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.Iterator r5 = r5.iterator()
        L_0x03bb:
            boolean r10 = r5.hasNext()
            if (r10 == 0) goto L_0x03d4
            java.lang.Object r10 = r5.next()
            r11 = r10
            org.videolan.moviepedia.database.models.MediaImage r11 = (org.videolan.moviepedia.database.models.MediaImage) r11
            org.videolan.moviepedia.database.models.MediaImageType r11 = r11.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r12 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP
            if (r11 != r12) goto L_0x03bb
            r7.add(r10)
            goto L_0x03bb
        L_0x03d4:
            java.util.List r7 = (java.util.List) r7
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$imageDiffCallback$1 r5 = r0.imageDiffCallback
            androidx.leanback.widget.DiffCallback r5 = (androidx.leanback.widget.DiffCallback) r5
            r9.setItems(r7, r5)
            androidx.leanback.widget.HeaderItem r5 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r1 = r17.getMetadata()
            if (r1 == 0) goto L_0x03fa
            org.videolan.moviepedia.database.models.MediaMetadata r1 = r1.getMetadata()
            if (r1 == 0) goto L_0x03fa
            java.lang.String r1 = r1.getMoviepediaId()
            if (r1 == 0) goto L_0x03fa
            int r7 = kotlin.text.CharsKt.checkRadix(r8)
            long r7 = java.lang.Long.parseLong(r1, r7)
            goto L_0x03fc
        L_0x03fa:
            r7 = 0
        L_0x03fc:
            int r1 = org.videolan.vlc.R.string.backdrops
            java.lang.String r1 = r0.getString(r1)
            r5.<init>(r7, r1)
            androidx.leanback.widget.ListRow r1 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r9 = (androidx.leanback.widget.ObjectAdapter) r9
            r1.<init>(r5, r9)
            r4.add(r1)
        L_0x040f:
            androidx.leanback.widget.ArrayObjectAdapter r1 = r0.rowsAdapter
            java.lang.String r5 = "rowsAdapter"
            if (r1 != 0) goto L_0x0419
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r1 = r3
        L_0x0419:
            java.util.List r4 = (java.util.List) r4
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$updateMetadata$1$4 r7 = new org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$updateMetadata$1$4
            r7.<init>()
            androidx.leanback.widget.DiffCallback r7 = (androidx.leanback.widget.DiffCallback) r7
            r1.setItems(r4, r7)
            androidx.leanback.widget.ArrayObjectAdapter r1 = r0.rowsAdapter
            if (r1 != 0) goto L_0x042d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L_0x042e
        L_0x042d:
            r3 = r1
        L_0x042e:
            r3.notifyItemRangeChanged(r6, r2)
            goto L_0x0435
        L_0x0432:
            loadBackdrop$default(r0, r3, r2, r3)
        L_0x0435:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaScrapingTvshowDetailsFragment.updateMetadata(org.videolan.moviepedia.viewmodel.MediaMetadataFull):void");
    }

    /* access modifiers changed from: private */
    public final void buildDetails(MediaMetadataFull mediaMetadataFull) {
        ClassPresenterSelector classPresenterSelector = new ClassPresenterSelector();
        TvShowDescriptionPresenter tvShowDescriptionPresenter = this.detailsDescriptionPresenter;
        if (tvShowDescriptionPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("detailsDescriptionPresenter");
            tvShowDescriptionPresenter = null;
        }
        FullWidthDetailsOverviewRowPresenter fullWidthDetailsOverviewRowPresenter = new FullWidthDetailsOverviewRowPresenter(tvShowDescriptionPresenter);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        VideoDetailsPresenter videoDetailsPresenter = new VideoDetailsPresenter(requireActivity, KextensionsKt.getScreenWidth(requireActivity2));
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
        this.detailsOverview = new DetailsOverviewRow(mediaMetadataFull);
        fullWidthDetailsOverviewRowPresenter.setBackgroundColor(ContextCompat.getColor(requireActivity3, R.color.orange500));
        fullWidthDetailsOverviewRowPresenter.setOnActionClickedListener(new MediaScrapingTvshowDetailsFragment$$ExternalSyntheticLambda0(requireActivity3, this));
        classPresenterSelector.addClassPresenter(DetailsOverviewRow.class, fullWidthDetailsOverviewRowPresenter);
        classPresenterSelector.addClassPresenter(VideoDetailsOverviewRow.class, videoDetailsPresenter);
        classPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        this.rowsAdapter = new ArrayObjectAdapter((PresenterSelector) classPresenterSelector);
        LifecycleOwner lifecycleOwner = this;
        LifecycleOwnerKt.getLifecycleScope(lifecycleOwner).launchWhenStarted(new MediaScrapingTvshowDetailsFragment$buildDetails$2(this, (Continuation<? super MediaScrapingTvshowDetailsFragment$buildDetails$2>) null));
        LifecycleOwnerKt.getLifecycleScope(lifecycleOwner).launchWhenStarted(new MediaScrapingTvshowDetailsFragment$buildDetails$3(requireActivity3, this, (Continuation<? super MediaScrapingTvshowDetailsFragment$buildDetails$3>) null));
    }

    /* access modifiers changed from: private */
    public static final void buildDetails$lambda$8(FragmentActivity fragmentActivity, MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment, Action action) {
        FragmentActivity fragmentActivity2 = fragmentActivity;
        MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment2 = mediaScrapingTvshowDetailsFragment;
        Intrinsics.checkNotNullParameter(fragmentActivity2, "$activity");
        Intrinsics.checkNotNullParameter(mediaScrapingTvshowDetailsFragment2, "this$0");
        int id = (int) action.getId();
        List<Season> list = null;
        if (id == 1) {
            MediaUtils mediaUtils = MediaUtils.INSTANCE;
            Context context = fragmentActivity2;
            MediaMetadataModel mediaMetadataModel = mediaScrapingTvshowDetailsFragment2.viewModel;
            if (mediaMetadataModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                mediaMetadataModel = null;
            }
            MediaScrapingTvshowProvider provider = mediaMetadataModel.getProvider();
            MediaMetadataModel mediaMetadataModel2 = mediaScrapingTvshowDetailsFragment2.viewModel;
            if (mediaMetadataModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                mediaMetadataModel2 = null;
            }
            MediaMetadataFull value = mediaMetadataModel2.getUpdateLiveData().getValue();
            if (value != null) {
                list = value.getSeasons();
            }
            MediaUtils.openList$default(mediaUtils, context, provider.getResumeMedias(list), 0, false, 8, (Object) null);
        } else if (id == 2) {
            TvUtil tvUtil = TvUtil.INSTANCE;
            Activity activity = fragmentActivity2;
            MediaMetadataModel mediaMetadataModel3 = mediaScrapingTvshowDetailsFragment2.viewModel;
            if (mediaMetadataModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                mediaMetadataModel3 = null;
            }
            MediaScrapingTvshowProvider provider2 = mediaMetadataModel3.getProvider();
            MediaMetadataModel mediaMetadataModel4 = mediaScrapingTvshowDetailsFragment2.viewModel;
            if (mediaMetadataModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                mediaMetadataModel4 = null;
            }
            MediaMetadataFull value2 = mediaMetadataModel4.getUpdateLiveData().getValue();
            if (value2 != null) {
                list = value2.getSeasons();
            }
            TvUtil.playMedia$default(tvUtil, activity, provider2.getAllMedias(list), 0, 4, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public final MediaMetadataWithImages getFirstResumableEpisode() {
        MediaMetadataModel mediaMetadataModel = this.viewModel;
        if (mediaMetadataModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            mediaMetadataModel = null;
        }
        MediaMetadataFull value = mediaMetadataModel.getUpdateLiveData().getValue();
        List<Season> seasons = value != null ? value.getSeasons() : null;
        if (seasons != null) {
            for (Season episodes : seasons) {
                Iterator it = episodes.getEpisodes().iterator();
                while (true) {
                    if (it.hasNext()) {
                        MediaMetadataWithImages mediaMetadataWithImages = (MediaMetadataWithImages) it.next();
                        MediaWrapper media = mediaMetadataWithImages.getMedia();
                        if (media != null && media.getSeen() < 1) {
                            return mediaMetadataWithImages;
                        }
                    }
                }
            }
        }
        return null;
    }
}
