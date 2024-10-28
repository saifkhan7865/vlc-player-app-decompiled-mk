package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SimpleAdapter;

public abstract class SimpleItemBinding extends ViewDataBinding {
    public final ImageView imageView8;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected SimpleAdapter.ClickHandler mHandler;
    @Bindable
    protected int mImageWidth;
    @Bindable
    protected MediaLibraryItem mItem;
    @Bindable
    protected int mPosition;
    @Bindable
    protected boolean mSelected;
    public final ImageView mlItemOverlay;
    public final ImageView selectedCheck;
    public final TextView subtitle;
    public final TextView textView16;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHandler(SimpleAdapter.ClickHandler clickHandler);

    public abstract void setImageWidth(int i);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setPosition(int i);

    public abstract void setSelected(boolean z);

    protected SimpleItemBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.imageView8 = imageView;
        this.mlItemOverlay = imageView2;
        this.selectedCheck = imageView3;
        this.subtitle = textView;
        this.textView16 = textView2;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public SimpleAdapter.ClickHandler getHandler() {
        return this.mHandler;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public boolean getSelected() {
        return this.mSelected;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public static SimpleItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SimpleItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SimpleItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.simple_item, viewGroup, z, obj);
    }

    public static SimpleItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SimpleItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SimpleItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.simple_item, (ViewGroup) null, false, obj);
    }

    public static SimpleItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SimpleItemBinding bind(View view, Object obj) {
        return (SimpleItemBinding) bind(obj, view, R.layout.simple_item);
    }
}
