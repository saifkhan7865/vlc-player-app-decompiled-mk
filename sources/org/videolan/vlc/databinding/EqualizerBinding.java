package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.slider.Slider;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.EqualizerFragment;

public abstract class EqualizerBinding extends ViewDataBinding {
    public final TextView eqTitle;
    public final LinearLayout equalizerBands;
    public final SwitchCompat equalizerButton;
    public final ConstraintLayout equalizerContainer;
    public final Button equalizerDelete;
    public final Slider equalizerPreamp;
    public final AppCompatSpinner equalizerPresets;
    public final Button equalizerRevert;
    public final Button equalizerSave;
    @Bindable
    protected EqualizerFragment.EqualizerState mState;
    public final SwitchCompat snapBands;
    public final TextView textView10;
    public final TextView textView11;

    public abstract void setState(EqualizerFragment.EqualizerState equalizerState);

    protected EqualizerBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, SwitchCompat switchCompat, ConstraintLayout constraintLayout, Button button, Slider slider, AppCompatSpinner appCompatSpinner, Button button2, Button button3, SwitchCompat switchCompat2, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.eqTitle = textView;
        this.equalizerBands = linearLayout;
        this.equalizerButton = switchCompat;
        this.equalizerContainer = constraintLayout;
        this.equalizerDelete = button;
        this.equalizerPreamp = slider;
        this.equalizerPresets = appCompatSpinner;
        this.equalizerRevert = button2;
        this.equalizerSave = button3;
        this.snapBands = switchCompat2;
        this.textView10 = textView2;
        this.textView11 = textView3;
    }

    public EqualizerFragment.EqualizerState getState() {
        return this.mState;
    }

    public static EqualizerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EqualizerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (EqualizerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.equalizer, viewGroup, z, obj);
    }

    public static EqualizerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EqualizerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (EqualizerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.equalizer, (ViewGroup) null, false, obj);
    }

    public static EqualizerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EqualizerBinding bind(View view, Object obj) {
        return (EqualizerBinding) bind(obj, view, R.layout.equalizer);
    }
}
