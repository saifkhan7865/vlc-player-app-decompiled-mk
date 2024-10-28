package org.videolan.vlc.gui.audio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.ContentActivity;
import org.videolan.vlc.gui.HeaderMediaListActivity;
import org.videolan.vlc.gui.browser.MediaBrowserFragment;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialog;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialogKt;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.PlaylistModel;
import org.videolan.vlc.viewmodels.mobile.AlbumSongsViewModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0014J\b\u0010\u001b\u001a\u00020\u001cH\u0016J \u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010#\u001a\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J&\u0010&\u001a\u0004\u0018\u00010\u001f2\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0018\u0010+\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u00162\u0006\u0010,\u001a\u00020-H\u0016J\u0018\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u001fH\u0016J\u0010\u00104\u001a\u00020\f2\u0006\u0010!\u001a\u000205H\u0016J\u0010\u00106\u001a\u00020\u00182\u0006\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020\u0018H\u0016J\u0010\u0010:\u001a\u00020\u00182\u0006\u0010;\u001a\u00020%H\u0016J\u0010\u0010<\u001a\u00020\u00182\u0006\u0010=\u001a\u00020>H\u0016J\u0010\u0010?\u001a\u00020\u00182\u0006\u0010=\u001a\u00020>H\u0016J\u0010\u0010@\u001a\u00020\u00182\u0006\u0010=\u001a\u00020>H\u0016J\u001a\u0010A\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010B\u001a\u00020\u0018H\u0016J\u0010\u0010C\u001a\u00020\u00182\u0006\u0010D\u001a\u00020\u0016H\u0016J\b\u0010E\u001a\u00020\u0018H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X.¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioAlbumsSongsFragment;", "Lorg/videolan/vlc/gui/audio/BaseAudioBrowser;", "Lorg/videolan/vlc/viewmodels/mobile/AlbumSongsViewModel;", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$OnRefreshListener;", "()V", "albumsAdapter", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "audioPagerAdapter", "Lorg/videolan/vlc/gui/audio/AudioPagerAdapter;", "fastScroller", "Lorg/videolan/vlc/gui/view/FastScroller;", "fromAlbums", "", "hasTabs", "getHasTabs", "()Z", "lists", "", "Landroidx/recyclerview/widget/RecyclerView;", "[Landroidx/recyclerview/widget/RecyclerView;", "songsAdapter", "spacing", "", "clear", "", "getCurrentAdapter", "getCurrentRV", "getTitle", "", "onClick", "v", "Landroid/view/View;", "position", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDisplaySettingChanged", "key", "value", "", "onFabPlayClick", "view", "onOptionsItemSelected", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onRefresh", "onSaveInstanceState", "outState", "onTabReselected", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "onViewCreated", "setupTabLayout", "sortBy", "sort", "updateTabs", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioAlbumsSongsFragment.kt */
public final class AudioAlbumsSongsFragment extends BaseAudioBrowser<AlbumSongsViewModel> implements SwipeRefreshLayout.OnRefreshListener {
    /* access modifiers changed from: private */
    public AudioBrowserAdapter albumsAdapter;
    private AudioPagerAdapter audioPagerAdapter;
    /* access modifiers changed from: private */
    public FastScroller fastScroller;
    private boolean fromAlbums;
    private final boolean hasTabs = true;
    /* access modifiers changed from: private */
    public RecyclerView[] lists;
    /* access modifiers changed from: private */
    public AudioBrowserAdapter songsAdapter;
    private int spacing;

    public boolean getHasTabs() {
        return this.hasTabs;
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            r0 = 33
            java.lang.String r1 = "ML_ITEM"
            r2 = 0
            if (r6 == 0) goto L_0x002b
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r0) goto L_0x0017
            java.lang.Class<org.videolan.medialibrary.media.MediaLibraryItem> r3 = org.videolan.medialibrary.media.MediaLibraryItem.class
            java.lang.Object r3 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r6, (java.lang.String) r1, (java.lang.Class) r3)
            android.os.Parcelable r3 = (android.os.Parcelable) r3
            goto L_0x0024
        L_0x0017:
            android.os.Parcelable r3 = r6.getParcelable(r1)
            boolean r4 = r3 instanceof org.videolan.medialibrary.media.MediaLibraryItem
            if (r4 != 0) goto L_0x0020
            r3 = r2
        L_0x0020:
            org.videolan.medialibrary.media.MediaLibraryItem r3 = (org.videolan.medialibrary.media.MediaLibraryItem) r3
            android.os.Parcelable r3 = (android.os.Parcelable) r3
        L_0x0024:
            org.videolan.medialibrary.media.MediaLibraryItem r3 = (org.videolan.medialibrary.media.MediaLibraryItem) r3
            if (r3 != 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r2 = r3
            goto L_0x0050
        L_0x002b:
            android.os.Bundle r3 = r5.getArguments()
            if (r3 == 0) goto L_0x0050
            int r4 = android.os.Build.VERSION.SDK_INT
            if (r4 < r0) goto L_0x003e
            java.lang.Class<org.videolan.medialibrary.media.MediaLibraryItem> r0 = org.videolan.medialibrary.media.MediaLibraryItem.class
            java.lang.Object r0 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r3, (java.lang.String) r1, (java.lang.Class) r0)
            android.os.Parcelable r0 = (android.os.Parcelable) r0
            goto L_0x004d
        L_0x003e:
            android.os.Parcelable r0 = r3.getParcelable(r1)
            boolean r1 = r0 instanceof org.videolan.medialibrary.media.MediaLibraryItem
            if (r1 != 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r2 = r0
        L_0x0048:
            org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
            r0 = r2
            android.os.Parcelable r0 = (android.os.Parcelable) r0
        L_0x004d:
            r2 = r0
            org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
        L_0x0050:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            org.videolan.vlc.viewmodels.mobile.AlbumSongsViewModel r0 = org.videolan.vlc.viewmodels.mobile.AlbumSongsViewModelKt.getViewModel(r5, r2)
            org.videolan.vlc.viewmodels.SortableModel r0 = (org.videolan.vlc.viewmodels.SortableModel) r0
            r5.setViewModel(r0)
            java.lang.String r0 = "ARTIST_FROM_ALBUM"
            if (r6 == 0) goto L_0x0065
            boolean r6 = r6.getBoolean(r0)
            goto L_0x0072
        L_0x0065:
            android.os.Bundle r6 = r5.getArguments()
            r1 = 0
            if (r6 == 0) goto L_0x0071
            boolean r6 = r6.getBoolean(r0, r1)
            goto L_0x0072
        L_0x0071:
            r6 = 0
        L_0x0072:
            r5.fromAlbums = r6
            org.videolan.vlc.media.PlaylistManager$Companion r6 = org.videolan.vlc.media.PlaylistManager.Companion
            androidx.lifecycle.MutableLiveData r6 = r6.getCurrentPlayedMedia()
            r0 = r5
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            org.videolan.vlc.gui.audio.AudioAlbumsSongsFragment$onCreate$1 r1 = new org.videolan.vlc.gui.audio.AudioAlbumsSongsFragment$onCreate$1
            r1.<init>(r5)
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            org.videolan.vlc.gui.audio.AudioAlbumsSongsFragmentKt$sam$androidx_lifecycle_Observer$0 r2 = new org.videolan.vlc.gui.audio.AudioAlbumsSongsFragmentKt$sam$androidx_lifecycle_Observer$0
            r2.<init>(r1)
            androidx.lifecycle.Observer r2 = (androidx.lifecycle.Observer) r2
            r6.observe(r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioAlbumsSongsFragment.onCreate(android.os.Bundle):void");
    }

    public String getTitle() {
        String title = ((AlbumSongsViewModel) getViewModel()).getParent().getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        return title;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.audio_albums_songs, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.spacing = (int) getResources().getDimension(R.dimen.kl_small);
        RecyclerSectionItemGridDecoration.Companion companion = RecyclerSectionItemGridDecoration.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        int itemSize = companion.getItemSize(KextensionsKt.getScreenWidth(requireActivity), getNbColumns(), this.spacing, KotlinExtensionsKt.getDp(16));
        View findViewById = getViewPager().getChildAt(0).findViewById(R.id.audio_list);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        RecyclerView recyclerView = (RecyclerView) findViewById;
        View findViewById2 = getViewPager().getChildAt(1).findViewById(R.id.audio_list);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        RecyclerView recyclerView2 = (RecyclerView) findViewById2;
        this.lists = new RecyclerView[]{recyclerView, recyclerView2};
        String[] strArr = {getString(R.string.albums), getString(R.string.songs)};
        IEventsHandler iEventsHandler = this;
        this.albumsAdapter = new AudioBrowserAdapter(2, iEventsHandler, (IListEventsHandler) null, false, ((AlbumSongsViewModel) getViewModel()).getProvidersInCard()[0].booleanValue() ? itemSize : -1, 12, (DefaultConstructorMarker) null);
        this.songsAdapter = new AudioBrowserAdapter(32, iEventsHandler, (IListEventsHandler) null, false, ((AlbumSongsViewModel) getViewModel()).getProvidersInCard()[1].booleanValue() ? itemSize : -1, 12, (DefaultConstructorMarker) null);
        PlaylistModel playlistModel = PlaylistModel.Companion.get((Fragment) this);
        AudioBrowserAdapter audioBrowserAdapter = this.songsAdapter;
        if (audioBrowserAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
            audioBrowserAdapter = null;
        }
        audioBrowserAdapter.setModel(playlistModel);
        KextensionsKt.launchWhenStarted(FlowKt.onEach(FlowKt.conflate(FlowLiveDataConversions.asFlow(playlistModel.getDataset())), new AudioAlbumsSongsFragment$onViewCreated$1(this, playlistModel, (Continuation<? super AudioAlbumsSongsFragment$onViewCreated$1>) null)), LifecycleOwnerKt.getLifecycleScope(this));
        AudioBrowserAdapter[] audioBrowserAdapterArr = new AudioBrowserAdapter[2];
        AudioBrowserAdapter audioBrowserAdapter2 = this.albumsAdapter;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("albumsAdapter");
            audioBrowserAdapter2 = null;
        }
        audioBrowserAdapterArr[0] = audioBrowserAdapter2;
        AudioBrowserAdapter audioBrowserAdapter3 = this.songsAdapter;
        if (audioBrowserAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
            audioBrowserAdapter3 = null;
        }
        audioBrowserAdapterArr[1] = audioBrowserAdapter3;
        setAdapters$vlc_android_release(audioBrowserAdapterArr);
        AudioBrowserAdapter audioBrowserAdapter4 = this.songsAdapter;
        if (audioBrowserAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
            audioBrowserAdapter4 = null;
        }
        recyclerView2.setAdapter(audioBrowserAdapter4);
        AudioBrowserAdapter audioBrowserAdapter5 = this.albumsAdapter;
        if (audioBrowserAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("albumsAdapter");
            audioBrowserAdapter5 = null;
        }
        recyclerView.setAdapter(audioBrowserAdapter5);
        getViewPager().setOffscreenPageLimit(1);
        View childAt = getViewPager().getChildAt(0);
        Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
        View childAt2 = getViewPager().getChildAt(1);
        Intrinsics.checkNotNullExpressionValue(childAt2, "getChildAt(...)");
        this.audioPagerAdapter = new AudioPagerAdapter(new View[]{childAt, childAt2}, strArr);
        ViewPager viewPager = getViewPager();
        AudioPagerAdapter audioPagerAdapter2 = this.audioPagerAdapter;
        if (audioPagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPagerAdapter");
            audioPagerAdapter2 = null;
        }
        viewPager.setAdapter(audioPagerAdapter2);
        View findViewById3 = view.getRootView().findViewById(R.id.songs_fast_scroller);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type org.videolan.vlc.gui.view.FastScroller");
        FastScroller fastScroller2 = (FastScroller) findViewById3;
        this.fastScroller = fastScroller2;
        if (fastScroller2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fastScroller");
            fastScroller2 = null;
        }
        View findViewById4 = view.getRootView().findViewById(R.id.appbar);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout");
        View findViewById5 = view.getRootView().findViewById(R.id.coordinator);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout");
        View findViewById6 = view.getRootView().findViewById(R.id.fab);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type com.google.android.material.floatingactionbutton.FloatingActionButton");
        fastScroller2.attachToCoordinator((AppBarLayout) findViewById4, (CoordinatorLayout) findViewById5, (FloatingActionButton) findViewById6);
        getViewPager().setOnTouchListener(getSwipeFilter());
        getSwipeRefreshLayout().setOnRefreshListener(this);
        RecyclerView[] recyclerViewArr = this.lists;
        if (recyclerViewArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lists");
            recyclerViewArr = null;
        }
        for (RecyclerView recyclerView3 : recyclerViewArr) {
            recyclerView3.setLayoutManager(new LinearLayoutManager(view.getContext()));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setRecycleChildrenOnDetach(true);
            recyclerView3.setLayoutManager(linearLayoutManager);
            recyclerView3.addOnScrollListener(getScrollListener$vlc_android_release());
        }
        FloatingActionButton fabPlay = getFabPlay();
        if (fabPlay != null) {
            fabPlay.setImageResource(R.drawable.ic_fab_play);
        }
        FloatingActionButton fabPlay2 = getFabPlay();
        if (fabPlay2 != null) {
            fabPlay2.setContentDescription(getString(R.string.play));
        }
        ((AlbumSongsViewModel) getViewModel()).getAlbumsProvider().getPagedList().observe(requireActivity(), new AudioAlbumsSongsFragmentKt$sam$androidx_lifecycle_Observer$0(new AudioAlbumsSongsFragment$onViewCreated$2(this)));
        ((AlbumSongsViewModel) getViewModel()).getTracksProvider().getPagedList().observe(requireActivity(), new AudioAlbumsSongsFragmentKt$sam$androidx_lifecycle_Observer$0(new AudioAlbumsSongsFragment$onViewCreated$3(this)));
        for (int i = 0; i < 2; i++) {
            boolean booleanValue = ((AlbumSongsViewModel) getViewModel()).getProvidersInCard()[i].booleanValue();
            RecyclerView[] recyclerViewArr2 = this.lists;
            if (recyclerViewArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lists");
                recyclerViewArr2 = null;
            }
            RecyclerView recyclerView4 = recyclerViewArr2[i];
            MedialibraryProvider medialibraryProvider = ((AlbumSongsViewModel) getViewModel()).getProviders()[i];
            Intrinsics.checkNotNull(medialibraryProvider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.media.MediaLibraryItem>");
            setupLayoutManager(booleanValue, recyclerView4, medialibraryProvider, getAdapters$vlc_android_release()[i], this.spacing);
        }
        ((AlbumSongsViewModel) getViewModel()).getAlbumsProvider().getLoading().observe(requireActivity(), new AudioAlbumsSongsFragmentKt$sam$androidx_lifecycle_Observer$0(new AudioAlbumsSongsFragment$onViewCreated$4(this)));
        ((AlbumSongsViewModel) getViewModel()).getAlbumsProvider().getLiveHeaders().observe(getViewLifecycleOwner(), new AudioAlbumsSongsFragmentKt$sam$androidx_lifecycle_Observer$0(new AudioAlbumsSongsFragment$onViewCreated$5(this)));
        ((AlbumSongsViewModel) getViewModel()).getTracksProvider().getLiveHeaders().observe(getViewLifecycleOwner(), new AudioAlbumsSongsFragmentKt$sam$androidx_lifecycle_Observer$0(new AudioAlbumsSongsFragment$onViewCreated$6(this)));
    }

    public void sortBy(int i) {
        ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].sort(i);
    }

    public AudioBrowserAdapter getCurrentAdapter() {
        return getAdapters$vlc_android_release()[getCurrentTab()];
    }

    public void onRefresh() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.videolan.vlc.gui.ContentActivity");
        ((ContentActivity) requireActivity).closeSearchView();
        ((AlbumSongsViewModel) getViewModel()).refresh();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putParcelable("ML_ITEM", ((AlbumSongsViewModel) getViewModel()).getParent());
        super.onSaveInstanceState(bundle);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.ml_menu_display_options).setVisible(true);
        menu.findItem(R.id.ml_menu_sortby).setVisible(false);
        MediaBrowserFragment.sortMenuTitles$default(this, 0, 1, (Object) null);
        reopenSearchIfNeeded();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.play_all) {
            FastScroller fastScroller2 = this.fastScroller;
            if (fastScroller2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fastScroller");
                fastScroller2 = null;
            }
            onFabPlayClick(fastScroller2);
            return true;
        } else if (itemId != R.id.ml_menu_display_options) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            Collection arrayList = new ArrayList();
            for (Object next : CollectionsKt.arrayListOf(1, 10, 7, 9, 2, 5, 4, 6, 15)) {
                if (((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].canSortBy(((Number) next).intValue())) {
                    arrayList.add(next);
                }
            }
            DisplaySettingsDialog.Companion.newInstance$default(DisplaySettingsDialog.Companion, ((AlbumSongsViewModel) getViewModel()).getProvidersInCard()[getCurrentTab()].booleanValue(), (Boolean) null, Boolean.valueOf(((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].getOnlyFavorites()), (List) arrayList, ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].getSort(), ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].getDesc(), (String) null, (Boolean) null, (Boolean) null, 450, (Object) null).show(requireActivity().getSupportFragmentManager(), "DisplaySettingsDialog");
            return true;
        }
    }

    public void onDisplaySettingChanged(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(obj, "value");
        int hashCode = str.hashCode();
        if (hashCode != -341247322) {
            if (hashCode != 292773227) {
                if (hashCode == 1468888228 && str.equals(DisplaySettingsDialogKt.CURRENT_SORT)) {
                    Pair pair = (Pair) obj;
                    ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].setSort(((Number) pair.getFirst()).intValue());
                    ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].setDesc(((Boolean) pair.getSecond()).booleanValue());
                    ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].saveSort();
                    ((AlbumSongsViewModel) getViewModel()).refresh();
                }
            } else if (str.equals(DisplaySettingsDialogKt.ONLY_FAVS)) {
                ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()].showOnlyFavs(((Boolean) obj).booleanValue());
                ((AlbumSongsViewModel) getViewModel()).refresh();
                updateTabs();
            }
        } else if (str.equals(DisplaySettingsDialogKt.DISPLAY_IN_CARDS)) {
            ((AlbumSongsViewModel) getViewModel()).getProvidersInCard()[getCurrentTab()] = obj;
            boolean booleanValue = ((AlbumSongsViewModel) getViewModel()).getProvidersInCard()[getCurrentTab()].booleanValue();
            RecyclerView[] recyclerViewArr = this.lists;
            AudioBrowserAdapter audioBrowserAdapter = null;
            if (recyclerViewArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lists");
                recyclerViewArr = null;
            }
            RecyclerView recyclerView = recyclerViewArr[getCurrentTab()];
            MedialibraryProvider medialibraryProvider = ((AlbumSongsViewModel) getViewModel()).getProviders()[getCurrentTab()];
            Intrinsics.checkNotNull(medialibraryProvider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.media.MediaLibraryItem>");
            setupLayoutManager(booleanValue, recyclerView, medialibraryProvider, getAdapters$vlc_android_release()[getCurrentTab()], this.spacing);
            RecyclerView[] recyclerViewArr2 = this.lists;
            if (recyclerViewArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lists");
                recyclerViewArr2 = null;
            }
            recyclerViewArr2[getCurrentTab()].setAdapter(getAdapters$vlc_android_release()[getCurrentTab()]);
            if (getCurrentTab() == 1) {
                AudioBrowserAdapter audioBrowserAdapter2 = this.songsAdapter;
                if (audioBrowserAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
                    audioBrowserAdapter2 = null;
                }
                if (audioBrowserAdapter2.getCurrentMedia() != null) {
                    AudioBrowserAdapter audioBrowserAdapter3 = this.songsAdapter;
                    if (audioBrowserAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
                        audioBrowserAdapter3 = null;
                    }
                    audioBrowserAdapter3.setCurrentMedia((MediaWrapper) null);
                    AudioBrowserAdapter audioBrowserAdapter4 = this.songsAdapter;
                    if (audioBrowserAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
                    } else {
                        audioBrowserAdapter = audioBrowserAdapter4;
                    }
                    audioBrowserAdapter.setCurrentMedia(PlaylistManager.Companion.getCurrentPlayedMedia().getValue());
                }
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.invalidateOptionsMenu();
            }
            Settings settings = Settings.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), ((AlbumSongsViewModel) getViewModel()).getDisplayModeKeys()[getCurrentTab()], obj);
        }
    }

    private final void updateTabs() {
        View view;
        TabLayout tabLayout = getTabLayout();
        Intrinsics.checkNotNull(tabLayout);
        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout tabLayout2 = getTabLayout();
            Intrinsics.checkNotNull(tabLayout2);
            TabLayout.Tab tabAt = tabLayout2.getTabAt(i);
            AudioPagerAdapter audioPagerAdapter2 = null;
            if (tabAt == null || (view = tabAt.getCustomView()) == null) {
                view = View.inflate(requireActivity(), R.layout.audio_tab, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(R.id.tab_title);
            AudioPagerAdapter audioPagerAdapter3 = this.audioPagerAdapter;
            if (audioPagerAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPagerAdapter");
            } else {
                audioPagerAdapter2 = audioPagerAdapter3;
            }
            textView.setText(audioPagerAdapter2.getPageTitle(i));
            if (((AlbumSongsViewModel) getViewModel()).getProviders()[i].getOnlyFavorites()) {
                UiTools uiTools = UiTools.INSTANCE;
                Intrinsics.checkNotNull(textView);
                uiTools.addFavoritesIcon(textView);
            } else {
                UiTools uiTools2 = UiTools.INSTANCE;
                Intrinsics.checkNotNull(textView);
                uiTools2.removeDrawables(textView);
            }
            if (tabAt != null) {
                tabAt.setCustomView(view);
            }
        }
    }

    public void setupTabLayout() {
        super.setupTabLayout();
        updateTabs();
    }

    public void clear() {
        for (AudioBrowserAdapter clear : getAdapters$vlc_android_release()) {
            clear.clear();
        }
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (getActionMode() != null) {
            super.onClick(view, i, mediaLibraryItem);
        } else if (mediaLibraryItem instanceof Album) {
            Intent intent = new Intent(getActivity(), HeaderMediaListActivity.class);
            intent.putExtra("ML_ITEM", mediaLibraryItem);
            if (this.fromAlbums) {
                intent.setFlags(intent.getFlags() | 1073741824);
            }
            startActivity(intent);
        } else {
            if (inSearchMode()) {
                UiTools.INSTANCE.setKeyboardVisibility(view, false);
            }
            Settings settings = Settings.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            if (((SharedPreferences) settings.getInstance(requireContext)).getBoolean(SettingsKt.FORCE_PLAY_ALL_AUDIO, false)) {
                MediaUtils.INSTANCE.playAll(getActivity(), ((AlbumSongsViewModel) getViewModel()).getTracksProvider(), i, false);
            } else {
                MediaUtils.INSTANCE.openMedia(view.getContext(), (MediaWrapper) mediaLibraryItem);
            }
        }
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        if (contextOption == ContextOption.CTX_PLAY_ALL) {
            MediaUtils.INSTANCE.playAll(getActivity(), ((AlbumSongsViewModel) getViewModel()).getTracksProvider(), i, false);
        } else {
            super.onCtxAction(i, contextOption);
        }
    }

    public void onTabUnselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        super.onTabUnselected(tab);
        ((AlbumSongsViewModel) getViewModel()).restore();
    }

    public void onTabReselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        RecyclerView[] recyclerViewArr = this.lists;
        RecyclerView[] recyclerViewArr2 = null;
        if (recyclerViewArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lists");
            recyclerViewArr = null;
        }
        recyclerViewArr[tab.getPosition()].smoothScrollToPosition(0);
        FastScroller fastScroller2 = this.fastScroller;
        if (fastScroller2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fastScroller");
            fastScroller2 = null;
        }
        RecyclerView[] recyclerViewArr3 = this.lists;
        if (recyclerViewArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lists");
        } else {
            recyclerViewArr2 = recyclerViewArr3;
        }
        fastScroller2.setRecyclerView(recyclerViewArr2[tab.getPosition()], ((AlbumSongsViewModel) getViewModel()).getProviders()[tab.getPosition()]);
    }

    public void onTabSelected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        super.onTabSelected(tab);
        FastScroller fastScroller2 = this.fastScroller;
        RecyclerView[] recyclerViewArr = null;
        if (fastScroller2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fastScroller");
            fastScroller2 = null;
        }
        RecyclerView[] recyclerViewArr2 = this.lists;
        if (recyclerViewArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lists");
        } else {
            recyclerViewArr = recyclerViewArr2;
        }
        fastScroller2.setRecyclerView(recyclerViewArr[tab.getPosition()], ((AlbumSongsViewModel) getViewModel()).getProviders()[tab.getPosition()]);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: protected */
    public RecyclerView getCurrentRV() {
        RecyclerView[] recyclerViewArr = this.lists;
        if (recyclerViewArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lists");
            recyclerViewArr = null;
        }
        return recyclerViewArr[getCurrentTab()];
    }

    public void onFabPlayClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (getCurrentTab() == 0) {
            MediaUtils.INSTANCE.playAlbums(getActivity(), ((AlbumSongsViewModel) getViewModel()).getAlbumsProvider(), 0, false);
        } else {
            MediaUtils.INSTANCE.playAll(getActivity(), ((AlbumSongsViewModel) getViewModel()).getTracksProvider(), 0, false);
        }
    }
}
