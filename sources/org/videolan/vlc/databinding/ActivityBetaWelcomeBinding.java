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

public abstract class ActivityBetaWelcomeBinding extends ViewDataBinding {
    public final Button betaOkButton;
    public final ImageView imageView6;
    public final ImageView imageView7;
    public final TextView textView20;
    public final TextView textView23;
    public final TextView textView24;
    public final TextView textView25;

    protected ActivityBetaWelcomeBinding(Object obj, View view, int i, Button button, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.betaOkButton = button;
        this.imageView6 = imageView;
        this.imageView7 = imageView2;
        this.textView20 = textView;
        this.textView23 = textView2;
        this.textView24 = textView3;
        this.textView25 = textView4;
    }

    public static ActivityBetaWelcomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBetaWelcomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityBetaWelcomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_beta_welcome, viewGroup, z, obj);
    }

    public static ActivityBetaWelcomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBetaWelcomeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityBetaWelcomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_beta_welcome, (ViewGroup) null, false, obj);
    }

    public static ActivityBetaWelcomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBetaWelcomeBinding bind(View view, Object obj) {
        return (ActivityBetaWelcomeBinding) bind(obj, view, R.layout.activity_beta_welcome);
    }
}
