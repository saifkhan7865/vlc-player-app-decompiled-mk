package androidx.car.app.utils;

import android.content.Context;
import j$.util.Objects;

public final class CommonUtils {
    public static boolean isAutomotiveOS(Context context) {
        return ((Context) Objects.requireNonNull(context)).getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    private CommonUtils() {
    }
}
