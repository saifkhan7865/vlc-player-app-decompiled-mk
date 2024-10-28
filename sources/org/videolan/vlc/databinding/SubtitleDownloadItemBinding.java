package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.Barrier;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

public abstract class SubtitleDownloadItemBinding extends ViewDataBinding {
    public final Barrier barrier;
    public final AppCompatImageView downloadSub;
    public final TextView language;
    public final ProgressBar loading;
    @Bindable
    protected SubtitleItem mSubtitleItem;
    public final TextView subTitle;

    public abstract void setSubtitleItem(SubtitleItem subtitleItem);

    protected SubtitleDownloadItemBinding(Object obj, View view, int i, Barrier barrier2, AppCompatImageView appCompatImageView, TextView textView, ProgressBar progressBar, TextView textView2) {
        super(obj, view, i);
        this.barrier = barrier2;
        this.downloadSub = appCompatImageView;
        this.language = textView;
        this.loading = progressBar;
        this.subTitle = textView2;
    }

    public SubtitleItem getSubtitleItem() {
        return this.mSubtitleItem;
    }

    public static SubtitleDownloadItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SubtitleDownloadItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SubtitleDownloadItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.subtitle_download_item, viewGroup, z, obj);
    }

    public static SubtitleDownloadItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SubtitleDownloadItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SubtitleDownloadItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.subtitle_download_item, (ViewGroup) null, false, obj);
    }

    public static SubtitleDownloadItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SubtitleDownloadItemBinding bind(View view, Object obj) {
        return (SubtitleDownloadItemBinding) bind(obj, view, R.layout.subtitle_download_item);
    }
}
