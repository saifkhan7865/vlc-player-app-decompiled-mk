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
import org.videolan.vlc.gui.LibraryWithLicense;

public abstract class LibraryItemBinding extends ViewDataBinding {
    public final AppCompatImageView imageView17;
    @Bindable
    protected LibraryWithLicense mLibrary;
    public final TextView textView3;
    public final TextView textView31;
    public final TextView title;

    public abstract void setLibrary(LibraryWithLicense libraryWithLicense);

    protected LibraryItemBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, TextView textView, TextView textView2, TextView textView4) {
        super(obj, view, i);
        this.imageView17 = appCompatImageView;
        this.textView3 = textView;
        this.textView31 = textView2;
        this.title = textView4;
    }

    public LibraryWithLicense getLibrary() {
        return this.mLibrary;
    }

    public static LibraryItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LibraryItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LibraryItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.library_item, viewGroup, z, obj);
    }

    public static LibraryItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LibraryItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LibraryItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.library_item, (ViewGroup) null, false, obj);
    }

    public static LibraryItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LibraryItemBinding bind(View view, Object obj) {
        return (LibraryItemBinding) bind(obj, view, R.layout.library_item);
    }
}
