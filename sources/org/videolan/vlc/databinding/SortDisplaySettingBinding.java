package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class SortDisplaySettingBinding extends ViewDataBinding {
    public final Guideline guideline15;
    public final TextView sortAsc;
    public final TextView sortDesc;
    public final ConstraintLayout sortDisplaySetting;
    public final ImageView sortIcon;
    public final TextView sortTitle;

    protected SortDisplaySettingBinding(Object obj, View view, int i, Guideline guideline, TextView textView, TextView textView2, ConstraintLayout constraintLayout, ImageView imageView, TextView textView3) {
        super(obj, view, i);
        this.guideline15 = guideline;
        this.sortAsc = textView;
        this.sortDesc = textView2;
        this.sortDisplaySetting = constraintLayout;
        this.sortIcon = imageView;
        this.sortTitle = textView3;
    }

    public static SortDisplaySettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SortDisplaySettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SortDisplaySettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sort_display_setting, viewGroup, z, obj);
    }

    public static SortDisplaySettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SortDisplaySettingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SortDisplaySettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sort_display_setting, (ViewGroup) null, false, obj);
    }

    public static SortDisplaySettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SortDisplaySettingBinding bind(View view, Object obj) {
        return (SortDisplaySettingBinding) bind(obj, view, R.layout.sort_display_setting);
    }
}
