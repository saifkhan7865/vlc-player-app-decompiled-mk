package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.VlcLoginDialog;

public abstract class VlcLoginDialogBinding extends ViewDataBinding {
    public final Button action;
    public final Button cancel;
    public final EditText login;
    public final TextInputLayout loginContainer;
    @Bindable
    protected Dialog.LoginDialog mDialog;
    @Bindable
    protected VlcLoginDialog mHandler;
    public final EditText password;
    public final TextInputLayout passwordContainer;
    public final CheckBox store;
    public final TextView text;
    public final TextView warning;

    public abstract void setDialog(Dialog.LoginDialog loginDialog);

    public abstract void setHandler(VlcLoginDialog vlcLoginDialog);

    protected VlcLoginDialogBinding(Object obj, View view, int i, Button button, Button button2, EditText editText, TextInputLayout textInputLayout, EditText editText2, TextInputLayout textInputLayout2, CheckBox checkBox, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.action = button;
        this.cancel = button2;
        this.login = editText;
        this.loginContainer = textInputLayout;
        this.password = editText2;
        this.passwordContainer = textInputLayout2;
        this.store = checkBox;
        this.text = textView;
        this.warning = textView2;
    }

    public Dialog.LoginDialog getDialog() {
        return this.mDialog;
    }

    public VlcLoginDialog getHandler() {
        return this.mHandler;
    }

    public static VlcLoginDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcLoginDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (VlcLoginDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vlc_login_dialog, viewGroup, z, obj);
    }

    public static VlcLoginDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcLoginDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (VlcLoginDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vlc_login_dialog, (ViewGroup) null, false, obj);
    }

    public static VlcLoginDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcLoginDialogBinding bind(View view, Object obj) {
        return (VlcLoginDialogBinding) bind(obj, view, R.layout.vlc_login_dialog);
    }
}
