package org.videolan.vlc.gui.view;

import android.view.ViewTreeObserver;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/vlc/gui/view/CollapsibleLinearLayout$initialize$1", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onGlobalLayout", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CollapsibleLinearLayout.kt */
public final class CollapsibleLinearLayout$initialize$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ CollapsibleLinearLayout this$0;

    CollapsibleLinearLayout$initialize$1(CollapsibleLinearLayout collapsibleLinearLayout) {
        this.this$0 = collapsibleLinearLayout;
    }

    public void onGlobalLayout() {
        CollapsibleLinearLayout collapsibleLinearLayout = this.this$0;
        collapsibleLinearLayout.maxHeight = collapsibleLinearLayout.getHeight();
        this.this$0.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.this$0.getLayoutParams().height = 0;
        this.this$0.requestLayout();
        Function0 access$getOnReadyListener$p = this.this$0.onReadyListener;
        if (access$getOnReadyListener$p != null) {
            access$getOnReadyListener$p.invoke();
        }
    }
}
