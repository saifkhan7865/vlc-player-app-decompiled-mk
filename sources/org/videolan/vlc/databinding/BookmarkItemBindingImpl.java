package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.vlc.BR;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.gui.helpers.BookmarkAdapter;
import org.videolan.vlc.gui.helpers.TalkbackUtil;
import org.videolan.vlc.gui.helpers.UiToolsKt;

public class BookmarkItemBindingImpl extends BookmarkItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback1;
    private final View.OnClickListener mCallback2;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public BookmarkItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private BookmarkItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[2], objArr[4], objArr[1]);
        this.mDirtyFlags = -1;
        this.audioItemSubtitle.setTag((Object) null);
        this.audioItemTitle.setTag((Object) null);
        this.itemMore.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.selector.setTag((Object) null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
        this.mCallback2 = new OnClickListener(this, 2);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.bookmark == i) {
            setBookmark((Bookmark) obj);
        } else if (BR.holder != i) {
            return false;
        } else {
            setHolder((BookmarkAdapter.ViewHolder) obj);
        }
        return true;
    }

    public void setBookmark(Bookmark bookmark) {
        this.mBookmark = bookmark;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.bookmark);
        super.requestRebind();
    }

    public void setHolder(BookmarkAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        String str2;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Bookmark bookmark = this.mBookmark;
        BookmarkAdapter.ViewHolder viewHolder = this.mHolder;
        long j3 = 5 & j;
        String str3 = null;
        if (j3 != 0) {
            if (bookmark != null) {
                j2 = bookmark.getTime();
                str2 = bookmark.getTitle();
            } else {
                str2 = null;
                j2 = 0;
            }
            str = TalkbackUtil.INSTANCE.millisToString(getRoot().getContext(), j2);
            str3 = Tools.millisToString(j2);
        } else {
            str2 = null;
            str = null;
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.audioItemSubtitle, str3);
            TextViewBindingAdapter.setText(this.audioItemTitle, str2);
            if (getBuildSdkInt() >= 4) {
                this.audioItemSubtitle.setContentDescription(str);
            }
        }
        if ((j & 4) != 0) {
            UiToolsKt.setEllipsizeModeByPref(this.audioItemTitle, true);
            this.itemMore.setOnClickListener(this.mCallback2);
            this.selector.setOnClickListener(this.mCallback1);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        if (i == 1) {
            Bookmark bookmark = this.mBookmark;
            BookmarkAdapter.ViewHolder viewHolder = this.mHolder;
            if (viewHolder != null) {
                viewHolder.onClick(view, bookmark);
            }
        } else if (i == 2) {
            Bookmark bookmark2 = this.mBookmark;
            BookmarkAdapter.ViewHolder viewHolder2 = this.mHolder;
            if (viewHolder2 != null) {
                viewHolder2.onMoreClick(view, bookmark2);
            }
        }
    }
}
