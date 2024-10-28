package org.videolan.vlc.gui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.slider.Slider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Marker;
import org.videolan.vlc.R;
import org.videolan.vlc.interfaces.OnEqualizerBarChangeListener;

@Metadata(d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f*\u0001\u0010\u0018\u0000 #2\u00020\u0001:\u0001#B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0005J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003J\u0010\u0010\u0019\u001a\u00020\u00182\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0015H\u0016J\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0015J\u000e\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u0005J\b\u0010\"\u001a\u00020\u0018H\u0002R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/view/EqualizerBar;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "band", "", "(Landroid/content/Context;F)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "bandTextView", "Landroid/widget/TextView;", "bandValueTextView", "listener", "Lorg/videolan/vlc/interfaces/OnEqualizerBarChangeListener;", "seekListener", "org/videolan/vlc/gui/view/EqualizerBar$seekListener$1", "Lorg/videolan/vlc/gui/view/EqualizerBar$seekListener$1;", "verticalSlider", "Lcom/google/android/material/slider/Slider;", "getProgress", "", "getValue", "init", "", "setListener", "setNextFocusLeftId", "nextFocusLeftId", "setNextFocusRightId", "nextFocusRightId", "setProgress", "fl", "setValue", "value", "updateValueText", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerBar.kt */
public final class EqualizerBar extends LinearLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int PRECISION = 10;
    public static final int RANGE = 200;
    private TextView bandTextView;
    private TextView bandValueTextView;
    /* access modifiers changed from: private */
    public OnEqualizerBarChangeListener listener;
    private final EqualizerBar$seekListener$1 seekListener = new EqualizerBar$seekListener$1(this);
    private Slider verticalSlider;

    public void setNextFocusLeftId(int i) {
        super.setNextFocusLeftId(i);
        Slider slider = this.verticalSlider;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        slider.setNextFocusLeftId(i);
    }

    public void setNextFocusRightId(int i) {
        super.setNextFocusRightId(i);
        Slider slider = this.verticalSlider;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        slider.setNextFocusRightId(i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EqualizerBar(Context context, float f) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context, f);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EqualizerBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        init(context, 0.0f);
    }

    private final void init(Context context, float f) {
        CharSequence charSequence;
        LayoutInflater.from(context).inflate(R.layout.equalizer_bar, this, true);
        View findViewById = findViewById(R.id.equalizer_seek);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        Slider slider = (Slider) findViewById;
        this.verticalSlider = slider;
        TextView textView = null;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        slider.setLayoutDirection(0);
        Slider slider2 = this.verticalSlider;
        if (slider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider2 = null;
        }
        slider2.setValueTo(400.0f);
        Slider slider3 = this.verticalSlider;
        if (slider3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider3 = null;
        }
        slider3.setValue(200.0f);
        Slider slider4 = this.verticalSlider;
        if (slider4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider4 = null;
        }
        slider4.addOnChangeListener(this.seekListener);
        Slider slider5 = this.verticalSlider;
        if (slider5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider5 = null;
        }
        slider5.addOnSliderTouchListener(new EqualizerBar$init$1(this));
        View findViewById2 = findViewById(R.id.equalizer_band);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.bandTextView = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.band_value);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.bandValueTextView = (TextView) findViewById3;
        TextView textView2 = this.bandTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bandTextView");
        } else {
            textView = textView2;
        }
        if (f < 999.5f) {
            charSequence = ((int) (f + 0.5f)) + "Hz";
        } else {
            charSequence = ((int) ((f / 1000.0f) + 0.5f)) + "kHz";
        }
        textView.setText(charSequence);
        updateValueText();
    }

    /* access modifiers changed from: private */
    public final void updateValueText() {
        Slider slider = this.verticalSlider;
        TextView textView = null;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        float value = (slider.getValue() / ((float) 10)) - ((float) 20);
        TextView textView2 = this.bandValueTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bandValueTextView");
        } else {
            textView = textView2;
        }
        StringBuilder sb = value > 0.0f ? new StringBuilder(Marker.ANY_NON_NULL_MARKER) : new StringBuilder();
        sb.append((int) value);
        sb.append("dB");
        textView.setText(sb.toString());
    }

    public final void setValue(float f) {
        Slider slider = this.verticalSlider;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        slider.setValue((f * ((float) 10)) + ((float) 200));
        updateValueText();
    }

    public final float getValue() {
        Slider slider = this.verticalSlider;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        return (slider.getValue() - ((float) 200)) / 10.0f;
    }

    public final void setListener(OnEqualizerBarChangeListener onEqualizerBarChangeListener) {
        this.listener = onEqualizerBarChangeListener;
    }

    public final void setProgress(int i) {
        Slider slider = this.verticalSlider;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        slider.setValue((float) i);
        updateValueText();
    }

    public final int getProgress() {
        Slider slider = this.verticalSlider;
        if (slider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("verticalSlider");
            slider = null;
        }
        return (int) slider.getValue();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/gui/view/EqualizerBar$Companion;", "", "()V", "PRECISION", "", "RANGE", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: EqualizerBar.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
