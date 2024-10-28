package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.CtxMenuItem;

public abstract class ContextItemBinding extends ViewDataBinding {
    public final ImageView contextOptionIcon;
    public final TextView contextOptionTitle;
    @Bindable
    protected CtxMenuItem mMenuItem;

    public abstract void setMenuItem(CtxMenuItem ctxMenuItem);

    protected ContextItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.contextOptionIcon = imageView;
        this.contextOptionTitle = textView;
    }

    public CtxMenuItem getMenuItem() {
        return this.mMenuItem;
    }

    public static ContextItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContextItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ContextItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.context_item, viewGroup, z, obj);
    }

    public static ContextItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContextItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ContextItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.context_item, (ViewGroup) null, false, obj);
    }

    public static ContextItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContextItemBinding bind(View view, Object obj) {
        return (ContextItemBinding) bind(obj, view, R.layout.context_item);
    }
}
