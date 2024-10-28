package com.google.android.material.shape;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline1;

class ShapeableDelegateV33 extends ShapeableDelegate {
    ShapeableDelegateV33(View view) {
        initMaskOutlineProvider(view);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldUseCompatClipping() {
        return this.forceCompatClippingEnabled;
    }

    /* access modifiers changed from: package-private */
    public void invalidateClippingMethod(View view) {
        view.setClipToOutline(!shouldUseCompatClipping());
        if (shouldUseCompatClipping()) {
            view.invalidate();
        } else {
            Chip$$ExternalSyntheticApiModelOutline1.m(view);
        }
    }

    private void initMaskOutlineProvider(View view) {
        view.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                if (!ShapeableDelegateV33.this.shapePath.isEmpty()) {
                    outline.setPath(ShapeableDelegateV33.this.shapePath);
                }
            }
        });
    }
}
