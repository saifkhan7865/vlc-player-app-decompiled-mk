package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.HistoryAdapter;

public abstract class HistoryItemCardBinding extends ViewDataBinding {
    public final ConstraintLayout constraintLayout4;
    public final ImageView icon;
    @Bindable
    protected int mBgColor;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected HistoryAdapter.ViewHolder mHolder;
    @Bindable
    protected boolean mIsNetwork;
    @Bindable
    protected boolean mIsOTG;
    @Bindable
    protected boolean mIsSD;
    @Bindable
    protected MediaWrapper mMedia;
    public final ImageView missingMedia;
    public final View missingOverlay;
    public final ImageView networkMedia;
    public final ImageView otgMedia;
    public final ImageView sdMedia;
    public final ImageView songMarker;
    public final TextView subtitle;
    public final TextView title;

    public abstract void setBgColor(int i);

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHolder(HistoryAdapter.ViewHolder viewHolder);

    public abstract void setIsNetwork(boolean z);

    public abstract void setIsOTG(boolean z);

    public abstract void setIsSD(boolean z);

    public abstract void setMedia(MediaWrapper mediaWrapper);

    protected HistoryItemCardBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, View view2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.constraintLayout4 = constraintLayout;
        this.icon = imageView;
        this.missingMedia = imageView2;
        this.missingOverlay = view2;
        this.networkMedia = imageView3;
        this.otgMedia = imageView4;
        this.sdMedia = imageView5;
        this.songMarker = imageView6;
        this.subtitle = textView;
        this.title = textView2;
    }

    public HistoryAdapter.ViewHolder getHolder() {
        return this.mHolder;
    }

    public MediaWrapper getMedia() {
        return this.mMedia;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public boolean getIsNetwork() {
        return this.mIsNetwork;
    }

    public boolean getIsOTG() {
        return this.mIsOTG;
    }

    public boolean getIsSD() {
        return this.mIsSD;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public static HistoryItemCardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HistoryItemCardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (HistoryItemCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.history_item_card, viewGroup, z, obj);
    }

    public static HistoryItemCardBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HistoryItemCardBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (HistoryItemCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.history_item_card, (ViewGroup) null, false, obj);
    }

    public static HistoryItemCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HistoryItemCardBinding bind(View view, Object obj) {
        return (HistoryItemCardBinding) bind(obj, view, R.layout.history_item_card);
    }
}
