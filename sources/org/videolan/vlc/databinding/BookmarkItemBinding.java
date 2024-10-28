package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.BookmarkAdapter;

public abstract class BookmarkItemBinding extends ViewDataBinding {
    public final TextView audioItemSubtitle;
    public final TextView audioItemTitle;
    public final AppCompatImageButton itemMore;
    @Bindable
    protected Bookmark mBookmark;
    @Bindable
    protected BookmarkAdapter.ViewHolder mHolder;
    public final View selector;

    public abstract void setBookmark(Bookmark bookmark);

    public abstract void setHolder(BookmarkAdapter.ViewHolder viewHolder);

    protected BookmarkItemBinding(Object obj, View view, int i, TextView textView, TextView textView2, AppCompatImageButton appCompatImageButton, View view2) {
        super(obj, view, i);
        this.audioItemSubtitle = textView;
        this.audioItemTitle = textView2;
        this.itemMore = appCompatImageButton;
        this.selector = view2;
    }

    public BookmarkAdapter.ViewHolder getHolder() {
        return this.mHolder;
    }

    public Bookmark getBookmark() {
        return this.mBookmark;
    }

    public static BookmarkItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BookmarkItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (BookmarkItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bookmark_item, viewGroup, z, obj);
    }

    public static BookmarkItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BookmarkItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (BookmarkItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bookmark_item, (ViewGroup) null, false, obj);
    }

    public static BookmarkItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BookmarkItemBinding bind(View view, Object obj) {
        return (BookmarkItemBinding) bind(obj, view, R.layout.bookmark_item);
    }
}
