package org.videolan.television.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
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
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.database.models.MediaImage;
import org.videolan.moviepedia.database.models.MediaImageType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.repository.MediaMetadataRepository;
import org.videolan.moviepedia.repository.MediaPersonRepository;
import org.videolan.moviepedia.viewmodel.MediaMetadataFull;
import org.videolan.moviepedia.viewmodel.MediaMetadataModel;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.R;
import org.videolan.vlc.repository.BrowserFavRepository;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000*\u0002\u0018\"\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010+\u001a\u00020,H\u0002J\u0014\u0010-\u001a\u00020,2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/H\u0002J\u0012\u00100\u001a\u00020,2\b\u00101\u001a\u0004\u0018\u000102H\u0016J0\u00103\u001a\u00020,2\b\u00104\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u0001072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020,H\u0016J\b\u0010=\u001a\u00020,H\u0016J\u0010\u0010>\u001a\u00020,2\u0006\u0010?\u001a\u000202H\u0016J\u0012\u0010@\u001a\u00020,2\b\u0010A\u001a\u0004\u0018\u00010BH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000eX\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\"X\u0004¢\u0006\u0004\n\u0002\u0010#R\u000e\u0010$\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020&8BX\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b'\u0010(¨\u0006C"}, d2 = {"Lorg/videolan/television/ui/MediaItemDetailsFragment;", "Landroidx/leanback/app/DetailsSupportFragment;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "()V", "actionsAdapter", "Landroidx/leanback/widget/SparseArrayObjectAdapter;", "arrayObjectAdapterPosters", "Landroidx/leanback/widget/ArrayObjectAdapter;", "backgroundManager", "Landroidx/leanback/app/BackgroundManager;", "browserFavRepository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "detailsDescriptionPresenter", "Lorg/videolan/television/ui/DetailsDescriptionPresenter;", "detailsOverview", "Landroidx/leanback/widget/DetailsOverviewRow;", "fromHistory", "", "imageDiffCallback", "org/videolan/television/ui/MediaItemDetailsFragment$imageDiffCallback$1", "Lorg/videolan/television/ui/MediaItemDetailsFragment$imageDiffCallback$1;", "mediaMetadataModel", "Lorg/videolan/moviepedia/viewmodel/MediaMetadataModel;", "mediaMetadataRepository", "Lorg/videolan/moviepedia/repository/MediaMetadataRepository;", "mediaPersonRepository", "Lorg/videolan/moviepedia/repository/MediaPersonRepository;", "mediaStarted", "personsDiffCallback", "org/videolan/television/ui/MediaItemDetailsFragment$personsDiffCallback$1", "Lorg/videolan/television/ui/MediaItemDetailsFragment$personsDiffCallback$1;", "rowsAdapter", "viewModel", "Lorg/videolan/television/ui/MediaItemDetailsModel;", "getViewModel", "()Lorg/videolan/television/ui/MediaItemDetailsModel;", "viewModel$delegate", "Lkotlin/Lazy;", "buildDetails", "", "loadBackdrop", "url", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "onPause", "onResume", "onSaveInstanceState", "outState", "updateMetadata", "mediaMetadataFull", "Lorg/videolan/moviepedia/viewmodel/MediaMetadataFull;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaItemDetailsFragment.kt */
public final class MediaItemDetailsFragment extends DetailsSupportFragment implements CoroutineScope, OnItemViewClickedListener {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    /* access modifiers changed from: private */
    public final SparseArrayObjectAdapter actionsAdapter;
    private ArrayObjectAdapter arrayObjectAdapterPosters;
    /* access modifiers changed from: private */
    public BackgroundManager backgroundManager;
    /* access modifiers changed from: private */
    public BrowserFavRepository browserFavRepository;
    private DetailsDescriptionPresenter detailsDescriptionPresenter;
    /* access modifiers changed from: private */
    public DetailsOverviewRow detailsOverview;
    /* access modifiers changed from: private */
    public boolean fromHistory;
    private final MediaItemDetailsFragment$imageDiffCallback$1 imageDiffCallback;
    private MediaMetadataModel mediaMetadataModel;
    private MediaMetadataRepository mediaMetadataRepository;
    private MediaPersonRepository mediaPersonRepository;
    private boolean mediaStarted;
    private final MediaItemDetailsFragment$personsDiffCallback$1 personsDiffCallback;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter rowsAdapter;
    private final Lazy viewModel$delegate;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public MediaItemDetailsFragment() {
        Fragment fragment = this;
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(MediaItemDetailsModel.class), new MediaItemDetailsFragment$special$$inlined$activityViewModels$default$1(fragment), new MediaItemDetailsFragment$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new MediaItemDetailsFragment$special$$inlined$activityViewModels$default$3(fragment));
        this.actionsAdapter = new SparseArrayObjectAdapter();
        this.imageDiffCallback = new MediaItemDetailsFragment$imageDiffCallback$1();
        this.personsDiffCallback = new MediaItemDetailsFragment$personsDiffCallback$1();
    }

    /* access modifiers changed from: private */
    public final MediaItemDetailsModel getViewModel() {
        return (MediaItemDetailsModel) this.viewModel$delegate.getValue();
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        Parcelable parcelable2;
        super.onCreate(bundle);
        BackgroundManager instance = BackgroundManager.getInstance(requireActivity());
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.backgroundManager = instance;
        MediaMetadataModel mediaMetadataModel2 = null;
        if (instance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            instance = null;
        }
        instance.setAutoReleaseOnStop(false);
        BrowserFavRepository.Companion companion = BrowserFavRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.browserFavRepository = (BrowserFavRepository) companion.getInstance(requireContext);
        getViewModel().setMediaStarted(false);
        this.detailsDescriptionPresenter = new DetailsDescriptionPresenter();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.arrayObjectAdapterPosters = new ArrayObjectAdapter((Presenter) new MediaImageCardPresenter(requireActivity, MediaImageType.POSTER));
        Bundle extras = requireActivity().getIntent().getExtras();
        if (extras != null) {
            bundle = extras;
        } else if (bundle == null) {
            return;
        }
        MediaItemDetailsModel viewModel = getViewModel();
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, "item", MediaItemDetails.class);
        } else {
            Parcelable parcelable3 = bundle.getParcelable("item");
            if (!(parcelable3 instanceof MediaItemDetails)) {
                parcelable3 = null;
            }
            parcelable = (MediaItemDetails) parcelable3;
        }
        MediaItemDetails mediaItemDetails = (MediaItemDetails) parcelable;
        if (mediaItemDetails != null) {
            viewModel.setMediaItemDetails(mediaItemDetails);
            boolean containsKey = bundle.containsKey("media");
            this.fromHistory = bundle.getBoolean(MediaItemDetailsFragmentKt.EXTRA_FROM_HISTORY, false);
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable2 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, "media", Parcelable.class);
            } else {
                parcelable2 = bundle.getParcelable("media");
                if (!(parcelable2 instanceof Parcelable)) {
                    parcelable2 = null;
                }
            }
            if (parcelable2 == null) {
                parcelable2 = MLServiceLocator.getAbstractMediaWrapper(AndroidUtil.LocationToUri(getViewModel().getMediaItemDetails().getLocation()));
            }
            Intrinsics.checkNotNull(parcelable2, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            MediaWrapper mediaWrapper = (MediaWrapper) parcelable2;
            getViewModel().setMedia(mediaWrapper);
            if (!containsKey) {
                getViewModel().getMedia().setDisplayTitle(getViewModel().getMediaItemDetails().getTitle());
            }
            setTitle(getViewModel().getMedia().getTitle());
            MediaMetadataRepository.Companion companion2 = MediaMetadataRepository.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
            this.mediaMetadataRepository = (MediaMetadataRepository) companion2.getInstance(requireContext2);
            MediaPersonRepository.Companion companion3 = MediaPersonRepository.Companion;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext(...)");
            this.mediaPersonRepository = (MediaPersonRepository) companion3.getInstance(requireContext3);
            this.mediaStarted = false;
            buildDetails();
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
            ViewModelProvider viewModelProvider = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) new MediaMetadataModel.Factory(requireActivity2, Long.valueOf(mediaWrapper.getId()), (String) null, 4, (DefaultConstructorMarker) null));
            String path = mediaWrapper.getUri().getPath();
            if (path == null) {
                path = "";
            }
            MediaMetadataModel mediaMetadataModel3 = (MediaMetadataModel) viewModelProvider.get(path, MediaMetadataModel.class);
            this.mediaMetadataModel = mediaMetadataModel3;
            if (mediaMetadataModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaMetadataModel");
                mediaMetadataModel3 = null;
            }
            LifecycleOwner lifecycleOwner = this;
            mediaMetadataModel3.getUpdateLiveData().observe(lifecycleOwner, new MediaItemDetailsFragment$$ExternalSyntheticLambda0(this));
            getViewModel().getBrowserFavUpdated().observe(lifecycleOwner, new MediaItemDetailsFragment$$ExternalSyntheticLambda1(this));
            MediaMetadataModel mediaMetadataModel4 = this.mediaMetadataModel;
            if (mediaMetadataModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaMetadataModel");
            } else {
                mediaMetadataModel2 = mediaMetadataModel4;
            }
            mediaMetadataModel2.getNextEpisode().observe(lifecycleOwner, new MediaItemDetailsFragment$$ExternalSyntheticLambda2(this));
            setOnItemViewClickedListener(this);
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(MediaItemDetailsFragment mediaItemDetailsFragment, MediaMetadataFull mediaMetadataFull) {
        Intrinsics.checkNotNullParameter(mediaItemDetailsFragment, "this$0");
        mediaItemDetailsFragment.updateMetadata(mediaMetadataFull);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(MediaItemDetailsFragment mediaItemDetailsFragment, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaItemDetailsFragment, "this$0");
        Intent intent = new Intent(mediaItemDetailsFragment.requireActivity(), DetailsActivity.class);
        intent.putExtra("media", mediaWrapper);
        intent.putExtra("item", new MediaItemDetails(mediaWrapper.getTitle(), mediaWrapper.getArtist(), mediaWrapper.getAlbum(), mediaWrapper.getLocation(), mediaWrapper.getArtworkURL()));
        mediaItemDetailsFragment.startActivity(intent);
        mediaItemDetailsFragment.requireActivity().finish();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(MediaItemDetailsFragment mediaItemDetailsFragment, MediaMetadataWithImages mediaMetadataWithImages) {
        Intrinsics.checkNotNullParameter(mediaItemDetailsFragment, "this$0");
        if (mediaMetadataWithImages != null) {
            mediaItemDetailsFragment.actionsAdapter.set(2, new Action(2, mediaItemDetailsFragment.getString(R.string.next_episode)));
            SparseArrayObjectAdapter sparseArrayObjectAdapter = mediaItemDetailsFragment.actionsAdapter;
            sparseArrayObjectAdapter.notifyArrayItemRangeChanged(0, sparseArrayObjectAdapter.size());
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
        Context context;
        BackgroundManager backgroundManager2 = this.backgroundManager;
        if (backgroundManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            backgroundManager2 = null;
        }
        backgroundManager2.release();
        super.onPause();
        if (getViewModel().getMediaStarted() && (context = getContext()) != null) {
            context.sendBroadcast(new Intent(Constants.ACTION_REMOTE_STOP));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putParcelable("item", getViewModel().getMediaItemDetails());
        bundle.putParcelable("media", getViewModel().getMedia());
        super.onSaveInstanceState(bundle);
    }

    public void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        if (obj instanceof MediaImage) {
            MediaMetadataModel mediaMetadataModel2 = this.mediaMetadataModel;
            if (mediaMetadataModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaMetadataModel");
                mediaMetadataModel2 = null;
            }
            mediaMetadataModel2.updateMetadataImage((MediaImage) obj);
        }
    }

    static /* synthetic */ void loadBackdrop$default(MediaItemDetailsFragment mediaItemDetailsFragment, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        mediaItemDetailsFragment.loadBackdrop(str);
    }

    private final void loadBackdrop(String str) {
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new MediaItemDetailsFragment$loadBackdrop$1(str, this, (Continuation<? super MediaItemDetailsFragment$loadBackdrop$1>) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        r6 = r6.getMetadata();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updateMetadata(org.videolan.moviepedia.viewmodel.MediaMetadataFull r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x03cc
            org.videolan.television.ui.DetailsDescriptionPresenter r4 = r0.detailsDescriptionPresenter
            if (r4 != 0) goto L_0x0012
            java.lang.String r4 = "detailsDescriptionPresenter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r4 = r3
        L_0x0012:
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r5 = r18.getMetadata()
            r4.setMetadata(r5)
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r4 = r18.getMetadata()
            r5 = 0
            if (r4 == 0) goto L_0x002f
            org.videolan.moviepedia.database.models.MediaMetadata r4 = r4.getMetadata()
            if (r4 == 0) goto L_0x002f
            java.lang.String r4 = r4.getCurrentBackdrop()
            r0.loadBackdrop(r4)
            r4 = 1
            goto L_0x0030
        L_0x002f:
            r4 = 0
        L_0x0030:
            r6 = r0
            androidx.lifecycle.LifecycleOwner r6 = (androidx.lifecycle.LifecycleOwner) r6
            androidx.lifecycle.LifecycleCoroutineScope r6 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r6)
            org.videolan.television.ui.MediaItemDetailsFragment$updateMetadata$1$2 r7 = new org.videolan.television.ui.MediaItemDetailsFragment$updateMetadata$1$2
            r7.<init>(r1, r0, r3)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r6.launchWhenStarted(r7)
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r6 = r18.getMetadata()
            if (r6 == 0) goto L_0x0052
            org.videolan.moviepedia.database.models.MediaMetadata r6 = r6.getMetadata()
            if (r6 == 0) goto L_0x0052
            java.lang.String r6 = r6.getTitle()
            goto L_0x0053
        L_0x0052:
            r6 = r3
        L_0x0053:
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r0.setTitle(r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            androidx.leanback.widget.DetailsOverviewRow r7 = r0.detailsOverview
            if (r7 != 0) goto L_0x0067
            java.lang.String r7 = "detailsOverview"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r7 = r3
        L_0x0067:
            r6.add(r7)
            java.util.List r7 = r18.getWriters()
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.String r8 = "requireActivity(...)"
            r9 = 36
            if (r7 == 0) goto L_0x00cf
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x007d
            goto L_0x00cf
        L_0x007d:
            androidx.leanback.widget.ArrayObjectAdapter r7 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r12 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r13 = r17.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r8)
            android.app.Activity r13 = (android.app.Activity) r13
            r12.<init>(r13)
            androidx.leanback.widget.Presenter r12 = (androidx.leanback.widget.Presenter) r12
            r7.<init>((androidx.leanback.widget.Presenter) r12)
            java.util.List r12 = r18.getWriters()
            org.videolan.television.ui.MediaItemDetailsFragment$personsDiffCallback$1 r13 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r13 = (androidx.leanback.widget.DiffCallback) r13
            r7.setItems(r12, r13)
            androidx.leanback.widget.HeaderItem r12 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r13 = r18.getMetadata()
            if (r13 == 0) goto L_0x00ba
            org.videolan.moviepedia.database.models.MediaMetadata r13 = r13.getMetadata()
            if (r13 == 0) goto L_0x00ba
            java.lang.String r13 = r13.getMoviepediaId()
            if (r13 == 0) goto L_0x00ba
            int r14 = kotlin.text.CharsKt.checkRadix(r9)
            long r13 = java.lang.Long.parseLong(r13, r14)
            goto L_0x00bc
        L_0x00ba:
            r13 = 0
        L_0x00bc:
            int r15 = org.videolan.vlc.R.string.written_by
            java.lang.String r15 = r0.getString(r15)
            r12.<init>(r13, r15)
            androidx.leanback.widget.ListRow r13 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r7 = (androidx.leanback.widget.ObjectAdapter) r7
            r13.<init>(r12, r7)
            r6.add(r13)
        L_0x00cf:
            java.util.List r7 = r18.getActors()
            java.util.Collection r7 = (java.util.Collection) r7
            if (r7 == 0) goto L_0x0130
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x00de
            goto L_0x0130
        L_0x00de:
            androidx.leanback.widget.ArrayObjectAdapter r7 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r12 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r13 = r17.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r8)
            android.app.Activity r13 = (android.app.Activity) r13
            r12.<init>(r13)
            androidx.leanback.widget.Presenter r12 = (androidx.leanback.widget.Presenter) r12
            r7.<init>((androidx.leanback.widget.Presenter) r12)
            java.util.List r12 = r18.getActors()
            org.videolan.television.ui.MediaItemDetailsFragment$personsDiffCallback$1 r13 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r13 = (androidx.leanback.widget.DiffCallback) r13
            r7.setItems(r12, r13)
            androidx.leanback.widget.HeaderItem r12 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r13 = r18.getMetadata()
            if (r13 == 0) goto L_0x011b
            org.videolan.moviepedia.database.models.MediaMetadata r13 = r13.getMetadata()
            if (r13 == 0) goto L_0x011b
            java.lang.String r13 = r13.getMoviepediaId()
            if (r13 == 0) goto L_0x011b
            int r14 = kotlin.text.CharsKt.checkRadix(r9)
            long r13 = java.lang.Long.parseLong(r13, r14)
            goto L_0x011d
        L_0x011b:
            r13 = 0
        L_0x011d:
            int r15 = org.videolan.vlc.R.string.casting
            java.lang.String r15 = r0.getString(r15)
            r12.<init>(r13, r15)
            androidx.leanback.widget.ListRow r13 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r7 = (androidx.leanback.widget.ObjectAdapter) r7
            r13.<init>(r12, r7)
            r6.add(r13)
        L_0x0130:
            java.util.List r7 = r18.getDirectors()
            java.util.Collection r7 = (java.util.Collection) r7
            if (r7 == 0) goto L_0x0191
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x013f
            goto L_0x0191
        L_0x013f:
            androidx.leanback.widget.ArrayObjectAdapter r7 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r12 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r13 = r17.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r8)
            android.app.Activity r13 = (android.app.Activity) r13
            r12.<init>(r13)
            androidx.leanback.widget.Presenter r12 = (androidx.leanback.widget.Presenter) r12
            r7.<init>((androidx.leanback.widget.Presenter) r12)
            java.util.List r12 = r18.getDirectors()
            org.videolan.television.ui.MediaItemDetailsFragment$personsDiffCallback$1 r13 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r13 = (androidx.leanback.widget.DiffCallback) r13
            r7.setItems(r12, r13)
            androidx.leanback.widget.HeaderItem r12 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r13 = r18.getMetadata()
            if (r13 == 0) goto L_0x017c
            org.videolan.moviepedia.database.models.MediaMetadata r13 = r13.getMetadata()
            if (r13 == 0) goto L_0x017c
            java.lang.String r13 = r13.getMoviepediaId()
            if (r13 == 0) goto L_0x017c
            int r14 = kotlin.text.CharsKt.checkRadix(r9)
            long r13 = java.lang.Long.parseLong(r13, r14)
            goto L_0x017e
        L_0x017c:
            r13 = 0
        L_0x017e:
            int r15 = org.videolan.vlc.R.string.directed_by
            java.lang.String r15 = r0.getString(r15)
            r12.<init>(r13, r15)
            androidx.leanback.widget.ListRow r13 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r7 = (androidx.leanback.widget.ObjectAdapter) r7
            r13.<init>(r12, r7)
            r6.add(r13)
        L_0x0191:
            java.util.List r7 = r18.getProducers()
            java.util.Collection r7 = (java.util.Collection) r7
            if (r7 == 0) goto L_0x01f2
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x01a0
            goto L_0x01f2
        L_0x01a0:
            androidx.leanback.widget.ArrayObjectAdapter r7 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r12 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r13 = r17.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r8)
            android.app.Activity r13 = (android.app.Activity) r13
            r12.<init>(r13)
            androidx.leanback.widget.Presenter r12 = (androidx.leanback.widget.Presenter) r12
            r7.<init>((androidx.leanback.widget.Presenter) r12)
            java.util.List r12 = r18.getProducers()
            org.videolan.television.ui.MediaItemDetailsFragment$personsDiffCallback$1 r13 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r13 = (androidx.leanback.widget.DiffCallback) r13
            r7.setItems(r12, r13)
            androidx.leanback.widget.HeaderItem r12 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r13 = r18.getMetadata()
            if (r13 == 0) goto L_0x01dd
            org.videolan.moviepedia.database.models.MediaMetadata r13 = r13.getMetadata()
            if (r13 == 0) goto L_0x01dd
            java.lang.String r13 = r13.getMoviepediaId()
            if (r13 == 0) goto L_0x01dd
            int r14 = kotlin.text.CharsKt.checkRadix(r9)
            long r13 = java.lang.Long.parseLong(r13, r14)
            goto L_0x01df
        L_0x01dd:
            r13 = 0
        L_0x01df:
            int r15 = org.videolan.vlc.R.string.produced_by
            java.lang.String r15 = r0.getString(r15)
            r12.<init>(r13, r15)
            androidx.leanback.widget.ListRow r13 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r7 = (androidx.leanback.widget.ObjectAdapter) r7
            r13.<init>(r12, r7)
            r6.add(r13)
        L_0x01f2:
            java.util.List r7 = r18.getMusicians()
            java.util.Collection r7 = (java.util.Collection) r7
            if (r7 == 0) goto L_0x0253
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0201
            goto L_0x0253
        L_0x0201:
            androidx.leanback.widget.ArrayObjectAdapter r7 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.PersonCardPresenter r12 = new org.videolan.television.ui.PersonCardPresenter
            androidx.fragment.app.FragmentActivity r13 = r17.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r8)
            android.app.Activity r13 = (android.app.Activity) r13
            r12.<init>(r13)
            androidx.leanback.widget.Presenter r12 = (androidx.leanback.widget.Presenter) r12
            r7.<init>((androidx.leanback.widget.Presenter) r12)
            java.util.List r12 = r18.getMusicians()
            org.videolan.television.ui.MediaItemDetailsFragment$personsDiffCallback$1 r13 = r0.personsDiffCallback
            androidx.leanback.widget.DiffCallback r13 = (androidx.leanback.widget.DiffCallback) r13
            r7.setItems(r12, r13)
            androidx.leanback.widget.HeaderItem r12 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r13 = r18.getMetadata()
            if (r13 == 0) goto L_0x023e
            org.videolan.moviepedia.database.models.MediaMetadata r13 = r13.getMetadata()
            if (r13 == 0) goto L_0x023e
            java.lang.String r13 = r13.getMoviepediaId()
            if (r13 == 0) goto L_0x023e
            int r14 = kotlin.text.CharsKt.checkRadix(r9)
            long r13 = java.lang.Long.parseLong(r13, r14)
            goto L_0x0240
        L_0x023e:
            r13 = 0
        L_0x0240:
            int r15 = org.videolan.vlc.R.string.music_by
            java.lang.String r15 = r0.getString(r15)
            r12.<init>(r13, r15)
            androidx.leanback.widget.ListRow r13 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r7 = (androidx.leanback.widget.ObjectAdapter) r7
            r13.<init>(r12, r7)
            r6.add(r13)
        L_0x0253:
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r7 = r18.getMetadata()
            if (r7 == 0) goto L_0x03a7
            java.util.List r12 = r7.getImages()
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            boolean r13 = r12 instanceof java.util.Collection
            if (r13 == 0) goto L_0x026e
            r13 = r12
            java.util.Collection r13 = (java.util.Collection) r13
            boolean r13 = r13.isEmpty()
            if (r13 == 0) goto L_0x026e
            goto L_0x02fe
        L_0x026e:
            java.util.Iterator r12 = r12.iterator()
        L_0x0272:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x02fe
            java.lang.Object r13 = r12.next()
            org.videolan.moviepedia.database.models.MediaImage r13 = (org.videolan.moviepedia.database.models.MediaImage) r13
            org.videolan.moviepedia.database.models.MediaImageType r13 = r13.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r14 = org.videolan.moviepedia.database.models.MediaImageType.POSTER
            if (r13 != r14) goto L_0x0272
            androidx.leanback.widget.ArrayObjectAdapter r12 = r0.arrayObjectAdapterPosters
            java.lang.String r13 = "arrayObjectAdapterPosters"
            if (r12 != 0) goto L_0x0290
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r13)
            r12 = r3
        L_0x0290:
            java.util.List r14 = r7.getImages()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.Collection r15 = (java.util.Collection) r15
            java.util.Iterator r14 = r14.iterator()
        L_0x02a1:
            boolean r16 = r14.hasNext()
            if (r16 == 0) goto L_0x02bb
            java.lang.Object r10 = r14.next()
            r11 = r10
            org.videolan.moviepedia.database.models.MediaImage r11 = (org.videolan.moviepedia.database.models.MediaImage) r11
            org.videolan.moviepedia.database.models.MediaImageType r11 = r11.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r3 = org.videolan.moviepedia.database.models.MediaImageType.POSTER
            if (r11 != r3) goto L_0x02b9
            r15.add(r10)
        L_0x02b9:
            r3 = 0
            goto L_0x02a1
        L_0x02bb:
            java.util.List r15 = (java.util.List) r15
            org.videolan.television.ui.MediaItemDetailsFragment$imageDiffCallback$1 r3 = r0.imageDiffCallback
            androidx.leanback.widget.DiffCallback r3 = (androidx.leanback.widget.DiffCallback) r3
            r12.setItems(r15, r3)
            androidx.leanback.widget.HeaderItem r3 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = r18.getMetadata()
            if (r10 == 0) goto L_0x02e1
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r10.getMetadata()
            if (r10 == 0) goto L_0x02e1
            java.lang.String r10 = r10.getMoviepediaId()
            if (r10 == 0) goto L_0x02e1
            int r11 = kotlin.text.CharsKt.checkRadix(r9)
            long r10 = java.lang.Long.parseLong(r10, r11)
            goto L_0x02e3
        L_0x02e1:
            r10 = 0
        L_0x02e3:
            int r12 = org.videolan.vlc.R.string.posters
            java.lang.String r12 = r0.getString(r12)
            r3.<init>(r10, r12)
            androidx.leanback.widget.ListRow r10 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ArrayObjectAdapter r11 = r0.arrayObjectAdapterPosters
            if (r11 != 0) goto L_0x02f6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r13)
            r11 = 0
        L_0x02f6:
            androidx.leanback.widget.ObjectAdapter r11 = (androidx.leanback.widget.ObjectAdapter) r11
            r10.<init>(r3, r11)
            r6.add(r10)
        L_0x02fe:
            java.util.List r3 = r7.getImages()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            boolean r10 = r3 instanceof java.util.Collection
            if (r10 == 0) goto L_0x0313
            r10 = r3
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x0313
            goto L_0x03a7
        L_0x0313:
            java.util.Iterator r3 = r3.iterator()
        L_0x0317:
            boolean r10 = r3.hasNext()
            if (r10 == 0) goto L_0x03a7
            java.lang.Object r10 = r3.next()
            org.videolan.moviepedia.database.models.MediaImage r10 = (org.videolan.moviepedia.database.models.MediaImage) r10
            org.videolan.moviepedia.database.models.MediaImageType r10 = r10.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r11 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP
            if (r10 != r11) goto L_0x0317
            androidx.leanback.widget.ArrayObjectAdapter r3 = new androidx.leanback.widget.ArrayObjectAdapter
            org.videolan.television.ui.MediaImageCardPresenter r10 = new org.videolan.television.ui.MediaImageCardPresenter
            androidx.fragment.app.FragmentActivity r11 = r17.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r8)
            android.app.Activity r11 = (android.app.Activity) r11
            org.videolan.moviepedia.database.models.MediaImageType r8 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP
            r10.<init>(r11, r8)
            androidx.leanback.widget.Presenter r10 = (androidx.leanback.widget.Presenter) r10
            r3.<init>((androidx.leanback.widget.Presenter) r10)
            java.util.List r7 = r7.getImages()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r7 = r7.iterator()
        L_0x0353:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x036c
            java.lang.Object r10 = r7.next()
            r11 = r10
            org.videolan.moviepedia.database.models.MediaImage r11 = (org.videolan.moviepedia.database.models.MediaImage) r11
            org.videolan.moviepedia.database.models.MediaImageType r11 = r11.getImageType()
            org.videolan.moviepedia.database.models.MediaImageType r12 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP
            if (r11 != r12) goto L_0x0353
            r8.add(r10)
            goto L_0x0353
        L_0x036c:
            java.util.List r8 = (java.util.List) r8
            org.videolan.television.ui.MediaItemDetailsFragment$imageDiffCallback$1 r7 = r0.imageDiffCallback
            androidx.leanback.widget.DiffCallback r7 = (androidx.leanback.widget.DiffCallback) r7
            r3.setItems(r8, r7)
            androidx.leanback.widget.HeaderItem r7 = new androidx.leanback.widget.HeaderItem
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r1 = r18.getMetadata()
            if (r1 == 0) goto L_0x0392
            org.videolan.moviepedia.database.models.MediaMetadata r1 = r1.getMetadata()
            if (r1 == 0) goto L_0x0392
            java.lang.String r1 = r1.getMoviepediaId()
            if (r1 == 0) goto L_0x0392
            int r8 = kotlin.text.CharsKt.checkRadix(r9)
            long r10 = java.lang.Long.parseLong(r1, r8)
            goto L_0x0394
        L_0x0392:
            r10 = 0
        L_0x0394:
            int r1 = org.videolan.vlc.R.string.backdrops
            java.lang.String r1 = r0.getString(r1)
            r7.<init>(r10, r1)
            androidx.leanback.widget.ListRow r1 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.ObjectAdapter r3 = (androidx.leanback.widget.ObjectAdapter) r3
            r1.<init>(r7, r3)
            r6.add(r1)
        L_0x03a7:
            androidx.leanback.widget.ArrayObjectAdapter r1 = r0.rowsAdapter
            java.lang.String r3 = "rowsAdapter"
            if (r1 != 0) goto L_0x03b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r1 = 0
        L_0x03b1:
            java.util.List r6 = (java.util.List) r6
            org.videolan.television.ui.MediaItemDetailsFragment$updateMetadata$1$4 r7 = new org.videolan.television.ui.MediaItemDetailsFragment$updateMetadata$1$4
            r7.<init>()
            androidx.leanback.widget.DiffCallback r7 = (androidx.leanback.widget.DiffCallback) r7
            r1.setItems(r6, r7)
            androidx.leanback.widget.ArrayObjectAdapter r1 = r0.rowsAdapter
            if (r1 != 0) goto L_0x03c5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r1 = 0
        L_0x03c5:
            r1.notifyItemRangeChanged(r5, r2)
            if (r4 != 0) goto L_0x03d0
            r1 = 0
            goto L_0x03cd
        L_0x03cc:
            r1 = r3
        L_0x03cd:
            loadBackdrop$default(r0, r1, r2, r1)
        L_0x03d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaItemDetailsFragment.updateMetadata(org.videolan.moviepedia.viewmodel.MediaMetadataFull):void");
    }

    private final void buildDetails() {
        ClassPresenterSelector classPresenterSelector = new ClassPresenterSelector();
        DetailsDescriptionPresenter detailsDescriptionPresenter2 = this.detailsDescriptionPresenter;
        if (detailsDescriptionPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("detailsDescriptionPresenter");
            detailsDescriptionPresenter2 = null;
        }
        FullWidthDetailsOverviewRowPresenter fullWidthDetailsOverviewRowPresenter = new FullWidthDetailsOverviewRowPresenter(detailsDescriptionPresenter2);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        VideoDetailsPresenter videoDetailsPresenter = new VideoDetailsPresenter(requireActivity, KextensionsKt.getScreenWidth(requireActivity2));
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
        this.detailsOverview = new DetailsOverviewRow(getViewModel().getMediaItemDetails());
        Action action = new Action(4, getString(R.string.favorites_add));
        Action action2 = new Action(14, getString(R.string.favorites_edit));
        Action action3 = new Action(5, getString(R.string.favorites_remove));
        fullWidthDetailsOverviewRowPresenter.setBackgroundColor(ContextCompat.getColor(requireActivity3, R.color.orange500));
        fullWidthDetailsOverviewRowPresenter.setOnActionClickedListener(new MediaItemDetailsFragment$$ExternalSyntheticLambda3(requireActivity3, this, action3, action));
        classPresenterSelector.addClassPresenter(DetailsOverviewRow.class, fullWidthDetailsOverviewRowPresenter);
        classPresenterSelector.addClassPresenter(VideoDetailsOverviewRow.class, videoDetailsPresenter);
        classPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        this.rowsAdapter = new ArrayObjectAdapter((PresenterSelector) classPresenterSelector);
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new MediaItemDetailsFragment$buildDetails$2(this, requireActivity3, action3, action, action2, (Continuation<? super MediaItemDetailsFragment$buildDetails$2>) null));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: org.videolan.moviepedia.viewmodel.MediaMetadataModel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: androidx.leanback.widget.ArrayObjectAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: androidx.leanback.widget.ArrayObjectAdapter} */
    /* JADX WARNING: type inference failed for: r11v0 */
    /* JADX WARNING: type inference failed for: r11v2 */
    /* JADX WARNING: type inference failed for: r11v4 */
    /* JADX WARNING: type inference failed for: r11v6 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void buildDetails$lambda$14(androidx.fragment.app.FragmentActivity r18, org.videolan.television.ui.MediaItemDetailsFragment r19, androidx.leanback.widget.Action r20, androidx.leanback.widget.Action r21, androidx.leanback.widget.Action r22) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            java.lang.String r4 = "$activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "$actionDelete"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            java.lang.String r4 = "$actionAdd"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
            long r4 = r22.getId()
            int r5 = (int) r4
            r4 = 11
            java.lang.String r6 = "file"
            java.lang.String r7 = "rowsAdapter"
            java.lang.String r8 = "requireActivity(...)"
            r9 = 1
            r10 = 0
            r11 = 0
            switch(r5) {
                case 1: goto L_0x0249;
                case 2: goto L_0x0212;
                case 3: goto L_0x01fb;
                case 4: goto L_0x019c;
                case 5: goto L_0x0157;
                case 6: goto L_0x0148;
                case 7: goto L_0x0132;
                case 8: goto L_0x0107;
                case 9: goto L_0x00e7;
                case 10: goto L_0x00c6;
                case 11: goto L_0x002f;
                case 12: goto L_0x00ab;
                case 13: goto L_0x0065;
                case 14: goto L_0x0031;
                default: goto L_0x002f;
            }
        L_0x002f:
            goto L_0x0263
        L_0x0031:
            org.videolan.television.ui.MediaItemDetailsModel r2 = r19.getViewModel()
            r2.setListenForNetworkFav(r9)
            androidx.fragment.app.FragmentActivity r2 = r19.requireActivity()
            android.content.Intent r3 = new android.content.Intent
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Class<org.videolan.vlc.gui.DialogActivity> r4 = org.videolan.vlc.gui.DialogActivity.class
            r3.<init>(r0, r4)
            java.lang.String r0 = "serverDialog"
            android.content.Intent r0 = r3.setAction(r0)
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            android.content.Intent r0 = r0.addFlags(r3)
            org.videolan.television.ui.MediaItemDetailsModel r1 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            android.os.Parcelable r1 = (android.os.Parcelable) r1
            java.lang.String r3 = "extra_media"
            r0.putExtra(r3, r1)
            r2.startActivity(r0)
            goto L_0x0263
        L_0x0065:
            org.videolan.television.ui.MediaItemDetailsModel r1 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            android.net.Uri r1 = r1.getUri()
            android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.retrieveParent(r1)
            if (r1 == 0) goto L_0x0263
            android.content.Intent r2 = new android.content.Intent
            r3 = r0
            android.content.Context r3 = (android.content.Context) r3
            java.lang.Class<org.videolan.television.ui.browser.VerticalGridActivity> r4 = org.videolan.television.ui.browser.VerticalGridActivity.class
            r2.<init>(r3, r4)
            java.lang.String r3 = r1.getScheme()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x008e
            r3 = 4
            goto L_0x0090
        L_0x008e:
            r3 = 3
        L_0x0090:
            java.lang.String r5 = "browser_type"
            r2.putExtra(r5, r3)
            java.lang.String r3 = "favorite_title"
            java.lang.String r4 = r1.getLastPathSegment()
            r2.putExtra(r3, r4)
            r2.setData(r1)
            r1 = 335544320(0x14000000, float:6.4623485E-27)
            r2.addFlags(r1)
            r0.startActivity(r2)
            goto L_0x0263
        L_0x00ab:
            r0 = r1
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            androidx.lifecycle.LifecycleCoroutineScope r0 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r0)
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$1 r2 = new org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$1
            r2.<init>(r1, r11)
            r4 = r2
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 3
            r6 = 0
            r2 = 0
            r3 = 0
            r1 = r0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, r2, r3, r4, r5, r6)
            goto L_0x0263
        L_0x00c6:
            android.content.Intent r0 = new android.content.Intent
            androidx.fragment.app.FragmentActivity r2 = r19.requireActivity()
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Class<org.videolan.television.ui.MediaScrapingTvActivity> r3 = org.videolan.television.ui.MediaScrapingTvActivity.class
            r0.<init>(r2, r3)
            org.videolan.television.ui.MediaItemDetailsModel r2 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r2.getMedia()
            android.os.Parcelable r2 = (android.os.Parcelable) r2
            java.lang.String r3 = "MEDIA"
            r0.putExtra(r3, r2)
            r1.startActivity(r0)
            goto L_0x0263
        L_0x00e7:
            org.videolan.vlc.gui.helpers.UiTools r0 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r19.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r8)
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r3 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[r9]
            org.videolan.television.ui.MediaItemDetailsModel r1 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            r3[r10] = r1
            java.util.ArrayList r1 = kotlin.collections.CollectionsKt.arrayListOf(r3)
            java.util.List r1 = (java.util.List) r1
            r0.addToPlaylist(r2, r1)
            goto L_0x0263
        L_0x0107:
            org.videolan.television.ui.MediaItemDetailsModel r2 = r19.getViewModel()
            r2.setMediaStarted(r10)
            org.videolan.vlc.gui.video.VideoPlayerActivity$Companion r2 = org.videolan.vlc.gui.video.VideoPlayerActivity.Companion
            androidx.fragment.app.FragmentActivity r3 = r19.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r8)
            android.content.Context r3 = (android.content.Context) r3
            org.videolan.television.ui.MediaItemDetailsModel r1 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            android.net.Uri r1 = r1.getUri()
            java.lang.String r4 = "getUri(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            r2.start((android.content.Context) r3, (android.net.Uri) r1, (boolean) r9)
            r18.finish()
            goto L_0x0263
        L_0x0132:
            org.videolan.vlc.media.MediaUtils r0 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r19.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r8)
            org.videolan.television.ui.MediaItemDetailsModel r1 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            r0.getSubs((androidx.fragment.app.FragmentActivity) r2, (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1)
            goto L_0x0263
        L_0x0148:
            org.videolan.television.ui.TvUtil r2 = org.videolan.television.ui.TvUtil.INSTANCE
            org.videolan.television.ui.MediaItemDetailsModel r1 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            r2.openMedia(r0, r1)
            goto L_0x0263
        L_0x0157:
            r2 = r1
            androidx.lifecycle.LifecycleOwner r2 = (androidx.lifecycle.LifecycleOwner) r2
            androidx.lifecycle.LifecycleCoroutineScope r2 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r2)
            r12 = r2
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$5 r2 = new org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$5
            r2.<init>(r1, r11)
            r15 = r2
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r16 = 3
            r17 = 0
            r13 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)
            androidx.leanback.widget.SparseArrayObjectAdapter r2 = r1.actionsAdapter
            r2.set(r4, r3)
            androidx.leanback.widget.ArrayObjectAdapter r2 = r1.rowsAdapter
            if (r2 != 0) goto L_0x017f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r2 = r11
        L_0x017f:
            androidx.leanback.widget.ArrayObjectAdapter r1 = r1.rowsAdapter
            if (r1 != 0) goto L_0x0187
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            goto L_0x0188
        L_0x0187:
            r11 = r1
        L_0x0188:
            int r1 = r11.size()
            r2.notifyArrayItemRangeChanged(r10, r1)
            android.content.Context r0 = (android.content.Context) r0
            int r1 = org.videolan.vlc.R.string.favorite_removed
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r10)
            r0.show()
            goto L_0x0263
        L_0x019c:
            org.videolan.television.ui.MediaItemDetailsModel r3 = r19.getViewModel()
            org.videolan.television.ui.MediaItemDetails r3 = r3.getMediaItemDetails()
            java.lang.String r3 = r3.getLocation()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            android.net.Uri r3 = android.net.Uri.parse(r3)
            java.lang.String r5 = r3.getScheme()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r5)
            r6 = r1
            androidx.lifecycle.LifecycleOwner r6 = (androidx.lifecycle.LifecycleOwner) r6
            androidx.lifecycle.LifecycleCoroutineScope r6 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r6)
            r12 = r6
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$3 r6 = new org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$3
            r6.<init>(r5, r1, r3, r11)
            r15 = r6
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r16 = 3
            r17 = 0
            r13 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)
            androidx.leanback.widget.SparseArrayObjectAdapter r3 = r1.actionsAdapter
            r3.set(r4, r2)
            androidx.leanback.widget.ArrayObjectAdapter r2 = r1.rowsAdapter
            if (r2 != 0) goto L_0x01df
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r2 = r11
        L_0x01df:
            androidx.leanback.widget.ArrayObjectAdapter r1 = r1.rowsAdapter
            if (r1 != 0) goto L_0x01e7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            goto L_0x01e8
        L_0x01e7:
            r11 = r1
        L_0x01e8:
            int r1 = r11.size()
            r2.notifyArrayItemRangeChanged(r10, r1)
            android.content.Context r0 = (android.content.Context) r0
            int r1 = org.videolan.vlc.R.string.favorite_added
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r10)
            r0.show()
            goto L_0x0263
        L_0x01fb:
            org.videolan.vlc.media.MediaUtils r2 = org.videolan.vlc.media.MediaUtils.INSTANCE
            android.content.Context r0 = (android.content.Context) r0
            org.videolan.television.ui.MediaItemDetailsModel r3 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = r3.getMedia()
            r2.openMedia(r0, r3)
            org.videolan.television.ui.MediaItemDetailsModel r0 = r19.getViewModel()
            r0.setMediaStarted(r9)
            goto L_0x0263
        L_0x0212:
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r0 = r1.mediaMetadataModel
            if (r0 != 0) goto L_0x021c
            java.lang.String r0 = "mediaMetadataModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L_0x021d
        L_0x021c:
            r11 = r0
        L_0x021d:
            androidx.lifecycle.MutableLiveData r0 = r11.getNextEpisode()
            java.lang.Object r0 = r0.getValue()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r0 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r0
            if (r0 == 0) goto L_0x0263
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r0.getMedia()
            if (r4 == 0) goto L_0x0263
            org.videolan.television.ui.TvUtil r2 = org.videolan.television.ui.TvUtil.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r19.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)
            r3 = r0
            android.content.Context r3 = (android.content.Context) r3
            r6 = 4
            r7 = 0
            r5 = 0
            org.videolan.television.ui.TvUtil.showMediaDetail$default(r2, r3, r4, r5, r6, r7)
            androidx.fragment.app.FragmentActivity r0 = r19.requireActivity()
            r0.finish()
            goto L_0x0263
        L_0x0249:
            org.videolan.television.ui.MediaItemDetailsModel r2 = r19.getViewModel()
            r2.setMediaStarted(r10)
            org.videolan.television.ui.TvUtil r2 = org.videolan.television.ui.TvUtil.INSTANCE
            r3 = r0
            android.app.Activity r3 = (android.app.Activity) r3
            org.videolan.television.ui.MediaItemDetailsModel r1 = r19.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            r2.playMedia(r3, r1)
            r18.finish()
        L_0x0263:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaItemDetailsFragment.buildDetails$lambda$14(androidx.fragment.app.FragmentActivity, org.videolan.television.ui.MediaItemDetailsFragment, androidx.leanback.widget.Action, androidx.leanback.widget.Action, androidx.leanback.widget.Action):void");
    }
}
