package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.vlc.R;

public abstract class AboutAuthorsActivityBinding extends ViewDataBinding {
    public final RecyclerView authorsList;
    public final CoordinatorLayout coordinator;

    protected AboutAuthorsActivityBinding(Object obj, View view, int i, RecyclerView recyclerView, CoordinatorLayout coordinatorLayout) {
        super(obj, view, i);
        this.authorsList = recyclerView;
        this.coordinator = coordinatorLayout;
    }

    public static AboutAuthorsActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AboutAuthorsActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AboutAuthorsActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.about_authors_activity, viewGroup, z, obj);
    }

    public static AboutAuthorsActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AboutAuthorsActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AboutAuthorsActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.about_authors_activity, (ViewGroup) null, false, obj);
    }

    public static AboutAuthorsActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AboutAuthorsActivityBinding bind(View view, Object obj) {
        return (AboutAuthorsActivityBinding) bind(obj, view, R.layout.about_authors_activity);
    }
}
