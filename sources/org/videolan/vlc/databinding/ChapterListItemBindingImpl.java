package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.gui.dialogs.SelectChapterDialog;
import org.videolan.vlc.gui.helpers.UiToolsKt;

public class ChapterListItemBindingImpl extends ChapterListItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public ChapterListItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ChapterListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        this.chapterTime.setTag((Object) null);
        this.chapterTitle.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (BR.chapter == i) {
            setChapter((SelectChapterDialog.Chapter) obj);
        } else if (BR.holder == i) {
            setHolder((SelectChapterDialog.ChapterViewHolder) obj);
        } else if (BR.selected != i) {
            return false;
        } else {
            setSelected((Boolean) obj);
        }
        return true;
    }

    public void setChapter(SelectChapterDialog.Chapter chapter) {
        this.mChapter = chapter;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.chapter);
        super.requestRebind();
    }

    public void setHolder(SelectChapterDialog.ChapterViewHolder chapterViewHolder) {
        this.mHolder = chapterViewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setSelected(Boolean bool) {
        this.mSelected = bool;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.selected);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SelectChapterDialog.Chapter chapter = this.mChapter;
        SelectChapterDialog.ChapterViewHolder chapterViewHolder = this.mHolder;
        Boolean bool = this.mSelected;
        long j2 = 9 & j;
        OnClickListenerImpl onClickListenerImpl = null;
        if (j2 == 0 || chapter == null) {
            str2 = null;
            str = null;
        } else {
            str = chapter.getTime();
            str2 = chapter.getName();
        }
        long j3 = 10 & j;
        if (!(j3 == 0 || chapterViewHolder == null)) {
            OnClickListenerImpl onClickListenerImpl2 = this.mHolderOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mHolderOnClickAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(chapterViewHolder);
        }
        long j4 = j & 12;
        boolean safeUnbox = j4 != 0 ? ViewDataBinding.safeUnbox(bool) : false;
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.chapterTime, str);
            TextViewBindingAdapter.setText(this.chapterTitle, str2);
        }
        if (j4 != 0) {
            UiToolsKt.isSelected(this.chapterTitle, bool);
            this.mboundView0.setSelected(safeUnbox);
        }
        if (j3 != 0) {
            this.mboundView0.setOnClickListener(onClickListenerImpl);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private SelectChapterDialog.ChapterViewHolder value;

        public OnClickListenerImpl setValue(SelectChapterDialog.ChapterViewHolder chapterViewHolder) {
            this.value = chapterViewHolder;
            if (chapterViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }
}
