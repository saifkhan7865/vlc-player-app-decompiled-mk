package org.videolan.television.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.television.ui.audioplayer.AudioPlayerActivity;
import org.videolan.television.ui.browser.VerticalGridActivity;
import org.videolan.television.ui.preferences.PreferencesActivity;
import org.videolan.television.viewmodel.MainTvModel;
import org.videolan.tools.Settings;
import org.videolan.vlc.MediaParsingServiceKt;
import org.videolan.vlc.R;
import org.videolan.vlc.RecommendationsService;
import org.videolan.vlc.StartActivity;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.helpers.hf.PinCodeDelegate;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u001dH\u0002J\u0010\u00107\u001a\u0002052\u0006\u00108\u001a\u000209H\u0002J\u0012\u0010:\u001a\u0002052\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0012\u0010=\u001a\u0002052\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J0\u0010@\u001a\u0002052\b\u0010A\u001a\u0004\u0018\u00010B2\b\u0010C\u001a\u0004\u0018\u0001012\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J0\u0010H\u001a\u0002052\b\u0010A\u001a\u0004\u0018\u00010B2\b\u0010C\u001a\u0004\u0018\u0001012\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\b\u0010I\u001a\u000205H\u0016J\b\u0010J\u001a\u000205H\u0016J\u001a\u0010K\u001a\u0002052\u0006\u0010L\u001a\u00020<2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\b\u0010M\u001a\u000205H\u0002J\b\u0010N\u001a\u000205H\u0002J\u0006\u0010O\u001a\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lorg/videolan/television/ui/MainTvFragment;", "Landroidx/leanback/app/BrowseSupportFragment;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "Landroid/view/View$OnClickListener;", "()V", "audioRow", "Landroidx/leanback/widget/ListRow;", "backgroundManager", "Landroidx/leanback/app/BackgroundManager;", "browserAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "browsersRow", "categoriesAdapter", "displayFavorites", "", "displayHistory", "displayNowPlaying", "displayPlaylist", "displayRecentlyAdded", "displayRecentlyPlayed", "favoritesAdapter", "favoritesRow", "historyAdapter", "historyRow", "lines", "", "loadedLines", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "miscRow", "model", "Lorg/videolan/television/viewmodel/MainTvModel;", "getModel$television_release", "()Lorg/videolan/television/viewmodel/MainTvModel;", "setModel$television_release", "(Lorg/videolan/television/viewmodel/MainTvModel;)V", "nowPlayingAdapter", "nowPlayingRow", "otherAdapter", "playlistAdapter", "playlistRow", "recentlyAdddedRow", "recentlyAddedAdapter", "recentlyPlayedAdapter", "recentlyPlayedRow", "rowsAdapter", "selectedItem", "", "videoAdapter", "videoRow", "addAndCheckLoadedLines", "", "header", "manageDonationVisibility", "donateCard", "Lorg/videolan/television/ui/GenericCardItem;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "onItemSelected", "onStart", "onStop", "onViewCreated", "view", "registerDatasets", "resetLines", "showDetails", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainTvFragment.kt */
public final class MainTvFragment extends BrowseSupportFragment implements OnItemViewSelectedListener, OnItemViewClickedListener, View.OnClickListener {
    private ListRow audioRow;
    private BackgroundManager backgroundManager;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter browserAdapter;
    private ListRow browsersRow;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter categoriesAdapter;
    /* access modifiers changed from: private */
    public boolean displayFavorites;
    /* access modifiers changed from: private */
    public boolean displayHistory;
    /* access modifiers changed from: private */
    public boolean displayNowPlaying;
    /* access modifiers changed from: private */
    public boolean displayPlaylist;
    /* access modifiers changed from: private */
    public boolean displayRecentlyAdded;
    /* access modifiers changed from: private */
    public boolean displayRecentlyPlayed;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter favoritesAdapter;
    private ListRow favoritesRow;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter historyAdapter;
    private ListRow historyRow;
    private int lines = 7;
    private ArrayList<Long> loadedLines = new ArrayList<>();
    private ListRow miscRow;
    public MainTvModel model;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter nowPlayingAdapter;
    private ListRow nowPlayingRow;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter otherAdapter;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter playlistAdapter;
    private ListRow playlistRow;
    private ListRow recentlyAdddedRow;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter recentlyAddedAdapter;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter recentlyPlayedAdapter;
    private ListRow recentlyPlayedRow;
    private ArrayObjectAdapter rowsAdapter;
    private Object selectedItem;
    /* access modifiers changed from: private */
    public ArrayObjectAdapter videoAdapter;
    private ListRow videoRow;

    public final MainTvModel getModel$television_release() {
        MainTvModel mainTvModel = this.model;
        if (mainTvModel != null) {
            return mainTvModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("model");
        return null;
    }

    public final void setModel$television_release(MainTvModel mainTvModel) {
        Intrinsics.checkNotNullParameter(mainTvModel, "<set-?>");
        this.model = mainTvModel;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHeadersState(1);
        setTitle(getString(R.string.app_name));
        setBadgeDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.icon));
        if (AndroidDevices.INSTANCE.getHasPlayServices()) {
            setOnSearchClickedListener(this);
            setSearchAffordanceColor(ContextCompat.getColor(requireContext(), R.color.orange600));
        }
        setBrandColor(ContextCompat.getColor(requireContext(), R.color.orange900));
        BackgroundManager instance = BackgroundManager.getInstance(requireActivity());
        instance.attach(requireActivity().getWindow());
        this.backgroundManager = instance;
        setModel$television_release(MainTvModel.Companion.getMainTvModel(this));
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.rowsAdapter = new ArrayObjectAdapter((Presenter) new ListRowPresenter());
        Activity activity = requireActivity;
        this.nowPlayingAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(activity, false, false, 6, (DefaultConstructorMarker) null));
        HeaderItem headerItem = new HeaderItem(34, getString(R.string.music_now_playing));
        ArrayObjectAdapter arrayObjectAdapter = this.nowPlayingAdapter;
        ArrayObjectAdapter arrayObjectAdapter2 = null;
        if (arrayObjectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nowPlayingAdapter");
            arrayObjectAdapter = null;
        }
        this.nowPlayingRow = new ListRow(headerItem, arrayObjectAdapter);
        ArrayObjectAdapter arrayObjectAdapter3 = this.rowsAdapter;
        if (arrayObjectAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter3 = null;
        }
        ListRow listRow = this.nowPlayingRow;
        if (listRow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nowPlayingRow");
            listRow = null;
        }
        arrayObjectAdapter3.add(listRow);
        this.recentlyPlayedAdapter = new ArrayObjectAdapter((Presenter) new MetadataCardPresenter(activity));
        HeaderItem headerItem2 = new HeaderItem(32, getString(R.string.recently_played));
        ArrayObjectAdapter arrayObjectAdapter4 = this.recentlyPlayedAdapter;
        if (arrayObjectAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recentlyPlayedAdapter");
            arrayObjectAdapter4 = null;
        }
        this.recentlyPlayedRow = new ListRow(headerItem2, arrayObjectAdapter4);
        ArrayObjectAdapter arrayObjectAdapter5 = this.rowsAdapter;
        if (arrayObjectAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter5 = null;
        }
        ListRow listRow2 = this.recentlyPlayedRow;
        if (listRow2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recentlyPlayedRow");
            listRow2 = null;
        }
        arrayObjectAdapter5.add(listRow2);
        this.recentlyAddedAdapter = new ArrayObjectAdapter((Presenter) new MetadataCardPresenter(activity));
        HeaderItem headerItem3 = new HeaderItem(33, getString(R.string.recently_added));
        ArrayObjectAdapter arrayObjectAdapter6 = this.recentlyAddedAdapter;
        if (arrayObjectAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recentlyAddedAdapter");
            arrayObjectAdapter6 = null;
        }
        this.recentlyAdddedRow = new ListRow(headerItem3, arrayObjectAdapter6);
        ArrayObjectAdapter arrayObjectAdapter7 = this.rowsAdapter;
        if (arrayObjectAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter7 = null;
        }
        ListRow listRow3 = this.recentlyAdddedRow;
        if (listRow3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recentlyAdddedRow");
            listRow3 = null;
        }
        arrayObjectAdapter7.add(listRow3);
        this.videoAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(activity, false, false, 6, (DefaultConstructorMarker) null));
        HeaderItem headerItem4 = new HeaderItem(0, getString(R.string.video));
        ArrayObjectAdapter arrayObjectAdapter8 = this.videoAdapter;
        if (arrayObjectAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoAdapter");
            arrayObjectAdapter8 = null;
        }
        this.videoRow = new ListRow(headerItem4, arrayObjectAdapter8);
        ArrayObjectAdapter arrayObjectAdapter9 = this.rowsAdapter;
        if (arrayObjectAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter9 = null;
        }
        ListRow listRow4 = this.videoRow;
        if (listRow4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoRow");
            listRow4 = null;
        }
        arrayObjectAdapter9.add(listRow4);
        this.categoriesAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(activity, false, false, 6, (DefaultConstructorMarker) null));
        HeaderItem headerItem5 = new HeaderItem(1, getString(R.string.audio));
        ArrayObjectAdapter arrayObjectAdapter10 = this.categoriesAdapter;
        if (arrayObjectAdapter10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categoriesAdapter");
            arrayObjectAdapter10 = null;
        }
        this.audioRow = new ListRow(headerItem5, arrayObjectAdapter10);
        ArrayObjectAdapter arrayObjectAdapter11 = this.rowsAdapter;
        if (arrayObjectAdapter11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter11 = null;
        }
        ListRow listRow5 = this.audioRow;
        if (listRow5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioRow");
            listRow5 = null;
        }
        arrayObjectAdapter11.add(listRow5);
        this.playlistAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(activity, false, false, 6, (DefaultConstructorMarker) null));
        HeaderItem headerItem6 = new HeaderItem(8, getString(R.string.playlists));
        ArrayObjectAdapter arrayObjectAdapter12 = this.playlistAdapter;
        if (arrayObjectAdapter12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
            arrayObjectAdapter12 = null;
        }
        this.playlistRow = new ListRow(headerItem6, arrayObjectAdapter12);
        this.favoritesAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(activity, false, false, 6, (DefaultConstructorMarker) null));
        HeaderItem headerItem7 = new HeaderItem(8, getString(R.string.favorites));
        ArrayObjectAdapter arrayObjectAdapter13 = this.favoritesAdapter;
        if (arrayObjectAdapter13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesAdapter");
            arrayObjectAdapter13 = null;
        }
        this.favoritesRow = new ListRow(headerItem7, arrayObjectAdapter13);
        this.browserAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(activity, false, false, 6, (DefaultConstructorMarker) null));
        HeaderItem headerItem8 = new HeaderItem(3, getString(R.string.browsing));
        ArrayObjectAdapter arrayObjectAdapter14 = this.browserAdapter;
        if (arrayObjectAdapter14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserAdapter");
            arrayObjectAdapter14 = null;
        }
        this.browsersRow = new ListRow(headerItem8, arrayObjectAdapter14);
        ArrayObjectAdapter arrayObjectAdapter15 = this.rowsAdapter;
        if (arrayObjectAdapter15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter15 = null;
        }
        ListRow listRow6 = this.browsersRow;
        if (listRow6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browsersRow");
            listRow6 = null;
        }
        arrayObjectAdapter15.add(listRow6);
        this.otherAdapter = new ArrayObjectAdapter((Presenter) new GenericCardPresenter(requireActivity, 0, 2, (DefaultConstructorMarker) null));
        HeaderItem headerItem9 = new HeaderItem(5, getString(R.string.other));
        String string = getString(R.string.lock_with_pin_short);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        GenericCardItem genericCardItem = new GenericCardItem(17, string, "", R.drawable.ic_pin_lock_big, R.color.tv_card_content_dark);
        if (Intrinsics.areEqual((Object) PinCodeDelegate.Companion.getPinUnlocked().getValue(), (Object) true)) {
            ArrayObjectAdapter arrayObjectAdapter16 = this.otherAdapter;
            if (arrayObjectAdapter16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
                arrayObjectAdapter16 = null;
            }
            arrayObjectAdapter16.add(genericCardItem);
        }
        ArrayObjectAdapter arrayObjectAdapter17 = this.otherAdapter;
        if (arrayObjectAdapter17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
            arrayObjectAdapter17 = null;
        }
        String string2 = getString(R.string.preferences);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        arrayObjectAdapter17.add(new GenericCardItem(10, string2, "", R.drawable.ic_settings_big, R.color.tv_card_content_dark));
        String string3 = getString(R.string.remote_access);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        Settings.INSTANCE.getRemoteAccessEnabled().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$onViewCreated$1(this, new GenericCardItem(18, string3, "", R.drawable.ic_remote_access_big, R.color.tv_card_content_dark))));
        Permissions permissions = Permissions.INSTANCE;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        if (permissions.canReadStorage(requireActivity2)) {
            ArrayObjectAdapter arrayObjectAdapter18 = this.otherAdapter;
            if (arrayObjectAdapter18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
                arrayObjectAdapter18 = null;
            }
            String string4 = getString(R.string.refresh);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            arrayObjectAdapter18.add(new GenericCardItem(13, string4, "", R.drawable.ic_scan_big, R.color.tv_card_content_dark));
        }
        ArrayObjectAdapter arrayObjectAdapter19 = this.otherAdapter;
        if (arrayObjectAdapter19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
            arrayObjectAdapter19 = null;
        }
        String string5 = getString(R.string.about);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        arrayObjectAdapter19.add(new GenericCardItem(11, string5, getString(R.string.app_name_full) + " 3.6.0 Beta 2", R.drawable.ic_info_big, R.color.tv_card_content_dark));
        String string6 = getString(R.string.tip_jar);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        GenericCardItem genericCardItem2 = new GenericCardItem(16, string6, "", R.drawable.ic_donate_big, R.color.tv_card_content_dark);
        PinCodeDelegate.Companion.getPinUnlocked().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$onViewCreated$2(this, genericCardItem)));
        manageDonationVisibility(genericCardItem2);
        ArrayObjectAdapter arrayObjectAdapter20 = this.otherAdapter;
        if (arrayObjectAdapter20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
            arrayObjectAdapter20 = null;
        }
        this.miscRow = new ListRow(headerItem9, arrayObjectAdapter20);
        ArrayObjectAdapter arrayObjectAdapter21 = this.rowsAdapter;
        if (arrayObjectAdapter21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter21 = null;
        }
        ListRow listRow7 = this.miscRow;
        if (listRow7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("miscRow");
            listRow7 = null;
        }
        arrayObjectAdapter21.add(listRow7);
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
        this.historyAdapter = new ArrayObjectAdapter((Presenter) new CardPresenter(requireActivity3, false, true, 2, (DefaultConstructorMarker) null));
        HeaderItem headerItem10 = new HeaderItem(2, getString(R.string.history));
        ArrayObjectAdapter arrayObjectAdapter22 = this.historyAdapter;
        if (arrayObjectAdapter22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            arrayObjectAdapter22 = null;
        }
        this.historyRow = new ListRow(headerItem10, arrayObjectAdapter22);
        ArrayObjectAdapter arrayObjectAdapter23 = this.rowsAdapter;
        if (arrayObjectAdapter23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
        } else {
            arrayObjectAdapter2 = arrayObjectAdapter23;
        }
        setAdapter(arrayObjectAdapter2);
        setOnItemViewClickedListener(this);
        setOnItemViewSelectedListener(this);
        registerDatasets();
    }

    private final void manageDonationVisibility(GenericCardItem genericCardItem) {
        if (getActivity() != null) {
            ArrayObjectAdapter arrayObjectAdapter = this.otherAdapter;
            if (arrayObjectAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
                arrayObjectAdapter = null;
            }
            arrayObjectAdapter.remove(genericCardItem);
        }
    }

    private final void registerDatasets() {
        getModel$television_release().getBrowsers().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$1(this)));
        getModel$television_release().getFavoritesList().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$2(this)));
        getModel$television_release().getAudioCategories().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$3(this)));
        getModel$television_release().getVideos().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$4(this)));
        getModel$television_release().getNowPlaying().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$5(this)));
        getModel$television_release().getRecentlyPlayed().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$6(this)));
        getModel$television_release().getRecentlyAdded().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$7(this)));
        getModel$television_release().getHistory().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$8(this)));
        getModel$television_release().getPlaylist().observe(requireActivity(), new MainTvFragmentKt$sam$androidx_lifecycle_Observer$0(new MainTvFragment$registerDatasets$9(this)));
    }

    /* access modifiers changed from: private */
    public final void addAndCheckLoadedLines(long j) {
        if (!this.loadedLines.contains(Long.valueOf(j))) {
            this.loadedLines.add(Long.valueOf(j));
        }
        if (this.lines == this.loadedLines.size()) {
            setSelectedPosition(0);
            this.lines = -1;
        }
    }

    /* access modifiers changed from: private */
    public final void resetLines() {
        ListRow[] listRowArr = new ListRow[10];
        ListRow listRow = this.nowPlayingRow;
        ArrayObjectAdapter arrayObjectAdapter = null;
        if (listRow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nowPlayingRow");
            listRow = null;
        }
        boolean z = false;
        listRowArr[0] = listRow;
        ListRow listRow2 = this.recentlyPlayedRow;
        if (listRow2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recentlyPlayedRow");
            listRow2 = null;
        }
        listRowArr[1] = listRow2;
        ListRow listRow3 = this.recentlyAdddedRow;
        if (listRow3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recentlyAdddedRow");
            listRow3 = null;
        }
        listRowArr[2] = listRow3;
        ListRow listRow4 = this.videoRow;
        if (listRow4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoRow");
            listRow4 = null;
        }
        listRowArr[3] = listRow4;
        ListRow listRow5 = this.audioRow;
        if (listRow5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioRow");
            listRow5 = null;
        }
        listRowArr[4] = listRow5;
        ListRow listRow6 = this.playlistRow;
        if (listRow6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistRow");
            listRow6 = null;
        }
        listRowArr[5] = listRow6;
        ListRow listRow7 = this.historyRow;
        if (listRow7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyRow");
            listRow7 = null;
        }
        listRowArr[6] = listRow7;
        ListRow listRow8 = this.favoritesRow;
        if (listRow8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesRow");
            listRow8 = null;
        }
        listRowArr[7] = listRow8;
        ListRow listRow9 = this.browsersRow;
        if (listRow9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browsersRow");
            listRow9 = null;
        }
        listRowArr[8] = listRow9;
        ListRow listRow10 = this.miscRow;
        if (listRow10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("miscRow");
            listRow10 = null;
        }
        listRowArr[9] = listRow10;
        Collection arrayList = new ArrayList();
        for (Object next : CollectionsKt.listOf(listRowArr)) {
            ListRow listRow11 = (ListRow) next;
            if (!this.displayRecentlyPlayed) {
                ListRow listRow12 = this.recentlyPlayedRow;
                if (listRow12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recentlyPlayedRow");
                    listRow12 = null;
                }
                if (Intrinsics.areEqual((Object) listRow11, (Object) listRow12)) {
                }
            }
            if (!this.displayRecentlyAdded) {
                ListRow listRow13 = this.recentlyAdddedRow;
                if (listRow13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recentlyAdddedRow");
                    listRow13 = null;
                }
                if (Intrinsics.areEqual((Object) listRow11, (Object) listRow13)) {
                }
            }
            if (!this.displayHistory) {
                ListRow listRow14 = this.historyRow;
                if (listRow14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("historyRow");
                    listRow14 = null;
                }
                if (Intrinsics.areEqual((Object) listRow11, (Object) listRow14)) {
                }
            }
            if (!this.displayPlaylist) {
                ListRow listRow15 = this.playlistRow;
                if (listRow15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playlistRow");
                    listRow15 = null;
                }
                if (Intrinsics.areEqual((Object) listRow11, (Object) listRow15)) {
                }
            }
            if (!this.displayNowPlaying) {
                ListRow listRow16 = this.nowPlayingRow;
                if (listRow16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nowPlayingRow");
                    listRow16 = null;
                }
                if (Intrinsics.areEqual((Object) listRow11, (Object) listRow16)) {
                }
            }
            if (!this.displayFavorites) {
                ListRow listRow17 = this.favoritesRow;
                if (listRow17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("favoritesRow");
                    listRow17 = null;
                }
                if (Intrinsics.areEqual((Object) listRow11, (Object) listRow17)) {
                }
            }
            arrayList.add(next);
        }
        List list = (List) arrayList;
        int size = list.size();
        ArrayObjectAdapter arrayObjectAdapter2 = this.rowsAdapter;
        if (arrayObjectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
            arrayObjectAdapter2 = null;
        }
        if (size == arrayObjectAdapter2.size()) {
            for (IndexedValue indexedValue : CollectionsKt.withIndex(list)) {
                ArrayObjectAdapter arrayObjectAdapter3 = this.rowsAdapter;
                if (arrayObjectAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
                    arrayObjectAdapter3 = null;
                }
                Object obj = arrayObjectAdapter3.get(indexedValue.getIndex());
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.leanback.widget.ListRow");
                if (!Intrinsics.areEqual((Object) ((ListRow) obj).getHeaderItem(), (Object) ((ListRow) indexedValue.getValue()).getHeaderItem())) {
                    z = true;
                }
            }
            if (!z) {
                return;
            }
        }
        ArrayObjectAdapter arrayObjectAdapter4 = this.rowsAdapter;
        if (arrayObjectAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
        } else {
            arrayObjectAdapter = arrayObjectAdapter4;
        }
        arrayObjectAdapter.setItems(list, TvUtil.INSTANCE.getListDiffCallback());
    }

    public void onStart() {
        super.onStart();
        if (this.selectedItem instanceof MediaWrapper) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            TvUtilKt.updateBackground(LifecycleOwnerKt.getLifecycleScope(this), requireActivity, this.backgroundManager, this.selectedItem);
        }
        getModel$television_release().refresh();
    }

    public void onStop() {
        super.onStop();
        if (AndroidDevices.INSTANCE.isAndroidTv() && !AndroidUtil.isOOrLater) {
            requireActivity().startService(new Intent(requireActivity(), RecommendationsService.class));
        }
    }

    public void onClick(View view) {
        requireActivity().startActivity(new Intent(requireContext(), SearchActivity.class));
    }

    public final boolean showDetails() {
        Object obj = this.selectedItem;
        MediaWrapper mediaWrapper = obj instanceof MediaWrapper ? (MediaWrapper) obj : null;
        if (mediaWrapper == null || mediaWrapper.getType() != 3) {
            return false;
        }
        Intent intent = new Intent(requireActivity(), DetailsActivity.class);
        intent.putExtra("media", mediaWrapper);
        intent.putExtra("item", new MediaItemDetails(mediaWrapper.getTitle(), mediaWrapper.getArtist(), mediaWrapper.getAlbum(), mediaWrapper.getLocation(), mediaWrapper.getArtworkURL()));
        startActivity(intent);
        return true;
    }

    public void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Long valueOf = row != null ? Long.valueOf(row.getId()) : null;
        if (valueOf != null && valueOf.longValue() == 1) {
            Intent intent = new Intent(requireActivity, VerticalGridActivity.class);
            intent.putExtra(MainTvActivity.BROWSER_TYPE, 1);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.media.DummyItem");
            intent.putExtra(Constants.CATEGORY, ((DummyItem) obj).getId());
            requireActivity.startActivity(intent);
        } else if (valueOf != null && valueOf.longValue() == 5) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.television.ui.GenericCardItem");
            long id = ((GenericCardItem) obj).getId();
            if (id == 10) {
                requireActivity.startActivityForResult(new Intent(requireActivity, PreferencesActivity.class), 1);
            } else if (id == 13) {
                if (!Medialibrary.getInstance().isWorking()) {
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    MediaParsingServiceKt.reloadLibrary(requireActivity2);
                }
            } else if (id == 11) {
                requireActivity.startActivity(new Intent(requireActivity, AboutActivity.class));
            } else if (id == 16) {
                UiTools.INSTANCE.showDonations(requireActivity);
            } else if (id == 17) {
                PinCodeDelegate.Companion.getPinUnlocked().postValue(false);
            } else if (id == 18) {
                FragmentActivity requireActivity3 = requireActivity();
                Intent intent2 = new Intent(requireActivity, StartActivity.class);
                intent2.setAction("vlc.remoteaccess.share");
                requireActivity3.startActivity(intent2);
            }
        } else if (valueOf != null && valueOf.longValue() == 34) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.media.DummyItem");
            DummyItem dummyItem = (DummyItem) obj;
            if (dummyItem.getId() == 20) {
                requireActivity.startActivity(new Intent(requireActivity, AudioPlayerActivity.class));
            } else if (dummyItem.getId() == 26) {
                requireActivity.startActivity(new Intent(requireActivity, VideoPlayerActivity.class));
            }
        } else {
            getModel$television_release().open(requireActivity, obj);
        }
    }

    public void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        this.selectedItem = obj;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        TvUtilKt.updateBackground(LifecycleOwnerKt.getLifecycleScope(this), requireActivity, this.backgroundManager, obj);
    }
}
