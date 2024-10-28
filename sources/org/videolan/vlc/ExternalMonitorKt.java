package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.tools.Strings;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a!\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"TAG", "", "containsDevice", "", "devices", "", "device", "([Ljava/lang/String;Ljava/lang/String;)Z", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExternalMonitor.kt */
public final class ExternalMonitorKt {
    private static final String TAG = "VLC/ExternalMonitor";

    public static final boolean containsDevice(String[] strArr, String str) {
        Intrinsics.checkNotNullParameter(strArr, "devices");
        Intrinsics.checkNotNullParameter(str, "device");
        if (strArr.length == 0) {
            return false;
        }
        for (String removeFileScheme : strArr) {
            if (StringsKt.startsWith$default(str, Strings.removeFileScheme(removeFileScheme), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}
