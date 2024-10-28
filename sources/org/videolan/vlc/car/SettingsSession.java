package org.videolan.vlc.car;

import android.content.Intent;
import androidx.car.app.CarContext;
import androidx.car.app.Session;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/car/SettingsSession;", "Landroidx/car/app/Session;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "()V", "onCreate", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onCreateScreen", "Lorg/videolan/vlc/car/CarSettingsScreen;", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCCarService.kt */
public final class SettingsSession extends Session implements DefaultLifecycleObserver {
    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
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

    public SettingsSession() {
        getLifecycle().addObserver(this);
    }

    public void onCreate(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
        getCarContext().getOnBackPressedDispatcher().addCallback(this, new SettingsSession$onCreate$1(this));
    }

    public CarSettingsScreen onCreateScreen(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "getCarContext(...)");
        return new CarSettingsScreen(carContext);
    }
}
