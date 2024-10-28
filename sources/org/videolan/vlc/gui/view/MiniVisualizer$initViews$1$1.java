package org.videolan.vlc.gui.view;

import android.view.View;
import android.view.ViewTreeObserver;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/vlc/gui/view/MiniVisualizer$initViews$1$1", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onGlobalLayout", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MiniVisualizer.kt */
public final class MiniVisualizer$initViews$1$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ View $it;

    MiniVisualizer$initViews$1$1(View view) {
        this.$it = view;
    }

    public void onGlobalLayout() {
        if (this.$it.getHeight() > 0) {
            View view = this.$it;
            view.setPivotY((float) view.getHeight());
            this.$it.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }
}
