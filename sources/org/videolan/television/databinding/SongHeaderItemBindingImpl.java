package org.videolan.television.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import org.videolan.television.BR;
import org.videolan.television.R;
import org.videolan.television.ui.MediaHeaderAdapter;

public class SongHeaderItemBindingImpl extends SongHeaderItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public SongHeaderItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private SongHeaderItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1]);
        this.mDirtyFlags = -1;
        this.header.setTag((Object) null);
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
        if (BR.headerText == i) {
            setHeaderText((String) obj);
        } else if (BR.hasContent == i) {
            setHasContent((Boolean) obj);
        } else if (BR.holder != i) {
            return false;
        } else {
            setHolder((MediaHeaderAdapter.ViewHolder) obj);
        }
        return true;
    }

    public void setHeaderText(String str) {
        this.mHeaderText = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.headerText);
        super.requestRebind();
    }

    public void setHasContent(Boolean bool) {
        this.mHasContent = bool;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.hasContent);
        super.requestRebind();
    }

    public void setHolder(MediaHeaderAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        Drawable drawable;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mHeaderText;
        Boolean bool = this.mHasContent;
        MediaHeaderAdapter.ViewHolder viewHolder = this.mHolder;
        long j2 = j & 10;
        OnClickListenerImpl onClickListenerImpl = null;
        if (j2 != 0) {
            z = ViewDataBinding.safeUnbox(bool);
            if (j2 != 0) {
                j |= z ? 32 : 16;
            }
            drawable = AppCompatResources.getDrawable(this.mboundView0.getContext(), z ? R.drawable.header_background_content : R.drawable.header_background_no_content);
        } else {
            z = false;
            drawable = null;
        }
        long j3 = 12 & j;
        if (!(j3 == 0 || viewHolder == null)) {
            OnClickListenerImpl onClickListenerImpl2 = this.mHolderOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mHolderOnClickAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(viewHolder);
        }
        if ((9 & j) != 0) {
            TextViewBindingAdapter.setText(this.header, str);
        }
        if ((j & 10) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView0, drawable);
            this.mboundView0.setFocusable(z);
            this.mboundView0.setFocusableInTouchMode(z);
        }
        if (j3 != 0) {
            this.mboundView0.setOnClickListener(onClickListenerImpl);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private MediaHeaderAdapter.ViewHolder value;

        public OnClickListenerImpl setValue(MediaHeaderAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }
}
