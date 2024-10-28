package androidx.leanback.widget;

import android.graphics.Outline;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;

class RoundedRectHelperApi21 {
    private static final int MAX_CACHED_PROVIDER = 32;
    private static SparseArray<ViewOutlineProvider> sRoundedRectProvider;

    static final class RoundedRectOutlineProvider extends ViewOutlineProvider {
        private int mRadius;

        RoundedRectOutlineProvider(int i) {
            this.mRadius = i;
        }

        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) this.mRadius);
            outline.setAlpha(1.0f);
        }
    }

    public static void setClipToRoundedOutline(View view, boolean z, int i) {
        if (z) {
            if (sRoundedRectProvider == null) {
                sRoundedRectProvider = new SparseArray<>();
            }
            ViewOutlineProvider m = Trace$$ExternalSyntheticApiModelOutline0.m((Object) sRoundedRectProvider.get(i));
            if (m == null) {
                m = new RoundedRectOutlineProvider(i);
                if (sRoundedRectProvider.size() < 32) {
                    sRoundedRectProvider.put(i, m);
                }
            }
            view.setOutlineProvider(m);
        } else {
            view.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        }
        view.setClipToOutline(z);
    }

    private RoundedRectHelperApi21() {
    }
}
