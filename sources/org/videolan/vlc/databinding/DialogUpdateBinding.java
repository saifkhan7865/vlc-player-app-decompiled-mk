package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogUpdateBinding extends ViewDataBinding {
    public final TextView descriptionText;
    public final Button download;
    public final ProgressBar downloadProgress;
    public final AppCompatImageView imageView18;
    public final CheckBox neverAgain;
    public final TextView nightlyDate;
    public final Button openInBrowser;
    public final TextView title;

    protected DialogUpdateBinding(Object obj, View view, int i, TextView textView, Button button, ProgressBar progressBar, AppCompatImageView appCompatImageView, CheckBox checkBox, TextView textView2, Button button2, TextView textView3) {
        super(obj, view, i);
        this.descriptionText = textView;
        this.download = button;
        this.downloadProgress = progressBar;
        this.imageView18 = appCompatImageView;
        this.neverAgain = checkBox;
        this.nightlyDate = textView2;
        this.openInBrowser = button2;
        this.title = textView3;
    }

    public static DialogUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_update, viewGroup, z, obj);
    }

    public static DialogUpdateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogUpdateBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_update, (ViewGroup) null, false, obj);
    }

    public static DialogUpdateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogUpdateBinding bind(View view, Object obj) {
        return (DialogUpdateBinding) bind(obj, view, R.layout.dialog_update);
    }
}
