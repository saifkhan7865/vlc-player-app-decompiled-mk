package org.videolan.television.ui.browser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadataType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.provider.MediaScrapingProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.television.ui.MediaScrapingTvItemAdapter;
import org.videolan.television.ui.MediaScrapingTvshowDetailsActivity;
import org.videolan.television.ui.MediaScrapingTvshowDetailsActivityKt;
import org.videolan.television.ui.TvItemAdapter;
import org.videolan.television.viewmodel.MediaScrapingBrowserViewModel;
import org.videolan.television.viewmodel.MediaScrapingBrowserViewModelKt;
import org.videolan.vlc.R;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J \u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u001e\u0010\u001c\u001a\u00020\u00052\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\rH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006!"}, d2 = {"Lorg/videolan/television/ui/browser/MediaScrapingBrowserTvFragment;", "Lorg/videolan/television/ui/browser/BaseBrowserTvFragment;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "()V", "adapter", "Lorg/videolan/television/ui/TvItemAdapter;", "getAdapter", "()Lorg/videolan/television/ui/TvItemAdapter;", "setAdapter", "(Lorg/videolan/television/ui/TvItemAdapter;)V", "getCategory", "", "getColumnNumber", "", "getDisplayPrefId", "", "getTitle", "onClick", "", "v", "Landroid/view/View;", "position", "item", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLongClick", "", "provideAdapter", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "itemSize", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingBrowserTvFragment.kt */
public final class MediaScrapingBrowserTvFragment extends BaseBrowserTvFragment<MediaMetadataWithImages> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public TvItemAdapter adapter;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingBrowserTvFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaMetadataType.values().length];
            try {
                iArr[MediaMetadataType.TV_SHOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TvItemAdapter provideAdapter(IEventsHandler<MediaMetadataWithImages> iEventsHandler, int i) {
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaScrapingBrowserViewModel");
        return new MediaScrapingTvItemAdapter(((MediaScrapingBrowserViewModel) viewModel).getCategory(), this, i, false, 8, (DefaultConstructorMarker) null);
    }

    public String getDisplayPrefId() {
        StringBuilder sb = new StringBuilder("display_tv_moviepedia_");
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaScrapingBrowserViewModel");
        sb.append(((MediaScrapingBrowserViewModel) viewModel).getCategory());
        return sb.toString();
    }

    public TvItemAdapter getAdapter() {
        TvItemAdapter tvItemAdapter = this.adapter;
        if (tvItemAdapter != null) {
            return tvItemAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public void setAdapter(TvItemAdapter tvItemAdapter) {
        Intrinsics.checkNotNullParameter(tvItemAdapter, "<set-?>");
        this.adapter = tvItemAdapter;
    }

    public String getTitle() {
        String str;
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaScrapingBrowserViewModel");
        long category = ((MediaScrapingBrowserViewModel) viewModel).getCategory();
        if (category == 31) {
            str = getString(R.string.header_tvshows);
        } else if (category == 30) {
            str = getString(R.string.header_movies);
        } else {
            str = getString(R.string.video);
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    public long getCategory() {
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaScrapingBrowserViewModel");
        return ((MediaScrapingBrowserViewModel) viewModel).getCategory();
    }

    public int getColumnNumber() {
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaScrapingBrowserViewModel");
        if (((MediaScrapingBrowserViewModel) viewModel).getCategory() == 25) {
            return getResources().getInteger(R.integer.tv_videos_col_count);
        }
        return getResources().getInteger(R.integer.tv_songs_col_count);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/television/ui/browser/MediaScrapingBrowserTvFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/television/ui/browser/MediaScrapingBrowserTvFragment;", "type", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingBrowserTvFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaScrapingBrowserTvFragment newInstance(long j) {
            MediaScrapingBrowserTvFragment mediaScrapingBrowserTvFragment = new MediaScrapingBrowserTvFragment();
            mediaScrapingBrowserTvFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(Constants.CATEGORY, Long.valueOf(j))));
            return mediaScrapingBrowserTvFragment;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Fragment fragment = this;
        Bundle arguments = getArguments();
        long j = 30;
        if (arguments != null) {
            j = arguments.getLong(Constants.CATEGORY, 30);
        }
        setViewModel(MediaScrapingBrowserViewModelKt.getMoviepediaBrowserModel(fragment, j));
        HeaderProvider provider = getViewModel().getProvider();
        Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.moviepedia.provider.MediaScrapingProvider");
        LifecycleOwner lifecycleOwner = this;
        ((MediaScrapingProvider) provider).getPagedList().observe(lifecycleOwner, new MediaScrapingBrowserTvFragment$sam$androidx_lifecycle_Observer$0(new MediaScrapingBrowserTvFragment$onCreate$1(this)));
        getViewModel().getProvider().getLiveHeaders().observe(lifecycleOwner, new MediaScrapingBrowserTvFragment$sam$androidx_lifecycle_Observer$0(new MediaScrapingBrowserTvFragment$onCreate$2(this)));
        HeaderProvider provider2 = getViewModel().getProvider();
        Intrinsics.checkNotNull(provider2, "null cannot be cast to non-null type org.videolan.moviepedia.provider.MediaScrapingProvider");
        ((MediaScrapingProvider) provider2).getLoading().observe(lifecycleOwner, new MediaScrapingBrowserTvFragment$sam$androidx_lifecycle_Observer$0(new MediaScrapingBrowserTvFragment$onCreate$3(this)));
    }

    public void onClick(View view, int i, MediaMetadataWithImages mediaMetadataWithImages) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "item");
        if (WhenMappings.$EnumSwitchMapping$0[mediaMetadataWithImages.getMetadata().getType().ordinal()] == 1) {
            Intent intent = new Intent(getActivity(), MediaScrapingTvshowDetailsActivity.class);
            intent.putExtra(MediaScrapingTvshowDetailsActivityKt.TV_SHOW_ID, mediaMetadataWithImages.getMetadata().getMoviepediaId());
            requireActivity().startActivity(intent);
            return;
        }
        Long mlId = mediaMetadataWithImages.getMetadata().getMlId();
        if (mlId != null) {
            LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new MediaScrapingBrowserTvFragment$onClick$1$1(this, mlId.longValue(), (Continuation<? super MediaScrapingBrowserTvFragment$onClick$1$1>) null));
        }
    }

    public boolean onLongClick(View view, int i, MediaMetadataWithImages mediaMetadataWithImages) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "item");
        if (WhenMappings.$EnumSwitchMapping$0[mediaMetadataWithImages.getMetadata().getType().ordinal()] == 1) {
            Intent intent = new Intent(getActivity(), MediaScrapingTvshowDetailsActivity.class);
            intent.putExtra(MediaScrapingTvshowDetailsActivityKt.TV_SHOW_ID, mediaMetadataWithImages.getMetadata().getMoviepediaId());
            requireActivity().startActivity(intent);
        } else {
            Long mlId = mediaMetadataWithImages.getMetadata().getMlId();
            if (mlId != null) {
                LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new MediaScrapingBrowserTvFragment$onLongClick$1$1(this, mlId.longValue(), (Continuation<? super MediaScrapingBrowserTvFragment$onLongClick$1$1>) null));
            }
        }
        return true;
    }
}
