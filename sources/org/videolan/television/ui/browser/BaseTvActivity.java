package org.videolan.television.ui.browser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.television.ui.SearchActivity;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.KeyHelper;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.ExternalMonitor;
import org.videolan.vlc.MediaParsingService;
import org.videolan.vlc.ScanProgress;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.util.DialogDelegate;
import org.videolan.vlc.util.IDialogManager;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\"\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0012\u0010\u001b\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0018\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010#\u001a\u00020\rH\u0014J\u0012\u0010$\u001a\u00020\r2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010'\u001a\u00020\rH\u0014J\b\u0010(\u001a\u00020\rH\u0014J\b\u0010)\u001a\u00020\rH\u0014J\b\u0010*\u001a\u00020\rH$J\b\u0010+\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/videolan/television/ui/browser/BaseTvActivity;", "Landroidx/fragment/app/FragmentActivity;", "Lorg/videolan/vlc/util/IDialogManager;", "()V", "currentlyVisible", "", "dialogsDelegate", "Lorg/videolan/vlc/util/DialogDelegate;", "mediaLibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "settings", "Landroid/content/SharedPreferences;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "dialogCanceled", "dialog", "Lorg/videolan/libvlc/Dialog;", "fireDialog", "getApplicationContext", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onKeyUp", "onParsingServiceFinished", "onParsingServiceProgress", "scanProgress", "Lorg/videolan/vlc/ScanProgress;", "onParsingServiceStarted", "onStart", "onStop", "refresh", "registerLiveData", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseTvActivity.kt */
public abstract class BaseTvActivity extends FragmentActivity implements IDialogManager {
    private volatile boolean currentlyVisible;
    private final DialogDelegate dialogsDelegate = new DialogDelegate();
    private Medialibrary mediaLibrary;
    private SharedPreferences settings;

    /* access modifiers changed from: protected */
    public void onParsingServiceFinished() {
    }

    /* access modifiers changed from: protected */
    public void onParsingServiceProgress(ScanProgress scanProgress) {
    }

    /* access modifiers changed from: protected */
    public void onParsingServiceStarted() {
    }

    /* access modifiers changed from: protected */
    public abstract void refresh();

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context != null ? LocaleUtilsKt.getContextWithLocale(context, AppContextProvider.INSTANCE.getLocale()) : null);
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            ExtensionsKt.startMedialibrary$default(this, false, false, true, false, (CoroutineContextProvider) null, 24, (Object) null);
        }
        super.onCreate(bundle);
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.mediaLibrary = instance;
        this.settings = (SharedPreferences) Settings.INSTANCE.getInstance(this);
        registerLiveData();
        this.dialogsDelegate.observeDialogs(this, this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        ExternalMonitor.INSTANCE.subscribeStorageCb(this);
        super.onStart();
        this.currentlyVisible = true;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.currentlyVisible = false;
        ExternalMonitor.INSTANCE.unsubscribeStorageCb(this);
        super.onStop();
    }

    public void fireDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogActivity.Companion.setDialog(dialog);
        startActivity(new Intent(DialogActivity.KEY_DIALOG, (Uri) null, this, DialogActivity.class));
    }

    public void dialogCanceled(Dialog dialog) {
        if (dialog instanceof Dialog.ErrorMessage) {
            View decorView = getWindow().getDecorView();
            StringBuilder sb = new StringBuilder();
            Dialog.ErrorMessage errorMessage = (Dialog.ErrorMessage) dialog;
            sb.append(errorMessage.getTitle());
            sb.append(": ");
            sb.append(errorMessage.getText());
            Snackbar.make(decorView, (CharSequence) sb.toString(), 0).show();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        KeyHelper.INSTANCE.manageModifiers(keyEvent);
        if (i != 84) {
            return super.onKeyDown(i, keyEvent);
        }
        startActivity(new Intent(this, SearchActivity.class));
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        KeyHelper.INSTANCE.manageModifiers(keyEvent);
        return super.onKeyUp(i, keyEvent);
    }

    private final void registerLiveData() {
        LifecycleOwner lifecycleOwner = this;
        MediaParsingService.Companion.getProgress().observe(lifecycleOwner, new BaseTvActivityKt$sam$androidx_lifecycle_Observer$0(new BaseTvActivity$registerLiveData$1(this)));
        Medialibrary.getState().observe(lifecycleOwner, new BaseTvActivityKt$sam$androidx_lifecycle_Observer$0(new BaseTvActivity$registerLiveData$2(this)));
        MediaParsingService.Companion.getNewStorages().observe(lifecycleOwner, new BaseTvActivityKt$sam$androidx_lifecycle_Observer$0(new BaseTvActivity$registerLiveData$3(this)));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 100) {
            if (i == 101) {
                Process.killProcess(Process.myPid());
            }
        } else if (i2 == 2) {
            finish();
        } else {
            try {
                ComponentName componentName = new ComponentName("com.android.tv.settings", "com.android.tv.settings.connectivity.NetworkActivity");
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setFlags(270532608);
                intent2.setComponent(componentName);
                startActivity(intent2);
            } catch (Exception unused) {
                startActivity(new Intent("android.settings.SETTINGS"));
            }
        }
        super.onActivityResult(i, i2, intent);
    }
}
