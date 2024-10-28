package androidx.car.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.util.Log;
import androidx.car.app.ICarApp;
import androidx.car.app.navigation.NavigationManager;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.car.app.validation.HostValidator;
import androidx.car.app.versioning.CarAppApiLevels;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import j$.util.Objects;
import java.security.InvalidParameterException;

final class CarAppBinder extends ICarApp.Stub {
    private Session mCurrentSession;
    private final SessionInfo mCurrentSessionInfo;
    private HandshakeInfo mHandshakeInfo;
    private HostValidator mHostValidator;
    private CarAppService mService;

    CarAppBinder(CarAppService carAppService, SessionInfo sessionInfo) {
        this.mService = carAppService;
        this.mCurrentSessionInfo = sessionInfo;
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        onDestroyLifecycle();
        this.mCurrentSession = null;
        this.mHostValidator = null;
        this.mHandshakeInfo = null;
        this.mService = null;
    }

    /* access modifiers changed from: package-private */
    public void onDestroyLifecycle() {
        Session session = this.mCurrentSession;
        if (session != null) {
            session.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        }
        this.mCurrentSession = null;
    }

    public void onAppCreate(ICarHost iCarHost, Intent intent, Configuration configuration, IOnDoneCallback iOnDoneCallback) {
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "onAppCreate intent: " + intent);
        }
        RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onAppCreate", (RemoteUtils.HostCall) new CarAppBinder$$ExternalSyntheticLambda4(this, iCarHost, configuration, intent));
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "onAppCreate completed");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAppCreate$0$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ Object m10lambda$onAppCreate$0$androidxcarappCarAppBinder(ICarHost iCarHost, Configuration configuration, Intent intent) throws BundlerException {
        CarAppService carAppService = (CarAppService) Objects.requireNonNull(this.mService);
        Session session = this.mCurrentSession;
        if (session == null || session.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            session = carAppService.onCreateSession((SessionInfo) Objects.requireNonNull(this.mCurrentSessionInfo));
            this.mCurrentSession = session;
        }
        session.configure(carAppService, (HandshakeInfo) Objects.requireNonNull(getHandshakeInfo()), (HostInfo) Objects.requireNonNull(carAppService.getHostInfo()), iCarHost, configuration);
        LifecycleRegistry lifecycleRegistry = (LifecycleRegistry) session.getLifecycle();
        Lifecycle.State currentState = lifecycleRegistry.getCurrentState();
        int size = ((ScreenManager) session.getCarContext().getCarService(ScreenManager.class)).getScreenStack().size();
        if (!currentState.isAtLeast(Lifecycle.State.CREATED) || size < 1) {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                Log.d(LogTags.TAG, "onAppCreate the app was not yet created or the screen stack was empty state: " + lifecycleRegistry.getCurrentState() + ", stack size: " + size);
            }
            session.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
            ((ScreenManager) session.getCarContext().getCarService(ScreenManager.class)).push(session.onCreateScreen(intent));
            return null;
        }
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "onAppCreate the app was already created");
        }
        onNewIntentInternal(session, intent);
        return null;
    }

    public void onAppStart(IOnDoneCallback iOnDoneCallback) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), iOnDoneCallback, "onAppStart", new CarAppBinder$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAppStart$1$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ Object m13lambda$onAppStart$1$androidxcarappCarAppBinder() throws BundlerException {
        ((Session) Objects.requireNonNull(this.mCurrentSession)).handleLifecycleEvent(Lifecycle.Event.ON_START);
        return null;
    }

    public void onAppResume(IOnDoneCallback iOnDoneCallback) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), iOnDoneCallback, "onAppResume", new CarAppBinder$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAppResume$2$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ Object m12lambda$onAppResume$2$androidxcarappCarAppBinder() throws BundlerException {
        ((Session) Objects.requireNonNull(this.mCurrentSession)).handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        return null;
    }

    public void onAppPause(IOnDoneCallback iOnDoneCallback) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), iOnDoneCallback, "onAppPause", new CarAppBinder$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAppPause$3$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ Object m11lambda$onAppPause$3$androidxcarappCarAppBinder() throws BundlerException {
        ((Session) Objects.requireNonNull(this.mCurrentSession)).handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        return null;
    }

    public void onAppStop(IOnDoneCallback iOnDoneCallback) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), iOnDoneCallback, "onAppStop", new CarAppBinder$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAppStop$4$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ Object m14lambda$onAppStop$4$androidxcarappCarAppBinder() throws BundlerException {
        ((Session) Objects.requireNonNull(this.mCurrentSession)).handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        return null;
    }

    public void onNewIntent(Intent intent, IOnDoneCallback iOnDoneCallback) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), iOnDoneCallback, "onNewIntent", new CarAppBinder$$ExternalSyntheticLambda6(this, intent));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onNewIntent$5$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ Object m16lambda$onNewIntent$5$androidxcarappCarAppBinder(Intent intent) throws BundlerException {
        onNewIntentInternal((Session) Objects.requireNonNull(this.mCurrentSession), intent);
        return null;
    }

    public void onConfigurationChanged(Configuration configuration, IOnDoneCallback iOnDoneCallback) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), iOnDoneCallback, "onConfigurationChanged", new CarAppBinder$$ExternalSyntheticLambda0(this, configuration));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onConfigurationChanged$6$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ Object m15lambda$onConfigurationChanged$6$androidxcarappCarAppBinder(Configuration configuration) throws BundlerException {
        onConfigurationChangedInternal((Session) Objects.requireNonNull(this.mCurrentSession), configuration);
        return null;
    }

    public void getManager(String str, IOnDoneCallback iOnDoneCallback) {
        ThreadUtils.runOnMain(new CarAppBinder$$ExternalSyntheticLambda5(this, str, iOnDoneCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getManager$7$androidx-car-app-CarAppBinder  reason: not valid java name */
    public /* synthetic */ void m9lambda$getManager$7$androidxcarappCarAppBinder(String str, IOnDoneCallback iOnDoneCallback) {
        Session session = (Session) Objects.requireNonNull(this.mCurrentSession);
        str.hashCode();
        if (str.equals(CarContext.APP_SERVICE)) {
            RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "getManager", ((AppManager) session.getCarContext().getCarService(AppManager.class)).getIInterface());
        } else if (!str.equals("navigation")) {
            Log.e(LogTags.TAG, str + "%s is not a valid manager");
            RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "getManager", new InvalidParameterException(str + " is not a valid manager type"));
        } else {
            RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "getManager", ((NavigationManager) session.getCarContext().getCarService(NavigationManager.class)).getIInterface());
        }
    }

    public void getAppInfo(IOnDoneCallback iOnDoneCallback) {
        try {
            RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "getAppInfo", ((CarAppService) Objects.requireNonNull(this.mService)).getAppInfo());
        } catch (IllegalArgumentException e) {
            RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "getAppInfo", e);
        }
    }

    public void onHandshakeCompleted(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) {
        CarAppService carAppService = (CarAppService) Objects.requireNonNull(this.mService);
        try {
            HandshakeInfo handshakeInfo = (HandshakeInfo) bundleable.get();
            String hostPackageName = handshakeInfo.getHostPackageName();
            int callingUid = Binder.getCallingUid();
            HostInfo hostInfo = new HostInfo(hostPackageName, callingUid);
            if (!getHostValidator().isValidHost(hostInfo)) {
                RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "onHandshakeCompleted", new IllegalArgumentException("Unknown host '" + hostPackageName + "', uid:" + callingUid));
                return;
            }
            AppInfo appInfo = carAppService.getAppInfo();
            int minCarAppApiLevel = appInfo.getMinCarAppApiLevel();
            int latestCarAppApiLevel = appInfo.getLatestCarAppApiLevel();
            int hostCarAppApiLevel = handshakeInfo.getHostCarAppApiLevel();
            if (minCarAppApiLevel > hostCarAppApiLevel) {
                RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "onHandshakeCompleted", new IllegalArgumentException("Host API level (" + hostCarAppApiLevel + ") is less than the app's min API level (" + minCarAppApiLevel + ")"));
            } else if (latestCarAppApiLevel < hostCarAppApiLevel) {
                RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "onHandshakeCompleted", new IllegalArgumentException("Host API level (" + hostCarAppApiLevel + ") is greater than the app's max API level (" + latestCarAppApiLevel + ")"));
            } else {
                carAppService.setHostInfo(hostInfo);
                this.mHandshakeInfo = handshakeInfo;
                RemoteUtils.sendSuccessResponseToHost(iOnDoneCallback, "onHandshakeCompleted", (Object) null);
            }
        } catch (BundlerException | IllegalArgumentException e) {
            carAppService.setHostInfo((HostInfo) null);
            RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "onHandshakeCompleted", e);
        }
    }

    private Lifecycle getCurrentLifecycle() {
        Session session = this.mCurrentSession;
        if (session == null) {
            return null;
        }
        return session.getLifecycle();
    }

    private HostValidator getHostValidator() {
        if (this.mHostValidator == null) {
            this.mHostValidator = ((CarAppService) Objects.requireNonNull(this.mService)).createHostValidator();
        }
        return this.mHostValidator;
    }

    private void onNewIntentInternal(Session session, Intent intent) {
        ThreadUtils.checkMainThread();
        session.onNewIntent(intent);
    }

    private void onConfigurationChangedInternal(Session session, Configuration configuration) {
        ThreadUtils.checkMainThread();
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "onCarConfigurationChanged configuration: " + configuration);
        }
        session.onCarConfigurationChangedInternal(configuration);
    }

    /* access modifiers changed from: package-private */
    public void onAutoDriveEnabled() {
        Session session = this.mCurrentSession;
        if (session != null) {
            ((NavigationManager) session.getCarContext().getCarService(NavigationManager.class)).onAutoDriveEnabled();
        }
    }

    /* access modifiers changed from: package-private */
    public void setHandshakeInfo(HandshakeInfo handshakeInfo) {
        int hostCarAppApiLevel = handshakeInfo.getHostCarAppApiLevel();
        if (CarAppApiLevels.isValid(hostCarAppApiLevel)) {
            this.mHandshakeInfo = handshakeInfo;
            return;
        }
        throw new IllegalArgumentException("Invalid Car App API level received: " + hostCarAppApiLevel);
    }

    /* access modifiers changed from: package-private */
    public HandshakeInfo getHandshakeInfo() {
        return this.mHandshakeInfo;
    }

    /* access modifiers changed from: package-private */
    public Session getCurrentSession() {
        return this.mCurrentSession;
    }

    /* access modifiers changed from: package-private */
    public SessionInfo getCurrentSessionInfo() {
        return this.mCurrentSessionInfo;
    }
}
