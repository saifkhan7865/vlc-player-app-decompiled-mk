package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogDuplicationWarningBinding extends ViewDataBinding {
    public final Button addAllButton;
    public final Button addNewButton;
    public final Button cancelButton;
    public final ConstraintLayout myTestRoot;
    public final TextView primaryTextview;
    public final TextView secondaryTextview;

    protected DialogDuplicationWarningBinding(Object obj, View view, int i, Button button, Button button2, Button button3, ConstraintLayout constraintLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.addAllButton = button;
        this.addNewButton = button2;
        this.cancelButton = button3;
        this.myTestRoot = constraintLayout;
        this.primaryTextview = textView;
        this.secondaryTextview = textView2;
    }

    public static DialogDuplicationWarningBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDuplicationWarningBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogDuplicationWarningBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_duplication_warning, viewGroup, z, obj);
    }

    public static DialogDuplicationWarningBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDuplicationWarningBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogDuplicationWarningBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_duplication_warning, (ViewGroup) null, false, obj);
    }

    public static DialogDuplicationWarningBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDuplicationWarningBinding bind(View view, Object obj) {
        return (DialogDuplicationWarningBinding) bind(obj, view, R.layout.dialog_duplication_warning);
    }
}
