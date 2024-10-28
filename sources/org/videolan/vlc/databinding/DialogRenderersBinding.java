package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.RenderersDialog;

public abstract class DialogRenderersBinding extends ViewDataBinding {
    @Bindable
    protected RenderersDialog.RendererClickhandler mHolder;
    public final Button renderersDisconnect;
    public final RecyclerView renderersList;
    public final TextView renderersTitle;

    public abstract void setHolder(RenderersDialog.RendererClickhandler rendererClickhandler);

    protected DialogRenderersBinding(Object obj, View view, int i, Button button, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.renderersDisconnect = button;
        this.renderersList = recyclerView;
        this.renderersTitle = textView;
    }

    public RenderersDialog.RendererClickhandler getHolder() {
        return this.mHolder;
    }

    public static DialogRenderersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRenderersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogRenderersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_renderers, viewGroup, z, obj);
    }

    public static DialogRenderersBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRenderersBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogRenderersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_renderers, (ViewGroup) null, false, obj);
    }

    public static DialogRenderersBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRenderersBinding bind(View view, Object obj) {
        return (DialogRenderersBinding) bind(obj, view, R.layout.dialog_renderers);
    }
}
