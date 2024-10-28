package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;

public abstract class LicenseActivityBinding extends ViewDataBinding {
    public final CoordinatorLayout coordinator;
    public final RecyclerView licenses;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected MediaLibraryItem mPlaylist;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setPlaylist(MediaLibraryItem mediaLibraryItem);

    protected LicenseActivityBinding(Object obj, View view, int i, CoordinatorLayout coordinatorLayout, RecyclerView recyclerView) {
        super(obj, view, i);
        this.coordinator = coordinatorLayout;
        this.licenses = recyclerView;
    }

    public MediaLibraryItem getPlaylist() {
        return this.mPlaylist;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public static LicenseActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LicenseActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LicenseActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.license_activity, viewGroup, z, obj);
    }

    public static LicenseActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LicenseActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LicenseActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.license_activity, (ViewGroup) null, false, obj);
    }

    public static LicenseActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LicenseActivityBinding bind(View view, Object obj) {
        return (LicenseActivityBinding) bind(obj, view, R.layout.license_activity);
    }
}
