package androidx.car.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

public class SessionInfoIntentEncoder {
    private static final String EXTRA_SESSION_INFO = "androidx.car.app.extra.SESSION_INFO_BUNDLE";
    private static final String KEY_DISPLAY_TYPE = "display-type";
    private static final String KEY_SESSION_ID = "session-id";

    private SessionInfoIntentEncoder() {
    }

    public static void encode(SessionInfo sessionInfo, Intent intent) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29.setIdentifier(intent, sessionInfo.toString());
        } else {
            intent.setData(new Uri.Builder().path(sessionInfo.toString()).build());
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DISPLAY_TYPE, sessionInfo.getDisplayType());
        bundle.putString(KEY_SESSION_ID, sessionInfo.getSessionId());
        intent.putExtra(EXTRA_SESSION_INFO, bundle);
    }

    public static SessionInfo decode(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle(EXTRA_SESSION_INFO);
            return new SessionInfo(bundle.getInt(KEY_DISPLAY_TYPE), bundle.getString(KEY_SESSION_ID));
        }
        throw new IllegalArgumentException("Expected the SessionInfo to be encoded in the bind intent extras, but the extras were null.");
    }

    public static boolean containsSessionInfo(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return false;
        }
        return extras.containsKey(EXTRA_SESSION_INFO);
    }

    private static class Api29 {
        private Api29() {
        }

        static void setIdentifier(Intent intent, String str) {
            Intent unused = intent.setIdentifier(str);
        }
    }
}
