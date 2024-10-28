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
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.vlc.R;
import org.videolan.vlc.viewmodels.StreamsModel;

public abstract class MrlPanelBinding extends ViewDataBinding {
    public final TextView clipboardIndicator;
    @Bindable
    protected StreamsModel mViewmodel;
    public final TextInputLayout mrlEdit;
    public final RecyclerView mrlList;
    public final ConstraintLayout mrlRoot;
    public final ConstraintLayout mrlbar;
    public final ImageView play;

    public abstract void setViewmodel(StreamsModel streamsModel);

    protected MrlPanelBinding(Object obj, View view, int i, TextView textView, TextInputLayout textInputLayout, RecyclerView recyclerView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView) {
        super(obj, view, i);
        this.clipboardIndicator = textView;
        this.mrlEdit = textInputLayout;
        this.mrlList = recyclerView;
        this.mrlRoot = constraintLayout;
        this.mrlbar = constraintLayout2;
        this.play = imageView;
    }

    public StreamsModel getViewmodel() {
        return this.mViewmodel;
    }

    public static MrlPanelBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlPanelBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MrlPanelBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_panel, viewGroup, z, obj);
    }

    public static MrlPanelBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlPanelBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MrlPanelBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mrl_panel, (ViewGroup) null, false, obj);
    }

    public static MrlPanelBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MrlPanelBinding bind(View view, Object obj) {
        return (MrlPanelBinding) bind(obj, view, R.layout.mrl_panel);
    }
}
