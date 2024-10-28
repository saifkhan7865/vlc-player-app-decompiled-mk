package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;

public abstract class ContextualSheetBinding extends ViewDataBinding {
    public final ImageView ctxCover;
    public final CardView ctxCoverCard;
    public final ConstraintLayout ctxCoverLayout;
    public final TextView ctxCoverTitle;
    public final RecyclerView ctxList;
    public final TextView ctxTitle;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected MediaLibraryItem mItem;
    @Bindable
    protected boolean mShowCover;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setShowCover(boolean z);

    protected ContextualSheetBinding(Object obj, View view, int i, ImageView imageView, CardView cardView, ConstraintLayout constraintLayout, TextView textView, RecyclerView recyclerView, TextView textView2) {
        super(obj, view, i);
        this.ctxCover = imageView;
        this.ctxCoverCard = cardView;
        this.ctxCoverLayout = constraintLayout;
        this.ctxCoverTitle = textView;
        this.ctxList = recyclerView;
        this.ctxTitle = textView2;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public boolean getShowCover() {
        return this.mShowCover;
    }

    public static ContextualSheetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContextualSheetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ContextualSheetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.contextual_sheet, viewGroup, z, obj);
    }

    public static ContextualSheetBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContextualSheetBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ContextualSheetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.contextual_sheet, (ViewGroup) null, false, obj);
    }

    public static ContextualSheetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContextualSheetBinding bind(View view, Object obj) {
        return (ContextualSheetBinding) bind(obj, view, R.layout.contextual_sheet);
    }
}
