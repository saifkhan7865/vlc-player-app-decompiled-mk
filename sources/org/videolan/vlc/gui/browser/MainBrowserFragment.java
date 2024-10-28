package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.MediaWrapperImpl;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.NetworkMonitor;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.dialogs.NetworkServerDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.helpers.hf.OtgAccess;
import org.videolan.vlc.gui.helpers.hf.OtgAccessKt;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.TitleListView;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.repository.BrowserFavRepository;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.viewmodels.browser.BrowserFavoritesModel;
import org.videolan.vlc.viewmodels.browser.BrowserModel;
import org.videolan.vlc.viewmodels.browser.BrowserModelKt;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001GB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010 \u001a\u00020\u0013H\u0016J\b\u0010!\u001a\u00020\u0011H\u0014J\u001c\u0010\"\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\u0012\u0010+\u001a\u00020(2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u001c\u0010.\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J&\u00101\u001a\u0004\u0018\u00010*2\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001052\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0018\u00106\u001a\u00020(2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0016J\u0012\u0010;\u001a\u00020(2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010<\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010=\u001a\u00020(2\u0006\u0010/\u001a\u000200H\u0016J\b\u0010>\u001a\u00020(H\u0016J\u001a\u0010?\u001a\u00020(2\u0006\u0010@\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0012\u0010A\u001a\u00020(2\b\u0010B\u001a\u0004\u0018\u00010CH\u0002J\u0010\u0010D\u001a\u00020(2\u0006\u0010E\u001a\u00020FH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000RJ\u0010\u0007\u001a>\u0012\b\u0012\u00060\tR\u00020\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\bj\u001e\u0012\b\u0012\u00060\tR\u00020\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n`\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0018\u00010\tR\u00020\u0000X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lorg/videolan/vlc/gui/browser/MainBrowserFragment;", "Lorg/videolan/vlc/gui/BaseFragment;", "Landroid/view/View$OnClickListener;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "()V", "browserFavRepository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "containerAdapterAssociation", "Ljava/util/HashMap;", "Lorg/videolan/vlc/gui/browser/MainBrowserFragment$MainBrowserContainer;", "Lkotlin/Pair;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "Landroidx/lifecycle/ViewModel;", "Lkotlin/collections/HashMap;", "currentAdapterActionMode", "currentCtx", "displayInList", "", "displayInListKey", "", "favoritesEntry", "Lorg/videolan/vlc/gui/view/TitleListView;", "favoritesViewModel", "Lorg/videolan/vlc/viewmodels/browser/BrowserFavoritesModel;", "localEntry", "localViewModel", "Lorg/videolan/vlc/viewmodels/browser/BrowserModel;", "networkEntry", "networkMonitor", "Lorg/videolan/tools/NetworkMonitor;", "networkViewModel", "requiringOtg", "getTitle", "hasFAB", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onClick", "", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDestroyActionMode", "onOptionsItemSelected", "onPrepareOptionsMenu", "onResume", "onViewCreated", "view", "showAddServerDialog", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "updateNetworkEmptyView", "emptyLoading", "Lorg/videolan/vlc/gui/view/EmptyLoadingStateView;", "MainBrowserContainer", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainBrowserFragment.kt */
public final class MainBrowserFragment extends BaseFragment implements View.OnClickListener, CtxActionReceiver {
    /* access modifiers changed from: private */
    public BrowserFavRepository browserFavRepository;
    /* access modifiers changed from: private */
    public final HashMap<MainBrowserContainer, Pair<BaseBrowserAdapter, ViewModel>> containerAdapterAssociation = new HashMap<>();
    /* access modifiers changed from: private */
    public BaseBrowserAdapter currentAdapterActionMode;
    /* access modifiers changed from: private */
    public MainBrowserContainer currentCtx;
    private boolean displayInList;
    private final String displayInListKey = "main_browser_fragment_display_mode";
    /* access modifiers changed from: private */
    public TitleListView favoritesEntry;
    private BrowserFavoritesModel favoritesViewModel;
    /* access modifiers changed from: private */
    public TitleListView localEntry;
    /* access modifiers changed from: private */
    public BrowserModel localViewModel;
    /* access modifiers changed from: private */
    public TitleListView networkEntry;
    private NetworkMonitor networkMonitor;
    /* access modifiers changed from: private */
    public BrowserModel networkViewModel;
    /* access modifiers changed from: private */
    public boolean requiringOtg;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MainBrowserFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.util.ContextOption[] r0 = org.videolan.vlc.util.ContextOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_AND_SUB_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_EDIT     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.MainBrowserFragment.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasFAB() {
        return false;
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.main_browser_fragment, viewGroup, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        r11 = r11.getMultiSelectHelper();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onActionItemClicked(androidx.appcompat.view.ActionMode r11, android.view.MenuItem r12) {
        /*
            r10 = this;
            r11 = r10
            androidx.lifecycle.LifecycleOwner r11 = (androidx.lifecycle.LifecycleOwner) r11
            boolean r11 = org.videolan.tools.KotlinExtensionsKt.isStarted(r11)
            r0 = 0
            if (r11 != 0) goto L_0x000b
            return r0
        L_0x000b:
            org.videolan.vlc.gui.browser.BaseBrowserAdapter r11 = r10.currentAdapterActionMode
            r1 = 0
            if (r11 == 0) goto L_0x001b
            org.videolan.tools.MultiSelectHelper r11 = r11.getMultiSelectHelper()
            if (r11 == 0) goto L_0x001b
            java.util.List r11 = r11.getSelection()
            goto L_0x001c
        L_0x001b:
            r11 = r1
        L_0x001c:
            boolean r2 = r11 instanceof java.util.List
            if (r2 == 0) goto L_0x0022
            r5 = r11
            goto L_0x0023
        L_0x0022:
            r5 = r1
        L_0x0023:
            if (r5 != 0) goto L_0x0026
            return r0
        L_0x0026:
            r11 = r5
            java.util.Collection r11 = (java.util.Collection) r11
            boolean r11 = r11.isEmpty()
            r2 = 1
            r11 = r11 ^ r2
            if (r11 == 0) goto L_0x00ab
            if (r12 == 0) goto L_0x003b
            int r11 = r12.getItemId()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)
        L_0x003b:
            int r11 = org.videolan.vlc.R.id.action_mode_file_play
            if (r1 != 0) goto L_0x0040
            goto L_0x0058
        L_0x0040:
            int r12 = r1.intValue()
            if (r12 != r11) goto L_0x0058
            org.videolan.vlc.media.MediaUtils r3 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r11 = r10.getActivity()
            r4 = r11
            android.content.Context r4 = (android.content.Context) r4
            r8 = 8
            r9 = 0
            r6 = 0
            r7 = 0
            org.videolan.vlc.media.MediaUtils.openList$default(r3, r4, r5, r6, r7, r8, r9)
            goto L_0x00ab
        L_0x0058:
            int r11 = org.videolan.vlc.R.id.action_mode_file_append
            if (r1 != 0) goto L_0x005d
            goto L_0x006f
        L_0x005d:
            int r12 = r1.intValue()
            if (r12 != r11) goto L_0x006f
            org.videolan.vlc.media.MediaUtils r11 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r12 = r10.getActivity()
            android.content.Context r12 = (android.content.Context) r12
            r11.appendMedia((android.content.Context) r12, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r5)
            goto L_0x00ab
        L_0x006f:
            int r11 = org.videolan.vlc.R.id.action_mode_file_add_playlist
            java.lang.String r12 = "requireActivity(...)"
            if (r1 != 0) goto L_0x0076
            goto L_0x0089
        L_0x0076:
            int r3 = r1.intValue()
            if (r3 != r11) goto L_0x0089
            org.videolan.vlc.gui.helpers.UiTools r11 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r10.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r12)
            r11.addToPlaylist(r0, r5)
            goto L_0x00ab
        L_0x0089:
            int r11 = org.videolan.vlc.R.id.action_mode_file_info
            if (r1 != 0) goto L_0x008e
            goto L_0x00a7
        L_0x008e:
            int r1 = r1.intValue()
            if (r1 != r11) goto L_0x00a7
            org.videolan.vlc.gui.helpers.UiTools r11 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.FragmentActivity r1 = r10.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r12)
            java.lang.Object r12 = r5.get(r0)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r12
            r11.showMediaInfo(r1, r12)
            goto L_0x00ab
        L_0x00a7:
            r10.stopActionMode()
            return r0
        L_0x00ab:
            r10.stopActionMode()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.MainBrowserFragment.onActionItemClicked(androidx.appcompat.view.ActionMode, android.view.MenuItem):boolean");
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.ml_menu_display_grid).setVisible(this.displayInList);
        menu.findItem(R.id.ml_menu_display_list).setVisible(!this.displayInList);
        menu.findItem(R.id.add_server_favorite).setVisible(true);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        TitleListView titleListView = null;
        if (itemId == R.id.ml_menu_display_list || itemId == R.id.ml_menu_display_grid) {
            this.displayInList = menuItem.getItemId() == R.id.ml_menu_display_list;
            Set<MainBrowserContainer> keySet = this.containerAdapterAssociation.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
            for (MainBrowserContainer inCards : keySet) {
                inCards.setInCards(!this.displayInList);
            }
            TitleListView titleListView2 = this.localEntry;
            if (titleListView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localEntry");
                titleListView2 = null;
            }
            titleListView2.setDisplayInCards(!this.displayInList);
            TitleListView titleListView3 = this.favoritesEntry;
            if (titleListView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
                titleListView3 = null;
            }
            titleListView3.setDisplayInCards(!this.displayInList);
            TitleListView titleListView4 = this.networkEntry;
            if (titleListView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            } else {
                titleListView = titleListView4;
            }
            titleListView.setDisplayInCards(!this.displayInList);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.invalidateOptionsMenu();
            }
            Settings settings = Settings.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), this.displayInListKey, Boolean.valueOf(this.displayInList));
            return true;
        } else if (itemId != R.id.add_server_favorite) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            showAddServerDialog((MediaWrapper) null);
            return true;
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater menuInflater;
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
        BaseBrowserAdapter baseBrowserAdapter = this.currentAdapterActionMode;
        if (baseBrowserAdapter != null) {
            int itemCount = baseBrowserAdapter.getItemCount();
            BaseBrowserAdapter baseBrowserAdapter2 = this.currentAdapterActionMode;
            if (!(baseBrowserAdapter2 == null || (multiSelectHelper = baseBrowserAdapter2.getMultiSelectHelper()) == null)) {
                multiSelectHelper.toggleActionMode(true, itemCount);
            }
        }
        if (!(actionMode == null || (menuInflater = actionMode.getMenuInflater()) == null)) {
            menuInflater.inflate(R.menu.action_mode_browser_file, menu);
        }
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper2;
        BaseBrowserAdapter baseBrowserAdapter = this.currentAdapterActionMode;
        if (baseBrowserAdapter != null) {
            int itemCount = baseBrowserAdapter.getItemCount();
            BaseBrowserAdapter baseBrowserAdapter2 = this.currentAdapterActionMode;
            if (!(baseBrowserAdapter2 == null || (multiSelectHelper2 = baseBrowserAdapter2.getMultiSelectHelper()) == null)) {
                multiSelectHelper2.toggleActionMode(false, itemCount);
            }
        }
        setActionMode((ActionMode) null);
        BaseBrowserAdapter baseBrowserAdapter3 = this.currentAdapterActionMode;
        if (!(baseBrowserAdapter3 == null || (multiSelectHelper = baseBrowserAdapter3.getMultiSelectHelper()) == null)) {
            multiSelectHelper.clearSelection();
        }
        this.currentAdapterActionMode = null;
    }

    public String getTitle() {
        String string = getString(R.string.browse);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public void onCreate(Bundle bundle) {
        BrowserFavRepository.Companion companion = BrowserFavRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.browserFavRepository = (BrowserFavRepository) companion.getInstance(requireContext);
        NetworkMonitor.Companion companion2 = NetworkMonitor.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
        this.networkMonitor = (NetworkMonitor) companion2.getInstance(requireContext2);
        super.onCreate(bundle);
        Fragment fragment = this;
        this.localViewModel = BrowserModelKt.getBrowserModel$default(fragment, 0, (String) null, false, 4, (Object) null);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext(...)");
        this.favoritesViewModel = new BrowserFavoritesModel(requireContext3);
        this.networkViewModel = BrowserModelKt.getBrowserModel$default(fragment, 1, (String) null, false, 4, (Object) null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        View view2 = view;
        Intrinsics.checkNotNullParameter(view2, "view");
        super.onViewCreated(view, bundle);
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.displayInList = ((SharedPreferences) settings.getInstance(requireActivity)).getBoolean(this.displayInListKey, false);
        View findViewById = view2.findViewById(R.id.local_browser_entry);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.localEntry = (TitleListView) findViewById;
        MainBrowserContainer mainBrowserContainer = new MainBrowserContainer(this, false, (String) null, false, false, true, !this.displayInList, 7, (DefaultConstructorMarker) null);
        BaseBrowserAdapter baseBrowserAdapter = new BaseBrowserAdapter(mainBrowserContainer, 0, false, false, 14, (DefaultConstructorMarker) null);
        TitleListView titleListView = this.localEntry;
        if (titleListView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localEntry");
            titleListView = null;
        }
        titleListView.getList().setAdapter(baseBrowserAdapter);
        Map map = this.containerAdapterAssociation;
        BrowserModel browserModel = this.localViewModel;
        if (browserModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel = null;
        }
        map.put(mainBrowserContainer, new Pair(baseBrowserAdapter, browserModel));
        BrowserModel browserModel2 = this.localViewModel;
        if (browserModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel2 = null;
        }
        browserModel2.getDataset().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$1(this, baseBrowserAdapter)));
        BrowserModel browserModel3 = this.localViewModel;
        if (browserModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel3 = null;
        }
        browserModel3.getLoading().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$2(this)));
        BrowserModel browserModel4 = this.localViewModel;
        if (browserModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel4 = null;
        }
        browserModel4.browseRoot();
        BrowserModel browserModel5 = this.localViewModel;
        if (browserModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            browserModel5 = null;
        }
        browserModel5.getDescriptionUpdate().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$3(baseBrowserAdapter)));
        View findViewById2 = view2.findViewById(R.id.fav_browser_entry);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        TitleListView titleListView2 = (TitleListView) findViewById2;
        this.favoritesEntry = titleListView2;
        if (titleListView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
            titleListView2 = null;
        }
        titleListView2.getLoading().setShowNoMedia(false);
        TitleListView titleListView3 = this.favoritesEntry;
        if (titleListView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
            titleListView3 = null;
        }
        EmptyLoadingStateView loading = titleListView3.getLoading();
        String string = getString(R.string.no_favorite);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        loading.setEmptyText(string);
        MainBrowserContainer mainBrowserContainer2 = r0;
        String str = "localEntry";
        String str2 = "getString(...)";
        MainBrowserContainer mainBrowserContainer3 = new MainBrowserContainer(this, false, (String) null, false, false, true, !this.displayInList, 7, (DefaultConstructorMarker) null);
        BaseBrowserAdapter baseBrowserAdapter2 = new BaseBrowserAdapter(mainBrowserContainer2, 0, false, false, 14, (DefaultConstructorMarker) null);
        TitleListView titleListView4 = this.favoritesEntry;
        if (titleListView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
            titleListView4 = null;
        }
        titleListView4.getList().setAdapter(baseBrowserAdapter2);
        Map map2 = this.containerAdapterAssociation;
        BrowserFavoritesModel browserFavoritesModel = this.favoritesViewModel;
        if (browserFavoritesModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesViewModel");
            browserFavoritesModel = null;
        }
        map2.put(mainBrowserContainer2, new Pair(baseBrowserAdapter2, browserFavoritesModel));
        BrowserFavoritesModel browserFavoritesModel2 = this.favoritesViewModel;
        if (browserFavoritesModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesViewModel");
            browserFavoritesModel2 = null;
        }
        browserFavoritesModel2.getFavorites().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$4(this, baseBrowserAdapter2)));
        BrowserFavoritesModel browserFavoritesModel3 = this.favoritesViewModel;
        if (browserFavoritesModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesViewModel");
            browserFavoritesModel3 = null;
        }
        browserFavoritesModel3.getProvider().getLoading().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$5(this)));
        BrowserFavoritesModel browserFavoritesModel4 = this.favoritesViewModel;
        if (browserFavoritesModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesViewModel");
            browserFavoritesModel4 = null;
        }
        browserFavoritesModel4.getProvider().getDescriptionUpdate().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$6(baseBrowserAdapter2)));
        View findViewById3 = view2.findViewById(R.id.network_browser_entry);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        TitleListView titleListView5 = (TitleListView) findViewById3;
        this.networkEntry = titleListView5;
        if (titleListView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView5 = null;
        }
        titleListView5.getLoading().setShowNoMedia(false);
        TitleListView titleListView6 = this.networkEntry;
        if (titleListView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView6 = null;
        }
        EmptyLoadingStateView loading2 = titleListView6.getLoading();
        String string2 = getString(R.string.nomedia);
        Intrinsics.checkNotNullExpressionValue(string2, str2);
        loading2.setEmptyText(string2);
        MainBrowserContainer mainBrowserContainer4 = new MainBrowserContainer(this, false, (String) null, false, true, false, !this.displayInList, 7, (DefaultConstructorMarker) null);
        BaseBrowserAdapter baseBrowserAdapter3 = new BaseBrowserAdapter(mainBrowserContainer4, 0, false, false, 14, (DefaultConstructorMarker) null);
        TitleListView titleListView7 = this.networkEntry;
        if (titleListView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView7 = null;
        }
        titleListView7.getList().setAdapter(baseBrowserAdapter3);
        Map map3 = this.containerAdapterAssociation;
        BrowserModel browserModel6 = this.networkViewModel;
        if (browserModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
            browserModel6 = null;
        }
        map3.put(mainBrowserContainer4, new Pair(baseBrowserAdapter3, browserModel6));
        BrowserModel browserModel7 = this.networkViewModel;
        if (browserModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
            browserModel7 = null;
        }
        browserModel7.getDataset().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$7(baseBrowserAdapter3, this)));
        BrowserModel browserModel8 = this.networkViewModel;
        if (browserModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
            browserModel8 = null;
        }
        browserModel8.getLoading().observe(getViewLifecycleOwner(), new MainBrowserFragment$sam$androidx_lifecycle_Observer$0(new MainBrowserFragment$onViewCreated$8(this)));
        BrowserModel browserModel9 = this.networkViewModel;
        if (browserModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
            browserModel9 = null;
        }
        browserModel9.browseRoot();
        TitleListView titleListView8 = this.localEntry;
        if (titleListView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
            titleListView8 = null;
        }
        titleListView8.setDisplayInCards(!this.displayInList);
        TitleListView titleListView9 = this.favoritesEntry;
        if (titleListView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
            titleListView9 = null;
        }
        titleListView9.setDisplayInCards(!this.displayInList);
        TitleListView titleListView10 = this.networkEntry;
        if (titleListView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            titleListView10 = null;
        }
        titleListView10.setDisplayInCards(!this.displayInList);
    }

    public void onResume() {
        super.onResume();
        if (this.requiringOtg && OtgAccess.Companion.getOtgRoot().getValue() != null) {
            Intent intent = new Intent(requireActivity().getApplicationContext(), SecondaryActivity.class);
            MediaWrapperImpl mediaWrapperImpl = new MediaWrapperImpl(Uri.parse("otg://"));
            mediaWrapperImpl.setTitle(getString(R.string.otg_device_title));
            intent.putExtra(BaseBrowserFragmentKt.KEY_MEDIA, mediaWrapperImpl);
            intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.FILE_BROWSER);
            startActivity(intent);
        }
        this.requiringOtg = false;
    }

    /* access modifiers changed from: private */
    public final void updateNetworkEmptyView(EmptyLoadingStateView emptyLoadingStateView) {
        NetworkMonitor networkMonitor2 = this.networkMonitor;
        TitleListView titleListView = null;
        if (networkMonitor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkMonitor");
            networkMonitor2 = null;
        }
        if (networkMonitor2.getConnected()) {
            BrowserModel browserModel = this.networkViewModel;
            if (browserModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
                browserModel = null;
            }
            if (browserModel.isEmpty()) {
                BrowserModel browserModel2 = this.networkViewModel;
                if (browserModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
                    browserModel2 = null;
                }
                if (Intrinsics.areEqual((Object) browserModel2.getLoading().getValue(), (Object) true)) {
                    emptyLoadingStateView.setState(EmptyLoadingState.LOADING);
                    return;
                }
                NetworkMonitor networkMonitor3 = this.networkMonitor;
                if (networkMonitor3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkMonitor");
                    networkMonitor3 = null;
                }
                if (networkMonitor3.getLanAllowed()) {
                    emptyLoadingStateView.setState(EmptyLoadingState.LOADING);
                    String string = getString(R.string.network_shares_discovery);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    emptyLoadingStateView.setLoadingText(string);
                } else {
                    emptyLoadingStateView.setState(EmptyLoadingState.EMPTY);
                    String string2 = getString(R.string.network_connection_needed);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    emptyLoadingStateView.setEmptyText(string2);
                }
                TitleListView titleListView2 = this.networkEntry;
                if (titleListView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
                } else {
                    titleListView = titleListView2;
                }
                titleListView.getList().setVisibility(8);
                return;
            }
            emptyLoadingStateView.setState(EmptyLoadingState.NONE);
            TitleListView titleListView3 = this.networkEntry;
            if (titleListView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
            } else {
                titleListView = titleListView3;
            }
            titleListView.getList().setVisibility(0);
            return;
        }
        emptyLoadingStateView.setState(EmptyLoadingState.EMPTY);
        String string3 = getString(R.string.network_connection_needed);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        emptyLoadingStateView.setEmptyText(string3);
        TitleListView titleListView4 = this.networkEntry;
        if (titleListView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
        } else {
            titleListView = titleListView4;
        }
        titleListView.getList().setVisibility(8);
    }

    private final void showAddServerDialog(MediaWrapper mediaWrapper) {
        try {
            FragmentManager parentFragmentManager = getParentFragmentManager();
            Intrinsics.checkNotNull(parentFragmentManager);
            NetworkServerDialog networkServerDialog = new NetworkServerDialog();
            if (mediaWrapper != null) {
                networkServerDialog.setServer(mediaWrapper);
            }
            networkServerDialog.show(parentFragmentManager, "fragment_add_server");
        } catch (IllegalStateException unused) {
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B?\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\u0004¢\u0006\u0002\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0004H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0001J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0002H\u0016J \u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0002H\u0016J \u0010 \u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0002H\u0016J\u0019\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0002H\u0001J \u0010\"\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0002H\u0016J!\u0010#\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0002H\u0001J\u0015\u0010$\u001a\u00020\u00192\n\u0010%\u001a\u0006\u0012\u0002\b\u00030&H\u0001J\u0006\u0010'\u001a\u00020(J\b\u0010)\u001a\u00020*H\u0002R\u001a\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\t\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\rR\u0014\u0010\b\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006+"}, d2 = {"Lorg/videolan/vlc/gui/browser/MainBrowserFragment$MainBrowserContainer;", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "scannedDirectory", "", "mrl", "", "isRootDirectory", "isNetwork", "isFile", "inCards", "(Lorg/videolan/vlc/gui/browser/MainBrowserFragment;ZLjava/lang/String;ZZZZ)V", "getInCards", "()Z", "setInCards", "(Z)V", "getMrl", "()Ljava/lang/String;", "getScannedDirectory", "checkAdapterForActionMode", "containerActivity", "Landroidx/fragment/app/FragmentActivity;", "getStorageDelegate", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "onClick", "", "v", "Landroid/view/View;", "position", "", "item", "onCtxClick", "onImageClick", "onItemFocused", "onLongClick", "onMainActionClick", "onUpdateFinished", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "requireAdapter", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "requireViewModel", "Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MainBrowserFragment.kt */
    public final class MainBrowserContainer implements BrowserContainer<MediaLibraryItem> {
        private final /* synthetic */ BrowserContainerImpl<MediaLibraryItem> $$delegate_0;
        private boolean inCards;
        private final boolean isFile;
        private final boolean isNetwork;
        private final boolean isRootDirectory;
        private final String mrl;
        private final boolean scannedDirectory;

        public IStorageFragmentDelegate getStorageDelegate() {
            return this.$$delegate_0.getStorageDelegate();
        }

        public void onItemFocused(View view, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            this.$$delegate_0.onItemFocused(view, mediaLibraryItem);
        }

        public void onMainActionClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            this.$$delegate_0.onMainActionClick(view, i, mediaLibraryItem);
        }

        public void onUpdateFinished(RecyclerView.Adapter<?> adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            this.$$delegate_0.onUpdateFinished(adapter);
        }

        public MainBrowserContainer(boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.scannedDirectory = z;
            this.mrl = str;
            this.isRootDirectory = z2;
            this.isNetwork = z3;
            this.isFile = z4;
            this.inCards = z5;
            this.$$delegate_0 = new BrowserContainerImpl(z, str, z2, z3, z4, z5);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ MainBrowserContainer(MainBrowserFragment mainBrowserFragment, boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? true : z2, z3, z4, (i & 32) != 0 ? true : z5);
        }

        public boolean getScannedDirectory() {
            return this.scannedDirectory;
        }

        public String getMrl() {
            return this.mrl;
        }

        public boolean isRootDirectory() {
            return this.isRootDirectory;
        }

        public boolean isNetwork() {
            return this.isNetwork;
        }

        public boolean isFile() {
            return this.isFile;
        }

        public boolean getInCards() {
            return this.inCards;
        }

        public void setInCards(boolean z) {
            this.inCards = z;
        }

        public FragmentActivity containerActivity() {
            FragmentActivity requireActivity = MainBrowserFragment.this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            return requireActivity;
        }

        public final BaseBrowserAdapter requireAdapter() {
            BaseBrowserAdapter baseBrowserAdapter;
            Pair pair = (Pair) MainBrowserFragment.this.containerAdapterAssociation.get(this);
            if (pair != null && (baseBrowserAdapter = (BaseBrowserAdapter) pair.getFirst()) != null) {
                return baseBrowserAdapter;
            }
            throw new IllegalStateException("Adapter not stored on the association map");
        }

        /* access modifiers changed from: private */
        public final ViewModel requireViewModel() {
            ViewModel viewModel;
            Pair pair = (Pair) MainBrowserFragment.this.containerAdapterAssociation.get(this);
            if (pair != null && (viewModel = (ViewModel) pair.getSecond()) != null) {
                return viewModel;
            }
            throw new IllegalStateException("ViewModel not stored on the association map");
        }

        private final boolean checkAdapterForActionMode() {
            BaseBrowserAdapter requireAdapter = requireAdapter();
            if (MainBrowserFragment.this.currentAdapterActionMode != null) {
                return Intrinsics.areEqual((Object) MainBrowserFragment.this.currentAdapterActionMode, (Object) requireAdapter);
            }
            MainBrowserFragment.this.currentAdapterActionMode = requireAdapter;
            return true;
        }

        public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            if (MainBrowserFragment.this.getActionMode() != null) {
                if (checkAdapterForActionMode()) {
                    BaseBrowserAdapter requireAdapter = requireAdapter();
                    if (mediaWrapper.getType() == 1 || mediaWrapper.getType() == 0 || mediaWrapper.getType() == 3) {
                        MultiSelectHelper.toggleSelection$default(requireAdapter.getMultiSelectHelper(), i, false, 2, (Object) null);
                        if (requireAdapter.getMultiSelectHelper().getSelection().isEmpty()) {
                            MainBrowserFragment.this.stopActionMode();
                        }
                        MainBrowserFragment.this.invalidateActionMode();
                    }
                }
            } else if (mediaWrapper.getItemType() == 32 && Intrinsics.areEqual((Object) "otg://", (Object) mediaWrapper.getLocation()) && OtgAccess.Companion.getOtgRoot().getValue() == null) {
                MainBrowserFragment.this.requiringOtg = true;
                FragmentActivity requireActivity = MainBrowserFragment.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                OtgAccessKt.requestOtgRoot(requireActivity);
            } else {
                Intent intent = new Intent(MainBrowserFragment.this.requireActivity().getApplicationContext(), SecondaryActivity.class);
                intent.putExtra(BaseBrowserFragmentKt.KEY_MEDIA, mediaLibraryItem);
                intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.FILE_BROWSER);
                MainBrowserFragment.this.startActivity(intent);
            }
        }

        public boolean onLongClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            if (mediaLibraryItem.getItemType() != 32) {
                return false;
            }
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            if (mediaWrapper.getType() != 1 && mediaWrapper.getType() != 0 && mediaWrapper.getType() != 3) {
                onCtxClick(view, i, mediaLibraryItem);
            } else if (!checkAdapterForActionMode()) {
                return false;
            } else {
                MultiSelectHelper.toggleSelection$default(requireAdapter().getMultiSelectHelper(), i, false, 2, (Object) null);
                if (MainBrowserFragment.this.getActionMode() == null) {
                    MainBrowserFragment.this.startActionMode();
                } else {
                    MainBrowserFragment.this.invalidateActionMode();
                }
            }
            return true;
        }

        public void onImageClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            onClick(view, i, mediaLibraryItem);
        }

        public void onCtxClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            if (MainBrowserFragment.this.getActionMode() == null && mediaLibraryItem.getItemType() == 32) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(MainBrowserFragment.this), (CoroutineContext) null, (CoroutineStart) null, new MainBrowserFragment$MainBrowserContainer$onCtxClick$1(this, mediaLibraryItem, MainBrowserFragment.this, i, (Continuation<? super MainBrowserFragment$MainBrowserContainer$onCtxClick$1>) null), 3, (Object) null);
            }
        }
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        BaseBrowserAdapter requireAdapter;
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        MainBrowserContainer mainBrowserContainer = this.currentCtx;
        if (mainBrowserContainer != null && (requireAdapter = mainBrowserContainer.requireAdapter()) != null) {
            MediaLibraryItem item = requireAdapter.getItem(i);
            MediaWrapper mediaWrapper = item instanceof MediaWrapper ? (MediaWrapper) item : null;
            if (mediaWrapper != null) {
                int i2 = WhenMappings.$EnumSwitchMapping$0[contextOption.ordinal()];
                if (i2 == 1) {
                    MediaUtils.INSTANCE.openMedia(getActivity(), mediaWrapper);
                } else if (i2 == 2) {
                    Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new MainBrowserFragment$onCtxAction$1(this, mediaWrapper, (Continuation<? super MainBrowserFragment$onCtxAction$1>) null), 2, (Object) null);
                } else if (i2 == 3) {
                    UiTools uiTools = UiTools.INSTANCE;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                    String uri = mediaWrapper.getUri().toString();
                    Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                    String title = mediaWrapper.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                    uiTools.addToPlaylistAsync(requireActivity, uri, false, title);
                } else if (i2 == 4) {
                    UiTools uiTools2 = UiTools.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    String uri2 = mediaWrapper.getUri().toString();
                    Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                    String title2 = mediaWrapper.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
                    uiTools2.addToPlaylistAsync(requireActivity2, uri2, true, title2);
                } else if (i2 == 5) {
                    showAddServerDialog(mediaWrapper);
                }
            }
        }
    }
}
