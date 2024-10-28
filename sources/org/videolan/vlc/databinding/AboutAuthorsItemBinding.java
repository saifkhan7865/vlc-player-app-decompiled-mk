package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class AboutAuthorsItemBinding extends ViewDataBinding {
    public final TextView authorName;
    public final AppCompatImageView imageView17;
    @Bindable
    protected String mAuthor;

    public abstract void setAuthor(String str);

    protected AboutAuthorsItemBinding(Object obj, View view, int i, TextView textView, AppCompatImageView appCompatImageView) {
        super(obj, view, i);
        this.authorName = textView;
        this.imageView17 = appCompatImageView;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public static AboutAuthorsItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AboutAuthorsItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AboutAuthorsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.about_authors_item, viewGroup, z, obj);
    }

    public static AboutAuthorsItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AboutAuthorsItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AboutAuthorsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.about_authors_item, (ViewGroup) null, false, obj);
    }

    public static AboutAuthorsItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AboutAuthorsItemBinding bind(View view, Object obj) {
        return (AboutAuthorsItemBinding) bind(obj, view, R.layout.about_authors_item);
    }
}
