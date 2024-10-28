package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.VlcProgressDialog;

public abstract class VlcProgressDialogBinding extends ViewDataBinding {
    public final Button cancel;
    @Bindable
    protected Dialog.ProgressDialog mDialog;
    @Bindable
    protected VlcProgressDialog mHandler;
    public final ContentLoadingProgressBar progress;
    public final TextView text;

    public abstract void setDialog(Dialog.ProgressDialog progressDialog);

    public abstract void setHandler(VlcProgressDialog vlcProgressDialog);

    protected VlcProgressDialogBinding(Object obj, View view, int i, Button button, ContentLoadingProgressBar contentLoadingProgressBar, TextView textView) {
        super(obj, view, i);
        this.cancel = button;
        this.progress = contentLoadingProgressBar;
        this.text = textView;
    }

    public Dialog.ProgressDialog getDialog() {
        return this.mDialog;
    }

    public VlcProgressDialog getHandler() {
        return this.mHandler;
    }

    public static VlcProgressDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcProgressDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (VlcProgressDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vlc_progress_dialog, viewGroup, z, obj);
    }

    public static VlcProgressDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcProgressDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (VlcProgressDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vlc_progress_dialog, (ViewGroup) null, false, obj);
    }

    public static VlcProgressDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcProgressDialogBinding bind(View view, Object obj) {
        return (VlcProgressDialogBinding) bind(obj, view, R.layout.vlc_progress_dialog);
    }
}
