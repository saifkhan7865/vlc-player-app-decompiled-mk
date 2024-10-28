package org.videolan.vlc.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.RendererItem;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.Settings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.RendererDelegate;
import org.videolan.vlc.gui.browser.MLStorageBrowserFragment;
import org.videolan.vlc.gui.dialogs.RenderersDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.interfaces.Filterable;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u0000 /2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001/B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0014J\u0006\u0010\u0014\u001a\u00020\rJ\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\rH\u0002J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010!\u001a\u00020\u000fH\u0014J\u0012\u0010\"\u001a\u00020\u000f2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0010\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0011H\u0016J\u0010\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u0011H\u0016J\b\u0010)\u001a\u00020\u000fH\u0002J\u0006\u0010*\u001a\u00020\u000fJ\b\u0010+\u001a\u00020\u000fH\u0002J\u000e\u0010,\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u0011J\u0010\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\rH\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/videolan/vlc/gui/ContentActivity;", "Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "Landroid/view/MenuItem$OnActionExpandListener;", "()V", "searchHiddenMenuItem", "Ljava/util/ArrayList;", "Landroid/view/MenuItem;", "Lkotlin/collections/ArrayList;", "searchItem", "searchView", "Landroidx/appcompat/widget/SearchView;", "showRenderers", "", "closeSearchView", "", "getCurrentQuery", "", "hideRenderers", "initAudioPlayerContainerActivity", "isSearchViewVisible", "makeRoomForSearch", "hide", "onClick", "v", "Landroid/view/View;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onMenuItemActionCollapse", "item", "onMenuItemActionExpand", "onOptionsItemSelected", "onPause", "onPostCreate", "savedInstanceState", "Landroid/os/Bundle;", "onQueryTextChange", "filterQueryString", "onQueryTextSubmit", "query", "openSearchActivity", "openSearchView", "restoreCurrentList", "setCurrentQuery", "setSearchVisibility", "visible", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContentActivity.kt */
public class ContentActivity extends AudioPlayerContainerActivity implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/ContentActivity";
    private final ArrayList<MenuItem> searchHiddenMenuItem;
    private MenuItem searchItem;
    private SearchView searchView;
    /* access modifiers changed from: private */
    public boolean showRenderers;

    public boolean hideRenderers() {
        return false;
    }

    public boolean onQueryTextSubmit(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r0 = org.videolan.vlc.RendererDelegate.INSTANCE.getRenderers().getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ContentActivity() {
        /*
            r1 = this;
            r1.<init>()
            org.videolan.resources.AndroidDevices r0 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r0 = r0.isChromeBook()
            if (r0 != 0) goto L_0x0022
            org.videolan.vlc.RendererDelegate r0 = org.videolan.vlc.RendererDelegate.INSTANCE
            org.videolan.tools.livedata.LiveDataset r0 = r0.getRenderers()
            java.util.List r0 = r0.getValue()
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x0022
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r0 = 1
            goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            r1.showRenderers = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.searchHiddenMenuItem = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.ContentActivity.<init>():void");
    }

    /* access modifiers changed from: protected */
    public void initAudioPlayerContainerActivity() {
        super.initAudioPlayerContainerActivity();
        if (!AndroidDevices.INSTANCE.isChromeBook() && !AndroidDevices.INSTANCE.isAndroidTv() && ((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean("enable_casting", true)) {
            LifecycleOwner lifecycleOwner = this;
            PlaybackService.Companion.getRenderer().observe(lifecycleOwner, new ContentActivity$sam$androidx_lifecycle_Observer$0(new ContentActivity$initAudioPlayerContainerActivity$1(this)));
            RendererDelegate.INSTANCE.getRenderers().observe(lifecycleOwner, new ContentActivity$sam$androidx_lifecycle_Observer$0(new ContentActivity$initAudioPlayerContainerActivity$2(this)));
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        UiTools.INSTANCE.setOnDragListener(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MenuItem menuItem = this.searchItem;
        if (menuItem != null) {
            if (menuItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchItem");
                menuItem = null;
            }
            menuItem.collapseActionView();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Fragment currentFragment = getCurrentFragment();
        super.onCreateOptionsMenu(menu);
        if (currentFragment instanceof AboutFragment) {
            return true;
        }
        getMenuInflater().inflate(R.menu.activity_option, menu);
        boolean z = false;
        if (currentFragment instanceof Filterable) {
            Filterable filterable = (Filterable) currentFragment;
            MenuItem findItem = menu.findItem(R.id.ml_menu_filter);
            Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
            this.searchItem = findItem;
            MenuItem menuItem = null;
            if (findItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchItem");
                findItem = null;
            }
            View actionView = findItem.getActionView();
            Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
            SearchView searchView2 = (SearchView) actionView;
            this.searchView = searchView2;
            if (searchView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
                searchView2 = null;
            }
            searchView2.setQueryHint(getString(R.string.search_in_list_hint));
            SearchView searchView3 = this.searchView;
            if (searchView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
                searchView3 = null;
            }
            searchView3.setOnQueryTextListener(this);
            String filterQuery = filterable.getFilterQuery();
            CharSequence charSequence = filterQuery;
            if (!(charSequence == null || charSequence.length() == 0)) {
                SearchView searchView4 = this.searchView;
                if (searchView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("searchView");
                    searchView4 = null;
                }
                searchView4.post(new ContentActivity$$ExternalSyntheticLambda0(this, filterQuery));
            }
            MenuItem menuItem2 = this.searchItem;
            if (menuItem2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchItem");
            } else {
                menuItem = menuItem2;
            }
            menuItem.setOnActionExpandListener(this);
        } else {
            menu.findItem(R.id.ml_menu_filter).setVisible(false);
        }
        MenuItem findItem2 = menu.findItem(R.id.ml_menu_renderers);
        if (!(currentFragment instanceof MLStorageBrowserFragment) && !hideRenderers() && this.showRenderers && ((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean("enable_casting", true)) {
            z = true;
        }
        findItem2.setVisible(z);
        menu.findItem(R.id.ml_menu_renderers).setIcon(!PlaybackService.Companion.hasRenderer() ? R.drawable.ic_renderer : R.drawable.ic_renderer_on);
        return true;
    }

    /* access modifiers changed from: private */
    public static final void onCreateOptionsMenu$lambda$0(ContentActivity contentActivity, String str) {
        Intrinsics.checkNotNullParameter(contentActivity, "this$0");
        MenuItem menuItem = contentActivity.searchItem;
        SearchView searchView2 = null;
        if (menuItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchItem");
            menuItem = null;
        }
        menuItem.expandActionView();
        SearchView searchView3 = contentActivity.searchView;
        if (searchView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView3 = null;
        }
        searchView3.clearFocus();
        UiTools uiTools = UiTools.INSTANCE;
        SearchView searchView4 = contentActivity.searchView;
        if (searchView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView4 = null;
        }
        uiTools.setKeyboardVisibility(searchView4, false);
        SearchView searchView5 = contentActivity.searchView;
        if (searchView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
        } else {
            searchView2 = searchView5;
        }
        searchView2.setQuery(str, false);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.ml_menu_search) {
            startActivity(new Intent("android.intent.action.SEARCH", (Uri) null, this, SearchActivity.class));
            return true;
        } else if (itemId == R.id.ml_menu_renderers) {
            if (!PlaybackService.Companion.hasRenderer() && RendererDelegate.INSTANCE.getRenderers().getSize() == 1) {
                RendererItem rendererItem = (RendererItem) RendererDelegate.INSTANCE.getRenderers().getValue().get(0);
                PlaybackService.Companion.getRenderer().setValue(rendererItem);
                String string = getString(R.string.casting_connected_renderer, new Object[]{rendererItem.displayName});
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                UiTools.INSTANCE.snacker(this, string);
            } else if (getSupportFragmentManager().findFragmentByTag("renderers") == null) {
                new RenderersDialog().show(getSupportFragmentManager(), "renderers");
            }
            return true;
        } else if (itemId != R.id.ml_menu_filter) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (!menuItem.isActionViewExpanded()) {
                setSearchVisibility(true);
            }
            return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean onQueryTextChange(String str) {
        Intrinsics.checkNotNullParameter(str, "filterQueryString");
        Fragment currentFragment = getCurrentFragment();
        if (!(currentFragment instanceof Filterable)) {
            return false;
        }
        if (str.length() == 0) {
            ((Filterable) currentFragment).restoreList();
            return true;
        }
        ((Filterable) currentFragment).filter(str);
        return true;
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        setSearchVisibility(true);
        return true;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        setSearchVisibility(false);
        restoreCurrentList();
        return true;
    }

    private final void openSearchActivity() {
        setSearchVisibility(false);
        SearchView searchView2 = null;
        Intent intent = new Intent("android.intent.action.SEARCH", (Uri) null, this, SearchActivity.class);
        SearchView searchView3 = this.searchView;
        if (searchView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
        } else {
            searchView2 = searchView3;
        }
        startActivity(intent.putExtra("query", searchView2.getQuery().toString()));
    }

    private final void setSearchVisibility(boolean z) {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof Filterable) {
            ((Filterable) currentFragment).setSearchVisibility(z);
            makeRoomForSearch(z);
        }
    }

    private final void makeRoomForSearch(boolean z) {
        Menu menu = getToolbar().getMenu();
        if (!z) {
            for (MenuItem visible : this.searchHiddenMenuItem) {
                visible.setVisible(true);
            }
            this.searchHiddenMenuItem.clear();
            invalidateOptionsMenu();
            return;
        }
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (item.isVisible()) {
                item.setVisible(false);
                this.searchHiddenMenuItem.add(item);
            }
        }
    }

    public final void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (view.getId() == R.id.searchButton) {
            openSearchActivity();
        }
    }

    public final void closeSearchView() {
        MenuItem findItem;
        Menu menu = getToolbar().getMenu();
        if (menu != null && (findItem = menu.findItem(R.id.ml_menu_filter)) != null) {
            findItem.collapseActionView();
        }
    }

    public final void openSearchView() {
        MenuItem findItem;
        Menu menu = getToolbar().getMenu();
        if (menu != null && (findItem = menu.findItem(R.id.ml_menu_filter)) != null) {
            findItem.expandActionView();
        }
    }

    public final boolean isSearchViewVisible() {
        MenuItem findItem;
        Menu menu = getToolbar().getMenu();
        if (menu == null || (findItem = menu.findItem(R.id.ml_menu_filter)) == null) {
            return false;
        }
        return findItem.isActionViewExpanded();
    }

    public final String getCurrentQuery() {
        SearchView searchView2 = this.searchView;
        if (searchView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView2 = null;
        }
        return searchView2.getQuery().toString();
    }

    public final void setCurrentQuery(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        SearchView searchView2 = this.searchView;
        if (searchView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView2 = null;
        }
        searchView2.setQuery(str, false);
    }

    private final void restoreCurrentList() {
        Fragment currentFragment = getCurrentFragment();
        Filterable filterable = currentFragment instanceof Filterable ? (Filterable) currentFragment : null;
        if (filterable != null) {
            filterable.restoreList();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/ContentActivity$Companion;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ContentActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
