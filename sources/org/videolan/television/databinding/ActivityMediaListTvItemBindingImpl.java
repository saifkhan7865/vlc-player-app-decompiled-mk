package org.videolan.television.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.BR;
import org.videolan.television.R;
import org.videolan.television.ui.details.MediaListAdapter;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

public class ActivityMediaListTvItemBindingImpl extends ActivityMediaListTvItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl3 mHolderOnClickAddToPlaylistAndroidViewViewOnClickListener;
    private OnClickListenerImpl6 mHolderOnClickAppendAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mHolderOnClickMoveDownAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHolderOnClickMoveUpAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHolderOnClickPlayAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mHolderOnClickPlayNextAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHolderOnClickRemoveAndroidViewViewOnClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.item_play, 12);
    }

    public ActivityMediaListTvItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private ActivityMediaListTvItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[0], objArr[1], objArr[10], objArr[9], objArr[8], objArr[4], objArr[6], objArr[5], objArr[12], objArr[7], objArr[11], objArr[3], objArr[2]);
        this.mDirtyFlags = -1;
        this.container.setTag((Object) null);
        this.imCover.setTag((Object) null);
        this.itemAddPlaylist.setTag((Object) null);
        this.itemAppend.setTag((Object) null);
        this.itemInsertNext.setTag((Object) null);
        this.itemLength.setTag((Object) null);
        this.itemMoveDown.setTag((Object) null);
        this.itemMoveUp.setTag((Object) null);
        this.itemRemove.setTag((Object) null);
        this.itemSelector.setTag((Object) null);
        this.itemSubtitle.setTag((Object) null);
        this.title.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (BR.item == i) {
            setItem((MediaWrapper) obj);
        } else if (BR.subtitle == i) {
            setSubtitle((String) obj);
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.holder != i) {
            return false;
        } else {
            setHolder((MediaListAdapter.MediaListViewHolder) obj);
        }
        return true;
    }

    public void setItem(MediaWrapper mediaWrapper) {
        this.mItem = mediaWrapper;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.subtitle);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setHolder(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
        this.mHolder = mediaListViewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 8;
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
        String str3;
        long j3;
        OnClickListenerImpl2 onClickListenerImpl2;
        OnClickListenerImpl1 onClickListenerImpl1;
        OnClickListenerImpl5 onClickListenerImpl5;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl4 onClickListenerImpl4;
        OnClickListenerImpl6 onClickListenerImpl6;
        long j4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaWrapper mediaWrapper = this.mItem;
        String str4 = this.mSubtitle;
        BitmapDrawable bitmapDrawable = this.mCover;
        MediaListAdapter.MediaListViewHolder mediaListViewHolder = this.mHolder;
        long j5 = 17 & j;
        OnClickListenerImpl3 onClickListenerImpl3 = null;
        if (j5 != 0) {
            if (mediaWrapper != null) {
                str2 = mediaWrapper.getTitle();
                j4 = mediaWrapper.getLength();
            } else {
                j4 = 0;
                str2 = null;
            }
            str = Tools.millisToString(j4);
        } else {
            str2 = null;
            str = null;
        }
        long j6 = 18 & j;
        long j7 = j & 20;
        long j8 = j & 24;
        if (j8 == 0 || mediaListViewHolder == null) {
            str3 = str4;
            onClickListenerImpl6 = null;
            onClickListenerImpl4 = null;
            onClickListenerImpl = null;
            onClickListenerImpl5 = null;
            j2 = j6;
            j3 = 0;
            onClickListenerImpl1 = null;
            onClickListenerImpl2 = null;
        } else {
            OnClickListenerImpl onClickListenerImpl7 = this.mHolderOnClickMoveUpAndroidViewViewOnClickListener;
            if (onClickListenerImpl7 == null) {
                onClickListenerImpl7 = new OnClickListenerImpl();
                this.mHolderOnClickMoveUpAndroidViewViewOnClickListener = onClickListenerImpl7;
            }
            OnClickListenerImpl value = onClickListenerImpl7.setValue(mediaListViewHolder);
            OnClickListenerImpl1 onClickListenerImpl12 = this.mHolderOnClickRemoveAndroidViewViewOnClickListener;
            if (onClickListenerImpl12 == null) {
                onClickListenerImpl12 = new OnClickListenerImpl1();
                this.mHolderOnClickRemoveAndroidViewViewOnClickListener = onClickListenerImpl12;
            }
            OnClickListenerImpl1 value2 = onClickListenerImpl12.setValue(mediaListViewHolder);
            OnClickListenerImpl2 onClickListenerImpl22 = this.mHolderOnClickPlayAndroidViewViewOnClickListener;
            if (onClickListenerImpl22 == null) {
                onClickListenerImpl22 = new OnClickListenerImpl2();
                this.mHolderOnClickPlayAndroidViewViewOnClickListener = onClickListenerImpl22;
            }
            OnClickListenerImpl2 value3 = onClickListenerImpl22.setValue(mediaListViewHolder);
            OnClickListenerImpl1 onClickListenerImpl13 = value2;
            OnClickListenerImpl3 onClickListenerImpl32 = this.mHolderOnClickAddToPlaylistAndroidViewViewOnClickListener;
            if (onClickListenerImpl32 == null) {
                onClickListenerImpl32 = new OnClickListenerImpl3();
                this.mHolderOnClickAddToPlaylistAndroidViewViewOnClickListener = onClickListenerImpl32;
            }
            OnClickListenerImpl3 value4 = onClickListenerImpl32.setValue(mediaListViewHolder);
            OnClickListenerImpl4 onClickListenerImpl42 = this.mHolderOnClickPlayNextAndroidViewViewOnClickListener;
            if (onClickListenerImpl42 == null) {
                onClickListenerImpl42 = new OnClickListenerImpl4();
                this.mHolderOnClickPlayNextAndroidViewViewOnClickListener = onClickListenerImpl42;
            }
            OnClickListenerImpl4 value5 = onClickListenerImpl42.setValue(mediaListViewHolder);
            OnClickListenerImpl5 onClickListenerImpl52 = this.mHolderOnClickMoveDownAndroidViewViewOnClickListener;
            if (onClickListenerImpl52 == null) {
                onClickListenerImpl52 = new OnClickListenerImpl5();
                this.mHolderOnClickMoveDownAndroidViewViewOnClickListener = onClickListenerImpl52;
            }
            OnClickListenerImpl5 value6 = onClickListenerImpl52.setValue(mediaListViewHolder);
            OnClickListenerImpl6 onClickListenerImpl62 = this.mHolderOnClickAppendAndroidViewViewOnClickListener;
            if (onClickListenerImpl62 == null) {
                onClickListenerImpl62 = new OnClickListenerImpl6();
                this.mHolderOnClickAppendAndroidViewViewOnClickListener = onClickListenerImpl62;
            }
            onClickListenerImpl6 = onClickListenerImpl62.setValue(mediaListViewHolder);
            onClickListenerImpl5 = value6;
            j3 = 0;
            long j9 = j6;
            onClickListenerImpl2 = value3;
            onClickListenerImpl1 = onClickListenerImpl13;
            onClickListenerImpl4 = value5;
            str3 = str4;
            onClickListenerImpl = value;
            onClickListenerImpl3 = value4;
            j2 = j9;
        }
        int i = (j7 > j3 ? 1 : (j7 == j3 ? 0 : -1));
        OnClickListenerImpl2 onClickListenerImpl23 = onClickListenerImpl2;
        if (i != 0) {
            ViewBindingAdapter.setBackground(this.imCover, bitmapDrawable);
        }
        if (j5 != j3) {
            ImageLoaderKt.loadImage(this.imCover, mediaWrapper, 0, true, false);
            TextViewBindingAdapter.setText(this.itemLength, str);
            TextViewBindingAdapter.setText(this.title, str2);
        }
        if (j8 != 0) {
            this.itemAddPlaylist.setOnClickListener(onClickListenerImpl3);
            this.itemAppend.setOnClickListener(onClickListenerImpl6);
            this.itemInsertNext.setOnClickListener(onClickListenerImpl4);
            this.itemMoveDown.setOnClickListener(onClickListenerImpl5);
            this.itemMoveUp.setOnClickListener(onClickListenerImpl);
            this.itemRemove.setOnClickListener(onClickListenerImpl1);
            this.itemSelector.setOnClickListener(onClickListenerImpl23);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.itemSubtitle, str3);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private MediaListAdapter.MediaListViewHolder value;

        public OnClickListenerImpl setValue(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
            this.value = mediaListViewHolder;
            if (mediaListViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClickMoveUp(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private MediaListAdapter.MediaListViewHolder value;

        public OnClickListenerImpl1 setValue(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
            this.value = mediaListViewHolder;
            if (mediaListViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClickRemove(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private MediaListAdapter.MediaListViewHolder value;

        public OnClickListenerImpl2 setValue(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
            this.value = mediaListViewHolder;
            if (mediaListViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClickPlay(view);
        }
    }

    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private MediaListAdapter.MediaListViewHolder value;

        public OnClickListenerImpl3 setValue(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
            this.value = mediaListViewHolder;
            if (mediaListViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClickAddToPlaylist(view);
        }
    }

    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private MediaListAdapter.MediaListViewHolder value;

        public OnClickListenerImpl4 setValue(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
            this.value = mediaListViewHolder;
            if (mediaListViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClickPlayNext(view);
        }
    }

    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private MediaListAdapter.MediaListViewHolder value;

        public OnClickListenerImpl5 setValue(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
            this.value = mediaListViewHolder;
            if (mediaListViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClickMoveDown(view);
        }
    }

    public static class OnClickListenerImpl6 implements View.OnClickListener {
        private MediaListAdapter.MediaListViewHolder value;

        public OnClickListenerImpl6 setValue(MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
            this.value = mediaListViewHolder;
            if (mediaListViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClickAppend(view);
        }
    }
}
