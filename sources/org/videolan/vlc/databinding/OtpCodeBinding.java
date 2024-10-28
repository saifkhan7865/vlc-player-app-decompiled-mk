package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class OtpCodeBinding extends ViewDataBinding {
    public final Button cancelButton;
    public final TextView code1;
    public final TextView code2;
    public final TextView code3;
    public final TextView code4;
    public final TextView title;
    public final TextView title2;

    protected OtpCodeBinding(Object obj, View view, int i, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.cancelButton = button;
        this.code1 = textView;
        this.code2 = textView2;
        this.code3 = textView3;
        this.code4 = textView4;
        this.title = textView5;
        this.title2 = textView6;
    }

    public static OtpCodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OtpCodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (OtpCodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.otp_code, viewGroup, z, obj);
    }

    public static OtpCodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OtpCodeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (OtpCodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.otp_code, (ViewGroup) null, false, obj);
    }

    public static OtpCodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OtpCodeBinding bind(View view, Object obj) {
        return (OtpCodeBinding) bind(obj, view, R.layout.otp_code);
    }
}
