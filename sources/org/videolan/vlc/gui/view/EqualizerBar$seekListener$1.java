package org.videolan.vlc.gui.view;

import com.google.android.material.slider.Slider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.interfaces.OnEqualizerBarChangeListener;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"org/videolan/vlc/gui/view/EqualizerBar$seekListener$1", "Lcom/google/android/material/slider/Slider$OnChangeListener;", "onValueChange", "", "slider", "Lcom/google/android/material/slider/Slider;", "value", "", "fromUser", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerBar.kt */
public final class EqualizerBar$seekListener$1 implements Slider.OnChangeListener {
    final /* synthetic */ EqualizerBar this$0;

    EqualizerBar$seekListener$1(EqualizerBar equalizerBar) {
        this.this$0 = equalizerBar;
    }

    public void onValueChange(Slider slider, float f, boolean z) {
        Intrinsics.checkNotNullParameter(slider, "slider");
        float f2 = (f - ((float) 200)) / 10.0f;
        OnEqualizerBarChangeListener access$getListener$p = this.this$0.listener;
        if (access$getListener$p != null) {
            access$getListener$p.onProgressChanged(f2, z);
        }
        this.this$0.updateValueText();
    }
}
