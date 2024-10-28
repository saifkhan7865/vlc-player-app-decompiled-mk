package org.videolan.mobile.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.LibVLCFactory;
import org.videolan.libvlc.MediaFactory;
import org.videolan.libvlc.interfaces.ILibVLCFactory;
import org.videolan.libvlc.interfaces.IMediaFactory;
import org.videolan.mobile.app.delegates.IIndexersDelegate;
import org.videolan.mobile.app.delegates.IMediaContentDelegate;
import org.videolan.mobile.app.delegates.IndexersDelegate;
import org.videolan.mobile.app.delegates.MediaContentDelegate;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.AppScope;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.helpers.NotificationHelper;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002J\f\u0010\f\u001a\u00020\r*\u00020\u000eH\u0017J\r\u0010\u000f\u001a\u00020\r*\u00020\u000bH\u0001J\r\u0010\u0010\u001a\u00020\r*\u00020\u000bH\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lorg/videolan/mobile/app/AppSetupDelegate;", "Lorg/videolan/mobile/app/AppDelegate;", "Lorg/videolan/mobile/app/delegates/IMediaContentDelegate;", "Lorg/videolan/mobile/app/delegates/IIndexersDelegate;", "()V", "appContextProvider", "Lorg/videolan/resources/AppContextProvider;", "getAppContextProvider", "()Lorg/videolan/resources/AppContextProvider;", "backgroundInit", "Lkotlinx/coroutines/Job;", "Landroid/content/Context;", "setupApplication", "", "Landroid/app/Application;", "setupContentResolvers", "setupIndexers", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AppSetupDelegate.kt */
public final class AppSetupDelegate implements AppDelegate, IMediaContentDelegate, IIndexersDelegate {
    private final /* synthetic */ MediaContentDelegate $$delegate_0 = new MediaContentDelegate();
    private final /* synthetic */ IndexersDelegate $$delegate_1 = new IndexersDelegate();
    private final AppContextProvider appContextProvider = AppContextProvider.INSTANCE;

    public void setupContentResolvers(Context context) {
        this.$$delegate_0.setupContentResolvers(context);
    }

    public void setupIndexers(Context context) {
        this.$$delegate_1.setupIndexers(context);
    }

    public AppContextProvider getAppContextProvider() {
        return this.appContextProvider;
    }

    public void setupApplication(Application application) {
        Context context = application;
        getAppContextProvider().init(context);
        NotificationHelper.INSTANCE.createNotificationChannels(context);
        FactoryManager.registerFactory(IMediaFactory.factoryId, new MediaFactory());
        FactoryManager.registerFactory(ILibVLCFactory.factoryId, new LibVLCFactory());
        System.setProperty(DebugKt.DEBUG_PROPERTY_NAME, DebugKt.DEBUG_PROPERTY_VALUE_ON);
        AppContextProvider.INSTANCE.setLocale(((SharedPreferences) Settings.INSTANCE.getInstance(application)).getString("set_locale", ""));
        backgroundInit(context);
        if (((SharedPreferences) Settings.INSTANCE.getInstance(application)).getBoolean(SettingsKt.KEY_ENABLE_REMOTE_ACCESS, false)) {
            ExtensionsKt.startRemoteAccess(context);
        }
        application.registerActivityLifecycleCallbacks(new AppSetupDelegate$setupApplication$1());
    }

    private final Job backgroundInit(Context context) {
        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new AppSetupDelegate$backgroundInit$1(context, this, (Continuation<? super AppSetupDelegate$backgroundInit$1>) null), 3, (Object) null);
    }
}
