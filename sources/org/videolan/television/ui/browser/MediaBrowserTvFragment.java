package org.videolan.television.ui.browser;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.television.ui.MediaTvItemAdapter;
import org.videolan.television.ui.TvItemAdapter;
import org.videolan.television.viewmodel.MediaBrowserViewModel;
import org.videolan.television.viewmodel.MediaBrowserViewModelKt;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.R;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001e\u0010\u001a\u001a\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\rH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u001f"}, d2 = {"Lorg/videolan/television/ui/browser/MediaBrowserTvFragment;", "Lorg/videolan/television/ui/browser/BaseBrowserTvFragment;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "()V", "adapter", "Lorg/videolan/television/ui/TvItemAdapter;", "getAdapter", "()Lorg/videolan/television/ui/TvItemAdapter;", "setAdapter", "(Lorg/videolan/television/ui/TvItemAdapter;)V", "getCategory", "", "getColumnNumber", "", "getDisplayPrefId", "", "getTitle", "onClick", "", "v", "Landroid/view/View;", "position", "item", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "provideAdapter", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "itemSize", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserTvFragment.kt */
public final class MediaBrowserTvFragment extends BaseBrowserTvFragment<MediaLibraryItem> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public TvItemAdapter adapter;

    public TvItemAdapter provideAdapter(IEventsHandler<MediaLibraryItem> iEventsHandler, int i) {
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel");
        long category = ((MediaBrowserViewModel) viewModel).getCategory();
        return new MediaTvItemAdapter(category == 24 ? 1 : category == 22 ? 2 : category == 21 ? 4 : category == 23 ? 8 : category == 27 ? 5 : 0, this, i, false, 8, (DefaultConstructorMarker) null);
    }

    public String getDisplayPrefId() {
        StringBuilder sb = new StringBuilder("display_tv_media_");
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel");
        sb.append(((MediaBrowserViewModel) viewModel).getCategory());
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
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel");
        long category = ((MediaBrowserViewModel) viewModel).getCategory();
        if (category == 24) {
            str = getString(R.string.tracks);
        } else if (category == 22) {
            str = getString(R.string.albums);
        } else if (category == 21) {
            str = getString(R.string.artists);
        } else if (category == 23) {
            str = getString(R.string.genres);
        } else if (category == 27) {
            str = getString(R.string.playlists);
        } else {
            str = getString(R.string.video);
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    public long getCategory() {
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel");
        return ((MediaBrowserViewModel) viewModel).getCategory();
    }

    public int getColumnNumber() {
        TvBrowserModel viewModel = getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel");
        if (((MediaBrowserViewModel) viewModel).getCategory() == 25) {
            return getResources().getInteger(R.integer.tv_videos_col_count);
        }
        return getResources().getInteger(R.integer.tv_songs_col_count);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lorg/videolan/television/ui/browser/MediaBrowserTvFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/television/ui/browser/MediaBrowserTvFragment;", "type", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaBrowserTvFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaBrowserTvFragment newInstance(long j, MediaLibraryItem mediaLibraryItem) {
            MediaBrowserTvFragment mediaBrowserTvFragment = new MediaBrowserTvFragment();
            mediaBrowserTvFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(Constants.CATEGORY, Long.valueOf(j)), TuplesKt.to("item", mediaLibraryItem)));
            return mediaBrowserTvFragment;
        }
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        Parcelable parcelable2;
        super.onCreate(bundle);
        MediaLibraryItem mediaLibraryItem = null;
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable2 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, "item", Parcelable.class);
            } else {
                parcelable2 = bundle.getParcelable("item");
                if (!(parcelable2 instanceof Parcelable)) {
                    parcelable2 = null;
                }
            }
            if (parcelable2 instanceof MediaLibraryItem) {
                mediaLibraryItem = (MediaLibraryItem) parcelable2;
            }
        } else {
            Intent intent = requireActivity().getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "item", Parcelable.class);
            } else {
                parcelable = intent.getParcelableExtra("item");
                if (!(parcelable instanceof Parcelable)) {
                    parcelable = null;
                }
            }
            if (parcelable instanceof MediaLibraryItem) {
                mediaLibraryItem = (MediaLibraryItem) parcelable;
            }
        }
        Fragment fragment = this;
        Bundle arguments = getArguments();
        long j = 24;
        if (arguments != null) {
            j = arguments.getLong(Constants.CATEGORY, 24);
        }
        setViewModel(MediaBrowserViewModelKt.getMediaBrowserModel(fragment, j, mediaLibraryItem));
        HeaderProvider provider = getViewModel().getProvider();
        Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<*>");
        LifecycleOwner lifecycleOwner = this;
        ((MedialibraryProvider) provider).getPagedList().observe(lifecycleOwner, new MediaBrowserTvFragment$sam$androidx_lifecycle_Observer$0(new MediaBrowserTvFragment$onCreate$1(this)));
        getViewModel().getProvider().getLiveHeaders().observe(lifecycleOwner, new MediaBrowserTvFragment$sam$androidx_lifecycle_Observer$0(new MediaBrowserTvFragment$onCreate$2(this)));
        HeaderProvider provider2 = getViewModel().getProvider();
        Intrinsics.checkNotNull(provider2, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<*>");
        ((MedialibraryProvider) provider2).getLoading().observe(lifecycleOwner, new MediaBrowserTvFragment$sam$androidx_lifecycle_Observer$0(new MediaBrowserTvFragment$onCreate$3(this)));
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new MediaBrowserTvFragment$onClick$1(this, mediaLibraryItem, (Continuation<? super MediaBrowserTvFragment$onClick$1>) null));
    }
}
