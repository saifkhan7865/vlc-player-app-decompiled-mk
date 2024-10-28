package io.ktor.server.engine;

import android.support.v4.media.session.PlaybackStateCompat;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\f\u0010\u0004\u001a\u00020\u0002*\u00020\u0005H\u0000\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"longStrings", "", "", "[Ljava/lang/String;", "toStringFast", "", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Long.kt */
public final class LongKt {
    private static final String[] longStrings;

    static {
        String[] strArr = new String[1024];
        for (int i = 0; i < 1024; i++) {
            strArr[i] = String.valueOf(i);
        }
        longStrings = strArr;
    }

    public static final String toStringFast(long j) {
        return (0 > j || j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) ? String.valueOf(j) : longStrings[(int) j];
    }
}
