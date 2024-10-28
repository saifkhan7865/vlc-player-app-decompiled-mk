package org.videolan.vlc.gui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.core.os.BundleKt;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.AppBarLayout;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.PinCodeActivity;
import org.videolan.vlc.gui.PinCodeReason;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;
import org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004J\u0006\u0010\u0012\u001a\u00020\u0010J\r\u0010\u0013\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\"\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0014J\u0012\u0010\u001c\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$H\u0016J\u0006\u0010%\u001a\u00020\u0010J\u0006\u0010&\u001a\u00020\u0010J\u0006\u0010'\u001a\u00020\u0010R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eXD¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "displayTitle", "", "getDisplayTitle", "()Z", "mAppBarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "pinCodeResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "searchRequestCode", "", "detectHeadset", "", "detect", "exitAndRescan", "expandBar", "expandBar$vlc_android_release", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "onActivityResult", "requestCode", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setRestart", "setRestartApp", "updateArtists", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesActivity.kt */
public final class PreferencesActivity extends BaseActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean displayTitle = true;
    private AppBarLayout mAppBarLayout;
    private ActivityResultLauncher<Intent> pinCodeResult;
    private final int searchRequestCode = 167;

    public PreferencesActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new PreferencesActivity$$ExternalSyntheticLambda1(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.pinCodeResult = registerForActivityResult;
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    /* access modifiers changed from: private */
    public static final void pinCodeResult$lambda$0(PreferencesActivity preferencesActivity, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(preferencesActivity, "this$0");
        if (activityResult.getResultCode() != -1) {
            preferencesActivity.finish();
        }
    }

    public View getSnackAnchorView(boolean z) {
        return findViewById(16908290);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        super.onCreate(bundle);
        if (((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean(SettingsKt.KEY_RESTRICT_SETTINGS, false)) {
            this.pinCodeResult.launch(PinCodeActivity.Companion.getIntent(this, PinCodeReason.CHECK));
        }
        setContentView(R.layout.preferences_activity);
        View findViewById = findViewById(R.id.main_toolbar);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.Toolbar");
        setSupportActionBar((Toolbar) findViewById);
        if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            int i = R.id.fragment_placeholder;
            PreferencesFragment preferencesFragment = new PreferencesFragment();
            if (getIntent().hasExtra(PreferencesActivityKt.EXTRA_PREF_END_POINT)) {
                Pair[] pairArr = new Pair[1];
                Intent intent = getIntent();
                Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
                if (Build.VERSION.SDK_INT >= 33) {
                    parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, PreferencesActivityKt.EXTRA_PREF_END_POINT, Parcelable.class);
                } else {
                    parcelable = intent.getParcelableExtra(PreferencesActivityKt.EXTRA_PREF_END_POINT);
                    if (!(parcelable instanceof Parcelable)) {
                        parcelable = null;
                    }
                }
                pairArr[0] = TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, parcelable);
                preferencesFragment.setArguments(BundleKt.bundleOf(pairArr));
            }
            Unit unit = Unit.INSTANCE;
            beginTransaction.replace(i, preferencesFragment).commit();
        }
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        this.mAppBarLayout = appBarLayout;
        Intrinsics.checkNotNull(appBarLayout);
        appBarLayout.post(new PreferencesActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(PreferencesActivity preferencesActivity) {
        Intrinsics.checkNotNullParameter(preferencesActivity, "this$0");
        AppBarLayout appBarLayout = preferencesActivity.mAppBarLayout;
        Intrinsics.checkNotNull(appBarLayout);
        ViewCompat.setElevation(appBarLayout, (float) preferencesActivity.getResources().getDimensionPixelSize(R.dimen.default_appbar_elevation));
    }

    public final void expandBar$vlc_android_release() {
        AppBarLayout appBarLayout = this.mAppBarLayout;
        Intrinsics.checkNotNull(appBarLayout);
        appBarLayout.setExpanded(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.activity_prefs, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId != 16908332) {
            if (itemId == R.id.menu_pref_search) {
                startActivityForResult(new Intent(this, PreferenceSearchActivity.class), this.searchRequestCode);
            }
            return super.onOptionsItemSelected(menuItem);
        } else if (getSupportFragmentManager().popBackStackImmediate()) {
            return true;
        } else {
            finish();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        Parcelable parcelable;
        super.onActivityResult(i, i2, intent);
        if (i == this.searchRequestCode && i2 == -1 && intent != null && (extras = intent.getExtras()) != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(extras, PreferencesActivityKt.EXTRA_PREF_END_POINT, PreferenceItem.class);
            } else {
                Parcelable parcelable2 = extras.getParcelable(PreferencesActivityKt.EXTRA_PREF_END_POINT);
                if (!(parcelable2 instanceof PreferenceItem)) {
                    parcelable2 = null;
                }
                parcelable = (PreferenceItem) parcelable2;
            }
            PreferenceItem preferenceItem = (PreferenceItem) parcelable;
            if (preferenceItem != null) {
                getSupportFragmentManager().popBackStack();
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                int i3 = R.id.fragment_placeholder;
                PreferencesFragment preferencesFragment = new PreferencesFragment();
                preferencesFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                Unit unit = Unit.INSTANCE;
                beginTransaction.replace(i3, preferencesFragment).commit();
            }
        }
    }

    public final void exitAndRescan() {
        setRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public final void setRestart() {
        setResult(3);
    }

    public final void setRestartApp() {
        setResult(4);
    }

    public final void updateArtists() {
        setResult(6);
    }

    public final void detectHeadset(boolean z) {
        LiveEvent<Boolean> headSetDetection = PlaybackService.Companion.getHeadSetDetection();
        if (headSetDetection.hasObservers()) {
            headSetDetection.setValue(Boolean.valueOf(z));
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesActivity$Companion;", "", "()V", "launchWithPref", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "prefKey", "", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PreferencesActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.lang.String} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0065  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object launchWithPref(androidx.fragment.app.FragmentActivity r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
            /*
                r5 = this;
                boolean r0 = r8 instanceof org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$1
                if (r0 == 0) goto L_0x0014
                r0 = r8
                org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$1 r0 = (org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L_0x0019
            L_0x0014:
                org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$1 r0 = new org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$1
                r0.<init>(r5, r8)
            L_0x0019:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x003b
                if (r2 != r3) goto L_0x0033
                java.lang.Object r6 = r0.L$1
                r7 = r6
                java.lang.String r7 = (java.lang.String) r7
                java.lang.Object r6 = r0.L$0
                androidx.fragment.app.FragmentActivity r6 = (androidx.fragment.app.FragmentActivity) r6
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x0059
            L_0x0033:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x003b:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
                kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
                org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$pref$1 r2 = new org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$pref$1
                r4 = 0
                r2.<init>(r6, r4)
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                r0.L$0 = r6
                r0.L$1 = r7
                r0.label = r3
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
                if (r8 != r1) goto L_0x0059
                return r1
            L_0x0059:
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.util.Iterator r8 = r8.iterator()
            L_0x005f:
                boolean r0 = r8.hasNext()
                if (r0 == 0) goto L_0x008c
                java.lang.Object r0 = r8.next()
                org.videolan.vlc.gui.preferences.search.PreferenceItem r0 = (org.videolan.vlc.gui.preferences.search.PreferenceItem) r0
                java.lang.String r1 = r0.getKey()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r7)
                if (r1 == 0) goto L_0x005f
                android.content.Intent r7 = new android.content.Intent
                r8 = r6
                android.content.Context r8 = (android.content.Context) r8
                java.lang.Class<org.videolan.vlc.gui.preferences.PreferencesActivity> r1 = org.videolan.vlc.gui.preferences.PreferencesActivity.class
                r7.<init>(r8, r1)
                java.lang.String r8 = "extra_pref_end_point"
                android.os.Parcelable r0 = (android.os.Parcelable) r0
                r7.putExtra(r8, r0)
                r6.startActivityForResult(r7, r3)
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L_0x008c:
                java.util.NoSuchElementException r6 = new java.util.NoSuchElementException
                java.lang.String r7 = "Collection contains no element matching the predicate."
                r6.<init>(r7)
                goto L_0x0095
            L_0x0094:
                throw r6
            L_0x0095:
                goto L_0x0094
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.PreferencesActivity.Companion.launchWithPref(androidx.fragment.app.FragmentActivity, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
