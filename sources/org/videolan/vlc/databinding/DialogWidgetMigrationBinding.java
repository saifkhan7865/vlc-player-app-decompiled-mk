package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogWidgetMigrationBinding extends ViewDataBinding {
    public final ImageView imageView14;
    public final TextView textView32;
    public final TextView textView33;
    public final TextView textView34;
    public final TextView title;

    protected DialogWidgetMigrationBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.imageView14 = imageView;
        this.textView32 = textView;
        this.textView33 = textView2;
        this.textView34 = textView3;
        this.title = textView4;
    }

    public static DialogWidgetMigrationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWidgetMigrationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogWidgetMigrationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_widget_migration, viewGroup, z, obj);
    }

    public static DialogWidgetMigrationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWidgetMigrationBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogWidgetMigrationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_widget_migration, (ViewGroup) null, false, obj);
    }

    public static DialogWidgetMigrationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWidgetMigrationBinding bind(View view, Object obj) {
        return (DialogWidgetMigrationBinding) bind(obj, view, R.layout.dialog_widget_migration);
    }
}
