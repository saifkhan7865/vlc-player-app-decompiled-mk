package org.videolan.vlc.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.AppBarLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HelpersKt;
import org.videolan.vlc.MediaParsingServiceKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.util.DialogDelegate;
import org.videolan.vlc.util.IDialogManager;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 *2\u00020\u00012\u00020\u0002:\u0001*B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0007H\u0016J\"\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u000eH\u0014J\u0012\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020\u000eH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\f\u0010\t¨\u0006+"}, d2 = {"Lorg/videolan/vlc/gui/SecondaryActivity;", "Lorg/videolan/vlc/gui/ContentActivity;", "Lorg/videolan/vlc/util/IDialogManager;", "()V", "dialogsDelegate", "Lorg/videolan/vlc/util/DialogDelegate;", "displayTitle", "", "getDisplayTitle", "()Z", "fragment", "Landroidx/fragment/app/Fragment;", "isOnboarding", "dialogCanceled", "", "dialog", "Lorg/videolan/libvlc/Dialog;", "fetchSecondaryFragment", "id", "", "finish", "fireDialog", "forcedTheme", "", "()Ljava/lang/Integer;", "hideRenderers", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onResume", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SecondaryActivity.kt */
public final class SecondaryActivity extends ContentActivity implements IDialogManager {
    public static final String ABOUT = "about";
    public static final int ACTIVITY_RESULT_SECONDARY = 3;
    public static final String ALBUMS_SONGS = "albumsSongs";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String FILE_BROWSER = "file_browser";
    public static final String HISTORY = "history";
    public static final String KEY_FRAGMENT = "fragment";
    public static final String STORAGE_BROWSER = "storage_browser";
    public static final String STORAGE_BROWSER_ONBOARDING = "storage_browser_onboarding";
    public static final String STREAMS = "streams";
    public static final String TAG = "VLC/SecondaryActivity";
    public static final String VIDEO_GROUP_LIST = "videoGroupList";
    private final DialogDelegate dialogsDelegate = new DialogDelegate();
    private final boolean displayTitle = true;
    private Fragment fragment;

    public void dialogCanceled(Dialog dialog) {
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    public final boolean isOnboarding() {
        return Intrinsics.areEqual((Object) getIntent().getStringExtra(KEY_FRAGMENT), (Object) STORAGE_BROWSER_ONBOARDING);
    }

    public Integer forcedTheme() {
        if (Intrinsics.areEqual((Object) getIntent().getStringExtra(KEY_FRAGMENT), (Object) STORAGE_BROWSER_ONBOARDING)) {
            return Integer.valueOf(R.style.Theme_VLC_Black);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ActionBar supportActionBar;
        super.onCreate(bundle);
        setContentView(R.layout.secondary);
        initAudioPlayerContainerActivity();
        if (isOnboarding()) {
            new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(false);
        }
        View findViewById = findViewById(R.id.fragment_placeholder);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
        if (AndroidDevices.INSTANCE.isTv()) {
            HelpersKt.applyOverscanMargin((Activity) this);
            layoutParams2.topMargin = getResources().getDimensionPixelSize(UiTools.INSTANCE.getResourceFromAttribute(this, R.attr.actionBarSize));
        } else {
            layoutParams2.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        }
        findViewById.requestLayout();
        ActionBar supportActionBar2 = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar2);
        supportActionBar2.setDisplayHomeAsUpEnabled(true);
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_placeholder) == null) {
            String stringExtra = getIntent().getStringExtra(KEY_FRAGMENT);
            if (stringExtra != null) {
                fetchSecondaryFragment(stringExtra);
            }
            if (this.fragment == null) {
                finish();
                return;
            }
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            int i = R.id.fragment_placeholder;
            Fragment fragment2 = this.fragment;
            Intrinsics.checkNotNull(fragment2);
            beginTransaction.add(i, fragment2).commit();
        }
        this.dialogsDelegate.observeDialogs(this, this);
        if (getIntent().getBooleanExtra(Constants.KEY_ANIMATED, false) && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_close_up);
        }
    }

    public void fireDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogActivity.Companion.setDialog(dialog);
        startActivity(new Intent(DialogActivity.KEY_DIALOG, (Uri) null, this, DialogActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (!getIntent().getBooleanExtra(Constants.KEY_ANIMATED, false)) {
            overridePendingTransition(0, 0);
        }
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (!getIntent().getBooleanExtra(Constants.KEY_ANIMATED, false) && isFinishing()) {
            overridePendingTransition(0, 0);
        }
        super.onPause();
    }

    public void finish() {
        super.finish();
        if (getIntent().getBooleanExtra(Constants.KEY_ANIMATED, false)) {
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_bottom);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 3 && i2 == 2) {
            MediaParsingServiceKt.reloadLibrary(this);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu != null ? menu.findItem(R.id.ml_menu_refresh) : null;
        if (findItem != null) {
            findItem.setVisible(Permissions.INSTANCE.canReadStorage(this));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != R.id.ml_menu_refresh) {
            return super.onOptionsItemSelected(menuItem);
        }
        Context context = this;
        if (!Permissions.INSTANCE.canReadStorage(context)) {
            return true;
        }
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        if (instance.isWorking()) {
            return true;
        }
        MediaParsingServiceKt.reloadLibrary(context);
        return true;
    }

    public boolean hideRenderers() {
        return Intrinsics.areEqual((Object) getIntent().getStringExtra(KEY_FRAGMENT), (Object) STORAGE_BROWSER_ONBOARDING);
    }

    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r7v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v18, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r7v8, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r10v23 */
    /* JADX WARNING: type inference failed for: r10v35, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r10v38, types: [android.os.Parcelable] */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0102, code lost:
        if (r10.equals(STORAGE_BROWSER_ONBOARDING) != false) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0104, code lost:
        r9.fragment = org.videolan.vlc.gui.browser.MLStorageBrowserFragment.Companion.newInstance(kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) STORAGE_BROWSER_ONBOARDING));
        setResult(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0019, code lost:
        if (r10.equals(STORAGE_BROWSER) != false) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0198, code lost:
        throw new java.lang.IllegalArgumentException("Wrong fragment id.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        return;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void fetchSecondaryFragment(java.lang.String r10) {
        /*
            r9 = this;
            int r0 = r10.hashCode()
            r1 = 2
            java.lang.String r2 = "storage_browser_onboarding"
            r3 = 1
            r4 = 33
            java.lang.String r5 = "getIntent(...)"
            r6 = 0
            r7 = 0
            switch(r0) {
                case -1881890573: goto L_0x017f;
                case -1166431870: goto L_0x0118;
                case -958719018: goto L_0x00fe;
                case 80329850: goto L_0x00a7;
                case 92611469: goto L_0x0094;
                case 926934164: goto L_0x0081;
                case 1176472421: goto L_0x001d;
                case 1455319396: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0191
        L_0x0013:
            java.lang.String r0 = "storage_browser"
            boolean r0 = r10.equals(r0)
            if (r0 == 0) goto L_0x0191
            goto L_0x0104
        L_0x001d:
            java.lang.String r0 = "file_browser"
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0191
            android.content.Intent r10 = r9.getIntent()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r5)
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "key_media"
            if (r0 < r4) goto L_0x003b
            java.lang.Class<org.videolan.medialibrary.interfaces.media.MediaWrapper> r0 = org.videolan.medialibrary.interfaces.media.MediaWrapper.class
            java.lang.Object r10 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.content.Intent) r10, (java.lang.String) r1, (java.lang.Class) r0)
            android.os.Parcelable r10 = (android.os.Parcelable) r10
            goto L_0x0048
        L_0x003b:
            android.os.Parcelable r10 = r10.getParcelableExtra(r1)
            boolean r0 = r10 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r0 != 0) goto L_0x0044
            r10 = r7
        L_0x0044:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r10 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r10
            android.os.Parcelable r10 = (android.os.Parcelable) r10
        L_0x0048:
            boolean r0 = r10 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r0 == 0) goto L_0x004f
            r7 = r10
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r7
        L_0x004f:
            if (r7 == 0) goto L_0x0190
            android.net.Uri r10 = r7.getUri()
            java.lang.String r10 = r10.getScheme()
            boolean r10 = org.videolan.vlc.util.BrowserutilsKt.isSchemeNetwork(r10)
            if (r10 == 0) goto L_0x0067
            org.videolan.vlc.gui.browser.NetworkBrowserFragment r10 = new org.videolan.vlc.gui.browser.NetworkBrowserFragment
            r10.<init>()
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            goto L_0x006e
        L_0x0067:
            org.videolan.vlc.gui.browser.FileBrowserFragment r10 = new org.videolan.vlc.gui.browser.FileBrowserFragment
            r10.<init>()
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
        L_0x006e:
            r9.fragment = r10
            kotlin.Pair[] r0 = new kotlin.Pair[r3]
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r7)
            r0[r6] = r1
            android.os.Bundle r0 = androidx.core.os.BundleKt.bundleOf(r0)
            r10.setArguments(r0)
            goto L_0x0190
        L_0x0081:
            java.lang.String r0 = "history"
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0191
            org.videolan.vlc.gui.HistoryFragment r10 = new org.videolan.vlc.gui.HistoryFragment
            r10.<init>()
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            r9.fragment = r10
            goto L_0x0190
        L_0x0094:
            java.lang.String r0 = "about"
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0191
            org.videolan.vlc.gui.AboutFragment r10 = new org.videolan.vlc.gui.AboutFragment
            r10.<init>()
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            r9.fragment = r10
            goto L_0x0190
        L_0x00a7:
            java.lang.String r0 = "albumsSongs"
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0191
            org.videolan.vlc.gui.audio.AudioAlbumsSongsFragment r10 = new org.videolan.vlc.gui.audio.AudioAlbumsSongsFragment
            r10.<init>()
            kotlin.Pair[] r0 = new kotlin.Pair[r1]
            android.content.Intent r1 = r9.getIntent()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            int r2 = android.os.Build.VERSION.SDK_INT
            java.lang.String r5 = "ML_ITEM"
            if (r2 < r4) goto L_0x00cd
            java.lang.Class<android.os.Parcelable> r2 = android.os.Parcelable.class
            java.lang.Object r1 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.content.Intent) r1, (java.lang.String) r5, (java.lang.Class) r2)
            r7 = r1
            android.os.Parcelable r7 = (android.os.Parcelable) r7
            goto L_0x00d7
        L_0x00cd:
            android.os.Parcelable r1 = r1.getParcelableExtra(r5)
            boolean r2 = r1 instanceof android.os.Parcelable
            if (r2 != 0) goto L_0x00d6
            goto L_0x00d7
        L_0x00d6:
            r7 = r1
        L_0x00d7:
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r7)
            r0[r6] = r1
            android.content.Intent r1 = r9.getIntent()
            java.lang.String r2 = "ARTIST_FROM_ALBUM"
            boolean r1 = r1.getBooleanExtra(r2, r6)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r0[r3] = r1
            android.os.Bundle r0 = androidx.core.os.BundleKt.bundleOf(r0)
            r10.setArguments(r0)
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            r9.fragment = r10
            goto L_0x0190
        L_0x00fe:
            boolean r0 = r10.equals(r2)
            if (r0 == 0) goto L_0x0191
        L_0x0104:
            org.videolan.vlc.gui.browser.MLStorageBrowserFragment$Companion r0 = org.videolan.vlc.gui.browser.MLStorageBrowserFragment.Companion
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r2)
            org.videolan.vlc.gui.browser.MLStorageBrowserFragment r10 = r0.newInstance(r10)
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            r9.fragment = r10
            r10 = 3
            r9.setResult(r10)
            goto L_0x0190
        L_0x0118:
            java.lang.String r0 = "videoGroupList"
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0191
            org.videolan.vlc.gui.video.VideoGridFragment r10 = new org.videolan.vlc.gui.video.VideoGridFragment
            r10.<init>()
            kotlin.Pair[] r0 = new kotlin.Pair[r1]
            android.content.Intent r1 = r9.getIntent()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            int r2 = android.os.Build.VERSION.SDK_INT
            java.lang.String r8 = "key_folder"
            if (r2 < r4) goto L_0x013d
            java.lang.Class<android.os.Parcelable> r2 = android.os.Parcelable.class
            java.lang.Object r1 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.content.Intent) r1, (java.lang.String) r8, (java.lang.Class) r2)
            android.os.Parcelable r1 = (android.os.Parcelable) r1
            goto L_0x0146
        L_0x013d:
            android.os.Parcelable r1 = r1.getParcelableExtra(r8)
            boolean r2 = r1 instanceof android.os.Parcelable
            if (r2 != 0) goto L_0x0146
            r1 = r7
        L_0x0146:
            kotlin.Pair r1 = kotlin.TuplesKt.to(r8, r1)
            r0[r6] = r1
            android.content.Intent r1 = r9.getIntent()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            int r2 = android.os.Build.VERSION.SDK_INT
            java.lang.String r5 = "key_group"
            if (r2 < r4) goto L_0x0163
            java.lang.Class<android.os.Parcelable> r2 = android.os.Parcelable.class
            java.lang.Object r1 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.content.Intent) r1, (java.lang.String) r5, (java.lang.Class) r2)
            r7 = r1
            android.os.Parcelable r7 = (android.os.Parcelable) r7
            goto L_0x016d
        L_0x0163:
            android.os.Parcelable r1 = r1.getParcelableExtra(r5)
            boolean r2 = r1 instanceof android.os.Parcelable
            if (r2 != 0) goto L_0x016c
            goto L_0x016d
        L_0x016c:
            r7 = r1
        L_0x016d:
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r7)
            r0[r3] = r1
            android.os.Bundle r0 = androidx.core.os.BundleKt.bundleOf(r0)
            r10.setArguments(r0)
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            r9.fragment = r10
            goto L_0x0190
        L_0x017f:
            java.lang.String r0 = "streams"
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0191
            org.videolan.vlc.gui.network.MRLPanelFragment r10 = new org.videolan.vlc.gui.network.MRLPanelFragment
            r10.<init>()
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            r9.fragment = r10
        L_0x0190:
            return
        L_0x0191:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Wrong fragment id."
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.SecondaryActivity.fetchSecondaryFragment(java.lang.String):void");
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/SecondaryActivity$Companion;", "", "()V", "ABOUT", "", "ACTIVITY_RESULT_SECONDARY", "", "ALBUMS_SONGS", "FILE_BROWSER", "HISTORY", "KEY_FRAGMENT", "STORAGE_BROWSER", "STORAGE_BROWSER_ONBOARDING", "STREAMS", "TAG", "VIDEO_GROUP_LIST", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SecondaryActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
