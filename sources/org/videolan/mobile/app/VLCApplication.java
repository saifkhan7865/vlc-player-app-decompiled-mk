package org.videolan.mobile.app;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.BitmapCache;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.util.DialogDelegate;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0019\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fH\u0001J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\nH\u0017J\u0019\u0010\u0012\u001a\u00020\n2\u000e\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\u00130\u0013H\u0001J\u0019\u0010\u0012\u001a\u00020\n2\u000e\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\u00140\u0014H\u0001J\u0019\u0010\u0012\u001a\u00020\n2\u000e\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\u00150\u0015H\u0001J\u0019\u0010\u0012\u001a\u00020\n2\u000e\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\u00160\u0016H\u0001J\b\u0010\u0017\u001a\u00020\nH\u0016J\u0019\u0010\u0018\u001a\u00020\n2\u000e\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\u00150\u0015H\u0001J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\r\u0010\u001c\u001a\u00020\n*\u00020\u001dH\u0001R\u0012\u0010\u0005\u001a\u00020\u0006X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u001e"}, d2 = {"Lorg/videolan/mobile/app/VLCApplication;", "Landroidx/multidex/MultiDexApplication;", "Lorg/videolan/libvlc/Dialog$Callbacks;", "Lorg/videolan/mobile/app/AppDelegate;", "()V", "appContextProvider", "Lorg/videolan/resources/AppContextProvider;", "getAppContextProvider", "()Lorg/videolan/resources/AppContextProvider;", "onCanceled", "", "p0", "Lorg/videolan/libvlc/Dialog;", "kotlin.jvm.PlatformType", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "onDisplay", "Lorg/videolan/libvlc/Dialog$ErrorMessage;", "Lorg/videolan/libvlc/Dialog$LoginDialog;", "Lorg/videolan/libvlc/Dialog$ProgressDialog;", "Lorg/videolan/libvlc/Dialog$QuestionDialog;", "onLowMemory", "onProgressUpdate", "onTrimMemory", "level", "", "setupApplication", "Landroid/app/Application;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCApplication.kt */
public final class VLCApplication extends MultiDexApplication implements Dialog.Callbacks, AppDelegate {
    private final /* synthetic */ DialogDelegate.DialogsListener $$delegate_0 = DialogDelegate.DialogsListener;
    private final /* synthetic */ AppSetupDelegate $$delegate_1 = new AppSetupDelegate();

    public AppContextProvider getAppContextProvider() {
        return this.$$delegate_1.getAppContextProvider();
    }

    public void onCanceled(Dialog dialog) {
        this.$$delegate_0.onCanceled(dialog);
    }

    public void onDisplay(Dialog.ErrorMessage errorMessage) {
        DialogDelegate.DialogsListener dialogsListener = this.$$delegate_0;
        Intrinsics.checkNotNull(errorMessage);
        dialogsListener.onDisplay(errorMessage);
    }

    public void onDisplay(Dialog.LoginDialog loginDialog) {
        DialogDelegate.DialogsListener dialogsListener = this.$$delegate_0;
        Intrinsics.checkNotNull(loginDialog);
        dialogsListener.onDisplay(loginDialog);
    }

    public void onDisplay(Dialog.ProgressDialog progressDialog) {
        DialogDelegate.DialogsListener dialogsListener = this.$$delegate_0;
        Intrinsics.checkNotNull(progressDialog);
        dialogsListener.onDisplay(progressDialog);
    }

    public void onDisplay(Dialog.QuestionDialog questionDialog) {
        DialogDelegate.DialogsListener dialogsListener = this.$$delegate_0;
        Intrinsics.checkNotNull(questionDialog);
        dialogsListener.onDisplay(questionDialog);
    }

    public void onProgressUpdate(Dialog.ProgressDialog progressDialog) {
        DialogDelegate.DialogsListener dialogsListener = this.$$delegate_0;
        Intrinsics.checkNotNull(progressDialog);
        dialogsListener.onProgressUpdate(progressDialog);
    }

    public void setupApplication(Application application) {
        this.$$delegate_1.setupApplication(application);
    }

    public VLCApplication() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void onCreate() {
        setupApplication(this);
        super.onCreate();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getAppContextProvider().updateContext();
    }

    public void onLowMemory() {
        super.onLowMemory();
        Log.w("VLC/VLCApplication", "System is running low on memory");
        BitmapCache.INSTANCE.clear();
        ArtworkProvider.Companion.clear();
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Log.w("VLC/VLCApplication", "onTrimMemory, level: " + i);
        BitmapCache.INSTANCE.clear();
        ArtworkProvider.Companion.clear();
    }
}
