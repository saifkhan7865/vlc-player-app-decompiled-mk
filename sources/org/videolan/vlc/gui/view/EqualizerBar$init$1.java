package org.videolan.vlc.gui.view;

import com.google.android.material.slider.Slider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.interfaces.OnEqualizerBarChangeListener;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"org/videolan/vlc/gui/view/EqualizerBar$init$1", "Lcom/google/android/material/slider/Slider$OnSliderTouchListener;", "onStartTrackingTouch", "", "slider", "Lcom/google/android/material/slider/Slider;", "onStopTrackingTouch", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerBar.kt */
public final class EqualizerBar$init$1 implements Slider.OnSliderTouchListener {
    final /* synthetic */ EqualizerBar this$0;

    EqualizerBar$init$1(EqualizerBar equalizerBar) {
        this.this$0 = equalizerBar;
    }

    public void onStartTrackingTouch(Slider slider) {
        Intrinsics.checkNotNullParameter(slider, "slider");
        OnEqualizerBarChangeListener access$getListener$p = this.this$0.listener;
        if (access$getListener$p != null) {
            access$getListener$p.onStartTrackingTouch();
        }
    }

    public void onStopTrackingTouch(Slider slider) {
        Intrinsics.checkNotNullParameter(slider, "slider");
        OnEqualizerBarChangeListener access$getListener$p = this.this$0.listener;
        if (access$getListener$p != null) {
            access$getListener$p.onStopTrackingTouch();
        }
    }
}
