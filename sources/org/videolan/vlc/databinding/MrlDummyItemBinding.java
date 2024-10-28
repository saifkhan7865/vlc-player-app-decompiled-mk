package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class MrlDummyItemBinding extends ViewDataBinding {
    public final ConstraintLayout container;
    public final ImageView imageView10;
    public final TextView mrlItemTitle;

    protected MrlDummyItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.imageView10 = imageView;
        this.mrlItemTitle = textView;
    }

    public static MrlDummyItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlDummyItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MrlDummyItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_dummy_item, viewGroup, z, obj);
    }

    public static MrlDummyItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlDummyItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MrlDummyItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_dummy_item, (ViewGroup) null, false, obj);
    }

    public static MrlDummyItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlDummyItemBinding bind(View view, Object obj) {
        return (MrlDummyItemBinding) bind(obj, view, R.layout.mrl_dummy_item);
    }
}
