package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.libvlc.RendererItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.RenderersDialog;

public abstract class ItemRendererBinding extends ViewDataBinding {
    @Bindable
    protected RenderersDialog.RendererClickhandler mClicHandler;
    @Bindable
    protected RendererItem mRenderer;
    public final AppCompatImageView rendererIcon;
    public final TextView rendererName;

    public abstract void setClicHandler(RenderersDialog.RendererClickhandler rendererClickhandler);

    public abstract void setRenderer(RendererItem rendererItem);

    protected ItemRendererBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, TextView textView) {
        super(obj, view, i);
        this.rendererIcon = appCompatImageView;
        this.rendererName = textView;
    }

    public RendererItem getRenderer() {
        return this.mRenderer;
    }

    public RenderersDialog.RendererClickhandler getClicHandler() {
        return this.mClicHandler;
    }

    public static ItemRendererBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRendererBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemRendererBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_renderer, viewGroup, z, obj);
    }

    public static ItemRendererBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRendererBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemRendererBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_renderer, (ViewGroup) null, false, obj);
    }

    public static ItemRendererBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRendererBinding bind(View view, Object obj) {
        return (ItemRendererBinding) bind(obj, view, R.layout.item_renderer);
    }
}
