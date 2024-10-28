package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.ExtDeviceHandler;

public abstract class DialogExtDeviceBinding extends ViewDataBinding {
    public final Button extDeviceCancel;
    public final Button extDeviceOpen;
    public final Button extDeviceScan;
    public final TextView extDeviceSummary;
    @Bindable
    protected ExtDeviceHandler mHandler;

    public abstract void setHandler(ExtDeviceHandler extDeviceHandler);

    protected DialogExtDeviceBinding(Object obj, View view, int i, Button button, Button button2, Button button3, TextView textView) {
        super(obj, view, i);
        this.extDeviceCancel = button;
        this.extDeviceOpen = button2;
        this.extDeviceScan = button3;
        this.extDeviceSummary = textView;
    }

    public ExtDeviceHandler getHandler() {
        return this.mHandler;
    }

    public static DialogExtDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogExtDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogExtDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_ext_device, viewGroup, z, obj);
    }

    public static DialogExtDeviceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogExtDeviceBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogExtDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_ext_device, (ViewGroup) null, false, obj);
    }

    public static DialogExtDeviceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogExtDeviceBinding bind(View view, Object obj) {
        return (DialogExtDeviceBinding) bind(obj, view, R.layout.dialog_ext_device);
    }
}
