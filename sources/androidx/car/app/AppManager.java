package androidx.car.app;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.util.Log;
import androidx.car.app.IAppManager;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.managers.Manager;
import androidx.car.app.media.OpenMicrophoneRequest;
import androidx.car.app.media.OpenMicrophoneResponse;
import androidx.car.app.model.Alert;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.core.location.LocationListenerCompat;
import androidx.lifecycle.Lifecycle;
import j$.util.Objects;

public class AppManager implements Manager {
    private static final int LOCATION_UPDATE_MIN_DISTANCE_METER = 1;
    private static final int LOCATION_UPDATE_MIN_INTERVAL_MILLIS = 1000;
    private final IAppManager.Stub mAppManager;
    private final CarContext mCarContext;
    private final HostDispatcher mHostDispatcher;
    private final Lifecycle mLifecycle;
    private final LocationListenerCompat mLocationListener = new AppManager$$ExternalSyntheticLambda5(this);
    final HandlerThread mLocationUpdateHandlerThread = new HandlerThread("LocationUpdateThread");

    public void setSurfaceCallback(SurfaceCallback surfaceCallback) {
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "setSurfaceListener", new AppManager$$ExternalSyntheticLambda3(this, surfaceCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setSurfaceCallback$0$androidx-car-app-AppManager  reason: not valid java name */
    public /* synthetic */ Object m8lambda$setSurfaceCallback$0$androidxcarappAppManager(SurfaceCallback surfaceCallback, IAppHost iAppHost) throws RemoteException {
        iAppHost.setSurfaceCallback(RemoteUtils.stubSurfaceCallback(this.mLifecycle, surfaceCallback));
        return null;
    }

    public void invalidate() {
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "invalidate", new AppManager$$ExternalSyntheticLambda1());
    }

    public void showToast(CharSequence charSequence, int i) {
        Objects.requireNonNull(charSequence);
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "showToast", new AppManager$$ExternalSyntheticLambda6(charSequence, i));
    }

    @RequiresCarApi(5)
    public void showAlert(Alert alert) {
        Objects.requireNonNull(alert);
        try {
            this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "showAlert", new AppManager$$ExternalSyntheticLambda7(Bundleable.create(alert)));
        } catch (BundlerException e) {
            throw new IllegalArgumentException("Serialization failure", e);
        }
    }

    @RequiresCarApi(5)
    public void dismissAlert(int i) {
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "dismissAlert", new AppManager$$ExternalSyntheticLambda2(i));
    }

    public OpenMicrophoneResponse openMicrophone(OpenMicrophoneRequest openMicrophoneRequest) {
        try {
            return (OpenMicrophoneResponse) this.mHostDispatcher.dispatchForResult(CarContext.APP_SERVICE, "openMicrophone", new AppManager$$ExternalSyntheticLambda0(openMicrophoneRequest));
        } catch (RemoteException e) {
            Log.e(LogTags.TAG, "Error getting microphone bytes from host", e);
            return null;
        }
    }

    static /* synthetic */ OpenMicrophoneResponse lambda$openMicrophone$5(OpenMicrophoneRequest openMicrophoneRequest, IAppHost iAppHost) throws RemoteException {
        try {
            Bundleable openMicrophone = iAppHost.openMicrophone(Bundleable.create(openMicrophoneRequest));
            if (openMicrophone == null) {
                return null;
            }
            return (OpenMicrophoneResponse) openMicrophone.get();
        } catch (BundlerException e) {
            Log.e(LogTags.TAG, "Cannot open microphone", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public IAppManager.Stub getIInterface() {
        return this.mAppManager;
    }

    /* access modifiers changed from: package-private */
    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    /* access modifiers changed from: package-private */
    public void startLocationUpdates() {
        stopLocationUpdates();
        ((LocationManager) this.mCarContext.getSystemService("location")).requestLocationUpdates("fused", 1000, 1.0f, this.mLocationListener, this.mLocationUpdateHandlerThread.getLooper());
    }

    /* access modifiers changed from: package-private */
    public void stopLocationUpdates() {
        ((LocationManager) this.mCarContext.getSystemService("location")).removeUpdates(this.mLocationListener);
    }

    static AppManager create(CarContext carContext, HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        Objects.requireNonNull(carContext);
        Objects.requireNonNull(hostDispatcher);
        Objects.requireNonNull(lifecycle);
        return new AppManager(carContext, hostDispatcher, lifecycle);
    }

    protected AppManager(final CarContext carContext, HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        this.mCarContext = carContext;
        this.mHostDispatcher = hostDispatcher;
        this.mLifecycle = lifecycle;
        this.mAppManager = new IAppManager.Stub() {
            public void getTemplate(IOnDoneCallback iOnDoneCallback) {
                Lifecycle lifecycle = AppManager.this.getLifecycle();
                ScreenManager screenManager = (ScreenManager) carContext.getCarService(ScreenManager.class);
                Objects.requireNonNull(screenManager);
                RemoteUtils.dispatchCallFromHost(lifecycle, iOnDoneCallback, "getTemplate", new AppManager$1$$ExternalSyntheticLambda1(screenManager));
            }

            public void onBackPressed(IOnDoneCallback iOnDoneCallback) {
                RemoteUtils.dispatchCallFromHost(AppManager.this.getLifecycle(), iOnDoneCallback, "onBackPressed", new AppManager$1$$ExternalSyntheticLambda0(carContext));
            }

            public void startLocationUpdates(IOnDoneCallback iOnDoneCallback) {
                PackageManager packageManager = carContext.getPackageManager();
                boolean z = true;
                boolean z2 = packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", carContext.getPackageName()) == -1;
                if (packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", carContext.getPackageName()) != -1) {
                    z = false;
                }
                if (z2 && z) {
                    RemoteUtils.sendFailureResponseToHost(iOnDoneCallback, "startLocationUpdates", new SecurityException("Location permission(s) not granted."));
                }
                RemoteUtils.dispatchCallFromHost(AppManager.this.getLifecycle(), iOnDoneCallback, "startLocationUpdates", new AppManager$1$$ExternalSyntheticLambda3(carContext));
            }

            public void stopLocationUpdates(IOnDoneCallback iOnDoneCallback) {
                RemoteUtils.dispatchCallFromHost(AppManager.this.getLifecycle(), iOnDoneCallback, "stopLocationUpdates", new AppManager$1$$ExternalSyntheticLambda2(carContext));
            }
        };
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$7$androidx-car-app-AppManager  reason: not valid java name */
    public /* synthetic */ void m7lambda$new$7$androidxcarappAppManager(Location location) {
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "sendLocation", new AppManager$$ExternalSyntheticLambda4(location));
    }
}
