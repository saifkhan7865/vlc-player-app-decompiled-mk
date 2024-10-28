package org.videolan.tools;

import android.view.KeyEvent;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0005\"\u0004\b\t\u0010\u0007R\u001a\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0005\"\u0004\b\u000b\u0010\u0007R\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/videolan/tools/KeyHelper;", "", "()V", "isAltPressed", "", "()Z", "setAltPressed", "(Z)V", "isCtrlPressed", "setCtrlPressed", "isFunctionPressed", "setFunctionPressed", "isShiftPressed", "setShiftPressed", "manageModifiers", "", "event", "Landroid/view/KeyEvent;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyHelper.kt */
public final class KeyHelper {
    public static final KeyHelper INSTANCE = new KeyHelper();
    private static boolean isAltPressed;
    private static boolean isCtrlPressed;
    private static boolean isFunctionPressed;
    private static boolean isShiftPressed;

    private KeyHelper() {
    }

    public final void manageModifiers(KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        isShiftPressed = keyEvent.isShiftPressed();
        isCtrlPressed = keyEvent.isCtrlPressed();
        isAltPressed = keyEvent.isAltPressed();
        isFunctionPressed = keyEvent.isFunctionPressed();
    }

    public final boolean isShiftPressed() {
        return isShiftPressed;
    }

    public final void setShiftPressed(boolean z) {
        isShiftPressed = z;
    }

    public final boolean isCtrlPressed() {
        return isCtrlPressed;
    }

    public final void setCtrlPressed(boolean z) {
        isCtrlPressed = z;
    }

    public final boolean isAltPressed() {
        return isAltPressed;
    }

    public final void setAltPressed(boolean z) {
        isAltPressed = z;
    }

    public final boolean isFunctionPressed() {
        return isFunctionPressed;
    }

    public final void setFunctionPressed(boolean z) {
        isFunctionPressed = z;
    }
}
