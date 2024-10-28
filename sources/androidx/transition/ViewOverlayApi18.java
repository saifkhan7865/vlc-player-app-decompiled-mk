package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

class ViewOverlayApi18 implements ViewOverlayImpl {
    private final ViewOverlay mViewOverlay;

    ViewOverlayApi18(View view) {
        this.mViewOverlay = WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(view);
    }

    public void add(Drawable drawable) {
        this.mViewOverlay.add(drawable);
    }

    public void remove(Drawable drawable) {
        this.mViewOverlay.remove(drawable);
    }
}
