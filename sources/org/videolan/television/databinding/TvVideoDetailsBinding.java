package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.television.R;

public abstract class TvVideoDetailsBinding extends ViewDataBinding {
    public final ConstraintLayout container;
    @Bindable
    protected MediaMetadata mItem;
    public final TextView textView22;
    public final TextView textView26;
    public final TextView textView27;
    public final TextView textView28;
    public final TextView textView29;
    public final TextView textView30;
    public final TextView textView31;
    public final TextView textView32;
    public final TextView textView33;
    public final TextView textView34;

    public abstract void setItem(MediaMetadata mediaMetadata);

    protected TvVideoDetailsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.textView22 = textView;
        this.textView26 = textView2;
        this.textView27 = textView3;
        this.textView28 = textView4;
        this.textView29 = textView5;
        this.textView30 = textView6;
        this.textView31 = textView7;
        this.textView32 = textView8;
        this.textView33 = textView9;
        this.textView34 = textView10;
    }

    public MediaMetadata getItem() {
        return this.mItem;
    }

    public static TvVideoDetailsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvVideoDetailsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (TvVideoDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tv_video_details, viewGroup, z, obj);
    }

    public static TvVideoDetailsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvVideoDetailsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (TvVideoDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tv_video_details, (ViewGroup) null, false, obj);
    }

    public static TvVideoDetailsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvVideoDetailsBinding bind(View view, Object obj) {
        return (TvVideoDetailsBinding) bind(obj, view, R.layout.tv_video_details);
    }
}
