package androidx.core.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class HapticFeedbackConstantsCompat {
    public static final int CLOCK_TICK = 4;
    public static final int CONFIRM = 16;
    public static final int CONTEXT_CLICK = 6;
    public static final int DRAG_START = 25;
    static final int FIRST_CONSTANT_INT = 0;
    public static final int FLAG_IGNORE_VIEW_SETTING = 1;
    public static final int GESTURE_END = 13;
    public static final int GESTURE_START = 12;
    public static final int GESTURE_THRESHOLD_ACTIVATE = 23;
    public static final int GESTURE_THRESHOLD_DEACTIVATE = 24;
    public static final int KEYBOARD_PRESS = 3;
    public static final int KEYBOARD_RELEASE = 7;
    public static final int KEYBOARD_TAP = 3;
    static final int LAST_CONSTANT_INT = 27;
    public static final int LONG_PRESS = 0;
    public static final int NO_HAPTICS = -1;
    public static final int REJECT = 17;
    public static final int SEGMENT_FREQUENT_TICK = 27;
    public static final int SEGMENT_TICK = 26;
    public static final int TEXT_HANDLE_MOVE = 9;
    public static final int TOGGLE_OFF = 22;
    public static final int TOGGLE_ON = 21;
    public static final int VIRTUAL_KEY = 1;
    public static final int VIRTUAL_KEY_RELEASE = 8;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HapticFeedbackFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HapticFeedbackType {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        if (r6 != 17) goto L_0x0031;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0055 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int getFeedbackConstantOrFallback(int r6) {
        /*
            r0 = -1
            if (r6 != r0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 34
            r3 = 0
            r4 = 4
            r5 = 6
            if (r1 >= r2) goto L_0x0016
            switch(r6) {
                case 21: goto L_0x0015;
                case 22: goto L_0x0013;
                case 23: goto L_0x0015;
                case 24: goto L_0x0013;
                case 25: goto L_0x0011;
                case 26: goto L_0x0015;
                case 27: goto L_0x0013;
                default: goto L_0x0010;
            }
        L_0x0010:
            goto L_0x0016
        L_0x0011:
            r6 = 0
            goto L_0x0016
        L_0x0013:
            r6 = 4
            goto L_0x0016
        L_0x0015:
            r6 = 6
        L_0x0016:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 30
            if (r1 >= r2) goto L_0x0031
            r1 = 12
            if (r6 == r1) goto L_0x002f
            r1 = 13
            if (r6 == r1) goto L_0x002d
            r1 = 16
            if (r6 == r1) goto L_0x002f
            r1 = 17
            if (r6 == r1) goto L_0x0032
            goto L_0x0031
        L_0x002d:
            r3 = 6
            goto L_0x0032
        L_0x002f:
            r3 = 1
            goto L_0x0032
        L_0x0031:
            r3 = r6
        L_0x0032:
            int r6 = android.os.Build.VERSION.SDK_INT
            r1 = 27
            if (r6 >= r1) goto L_0x0045
            r6 = 7
            if (r3 == r6) goto L_0x0044
            r6 = 8
            if (r3 == r6) goto L_0x0044
            r6 = 9
            if (r3 == r6) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            r3 = -1
        L_0x0045:
            int r6 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r6 >= r1) goto L_0x004f
            if (r3 == r5) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r3 = 4
        L_0x004f:
            int r6 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r6 >= r1) goto L_0x0057
            if (r3 == r4) goto L_0x0058
        L_0x0057:
            r0 = r3
        L_0x0058:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.HapticFeedbackConstantsCompat.getFeedbackConstantOrFallback(int):int");
    }

    private HapticFeedbackConstantsCompat() {
    }
}
