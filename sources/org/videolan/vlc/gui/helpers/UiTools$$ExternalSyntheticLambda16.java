package org.videolan.vlc.gui.helpers;

import android.app.Activity;
import android.view.DragEvent;
import android.view.View;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UiTools$$ExternalSyntheticLambda16 implements View.OnDragListener {
    public final /* synthetic */ Activity f$0;

    public /* synthetic */ UiTools$$ExternalSyntheticLambda16(Activity activity) {
        this.f$0 = activity;
    }

    public final boolean onDrag(View view, DragEvent dragEvent) {
        return UiTools.setOnDragListener$lambda$25(this.f$0, view, dragEvent);
    }
}
