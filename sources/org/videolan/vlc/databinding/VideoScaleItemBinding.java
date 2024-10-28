package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class VideoScaleItemBinding extends ViewDataBinding {
    public final ImageView imageView11;
    @Bindable
    protected String mScaleName;
    @Bindable
    protected Boolean mSelected;
    public final ConstraintLayout trackContainer;
    public final TextView trackTitle;

    public abstract void setScaleName(String str);

    public abstract void setSelected(Boolean bool);

    protected VideoScaleItemBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.imageView11 = imageView;
        this.trackContainer = constraintLayout;
        this.trackTitle = textView;
    }

    public String getScaleName() {
        return this.mScaleName;
    }

    public Boolean getSelected() {
        return this.mSelected;
    }

    public static VideoScaleItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoScaleItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (VideoScaleItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_scale_item, viewGroup, z, obj);
    }

    public static VideoScaleItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoScaleItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (VideoScaleItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_scale_item, (ViewGroup) null, false, obj);
    }

    public static VideoScaleItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoScaleItemBinding bind(View view, Object obj) {
        return (VideoScaleItemBinding) bind(obj, view, R.layout.video_scale_item);
    }
}
