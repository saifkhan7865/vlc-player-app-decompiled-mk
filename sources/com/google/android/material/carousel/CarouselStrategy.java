package com.google.android.material.carousel;

import android.view.View;

public abstract class CarouselStrategy {
    static float getChildMaskPercentage(float f, float f2, float f3) {
        return 1.0f - ((f - f3) / (f2 - f3));
    }

    /* access modifiers changed from: package-private */
    public boolean isContained() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public abstract KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view);

    /* access modifiers changed from: package-private */
    public boolean shouldRefreshKeylineState(Carousel carousel, int i) {
        return false;
    }

    static int[] doubleCounts(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            iArr2[i] = iArr[i] * 2;
        }
        return iArr2;
    }
}
