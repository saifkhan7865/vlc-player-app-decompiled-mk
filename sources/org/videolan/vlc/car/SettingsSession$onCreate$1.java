package org.videolan.vlc.car;

import androidx.activity.OnBackPressedCallback;
import androidx.car.app.ScreenManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/vlc/car/SettingsSession$onCreate$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCCarService.kt */
public final class SettingsSession$onCreate$1 extends OnBackPressedCallback {
    final /* synthetic */ SettingsSession this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SettingsSession$onCreate$1(SettingsSession settingsSession) {
        super(true);
        this.this$0 = settingsSession;
    }

    public void handleOnBackPressed() {
        Object carService = this.this$0.getCarContext().getCarService(ScreenManager.class);
        Intrinsics.checkNotNullExpressionValue(carService, "getCarService(...)");
        ScreenManager screenManager = (ScreenManager) carService;
        if (screenManager.getStackSize() > 1) {
            screenManager.pop();
        } else {
            this.this$0.getCarContext().finishCarApp();
        }
    }
}
