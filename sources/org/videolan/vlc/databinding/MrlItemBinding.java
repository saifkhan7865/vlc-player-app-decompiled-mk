package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.R;

public abstract class MrlItemBinding extends ViewDataBinding {
    @Bindable
    protected MediaWrapper mItem;
    public final AppCompatImageView mrlCtx;
    public final TextView mrlItemTitle;
    public final TextView mrlItemUri;
    public final View selector;

    public abstract void setItem(MediaWrapper mediaWrapper);

    protected MrlItemBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.mrlCtx = appCompatImageView;
        this.mrlItemTitle = textView;
        this.mrlItemUri = textView2;
        this.selector = view2;
    }

    public MediaWrapper getItem() {
        return this.mItem;
    }

    public static MrlItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MrlItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_item, viewGroup, z, obj);
    }

    public static MrlItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MrlItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_item, (ViewGroup) null, false, obj);
    }

    public static MrlItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlItemBinding bind(View view, Object obj) {
        return (MrlItemBinding) bind(obj, view, R.layout.mrl_item);
    }
}
