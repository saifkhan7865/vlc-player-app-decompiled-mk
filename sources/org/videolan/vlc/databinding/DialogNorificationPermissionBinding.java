package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogNorificationPermissionBinding extends ViewDataBinding {
    public final ImageView notficationPermissionIcon;
    public final TextView notficationPermissionText;
    public final Button okButton;
    public final TextView title;

    protected DialogNorificationPermissionBinding(Object obj, View view, int i, ImageView imageView, TextView textView, Button button, TextView textView2) {
        super(obj, view, i);
        this.notficationPermissionIcon = imageView;
        this.notficationPermissionText = textView;
        this.okButton = button;
        this.title = textView2;
    }

    public static DialogNorificationPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNorificationPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogNorificationPermissionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_norification_permission, viewGroup, z, obj);
    }

    public static DialogNorificationPermissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNorificationPermissionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogNorificationPermissionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_norification_permission, (ViewGroup) null, false, obj);
    }

    public static DialogNorificationPermissionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNorificationPermissionBinding bind(View view, Object obj) {
        return (DialogNorificationPermissionBinding) bind(obj, view, R.layout.dialog_norification_permission);
    }
}
