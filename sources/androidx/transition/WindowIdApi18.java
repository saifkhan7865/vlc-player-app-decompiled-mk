package androidx.transition;

import android.view.View;
import android.view.WindowId;

class WindowIdApi18 implements WindowIdImpl {
    private final WindowId mWindowId;

    WindowIdApi18(View view) {
        this.mWindowId = WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(view);
    }

    public boolean equals(Object obj) {
        return (obj instanceof WindowIdApi18) && ((WindowIdApi18) obj).mWindowId.equals(this.mWindowId);
    }

    public int hashCode() {
        return this.mWindowId.hashCode();
    }
}
