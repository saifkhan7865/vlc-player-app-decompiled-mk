package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class CoverMediaSwitcherItemBinding extends ViewDataBinding {
    public final AppCompatImageView cover;
    public final CardView coverCard;
    public final ImageView nextChapter;
    public final ImageView previousChapter;
    public final TextView songSubtitle;
    public final TextView songTitle;
    public final TextView songTrackInfo;

    protected CoverMediaSwitcherItemBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, CardView cardView, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.cover = appCompatImageView;
        this.coverCard = cardView;
        this.nextChapter = imageView;
        this.previousChapter = imageView2;
        this.songSubtitle = textView;
        this.songTitle = textView2;
        this.songTrackInfo = textView3;
    }

    public static CoverMediaSwitcherItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CoverMediaSwitcherItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (CoverMediaSwitcherItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.cover_media_switcher_item, viewGroup, z, obj);
    }

    public static CoverMediaSwitcherItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CoverMediaSwitcherItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (CoverMediaSwitcherItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.cover_media_switcher_item, (ViewGroup) null, false, obj);
    }

    public static CoverMediaSwitcherItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CoverMediaSwitcherItemBinding bind(View view, Object obj) {
        return (CoverMediaSwitcherItemBinding) bind(obj, view, R.layout.cover_media_switcher_item);
    }
}
