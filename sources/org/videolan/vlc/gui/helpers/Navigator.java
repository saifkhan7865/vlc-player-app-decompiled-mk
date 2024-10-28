package org.videolan.vlc.gui.helpers;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.MainActivity;
import org.videolan.vlc.gui.MoreFragment;
import org.videolan.vlc.gui.PlaylistFragment;
import org.videolan.vlc.gui.audio.AudioBrowserFragment;
import org.videolan.vlc.gui.browser.BaseBrowserFragment;
import org.videolan.vlc.gui.browser.MainBrowserFragment;
import org.videolan.vlc.gui.video.VideoBrowserFragment;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010 \u001a\u00020!2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#H\u0002J\u0010\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\u0011H\u0016J\b\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0010\u0010.\u001a\u00020'2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0010\u0010/\u001a\u00020'2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020!2\u0006\u00103\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020!2\u0006\u00103\u001a\u000204H\u0016J\b\u00106\u001a\u00020!H\u0016J\"\u00107\u001a\u00020!2\u0006\u00108\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u00112\b\b\u0002\u00109\u001a\u00020-H\u0002J\u0010\u00107\u001a\u00020!2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0010\u0010:\u001a\u00020!2\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0016\u0010;\u001a\u00020!*\u00020\u00062\b\u0010<\u001a\u0004\u0018\u00010=H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lorg/videolan/vlc/gui/helpers/Navigator;", "Lcom/google/android/material/navigation/NavigationBarView$OnItemSelectedListener;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Lorg/videolan/vlc/gui/helpers/INavigator;", "()V", "activity", "Lorg/videolan/vlc/gui/MainActivity;", "appbarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "getAppbarLayout", "()Lcom/google/android/material/appbar/AppBarLayout;", "setAppbarLayout", "(Lcom/google/android/material/appbar/AppBarLayout;)V", "<set-?>", "Landroidx/fragment/app/Fragment;", "currentFragment", "currentFragmentId", "", "getCurrentFragmentId", "()I", "setCurrentFragmentId", "(I)V", "defaultFragmentId", "navigationView", "", "Lcom/google/android/material/navigation/NavigationBarView;", "getNavigationView", "()Ljava/util/List;", "setNavigationView", "(Ljava/util/List;)V", "settings", "Landroid/content/SharedPreferences;", "clearBackstackFromClass", "", "clazz", "Ljava/lang/Class;", "configurationChanged", "size", "currentIdIsExtension", "", "getFragmentWidth", "Landroid/app/Activity;", "getNewFragment", "id", "getTag", "", "idIsExtension", "onNavigationItemSelected", "item", "Landroid/view/MenuItem;", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "reloadPreferences", "showFragment", "fragment", "tag", "updateCheckedItem", "setupNavigation", "state", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Navigator.kt */
public final class Navigator implements NavigationBarView.OnItemSelectedListener, DefaultLifecycleObserver, INavigator {
    private MainActivity activity;
    public AppBarLayout appbarLayout;
    private Fragment currentFragment;
    private int currentFragmentId;
    private final int defaultFragmentId = R.id.nav_video;
    public List<? extends NavigationBarView> navigationView;
    private SharedPreferences settings;

    private final boolean idIsExtension(int i) {
        return 1 <= i && i < 101;
    }

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    public int getCurrentFragmentId() {
        return this.currentFragmentId;
    }

    public void setCurrentFragmentId(int i) {
        this.currentFragmentId = i;
    }

    public List<NavigationBarView> getNavigationView() {
        List<? extends NavigationBarView> list = this.navigationView;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("navigationView");
        return null;
    }

    public void setNavigationView(List<? extends NavigationBarView> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.navigationView = list;
    }

    public AppBarLayout getAppbarLayout() {
        AppBarLayout appBarLayout = this.appbarLayout;
        if (appBarLayout != null) {
            return appBarLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appbarLayout");
        return null;
    }

    public void setAppbarLayout(AppBarLayout appBarLayout) {
        Intrinsics.checkNotNullParameter(appBarLayout, "<set-?>");
        this.appbarLayout = appBarLayout;
    }

    public void setupNavigation(MainActivity mainActivity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(mainActivity, "<this>");
        this.activity = mainActivity;
        this.settings = mainActivity.getSettings();
        mainActivity.setCurrentFragmentId(mainActivity.getIntent().getIntExtra("extra_parse", 0));
        if (bundle != null) {
            this.currentFragment = mainActivity.getSupportFragmentManager().getFragment(bundle, "current_fragment");
        }
        mainActivity.getLifecycle().addObserver(this);
        mainActivity.setNavigationView(CollectionsKt.listOf(mainActivity.findViewById(R.id.navigation), mainActivity.findViewById(R.id.navigation_rail)));
        View findViewById = mainActivity.findViewById(R.id.appbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        mainActivity.setAppbarLayout((AppBarLayout) findViewById);
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        int i;
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        if (this.currentFragment == null && !currentIdIsExtension()) {
            if (getCurrentFragmentId() != 0) {
                i = getCurrentFragmentId();
            } else {
                SharedPreferences sharedPreferences = this.settings;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settings");
                    sharedPreferences = null;
                }
                i = sharedPreferences.getInt("fragment_id", this.defaultFragmentId);
            }
            showFragment(i);
        }
        for (NavigationBarView onItemSelectedListener : getNavigationView()) {
            onItemSelectedListener.setOnItemSelectedListener(this);
        }
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        for (NavigationBarView onItemSelectedListener : getNavigationView()) {
            onItemSelectedListener.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) null);
        }
    }

    private final Fragment getNewFragment(int i) {
        if (i == R.id.nav_audio) {
            return new AudioBrowserFragment();
        }
        if (i == R.id.nav_directories) {
            return new MainBrowserFragment();
        }
        if (i == R.id.nav_playlists) {
            return new PlaylistFragment();
        }
        if (i == R.id.nav_more) {
            return new MoreFragment();
        }
        return new VideoBrowserFragment();
    }

    private final void showFragment(int i) {
        showFragment(getNewFragment(i), i, getTag(i));
    }

    static /* synthetic */ void showFragment$default(Navigator navigator, Fragment fragment, int i, String str, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = navigator.getTag(i);
        }
        navigator.showFragment(fragment, i, str);
    }

    private final void showFragment(Fragment fragment, int i, String str) {
        MainActivity mainActivity = this.activity;
        if (mainActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            mainActivity = null;
        }
        FragmentManager supportFragmentManager = mainActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        if (this.currentFragment instanceof BaseBrowserFragment) {
            supportFragmentManager.popBackStackImmediate("root", 1);
        }
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
        beginTransaction.replace(R.id.fragment_placeholder, fragment, str);
        beginTransaction.commitAllowingStateLoss();
        updateCheckedItem(i);
        this.currentFragment = fragment;
        setCurrentFragmentId(i);
    }

    public boolean currentIdIsExtension() {
        return idIsExtension(getCurrentFragmentId());
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0013 A[LOOP:0: B:4:0x0013->B:7:0x001f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void clearBackstackFromClass(java.lang.Class<?> r3) {
        /*
            r2 = this;
            org.videolan.vlc.gui.MainActivity r0 = r2.activity
            if (r0 != 0) goto L_0x000a
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = 0
        L_0x000a:
            androidx.fragment.app.FragmentManager r0 = r0.getSupportFragmentManager()
            java.lang.String r1 = "getSupportFragmentManager(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
        L_0x0013:
            androidx.fragment.app.Fragment r1 = r2.currentFragment
            boolean r1 = r3.isInstance(r1)
            if (r1 == 0) goto L_0x0021
            boolean r1 = r0.popBackStackImmediate()
            if (r1 != 0) goto L_0x0013
        L_0x0021:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.Navigator.clearBackstackFromClass(java.lang.Class):void");
    }

    public void reloadPreferences() {
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        setCurrentFragmentId(sharedPreferences.getInt("fragment_id", this.defaultFragmentId));
    }

    public void configurationChanged(int i) {
        for (NavigationBarView navigationBarView : getNavigationView()) {
            MainActivity mainActivity = null;
            if (navigationBarView instanceof BottomNavigationView) {
                UiTools uiTools = UiTools.INSTANCE;
                MainActivity mainActivity2 = this.activity;
                if (mainActivity2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activity");
                } else {
                    mainActivity = mainActivity2;
                }
                View view = navigationBarView;
                if (uiTools.isTablet(mainActivity)) {
                    KotlinExtensionsKt.setGone(view);
                } else {
                    KotlinExtensionsKt.setVisible(view);
                }
            } else {
                UiTools uiTools2 = UiTools.INSTANCE;
                MainActivity mainActivity3 = this.activity;
                if (mainActivity3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activity");
                } else {
                    mainActivity = mainActivity3;
                }
                View view2 = navigationBarView;
                if (!uiTools2.isTablet(mainActivity)) {
                    KotlinExtensionsKt.setGone(view2);
                } else {
                    KotlinExtensionsKt.setVisible(view2);
                }
            }
        }
    }

    public int getFragmentWidth(Activity activity2) {
        Intrinsics.checkNotNullParameter(activity2, "activity");
        return KextensionsKt.getScreenWidth(activity2) - ((int) activity2.getResources().getDimension(R.dimen.navigation_margin));
    }

    private final String getTag(int i) {
        if (i == R.id.nav_audio) {
            return Constants.ID_AUDIO;
        }
        if (i == R.id.nav_directories) {
            return Constants.ID_DIRECTORIES;
        }
        return "video";
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        Fragment fragment = this.currentFragment;
        getAppbarLayout().setExpanded(true, true);
        if (fragment == null) {
            return false;
        }
        if (fragment instanceof BaseFragment) {
            BaseFragment baseFragment = (BaseFragment) fragment;
            if (baseFragment.getActionMode() != null) {
                baseFragment.stopActionMode();
            }
        }
        MainActivity mainActivity = null;
        if (getCurrentFragmentId() == itemId) {
            BaseBrowserFragment baseBrowserFragment = fragment instanceof BaseBrowserFragment ? (BaseBrowserFragment) fragment : null;
            if (baseBrowserFragment == null || KotlinExtensionsKt.isStarted(baseBrowserFragment)) {
                return false;
            }
            MainActivity mainActivity2 = this.activity;
            if (mainActivity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
            } else {
                mainActivity = mainActivity2;
            }
            mainActivity.getSupportFragmentManager().popBackStackImmediate("root", 1);
        } else {
            MainActivity mainActivity3 = this.activity;
            if (mainActivity3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
            } else {
                mainActivity = mainActivity3;
            }
            mainActivity.slideDownAudioPlayer();
            showFragment(itemId);
        }
        return true;
    }

    private final void updateCheckedItem(int i) {
        int currentFragmentId2 = getCurrentFragmentId();
        for (NavigationBarView navigationBarView : getNavigationView()) {
            MenuItem findItem = navigationBarView.getMenu().findItem(i);
            if (!(i == navigationBarView.getSelectedItemId() || findItem == null)) {
                MenuItem findItem2 = navigationBarView.getMenu().findItem(currentFragmentId2);
                if (findItem2 != null) {
                    findItem2.setChecked(false);
                }
                findItem.setChecked(true);
                SharedPreferences sharedPreferences = this.settings;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settings");
                    sharedPreferences = null;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("fragment_id", i);
                edit.apply();
            }
        }
    }
}
