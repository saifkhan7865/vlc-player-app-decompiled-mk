package org.videolan.vlc.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.MediaParsingServiceKt;
import org.videolan.vlc.R;
import org.videolan.vlc.StartActivity;
import org.videolan.vlc.gui.audio.AudioBrowserFragment;
import org.videolan.vlc.gui.browser.BaseBrowserFragment;
import org.videolan.vlc.gui.dialogs.AllAccessPermissionDialog;
import org.videolan.vlc.gui.helpers.INavigator;
import org.videolan.vlc.gui.helpers.Navigator;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.video.VideoGridFragment;
import org.videolan.vlc.interfaces.Filterable;
import org.videolan.vlc.interfaces.IRefreshable;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.Permissions;
import org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0011\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000bH\u0001J\t\u0010&\u001a\u00020\u001aH\u0001J\u0006\u0010'\u001a\u00020$J\u0012\u0010'\u001a\u00020$2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u0011\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020,H\u0001J\u0012\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020\u001aH\u0016J\"\u00100\u001a\u00020$2\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\b\u00103\u001a\u0004\u0018\u000104H\u0014J\b\u00105\u001a\u00020$H\u0017J\u0012\u00106\u001a\u00020$2\b\u00107\u001a\u0004\u0018\u000108H\u0015J\u0018\u00109\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020?H\u0016J\u0012\u0010A\u001a\u00020\u001a2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010D\u001a\u00020$H\u0014J\b\u0010E\u001a\u00020$H\u0014J\u0010\u0010F\u001a\u00020$2\u0006\u0010G\u001a\u000208H\u0014J\b\u0010H\u001a\u00020$H\u0014J\b\u0010I\u001a\u00020$H\u0014J\b\u0010J\u001a\u00020$H\u0002J\t\u0010K\u001a\u00020$H\u0001J\u0012\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020OH\u0016J\b\u0010P\u001a\u00020$H\u0002J\u0017\u0010Q\u001a\u00020$*\u00020\u00002\b\u0010R\u001a\u0004\u0018\u000108H\u0001R\u0018\u0010\u0004\u001a\u00020\u0005X\u000f¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00020\u000bX\u000f¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u000f¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X.¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lorg/videolan/vlc/gui/MainActivity;", "Lorg/videolan/vlc/gui/ContentActivity;", "Lorg/videolan/vlc/gui/helpers/INavigator;", "()V", "appbarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "getAppbarLayout", "()Lcom/google/android/material/appbar/AppBarLayout;", "setAppbarLayout", "(Lcom/google/android/material/appbar/AppBarLayout;)V", "currentFragmentId", "", "getCurrentFragmentId", "()I", "setCurrentFragmentId", "(I)V", "mediaLibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "navigationView", "", "Lcom/google/android/material/navigation/NavigationBarView;", "getNavigationView", "()Ljava/util/List;", "setNavigationView", "(Ljava/util/List;)V", "value", "", "refreshing", "getRefreshing", "()Z", "setRefreshing", "(Z)V", "scanNeeded", "toolbarIcon", "Landroid/widget/ImageView;", "configurationChanged", "", "size", "currentIdIsExtension", "forceRefresh", "current", "Landroidx/fragment/app/Fragment;", "getFragmentWidth", "activity", "Landroid/app/Activity;", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onMenuItemActionExpand", "item", "Landroid/view/MenuItem;", "onOptionsItemSelected", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onRestart", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "prepareActionBar", "reloadPreferences", "startSupportActionMode", "Landroidx/appcompat/view/ActionMode;", "callback", "Landroidx/appcompat/view/ActionMode$Callback;", "updateIncognitoModeIcon", "setupNavigation", "state", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainActivity.kt */
public final class MainActivity extends ContentActivity implements INavigator {
    private final /* synthetic */ Navigator $$delegate_0 = new Navigator();
    private Medialibrary mediaLibrary;
    private boolean refreshing;
    private boolean scanNeeded;
    private ImageView toolbarIcon;

    public void configurationChanged(int i) {
        this.$$delegate_0.configurationChanged(i);
    }

    public boolean currentIdIsExtension() {
        return this.$$delegate_0.currentIdIsExtension();
    }

    public AppBarLayout getAppbarLayout() {
        return this.$$delegate_0.getAppbarLayout();
    }

    public int getCurrentFragmentId() {
        return this.$$delegate_0.getCurrentFragmentId();
    }

    public int getFragmentWidth(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.$$delegate_0.getFragmentWidth(activity);
    }

    public List<NavigationBarView> getNavigationView() {
        return this.$$delegate_0.getNavigationView();
    }

    public void reloadPreferences() {
        this.$$delegate_0.reloadPreferences();
    }

    public void setAppbarLayout(AppBarLayout appBarLayout) {
        Intrinsics.checkNotNullParameter(appBarLayout, "<set-?>");
        this.$$delegate_0.setAppbarLayout(appBarLayout);
    }

    public void setCurrentFragmentId(int i) {
        this.$$delegate_0.setCurrentFragmentId(i);
    }

    public void setNavigationView(List<? extends NavigationBarView> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.$$delegate_0.setNavigationView(list);
    }

    public void setupNavigation(MainActivity mainActivity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(mainActivity, "<this>");
        this.$$delegate_0.setupNavigation(mainActivity, bundle);
    }

    public final boolean getRefreshing() {
        return this.refreshing;
    }

    public final void setRefreshing(boolean z) {
        this.refreshing = z;
    }

    public View getSnackAnchorView(boolean z) {
        View snackAnchorView = super.getSnackAnchorView(z);
        if (snackAnchorView == null || snackAnchorView.getId() != 16908290 || UiTools.INSTANCE.isTablet(this)) {
            return snackAnchorView;
        }
        return z ? findViewById(16908290) : findViewById(R.id.appbar);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0026, code lost:
        if (getSettings().getBoolean(org.videolan.tools.SettingsKt.KEY_MEDIALIBRARY_AUTO_RESCAN, true) != false) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r7) {
        /*
            r6 = this;
            super.onCreate(r7)
            org.videolan.vlc.util.Util r0 = org.videolan.vlc.util.Util.INSTANCE
            r1 = r6
            android.content.Context r1 = (android.content.Context) r1
            r0.checkCpuCompatibility(r1)
            int r0 = org.videolan.vlc.R.layout.main
            r6.setContentView((int) r0)
            r6.initAudioPlayerContainerActivity()
            r6.setupNavigation(r6, r7)
            r6.prepareActionBar()
            if (r7 != 0) goto L_0x0029
            android.content.SharedPreferences r7 = r6.getSettings()
            java.lang.String r0 = "auto_rescan"
            r1 = 1
            boolean r7 = r7.getBoolean(r0, r1)
            if (r7 == 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r1 = 0
        L_0x002a:
            r6.scanNeeded = r1
            org.videolan.medialibrary.interfaces.Medialibrary r7 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            java.lang.String r0 = "getInstance(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r6.mediaLibrary = r7
            org.videolan.vlc.gui.dialogs.NotificationPermissionManager r7 = org.videolan.vlc.gui.dialogs.NotificationPermissionManager.INSTANCE
            r0 = r6
            androidx.fragment.app.FragmentActivity r0 = (androidx.fragment.app.FragmentActivity) r0
            boolean r7 = r7.launchIfNeeded(r0)
            if (r7 != 0) goto L_0x0064
            org.videolan.vlc.util.WidgetMigration r7 = org.videolan.vlc.util.WidgetMigration.INSTANCE
            r0 = r6
            androidx.appcompat.app.AppCompatActivity r0 = (androidx.appcompat.app.AppCompatActivity) r0
            boolean r7 = r7.launchIfNeeded(r0)
            if (r7 != 0) goto L_0x0064
            org.videolan.tools.Settings r7 = org.videolan.tools.Settings.INSTANCE
            boolean r7 = r7.getFirstRun()
            if (r7 != 0) goto L_0x005b
            org.videolan.vlc.util.WhatsNewManager r7 = org.videolan.vlc.util.WhatsNewManager.INSTANCE
            r7.launchIfNeeded(r0)
            goto L_0x0064
        L_0x005b:
            org.videolan.vlc.util.WhatsNewManager r7 = org.videolan.vlc.util.WhatsNewManager.INSTANCE
            android.content.SharedPreferences r0 = r6.getSettings()
            r7.markAsShown(r0)
        L_0x0064:
            r7 = r6
            androidx.lifecycle.LifecycleOwner r7 = (androidx.lifecycle.LifecycleOwner) r7
            androidx.lifecycle.LifecycleCoroutineScope r7 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r7)
            r0 = r7
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            org.videolan.vlc.gui.MainActivity$onCreate$1 r7 = new org.videolan.vlc.gui.MainActivity$onCreate$1
            r1 = 0
            r7.<init>(r6, r1)
            r3 = r7
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = 3
            r5 = 0
            r2 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.MainActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Snackbar action;
        super.onResume();
        if (!getSettings().getBoolean(SettingsKt.PERMISSION_NEVER_ASK, false) && getSettings().getLong(SettingsKt.PERMISSION_NEXT_ASK, 0) < System.currentTimeMillis()) {
            Context context = this;
            if (Permissions.INSTANCE.canReadStorage(context) && !Permissions.INSTANCE.hasAllAccess(context)) {
                String string = getString(R.string.partial_content);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                Snackbar snackerMessageInfinite = UiTools.INSTANCE.snackerMessageInfinite(this, string);
                if (!(snackerMessageInfinite == null || (action = snackerMessageInfinite.setAction(R.string.more, (View.OnClickListener) new MainActivity$$ExternalSyntheticLambda1(this))) == null)) {
                    action.show();
                }
                SettingsKt.putSingle(getSettings(), SettingsKt.PERMISSION_NEXT_ASK, Long.valueOf(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(2)));
            }
        }
        configurationChanged(KextensionsKt.getScreenWidth(this));
    }

    /* access modifiers changed from: private */
    public static final void onResume$lambda$0(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        AllAccessPermissionDialog.Companion.newInstance().show(mainActivity.getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(AllAccessPermissionDialog.class).getSimpleName());
    }

    private final void prepareActionBar() {
        View findViewById = findViewById(R.id.toolbar_icon);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.toolbarIcon = (ImageView) findViewById;
        updateIncognitoModeIcon();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setHomeButtonEnabled(false);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Medialibrary medialibrary = this.mediaLibrary;
        Medialibrary medialibrary2 = null;
        if (medialibrary == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaLibrary");
            medialibrary = null;
        }
        if (medialibrary.isInitiated() && this.scanNeeded) {
            Context context = this;
            if (Permissions.INSTANCE.canReadStorage(context)) {
                Medialibrary medialibrary3 = this.mediaLibrary;
                if (medialibrary3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaLibrary");
                } else {
                    medialibrary2 = medialibrary3;
                }
                if (!medialibrary2.isWorking()) {
                    MediaParsingServiceKt.reloadLibrary(context);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (getChangingConfigurations() == 0) {
            Medialibrary medialibrary = this.mediaLibrary;
            if (medialibrary == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaLibrary");
                medialibrary = null;
            }
            this.scanNeeded = medialibrary.isWorking();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        Fragment currentFragment = getCurrentFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNull(currentFragment);
        supportFragmentManager.putFragment(bundle, "current_fragment", currentFragment);
        bundle.putInt("extra_parse", getCurrentFragmentId());
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        reloadPreferences();
    }

    public void onBackPressed() {
        if (!isAudioPlayerReady() || (!getAudioPlayer().backPressed() && !slideDownAudioPlayer())) {
            Fragment currentFragment = getCurrentFragment();
            if ((currentFragment instanceof BaseBrowserFragment) && ((BaseBrowserFragment) currentFragment).goBack()) {
                return;
            }
            if (!AndroidUtil.isNougatOrLater || !isInMultiWindowMode()) {
                finish();
            } else {
                UiTools.INSTANCE.confirmExit(this);
            }
        }
    }

    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        getAppBarLayout().setExpanded(true);
        return super.startSupportActionMode(callback);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = null;
        MenuItem findItem = menu != null ? menu.findItem(R.id.ml_menu_refresh) : null;
        if (findItem != null) {
            findItem.setVisible(Permissions.INSTANCE.canReadStorage(this));
        }
        if (menu != null) {
            menuItem = menu.findItem(R.id.incognito_mode);
        }
        if (menuItem != null) {
            menuItem.setChecked(((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean(SettingsKt.KEY_INCOGNITO, false));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != R.id.ml_menu_filter) {
            UiTools.INSTANCE.setKeyboardVisibility(getAppBarLayout(), false);
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.ml_menu_refresh) {
            if (!Permissions.INSTANCE.canReadStorage(this)) {
                return true;
            }
            forceRefresh();
            return true;
        } else if (itemId == R.id.incognito_mode) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new MainActivity$onOptionsItemSelected$1(this, menuItem, (Continuation<? super MainActivity$onOptionsItemSelected$1>) null), 3, (Object) null);
            return true;
        } else if (itemId == 16908332) {
            return slideDownAudioPlayer();
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: private */
    public final void updateIncognitoModeIcon() {
        boolean z = ((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean(SettingsKt.KEY_INCOGNITO, false);
        ImageView imageView = this.toolbarIcon;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarIcon");
            imageView = null;
        }
        imageView.setImageDrawable(ContextCompat.getDrawable(this, z ? R.drawable.ic_incognito : R.drawable.ic_icon));
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (!(getCurrentFragment() instanceof Filterable)) {
            return false;
        }
        Fragment currentFragment = getCurrentFragment();
        Intrinsics.checkNotNull(currentFragment, "null cannot be cast to non-null type org.videolan.vlc.interfaces.Filterable");
        return ((Filterable) currentFragment).allowedToExpand();
    }

    public final void forceRefresh() {
        forceRefresh(getCurrentFragment());
    }

    private final void forceRefresh(Fragment fragment) {
        Medialibrary medialibrary = this.mediaLibrary;
        if (medialibrary == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaLibrary");
            medialibrary = null;
        }
        if (medialibrary.isWorking()) {
            return;
        }
        if (fragment == null || !(fragment instanceof IRefreshable)) {
            MediaParsingServiceKt.reloadLibrary(this);
        } else {
            ((IRefreshable) fragment).refresh();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == 2) {
                MediaParsingServiceKt.reloadLibrary(this);
            } else if (i2 == 3 || i2 == 4) {
                Intent intent2 = new Intent(this, i2 == 4 ? StartActivity.class : MainActivity.class);
                finish();
                startActivity(intent2);
            } else if (i2 == 5) {
                for (Fragment next : getSupportFragmentManager().getFragments()) {
                    if (next instanceof VideoGridFragment) {
                        ((VideoGridFragment) next).updateSeenMediaMarker();
                    }
                }
            } else if (i2 == 6) {
                Fragment currentFragment = getCurrentFragment();
                if (currentFragment instanceof AudioBrowserFragment) {
                    ((AudioBrowserViewModel) ((AudioBrowserFragment) currentFragment).getViewModel()).refresh();
                }
            }
        } else if (i == 2 && i2 == -1) {
            Intrinsics.checkNotNull(intent);
            MediaUtils.INSTANCE.openUri(this, intent.getData());
        } else if (i != 3) {
        } else {
            if (i2 == 2) {
                forceRefresh(getCurrentFragment());
            } else {
                this.scanNeeded = false;
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i == 84) {
            getToolbar().getMenu().findItem(R.id.ml_menu_filter).expandActionView();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
