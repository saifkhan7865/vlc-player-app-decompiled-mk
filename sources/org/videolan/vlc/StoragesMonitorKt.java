package org.videolan.vlc;

import android.content.ComponentName;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"TAG", "", "enableStorageMonitoring", "", "Landroid/content/Context;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: StoragesMonitor.kt */
public final class StoragesMonitorKt {
    private static final String TAG = "VLC/StoragesMonitor";

    public static final void enableStorageMonitoring(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        context.getApplicationContext().getPackageManager().setComponentEnabledSetting(new ComponentName(context.getApplicationContext(), StoragesMonitor.class), 1, 1);
    }
}
