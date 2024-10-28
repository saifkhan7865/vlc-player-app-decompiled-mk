package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.SelectChapterDialog;

public abstract class ChapterListItemBinding extends ViewDataBinding {
    public final TextView chapterTime;
    public final TextView chapterTitle;
    @Bindable
    protected SelectChapterDialog.Chapter mChapter;
    @Bindable
    protected SelectChapterDialog.ChapterViewHolder mHolder;
    @Bindable
    protected Boolean mSelected;

    public abstract void setChapter(SelectChapterDialog.Chapter chapter);

    public abstract void setHolder(SelectChapterDialog.ChapterViewHolder chapterViewHolder);

    public abstract void setSelected(Boolean bool);

    protected ChapterListItemBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.chapterTime = textView;
        this.chapterTitle = textView2;
    }

    public Boolean getSelected() {
        return this.mSelected;
    }

    public SelectChapterDialog.Chapter getChapter() {
        return this.mChapter;
    }

    public SelectChapterDialog.ChapterViewHolder getHolder() {
        return this.mHolder;
    }

    public static ChapterListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ChapterListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ChapterListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.chapter_list_item, viewGroup, z, obj);
    }

    public static ChapterListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ChapterListItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ChapterListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.chapter_list_item, (ViewGroup) null, false, obj);
    }

    public static ChapterListItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ChapterListItemBinding bind(View view, Object obj) {
        return (ChapterListItemBinding) bind(obj, view, R.layout.chapter_list_item);
    }
}
