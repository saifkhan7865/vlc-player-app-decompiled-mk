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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.R;

public abstract class MrlCardItemBinding extends ViewDataBinding {
    public final ConstraintLayout container;
    public final ImageView imageView10;
    @Bindable
    protected MediaWrapper mItem;
    public final ImageView mrlCtx;
    public final TextView mrlItemTitle;
    public final TextView mrlItemUri;

    public abstract void setItem(MediaWrapper mediaWrapper);

    protected MrlCardItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.imageView10 = imageView;
        this.mrlCtx = imageView2;
        this.mrlItemTitle = textView;
        this.mrlItemUri = textView2;
    }

    public MediaWrapper getItem() {
        return this.mItem;
    }

    public static MrlCardItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlCardItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MrlCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_card_item, viewGroup, z, obj);
    }

    public static MrlCardItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlCardItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MrlCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_card_item, (ViewGroup) null, false, obj);
    }

    public static MrlCardItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlCardItemBinding bind(View view, Object obj) {
        return (MrlCardItemBinding) bind(obj, view, R.layout.mrl_card_item);
    }
}
