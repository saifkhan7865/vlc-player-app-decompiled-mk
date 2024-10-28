package androidx.car.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import j$.util.Objects;

public abstract class Session implements LifecycleOwner {
    private CarContext mCarContext;
    private final LifecycleObserver mLifecycleObserver;
    private LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    final LifecycleRegistry mRegistryPublic = new LifecycleRegistry(this);

    public void onCarConfigurationChanged(Configuration configuration) {
    }

    public abstract Screen onCreateScreen(Intent intent);

    public void onNewIntent(Intent intent) {
    }

    public Session() {
        LifecycleObserverImpl lifecycleObserverImpl = new LifecycleObserverImpl();
        this.mLifecycleObserver = lifecycleObserverImpl;
        this.mRegistry.addObserver(lifecycleObserverImpl);
        this.mCarContext = CarContext.create(this.mRegistry);
    }

    public Lifecycle getLifecycle() {
        return this.mRegistryPublic;
    }

    /* access modifiers changed from: package-private */
    public Lifecycle getLifecycleInternal() {
        return this.mRegistry;
    }

    /* access modifiers changed from: package-private */
    public void handleLifecycleEvent(Lifecycle.Event event) {
        this.mRegistry.handleLifecycleEvent(event);
    }

    public void setLifecycleRegistryInternal(LifecycleRegistry lifecycleRegistry) {
        this.mRegistry = lifecycleRegistry;
        lifecycleRegistry.addObserver(this.mLifecycleObserver);
    }

    public final CarContext getCarContext() {
        return (CarContext) Objects.requireNonNull(this.mCarContext);
    }

    public void setCarContextInternal(CarContext carContext) {
        this.mCarContext = carContext;
    }

    /* access modifiers changed from: package-private */
    public void configure(Context context, HandshakeInfo handshakeInfo, HostInfo hostInfo, ICarHost iCarHost, Configuration configuration) {
        this.mCarContext.updateHandshakeInfo(handshakeInfo);
        this.mCarContext.updateHostInfo(hostInfo);
        this.mCarContext.attachBaseContext(context, configuration);
        this.mCarContext.setCarHost(iCarHost);
    }

    /* access modifiers changed from: package-private */
    public void onCarConfigurationChangedInternal(Configuration configuration) {
        this.mCarContext.onCarConfigurationChanged(configuration);
        onCarConfigurationChanged(this.mCarContext.getResources().getConfiguration());
    }

    class LifecycleObserverImpl implements DefaultLifecycleObserver {
        LifecycleObserverImpl() {
        }

        public void onCreate(LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        }

        public void onStart(LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_START);
        }

        public void onResume(LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }

        public void onPause(LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }

        public void onStop(LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }

        public void onDestroy(LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
