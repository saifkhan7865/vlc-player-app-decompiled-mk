package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class SendCrashActivityBinding extends ViewDataBinding {
    public final ConstraintLayout crashFirstStepContainer;
    public final ConstraintLayout crashSecondStepContainer;
    public final ImageView imageView5;
    public final Switch includeMedialibSwitch;
    public final Button reportBugButton;
    public final Button reportCrashButton;
    public final Button sendCrashButton;
    public final ProgressBar sendCrashProgress;
    public final TextView textView15;
    public final TextView textView18;
    public final TextView textView19;
    public final TextView textView21;

    protected SendCrashActivityBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, Switch switchR, Button button, Button button2, Button button3, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.crashFirstStepContainer = constraintLayout;
        this.crashSecondStepContainer = constraintLayout2;
        this.imageView5 = imageView;
        this.includeMedialibSwitch = switchR;
        this.reportBugButton = button;
        this.reportCrashButton = button2;
        this.sendCrashButton = button3;
        this.sendCrashProgress = progressBar;
        this.textView15 = textView;
        this.textView18 = textView2;
        this.textView19 = textView3;
        this.textView21 = textView4;
    }

    public static SendCrashActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SendCrashActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SendCrashActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.send_crash_activity, viewGroup, z, obj);
    }

    public static SendCrashActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SendCrashActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SendCrashActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.send_crash_activity, (ViewGroup) null, false, obj);
    }

    public static SendCrashActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SendCrashActivityBinding bind(View view, Object obj) {
        return (SendCrashActivityBinding) bind(obj, view, R.layout.send_crash_activity);
    }
}
