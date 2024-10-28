package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.vlc.R;

public abstract class DialogAddToGroupBinding extends ViewDataBinding {
    public final FrameLayout dialogListContainer;
    public final TextView empty;
    public final RecyclerView list;
    @Bindable
    protected Boolean mIsLoading;
    public final ProgressBar progressBar2;
    public final TextView textView8;

    public abstract void setIsLoading(Boolean bool);

    protected DialogAddToGroupBinding(Object obj, View view, int i, FrameLayout frameLayout, TextView textView, RecyclerView recyclerView, ProgressBar progressBar, TextView textView2) {
        super(obj, view, i);
        this.dialogListContainer = frameLayout;
        this.empty = textView;
        this.list = recyclerView;
        this.progressBar2 = progressBar;
        this.textView8 = textView2;
    }

    public Boolean getIsLoading() {
        return this.mIsLoading;
    }

    public static DialogAddToGroupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddToGroupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogAddToGroupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_add_to_group, viewGroup, z, obj);
    }

    public static DialogAddToGroupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddToGroupBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogAddToGroupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_add_to_group, (ViewGroup) null, false, obj);
    }

    public static DialogAddToGroupBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddToGroupBinding bind(View view, Object obj) {
        return (DialogAddToGroupBinding) bind(obj, view, R.layout.dialog_add_to_group);
    }
}
