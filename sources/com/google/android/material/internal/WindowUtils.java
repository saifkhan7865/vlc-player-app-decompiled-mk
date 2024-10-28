package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WindowUtils {
    /* access modifiers changed from: private */
    public static final String TAG = "WindowUtils";

    private WindowUtils() {
    }

    public static Rect getCurrentWindowBounds(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getCurrentWindowBounds(windowManager);
        }
        return Api17Impl.getCurrentWindowBounds(windowManager);
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static Rect getCurrentWindowBounds(WindowManager windowManager) {
            return WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(windowManager.getCurrentWindowMetrics());
        }
    }

    private static class Api17Impl {
        private Api17Impl() {
        }

        static Rect getCurrentWindowBounds(WindowManager windowManager) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            Rect rect = new Rect();
            rect.right = point.x;
            rect.bottom = point.y;
            return rect;
        }
    }

    private static class Api14Impl {
        private Api14Impl() {
        }

        static Rect getCurrentWindowBounds(WindowManager windowManager) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point realSizeForDisplay = getRealSizeForDisplay(defaultDisplay);
            Rect rect = new Rect();
            if (realSizeForDisplay.x == 0 || realSizeForDisplay.y == 0) {
                defaultDisplay.getRectSize(rect);
            } else {
                rect.right = realSizeForDisplay.x;
                rect.bottom = realSizeForDisplay.y;
            }
            return rect;
        }

        private static Point getRealSizeForDisplay(Display display) {
            Point point = new Point();
            try {
                Method declaredMethod = Display.class.getDeclaredMethod("getRealSize", new Class[]{Point.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(display, new Object[]{point});
            } catch (NoSuchMethodException e) {
                Log.w(WindowUtils.TAG, e);
            } catch (IllegalAccessException e2) {
                Log.w(WindowUtils.TAG, e2);
            } catch (InvocationTargetException e3) {
                Log.w(WindowUtils.TAG, e3);
            }
            return point;
        }
    }
}
