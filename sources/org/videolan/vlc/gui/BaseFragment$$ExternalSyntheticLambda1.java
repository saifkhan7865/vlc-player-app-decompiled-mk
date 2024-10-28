package org.videolan.vlc.gui;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BaseFragment$$ExternalSyntheticLambda1 implements View.OnTouchListener {
    public final /* synthetic */ BaseFragment f$0;

    public /* synthetic */ BaseFragment$$ExternalSyntheticLambda1(BaseFragment baseFragment) {
        this.f$0 = baseFragment;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return BaseFragment.swipeFilter$lambda$0(this.f$0, view, motionEvent);
    }
}
