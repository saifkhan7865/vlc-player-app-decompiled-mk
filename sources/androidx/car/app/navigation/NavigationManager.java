package androidx.car.app.navigation;

import android.util.Log;
import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.managers.Manager;
import androidx.car.app.navigation.INavigationManager;
import androidx.car.app.navigation.model.Trip;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import j$.util.Objects;
import java.util.concurrent.Executor;

public class NavigationManager implements Manager {
    private final CarContext mCarContext;
    private final HostDispatcher mHostDispatcher;
    private boolean mIsAutoDriveEnabled;
    private boolean mIsNavigating;
    private final INavigationManager.Stub mNavigationManager;
    private NavigationManagerCallback mNavigationManagerCallback;
    private Executor mNavigationManagerCallbackExecutor;

    public void updateTrip(Trip trip) {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating) {
            try {
                this.mHostDispatcher.dispatch("navigation", "updateTrip", new NavigationManager$$ExternalSyntheticLambda0(Bundleable.create(trip)));
            } catch (BundlerException e) {
                throw new IllegalArgumentException("Serialization failure", e);
            }
        } else {
            throw new IllegalStateException("Navigation is not started");
        }
    }

    public void setNavigationManagerCallback(NavigationManagerCallback navigationManagerCallback) {
        ThreadUtils.checkMainThread();
        setNavigationManagerCallback(ContextCompat.getMainExecutor(this.mCarContext), navigationManagerCallback);
    }

    public void setNavigationManagerCallback(Executor executor, NavigationManagerCallback navigationManagerCallback) {
        ThreadUtils.checkMainThread();
        this.mNavigationManagerCallbackExecutor = executor;
        this.mNavigationManagerCallback = navigationManagerCallback;
        if (this.mIsAutoDriveEnabled) {
            onAutoDriveEnabled();
        }
    }

    public void clearNavigationManagerCallback() {
        ThreadUtils.checkMainThread();
        if (!this.mIsNavigating) {
            this.mNavigationManagerCallbackExecutor = null;
            this.mNavigationManagerCallback = null;
            return;
        }
        throw new IllegalStateException("Removing callback while navigating");
    }

    public void navigationStarted() {
        ThreadUtils.checkMainThread();
        if (!this.mIsNavigating) {
            if (this.mNavigationManagerCallback != null) {
                this.mIsNavigating = true;
                this.mHostDispatcher.dispatch("navigation", "navigationStarted", new NavigationManager$$ExternalSyntheticLambda2());
                return;
            }
            throw new IllegalStateException("No callback has been set");
        }
    }

    public void navigationEnded() {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating) {
            this.mIsNavigating = false;
            this.mHostDispatcher.dispatch("navigation", "navigationEnded", new NavigationManager$$ExternalSyntheticLambda1());
        }
    }

    public static NavigationManager create(CarContext carContext, HostDispatcher hostDispatcher, Lifecycle lifecycle) {
        Objects.requireNonNull(carContext);
        Objects.requireNonNull(hostDispatcher);
        Objects.requireNonNull(lifecycle);
        return new NavigationManager(carContext, hostDispatcher, lifecycle);
    }

    public INavigationManager.Stub getIInterface() {
        return this.mNavigationManager;
    }

    public void onStopNavigation() {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating) {
            this.mIsNavigating = false;
            Executor executor = this.mNavigationManagerCallbackExecutor;
            if (executor != null) {
                executor.execute(new NavigationManager$$ExternalSyntheticLambda4(this));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onStopNavigation$3$androidx-car-app-navigation-NavigationManager  reason: not valid java name */
    public /* synthetic */ void m48lambda$onStopNavigation$3$androidxcarappnavigationNavigationManager() {
        NavigationManagerCallback navigationManagerCallback = this.mNavigationManagerCallback;
        if (navigationManagerCallback != null) {
            navigationManagerCallback.onStopNavigation();
        }
    }

    public void onAutoDriveEnabled() {
        ThreadUtils.checkMainThread();
        if (Log.isLoggable(LogTags.TAG_NAVIGATION_MANAGER, 3)) {
            Log.d(LogTags.TAG_NAVIGATION_MANAGER, "Executing onAutoDriveEnabled");
        }
        this.mIsAutoDriveEnabled = true;
        NavigationManagerCallback navigationManagerCallback = this.mNavigationManagerCallback;
        Executor executor = this.mNavigationManagerCallbackExecutor;
        if (navigationManagerCallback == null || executor == null) {
            Log.w(LogTags.TAG_NAVIGATION_MANAGER, "NavigationManagerCallback not set, skipping onAutoDriveEnabled");
            return;
        }
        Objects.requireNonNull(navigationManagerCallback);
        executor.execute(new NavigationManager$$ExternalSyntheticLambda3(navigationManagerCallback));
    }

    protected NavigationManager(CarContext carContext, HostDispatcher hostDispatcher, final Lifecycle lifecycle) {
        this.mCarContext = (CarContext) Objects.requireNonNull(carContext);
        this.mHostDispatcher = (HostDispatcher) Objects.requireNonNull(hostDispatcher);
        this.mNavigationManager = new INavigationManager.Stub() {
            public void onStopNavigation(IOnDoneCallback iOnDoneCallback) {
                RemoteUtils.dispatchCallFromHost(lifecycle, iOnDoneCallback, "onStopNavigation", new NavigationManager$1$$ExternalSyntheticLambda0(this));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onStopNavigation$0$androidx-car-app-navigation-NavigationManager$1  reason: not valid java name */
            public /* synthetic */ Object m49lambda$onStopNavigation$0$androidxcarappnavigationNavigationManager$1() throws BundlerException {
                NavigationManager.this.onStopNavigation();
                return null;
            }
        };
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
                NavigationManager.this.onStopNavigation();
                lifecycle.removeObserver(this);
            }
        });
    }
}
