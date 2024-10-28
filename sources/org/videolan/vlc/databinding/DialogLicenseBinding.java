package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.LibraryWithLicense;

public abstract class DialogLicenseBinding extends ViewDataBinding {
    public final TextView copyright;
    public final ImageView licenseButton;
    @Bindable
    protected LibraryWithLicense mLibrary;
    public final TextView textView28;
    public final TextView title;

    public abstract void setLibrary(LibraryWithLicense libraryWithLicense);

    protected DialogLicenseBinding(Object obj, View view, int i, TextView textView, ImageView imageView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.copyright = textView;
        this.licenseButton = imageView;
        this.textView28 = textView2;
        this.title = textView3;
    }

    public LibraryWithLicense getLibrary() {
        return this.mLibrary;
    }

    public static DialogLicenseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLicenseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogLicenseBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_license, viewGroup, z, obj);
    }

    public static DialogLicenseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLicenseBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogLicenseBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_license, (ViewGroup) null, false, obj);
    }

    public static DialogLicenseBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLicenseBinding bind(View view, Object obj) {
        return (DialogLicenseBinding) bind(obj, view, R.layout.dialog_license);
    }
}
