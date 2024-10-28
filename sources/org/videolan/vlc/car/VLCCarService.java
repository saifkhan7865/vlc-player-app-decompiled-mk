package org.videolan.vlc.car;

import androidx.car.app.CarAppService;
import androidx.car.app.validation.HostValidator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;
import org.videolan.vlc.util.AccessControl;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/car/VLCCarService;", "Landroidx/car/app/CarAppService;", "()V", "createHostValidator", "Landroidx/car/app/validation/HostValidator;", "onCreateSession", "Lorg/videolan/vlc/car/SettingsSession;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCCarService.kt */
public final class VLCCarService extends CarAppService {
    public HostValidator createHostValidator() {
        if ((getApplicationInfo().flags & 2) != 0) {
            HostValidator hostValidator = HostValidator.ALLOW_ALL_HOSTS_VALIDATOR;
            Intrinsics.checkNotNull(hostValidator);
            return hostValidator;
        }
        HostValidator.Builder builder = new HostValidator.Builder(getApplicationContext());
        for (String replace$default : AccessControl.INSTANCE.getKeysByPackage(Constants.ANDROID_AUTO_APP_PKG)) {
            builder.addAllowedHost(Constants.ANDROID_AUTO_APP_PKG, StringsKt.replace$default(replace$default, ":", "", false, 4, (Object) null));
        }
        HostValidator build = builder.build();
        Intrinsics.checkNotNull(build);
        return build;
    }

    public SettingsSession onCreateSession() {
        return new SettingsSession();
    }
}
