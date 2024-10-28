package org.videolan.vlc.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.BaseContextWrappingDelegate;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.window.layout.WindowLayoutInfo;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.KeyHelper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.gui.helpers.hf.PinCodeDelegate;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u000f\u0010%\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020\"H\u0016J\b\u0010(\u001a\u00020\u0004H\u0016J\u0014\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010+\u001a\u00020\bH&J\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0018\u00100\u001a\u00020\b2\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u000203H\u0016J\u0018\u00104\u001a\u00020\b2\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u000203H\u0016J\u0010\u00105\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0016J\u0012\u00108\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0012\u00109\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u0010\u0010<\u001a\u00020-2\u0006\u0010=\u001a\u00020>H\u0016J\u0010\u0010?\u001a\u00020-2\u0006\u0010=\u001a\u00020>H\u0016J\u0010\u0010@\u001a\u00020-2\u0006\u0010A\u001a\u00020BH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\n\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00120\u00120\u0011X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006C"}, d2 = {"Lorg/videolan/vlc/gui/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "baseContextWrappingDelegate", "Landroidx/appcompat/app/AppCompatDelegate;", "currentNightMode", "", "displayTitle", "", "getDisplayTitle", "()Z", "isOTPActivity", "setOTPActivity", "(Z)V", "lastDisplayedOTPCode", "", "resultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "settings", "Landroid/content/SharedPreferences;", "getSettings", "()Landroid/content/SharedPreferences;", "setSettings", "(Landroid/content/SharedPreferences;)V", "startColor", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "getWindowLayoutInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "setWindowLayoutInfo", "(Landroidx/window/layout/WindowLayoutInfo;)V", "createConfigurationContext", "Landroid/content/Context;", "overrideConfiguration", "Landroid/content/res/Configuration;", "forcedTheme", "()Ljava/lang/Integer;", "getApplicationContext", "getDelegate", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onKeyUp", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPostCreate", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onSupportActionModeFinished", "mode", "Landroidx/appcompat/view/ActionMode;", "onSupportActionModeStarted", "openFile", "pickerInitialUri", "Landroid/net/Uri;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseActivity.kt */
public abstract class BaseActivity extends AppCompatActivity {
    private AppCompatDelegate baseContextWrappingDelegate;
    private int currentNightMode;
    private final boolean displayTitle;
    private boolean isOTPActivity;
    /* access modifiers changed from: private */
    public String lastDisplayedOTPCode = "";
    private ActivityResultLauncher<Intent> resultLauncher;
    public SharedPreferences settings;
    private int startColor;
    private WindowLayoutInfo windowLayoutInfo;

    public Integer forcedTheme() {
        return null;
    }

    public abstract View getSnackAnchorView(boolean z);

    public BaseActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new BaseActivity$$ExternalSyntheticLambda1());
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.resultLauncher = registerForActivityResult;
    }

    public final SharedPreferences getSettings() {
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    public final void setSettings(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.settings = sharedPreferences;
    }

    public final WindowLayoutInfo getWindowLayoutInfo() {
        return this.windowLayoutInfo;
    }

    public final void setWindowLayoutInfo(WindowLayoutInfo windowLayoutInfo2) {
        this.windowLayoutInfo = windowLayoutInfo2;
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    public boolean isOTPActivity() {
        return this.isOTPActivity;
    }

    public void setOTPActivity(boolean z) {
        this.isOTPActivity = z;
    }

    public static /* synthetic */ View getSnackAnchorView$default(BaseActivity baseActivity, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = false;
            }
            return baseActivity.getSnackAnchorView(z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSnackAnchorView");
    }

    /* access modifiers changed from: private */
    public static final void resultLauncher$lambda$1(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            FileUtils fileUtils = FileUtils.INSTANCE;
            Intent data = activityResult.getData();
            Uri uri = fileUtils.getUri(data != null ? data.getData() : null);
            if (uri != null) {
                MediaUtils.INSTANCE.openMediaNoUi(uri);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setSettings((SharedPreferences) Settings.INSTANCE.getInstance(this));
        UiToolsKt.applyTheme(this);
        super.onCreate(bundle);
        if (UiTools.INSTANCE.getCurrentNightMode() != (getResources().getConfiguration().uiMode & 48)) {
            UiTools.INSTANCE.invalidateBitmaps();
            UiTools.INSTANCE.setCurrentNightMode(getResources().getConfiguration().uiMode & 48);
        }
        LifecycleOwner lifecycleOwner = this;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), Dispatchers.getMain(), (CoroutineStart) null, new BaseActivity$onCreate$1(this, (Continuation<? super BaseActivity$onCreate$1>) null), 2, (Object) null);
        PinCodeDelegate.Companion.getPinUnlocked().observe(lifecycleOwner, new BaseActivity$sam$androidx_lifecycle_Observer$0(new BaseActivity$onCreate$2(this)));
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new BaseActivity$onCreate$3(this, (Continuation<? super BaseActivity$onCreate$3>) null), 3, (Object) null);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = null;
        MenuItem findItem = menu != null ? menu.findItem(R.id.pin_relocked) : null;
        boolean z = true;
        if (findItem != null) {
            findItem.setVisible(Settings.INSTANCE.getSafeMode() && Intrinsics.areEqual((Object) PinCodeDelegate.Companion.getPinUnlocked().getValue(), (Object) true));
        }
        if (menu != null) {
            menuItem = menu.findItem(R.id.pin_unlock);
        }
        if (menuItem != null) {
            if (!Settings.INSTANCE.getSafeMode() || !Intrinsics.areEqual((Object) PinCodeDelegate.Companion.getPinUnlocked().getValue(), (Object) false)) {
                z = false;
            }
            menuItem.setVisible(z);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == R.id.pin_relocked) {
            PinCodeDelegate.Companion.getPinUnlocked().postValue(false);
            UiTools.snacker$default(UiTools.INSTANCE, this, R.string.safe_mode_enabled, false, 4, (Object) null);
            return true;
        } else if (menuItem.getItemId() != R.id.pin_unlock) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseActivity$onOptionsItemSelected$1(this, (Continuation<? super BaseActivity$onOptionsItemSelected$1>) null), 3, (Object) null);
            return true;
        }
    }

    public final void openFile(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "pickerInitialUri");
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        intent.putExtra("android.provider.extra.INITIAL_URI", uri);
        this.resultLauncher.launch(intent);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (getDisplayTitle()) {
            KotlinExtensionsKt.setGone(findViewById(R.id.toolbar_icon));
            KotlinExtensionsKt.setGone(findViewById(R.id.toolbar_vlc_title));
        }
    }

    public AppCompatDelegate getDelegate() {
        AppCompatDelegate appCompatDelegate = this.baseContextWrappingDelegate;
        if (appCompatDelegate != null) {
            return appCompatDelegate;
        }
        AppCompatDelegate delegate = super.getDelegate();
        Intrinsics.checkNotNullExpressionValue(delegate, "getDelegate(...)");
        AppCompatDelegate baseContextWrappingDelegate2 = new BaseContextWrappingDelegate(delegate);
        this.baseContextWrappingDelegate = baseContextWrappingDelegate2;
        return baseContextWrappingDelegate2;
    }

    public Context createConfigurationContext(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "overrideConfiguration");
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        Intrinsics.checkNotNullExpressionValue(createConfigurationContext, "createConfigurationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(createConfigurationContext, AppContextProvider.INSTANCE.getLocale());
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        KeyHelper.INSTANCE.manageModifiers(keyEvent);
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        KeyHelper.INSTANCE.manageModifiers(keyEvent);
        return super.onKeyUp(i, keyEvent);
    }

    public void onSupportActionModeStarted(ActionMode actionMode) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        if (Build.VERSION.SDK_INT >= 21) {
            this.startColor = getWindow().getStatusBarColor();
            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.actionModeBackground, typedValue, true);
            getWindow().setStatusBarColor(typedValue.data);
        }
        super.onSupportActionModeStarted(actionMode);
    }

    public void onSupportActionModeFinished(ActionMode actionMode) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(this.startColor);
        }
        super.onSupportActionModeFinished(actionMode);
    }
}
