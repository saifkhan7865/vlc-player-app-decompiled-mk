package org.videolan.vlc.gui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.PlaylistFragment;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.interfaces.Filterable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001KB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\nH\u0016J\n\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\n\u0010#\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\nH\u0016J\b\u0010(\u001a\u00020\u0006H\u0014J\u001c\u0010)\u001a\u00020\u00062\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u001c\u0010.\u001a\u00020\u00062\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J&\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0012\u00109\u001a\u00020\u001f2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u0010:\u001a\u00020\u001f2\u0006\u0010/\u001a\u000200H\u0016J\b\u0010;\u001a\u00020\u001fH\u0016J\b\u0010<\u001a\u00020\u001fH\u0016J\u0010\u0010=\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010A\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020?H\u0016J\u001a\u0010B\u001a\u00020\u001f2\u0006\u0010C\u001a\u0002022\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u0010D\u001a\u00020\u001fH\u0002J\b\u0010E\u001a\u00020\u001fH\u0016J\u0010\u0010F\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020\u0006H\u0016J\b\u0010H\u001a\u00020\u001fH\u0002J\b\u0010I\u001a\u00020\u001fH\u0002J\b\u0010J\u001a\u00020\u001fH\u0002R\u0014\u0010\u0005\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\b\"\u0004\b\u0017\u0010\u0010R\u0012\u0010\u0018\u001a\u00060\u0019R\u00020\u0000X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoBrowserFragment;", "Lorg/videolan/vlc/gui/BaseFragment;", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "Lorg/videolan/vlc/interfaces/Filterable;", "()V", "hasTabs", "", "getHasTabs", "()Z", "lastQuery", "", "needToReopenSearch", "value", "playlistOnlyFavorites", "getPlaylistOnlyFavorites", "setPlaylistOnlyFavorites", "(Z)V", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "tabLayoutMediator", "Lcom/google/android/material/tabs/TabLayoutMediator;", "videoGridOnlyFavorites", "getVideoGridOnlyFavorites", "setVideoGridOnlyFavorites", "videoPagerAdapter", "Lorg/videolan/vlc/gui/video/VideoBrowserFragment$VideoPagerAdapter;", "viewPager", "Landroidx/viewpager2/widget/ViewPager2;", "allowedToExpand", "enableSearchOption", "filter", "", "query", "getCurrentFragment", "Landroidx/fragment/app/Fragment;", "getFilterQuery", "getPageTitle", "position", "", "getTitle", "hasFAB", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyActionMode", "onPrepareOptionsMenu", "onStart", "onStop", "onTabReselected", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "onViewCreated", "view", "reopenSearchIfNeeded", "restoreList", "setSearchVisibility", "visible", "setupTabLayout", "unSetTabLayout", "updateTabs", "VideoPagerAdapter", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoBrowserFragment.kt */
public final class VideoBrowserFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, Filterable {
    private final boolean hasTabs = true;
    private String lastQuery = "";
    private boolean needToReopenSearch;
    private boolean playlistOnlyFavorites;
    private TabLayout tabLayout;
    private TabLayoutMediator tabLayoutMediator;
    private boolean videoGridOnlyFavorites;
    private VideoPagerAdapter videoPagerAdapter;
    private ViewPager2 viewPager;

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
    }

    public void onTabReselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
    }

    public void onTabSelected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
    }

    public String getTitle() {
        String string = getString(R.string.videos);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public boolean getHasTabs() {
        return this.hasTabs;
    }

    public final boolean getVideoGridOnlyFavorites() {
        return this.videoGridOnlyFavorites;
    }

    public final void setVideoGridOnlyFavorites(boolean z) {
        this.videoGridOnlyFavorites = z;
        updateTabs();
    }

    public final boolean getPlaylistOnlyFavorites() {
        return this.playlistOnlyFavorites;
    }

    public final void setPlaylistOnlyFavorites(boolean z) {
        this.playlistOnlyFavorites = z;
        updateTabs();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.video_browser, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.tabLayout = (TabLayout) requireActivity().findViewById(R.id.sliding_tabs);
        View findViewById = view.findViewById(R.id.pager);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.viewPager = (ViewPager2) findViewById;
        this.videoPagerAdapter = new VideoPagerAdapter(this, this);
        ViewPager2 viewPager2 = this.viewPager;
        VideoPagerAdapter videoPagerAdapter2 = null;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        VideoPagerAdapter videoPagerAdapter3 = this.videoPagerAdapter;
        if (videoPagerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoPagerAdapter");
        } else {
            videoPagerAdapter2 = videoPagerAdapter3;
        }
        viewPager2.setAdapter(videoPagerAdapter2);
    }

    public void onStart() {
        setupTabLayout();
        super.onStart();
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [androidx.fragment.app.Fragment] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStop() {
        /*
            r4 = this;
            r4.unSetTabLayout()
            androidx.viewpager2.widget.ViewPager2 r0 = r4.viewPager
            r1 = 0
            if (r0 != 0) goto L_0x000e
            java.lang.String r0 = "viewPager"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x000e:
            androidx.fragment.app.FragmentManager r2 = r4.getChildFragmentManager()
            java.lang.String r3 = "getChildFragmentManager(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            androidx.fragment.app.Fragment r0 = org.videolan.vlc.util.KextensionsKt.findCurrentFragment(r0, r2)
            boolean r2 = r0 instanceof org.videolan.vlc.gui.BaseFragment
            if (r2 == 0) goto L_0x0022
            r1 = r0
            org.videolan.vlc.gui.BaseFragment r1 = (org.videolan.vlc.gui.BaseFragment) r1
        L_0x0022:
            if (r1 == 0) goto L_0x0027
            r1.stopActionMode()
        L_0x0027:
            super.onStop()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoBrowserFragment.onStop():void");
    }

    /* JADX WARNING: type inference failed for: r4v12, types: [androidx.fragment.app.Fragment] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onTabUnselected(com.google.android.material.tabs.TabLayout.Tab r4) {
        /*
            r3 = this;
            java.lang.String r0 = "tab"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r3.stopActionMode()
            androidx.fragment.app.FragmentActivity r4 = r3.getActivity()
            boolean r0 = r4 instanceof org.videolan.vlc.gui.ContentActivity
            r1 = 0
            if (r0 == 0) goto L_0x0014
            org.videolan.vlc.gui.ContentActivity r4 = (org.videolan.vlc.gui.ContentActivity) r4
            goto L_0x0015
        L_0x0014:
            r4 = r1
        L_0x0015:
            if (r4 == 0) goto L_0x001c
            boolean r4 = r4.isSearchViewVisible()
            goto L_0x001d
        L_0x001c:
            r4 = 0
        L_0x001d:
            r3.needToReopenSearch = r4
            androidx.fragment.app.FragmentActivity r4 = r3.getActivity()
            boolean r0 = r4 instanceof org.videolan.vlc.gui.ContentActivity
            if (r0 == 0) goto L_0x002a
            org.videolan.vlc.gui.ContentActivity r4 = (org.videolan.vlc.gui.ContentActivity) r4
            goto L_0x002b
        L_0x002a:
            r4 = r1
        L_0x002b:
            if (r4 == 0) goto L_0x0033
            java.lang.String r4 = r4.getCurrentQuery()
            if (r4 != 0) goto L_0x0035
        L_0x0033:
            java.lang.String r4 = ""
        L_0x0035:
            r3.lastQuery = r4
            r4 = r3
            androidx.lifecycle.LifecycleOwner r4 = (androidx.lifecycle.LifecycleOwner) r4
            boolean r4 = org.videolan.tools.KotlinExtensionsKt.isStarted(r4)
            if (r4 == 0) goto L_0x0063
            androidx.viewpager2.widget.ViewPager2 r4 = r3.viewPager
            if (r4 != 0) goto L_0x004a
            java.lang.String r4 = "viewPager"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r4 = r1
        L_0x004a:
            androidx.fragment.app.FragmentManager r0 = r3.getChildFragmentManager()
            java.lang.String r2 = "getChildFragmentManager(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            androidx.fragment.app.Fragment r4 = org.videolan.vlc.util.KextensionsKt.findCurrentFragment(r4, r0)
            boolean r0 = r4 instanceof org.videolan.vlc.gui.BaseFragment
            if (r0 == 0) goto L_0x005e
            r1 = r4
            org.videolan.vlc.gui.BaseFragment r1 = (org.videolan.vlc.gui.BaseFragment) r1
        L_0x005e:
            if (r1 == 0) goto L_0x0063
            r1.stopActionMode()
        L_0x0063:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoBrowserFragment.onTabUnselected(com.google.android.material.tabs.TabLayout$Tab):void");
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        reopenSearchIfNeeded();
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void reopenSearchIfNeeded() {
        /*
            r3 = this;
            boolean r0 = r3.needToReopenSearch
            if (r0 == 0) goto L_0x002f
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r1 = r0 instanceof org.videolan.vlc.gui.ContentActivity
            r2 = 0
            if (r1 == 0) goto L_0x0010
            org.videolan.vlc.gui.ContentActivity r0 = (org.videolan.vlc.gui.ContentActivity) r0
            goto L_0x0011
        L_0x0010:
            r0 = r2
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.openSearchView()
        L_0x0016:
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r1 = r0 instanceof org.videolan.vlc.gui.ContentActivity
            if (r1 == 0) goto L_0x0021
            r2 = r0
            org.videolan.vlc.gui.ContentActivity r2 = (org.videolan.vlc.gui.ContentActivity) r2
        L_0x0021:
            if (r2 == 0) goto L_0x0028
            java.lang.String r0 = r3.lastQuery
            r2.setCurrentQuery(r0)
        L_0x0028:
            java.lang.String r0 = ""
            r3.lastQuery = r0
            r0 = 0
            r3.needToReopenSearch = r0
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoBrowserFragment.reopenSearchIfNeeded():void");
    }

    private final void setupTabLayout() {
        TabLayout tabLayout2 = this.tabLayout;
        if (tabLayout2 != null && this.viewPager != null) {
            if (tabLayout2 != null) {
                tabLayout2.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
            }
            TabLayout tabLayout3 = this.tabLayout;
            if (tabLayout3 != null) {
                ViewPager2 viewPager2 = this.viewPager;
                if (viewPager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                    viewPager2 = null;
                }
                TabLayoutMediator tabLayoutMediator2 = new TabLayoutMediator(tabLayout3, viewPager2, new VideoBrowserFragment$$ExternalSyntheticLambda0(this));
                this.tabLayoutMediator = tabLayoutMediator2;
                tabLayoutMediator2.attach();
            }
            updateTabs();
        }
    }

    /* access modifiers changed from: private */
    public static final void setupTabLayout$lambda$1$lambda$0(VideoBrowserFragment videoBrowserFragment, TabLayout.Tab tab, int i) {
        Intrinsics.checkNotNullParameter(videoBrowserFragment, "this$0");
        Intrinsics.checkNotNullParameter(tab, "tab");
        tab.setText((CharSequence) videoBrowserFragment.getPageTitle(i));
    }

    private final String getPageTitle(int i) {
        String str;
        if (i == 0) {
            str = getString(R.string.videos);
        } else {
            str = getString(R.string.playlists);
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    /* access modifiers changed from: protected */
    public boolean hasFAB() {
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            if (viewPager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager2 = null;
            }
            return viewPager2.getCurrentItem() == 0;
        }
    }

    private final void unSetTabLayout() {
        TabLayout tabLayout2 = this.tabLayout;
        if (tabLayout2 != null && this.viewPager != null) {
            if (tabLayout2 != null) {
                tabLayout2.removeOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
            }
            TabLayoutMediator tabLayoutMediator2 = this.tabLayoutMediator;
            if (tabLayoutMediator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayoutMediator");
                tabLayoutMediator2 = null;
            }
            tabLayoutMediator2.detach();
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoBrowserFragment$VideoPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fa", "Lorg/videolan/vlc/gui/video/VideoBrowserFragment;", "(Lorg/videolan/vlc/gui/video/VideoBrowserFragment;Lorg/videolan/vlc/gui/video/VideoBrowserFragment;)V", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "", "getItemCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoBrowserFragment.kt */
    public final class VideoPagerAdapter extends FragmentStateAdapter {
        final /* synthetic */ VideoBrowserFragment this$0;

        public int getItemCount() {
            return 2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VideoPagerAdapter(VideoBrowserFragment videoBrowserFragment, VideoBrowserFragment videoBrowserFragment2) {
            super((Fragment) videoBrowserFragment2);
            Intrinsics.checkNotNullParameter(videoBrowserFragment2, "fa");
            this.this$0 = videoBrowserFragment;
        }

        public Fragment createFragment(int i) {
            if (i == 0) {
                return VideoGridFragment.Companion.newInstance();
            }
            if (i == 1) {
                return PlaylistFragment.Companion.newInstance(Playlist.Type.Video);
            }
            throw new IllegalStateException("Invalid fragment index");
        }
    }

    private final Fragment getCurrentFragment() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        StringBuilder sb = new StringBuilder("f");
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        sb.append(viewPager2.getCurrentItem());
        return childFragmentManager.findFragmentByTag(sb.toString());
    }

    public String getFilterQuery() {
        try {
            Fragment currentFragment = getCurrentFragment();
            Filterable filterable = currentFragment instanceof Filterable ? (Filterable) currentFragment : null;
            if (filterable != null) {
                return filterable.getFilterQuery();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean enableSearchOption() {
        Fragment currentFragment = getCurrentFragment();
        Filterable filterable = currentFragment instanceof Filterable ? (Filterable) currentFragment : null;
        if (filterable != null) {
            return filterable.enableSearchOption();
        }
        return false;
    }

    public void filter(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        Fragment currentFragment = getCurrentFragment();
        Filterable filterable = currentFragment instanceof Filterable ? (Filterable) currentFragment : null;
        if (filterable != null) {
            filterable.filter(str);
        }
    }

    public void restoreList() {
        Fragment currentFragment = getCurrentFragment();
        Filterable filterable = currentFragment instanceof Filterable ? (Filterable) currentFragment : null;
        if (filterable != null) {
            filterable.restoreList();
        }
    }

    public void setSearchVisibility(boolean z) {
        Fragment currentFragment = getCurrentFragment();
        Filterable filterable = currentFragment instanceof Filterable ? (Filterable) currentFragment : null;
        if (filterable != null) {
            filterable.setSearchVisibility(z);
        }
    }

    public boolean allowedToExpand() {
        Fragment currentFragment = getCurrentFragment();
        Filterable filterable = currentFragment instanceof Filterable ? (Filterable) currentFragment : null;
        if (filterable != null) {
            return filterable.allowedToExpand();
        }
        return false;
    }

    private final void updateTabs() {
        View view;
        TabLayout tabLayout2 = this.tabLayout;
        Intrinsics.checkNotNull(tabLayout2);
        int tabCount = tabLayout2.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout tabLayout3 = this.tabLayout;
            Intrinsics.checkNotNull(tabLayout3);
            TabLayout.Tab tabAt = tabLayout3.getTabAt(i);
            if (tabAt == null || (view = tabAt.getCustomView()) == null) {
                view = View.inflate(requireActivity(), R.layout.audio_tab, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(R.id.tab_title);
            textView.setText(getPageTitle(i));
            if (i != 0) {
                if (i == 1) {
                    if (this.playlistOnlyFavorites) {
                        UiTools uiTools = UiTools.INSTANCE;
                        Intrinsics.checkNotNull(textView);
                        uiTools.addFavoritesIcon(textView);
                    } else {
                        UiTools uiTools2 = UiTools.INSTANCE;
                        Intrinsics.checkNotNull(textView);
                        uiTools2.removeDrawables(textView);
                    }
                }
            } else if (this.videoGridOnlyFavorites) {
                UiTools uiTools3 = UiTools.INSTANCE;
                Intrinsics.checkNotNull(textView);
                uiTools3.addFavoritesIcon(textView);
            } else {
                UiTools uiTools4 = UiTools.INSTANCE;
                Intrinsics.checkNotNull(textView);
                uiTools4.removeDrawables(textView);
            }
            if (tabAt != null) {
                tabAt.setCustomView(view);
            }
        }
    }
}
