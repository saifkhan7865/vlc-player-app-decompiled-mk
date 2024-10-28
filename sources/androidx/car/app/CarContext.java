package androidx.car.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Surface;
import androidx.activity.OnBackPressedDispatcher;
import androidx.car.app.IOnRequestPermissionsListener;
import androidx.car.app.IStartCarApp;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.constraints.ConstraintManager;
import androidx.car.app.hardware.CarHardwareManager;
import androidx.car.app.managers.ManagerCache;
import androidx.car.app.managers.ResultManager;
import androidx.car.app.navigation.NavigationManager;
import androidx.car.app.suggestion.SuggestionManager;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class CarContext extends ContextWrapper {
    public static final String ACTION_NAVIGATE = "androidx.car.app.action.NAVIGATE";
    public static final String APP_SERVICE = "app";
    public static final String CAR_SERVICE = "car";
    @RequiresCarApi(2)
    public static final String CONSTRAINT_SERVICE = "constraints";
    static final String EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY = "androidx.car.app.action.EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY";
    static final String EXTRA_PERMISSIONS_KEY = "androidx.car.app.action.EXTRA_PERMISSIONS_KEY";
    public static final String EXTRA_START_CAR_APP_BINDER_KEY = "androidx.car.app.extra.START_CAR_APP_BINDER_KEY";
    @RequiresCarApi(3)
    public static final String HARDWARE_SERVICE = "hardware";
    public static final String NAVIGATION_SERVICE = "navigation";
    static final String REQUEST_PERMISSIONS_ACTION = "androidx.car.app.action.REQUEST_PERMISSIONS";
    public static final String SCREEN_SERVICE = "screen";
    public static final String SUGGESTION_SERVICE = "suggestion";
    private int mCarAppApiLevel = 0;
    private final HostDispatcher mHostDispatcher;
    private HostInfo mHostInfo = null;
    private final Lifecycle mLifecycle;
    private final ManagerCache mManagers;
    private final OnBackPressedDispatcher mOnBackPressedDispatcher;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarServiceType {
    }

    public static CarContext create(Lifecycle lifecycle) {
        return new CarContext(lifecycle, new HostDispatcher());
    }

    public Object getCarService(String str) {
        Objects.requireNonNull(str);
        return this.mManagers.getOrCreate(str);
    }

    public <T> T getCarService(Class<T> cls) {
        Objects.requireNonNull(cls);
        return this.mManagers.getOrCreate(cls);
    }

    public String getCarServiceName(Class<?> cls) {
        Objects.requireNonNull(cls);
        return this.mManagers.getName(cls);
    }

    public void startCarApp(Intent intent) {
        Objects.requireNonNull(intent);
        this.mHostDispatcher.dispatch(CAR_SERVICE, "startCarApp", new CarContext$$ExternalSyntheticLambda8(intent));
    }

    @Deprecated
    public static void startCarApp(Intent intent, Intent intent2) {
        Objects.requireNonNull(intent);
        Objects.requireNonNull(intent2);
        Bundle extras = intent.getExtras();
        IBinder m = extras != null ? extras.getBinder(EXTRA_START_CAR_APP_BINDER_KEY) : null;
        if (m != null) {
            RemoteUtils.dispatchCallToHost("startCarApp from notification", new CarContext$$ExternalSyntheticLambda6((IStartCarApp) Objects.requireNonNull(IStartCarApp.Stub.asInterface(m)), intent2));
            return;
        }
        throw new IllegalArgumentException("Notification intent missing expected extra");
    }

    public void finishCarApp() {
        this.mHostDispatcher.dispatch(CAR_SERVICE, "finish", new CarContext$$ExternalSyntheticLambda7());
    }

    @RequiresCarApi(2)
    public void setCarAppResult(int i, Intent intent) {
        ((ResultManager) getCarService(ResultManager.class)).setCarAppResult(i, intent);
    }

    @RequiresCarApi(2)
    public ComponentName getCallingComponent() {
        try {
            return ((ResultManager) getCarService(ResultManager.class)).getCallingComponent();
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public boolean isDarkMode() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    public OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    public int getCarAppApiLevel() {
        int i = this.mCarAppApiLevel;
        if (i != 0) {
            return i;
        }
        throw new IllegalStateException("Car App API level hasn't been established yet");
    }

    public HostInfo getHostInfo() {
        return this.mHostInfo;
    }

    public void requestPermissions(List<String> list, OnRequestPermissionsListener onRequestPermissionsListener) {
        requestPermissions(list, ContextCompat.getMainExecutor(this), onRequestPermissionsListener);
    }

    public void requestPermissions(List<String> list, final Executor executor, final OnRequestPermissionsListener onRequestPermissionsListener) {
        Objects.requireNonNull(executor);
        Objects.requireNonNull(list);
        Objects.requireNonNull(onRequestPermissionsListener);
        ComponentName componentName = new ComponentName(this, CarAppPermissionActivity.class);
        final Lifecycle lifecycle = this.mLifecycle;
        Bundle bundle = new Bundle(2);
        bundle.putBinder(EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY, new IOnRequestPermissionsListener.Stub() {
            public void onRequestPermissionsResult(String[] strArr, String[] strArr2) {
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    executor.execute(new CarContext$1$$ExternalSyntheticLambda0(onRequestPermissionsListener, Arrays.asList(strArr), Arrays.asList(strArr2)));
                }
            }
        }.asBinder());
        bundle.putStringArray(EXTRA_PERMISSIONS_KEY, (String[]) list.toArray(new String[0]));
        startActivity(new Intent(REQUEST_PERMISSIONS_ACTION).setComponent(componentName).putExtras(bundle).addFlags(268435456));
    }

    public void setCarHost(ICarHost iCarHost) {
        ThreadUtils.checkMainThread();
        this.mHostDispatcher.setCarHost((ICarHost) Objects.requireNonNull(iCarHost));
    }

    /* access modifiers changed from: package-private */
    public void onCarConfigurationChanged(Configuration configuration) {
        ThreadUtils.checkMainThread();
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "Car configuration changed, configuration: " + configuration + ", displayMetrics: " + getResources().getDisplayMetrics());
        }
        getResources().updateConfiguration((Configuration) Objects.requireNonNull(configuration), getResources().getDisplayMetrics());
    }

    public void updateHandshakeInfo(HandshakeInfo handshakeInfo) {
        this.mCarAppApiLevel = handshakeInfo.getHostCarAppApiLevel();
    }

    /* access modifiers changed from: package-private */
    public void updateHostInfo(HostInfo hostInfo) {
        this.mHostInfo = hostInfo;
    }

    /* access modifiers changed from: package-private */
    public void attachBaseContext(Context context, Configuration configuration) {
        ThreadUtils.checkMainThread();
        if (getBaseContext() == null) {
            attachBaseContext(context.createDisplayContext(((DisplayManager) Objects.requireNonNull(context.getSystemService("display"))).createVirtualDisplay("CarAppService", configuration.screenWidthDp, configuration.screenHeightDp, configuration.densityDpi, (Surface) null, 8).getDisplay()).createConfigurationContext(configuration));
        }
        onCarConfigurationChanged(configuration);
    }

    /* access modifiers changed from: package-private */
    public ManagerCache getManagers() {
        return this.mManagers;
    }

    protected CarContext(Lifecycle lifecycle, final HostDispatcher hostDispatcher) {
        super((Context) null);
        ManagerCache managerCache = new ManagerCache();
        this.mManagers = managerCache;
        this.mHostDispatcher = hostDispatcher;
        managerCache.addFactory(AppManager.class, APP_SERVICE, new CarContext$$ExternalSyntheticLambda9(this, hostDispatcher, lifecycle));
        managerCache.addFactory(NavigationManager.class, "navigation", new CarContext$$ExternalSyntheticLambda10(this, hostDispatcher, lifecycle));
        managerCache.addFactory(ScreenManager.class, SCREEN_SERVICE, new CarContext$$ExternalSyntheticLambda11(this, lifecycle));
        managerCache.addFactory(ConstraintManager.class, "constraints", new CarContext$$ExternalSyntheticLambda12(this, hostDispatcher));
        managerCache.addFactory(CarHardwareManager.class, HARDWARE_SERVICE, new CarContext$$ExternalSyntheticLambda13(this, hostDispatcher));
        managerCache.addFactory(ResultManager.class, (String) null, new CarContext$$ExternalSyntheticLambda3(this));
        managerCache.addFactory(SuggestionManager.class, SUGGESTION_SERVICE, new CarContext$$ExternalSyntheticLambda4(this, hostDispatcher, lifecycle));
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new CarContext$$ExternalSyntheticLambda5(this));
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(new DefaultLifecycleObserver() {
            public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
            }

            public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
            }

            public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
            }

            public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onStart(this, lifecycleOwner);
            }

            public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
                DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
            }

            public void onDestroy(LifecycleOwner lifecycleOwner) {
                hostDispatcher.resetHosts();
                lifecycleOwner.getLifecycle().removeObserver(this);
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$3$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ AppManager m21lambda$new$3$androidxcarappCarContext(HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        return AppManager.create(this, hostDispatcher, lifecycle);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$4$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ NavigationManager m22lambda$new$4$androidxcarappCarContext(HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        return NavigationManager.create(this, hostDispatcher, lifecycle);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$5$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ ScreenManager m23lambda$new$5$androidxcarappCarContext(Lifecycle lifecycle) {
        return ScreenManager.create(this, lifecycle);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$6$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ ConstraintManager m24lambda$new$6$androidxcarappCarContext(HostDispatcher hostDispatcher) {
        return ConstraintManager.create(this, hostDispatcher);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$7$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ CarHardwareManager m25lambda$new$7$androidxcarappCarContext(HostDispatcher hostDispatcher) {
        return CarHardwareManager.CC.create(this, hostDispatcher);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$8$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ ResultManager m26lambda$new$8$androidxcarappCarContext() {
        return ResultManager.CC.create(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$9$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ SuggestionManager m27lambda$new$9$androidxcarappCarContext(HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        return SuggestionManager.create(this, hostDispatcher, lifecycle);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$10$androidx-car-app-CarContext  reason: not valid java name */
    public /* synthetic */ void m20lambda$new$10$androidxcarappCarContext() {
        ((ScreenManager) getCarService(ScreenManager.class)).pop();
    }
}
