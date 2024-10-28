package org.videolan.vlc.gui.audio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.AudioBrowserBinding;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.ContentActivity;
import org.videolan.vlc.gui.HeaderMediaListActivity;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialog;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialogKt;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.util.AccessibilityHelperKt;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.Permissions;
import org.videolan.vlc.viewmodels.PlaylistModel;
import org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel;
import org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModelKt;

@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001^B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020\u0011H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0016J\b\u0010#\u001a\u00020\tH\u0016J\b\u0010$\u001a\u00020\u0016H\u0014J\b\u0010%\u001a\u00020\u0005H\u0016J \u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020/H\u0016J\u0012\u00100\u001a\u00020'2\b\u00101\u001a\u0004\u0018\u000102H\u0016J&\u00103\u001a\u0004\u0018\u00010)2\u0006\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u0001072\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0018\u00108\u001a\u00020'2\u0006\u0010*\u001a\u00020\u001c2\u0006\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020'H\u0016J\u0018\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020\u00052\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020)H\u0016J\u0010\u0010B\u001a\u00020\u00112\u0006\u0010+\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u00020'2\u0006\u0010*\u001a\u00020\u001cH\u0016J\u0010\u0010E\u001a\u00020'2\u0006\u0010F\u001a\u00020GH\u0016J\u0010\u0010H\u001a\u00020'2\u0006\u0010I\u001a\u000202H\u0016J\b\u0010J\u001a\u00020'H\u0016J\u0010\u0010K\u001a\u00020'2\u0006\u0010L\u001a\u00020MH\u0016J\u0010\u0010N\u001a\u00020'2\u0006\u0010L\u001a\u00020MH\u0016J\u0010\u0010O\u001a\u00020'2\u0006\u0010L\u001a\u00020MH\u0016J\u0014\u0010P\u001a\u00020'2\n\u0010Q\u001a\u0006\u0012\u0002\b\u00030RH\u0016J\u001a\u0010S\u001a\u00020'2\u0006\u0010A\u001a\u00020)2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0012\u0010T\u001a\u00020'2\b\b\u0002\u0010U\u001a\u00020\u0011H\u0002J\b\u0010V\u001a\u00020'H\u0002J\u0012\u0010W\u001a\u00020'2\b\b\u0002\u0010X\u001a\u00020\u001cH\u0002J\b\u0010Y\u001a\u00020'H\u0016J\u0010\u0010Z\u001a\u00020'2\u0006\u0010[\u001a\u00020\u001cH\u0016J\b\u0010\\\u001a\u00020'H\u0002J\b\u0010]\u001a\u00020'H\u0002R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\b\u0007R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioBrowserFragment;", "Lorg/videolan/vlc/gui/audio/BaseAudioBrowser;", "Lorg/videolan/vlc/viewmodels/mobile/AudioBrowserViewModel;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TAG$1", "albumsAdapter", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "artistsAdapter", "audioPagerAdapter", "Lorg/videolan/vlc/gui/audio/AudioPagerAdapter;", "binding", "Lorg/videolan/vlc/databinding/AudioBrowserBinding;", "genresAdapter", "hasTabs", "", "getHasTabs", "()Z", "lists", "", "Landroidx/recyclerview/widget/RecyclerView;", "playlistAdapter", "playlistModel", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "restorePositions", "Landroid/util/SparseArray;", "", "settings", "Landroid/content/SharedPreferences;", "songsAdapter", "spacing", "allowedToExpand", "enableSearchOption", "getCurrentAdapter", "getCurrentRV", "getTitle", "onClick", "", "v", "Landroid/view/View;", "position", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDestroy", "onDisplaySettingChanged", "key", "value", "", "onFabPlayClick", "view", "onOptionsItemSelected", "Landroid/view/MenuItem;", "onPageSelected", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onSaveInstanceState", "outState", "onStart", "onTabReselected", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "onUpdateFinished", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "onViewCreated", "setFabPlayShuffleAllVisibility", "force", "setupModels", "setupProvider", "index", "setupTabLayout", "sortBy", "sort", "updateEmptyView", "updateTabs", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioBrowserFragment.kt */
public final class AudioBrowserFragment extends BaseAudioBrowser<AudioBrowserViewModel> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_LISTS_POSITIONS = "key_lists_position";
    private static final int MODE_TOTAL = 5;
    public static final String TAG = "VLC/AudioBrowserFragment";
    public static final String TAG_ITEM = "ML_ITEM";
    private final String TAG$1 = getClass().getName();
    private AudioBrowserAdapter albumsAdapter;
    private AudioBrowserAdapter artistsAdapter;
    private AudioPagerAdapter audioPagerAdapter;
    /* access modifiers changed from: private */
    public AudioBrowserBinding binding;
    private AudioBrowserAdapter genresAdapter;
    private final boolean hasTabs = true;
    /* access modifiers changed from: private */
    public final List<RecyclerView> lists = new ArrayList();
    private AudioBrowserAdapter playlistAdapter;
    /* access modifiers changed from: private */
    public PlaylistModel playlistModel;
    /* access modifiers changed from: private */
    public SparseArray<Integer> restorePositions = new SparseArray<>();
    private SharedPreferences settings;
    /* access modifiers changed from: private */
    public AudioBrowserAdapter songsAdapter;
    private int spacing;

    public boolean enableSearchOption() {
        return true;
    }

    public boolean getHasTabs() {
        return this.hasTabs;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.spacing = (int) requireActivity().getResources().getDimension(R.dimen.kl_small);
        PlaylistManager.Companion.getCurrentPlayedMedia().observe(this, new AudioBrowserFragment$sam$androidx_lifecycle_Observer$0(new AudioBrowserFragment$onCreate$1(this)));
        if (this.settings == null) {
            Settings settings2 = Settings.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            this.settings = (SharedPreferences) settings2.getInstance(requireContext);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        AudioBrowserBinding inflate = AudioBrowserBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        ArrayList<Integer> integerArrayList;
        Iterable<IndexedValue> withIndex;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        AppBarLayout appBarLayout = (AppBarLayout) view.getRootView().findViewById(R.id.appbar);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view.getRootView().findViewById(R.id.coordinator);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.getRootView().findViewById(R.id.fab);
        AudioBrowserBinding audioBrowserBinding = this.binding;
        AudioPagerAdapter audioPagerAdapter2 = null;
        if (audioBrowserBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioBrowserBinding = null;
        }
        FastScroller fastScroller = audioBrowserBinding.songsFastScroller;
        Intrinsics.checkNotNull(appBarLayout);
        Intrinsics.checkNotNull(coordinatorLayout);
        fastScroller.attachToCoordinator(appBarLayout, coordinatorLayout, floatingActionButton);
        AudioBrowserBinding audioBrowserBinding2 = this.binding;
        if (audioBrowserBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioBrowserBinding2 = null;
        }
        audioBrowserBinding2.audioEmptyLoading.setOnNoMediaClickListener(new AudioBrowserFragment$onViewCreated$1(this));
        ArrayList arrayList = new ArrayList(5);
        for (int i = 0; i < 5; i++) {
            View childAt = getViewPager().getChildAt(i);
            arrayList.add(childAt);
            List<RecyclerView> list = this.lists;
            View findViewById = childAt.findViewById(R.id.audio_list);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            list.add(findViewById);
        }
        getViewPager().setOffscreenPageLimit(4);
        this.audioPagerAdapter = new AudioPagerAdapter((View[]) arrayList.toArray(new View[0]), new String[]{getString(R.string.artists), getString(R.string.albums), getString(R.string.tracks), getString(R.string.genres), getString(R.string.playlists)});
        ViewPager viewPager = getViewPager();
        AudioPagerAdapter audioPagerAdapter3 = this.audioPagerAdapter;
        if (audioPagerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPagerAdapter");
        } else {
            audioPagerAdapter2 = audioPagerAdapter3;
        }
        viewPager.setAdapter(audioPagerAdapter2);
        if (!(bundle == null || (integerArrayList = bundle.getIntegerArrayList(KEY_LISTS_POSITIONS)) == null || (withIndex = CollectionsKt.withIndex(integerArrayList)) == null)) {
            for (IndexedValue indexedValue : withIndex) {
                this.restorePositions.put(indexedValue.getIndex(), indexedValue.getValue());
            }
        }
        if (this.songsAdapter == null) {
            setupModels();
        }
        if (((AudioBrowserViewModel) getViewModel()).getShowResumeCard()) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
            ((AudioPlayerContainerActivity) requireActivity).proposeCard();
            ((AudioBrowserViewModel) getViewModel()).setShowResumeCard(false);
        }
        for (int i2 = 0; i2 < 5; i2++) {
            MedialibraryProvider medialibraryProvider = ((AudioBrowserViewModel) getViewModel()).getProviders()[i2];
            Intrinsics.checkNotNull(medialibraryProvider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.media.MediaLibraryItem>");
            setupLayoutManager(((AudioBrowserViewModel) getViewModel()).getProvidersInCard()[i2].booleanValue(), this.lists.get(i2), medialibraryProvider, getAdapters$vlc_android_release()[i2], this.spacing);
            RecyclerView.LayoutManager layoutManager = this.lists.get(i2).getLayoutManager();
            Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            ((LinearLayoutManager) layoutManager).setRecycleChildrenOnDetach(true);
            RecyclerView recyclerView = this.lists.get(i2);
            recyclerView.setAdapter(getAdapters$vlc_android_release()[i2]);
            recyclerView.addOnScrollListener(getScrollListener$vlc_android_release());
        }
        getViewPager().setOnTouchListener(getSwipeFilter());
        getSwipeRefreshLayout().setOnRefreshListener(new AudioBrowserFragment$$ExternalSyntheticLambda0(this));
        getViewPager().addOnPageChangeListener(new AudioBrowserFragment$onViewCreated$5(this));
        for (AudioBrowserAdapter onAnyChange : getAdapters$vlc_android_release()) {
            KextensionsKt.onAnyChange(onAnyChange, new AudioBrowserFragment$onViewCreated$6$1(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(AudioBrowserFragment audioBrowserFragment) {
        Intrinsics.checkNotNullParameter(audioBrowserFragment, "this$0");
        FragmentActivity requireActivity = audioBrowserFragment.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.videolan.vlc.gui.ContentActivity");
        ((ContentActivity) requireActivity).closeSearchView();
        ((AudioBrowserViewModel) audioBrowserFragment.getViewModel()).refresh();
    }

    public void onDestroy() {
        getViewPager().setOnTouchListener((View.OnTouchListener) null);
        super.onDestroy();
    }

    public void onDisplaySettingChanged(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(obj, "value");
        switch (str.hashCode()) {
            case -434921684:
                if (str.equals(DisplaySettingsDialogKt.SHOW_ALL_ARTISTS)) {
                    Settings settings2 = Settings.INSTANCE;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                    Boolean bool = (Boolean) obj;
                    SettingsKt.putSingle((SharedPreferences) settings2.getInstance(requireActivity), SettingsKt.KEY_ARTISTS_SHOW_ALL, bool);
                    ((AudioBrowserViewModel) getViewModel()).getArtistsProvider().setShowAll(bool.booleanValue());
                    ((AudioBrowserViewModel) getViewModel()).refresh();
                    return;
                }
                return;
            case -341247322:
                if (str.equals(DisplaySettingsDialogKt.DISPLAY_IN_CARDS)) {
                    ((AudioBrowserViewModel) getViewModel()).getProvidersInCard()[getCurrentTab()] = obj;
                    MedialibraryProvider medialibraryProvider = ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()];
                    Intrinsics.checkNotNull(medialibraryProvider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.media.MediaLibraryItem>");
                    setupLayoutManager(((AudioBrowserViewModel) getViewModel()).getProvidersInCard()[getCurrentTab()].booleanValue(), this.lists.get(getCurrentTab()), medialibraryProvider, getAdapters$vlc_android_release()[getCurrentTab()], this.spacing);
                    this.lists.get(getCurrentTab()).setAdapter(getAdapters$vlc_android_release()[getCurrentTab()]);
                    if (getCurrentTab() == 2) {
                        AudioBrowserAdapter audioBrowserAdapter = this.songsAdapter;
                        AudioBrowserAdapter audioBrowserAdapter2 = null;
                        if (audioBrowserAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
                            audioBrowserAdapter = null;
                        }
                        if (audioBrowserAdapter.getCurrentMedia() != null) {
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
                                audioBrowserAdapter2 = audioBrowserAdapter4;
                            }
                            audioBrowserAdapter2.setCurrentMedia(PlaylistManager.Companion.getCurrentPlayedMedia().getValue());
                        }
                    }
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        activity.invalidateOptionsMenu();
                    }
                    Settings settings3 = Settings.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    SettingsKt.putSingle((SharedPreferences) settings3.getInstance(requireActivity2), ((AudioBrowserViewModel) getViewModel()).getDisplayModeKeys()[getCurrentTab()], obj);
                    return;
                }
                return;
            case 292773227:
                if (str.equals(DisplaySettingsDialogKt.ONLY_FAVS)) {
                    ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].showOnlyFavs(((Boolean) obj).booleanValue());
                    ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].refresh();
                    updateTabs();
                    return;
                }
                return;
            case 1468888228:
                if (str.equals(DisplaySettingsDialogKt.CURRENT_SORT)) {
                    Pair pair = (Pair) obj;
                    ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].setSort(((Number) pair.getFirst()).intValue());
                    ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].setDesc(((Boolean) pair.getSecond()).booleanValue());
                    ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].saveSort();
                    ((AudioBrowserViewModel) getViewModel()).refresh();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        for (int i = 0; i < 5; i++) {
            if (i < this.lists.size() && i < getAdapters$vlc_android_release().length && (this.lists.get(i).getLayoutManager() instanceof GridLayoutManager)) {
                RecyclerView.LayoutManager layoutManager = this.lists.get(i).getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.GridLayoutManager");
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanCount(getNbColumns());
                this.lists.get(i).setLayoutManager(gridLayoutManager);
                getAdapters$vlc_android_release()[i].notifyItemRangeChanged(gridLayoutManager.findFirstVisibleItemPosition(), gridLayoutManager.findLastVisibleItemPosition() - gridLayoutManager.findFirstVisibleItemPosition());
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        ArrayList arrayList = new ArrayList(5);
        for (int i = 0; i < 5; i++) {
            RecyclerView.LayoutManager layoutManager = this.lists.get(i).getLayoutManager();
            Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            arrayList.add(Integer.valueOf(((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition()));
        }
        bundle.putIntegerArrayList(KEY_LISTS_POSITIONS, arrayList);
        super.onSaveInstanceState(bundle);
    }

    private final void setupModels() {
        setViewModel(AudioBrowserViewModelKt.getViewModel(this));
        setCurrentTab(((AudioBrowserViewModel) getViewModel()).getCurrentTab());
        IEventsHandler iEventsHandler = this;
        IEventsHandler iEventsHandler2 = iEventsHandler;
        AudioBrowserAdapter audioBrowserAdapter = new AudioBrowserAdapter(4, iEventsHandler2, (IListEventsHandler) null, false, 0, 28, (DefaultConstructorMarker) null);
        audioBrowserAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        this.artistsAdapter = audioBrowserAdapter;
        AudioBrowserAdapter audioBrowserAdapter2 = new AudioBrowserAdapter(2, iEventsHandler2, (IListEventsHandler) null, false, 0, 28, (DefaultConstructorMarker) null);
        audioBrowserAdapter2.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        this.albumsAdapter = audioBrowserAdapter2;
        AudioBrowserAdapter audioBrowserAdapter3 = new AudioBrowserAdapter(32, iEventsHandler2, (IListEventsHandler) null, false, 0, 28, (DefaultConstructorMarker) null);
        audioBrowserAdapter3.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        this.songsAdapter = audioBrowserAdapter3;
        this.playlistModel = PlaylistModel.Companion.get((Fragment) this);
        AudioBrowserAdapter audioBrowserAdapter4 = this.songsAdapter;
        if (audioBrowserAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
            audioBrowserAdapter4 = null;
        }
        PlaylistModel playlistModel2 = this.playlistModel;
        if (playlistModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistModel");
            playlistModel2 = null;
        }
        audioBrowserAdapter4.setModel(playlistModel2);
        PlaylistModel playlistModel3 = this.playlistModel;
        if (playlistModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistModel");
            playlistModel3 = null;
        }
        KextensionsKt.launchWhenStarted(FlowKt.onEach(FlowKt.conflate(FlowLiveDataConversions.asFlow(playlistModel3.getDataset())), new AudioBrowserFragment$setupModels$4(this, (Continuation<? super AudioBrowserFragment$setupModels$4>) null)), LifecycleOwnerKt.getLifecycleScope(this));
        IEventsHandler iEventsHandler3 = iEventsHandler;
        AudioBrowserAdapter audioBrowserAdapter5 = new AudioBrowserAdapter(8, iEventsHandler3, (IListEventsHandler) null, false, 0, 28, (DefaultConstructorMarker) null);
        audioBrowserAdapter5.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        this.genresAdapter = audioBrowserAdapter5;
        AudioBrowserAdapter audioBrowserAdapter6 = new AudioBrowserAdapter(16, iEventsHandler3, (IListEventsHandler) null, false, 0, 28, (DefaultConstructorMarker) null);
        audioBrowserAdapter6.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        this.playlistAdapter = audioBrowserAdapter6;
        AudioBrowserAdapter[] audioBrowserAdapterArr = new AudioBrowserAdapter[5];
        AudioBrowserAdapter audioBrowserAdapter7 = this.artistsAdapter;
        if (audioBrowserAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("artistsAdapter");
            audioBrowserAdapter7 = null;
        }
        audioBrowserAdapterArr[0] = audioBrowserAdapter7;
        AudioBrowserAdapter audioBrowserAdapter8 = this.albumsAdapter;
        if (audioBrowserAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("albumsAdapter");
            audioBrowserAdapter8 = null;
        }
        audioBrowserAdapterArr[1] = audioBrowserAdapter8;
        AudioBrowserAdapter audioBrowserAdapter9 = this.songsAdapter;
        if (audioBrowserAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
            audioBrowserAdapter9 = null;
        }
        audioBrowserAdapterArr[2] = audioBrowserAdapter9;
        AudioBrowserAdapter audioBrowserAdapter10 = this.genresAdapter;
        if (audioBrowserAdapter10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genresAdapter");
            audioBrowserAdapter10 = null;
        }
        audioBrowserAdapterArr[3] = audioBrowserAdapter10;
        AudioBrowserAdapter audioBrowserAdapter11 = this.playlistAdapter;
        if (audioBrowserAdapter11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
            audioBrowserAdapter11 = null;
        }
        audioBrowserAdapterArr[4] = audioBrowserAdapter11;
        setAdapters$vlc_android_release(audioBrowserAdapterArr);
        setupProvider$default(this, 0, 1, (Object) null);
    }

    static /* synthetic */ void setupProvider$default(AudioBrowserFragment audioBrowserFragment, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = ((AudioBrowserViewModel) audioBrowserFragment.getViewModel()).getCurrentTab();
        }
        audioBrowserFragment.setupProvider(i);
    }

    private final void setupProvider(int i) {
        MedialibraryProvider medialibraryProvider = ((AudioBrowserViewModel) getViewModel()).getProviders()[RangesKt.coerceIn(i, 0, ((AudioBrowserViewModel) getViewModel()).getProviders().length - 1)];
        if (!medialibraryProvider.getLoading().hasObservers()) {
            LifecycleOwner lifecycleOwner = this;
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new AudioBrowserFragment$setupProvider$1(medialibraryProvider, this, i, (Continuation<? super AudioBrowserFragment$setupProvider$1>) null), 3, (Object) null);
            medialibraryProvider.getLoading().observe(getViewLifecycleOwner(), new AudioBrowserFragment$sam$androidx_lifecycle_Observer$0(new AudioBrowserFragment$setupProvider$2(this, i)));
            medialibraryProvider.getLiveHeaders().observe(getViewLifecycleOwner(), new AudioBrowserFragment$sam$androidx_lifecycle_Observer$0(new AudioBrowserFragment$setupProvider$3(this)));
            LifecycleOwnerKt.getLifecycleScope(lifecycleOwner).launchWhenStarted(new AudioBrowserFragment$setupProvider$4(medialibraryProvider, this, i, (Continuation<? super AudioBrowserFragment$setupProvider$4>) null));
        }
    }

    public void onStart() {
        super.onStart();
        setFabPlayShuffleAllVisibility$default(this, false, 1, (Object) null);
        FloatingActionButton fabPlay = getFabPlay();
        if (fabPlay != null) {
            fabPlay.setImageResource(R.drawable.ic_fab_shuffle);
        }
        FloatingActionButton fabPlay2 = getFabPlay();
        if (fabPlay2 != null) {
            fabPlay2.setContentDescription(getString(R.string.shuffle_play));
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuItem findItem = menu.findItem(R.id.ml_menu_last_playlist);
        if (findItem != null) {
            SharedPreferences sharedPreferences = this.settings;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
                sharedPreferences = null;
            }
            findItem.setVisible(sharedPreferences.contains(Constants.KEY_AUDIO_LAST_PLAYLIST));
        }
        MedialibraryProvider medialibraryProvider = ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()];
        menu.findItem(R.id.ml_menu_sortby).setVisible(false);
        menu.findItem(R.id.ml_menu_display_options).setVisible(true);
        sortMenuTitles(getCurrentTab());
        reopenSearchIfNeeded();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (AccessibilityHelperKt.isTalkbackIsEnabled(requireActivity)) {
            menu.findItem(R.id.shuffle_all).setVisible(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Boolean bool;
        AudioBrowserBinding audioBrowserBinding;
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.shuffle_all) {
            AudioBrowserBinding audioBrowserBinding2 = this.binding;
            if (audioBrowserBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioBrowserBinding = null;
            } else {
                audioBrowserBinding = audioBrowserBinding2;
            }
            EmptyLoadingStateView emptyLoadingStateView = audioBrowserBinding.audioEmptyLoading;
            Intrinsics.checkNotNullExpressionValue(emptyLoadingStateView, "audioEmptyLoading");
            onFabPlayClick(emptyLoadingStateView);
            return true;
        } else if (itemId != R.id.ml_menu_display_options) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            Collection arrayList = new ArrayList();
            for (Object next : CollectionsKt.arrayListOf(1, 10, 7, 9, 2, 5, 4, 6, 15, 3)) {
                if (((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].canSortBy(((Number) next).intValue())) {
                    arrayList.add(next);
                }
            }
            List list = (List) arrayList;
            DisplaySettingsDialog.Companion companion = DisplaySettingsDialog.Companion;
            boolean booleanValue = ((AudioBrowserViewModel) getViewModel()).getProvidersInCard()[getCurrentTab()].booleanValue();
            if (getCurrentTab() == 0) {
                Settings settings2 = Settings.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                bool = Boolean.valueOf(((SharedPreferences) settings2.getInstance(requireActivity)).getBoolean(SettingsKt.KEY_ARTISTS_SHOW_ALL, false));
            } else {
                bool = null;
            }
            DisplaySettingsDialog.Companion.newInstance$default(companion, booleanValue, bool, Boolean.valueOf(((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].getOnlyFavorites()), list, ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].getSort(), ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].getDesc(), (String) null, (Boolean) null, (Boolean) null, 448, (Object) null).show(requireActivity().getSupportFragmentManager(), "DisplaySettingsDialog");
            return true;
        }
    }

    public void sortBy(int i) {
        ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].sort(i);
    }

    public void onFabPlayClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        MediaUtils.INSTANCE.playAll(getActivity(), ((AudioBrowserViewModel) getViewModel()).getTracksProvider(), 0, true);
    }

    static /* synthetic */ void setFabPlayShuffleAllVisibility$default(AudioBrowserFragment audioBrowserFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        audioBrowserFragment.setFabPlayShuffleAllVisibility(z);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r3 = (androidx.paging.PagedList) ((org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].getPagedList().getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setFabPlayShuffleAllVisibility(boolean r3) {
        /*
            r2 = this;
            int r0 = r2.getCurrentTab()
            r1 = 2
            if (r0 != r1) goto L_0x002d
            if (r3 != 0) goto L_0x002b
            org.videolan.vlc.viewmodels.SortableModel r3 = r2.getViewModel()
            org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel r3 = (org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel) r3
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r3 = r3.getProviders()
            int r0 = r2.getCurrentTab()
            r3 = r3[r0]
            androidx.lifecycle.LiveData r3 = r3.getPagedList()
            java.lang.Object r3 = r3.getValue()
            androidx.paging.PagedList r3 = (androidx.paging.PagedList) r3
            if (r3 == 0) goto L_0x002d
            int r3 = r3.size()
            if (r3 <= r1) goto L_0x002d
        L_0x002b:
            r3 = 1
            goto L_0x002e
        L_0x002d:
            r3 = 0
        L_0x002e:
            r2.setFabPlayVisibility(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioBrowserFragment.setFabPlayShuffleAllVisibility(boolean):void");
    }

    public String getTitle() {
        String string = getString(R.string.audio);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* access modifiers changed from: private */
    public final void updateEmptyView() {
        EmptyLoadingState emptyLoadingState;
        String filterQuery;
        if (isAdded()) {
            getSwipeRefreshLayout().setVisibility(Medialibrary.getInstance().isInitiated() ? 0 : 8);
            AudioBrowserBinding audioBrowserBinding = this.binding;
            AudioBrowserBinding audioBrowserBinding2 = null;
            if (audioBrowserBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioBrowserBinding = null;
            }
            EmptyLoadingStateView emptyLoadingStateView = audioBrowserBinding.audioEmptyLoading;
            String filterQuery2 = ((AudioBrowserViewModel) getViewModel()).getFilterQuery();
            String string = filterQuery2 != null ? getString(R.string.empty_search, filterQuery2) : null;
            if (string == null) {
                string = getString(((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].getOnlyFavorites() ? R.string.nofav : R.string.nomedia);
                Intrinsics.checkNotNull(string);
            }
            emptyLoadingStateView.setEmptyText(string);
            AudioBrowserBinding audioBrowserBinding3 = this.binding;
            if (audioBrowserBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                audioBrowserBinding2 = audioBrowserBinding3;
            }
            EmptyLoadingStateView emptyLoadingStateView2 = audioBrowserBinding2.audioEmptyLoading;
            Permissions permissions = Permissions.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            if (!permissions.canReadStorage(requireActivity) && getEmpty()) {
                emptyLoadingState = EmptyLoadingState.MISSING_PERMISSION;
            } else if (Intrinsics.areEqual((Object) ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].getLoading().getValue(), (Object) true) && getEmpty()) {
                emptyLoadingState = EmptyLoadingState.LOADING;
            } else if (emptyAt(getCurrentTab()) && (filterQuery = ((AudioBrowserViewModel) getViewModel()).getFilterQuery()) != null && filterQuery.length() > 0) {
                emptyLoadingState = EmptyLoadingState.EMPTY_SEARCH;
            } else if (emptyAt(getCurrentTab()) && ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].getOnlyFavorites()) {
                emptyLoadingState = EmptyLoadingState.EMPTY_FAVORITES;
            } else if (emptyAt(getCurrentTab())) {
                emptyLoadingState = EmptyLoadingState.EMPTY;
            } else {
                emptyLoadingState = EmptyLoadingState.NONE;
            }
            emptyLoadingStateView2.setState(emptyLoadingState);
        }
    }

    public void setupTabLayout() {
        super.setupTabLayout();
        updateTabs();
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
            if (((AudioBrowserViewModel) getViewModel()).getProviders()[i].getOnlyFavorites()) {
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

    public void onPageSelected(int i) {
        updateEmptyView();
        setFabPlayShuffleAllVisibility$default(this, false, 1, (Object) null);
    }

    public void onTabSelected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        setAdapter(getAdapters$vlc_android_release()[tab.getPosition()]);
        ((AudioBrowserViewModel) getViewModel()).setCurrentTab(tab.getPosition());
        setupProvider$default(this, 0, 1, (Object) null);
        super.onTabSelected(tab);
        AudioBrowserBinding audioBrowserBinding = this.binding;
        if (audioBrowserBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioBrowserBinding = null;
        }
        audioBrowserBinding.songsFastScroller.setRecyclerView(this.lists.get(tab.getPosition()), ((AudioBrowserViewModel) getViewModel()).getProviders()[tab.getPosition()]);
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        SettingsKt.putSingle(sharedPreferences, Constants.KEY_AUDIO_CURRENT_TAB, Integer.valueOf(tab.getPosition()));
        if (Medialibrary.getInstance().isInitiated()) {
            BaseFragment.setRefreshing$default(this, ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()].isRefreshing(), (Function1) null, 2, (Object) null);
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    public void onTabUnselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        super.onTabUnselected(tab);
        onDestroyActionMode$vlc_android_release((AudioBrowserAdapter) this.lists.get(tab.getPosition()).getAdapter());
        ((AudioBrowserViewModel) getViewModel()).restore();
    }

    public void onTabReselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        this.lists.get(tab.getPosition()).smoothScrollToPosition(0);
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        if (contextOption == ContextOption.CTX_PLAY_ALL) {
            MedialibraryProvider medialibraryProvider = ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()];
            Intrinsics.checkNotNull(medialibraryProvider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.interfaces.media.MediaWrapper>");
            MediaUtils.INSTANCE.playAll(getActivity(), medialibraryProvider, i, false);
            return;
        }
        super.onCtxAction(i, contextOption);
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intent intent;
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (getActionMode() != null) {
            super.onClick(view, i, mediaLibraryItem);
            return;
        }
        if (inSearchMode()) {
            UiTools.INSTANCE.setKeyboardVisibility(view, false);
        }
        if (mediaLibraryItem.getItemType() != 32) {
            int itemType = mediaLibraryItem.getItemType();
            if (itemType != 2) {
                if (itemType == 4 || itemType == 8) {
                    intent = new Intent(getActivity(), SecondaryActivity.class);
                    intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.ALBUMS_SONGS);
                    intent.putExtra("ML_ITEM", mediaLibraryItem);
                    startActivity(intent);
                } else if (itemType != 16) {
                    return;
                }
            }
            intent = new Intent(getActivity(), HeaderMediaListActivity.class);
            intent.putExtra("ML_ITEM", mediaLibraryItem);
            startActivity(intent);
        } else if (!(mediaLibraryItem instanceof MediaWrapper) || ((MediaWrapper) mediaLibraryItem).isPresent()) {
            Log.d(this.TAG$1, "onClick: skbench: ");
            Settings settings2 = Settings.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            if (((SharedPreferences) settings2.getInstance(requireContext)).getBoolean(SettingsKt.FORCE_PLAY_ALL_AUDIO, false)) {
                MedialibraryProvider medialibraryProvider = ((AudioBrowserViewModel) getViewModel()).getProviders()[getCurrentTab()];
                Intrinsics.checkNotNull(medialibraryProvider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.MedialibraryProvider<org.videolan.medialibrary.interfaces.media.MediaWrapper>");
                MediaUtils.INSTANCE.playAll(getActivity(), medialibraryProvider, i, false);
                return;
            }
            MediaUtils.INSTANCE.openMedia(getActivity(), (MediaWrapper) mediaLibraryItem);
        } else {
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            uiTools.snackerMissing(requireActivity);
        }
    }

    public void onUpdateFinished(RecyclerView.Adapter<?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        super.onUpdateFinished(adapter);
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new AudioBrowserFragment$onUpdateFinished$1(adapter, this, (Continuation<? super AudioBrowserFragment$onUpdateFinished$1>) null));
    }

    /* access modifiers changed from: protected */
    public RecyclerView getCurrentRV() {
        return this.lists.get(getCurrentTab());
    }

    public AudioBrowserAdapter getCurrentAdapter() {
        return getAdapters$vlc_android_release()[getCurrentTab()];
    }

    public boolean allowedToExpand() {
        return getCurrentRV().getScrollState() == 0;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioBrowserFragment$Companion;", "", "()V", "KEY_LISTS_POSITIONS", "", "MODE_TOTAL", "", "TAG", "TAG_ITEM", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioBrowserFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
