package org.videolan.vlc.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.MediaParsingServiceKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.PlaylistsFragmentBinding;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;
import org.videolan.vlc.gui.audio.BaseAudioBrowser;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialog;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.helpers.INavigator;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.RecyclerSectionItemDecoration;
import org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.providers.medialibrary.PlaylistsProvider;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel;
import org.videolan.vlc.viewmodels.mobile.PlaylistsViewModelKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 82\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00018B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\nH\u0014J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J&\u0010$\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0018\u0010)\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+H\u0016J\u0018\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020\n2\u0006\u0010\u001a\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#H\u0016J\b\u00103\u001a\u00020\u0015H\u0016J\u001a\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u00106\u001a\u00020\u0015H\u0002J\b\u00107\u001a\u00020\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lorg/videolan/vlc/gui/PlaylistFragment;", "Lorg/videolan/vlc/gui/audio/BaseAudioBrowser;", "Lorg/videolan/vlc/viewmodels/mobile/PlaylistsViewModel;", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$OnRefreshListener;", "()V", "binding", "Lorg/videolan/vlc/databinding/PlaylistsFragmentBinding;", "fastScroller", "Lorg/videolan/vlc/gui/view/FastScroller;", "isMainNavigationPoint", "", "()Z", "playlistAdapter", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "playlists", "Landroidx/recyclerview/widget/RecyclerView;", "getCurrentRV", "getTitle", "", "hasFAB", "onClick", "", "v", "Landroid/view/View;", "position", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "mode", "Landroidx/appcompat/view/ActionMode;", "menu", "Landroid/view/Menu;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDisplaySettingChanged", "key", "value", "", "onOptionsItemSelected", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "onRefresh", "onViewCreated", "view", "setupLayoutManager", "updateEmptyView", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistFragment.kt */
public final class PlaylistFragment extends BaseAudioBrowser<PlaylistsViewModel> implements SwipeRefreshLayout.OnRefreshListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PLAYLIST_TYPE = "PLAYLIST_TYPE";
    private PlaylistsFragmentBinding binding;
    private FastScroller fastScroller;
    private final boolean isMainNavigationPoint;
    /* access modifiers changed from: private */
    public AudioBrowserAdapter playlistAdapter;
    /* access modifiers changed from: private */
    public RecyclerView playlists;

    /* access modifiers changed from: protected */
    public boolean hasFAB() {
        return false;
    }

    public boolean isMainNavigationPoint() {
        return this.isMainNavigationPoint;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        int i = 0;
        if (arguments != null) {
            i = arguments.getInt(PLAYLIST_TYPE, 0);
        }
        setViewModel(PlaylistsViewModelKt.getViewModel(this, Playlist.Type.values()[i]));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        PlaylistsFragmentBinding inflate = PlaylistsFragmentBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        PlaylistsFragmentBinding playlistsFragmentBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View findViewById = inflate.swipeLayout.findViewById(R.id.audio_list);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.playlists = (RecyclerView) findViewById;
        PlaylistsFragmentBinding playlistsFragmentBinding2 = this.binding;
        if (playlistsFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            playlistsFragmentBinding = playlistsFragmentBinding2;
        }
        return playlistsFragmentBinding.getRoot();
    }

    /* JADX WARNING: type inference failed for: r14v19, types: [androidx.fragment.app.Fragment] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onViewCreated(android.view.View r14, android.os.Bundle r15) {
        /*
            r13 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            super.onViewCreated(r14, r15)
            org.videolan.vlc.databinding.PlaylistsFragmentBinding r15 = r13.binding
            r0 = 0
            if (r15 != 0) goto L_0x0013
            java.lang.String r15 = "binding"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r15)
            r15 = r0
        L_0x0013:
            org.videolan.vlc.gui.view.SwipeRefreshLayout r15 = r15.swipeLayout
            r1 = r13
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener r1 = (androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener) r1
            r15.setOnRefreshListener(r1)
            android.content.res.Resources r15 = r13.getResources()
            int r1 = org.videolan.vlc.R.dimen.kl_half
            float r15 = r15.getDimension(r1)
            int r15 = (int) r15
            android.content.res.Resources r1 = r13.getResources()
            int r2 = org.videolan.vlc.R.dimen.default_content_width
            float r1 = r1.getDimension(r2)
            r2 = 0
            java.lang.String r3 = "requireActivity(...)"
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x004a
            androidx.fragment.app.FragmentActivity r2 = r13.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            android.app.Activity r2 = (android.app.Activity) r2
            int r2 = org.videolan.vlc.util.KextensionsKt.getScreenWidth(r2)
            int r1 = (int) r1
            int r1 = java.lang.Math.min(r2, r1)
            goto L_0x0057
        L_0x004a:
            androidx.fragment.app.FragmentActivity r1 = r13.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            android.app.Activity r1 = (android.app.Activity) r1
            int r1 = org.videolan.vlc.util.KextensionsKt.getScreenWidth(r1)
        L_0x0057:
            org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration$Companion r2 = org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration.Companion
            int r3 = r15 * 2
            int r1 = r1 - r3
            int r3 = r13.getNbColumns()
            r4 = 16
            int r4 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            int r10 = r2.getItemSize(r1, r3, r15, r4)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r15 = new org.videolan.vlc.gui.audio.AudioBrowserAdapter
            r7 = r13
            org.videolan.vlc.interfaces.IEventsHandler r7 = (org.videolan.vlc.interfaces.IEventsHandler) r7
            r11 = 12
            r12 = 0
            r6 = 16
            r8 = 0
            r9 = 0
            r5 = r15
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            r13.playlistAdapter = r15
            androidx.recyclerview.widget.RecyclerView$Adapter r15 = (androidx.recyclerview.widget.RecyclerView.Adapter) r15
            org.videolan.vlc.gui.PlaylistFragment$onViewCreated$1 r1 = new org.videolan.vlc.gui.PlaylistFragment$onViewCreated$1
            r1.<init>(r13)
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            org.videolan.vlc.util.KextensionsKt.onAnyChange(r15, r1)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r15 = r13.playlistAdapter
            java.lang.String r1 = "playlistAdapter"
            if (r15 != 0) goto L_0x0092
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r15 = r0
        L_0x0092:
            r13.setAdapter(r15)
            r13.setupLayoutManager()
            androidx.recyclerview.widget.RecyclerView r15 = r13.playlists
            if (r15 != 0) goto L_0x00a2
            java.lang.String r15 = "playlists"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r15)
            r15 = r0
        L_0x00a2:
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r2 = r13.playlistAdapter
            if (r2 != 0) goto L_0x00aa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r2 = r0
        L_0x00aa:
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = (androidx.recyclerview.widget.RecyclerView.Adapter) r2
            r15.setAdapter(r2)
            android.view.View r14 = r14.getRootView()
            int r15 = org.videolan.vlc.R.id.songs_fast_scroller_playlist
            android.view.View r14 = r14.findViewById(r15)
            java.lang.String r15 = "null cannot be cast to non-null type org.videolan.vlc.gui.view.FastScroller"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r15)
            org.videolan.vlc.gui.view.FastScroller r14 = (org.videolan.vlc.gui.view.FastScroller) r14
            r13.fastScroller = r14
            java.lang.String r15 = "fastScroller"
            if (r14 != 0) goto L_0x00ca
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r15)
            r14 = r0
        L_0x00ca:
            androidx.fragment.app.FragmentActivity r1 = r13.requireActivity()
            int r2 = org.videolan.vlc.R.id.appbar
            android.view.View r1 = r1.findViewById(r2)
            java.lang.String r2 = "null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            com.google.android.material.appbar.AppBarLayout r1 = (com.google.android.material.appbar.AppBarLayout) r1
            androidx.fragment.app.FragmentActivity r2 = r13.requireActivity()
            int r3 = org.videolan.vlc.R.id.coordinator
            android.view.View r2 = r2.findViewById(r3)
            java.lang.String r3 = "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)
            androidx.coordinatorlayout.widget.CoordinatorLayout r2 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r2
            androidx.fragment.app.FragmentActivity r3 = r13.requireActivity()
            int r4 = org.videolan.vlc.R.id.fab
            android.view.View r3 = r3.findViewById(r4)
            java.lang.String r4 = "null cannot be cast to non-null type com.google.android.material.floatingactionbutton.FloatingActionButton"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r4)
            com.google.android.material.floatingactionbutton.FloatingActionButton r3 = (com.google.android.material.floatingactionbutton.FloatingActionButton) r3
            r14.attachToCoordinator(r1, r2, r3)
            org.videolan.vlc.viewmodels.SortableModel r14 = r13.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r14 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r14
            org.videolan.vlc.providers.medialibrary.PlaylistsProvider r14 = r14.getProvider()
            androidx.lifecycle.LiveData r14 = r14.getPagedList()
            androidx.lifecycle.LifecycleOwner r1 = r13.getViewLifecycleOwner()
            org.videolan.vlc.gui.PlaylistFragment$onViewCreated$2 r2 = new org.videolan.vlc.gui.PlaylistFragment$onViewCreated$2
            r2.<init>(r13)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.vlc.gui.PlaylistFragment$sam$androidx_lifecycle_Observer$0 r3 = new org.videolan.vlc.gui.PlaylistFragment$sam$androidx_lifecycle_Observer$0
            r3.<init>(r2)
            androidx.lifecycle.Observer r3 = (androidx.lifecycle.Observer) r3
            r14.observe(r1, r3)
            org.videolan.vlc.viewmodels.SortableModel r14 = r13.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r14 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r14
            org.videolan.vlc.providers.medialibrary.PlaylistsProvider r14 = r14.getProvider()
            androidx.lifecycle.MutableLiveData r14 = r14.getLoading()
            androidx.lifecycle.LifecycleOwner r1 = r13.getViewLifecycleOwner()
            org.videolan.vlc.gui.PlaylistFragment$onViewCreated$3 r2 = new org.videolan.vlc.gui.PlaylistFragment$onViewCreated$3
            r2.<init>(r13)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.vlc.gui.PlaylistFragment$sam$androidx_lifecycle_Observer$0 r3 = new org.videolan.vlc.gui.PlaylistFragment$sam$androidx_lifecycle_Observer$0
            r3.<init>(r2)
            androidx.lifecycle.Observer r3 = (androidx.lifecycle.Observer) r3
            r14.observe(r1, r3)
            org.videolan.vlc.viewmodels.SortableModel r14 = r13.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r14 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r14
            org.videolan.vlc.providers.medialibrary.PlaylistsProvider r14 = r14.getProvider()
            androidx.lifecycle.LiveData r14 = r14.getLiveHeaders()
            androidx.lifecycle.LifecycleOwner r1 = r13.getViewLifecycleOwner()
            org.videolan.vlc.gui.PlaylistFragment$onViewCreated$4 r2 = new org.videolan.vlc.gui.PlaylistFragment$onViewCreated$4
            r2.<init>(r13)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.vlc.gui.PlaylistFragment$sam$androidx_lifecycle_Observer$0 r3 = new org.videolan.vlc.gui.PlaylistFragment$sam$androidx_lifecycle_Observer$0
            r3.<init>(r2)
            androidx.lifecycle.Observer r3 = (androidx.lifecycle.Observer) r3
            r14.observe(r1, r3)
            org.videolan.vlc.gui.view.FastScroller r14 = r13.fastScroller
            if (r14 != 0) goto L_0x0171
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r15)
            r14 = r0
        L_0x0171:
            androidx.recyclerview.widget.RecyclerView r15 = r13.getCurrentRV()
            org.videolan.vlc.viewmodels.SortableModel r1 = r13.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r1 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r1
            org.videolan.vlc.providers.medialibrary.PlaylistsProvider r1 = r1.getProvider()
            org.videolan.resources.util.HeaderProvider r1 = (org.videolan.resources.util.HeaderProvider) r1
            r14.setRecyclerView(r15, r1)
            androidx.fragment.app.Fragment r14 = r13.getParentFragment()
            boolean r15 = r14 instanceof org.videolan.vlc.gui.video.VideoBrowserFragment
            if (r15 == 0) goto L_0x018f
            r0 = r14
            org.videolan.vlc.gui.video.VideoBrowserFragment r0 = (org.videolan.vlc.gui.video.VideoBrowserFragment) r0
        L_0x018f:
            if (r0 != 0) goto L_0x0192
            goto L_0x01a3
        L_0x0192:
            org.videolan.vlc.viewmodels.SortableModel r14 = r13.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r14 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r14
            org.videolan.vlc.providers.medialibrary.PlaylistsProvider r14 = r14.getProvider()
            boolean r14 = r14.getOnlyFavorites()
            r0.setPlaylistOnlyFavorites(r14)
        L_0x01a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.PlaylistFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: org.videolan.vlc.gui.video.VideoBrowserFragment} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDisplaySettingChanged(java.lang.String r4, java.lang.Object r5) {
        /*
            r3 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = r4.hashCode()
            r1 = -341247322(0xffffffffeba8faa6, float:-4.085664E26)
            r2 = 0
            if (r0 == r1) goto L_0x00c6
            r1 = 292773227(0x11735d6b, float:1.9198107E-28)
            if (r0 == r1) goto L_0x0084
            r1 = 1468888228(0x578d74a4, float:3.11064214E14)
            if (r0 == r1) goto L_0x0020
            goto L_0x011c
        L_0x0020:
            java.lang.String r0 = "current_sort"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x002a
            goto L_0x011c
        L_0x002a:
            kotlin.Pair r5 = (kotlin.Pair) r5
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r4 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r4 = r4.getProviders()
            int r0 = r3.getCurrentTab()
            r4 = r4[r0]
            java.lang.Object r0 = r5.getFirst()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            r4.setSort(r0)
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r4 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r4 = r4.getProviders()
            int r0 = r3.getCurrentTab()
            r4 = r4[r0]
            java.lang.Object r5 = r5.getSecond()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setDesc(r5)
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r4 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r4 = r4.getProviders()
            int r5 = r3.getCurrentTab()
            r4 = r4[r5]
            r4.saveSort()
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r4 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r4
            r4.refresh()
            goto L_0x011c
        L_0x0084:
            java.lang.String r0 = "only_favs"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x008e
            goto L_0x011c
        L_0x008e:
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r4 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r4 = r4.getProviders()
            int r0 = r3.getCurrentTab()
            r4 = r4[r0]
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r0 = r5.booleanValue()
            r4.showOnlyFavs(r0)
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r4 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r4
            r4.refresh()
            androidx.fragment.app.Fragment r4 = r3.getParentFragment()
            boolean r0 = r4 instanceof org.videolan.vlc.gui.video.VideoBrowserFragment
            if (r0 == 0) goto L_0x00bb
            r2 = r4
            org.videolan.vlc.gui.video.VideoBrowserFragment r2 = (org.videolan.vlc.gui.video.VideoBrowserFragment) r2
        L_0x00bb:
            if (r2 != 0) goto L_0x00be
            goto L_0x011c
        L_0x00be:
            boolean r4 = r5.booleanValue()
            r2.setPlaylistOnlyFavorites(r4)
            goto L_0x011c
        L_0x00c6:
            java.lang.String r0 = "display_in_cards"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x011c
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r4 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r4
            r0 = r5
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r4.setProviderInCard(r0)
            r3.setupLayoutManager()
            androidx.recyclerview.widget.RecyclerView r4 = r3.playlists
            if (r4 != 0) goto L_0x00eb
            java.lang.String r4 = "playlists"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x00ec
        L_0x00eb:
            r2 = r4
        L_0x00ec:
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r4 = r3.getAdapter()
            androidx.recyclerview.widget.RecyclerView$Adapter r4 = (androidx.recyclerview.widget.RecyclerView.Adapter) r4
            r2.setAdapter(r4)
            androidx.fragment.app.FragmentActivity r4 = r3.getActivity()
            if (r4 == 0) goto L_0x00fe
            r4.invalidateOptionsMenu()
        L_0x00fe:
            org.videolan.tools.Settings r4 = org.videolan.tools.Settings.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r3.requireActivity()
            java.lang.String r1 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Object r4 = r4.getInstance(r0)
            android.content.SharedPreferences r4 = (android.content.SharedPreferences) r4
            org.videolan.vlc.viewmodels.SortableModel r0 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel r0 = (org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel) r0
            java.lang.String r0 = r0.getDisplayModeKey()
            org.videolan.tools.SettingsKt.putSingle(r4, r0, r5)
        L_0x011c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.PlaylistFragment.onDisplaySettingChanged(java.lang.String, java.lang.Object):void");
    }

    /* access modifiers changed from: private */
    public final void updateEmptyView() {
        EmptyLoadingState emptyLoadingState;
        if (isAdded()) {
            getSwipeRefreshLayout().setVisibility(Medialibrary.getInstance().isInitiated() ? 0 : 8);
            PlaylistsFragmentBinding playlistsFragmentBinding = this.binding;
            PlaylistsFragmentBinding playlistsFragmentBinding2 = null;
            if (playlistsFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                playlistsFragmentBinding = null;
            }
            EmptyLoadingStateView emptyLoadingStateView = playlistsFragmentBinding.emptyLoading;
            String filterQuery = ((PlaylistsViewModel) getViewModel()).getFilterQuery();
            String string = filterQuery != null ? getString(R.string.empty_search, filterQuery) : null;
            if (string == null) {
                string = getString(((PlaylistsViewModel) getViewModel()).getProvider().getOnlyFavorites() ? R.string.nofav : R.string.noplaylist);
                Intrinsics.checkNotNull(string);
            }
            emptyLoadingStateView.setEmptyText(string);
            PlaylistsFragmentBinding playlistsFragmentBinding3 = this.binding;
            if (playlistsFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                playlistsFragmentBinding2 = playlistsFragmentBinding3;
            }
            EmptyLoadingStateView emptyLoadingStateView2 = playlistsFragmentBinding2.emptyLoading;
            if (Intrinsics.areEqual((Object) ((PlaylistsViewModel) getViewModel()).getProvider().getLoading().getValue(), (Object) true) && getEmpty()) {
                emptyLoadingState = EmptyLoadingState.LOADING;
            } else if (getEmpty() && ((PlaylistsViewModel) getViewModel()).getProvider().getOnlyFavorites()) {
                emptyLoadingState = EmptyLoadingState.EMPTY_FAVORITES;
            } else if (getEmpty() && ((PlaylistsViewModel) getViewModel()).getFilterQuery() != null) {
                emptyLoadingState = EmptyLoadingState.EMPTY_SEARCH;
            } else if (getEmpty()) {
                emptyLoadingState = EmptyLoadingState.EMPTY_SEARCH;
            } else {
                emptyLoadingState = EmptyLoadingState.NONE;
            }
            emptyLoadingStateView2.setState(emptyLoadingState);
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        AudioBrowserAdapter adapter = getAdapter();
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            MultiSelectHelper multiHelper = getMultiHelper();
            if (multiHelper != null) {
                multiHelper.toggleActionMode(true, itemCount);
            }
        }
        actionMode.getMenuInflater().inflate(R.menu.action_mode_audio_browser, menu);
        menu.findItem(R.id.action_mode_audio_add_playlist).setVisible(false);
        return true;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.ml_menu_sortby).setVisible(false);
        menu.findItem(R.id.ml_menu_display_options).setVisible(true);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != R.id.ml_menu_display_options) {
            return super.onOptionsItemSelected(menuItem);
        }
        Collection arrayList = new ArrayList();
        for (Object next : CollectionsKt.arrayListOf(1, 10, 7, 9, 2, 5, 4, 6, 15)) {
            if (((PlaylistsViewModel) getViewModel()).getProvider().canSortBy(((Number) next).intValue())) {
                arrayList.add(next);
            }
        }
        DisplaySettingsDialog.Companion.newInstance$default(DisplaySettingsDialog.Companion, ((PlaylistsViewModel) getViewModel()).getProviderInCard(), (Boolean) null, Boolean.valueOf(((PlaylistsViewModel) getViewModel()).getProvider().getOnlyFavorites()), (List) arrayList, ((PlaylistsViewModel) getViewModel()).getProvider().getSort(), ((PlaylistsViewModel) getViewModel()).getProvider().getDesc(), (String) null, (Boolean) null, (Boolean) null, 450, (Object) null).show(requireActivity().getSupportFragmentManager(), "DisplaySettingsDialog");
        return true;
    }

    private final void setupLayoutManager() {
        int i;
        int dimension = (int) getResources().getDimension(R.dimen.kl_half);
        RecyclerView recyclerView = this.playlists;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
            recyclerView = null;
        }
        if (recyclerView.getItemDecorationCount() > 0) {
            RecyclerView recyclerView3 = this.playlists;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
                recyclerView3 = null;
            }
            recyclerView3.removeItemDecorationAt(0);
        }
        int i2 = -1;
        if (((PlaylistsViewModel) getViewModel()).getProviderInCard()) {
            FragmentActivity requireActivity = requireActivity();
            INavigator iNavigator = requireActivity instanceof INavigator ? (INavigator) requireActivity : null;
            if (iNavigator != null) {
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                i = iNavigator.getFragmentWidth(requireActivity2);
            } else {
                FragmentActivity requireActivity3 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                i = KextensionsKt.getScreenWidth(requireActivity3);
            }
            AudioBrowserAdapter adapter = getAdapter();
            if (adapter != null) {
                adapter.setCardSize$vlc_android_release(RecyclerSectionItemGridDecoration.Companion.getItemSize(i, getNbColumns(), dimension, KotlinExtensionsKt.getDp(16)));
            }
            AudioBrowserAdapter adapter2 = getAdapter();
            if (adapter2 != null) {
                RecyclerView recyclerView4 = this.playlists;
                if (recyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
                    recyclerView4 = null;
                }
                PlaylistsProvider provider = ((PlaylistsViewModel) getViewModel()).getProvider();
                Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.media.MediaLibraryItem>");
                displayListInGrid(recyclerView4, adapter2, provider, dimension);
            }
        } else {
            AudioBrowserAdapter adapter3 = getAdapter();
            if (adapter3 != null) {
                adapter3.setCardSize$vlc_android_release(-1);
            }
            RecyclerView recyclerView5 = this.playlists;
            if (recyclerView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
                recyclerView5 = null;
            }
            recyclerView5.addItemDecoration(new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height), true, ((PlaylistsViewModel) getViewModel()).getProvider()));
            RecyclerView recyclerView6 = this.playlists;
            if (recyclerView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
                recyclerView6 = null;
            }
            recyclerView6.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        RecyclerView recyclerView7 = this.playlists;
        if (recyclerView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
            recyclerView7 = null;
        }
        ViewGroup.LayoutParams layoutParams = recyclerView7.getLayoutParams();
        float dimension2 = requireActivity().getResources().getDimension(R.dimen.default_content_width);
        if (!((PlaylistsViewModel) getViewModel()).getProviderInCard()) {
            i2 = (int) dimension2;
        }
        layoutParams.width = i2;
        RecyclerView recyclerView8 = this.playlists;
        if (recyclerView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
            recyclerView8 = null;
        }
        ViewParent parent = recyclerView8.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        ((View) parent).setBackgroundColor((((PlaylistsViewModel) getViewModel()).getProviderInCard() || dimension2 == -1.0f) ? ContextCompat.getColor(requireContext(), R.color.transparent) : getBackgroundColor());
        RecyclerView recyclerView9 = this.playlists;
        if (recyclerView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
        } else {
            recyclerView2 = recyclerView9;
        }
        recyclerView2.setBackgroundColor((((PlaylistsViewModel) getViewModel()).getProviderInCard() || dimension2 == -1.0f) ? ContextCompat.getColor(requireContext(), R.color.transparent) : getListColor());
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (getActionMode() == null) {
            Intent intent = new Intent(getActivity(), HeaderMediaListActivity.class);
            intent.putExtra("ML_ITEM", mediaLibraryItem);
            startActivity(intent);
            return;
        }
        super.onClick(view, i, mediaLibraryItem);
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        if (contextOption == ContextOption.CTX_PLAY_ALL) {
            PlaylistsProvider provider = ((PlaylistsViewModel) getViewModel()).getProvider();
            Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.interfaces.media.MediaWrapper>");
            MediaUtils.INSTANCE.playAll(getActivity(), provider, i, false);
            return;
        }
        super.onCtxAction(i, contextOption);
    }

    public void onRefresh() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            MediaParsingServiceKt.reloadLibrary(activity);
        }
    }

    public String getTitle() {
        String string = getString(R.string.playlists);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* access modifiers changed from: protected */
    public RecyclerView getCurrentRV() {
        RecyclerView recyclerView = this.playlists;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Constants.ID_PLAYLISTS);
        return null;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/PlaylistFragment$Companion;", "", "()V", "PLAYLIST_TYPE", "", "newInstance", "Lorg/videolan/vlc/gui/PlaylistFragment;", "type", "Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlaylistFragment newInstance(Playlist.Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            PlaylistFragment playlistFragment = new PlaylistFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(PlaylistFragment.PLAYLIST_TYPE, type.ordinal());
            playlistFragment.setArguments(bundle);
            return playlistFragment;
        }
    }
}
