package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import org.videolan.vlc.R;

public abstract class DialogVideoControlsSettingsBinding extends ViewDataBinding {
    public final ConstraintLayout container;
    public final FragmentContainerView fragmentContainerView;
    public final TextView videoControlsTitle;

    protected DialogVideoControlsSettingsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, FragmentContainerView fragmentContainerView2, TextView textView) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.fragmentContainerView = fragmentContainerView2;
        this.videoControlsTitle = textView;
    }

    public static DialogVideoControlsSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVideoControlsSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogVideoControlsSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_video_controls_settings, viewGroup, z, obj);
    }

    public static DialogVideoControlsSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVideoControlsSettingsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogVideoControlsSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_video_controls_settings, (ViewGroup) null, false, obj);
    }

    public static DialogVideoControlsSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVideoControlsSettingsBinding bind(View view, Object obj) {
        return (DialogVideoControlsSettingsBinding) bind(obj, view, R.layout.dialog_video_controls_settings);
    }
}
