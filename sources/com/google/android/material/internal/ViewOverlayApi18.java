package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;

class ViewOverlayApi18 implements ViewOverlayImpl {
    private final ViewOverlay viewOverlay;

    ViewOverlayApi18(View view) {
        this.viewOverlay = WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(view);
    }

    public void add(Drawable drawable) {
        this.viewOverlay.add(drawable);
    }

    public void remove(Drawable drawable) {
        this.viewOverlay.remove(drawable);
    }
}
